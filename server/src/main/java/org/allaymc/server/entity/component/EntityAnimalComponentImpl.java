package org.allaymc.server.entity.component;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityAnimalComponent;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;

import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;

import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class EntityAnimalComponentImpl implements EntityAnimalComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_animal_component");

    @ComponentObject
    protected EntityAnimal thisEntity;
    @Dependency(optional = true)
    protected EntityAIComponent aiComponent;

    protected final Predicate<ItemStack> breedingItemPredicate;

    public EntityAnimalComponentImpl(Predicate<ItemStack> breedingItemPredicate) {
        this.breedingItemPredicate = breedingItemPredicate;
    }

    @Override
    public boolean isBreedingItem(ItemStack item) {
        return breedingItemPredicate.test(item);
    }

    @EventHandler
    protected void onAfterDamage(CEntityAfterDamageEvent event) {
        if (aiComponent != null) {
            aiComponent.getMemoryStorage().put(MemoryTypes.LAST_BE_ATTACKED_TIME, thisEntity.getTick());
        }
    }
}
