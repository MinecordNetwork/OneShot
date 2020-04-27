package net.minecord.oneshot.game.board

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.board.GameBoard
import net.minecord.gamesys.game.player.GamePlayer

class OneShotBoard(plugin: Gamesys, game: Game) : GameBoard(plugin, game) {
    override fun getTitle(player: GamePlayer): String {
        return "  &e&l${game.arena.name}   "
    }

    override fun getLines(player: GamePlayer): HashMap<String, Int> {
        val list = super.getLines(player)
        val playerList = game.players

        playerList.sortBy { it.kills }

        list["&7----------------"] = 23
        list["&bYour kills: &f${player.kills}"] = 22
        list["&7&7----------------"] = 21

        var limit = 9
        playerList.filter { limit-- > 0 }.forEach {
            list["&f${it.kills} &a${it.player.name}"] = it.kills
        }

        list["&7&7&7----------------"] = -1
        list["&bIP: &fmc.minecord.net"] = -2
        list["&7&7&7&7----------------"] = -3

        return list
    }
}
