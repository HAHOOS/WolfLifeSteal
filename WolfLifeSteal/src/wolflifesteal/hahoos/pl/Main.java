package wolflifesteal.hahoos.pl;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import wolflifesteal.hahoos.pl.commands.MaxHearths;
import wolflifesteal.hahoos.pl.commands.WithdrawHearths;
import wolflifesteal.hahoos.pl.listeners.onItemInteraction;
import wolflifesteal.hahoos.pl.listeners.onPlayerDeath;

public class Main extends JavaPlugin implements Listener {
  public void onEnable() {
	  getServer().getPluginManager().registerEvents(new onPlayerDeath(),this);
	  getServer().getPluginManager().registerEvents(new onItemInteraction(), this);
	  this.getCommand("maxhearths").setExecutor(new MaxHearths());
	  this.getCommand("withdrawhearths").setExecutor(new WithdrawHearths());
  }
  
  public void onDisable() {
	  
  }
  
  
}
