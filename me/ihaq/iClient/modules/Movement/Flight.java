package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
 
public class Flight extends Module{
	private static String mode;
    public Flight() {
        super("Flight", Keyboard.KEY_G,Category.MOVEMENT, mode);
    }
       
    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;
        super.onDisable();
    }
   
    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;
              
        vanilla();
		
    }
    
    public void vanilla(){
    	setMode("\u00A7f[VANILLA]");
    	if(mc.gameSettings.keyBindJump.isPressed()){
			mc.thePlayer.motionY += 0.2;
		}
		
		if(mc.gameSettings.keyBindSneak.isPressed()){
			mc.thePlayer.motionY -= 0.2;
		}
		mc.thePlayer.capabilities.isFlying = true;
    }
   
}