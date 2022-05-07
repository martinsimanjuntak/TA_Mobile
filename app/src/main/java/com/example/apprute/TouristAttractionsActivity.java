package com.example.apprute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.apprute.Adapter.TouristAttractionsAdapter;
import com.example.apprute.Fragment.TouristFragment;
import com.example.apprute.model.TouristAttraction;
import com.example.apprute.service.ApiInterface;
import com.example.apprute.service.BaseUrl;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristAttractionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attractions);

        recyclerView = findViewById(R.id.recycerlview_tourist_attraction);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiInterface = BaseUrl.getClient().create(ApiInterface.class);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.tour);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.rute:
                        startActivity(new Intent(getApplicationContext(), RuteActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.itemhome:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tour:
                        return true;
                }
                return false;
            }
        });
        getTouristAttraction();
    }
    private void getTouristAttraction(){
        Call<List<TouristAttraction>> listCall = apiInterface.getTouristAttraction();
        listCall.enqueue(new Callback<List<TouristAttraction>>() {

            @Override
            public void onResponse(Call<List<TouristAttraction>> call, Response<List<TouristAttraction>> response) {
                List<TouristAttraction> touristAttractions = response.body();
                TouristAttractionsAdapter touristAttractionsAdapter = new TouristAttractionsAdapter(TouristAttractionsActivity.this,touristAttractions);
                recyclerView.setAdapter(touristAttractionsAdapter);
            }
            @Override
            public void onFailure(Call<List<TouristAttraction>> call, Throwable t) {

            }
        });
    }
}