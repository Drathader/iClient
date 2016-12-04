package me.ihaq.iClient.modules.Movement;


import net.minecraft.network.play.client.C03PacketPlayer;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;

public class Sprint extends Module{
	
	public Sprint(){
		super("Sprint", Keyboard.KEY_G,Category.MOVEMENT,"");
	}
	
	public void onUpdate(){
		if(!this.isToggled()){
			return;
		}
		if(!(mc.thePlayer.isCollidedHorizontally) && mc.thePlayer.moveForward > 0.0f) {
			mc.thePlayer.setSprinting(true);
		}else{
			if(!(mc.thePlayer.isSprinting())){
				//YAY
			}
			else{
				mc.thePlayer.setSprinting(false);
			}
		}	
	}

}
