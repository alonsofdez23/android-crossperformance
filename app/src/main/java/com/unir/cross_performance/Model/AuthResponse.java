package com.unir.cross_performance.Model;

public class AuthResponse {
    private String status;
    private String message;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
