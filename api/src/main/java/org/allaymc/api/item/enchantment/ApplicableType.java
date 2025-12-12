package org.allaymc.api.item.enchantment;

import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;

/**
 * Represents the item type that the enchantment can be applied to.
 *
 * @author daoge_cmd
 */
public enum ApplicableType {
    /**
     * Represents any item type.
     */
    ANY {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return true;
        }
    },
    /**
     * Represents an armor item.
     */
    ARMOR {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isArmor(itemType);
        }
    },
    /**
     * Represents an armor item without elytra.
     */
    ARMOR_WITHOUT_ELYTRA {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isArmor(itemType) && itemType != ItemTypes.ELYTRA;
        }
    },
    /**
     * Represents an armor item that can be worn on the head.
     */
    HELMET {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (itemType.createItemStack() instanceof ItemArmorBaseComponent armor) {
                return armor.getArmorType() == ArmorType.HELMET;
            }

            return false;
        }
    },
    /**
     * Represents an armor item that can be worn on the chest.
     */
    CHESTPLATE {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (itemType.createItemStack() instanceof ItemArmorBaseComponent armor) {
                return armor.getArmorType() == ArmorType.CHESTPLATE;
            }

            return false;
        }
    },
    /**
     * Represents an armor item that can be worn on the legs.
     */
    LEGGINGS {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (itemType.createItemStack() instanceof ItemArmorBaseComponent armor) {
                return armor.getArmorType() == ArmorType.LEGGINGS;
            }

            return false;
        }
    },
    /**
     * Represents an armor item that can be worn on the feet.
     */
    BOOTS {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            if (itemType.createItemStack() instanceof ItemArmorBaseComponent armor) {
                return armor.getArmorType() == ArmorType.BOOTS;
            }

            return false;
        }
    },
    /**
     * Represents a sword item.
     */
    SWORD {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isSword(itemType);
        }
    },
    /**
     * Represents a sword or axe item.
     */
    SWORD_OR_AXE {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return SWORD.canBeAppliedTo(itemType) || ItemHelper.isAxe(itemType);
        }
    },
    /**
     * Represents an item that is used to dig blocks, like pickaxe, shovel, axe and hoe.
     */
    DIGGER {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isPickaxe(itemType) || ItemHelper.isShovel(itemType) || ItemHelper.isAxe(itemType) || ItemHelper.isHoe(itemType);
        }
    },
    /**
     * Represents a digger or shears item.
     */
    DIGGER_OR_SHEARS {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return DIGGER.canBeAppliedTo(itemType) || itemType == ItemTypes.SHEARS;
        }
    },
    /**
     * Represents a bow item.
     */
    FISHING_ROD {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.FISHING_ROD;
        }
    },
    /**
     * Represents an item that is damageable.
     */
    DAMAGEABLE {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType.getItemData().isDamageable();
        }
    },
    /**
     * Represents a bow item.
     */
    BOW {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.BOW;
        }
    },
    /**
     * Represents an item that can be worn.
     */
    WEARABLE {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isArmor(itemType) || itemType == ItemTypes.CARVED_PUMPKIN || ItemHelper.isHead(itemType);
        }
    },
    /**
     * Represents a trident item.
     */
    TRIDENT {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.TRIDENT;
        }
    },
    /**
     * Represents a crossbow item.
     */
    CROSSBOW {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.CROSSBOW;
        }
    },
    /**
     * Represents a mace item.
     */
    MACE {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return itemType == ItemTypes.MACE;
        }
    },
    /**
     * Represents a spear item.
     */
    SPEAR {
        @Override
        public boolean canBeAppliedTo(ItemType<?> itemType) {
            return ItemHelper.isSpear(itemType);
        }
    };

    public abstract boolean canBeAppliedTo(ItemType<?> itemType);
}
