package com.example.ma_reu.data.repository;

import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.model.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRepository {

    private static MeetingRepository INSTANCE;

    public static MeetingRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MeetingRepository();
        }
        return INSTANCE;
    }

    private List<Meeting> LIST_MEETINGS = new ArrayList<>(
    );

    private MeetingRepository() {
        CreateMeeting(new Meeting("Barbe","SANKARA","10/08/2021","10","12","Toto", Arrays.asList(new Participant(0,"ama@lamazone.com","ama"),new Participant(1,"tata@lamazone.com","Tata"))));
    }

    public List<Meeting> getLIST_MEETINGS() {
        return LIST_MEETINGS;
    }



    public void CreateMeeting (Meeting meeting) {
        LIST_MEETINGS.add(meeting);
    }
}

