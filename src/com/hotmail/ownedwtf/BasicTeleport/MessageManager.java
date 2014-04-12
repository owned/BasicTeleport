package com.hotmail.ownedwtf.BasicTeleport;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager{
	
	private MessageManager() { }
	
	private static MessageManager instance = new MessageManager();
	
	public static MessageManager getInstance() {
		return instance;
		
	}
	public void severe(CommandSender sender, String msg){
		msg(sender, ChatColor.YELLOW + "[BasicTeleport] " + ChatColor.RED, msg);
	}
	
	public void success(CommandSender sender, String msg){
		msg(sender, ChatColor.YELLOW + "[BasicTeleport] " + ChatColor.GREEN, msg);
	}
	
	private void msg(CommandSender sender, String string, String msg){
		sender.sendMessage(string + msg);
	}

}