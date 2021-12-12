package net.minecord.oneshot

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.system.System
import net.minecord.oneshot.game.player.OneShotPlayerListener
import net.minecord.oneshot.system.OneShotSystem
import org.bukkit.Bukkit

class OneShot : Gamesys() {
    override val system: System by lazy { OneShotSystem(this) }

    override fun onEnable() {
        super.onEnable()

        Bukkit.getPluginManager().registerEvents(OneShotPlayerListener(this), this)

        logger.logInfo("Plugin successfully enabled!")
        logger.logInfo("This plugin was created by &eMinecord Network &a[https://minecord.net]")
    }

    override fun onDisable() {
        super.onDisable()

        logger.logInfo("Plugin was successfully disabled!")
    }
}
