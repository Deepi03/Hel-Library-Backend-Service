package com.rest_api.fs14backend.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranscationDto {
    private UUID bookId;
    private UUID userId;
}

