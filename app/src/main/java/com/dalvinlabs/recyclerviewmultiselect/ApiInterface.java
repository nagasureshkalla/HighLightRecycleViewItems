package com.dalvinlabs.recyclerviewmultiselect;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("collections/branchesdata/")
    Call<List<Branches>> getBran(@Query("apiKey") String apiKey);

}