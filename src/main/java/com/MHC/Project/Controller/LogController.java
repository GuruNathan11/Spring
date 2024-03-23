package com.MHC.Project.Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api/logs")
@SecurityRequirement(name = "mettlerHealth")
public class LogController {

	@GetMapping("/app.log")
	public ResponseEntity<String> getAppLog() throws IOException {
		File logFile = new File("logs/app.log");

		if (!logFile.exists()) {
			return ResponseEntity.notFound().build();
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
			StringBuilder logContent = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				logContent.append(line).append("\n");
			}
			return ResponseEntity.ok().body(logContent.toString());
		}
	}
	
	@GetMapping("/app.{date}.log")
    public ResponseEntity<String> getAppLogByDate(@PathVariable String date) throws IOException {
        File logFile = new File("logs/app."+date+".log");
        
        if (!logFile.exists()) {
            return ResponseEntity.notFound().build();
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            StringBuilder logContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                logContent.append(line).append("\n");
            }
            return ResponseEntity.ok().body(logContent.toString());
        }
    }
}
