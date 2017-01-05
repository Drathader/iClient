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
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.util.Session;
import com.mojang.authlib.Agent;
import java.net.Proxy;

public class GuiAltManager extends GuiScreen {
	  
	public GuiScreen parent;
	  public GuiTextField usernameBox;
	  public GuiTextField passwordBox;
	  public GuiTextField sessionBox;
	  
	private GuiMainMenu prevMenu;
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		
		ScaledResolution scaledRes = new ScaledResolution(this.mc);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/back1.jpg"));
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0f, 0.0f, scaledRes.getScaledWidth(), scaledRes.getScaledHeight(),
				scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), scaledRes.getScaledWidth(),
				scaledRes.getScaledHeight());
		
        int size = 40;
        String name = Minecraft.getMinecraft().getSession().getUsername();
        try {
			AbstractClientPlayer.getDownloadImageSkin(AbstractClientPlayer.getLocationSkin(name), name).loadTexture(Minecraft.getMinecraft().getResourceManager());
			Minecraft.getMinecraft().getTextureManager().bindTexture(AbstractClientPlayer.getLocationSkin(name));
			GL11.glColor4f(1F, 1F, 1F, 1F);
			Gui.drawScaledCustomSizeModalRect(width / 2 - 100, (this.height / 4-86+20), 8, 8, 8, 8, size, size, 64, 64);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float scale = 2.0F;
        GL11.glScalef(scale, scale, scale);
		drawString(Minecraft.getMinecraft().fontRendererObj, "Logged in As:", (int) ((this.width / 2 -55)/scale), (int) ((this.height / 4-86+24)/scale), -1);
		drawString(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getSession().getUsername(), (int) ((this.width / 2 -55)/scale), (int) ((this.height / 4-86 + 44)/scale), Colors.getRainbow(0L, 1.0F).hashCode());
		GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
		
		
	    drawString(Minecraft.getMinecraft().fontRendererObj, "Username", width / 2 - 100, height/2-100, -1);
	    drawString(Minecraft.getMinecraft().fontRendererObj, "Password", width / 2 - 100, height/2-50, -1);   
	    
        this.usernameBox.drawTextBox();
        this.passwordBox.drawTextBox();

	
		super.drawScreen(par1, par2, par3);
	}

	public GuiAltManager(GuiMainMenu prevMultiplayerMenu) {
		this.prevMenu = prevMultiplayerMenu;
	}

	@Override
	public void updateScreen() {
	       super.updateScreen();
	       this.usernameBox.updateCursorCounter();
	       this.passwordBox.updateCursorCounter();
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		
		this.buttonList.add(new GUIIButton(100, this.width / 2 - 100, (this.height /2 + 70), "Back"));
		this.buttonList.add(new GUIIButton(101, this.width / 2 - 100, (this.height /2 + 70-22), 98, 20, "Login"));
		this.buttonList.add(new GUIIButton(102, this.width / 2 , (this.height /2 + 70-22), 98, 20, "Clear"));
		
	    this.usernameBox = new GuiTextField(1, this.fontRendererObj, width / 2 - 100, height/2-100 +20, 200, 20);
        usernameBox.setMaxStringLength(23);
        usernameBox.setText("");
        this.usernameBox.setFocused(false);
        
	    this.passwordBox = new GuiTextField(2, this.fontRendererObj, width / 2 - 100 , height/2-50 +20, 200, 20);
	    passwordBox.setMaxStringLength(23);
	    passwordBox.setText("");
        this.passwordBox.setFocused(false);
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
			} else if (clickedButton.id == 101) {
				String username = usernameBox.getText(), password = passwordBox.getText();
				if (username.length() < 1 || password.length() < 1) {
					// Draw a string or something saying you need to input a password and username...
					return;	
				}
				
				try {
				YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
				YggdrasilUserAuthentication auth = new YggdrasilUserAuthentication(service, Agent.MINECRAFT);
				auth.setUsername(username);
				auth.setPassword(password);
				auth.logIn();
				minecraft.session = (new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), auth.getUserType().getName()));
				} catch (AuthenticationException e) {
					// Draw an erorr string or something...
				}


			} else if(clickedButton.id == 102){ //clear
				usernameBox.setText("");
				passwordBox.setText("");
			}
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) throws IOException {
		if (par2 == 28 || par2 == 156) {
			this.actionPerformed((GuiButton) this.buttonList.get(0));
		}
		if (par2 == 1) {
			this.mc.displayGuiScreen(new GuiMainMenu());
		}
		
		super.keyTyped(par1, par2);
        this.usernameBox.textboxKeyTyped(par1, par2);
        this.passwordBox.textboxKeyTyped(par1, par2);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException {
		super.mouseClicked(par1, par2, par3);
        this.usernameBox.mouseClicked(par1, par2, par3);
        this.passwordBox.mouseClicked(par1, par2, par3);
	}

}
