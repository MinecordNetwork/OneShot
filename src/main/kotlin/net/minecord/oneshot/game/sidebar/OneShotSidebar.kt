package net.minecord.oneshot.game.sidebar

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.game.sidebar.GameSidebar

class OneShotSidebar(plugin: Gamesys, game: Game) : GameSidebar(plugin, game) {
    override fun getTitle(player: GamePlayer): String {
        return "<gray><bold>Arena</bold></gray> <white>➲ <yellow><bold>${game.arena.name}"
    }

    override fun getLines(player: GamePlayer): ArrayList<String> {
        val list = super.getLines(player)
        val playerList = game.players

        playerList.sortByDescending { it.kills }

        list.add("<gray><st>----------------")
        list.add("<aqua>Your kills: <white>${player.kills}")
        list.add("<gray><st>-----------------")

        var limit = 9
        playerList.filter { limit-- > 0 }.sortedByDescending { it.kills }.forEach {
            list.add("<white>${it.kills} <green>${it.player.name}")
        }

        list.add("<gray><st>-----------------")
        list.add("<aqua>IP: <white>mc.minecord.net")
        list.add("<gray><st>-----------------")

        return list
    }
}
