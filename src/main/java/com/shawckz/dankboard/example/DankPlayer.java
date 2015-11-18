package com.shawckz.dankboard.example;

import com.shawckz.dankboard.frontend.Dankboard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DankPlayer {

    private final Player player;
    private final Dankboard scoreboard;

    public DankPlayer(Player player, Plugin instance) {
        this.player = player;
        this.scoreboard = new Dankboard(player, instance, "Scoreboard Title");
    }

    public Player getPlayer() {
        return player;
    }

    public Dankboard getScoreboard() {
        return scoreboard;
    }
}
