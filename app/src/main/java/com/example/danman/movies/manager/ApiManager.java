package com.example.danman.movies.manager;

import com.example.danman.movies.api.movies.MoviesResponse;
import com.example.danman.movies.api.movies.MoviesService;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 09.12.2017.
 */

public class ApiManager {
    public static final String API_KEY = "fbe4e6280f6a460beaad8ebe2bc130ac";
    public static final String KEY_PARAM = "api_key";
    public static final String SCHEME = "https://";
    public static final String HOSTNAME = "api.themoviedb.org";

    public static final String SERVER = SCHEME + HOSTNAME;
    public static final String IMAGE_HOSTNAME = "image.tmdb.org/t/p/w500";
    public static final String IMAGE_SERVER = SCHEME + IMAGE_HOSTNAME;
    private Retrofit mRetrofit;
    private MoviesService mMoviesService;

    public ApiManager() {
        initRetrofit();
        initServices();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(SERVER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .addConverterFactory(createGsonConverter())
                .build();
    }

    private void initServices() {
        mMoviesService = mRetrofit.create(MoviesService.class);
    }

    private GsonConverterFactory createGsonConverter() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return GsonConverterFactory.create(builder.create());
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        okhttp3.Request original = chain.request();
                        HttpUrl url = original.url().newBuilder().addQueryParameter(KEY_PARAM, API_KEY).build();
                        original = original.newBuilder().url(url).build();
                        okhttp3.Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());

                        return chain.proceed(requestBuilder.build());
                    }
                })
                .addInterceptor(logInterceptor)
                .build();
    }

    public Single<MoviesResponse> getPopularMovies() {
        return mMoviesService.getPopularMovies(new HashMap<>());

    }
}
