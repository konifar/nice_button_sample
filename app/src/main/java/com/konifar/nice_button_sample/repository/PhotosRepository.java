package com.konifar.nice_button_sample.repository;

import com.konifar.nice_button_sample.api.FlickrApiClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotosRepository {

    @Inject
    FlickrApiClient client;

}
