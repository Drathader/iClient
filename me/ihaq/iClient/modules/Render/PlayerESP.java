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

public class PlayerESP extends Module {
	private static String mode;

	public PlayerESP() {
		super("PlayerESP", Keyboard.KEY_P, Category.RENDER, mode);
		setMode("\u00A7f[OUTLINE]");
	}

	/*
	 * @Override public void onRender() {
	 * 
	 * if(!this.isToggled()) return; for(Object theObject :
	 * mc.theWorld.loadedEntityList){ for(Object e:
	 * mc.theWorld.loadedEntityList){ if(e instanceof EntityPlayer){ if(e !=
	 * mc.thePlayer) RenderUtils.entityESPBox((Entity)e, 0); } }
	 * 
	 * 
	 * }
	 * 
	 * super.onRender(); }
	 */
}