package com.unir.cross_performance;

import com.unir.cross_performance.Interface.ApiService;
import com.unir.cross_performance.Model.AuthResponse;
import com.unir.cross_performance.Model.LoginRequest;
import com.unir.cross_performance.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://apilaravel.duckdns.org:80";
    private String authToken;

    private ApiService apiService;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void createUser(User user, Callback<AuthResponse> callback) {
        Call<AuthResponse> call = apiService.createUser(user);
        call.enqueue(callback);
    }

    public void loginUser(LoginRequest loginRequest, Callback<AuthResponse> callback) {
        Call<AuthResponse> call = apiService.loginUser(loginRequest);
        call.enqueue(callback);
    }
}
