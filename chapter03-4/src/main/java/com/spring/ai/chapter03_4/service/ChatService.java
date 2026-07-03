package com.spring.ai.chapter03_4.service;

import com.spring.ai.chapter03_4.entity.Tutorial;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getResponse(String query) {
        return chatClient.prompt()
                .user(query)
                .system("스포츠 전문가로서 답해줘.")
                .call()
                .content();
    }

    public String simpleChat(String query) {
        Prompt prompt = new Prompt(query);
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    public String getDetailedContent(String query) {
        Prompt prompt = new Prompt(query);
        var content = chatClient.prompt(prompt)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
        System.out.println(content);
        return content;
    }

    public String getMetaData(String query) {
        Prompt prompt = new Prompt(query);
        var metaData = chatClient.prompt(prompt)
                .call()
                .chatResponse()
                .getMetadata();
        System.out.println(metaData);
        return metaData.toString();
    }

    public Tutorial getEntity(String query) {
        Prompt prompt = new Prompt(query);
        return chatClient.prompt(prompt)
                .call()
                .entity(Tutorial.class);
    }

    public List<String> getStringList(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .entity(new ParameterizedTypeReference<List<String>>() {});
    }

    public List<Tutorial> getTutorialList(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .entity(new ParameterizedTypeReference<List<Tutorial>>() {});
    }

    public String getPriorityTestResponse(String query) {
        OpenAiChatOptions requestOptions = OpenAiChatOptions.builder().temperature(1.2).build();
        return chatClient.prompt(new Prompt(query,requestOptions)).call().content();
    }

}