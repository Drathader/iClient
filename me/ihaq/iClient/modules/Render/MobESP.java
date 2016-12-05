package me.ihaq.iClient.modules.Render;

import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

public class MobESP extends Module {
	public static String mode = "outline";

	public MobESP() {
		super("MobESP", Keyboard.KEY_NONE, Category.RENDER, mode);
	}

	@Override
	public void onRender() {

		if (!this.isToggled())
			return;
		
		if (mode.equals("outline")) {
			setMode("\u00A7f[OUTLINE]");
		} else if (mode.equals("box")) {
			box();
		}
		else if (mode.equals("wireframe")) {
			setMode("\u00A7f[WIREFRAME]");
		}
	}

	public static String getESPMode() {
		return mode;
	}

	public void box() {
		setMode("\u00A7f[BOX]");
		mode = "box";
		for (Object theObject : mc.theWorld.loadedEntityList) {
			for (Object e : mc.theWorld.loadedEntityList) {
				if (e instanceof EntityLiving) {
					RenderUtils.entityESPBox((Entity) e, 0);
				}
			}
		}
	}
}