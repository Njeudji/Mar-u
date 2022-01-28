package com.example.ma_reu.data.repository;

import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.model.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
        CreateMeeting(new Meeting("0","Barbe", "SANKARA", "10/08/2021", "10", "12", "Toto", Arrays.asList(new Participant(0, "ama@lamazone.com", "ama"), new Participant(1, "tata@lamazone.com", "Tata"))));

    }

    public List<Meeting> getLIST_MEETINGS() {
        return LIST_MEETINGS;
    }


    public void CreateMeeting(Meeting meeting) {
        LIST_MEETINGS.add(meeting);
    }

    public void DeleteMeeting(Meeting meeting) {
        LIST_MEETINGS.remove(meeting);
    }


    public List<Meeting> filterByDate(String date) {
        List<Meeting> filterDate = new ArrayList<>();
        for (Meeting meeting : LIST_MEETINGS) {
            if (meeting.getDate().equals(date)) {
                filterDate.add(meeting);
            }
        }
        return filterDate;
    }

    /*
    public List<Meeting> filterByDate(String date){
        List<Meeting> filterDate = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                for (Meeting meeting : LIST_MEETINGS) {
                    Calendar calender1 = Calendar.getInstance();
                    calender1.setTime(date);
                    if (date.compareTo(calender1) > 0) {
                        filterByDate(date).add(meeting);
                    }
                }
                return filterByDate(date);
    }
     */

    /*
    TODO : String sDate1="31/12/1998";
    public List<Meeting> filterByDate(String date) {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date date1=formatter1.parse(sDate1);
        return LIST_MEETINGS.filterByDate(date);
        String date1 = "31/12/1998";
    }
     */

    public List<Meeting> filterByRoom(String place) {
        List<Meeting> filterRoom = new ArrayList<>();
        for (Meeting meeting : LIST_MEETINGS) {
            if (place.equals(meeting.getMeetingRoom())){
                filterRoom.add(meeting);
            }
        }
        return filterRoom;
    }

    public List<Meeting> filterByRoom(List<Room> rooms) {
        List<Meeting> filterRoom = new ArrayList<>();
        for (Meeting meeting : LIST_MEETINGS) {
            for (Room room : rooms) {
                if (room.getName().equals(meeting.getMeetingRoom())) {
                    filterRoom.add(meeting);
                }
            }
        }
        return filterRoom;
    }


    //Filtre qui retourne un élément = meeting a supprimé
    public Meeting findMeeting(String id) {
        for (Meeting meeting : LIST_MEETINGS) {
            if (id.equals(meeting.getId())){
                return meeting;
            }
        }
        return null;
    }
}

