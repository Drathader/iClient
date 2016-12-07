package me.ihaq.iClient.modules.Combat;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.Events.Events.VitalTimer;
import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module {
	
	public AutoArmor() {
		super("AutoArmor", Keyboard.KEY_NONE, Category.COMBAT, "");
	}

	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;
	

	}
}
