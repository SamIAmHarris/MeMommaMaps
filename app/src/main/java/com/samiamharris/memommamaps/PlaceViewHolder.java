package com.samiamharris.memommamaps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 8/1/16.
 */

public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;

    @BindView(R.id.row_place_name_text_view)
    public TextView nameTextView;
    @BindView(R.id.row_place_address_text_view)
    public TextView addressTextView;
    PlaceAdapter.OnPlaceClickedListener onPlaceClickedListener;
    SimplePlace simplePlace;

    public PlaceViewHolder(View itemView, PlaceAdapter.OnPlaceClickedListener onPlaceClickedListener) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.onPlaceClickedListener = onPlaceClickedListener;
    }

    public void bindView(SimplePlace simplePlace) {
        this.simplePlace = simplePlace;
        nameTextView.setText(simplePlace.name);
        addressTextView.setText(simplePlace.address);
    }

    @Override
    public void onClick(View view) {
        onPlaceClickedListener.onPlaceSelected(simplePlace.id);
    }
}
