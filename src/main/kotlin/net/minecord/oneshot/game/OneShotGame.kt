package net.minecord.oneshot.game

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.arena.Arena
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.utils.instantFirework
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack

class OneShotGame(plugin: Gamesys, arena: Arena) : Game(plugin, arena) {
    override fun getGameMode(gamePlayer: GamePlayer): GameMode {
        return GameMode.ADVENTURE
    }

    override fun getGameItems(gamePlayer: GamePlayer): HashMap<Int, ItemStack> {
        val items = super.getGameItems(gamePlayer)

        items[0] = ItemStack(Material.IRON_AXE)
        items[1] = ItemStack(Material.BOW)
        items[2] = ItemStack(Material.ARROW)

        return items
    }

    override fun onPlayerDeath(player: GamePlayer, cause: EntityDamageEvent.DamageCause?, killer: GamePlayer?) {
        super.onPlayerDeath(player, cause, killer)

        instantFirework(FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BURST).build(), player.player.location)

        killer?.let {
            it.player.inventory.addItem(ItemStack(Material.ARROW))
            if (it.kills >= 20) {
                onGameEnd(killer)
            }
        }
    }
}
