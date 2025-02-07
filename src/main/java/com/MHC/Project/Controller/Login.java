package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Error.JwtResponse;
import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Session;
import com.MHC.Project.Model.Staff;
import com.MHC.Project.Model.User;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.SessionRepository;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Repository.UserRepository;
import com.MHC.Project.Request.ForgotPasswordRequest;
import com.MHC.Project.Request.LoginRequest;
import com.MHC.Project.Request.RecreatePasswordRequest;
import com.MHC.Project.Request.ResetPasswordRequest;
import com.MHC.Project.Request.ResetSecretRequest;
import com.MHC.Project.Request.SignoutRequest;
import com.MHC.Project.Request.VerifyOtpRequest;
import com.MHC.Project.Request.VerifyRequest;
import com.MHC.Project.Response.ResetPasswordResponse;
import com.MHC.Project.Response.SignInResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Security.Jwt.JwtUtils;
import com.MHC.Project.Security.Service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class Login {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	StaffRepository staffRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	public static String generateSecretKey() {
		int secretKeyLength = 6;
		int min = (int) Math.pow(10, secretKeyLength - 1);
		int max = (int) Math.pow(10, secretKeyLength) - 1;
		return String.valueOf((int) (Math.random() * (max - min + 1) + min));
	}

	// ----------------------------- Sign - in
	// -------------------------------------------------------------------------

	private static final int MAX_WRONG_PASSWORD_ATTEMPTS = 3;
	private static final long TEMPORARY_BLOCK_DURATION_MINUTES = 5;
	int EXPIRATION_MINUTES = 5;

	@PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {

	      // Retrieve the existing session (if any)
	      List<Session> existingSession = sessionRepository.findByUsername(loginRequest.getUsername());
	      
	      Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

	      if (userOptional.isEmpty()) {
	          return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0005"));
	      }
	      User user = userOptional.get();
	      
	      Authentication authentication;
	      try {
	          authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	      } catch (BadCredentialsException e) {
	          int wrongPasswordAttemptCount = incrementWrongPasswordAttemptCount(loginRequest.getUsername());
	          System.out.print("count  " + wrongPasswordAttemptCount + "  ");

	          // If the maximum wrong password attempts are reached, temporarily block the user's account
	          if (wrongPasswordAttemptCount >= MAX_WRONG_PASSWORD_ATTEMPTS) {
	              blockUserAccount(loginRequest.getUsername());
	              return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0003"));
	          }

	          return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0007"));
	      }
	      Optional<Organization> organizationOptional = organizationRepository.findById(user.getOrganization());

	      if (organizationOptional.isEmpty()) {
	          return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0025"));
	      }

	      if (existingSession != null ) {
	          // Delete the existing session
	          sessionRepository.deleteAll(existingSession);
	      }

	      // Check if the user is temporarily blocked
	      if (isUserTemporarilyBlocked(loginRequest.getUsername())) {
	          return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0003"));
	      }
	      // If the password is correct, reset the wrong password attempt count
	      resetWrongPasswordAttemptCount(loginRequest.getUsername());
	      // Create a new session
	      Session session = new Session();
	      session.setUsername(loginRequest.getUsername());
	      session.setSessionId(UUID.randomUUID().toString());
	      session.setCreatedDate(LocalDateTime.now());

	      // Set the expiry time as a future timestamp
	      LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
	      session.setExpireTime(expiryTime);
	      sessionRepository.save(session);

	      // Set the authentication in the SecurityContextHolder
	      SecurityContextHolder.getContext().setAuthentication(authentication);

	      // Retrieve the user type from the UserDetailsImpl
	      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	      Set<String> userType = userDetails.getAuthorities()
	              .stream()
	              .map(GrantedAuthority::getAuthority)
	              .collect(Collectors.toSet());

	      // Generate a new JWT token and cookie
	      ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails, userType);
//	      System.out.println(userType);

	      // Set the JWT token in the User entity
	      user.setJwt(jwtCookie.getValue());

	      // Save the updated User entity to the database
	      userRepository.save(user);
	     
	      int resetCount = user.getResetCount();
	      
	      // Convert userType to lowercase for case-insensitive comparison
	      Set<String> lowercaseUserType = userType.stream()
	              .map(String::toLowerCase)
	              .collect(Collectors.toSet());

	      // Retrieve the user details based on userType
	      Object userDetail = null; // Declare userDetail outside of the if-else blocks

	      if (lowercaseUserType.contains("patient")) {
	          Optional<Patient> patientDetail = patientRepository.findByUsername(loginRequest.getUsername());
	          if (patientDetail.isPresent()) {
	              userDetail = patientDetail.get(); // Assign the value if it's present
	          }
	      } else if(lowercaseUserType.contains("super admin")) {
	    	  
	    	  if(organizationOptional.isPresent()) {
	    		  userDetail = organizationOptional.get();
	    	  }
	      } else {
	          Optional<Staff> staffDetail = staffRepository.findByUsername(loginRequest.getUsername());
	          if (staffDetail != null) {
	              userDetail = staffDetail; // Assign the value if it's not null
	          }
	      }
	      
	      // Prepare the response body with session and JWT information
	      Map<String, Object> responseBody = new HashMap<>();
	      responseBody.put("session", session);
	      responseBody.put("jwt", new JwtResponse(jwtCookie.getValue()));
	      responseBody.put("organization", user.getOrganization());
	      responseBody.put("userType", userType);
	      responseBody.put("resetCount",resetCount);
	      responseBody.put("userDetail", userDetail);

	      SignInResponse.Response verifyResponse = SignInResponse.createVerifyResponse("MHC - 0208");

	      // Create the SignInResponse object
	      SignInResponse signInResponse = new SignInResponse(verifyResponse, responseBody);

	      // Return the response with the SignInResponse object
	      return ResponseEntity.ok()
	              .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
	              .body(signInResponse);
	  }
	private Map<String, Integer> wrongPasswordAttemptCountMap = new HashMap<>();
	private Map<String, Boolean> blockedUsersMap = new HashMap<>();

	private int incrementWrongPasswordAttemptCount(String username) {

		String key = "WRONG_PASSWORD_ATTEMPTS_" + username;
		int attempts = getWrongPasswordAttemptCount(username) + 1;
		cacheWrongPasswordAttemptCount(key, attempts);
		return attempts;
	}
	private int getWrongPasswordAttemptCount(String username) {
		String key = "WRONG_PASSWORD_ATTEMPTS_" + username;
		Integer attempts = wrongPasswordAttemptCountMap.getOrDefault(key, 0);
		return attempts != null ? attempts : 0;
	}

	private void cacheWrongPasswordAttemptCount(String key, int attempts) {
		wrongPasswordAttemptCountMap.put(key, attempts);
	}

	private void resetWrongPasswordAttemptCount(String username) {
		String key = "WRONG_PASSWORD_ATTEMPTS_" + username;
		wrongPasswordAttemptCountMap.remove(key);
	}

	private boolean isUserTemporarilyBlocked(String username) {
		String key = "BLOCKED_USER_" + username;
		return blockedUsersMap.containsKey(key);
	}

	private void blockUserAccount(String username) {
		String key = "BLOCKED_USER_" + username;
		blockedUsersMap.put(key, true);
		// Schedule a task to unblock the user after the specified duration
		Executors.newScheduledThreadPool(1).schedule(() -> {
			blockedUsersMap.remove(key);
		}, TEMPORARY_BLOCK_DURATION_MINUTES, TimeUnit.MINUTES);
	}
	
	
	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/unblock")
	public ResponseEntity<?> unblockUser(@RequestParam String username, @RequestParam String secretKey) {
		if (unblockAccount(username, secretKey)) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0218"));
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0280"));
		}
	}

	private boolean unblockAccount(String username, String secretKey) {
		// Check if the user is temporarily blocked
		if (!isUserTemporarilyBlocked(username)) {
			return false; // Account is not blocked
		}

		// check the username and secret key
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();

			String secretKey1 = user.getSecretKey();
			if (secretKey1 == null || !secretKey1.equals(secretKey)) {
				return false;
			}

		}

		// Unblock the user account
		String blockedUserKey = "BLOCKED_USER_" + username;
		blockedUsersMap.remove(blockedUserKey);
		return true; // Account unblocked successfully
	}
	// -------------------------------Verify-------------------------------

	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/verify")
	public ResponseEntity<?> verifyUser(@RequestBody VerifyRequest verifyRequest) {
		String secretKey = verifyRequest.getSecretKey();
		String jwt = verifyRequest.getJwt();

		// Verify the JWT token
		boolean isJwtValid = jwtUtils.validateJwtToken(jwt);
		if (!isJwtValid) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0010"));
		}

		// Retrieve the username from the JWT token
		String usernameFromJwt = jwtUtils.getUserNameFromJwtToken(jwt);
	    if (usernameFromJwt == null) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0012"));
	    }

	    // Check if the secret key is valid and retrieve the user from the UserRepository
	    Optional<User> userOptional = userRepository.findByUsername(usernameFromJwt);
	    if (userOptional.isEmpty()) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0005"));
	    }

	    User user = userOptional.get();

	    // Compare the stored secret key with the provided secret key
	    if (!user.getSecretKey().equals(secretKey)) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0011"));
	    }

	    // If the secret key matches, return success message
	    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0201"));
	}
