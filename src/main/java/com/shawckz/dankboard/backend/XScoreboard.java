package com.shawckz.dankboard.backend;

import com.shawckz.dankboard.backend.label.XLabel;
import com.shawckz.dankboard.backend.label.XLabelValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * XScoreboard is the abstract instance of all scoreboards, providing the base sets of labels, etc.
 * Here you can add and do other things with labels, scores, and things relative to the scoreboard.
 */
public abstract class XScoreboard {

    public static final String OBJECTIVE_TYPE = "dummy";
    public static final DisplaySlot DISPLAY_SLOT = DisplaySlot.SIDEBAR;

    protected final Scoreboard scoreboard;
    protected final Objective objective;
    protected final ConcurrentMap<Integer, XLabel> scores = new ConcurrentHashMap<>();

    /**
     * @param scoreboardTitle The title of the scoreboard
     * @param player The player to give the scoreboard to - You could of course change this class to make it so that you can have multiple players on one scoreboard,
     *               in fact the only reason that the player is a param here is because when I designed this it was supposed to take
     *               whether the player already has a scoreboard - respecting other plugins
     * @param overridePlayerScoreboard Whether or not to forcefully override any other scoreboard the player may have, if this is false
     *                                 we will use a player's scoreboard that is already existing if available
     */
    public XScoreboard(String scoreboardTitle, Player player, boolean overridePlayerScoreboard) {
        if (player.getScoreboard() != null && !overridePlayerScoreboard) {
            this.scoreboard = player.getScoreboard();
        } else {
            this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }
        this.objective = this.scoreboard.registerNewObjective(getFilteredTitle(scoreboardTitle), OBJECTIVE_TYPE);
        this.objective.setDisplaySlot(DISPLAY_SLOT);
    }

    /**
     * Add a label (line; score) to the scoreboard
     * @param label The label to add to the scoreboard
     */
    public final void addLabel(XLabel label) {
        scores.put(label.getScore(), label);
        label.updateLabel();
    }

    /**
     * Whether or not the scoreboard currently has a certain Label
     * Note that this will return true if the label is HIDDEN, but still there.
     * @see {@link XLabel#isVisible()}
     * @param label The label to check if the scoreboard has
     * @return True if the label is on the scoreboard (visible or not), and false otherwise
     */
    public final boolean hasLabel(XLabel label) {
        for (XLabel l : scores.values()) {
            if (l.equals(label)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a label from the scoreboard
     * If the label specified is not on the scoreboard nothing will happen.
     * @param label The label to remove from the scoreboard
     */
    public final void removeLabel(XLabel label) {
        label.getValue().removeValue();
        Iterator<Integer> it = scores.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            if (scores.get(key).getValue().equals(label.getValue())) {
                scores.remove(key);
            }
        }
    }

    /**
     * Get a label based on the current full value of a line
     * @see {@link XLabelValue#getFullValue()}
     * @param value The line(value/label/text) to search for
     * @return The XLabel, null if it cannot be found
     */
    public final XLabel getLabel(String value) {
        for (XLabel label : scores.values()) {
            if (label.getValue().getFullValue().equals(value)) {
                return label;
            }
        }
        return null;
    }

    /**
     * Get a label based on it's score
     * @param score The score of the label.
     *              Note that this library stores scores by KEY, meaning that it only allows for one label per
     *              concurrent score
     * @return The XLabel, null if it cannot be found
     */
    public final XLabel getLabel(int score) {
        if (scores.containsKey(score)) {
            return scores.get(score);
        }
        return null;
    }

    /**
     * Get the bukkit scoreboard
     * @return The bukkit scoreboard
     */
    public final Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     * Get the bukkit scoreboard objective
     * @return The bukkit scoreboard objective
     */
    public final Objective getObjective() {
        return objective;
    }

    private String getFilteredTitle(String title) {
        return (title.length() > 16 ? title.substring(0, 16) : title);
        //Filters the title since our titles only support up to 16 chars
    }

    public final ConcurrentMap<Integer, XLabel> getScores() {
        return scores;
    }

    /**
     * Send the scoreboard to a player
     * Make sure you don't forget to call this!
     * @param player The player to send the scoreboard to
     */
    public final void sendToPlayer(Player player) {
        player.setScoreboard(scoreboard);
    }

}
