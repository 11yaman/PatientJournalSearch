package com.example.dto;

import com.example.model.Message;

import java.time.LocalDateTime;

public record MessageDto(Long id, String content, LocalDateTime dateTime,
                         UserDto sender, Message.Status status) {
}
