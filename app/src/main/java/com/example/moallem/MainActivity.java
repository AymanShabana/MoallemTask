package com.example.moallem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.example.moallem.Adapter.SubjectAdapter;
import com.example.moallem.Adapter.VideoAdapter;
import com.example.moallem.Model.Video;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mRoot;

    private RecyclerView recyclerViewSubjects;
    private SubjectAdapter subjectAdapter;
    private List<String> subjectList;

    private RecyclerView recyclerViewVideos;
    private VideoAdapter videoAdapter;
    private List<Video> videoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRoot = FirebaseDatabase.getInstance().getReference().child("VideoURLS");

        recyclerViewSubjects = findViewById(R.id.recycler_view_subjects);
        recyclerViewSubjects.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewSubjects.setLayoutManager(linearLayoutManager);
        subjectList = new ArrayList<>();
        subjectList.add("Physics");
        subjectList.add("Biology");
        subjectList.add("History");
        subjectList.add("Algebra");
        subjectAdapter = new SubjectAdapter(getApplicationContext(),subjectList);
        recyclerViewSubjects.setAdapter(subjectAdapter);

        recyclerViewVideos = findViewById(R.id.recycler_view_videos);
        recyclerViewVideos.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewVideos.setLayoutManager(linearLayoutManager2);
        videoList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getApplicationContext(),videoList,new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Video video) {
                //Toast.makeText(ViewRequestsActivity.this, request.getRequesterId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
                intent.putExtra("video", video);
                startActivity(intent);
            }
        });
        recyclerViewVideos.setAdapter(videoAdapter);

        mRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                videoList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Video video = snapshot.getValue(Video.class);
                    Log.i("VideoLink",video.getLink());
                    videoList.add(video);
                }
                videoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*
        Intent i = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(i);
        */

    }
}
