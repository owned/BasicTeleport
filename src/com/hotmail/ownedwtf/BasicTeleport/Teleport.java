package com.hotmail.ownedwtf.BasicTeleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Teleport extends JavaPlugin{

	public void onEnable(){
		Bukkit.getServer().getLogger().info("BasicTeleport v " + this.getDescription().getVersion()  + " enabled!");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("BasicTeleport v " + this.getDescription().getVersion()  + " disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			MessageManager.getInstance().severe(sender, " This plugin is for players only.");
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("tp")){
			if (!sender.hasPermission("basic.tp")){
				MessageManager.getInstance().severe(sender, " You are not permitted to use this command.");
				return true;
			}
			if (args.length == 0){
				MessageManager.getInstance().severe(sender, " Please specify a player.");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null){
				MessageManager.getInstance().severe(sender, " Could not find player " + args[0] + ".");
				return true;
			}
			p.teleport(target.getLocation());
			MessageManager.getInstance().success(sender, "Teleporting!");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("tphere")){
			if (!sender.hasPermission("basic.tphere")){
				MessageManager.getInstance().severe(sender, " You are not permitted to use this command.");
				return true;
			}
			if (args.length == 0){
				MessageManager.getInstance().severe(sender, " Please specify a player.");
				return true;
			}
			Player target = (Player) this.getServer().getPlayer(args[0]);
			if (target == null){
				MessageManager.getInstance().severe(sender, " Could not find player " + args[0] + ".");
				return true;
			}
			target.teleport(p);
			MessageManager.getInstance().success(sender, "Teleporting player now!");
			MessageManager.getInstance().success(target, "Teleporting.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("tpxyz")){
			if (!sender.hasPermission("basic.tpxyz")){
				MessageManager.getInstance().severe(sender, " You are not permitted to use this command.");
				return true;
			}
			if(args.length == 0){
				MessageManager.getInstance().severe(sender, " Please enter a [x] [y] [z].");
				return true;
			}
			Location loc = new Location(p.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
			p.teleport(loc);
			MessageManager.getInstance().success(sender, "You have sucessfully teleported to " + args[0] + ", " + args[1] + ", " + args[2]);
			return true;
		}
		/*
		if (cmd.getName().equalsIgnoreCase("tpa")){
			if (args.length == 0){
				MessageManager.getInstance().severe(sender, " Please specify a player.");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null){
				MessageManager.getInstance().severe(sender, " Could not find player " + args[0] + ".");
				return true;
			}
			MessageManager.getInstance().info(target, args[0] + " wants to teleport to you if you accept type /tpaccept if not /tpdeny");
			MessageManager.getInstance().success(sender, "Request sent to " + args[0] + " awaiting permission to teleport.");
				p.teleport(target.getLocation());
				return true;
			}
		}
		*/
		if(cmd.getName().equalsIgnoreCase("setspawn")){
			if (!sender.hasPermission("basic.spawn.set")){
				MessageManager.getInstance().severe(sender, " You are not permitted to use this command.");
				return true;
			}
			getConfig().set("spawn.world", p.getLocation().getWorld().getName());
			getConfig().set("spawn.x", p.getLocation().getX());
			getConfig().set("spawn.y", p.getLocation().getY());
			getConfig().set("spawn.z", p.getLocation().getZ());
			getConfig().set("spawn.pitch", p.getLocation().getPitch());
			getConfig().set("spawn.yaw", p.getLocation().getYaw());
			saveConfig();
			MessageManager.getInstance().success(sender, " Spawn has been set!");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("spawn")){
			if(getConfig().getConfigurationSection("spawn") == null){
				MessageManager.getInstance().severe(sender, " The spawn has not been setup.");
				return true;
			}
			World w = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
			double x = getConfig().getDouble("spawn.x");
			double y = getConfig().getDouble("spawn.y");
			double z = getConfig().getDouble("spawn.z");
			p.teleport(new Location(w, x, y, z));
		}
		
		if (cmd.getName().equalsIgnoreCase("tpaccept")){
		}
		return true;
	}
}
