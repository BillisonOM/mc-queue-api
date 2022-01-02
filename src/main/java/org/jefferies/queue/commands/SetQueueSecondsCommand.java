package org.jefferies.queue.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jefferies.queue.queue.Queue;
import org.jefferies.queue.utils.Command;

public class SetQueueSecondsCommand extends Command {

    public SetQueueSecondsCommand(){
        super("setqueueseconds");
        addPermission("queues.setseconds");
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if(args.length != 2){
            sender.sendMessage(ChatColor.RED + "Usage: /setqueueseconds [server-name] [seconds]");
        } else {
            if(plugin.getQueueHandler().getQueueById(args[0]) != null){
                Queue queue = plugin.getQueueHandler().getQueueById(args[0]);
                double s;
                try {
                    s = Double.parseDouble(args[1]);
                    queue.setUpdateSeconds(s);
                    sender.sendMessage(ChatColor.GREEN + "You have updated " + queue.id() + " queue's update delay to: " + s + 's');
                } catch (Exception ex){
                    sender.sendMessage(ChatColor.RED + "Invalid number, try again.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid queue, try /queues to fetch the list.");
            }
        }
    }
}
