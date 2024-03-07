package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.boots.*;
import org.allaymc.api.item.interfaces.chestplate.*;
import org.allaymc.api.item.interfaces.helmet.*;
import org.allaymc.api.item.interfaces.leggings.*;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * Allay Project 07/03/2024
 *
 * @author IWareQ
 */
public interface ItemArmorStackInitializer {

    static void init() {
        initHelmets();
        initChestplates();
        initLeggings();
        initBoots();
    }

    static void initHelmets() {
        ItemTypes.CHAINMAIL_HELMET_TYPE = init(ItemChainmailHelmetStack.class, VanillaItemId.CHAINMAIL_HELMET);
        ItemTypes.DIAMOND_HELMET_TYPE = init(ItemDiamondHelmetStack.class, VanillaItemId.DIAMOND_HELMET);
        ItemTypes.GOLDEN_HELMET_TYPE = init(ItemGoldenHelmetStack.class, VanillaItemId.GOLDEN_HELMET);
        ItemTypes.IRON_HELMET_TYPE = init(ItemIronHelmetStack.class, VanillaItemId.IRON_HELMET);
        ItemTypes.LEATHER_HELMET_TYPE = init(ItemLeatherHelmetStack.class, VanillaItemId.LEATHER_HELMET);
        ItemTypes.NETHERITE_HELMET_TYPE = init(ItemNetheriteHelmetStack.class, VanillaItemId.NETHERITE_HELMET);
    }

    static void initChestplates() {
        ItemTypes.CHAINMAIL_CHESTPLATE_TYPE = init(ItemChainmailChestplateStack.class, VanillaItemId.CHAINMAIL_CHESTPLATE);
        ItemTypes.DIAMOND_CHESTPLATE_TYPE = init(ItemDiamondChestplateStack.class, VanillaItemId.DIAMOND_CHESTPLATE);
        ItemTypes.GOLDEN_CHESTPLATE_TYPE = init(ItemGoldenChestplateStack.class, VanillaItemId.GOLDEN_CHESTPLATE);
        ItemTypes.IRON_CHESTPLATE_TYPE = init(ItemIronChestplateStack.class, VanillaItemId.IRON_CHESTPLATE);
        ItemTypes.LEATHER_CHESTPLATE_TYPE = init(ItemLeatherChestplateStack.class, VanillaItemId.LEATHER_CHESTPLATE);
        ItemTypes.NETHERITE_CHESTPLATE_TYPE = init(ItemNetheriteChestplateStack.class, VanillaItemId.NETHERITE_CHESTPLATE);
    }

    static void initLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS_TYPE = init(ItemChainmailLeggingsStack.class, VanillaItemId.CHAINMAIL_LEGGINGS);
        ItemTypes.DIAMOND_LEGGINGS_TYPE = init(ItemDiamondLeggingsStack.class, VanillaItemId.DIAMOND_LEGGINGS);
        ItemTypes.GOLDEN_LEGGINGS_TYPE = init(ItemGoldenLeggingsStack.class, VanillaItemId.GOLDEN_LEGGINGS);
        ItemTypes.IRON_LEGGINGS_TYPE = init(ItemIronLeggingsStack.class, VanillaItemId.IRON_LEGGINGS);
        ItemTypes.LEATHER_LEGGINGS_TYPE = init(ItemLeatherLeggingsStack.class, VanillaItemId.LEATHER_LEGGINGS);
        ItemTypes.NETHERITE_LEGGINGS_TYPE = init(ItemNetheriteLeggingsStack.class, VanillaItemId.NETHERITE_LEGGINGS);
    }

    static void initBoots() {
        ItemTypes.CHAINMAIL_BOOTS_TYPE = init(ItemChainmailBootsStack.class, VanillaItemId.CHAINMAIL_BOOTS);
        ItemTypes.DIAMOND_BOOTS_TYPE = init(ItemDiamondBootsStack.class, VanillaItemId.DIAMOND_BOOTS);
        ItemTypes.GOLDEN_BOOTS_TYPE = init(ItemGoldenBootsStack.class, VanillaItemId.GOLDEN_BOOTS);
        ItemTypes.IRON_BOOTS_TYPE = init(ItemIronBootsStack.class, VanillaItemId.IRON_BOOTS);
        ItemTypes.LEATHER_BOOTS_TYPE = init(ItemLeatherBootsStack.class, VanillaItemId.LEATHER_BOOTS);
        ItemTypes.NETHERITE_BOOTS_TYPE = init(ItemNetheriteBootsStack.class, VanillaItemId.NETHERITE_BOOTS);
    }

    static <T extends ItemStack> ItemType<T> init(Class<T> type, VanillaItemId itemId) {
        return ItemTypeBuilder.builder(type).vanillaItem(itemId)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }
}
