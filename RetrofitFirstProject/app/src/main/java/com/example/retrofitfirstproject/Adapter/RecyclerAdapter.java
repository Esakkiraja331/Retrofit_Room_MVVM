package com.example.retrofitfirstproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitfirstproject.Diffutils.MyDiffUtils;
import com.example.retrofitfirstproject.Entity.DataModel;
import com.example.retrofitfirstproject.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

 List<DataModel> modelList;
    private Context context;

    public RecyclerAdapter( Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        final DataModel dataModel = modelList.get(viewType);

       /* myViewHolder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(dataModel.getUrl()));
                context.startActivity(browserIntent);
            }
        });*/

        myViewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ImageView images;

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);

                TextView tvTitle = (TextView) view.findViewById(R.id.TV_TittleM);
                tvTitle.setText(dataModel.getTitle());
                TextView tvUrl=(TextView)view.findViewById(R.id.TV_Url);
                tvUrl.setText(dataModel.getUrl());
                tvUrl.setMovementMethod(LinkMovementMethod.getInstance());
                tvUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        browserIntent.setData(Uri.parse(dataModel.getUrl()));
                        context.startActivity(browserIntent);
                    }
                });
                //image insert
                ImageView images = (ImageView) view.findViewById(R.id.IV_ImagesD);
                Picasso.get().load(dataModel.getUrl()).into(images);
                TextView tvMedia = (TextView) view.findViewById(R.id.TV_MediaType);
                tvMedia.setText(dataModel.getMedia_type());
                TextView textPlan=(TextView)view.findViewById(R.id.TV_Explanation);
                textPlan.setText(dataModel.getExplanation());
                textPlan.setMovementMethod(new ScrollingMovementMethod());
               // VideoView video = (VideoView) view.findViewById(R.id)

                builder.setView(view);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setLayout(RecyclerView.LayoutParams.FILL_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);

            }
        });
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DataModel dataModel = modelList.get(position);

        holder.title.setText(dataModel.getTitle());
       // holder.media.setText(dataModel.getMedia_type());
        holder.date.setText(dataModel.getDate());
        //image insert recyclerView in thumbnail
        Picasso.get().load(dataModel.getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return  modelList.size();
    }

    public void getALLModelData(List<DataModel> modelList)
    {
        this.modelList = modelList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title,date;
        ImageView image;
        Button more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            date = itemView.findViewById(R.id.TV_Date);
            image = itemView.findViewById(R.id.IV_Media);
            more = itemView.findViewById(R.id.btnMore);
        }
    }

    public void updateList(List<DataModel> newList){

        MyDiffUtils diffUtilCallback = new MyDiffUtils(this.modelList,newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        diffResult.dispatchUpdatesTo(this);
    }
}