package me.ihaq.iClient.gui;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.opengl.GL11;

import me.ihaq.iClient.iClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import me.ihaq.iClient.gui.TabGUI;
import me.ihaq.iClient.modules.Module;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import me.ihaq.iClient.utils.Colors;
import me.ihaq.iClient.utils.ComparatorStrings;
import me.ihaq.iClient.utils.R2DUtils;

public class InGameGUI extends GuiScreen {

	private ArrayList<String> mods = new ArrayList<String>();

	public InGameGUI() {
		TabGUI.init();
		this.mc = Minecraft.getMinecraft();
	}

	@SuppressWarnings("incomplete-switch")
	public void renderScreen() {
		
		Entity entity = this.mc.getRenderViewEntity();
		EnumFacing enumfacing = entity.getHorizontalFacing();
		String s = "Invalid";
		switch (enumfacing) {
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
		double playerX = Math.round(mc.thePlayer.posX * 100.0) / 100.0;
		double playerY = Math.round(mc.thePlayer.posY * 100.0) / 100.0;
		double playerZ = Math.round(mc.thePlayer.posZ * 100.0) / 100.0;

		drawRect(5, 80, TabGUI.baseCategoryWidth + 2 + 5, 121, -1610612736);
		drawRect(5, 80, 5 + 5, 121, Colors.getRainbow(0L, 1.0F).hashCode());
		mc.fontRendererObj.drawString("X: \u00A7f" + playerX, 6 + 6, 82, Colors.getRainbow(0L, 1.0F).hashCode());
		mc.fontRendererObj.drawString("Y: \u00A7f" + playerY, 6 + 6, 92, Colors.getRainbow(0L, 1.0F).hashCode());
		mc.fontRendererObj.drawString("Z: \u00A7f" + playerZ, 6 + 6, 102, Colors.getRainbow(0L, 1.0F).hashCode());
		mc.fontRendererObj.drawString("FPS: \u00A7f" + playerFPS, 6 + 6, 112, Colors.getRainbow(0L, 1.0F).hashCode());

		if (mc.ingameGUI.getChatGUI().getChatOpen()) {
			// YAY
		} else {
			//drawRect(0, GuiScreen.height - 20, GuiScreen.width, GuiScreen.height, 1610612736);
			
			drawRect(0, GuiScreen.height - 20, 5, GuiScreen.height, Colors.getRainbow(0L, 1.0F).hashCode());
			mc.fontRendererObj.drawString("Version: \u00A7f1.8", 7, GuiScreen.height - 9,
					Colors.getRainbow(0L, 1.0F).hashCode());
			mc.fontRendererObj.drawString("Ping: \u00A7f" + getPing() + "ms", 7, GuiScreen.height - 18,
					Colors.getRainbow(0L, 1.0F).hashCode());

			drawRect(GuiScreen.width - 5, GuiScreen.height - 20, GuiScreen.width, GuiScreen.height,
					Colors.getRainbow(0L, 1.0F).hashCode());
			SimpleDateFormat time_formatter_time = new SimpleDateFormat("HH:mm:ss");
			String current_time_str = time_formatter_time.format(System.currentTimeMillis());
			String time = "\u00A7f" + current_time_str;
			mc.fontRendererObj.drawString(time, GuiScreen.width - (mc.fontRendererObj.getStringWidth(time)) - 7,
					GuiScreen.height - 9, Colors.getRainbow(0L, 1.0F).hashCode());
			SimpleDateFormat time_formatter_date = new SimpleDateFormat("yyyy/MM/dd");
			String current_date_str = time_formatter_date.format(System.currentTimeMillis());
			String date = "\u00A7f" + current_date_str;
			mc.fontRendererObj.drawString(date, GuiScreen.width - (mc.fontRendererObj.getStringWidth(date)) - 7,
					GuiScreen.height - 18, Colors.getRainbow(0L, 1.0F).hashCode());

		}

		float scale = 2.0F;
		GL11.glScalef(scale, scale, scale);
		mc.fontRendererObj.drawString("i" + "\u00A7fClient", 3, 2, Colors.getRainbow(0L, 1.0F).hashCode());
		GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

		TabGUI.init();
		TabGUI.render();

		mods.clear();
		int count = 5;
		for (Module module : iClient.getModules()) {
			if (!module.isToggled())
				continue;
			mods.add(module.getName() + " " + module.getMode());
		}
		Collections.sort(mods, new ComparatorStrings());
		for (String m : mods) {
			mc.fontRendererObj.drawString(m, (GuiScreen.width - 5) - (mc.fontRendererObj.getStringWidth(m)), count,
					Colors.getRainbow(0L, 1.0F).hashCode());
			count += 10;
		}
	}

	public int getPing() {
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
