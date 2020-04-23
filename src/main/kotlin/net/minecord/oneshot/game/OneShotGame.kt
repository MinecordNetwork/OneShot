package net.minecord.oneshot.game

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.arena.Arena
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.GameStatus
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.utils.instantFirework
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack

class OneShotGame(plugin: Gamesys, arena: Arena) : Game(plugin, arena) {
    override fun onPlayerLeft(player: GamePlayer) {
        super.onPlayerLeft(player)

        if (players.size <= 1 && status == GameStatus.RUNNING) {
            onEndCountdownStart()
        }
    }

    override fun onPlayerDeath(player: GamePlayer, cause: EntityDamageEvent.DamageCause?, killer: GamePlayer?) {
        super.onPlayerDeath(player, cause, killer)

        instantFirework(FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BURST).build(), player.player.location)

        if (status == GameStatus.RUNNING) {
            killer?.let {
                it.player.inventory.addItem(ItemStack(Material.ARROW))
                if (it.kills >= 20) {
                    onEndCountdownStart(killer)
                }
            }
        }
    }
}
