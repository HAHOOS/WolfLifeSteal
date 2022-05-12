package wolflifesteal.hahoos.pl.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import wolflifesteal.hahoos.pl.Main;

public class WithdrawHearths implements CommandExecutor {
	Plugin plugin = Main.getPlugin(null);
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			ItemStack item = new ItemStack(Material.NETHER_STAR);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Commands.withdrawhearths.Item.Name")));
			im.setLore((List<String>) plugin.getConfig().getList("Commands.withdrawhearths.Item.Description"));
			item.setItemMeta(im);
			item.setAmount(Integer.parseInt(args[0]));
			net.minecraft.server.v1_8_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
			NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
			itemCompound.set("wolflifesteal_hearth", itemCompound);
			nmsItem.setTag(itemCompound);
			((Player) sender).getInventory().addItem(item);
			((Player) sender).setMaxHealth(
					((Player) sender)
					.getMaxHealth()-
					(Integer.parseInt(args[0]) * plugin.getConfig().getInt("AddedHearths")));
		}
		return true;
	}

}
