package com.MHC.Project.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Q15Form;
import com.MHC.Project.Repository.Q15FormRepository;


@Service
public class Q15FormService {

	@Autowired
	private Q15FormRepository repository;
	
	public Q15Form saveQ15Form(Q15Form q15FormRequest) {
		String uid = generateUID();
		
		Q15Form q15Form = new Q15Form(uid, q15FormRequest.getLocation(), q15FormRequest.getActivity(), q15FormRequest.getQ15());
		q15Form.setId(uid);
		return repository.save(q15Form);
	}
	
	// *************** Get All Organization *************\\
	public List<Q15Form> getAllForm() {
		return repository.findAll();
	}
	
	// ***************** Get the Config Details Based on PID ****************\\
	public Optional<Q15Form> getQ15Form(String id) throws PageNotFound {
	    Optional<Q15Form> configs = repository.findById(id);
	    if (configs.isEmpty()) {
	        throw new PageNotFound("No records found for the given id: " + id);
	    }
	    return configs;
	}	
	
	// ***************** Update the Config Details Based on PID ****************\\
	public Q15Form updateQ15Form(String id, Q15Form q15FormRequest) throws PageNotFound {
		Optional<Q15Form> q15FormOptional = repository.findById(id);
		if (q15FormOptional.isPresent()) {
			Q15Form q15Form = q15FormOptional.get();
			q15Form.setLocation(q15FormRequest.getLocation());
			q15Form.setActivity(q15FormRequest.getActivity());
			q15Form.setQ15(q15FormRequest.getQ15());
			return repository.save(q15Form);
		} else {
			throw new PageNotFound("Q15Form not found with id: " + id);
		}
	}
	
	private String generateUID() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
}
