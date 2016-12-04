package me.ihaq.iClient.modules.Misc;


import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
 
public class NoFall extends Module{
       
    public NoFall() {
        super("NoFall", Keyboard.KEY_N, Category.MISC,"");
    }
       
    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;
               
        if(mc.thePlayer.fallDistance > 2F) {
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
       
        super.onUpdate();
    }
   
}