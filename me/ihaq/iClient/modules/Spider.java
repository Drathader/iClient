package me.ihaq.iClient.modules;

import java.util.Timer;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Spider extends Module {

	private static String mode;

	public Spider() {
		super("Spider", Keyboard.KEY_X, Category.MOVEMENT, mode);
	}
	
	double lastJumpPos;
	int intervel;
	@Override
	public void onUpdate() {
		if (!this.isToggled())
			return;
		
		intervel++;
		newMode();
	}

	public void newMode() {
		setMode("\u00A7f[NEW]");
		if ((mc.thePlayer.isCollidedHorizontally)) {
			for (int i = 0; i < 20; i++) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY + 0.049, mc.thePlayer.posZ, false));
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY, mc.thePlayer.posZ, false));

			}
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY, mc.thePlayer.posZ, true));

			if(mc.thePlayer.onGround){
				lastJumpPos = mc.thePlayer.posY;
				mc.thePlayer.jump();
				if(mc.thePlayer.posY == lastJumpPos + 1){
					mc.thePlayer.jump();
				}
			}
			

		}
	}
	
	public void oldMode() {
		setMode("\u00A7f[OLD]");
		if ((mc.thePlayer.isCollidedHorizontally)) {
				mc.thePlayer.jump();
		}
	}
}
