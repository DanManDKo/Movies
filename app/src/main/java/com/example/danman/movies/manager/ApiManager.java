package com.example.danman.movies.manager;

import com.example.danman.movies.api.genre.GenreResponse;
import com.example.danman.movies.api.genre.GenreService;
import com.example.danman.movies.api.movies.MoviesResponse;
import com.example.danman.movies.api.movies.MoviesService;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
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
    public static final String QUERY_PAGE = "page";
    public static final String QUERY_GENRE = "with_genres";
    private Retrofit mRetrofit;
    private MoviesService mMoviesService;
    private GenreService mGenreService;

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
        mGenreService = mRetrofit.create(GenreService.class);
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

    public Single<MoviesResponse> getPopularMovies(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put(QUERY_PAGE, page);
        return mMoviesService.getPopularMovies(map);

    }

    public Single<MoviesResponse> findMovieByName(String name) {
        return mMoviesService.findMovieByName(name);
    }

    public Single<GenreResponse>getGenres(){
        return mGenreService.getGenres();
    }
}
