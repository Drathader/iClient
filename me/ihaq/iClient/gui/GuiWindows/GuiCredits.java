package me.ihaq.iClient.gui.GuiWindows;

import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.gui.GUIIButton;
import me.ihaq.iClient.utils.FontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class GuiCredits extends GuiScreen {
	private GuiMainMenu prevMenu;
    private final static FontUtils fu_mods = new FontUtils("Audiowide", Font.PLAIN, 18);
    private static final FontUtils fu_title =  new FontUtils("Audiowide", Font.PLAIN, 60);
    private static final FontUtils fu_sub_title =  new FontUtils("Audiowide", Font.PLAIN, 25);

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		fu_title.drawCenteredString( "CREDITS", this.width / 2, this.height / 2-86, -1);

		fu_sub_title.drawCenteredString( "Coder", this.width / 2, this.height / 2-48, -1);
		fu_mods.drawCenteredString( "iHaq - Skidding", this.width / 2, this.height / 2-32, -1);

		fu_sub_title.drawCenteredString( "Helpers", this.width / 2, this.height / 2-16 , -1);
		fu_mods.drawCenteredString( "XYZER - Menus", this.width / 2, this.height / 2, -1);
		fu_mods.drawCenteredString( "EaZyClient - Commands", this.width / 2, this.height / 2+11, -1);
		fu_mods.drawCenteredString( "ProMcHacks - RandomStuff", this.width / 2, this.height / 2+22, -1);
		fu_mods.drawCenteredString( "SwezedCode - Fix Font Error", this.width / 2, this.height / 2+33, -1);
		fu_mods.drawCenteredString( "Volcano - TABGUI & OutlineESP", this.width / 2, this.height / 2+43, -1);
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
