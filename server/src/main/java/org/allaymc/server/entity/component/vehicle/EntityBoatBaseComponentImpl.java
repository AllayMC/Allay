package org.allaymc.server.entity.component.vehicle;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityBoatBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.BoatVariant;
import org.allaymc.api.entity.data.EntityLinkType;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityBeforeTeleportEvent;
import org.allaymc.server.entity.component.event.CEntityDieEvent;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

/**
 * Base behavior for ordinary boats and bamboo rafts.
 */
public class EntityBoatBaseComponentImpl extends EntityBaseComponentImpl implements EntityBoatBaseComponent {
    protected static final String TAG_VARIANT = "Variant";
    protected static final int INPUT_EXPIRY_TICKS = 2;
    protected static final double MAX_HORIZONTAL_SPEED = 0.4;

    @ComponentObject
    protected EntityBoat boat;
    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    protected BoatVariant variant = BoatVariant.OAK;
    protected final List<EntityPlayer> passengers = new ArrayList<>(2);
    protected final Vector2f movementInput = new Vector2f();
    protected boolean paddleLeft;
    protected boolean paddleRight;
    protected float rowTimeLeft;
    protected float rowTimeRight;
    protected long lastInputTick = Long.MIN_VALUE;

    public EntityBoatBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.7, 0, -0.7, 0.7, 0.455, 0.7);
    }

    @Override
    public BoatVariant getBoatVariant() {
        return variant;
    }

    @Override
    public void setBoatVariant(BoatVariant variant) {
        this.variant = Objects.requireNonNull(variant, "variant");
        broadcastState();
        updatePassengerPositions();
    }

    @Override
    public float getRowTimeLeft() {
        return rowTimeLeft;
    }

    @Override
    public float getRowTimeRight() {
        return rowTimeRight;
    }

    @Override
    public boolean isPaddlingLeft() {
        return paddleLeft;
    }

    @Override
    public boolean isPaddlingRight() {
        return paddleRight;
    }

    @Override
    public int getPassengerCapacity() {
        return 2;
    }

    @Override
    public synchronized List<EntityPlayer> getPassengers() {
        return List.copyOf(passengers);
    }

    @Override
    public synchronized EntityPlayer getControllingPassenger() {
        return passengers.isEmpty() ? null : passengers.getFirst();
    }

    @Override
    public synchronized boolean addPassenger(EntityPlayer passenger) {
        if (passenger == null || passenger.getGameMode() == GameMode.SPECTATOR ||
            passenger.getDimension() != getDimension() || passenger.isRiding() ||
            passengers.contains(passenger) || passengers.size() >= getPassengerCapacity()) {
            return false;
        }

        passengers.add(passenger);
        passenger.setVehicle(boat);
        broadcastLink(passenger, passengers.size() == 1 ? EntityLinkType.RIDER : EntityLinkType.PASSENGER);
        broadcastState();
        updatePassengerPositions();
        return true;
    }

    @Override
    public synchronized boolean removePassenger(EntityPlayer passenger) {
        var index = passengers.indexOf(passenger);
        if (index < 0) {
            return false;
        }

        broadcastLink(passenger, EntityLinkType.REMOVE);
        passengers.remove(index);
        passenger.setVehicle(null);
        movePassengerToDismountPosition(passenger);

        if (index == 0 && !passengers.isEmpty()) {
            var promoted = passengers.getFirst();
            broadcastLink(promoted, EntityLinkType.REMOVE);
            broadcastLink(promoted, EntityLinkType.RIDER);
        }

        clearInput();
        broadcastState();
        updatePassengerPositions();
        return true;
    }

    @Override
    public synchronized void ejectPassengers() {
        for (var passenger : List.copyOf(passengers)) {
            removePassenger(passenger);
        }
    }

    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        return addPassenger(player);
    }

    @Override
    public synchronized void setPaddleInput(EntityPlayer player, Vector2fc movement, boolean paddleLeft, boolean paddleRight) {
        if (player != getControllingPassenger()) {
            return;
        }

        this.movementInput.set(movement == null ? new Vector2f() : movement);
        this.paddleLeft = paddleLeft;
        this.paddleRight = paddleRight;
        this.lastInputTick = getTick();
    }

    @EventHandler
    protected synchronized void onTick(CEntityTickEvent event) {
        if (getControllingPassenger() == null || getTick() - lastInputTick > INPUT_EXPIRY_TICKS) {
            clearInput();
        }

        applyControlInput();
        updateRowingAnimation();
    }

    protected void applyControlInput() {
        if (getControllingPassenger() == null) {
            return;
        }

        var forward = Math.clamp(movementInput.y(), -1f, 1f);
        var steer = Math.clamp(movementInput.x(), -1f, 1f);
        if (Math.abs(forward) < 0.001f && Math.abs(steer) < 0.001f) {
            if (paddleLeft && paddleRight) {
                forward = 1;
            } else if (paddleLeft != paddleRight) {
                steer = paddleRight ? 1 : -1;
            }
        }

        var location = new Location3d(getLocation());
        if (Math.abs(steer) > 0.001f) {
            location.yaw += steer;
            trySetLocation(location);
        }

        double acceleration = forward >= 0 ? 0.04 * forward : 0.02 * forward;
        if (Math.abs(forward) < 0.001f && paddleLeft != paddleRight) {
            acceleration = 0.005;
        }
        if (Math.abs(acceleration) < 0.00001) {
            return;
        }

        var yaw = Math.toRadians(location.yaw());
        var motion = physicsComponent.getMotion().add(
                -Math.sin(yaw) * acceleration,
                0,
                Math.cos(yaw) * acceleration,
                new Vector3d()
        );
        var horizontalSpeed = Math.hypot(motion.x(), motion.z());
        if (horizontalSpeed > MAX_HORIZONTAL_SPEED) {
            var scale = MAX_HORIZONTAL_SPEED / horizontalSpeed;
            motion.x *= scale;
            motion.z *= scale;
        }
        physicsComponent.setMotion(motion);
    }

    protected void updateRowingAnimation() {
        var oldLeft = rowTimeLeft;
        var oldRight = rowTimeRight;
        rowTimeLeft = paddleLeft ? rowTimeLeft + (float) (Math.PI / 8) : 0;
        rowTimeRight = paddleRight ? rowTimeRight + (float) (Math.PI / 8) : 0;
        if (Float.compare(oldLeft, rowTimeLeft) != 0 || Float.compare(oldRight, rowTimeRight) != 0) {
            broadcastState();
        }
    }

    protected void clearInput() {
        movementInput.zero();
        paddleLeft = false;
        paddleRight = false;
    }

    @Override
    public synchronized void updatePassengerPositions() {
        if (passengers.isEmpty()) {
            return;
        }

        for (int i = 0; i < passengers.size(); i++) {
            var localX = passengers.size() == 1 ? 0 : (i == 0 ? 0.2 : -0.6);
            var localY = variant.isRaft() ? 0.1 : -0.2;
            var yaw = Math.toRadians(getLocation().yaw());
            var x = getLocation().x() + localX * Math.cos(yaw);
            var z = getLocation().z() + localX * Math.sin(yaw);
            var passenger = passengers.get(i);
            passenger.trySetLocation(new Location3d(
                    x, getLocation().y() + localY, z,
                    passenger.getLocation().pitch(), getLocation().yaw() - 90,
                    getDimension()
            ));
        }
    }

    protected void movePassengerToDismountPosition(EntityPlayer passenger) {
        var yaw = Math.toRadians(getLocation().yaw());
        var sideX = Math.cos(yaw) * 1.5;
        var sideZ = Math.sin(yaw) * 1.5;
        var candidates = new double[][]{
                {getLocation().x() + sideX, getLocation().y(), getLocation().z() + sideZ},
                {getLocation().x() - sideX, getLocation().y(), getLocation().z() - sideZ},
                {getLocation().x() + sideZ, getLocation().y(), getLocation().z() - sideX},
                {getLocation().x() - sideZ, getLocation().y(), getLocation().z() + sideX}
        };
        for (var candidate : candidates) {
            var aabb = passenger.getAABB().translate(candidate[0], candidate[1], candidate[2], new AABBd());
            if (getDimension().getCollidingBlockStates(aabb) == null && !aabb.intersectsAABB(getOffsetAABB())) {
                passenger.trySetLocation(new Location3d(candidate[0], candidate[1], candidate[2],
                        passenger.getLocation().pitch(), passenger.getLocation().yaw(), getDimension()));
                return;
            }
        }
        passenger.trySetLocation(new Location3d(getLocation().x(), getLocation().y() + 0.6, getLocation().z(),
                passenger.getLocation().pitch(), passenger.getLocation().yaw(), getDimension()));
    }

    protected void broadcastLink(EntityPlayer passenger, EntityLinkType linkType) {
        var viewers = new LinkedHashSet<>(getViewers());
        if (passenger.getController() != null) {
            viewers.add(passenger.getController());
        }
        viewers.forEach(viewer -> viewer.viewEntityLink(boat, passenger, linkType));
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putInt(TAG_VARIANT, variant.getNetworkId());
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        if (event.getNbt().containsKey(TAG_VARIANT)) {
            var loaded = BoatVariant.fromNetworkId(event.getNbt().getInt(TAG_VARIANT));
            variant = loaded == null ? BoatVariant.OAK : loaded;
        }
    }

    @EventHandler
    protected void onBeforeTeleport(CEntityBeforeTeleportEvent event) {
        ejectPassengers();
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        ejectPassengers();
    }
}
