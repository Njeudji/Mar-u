package com.example.ma_reu.UI.detailsMeeting;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.data.repository.MeetingRepository;

import java.util.ArrayList;
import java.util.List;

public class MeetingListAdapter extends RecyclerView.Adapter <MeetingListViewHolder> {

    public interface MeetingDeleteClickListener {
        void onDeleteCliked (MeetingUi meetingUi);
    }

    public MeetingDeleteClickListener meetingDeleteClickListener;
    private final ArrayList<MeetingUi> meetingUiArrayList = new ArrayList<>();

    @NonNull
    @Override
    public MeetingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeetingListViewHolder(parent, new MeetingListViewHolder.MeetingDeleteClickListener() {
            @Override
            public void onDeleteCliked(MeetingUi meetingUi) {
                if (meetingDeleteClickListener != null) {
                    meetingDeleteClickListener.onDeleteCliked(meetingUi);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingListViewHolder holder, int position) {
        holder.bind(meetingUiArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return meetingUiArrayList.size();
    }

    public void update (List<MeetingUi> newMeetings) {
        meetingUiArrayList.clear();
        meetingUiArrayList.addAll(newMeetings);
        notifyDataSetChanged();
    }
}