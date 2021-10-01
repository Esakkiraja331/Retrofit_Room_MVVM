package com.example.retrofitfirstproject.Repositry;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitfirstproject.Dao.ModelDao;
import com.example.retrofitfirstproject.Entity.DataModel;
import com.example.retrofitfirstproject.Retrofit.RetrofitClient;
import com.example.retrofitfirstproject.RoomDB.ModelRoomDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    public String apikey ="goLj8jNbuSbNDt3IRwbLGyModq3yUWPKob5zzao7";
    private LiveData<List<DataModel>> getAllModelData;
    private ModelRoomDatabase modelRoomDatabase;
    public Context context;
    private MutableLiveData<String> toastMessageObserver = new MutableLiveData();


    public DataRepository(Application application){
        modelRoomDatabase =ModelRoomDatabase.getInstance(application);


    }
    public  void insert(List<DataModel> modelList){
        getLists(modelList);
    }

    public LiveData<List<DataModel>> getAllModelData(){
        getAllModelData = modelRoomDatabase.modelDao().getAllModelData();
        return getAllModelData;
    }

    public void getLists(final List<DataModel> lists){
        modelRoomDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
            modelRoomDatabase.modelDao().insert(lists);
            }
        });
    }


    public void  apiRequest(String count){
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<DataModel>> call =retrofitClient.apiInterface().getAllModelData(apikey,count);
        call.enqueue(new Callback<List<DataModel>>() {

            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {

                if(response.isSuccessful()){

                    insert(response.body());
                    Log.d("main","onResponse"+response.body());
                    toastMessageObserver.setValue("Data inserted successfully"+response.message());

                }
                else {
                    response.code();
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Log.d("main", "onFailure: ");

            }
        });
    }


}
