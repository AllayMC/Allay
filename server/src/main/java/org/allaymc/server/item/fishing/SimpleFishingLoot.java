package org.allaymc.server.item.fishing;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.fishing.FishingLoot;
import org.allaymc.api.item.fishing.FishingLootCategory;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.function.Supplier;

/**
 * {@code SimpleFishingLoot} represents a weighted fishing-loot definition
 * backed by an item supplier. Its constructors retain the category, weight,
 * water requirement, and supplier, while factory invocation delegates stack
 * creation without copying or validating the supplier's result.
 *
 * @author daoge_cmd
 */
@Getter
public class SimpleFishingLoot implements FishingLoot {
    /**
     * Registered loot definition identifier.
     */
    protected final Identifier identifier;
    /**
     * Weighted selection category.
     */
    protected final FishingLootCategory category;
    /**
     * Relative category-selection weight.
     */
    protected final float weight;
    /**
     * {@code itemStackSupplier} holds the retained catch factory.
     * {@code createItemStack} returns its result directly, so the supplier
     * must provide fresh stacks for independent catch mutations.
     */
    protected final Supplier<ItemStack> itemStackSupplier;
    /**
     * {@code requiresOpenWater} stores the declared water requirement. The
     * convenience constructor selects it for treasure, but
     * {@code FishingLootTable} currently uses category state instead of this
     * property.
     */
    protected final boolean requiresOpenWater;

    /**
     * Creates a supplier-backed loot definition with category-based open-water
     * metadata. Treasure sets the requirement to {@code true}; this
     * constructor retains all supplied values without validating weight or
     * factory results.
     */
    public SimpleFishingLoot(Identifier identifier, FishingLootCategory category, float weight, Supplier<ItemStack> itemStackSupplier) {
        this(identifier, category, weight, itemStackSupplier, category == FishingLootCategory.TREASURE);
    }

    /**
     * Creates a loot definition retaining the supplied selection data and item
     * factory. {@code requiresOpenWater} is stored independently of category;
     * this constructor does not validate weights, identifiers, or supplier
     * results.
     */
    public SimpleFishingLoot(Identifier identifier, FishingLootCategory category, float weight, Supplier<ItemStack> itemStackSupplier, boolean requiresOpenWater) {
        this.identifier = identifier;
        this.category = category;
        this.weight = weight;
        this.itemStackSupplier = itemStackSupplier;
        this.requiresOpenWater = requiresOpenWater;
    }

    /**
     * Creates loot whose supplier invokes the item type's default stack
     * factory for every catch. The category selects the declared open-water
     * requirement, and the factory uses the type's ordinary default
     * initialization.
     */
    public static SimpleFishingLoot of(Identifier identifier, FishingLootCategory category, float weight, ItemType<?> itemType) {
        return new SimpleFishingLoot(identifier, category, weight, itemType::createItemStack);
    }

    /**
     * Creates loot whose supplier constructs the specified item type and
     * metadata. This overload leaves the initialization builder's count at its
     * default {@code 0}, so its current supplier creates a zero-count stack.
     */
    public static SimpleFishingLoot of(Identifier identifier, FishingLootCategory category, float weight, ItemType<?> itemType, int meta) {
        return new SimpleFishingLoot(identifier, category, weight, () -> itemType.createItemStack(
                org.allaymc.api.item.ItemStackInitInfo.builder().meta(meta).build()
        ));
    }

    /**
     * Invokes the retained supplier and returns its result without copying.
     * Supplier failures propagate, and this implementation does not reject
     * {@code null} or shared results before returning them to fishing
     * selection.
     */
    @Override
    public ItemStack createItemStack() {
        return itemStackSupplier.get();
    }

    /**
     * Returns the open-water requirement stored by this loot definition.
     */
    @Override
    public boolean requiresOpenWater() {
        return requiresOpenWater;
    }
}
