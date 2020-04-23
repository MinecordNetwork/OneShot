package net.minecord.oneshot.game.player

import net.minecord.gamesys.game.GameStatus
import net.minecord.oneshot.OneShot
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerDropItemEvent


class OneShotPlayerListener(private val plugin: OneShot): Listener {
    @EventHandler(priority = EventPriority.LOW)
    fun onKill(e: EntityDamageByEntityEvent) {
        if (e.entity !is Player) return

        val victim = plugin.gamePlayerManager.get(e.entity as Player)
        if (victim.game == null) return

        if (victim.game!!.status == GameStatus.RUNNING) {
            val damagedBy = e.damager

            if (damagedBy is Firework) {
                e.isCancelled = true
                return
            }

            if (damagedBy is Projectile && damagedBy.shooter is Player) {
                val shooter = damagedBy.shooter as Player
                if (victim.player.uniqueId == shooter.uniqueId) {
                    e.isCancelled = true
                    return
                }
                victim.game!!.onPlayerDeath(victim, e.cause, plugin.gamePlayerManager.get(shooter))
            }
        }
    }

    @EventHandler
    fun onShot(e: ProjectileLaunchEvent) {
        val entity = e.entity

        if (entity is Arrow) {
            entity.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED
        }
    }
}
