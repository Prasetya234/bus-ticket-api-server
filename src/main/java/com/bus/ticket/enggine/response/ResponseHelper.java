package com.bus.ticket.enggine.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static <T> CommonResponse<T> successResponse(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setStatus(String.valueOf(HttpStatus.OK.value()));
        response.setMessage(HttpStatus.OK.name());
        response.setData(data);
        return response;
    }

    public static <T> ResponseEntity<CommonResponseErr<T>> errorResponse(T message, HttpStatus http) {
        CommonResponseErr<T> response = new CommonResponseErr<>();
        response.setStatus(String.valueOf(http.value()));
        response.setMessage(http.name());
        response.setError((T) message);
        return new ResponseEntity<>(response, http);
    }
}
