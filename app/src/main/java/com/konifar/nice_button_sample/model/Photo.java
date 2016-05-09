package com.konifar.nice_button_sample.model;

import org.parceler.Parcel;

@Parcel
public class Photo {

    public String id;
    public String owner;
    public String secret;
    public String server;
    public int farm;
    public String title;
    private int ispublic;
    private int isfriend;
    private int isfamily;

    public String getImageUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://farm");
        sb.append(farm);
        sb.append(".staticflickr.com/");
        sb.append(server);
        sb.append("/");
        sb.append(id);
        sb.append("_");
        sb.append(secret);
        sb.append(".jpg");

        return sb.toString();
    }

    public boolean isPublic() {
        return ispublic != 0;
    }

    public boolean isFriend() {
        return isfriend != 0;
    }

    public boolean isFamily() {
        return isfamily != 0;
    }

}
