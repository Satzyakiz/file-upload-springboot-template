package com.example.controllers;

import com.example.models.Docs;

import com.example.models.DocsResponse;
import com.example.response.GetResponse;
import com.example.response.PostResponse;
import com.example.services.DocService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DocController {

    @PostMapping("/docs")
    public PostResponse upload(@RequestParam("title") String title,
                             @RequestParam("file") MultipartFile file){
        DocService docService = new DocService();
        try{
//            String title = (String)payload.get("title");
//            MultipartFile file = (MultipartFile) payload.get("file");
            System.out.println(docService.addDoc(title, file));
        }catch(Exception e){
            e.printStackTrace();
            return new PostResponse("failure");
        }
        return  new PostResponse("success");
    }
    @GetMapping("/docs")
    public GetResponse data(){
        DocService docService = new DocService();
        List<DocsResponse> data;
        try{
            System.out.println("Getting elements - DocController.java");
            data = docService.getAllDocs();
        }catch(Exception e){
            e.printStackTrace();
            return new GetResponse("failure", null);
        }
        return new GetResponse("success", data);
    }
}
