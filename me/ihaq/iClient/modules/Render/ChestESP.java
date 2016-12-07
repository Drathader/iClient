package me.ihaq.iClient.modules.Render;

import net.minecraft.tileentity.TileEntityChest;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.utils.RenderUtils;

public class ChestESP extends Module{
	private static String mode = "outline";
	
	public ChestESP() {
		super("ChestESP", Keyboard.KEY_NONE, Category.RENDER, mode);
	}
	
	public void onRender() {
		if(this.isToggled()){
			if(mode.equals("outline")){
				setMode("\u00A7f[OUTLINE]");
			}
			else if(mode.equals("box")){
				box();
			}
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

	public static String getESPMode() {
		return mode;
	}

}