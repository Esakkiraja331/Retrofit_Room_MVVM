package com.example.retrofitfirstproject.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.google.gson.annotations.SerializedName;

@Entity(tableName = "model_table")
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("explanation")
    @ColumnInfo(name = "explanation")
    private String explanation;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;



    @SerializedName("date")
    @ColumnInfo(name = "date")
    private String date;

    @SerializedName("media_type")
    @ColumnInfo(name = "media_type")
    private String media_type;


    
    public DataModel(String explanation,String url,String title,String date,String media_type){
        this.explanation=explanation;
        this.url=url;

        this.title=title;
        this.date=date;
        this.media_type=media_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type =media_type ;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return "DataModel{"+
                "tittle" + title +
                ",url"+url+
                ",explanation"+explanation+
                ",media_type"+media_type+
                ",date"+date+
                '}';

    }




}
