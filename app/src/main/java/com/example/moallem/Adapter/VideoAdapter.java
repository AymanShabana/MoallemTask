package com.example.moallem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moallem.Model.Video;
import com.example.moallem.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context mContext;
    private List<Video> mVideos;
    private OnItemClickListener listener;

    public VideoAdapter(Context mContext, List<Video> mVideos, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mVideos = mVideos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video videoLink = mVideos.get(position);
        holder.bind(videoLink,listener);
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView videoThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail = itemView.findViewById(R.id.video_thumbnail);
        }
        public void bind(final Video videoLink, final OnItemClickListener listener){
            RequestOptions options = new RequestOptions().frame(1000);
            Glide.with(mContext).asBitmap().load(videoLink.getLink()).apply(options).into(this.videoThumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(videoLink);
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Video video);
    }
}
