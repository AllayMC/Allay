package org.allaymc.server.entity.component.fireworks;

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
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityFireworksRocketBaseComponentImpl extends EntityBaseComponentImpl implements EntityFireworksRocketBaseComponent {

    protected static final String TAG_EXPLOSIONS = "Explosions";
    protected static final String TAG_LIFE_TIME = "LifeTime";
    protected static final String TAG_LIFE = "Life";

    protected int existenceTicks, existedTicks;
    @Setter(AccessLevel.NONE)
    protected Set<FireworkExplosion> explosions;
    protected EntityPlayer attachedPlayer;

    public EntityFireworksRocketBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.existenceTicks = 20;
        this.explosions = new HashSet<>();
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        this.existedTicks++;
        if (this.existedTicks > this.existenceTicks) {
            explode();
            remove();
        }
    }

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

        var affectedEntities = dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(getOffsetAABB().expand(5.25, new AABBd()));
        for (var affectedEntity : affectedEntities) {
            if (affectedEntity instanceof EntityLiving living) {
                var pos = affectedEntity.getLocation();
                var distance = pos.distance(this.location);
                if (distance > 5.0) {
                    // The maximum distance allowed is 5.0 blocks.
                    continue;
                }

                // Perform ray cast to check if we should apply damage to the entity. This is simply determined
                // by just checking if there are solid blocks between the firework and the entity.
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
                    var damage = DamageContainer.entityExplosion(
                            thisEntity,
                            (float) (event.getForce() * Math.sqrt((5.0 - distance) / 5.0))
                    );
                    living.attack(damage);
                }
            }
        }
    }

    @Override
    public void setExplosions(Set<FireworkExplosion> explosions) {
        this.explosions = explosions;
        broadcastState();
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putList(TAG_EXPLOSIONS, NbtType.COMPOUND, this.explosions.stream().map(FireworkExplosion::saveNBT).toList())
                .putInt(TAG_LIFE_TIME, this.existenceTicks)
                .putInt(TAG_LIFE, this.existedTicks)
                .build();
    }

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

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.0625, 0.0, -0.0625, 0.0625, 0.5, 0.0625);
    }
}
