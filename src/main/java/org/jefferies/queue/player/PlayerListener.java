package org.jefferies.queue.player;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jefferies.queue.QueuePlugin;

@AllArgsConstructor
public class PlayerListener implements Listener {

    private QueuePlugin plugin;

    public void setup(){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){
        if(plugin.getQueueHandler().isQueueing(event.getPlayer())){
            plugin.getQueueHandler().getQueueByPlayer(event.getPlayer()).remove(event.getPlayer());
        }
    }

}
