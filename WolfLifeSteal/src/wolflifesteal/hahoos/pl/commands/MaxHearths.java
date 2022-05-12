package wolflifesteal.hahoos.pl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import wolflifesteal.hahoos.pl.Main;

public class MaxHearths implements CommandExecutor {
    Plugin plugin = Main.getPlugin(null);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(args[0] != null) {
				Player target = plugin.getServer().getPlayer(args[0]);
				target.setMaxHealth(20);
				target.setHealth(20);
				target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.MaxHearthsExecutedByOtherPlayer")).replace("%player%", ((Player) sender).getDisplayName()));
			}else {
				((Player) sender).getPlayer().setMaxHealth(20);
				((Player) sender).getPlayer().setHealth(20);
				((Player) sender).getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.MaxHearthsExecuted")));
			}
		}
		return true;
	}

}
