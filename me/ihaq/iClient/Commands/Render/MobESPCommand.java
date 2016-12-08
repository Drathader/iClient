package me.ihaq.iClient.Commands.Render;

import me.ihaq.iClient.modules.Render.MobESP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class MobESPCommand {
	
	public static void getChat(String[] args){
		String[] argsmobesp = args;
		if (argsmobesp.length != 2) {
			if (argsmobesp[2].equalsIgnoreCase("outline")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] MobESP mode set to §fOUTLINE§7!");
				MobESP.mode = "outline";
			} 
			else if (argsmobesp[2].equalsIgnoreCase("box")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] MobESP mode set to §fBOX§7!");
				MobESP.mode = "box";
			} 
			else if (argsmobesp[2].equalsIgnoreCase("wireframe")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] MobESP mode set to §fWIREFRAME§7!");
				MobESP.mode = "wireframe";
			} 
			else {
				EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render mobesp §7(§fOUTLINE§7/§fBOX§7/§fWIREFRAME§7)");
			}
		}
		else{
			EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render mobesp §7(§fOUTLINE§7/§fBOX§7/§fWIREFRAME§7)");
		}
	}
}
