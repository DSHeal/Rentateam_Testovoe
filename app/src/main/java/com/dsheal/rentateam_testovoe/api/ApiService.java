package com.dsheal.rentateam_testovoe.api;

import com.dsheal.rentateam_testovoe.model.pojo.UserResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

/*здесь мы указываем все запросы на сайт
Retrofit построит запрос на этот базовый url, и когда мы вызовем метод getUsers, он добавит в конец "users"
и вернет объект UserResponse*/
public interface ApiService {
    //первое, что будет возвращать сервер - это объект UserResponse
    @GET("users")
    // чтобы можно было слушать, что происходит с объектом userResponse, нужно обернуть объект в Observable
    Observable<UserResponse> getUsers();
}
