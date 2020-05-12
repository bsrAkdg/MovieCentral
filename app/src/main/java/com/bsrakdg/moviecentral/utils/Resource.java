package com.bsrakdg.moviecentral.utils;

import androidx.annotation.NonNull;

/** A generic class that contains data and status about loading this data.
 * https://developer.android.com/jetpack/docs/guide#addendum
 */
public class Resource<T> {

    private T body;
    private String message;
    private Status status;

    private Resource(T body, String message, Status status) {
        this.body = body;
        this.message = message;
        this.status = status;
    }

    public static <T> Resource<T> loading() {
        return new Resource<T>(null, null, Status.LOADING);
    }

    public static <T> Resource<T> error(String message) {
        return new Resource<T>(null, message, Status.ERROR);
    }

    @NonNull
    public static <T> Resource<T> success(T body) {
        return new Resource<>(body, null, Status.SUCCESS);
    }

    public T getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }
}
