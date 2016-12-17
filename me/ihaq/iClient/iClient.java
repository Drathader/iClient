package me.ihaq.iClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.opengl.Display;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import me.ihaq.iClient.gui.InGameGUI;
import me.ihaq.iClient.gui.TabGUI;
import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Combat.*;
import me.ihaq.iClient.modules.Misc.*;
import me.ihaq.iClient.modules.Movement.*;
import me.ihaq.iClient.modules.Render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class iClient {

	public static String Client_Name = "iClient";
	public static double Client_Version = 1.0;

	public static final iClient theClient = new iClient();
	public static ArrayList<Module> modules;
	private static InGameGUI inGameGUI;

	public static void StartClient() {
		modules = new ArrayList<Module>();

		registerModule(new NoFall());
		registerModule(new Flight());
		registerModule(new Sprint());
		registerModule(new Fullbright());
		registerModule(new PlayerESP());
		registerModule(new MobESP());
		registerModule(new Step());
		registerModule(new ChestESP());
		registerModule(new Jesus());
		registerModule(new Respawn());
		registerModule(new Speed());
		registerModule(new Spider());
		registerModule(new KillSpam());
		registerModule(new Damage());
		registerModule(new Sucide());
		registerModule(new KillAura());
		registerModule(new Criticals());
		registerModule(new LongJump());
		registerModule(new AntiBot());
		registerModule(new AutoArmor());
		registerModule(new InventoryMove());
		registerModule(new AirJump());
		registerModule(new NoSlowDown());
		
		//////////////////////////
		YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
		YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service
				.createUserAuthentication(Agent.MINECRAFT);
		auth.setUsername("grantbmills@gmail.com");
		auth.setPassword("01011001Y");
		try {
			auth.logIn();
			Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(),
					auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
			// yay
		} catch (Exception e) {
			// help
		}
		///////////////////////////

		Display.setTitle(Client_Name + " | " + Client_Version + " | " + "iHaq");
		inGameGUI = new InGameGUI();
	}

	public static void registerModule(Module module) {
		modules.add(module);
	}

	public static ArrayList<Module> getModules() {
		return modules;
	}

	public static InGameGUI getInGameGUI() {
		return inGameGUI;
	}

	public static void onKeyPressed(int keyCode) {
		TabGUI.keyPress(keyCode);
		for (Module module : getModules()) {
			if (module.getKeyCode() == keyCode) {
				module.toggle();
			}
		}
	}

	public static void onUpdate() {
		for (Module module : getModules()) {
			module.onUpdate();
		}

	}

	public static void onRender() {
		for (Module module : getModules()) {
			module.onRender();
		}

	}
}
