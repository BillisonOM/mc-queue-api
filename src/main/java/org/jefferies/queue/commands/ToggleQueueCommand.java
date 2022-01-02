package org.jefferies.queue.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jefferies.queue.queue.Queue;
import org.jefferies.queue.utils.Command;

public class ToggleQueueCommand extends Command {

    public ToggleQueueCommand(){
        super("togglequeue");
        addPermission("queues.togglequeue");
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if(args.length != 1){
            sender.sendMessage(ChatColor.RED + "Usage: /togglequeue [server-name]");
        } else {
            if(plugin.getQueueHandler().getQueueById(args[0]) != null){
                Queue queue = plugin.getQueueHandler().getQueueById(args[0]);
                queue.setEnabled(!queue.isEnabled());
                sender.sendMessage(queue.isEnabled() ?
                        "&aYou have enabled the " + queue.id() + " queue."
                        :
                        "&cYou have disabled the " + queue.id() + " queue."
                );
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid queue, try /queues to fetch the list.");
            }
        }
    }
}
