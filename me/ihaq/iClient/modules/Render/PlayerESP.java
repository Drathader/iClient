package me.ihaq.iClient.modules.Render;

import me.ihaq.iClient.iClient;
import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.utils.RenderUtils;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

import javax.swing.Spring;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class PlayerESP extends Module {
	public static String mode = "outline";

	public PlayerESP() {
		super("PlayerESP", Keyboard.KEY_NONE, Category.RENDER, mode);
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
				if (e instanceof EntityPlayer) {
					if (e != mc.thePlayer)
						RenderUtils.entityESPBox((Entity) e, 0);
				}
			}
		}
	}
}