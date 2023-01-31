package com.example.repository;

import com.example.models.Docs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepo extends MongoRepository<Docs, String> { }