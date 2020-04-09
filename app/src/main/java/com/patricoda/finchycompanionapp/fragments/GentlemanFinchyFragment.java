package com.patricoda.finchycompanionapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.patricoda.finchycompanionapp.R;
import com.patricoda.finchycompanionapp.sound.SoundManager;

public class GentlemanFinchyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO want to pass sound manager from main activity
        final SoundManager soundManager = new SoundManager(this.getContext(), "");
        View view =  inflater.inflate(R.layout.gentleman_finchy_fragment, container, false);

        ImageButton button = view.findViewById(R.id.finchyButton);
        button.setOnClickListener(
                v -> {
                    soundManager.playSound();
                }
        );

        return view;
    }
}
