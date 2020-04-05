package com.patricoda.finchycompanionapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.patricoda.finchycompanionapp.sound.SoundManager;

public class FinchyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final SoundManager soundManager = new SoundManager(this.getContext());
        View view =  inflater.inflate(R.layout.fragment_finchy, container, false);

        ImageButton button = view.findViewById(R.id.finchyButton);
        button.setOnClickListener(
                v -> {
                    soundManager.playSound();
                }
        );

        return view;
    }
}
