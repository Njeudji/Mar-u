package com.example.ma_reu.UI.detailsMeeting;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ma_reu.R;
import com.example.ma_reu.UI.createMeeting.AddMeetingFragment;
import com.example.ma_reu.UI.detailsMeeting.model.MeetingUi;
import com.example.ma_reu.data.model.Participant;
import com.example.ma_reu.data.model.Room;
import com.example.ma_reu.databinding.FragmentListMeetingsBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ListMeetingsFragment extends Fragment {

    ListMeetingFragmentViewState viewState;
    FragmentListMeetingsBinding binding;
    ListMeetingFragmentViewModel viewModel;
    MeetingListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = FragmentListMeetingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MeetingListAdapter();
        binding.recyclerViewListMeetings.setAdapter(adapter);
        adapter.meetingDeleteClickListener = new MeetingListAdapter.MeetingDeleteClickListener() {
            @Override
            public void onDeleteCliked(MeetingUi meetingUi) {
                viewModel.OnDeleteViewModel(meetingUi);
            }
        };
        binding.recyclerViewListMeetings.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(ListMeetingFragmentViewModel.class);
        viewModel.getRoom().observe(getViewLifecycleOwner(), new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                ChipGroup chipGroup = binding.chipGroupRoomItem;
                chipGroup.removeAllViews();
                for (int i = 0; i < rooms.size() ; i++) {
                    Room room = rooms.get(i);
                    Chip chip = new Chip(getContext());
                    chip.setId(View.generateViewId());
                    chip.setText(room.getName());
                    chip.setEnsureMinTouchTargetSize(false);
                    chip.setChipBackgroundColorResource(R.color.green);
                    chip.setCloseIconVisible(false);
                    chip.setTextColor(getResources().getColor(R.color.white));
                    chip.setClickable(true);
                    chip.setCheckable(true);
                    chipGroup.addView(chip);

                    chip.setTag(room);
                    chip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onChangedChecked();
                        }
                    });
                }
            }
        });
        viewModel.getViewState().observe(getViewLifecycleOwner(), new Observer<ListMeetingFragmentViewState>() {
            @Override
            public void onChanged(ListMeetingFragmentViewState listMeetingFragmentViewState) {
                if (
                        listMeetingFragmentViewState.isDisplayClearButton()
                ) {
                    binding.refreshButton.setVisibility(View.VISIBLE);
                } else {
                    binding.refreshButton.setVisibility(View.GONE);
                }
                adapter.update(listMeetingFragmentViewState.getMeetingsUi());
            }
        });
        binding.fabListMeetingsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ajout d'un fragment (substitue d'intent d'Activity, le code sera toujours similaire)
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new AddMeetingFragment())
                        .addToBackStack(null).commitAllowingStateLoss();
                // ADD = rajouter la page
                // Replace = site où il faut mettre login
            }
        });
        binding.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.DisplayMeeting();
            }
        });
    }

    private void onChangedChecked() {
        ChipGroup group = binding.chipGroupRoomItem;
        final List<Room> rooms = new ArrayList<>();
        for (Integer checkedChipId : group.getCheckedChipIds()) {
            final Chip chip = group.findViewById(checkedChipId);
            Object tag = chip.getTag();
            rooms.add(((Room) tag));
        }
        viewModel.filterByRoom(rooms);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_filter_date_participant_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterByDate: {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                int yearStart = calendar.get(Calendar.YEAR);
                int monthStart = calendar.get(Calendar.MONTH);
                int dayOfMonthStart = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        viewModel.filterByDate(year + "/" + month + "/" + dayOfMonth);
                    }
                }, yearStart, monthStart, dayOfMonthStart)
                        .show();
                return true;
            }

            case R.id.filterByRoom:

               binding.horizontalFilter.setVisibility(View.VISIBLE);
            }
        return true;
    }

        @Override
        public void onResume () {
            super.onResume();
            viewModel.DisplayMeeting();
            // avec RxJava on enlève le Resume
        }
    }

