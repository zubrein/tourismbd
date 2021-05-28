package xit.zubrein.opa.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xit.zubrein.opa.model.ModelGallery;
import xit.zubrein.opa.model.ModelHospital;
import xit.zubrein.opa.model.ModelHotel;
import xit.zubrein.opa.model.ModelPackage;
import xit.zubrein.opa.model.ModelPoliceStation;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.model.ModelRestaurant;
import xit.zubrein.opa.model.ModelReview;
import xit.zubrein.opa.model.ModelRoom;
import xit.zubrein.opa.model.ModelSearchPlace;

/**
 * Created by zubrein on 7/15/19.
 */

public interface ApiInterface {


    @FormUrlEncoded
    @POST("logincheck.php")
    Call<ModelResponse> login_check(@Field("user_email") String user_email, @Field("user_password") String user_password);

    @FormUrlEncoded
    @POST("registration.php")
    Call<ModelResponse> registration(@Field("user_name") String user_name,
                            @Field("user_email") String user_email,
                            @Field("user_password") String user_password,
                            @Field("user_mobile") String user_mobile);

    @FormUrlEncoded
    @POST("booking_room.php")
    Call<ModelResponse> booking_room(@Field("user_id") String user_id,
                            @Field("hotel_id") String hotel_id,
                            @Field("room_id") String room_id,
                            @Field("date") String date);

    @FormUrlEncoded
    @POST("booking_package.php")
    Call<ModelResponse> booking_package(@Field("user_id") String user_id,
                            @Field("hotel_id") String hotel_id,
                            @Field("package_id") String package_id,
                            @Field("date") String date);


    @FormUrlEncoded
    @POST("search_place.php")
    Call<List<ModelSearchPlace>> search_place(@Field("place") String place,
                                             @Field("view") String view);
    @FormUrlEncoded
    @POST("budget_search.php")
    Call<List<ModelSearchPlace>> budget_search(@Field("district") String district,
                                             @Field("budget") String budget);



    @FormUrlEncoded
    @POST("hotel_info.php")
    Call<List<ModelHotel>> hotel_info(@Field("place") String place);

    @FormUrlEncoded
    @POST("room_list.php")
    Call<List<ModelRoom>> room_list(@Field("id") String id);

    @FormUrlEncoded
    @POST("package_list.php")
    Call<List<ModelPackage>> package_list(@Field("id") String id);



    @FormUrlEncoded
    @POST("my_bookings_rooms.php")
    Call<List<ModelRoom>> my_bookings(@Field("id") String id);

    @FormUrlEncoded
    @POST("my_bookings_packages.php")
    Call<List<ModelPackage>> my_bookings_packages(@Field("id") String id);



    @FormUrlEncoded
    @POST("room_details.php")
    Call<List<ModelRoom>> room_details(@Field("id") String id);

    @FormUrlEncoded
    @POST("package_details.php")
    Call<List<ModelPackage>> package_details(@Field("id") String id);


    @FormUrlEncoded
    @POST("overview.php")
    Call<ModelReview> get_overview(@Field("place") String place);



    @FormUrlEncoded
    @POST("res_info.php")
    Call<List<ModelRestaurant>> restaurant_info(@Field("place") String place);

    @FormUrlEncoded
    @POST("hospital_info.php")
    Call<List<ModelHospital>> hospital_info(@Field("place") String place);


    @FormUrlEncoded
    @POST("travel_path.php")
    Call<ModelResponse> travel_path(@Field("place") String place);

    @FormUrlEncoded
    @POST("police_station_info.php")
    Call<List<ModelPoliceStation>> police_station_info(@Field("place") String place);

    @FormUrlEncoded
    @POST("gallery.php")
    Call<List<ModelGallery>> get_gallery(@Field("place") String place);

    @FormUrlEncoded
    @POST("submit_place_review.php")
    Call<ModelResponse> send_review(@Field("user_id") String user_id,@Field("place") String place,
                                    @Field("rating") String rating,@Field("review") String review);







}
