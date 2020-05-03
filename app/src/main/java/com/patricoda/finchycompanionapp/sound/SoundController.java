package com.patricoda.finchycompanionapp.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.patricoda.finchycompanionapp.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import androidx.lifecycle.MutableLiveData;

public class SoundController {
    private Context context;
    private SoundPool soundPool = new SoundPool(1 , AudioManager.STREAM_MUSIC, 0);
    private List<SoundPoolResourceIdWithDuration> soundPoolResourceList = new ArrayList<>();
    private List<SoundPoolResourceIdWithDuration> playedSoundPoolResourceList = new ArrayList<>();
    private Random randomSoundSelector = new Random();
    private MutableLiveData<Boolean> isPlayingFlag = new MutableLiveData<>();

    public MutableLiveData<Boolean> isPlaying() {
        return this.isPlayingFlag;
    }

    public SoundController(Context context, String soundPrefix) {
        this.context = context;
        this.isPlayingFlag.setValue(false);

        loadSounds(soundPrefix);
    }

    private void loadSounds(String soundPrefix) {
        List<Field> rawSoundResources = Arrays.asList(R.raw.class.getFields());

        if(!soundPrefix.isEmpty()) {
            List<Field> subset = new ArrayList<>();
            for(Field field: rawSoundResources) {
                String fieldPrefix = field.getName().split("_")[0];
                if(soundPrefix.equals(fieldPrefix)) {
                    subset.add(field);
                }
            }

            rawSoundResources = subset;
        }

        populateSoundResourcesList(rawSoundResources);
    }

    private void populateSoundResourcesList(List<Field> rawSoundResources) {
        for(Field sound: rawSoundResources) {
            try {
                int rawSoundResourceId = sound.getInt(sound);
                int soundPoolResourceId = soundPool.load(context, rawSoundResourceId, 1);

                SoundPoolResourceIdWithDuration soundResource = new SoundPoolResourceIdWithDuration(soundPoolResourceId, getSoundDuration(rawSoundResourceId));
                soundPoolResourceList.add(soundResource);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void playRandomSound() {
        int selectedIndex = randomSoundSelector.nextInt(soundPoolResourceList.size());
        final SoundPoolResourceIdWithDuration selectedSound = soundPoolResourceList.get(selectedIndex);

        playSound(selectedSound);
    }

    public void playSound(final SoundPoolResourceIdWithDuration selectedSound) {
        isPlayingFlag.setValue(true);
        soundPool.play(selectedSound.getId(), 1, 1, 0, 0, 1);

        //use the duration to determine when isPlayingFlag should be set to false
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isPlayingFlag.postValue(false);
            }
        }, selectedSound.getDuration());

        transferSoundToPlayedList(selectedSound);
    }

    public void transferSoundToPlayedList(final SoundPoolResourceIdWithDuration selectedSound) {
        soundPoolResourceList.remove(selectedSound);
        playedSoundPoolResourceList.add(selectedSound);

        //if we are out of sounds to select from, reset the lists
        if(soundPoolResourceList.isEmpty()) {
            soundPoolResourceList.addAll(playedSoundPoolResourceList);
            playedSoundPoolResourceList.clear();
        }
    }

    private long getSoundDuration(int soundId){
        final MediaPlayer player = MediaPlayer.create(context, soundId);
        final int duration = player.getDuration();

        player.release();
        return duration;
    }

    private class SoundPoolResourceIdWithDuration {
        private int id;
        private long duration;

        public SoundPoolResourceIdWithDuration(final int id, final long duration){
            this.id = id;
            this.duration = duration;
        }

        public int getId() {
            return this.id;
        }

        public long getDuration() {
            return this.duration;
        }
    }
}
