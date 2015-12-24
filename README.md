# dankboard

dankboard is a Bukkit scoreboard API for plugin developers.  It provides a base way to create scoreboards, assign them to players, and create different 'labels'.  Using this library, you can achieve very advantage, and object oriented scoreboard setups, for multiple players, just one, etc.  The possibilities are endless.
There are a few available examples within this repo (see below), and are not necessary.  Aswell, the entire 'frontend' package is an *example* implementation: while you may use it, it is designed for a specific situation in which I was using it.  I suggest making your own implementation of the classes within the 'backend' package.

For examples on how to use this library, see the example package
(https://github.com/Shawckz/dankboard/tree/master/src/main/java/com/shawckz/dankboard/example)

The backend package is where all of the XScoreboard library is stores, which provides the base.

I suggest you look at XScoreboard.java and other classes in that package to get an understanding.

** PLEASE NOTE THAT ALL OF THE "DANK" IMPLEMENTATIONS ARE EXAMPLES.  FOR MOST USAGES, YOU WILL WANT TO CREATE YOUR OWN IMPLEMENTATIONS
OF 'XScoreboardLabel', 'XScoreboardTimer', 'XScoreboard', ETC! **

Most of the "dank" implementations are just examples, while you can use them; I suggest making your own implementations.

Example.java in the example package should help you get started - theres lots of comments in there.
ExamplePlayer.java is a simple player object wrapper wherein I am using it to store a Dankboard instance assuming that in that example
I am using a per-player scoreboard situation.

Enjoy
