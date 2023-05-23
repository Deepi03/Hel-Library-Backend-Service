package com.rest_api.fs14backend.transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BorrowDto extends TranscationDto {
    private Day day;

    enum Day {
            TEN, TWENTY,THIRTY
    }

    public BorrowDto(UUID bookId, UUID userId, Day day) {
        super(bookId, userId);
        this.day = day;
    }
}
