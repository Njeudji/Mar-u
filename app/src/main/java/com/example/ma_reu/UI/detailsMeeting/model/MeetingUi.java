package com.example.ma_reu.UI.detailsMeeting.model;

import android.view.View;

public class MeetingUi {

    private final String participants;

    private final String title;

    private final int circleColor;

    private final String id;

    public MeetingUi(String id, String participants, String title, int circleColor) {
        this.id = id;
        this.participants = participants;
        this.title = title;
        this.circleColor = circleColor;
    }

    public String getParticipants() {
        return participants;
    }

    public String getTitle() {
        return title;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public String getId() {return id;}
}
