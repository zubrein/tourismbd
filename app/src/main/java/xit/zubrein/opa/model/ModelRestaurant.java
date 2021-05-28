package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelRestaurant {

    @SerializedName("res_image")
    String res_image;
    @SerializedName("res_name")
    String res_name;
    @SerializedName("res_id")
    String res_id;

    public String getRes_image() {
        return res_image;
    }

    public String getRes_name() {
        return res_name;
    }

    public String getRes_id() {
        return res_id;
    }
}
