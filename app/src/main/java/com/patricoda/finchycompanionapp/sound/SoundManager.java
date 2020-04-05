package com.patricoda.finchycompanionapp.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.patricoda.finchycompanionapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO make this an abstract class? have concrete instance per person?
public class SoundManager {
    private Context context;
    private SoundPool soundPool = new SoundPool(1 , AudioManager.STREAM_MUSIC, 0);
    private List<Integer> soundIdList = new ArrayList<>();

    public SoundManager(Context context) {
        this.context = context;
        loadSounds();
    }

    private void loadSounds() {
        //TODO can we loop the resources?
        final ArrayList<Integer> soundResources = new ArrayList<>();
        soundResources.add(R.raw.favourite_flavour);
        soundResources.add(R.raw.gav_it_might);
        soundResources.add(R.raw.got_chapstick);
        soundResources.add(R.raw.is_this_butterscotch);
        soundResources.add(R.raw.maximum_hold);
        soundResources.add(R.raw.taste_like_fudge);

        for(Integer soundResource : soundResources) {
            soundIdList.add(soundPool.load(context, soundResource, 1));
        }
    }

    public void playSound() {
        //TODO pick one randomly over shuffling each time, remove sound to ensure we don't play the same over and over?
        shuffleSounds();
        this.soundPool.play(soundIdList.get(0), 1, 1,0,0,1);
    }

    private void shuffleSounds() {
        Collections.shuffle(soundIdList);
    }
}
