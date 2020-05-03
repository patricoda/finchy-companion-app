package com.patricoda.finchycompanionapp.view.fragment;

import android.content.Context;
import android.util.AttributeSet;

import com.patricoda.finchycompanionapp.R;

public class ImageButtonWithSoundState extends androidx.appcompat.widget.AppCompatImageButton {
    private static final int[] PLAYING_SOUND = {R.attr.state_playing_sound};
    private boolean isPlayingSound = false;

    public ImageButtonWithSoundState(Context context) {
        super(context);
    }

    public ImageButtonWithSoundState(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageButtonWithSoundState(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean getIsPlayingSound() {
        return this.isPlayingSound;
    }

    public void setPlayingSound(final boolean isPlayingSound, final boolean refreshDrawableState) {
        this.isPlayingSound = isPlayingSound;

        if(refreshDrawableState) {
            this.refreshDrawableState();
        }
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isPlayingSound) {
            mergeDrawableStates(drawableState, PLAYING_SOUND);
        }
        return drawableState;
    }
}
