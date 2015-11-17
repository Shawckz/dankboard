package com.shawckz.dankboard.frontend.timer;


import com.shawckz.dankboard.backend.timer.XScoreboardTimer;
import com.shawckz.dankboard.backend.timer.XTimerTask;

public abstract class DankTimerTask implements XTimerTask {

    private final XScoreboardTimer timer;
    private final long interval;

    public DankTimerTask(XScoreboardTimer timer, long interval) {
        this.timer = timer;
        this.interval = interval;
    }

    @Override
    public abstract void run();

    @Override
    public long interval() {
        return interval;
    }

    @Override
    public boolean isComplete() {
        return interval <= 0.1;
    }

    @Override
    public void onComplete() {
        timer.setVisible(false);
        timer.setFrozen(true);
        timer.updateLabel();
    }

    @Override
    public boolean isFrozen() {
        return timer.isFrozen();
    }
}
