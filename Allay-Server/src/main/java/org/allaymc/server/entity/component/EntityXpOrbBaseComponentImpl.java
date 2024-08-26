package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.entity.component.event.CEntityTryDamageEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

/**
 * Allay Project 2024/8/12
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityXpOrbBaseComponentImpl extends EntityPickableBaseComponentImpl implements EntityXpOrbBaseComponent {

    public static float MAX_MOVE_DISTANCE = 7.25f;
    public static float MAX_MOVE_DISTANCE_SQUARED = MAX_MOVE_DISTANCE * MAX_MOVE_DISTANCE;

    protected int experienceValue;

    public EntityXpOrbBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        moveToNearestPlayer();
    }

    @Override
    public void onCollideWith(Entity other) {
        if (experienceValue == 0) return;
        if (!canBePicked()) return;
        if (!(other instanceof EntityPlayer player)) return;

        player.addExperience(experienceValue);
        experienceValue = 0;
        despawn();
    }

    protected void moveToNearestPlayer() {
        var nearestPlayer = findNearestPlayer();
        if (nearestPlayer == null) {
            return;
        }

        var playerLoc = nearestPlayer.getLocation();
        float dX = (playerLoc.x() - this.location.x) / 8.0f;
        float dY = (playerLoc.y() + nearestPlayer.getEyeHeight() / 2.0f - this.location.y) / 8.0f;
        float dZ = (playerLoc.z() - this.location.z) / 8.0f;
        float d = (float) Math.sqrt(dX * dX + dY * dY + dZ * dZ);
        float diff = 1.0f - d;

        if (diff > 0.0D) {
            diff = diff * diff;
            addMotion(
                    dX / d * diff * 0.1f,
                    dY / d * diff * 0.1f,
                    dZ / d * diff * 0.1f
            );
        }
    }

    protected EntityPlayer findNearestPlayer() {
        EntityPlayer nearestPlayer = null;
        float nearestDistanceSquared = MAX_MOVE_DISTANCE_SQUARED;
        for (var player : getDimension().getPlayers()) {
            var distanceSquared = player.getLocation().distanceSquared(location);
            if (distanceSquared < nearestDistanceSquared) {
                nearestPlayer = player;
            }
        }
        return nearestPlayer;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForInt("ExperienceValue", experienceValue -> this.experienceValue = experienceValue);

        setAndSendEntityData(EntityDataTypes.VALUE, experienceValue);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        builder.putInt("ExperienceValue", experienceValue);

        return builder.build();
    }

    @Override
    public AABBfc getAABB() {
        return new AABBf(-0.05f, 0.0f, -0.05f, 0.05f, 0.1f, 0.05f);
    }

    @EventHandler
    protected void onDamage(CEntityTryDamageEvent event) {
        var damageType = event.getDamage().getDamageType();
        event.setCanAttack(damageType != DamageContainer.DamageType.FALL);
    }
}
