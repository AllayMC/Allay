package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author daoge_cmd
 */
public class EntityEnderPearlBaseComponentImpl extends EntityProjectileBaseComponentImpl {
    public EntityEnderPearlBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    protected void onHitEntity(Entity other) {
        if (this.willBeDespawnedNextTick() || other == shootingEntity) {
            return;
        }

        if (other instanceof EntityDamageComponent damageComponent) {
            damageComponent.attack(DamageContainer.projectile(thisEntity, other.getEntityType() == EntityTypes.BLAZE ? 3 : 0));
        }

        this.teleport();
        this.despawn();
    }

    @Override
    protected void onHitBlock(BlockStateWithPos block) {
        if (this.willBeDespawnedNextTick()) {
            return;
        }

        this.teleport();
        this.despawn();
    }

    protected void teleport() {
        if (shootingEntity == null) {
            return;
        }

        this.getDimension().addLevelSoundEvent(this.location, SoundEvent.TELEPORT);
        if (!shootingEntity.teleport(this.location, EntityTeleportEvent.Reason.PROJECTILE)) {
            return;
        }

        this.getDimension().addLevelSoundEvent(this.location, SoundEvent.TELEPORT);
        this.getDimension().addLevelEvent(this.location, LevelEvent.PARTICLE_TELEPORT);
        if (shootingEntity instanceof EntityDamageComponent damageComponent) {
            damageComponent.attack(DamageContainer.fall(5));
        }
        // TODO: spawn ender mite
    }
}
