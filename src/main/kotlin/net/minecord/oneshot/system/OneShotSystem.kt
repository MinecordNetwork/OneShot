package net.minecord.oneshot.system

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.arena.Arena
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.game.sidebar.GameSidebar
import net.minecord.gamesys.system.DefaultSystem
import net.minecord.oneshot.game.OneShotGame
import net.minecord.oneshot.game.sidebar.OneShotSidebar
import net.minecord.oneshot.game.player.OneShotPlayer
import org.bukkit.entity.Player

class OneShotSystem(plugin: Gamesys) : DefaultSystem(plugin) {
    override fun createGame(plugin: Gamesys, arena: Arena): Game {
        return OneShotGame(plugin, arena)
    }

    override fun createGamePlayer(plugin: Gamesys, player: Player): GamePlayer {
        return OneShotPlayer(plugin, player)
    }

    override fun createGameSidebar(plugin: Gamesys, game: Game): GameSidebar {
        return OneShotSidebar(plugin, game)
    }

    override fun getChatPrefix(): String {
        return "&b&lOneShot &f&l●&7"
    }
}
