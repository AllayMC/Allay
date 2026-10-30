package org.allaymc.api.item.enchantment;

import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;

/**
 * {@code ApplicableType} classifies item types accepted by enchantment
 * applicability checks. Each constant implements one category, using item
 * helpers, registered type identities, item data, or wearable components;
 * {@code EnchantmentType.canBeAppliedTo} separately accepts ordinary books
 * before consulting these categories.
 *
 * @author daoge_cmd
 */
public enum ApplicableType {
    /**
     * Any item type.
     */
    ANY {
        /**
         * Returns {@code true} for every supplied value, including
         * {@code null}. This category does not inspect item data, tags, or
         * components, so it imposes no item-type constraint on its caller.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return true;
        }
    },
    /**
     * Items classified as armor.
     */
    ARMOR {
        /**
         * Returns whether {@code ItemHelper} classifies the supplied type as
         * armor.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isArmor(itemType);
        }
    },
    /**
     * Armor items excluding elytra.
     */
    ARMOR_WITHOUT_ELYTRA {
        /**
         * Returns whether the supplied type is armor other than
         * {@code ItemTypes.ELYTRA}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isArmor(itemType) && itemType != ItemTypes.ELYTRA;
        }
    },
    /**
     * Armor for the head.
     */
    HELMET {
        /**
         * Returns whether an armor item type produces a wearable stack for
         * {@code ArmorType.HELMET}. Nonarmor types are rejected before stack
         * creation, and wearable stacks for another slot also return
         * {@code false}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (ItemHelper.isArmor(itemType) && itemType.createItemStack() instanceof ItemWearableComponent armor) {
                return armor.getArmorType() == ArmorType.HELMET;
            }

            return false;
        }
    },
    /**
     * Armor for the chest.
     */
    CHESTPLATE {
        /**
         * Returns whether an armor item type produces a wearable stack for
         * {@code ArmorType.CHESTPLATE}. Nonarmor types are rejected before
         * stack creation, and wearable stacks for another slot also return
         * {@code false}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (ItemHelper.isArmor(itemType) && itemType.createItemStack() instanceof ItemWearableComponent armor) {
                return armor.getArmorType() == ArmorType.CHESTPLATE;
            }

            return false;
        }
    },
    /**
     * Armor for the legs.
     */
    LEGGINGS {
        /**
         * Returns whether an armor item type produces a wearable stack for
         * {@code ArmorType.LEGGINGS}. Nonarmor types are rejected before stack
         * creation, and wearable stacks for another slot also return
         * {@code false}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (ItemHelper.isArmor(itemType) && itemType.createItemStack() instanceof ItemWearableComponent armor) {
                return armor.getArmorType() == ArmorType.LEGGINGS;
            }

            return false;
        }
    },
    /**
     * Armor for the feet.
     */
    BOOTS {
        /**
         * Returns whether an armor item type produces a wearable stack for
         * {@code ArmorType.BOOTS}. Nonarmor types are rejected before stack
         * creation, and wearable stacks for another slot also return
         * {@code false}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (ItemHelper.isArmor(itemType) && itemType.createItemStack() instanceof ItemWearableComponent armor) {
                return armor.getArmorType() == ArmorType.BOOTS;
            }

            return false;
        }
    },
    /**
     * Items classified as swords.
     */
    SWORD {
        /**
         * Returns whether {@code ItemHelper} classifies the supplied type as a
         * sword.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isSword(itemType);
        }
    },
    /**
     * Sword or axe items.
     */
    SWORD_OR_AXE {
        /**
         * Returns whether the supplied item type is a sword or an axe.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return SWORD.canBeAppliedTo(itemType) || ItemHelper.isAxe(itemType);
        }
    },
    /**
     * Pickaxe, shovel, axe, or hoe.
     */
    DIGGER {
        /**
         * Returns whether the supplied type is a pickaxe, shovel, axe, or hoe.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isPickaxe(itemType) || ItemHelper.isShovel(itemType) || ItemHelper.isAxe(itemType) || ItemHelper.isHoe(itemType);
        }
    },
    /**
     * Digging tools or shears.
     */
    DIGGER_OR_SHEARS {
        /**
         * Returns whether the supplied type belongs to {@code DIGGER} or is
         * {@code ItemTypes.SHEARS}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return DIGGER.canBeAppliedTo(itemType) || itemType == ItemTypes.SHEARS;
        }
    },
    /**
     * The fishing rod type.
     */
    FISHING_ROD {
        /**
         * Returns whether the supplied type is the registered
         * {@code ItemTypes.FISHING_ROD}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.FISHING_ROD;
        }
    },
    /**
     * Items with damageable data.
     */
    DAMAGEABLE {
        /**
         * Returns whether the supplied item type reports damageable item data.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType.getItemData().isDamageable();
        }
    },
    /**
     * The standard bow type.
     */
    BOW {
        /**
         * Returns whether the supplied type is the registered
         * {@code ItemTypes.BOW}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.BOW;
        }
    },
    /**
     * Items creating wearable stacks.
     */
    WEARABLE {
        /**
         * Returns whether the stack produced for the supplied type implements
         * {@code ItemWearableComponent}. This check invokes
         * {@code createItemStack} on every call and returns {@code false} when
         * the resulting stack lacks that component.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType.createItemStack() instanceof ItemWearableComponent;
        }
    },
    /**
     * The standard trident type.
     */
    TRIDENT {
        /**
         * Returns whether the supplied type is the registered
         * {@code ItemTypes.TRIDENT}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.TRIDENT;
        }
    },
    /**
     * The standard crossbow type.
     */
    CROSSBOW {
        /**
         * Returns whether the supplied type is the registered
         * {@code ItemTypes.CROSSBOW}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.CROSSBOW;
        }
    },
    /**
     * The standard mace type.
     */
    MACE {
        /**
         * Returns whether the supplied type is the registered
         * {@code ItemTypes.MACE}.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.MACE;
        }
    },
    /**
     * Items classified as spears.
     */
    SPEAR {
        /**
         * Returns whether {@code ItemHelper} classifies the supplied type as a
         * spear.
         */
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isSpear(itemType);
        }
    };

    /**
     * Returns whether the supplied item type belongs to this applicability
     * category.
     */
    public abstract boolean canBeAppliedTo(ItemType<?> itemType);
}
