package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.FireworkExplosion;
import org.allaymc.api.world.data.FireworkType;
import org.allaymc.api.world.sound.SimpleSound;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.HashSet;
import java.util.Set;

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
    protected Set<FireworkExplosion> explosions;
    protected EntityPlayer attachedPlayer;

    public EntityFireworksRocketBaseComponentImpl(EntityInitInfo info) {
        super(info);
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

    // TODO: event
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

        var force = this.explosions.size() * 2 + 5.0;
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

                // TODO: check if any block is in the ray path

                var damage = DamageContainer.entityExplosion(
                        thisEntity,
                        (float) (force * Math.sqrt((5.0 - distance) / 5.0))
                );
                living.attack(damage);
            }
        }
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
    public AABBdc getAABB() {
        return new AABBd(-0.025, 0.0, -0.025, 0.025, 0.05, 0.025);
    }
}
