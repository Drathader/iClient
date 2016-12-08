package me.ihaq.iClient.Commands.Render;

import me.ihaq.iClient.modules.Render.Fullbright;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class FullBrightCommands {
	
	public static void getChat(String[] args){
		String[] argsfullbright = args;
		if (argsfullbright.length != 2) {
			if (argsfullbright[2].equalsIgnoreCase("gamma")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] FullBright mode set to §fGAMMA§7!");
				Fullbright.mode = "gamma";
			} 
			else if (argsfullbright[2].equalsIgnoreCase("potion")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] FullBright mode set to §fPOTION§7!");
				Fullbright.mode = "potion";
			} 
			else {
				EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render fullbright §7(§fGAMMA§7/§fPOTION§7)");
			}
		}
		else{
			EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render fullbright §7(§fGAMMA§7/§fPOTION§7)");
		}
	}
}
