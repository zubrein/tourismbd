package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

public class ModelResponse {

    @SerializedName("response")
    private String response;
    @SerializedName("user_id")
    private String user_id;


    public String getResponse() {
        return response;
    }

    public String getUser_id() {
        return user_id;
    }
}
