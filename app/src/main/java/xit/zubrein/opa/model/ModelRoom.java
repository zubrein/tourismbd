package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelRoom {
    @SerializedName("flat_name")
    String flat_name;
    @SerializedName("flat_price")
    String flat_price;
    @SerializedName("flat_total_room")
    String flat_total_room;
    @SerializedName("flat_ac")
    String flat_ac;
    @SerializedName("hotel_id")
    String hotel_id;
    @SerializedName("des")
    String description;
    @SerializedName("id")
    String id;
    @SerializedName("code")
    String code;
    @SerializedName("date")
    String date;
    @SerializedName("tv")
    String tv;
    @SerializedName("swimmingpool")
    String swimmingpool;
    @SerializedName("balcony")
    String balcony;
    @SerializedName("wifi")
    String wifi;
    @SerializedName("lift")
    String lift;
    @SerializedName("total_bath")
    String total_bath;
    @SerializedName("images")
    List<String> images = new ArrayList<>();

    public String getFlat_name() {
        return flat_name;
    }

    public String getFlat_price() {
        return flat_price;
    }

    public String getFlat_total_room() {
        return flat_total_room;
    }

    public String getFlat_ac() {
        return flat_ac;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public String getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTv() {
        return tv;
    }

    public String getSwimmingpool() {
        return swimmingpool;
    }

    public String getBalcony() {
        return balcony;
    }

    public String getWifi() {
        return wifi;
    }

    public String getLift() {
        return lift;
    }

    public String getTotal_bath() {
        return total_bath;
    }

    public String getCode() {
        return code;
    }
}
