package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.User;

public interface UserRepository extends MongoRepository<User , String> {

	User findByUsernameAndOrganization(String username, String organization);
	Optional<User> findByUsername(String username);
	List<User> findByOrganization(String organization);
	User findByEmail(String email);
	void deleteByUsername(String username);
	Object findByPassword(String password);
	User findByResetOtpAndEmail(String otp, String email);
	User findBySecretKey(String secretKey);
	void deleteByEmail(String email);
}
