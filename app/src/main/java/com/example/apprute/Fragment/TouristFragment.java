package com.example.apprute.Fragment;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apprute.Adapter.TouristAttractionAdapterFragment;
import com.example.apprute.Adapter.TouristAttractionsAdapter;
import com.example.apprute.MainActivity;
import com.example.apprute.R;
import com.example.apprute.RuteActivity;
import com.example.apprute.TouristAttractionsActivity;
import com.example.apprute.model.TouristAttraction;
import com.example.apprute.service.ApiInterface;
import com.example.apprute.service.BaseUrl;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristFragment extends Fragment implements TouristAttractionsAdapter.OnNoteListener{
    private RecyclerView recyclerView;
    ApiInterface apiInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_touris, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_tourist_attraction_choice);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        apiInterface = BaseUrl.getClient().create(ApiInterface.class);
        getTouristAttraction();

        return view;
    }
    private void getTouristAttraction(){
        Call<List<TouristAttraction>> listCall = apiInterface.getTouristAttraction();
        listCall.enqueue(new Callback<List<TouristAttraction>>() {

            @Override
            public void onResponse(Call<List<TouristAttraction>> call, Response<List<TouristAttraction>> response) {
                Log.d(TAG, "onResponse: Berhasil");
                List<TouristAttraction> touristAttractions = response.body();
                Log.d(TAG, "onResponse: "+response.body());
                TouristAttractionAdapterFragment touristAttractionAdapterFragment = new TouristAttractionAdapterFragment(getView().getContext(),touristAttractions);
                recyclerView.setAdapter(touristAttractionAdapterFragment);
            }
            @Override
            public void onFailure(Call<List<TouristAttraction>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onNoteClick(int position) {
        Toast toast = Toast.makeText(getActivity(),"CLICKED",Toast.LENGTH_SHORT);
        toast.show();
    }
}