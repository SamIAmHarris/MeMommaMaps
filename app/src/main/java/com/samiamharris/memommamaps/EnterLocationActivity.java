package com.samiamharris.memommamaps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 8/1/16.
 */

public class EnterLocationActivity extends AppCompatActivity {

    @BindView(R.id.enter_location_edit_text)
    EditText enterLocationEditText;
    @BindView(R.id.location_recyclerview)
    RecyclerView locationRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_location);
        ButterKnife.bind(this);
    }
}
