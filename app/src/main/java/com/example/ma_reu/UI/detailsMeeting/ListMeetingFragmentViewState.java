package com.example.ma_reu.UI.detailsMeeting;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.data.model.Room;
import com.example.ma_reu.data.repository.MeetingRepository;
import com.example.ma_reu.data.repository.RoomRepository;

import java.util.List;

public class ListMeetingFragmentViewState {

    private final boolean displayClearButton;

    private final List <MeetingUi> meetingsUi;

    private final List<Room> rooms;

    public ListMeetingFragmentViewState(List<MeetingUi> meetingsUi, boolean displayClearButton, List<Room> rooms) {
        this.meetingsUi = meetingsUi;
        this.displayClearButton = displayClearButton;
        this.rooms = rooms;
    }

    public List<Room> getRooms() {return rooms;}

    public List<MeetingUi> getMeetingsUi() {
        return meetingsUi;
    }

    public boolean isDisplayClearButton() {
        return displayClearButton;
    }

}
