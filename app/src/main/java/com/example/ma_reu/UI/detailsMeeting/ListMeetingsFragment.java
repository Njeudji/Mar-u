package com.example.ma_reu.UI.detailsMeeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ma_reu.R;
import com.example.ma_reu.UI.create.AddMeetingFragment;
import com.example.ma_reu.databinding.FragmentAddMeetingBinding;
import com.example.ma_reu.databinding.FragmentListMeetingsBinding;


public class ListMeetingsFragment extends Fragment {

    FragmentListMeetingsBinding binding;
    ListMeetingFragmentViewModel viewModel;
    MeetingListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListMeetingsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MeetingListAdapter();
        binding.recyclerViewListMeetings.setAdapter(adapter);
        binding.recyclerViewListMeetings.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(ListMeetingFragmentViewModel.class);
        viewModel.getViewState().observe(getViewLifecycleOwner(), new Observer<ListMeetingFragmentViewState>() {
            @Override
            public void onChanged(ListMeetingFragmentViewState listMeetingFragmentViewState) {
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
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.DisplayMeeting();
        // avec RxJava on enlève le Resume
    }
}