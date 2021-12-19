package com.example.ma_reu.data.repository;

import com.example.ma_reu.data.model.Room;

import java.util.Arrays;
import java.util.List;

public class RoomRepository {

    private static RoomRepository INSTANCE;

    public static RoomRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RoomRepository();
        }
        return INSTANCE;
    }

        private RoomRepository() {
        }

        private List<Room> LIST_ROOM =
                Arrays.asList(
                        new Room("SANKARA"),
                        new Room("LUMUMBA"),
                        new Room("DIOP"),
                        new Room("ELITE"),
                        new Room("DYNASTIE"),
                        new Room("GEOPO"),
                        new Room("KHRUMA"),
                        new Room("ISIS"),
                        new Room("ICARE"),
                        new Room("TECHNIQUE")
                        );

    public List<Room> getLIST_ROOM() {
        return LIST_ROOM;
    }
}
