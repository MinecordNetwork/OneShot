package net.minecord.oneshot.command

import net.minecord.oneshot.OneShot
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestCommand(private val plugin: OneShot) : CommandExecutor {
    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        if (strings.isNotEmpty()) {
            val game = plugin.gameManager.getSuitableGame()
            if (game != null) {
                game.onPlayerJoined(plugin.gamePlayerManager.get(commandSender as Player))
            } else {
                commandSender.sendMessage("no game available")
            }
            /*when {
                strings[0] == "create" -> {
                    plugin.gameManager.createTestGame(plugin.arenaManager.arenas.random())
                }
                strings[0] == "join" -> {
                    plugin.gameManager.testGame.onPlayerJoined(plugin.gamePlayerManager.get(commandSender as Player))
                }
                strings[0] == "leave" -> {
                    plugin.gameManager.testGame.onPlayerLeft(plugin.gamePlayerManager.get(commandSender as Player))
                }
            }*/
        }
        return true
    }
}