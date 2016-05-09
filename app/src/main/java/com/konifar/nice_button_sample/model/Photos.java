package com.konifar.nice_button_sample.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Photos {

    public int page;
    public int pages;
    public int perpage;
    public String total;
    public List<Photo> photo;

}
