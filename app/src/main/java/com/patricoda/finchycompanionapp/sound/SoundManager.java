package com.patricoda.finchycompanionapp.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.patricoda.finchycompanionapp.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO make this an abstract class? have concrete instance per person?
public class SoundManager {
    private Context context;
    private SoundPool soundPool = new SoundPool(1 , AudioManager.STREAM_MUSIC, 0);
    private List<Integer> soundResourceIdList = new ArrayList<>();

    public SoundManager(Context context) {
        this.context = context;
        loadSounds();
    }

    private void loadSounds() {
        Field[] soundResources = R.raw.class.getFields();

        for(Field sound: soundResources) {
            try {
                soundResourceIdList.add(soundPool.load(context, sound.getInt(sound), 1));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void playSound() {
        //TODO pick one randomly over shuffling each time, remove sound to ensure we don't play the same over and over?
        shuffleSounds();
        this.soundPool.play(soundResourceIdList.get(0), 1, 1,0,0,1);
    }

    private void shuffleSounds() {
        Collections.shuffle(soundResourceIdList);
    }
}
