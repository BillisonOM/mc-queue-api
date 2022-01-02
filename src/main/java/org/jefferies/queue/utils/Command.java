package org.jefferies.queue.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jefferies.queue.QueuePlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command extends org.bukkit.command.Command {

    public final QueuePlugin plugin = QueuePlugin.getInstance().getInstance();

    private List<String> permissions = new ArrayList<>();

    public Command(String name, String... aliases) {
        super(name);
        setAliases(Arrays.asList(aliases));
        try {
            Field cmdMap = plugin.getServer().getClass().getDeclaredField("commandMap");
            cmdMap.setAccessible(true);
            ((org.bukkit.command.CommandMap) cmdMap.get(plugin.getServer())).register(plugin.getDescription().getName(), this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Command(String name) {
        super(name);
        try {
            Field cmdMap = plugin.getServer().getClass().getDeclaredField("commandMap");
            cmdMap.setAccessible(true);
            ((org.bukkit.command.CommandMap) cmdMap.get(plugin.getServer())).register(plugin.getDescription().getName(), this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final void addPermission(String permission) {
        permissions.add(permission);
    }

    public void onExecute(CommandSender sender, String[] args) {

    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!permissions.isEmpty()) {
                boolean canExecute = false;
                for (String permission : permissions) {
                    if (player.hasPermission(permission)) {
                        canExecute = true;
                    }
                }
                if (canExecute) {
                    onExecute(sender, args);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                    return true;
                }
            } else {
                onExecute(sender, args);
                return true;
            }
        } else {
            onExecute(sender, args);
        }
        return false;
    }
}
