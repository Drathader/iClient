package me.ihaq.iClient.modules.Movement;

import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import me.ihaq.iClient.modules.Module.Category;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.Timer;


import java.util.List;
import me.ihaq.iClient.Events.Event;
import me.ihaq.iClient.Events.Events.EventLivingUpdate;
import me.ihaq.iClient.Events.Events.EventPlayerVelocity;
import me.ihaq.iClient.utils.BlockUtils;
import me.ihaq.iClient.utils.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;



public class LongJump extends Module{
	
	private static String mode;
    public static boolean active;
    boolean speedTick;
    int delay;
    int delay2;
    boolean hypickle2bigboostready = true;
    int hypickle2delayafterbigboost = 0;
    private double moveSpeed;
    private double lastDist;
    public static int stage;
    private double boost = 4.0;
    private TimerUtil time = new TimerUtil();
    private boolean move = true;
    private boolean canChangeMotion = false;
    private int airTicks;
    private int groundTicks;
    private float headStart;
    private double lastHDistance;
    private boolean isSpeeding;
    int timeonground;
    boolean off = false;
	
	public LongJump() {
		super("LongJump", Keyboard.KEY_F, Category.MOVEMENT, mode);
		setMode("\u00A7f[JUMPMAN]");
	}
	
	public void onUpdate(){
		if(!this.isToggled()){
			return;
		}	
		jumpMan();
	}
	
