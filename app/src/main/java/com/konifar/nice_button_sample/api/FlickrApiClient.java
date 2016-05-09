package com.konifar.nice_button_sample.api;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.konifar.nice_button_sample.BuildConfig;
import com.konifar.nice_button_sample.model.PhotoSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@Singleton
public class FlickrApiClient {

    private final FlickrApiService service;

    @Inject
    public FlickrApiClient(OkHttpClient client) {
        service = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.flickr.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build()
                .create(FlickrApiService.class);
    }

    public static Gson createGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public Observable<PhotoSource> getCatPhotos(int page) {
        return getCatPhotos(page, 20, null);
    }

    public Observable<PhotoSource> getCatPhotos(int page, int per, @Nullable SortOption sortOption) {
        if (sortOption == null) {
            sortOption = SortOption.SORT_INTERESTINGNESS_DESC;
        }
        return service.photosSearch("cat", page, per, sortOption.toString());
    }

    private enum SortOption {
        SORT_INTERESTINGNESS_DESC {
            @Override
            public String toString() {
                return "interestingness-desc";
            }
        },
        SORT_DATE_POSTED_DESC {
            @Override
            public String toString() {
                return "date-posted-desc";
            }
        }
    }

    public interface FlickrApiService {

        /**
         * https://www.flickr.com/services/api/flickr.photos.search.html
         */
        @GET("/services/rest?method=flickr.photos.search&api_key=" + BuildConfig.FLICKR_API_KEY + "&format=json&nojsoncallback=1")
        Observable<PhotoSource> photosSearch(@Query("text") String text,
                                             @Query("page") Integer page,
                                             @Query("per_page") Integer perpage,
                                             @Query("sort") String sort);

    }

}
