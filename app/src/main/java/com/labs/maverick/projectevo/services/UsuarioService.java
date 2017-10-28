package com.labs.maverick.projectevo.services;

import com.labs.maverick.projectevo.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface UsuarioService {
    @POST("api/SignUp")
    @Headers("Content-Type: application/json")
    Call<Boolean>AddUsers(@Body Usuario usuario);

    @POST("api/SignIn")
    @Headers("Content-Type: application/json")
    Call<Boolean>Login(@Body Usuario usuario);
}
