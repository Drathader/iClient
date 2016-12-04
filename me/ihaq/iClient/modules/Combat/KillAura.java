package me.ihaq.iClient.modules.Combat;

import java.util.Timer;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import java.util.Iterator;

public class KillAura extends Module {

	private static String mode;
	int triggerDelay;

	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.COMBAT, mode);
	}
		
	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		triggerBot();
	}
	
	
	public void triggerBot() {
		setMode("\u00A7f[TRIGGERBOT]");		
		
		for(Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
            Object theObject = entities.next();
            if(theObject instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) theObject;
               
                if(entity instanceof EntityPlayerSP){
                	continue;
                }
                if(mc.thePlayer.getDistanceToEntity(entity) <= 4.5F) {
                    if(entity.isEntityAlive()) {
                    	
                    	if(Criticals.getCriticalsMode().equals("JUMP")){
                    		if(mc.thePlayer.onGround){
                    			mc.thePlayer.jump();
                    		}
                    		mc.playerController.attackEntity(mc.thePlayer, entity);
                            mc.thePlayer.swingItem();
                            continue;
                    	}
                    	else{
                            mc.playerController.attackEntity(mc.thePlayer, entity);
                            mc.thePlayer.swingItem();
                            continue;
                    	}
                    }
                }
            }
        }
		super.onUpdate();
	}
}
