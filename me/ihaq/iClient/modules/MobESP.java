package me.ihaq.iClient.modules;

import me.ihaq.iClient.module.Module;
import me.ihaq.iClient.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
 
import org.lwjgl.input.Keyboard;
 
public class MobESP extends Module{
	private static String mode;
        public MobESP() {
                super("MobESP", Keyboard.KEY_L, Category.RENDER, mode);
        		setMode("\u00A7f[OUTLINE]");
        }
   /*    
        @Override
        public void onRender() {
               
                if(!this.isToggled())
                return;
                for(Object theObject : mc.theWorld.loadedEntityList){
                	for(Object e: mc.theWorld.loadedEntityList){
        				if(e instanceof EntityLiving){
        					RenderUtils.entityESPBox((Entity)e, 0);
        				}
        }
                       

                }
               
                super.onRender();
        }
   */
}