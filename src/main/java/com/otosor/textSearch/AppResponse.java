package com.otosor.textSearch;

import lombok.Data;

@Data
public class AppResponse {
    private boolean successful;
    private String message;
    private Object data;

    public static AppResponse ok(String message) {
        AppResponse res = new AppResponse();
        res.setMessage(message);
        res.setSuccessful(true);
        return res;
    }

    public static AppResponse ok(Object object) {
        AppResponse res = new AppResponse();
        res.setData(object);
        res.setSuccessful(true);
        return res;
    }

    public static AppResponse error(String errorMessage) {
        AppResponse res = new AppResponse();
        res.setMessage(errorMessage);
        res.setSuccessful(false);
        return res;
    }
}
