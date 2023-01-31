package com.example.response;

import com.example.models.Docs;
import com.example.models.DocsResponse;

import java.util.List;

public record GetResponse(String status, List<DocsResponse> data) {
}
