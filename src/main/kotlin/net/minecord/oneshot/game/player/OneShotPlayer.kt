package net.minecord.oneshot.game.player

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.player.GamePlayer
import org.bukkit.entity.Player

class OneShotPlayer(plugin: Gamesys, player: Player): GamePlayer(plugin, player)
