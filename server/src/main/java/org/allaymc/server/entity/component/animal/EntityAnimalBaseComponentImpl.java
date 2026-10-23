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
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityGetDropXpEvent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityAnimalBaseComponentImpl} supplies feeding interactions and adult
 * experience drops for passive animals. A recognized breeding item records the
 * feeding player and tick in AI memory before consuming the hand item, while
 * babies leave the experience-drop event unchanged.
 */
public abstract class EntityAnimalBaseComponentImpl extends EntityBaseComponentImpl {

    /**
     * Breeding-item recognition for this animal.
     */
    @Dependency
    protected EntityAnimalComponent animalComponent;
    /**
     * Baby state controlling adult experience drops.
     */
    @Dependency
    protected EntityBabyComponent babyComponent;
    /**
     * Living-state component associated with this animal.
     */
    @Dependency
    protected EntityLivingComponent livingComponent;
    /**
     * {@code aiComponent} supplies feeding memory when the animal has AI. An
     * absent optional dependency makes feeding interactions return
     * {@code false} before item consumption or memory updates.
     */
    @Dependency(optional = true)
    protected EntityAIComponent aiComponent;

    /**
     * Creates the animal base using the supplied entity initialization data.
     */
    protected EntityAnimalBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Records a recognized feeding interaction and attempts to consume the
     * player's hand item. A missing player, missing AI dependency, or
     * unrecognized item returns {@code false}; accepted interactions record the
     * current entity tick and player runtime identifier before returning
     * {@code true}.
     */
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

    /**
     * Assigns one to three experience points for an adult animal. The handler
     * leaves the event's existing experience value unchanged for babies, so
     * another component can still supply a separate drop policy.
     */
    @EventHandler
    protected void onGetDropXp(CEntityGetDropXpEvent event) {
        if (babyComponent.isBaby()) return;
        // Adult passive-animal kills select their base experience within this
        // range; looting does not alter the experience chosen by this handler.
        event.setXp(ThreadLocalRandom.current().nextInt(1, 4));
    }
}
