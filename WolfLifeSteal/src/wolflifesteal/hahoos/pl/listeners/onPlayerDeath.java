package wolflifesteal.hahoos.pl.listeners;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import wolflifesteal.hahoos.pl.Main;

public class onPlayerDeath implements Listener {
	Plugin plugin = Main.getPlugin(null);
	@EventHandler
	  public void PlayerDeath(PlayerDeathEvent death) {
		  if ((death.getEntity() instanceof Player)) {
			 
	          Player player = death.getEntity();
	          Player killer = player.getKiller();
	          if((killer.getUniqueId() == player.getUniqueId())) return;
	          if(killer.getMaxHealth() >= plugin.getConfig().getInt("HearthLimit.Limit") && plugin.getConfig().getBoolean("HearthLimit.Enabled")) {
	        	  killer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.LimitReached").replace("%player%", player.getName()).replace("%hearths%", plugin.getConfig().getString("AddedHearths"))));
	          }else {
	        	  killer.setMaxHealth(killer.getMaxHealth() + plugin.getConfig().getInt("AddedHearths"));
	              killer.setHealth(killer.getHealth() + plugin.getConfig().getInt("AddedHearths"));
	              killer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.HearthAdded").replace("%player%", player.getName()).replace("%hearths%", plugin.getConfig().getString("AddedHearths"))));  
	          } 
	          if(player.getMaxHealth() <= 2.0) {
	        	  
	        	  player.getEquipment().clear();
	              player.getInventory().clear();
	        	  Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoHearthBan").replace("%killer%", killer.getName())),null,null);
	              player.kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoHearthBan").replace("%killer%", killer.getName())));
	          }else {
	        	  player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.LostHearths").replace("%killer%", killer.getName()).replace("%hearths%", plugin.getConfig().getString("AddedHearths"))));  
	        	  player.setMaxHealth(player.getMaxHealth() - plugin.getConfig().getInt("AddedHearths"));
	          }
	          
	          
	      }
	  }
}
