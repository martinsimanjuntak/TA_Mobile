package com.example.apprute.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprute.DetailTouristAttraction;
import com.example.apprute.Fragment.OptionFragment;
import com.example.apprute.Fragment.TouristFragment;
import com.example.apprute.R;
import com.example.apprute.TouristAttractionChoice;
import com.example.apprute.model.TouristAttraction;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class TouristAttractionAdapterFragment extends RecyclerView.Adapter<TouristAttractionAdapterFragment.TouristFragmentHolder>{
    Context context;
    private List<TouristAttraction> touristAttractionList;
    public TouristAttractionAdapterFragment(Context context, List<TouristAttraction> touristAttractions){
        this.context = context;
        this.touristAttractionList = touristAttractions;
    }
    @NonNull
    @Override
    public TouristFragmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tourist_attraction_item_fragment, parent, false);
        return new TouristAttractionAdapterFragment.TouristFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristFragmentHolder holder, int position) {
        TouristAttraction touristAttraction = touristAttractionList.get(position);
        holder.name.setText(touristAttraction.getName());
        holder.location.setText(touristAttraction.getLocation());
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                OptionFragment optionFragment = new OptionFragment();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment1, optionFragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return touristAttractionList.size();
    }

    public class TouristFragmentHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        MaterialCardView materialCardView;
        public TouristFragmentHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tourist_attraction_name_fragment);
            location = itemView.findViewById(R.id.location_tourist_attraction_fragment);
            materialCardView = itemView.findViewById(R.id.parent_layout_fragment);
        }
    }
}
