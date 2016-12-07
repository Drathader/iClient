package me.ihaq.iClient.modules.Combat;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Module.Category;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AntiBot extends Module {
	
	public AntiBot() {
		super("AntiBot", Keyboard.KEY_NONE, Category.COMBAT, "");
	}

	@Override
	public void onUpdate() {
		if (!this.isToggled()) {
			return;
		}
	
		for (Object o : mc.theWorld.loadedEntityList) {
			Entity entity = (Entity) o;
			if (((entity instanceof EntityPlayer)) && (entity.getEntityId() > 10060000)) {
				mc.theWorld.removeEntity(entity);
				entity.setInvisible(true);
			}
		}

	}
}
