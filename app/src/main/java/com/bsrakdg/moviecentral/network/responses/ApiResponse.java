package com.bsrakdg.moviecentral.network.responses;

import retrofit2.Response;

/**
 * T is a response class. This should be GenresResponse
 * ApiResponse class convert responses classes from Response to ApiResponse<Response>
 * <p>
 * There are three ApiResponse format :
 * <p>
 * ApiSuccessResponse returns body
 * ApiErrorResponse returns error message with body
 * ApiEmptyResponse return anything
 * <p>
 * This response (ApiResponse<Response>) handled on Repository
 */

public class ApiResponse<T> {

    public <R> ApiResponse<R> create(Response<R> response) {
        if (response == null || response.body() == null) {
            return new ApiEmptyResponse<R>();
        }

        if (response.isSuccessful()) {
            return new ApiSuccessResponse<>(response.body());
        } else {
            return new ApiErrorResponse<>(response.message());
        }
    }

    public <R> ApiResponse<R> create(Throwable t) {
        return new ApiErrorResponse<>(t.getLocalizedMessage());
    }

    public class ApiSuccessResponse<T> extends ApiResponse<T> {
        private T body;

        public ApiSuccessResponse(T body) {
            this.body = body;
        }

        public T getBody() {
            return body;
        }
    }

    public class ApiErrorResponse<T> extends ApiResponse<T> {
        private String message;

        public ApiErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public class ApiEmptyResponse<T> extends ApiResponse<T> {

    }
}
