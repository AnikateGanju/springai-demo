package com.example.ai.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DemoDataLoader {

    private final RagRepository repository;

    public DemoDataLoader(RagRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        // Seed a couple demo documents (replace with real loaders in prod)
        repository.upsertDocuments(List.of(
                new Document("Our refund policy allows returns within 30 days with receipt."),
                new Document("Premium plan includes 24/7 support and 99.95% uptime SLA."),
                new Document("To reset your password, use the corporate SSO at sso.example.com.")
        ));
    }
}
