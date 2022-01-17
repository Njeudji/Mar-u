package com.example.ma_reu.UI.createMeeting;

import androidx.annotation.NonNull;

import com.example.ma_reu.data.model.Room;

public class AddMeetingViewState {

    @NonNull
    private final Room[] rooms;

    @NonNull
    private final String time;

    @NonNull
    private final String date;




    public AddMeetingViewState(@NonNull Room[] rooms, @NonNull String time, @NonNull String date) {
        this.rooms = rooms;
        this.time = time;
        this.date = date;
    }

    @NonNull
    public Room[] getRoom() {
        return rooms;
    }

    @NonNull
    public String getTime() {
        return time;
    }

}