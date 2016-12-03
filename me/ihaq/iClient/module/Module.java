package me.ihaq.iClient.module;

import net.minecraft.client.Minecraft;

 
public class Module {
 
    protected static Minecraft mc = Minecraft.getMinecraft();
   
    public String name;
    private int keyCode;
    public Category c;
    private boolean toggled;
    public String mode;
   

    
    public Module(String name, int keyCode, Category c, String m) {
        this.name = name;
        this.keyCode = keyCode;
        this.c = c;
        this.toggled = false;
        this.mode = m;
    }
   
 

	public void toggle() {
        this.toggled = !this.toggled;
        if(this.toggled) {
            onEnable();
        }else {
            onDisable();
            }
        }
   
    public void onEnable() { }
   
    public void onDisable() { }
   
    public void onUpdate() { }
   
    public void onRender() { }
    
    public enum Category {
  	  COMBAT, MOVEMENT, RENDER, MISC
    }
   
    public String getName() {
        return this.name;
    }
   
    public void setName(String name) {
        this.name = name;
    }
   
    public int getKeyCode() {
        return this.keyCode;
    }
   
    public void setKeyCode(int keycode) {
        this.keyCode = keycode;
    }
   
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
    
    public void disable() {
        this.toggled = false;
    }
   
    public boolean isToggled() {
        return this.toggled;
    }
    
    public String getMode() {
        return this.mode;
    }
    
    public void setMode(String m) {
        this.mode = m;
    }
   
}