package org.allaymc.server.item.type;

import com.google.common.base.Preconditions;
import lombok.Builder;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.message.MayContainTrKey;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.List;
import java.util.Map;

/**
 * CustomItemDefinitionGenerator is the default implementation of {@link ItemDefinitionGenerator}, it contains
 * a number of useful methods for defining the behavior of the item type client-side.
 *
 * @author daoge_cmd
 */
@Builder
public class CustomItemDefinitionGenerator implements ItemDefinitionGenerator {

    /**
     * The texture of the item, which is used to render the item in the inventory and on the ground.
     */
    protected final String texture;
    /**
     * The text shown when an item name is shown, such as hover text. If {@code null}, the display name will be the identifier of the item.
     */
    @MayContainTrKey
    protected final String displayName;
    /**
     * The render offsets of the item at different viewpoints.
     */
    protected final RenderOffsets renderOffsets;
    /**
     * Whether the item is displayed with enchantment glints even when not enchanted (like enchanted golden apple).
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
    /**
     * Cooldown for this item.
     */
    protected final Integer cooldown;

    protected final Map<String, NbtMap> customProperties;
    protected final Map<String, NbtMap> customComponents;

    @Override
    public ItemDefinition generate(ItemType<?> itemType) {
        var itemData = itemType.getItemData();
        var itemStack = itemType.createItemStack();

        var properties = NbtMap.builder();
        var components = NbtMap.builder();

        properties.putInt("max_stack_size", itemData.maxStackSize())
                // When item is a tool, it is displayed as hand equipped which is different from normal items
                .putBoolean("hand_equipped", itemStack instanceof ItemToolComponent)
                .putBoolean("allow_off_hand", this.allowOffHand)
                .putBoolean("can_destroy_in_creative", this.canDestroyInCreative);

        properties.putCompound("minecraft:icon",
                NbtMap.builder().putCompound("textures",
                        NbtMap.builder().putString("default", this.texture).build()).build());

        if (renderOffsets != null) {
            components.putCompound("minecraft:render_offsets", renderOffsets.toNBT());
        }

        if (this.foil) {
            properties.putBoolean("foil", true);
        }

        components.putCompound("minecraft:display_name", NbtMap.builder().putString("value", this.displayName != null ? this.displayName : itemType.getIdentifier().toString()).build());

        var tags = itemType.getItemTags();
        if (!tags.isEmpty()) {
            // TODO: Check if we should use `minecraft:tags`
            components.putList("item_tags", NbtType.STRING, tags.stream().map(ItemTag::name).toArray(String[]::new));
        }

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

        if (itemStack instanceof ItemEdibleComponent edibleComponent) {
            properties.putInt("use_duration", edibleComponent.getEatingTime())
                    .putInt("use_animation", edibleComponent.isDrink() ? 2 : 1);
            components.putCompound("minecraft:food", NbtMap.builder().putBoolean("can_always_eat", edibleComponent.canBeAlwaysEaten()).build());
        }

        if (cooldown != null) {
            components.putCompound("minecraft:cooldown", NbtMap.builder().putString("category", itemType.getIdentifier().toString()).putFloat("duration", cooldown).build());
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

        return new ItemDefinition(true, NbtMap.builder().putCompound("components", components.build()).build(), ItemVersion.DATA_DRIVEN);
    }

    /**
     * Store the render offsets of custom items.
     *
     * @param mainHand the offsets for the main hand, can be {@code null}
     * @param offHand  the offsets for the offHand, can be {@code null}
     */
    @Builder
    public record RenderOffsets(Hand mainHand, Hand offHand) {

        /**
         * Creates a RenderOffsets instance with texture size applied to the offsets.
         *
         * @param textureSize the size of the texture, must be greater than 0 and a multiple of 16
         * @return a RenderOffsets instance with scaled offsets based on the texture size
         */
        public static RenderOffsets textureSize(int textureSize) {
            return scale(textureSize / 16f);
        }

        /**
         * Creates a RenderOffsets instance with scale applied to the offsets.
         *
         * @param scale the scale factor to apply to the offsets, must be greater than 0
         * @return a RenderOffsets instance with scaled offsets
         */
        public static RenderOffsets scale(float scale) {
            Preconditions.checkArgument(scale > 0, "Scale must be greater than 0");
            float scale1 = (float) (0.075 / scale);
            float scale2 = (float) (0.125 / scale);
            float scale3 = (float) (0.075 / (scale * 2.4f));
            return RenderOffsets.builder()
                    .mainHand(
                            Hand.builder()
                                    .firstPerson(Offset.builder().scale(new Vector3f(scale3, scale3, scale3)).build())
                                    .thirdPerson(Offset.builder().scale(new Vector3f(scale1, scale2, scale1)).build())
                                    .build()
                    )
                    .offHand(
                            Hand.builder()
                                    .firstPerson(Offset.builder().scale(new Vector3f(scale1, scale2, scale1)).build())
                                    .thirdPerson(Offset.builder().scale(new Vector3f(scale1, scale2, scale1)).build())
                                    .build()
                    )
                    .build();
        }

        public NbtMap toNBT() {
            var builder = NbtMap.builder();

            if (mainHand != null) {
                NbtMap nbt = mainHand.toNBT();
                if (nbt != null) {
                    builder.putCompound("main_hand", nbt);
                }
            }
            if (offHand != null) {
                NbtMap nbt = offHand.toNBT();
                if (nbt != null) {
                    builder.putCompound("off_hand", nbt);
                }
            }

            return builder.build();
        }

        /**
         * The hand that is used for the offset.
         *
         * @param firstPerson the offset for the first person view, can be {@code null}
         * @param thirdPerson the offset for the third person view, can be {@code null}
         */
        @Builder
        public record Hand(Offset firstPerson, Offset thirdPerson) {
            private NbtMap toNBT() {
                if (firstPerson == null && thirdPerson == null) {
                    return null;
                }

                var builder = NbtMap.builder();
                if (firstPerson != null) {
                    builder.putCompound("first_person", firstPerson.toNBT());
                }
                if (thirdPerson != null) {
                    builder.putCompound("third_person", thirdPerson.toNBT());
                }

                return builder.build();
            }
        }

        /**
         * The offset of the item.
         *
         * @param position the position, can be {@code null}
         * @param rotation the rotation, can be {@code null}
         * @param scale    the scale, can be {@code null}
         */
        @Builder
        public record Offset(Vector3fc position, Vector3fc rotation, Vector3fc scale) {
            private static List<Float> vecToList(Vector3fc xyz) {
                return List.of(xyz.x(), xyz.y(), xyz.z());
            }

            private NbtMap toNBT() {
                if (position == null && rotation == null && scale == null) {
                    return null;
                }

                NbtMapBuilder builder = NbtMap.builder();
                if (position != null) {
                    builder.putList("position", NbtType.FLOAT, vecToList(position));
                }
                if (rotation != null) {
                    builder.putList("rotation", NbtType.FLOAT, vecToList(rotation));
                }
                if (scale != null) {
                    builder.putList("scale", NbtType.FLOAT, vecToList(scale));
                }

                return builder.build();
            }
        }
    }
}
