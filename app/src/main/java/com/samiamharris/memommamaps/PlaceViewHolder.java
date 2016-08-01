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

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    @BindView(R.id.row_place_name_text_view)
    public TextView nameTextView;
    @BindView(R.id.row_place_address_text_view)
    public TextView addressTextView;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Place place) {
        nameTextView.setText(place.name);
        addressTextView.setText(place.address);
    }

}
