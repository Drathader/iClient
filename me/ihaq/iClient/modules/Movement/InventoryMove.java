package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;

public class InventoryMove extends Module {

	public InventoryMove() {
		super("InventoryMove", Keyboard.KEY_NONE, Category.MOVEMENT, "");
	}

	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		KeyBinding[] moveKeys = { mc.gameSettings.keyBindRight, mc.gameSettings.keyBindLeft,mc.gameSettings.keyBindBack, mc.gameSettings.keyBindForward, mc.gameSettings.keyBindJump,mc.gameSettings.keyBindSprint };
		
		if ((Minecraft.currentScreen instanceof GuiContainer)) {
			KeyBinding[] array;
			int length = (array = moveKeys).length;
			for (int i = 0; i < length; i++) {
				KeyBinding key = array[i];
				key.pressed = Keyboard.isKeyDown(key.getKeyCode());
			}
		} 
		else if (Minecraft.currentScreen == null) {
			KeyBinding[] array2;
			int length2 = (array2 = moveKeys).length;
			for (int j = 0; j < length2; j++) {
				KeyBinding bind = array2[j];
				if (!Keyboard.isKeyDown(bind.getKeyCode())) {
					KeyBinding.setKeyBindState(bind.getKeyCode(), false);
				}
			}
		}
	}
}