	public void jumpMan(){
        float zDir;
        float xDir;
        float zDir2;
        float xDir2;
        float direction;
            if (LongJump.mc.gameSettings.keyBindSneak.pressed) {
                return;
            }
            LongJump.mc.thePlayer.setSprinting(false);
            LongJump.mc.gameSettings.keyBindLeft.pressed = false;
            LongJump.mc.gameSettings.keyBindRight.pressed = false;
            LongJump.mc.gameSettings.keyBindBack.pressed = false;
            if (this.isMoving()) {
                if (Keyboard.isKeyDown((int)56)) {
                    this.updatePosition(0.0, 2.147483647E9, 0.0);
                }
                if (LongJump.mc.theWorld != null && LongJump.mc.thePlayer != null && LongJump.mc.thePlayer.onGround && !LongJump.mc.thePlayer.isDead) {
                    this.lastHDistance = 0.0;
                }
                float direction3 = LongJump.mc.thePlayer.rotationYaw + (float)(LongJump.mc.thePlayer.moveForward < 0.0f ? 180 : 0) + (LongJump.mc.thePlayer.moveStrafing > 0.0f ? -90.0f * (LongJump.mc.thePlayer.moveForward < 0.0f ? -0.5f : (LongJump.mc.thePlayer.moveForward > 0.0f ? 0.5f : 1.0f)) : 0.0f) - (LongJump.mc.thePlayer.moveStrafing < 0.0f ? -90.0f * (LongJump.mc.thePlayer.moveForward < 0.0f ? -0.5f : (LongJump.mc.thePlayer.moveForward > 0.0f ? 0.5f : 1.0f)) : 0.0f);
                xDir = (float)Math.cos((double)(direction3 + 90.0f) * 3.141592653589793 / 180.0);
                zDir = (float)Math.sin((double)(direction3 + 90.0f) * 3.141592653589793 / 180.0);
                if (!LongJump.mc.thePlayer.isCollidedVertically) {
                    ++this.airTicks;
                    this.isSpeeding = true;
                    if (LongJump.mc.gameSettings.keyBindSneak.isPressed()) {
                        LongJump.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(0.0, 2.147483647E9, 0.0, false));
                    }
                    this.groundTicks = 0;
                    if (!LongJump.mc.thePlayer.isCollidedVertically) {
                        if (LongJump.mc.thePlayer.motionY == -0.07190068807140403) {
                            LongJump.mc.thePlayer.motionY *= 0.3499999940395355;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.10306193759436909) {
                            LongJump.mc.thePlayer.motionY *= 0.550000011920929;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.13395038817442878) {
                            LongJump.mc.thePlayer.motionY *= 0.6700000166893005;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.16635183030382) {
                            LongJump.mc.thePlayer.motionY *= 0.6899999976158142;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.19088711097794803) {
                            LongJump.mc.thePlayer.motionY *= 0.7099999785423279;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.21121925191528862) {
                            LongJump.mc.thePlayer.motionY *= 0.20000000298023224;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.11979897632390576) {
                            LongJump.mc.thePlayer.motionY *= 0.9300000071525574;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.18758479151225355) {
                            LongJump.mc.thePlayer.motionY *= 0.7200000286102295;
                        }
                        if (LongJump.mc.thePlayer.motionY == -0.21075983825251726) {
                            LongJump.mc.thePlayer.motionY *= 0.7599999904632568;
                        }
                        if (this.getDistance(LongJump.mc.thePlayer, 69.0) < 0.5 && !BlockUtils.getBlock(new BlockPos(LongJump.mc.thePlayer.posX, LongJump.mc.thePlayer.posY - 0.32, LongJump.mc.thePlayer.posZ)).isFullCube()) {
                            if (LongJump.mc.thePlayer.motionY == -0.23537393014173347) {
                                LongJump.mc.thePlayer.motionY *= 0.029999999329447746;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08531999505205401) {
                                LongJump.mc.thePlayer.motionY *= -0.5;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.03659320313669756) {
                                LongJump.mc.thePlayer.motionY *= -0.10000000149011612;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.07481386749524899) {
                                LongJump.mc.thePlayer.motionY *= -0.07000000029802322;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.0732677700939672) {
                                LongJump.mc.thePlayer.motionY *= -0.05000000074505806;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.07480988066790395) {
                                LongJump.mc.thePlayer.motionY *= -0.03999999910593033;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.0784000015258789) {
                                LongJump.mc.thePlayer.motionY *= 0.10000000149011612;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08608320193943977) {
                                LongJump.mc.thePlayer.motionY *= 0.10000000149011612;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08683615560584318) {
                                LongJump.mc.thePlayer.motionY *= 0.05000000074505806;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08265497329678266) {
                                LongJump.mc.thePlayer.motionY *= 0.05000000074505806;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08245009535659828) {
                                LongJump.mc.thePlayer.motionY *= 0.05000000074505806;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08244005633718426) {
                                LongJump.mc.thePlayer.motionY = -0.08243956442521608;
                            }
                            if (LongJump.mc.thePlayer.motionY == -0.08243956442521608) {
                                LongJump.mc.thePlayer.motionY = -0.08244005590677261;
                            }
                            if (LongJump.mc.thePlayer.motionY > -0.1 && LongJump.mc.thePlayer.motionY < -0.08 && !LongJump.mc.thePlayer.onGround && LongJump.mc.gameSettings.keyBindForward.pressed) {
                                LongJump.mc.thePlayer.motionY = -9.999999747378752E-5;
                            }
                        } else {
                            if (LongJump.mc.thePlayer.motionY < -0.2 && LongJump.mc.thePlayer.motionY > -0.24) {
                                LongJump.mc.thePlayer.motionY *= 0.7;
                            }
                            if (LongJump.mc.thePlayer.motionY < -0.25 && LongJump.mc.thePlayer.motionY > -0.32) {
                                LongJump.mc.thePlayer.motionY *= 0.8;
                            }
                            if (LongJump.mc.thePlayer.motionY < -0.35 && LongJump.mc.thePlayer.motionY > -0.8) {
                                LongJump.mc.thePlayer.motionY *= 0.98;
                            }
                            if (LongJump.mc.thePlayer.motionY < -0.8 && LongJump.mc.thePlayer.motionY > -1.6) {
                                LongJump.mc.thePlayer.motionY *= 0.99;
                            }
                        }
                    }
                    Timer.timerSpeed = 0.85f;
                    double[] speedVals = new double[]{0.420606, 0.417924, 0.415258, 0.412609, 0.409977, 0.407361, 0.404761, 0.402178, 0.399611, 0.39706, 0.394525, 0.392, 0.3894, 0.38644, 0.383655, 0.381105, 0.37867, 0.37625, 0.37384, 0.37145, 0.369, 0.3666, 0.3642, 0.3618, 0.35945, 0.357, 0.354, 0.351, 0.348, 0.345, 0.342, 0.339, 0.336, 0.333, 0.33, 0.327, 0.324, 0.321, 0.318, 0.315, 0.312, 0.309, 0.307, 0.305, 0.303, 0.3, 0.297, 0.295, 0.293, 0.291, 0.289, 0.287, 0.285, 0.283, 0.281, 0.279, 0.277, 0.275, 0.273, 0.271, 0.269, 0.267, 0.265, 0.263, 0.261, 0.259, 0.257, 0.255, 0.253, 0.251, 0.249, 0.247, 0.245, 0.243, 0.241, 0.239, 0.237};
                    if (LongJump.mc.gameSettings.keyBindForward.pressed) {
                        try {
                            LongJump.mc.thePlayer.motionX = (double)xDir * speedVals[this.airTicks - 1] * 3.0 * this.addSpeedForSpeedEffect();
                            LongJump.mc.thePlayer.motionZ = (double)zDir * speedVals[this.airTicks - 1] * 3.0 * this.addSpeedForSpeedEffect();
                            this.off = true;
                        }
                        catch (ArrayIndexOutOfBoundsException var6_12) {}
                    } else {
                        LongJump.mc.thePlayer.motionX = 0.0;
                        LongJump.mc.thePlayer.motionZ = 0.0;
                    }
                } else {
                    Timer.timerSpeed = 1.0f;
                    this.airTicks = 0;
                    ++this.groundTicks;
                    this.headStart -= 1.0f;
                    LongJump.mc.thePlayer.motionX /= 13.0;
                    LongJump.mc.thePlayer.motionZ /= 13.0;
                    if (this.groundTicks == 1) {
                        this.updatePosition(LongJump.mc.thePlayer.posX, LongJump.mc.thePlayer.posY, LongJump.mc.thePlayer.posZ);
                        this.updatePosition(LongJump.mc.thePlayer.posX + 0.0624, LongJump.mc.thePlayer.posY, LongJump.mc.thePlayer.posZ);
                        this.updatePosition(LongJump.mc.thePlayer.posX, LongJump.mc.thePlayer.posY + 0.419, LongJump.mc.thePlayer.posZ);
                        this.updatePosition(LongJump.mc.thePlayer.posX + 0.0624, LongJump.mc.thePlayer.posY, LongJump.mc.thePlayer.posZ);
                        this.updatePosition(LongJump.mc.thePlayer.posX, LongJump.mc.thePlayer.posY + 0.419, LongJump.mc.thePlayer.posZ);
                    }
                    if (this.groundTicks > 2) {
                        this.groundTicks = 0;
                        LongJump.mc.thePlayer.motionX = (double)xDir * 0.3;
                        LongJump.mc.thePlayer.motionZ = (double)zDir * 0.3;
                        LongJump.mc.thePlayer.motionY = 0.42399999499320984;
                    }
                }
            }
        }
	
    private double addSpeedForSpeedEffect() {
        double baseSpeed = 1.0;
        if (LongJump.mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            int amplifier = LongJump.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed = 0.4;
        }
        return baseSpeed;
    }

    public void updatePosition(double x, double y, double z) {
        LongJump.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, LongJump.mc.thePlayer.onGround));
    }

    private double getDistance(EntityPlayer player, double distance) {
        List boundingBoxes = player.worldObj.getCollidingBoundingBoxes(player, player.getEntityBoundingBox().addCoord(0.0, - distance, 0.0));
        if (boundingBoxes.isEmpty()) {
            return 0.0;
        }
        double y = 0.0;
        /*
        for (AxisAlignedBB boundingBox : boundingBoxes) {
            if (boundingBox.maxY <= y) continue;
            y = boundingBox.maxY;
        }
        */
        return player.posY - y;
    }

    public boolean isMoving() {
        if (LongJump.mc.thePlayer.moveForward == 0.0f && LongJump.mc.thePlayer.moveStrafing == 0.0f) {
            return false;
        }
        return true;
    }

    @Override
    public void onEnable() {
        active = true;
        this.speedTick = false;
        this.canChangeMotion = true;
        stage = 0;
        this.off = false;
        Timer.timerSpeed = 1.0f;
    }

    @Override
    public void onDisable() {
        active = false;
        Timer.timerSpeed = 1.0f;
    }
}

