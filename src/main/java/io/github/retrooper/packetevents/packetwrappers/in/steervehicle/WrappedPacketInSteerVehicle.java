/**
 * Copyright (c) 2020 retrooper
 */
package io.github.retrooper.packetevents.packetwrappers.in.steervehicle;

import io.github.retrooper.packetevents.packet.PacketTypeClasses;
import io.github.retrooper.packetevents.packetwrappers.api.WrappedPacket;
import io.github.retrooper.packetevents.reflectionutils.Reflection;

public class WrappedPacketInSteerVehicle extends WrappedPacket {
    private static Class<?> packetClass;
    private float side, forward;
    private boolean jump, unmount;
    public WrappedPacketInSteerVehicle(Object packet) {
        super(packet);
    }

    public static void load() {
        packetClass = PacketTypeClasses.Client.STEER_VEHICLE;
    }

    @Override
    protected void setup() {
        try {
            this.side = Reflection.getField(packetClass, float.class, 0).getFloat(packet);
            this.forward = Reflection.getField(packetClass, float.class, 1).getFloat(packet);

            this.jump = Reflection.getField(packetClass, boolean.class, 0).getBoolean(packet);
            this.unmount = Reflection.getField(packetClass, boolean.class, 1).getBoolean(packet);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //Positive side value means left, negative means right
    public float getSideValue() {
        return side;
    }

    //Positive forward value means forward, negative is backwards
    public float getForwardValue() {
        return forward;
    }

    public boolean isJump() {
        return jump;
    }

    public boolean isUnmount() {
        return unmount;
    }
}
