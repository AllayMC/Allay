package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.tool.ItemAxeComponent;
import org.allaymc.api.item.component.tool.ItemHoeComponent;
import org.allaymc.api.item.component.tool.ItemPickaxeComponent;
import org.allaymc.api.item.component.tool.ItemShovelComponent;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemCauldronStack;
import org.allaymc.api.item.interfaces.axe.*;
import org.allaymc.api.item.interfaces.boots.*;
import org.allaymc.api.item.interfaces.chestplate.*;
import org.allaymc.api.item.interfaces.helmet.*;
import org.allaymc.api.item.interfaces.hoe.*;
import org.allaymc.api.item.interfaces.leggings.*;
import org.allaymc.api.item.interfaces.pickaxe.*;
import org.allaymc.api.item.interfaces.shovel.*;
import org.allaymc.api.item.interfaces.shulkerbox.*;
import org.allaymc.api.item.interfaces.sword.*;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.air.ItemAirBaseComponentImpl;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;
import org.allaymc.server.item.component.cauldron.ItemCauldronBaseComponentImpl;
import org.allaymc.server.item.component.common.ItemItemStorableComponentImpl;
import org.allaymc.server.item.component.tool.*;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class ItemTypeInitializer {

    public static void initSword() {
        ItemTypes.DIAMOND_SWORD_TYPE = ItemTypeBuilder
                .builder(ItemDiamondSwordStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_SWORD_TYPE = ItemTypeBuilder
                .builder(ItemGoldenSwordStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.IRON_SWORD_TYPE = ItemTypeBuilder
                .builder(ItemIronSwordStack.class)
                .vanillaItem(VanillaItemId.IRON_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_SWORD_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteSwordStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.STONE_SWORD_TYPE = ItemTypeBuilder
                .builder(ItemStoneSwordStack.class)
                .vanillaItem(VanillaItemId.STONE_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.WOODEN_SWORD_TYPE = ItemTypeBuilder
                .builder(ItemWoodenSwordStack.class)
                .vanillaItem(VanillaItemId.WOODEN_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
    }

    public static void initPickaxe() {
        ItemTypes.DIAMOND_PICKAXE_TYPE = ItemTypeBuilder
                .builder(ItemDiamondPickaxeStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.GOLDEN_PICKAXE_TYPE = ItemTypeBuilder
                .builder(ItemGoldenPickaxeStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.IRON_PICKAXE_TYPE = ItemTypeBuilder
                .builder(ItemIronPickaxeStack.class)
                .vanillaItem(VanillaItemId.IRON_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.NETHERITE_PICKAXE_TYPE = ItemTypeBuilder
                .builder(ItemNetheritePickaxeStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.STONE_PICKAXE_TYPE = ItemTypeBuilder
                .builder(ItemStonePickaxeStack.class)
                .vanillaItem(VanillaItemId.STONE_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.WOODEN_PICKAXE_TYPE = ItemTypeBuilder
                .builder(ItemWoodenPickaxeStack.class)
                .vanillaItem(VanillaItemId.WOODEN_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
    }

    public static void initAxe() {
        ItemTypes.DIAMOND_AXE_TYPE = ItemTypeBuilder
                .builder(ItemDiamondAxeStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.GOLDEN_AXE_TYPE = ItemTypeBuilder
                .builder(ItemGoldenAxeStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.IRON_AXE_TYPE = ItemTypeBuilder
                .builder(ItemIronAxeStack.class)
                .vanillaItem(VanillaItemId.IRON_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.NETHERITE_AXE_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteAxeStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.STONE_AXE_TYPE = ItemTypeBuilder
                .builder(ItemStoneAxeStack.class)
                .vanillaItem(VanillaItemId.STONE_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.WOODEN_AXE_TYPE = ItemTypeBuilder
                .builder(ItemWoodenAxeStack.class)
                .vanillaItem(VanillaItemId.WOODEN_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
    }

    public static void initShovel() {
        ItemTypes.DIAMOND_SHOVEL_TYPE = ItemTypeBuilder
                .builder(ItemDiamondShovelStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.GOLDEN_SHOVEL_TYPE = ItemTypeBuilder
                .builder(ItemGoldenShovelStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.IRON_SHOVEL_TYPE = ItemTypeBuilder
                .builder(ItemIronShovelStack.class)
                .vanillaItem(VanillaItemId.IRON_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.NETHERITE_SHOVEL_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteShovelStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.STONE_SHOVEL_TYPE = ItemTypeBuilder
                .builder(ItemStoneShovelStack.class)
                .vanillaItem(VanillaItemId.STONE_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.WOODEN_SHOVEL_TYPE = ItemTypeBuilder
                .builder(ItemWoodenShovelStack.class)
                .vanillaItem(VanillaItemId.WOODEN_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
    }

    public static void initHoe() {
        ItemTypes.DIAMOND_HOE_TYPE = ItemTypeBuilder
                .builder(ItemDiamondHoeStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.GOLDEN_HOE_TYPE = ItemTypeBuilder
                .builder(ItemGoldenHoeStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.IRON_HOE_TYPE = ItemTypeBuilder
                .builder(ItemIronHoeStack.class)
                .vanillaItem(VanillaItemId.IRON_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.NETHERITE_HOE_TYPE = ItemTypeBuilder
                .builder(ItemNetheriteHoeStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.STONE_HOE_TYPE = ItemTypeBuilder
                .builder(ItemStoneHoeStack.class)
                .vanillaItem(VanillaItemId.STONE_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.WOODEN_HOE_TYPE = ItemTypeBuilder
                .builder(ItemWoodenHoeStack.class)
                .vanillaItem(VanillaItemId.WOODEN_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
    }

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
