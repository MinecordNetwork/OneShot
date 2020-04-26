package net.minecord.oneshot.game.board

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.board.GameBoard

class OneShotBoard(plugin: Gamesys, game: Game) : GameBoard(plugin, game) {
    override fun getTitle(): String {
        return "  &e&lOneShot   "
    }

    override fun getLines(): HashMap<String, Int> {
        val list = super.getLines()
        val playerList = game.players

        playerList.sortBy { it.kills }

        var limit = 15
        playerList.filter { limit-- > 0 }.forEach {
            list["&f${it.kills} &a${it.player.name}"] = it.kills
        }

        return list
    }
}
