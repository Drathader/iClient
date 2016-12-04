package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Jesus extends Module {
	private static String mode;

    public Jesus() {
        super("Jesus", Keyboard.KEY_K, Category.MOVEMENT, mode);
    }
    
    public void packets(){
		mode = "\u00A7f[PACKETS]";
        if((mc.thePlayer.isCollidedHorizontally) && (mc.thePlayer.onGround)){
    		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.42, mc.thePlayer.posZ,mc.thePlayer.onGround));
    		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.75, mc.thePlayer.posZ, mc.thePlayer.onGround));
    		mc.thePlayer.stepHeight = 1.0f;
    	}
        
    	else{
    		mc.thePlayer.stepHeight = 0.5f;
    	}
	}

}