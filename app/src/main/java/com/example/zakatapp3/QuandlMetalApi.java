package com.example.zakatapp3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuandlMetalApi {

    @Headers("Accept: application/json")
    @GET("api/v3/datasets/LBMA/{metal}")
    Call<MetalDataSet> getMetalValue(@Path("metal") String metal,
                                    @Query("start_date") String start_date,
                                    @Query("end_date") String end_date,
                                    @Query("api_key") String api_key);
}
