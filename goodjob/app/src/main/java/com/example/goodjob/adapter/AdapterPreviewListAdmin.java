package com.example.goodjob.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goodjob.R;
import com.example.goodjob.classes.Actividad;
import com.example.goodjob.classes.ActividadEsperaAdmin;
import com.example.goodjob.viewholder.ActivityViewHolder;

import java.util.List;

public class AdapterPreviewListAdmin extends RecyclerView.Adapter<ActivityViewHolder> {
    private List<ActividadEsperaAdmin> activities;
    private Context context;
    private ActivityAdapter.OnActivityListener onActivityListener;

    public AdapterPreviewListAdmin(List<ActividadEsperaAdmin> activities, Context context, ActivityAdapter.OnActivityListener onActivityListener)
    {
        this.activities = activities;
        this.context = context;
        this.onActivityListener = onActivityListener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item,
                viewGroup, false);

        return new ActivityViewHolder(view, onActivityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder activityViewHolder, int i) {
        activityViewHolder.authorName.setText(activities.get(i).getAuthor());
        activityViewHolder.title.setText(activities.get(i).getTitle());
        activityViewHolder.authorName.setText(activities.get(i).getAuthor().substring(0, 20));
        activityViewHolder.description.setText(activities.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    // creando interface para hacer click en las activities
    public interface OnActivityListener
    {
        void onActivityClick(int position);
    }
}
