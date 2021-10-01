package com.example.retrofitfirstproject.ImageViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofitfirstproject.Entity.DataModel;
import com.example.retrofitfirstproject.Repositry.DataRepository;

import java.util.List;

public class ImageViewModel extends AndroidViewModel {
    private DataRepository repository;

    private LiveData<List<DataModel>> allModel;

    public ImageViewModel(@NonNull Application application) {
        super(application);

        repository = new DataRepository(application);
        allModel = repository.getAllModelData();

    }
    public void retrofitCall(String count){
    repository.apiRequest(count);
    }
    public void insert(List<DataModel> list){
    repository.insert(list);
    }
    public LiveData<List<DataModel>> allModel(){
        return allModel;
    }

}
