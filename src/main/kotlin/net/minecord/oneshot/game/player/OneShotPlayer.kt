package net.minecord.oneshot.game.player

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.game.player.GamePlayer
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class OneShotPlayer(plugin: Gamesys, player: Player): GamePlayer(plugin, player) {
    override fun getDefaultGameMode(): GameMode {
        return GameMode.ADVENTURE
    }

    override fun getGameItems(): HashMap<Int, ItemStack> {
        val items = super.getGameItems()

        items[0] = ItemStack(Material.IRON_AXE)
        items[1] = ItemStack(Material.BOW)
        items[2] = ItemStack(Material.ARROW)

        return items
    }
}
