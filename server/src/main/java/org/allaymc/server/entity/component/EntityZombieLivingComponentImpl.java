package org.allaymc.server.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityBabyComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.component.annotation.Dependency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Living component implementation for zombies.
 */
public class EntityZombieLivingComponentImpl extends EntityLivingComponentImpl {

    @Dependency
    protected EntityAIComponent aiComponent;
    @Dependency
    protected EntityBabyComponent babyComponent;

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

    @Override
    public List<ItemStack> getDrops(int lootingLevel) {
        var drops = new ArrayList<ItemStack>();
        var rand = ThreadLocalRandom.current();
        int fleshCount = rand.nextInt(4 + lootingLevel);
        if (fleshCount > 0) {
            drops.add(ItemTypes.ROTTEN_FLESH.createItemStack(fleshCount));
        }

        float rareChance = (1f / 120f) + ((1f / 300f) * lootingLevel);
        if (rand.nextFloat() < rareChance) {
            switch (rand.nextInt(4)) {
                case 0 -> drops.add(ItemTypes.IRON_INGOT.createItemStack());
                case 1 -> drops.add(ItemTypes.CARROT.createItemStack());
                case 2 -> drops.add(ItemTypes.POTATO.createItemStack());
                default -> {
                }
            }
        }

        return drops;
    }

    @Override
    public int getDropXpAmount() {
        return babyComponent.isBaby() ? 12 : 5;
    }
}
