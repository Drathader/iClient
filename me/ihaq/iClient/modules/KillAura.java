package me.ihaq.iClient.modules;

import java.util.Timer;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class KillAura extends Module {

	private static String mode;

	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.COMBAT, mode);
	}

	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		vanilla();
	}

	public void vanilla() {
		setMode("\u00A7f[VANILLA]");
		//mc.thePlayer.swingItem();
	}
}
