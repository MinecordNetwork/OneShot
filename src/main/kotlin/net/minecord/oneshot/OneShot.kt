package net.minecord.oneshot

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.system.System
import net.minecord.oneshot.game.player.OneShotPlayerListener
import net.minecord.oneshot.system.OneShotSystem
import org.bukkit.Bukkit
import org.bukkit.plugin.java.annotation.command.Command
import org.bukkit.plugin.java.annotation.command.Commands
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.dependency.DependsOn
import org.bukkit.plugin.java.annotation.dependency.SoftDependency
import org.bukkit.plugin.java.annotation.dependency.SoftDependsOn
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.Description
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.Website
import org.bukkit.plugin.java.annotation.plugin.author.Author

@Plugin(name = "OneShot", version = "1.0")
@Description("Minecraft minigame")
@Commands(
    Command(name = "join", desc = "Join"),
    Command(name = "leave", desc = "Leave"),
    Command(name = "start", desc = "Start")
)
@Website("https://minecord.net")
@Author("Minecord Network")
@ApiVersion(ApiVersion.Target.DEFAULT)
@DependsOn(
    Dependency("Multiverse-Core"),
    Dependency("HolographicDisplays"),
    Dependency("XoreBoardUtil"),
    Dependency("FastAsyncWorldEdit"),
    Dependency("WorldEdit")
)
@SoftDependsOn(
    SoftDependency("ProtocolSupport")
)
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
