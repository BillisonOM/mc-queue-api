package org.jefferies.queue.queue;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jefferies.queue.QueuePlugin;
import org.jefferies.queue.player.QueuePlayer;
import org.jefferies.queue.rank.Rank;

import java.util.*;

public abstract class Queue {
    @Getter @Setter private boolean enabled = true;
    @Getter private double updateSeconds = 1;
    @Getter private Map<UUID, QueuePlayer> participants = new HashMap<>();
    private BukkitTask task;
    public Queue() {
        run();
    }
    public abstract String id();
    public abstract void add(Player player);
    public abstract void remove(Player player);
    public abstract void execute(Player player);
    public final boolean contains(UUID uuid) {
        return participants.containsKey(uuid);
    }
    public void setUpdateSeconds(double d){
        updateSeconds = d;
        run();
    }
    private void run() {
        if (task != null) {
            task.cancel();
        }
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (enabled) {
                    nextParticipant();
                }
            }
        }.runTaskTimer(QueuePlugin.getInstance(), 0, getUpdateTicks());
    }
    private void nextParticipant() {
        List<QueuePlayer> players = new ArrayList<>();
        for (UUID uuid : getParticipants().keySet()) {
            QueuePlayer player = getParticipants().get(uuid);
            players.add(player);
        }
        players.sort(Comparator.comparing(QueuePlayer::getQueuePriority));
        if (players.isEmpty()) return;
        Rank chosen = players.get(0).getRank();
        QueuePlayer next = null;
        for (QueuePlayer player : players) {
            if (!player.getRank().equals(chosen)) continue;
            if (next == null) {
                next = player;
            } else {
                if (next.getJoinedAt() > player.getJoinedAt()) {
                    next = player;
                }
            }
        }
        if (next != null) {
            if (Bukkit.getPlayer(next.getUuid()) != null) {
                execute(Bukkit.getPlayer(next.getUuid()));
            } else {
                participants.remove(next.getUuid());
            }
        }
    }
    private long getUpdateTicks() {
        long time = 0;
        for (double d = 0; d < updateSeconds; d += 0.1) {
            time += 2;
        }
        return time;
    }
    public final int getPosition(UUID uuid) {
        if (participants.containsKey(uuid)) {
            int pos = 1;
            List<QueuePlayer> players = new ArrayList<>();
            for (UUID id : getParticipants().keySet()) {
                QueuePlayer player = getParticipants().get(id);
                players.add(player);
            }
            players.sort(Comparator.comparing(QueuePlayer::getQueuePriority));
            if (players.isEmpty()) return 1;
            Rank chosen = players.get(0).getRank();
            List<QueuePlayer> ordered = new ArrayList<>();
            for (Rank rank : QueuePlugin.getInstance().getQueueHandler().getRanksByPriority()) {
                if (rank.getPriority() <= chosen.getPriority()) {
                    List<QueuePlayer> playersWithRank = new ArrayList<>();
                    for (QueuePlayer player : players) {
                        if (!player.getRank().equals(chosen)) continue;
                        playersWithRank.add(player);
                    }
                    for (QueuePlayer player : playersWithRank) {
                        if (Bukkit.getPlayer(player.getUuid()) == null) {
                            playersWithRank.remove(player);
                            participants.remove(player.getUuid());
                        }
                    }
                    playersWithRank.sort(Comparator.comparing(QueuePlayer::getJoinedAt));
                    ordered.addAll(playersWithRank);
                }
            }
            for (QueuePlayer player : ordered) {
                if (player.getUuid().equals(uuid)) {
                    return pos;
                }
                pos++;
            }
            return pos;
        } else {
            return 1;
        }
    }
}
