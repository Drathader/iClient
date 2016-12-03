package me.ihaq.iClient.modules;


import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;
import me.ihaq.iClient.module.Module;
 
public class NoFall extends Module{
       
    public NoFall() {
        super("NoFall", Keyboard.KEY_N, Category.MOVEMENT,"");
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