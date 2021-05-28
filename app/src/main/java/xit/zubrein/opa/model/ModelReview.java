package xit.zubrein.opa.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelReview {
    @SerializedName("description")
    String description;
    @SerializedName("review")
    List<ReviewItem> list = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public List<ReviewItem> getList() {
        return list;
    }

    public class ReviewItem{
        @SerializedName("user_name")
        String user_name;
        @SerializedName("user_review")
        String user_review;
        @SerializedName("user_rating")
        String user_rating;

        public String getUser_name() {
            return user_name;
        }

        public String getUser_review() {
            return user_review;
        }

        public String getUser_rating() {
            return user_rating;
        }
    }
}
