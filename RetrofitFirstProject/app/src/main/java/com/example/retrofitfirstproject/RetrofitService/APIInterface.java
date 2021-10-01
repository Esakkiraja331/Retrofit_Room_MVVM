package com.example.retrofitfirstproject.RetrofitService;

import com.example.retrofitfirstproject.Entity.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

 @GET("apod")
    Call<List<DataModel>> getAllModelData(@Query("api_key")String apiKey, @Query("count")String count);
}
