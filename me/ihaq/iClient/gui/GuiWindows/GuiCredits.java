package me.ihaq.iClient.gui.GuiWindows;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.gui.GUIIButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class GuiCredits extends GuiScreen {
	private GuiMainMenu prevMenu;

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "CREDITS", this.width / 2, this.height / 2 - 70, -1);

		this.drawCenteredString(this.fontRendererObj, "§nCoder", this.width / 2, this.height / 2 - 50, -1);
		this.drawCenteredString(this.fontRendererObj, "EaZyCode", this.width / 2, this.height / 2 - 40, -1);

		this.drawCenteredString(this.fontRendererObj, "§nCo-Coder", this.width / 2, this.height / 2 - 20, -1);
		this.drawCenteredString(this.fontRendererObj, "XYZERTUBE", this.width / 2, this.height / 2 - 10, -1);

		this.drawCenteredString(this.fontRendererObj, "§nHelpers", this.width / 2, this.height / 2 + 10, -1);
		this.drawCenteredString(this.fontRendererObj, "Luca", this.width / 2, this.height / 2 + 20, -1);
		this.drawCenteredString(this.fontRendererObj, "ShiroYT", this.width / 2, this.height / 2 + 30, -1);
		this.drawCenteredString(this.fontRendererObj, "Dummkopf.json", this.width / 2, this.height / 2 + 40, -1);
		this.drawCenteredString(this.fontRendererObj, "-> YouTube <-", this.width / 2, this.height / 2 + 50, -1);
		this.drawCenteredString(this.fontRendererObj, "-> Twitter <-", this.width / 2, this.height / 2 + 60, -1);
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
		this.buttonList.add(new GUIIButton(100, this.width / 2 - 100, (this.height - 150), "BACK"));
		this.buttonList.add(new GUIIButton(101, this.width / 2 - 38, 148, 75, 11, "HIDETHISBUTTON"));
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
