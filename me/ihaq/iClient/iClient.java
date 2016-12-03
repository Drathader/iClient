package me.ihaq.iClient;

import java.net.Proxy;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import me.ihaq.iClient.gui.InGameGUI;
import me.ihaq.iClient.gui.TabGUI;
import me.ihaq.iClient.gui.GuiWindows.GuiCredits;
import me.ihaq.iClient.module.Module;
import me.ihaq.iClient.modules.Flight;
import me.ihaq.iClient.modules.NoFall;
import me.ihaq.iClient.modules.Sprint;
import me.ihaq.iClient.modules.PlayerESP;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import me.ihaq.iClient.modules.Fullbright;
import me.ihaq.iClient.modules.MobESP;
import me.ihaq.iClient.modules.Step;
import me.ihaq.iClient.modules.Sucide;
import me.ihaq.iClient.modules.ChestESP;
import me.ihaq.iClient.modules.Damage;
import me.ihaq.iClient.modules.Jesus;
import me.ihaq.iClient.modules.KillAura;
import me.ihaq.iClient.modules.KillSpam;
import me.ihaq.iClient.modules.Respawn;
import me.ihaq.iClient.modules.Speed;
import me.ihaq.iClient.modules.Spider;


public class iClient {

	public static String Client_Name = "iClient";
	public static double Client_Version = 1.0;

	public static final iClient theClient = new iClient();
	public static ArrayList<Module> modules;
	private static InGameGUI inGameGUI;

	public static void StartClient() {
		modules = new ArrayList<Module>();
		
		//////////////////////////
		YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)service.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername("ole.zimmermann@gmx.de");
        auth.setPassword("ole2002");
        try {
        	auth.logIn();
            Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "legacy");
            //yay
        }
        catch (Exception e) {
        	//help
        }
        ///////////////////////////
        
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
        
        
		Display.setTitle(Client_Name + " | " + Client_Version + " | "+ "iHaq");
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
        for(Module module : getModules()) {
            if(module.getKeyCode() == keyCode) {
                module.toggle();
            }
        }
    }
   
    public static void onUpdate() {
        for(Module module : getModules()) {
            module.onUpdate();
        }
       
    }
   
    public static void onRender() {
        for(Module module : getModules()) {
            module.onRender();
        }
   
    }
}
