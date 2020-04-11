package com.patricoda.finchycompanionapp.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.patricoda.finchycompanionapp.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SoundManager {
    private Context context;
    public SoundPool soundPool = new SoundPool(1 , AudioManager.STREAM_MUSIC, 0);
    private List<Integer> soundResourceIdList = new ArrayList<>();

    public SoundManager(Context context, String soundPrefix) {
        this.context = context;
        loadSounds(soundPrefix);
    }

    private void loadSounds(String soundPrefix) {
        List<Field> soundResources = Arrays.asList(R.raw.class.getFields());

        if(!soundPrefix.isEmpty()) {
            List<Field> subset = new ArrayList<>();
            for(Field field: soundResources) {
                String fieldPrefix = field.getName().split("_")[0];
                if(soundPrefix.equals(fieldPrefix)) {
                    subset.add(field);
                }
            }

            soundResources = subset;
        }

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

        soundPool.play(soundResourceIdList.get(0), 1, 1, 0, 0, 1);
    }

    private void shuffleSounds() {
        Collections.shuffle(soundResourceIdList);
    }
}
