package org.vanillacraft.vanillacraftplugin.listener;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnBoostListener extends BukkitRunnable implements Listener {
    private final Plugin plugin;
    private final int multiplyValue;
    private final int spawnRadius;
    private final boolean boostEnabled;
    private  final World world;
    private final List<Player>  flying = new ArrayList();
    private  final List<Player> boosted = new ArrayList();

    public SpawnBoostListener(Plugin plugin) {
        this.plugin = plugin;
        this.multiplyValue = 5;
        this.spawnRadius = 20;
        this.world = Bukkit.getWorld("world");
        this.boostEnabled = true;
        this.runTaskTimer(this.plugin, 0L, 3L);
    }

    public void run() {
        this.world.getPlayers().forEach((player) -> {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                player.setAllowFlight(this.isInSpawnRadius(player));
                if (this.flying.contains(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                    player.setAllowFlight(false);
                    player.setGliding(false);
                    this.boosted.remove(player);
                    Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                        this.flying.remove(player);
                    }, 5L);
                }
            }
        });
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if (this.isInSpawnRadius(event.getPlayer())) {
                event.setCancelled(true);
                event.getPlayer().setGliding(true);
                if (this.boostEnabled) {
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder(" Drücke ")).append(new KeybindComponent("key.swapOffhand")).append(" um dich zu boosten").create());
                    this.flying.add(event.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && (event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.FLY_INTO_WALL)  && this.flying.contains(event.getEntity())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwapItem(PlayerSwapHandItemsEvent event) {
        if (this.boostEnabled && this.flying.contains(event.getPlayer()) && !this.boosted.contains(event.getPlayer())) {
           event.setCancelled(true);
           this.boosted.add(event.getPlayer());
           event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(this.multiplyValue));
        }
    }

    @EventHandler
    public  void onToggleGlide(EntityToggleGlideEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && this.flying.contains(event.getEntity())) {
            event.setCancelled(true);
        }
    }

    private  boolean isInSpawnRadius(Player player) {
        if (!player.getWorld().equals(this.world)) {
            return false;
        } else {
            return this.world.getSpawnLocation().distance(player.getLocation()) <= (double)this.spawnRadius;
        }
    }
}
