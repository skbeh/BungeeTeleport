package org.skbeh.bungeeteleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandBt implements CommandExecutor {
    private final BungeeTeleportPlugin plugin;
    private final String permissionMessage;

    public CommandBt(BungeeTeleportPlugin plugin) {
        this.plugin = plugin;
        permissionMessage = plugin.getCommand("bt").getPermissionMessage();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("bungeeteleport.teleport")) {
            sender.sendMessage(permissionMessage);
            return true;
        }

        switch (args.length) {
            case 1 -> {
                if (sender instanceof Player) {
                    return sendBungeeData((Player) sender, args[0]);
                } else {
                    return false;
                }
            }
            case 2 -> {
                Player player = Bukkit.getPlayerExact(args[0]);
                if (player == null) {
                    return false;
                }
                return sendBungeeData(player, args[1]);
            }
            default -> {
                return false;
            }
        }
    }

    private boolean sendBungeeData(@NotNull Player player, @NotNull String arg) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArray);
        try {
            out.writeUTF("Connect");
            out.writeUTF(arg);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        player.sendPluginMessage(plugin, "BungeeCord", byteArray.toByteArray());
        return true;
    }
}
