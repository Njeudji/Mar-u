package com.example.ma_reu.UI.detailsMeeting;

import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;

import java.util.List;

public class ListMeetingFragmentViewState {

    private final List <MeetingUi> meetingsUi;

    public ListMeetingFragmentViewState(List<MeetingUi> meetingsUi) {
        this.meetingsUi = meetingsUi;
    }

    public List<MeetingUi> getMeetingsUi() {
        return meetingsUi;
    }


}
