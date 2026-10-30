package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.component.EntityAnimalComponent;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.item.ItemStack;

import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;

import java.util.function.Predicate;

/**
 * {@code EntityAnimalComponentImpl} recognizes breeding items through a
 * predicate provided by the entity type. Every query passes the original item
 * stack to that predicate, so accepted items and handling of {@code null}
 * depend on the predicate chosen during component construction.
 *
 * @author daoge_cmd
 */
public class EntityAnimalComponentImpl implements EntityAnimalComponent {

    /**
     * {@code IDENTIFIER} selects the predicate-backed breeding-item component
     * when an entity type composes the animal's behavior from named component
     * implementations.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_animal_component");

    /**
     * Animal owning the breeding-item predicate component.
     */
    @ComponentObject
    protected EntityAnimal thisEntity;

    /**
     * {@code breedingItemPredicate} defines accepted breeding items for this
     * animal. The constructor retains it directly, and {@code isBreedingItem}
     * passes the caller's stack to it without copying or {@code null} handling.
     */
    protected final Predicate<ItemStack> breedingItemPredicate;

    /**
     * Creates breeding-item recognition using the supplied predicate without
     * validation. The predicate is retained for the component lifetime and
     * receives each queried item directly, including {@code null} when a caller
     * supplies it.
     */
    public EntityAnimalComponentImpl(Predicate<ItemStack> breedingItemPredicate) {
        this.breedingItemPredicate = breedingItemPredicate;
    }

    /**
     * Returns the configured predicate's result for the supplied item stack.
     * The method passes {@code item} through unchanged, so the predicate
     * determines whether {@code null} is accepted and any predicate failure
     * propagates to the caller.
     */
    @Override
    public boolean isBreedingItem(ItemStack item) {
        return breedingItemPredicate.test(item);
    }
}
