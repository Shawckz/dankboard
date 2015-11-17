package com.shawckz.dankboard.backend.animation;

import com.shawckz.dankboard.backend.XScoreboard;
import com.shawckz.dankboard.backend.label.XLabel;
import com.shawckz.dankboard.backend.timer.TimerPool;
import com.shawckz.dankboard.backend.timer.XTimerTask;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Jonah on 11/7/2015.
 */
@Getter
@Setter
public class XScoreboardAnimation extends XLabel {

    private boolean frozen = false;
    private boolean running = false;
    private BukkitTask bukkitTask = null;
    private final TimerPool timerPool;

    public XScoreboardAnimation(XScoreboard scoreboard, String value, int score, long interval) {
        super(scoreboard, value, score);
        this.timerPool = new TimerPool(interval);
    }

    public XScoreboardAnimation(XScoreboard scoreboard, String value, int score, TimerPool timerPool) {
        super(scoreboard, value, score);
        this.timerPool = timerPool;
    }

    public final void startTimer(XTimerTask task, Plugin instance) {
        timerPool.registerTimer(task);
        if (!timerPool.isRunning()) {
            timerPool.startTimerPool(instance);
        }
    }


}
