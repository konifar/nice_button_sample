package com.konifar.nice_button_sample.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class PhotoSource {

    private Photos photos;
    private String stat;

    public List<Photo> getPhotos() {
        return photos.photo;
    }

}
