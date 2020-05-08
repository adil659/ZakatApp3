package com.example.zakatapp3;

import com.example.zakatapp3.Models.MetalDataSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuandlMetalApiClient {

    private static QuandlMetalApiClient instance;
    private QuandlMetalApi quandlMetalApi;
    private static final String BASE_URL = "https://www.quandl.com/";
    private static String API_KEY = "EhkfvazyLhnSSAVAM2qj";



    public QuandlMetalApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
         quandlMetalApi = retrofit.create(QuandlMetalApi.class);

    }

    public static QuandlMetalApiClient getInstance() {
        if (instance == null){
            instance = new QuandlMetalApiClient();
        }
        return instance;
    }

    public Call<MetalDataSet> getMetalData(String metal, String date){
        return quandlMetalApi.getMetalValue(metal, date, date, API_KEY);
    }
}
