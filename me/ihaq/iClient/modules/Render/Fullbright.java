package me.ihaq.iClient.modules.Render;


import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect; 

public class Fullbright extends Module{
       
    private float gamaSetting = 0F;
    public static String mode = "gamma";
   
    public Fullbright() {
        super("Fullbright", Keyboard.KEY_NONE,Category.RENDER, mode);
    }
       
    @Override
    public void onEnable() {
        this.gamaSetting = mc.gameSettings.gammaSetting;
    }
   
    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = this.gamaSetting;
    }
   
    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;
        
        if(mode.equals("gamma")){
        	setMode("\u00A7f[GAMMA]");
        	gamma();
        }else if(mode.equals("potion")){
        	setMode("\u00A7f[POTION]");
        	potion();
        }
       
    }
    
    public void gamma(){
    	mc.gameSettings.gammaSetting = 100F;
    }
    
    public void potion(){
    	mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 5200, 0));
    }
   
}