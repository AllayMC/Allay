package org.allaymc.server.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.server.component.annotation.Dependency;

/**
 * Living component implementation for zombies.
 */
public class EntityZombieLivingComponentImpl extends EntityLivingComponentImpl {

    @Dependency
    protected EntityAIComponent aiComponent;

    public EntityZombieLivingComponentImpl() {
        setMaxHealth(20);
    }

    @Override
    public boolean attack(DamageContainer damage, boolean ignoreCoolDown) {
        if (!super.attack(damage, ignoreCoolDown)) {
            return false;
        }

        var attacker = resolveAttacker(damage.getAttacker());
        if (attacker == null || attacker == thisEntity || !attacker.isAlive() || !(attacker instanceof EntityLivingComponent)) {
            return true;
        }

        aiComponent.getMemoryStorage().put(MemoryTypes.ATTACK_TARGET, attacker.getRuntimeId());
        return true;
    }

    protected Entity resolveAttacker(Object attacker) {
        if (attacker instanceof EntityProjectile projectile) {
            return projectile.getShooter();
        }

        return attacker instanceof Entity entity ? entity : null;
    }
}
