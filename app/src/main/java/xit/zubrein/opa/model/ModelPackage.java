package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelPackage {
    @SerializedName("hotel_id")
    String hotel_id;
    @SerializedName("id")
    String id;
    @SerializedName("package_name")
    String package_name;
    @SerializedName("package_price")
    String package_price;
    @SerializedName("package_description")
    String package_description;
    @SerializedName("package_image")
    String package_image;
    @SerializedName("date")
    String date;
    @SerializedName("code")
    String code;

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public String getId() {
        return id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public String getPackage_price() {
        return package_price;
    }

    public String getPackage_description() {
        return package_description;
    }

    public String getPackage_image() {
        return package_image;
    }
}
