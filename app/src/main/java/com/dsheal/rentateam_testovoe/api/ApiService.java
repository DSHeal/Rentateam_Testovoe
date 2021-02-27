package com.dsheal.rentateam_testovoe.api;

import com.dsheal.rentateam_testovoe.model.pojo.UserResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    
    @GET("users")
    Observable<UserResponse> getUsers();
}
