package com.MHC.Project.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.MHC.Project.Model.ImplantableDevice;

import java.util.List;

@Repository
public class ImplantableDeviceCustomRepository {

	 private final MongoTemplate mongoTemplate;

	    @Autowired
	    public ImplantableDeviceCustomRepository(MongoTemplate mongoTemplate) { 
	        this.mongoTemplate = mongoTemplate;
	    }

	    public List<ImplantableDevice> findByDeviceId(String deviceId) {
	        Query query = new Query();
	        query.addCriteria(Criteria.where("data.gudid.device.identifiers.identifier.deviceId").is(deviceId));

	        return mongoTemplate.find(query, ImplantableDevice.class);
	    }
}
