package com.example;

import com.example.repository.DocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@EnableMongoRepositories
public class FileUploadApplication {

	@Autowired
	DocRepo docRepo;
	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

}
