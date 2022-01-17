package com.example.ma_reu.UI.detailsMeeting;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.repository.MeetingRepository;
import com.example.ma_reu.databinding.LayoutCellMeetingBinding;

public class MeetingListViewHolder extends RecyclerView.ViewHolder {

    public interface MeetingDeleteClickListener {
        void onDeleteCliked (MeetingUi meetingUi);
    }

    private final LayoutCellMeetingBinding binding;
    private final MeetingDeleteClickListener meetingDeleteClickListener;

    public MeetingListViewHolder(ViewGroup parent,MeetingDeleteClickListener meetingDeleteClickListener) {
        this(LayoutCellMeetingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false),meetingDeleteClickListener);
    }


    private MeetingListViewHolder(LayoutCellMeetingBinding binding, MeetingDeleteClickListener meetingDeleteClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.meetingDeleteClickListener = meetingDeleteClickListener;
    }

    public void bind (final MeetingUi meetingHolder) {
        binding.infoMeeting.setText(meetingHolder.getTitle());
        binding.infoParticipant.setText(meetingHolder.getParticipants());
        binding.buttonAvailableMeeting.setColorFilter(meetingHolder.getCircleColor());
        binding.iconDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingDeleteClickListener.onDeleteCliked(meetingHolder);
            }
        });
            }
    }

