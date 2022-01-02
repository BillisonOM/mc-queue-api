package org.jefferies.queue.handler;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.jefferies.queue.QueuePlugin;
import org.jefferies.queue.commands.QueuesCommand;
import org.jefferies.queue.commands.SetQueueSecondsCommand;
import org.jefferies.queue.commands.ToggleQueueCommand;
import org.jefferies.queue.queue.Queue;
import org.jefferies.queue.rank.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class QueueHandler {

    private QueuePlugin plugin;

    private List<Rank> ranks;
    private List<Queue> queues;

    public QueueHandler(QueuePlugin plugin) {
        this.plugin = plugin;
        loadRanks();
        loadQueues();
        new QueuesCommand();
        new ToggleQueueCommand();
        new SetQueueSecondsCommand();
    }

    private void loadQueues() {
        queues = new ArrayList<>();
    }

    private void loadRanks() {
        ranks = new ArrayList<>();
        for (String id : plugin.getRanks().getKeys(false)) {
            ranks.add(new Rank(id, plugin.getRanks().getString(id + ".display"), plugin.getRanks().getInt(id + ".priority"), plugin.getRanks().getString(id + ".permission")));
        }
    }

    public List<Rank> getRanksByPriority() {
        List<Rank> ranks = new ArrayList<>(this.ranks);
        ranks.sort(Comparator.comparing(Rank::getPriority));
        return ranks;
    }

    public Rank getQueueRank(Player player) {
        for (Rank r : getRanksByPriority()) {
            if (player.hasPermission(r.getPermission())) return r;
        }
        return getRanksByPriority().get(ranks.size() - 1);
    }

    public Queue getQueueByPlayer(Player player) {
        for (Queue queue : queues) {
            if (queue.getParticipants().containsKey(player.getUniqueId())) return queue;
        }
        return null;
    }

    public boolean isQueueing(Player player) {
        return getQueueByPlayer(player) != null;
    }

    public Queue getQueueById(String id) {
        for (Queue q : queues) {
            if (q.id().equalsIgnoreCase(id)) return q;
        }
        return null;
    }

}

