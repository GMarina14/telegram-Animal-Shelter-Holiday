package com.example.telegramanimalshelterholiday.component;

import com.pengrad.telegrambot.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProcessingAssistant {
    private static final Logger logger = LoggerFactory.getLogger(MessageProcessingAssistant.class);
    private  final TelegramBot telegramBot;

}
