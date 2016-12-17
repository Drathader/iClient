package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.Events.Event;
import me.ihaq.iClient.Events.Events.EventLivingUpdate;
import me.ihaq.iClient.Events.Events.EventSlowDown;
import me.ihaq.iClient.Events.Events.ItemSpeedEvent;
import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Module.Category;
import me.ihaq.iClient.utils.BlockUtils;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovementInput;

public class NoSlowDown extends Module {
    public static boolean active;

    public NoSlowDown() {
    	super("NoSlowDown", Keyboard.KEY_NONE, Category.MOVEMENT, "");
    }

    private void onItemUse(ItemSpeedEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void onUpdate() {
       
        if(!this.isToggled())
            return;
        
        NoSlowDown.mc.thePlayer.setSprinting(true);
        MovementInput movementInput = NoSlowDown.mc.thePlayer.movementInput;
        float forward = MovementInput.moveForward;
        float strafe = MovementInput.moveStrafe;
        float yaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
        if (forward == 0.0f && strafe == 0.0f) {
            strafe = 0.0f;
            forward = 0.0f;
        } else if (forward != 0.0f) {
            if (strafe >= 1.0f) {
                yaw += (float)(forward > 0.0f ? -45 : 45);
                strafe = 0.0f;
            } else if (strafe <= -1.0f) {
                yaw += (float)(forward > 0.0f ? 45 : -45);
                strafe = 0.0f;
            }
            if (forward > 0.0f) {
                forward = 1.0f;
            } else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        double moveSpeed = this.getBaseMoveSpeed();
        double mx = Math.cos(Math.toRadians(yaw + 90.0f));
        double mz = Math.sin(Math.toRadians(yaw + 90.0f));
        double motionX = (double)forward * moveSpeed * mx + (double)strafe * moveSpeed * mz;
        double motionZ = (double)forward * moveSpeed * mz - (double)strafe * moveSpeed * mx;
        NoSlowDown.mc.thePlayer.motionX = (double)forward * moveSpeed * mx + (double)strafe * moveSpeed * mz;
        NoSlowDown.mc.thePlayer.motionZ = (double)forward * moveSpeed * mz - (double)strafe * moveSpeed * mx;
    }

    @Override
    public void onEnable() {
        active = true;
    }

    @Override
    public void onDisable() {
        active = false;
    }

    private double getBaseMoveSpeed() {
        double baseSpeed = 0.16;
        if (NoSlowDown.mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            int amplifier = NoSlowDown.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (double)(amplifier + 1);
        }
        return baseSpeed;
    }

    public static boolean SlowDown() {
        boolean ShouldNoSlow = false;
        if (BlockUtils.getBlockAtPosC(NoSlowDown.mc.thePlayer, 0.30000001192092896, 0.10000000149011612, 0.30000001192092896) instanceof BlockSoulSand && BlockUtils.getBlockAtPosC(NoSlowDown.mc.thePlayer, -0.30000001192092896, 0.10000000149011612, -0.30000001192092896) instanceof BlockSoulSand) {
            ShouldNoSlow = true;
        }
        if (NoSlowDown.mc.thePlayer.isInWeb) {
            ShouldNoSlow = true;
        }
        if (NoSlowDown.mc.thePlayer.isInWater()) {
            ShouldNoSlow = false;
        }
        return ShouldNoSlow;
    }
}

