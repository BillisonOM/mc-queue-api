package org.jefferies.queue;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jefferies.queue.bungee.BungeeChannel;
import org.jefferies.queue.handler.QueueHandler;
import org.jefferies.queue.player.PlayerListener;
import org.jefferies.queue.utils.Config;

@Getter
public class QueuePlugin extends JavaPlugin {

    @Getter private static QueuePlugin instance;

    private BungeeChannel bungeeChannel;

    private Config settings;
    private Config ranks;

    private QueueHandler queueHandler;

    @Override
    public void onEnable() {
        instance = this;
        settings = new Config("settings.yml", this);
        ranks = new Config("ranks.yml", this);
        queueHandler = new QueueHandler(this);
        new PlayerListener(this).setup();
        bungeeChannel = BungeeChannel.of(this);
    }
}
