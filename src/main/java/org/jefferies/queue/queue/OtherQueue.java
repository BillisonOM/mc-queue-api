package org.jefferies.queue.queue;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.jefferies.queue.player.QueuePlayer;

@AllArgsConstructor
public class OtherQueue extends Queue {

    private QueueEndPoint endPoint;

    @Override
    public String id() {
        return "not-needed";
    }

    @Override
    public void add(Player player) {
        getParticipants().put(player.getUniqueId(), new QueuePlayer(player));
    }

    @Override
    public void remove(Player player) {
        getParticipants().remove(player.getUniqueId());
    }

    @Override
    public void execute(Player player) {
        endPoint.execute(player);
    }

    public interface QueueEndPoint {
        void execute(Player p);
    }
}
