package com.MHC.Project.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.down;
import com.MHC.Project.Repository.DropdownRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/dropdowns")
@SecurityRequirement(name = "mettlerHealth")
public class DropdownController {

	private final DropdownRepository dropdownRepository;

	public DropdownController(DropdownRepository dropdownRepository) {
		this.dropdownRepository = dropdownRepository;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addDrop(@RequestBody down newDown) {
		// Check if a record with the same ID and dropdown exists
		down existingDown = dropdownRepository.findByIdAndDropdown(newDown.getId(), newDown.getDropdown());

		if (existingDown != null) {
			// Update the existing record with the new data
			existingDown.setList(newDown.getList()); // Update any other fields as needed
			dropdownRepository.save(existingDown);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, existingDown);
			return ResponseEntity.ok(dataResponse);
		} else {
			newDown.setId(generateUID());
			dropdownRepository.save(newDown);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, newDown);
			return ResponseEntity.ok(dataResponse);
		}
	}

	@GetMapping("/getByDropdown")
	public ResponseEntity<?> getDropdownData(@RequestParam String dropdown) {
		List<down> dropdownData = dropdownRepository.findByDropdown(dropdown);

		if (!dropdownData.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, dropdownData);
			return ResponseEntity.ok(dataResponse);
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
		}
	}

	@GetMapping("/get-all")
	public ResponseEntity<?> getAll() {
		List<down> allDowns = dropdownRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, allDowns);
		return ResponseEntity.ok(dataResponse);
	}
	
	@GetMapping("/all-dropdowns")
  public ResponseEntity<List<String>> getAllDropdowns() {
      List<down> allDowns = dropdownRepository.findAll();
      List<String> dropdownList = allDowns.stream()
              .map(down::getDropdown) // Extract the "dropdown" field
              .collect(Collectors.toList());
      return ResponseEntity.ok(dropdownList);
  }

	public static String generateUID() {
		// Generate a random alphanumeric ID with 10 digits
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

}