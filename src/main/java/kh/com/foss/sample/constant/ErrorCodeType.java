package kh.com.foss.sample.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCodeType {
    GENERAL_ERROR("E0001", "An error occurred"),
    INVALID_REQUEST("E0002", "Invalid request"),
    DUPLICATE_PHONE("E0003", "Duplicated phone number");

    private final String code;
    private final String message;
}
