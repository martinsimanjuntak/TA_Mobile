package com.example.apprute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.apprute.Fragment.OptionFragment;
import com.example.apprute.Fragment.TouristFragment;

public class TouristAttractionChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_choice);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment1, new TouristFragment());
        ft2.add(R.id.fragment2, new OptionFragment());
        ft.commit();


    }
}