package com.samiamharris.memommamaps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 8/1/16.
 */

public class EnterAddressActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.enter_address_edit_text)
    EditText enterAddressEditText;
    @BindView(R.id.address_recyclerview)
    RecyclerView addressRecyclerView;

    PlaceAdapter placeAdapter;
    ArrayList<Place> places;

    GoogleApiClient googleApiClient;

    AutocompleteFilter autocompleteFilter;
    LatLngBounds latLngBounds;

    private static final String TAG = "EnterAddressActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);
        ButterKnife.bind(this);

        places = new ArrayList<Place>();
        placeAdapter = new PlaceAdapter(this, places);
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressRecyclerView.setAdapter(placeAdapter);

        autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();

        latLngBounds = new LatLngBounds(
                new LatLng(-33.880490, 151.184363),
                new LatLng(-33.858754, 151.229596));

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        enterAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 2) {
                    PendingResult<AutocompletePredictionBuffer> result =
                            Places.GeoDataApi.getAutocompletePredictions(googleApiClient, charSequence.toString(),
                                    latLngBounds, autocompleteFilter);
                    result.setResultCallback(new ResultCallback<AutocompletePredictionBuffer>() {
                        @Override
                        public void onResult(@NonNull AutocompletePredictionBuffer autocompletePredictions) {
                            if(autocompletePredictions.getCount() > 0) {
                                for(AutocompletePrediction autocompletePrediction: autocompletePredictions) {
                                    Place place = new Place(
                                            autocompletePrediction.getPrimaryText(null).toString(),
                                            autocompletePrediction.getSecondaryText(null).toString(),
                                            autocompletePrediction.getPlaceId());
                                    places.add(place);
                                    Log.i(TAG, autocompletePrediction.getPrimaryText(null).toString());
                                }
                                placeAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    Log.i(TAG, "places received");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(EnterAddressActivity.this, "Connection failed", Toast.LENGTH_LONG).show();
    }
}
