package com.example.ma_reu.data.repository;

import com.example.ma_reu.data.model.Participant;

import java.util.Arrays;
import java.util.List;

public class ParticipantRepository {

    private static ParticipantRepository INSTANCE;

    public static ParticipantRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ParticipantRepository();
        }
        return INSTANCE;
    }

    private ParticipantRepository(){
    }

    private List<Participant> LIST_PARTICIPANT =
            Arrays.asList(
                    new Participant(0,"nzinga@lamzone","nzinga"),
                    new Participant(1,"amanda@lamzone","amanda"),
                    new Participant(2,"thomas@lamzone","thomas"),
                    new Participant(3,"nkwame@lamzone","nkwame"),
                    new Participant(4,"aime@lamzone","aime"),
                    new Participant(5,"frantz@lamzone","frantz"),
                    new Participant(6,"angela@lamzone","angela"),
                    new Participant(7,"winnie@lamzone","winnie"),
                    new Participant(8,"isidore@lamzone","isidore"),
                    new Participant(9,"malcom@lamzone","malcom"),
                    new Participant(10,"kira@lamzone","kira"),
                    new Participant(11,"soundjata@lamzone","soundjata")
    );

    public List<Participant> getLIST_PARTICIPANT() {
        return LIST_PARTICIPANT;
    }
}
