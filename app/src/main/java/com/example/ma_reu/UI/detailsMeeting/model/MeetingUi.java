package com.example.ma_reu.UI.detailsMeeting.model;

public class MeetingUi {

    private final String participants;

    private final String title;

    private final int circleColor;

    public MeetingUi(String participants, String title, int circleColor) {
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
}
