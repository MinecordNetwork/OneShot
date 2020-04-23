package net.minecord.oneshot.system

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.arena.Arena
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.system.BaseSystem
import net.minecord.oneshot.game.OneShotGame
import net.minecord.oneshot.game.player.OneShotPlayer
import org.bukkit.entity.Player

class OneShotSystem : BaseSystem() {
    override fun createGame(plugin: Gamesys, arena: Arena): Game {
        return OneShotGame(plugin, arena)
    }

    override fun createGamePlayer(plugin: Gamesys, player: Player): GamePlayer {
        return OneShotPlayer(plugin, player)
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
