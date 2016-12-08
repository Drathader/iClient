package me.ihaq.iClient.Commands.Render;

import me.ihaq.iClient.modules.Render.PlayerESP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class PlayerESPCommand {
	
	public static void getChat(String[] args){
		String[] argsplayeresp = args;
		if (argsplayeresp.length != 2) {
			if (argsplayeresp[2].equalsIgnoreCase("outline")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] PlayerESP mode set to §fOUTLINE§7!");
				PlayerESP.mode = "outline";
			} 
			else if (argsplayeresp[2].equalsIgnoreCase("box")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] PlayerESP mode set to §fBOX§7!");
				PlayerESP.mode = "box";
			}
			else if (argsplayeresp[2].equalsIgnoreCase("wireframe")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] PlayerESP mode set to §fWIREFRAME§7!");
				PlayerESP.mode = "wireframe";
			} 
			else {
				EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render playeresp §7(§fOUTLINE§7/§fBOX§7/§fWIREFRAME§7)");
			}
		}
		else{
			EntityPlayerSP.msg("§7[§2i§fClient§7] §f-render playeresp §7(§fOUTLINE§7/§fBOX§7/§fWIREFRAME§7)");
		}
	}
}
