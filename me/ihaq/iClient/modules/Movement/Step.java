package me.ihaq.iClient.modules.Movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

public class Step extends Module {
	private static String mode;

	public Step() {
		super("Step", Keyboard.KEY_H, Category.MOVEMENT, mode);
	}

	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		// packets();
		legit();

	}

	public void legit() {
		setMode("\u00A7f[LEGIT]");
		if ((mc.thePlayer.isCollidedHorizontally) && (mc.thePlayer.onGround) && (mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY + 0.2, mc.thePlayer.posX)).getBlock() instanceof BlockAir)) {
			mc.thePlayer.jump();
		}
	}

	public void packets() {
		setMode("\u00A7f[PACKETS]");
		if ((mc.thePlayer.isCollidedHorizontally) && (mc.thePlayer.onGround)) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,
					mc.thePlayer.posY + 0.42, mc.thePlayer.posZ, mc.thePlayer.onGround));
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,
					mc.thePlayer.posY + 0.75, mc.thePlayer.posZ, mc.thePlayer.onGround));
			mc.thePlayer.stepHeight = 1.0f;
		}

		else {
			mc.thePlayer.stepHeight = 0.5f;
		}

	}
}
