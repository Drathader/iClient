package me.ihaq.iClient.Commands.Combat;

import me.ihaq.iClient.modules.Combat.Criticals;
import me.ihaq.iClient.modules.Render.Fullbright;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class CriticalsCommands {
	
	public static void getChat(String[] args){
		String[] argscriticals = args;
		if (argscriticals.length != 2) {
			if (argscriticals[2].equalsIgnoreCase("jump")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] PlayerESP mode set to §fJUMP§7!");
				Criticals.mode = "JUMP";
			} 
			else if (argscriticals[2].equalsIgnoreCase("packets")) {
				EntityPlayerSP.msg("§7[§2i§fClient§7] Criticals mode set to §fPACKETS§7!");
				Criticals.mode = "PACKETS";
			}
			else {
				EntityPlayerSP.msg("§7[§2i§fClient§7] §f-combat criticals §7(§fJUMP§7/§fPACKETS§7)");
			}
		}
		else{
			EntityPlayerSP.msg("§7[§2i§fClient§7] §f-combat criticals §7(§fJUMP§7/§fPACKETS§7)");
		}
	}
}
