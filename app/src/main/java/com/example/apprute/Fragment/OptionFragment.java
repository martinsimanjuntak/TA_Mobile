package com.example.apprute.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apprute.Adapter.RouteAdapter;
import com.example.apprute.R;
import com.example.apprute.model.Route;
import com.example.apprute.model.TouristAttraction;
import com.example.apprute.service.ApiInterface;
import com.example.apprute.service.BaseUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OptionFragment extends Fragment {
    private RecyclerView recyclerView;
    ApiInterface apiInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_option, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_tourist_attraction_choice_fragment);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        apiInterface = BaseUrl.getClient().create(ApiInterface.class);
        getAllPortRoute();
        return view;
    }

    private void getAllPortRoute(){
        Call<List<Route>> listCall = apiInterface.getRoute();
        listCall.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                Log.e(TAG, "onResponse: "+ response);
                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                List<Route> routes = response.body();
                RouteAdapter routeAdapter = new RouteAdapter(getContext(), routes);
                recyclerView.setAdapter(routeAdapter);
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                System.out.println("Gagal");
                Log.e(TAG, "onFailure: gagal");
            }
        });
    }
}