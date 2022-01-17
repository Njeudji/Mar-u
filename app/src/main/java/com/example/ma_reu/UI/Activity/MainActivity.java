package com.example.ma_reu.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ma_reu.R;

public class MainActivity extends AppCompatActivity {

    //Parameters to handle fragments to display



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setupToolbar();

        //Initialize Fragments
        //addMeetingFragment = AddMeetingFragment.newInstance();
        //ListParticipantFragment = ListParticipantFragment.newInstance();
    }


    private void getLayoutInflater(int layout_dialog_filter_meeting_date) {
    }
}