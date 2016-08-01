package com.samiamharris.memommamaps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 8/1/16.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    Context context;
    ArrayList<Place> places;

    public PlaceAdapter(Context context, ArrayList<Place> places) {
        this.context = context;
        this.places = places;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_place, parent, false);

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.bindView(place);
    }

    @Override
    public int getItemCount() {
        if(places != null) {
            return places.size();
        }
        return 0;
    }

}
