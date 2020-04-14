package com.patricoda.finchycompanionapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.patricoda.finchycompanionapp.R;
import com.patricoda.finchycompanionapp.sound.SoundManager;

public class FinchyTabFragment extends TabFragment {
    final int layoutResource;
    final String soundPrefix;
    SoundManager soundManager = null;

    public FinchyTabFragment(final String tabTitle, final int layoutResource, final String soundPrefix) {
        super(tabTitle);
        this.layoutResource = layoutResource;
        this.soundPrefix = soundPrefix;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.soundManager = new SoundManager(this.getContext(), soundPrefix);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(this.layoutResource, container, false);

        ImageButton button = view.findViewById(R.id.finchyButton);

        button.setOnClickListener(
                v -> {
                    soundManager.playRandomSound();
                }
        );

        return view;
    }
}
