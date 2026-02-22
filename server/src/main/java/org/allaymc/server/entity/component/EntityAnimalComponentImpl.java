package org.allaymc.server.entity.component;

import org.allaymc.api.entity.component.EntityAnimalComponent;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.item.ItemStack;

import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;

import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
public class EntityAnimalComponentImpl implements EntityAnimalComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_animal_component");

    @ComponentObject
    protected EntityAnimal thisEntity;

    protected final Predicate<ItemStack> breedingItemPredicate;

    public EntityAnimalComponentImpl(Predicate<ItemStack> breedingItemPredicate) {
        this.breedingItemPredicate = breedingItemPredicate;
    }

    @Override
    public boolean isBreedingItem(ItemStack item) {
        return breedingItemPredicate.test(item);
    }
}
