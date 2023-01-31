package com.example.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "uploads")
public class Docs {

    @Id
    private String id;

    private String title;

    private Binary doc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Binary getDoc() {
        return doc;
    }
    public void setDoc(Binary doc) {
        this.doc = doc;
    }




}
