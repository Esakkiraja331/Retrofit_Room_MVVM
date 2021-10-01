package com.example.retrofitfirstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitfirstproject.Adapter.RecyclerAdapter;
import com.example.retrofitfirstproject.Entity.DataModel;
import com.example.retrofitfirstproject.ImageViewModel.ImageViewModel;
import com.example.retrofitfirstproject.Repositry.DataRepository;
import com.example.retrofitfirstproject.Retrofit.RetrofitClient;
import com.example.retrofitfirstproject.RetrofitService.APIInterface;
import com.example.retrofitfirstproject.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialTextView tittle;

    MaterialButton button1,button2,button3,button4;
    RecyclerView recyclerView;
    ActivityMainBinding activityMainBinding;
    RecyclerAdapter recyclerAdapter;

    ImageViewModel imageViewModel;

    public String count = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = activityMainBinding.getRoot();
        setContentView(view);


        tittle = activityMainBinding.TVTittle;
        button1 = activityMainBinding.BT1;
        button2 = activityMainBinding.BT2;
        button3 = activityMainBinding.BT3;
        button4 = activityMainBinding.BT4;
        recyclerView = activityMainBinding.recyclerview;

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerAdapter = new RecyclerAdapter(this);

        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        imageViewModel.allModel().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> modelList) {




                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.getALLModelData(modelList);
                    recyclerAdapter.updateList(modelList);

                    Log.d("main", "onChanged: " + modelList);
            }




        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.BT_1:
                count = "1";
                break;

            case R.id.BT_2:

                count = "2";
                break;

            case R.id.BT_3:

                count = "3";
                break;

            case R.id.BT_4:

                count = "4";
                break;


        }
        MaterialButton button = (MaterialButton) v;
        String count =button.getText().toString();
        imageViewModel.retrofitCall(count);

    }
}
