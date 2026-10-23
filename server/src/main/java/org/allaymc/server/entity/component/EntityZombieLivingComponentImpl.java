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
 * {@code EntityZombieLivingComponentImpl} specializes living behavior with
 * zombie health, retaliation memory, and drops. Accepted attacks resolve
 * projectile shooters before selecting an alive living attacker as the AI
 * target, while loot and experience depend on looting level and baby state.
 */
public class EntityZombieLivingComponentImpl extends EntityLivingComponentImpl {

    /**
     * AI component receiving the accepted retaliation target identity.
     */
    @Dependency
    protected EntityAIComponent aiComponent;
    /**
     * Age component selecting the zombie's experience reward.
     */
    @Dependency
    protected EntityBabyComponent babyComponent;

    /**
     * Creates the zombie living component with twenty maximum health points.
     */
    public EntityZombieLivingComponentImpl() {
        setMaxHealth(20);
    }

    /**
     * Applies living damage and then selects retaliation from the original
     * {@code damage} argument's attacker. A listener-replaced container can
     * change the parent damage pipeline without changing this source;
     * projectile shooters are resolved, and missing, self, dead, or nonliving
     * attackers leave retaliation memory unchanged.
     */
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

    /**
     * Returns a projectile's shooter or the supplied attacker when it is an
     * entity. Unknown attacker kinds return {@code null}, and a projectile
     * without a shooter also produces {@code null}.
     */
    protected Entity resolveAttacker(Object attacker) {
        if (attacker instanceof EntityProjectile projectile) {
            return projectile.getShooter();
        }

        // Block and environmental attackers have no entity identity to retaliate
        // against, so leave target selection to the accepted entity-only path.
        return attacker instanceof Entity entity ? entity : null;
    }

    /**
     * Creates a fresh list of zombie item drops using the supplied looting
     * level. The level increases the flesh bound and rare-drop chance; callers
     * must supply a level that keeps the random integer bound positive.
     */
    @Override
    public List<ItemStack> getDrops(int lootingLevel) {
        var drops = new ArrayList<ItemStack>();
        var rand = ThreadLocalRandom.current();
        int fleshCount = rand.nextInt(4 + lootingLevel);
        if (fleshCount > 0) {
            drops.add(ItemTypes.ROTTEN_FLESH.createItemStack(fleshCount));
        }

        // Looting adds one three-hundredth to the rare-drop roll per level.
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

    /**
     * Returns twelve experience points for babies and five for adult zombies.
     */
    @Override
    public int getDropXpAmount() {
        return babyComponent.isBaby() ? 12 : 5;
    }
}
