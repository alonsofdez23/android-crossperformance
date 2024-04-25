package com.unir.cross_performance.Utils;

import com.unir.cross_performance.Model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    /* Auth */
    @POST("api/auth/register")
    Call<AuthResponse> createUser(@Body User user);

    @POST("api/auth/login")
    Call<AuthResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("/api/auth/logout")
    Call<LogoutResponse> logoutUser(@Header("Authorization") String token);

    @GET("/api/clases")
    Call<List<Clase>> getClases(@Header("Authorization") String token);
}
