package wolflifesteal.hahoos.pl.listeners;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.ItemStack;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import wolflifesteal.hahoos.pl.Main;

public class onItemInteraction implements Listener {
	Plugin plugin = Main.getPlugin(null);
  @EventHandler
  public void onPlayerInteractEvent(PlayerInteractEvent item) {
	  Action Action = item.getAction();
	  if(Action == org.bukkit.event.block.Action.PHYSICAL && item.getItem().getType() == Material.getMaterial(plugin.getConfig().getString("Commands.withdrawhearths.Item.Item"))) {
		 ItemStack nmsItem = CraftItemStack.asNMSCopy(item.getItem());
		 NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		 if(itemCompound.hasKey("wolflifesteal_hearth")) {
			 item.getPlayer().getInventory().remove(item.getItem());
			 item.getPlayer().setMaxHealth(item.getPlayer().getMaxHealth()+plugin.getConfig().getInt("AddedHearths"));
			 item.getPlayer().setHealth(item.getPlayer().getHealth()+plugin.getConfig().getInt("AddedHearths"));
			 item.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.RecoveredHearth")).replace("%amount%", plugin.getConfig().getString("AddedHearths")));
		 } 
	  }
  }
}
