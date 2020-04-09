package com.patricoda.finchycompanionapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.patricoda.finchycompanionapp.R;
import com.patricoda.finchycompanionapp.sound.SoundManager;

public class KnightFinchyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO want to pass sound manager from main activity
        //TODO want to load sounds before create view
        final SoundManager soundManager = new SoundManager(this.getContext(), "ds3");
        View view = inflater.inflate(R.layout.knight_finchy_fragment, container, false);

        ImageButton button = view.findViewById(R.id.finchyButton);
        button.setOnClickListener(
                v -> {
                    soundManager.playSound();
                }
        );

        return view;
    }
}
