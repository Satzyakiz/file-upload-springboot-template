package com.example.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Document(collection = "uploads")
public class DocsResponse {
    @Id
    private String id;

    private String title;

    private String doc;


    public DocsResponse(){}

    public DocsResponse(Docs doc){
        this.setId(doc.getId());
        this.setTitle(doc.getTitle());
        this.setDoc(doc.getDoc());
    }
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

    public String getDoc() {
        return doc;
    }
    public void setDoc(Binary doc) {
        this.doc = Base64.getEncoder().encodeToString(doc.getData());
    }
}
