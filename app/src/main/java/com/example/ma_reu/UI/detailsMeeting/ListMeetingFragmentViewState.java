package com.example.ma_reu.UI.detailsMeeting;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;

import java.util.List;

public class ListMeetingFragmentViewState {

    private final boolean displayClearButton;

    private final List <MeetingUi> meetingsUi;

    public ListMeetingFragmentViewState(List<MeetingUi> meetingsUi, boolean displayClearButton) {
        this.meetingsUi = meetingsUi;
        this.displayClearButton = displayClearButton;
    }

    public List<MeetingUi> getMeetingsUi() {
        return meetingsUi;
    }

    public boolean isDisplayClearButton() {
        return displayClearButton;
    }

}
