package com.shawckz.dankboard.backend.timer;

import com.shawckz.dankboard.backend.XScoreboard;
import com.shawckz.dankboard.backend.label.XLabel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

@Getter
@Setter
public abstract class XScoreboardTimer extends XLabel {

    private boolean frozen = false;
    private boolean running = false;
    private BukkitTask bukkitTask = null;
    private final TimerPool timerPool;

    public XScoreboardTimer(XScoreboard scoreboard, String value, int score, long interval) {
        super(scoreboard, value, score);
        this.timerPool = new TimerPool(interval);
    }

    public XScoreboardTimer(XScoreboard scoreboard, String value, int score, TimerPool timerPool) {
        super(scoreboard, value, score);
        this.timerPool = timerPool;
    }

    public final void startTimer(XTimerTask task, Plugin plugin) {
        timerPool.registerTimer(task);
        if (!timerPool.isRunning()) {
            timerPool.startTimerPool(plugin);
        }
    }

}
