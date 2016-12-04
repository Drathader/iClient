package me.ihaq.iClient.modules.Render;


import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module; 

public class Fullbright extends Module{
       
    private float gamaSetting = 0F;
   
    public Fullbright() {
        super("Fullbright", Keyboard.KEY_B,Category.RENDER,"");
    }
       
    @Override
    public void onEnable() {
        this.gamaSetting = mc.gameSettings.gammaSetting;
        super.onEnable();
    }
   
    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = this.gamaSetting;
        super.onDisable();
    }
   
    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;
               
        mc.gameSettings.gammaSetting = 100F;
       
        super.onUpdate();
    }
   
}