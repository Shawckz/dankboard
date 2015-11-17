package com.shawckz.dankboard.frontend;

import com.shawckz.dankboard.backend.XScoreboard;
import com.shawckz.dankboard.backend.timer.TimerPool;
import com.shawckz.dankboard.frontend.label.DankLabel;
import com.shawckz.dankboard.frontend.timer.DankTimer;
import com.shawckz.dankboard.frontend.timer.DankTimerFormat;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Dankboard extends XScoreboard {

    private final Player player;
    private static final TimerPool timerPool = new TimerPool(2L);
    private int scoreIndex = 1;

    private Map<String, DankTimer> timers = new HashMap<>();
    private Map<String, DankLabel> labels = new HashMap<>();

    public Dankboard(Player player, Plugin instance, String title) {
        super(ChatColor.translateAlternateColorCodes('&', title), player);
        this.player = player;

        if (!timerPool.isRunning()) {
            timerPool.startTimerPool(instance, true);
        }
    }

    public void stopTimerPool() {
        timerPool.stopTimerPool();
    }

    public void registerTimer(FLabel key, DankTimer timer) {
        timers.put(key.toString(), timer);
    }

    public DankTimer getTimer(FLabel key) {
        return getTimer(key, DankTimerFormat.TENTH_OF_SECOND);
    }

    public DankTimer getTimer(FLabel key, boolean visible) {
        DankTimer timer = getTimer(key);
        timer.setVisible(visible);
        return timer;
    }

    public DankTimer getTimer(FLabel key, DankTimerFormat format) {
        if (!timers.containsKey(key.toString())) {
            registerTimer(key, new DankTimer(this, getKey(key), scoreIndex++, timerPool, format));
        }
        return timers.get(key.toString());
    }

    public DankLabel getHCFLabel(FLabel key) {
        if (!labels.containsKey(key.toString())) {
            labels.put(key.toString(), new DankLabel(this, getKey(key), scoreIndex++));
        }
        return labels.get(key.toString());
    }

    public String getKey(FLabel key) {
        //return Factions.getInstance().getFactionsConfig().getScoreboardKey(key); This is where you can make the DankLabels configurable
        return key.getKey();
    }

}
