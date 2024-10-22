package com.catspot.exceptionhandler;

// 에러 응답을 위한 DTO 클래스
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status,String message) {
        this.status = status;
        this.message = message;
    }

    // 게터 및 세터
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

