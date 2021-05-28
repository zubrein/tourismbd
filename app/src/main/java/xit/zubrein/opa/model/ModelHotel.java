package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelHotel {
    @SerializedName("hotel_image")
    String hotel_image;
    @SerializedName("hotel_name")
    String hotel_name;
    @SerializedName("price")
    String price;
    @SerializedName("address")
    String address;
    @SerializedName("contact_no")
    String contact_no;
    @SerializedName("id")
    String id;


    public String getHotel_image() {
        return hotel_image;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getId() {
        return id;
    }
}
