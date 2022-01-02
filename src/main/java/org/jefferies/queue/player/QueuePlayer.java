package org.jefferies.queue.player;

import lombok.Data;
import org.bukkit.entity.Player;
import org.jefferies.queue.QueuePlugin;
import org.jefferies.queue.rank.Rank;

import java.util.UUID;

@Data
public class QueuePlayer {

    private UUID uuid;
    private String name;
    private Rank rank;
    private long joinedAt;

    public QueuePlayer(Player player) {
        uuid = player.getUniqueId();
        name = player.getName();
        rank = QueuePlugin.getInstance().getQueueHandler().getQueueRank(player);
        joinedAt = System.currentTimeMillis();
    }

    public int getQueuePriority() {
        return rank.getPriority();
    }

}
