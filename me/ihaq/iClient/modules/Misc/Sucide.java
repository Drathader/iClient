package me.ihaq.iClient.modules.Misc;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;


public class Sucide extends Module{
	

	public Sucide() {
        super("Sucide", Keyboard.KEY_X, Category.MISC,"");
	}
	
	@Override
	public void onUpdate() {
	       
	        if(!this.isToggled())
	            return;
	    	   	
        	for (int i = 0; i <1000 ; i++) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.049, mc.thePlayer.posZ, false));
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
            }
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
            this.disable();
	}
}

