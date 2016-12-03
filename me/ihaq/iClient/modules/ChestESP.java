package me.ihaq.iClient.modules;

import net.minecraft.tileentity.TileEntityChest;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.module.Module;
import me.ihaq.iClient.utils.RenderUtils;

public class ChestESP extends Module{
	private static String mode;
	
	public ChestESP() {
		super("ChestESP", Keyboard.KEY_K, Category.RENDER, mode);
	}
	
	public void onRender() {
		if(this.isToggled()){
			box();
		}
	}
	
	public void box(){
		setMode("\u00A7f[BOX]");
		for(Object o: mc.theWorld.loadedTileEntityList){
			if(o instanceof TileEntityChest){
				RenderUtils.blockESPBox(((TileEntityChest)o).getPos());
			}
		}
	}

}