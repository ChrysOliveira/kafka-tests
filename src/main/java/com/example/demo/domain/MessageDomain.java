package com.example.demo.domain;

import jakarta.validation.constraints.NotNull;

public record MessageDomain(@NotNull String message) {
}
