package com.example.retrofitfirstproject.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.retrofitfirstproject.Dao.ModelDao;
import com.example.retrofitfirstproject.Entity.DataModel;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DataModel.class},version = 2,exportSchema = false)
public abstract class ModelRoomDatabase extends RoomDatabase {

    private static ModelRoomDatabase instance;

    public abstract ModelDao modelDao();

    public Executor executor = Executors.newSingleThreadExecutor();

    public static  ModelRoomDatabase getInstance(Context context) {


        if (instance == null) {
            synchronized (ModelRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, ModelRoomDatabase.class,
                            "model_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;

    }


}
