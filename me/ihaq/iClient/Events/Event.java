package me.ihaq.iClient.Events;

import me.ihaq.iClient.Events.Events.MoveEvent;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;

public abstract class Event {
    public static float yaw;
    public static float pitch;
    public double y;
    private boolean onground;
    private boolean alwaysSend;
    private boolean cancelled;
    public Object state;
    public double x;

    public Packet getPacket() {
        return null;
    }

    public void onUpdate() {
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean state) {
        this.cancelled = state;
    }

    public void setY(double d) {
        MoveEvent.y = d;
    }

    public class EventEntity
    extends Event {
        private int entityID;
        private Entity entity;

        public EventEntity(int entityID, Entity entity) {
            this.entityID = entityID;
            this.entity = entity;
        }

        public int getEntityID() {
            return this.entityID;
        }

        public Entity getEntity() {
            return this.entity;
        }
    }
}

