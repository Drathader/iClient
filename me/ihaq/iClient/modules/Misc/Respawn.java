package me.ihaq.iClient.modules.Misc;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Respawn extends Module{
    public Respawn() {
        super("Respawn", Keyboard.KEY_NONE, Category.MISC,"");
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
