package com.example.android.testing.espresso.BasicSample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends Activity implements View.OnClickListener{
    private static final int DATASET_COUNT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        // Create a RecyclerView, a LayoutManager, a data Adapter and wire everything up.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Show toast after clicked
        findViewById(R.id.buttonShowToast).setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        List<String> dataSet = new ArrayList<>(DATASET_COUNT);
        for (int i = 0; i < DATASET_COUNT; i++) {
            dataSet.add(getString(R.string.item_element_text) + (i+1));
        }
        CustomAdapter adapter = new CustomAdapter(dataSet, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
