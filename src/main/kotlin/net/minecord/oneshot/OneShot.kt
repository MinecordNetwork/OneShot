package net.minecord.oneshot

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.player.GamePlayerListener
import net.minecord.gamesys.utils.colored
import net.minecord.oneshot.command.TestCommand
import net.minecord.oneshot.game.player.OneShotPlayerListener
import net.minecord.oneshot.system.OneShotSystem
import org.bukkit.Bukkit
import org.bukkit.plugin.java.annotation.command.Command
import org.bukkit.plugin.java.annotation.command.Commands
import org.bukkit.plugin.java.annotation.plugin.Description
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.Website
import org.bukkit.plugin.java.annotation.plugin.author.Author

@Plugin(name = "OneShot", version = "1.0")
@Description("Minecraft minigame")
@Commands(Command(name = "gametest", desc = "Test command"))
@Website("https://minecord.net")
@Author("Minecord Network")
class OneShot : Gamesys() {
    override fun onEnable() {
        run(OneShotSystem())

        Bukkit.getPluginManager().registerEvents(OneShotPlayerListener(this), this)

        getCommand("gametest")!!.setExecutor(TestCommand(this))

        logger.logInfo("Plugin successfully enabled!")
        logger.logInfo("This plugin was created by &eMinecord Network &a[https://minecord.net]")
    }

    override fun onDisable() {
        logger.logInfo("Plugin was successfully disabled!")
    }
}
