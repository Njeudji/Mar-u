package com.example.ma_reu.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ma_reu.R;
import com.example.ma_reu.UI.create.AddMeetingFragment;
import com.example.ma_reu.UI.detailsParticipant.ListParticipantFragment;

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

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter_date_participant_fragment,menu);
                return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_activity_main_search:{
                getLayoutInflater(R.layout.layout_dialog_filter_meeting_date);
                return true;



                }
            }
        return super.onOptionsItemSelected(item);
        }


    private void getLayoutInflater(int layout_dialog_filter_meeting_date) {
    }
}