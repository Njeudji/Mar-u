package com.example.ma_reu.UI.createMeeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ma_reu.UI.utils.livedata.SingleLiveEvent;
import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.model.Room;
import com.example.ma_reu.data.repository.MeetingRepository;
import com.example.ma_reu.data.repository.ParticipantRepository;
import com.example.ma_reu.data.repository.RoomRepository;

import java.util.List;
import java.util.UUID;

public class AddMeetingViewModel extends ViewModel {


    private final ParticipantRepository participantRepository = ParticipantRepository.getINSTANCE();
    private final RoomRepository roomRepository = RoomRepository.getINSTANCE();
    private final MeetingRepository meetingRepository = MeetingRepository.getINSTANCE();

    public LiveData<Boolean> getCloseAction() {
        return closeAction;
    }

    private final SingleLiveEvent<Boolean> closeAction = new SingleLiveEvent<>();

    private final MutableLiveData<AddMeetingViewState> viewState = new MutableLiveData<>();

    private final MutableLiveData<List<Participant>> participant = new MutableLiveData<>();

    public LiveData<List<Participant>> getParticipant() {
        return participant;
    }

    private final MutableLiveData<List<Room>> room = new MutableLiveData<>();

    public LiveData<List<Room>> getRoom() {
        return room;
    }

    private final SingleLiveEvent<String> errors = new SingleLiveEvent<>();

    public SingleLiveEvent<String> getErrors() {
        return errors;
    }
    public AddMeetingViewModel() {
        participant.postValue(participantRepository.getLIST_PARTICIPANT());
        room.postValue(roomRepository.getLIST_ROOM());
    }

    public void createMeeting(String objectMeeting, String room, int hourStart, int minuteStart, int hourEnd, int minuteEnd, int yearStart, int monthStart, int dayOfMonthStart,List<Participant> participants) {
        if (objectMeeting == null || objectMeeting.trim().isEmpty()) {
            errors.setValue("Vous devez mettre un objet à cette réunion");
            return;
        }
        if (hourEnd < hourStart){
            errors.setValue("L'heure de fin ne doit pas être inférieur à l'heure de départ");
            return;
        }
        if (participants.isEmpty()){
            errors.setValue("Cette réunion ne contient pas de participant");
            return;
        }

        //UUID = Créer un id unique
                Meeting meeting = new Meeting(UUID.randomUUID().toString(),
                objectMeeting, room, yearStart + "/" + monthStart + "/" + dayOfMonthStart + "",
                hourStart + ":" + minuteStart,
                minuteEnd + ":" + minuteEnd, "",
                participants
        );
        meetingRepository.CreateMeeting(meeting);
        closeAction.setValue(true);
    }
}