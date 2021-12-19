package com.example.ma_reu.UI.create;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.model.Room;
import com.example.ma_reu.data.repository.ParticipantRepository;
import com.example.ma_reu.data.repository.RoomRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class AddMeetingViewModel extends ViewModel {

    private final ParticipantRepository participantRepository = ParticipantRepository.getINSTANCE();
    private final RoomRepository roomRepository = RoomRepository.getINSTANCE();

    private final MutableLiveData<AddMeetingViewState> viewState = new MutableLiveData<>();

    public LiveData<AddMeetingViewState> getViewState() {return viewState;}

    private final MutableLiveData<List<Participant>> participant = new MutableLiveData<>();

    public LiveData<List<Participant>> getParticipant() {return participant;}

    private final MutableLiveData<List<Room>> room = new MutableLiveData<>();

    public LiveData<List<Room>> getRoom() {return room; }


    public AddMeetingViewModel() {
        participant.postValue(participantRepository.getLIST_PARTICIPANT());
        room.postValue(roomRepository.getLIST_ROOM());
    }


    abstract static class ViewAction {
        static class CloseActivity extends ViewAction {
        }

        static class DisplayTimePicker extends ViewAction {
            private final int hour;
            private final int minute;

            DisplayTimePicker(int hour, int minute) {
                this.hour = hour;
                this.minute = minute;
            }

            public int getHour() {
                return hour;
            }

            public int getMinute() {
                return minute;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                DisplayTimePicker that = (DisplayTimePicker) o;
                return hour == that.hour && minute == that.minute;
            }

            @Override
            public int hashCode() {
                return Objects.hash(hour, minute);
            }

            @NonNull
            @Override
            public String toString() {
                return "DisplayTimePicker{" + "hour=" + hour + ", minute=" + minute + '}';
            }
        }
    }
}
