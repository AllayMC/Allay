package org.allaymc.server.item.type;

import lombok.Builder;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

import java.util.Map;

/**
 * @author daoge_cmd
 */
@Builder
public class CustomItemComponentDataGenerator implements ItemComponentDataGenerator {

    /**
     * The texture of the item, which is used to render the item in the inventory and on the ground.
     */
    protected final String texture;
    /**
     * The text shown when an item name is shown, such as hover text. If {@code null}, the display name will be the path of the item identifier.
     */
    @MayContainTrKey
    protected final String displayName;
    // TODO: check creative category and group
    /**
     * Whether item is displayed with enchantment glint even when not enchanted (like enchanted golden apple).
     */
    protected final boolean foil;
    /**
     * Whether item can be used to destroy blocks on creative (disable to match sword behavior).
     */
    @Builder.Default
    protected final boolean canDestroyInCreative = true;
    /**
     * Whether the item can be held in the off-hand slot.
     */
    protected final boolean allowOffHand;

    protected final Map<String, NbtMap> customProperties;
    protected final Map<String, NbtMap> customComponents;

    @Override
    public ItemComponentData generate(ItemType<?> itemType) {
        var itemData = itemType.getItemData();
        var itemStack = itemType.createItemStack();

        var properties = NbtMap.builder();
        properties.putInt("max_stack_size", itemData.maxStackSize())
                // When item is a tool, it is displayed as hand equipped which is different from normal items
                .putBoolean("hand_equipped", itemStack instanceof ItemToolComponent)
                .putBoolean("allow_off_hand", this.allowOffHand)
                .putBoolean("can_destroy_in_creative", this.canDestroyInCreative);

        properties.putCompound("minecraft:icon",
                NbtMap.builder().putCompound("textures",
                        NbtMap.builder().putString("default", this.texture).build()).build());

        if (this.foil) {
            properties.putBoolean("foil", true);
        }

        var components = NbtMap.builder();
        components.putCompound("minecraft:display_name", NbtMap.builder().putString("value", this.displayName != null ? this.displayName : itemType.getIdentifier().path()).build());

        if (itemData.isDamageable()) {
            components.putCompound("minecraft:durability", NbtMap.builder().putInt("max_durability", itemData.maxDamage()).build());
        }

        if (itemStack instanceof ItemArmorBaseComponent armorBaseComponent) {
            components.putCompound("minecraft:armor", NbtMap.builder().putInt("protection", itemData.armorValue()).build());
            switch (armorBaseComponent.getArmorType()) {
                case HELMET -> {
                    properties.putString("wearable_slot", "slot.armor.head");
                    components.putCompound("minecraft:wearable", NbtMap.builder().putString("slot", "slot.armor.head").build());
                }
                case CHESTPLATE -> {
                    properties.putString("wearable_slot", "slot.armor.chest");
                    components.putCompound("minecraft:wearable", NbtMap.builder().putString("slot", "slot.armor.chest").build());
                }
                case LEGGINGS -> {
                    properties.putString("wearable_slot", "slot.armor.legs");
                    components.putCompound("minecraft:wearable", NbtMap.builder().putString("slot", "slot.armor.legs").build());
                }
                case BOOTS -> {
                    properties.putString("wearable_slot", "slot.armor.feet");
                    components.putCompound("minecraft:wearable", NbtMap.builder().putString("slot", "slot.armor.feet").build());
                }
            }
        }

        if (itemStack instanceof ItemFoodComponent foodComponent) {
            properties.putInt("use_duration", foodComponent.getEatingTime())
                    .putInt("use_animation", foodComponent.isDrink() ? 2 : 1);
            components.putCompound("minecraft:food", NbtMap.builder().putBoolean("can_always_eat", foodComponent.canBeAlwaysEaten()).build());
        }

        if (itemStack instanceof ItemToolComponent) {
            properties.putInt("damage", itemData.attackDamage());
        }

        if (customProperties != null) {
            properties.putAll(customProperties);
        }
        components.putCompound("item_properties", properties.build());
        if (customComponents != null) {
            components.putAll(customComponents);
        }

        return new ItemComponentData(true, NbtMap.builder().putCompound("components", components.build()).build(), ItemVersion.DATA_DRIVEN);
    }
}
