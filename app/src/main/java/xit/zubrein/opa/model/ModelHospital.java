package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelHospital {
    @SerializedName("hospital_name")
    String hospital_name;
    @SerializedName("address")
    String address;
    @SerializedName("contact_no")
    String contact_no;

    public String getHospital_name() {
        return hospital_name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }
}
