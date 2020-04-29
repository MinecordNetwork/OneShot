package net.minecord.oneshot.game

import net.minecord.gamesys.Gamesys
import net.minecord.gamesys.arena.Arena
import net.minecord.gamesys.game.Game
import net.minecord.gamesys.game.GameStatus
import net.minecord.gamesys.game.player.GamePlayer
import net.minecord.gamesys.utils.colored
import net.minecord.gamesys.utils.instantFirework
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

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
                sidebar.update()
                it.player.inventory.addItem(ItemStack(Material.ARROW))
                if (it.kills >= 20) {
                    onEndCountdownStart(killer)
                }
            }
        }
    }

    override fun onPlayerStartsToRespawn(player: GamePlayer, cause: EntityDamageEvent.DamageCause?, killer: GamePlayer?) {
        super.onPlayerStartsToRespawn(player, cause, killer)

        object : BukkitRunnable() {
            var counter = getRespawnCooldown()
            override fun run() {
                if (counter <= 0) {
                    cancel()
                    return
                }
                if (killer != null) {
                    player.player.sendTitle("&e&l$counter".colored(), "&f&lYou were killed by &c&l${killer.player.name}".colored(), 0, 25, 0)
                } else {
                    player.player.sendTitle("&e&l$counter".colored(), "&f&lRespawning".colored(), 0, 25, 0)
                }
                counter--
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20)
    }

    override fun onGameStart() {
        super.onGameStart()

        players.forEach {
            it.player.sendTitle("&e&lGame Started".colored(), "&f&lBe first who kills &e&l20 &f&lplayers".colored(), 0, 80, 20)
        }
    }

    override fun onEndCountdownStart(winner: GamePlayer?) {
        super.onEndCountdownStart(winner)

        var countdown = getEndCountdown()
        object : BukkitRunnable() {
            override fun run() {
                when {
                    countdown <= 0 -> {
                        cancel()
                        return
                    }
                    else -> {
                        players.forEach {
                            it.player.playSound(it.player.location, Sound.UI_BUTTON_CLICK, 3f, 1f)
                            if (winner != null) {
                                it.player.sendTitle("&c&l${countdown}".colored(), "&f&lPlayer &a&l${winner.player.name} &f&lwins the Game".colored(), 0, 60, 20)
                            } else {
                                it.player.sendTitle("&c&l${countdown}".colored(), "&f&lGame has ended".colored(), 0, 60, 20)
                            }
                        }
                    }
                }
                countdown--
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20)
    }
}
