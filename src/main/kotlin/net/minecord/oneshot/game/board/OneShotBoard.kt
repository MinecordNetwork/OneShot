package net.minecord.oneshot.game.board

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.board.GameBoard

class OneShotBoard(plugin: Gamesys, game: Game) : GameBoard(plugin, game) {
    override fun getTitle(): String {
        return "  &e&lMINECORD   "
    }

    override fun getLines(): HashMap<String, Int> {
        val list = super.getLines()

        game.players.forEach {
            list["&f${it.kills} &b${it.player.name}"] = it.kills
        }

        return list
    }
}
