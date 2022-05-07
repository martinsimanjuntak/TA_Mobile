package com.example.apprute.service;

import com.example.apprute.model.Harbor;
import com.example.apprute.model.Route;
import com.example.apprute.model.TouristAttraction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("harbors")
    Call<List<Harbor>> getHarbor();

    @GET("touristattractions/{id}")
    Call<TouristAttraction> getDetailTouristAttraction(@Path("id") int id);

    @GET("touristattractions")
    Call<List<TouristAttraction>> getTouristAttraction();

    @GET("PortRoutes/getAllRoute")
    Call<List<Route>> getRoute();


}
