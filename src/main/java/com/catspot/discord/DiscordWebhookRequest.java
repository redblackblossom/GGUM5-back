package com.catspot.discord;

public class DiscordWebhookRequest {
    private String content;

    public DiscordWebhookRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

