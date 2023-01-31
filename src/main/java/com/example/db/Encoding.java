package com.example.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import java.net.URLEncoder;

public class Encoding {

    public static void main(String [] args){

        try{


            String uri = "mongodb+srv://satzy:Satan108@uploads.bbfumnp.mongodb.net/?retryWrites=true&w=majority";

            MongoClient mongoClient = MongoClients.create(uri);

            MongoDatabase database = mongoClient.getDatabase("file-uploads");
            MongoCollection<Document> collection = database.getCollection("uploads");

            collection.find().forEach(doc -> System.out.println(doc.toJson()));

        } catch(Exception e){
            System.err.println(e.getCause());

        }
    }
}
