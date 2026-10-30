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
 * {@code EntityAreaEffectCloudBaseComponentImpl} manages an area effect
 * cloud's radius, active lifetime, and repeated potion applications. The cloud
 * waits until its tenth entity tick before changing radius or duration, then
 * checks for effect targets every ten ticks. Radius changes can remove the
 * cloud before its duration expires.
 *
 * <p> {@code affectedEntities} records each target's next eligible cloud tick,
 * so overlapping visits do not immediately reapply effects. Each accepted
 * target changes the radius before later candidates are checked, making the
 * remaining cloud size relevant to that same pass. The persisted radius and
 * duration settings survive saves, while per-target cooldowns and
 * {@code ticksExisted} are not serialized by this component.
 *
 * @author daoge_cmd
 */
public class EntityAreaEffectCloudBaseComponentImpl extends EntityBaseComponentImpl implements EntityAreaEffectCloudBaseComponent {

    // DEFAULT_RADIUS, DEFAULT_DURATION, DEFAULT_RADIUS_PER_TICK,
    // DEFAULT_RADIUS_ON_USE, and DEFAULT_REAPPLICATION_DELAY initialize a new
    // cloud's persisted settings. MIN_RADIUS removes clouds too small to retain.

    /**
     * Initial cloud radius in blocks.
     */
    protected static final float DEFAULT_RADIUS = 3.0f;
    /**
     * Initial active lifetime in ticks.
     */
    protected static final int DEFAULT_DURATION = 600; // 30 seconds
    /**
     * Initial radius change in blocks per active tick.
     */
    protected static final float DEFAULT_RADIUS_PER_TICK = -0.005f;
    /**
     * Initial radius change in blocks per affected entity.
     */
    protected static final float DEFAULT_RADIUS_ON_USE = -0.5f;
    /**
     * Initial per-target effect reuse delay in ticks.
     */
    protected static final int DEFAULT_REAPPLICATION_DELAY = 40; // 2 seconds
    /**
     * Minimum retained radius in blocks.
     */
    protected static final float MIN_RADIUS = 0.5f;

    // TAG_RADIUS, TAG_DURATION, TAG_RADIUS_PER_TICK, TAG_RADIUS_ON_USE, and
    // TAG_REAPPLICATION_DELAY name the settings shared by the cloud NBT handlers.

    /**
     * NBT key for the current cloud radius.
     */
    protected static final String TAG_RADIUS = "Radius";
    /**
     * NBT key for the remaining active lifetime.
     */
    protected static final String TAG_DURATION = "Duration";
    /**
     * NBT key for radius change per active tick.
     */
    protected static final String TAG_RADIUS_PER_TICK = "RadiusPerTick";
    /**
     * NBT key for radius change per affected entity.
     */
    protected static final String TAG_RADIUS_ON_USE = "RadiusOnUse";
    /**
     * NBT key for the per-target reuse delay.
     */
    protected static final String TAG_REAPPLICATION_DELAY = "ReapplicationDelay";

    /**
     * Potion component supplying source effects for cloud applications.
     */
    @Dependency
    protected EntityPotionComponent potionComponent;

    /**
     * Current cloud radius measured in blocks.
     */
    @Getter
    @Setter
    protected float radius = DEFAULT_RADIUS;

    /**
     * Remaining active lifetime measured in entity ticks.
     */
    @Getter
    @Setter
    protected int duration = DEFAULT_DURATION;

    /**
     * Radius change in blocks per active tick.
     */
    @Getter
    @Setter
    protected float radiusPerTick = DEFAULT_RADIUS_PER_TICK;

    /**
     * Radius change in blocks after each eligible target.
     */
    @Getter
    @Setter
    protected float radiusOnUse = DEFAULT_RADIUS_ON_USE;

    /**
     * Effect reuse delay for each target in ticks.
     */
    @Getter
    @Setter
    protected int reapplicationDelay = DEFAULT_REAPPLICATION_DELAY;

    /**
     * {@code affectedEntities} maps target runtime identifiers to their next
     * eligible cloud tick. Entries are removed at application passes after
     * expiry, and this component does not persist the cooldown map.
     */
    protected final Long2LongOpenHashMap affectedEntities = new Long2LongOpenHashMap();

