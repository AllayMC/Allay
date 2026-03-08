package org.allaymc.server.item.fishing;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.fishing.FishingLoot;
import org.allaymc.api.item.fishing.FishingLootCategory;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.function.Supplier;

/**
 * A simple implementation of {@link FishingLoot}.
 *
 * @author daoge_cmd
 */
@Getter
public class SimpleFishingLoot implements FishingLoot {
    protected final Identifier identifier;
    protected final FishingLootCategory category;
    protected final float weight;
    protected final Supplier<ItemStack> itemStackSupplier;
    protected final boolean requiresOpenWater;

    public SimpleFishingLoot(Identifier identifier, FishingLootCategory category, float weight, Supplier<ItemStack> itemStackSupplier) {
        this(identifier, category, weight, itemStackSupplier, category == FishingLootCategory.TREASURE);
    }

    public SimpleFishingLoot(Identifier identifier, FishingLootCategory category, float weight, Supplier<ItemStack> itemStackSupplier, boolean requiresOpenWater) {
        this.identifier = identifier;
        this.category = category;
        this.weight = weight;
        this.itemStackSupplier = itemStackSupplier;
        this.requiresOpenWater = requiresOpenWater;
    }

    /**
     * Creates a simple fishing loot with a fixed item type.
     *
     * @param identifier the identifier
     * @param category   the category
     * @param weight     the weight
     * @param itemType   the item type
     * @return the fishing loot
     */
    public static SimpleFishingLoot of(Identifier identifier, FishingLootCategory category, float weight, ItemType<?> itemType) {
        return new SimpleFishingLoot(identifier, category, weight, itemType::createItemStack);
    }

    /**
     * Creates a simple fishing loot with a fixed item type and meta.
     *
     * @param identifier the identifier
     * @param category   the category
     * @param weight     the weight
     * @param itemType   the item type
     * @param meta       the item meta
     * @return the fishing loot
     */
    public static SimpleFishingLoot of(Identifier identifier, FishingLootCategory category, float weight, ItemType<?> itemType, int meta) {
        return new SimpleFishingLoot(identifier, category, weight, () -> itemType.createItemStack(
                org.allaymc.api.item.ItemStackInitInfo.builder().meta(meta).build()
        ));
    }

    @Override
    public ItemStack createItemStack() {
        return itemStackSupplier.get();
    }

    @Override
    public boolean requiresOpenWater() {
        return requiresOpenWater;
    }
}
