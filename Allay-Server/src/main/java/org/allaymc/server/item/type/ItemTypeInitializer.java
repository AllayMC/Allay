package org.allaymc.server.item.type;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemCauldronStack;
import org.allaymc.api.item.interfaces.boots.ItemChainmailBootsStack;
import org.allaymc.api.item.interfaces.boots.ItemDiamondBootsStack;
import org.allaymc.api.item.interfaces.boots.ItemGoldenBootsStack;
import org.allaymc.api.item.interfaces.boots.ItemIronBootsStack;
import org.allaymc.api.item.interfaces.boots.ItemLeatherBootsStack;
import org.allaymc.api.item.interfaces.boots.ItemNetheriteBootsStack;
import org.allaymc.api.item.interfaces.chestplate.ItemChainmailChestplateStack;
import org.allaymc.api.item.interfaces.chestplate.ItemDiamondChestplateStack;
import org.allaymc.api.item.interfaces.chestplate.ItemGoldenChestplateStack;
import org.allaymc.api.item.interfaces.chestplate.ItemIronChestplateStack;
import org.allaymc.api.item.interfaces.chestplate.ItemLeatherChestplateStack;
import org.allaymc.api.item.interfaces.chestplate.ItemNetheriteChestplateStack;
import org.allaymc.api.item.interfaces.helmet.ItemChainmailHelmetStack;
import org.allaymc.api.item.interfaces.helmet.ItemDiamondHelmetStack;
import org.allaymc.api.item.interfaces.helmet.ItemGoldenHelmetStack;
import org.allaymc.api.item.interfaces.helmet.ItemIronHelmetStack;
import org.allaymc.api.item.interfaces.helmet.ItemLeatherHelmetStack;
import org.allaymc.api.item.interfaces.helmet.ItemNetheriteHelmetStack;
import org.allaymc.api.item.interfaces.helmet.ItemTurtleHelmetStack;
import org.allaymc.api.item.interfaces.leggings.ItemChainmailLeggingsStack;
import org.allaymc.api.item.interfaces.leggings.ItemDiamondLeggingsStack;
import org.allaymc.api.item.interfaces.leggings.ItemGoldenLeggingsStack;
import org.allaymc.api.item.interfaces.leggings.ItemIronLeggingsStack;
import org.allaymc.api.item.interfaces.leggings.ItemLeatherLeggingsStack;
import org.allaymc.api.item.interfaces.leggings.ItemNetheriteLeggingsStack;
import org.allaymc.api.item.interfaces.shulkerbox.*;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.air.ItemAirBaseComponentImpl;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;
import org.allaymc.server.item.component.cauldron.ItemCauldronBaseComponentImpl;
import org.allaymc.server.item.component.common.ItemItemStorableComponentImpl;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
public final class ItemTypeInitializer {

