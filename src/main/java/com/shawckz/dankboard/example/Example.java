package com.shawckz.dankboard.example;

import com.shawckz.dankboard.frontend.DLabel;
import com.shawckz.dankboard.frontend.Dankboard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Example {



    void test(Plugin plugin) {

        DankPlayer dankPlayer = new DankPlayer(Bukkit.getPlayer("Shawckz"), plugin);
        //DankPlayer is an example player object wrapper, where you can store per-player Dankboard (scoreboard)s

        Dankboard board = dankPlayer.getScoreboard();
        board.getDankTimer(DLabel.SPAWN_TAG).setTime(30);
        board.getDankTimer(DLabel.SPAWN_TAG).unpauseTimer();//Makes sure the timer is not frozen, usually not necessary though
        board.getDankTimer(DLabel.SPAWN_TAG).show();//Makes sure the timer is not hidden, usually not necessary
        //A DLabel represents a line on the scoreboard, or at least the prefix of it.  The suffix is filled using a label class or similair.

        //You can do lots of other things within Timers, just see the DankTimer and XScoreboardTimer classes.  TimerPool & XTimerTask too.

        //To see where all the real magic in terms of supporting long lines, updates, etc. in the backend; see the XLabelValue class.

        board.getDankLabel(DLabel.ARMOR_CLASS).show().setEndValue("Something");//Example usage of a DankLabel (XLabel example implementation)

        //Another way to add a line is via XLabel, DankLabel is an implementation of XLabel by the way
        //You can do this via board#addLabel

        //Classes to look at that have documented code are XLabelValue, XLabel, XScoreboard, DankLabel, DankTimer, XScoreboardTimer, TimerPool
        //See DankTimer for an example of how to have a good XTimerTask implementation

        board.sendToPlayer(dankPlayer.getPlayer());//Really important, don't forget to call this or else the scoreboard will not be sent to your player
        //Note: Using this method you could easily give the same scoreboard to multiple players.

        //I won't cover animations in here, but they are structured similar to Timers, all classes relative to them
        // are in the com.shawckz.dankboard.backend.animation package

        //Remember that the DankLabel, DankTimer, etc.  Are EXAMPLE implementations of the XScoreboard library.  While you could
        //obviously easily use them, i suggest making your own as it makes it more flexible for yourself in the future.


    }

}