//-------------------------------Check - Login -------------------------------------------

	@SecurityRequirement(name = "mettlerHealth")
	@GetMapping("/check-login")
	public ResponseEntity<?> checkLogin(HttpServletRequest request) {
		// Get the JWT token from the request headers
		String jwtToken = jwtUtils.getJwtFromRequest(request);

		// Check if the JWT token is valid
		if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
			// Retrieve the username from the JWT token
			String username = jwtUtils.getUserNameFromJwtToken(jwtToken);

			// Retrieve the session by username from the repository
			List<Session> existingSession = sessionRepository.findByUsername(username);

			// Check if the session exists and is not expired
			if (existingSession != null ) {
				// User is signed in, return a success response
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0207"));
			} else {
				// User is not signed in or session expired, return an error response
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0020"));
			}
		} else {
			// JWT token is invalid or not provided, return an error response
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0021"));
		}
	}

//--------------------------------SIGN-OUT-------------------------------------------------------------------------------------------------------------

	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser(@Valid @RequestBody SignoutRequest signoutRequest, HttpServletRequest request,
			HttpServletResponse response) {

		// Verify the provided username and JWT token from the UserRepository
		Optional<User> optionalUser = userRepository.findByUsername(signoutRequest.getUsername());
		

		if (optionalUser.isEmpty() || !isJwtValid(optionalUser.get(), signoutRequest.getJwt())) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0013"));
		}

		// Retrieve the existing session from the repository
		List<Session> existingSession = sessionRepository.findByUsername(signoutRequest.getUsername());

		if (existingSession != null ) {
			// Delete the existing session
			sessionRepository.deleteAll(existingSession);
		}

		// Get the JWT token from the request body
		String jwtToken = signoutRequest.getJwt();

		if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
			// Invalidate the JWT token
			jwtUtils.invalidateJwtToken(jwtToken);

			// Remove or set the JWT value to null in the User entity
			User user = optionalUser.get();
			user.setJwt(null); // Set the JWT value to null
			userRepository.save(user);

			// Set the JWT expiration time to the current time to invalidate the token
			Claims claims = Jwts.parser().setSigningKey(jwtUtils.getJwtSecret()).parseClaimsJws(jwtToken).getBody();
			Date expirationDate = claims.getExpiration();
			Date now = new Date();
			if (expirationDate.after(now)) {
				claims.setExpiration(now);
				jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtUtils.getJwtSecret())
						.compact();
			}

			ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
			response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0202"));
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0013"));
		}
	}

	// Helper method to check if the provided JWT matches the user's stored JWT
	// (handles null case)
	private boolean isJwtValid(User user, String providedJwt) {
		String storedJwt = user.getJwt();
		return providedJwt != null && storedJwt != null && storedJwt.equals(providedJwt);
	}


