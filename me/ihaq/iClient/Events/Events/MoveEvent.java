
package me.ihaq.iClient.Events.Events;

import me.ihaq.iClient.Events.Event;

public class MoveEvent
extends Event {
    public static double x;
    public static double y;
    public static double z;

    public MoveEvent(double x, double y, double z) {
        MoveEvent.x = x;
        MoveEvent.y = y;
        MoveEvent.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        MoveEvent.x = x;
    }

    @Override
    public void setY(double y) {
        MoveEvent.y = y;
    }

    public void setZ(double z) {
        MoveEvent.z = z;
    }
}

