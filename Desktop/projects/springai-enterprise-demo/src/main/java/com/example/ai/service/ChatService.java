package com.example.ai.service;

import com.example.ai.rag.RagRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final RagRepository ragRepository;

    public ChatService(ChatClient.Builder chatClientBuilder, RagRepository ragRepository) {
        this.chatClient = chatClientBuilder.build();
        this.ragRepository = ragRepository;
    }

    public String answerWithRag(String question) {
        List<Document> docs = ragRepository.similaritySearch(question, 4);
        String context = docs.stream()
                .map(d -> d.getContent())
                .collect(Collectors.joining("\n---\n"));

        String system = """You are an enterprise assistant. Use the provided CONTEXT to answer precisely.
If the CONTEXT is irrelevant or insufficient, say you don't know and suggest next steps.
Cite sources as [Doc i] where i is the snippet order in the CONTEXT.""";

        String user = """Question:
%s

CONTEXT:
%s""".formatted(question, context.isBlank() ? "(no relevant context)" : context);

        var response = chatClient.prompt(new Prompt(List.of(
                new UserMessage(system),
                new UserMessage(user)
        ))).call();

        AssistantMessage msg = response.getResult().getOutput();
        return msg.getText();
    }
}
