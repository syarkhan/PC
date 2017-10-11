package com.example.sheryarkhan.projectcity.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.adapters.TownsRecyclerAdapter;

public class TownAutoCompleteActivity extends AppCompatActivity {


    RecyclerView autocompleteRecyclerView;
    TownsRecyclerAdapter townsRecyclerAdapter;
    EditText editTextSearchTown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_auto_complete);

        autocompleteRecyclerView = (RecyclerView)findViewById(R.id.townAutocompleteRecyclerView);
        autocompleteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        editTextSearchTown = (EditText)findViewById(R.id.editTextSearchTown);
        townsRecyclerAdapter = new TownsRecyclerAdapter(this);
        autocompleteRecyclerView.setAdapter(townsRecyclerAdapter);


        //text change listner
        editTextSearchTown.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                townsRecyclerAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autocompleteRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    }
