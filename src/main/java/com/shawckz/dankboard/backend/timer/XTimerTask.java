package com.shawckz.dankboard.backend.timer;

public interface XTimerTask {

    void run();

    long interval();

    boolean isComplete();

    void onComplete();

    boolean isFrozen();

}
