package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelPoliceStation {
    @SerializedName("police_station_name")
    String police_station_name;
    @SerializedName("address")
    String address;
    @SerializedName("contact_no")
    String contact_no;

    public String getPolice_station_name() {
        return police_station_name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }
}
