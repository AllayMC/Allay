package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityAnimalComponent;
import org.allaymc.api.entity.component.EntityBabyComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityGetDropXpEvent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Shared logic for passive animal base components.
 */
public abstract class EntityAnimalBaseComponentImpl extends EntityBaseComponentImpl {

    @Dependency
    protected EntityAnimalComponent animalComponent;
    @Dependency
    protected EntityBabyComponent babyComponent;
    @Dependency
    protected EntityLivingComponent livingComponent;
    @Dependency(optional = true)
    protected EntityAIComponent aiComponent;

    protected EntityAnimalBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (player == null || aiComponent == null || !animalComponent.isBreedingItem(itemStack)) {
            return false;
        }

        aiComponent.getMemoryStorage().put(MemoryTypes.LAST_BE_FEED_TIME, thisEntity.getTick());
        aiComponent.getMemoryStorage().put(MemoryTypes.LAST_FEED_PLAYER, player.getRuntimeId());
        player.tryConsumeItemInHand();
        return true;
    }

    protected boolean canDrop() {
        return thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_MOB_LOOT) && !babyComponent.isBaby();
    }

    @EventHandler
    protected void onGetDropXp(CEntityGetDropXpEvent event) {
        if (babyComponent.isBaby()) return;
        event.setXp(ThreadLocalRandom.current().nextInt(1, 4));
    }
}
