package com.example.ma_reu.UI.detailsMeeting;

import android.graphics.Color;
import android.provider.CalendarContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.model.Room;
import com.example.ma_reu.data.repository.MeetingRepository;
import com.example.ma_reu.data.repository.RoomRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executor;

public class ListMeetingFragmentViewModel extends ViewModel {

    private final MeetingRepository meetingRepository = MeetingRepository.getINSTANCE();
    private final RoomRepository roomRepository = RoomRepository.getINSTANCE();

    private final MutableLiveData<ListMeetingFragmentViewState> viewState = new MutableLiveData<>();

    public LiveData<ListMeetingFragmentViewState> getViewState() {
        return viewState;
    }

    private final MutableLiveData<List<Room>> room = new MutableLiveData<>();

    public LiveData<List<Room>> getRoom() {
        return room;
    }

    public ListMeetingFragmentViewModel() {
        room.postValue(roomRepository.getLIST_ROOM());
    }

    @NonNull
    public void DisplayMeeting() {
        List<Meeting> list_meetings = meetingRepository.getLIST_MEETINGS();
        display(list_meetings,false);
    }

    private void display(List<Meeting> list_meetings, boolean isFiltered) {
        List<MeetingUi> meetingUi = new ArrayList<>();
        for (Meeting meeting : list_meetings) {
            //for(int i = 0; i< list_meetings.size(); ++i) {
            //    Meeting meeting = list_meetings.get(i);
            MeetingUi m = new MeetingUi(meeting.getId(), generateParticipantString(meeting), meeting.getObjectMeeting() + " - "+  meeting.getMeetingRoom() +  " - " + meeting.getHourStart(),  generateColor(meeting));
            //TODO : rajouter les informations en suivant la note de cadrage
            //TODO: // C'est ici qu'il faudra intégrer le if pour la couleur en fonction de l'heure de la réunion avec un compareTo en comparant la date du téléphone (Calendar.getinstance()) à la date de l'application.
            meetingUi.add(m);
        }
        viewState.postValue(new ListMeetingFragmentViewState(meetingUi,isFiltered));
    }

    private int generateColor(Meeting meeting) {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int hourOfMeeting = Integer.valueOf(meeting.getHourStart().split(":")[0]);
        if (hourOfMeeting < hour){
            return Color.parseColor("#F44336");
        }
        else
            return Color.parseColor("#40D909");
    }

    public void OnDeleteViewModel(MeetingUi meetingUi) {
        Meeting meeting = meetingRepository.findMeeting(meetingUi.getId());
        if (meeting != null) {
            meetingRepository.DeleteMeeting(meeting);
            DisplayMeeting();
        }
    }

    public void filterByDate (String date) {
        List<Meeting> list_meetings = meetingRepository.filterByDate(date);
        display(list_meetings,true);
    }

    public void filterByRoom (List<Room> rooms) {
        List<Meeting> list_meetings = meetingRepository.filterByRoom(rooms);
        display(list_meetings,true);
    }

    private String generateParticipantString(Meeting meeting) {
        String generate = "";
        for (Participant participant: meeting.getListParticipants()) {
           if(!generate.isEmpty()){
               generate += ",";
           }
            generate += participant.getMail();
        }
        return generate;
    }
}
