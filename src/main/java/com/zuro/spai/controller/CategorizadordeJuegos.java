package com.zuro.spai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorizador")
public class CategorizadordeJuegos {

    private final ChatClient chatClient;

    public CategorizadordeJuegos(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String CategorizarJuegos(String juego){

        var sistema = "Tu eres un categorizador de juegos que se limita a 20 palabras y solo categoriza juegos en caso de que sea cualquier otro texto que no se relacione con videojuegos incluso si es tecnologico como cosas raras como pc android etc entonces simplemente escribe: Nulo";

        return this.chatClient.prompt()
                .system(sistema)
                .user(juego)
                .options(ChatOptionsBuilder
                        .builder()
                        .withTemperature(0.29)
                        .build())
                .call()
                .content();
    }

}
