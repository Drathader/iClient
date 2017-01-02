package me.ihaq.iClient.modules.Combat;

import java.util.Timer;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

import me.ihaq.iClient.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

import java.util.Iterator;
import java.util.List;

public class KillAura extends Module {

	private static String mode;
	int triggerDelay;

	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.COMBAT, mode);
	}

	@Override
	public void onUpdate() {

		if (!this.isToggled())
			return;

		triggerBot();
	}

	public void triggerBot() {
		setMode("\u00A7f[TRIGGERBOT]");
		List list = mc.theWorld.playerEntities;
		
		for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
			Object theObject = entities.next();
			if (theObject instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) theObject;
				
				if (entity instanceof EntityPlayerSP) {
					continue;
				}

				if (mc.objectMouseOver.entityHit == null) {
					continue;
				}
				if (mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
					continue;
				}
				
				if(!entity.equals(mc.objectMouseOver.entityHit)){
					continue;					
				}
				
				if (mc.thePlayer.getDistanceToEntity(entity) <= 4.5F && triggerDelay > 3) {
					if (entity.isEntityAlive()) {
						
						if (Criticals.getCriticalsMode().equals("JUMP") && Criticals.active == true) {
							if (mc.thePlayer.onGround) {
								mc.thePlayer.jump();
							}
							//mc.playerController.attackEntity(mc.thePlayer, entity);
							mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity,C02PacketUseEntity.Action.ATTACK));
							mc.thePlayer.swingItem();
							triggerDelay = 0;
							continue;
						} else if (Criticals.getCriticalsMode().equals("PACKETS") && Criticals.active == true) {
							Criticals.packets();
							//mc.playerController.attackEntity(mc.thePlayer, entity);
							mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity,C02PacketUseEntity.Action.ATTACK));
							mc.thePlayer.swingItem();
							triggerDelay = 0;
							continue;
						} else {
							//mc.playerController.attackEntity(mc.thePlayer, entity);
							mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity,C02PacketUseEntity.Action.ATTACK));
							mc.thePlayer.swingItem();
							triggerDelay = 0;
							continue;
						}
					}
				}
				triggerDelay++;
			}
		}
		super.onUpdate();
	}
}
