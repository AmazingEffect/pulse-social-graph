package com.pulse.social_graph.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // Common
    METHOD_NOT_ALLOWED("SOG001", "Method not allowed"),
    ENTITY_NOT_FOUND("SOG003", "Entity not found"),
    HANDLE_ACCESS_DENIED("SOG006", "Access is denied"),

    INVALID_TYPE_VALUE("SOG005", "Invalid type value"),
    INVALID_INPUT_VALUE("SOG002", "Invalid input value"),
    INVALID_INPUT_FORMAT("SOG004", "Invalid input format"),
    VALIDATION_FAILED("SOG007", "Validation failed"),


    UNEXPECTED_ERROR("SOG999", "Unexpected error"),
    INTERNAL_SERVER_ERROR("SOG500", "Server error"),
    INVALID_MESSAGE_STATUS("SOG008", "Invalid message status"),
    ;

    private final String code;
    private final String message;

}
