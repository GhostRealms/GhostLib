package net.ghostrealms.lib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Cliff on 8/23/2015.
 */
public class GhostMessage
{
    public static final String TITLE = ChatColor.GRAY + "[Realms] " + ChatColor.WHITE;

    public static void messagePlayer(Player player, String msg, boolean title)
    {
        if(title)
            player.sendMessage(TITLE + formatMessage(msg));
        else
            player.sendMessage(formatMessage(msg));
    }

    public static void broadcast(String msg, boolean title)
    {
        if(title)
            Bukkit.getServer().broadcastMessage(TITLE + formatMessage(msg));
        else
            Bukkit.getServer().broadcastMessage(formatMessage(msg));
    }

    /*
        Replaces color markers with bukkit chat color characters
     */
    public static String formatMessage(String msg)
    {
        if(msg.contains("&0"))
            msg = msg.replaceAll("&0", ChatColor.BLACK.toString());
        if(msg.contains("&1"))
            msg = msg.replaceAll("&1", ChatColor.DARK_BLUE.toString());
        if(msg.contains("&2"))
            msg = msg.replaceAll("&2", ChatColor.DARK_GREEN.toString());
        if(msg.contains("&3"))
            msg = msg.replaceAll("&3", ChatColor.DARK_AQUA.toString());
        if(msg.contains("&4"))
            msg = msg.replaceAll("&4", ChatColor.DARK_RED.toString());
        if(msg.contains("&5"))
            msg = msg.replaceAll("&5", ChatColor.DARK_PURPLE.toString());
        if(msg.contains("&6"))
            msg = msg.replaceAll("&6", ChatColor.GOLD.toString());
        if(msg.contains("&7"))
            msg = msg.replaceAll("&7", ChatColor.GRAY.toString());
        if(msg.contains("&8"))
            msg = msg.replaceAll("&8", ChatColor.DARK_GRAY.toString());
        if(msg.contains("&9"))
            msg = msg.replaceAll("&9", ChatColor.BLUE.toString());
        if(msg.contains("&a"))
            msg = msg.replaceAll("&a", ChatColor.GREEN.toString());
        if(msg.contains("&b"))
            msg = msg.replaceAll("&b", ChatColor.AQUA.toString());
        if(msg.contains("&c"))
            msg = msg.replaceAll("&c", ChatColor.RED.toString());
        if(msg.contains("&d"))
            msg = msg.replaceAll("&d", ChatColor.LIGHT_PURPLE.toString());
        if(msg.contains("&e"))
            msg = msg.replaceAll("&e", ChatColor.YELLOW.toString());
        if(msg.contains("&f"))
            msg = msg.replaceAll("&f", ChatColor.WHITE.toString());

        return msg;
    }

}
