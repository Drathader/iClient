package me.ihaq.iClient.modules.Combat;

import java.util.Timer;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import java.util.Iterator;

public class Criticals extends Module {

	private static String mode = "PACKETS";
	public static boolean active;

	public Criticals() {
		super("Criticals", Keyboard.KEY_NONE, Category.COMBAT, mode);
	}
	
	@Override
	public void onEnable(){
		 active = true;
	}
	
	@Override
	public void onDisable(){
		 active = false;
	}
	
	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		if(mode.equals("JUMP")){
			setMode("\u00A7f[JUMP]");
		}else if(mode.equals("PACKETS")){
			setMode("\u00A7f[PACKETS]");
		}
	}

	static void packets() {
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.05D, mc.thePlayer.posZ, false));
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.012511D, mc.thePlayer.posZ, false));
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	}

	public static String getCriticalsMode() {
		return mode;
	}
	


}
