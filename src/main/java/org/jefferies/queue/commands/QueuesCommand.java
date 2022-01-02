package org.jefferies.queue.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jefferies.queue.queue.Queue;
import org.jefferies.queue.utils.Command;

public class QueuesCommand extends Command {

    public QueuesCommand(){
        super("queues");
        addPermission("queues.list");
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if(plugin.getQueueHandler().getQueues().isEmpty()){
            sender.sendMessage(ChatColor.RED + "There are no queues available at this time.");
        } else {
            sender.sendMessage(ChatColor.GREEN + "Queues:");
            for(Queue queue : plugin.getQueueHandler().getQueues()){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7(" + (queue.isEnabled() ? "&aActive" : "&cIn-active") + "&7) " + queue.id() + " SERVER QUEUE PARTICIPANTS {" + queue.getParticipants().size() + "}"));
            }
        }
    }
}
