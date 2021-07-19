package com.example.user.crudwithretrofit;

import retrofit2.Retrofit;

public class ConfigApi {

    public static final String BASE_URL = "http://192.168.100.4/server_siswa/";

    public static ApiService getapiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
