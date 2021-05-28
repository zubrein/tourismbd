package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelSearchPlace {

    @SerializedName("place_name")
    String place_name;
    @SerializedName("place_image")
    String place_image;


    public String getPlace_name() {
        return place_name;
    }

    public String getPlace_image() {
        return place_image;
    }
}
