package com.example.admin.testretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemAPI {


    @GET("3")
    Call<List<Item>> messages();
}
