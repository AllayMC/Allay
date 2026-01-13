package org.allaymc.server.entity.component;

import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAreaEffectCloudBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;

/**
 * @author daoge_cmd
 */
public class EntityAreaEffectCloudBaseComponentImpl extends EntityBaseComponentImpl implements EntityAreaEffectCloudBaseComponent {

    protected static final float DEFAULT_RADIUS = 3.0f;
    protected static final int DEFAULT_DURATION = 600; // 30 seconds
    protected static final float DEFAULT_RADIUS_PER_TICK = -0.005f;
    protected static final float DEFAULT_RADIUS_ON_USE = -0.5f;
    protected static final int DEFAULT_REAPPLICATION_DELAY = 40; // 2 seconds
    protected static final float MIN_RADIUS = 0.5f;

    protected static final String TAG_RADIUS = "Radius";
    protected static final String TAG_DURATION = "Duration";
    protected static final String TAG_RADIUS_PER_TICK = "RadiusPerTick";
    protected static final String TAG_RADIUS_ON_USE = "RadiusOnUse";
    protected static final String TAG_REAPPLICATION_DELAY = "ReapplicationDelay";

    @Dependency
    protected EntityPotionComponent potionComponent;

    @Getter
    @Setter
    protected float radius = DEFAULT_RADIUS;

    @Getter
    @Setter
    protected int duration = DEFAULT_DURATION;

    @Getter
    @Setter
    protected float radiusPerTick = DEFAULT_RADIUS_PER_TICK;

    @Getter
    @Setter
    protected float radiusOnUse = DEFAULT_RADIUS_ON_USE;

    @Getter
    @Setter
    protected int reapplicationDelay = DEFAULT_REAPPLICATION_DELAY;

    // Maps entity runtime ID to the tick when effects can next be applied
    protected final Long2LongOpenHashMap affectedEntities = new Long2LongOpenHashMap();

    protected long ticksExisted = 0;

    public EntityAreaEffectCloudBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        ticksExisted++;

        // Don't apply effects for the first 0.5 seconds
        if (ticksExisted < 10) {
            return;
        }

        // Shrink radius every tick and broadcast state change
        if (radiusPerTick != 0) {
            radius += radiusPerTick;
            broadcastState();
        }

        if (radius < MIN_RADIUS) {
            thisEntity.remove();
            return;
        }

        // Decrease duration
        duration--;
        if (duration <= 0) {
            thisEntity.remove();
            return;
        }

        // Apply effects every 10 ticks (0.5 seconds)
        if (ticksExisted % 10 != 0) {
            return;
        }

        // Clean up expired cooldowns
        affectedEntities.long2LongEntrySet().removeIf(entry -> entry.getLongValue() <= ticksExisted);

        // Apply effects to entities within radius
        applyEffectsToNearbyEntities();
    }

    protected void applyEffectsToNearbyEntities() {
        var potionType = potionComponent.getPotionType();
        if (potionType == null) {
            return;
        }

        var effects = potionType.getEffects();
        if (effects.isEmpty()) {
            return;
        }

        var dimension = thisEntity.getDimension();
        var pos = thisEntity.getLocation();

        // Create a bounding box for detection
        var aabb = MathUtils.grow(new AABBd(thisEntity.getOffsetAABB()), new Vector3d(radius, 1.0, radius));
        var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb);

        boolean radiusChanged = false;

        for (var entity : entities) {
            if (!(entity instanceof EntityLiving living)) {
                continue;
            }

            // Check if entity is on cooldown
            if (affectedEntities.containsKey(entity.getRuntimeId())) {
                continue;
            }

            // Check horizontal distance (ignore Y)
            var entityPos = entity.getLocation();
            var deltaX = entityPos.x() - pos.x();
            var deltaZ = entityPos.z() - pos.z();
            var horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

            if (horizontalDistance > radius) {
                continue;
            }

            // Apply effects with 1/4 duration (except instant effects)
            for (var effect : effects) {
                var newEffect = new EffectInstance(effect);
                // Instant effects (duration <= 1) should not have duration divided
                if (effect.getDuration() > 1) {
                    newEffect.setDuration(effect.getDuration() / 4);
                }
                living.addEffect(newEffect);
            }

            // Set cooldown for this entity
            affectedEntities.put(entity.getRuntimeId(), ticksExisted + reapplicationDelay);

            // Shrink radius on use
            radius += radiusOnUse;
            radiusChanged = true;

            if (radius < MIN_RADIUS) {
                thisEntity.remove();
                return;
            }
        }

        if (radiusChanged) {
            broadcastState();
        }
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForFloat(TAG_RADIUS, v -> this.radius = v);
        nbt.listenForInt(TAG_DURATION, v -> this.duration = v);
        nbt.listenForFloat(TAG_RADIUS_PER_TICK, v -> this.radiusPerTick = v);
        nbt.listenForFloat(TAG_RADIUS_ON_USE, v -> this.radiusOnUse = v);
        nbt.listenForInt(TAG_REAPPLICATION_DELAY, v -> this.reapplicationDelay = v);
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        var nbt = event.getNbt();
        nbt.putFloat(TAG_RADIUS, radius);
        nbt.putInt(TAG_DURATION, duration);
        nbt.putFloat(TAG_RADIUS_PER_TICK, radiusPerTick);
        nbt.putFloat(TAG_RADIUS_ON_USE, radiusOnUse);
        nbt.putInt(TAG_REAPPLICATION_DELAY, reapplicationDelay);
    }
}
