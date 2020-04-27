package net.minecord.oneshot.system

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.arena.Arena
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.board.GameBoard
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.system.BaseSystem
import net.minecord.oneshot.game.OneShotGame
import net.minecord.oneshot.game.board.OneShotBoard
import net.minecord.oneshot.game.player.OneShotPlayer
import org.bukkit.entity.Player

class OneShotSystem(plugin: Gamesys) : BaseSystem(plugin) {
    override fun createGame(plugin: Gamesys, arena: Arena): Game {
        return OneShotGame(plugin, arena)
    }

    override fun createGamePlayer(plugin: Gamesys, player: Player): GamePlayer {
        return OneShotPlayer(plugin, player)
    }

    override fun createGameBoard(plugin: Gamesys, game: Game): GameBoard {
        return OneShotBoard(plugin, game)
    }

    override fun getChatPrefix(): String {
        return "&b&lOneShot &f&l●&7"
    }

    override fun dropItemsAfterDeath(): Boolean {
        return false
    }

    override fun isHungerBarDisabled(): Boolean {
        return true
    }

    override fun isItemThrowingAllowed(): Boolean {
        return false
    }
}
