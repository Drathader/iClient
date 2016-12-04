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

	private static String mode = "";
	int triggerDelay;

	public Criticals() {
		super("Criticals", Keyboard.KEY_NONE, Category.COMBAT, mode);
	}
	
	@Override
	public void onDisable(){
		mode = "";
	}
	
	@Override
	public void onEnable(){
		mode = "";
	}
	
		
	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;
		
		jumpMode();
		super.onUpdate();
	}
	
	
	public void jumpMode() {
		setMode("\u00A7f[JUMP]");
		mode = "JUMP";	
	}
	
	public static String getCriticalsMode(){
		return mode;
	}
	

}
