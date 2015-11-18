package com.shawckz.dankboard.frontend.timer;

import com.shawckz.dankboard.backend.XScoreboard;
import com.shawckz.dankboard.backend.timer.TimerPool;
import com.shawckz.dankboard.backend.timer.XScoreboardTimer;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class DankTimer extends XScoreboardTimer {

    private final String key;
    private final DankTimerFormat format;
    private double time = 0.0D;
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.#");

    public DankTimer(XScoreboard scoreboard, String key, int score, TimerPool timerPool, DankTimerFormat format) {
        super(scoreboard, key + ChatColor.GRAY + " - " + ChatColor.GOLD + "0.0", score, timerPool);
        this.key = key;
        this.format = format;
        getTimerPool().registerTimer(new DankTimerTask(this, timerPool.getInterval()) {
            @Override
            public void run() {
                if (time - 0.1D > 0) {
                    setTime(time - 0.1D);
                } else {
                    onComplete();
                }
            }
        });
    }

    public DankTimer(XScoreboard scoreboard, String key, int score, TimerPool timerPool) {
        this(scoreboard, key + ChatColor.GRAY + " - " + ChatColor.GOLD + "0.0", score, timerPool, DankTimerFormat.TENTH_OF_SECOND);
    }

    public void setTime(double time) {
        this.time = time;
        if (time > 0) {
            if (isFrozen()) {
                setFrozen(false);
            }
            if (!isVisible()) {
                setVisible(true);
            }
            updateTime();
        }
    }

    public void pauseTimer() {
        setFrozen(true);
        updateLabel();
    }

    public void unpauseTimer() {
        setFrozen(false);
        updateLabel();
    }

    public void stopTimer() {
        setFrozen(true);
        setVisible(false);
        setTime(0.0D);
        updateLabel();
    }

    public String getKey() {
        return key;
    }

    public double getTime() {
        return time;
    }

    private void updateTime() {
        if (format == DankTimerFormat.TENTH_OF_SECOND) {
            setValue(key + ChatColor.GRAY + " - " + ChatColor.GOLD + Float.parseFloat(DECIMAL_FORMAT.format(time)));
        } else if (format == DankTimerFormat.HH_MM_SS) {
            int millis = (int) Math.round(time);
            setValue(key + ChatColor.GRAY + " - " +
                    ChatColor.GOLD +
                    String.format("%02d:%02d:%02d",
                            TimeUnit.SECONDS.toHours(millis),
                            TimeUnit.SECONDS.toMinutes(millis) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(millis)),
                            TimeUnit.SECONDS.toSeconds(millis) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(millis))));
        } else if (format == DankTimerFormat.MM_SS) {
            int millis = (int) Math.round(time);
            setValue(key + ChatColor.GRAY + " - " +
                    ChatColor.GOLD +
                    String.format("%02d:%02d",
                            TimeUnit.SECONDS.toMinutes(millis) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(millis)),
                            TimeUnit.SECONDS.toSeconds(millis) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(millis))));
        }
    }

    public void hide() {
        setVisible(false);
        setFrozen(true);
        updateLabel();
    }

    public void show(){
        setVisible(true);
        updateLabel();
    }

}
