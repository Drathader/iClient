package me.ihaq.iClient.gui.GuiWindows;

import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.ihaq.iClient.gui.GUIIButton;
import me.ihaq.iClient.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiCredits extends GuiScreen {
	private GuiMainMenu prevMenu;

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		ScaledResolution scaledRes = new ScaledResolution(this.mc);
		//GlStateManager.color(1.0f, 0.85f, 0.85f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/back1.jpg"));
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0f, 0.0f, scaledRes.getScaledWidth(), scaledRes.getScaledHeight(),
				scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), scaledRes.getScaledWidth(),
				scaledRes.getScaledHeight());
		
		float scale = 2.0F;
        GL11.glScalef(scale, scale, scale);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "CREDITS", (int) ((this.width / 2)/scale), (int) ((this.height / 2-86)/scale), -1);
		GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
		
		float scale1 = 1.5F;
        GL11.glScalef(scale1, scale1, scale1);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Coder", (int)((this.width / 2)/scale1), (int) ((this.height / 2-48)/scale1), -1);
		GL11.glScalef(1.0F / scale1, 1.0F / scale1, 1.0F / scale1);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "iHaq - Skidding", this.width / 2, this.height / 2-32, -1);
		
		float scale2 = 1.5F;
        GL11.glScalef(scale2, scale2, scale2);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Helpers", (int)((this.width / 2)/scale2), (int) ((this.height / 2-16)/scale2) , -1);
		GL11.glScalef(1.0F / scale2, 1.0F / scale2, 1.0F / scale2);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "XYZER - Menus", this.width / 2, this.height / 2, -1);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "EaZyClient - Commands", this.width / 2, this.height / 2+11, -1);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "ProMcHacks - RandomStuff", this.width / 2, this.height / 2+22, -1);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "SwezedCode - Fix Font Error", this.width / 2, this.height / 2+33, -1);
		drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Volcano - TABGUI & OutlineESP", this.width / 2, this.height / 2+43, -1);
		super.drawScreen(par1, par2, par3);
	}

	public GuiCredits(GuiMainMenu prevMultiplayerMenu) {
		this.prevMenu = prevMultiplayerMenu;
	}

	@Override
	public void updateScreen() {
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(new GUIIButton(100, this.width / 2 - 100, (this.height /2 + 90), "Back"));
		this.buttonList.add(new GUIIButton(101, this.width / 2 - 100, (this.height /2 + 90-22), 98, 20, "Youtube"));
		this.buttonList.add(new GUIIButton(100, this.width / 2 , (this.height /2 + 90-22), 98, 20, "Twitter"));
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton clickedButton) {
		if (clickedButton.enabled) {
			if (clickedButton.id == 100) {
				mc.displayGuiScreen(this.prevMenu);
			} else if (clickedButton.id == 101) { // YouTube

				try {
					Desktop.getDesktop().browse(new URI("https://youtube.com/iHaq?sub_confirmation=1"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (par2 == 28 || par2 == 156) {
			this.actionPerformed((GuiButton) this.buttonList.get(0));
		}
		if (par2 == 1) {
			this.mc.displayGuiScreen(new GuiMainMenu());
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException {
		super.mouseClicked(par1, par2, par3);
	}

}
