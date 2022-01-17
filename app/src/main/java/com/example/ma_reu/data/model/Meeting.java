package com.example.ma_reu.data.model;

import java.util.Calendar;
import java.util.List;

public class Meeting {

    private String id;
    private String objectMeeting;
    private String meetingRoom;
    private String date;
    private String hourStart;
    private String hourEnd;
    private String information;
    private List<Participant> listParticipants;

    public String getObjectMeeting() {
        return objectMeeting;
    }

    public void setObjectMeeting(String objectMeeting) {
        this.objectMeeting = objectMeeting;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHourStart() {
        return hourStart;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<Participant> getListParticipants() {
        return listParticipants;
    }

    public void setListParticipants(List<Participant> listParticipants) {
        this.listParticipants = listParticipants;
    }

    public String getId() {
        return id;
    }


    /**
     * Constructor
     * @param id : String
     * @param objectMeeting : String
     * @param meetingRoom : String
     * @param date : String
     * @param hourStart : String
     * @param hourEnd : String
     * @param information : String
     * @param participants : List<Participant>
     */

    public Meeting(String id, String objectMeeting, String meetingRoom, String date, String hourStart, String hourEnd, String information, List<Participant> listParticipants) {
        this.id = id;
        this.objectMeeting = objectMeeting;
        this.meetingRoom = meetingRoom;
        this.date = date;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.information = information;
        this.listParticipants = listParticipants;
    }
}
