package org.allaymc.server.entity.component.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.FireworkExplodeEvent;
import org.allaymc.api.world.data.FireworkType;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.joml.primitives.Rayd;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@code EntityFireworksRocketBaseComponentImpl} represents a rocket lifetime
 * and its configured explosion definitions. Each tick increments elapsed time
 * before comparing it with the lifetime threshold, so expiration occurs only
 * after that threshold is exceeded. The expiration path emits rocket effects,
 * attempts blast damage, and then removes the rocket even when damage is
 * cancelled.
 *
 * <p>
 * Blast damage combines explosion count, event-adjusted force, target
 * distance, and block occlusion. The component emits action and sounds before
 * the damage event, then considers nearby living entities within five blocks.
 * Each target receives distance-attenuated damage only when the sampled block
 * shapes leave its location visible from the rocket.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityFireworksRocketBaseComponentImpl extends EntityBaseComponentImpl implements EntityFireworksRocketBaseComponent {

    /**
     * {@code TAG_EXPLOSIONS} names the compound list used to persist the
     * rocket explosion definitions with entity data.
     */
    protected static final String TAG_EXPLOSIONS = "Explosions";
    /**
     * {@code TAG_LIFE_TIME} names the integer lifetime threshold persisted for
     * rocket expiration processing on later ticks.
     */
    protected static final String TAG_LIFE_TIME = "LifeTime";
    /**
     * {@code TAG_LIFE} names the integer elapsed lifetime restored and
     * advanced by the rocket tick handler.
     */
    protected static final String TAG_LIFE = "Life";

    /**
     * {@code existenceTicks} stores the lifetime threshold, and {@code
     * existedTicks} stores elapsed ticks. {@code tick} increments elapsed time
     * before expiration comparison, while generated setters can replace either
     * value without validation.
     */
    protected int existenceTicks, existedTicks;
    /**
     * {@code explosions} retains the current mutable explosion set. The
     * generated getter exposes this same set, and {@code setExplosions}
     * accepts replacement storage without copying its entries.
     */
    @Setter(AccessLevel.NONE)
    protected Set<FireworkExplosion> explosions;
    /**
     * {@code attachedPlayer} holds the player followed by rocket physics, or
     * {@code null} during free flight. Generated accessors retain and expose
     * the same player reference.
     */
    protected EntityPlayer attachedPlayer;

    /**
     * Creates a rocket with a twenty-tick lifetime threshold and an empty
     * explosion set. Elapsed lifetime starts at zero, and inherited
     * initialization can restore different values from stored entity data.
     */
    public EntityFireworksRocketBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.existenceTicks = 20;
        this.explosions = new HashSet<>();
    }

    /**
     * Advances inherited entity ticking and increments the rocket elapsed
     * lifetime. Expiration calls {@code explode} before requesting removal,
     * and cancellation inside the damage phase does not prevent that removal.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        this.existedTicks++;
        if (this.existedTicks > this.existenceTicks) {
            // Expire only after the threshold is exceeded; equality leaves the
            // rocket alive for this tick.
            explode();
            remove();
        }
    }

    /**
     * Emits rocket blast effects and applies permitted damage to visible
     * nearby living entities. An empty explosion set still emits the rocket
     * action but skips sounds and damage. Sounds precede the cancellable
     * damage event, so cancellation leaves those effects observable.
     *
     * <p>
     * The event starts with force derived from explosion count. Candidate
     * bounds are expanded for overlap lookup, then target positions are
     * limited to five blocks and tested against block collision shapes along
     * the ray. Accepted targets receive force multiplied by a square-root
     * distance falloff; this method itself does not remove the rocket.
     */
    protected void explode() {
        var dimension = getDimension();

        applyAction(SimpleEntityAction.FIREWORK_EXPLODE);
        if (this.explosions.isEmpty()) {
            return;
        }

        for (var explosion : this.explosions) {
            if (explosion.type() == FireworkType.HUGE_SPHERE) {
                dimension.addSound(this.location, SimpleSound.FIREWORK_HUGE_BLAST);
            } else {
                dimension.addSound(this.location, SimpleSound.FIREWORK_BLAST);
            }

            if (explosion.flicker()) {
                dimension.addSound(this.location, SimpleSound.FIREWORK_FLICKER);
            }
        }

        var event = new FireworkExplodeEvent((EntityFireworksRocket) thisEntity, this.explosions.size() * 2 + 5.0);
        if (!event.call()) {
            return;
        }

        // Expand the rocket bounds beyond the damage radius so nearby target bounds
        // enter the broad-phase query.
        var affectedEntities = dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(getOffsetAABB().expand(5.25, new AABBd()));
        for (var affectedEntity : affectedEntities) {
            if (affectedEntity instanceof EntityLiving living) {
                var pos = affectedEntity.getLocation();
                var distance = pos.distance(this.location);
                // The broad-phase box includes extra candidates; position distance
                // limits actual damage to five blocks.
                if (distance > 5.0) {
                    continue;
                }

                // Block collision shapes along the ray occlude blast damage even
                // when a target overlaps the broad-phase query volume.
                var hasDamage = new AtomicBoolean(true);
                var aabb = new AABBd(
                        Math.min(this.location.x(), pos.x()),
                        Math.min(this.location.y(), pos.y()),
                        Math.min(this.location.z(), pos.z()),
                        Math.max(this.location.x(), pos.x()),
                        Math.max(this.location.y(), pos.y()),
                        Math.max(this.location.z(), pos.z())
                );
                var ray = new Rayd(location, pos.sub(this.location, new Vector3d()));
                dimension.forEachBlockStates(aabb, 0, (x, y, z, block) -> {
                    if (hasDamage.get() && block.getBlockStateData().computeOffsetCollisionShape(x, y, z).intersectsRay(ray)) {
                        hasDamage.set(false);
                    }
                });

                if (hasDamage.get()) {
                    // Square-root falloff keeps full event force at the origin and
                    // reaches zero at five blocks.
                    var damage = DamageContainer.entityExplosion(
                            thisEntity,
                            (float) (event.getForce() * Math.sqrt((5.0 - distance) / 5.0))
                    );
                    living.attack(damage);
                }
            }
        }
    }

    /**
     * Retains the supplied explosion set and broadcasts the updated rocket
     * state. The method performs no copy or {@code null} check, so callers
     * must supply usable shared storage for ticking and serialization.
     */
    @Override
    public void setExplosions(Set<FireworkExplosion> explosions) {
        this.explosions = explosions;
        broadcastState();
    }

    /**
     * Returns inherited entity data with explosion definitions, lifetime
     * threshold, and elapsed ticks. Serialization reads the retained explosion
     * set directly and therefore requires that it remain nonnull during this
     * call.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putList(TAG_EXPLOSIONS, NbtType.COMPOUND, this.explosions.stream().map(FireworkExplosion::saveNBT).toList())
                .putInt(TAG_LIFE_TIME, this.existenceTicks)
                .putInt(TAG_LIFE, this.existedTicks)
                .build();
    }

    /**
     * Loads inherited entity data, appends stored explosion definitions, and
     * restores present lifetime values. Loading does not clear the current
     * explosion set first, so existing entries remain alongside restored
     * entries.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForList(TAG_EXPLOSIONS, NbtType.COMPOUND, entries -> {
            for (var entry : entries) {
                this.explosions.add(FireworkExplosion.fromNBT(entry));
            }
        });

        nbt.listenForInt(TAG_LIFE_TIME, value -> this.existenceTicks = value);
        nbt.listenForInt(TAG_LIFE, value -> this.existedTicks = value);
    }

    /**
     * Returns the firework rocket local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        // The narrow half-block box supplies the rocket footprint used by spatial
        // queries.
        return new AABBd(-0.0625, 0.0, -0.0625, 0.0625, 0.5, 0.0625);
    }
}
