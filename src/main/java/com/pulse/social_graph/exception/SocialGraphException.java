package com.pulse.social_graph.exception;

import lombok.Getter;

@Getter
public class SocialGraphException extends RuntimeException {

    private final ErrorCode errorCode;

    public SocialGraphException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SocialGraphException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

}
