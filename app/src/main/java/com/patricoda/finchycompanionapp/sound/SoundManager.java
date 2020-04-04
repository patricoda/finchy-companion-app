package com.patricoda.finchycompanionapp.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.patricoda.finchycompanionapp.R;

import java.util.ArrayList;
import java.util.List;

//make this an abstract class, have concrete instance per person?
public class SoundManager {
    private Context context;
    private SoundPool soundPool = new SoundPool(1 , AudioManager.STREAM_MUSIC, 0);
    private List<Integer> soundIdList = new ArrayList<>();

    public SoundManager(Context context) {
        this.context = context;
        loadSounds();
    }

    public void playSound() {
        this.soundPool.play(soundIdList.get(0), 1, 1,0,0,1);
    }

    private void loadSounds() {
        final int soundId = soundPool.load(context, R.raw.taste_like_fudge, 1);
        soundIdList.add(soundId);
    }
}
