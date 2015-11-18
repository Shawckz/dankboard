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
    private static final TimerPool timerPool = new TimerPool(2L);//The timerpool is static, all players run on the same timerpool
    private int scoreIndex = 1;

    private Map<String, DankTimer> timers = new HashMap<>();
    private Map<String, DankLabel> labels = new HashMap<>();

    /**
     * Instantiate a player's Dankboard
     * This is designed to be a per-player scoreboard, but you could easily modify this class or make a
     * different implementation of XScoreboard to make it support multiple players.
     * @param player The player to assign this scoreboard to
     * @param instance An instance of your plugin
     * @param title The title of the scoreboard (up to 16 chars)
     */
    public Dankboard(Player player, Plugin instance, String title) {
        super(ChatColor.translateAlternateColorCodes('&', title), player, true);//Override the player's scoreboard if found
        this.player = player;

        if (!timerPool.isRunning()) {
            timerPool.startTimerPool(instance, true);
        }
    }

    /**
     * The timers running on this scoreboard all update at the same time within a "TimerPool"
     * Again you could easily change this or make them update individually by making an entirely different implementation
     */
    public void stopTimerPool() {
        timerPool.stopTimerPool();
    }

    /**
     * Register a timer into the TimerPool for this Dankboard
     * @param key DLabel
     * @param timer DankTimer
     */
    public void registerTimer(DLabel key, DankTimer timer) {
        timers.put(key.toString(), timer);
    }

    /**
     * Get a DankTimer from a DLabel
     * @param key DLabel
     * @return DankTimer
     */
    public DankTimer getDankTimer(DLabel key) {
        return getDankTimer(key, DankTimerFormat.TENTH_OF_SECOND);
    }


    public DankTimer getDankTimer(DLabel key, boolean visible) {
        DankTimer timer = getDankTimer(key);
        timer.setVisible(visible);
        return timer;
    }

    public DankTimer getDankTimer(DLabel key, DankTimerFormat format) {
        if (!timers.containsKey(key.toString())) {
            registerTimer(key, new DankTimer(this, getKey(key), scoreIndex++, timerPool, format));
        }
        return timers.get(key.toString());
    }

    public DankLabel getDankLabel(DLabel key) {
        if (!labels.containsKey(key.toString())) {
            labels.put(key.toString(), new DankLabel(this, getKey(key), scoreIndex++));
        }
        return labels.get(key.toString());
    }

    public String getKey(DLabel key) {
        //return Factions.getInstance().getFactionsConfig().getScoreboardKey(key); This is where you can make the DankLabels configurable
        return key.getKey();
    }

}
