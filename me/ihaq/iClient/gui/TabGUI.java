package me.ihaq.iClient.gui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.opengl.GL11;

import me.ihaq.iClient.iClient;
import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Module.Category;
import me.ihaq.iClient.utils.Colors;
import me.ihaq.iClient.utils.R2DUtils;
import me.ihaq.iClient.utils.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
 
public class TabGUI {
    private static final int NO_COLOR = 0;
    private static final int INSIDE_COLOR = -1610612736;
    private static final int BORDER_COLOR = 2013265920;
    private static final int COMPONENT_HEIGHT = 14;
    static int baseCategoryWidth;
    private static int baseCategoryHeight;
    private static int baseModWidth;
    private static int baseModHeight;
    private static Section section = Section.CATEGORY;
    private static int categoryTab = 0;
    private static int modTab = 0;
    private static int categoryPosition = 21;
    private static int categoryTargetPosition = 21;
    private static int modPosition = 15;
    private static int modTargetPosition = 15;
    private static boolean transitionQuickly;
    private static long lastUpdateTime;
    
    public static void init() {
        int highestWidth = 0;
        Category[] values;
        int length = (values = Category.values()).length;
        for (int i = 0; i < length; i++) {
            Category category = values[i];
            String name = StringUtil.capitalize(category.name().toLowerCase());
            int stringWidth = (int) Minecraft.getMinecraft().fontRendererObj.getStringWidth(name) + 5;
            highestWidth = Math.max(stringWidth, highestWidth);
        }
        baseCategoryWidth = highestWidth + 6;
        baseCategoryHeight = Category.values().length * 15 + 2;
        
    }

 
    public static void render() {
        updateBars();
        R2DUtils.drawRect(5.0F, 20.0F, 2 + baseCategoryWidth+5, 16 + baseCategoryHeight, -1610612736);
        R2DUtils.drawRect(5.0F,21.0F,10.0F,15 + baseCategoryHeight,Colors.getRainbow(0L, 1.0F).hashCode());
        R2DUtils.drawRect(6.0F, categoryPosition, 2 + baseCategoryWidth - 1 +5, categoryPosition + 14, Colors.getRainbow(0L, 1.0F).hashCode());
        int yPos = 20;
        int yPosBottom = 29;
        for (int i = 0; i < Category.values().length; i++) {
            Category category = Category.values()[i];
            String name = StringUtil.capitalize(category.name().toLowerCase());
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, (int) 12.0, yPos + 4, -1);
            yPos += 14;
            yPosBottom += 14;
        }
        if (section == Section.MODS) {
        	
        	
        	GL11.glScissor(baseCategoryWidth + 4 +5, categoryPosition + getModsInCategory(Category.values()[categoryTab]).size() * 14 + 1, baseCategoryWidth+baseModWidth+10, (categoryPosition + getModsInCategory(Category.values()[categoryTab]).size() * 14 + 1) - (categoryPosition - 1) );
        	GL11.glEnable(GL11.GL_SCISSOR_TEST);
        	for(int x = 0 ; x<baseModWidth+10 ; x++){
        		Gui.drawRect(baseCategoryWidth + 4 +5, categoryPosition - 1, baseCategoryWidth+x, categoryPosition + getModsInCategory(Category.values()[categoryTab]).size() * 14 + 1, -1610612736);
    			Gui.drawRect(baseCategoryWidth + 5 +5, modPosition, baseCategoryWidth +x-1, modPosition + 14, Colors.getRainbow(0L, 1.0F).hashCode());       	
        	}
        	GL11.glDisable(GL11.GL_SCISSOR_TEST);
        	
            yPos = categoryPosition;
            yPosBottom = categoryPosition + 14;
            for (int i = 0; i < getModsInCategory(Category.values()[categoryTab]).size(); i++) {
                Module mod = getModsInCategory(Category.values()[categoryTab]).get(i);
                String name = mod.name;
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(name, (int) (baseCategoryWidth + 7.0 +5.0), yPos + 4, mod.isToggled() ? -1 : -4210753);
                
                if(mod.isToggled()){
                	R2DUtils.drawRect(baseCategoryWidth +  baseModWidth + 10 -4,  yPos , baseCategoryWidth +  baseModWidth + 9, yPos+14, Colors.getRainbow(0L, 1.0F).hashCode());
                }
                yPos += 14;
                yPosBottom += 14;
            }
        }
    }

	public static void keyPress(int key) {
        if (section == Section.CATEGORY) {
            switch (key) {
            case 205:
                int highestWidth = 0;
                for (Module module : getModsInCategory(Category.values()[categoryTab])) {
                    String name = StringUtil.capitalize(module.name.toLowerCase());
                    int stringWidth = (int) Minecraft.getMinecraft().fontRendererObj.getStringWidth(name);
                    highestWidth = Math.max(stringWidth, highestWidth);
                }
                baseModWidth = highestWidth + 6;
                baseModHeight = getModsInCategory(Category.values()[categoryTab]).size() * 14 + 2;
                modTargetPosition = modPosition = categoryTargetPosition;
                modTab = 0;
                section = Section.MODS;
                break;
            case 208:
                categoryTab += 1;
                categoryTargetPosition += 14;
                if (categoryTab <= Category.values().length - 1) {
                    break;
                }
                transitionQuickly = true;
                categoryTargetPosition = 21;
                categoryTab = 0;
                break;
            case 200:
                categoryTab -= 1;
                categoryTargetPosition -= 14;
                if (categoryTab >= 0) {
                    break;
                }
                transitionQuickly = true;
                categoryTargetPosition = 21 + (Category.values().length - 1) * 14;
                categoryTab = Category.values().length - 1;
            }
        } else if (section == Section.MODS) {
            switch (key) {
            case 203:
                section = Section.CATEGORY;
                break;
            case 205:
                Module mod = getModsInCategory(Category.values()[categoryTab]).get(modTab);
                mod.toggle();
                break;
            case 208:
                modTab += 1;
                modTargetPosition += 14;
                if (modTab > getModsInCategory(Category.values()[categoryTab]).size() - 1) {
                    transitionQuickly = true;
                    modTargetPosition = categoryTargetPosition;
                    modTab = 0;
                }
                break;
            case 200:
                modTab -= 1;
                modTargetPosition -= 14;
                if (modTab < 0) {
                    transitionQuickly = true;
                    modTargetPosition = categoryTargetPosition
                            + (getModsInCategory(Category.values()[categoryTab]).size() - 1) * 14;
                    modTab = getModsInCategory(Category.values()[categoryTab]).size() - 1;
                }
                break;
            }
        }
    }
 
    private static void updateBars() {
        long timeDifference = System.currentTimeMillis() - lastUpdateTime;
        lastUpdateTime = System.currentTimeMillis();
        int increment = transitionQuickly ? 100 : 20;
        increment = Math.max(1, Math.round(increment * timeDifference / 100L));
        if (categoryPosition < categoryTargetPosition) {
            categoryPosition += increment;
            if (categoryPosition >= categoryTargetPosition) {
                categoryPosition = categoryTargetPosition;
                transitionQuickly = false;
            }
        } else if (categoryPosition > categoryTargetPosition) {
            categoryPosition -= increment;
            if (categoryPosition <= categoryTargetPosition) {
                categoryPosition = categoryTargetPosition;
                transitionQuickly = false;
            }
        }
        if (modPosition < modTargetPosition) {
            modPosition += increment;
            if (modPosition >= modTargetPosition) {
                modPosition = modTargetPosition;
                transitionQuickly = false;
            }
        } else if (modPosition > modTargetPosition) {
            modPosition -= increment;
            if (modPosition <= modTargetPosition) {
                modPosition = modTargetPosition;
                transitionQuickly = false;
            }
        }
    }
 
    private static List<Module> getModsInCategory(Category Category) {
        List<Module> modList = new ArrayList<Module>();
        for (Module mod : getSortedModuleArray()) {
            if (mod.c == Category) {
                modList.add(mod);
            }
        }
        return modList;
    }
 
    private static List<Module> getSortedModuleArray() {
        final List<Module> list = new ArrayList<Module>();
        for (final Module mod : iClient.modules) {
            list.add(mod);
        }
        list.sort(new Comparator<Module>() {
            @Override
            public int compare(final Module m1, final Module m2) {
                final String s1 = m1.name;
                final String s2 = m2.name;
                final int cmp = (int) (Minecraft.getMinecraft().fontRendererObj.getStringWidth(s2)
                        - Minecraft.getMinecraft().fontRendererObj.getStringWidth(s1));
                return (cmp != 0) ? cmp : s2.compareTo(s1);
            }
        });
        return list;
    }
 
    private enum Section {
        CATEGORY("CATEGORY", 0), MODS("MODS", 1);
        private Section(String s, int n) {
        }
    }
    
    public static int baseCategoryHeight(){
    	return baseCategoryHeight;
    }
    
    public static int baseCategoryWidth(){
    	return baseCategoryWidth;
    }
}