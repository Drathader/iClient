package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
 
public class AirJump extends Module{
	
    public AirJump() {
        super("AirJump", Keyboard.KEY_NONE ,Category.MOVEMENT, "");
    }
       
    @Override
    public void onDisable() {
        super.onDisable();
    }
   
    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;                     
        
        if(mc.gameSettings.keyBindJump.isPressed()){
        	mc.thePlayer.motionX *= 1.5;
        	mc.thePlayer.motionY = 0.4;
        	mc.thePlayer.motionZ *= 1.5;
        	mc.thePlayer.onGround =  true;
        }      
        	
    }  
}