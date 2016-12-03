package me.ihaq.iClient.modules;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Respawn extends Module{
    public Respawn() {
        super("Respawn", Keyboard.KEY_L, Category.MISC,"");
    }

	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		if (!mc.thePlayer.isEntityAlive()) {
			mc.thePlayer.respawnPlayer();
		}

		super.onUpdate();
	}
}
