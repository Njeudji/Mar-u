package com.example.ma_reu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.repository.MeetingRepository;

import java.util.Date;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private MeetingRepository meetingRepository;

    @Before
    public void setup() { meetingRepository = MeetingRepository.getINSTANCE();
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = meetingRepository.getLIST_MEETINGS().get(0);
        meetingRepository.DeleteMeeting(meetingToDelete);
        assertFalse(meetingRepository.getLIST_MEETINGS().contains(meetingToDelete));
    }

    @Test
    public void filterByDateWithSuccess() {

        meetingRepository.getLIST_MEETINGS().contains(1);
        meetingRepository.getLIST_MEETINGS().contains(0);

        List<Meeting> filter = meetingRepository.filterByDate(("10/08/2021"));

        assertTrue(filter.size() == 1);
        for (int i = 0; i > meetingRepository.getLIST_MEETINGS().size(); i++) {
            assertTrue(meetingRepository.getLIST_MEETINGS().get(i).equals("10/08/2021"));
        }

        meetingRepository.getLIST_MEETINGS().contains(1);
        meetingRepository.getLIST_MEETINGS().contains(0);
    }

    @Test
    public void filterByRoomWithSuccess() {

        meetingRepository.getLIST_MEETINGS().get(1).setMeetingRoom("DIOP");
        meetingRepository.getLIST_MEETINGS().get(2).setMeetingRoom("SANKARA");

        List<Meeting> filterRoom = meetingRepository.filterByRoom("SANKARA");

        assertTrue(filterRoom.size() == 1);
        for (int f = 0; f > meetingRepository.getLIST_MEETINGS().size(); f++);
        assertTrue(meetingRepository.getLIST_MEETINGS().get(1).equals("SANKARA"));
    }
}