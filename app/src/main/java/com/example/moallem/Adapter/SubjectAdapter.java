package com.example.moallem.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.Resource;
import com.example.moallem.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{
    private Context mContext;
    private List<String> mSubjects;
    public SubjectAdapter(Context mContext, List<String> mSubjects) {
        this.mContext = mContext;
        this.mSubjects = mSubjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_item,parent,false);
        return new SubjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String subject = mSubjects.get(position);
        holder.subjectName.setText(subject);
        holder.subjectImage.setImageResource(mContext.getResources().getIdentifier(subject.toLowerCase(),"drawable",mContext.getPackageName()));

    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView subjectImage;
        public TextView subjectName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectImage = itemView.findViewById(R.id.subject_image);
            subjectName = itemView.findViewById(R.id.subject_name);
        }
    }
}
