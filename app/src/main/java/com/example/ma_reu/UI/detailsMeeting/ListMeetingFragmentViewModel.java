package com.example.ma_reu.UI.detailsMeeting;

import android.graphics.Color;
import android.provider.CalendarContract;

import androidx.constraintlayout.motion.utils.ViewState;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.repository.MeetingRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMeetingFragmentViewModel extends ViewModel {

    private final MeetingRepository meetingRepository = MeetingRepository.getINSTANCE();

    private final MutableLiveData<ListMeetingFragmentViewState> viewState = new MutableLiveData<>();

    public LiveData<ListMeetingFragmentViewState> getViewState() {
        return viewState;
    }

    public ListMeetingFragmentViewModel() {
    }

    public void DisplayMeeting() {
        List<Meeting> list_meetings = meetingRepository.getLIST_MEETINGS();
        List<MeetingUi> meetingUi = new ArrayList<>();
        for (Meeting meeting : list_meetings) {
            //for(int i = 0; i< list_meetings.size(); ++i) {
            //    Meeting meeting = list_meetings.get(i);
            MeetingUi m = new MeetingUi(generateParticipantString(meeting), meeting.getMeetingRoom(), Color.parseColor("#FFF000"));
            // C'est ici qu'il faudra intégrer le if pour la couleur en fonction de l'heure de la réunion
            meetingUi.add(m);
        }
        viewState.postValue(new ListMeetingFragmentViewState(meetingUi));
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
