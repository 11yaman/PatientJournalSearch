package com.example.dto;

import java.time.LocalDateTime;

public record ReplyDto(Long id, String content, LocalDateTime dateTime, UserDto sender) {
}