    /**
     * Elapsed entity ticks used for cloud scheduling.
     */
    protected long ticksExisted = 0;

    /**
     * Creates base entity state and initializes the cloud's radius and timing
     * settings.
     */
    public EntityAreaEffectCloudBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Advances the cloud after the common entity tick and removes it when
     * radius or duration expires. The first nine cloud ticks leave active
     * duration and radius unchanged. Later ticks apply {@code radiusPerTick}
     * before checking the minimum radius, then consume one duration tick
     * before target processing.
     *
     * <p> Application passes occur every ten cloud ticks and remove expired
     * cooldown entries before checking nearby targets. This order makes a
     * target eligible at its recorded deadline; an expiring radius or duration
     * skips the pass entirely, and this component does not restore earlier
     * tick changes after removal is queued.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        ticksExisted++;

        // Delay active aging until the tenth entity tick, preserving the initial
        // cloud settings during its short startup interval.
        if (ticksExisted < 10) {
            return;
        }

        if (radiusPerTick != 0) {
            radius += radiusPerTick;
            broadcastState();
        }

        if (radius < MIN_RADIUS) {
            thisEntity.remove();
            return;
        }

        duration--;
        if (duration <= 0) {
            thisEntity.remove();
            return;
        }

        // Ten ticks separate effect passes, limiting repeated spatial queries for targets.
        if (ticksExisted % 10 != 0) {
            return;
        }

        // Drop expired target reservations before selecting recipients, so the
        // same pass can reapply effects at each target's recorded eligible tick.
        affectedEntities.long2LongEntrySet().removeIf(entry -> entry.getLongValue() <= ticksExisted);

        applyEffectsToNearbyEntities();
    }

    /**
     * Applies copied potion effects to eligible living entities within the
     * cloud's current horizontal radius. Missing potion data or an empty
     * effect list produces no applications. Candidate lookup first uses the
     * expanded entity box, then the current radius and target cooldown
     * determine eligibility.
     *
     * <p> Each eligible target receives fresh effect instances, with
     * noninstant durations divided by four. The target then receives a
     * cooldown and consumes {@code radiusOnUse}, even if its living component
     * rejects an effect. Later candidates use that changed radius, and
     * crossing {@code MIN_RADIUS} queues removal before another target can be
     * processed.
     */
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

        var aabb = MathUtils.grow(new AABBd(thisEntity.getOffsetAABB()), new Vector3d(radius, 1.0, radius));
        var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb);

        boolean radiusChanged = false;

        for (var entity : entities) {
            if (!(entity instanceof EntityLiving living)) {
                continue;
            }

            if (affectedEntities.containsKey(entity.getRuntimeId())) {
                continue;
            }

            var entityPos = entity.getLocation();
            var deltaX = entityPos.x() - pos.x();
            var deltaZ = entityPos.z() - pos.z();
            var horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

            if (horizontalDistance > radius) {
                continue;
            }

            for (var effect : effects) {
                var newEffect = new EffectInstance(effect);
                // Preserve one-tick effects while shortening longer cloud applications;
                // each copied instance changes independently of the potion's source effect.
                if (effect.getDuration() > 1) {
                    newEffect.setDuration(effect.getDuration() / 4);
                }
                living.addEffect(newEffect);
            }

            affectedEntities.put(entity.getRuntimeId(), ticksExisted + reapplicationDelay);

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

    /**
     * Loads cloud settings from NBT entries of the expected numeric types.
     * Missing entries preserve current values, and this handler does not
     * restore elapsed startup ticks or per-target cooldown reservations.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForFloat(TAG_RADIUS, v -> this.radius = v);
        nbt.listenForInt(TAG_DURATION, v -> this.duration = v);
        nbt.listenForFloat(TAG_RADIUS_PER_TICK, v -> this.radiusPerTick = v);
        nbt.listenForFloat(TAG_RADIUS_ON_USE, v -> this.radiusOnUse = v);
        nbt.listenForInt(TAG_REAPPLICATION_DELAY, v -> this.reapplicationDelay = v);
    }

    /**
     * Writes radius and lifetime settings to the shared entity NBT builder.
     */
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
