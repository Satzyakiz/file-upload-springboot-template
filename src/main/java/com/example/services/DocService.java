package com.example.services;


import com.example.models.Docs;
import com.example.models.DocsResponse;
import com.example.repository.DocRepo;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Component
public class DocService {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString = "mongodb+srv://<usersame>:<password>@uploads.bbfumnp.mongodb.net/?retryWrites=true&w=majority";

    private MongoCollection<Document> collection;
    @Bean
    public MongoCollection<Document> mongoClient() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClient mongoClientObj = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .codecRegistry(codecRegistry)
                .build());
        MongoDatabase db = mongoClientObj.getDatabase("file-uploads");
        MongoCollection<Document> collection = db.getCollection("uploads");
        return collection;
    }

    public DocService(){
        this.collection = this.mongoClient();
    }
    public String addDoc(String title, MultipartFile file) throws IOException{
        Docs doc = new Docs();
        doc.setTitle(title);
        doc.setId(""+ (collection.countDocuments()+1));
        doc.setDoc(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        // database and collection code goes here
        Document docc = new Document("title", doc.getTitle()).append("id", doc.getId()).append("file", doc.getDoc());
        collection.insertOne(docc);
        return doc.getId();
    }

//    public Docs getDocs(String id){
//        return docRepo.findById(id).get();
//    }

    public List<DocsResponse> getAllDocs(){

        MongoCursor<Document> cursor = collection.find().iterator();

        // iterate code goes here
        System.out.println("Getting elements - inside getAllDocs - DocService.java");
        List<DocsResponse> data = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
//                System.out.println(doc.toJson());
                Docs newDoc = new Docs();
                newDoc.setTitle((String)doc.get("title"));
                newDoc.setDoc((Binary)doc.get("file"));
                DocsResponse res = new DocsResponse(newDoc);
                data.add(res);
//                System.out.println(doc.get("title"));/
            }
        } finally {
            cursor.close();
        }
        return data;
    }
}
