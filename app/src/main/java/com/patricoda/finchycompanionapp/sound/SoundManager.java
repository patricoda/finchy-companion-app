package com.patricoda.finchycompanionapp.sound;

import android.media.AudioManager;
import android.media.SoundPool;

//make this an abstract class, have concrete instance per person?
public class SoundManager {
    private SoundPool soundPool = new SoundPool(1 , AudioManager.STREAM_MUSIC, 0);

    public SoundManager() {
        soundPool.load();
    }
}
