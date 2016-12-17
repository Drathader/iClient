package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;

public class Jesus extends Module {
	private static String mode;

	public Jesus() {
		super("Jesus", Keyboard.KEY_K, Category.MOVEMENT, mode);
	}

	public boolean wasInWater;

	
	@Override
	public void onUpdate() {
		if (!this.isToggled())
			return;
		
		if ((mc.thePlayer.isInWater() || mc.thePlayer.isInLava()) && (!Minecraft.thePlayer.onGround)
				&& (!mc.thePlayer.isSneaking())
				&& (!Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode()))) {
			mc.thePlayer.motionY = 0.09999999552965164D;
		}
		if ((!Minecraft.thePlayer.capabilities.isFlying) && (!Minecraft.thePlayer.isInWater())
				&& (!Minecraft.thePlayer.onGround)) {
			if ((Minecraft.thePlayer.motionY < -0.3D) || (Minecraft.thePlayer.onGround)
					|| (Minecraft.thePlayer.isOnLadder())) {
				return;
			}
			Minecraft.thePlayer.motionY = (Minecraft.thePlayer.motionY / 0.9800000190734863D + 0.08D);
			EntityPlayerSP entityPlayerSP = Minecraft.thePlayer;
			entityPlayerSP.motionY -= 0.03120000000005D;
			if ((mc.thePlayer.isInWater() || mc.thePlayer.isInLava()) && (!Minecraft.thePlayer.onGround)
					&& (Minecraft.thePlayer.motionY < 0.2D)) {
				Minecraft.thePlayer.motionY = 0.5D;
			}
		}
	}

	private boolean isVaildForJesus() {
		return (mc.thePlayer.fallDistance < 3.0F) && (!this.mc.gameSettings.keyBindJump.isPressed())
				&& (!Minecraft.thePlayer.onGround) && (!mc.thePlayer.isInWater() || !mc.thePlayer.isInLava()) && (!mc.thePlayer.isSneaking());
	}
}
