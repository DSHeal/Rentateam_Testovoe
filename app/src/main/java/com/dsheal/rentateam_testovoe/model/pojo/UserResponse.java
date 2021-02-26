package com.dsheal.rentateam_testovoe.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    // первый json, который мы получаем с массивом пользователей
    @SerializedName("data") // эти аннотации используются, чтобы показать библиотеке Gson как создать этот класс
    // "data" - в кавычках указываем ключ, по которому получаем данные
    @Expose
    private List<User> response = null;

    public List<User> getResponse() {
        return response;
    }

    public void setResponse(List<User> response) {
        this.response = response;
    }
}
