package com.example.ma_reu.UI.detailsMeeting;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.databinding.LayoutCellMeetingBinding;

public class MeetingListViewHolder extends RecyclerView.ViewHolder {

    private final LayoutCellMeetingBinding binding;

    public MeetingListViewHolder(ViewGroup parent) {
        this(LayoutCellMeetingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    private MeetingListViewHolder(LayoutCellMeetingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind (MeetingUi meetingHolder) {
        binding.infoMeeting.setText(meetingHolder.getTitle());
        binding.infoParticipant.setText(meetingHolder.getParticipants());
        binding.buttonAvailableMeeting.setImageDrawable(new ColorDrawable(meetingHolder.getCircleColor()));
    }
}
