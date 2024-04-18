package com.unir.cross_performance.Interface;

import com.unir.cross_performance.Model.AuthResponse;
import com.unir.cross_performance.Model.LoginRequest;
import com.unir.cross_performance.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/register")
    Call<AuthResponse> createUser(@Body User user);

    @POST("api/auth/login")
    Call<AuthResponse> loginUser(@Body LoginRequest loginRequest);
}
