package me.ihaq.iClient.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

public class GUIIButton extends GuiButton{
	private int fad3;

    public GUIIButton(final int buttonId, final int x, final int y, final String buttonText){
        this(buttonId, x, y, 200, 20, buttonText);
    }

    public GUIIButton(final int buttonId,final int x,final int y,final int widthIn,final int heightIn,final String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY){
    	if (this.visible && !this.displayString.equals("HIDETHISBUTTON")) {
        	
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
                if(!hovered){
                    if(this.fad3 != 100){
                        this.fad3 += 5;
                    }
                }
                else{
                    if(this.fad3 <= 40){
                        return;
                    }
                    if(this.fad3 != 70){
                        this.fad3 -= 5;
                    }
                }
                final Color a = new Color(0, 0, 0,this.fad3);
                final FontRenderer var4 = mc.fontRendererObj;
                Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, a.getRGB());
                this.drawCenteredString(var4, this.displayString ,this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 0xffffffff);
        }
    }
}
