package com.example.retrofitfirstproject.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retrofitfirstproject.Entity.DataModel;

import java.util.List;

@Dao
public interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DataModel> modelList);

    @Update
    void update(DataModel dataModel);
    @Query("SELECT * FROM model_table ORDER BY id DESC")
    LiveData<List<DataModel>> getAllModelData();

    @Delete
    void delete(DataModel dataModel);

    @Query("DELETE FROM model_table")
    void deleteAll();

}

