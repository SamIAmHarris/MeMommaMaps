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

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder>{

    Context context;
    ArrayList<SimplePlace> simplePlaces;
    int currentPosition;
    public OnPlaceClickedListener onPlaceClickedListener;


    public interface OnPlaceClickedListener {
        void onPlaceSelected(String id);
    }

    public PlaceAdapter(Context context, ArrayList<SimplePlace> simplePlaces, OnPlaceClickedListener onPlaceClickedListener) {
        this.context = context;
        this.simplePlaces = simplePlaces;
        this.onPlaceClickedListener = onPlaceClickedListener;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_place, parent, false);

        return new PlaceViewHolder(v, onPlaceClickedListener);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        SimplePlace simplePlace = simplePlaces.get(position);
        this.currentPosition = position;
        holder.bindView(simplePlace);
    }

    @Override
    public int getItemCount() {
        if(simplePlaces != null) {
            return simplePlaces.size();
        }
        return 0;
    }

}