//--------------------------------FORGOT - PASSWORD-------------------------------------------------------------------------------------------------------------
	private void sendOTPEmail(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Reset Password OTP");
		message.setText("Your OTP is: " + otp);
		mailSender.send(message);
	}

	private String generateOTP() {
		int otpLength = 6;
		int min = (int) Math.pow(10, otpLength - 1);
		int max = (int) Math.pow(10, otpLength) - 1;
		return String.valueOf((int) (Math.random() * (max - min + 1) + min));
	}

	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		// Check if the email exists in the database
		User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
		if (user == null) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0014"));
		}
		// Generate OTP
		String otp = generateOTP();
		user.setResetOtp(otp);
		user.setResetOtpExpiration(LocalDateTime.now().plusMinutes(15)); // Set OTP expiration to 15 minutes from now
		userRepository.save(user);

		// Send OTP to the user's email
		sendOTPEmail(forgotPasswordRequest.getEmail(), otp);
		return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0203"));

	}

//--------------------------------RESET - PASSWORD-------------------------------------------------------------------------------------------------------------

	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@Valid @RequestBody VerifyOtpRequest verifyotp) {
		String otp = verifyotp.getOtp();
		String email = verifyotp.getEmail();

		// Find the user by the OTP

		User user = userRepository.findByResetOtpAndEmail(otp, email);

		// Check if the user exists and the OTP has not expired
		if (user == null || LocalDateTime.now().isAfter(user.getResetOtpExpiration())) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0015"));
		}
		// Reset the OTP and OTP expiration in the user object
		user.setResetOtp(null);
		user.setResetOtpExpiration(null);
		userRepository.save(user);

		return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0204"));
	}

	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
		// Check if the new password and confirm new password match
		if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPass())) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0016"));
		}

		// Check if the email and new password are provided
		String email = resetPasswordRequest.getEmail();
		String newPassword = resetPasswordRequest.getNewPassword();
		if (email == null || email.isEmpty() || newPassword == null || newPassword.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0018"));
		}

		// Retrieve the user from the repository
		User user = userRepository.findByEmail(email);

		// Check if the user exists
		if (user == null) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0017"));
		}

		// Update the user password
		user.setPassword(encoder.encode(newPassword));

		// Increase the reset count if password reset was successful
		user.incrementResetCount();

		// Save the user entity
		userRepository.save(user);

		// Get the reset count from the user entity
		int resetCount = user.getResetCount();

		// Create a response object with reset count and success message
		ResetPasswordResponse response = new ResetPasswordResponse("MHC - 0200", "Password reset successful.",
				resetCount);

		return ResponseEntity.ok(response);
	}

