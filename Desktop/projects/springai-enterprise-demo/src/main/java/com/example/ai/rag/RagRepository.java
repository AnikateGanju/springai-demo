package com.example.ai.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RagRepository {
    private final VectorStore vectorStore;

    public RagRepository(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void upsertDocuments(List<Document> docs) {
        vectorStore.add(docs);
    }

    public List<Document> similaritySearch(String query, int topK) {
        return vectorStore.similaritySearch(query, topK);
    }
}
