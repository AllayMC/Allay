package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.tool.ItemAxeComponent;
import org.allaymc.api.item.component.tool.ItemHoeComponent;
import org.allaymc.api.item.component.tool.ItemPickaxeComponent;
import org.allaymc.api.item.component.tool.ItemShovelComponent;
import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.interfaces.axe.*;
import org.allaymc.api.item.interfaces.boots.*;
import org.allaymc.api.item.interfaces.chestplate.*;
import org.allaymc.api.item.interfaces.egg.*;
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
import org.allaymc.server.item.component.food.*;
import org.allaymc.server.item.component.spawnegg.ItemEvokerSpawnEggBaseComponentImpl;
import org.allaymc.server.item.component.spawnegg.ItemSpawnEggBaseComponentImpl;
import org.allaymc.server.item.component.spawnegg.ItemTropicalFishSpawnEggBaseComponentImpl;
import org.allaymc.server.item.component.tool.*;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class ItemTypeInitializer {
    public static void initTropicalFish() {
        ItemTypes.TROPICAL_FISH_TYPE = ItemTypeBuilder
                .builder(ItemTropicalFishStack.class)
                .vanillaItem(VanillaItemId.TROPICAL_FISH)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSweetBerries() {
        ItemTypes.SWEET_BERRIES_TYPE = ItemTypeBuilder
                .builder(ItemSweetBerriesStack.class)
                .vanillaItem(VanillaItemId.SWEET_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedBeef() {
        ItemTypes.COOKED_BEEF_TYPE = ItemTypeBuilder
                .builder(ItemCookedBeefStack.class)
                .vanillaItem(VanillaItemId.COOKED_BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSpiderEye() {
        ItemTypes.SPIDER_EYE_TYPE = ItemTypeBuilder
                .builder(ItemSpiderEyeStack.class)
                .vanillaItem(VanillaItemId.SPIDER_EYE)
                .addComponent(ItemSpiderEyeBaseComponentImpl::new, ItemSpiderEyeBaseComponentImpl.class)
                .build();
    }

    public static void initRottenFlesh() {
        ItemTypes.ROTTEN_FLESH_TYPE = ItemTypeBuilder
                .builder(ItemRottenFleshStack.class)
                .vanillaItem(VanillaItemId.ROTTEN_FLESH)
                .addComponent(ItemRottenFleshBaseComponentImpl::new, ItemRottenFleshBaseComponentImpl.class)
                .build();
    }

    public static void initSalmon() {
        ItemTypes.SALMON_TYPE = ItemTypeBuilder
                .builder(ItemSalmonStack.class)
                .vanillaItem(VanillaItemId.SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initRabbit() {
        ItemTypes.RABBIT_TYPE = ItemTypeBuilder
                .builder(ItemRabbitStack.class)
                .vanillaItem(VanillaItemId.RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPorkchop() {
        ItemTypes.PORKCHOP_TYPE = ItemTypeBuilder
                .builder(ItemPorkchopStack.class)
                .vanillaItem(VanillaItemId.PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initMutton() {
        ItemTypes.MUTTON_TYPE = ItemTypeBuilder
                .builder(ItemMuttonStack.class)
                .vanillaItem(VanillaItemId.MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCod() {
        ItemTypes.COD_TYPE = ItemTypeBuilder
                .builder(ItemCodStack.class)
                .vanillaItem(VanillaItemId.COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initChicken() {
        ItemTypes.CHICKEN_TYPE = ItemTypeBuilder
                .builder(ItemChickenStack.class)
                .vanillaItem(VanillaItemId.CHICKEN)
                .addComponent(ItemChickenBaseComponentImpl::new, ItemChickenBaseComponentImpl.class)
                .build();
    }

    public static void initBeef() {
        ItemTypes.BEEF_TYPE = ItemTypeBuilder
                .builder(ItemBeefStack.class)
                .vanillaItem(VanillaItemId.BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initRabbitStew() {
        ItemTypes.RABBIT_STEW_TYPE = ItemTypeBuilder
                .builder(ItemRabbitStewStack.class)
                .vanillaItem(VanillaItemId.RABBIT_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(10, 12f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPumpkinPie() {
        ItemTypes.PUMPKIN_PIE_TYPE = ItemTypeBuilder
                .builder(ItemPumpkinPieStack.class)
                .vanillaItem(VanillaItemId.PUMPKIN_PIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 4.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPufferfish() {
        ItemTypes.PUFFERFISH_TYPE = ItemTypeBuilder
                .builder(ItemPufferfishStack.class)
                .vanillaItem(VanillaItemId.PUFFERFISH)
                .addComponent(ItemPufferfishBaseComponentImpl::new, ItemPufferfishBaseComponentImpl.class)
                .build();
    }

    public static void initPotato() {
        ItemTypes.POTATO_TYPE = ItemTypeBuilder
                .builder(ItemPotatoStack.class)
                .vanillaItem(VanillaItemId.POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPoisonousPotato() {
        ItemTypes.POISONOUS_POTATO_TYPE = ItemTypeBuilder
                .builder(ItemPoisonousPotatoStack.class)
                .vanillaItem(VanillaItemId.POISONOUS_POTATO)
                .addComponent(ItemPoisonousPotatoBaseComponentImpl::new, ItemPoisonousPotatoBaseComponentImpl.class)
                .build();
    }

    public static void initMushroomStew() {
        ItemTypes.MUSHROOM_STEW_TYPE = ItemTypeBuilder
                .builder(ItemMushroomStewStack.class)
                .vanillaItem(VanillaItemId.MUSHROOM_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initMelonSlice() {
        ItemTypes.MELON_SLICE_TYPE = ItemTypeBuilder
                .builder(ItemMelonSliceStack.class)
                .vanillaItem(VanillaItemId.MELON_SLICE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initHoneyBottle() {
        ItemTypes.HONEY_BOTTLE_TYPE = ItemTypeBuilder
                .builder(ItemHoneyBottleStack.class)
                .vanillaItem(VanillaItemId.HONEY_BOTTLE)
                .addComponent(ItemHoneyBottleBaseComponentImpl::new, ItemHoneyBottleBaseComponentImpl.class)
                .build();
    }

    public static void initGoldenCarrot() {
        ItemTypes.GOLDEN_CARROT_TYPE = ItemTypeBuilder
                .builder(ItemGoldenCarrotStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 14.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initGlowBerries() {
        ItemTypes.GLOW_BERRIES_TYPE = ItemTypeBuilder
                .builder(ItemGlowBerriesStack.class)
                .vanillaItem(VanillaItemId.GLOW_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initGoldenApple() {
        ItemTypes.GOLDEN_APPLE_TYPE = ItemTypeBuilder
                .builder(ItemGoldenAppleStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_APPLE)
                .addComponent(ItemGoldenAppleBaseComponentImpl::new, ItemGoldenAppleBaseComponentImpl.class)
                .build();
    }

    public static void initEnchantedGoldenApple() {
        ItemTypes.ENCHANTED_GOLDEN_APPLE_TYPE = ItemTypeBuilder
                .builder(ItemEnchantedGoldenAppleStack.class)
                .vanillaItem(VanillaItemId.ENCHANTED_GOLDEN_APPLE)
                .addComponent(ItemEnchantedGoldenAppleBaseComponentImpl::new, ItemEnchantedGoldenAppleBaseComponentImpl.class)
                .build();
    }

    public static void initDriedKelp() {
        ItemTypes.DRIED_KELP_TYPE = ItemTypeBuilder
                .builder(ItemDriedKelpStack.class)
                .vanillaItem(VanillaItemId.DRIED_KELP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f, 16),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookie() {
        ItemTypes.COOKIE_TYPE = ItemTypeBuilder
                .builder(ItemCookieStack.class)
                .vanillaItem(VanillaItemId.COOKIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedSalmon() {
        ItemTypes.COOKED_SALMON_TYPE = ItemTypeBuilder
                .builder(ItemCookedSalmonStack.class)
                .vanillaItem(VanillaItemId.COOKED_SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedRabbit() {
        ItemTypes.COOKED_RABBIT_TYPE = ItemTypeBuilder
                .builder(ItemCookedRabbitStack.class)
                .vanillaItem(VanillaItemId.COOKED_RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedPorkchop() {
        ItemTypes.COOKED_PORKCHOP_TYPE = ItemTypeBuilder
                .builder(ItemCookedPorkchopStack.class)
                .vanillaItem(VanillaItemId.COOKED_PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedMutton() {
        ItemTypes.COOKED_MUTTON_TYPE = ItemTypeBuilder
                .builder(ItemCookedMuttonStack.class)
                .vanillaItem(VanillaItemId.COOKED_MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedCod() {
        ItemTypes.COOKED_COD_TYPE = ItemTypeBuilder
                .builder(ItemCookedCodStack.class)
                .vanillaItem(VanillaItemId.COOKED_COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedChicken() {
        ItemTypes.COOKED_CHICKEN_TYPE = ItemTypeBuilder
                .builder(ItemCookedChickenStack.class)
                .vanillaItem(VanillaItemId.COOKED_CHICKEN)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initChorusFruit() {
        ItemTypes.CHORUS_FRUIT_TYPE = ItemTypeBuilder
                .builder(ItemChorusFruitStack.class)
                .vanillaItem(VanillaItemId.CHORUS_FRUIT)
                .addComponent(ItemChorusFruitBaseComponentImpl::new, ItemChorusFruitBaseComponentImpl.class)
                .build();
    }

    public static void initCarrot() {
        ItemTypes.CARROT_TYPE = ItemTypeBuilder
                .builder(ItemCarrotStack.class)
                .vanillaItem(VanillaItemId.CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 3.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBread() {
        ItemTypes.BREAD_TYPE = ItemTypeBuilder
                .builder(ItemBreadStack.class)
                .vanillaItem(VanillaItemId.BREAD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBeetrootSoup() {
        ItemTypes.BEETROOT_SOUP_TYPE = ItemTypeBuilder
                .builder(ItemBeetrootSoupStack.class)
                .vanillaItem(VanillaItemId.BEETROOT_SOUP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBeetroot() {
        ItemTypes.BEETROOT_TYPE = ItemTypeBuilder
                .builder(ItemBeetrootStack.class)
                .vanillaItem(VanillaItemId.BEETROOT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBakedPotato() {
        ItemTypes.BAKED_POTATO_TYPE = ItemTypeBuilder
                .builder(ItemBakedPotatoStack.class)
                .vanillaItem(VanillaItemId.BAKED_POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initApple() {
        ItemTypes.APPLE_TYPE = ItemTypeBuilder
                .builder(ItemAppleStack.class)
                .vanillaItem(VanillaItemId.APPLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 2.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSpawnEgg() {
        ItemTypes.AGENT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemAgentSpawnEggStack.class)
                .vanillaItem(VanillaItemId.AGENT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ALLAY_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemAllaySpawnEggStack.class)
                .vanillaItem(VanillaItemId.ALLAY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ARMADILLO_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemArmadilloSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ARMADILLO_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.AXOLOTL_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemAxolotlSpawnEggStack.class)
                .vanillaItem(VanillaItemId.AXOLOTL_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BAT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemBatSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BEE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemBeeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BEE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BLAZE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemBlazeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BLAZE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BOGGED_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemBoggedSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BOGGED_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BREEZE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemBreezeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BREEZE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAMEL_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemCamelSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CAMEL_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemCatSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAVE_SPIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemCaveSpiderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CAVE_SPIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CHICKEN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemChickenSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CHICKEN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.COD_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemCodSpawnEggStack.class)
                .vanillaItem(VanillaItemId.COD_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.COW_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemCowSpawnEggStack.class)
                .vanillaItem(VanillaItemId.COW_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CREEPER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemCreeperSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CREEPER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DOLPHIN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemDolphinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.DOLPHIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DONKEY_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemDonkeySpawnEggStack.class)
                .vanillaItem(VanillaItemId.DONKEY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DROWNED_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemDrownedSpawnEggStack.class)
                .vanillaItem(VanillaItemId.DROWNED_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ELDER_GUARDIAN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemElderGuardianSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ELDER_GUARDIAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDER_DRAGON_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemEnderDragonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ENDER_DRAGON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDERMAN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemEndermanSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ENDERMAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDERMITE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemEndermiteSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ENDERMITE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.EVOKER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemEvokerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.EVOKER_SPAWN_EGG)
                .addComponent(ItemEvokerSpawnEggBaseComponentImpl::new, ItemEvokerSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.FOX_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemFoxSpawnEggStack.class)
                .vanillaItem(VanillaItemId.FOX_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.FROG_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemFrogSpawnEggStack.class)
                .vanillaItem(VanillaItemId.FROG_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GHAST_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemGhastSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GHAST_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GLOW_SQUID_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemGlowSquidSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GLOW_SQUID_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GOAT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemGoatSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GOAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GUARDIAN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemGuardianSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GUARDIAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HOGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemHoglinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.HOGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemHorseSpawnEggStack.class)
                .vanillaItem(VanillaItemId.HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HUSK_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemHuskSpawnEggStack.class)
                .vanillaItem(VanillaItemId.HUSK_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_GOLEM_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemIronGolemSpawnEggStack.class)
                .vanillaItem(VanillaItemId.IRON_GOLEM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.LLAMA_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemLlamaSpawnEggStack.class)
                .vanillaItem(VanillaItemId.LLAMA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MAGMA_CUBE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemMagmaCubeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.MAGMA_CUBE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MOOSHROOM_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemMooshroomSpawnEggStack.class)
                .vanillaItem(VanillaItemId.MOOSHROOM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MULE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemMuleSpawnEggStack.class)
                .vanillaItem(VanillaItemId.MULE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.NPC_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemNpcSpawnEggStack.class)
                .vanillaItem(VanillaItemId.NPC_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.OCELOT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemOcelotSpawnEggStack.class)
                .vanillaItem(VanillaItemId.OCELOT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PANDA_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPandaSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PANDA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PARROT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemParrotSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PARROT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PHANTOM_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPhantomSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PHANTOM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIG_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPigSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PIG_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIGLIN_BRUTE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPiglinBruteSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PIGLIN_BRUTE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPiglinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PIGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPillagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.POLAR_BEAR_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPolarBearSpawnEggStack.class)
                .vanillaItem(VanillaItemId.POLAR_BEAR_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PUFFERFISH_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemPufferfishSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PUFFERFISH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.RABBIT_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemRabbitSpawnEggStack.class)
                .vanillaItem(VanillaItemId.RABBIT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.RAVAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemRavagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.RAVAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SALMON_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSalmonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SALMON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SHEEP_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSheepSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SHEEP_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SHULKER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemShulkerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SHULKER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SILVERFISH_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSilverfishSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SILVERFISH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SKELETON_HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSkeletonHorseSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SKELETON_HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SKELETON_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSkeletonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SKELETON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SLIME_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSlimeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SLIME_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SNIFFER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSnifferSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SNIFFER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SNOW_GOLEM_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSnowGolemSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SNOW_GOLEM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SPIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSpiderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SPIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SQUID_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemSquidSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SQUID_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.STRAY_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemStraySpawnEggStack.class)
                .vanillaItem(VanillaItemId.STRAY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.STRIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemStriderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.STRIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TADPOLE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemTadpoleSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TADPOLE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TRADER_LLAMA_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemTraderLlamaSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TRADER_LLAMA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TROPICAL_FISH_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemTropicalFishSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TROPICAL_FISH_SPAWN_EGG)
                .addComponent(ItemTropicalFishSpawnEggBaseComponentImpl::new, ItemTropicalFishSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TURTLE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemTurtleSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TURTLE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VEX_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemVexSpawnEggStack.class)
                .vanillaItem(VanillaItemId.VEX_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemVillagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.VILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VINDICATOR_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemVindicatorSpawnEggStack.class)
                .vanillaItem(VanillaItemId.VINDICATOR_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WANDERING_TRADER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemWanderingTraderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WANDERING_TRADER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WARDEN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemWardenSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WARDEN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITCH_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemWitchSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WITCH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITHER_SKELETON_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemWitherSkeletonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WITHER_SKELETON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITHER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemWitherSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WITHER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WOLF_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemWolfSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WOLF_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemZoglinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemZombieHorseSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemZombiePigmanSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_PIGMAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemZombieSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
                .builder(ItemZombieVillagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_VILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
    }

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