    public static void initShulkerBox() {
        ItemTypes.BLACK_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemBlackShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.BLACK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BLUE_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemBlueShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BROWN_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemBrownShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.BROWN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.CYAN_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemCyanShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.CYAN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GRAY_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemGrayShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GREEN_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemGreenShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.GREEN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_BLUE_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemLightBlueShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.LIGHT_BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_GRAY_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemLightGrayShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.LIGHT_GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIME_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemLimeShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.LIME_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.MAGENTA_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemMagentaShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.MAGENTA_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.ORANGE_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemOrangeShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.ORANGE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PINK_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemPinkShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.PINK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PURPLE_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemPurpleShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.PURPLE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.RED_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemRedShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.RED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.UNDYED_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemUndyedShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.UNDYED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.WHITE_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemWhiteShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.WHITE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.YELLOW_SHULKER_BOX_TYPE = ItemTypeBuilder
                .builder(ItemYellowShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.YELLOW_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
    }

    public static void initAir() {
        ItemTypes.AIR_TYPE = ItemTypeBuilder
                .builder(ItemAirStack.class)
                .vanillaItem(VanillaItemId.AIR)
                .addComponent(ItemAirBaseComponentImpl::new, ItemAirBaseComponentImpl.class)
                .build();
    }

    public static void initChainmailHelmet() {
        ItemTypes.CHAINMAIL_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemChainmailHelmetStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initDiamondHelmet() {
        ItemTypes.DIAMOND_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemDiamondHelmetStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initGoldenHelmet() {
        ItemTypes.GOLDEN_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemGoldenHelmetStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initIronHelmet() {
        ItemTypes.IRON_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemIronHelmetStack.class)
                .vanillaItem(VanillaItemId.IRON_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeatherHelmet() {
        ItemTypes.LEATHER_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemLeatherHelmetStack.class)
                .vanillaItem(VanillaItemId.LEATHER_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initNetheriteHelmet() {
        ItemTypes.NETHERITE_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteHelmetStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initTurtleHelmet() {
        ItemTypes.TURTLE_HELMET_TYPE = ItemTypeBuilder
                .builder(ItemTurtleHelmetStack.class)
                .vanillaItem(VanillaItemId.TURTLE_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initChainmailChestplate() {
        ItemTypes.CHAINMAIL_CHESTPLATE_TYPE = ItemTypeBuilder
                .builder(ItemChainmailChestplateStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_CHESTPLATE)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initDiamondChestplate() {
        ItemTypes.DIAMOND_CHESTPLATE_TYPE = ItemTypeBuilder
                .builder(ItemDiamondChestplateStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_CHESTPLATE)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initGoldenChestplate() {
        ItemTypes.GOLDEN_CHESTPLATE_TYPE = ItemTypeBuilder
                .builder(ItemGoldenChestplateStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_CHESTPLATE)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initIronChestplate() {
        ItemTypes.IRON_CHESTPLATE_TYPE = ItemTypeBuilder
                .builder(ItemIronChestplateStack.class)
                .vanillaItem(VanillaItemId.IRON_CHESTPLATE)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeatherChestplate() {
        ItemTypes.LEATHER_CHESTPLATE_TYPE = ItemTypeBuilder
                .builder(ItemLeatherChestplateStack.class)
                .vanillaItem(VanillaItemId.LEATHER_CHESTPLATE)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initNetheriteChestplate() {
        ItemTypes.NETHERITE_CHESTPLATE_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteChestplateStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_CHESTPLATE)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initChainmailLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS_TYPE = ItemTypeBuilder
                .builder(ItemChainmailLeggingsStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_LEGGINGS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initDiamondLeggings() {
        ItemTypes.DIAMOND_LEGGINGS_TYPE = ItemTypeBuilder
                .builder(ItemDiamondLeggingsStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_LEGGINGS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initGoldenLeggings() {
        ItemTypes.GOLDEN_LEGGINGS_TYPE = ItemTypeBuilder
                .builder(ItemGoldenLeggingsStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_LEGGINGS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initIronLeggings() {
        ItemTypes.IRON_LEGGINGS_TYPE = ItemTypeBuilder
                .builder(ItemIronLeggingsStack.class)
                .vanillaItem(VanillaItemId.IRON_LEGGINGS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeatherLeggings() {
        ItemTypes.LEATHER_LEGGINGS_TYPE = ItemTypeBuilder
                .builder(ItemLeatherLeggingsStack.class)
                .vanillaItem(VanillaItemId.LEATHER_LEGGINGS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initNetheriteLeggings() {
        ItemTypes.NETHERITE_LEGGINGS_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteLeggingsStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_LEGGINGS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initChainmailBoots() {
        ItemTypes.CHAINMAIL_BOOTS_TYPE = ItemTypeBuilder
                .builder(ItemChainmailBootsStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_BOOTS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initDiamondBoots() {
        ItemTypes.DIAMOND_BOOTS_TYPE = ItemTypeBuilder
                .builder(ItemDiamondBootsStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_BOOTS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initGoldenBoots() {
        ItemTypes.GOLDEN_BOOTS_TYPE = ItemTypeBuilder
                .builder(ItemGoldenBootsStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_BOOTS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initIronBoots() {
        ItemTypes.IRON_BOOTS_TYPE = ItemTypeBuilder
                .builder(ItemIronBootsStack.class)
                .vanillaItem(VanillaItemId.IRON_BOOTS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeatherBoots() {
        ItemTypes.LEATHER_BOOTS_TYPE = ItemTypeBuilder
                .builder(ItemLeatherBootsStack.class)
                .vanillaItem(VanillaItemId.LEATHER_BOOTS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initNetheriteBoots() {
        ItemTypes.NETHERITE_BOOTS_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteBootsStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_BOOTS)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initCauldron() {
        ItemTypes.CAULDRON_TYPE = ItemTypeBuilder
                .builder(ItemCauldronStack.class)
                .vanillaItem(VanillaItemId.CAULDRON)
                .addComponent(ItemCauldronBaseComponentImpl::new, ItemCauldronBaseComponentImpl.class)
                .build();
    }
}
