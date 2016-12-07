package me.ihaq.iClient.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import me.ihaq.iClient.iClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import me.ihaq.iClient.gui.TabGUI;
import me.ihaq.iClient.modules.Module;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import me.ihaq.iClient.utils.Colors;
import me.ihaq.iClient.utils.ComparatorStrings;
import me.ihaq.iClient.utils.FontUtils;
import me.ihaq.iClient.utils.R2DUtils;
 
public class InGameGUI extends GuiScreen {
	
	private ArrayList<String> mods = new ArrayList<String>();
	private final FontUtils fu_title = new FontUtils("Audiowide", Font.PLAIN, 30);
	
    public InGameGUI () {  
    	TabGUI.init();
        this.mc = Minecraft.getMinecraft();  
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
        
        drawRect(5,80, TabGUI.baseCategoryWidth+2, 121, -1610612736);
        mc.fontRendererObj.drawString("X: \u00A7f"+playerX, 6, 82, Colors.getRainbow(0L, 1.0F).hashCode());
        mc.fontRendererObj.drawString("Y: \u00A7f"+playerY, 6, 92, Colors.getRainbow(0L, 1.0F).hashCode());
        mc.fontRendererObj.drawString("Z: \u00A7f"+playerZ, 6, 102, Colors.getRainbow(0L, 1.0F).hashCode());
        mc.fontRendererObj.drawString("FPS: \u00A7f"+ playerFPS, 6, 112, Colors.getRainbow(0L, 1.0F).hashCode());
        

        
        if(mc.ingameGUI.getChatGUI().getChatOpen()){
        	//YAY
        }
        else{
        	mc.fontRendererObj.drawString("Version: \u00A7f1.8", 5, GuiScreen.height-15, Colors.getRainbow(0L, 1.0F).hashCode());
        	mc.fontRendererObj.drawString("Ping: \u00A7f"+ getPing() + "ms", 5, GuiScreen.height-25, Colors.getRainbow(0L, 1.0F).hashCode());
        	
        }
        
        mc.fontRendererObj.drawString("i"+"\u00A7fClient", 5, 2, Colors.getRainbow(0L, 1.0F).hashCode());
        
        TabGUI.init();
        TabGUI.render();
        
        mods.clear();
        int count = 5;     
        for(Module module : iClient.getModules()) {
            if(!module.isToggled())
                continue;
            mods.add(module.getName()+" "+module.getMode());
        }
        Collections.sort(mods, new ComparatorStrings());
		for (String m : mods){
			mc.fontRendererObj.drawString(m, (GuiScreen.width - 5) - (mc.fontRendererObj.getStringWidth(m)), count, Colors.getRainbow(0L, 1.0F).hashCode());
			count += 10;
		}       
    }
    
    public int getPing(){
    	int ping = 0;
        try {
            ping = (int) mc.thePlayer.sendQueue.getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
            return ping;
        } catch (Exception x) {
            ping = 0;
        }
        return ping;
    }
    
  
}
