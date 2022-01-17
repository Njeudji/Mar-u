package com.example.ma_reu.UI.createMeeting;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ma_reu.R;
import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.model.Room;
import com.example.ma_reu.databinding.FragmentAddMeetingBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddMeetingFragment extends Fragment {

    FragmentAddMeetingBinding binding;
    AddMeetingViewModel viewModel;
    int hourStart = 12, minuteStart = 0;
    int hourEnd = 12, minuteEnd = 0;
    int yearStart, monthStart, dayOfMonthStart;

    private boolean isRoomAutocompleteViewInitialized;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddMeetingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddMeetingViewModel.class);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        this.yearStart = calendar.get(Calendar.YEAR);
        this.monthStart = calendar.get(Calendar.MONTH);
        this.dayOfMonthStart = calendar.get(Calendar.DAY_OF_MONTH);
        this.hourStart = calendar.get(Calendar.HOUR_OF_DAY);
        this.minuteStart = calendar.get(Calendar.MINUTE);
        this.hourEnd = calendar.get(Calendar.HOUR_OF_DAY);
        this.minuteEnd = calendar.get(Calendar.MINUTE);


        binding.textLayoutHourStart.setOnClickListener(v -> {
            new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int m) {
                    hourStart = hourOfDay;
                    minuteStart = m;
                    binding.textLayoutHourStart.setText(hourStart + ":" + minuteStart);
                }
            }, hourStart, minuteStart, true)
                    .show();
        });

        binding.textLayoutHourEnd.setOnClickListener(v -> {
            new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int m) {
                    hourEnd = hourOfDay;
                    minuteEnd = m;
                    binding.textLayoutHourEnd.setText(hourEnd + ":" + minuteEnd);
                }
            }, hourEnd, minuteEnd, true)
                    .show();
        });

        binding.textInputDate.setOnClickListener(v -> {
            new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    yearStart = year;
                    monthStart = month;
                    dayOfMonthStart = dayOfMonth;
                    binding.textInputDate.setText(yearStart + "/" + monthStart + "/" + dayOfMonthStart);
                }
            }, yearStart, monthStart, dayOfMonthStart)
                    .show();
        });


        viewModel.getErrors().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getCloseAction().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getActivity().onBackPressed();
            }
        });

        viewModel.getRoom().observe(getViewLifecycleOwner(), new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                List<String> spinnerArray = new ArrayList<String>();
                for (Room room : rooms) {
                    spinnerArray.add(room.getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerLayoutRoomMeeting.setAdapter(adapter);
            }
        });

        viewModel.getParticipant().observe(getViewLifecycleOwner(), new Observer<List<Participant>>() {
            @Override
            public void onChanged(List<Participant> participants) {
                binding.chipGroupParticipant.removeAllViews();
                for (Participant participant : participants) {
                    final Chip chip = new Chip(getContext());
                    chip.setText(participant.getMail());
                    chip.setChipBackgroundColorResource(R.color.purple_500);
                    chip.setCloseIconVisible(false);
                    chip.setTextColor(getResources().getColor(R.color.white));
                    chip.setClickable(true);
                    chip.setCheckable(true);

                    binding.chipGroupParticipant.addView(chip);
                    chip.setTag(participant);
                }
            }
        });

        binding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final List<Participant> participantsNames = new ArrayList<>();
                final ChipGroup chipGroup = binding.chipGroupParticipant;
                for (Integer checkedChipId : chipGroup.getCheckedChipIds()) {
                    final Chip chip = chipGroup.findViewById(checkedChipId);
                    Object tag = chip.getTag();
                    participantsNames.add(((Participant) tag));
                }
                viewModel.createMeeting(
                        binding.textInputObjectMeeting.getText().toString(),
                        binding.spinnerLayoutRoomMeeting.getSelectedItem().toString(),
                        hourStart, minuteStart,
                        hourEnd, minuteEnd,
                        yearStart, monthStart, dayOfMonthStart,
                        participantsNames);
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

}


