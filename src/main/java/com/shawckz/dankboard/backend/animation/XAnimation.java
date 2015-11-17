package com.shawckz.dankboard.backend.animation;

import com.shawckz.dankboard.backend.timer.XTimerTask;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Jonah on 11/7/2015.
 */
@RequiredArgsConstructor
@Setter
public abstract class XAnimation implements XTimerTask {

    private final Animation animation;
    private final long interval;

    public String animate(String text) {
        return animation.animate(text);
    }

    @Override
    public abstract void run();

    @Override
    public long interval() {
        return interval;
    }

    @Override
    public abstract boolean isComplete();

    @Override
    public abstract void onComplete();

    @Override
    public abstract boolean isFrozen();
}
