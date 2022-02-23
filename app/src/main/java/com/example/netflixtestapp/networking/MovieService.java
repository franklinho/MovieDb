package com.example.netflixtestapp.networking;

import com.example.netflixtestapp.models.MoviesResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MovieService {
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original";

    private static String API_KEY = "d0bfa2d663af7a94e515085e33ab9615";


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build();
    private MovieApi movieApi = retrofit.create(MovieApi.class);

    public MovieApi getMovieApi() {
        return movieApi;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();
                HttpUrl originalHttpUrl = chain.request().url();
                HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build();
                request.url(url);
                Response response = chain.proceed(request.build());
                return response;
            }
        });
        return builder.build();
    }

    public static String getFullImageUrl(String path) {
        return BASE_IMAGE_URL + path;
    }
}

