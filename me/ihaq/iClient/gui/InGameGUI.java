package me.ihaq.iClient.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import me.ihaq.iClient.iClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import me.ihaq.iClient.gui.TabGUI;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import me.ihaq.iClient.module.Module;
import me.ihaq.iClient.utils.Colors;
import me.ihaq.iClient.utils.ComparatorStrings;
import me.ihaq.iClient.utils.FontUtils;
import me.ihaq.iClient.utils.R2DUtils;
 
public class InGameGUI extends GuiScreen {

	private FontUtils fu_title;
	private FontUtils fu_mods;
	private ArrayList<String> mods = new ArrayList<String>();

	
	
    public InGameGUI () {  
    	TabGUI.init();
        this.mc = Minecraft.getMinecraft();  
        this.fu_title = new FontUtils("Audiowide", Font.PLAIN, 30);
        this.fu_mods = new FontUtils("Audiowide", Font.PLAIN, 18);
    }
   
    @SuppressWarnings("incomplete-switch")
	public void renderScreen() {
    	
    	Entity entity = this.mc.getRenderViewEntity();
        EnumFacing enumfacing = entity.getHorizontalFacing();
        String s = "Invalid";
        switch (enumfacing)
        {
            case NORTH:
                s = "NORTH";
                break;

            case SOUTH:
                s = "SOUTH";
                break;

            case WEST:
                s = "WEST";
                break;

            case EAST:
                s = "EAST";
        }
        
        int playerFPS = mc.getDebugFPS();
        double playerX = Math.round(mc.thePlayer.posX*100.0)/100.0;
        double playerY = Math.round(mc.thePlayer.posY*100.0)/100.0;
        double playerZ = Math.round(mc.thePlayer.posZ*100.0)/100.0;
        
        drawRect(5,80,53,119, -1610612736);
        fu_mods.drawString("X: \u00A7f"+playerX, 6, 78, Colors.getRainbow(0L, 1.0F).hashCode());
        fu_mods.drawString("Y: \u00A7f"+playerY, 6, 88, Colors.getRainbow(0L, 1.0F).hashCode());
        fu_mods.drawString("Z: \u00A7f"+playerZ, 6, 98, Colors.getRainbow(0L, 1.0F).hashCode());
        fu_mods.drawString("FPS: \u00A7f"+ playerFPS, 6, 108, Colors.getRainbow(0L, 1.0F).hashCode());
        
        fu_title.drawString("i"+"\u00A7fClient", 5, 2, Colors.getRainbow(0L, 1.0F).hashCode());
        TabGUI.init();
        TabGUI.render();
        
        if(mc.ingameGUI.getChatGUI().getChatOpen()){
        	//YAY
        }
        else{
        	fu_mods.drawString("Version: \u00A7f1.8", 5, GuiScreen.height-15, Colors.getRainbow(0L, 1.0F).hashCode());
        	fu_mods.drawString("Ping: \u00A7f"+ getPing() + "ms", 5, GuiScreen.height-25, Colors.getRainbow(0L, 1.0F).hashCode());
        	
        }
        
        mods.clear();
        int count = 5;     
        for(Module module : iClient.getModules()) {
            if(!module.isToggled())
                continue;
            mods.add(module.getName()+" "+module.getMode());
        }
        //Collections.sort(mods, new ComparatorStrings());
		for (String m : mods){
			fu_mods.drawString(m, (GuiScreen.width - 5) - (fu_mods.getWidth(m)), count, Colors.getRainbow(0L, 1.0F).hashCode());
			count += 10;
		}       
    }
    
    public int getPing(){
    	int ping = 0;
        try {
            ping = mc.thePlayer.sendQueue.getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
            return ping;
        } catch (Exception x) {
            ping = 0;
        }
        return ping;
    }
    
  
}
