package net.ghostrealms.lib;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Cliff on 8/23/2015.
 */
public class GhostMessage
{
    public static final String TITLE = ChatColor.GRAY + "[Realms] " + ChatColor.WHITE;
    /*
        Note:
     */
    public static void messagePlayer(Player player, String msg, boolean title)
    {

        if(title)
            player.sendMessage(TITLE + formatMessage(msg));
        else
            player.sendMessage(formatMessage(msg));
    }

    /*
        Replaces color markers with bukkit chat color characters
     */
    public static String formatMessage(String msg)
    {
        if(msg.contains("&0"))
            msg.replace("&0", ChatColor.BLACK.toString());
        if(msg.contains("&1"))
            msg.replace("&1", ChatColor.DARK_BLUE.toString());
        if(msg.contains("&2"))
            msg.replace("&2", ChatColor.DARK_GREEN.toString());
        if(msg.contains("&3"))
            msg.replace("&3", ChatColor.DARK_AQUA.toString());
        if(msg.contains("&4"))
            msg.replace("&4", ChatColor.DARK_RED.toString());
        if(msg.contains("&5"))
            msg.replace("&5", ChatColor.DARK_PURPLE.toString());
        if(msg.contains("&6"))
            msg.replace("&6", ChatColor.GOLD.toString());
        if(msg.contains("&7"))
            msg.replace("&7", ChatColor.GRAY.toString());
        if(msg.contains("&8"))
            msg.replace("&8", ChatColor.DARK_GRAY.toString());
        if(msg.contains("&9"))
            msg.replace("&9", ChatColor.BLUE.toString());
        if(msg.contains("&a"))
            msg.replace("&a", ChatColor.GREEN.toString());
        if(msg.contains("&b"))
            msg.replace("&b", ChatColor.AQUA.toString());
        if(msg.contains("&c"))
            msg.replace("&c", ChatColor.RED.toString());
        if(msg.contains("&d"))
            msg.replace("&d", ChatColor.LIGHT_PURPLE.toString());
        if(msg.contains("&e"))
            msg.replace("&e", ChatColor.YELLOW.toString());
        if(msg.contains("&f"))
            msg.replace("&f", ChatColor.WHITE.toString());

        return msg;
    }

}
