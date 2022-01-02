package org.jefferies.queue.queue;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jefferies.queue.QueuePlugin;
import org.jefferies.queue.player.QueuePlayer;
import org.jefferies.queue.rank.Rank;

@Getter
public class ServerQueue extends Queue {

    private String id;
    private String bungee;
    @Setter
    private PauseCriteria criteria;

    public interface PauseCriteria {
        boolean pauseEndPoint();
    }

    public ServerQueue(String id, String bungee) {
        this.id = id;
        this.bungee = bungee;
        QueuePlugin.getInstance().getQueueHandler().getQueues().add(this);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public void add(Player player) {
        Rank r = QueuePlugin.getInstance().getQueueHandler().getQueueRank(player);
        String msg = QueuePlugin.getInstance().getSettings().getString("QUEUE-MESSAGE");
        msg = msg.replaceAll("%server%", id);
        msg = msg.replaceAll("%rank%", r.getDisplay());
        getParticipants().put(player.getUniqueId(), new QueuePlayer(player));
        player.sendMessage(msg);
    }

    @Override
    public void remove(Player player) {
        String msg = QueuePlugin.getInstance().getSettings().getString("QUEUE-LEAVE-MESSAGE");
        msg = msg.replaceAll("%server%", id);
        getParticipants().remove(player.getUniqueId());
        player.sendMessage(msg);
    }

    @Override
    public void execute(Player player) {
        if (criteria != null && criteria.pauseEndPoint()) {
            String msg = QueuePlugin.getInstance().getSettings().getString("QUEUE-SERVER-JOIN-MESSAGE");
            msg = msg.replaceAll("%server%", id);
            getParticipants().remove(player.getUniqueId());
            player.sendMessage(msg);
            QueuePlugin.getInstance().getBungeeChannel().connect(player, bungee);
        }
    }

}