//--------------- Reset - SecretKey ----------------------------------------------------------------- 

	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/resetSecretKey")
	public ResponseEntity<?> resetSecretKey(@Valid @RequestBody ResetSecretRequest resetRequest) {

		String jwt = resetRequest.getJwt();

		// Check if the email exists in the database
		User user = userRepository.findByEmail(resetRequest.getEmail());
		if (user == null) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0014"));
		}

		// Verify the JWT token
		if (!isJwtValid(user, jwt)) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0010"));
		}

		// Generate a new secret key
		String newSecretKey = generateSecretKey();

		// Update the user's secret key and expiration time
		user.setSecretKey(newSecretKey);
		user.setSecretKeyExpiration(LocalDateTime.now().plusDays(60)); // Set OTP expiration to 60 days from now
		userRepository.save(user);

		// Send the new secret key to the user's email
		sendVerificationEmail(user.getEmail(), newSecretKey);

		return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0206"));
	}

	private void sendVerificationEmail(String email, String newSecretKey) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("secretKey");
		message.setText("Your secretKey is: " + newSecretKey);
		mailSender.send(message);

	}
	
	@SecurityRequirement(name = "mettlerHealth")
	@PostMapping("/recreatePassword")
	public ResponseEntity<?> recreatePassword(@RequestBody RecreatePasswordRequest resetPasswordRequest) {
		String oldPassword = resetPasswordRequest.getOldPassword();
	    String newPassword = resetPasswordRequest.getNewPassword();
	    String confirmNewPassword = resetPasswordRequest.getConfirmNewPassword();
	    String username = resetPasswordRequest.getUsername(); // New field added

	    // Retrieve the user from the database based on the provided username
	    Optional<User> userOptional = userRepository.findByUsername(username);
	    if (userOptional.isEmpty()) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0017"));
	    }

	    User user = userOptional.get();

	 // Check if the old password matches the stored password
	    if (!encoder.matches(oldPassword, user.getPassword())) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0049"));
	    }

	    // Check if the new password and confirmation are provided and match
	    if (newPassword == null || confirmNewPassword == null || !newPassword.equals(confirmNewPassword)) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0050"));
	    }

	    // Check if the new password is strong (you can adjust this condition)
//	    if (!isStrongPassword(newPassword)) {
//	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0113"));
//	    }

	    // Set the new password for the user
	    user.setPassword(encoder.encode(newPassword));
	    userRepository.save(user);
	    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0205"));
	}
	private boolean isStrongPassword(String password) {
	    // Define your password strength criteria
	    int minLength = 8;          // Minimum password length
	    boolean hasUppercase = false;  // At least one uppercase letter
	    boolean hasLowercase = false;  // At least one lowercase letter
	    boolean hasDigit = false;      // At least one digit
	    boolean hasSpecial = false;    // At least one special character (e.g., !@#$%^&*)

	    if (password.length() < minLength) {
	        return false;
	    }

	    for (char ch : password.toCharArray()) {
	        if (Character.isUpperCase(ch)) {
	            hasUppercase = true;
	        } else if (Character.isLowerCase(ch)) {
	            hasLowercase = true;
	        } else if (Character.isDigit(ch)) {
	            hasDigit = true;
	        } else if (isSpecialCharacter(ch)) {
	            hasSpecial = true;
	        }
	    }

	    return hasUppercase && hasLowercase && hasDigit && hasSpecial;
	}

	private boolean isSpecialCharacter(char ch) {
	    // Define your set of special characters here
	    String specialCharacters = "!@#$%^&*()_-+=<>?/[]{}|";
	    return specialCharacters.indexOf(ch) != -1;
	}

}