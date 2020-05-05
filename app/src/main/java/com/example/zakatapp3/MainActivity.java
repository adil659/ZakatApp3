package com.example.zakatapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.zakatapp3.Fragments.LiabilityFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.viewpager.widget.ViewPager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {



    private static String API_KEY = "EhkfvazyLhnSSAVAM2qj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        FragmentTabsPagerAdapter fragmentTabsPagerAdapter = new FragmentTabsPagerAdapter(getSupportFragmentManager(), FragmentTabsPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(fragmentTabsPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.quandl.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        QuandlMetalApi quandlMetalApi = retrofit.create(QuandlMetalApi.class);

        SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd", getCurrentLocale(getApplicationContext())); // get todays date and make it in this format
        Date todayDate = Calendar.getInstance().getTime();
        String today = apiDateFormat.format(todayDate);

        Call<MetalDataSet> call = quandlMetalApi.getMetalValue("GOLD", "2020-05-01", "2020-05-01", API_KEY);

        call.enqueue(new Callback<MetalDataSet>() {
            @Override
            public void onResponse(Call<MetalDataSet> call, Response<MetalDataSet> response) {
                Log.d("On Response", response.body().toString());
                MetalDataSet metalDataSet = response.body();

                if(metalDataSet.getDataset().data.isEmpty()) {
                    quandlMetalApi.getMetalValue("GOLD", "2020-05-01", "2020-05-01", API_KEY);
                }

            }

            @Override
            public void onFailure(Call<MetalDataSet> call, Throwable t) {
                Log.d("API Failure", t.getMessage());


            }
        });


    }

    Locale getCurrentLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }


    public void calculateOnClick (View view) {
        LiabilityFragment fragment = (LiabilityFragment) getSupportFragmentManager().getFragments().get(1);

    }

}
