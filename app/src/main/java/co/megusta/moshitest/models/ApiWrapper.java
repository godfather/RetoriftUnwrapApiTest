package co.megusta.moshitest.models;

import java.util.ArrayList;
import java.util.List;

import co.megusta.moshitest.network.ApiError;

public class ApiWrapper<T> {
    public final int status;
    public final T data;
    public final List<ApiError> errors = new ArrayList<>();

    public ApiWrapper(int status, T data, List<ApiError> errors) {
        this.status = status;
        this.data = data;
        this.errors.addAll(errors);
    }

}
