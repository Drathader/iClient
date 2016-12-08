package me.ihaq.iClient.Commands;

import me.ihaq.iClient.Commands.Combat.CriticalsCommands;
import me.ihaq.iClient.Commands.Render.FullBrightCommands;
import me.ihaq.iClient.Commands.Render.MobESPCommand;
import me.ihaq.iClient.Commands.Render.PlayerESPCommand;
import net.minecraft.client.entity.EntityPlayerSP;

public class Command {
	
	public static void getRender(String[] cmd){
		if (cmd.length != 1) {
			try {
				String[] args = cmd;
				
				if (args[1].equalsIgnoreCase("playeresp")) {
					PlayerESPCommand.getChat(args);

				}
				else if (args[1].equalsIgnoreCase("mobesp")) {
					MobESPCommand.getChat(args);
				}
				else if (args[1].equalsIgnoreCase("fullbright")) {
					FullBrightCommands.getChat(args);
				}
				else{
					EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render (mode) (option)");
				}
			} 
			catch (Exception exception) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] §4Error: §c" + exception.toString());
			}
		} 
		else {
			EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render (modname) (option)");
		}		
	}
	
	public static void getCombat(String[] cmd){
		if (cmd.length != 1) {
			try {
				String[] args = cmd;						
				if (args[1].equalsIgnoreCase("criticals")) {
					CriticalsCommands.getChat(args);
				}
				else{
					EntityPlayerSP.msg("§7[§2i§fClient§7] §f-combat (mode) (option)");
				}
			} 
			catch (Exception exception) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] §4Error: §c" + exception.toString());
			}
		} 
		else {
			EntityPlayerSP.msg("§7[§2i§fClient§7] §f-combat (modname) (option)");
		}
		
	}

}
