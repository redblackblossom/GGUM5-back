package com.catspot.discord;

import com.catspot.dto.request.ClassroomRequestDto;
import com.catspot.dto.response.ClassroomGetResponseDto;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class DiscordController {
    private final String webhookUrl;
    private final RestTemplate restTemplate;

    public DiscordController(@Value("${discord.webhook.url}") String webhookUrl, RestTemplate restTemplate) {
        this.webhookUrl = webhookUrl;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/suggestion")
    public ResponseEntity<String> sendToDiscord(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String email = request.get("email");
        String body = request.get("body");

        String content = "> **" + title + "**(" + email + ")" + "\n"
                + "```" + body + "```";

        DiscordWebhookRequest discordRequest = new DiscordWebhookRequest(content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DiscordWebhookRequest> entity = new HttpEntity<>(discordRequest, headers);

        restTemplate.postForEntity(webhookUrl, entity, String.class);

        return ResponseEntity.status(HttpStatus.OK).body("디스코드에 전송 완료");
    }
}
