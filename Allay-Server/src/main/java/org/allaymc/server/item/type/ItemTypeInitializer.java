package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.ItemArmorBaseComponent.ArmorType;
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
import org.allaymc.api.item.interfaces.sign.*;
import org.allaymc.api.item.interfaces.sword.*;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.ItemAirBaseComponentImpl;
import org.allaymc.server.item.component.ItemArmorBaseComponentImpl;
import org.allaymc.server.item.component.ItemCauldronBaseComponentImpl;
import org.allaymc.server.item.component.ItemSignBaseComponentImpl;
import org.allaymc.server.item.component.ItemItemStorableComponentImpl;
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
        ItemTypes.TROPICAL_FISH = ItemTypeBuilder
                .builder(ItemTropicalFishStack.class)
                .vanillaItem(VanillaItemId.TROPICAL_FISH)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSweetBerries() {
        ItemTypes.SWEET_BERRIES = ItemTypeBuilder
                .builder(ItemSweetBerriesStack.class)
                .vanillaItem(VanillaItemId.SWEET_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedBeef() {
        ItemTypes.COOKED_BEEF = ItemTypeBuilder
                .builder(ItemCookedBeefStack.class)
                .vanillaItem(VanillaItemId.COOKED_BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSpiderEye() {
        ItemTypes.SPIDER_EYE = ItemTypeBuilder
                .builder(ItemSpiderEyeStack.class)
                .vanillaItem(VanillaItemId.SPIDER_EYE)
                .addComponent(ItemSpiderEyeBaseComponentImpl::new, ItemSpiderEyeBaseComponentImpl.class)
                .build();
    }

    public static void initRottenFlesh() {
        ItemTypes.ROTTEN_FLESH = ItemTypeBuilder
                .builder(ItemRottenFleshStack.class)
                .vanillaItem(VanillaItemId.ROTTEN_FLESH)
                .addComponent(ItemRottenFleshBaseComponentImpl::new, ItemRottenFleshBaseComponentImpl.class)
                .build();
    }

    public static void initSalmon() {
        ItemTypes.SALMON = ItemTypeBuilder
                .builder(ItemSalmonStack.class)
                .vanillaItem(VanillaItemId.SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initRabbit() {
        ItemTypes.RABBIT = ItemTypeBuilder
                .builder(ItemRabbitStack.class)
                .vanillaItem(VanillaItemId.RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPorkchop() {
        ItemTypes.PORKCHOP = ItemTypeBuilder
                .builder(ItemPorkchopStack.class)
                .vanillaItem(VanillaItemId.PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initMutton() {
        ItemTypes.MUTTON = ItemTypeBuilder
                .builder(ItemMuttonStack.class)
                .vanillaItem(VanillaItemId.MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCod() {
        ItemTypes.COD = ItemTypeBuilder
                .builder(ItemCodStack.class)
                .vanillaItem(VanillaItemId.COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initChicken() {
        ItemTypes.CHICKEN = ItemTypeBuilder
                .builder(ItemChickenStack.class)
                .vanillaItem(VanillaItemId.CHICKEN)
                .addComponent(ItemChickenBaseComponentImpl::new, ItemChickenBaseComponentImpl.class)
                .build();
    }

    public static void initBeef() {
        ItemTypes.BEEF = ItemTypeBuilder
                .builder(ItemBeefStack.class)
                .vanillaItem(VanillaItemId.BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initRabbitStew() {
        ItemTypes.RABBIT_STEW = ItemTypeBuilder
                .builder(ItemRabbitStewStack.class)
                .vanillaItem(VanillaItemId.RABBIT_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(10, 12f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPumpkinPie() {
        ItemTypes.PUMPKIN_PIE = ItemTypeBuilder
                .builder(ItemPumpkinPieStack.class)
                .vanillaItem(VanillaItemId.PUMPKIN_PIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 4.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPufferfish() {
        ItemTypes.PUFFERFISH = ItemTypeBuilder
                .builder(ItemPufferfishStack.class)
                .vanillaItem(VanillaItemId.PUFFERFISH)
                .addComponent(ItemPufferfishBaseComponentImpl::new, ItemPufferfishBaseComponentImpl.class)
                .build();
    }

    public static void initPotato() {
        ItemTypes.POTATO = ItemTypeBuilder
                .builder(ItemPotatoStack.class)
                .vanillaItem(VanillaItemId.POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initPoisonousPotato() {
        ItemTypes.POISONOUS_POTATO = ItemTypeBuilder
                .builder(ItemPoisonousPotatoStack.class)
                .vanillaItem(VanillaItemId.POISONOUS_POTATO)
                .addComponent(ItemPoisonousPotatoBaseComponentImpl::new, ItemPoisonousPotatoBaseComponentImpl.class)
                .build();
    }

    public static void initMushroomStew() {
        ItemTypes.MUSHROOM_STEW = ItemTypeBuilder
                .builder(ItemMushroomStewStack.class)
                .vanillaItem(VanillaItemId.MUSHROOM_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initMelonSlice() {
        ItemTypes.MELON_SLICE = ItemTypeBuilder
                .builder(ItemMelonSliceStack.class)
                .vanillaItem(VanillaItemId.MELON_SLICE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initHoneyBottle() {
        ItemTypes.HONEY_BOTTLE = ItemTypeBuilder
                .builder(ItemHoneyBottleStack.class)
                .vanillaItem(VanillaItemId.HONEY_BOTTLE)
                .addComponent(ItemHoneyBottleBaseComponentImpl::new, ItemHoneyBottleBaseComponentImpl.class)
                .build();
    }

    public static void initGoldenCarrot() {
        ItemTypes.GOLDEN_CARROT = ItemTypeBuilder
                .builder(ItemGoldenCarrotStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 14.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initGlowBerries() {
        ItemTypes.GLOW_BERRIES = ItemTypeBuilder
                .builder(ItemGlowBerriesStack.class)
                .vanillaItem(VanillaItemId.GLOW_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initGoldenApple() {
        ItemTypes.GOLDEN_APPLE = ItemTypeBuilder
                .builder(ItemGoldenAppleStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_APPLE)
                .addComponent(ItemGoldenAppleBaseComponentImpl::new, ItemGoldenAppleBaseComponentImpl.class)
                .build();
    }

    public static void initEnchantedGoldenApple() {
        ItemTypes.ENCHANTED_GOLDEN_APPLE = ItemTypeBuilder
                .builder(ItemEnchantedGoldenAppleStack.class)
                .vanillaItem(VanillaItemId.ENCHANTED_GOLDEN_APPLE)
                .addComponent(ItemEnchantedGoldenAppleBaseComponentImpl::new, ItemEnchantedGoldenAppleBaseComponentImpl.class)
                .build();
    }

    public static void initDriedKelp() {
        ItemTypes.DRIED_KELP = ItemTypeBuilder
                .builder(ItemDriedKelpStack.class)
                .vanillaItem(VanillaItemId.DRIED_KELP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f, 16),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookie() {
        ItemTypes.COOKIE = ItemTypeBuilder
                .builder(ItemCookieStack.class)
                .vanillaItem(VanillaItemId.COOKIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedSalmon() {
        ItemTypes.COOKED_SALMON = ItemTypeBuilder
                .builder(ItemCookedSalmonStack.class)
                .vanillaItem(VanillaItemId.COOKED_SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedRabbit() {
        ItemTypes.COOKED_RABBIT = ItemTypeBuilder
                .builder(ItemCookedRabbitStack.class)
                .vanillaItem(VanillaItemId.COOKED_RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedPorkchop() {
        ItemTypes.COOKED_PORKCHOP = ItemTypeBuilder
                .builder(ItemCookedPorkchopStack.class)
                .vanillaItem(VanillaItemId.COOKED_PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedMutton() {
        ItemTypes.COOKED_MUTTON = ItemTypeBuilder
                .builder(ItemCookedMuttonStack.class)
                .vanillaItem(VanillaItemId.COOKED_MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedCod() {
        ItemTypes.COOKED_COD = ItemTypeBuilder
                .builder(ItemCookedCodStack.class)
                .vanillaItem(VanillaItemId.COOKED_COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initCookedChicken() {
        ItemTypes.COOKED_CHICKEN = ItemTypeBuilder
                .builder(ItemCookedChickenStack.class)
                .vanillaItem(VanillaItemId.COOKED_CHICKEN)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initChorusFruit() {
        ItemTypes.CHORUS_FRUIT = ItemTypeBuilder
                .builder(ItemChorusFruitStack.class)
                .vanillaItem(VanillaItemId.CHORUS_FRUIT)
                .addComponent(ItemChorusFruitBaseComponentImpl::new, ItemChorusFruitBaseComponentImpl.class)
                .build();
    }

    public static void initCarrot() {
        ItemTypes.CARROT = ItemTypeBuilder
                .builder(ItemCarrotStack.class)
                .vanillaItem(VanillaItemId.CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 3.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBread() {
        ItemTypes.BREAD = ItemTypeBuilder
                .builder(ItemBreadStack.class)
                .vanillaItem(VanillaItemId.BREAD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBeetrootSoup() {
        ItemTypes.BEETROOT_SOUP = ItemTypeBuilder
                .builder(ItemBeetrootSoupStack.class)
                .vanillaItem(VanillaItemId.BEETROOT_SOUP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBeetroot() {
        ItemTypes.BEETROOT = ItemTypeBuilder
                .builder(ItemBeetrootStack.class)
                .vanillaItem(VanillaItemId.BEETROOT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initBakedPotato() {
        ItemTypes.BAKED_POTATO = ItemTypeBuilder
                .builder(ItemBakedPotatoStack.class)
                .vanillaItem(VanillaItemId.BAKED_POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initApple() {
        ItemTypes.APPLE = ItemTypeBuilder
                .builder(ItemAppleStack.class)
                .vanillaItem(VanillaItemId.APPLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 2.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSpawnEgg() {
        ItemTypes.AGENT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemAgentSpawnEggStack.class)
                .vanillaItem(VanillaItemId.AGENT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ALLAY_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemAllaySpawnEggStack.class)
                .vanillaItem(VanillaItemId.ALLAY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ARMADILLO_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemArmadilloSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ARMADILLO_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.AXOLOTL_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemAxolotlSpawnEggStack.class)
                .vanillaItem(VanillaItemId.AXOLOTL_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BAT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBatSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BEE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBeeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BEE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BLAZE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBlazeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BLAZE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BOGGED_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBoggedSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BOGGED_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BREEZE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemBreezeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.BREEZE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAMEL_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCamelSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CAMEL_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCatSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAVE_SPIDER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCaveSpiderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CAVE_SPIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CHICKEN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemChickenSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CHICKEN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.COD_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCodSpawnEggStack.class)
                .vanillaItem(VanillaItemId.COD_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.COW_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCowSpawnEggStack.class)
                .vanillaItem(VanillaItemId.COW_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CREEPER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemCreeperSpawnEggStack.class)
                .vanillaItem(VanillaItemId.CREEPER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DOLPHIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemDolphinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.DOLPHIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DONKEY_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemDonkeySpawnEggStack.class)
                .vanillaItem(VanillaItemId.DONKEY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DROWNED_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemDrownedSpawnEggStack.class)
                .vanillaItem(VanillaItemId.DROWNED_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ELDER_GUARDIAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemElderGuardianSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ELDER_GUARDIAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDER_DRAGON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEnderDragonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ENDER_DRAGON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDERMAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEndermanSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ENDERMAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDERMITE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEndermiteSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ENDERMITE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.EVOKER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemEvokerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.EVOKER_SPAWN_EGG)
                .addComponent(ItemEvokerSpawnEggBaseComponentImpl::new, ItemEvokerSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.FOX_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemFoxSpawnEggStack.class)
                .vanillaItem(VanillaItemId.FOX_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.FROG_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemFrogSpawnEggStack.class)
                .vanillaItem(VanillaItemId.FROG_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GHAST_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGhastSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GHAST_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GLOW_SQUID_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGlowSquidSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GLOW_SQUID_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GOAT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGoatSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GOAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GUARDIAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemGuardianSpawnEggStack.class)
                .vanillaItem(VanillaItemId.GUARDIAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HOGLIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemHoglinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.HOGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HORSE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemHorseSpawnEggStack.class)
                .vanillaItem(VanillaItemId.HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HUSK_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemHuskSpawnEggStack.class)
                .vanillaItem(VanillaItemId.HUSK_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_GOLEM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemIronGolemSpawnEggStack.class)
                .vanillaItem(VanillaItemId.IRON_GOLEM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.LLAMA_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemLlamaSpawnEggStack.class)
                .vanillaItem(VanillaItemId.LLAMA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MAGMA_CUBE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemMagmaCubeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.MAGMA_CUBE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MOOSHROOM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemMooshroomSpawnEggStack.class)
                .vanillaItem(VanillaItemId.MOOSHROOM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MULE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemMuleSpawnEggStack.class)
                .vanillaItem(VanillaItemId.MULE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.NPC_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemNpcSpawnEggStack.class)
                .vanillaItem(VanillaItemId.NPC_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.OCELOT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemOcelotSpawnEggStack.class)
                .vanillaItem(VanillaItemId.OCELOT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PANDA_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPandaSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PANDA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PARROT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemParrotSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PARROT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PHANTOM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPhantomSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PHANTOM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIG_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPigSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PIG_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIGLIN_BRUTE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPiglinBruteSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PIGLIN_BRUTE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIGLIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPiglinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PIGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PILLAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPillagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.POLAR_BEAR_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPolarBearSpawnEggStack.class)
                .vanillaItem(VanillaItemId.POLAR_BEAR_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PUFFERFISH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemPufferfishSpawnEggStack.class)
                .vanillaItem(VanillaItemId.PUFFERFISH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.RABBIT_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemRabbitSpawnEggStack.class)
                .vanillaItem(VanillaItemId.RABBIT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.RAVAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemRavagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.RAVAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SALMON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSalmonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SALMON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SHEEP_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSheepSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SHEEP_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SHULKER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemShulkerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SHULKER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SILVERFISH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSilverfishSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SILVERFISH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SKELETON_HORSE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSkeletonHorseSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SKELETON_HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SKELETON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSkeletonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SKELETON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SLIME_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSlimeSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SLIME_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SNIFFER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSnifferSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SNIFFER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SNOW_GOLEM_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSnowGolemSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SNOW_GOLEM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SPIDER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSpiderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SPIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SQUID_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemSquidSpawnEggStack.class)
                .vanillaItem(VanillaItemId.SQUID_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.STRAY_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemStraySpawnEggStack.class)
                .vanillaItem(VanillaItemId.STRAY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.STRIDER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemStriderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.STRIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TADPOLE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTadpoleSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TADPOLE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TRADER_LLAMA_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTraderLlamaSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TRADER_LLAMA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TROPICAL_FISH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTropicalFishSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TROPICAL_FISH_SPAWN_EGG)
                .addComponent(ItemTropicalFishSpawnEggBaseComponentImpl::new, ItemTropicalFishSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TURTLE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemTurtleSpawnEggStack.class)
                .vanillaItem(VanillaItemId.TURTLE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VEX_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemVexSpawnEggStack.class)
                .vanillaItem(VanillaItemId.VEX_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VILLAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemVillagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.VILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VINDICATOR_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemVindicatorSpawnEggStack.class)
                .vanillaItem(VanillaItemId.VINDICATOR_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WANDERING_TRADER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWanderingTraderSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WANDERING_TRADER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WARDEN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWardenSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WARDEN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITCH_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWitchSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WITCH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITHER_SKELETON_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWitherSkeletonSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WITHER_SKELETON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITHER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWitherSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WITHER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WOLF_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemWolfSpawnEggStack.class)
                .vanillaItem(VanillaItemId.WOLF_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOGLIN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZoglinSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_HORSE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombieHorseSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombiePigmanSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_PIGMAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombieSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG = ItemTypeBuilder
                .builder(ItemZombieVillagerSpawnEggStack.class)
                .vanillaItem(VanillaItemId.ZOMBIE_VILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
    }

    public static void initSword() {
        ItemTypes.DIAMOND_SWORD = ItemTypeBuilder
                .builder(ItemDiamondSwordStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_SWORD = ItemTypeBuilder
                .builder(ItemGoldenSwordStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.IRON_SWORD = ItemTypeBuilder
                .builder(ItemIronSwordStack.class)
                .vanillaItem(VanillaItemId.IRON_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_SWORD = ItemTypeBuilder
                .builder(ItemNetheriteSwordStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.STONE_SWORD = ItemTypeBuilder
                .builder(ItemStoneSwordStack.class)
                .vanillaItem(VanillaItemId.STONE_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.WOODEN_SWORD = ItemTypeBuilder
                .builder(ItemWoodenSwordStack.class)
                .vanillaItem(VanillaItemId.WOODEN_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
    }

    public static void initPickaxe() {
        ItemTypes.DIAMOND_PICKAXE = ItemTypeBuilder
                .builder(ItemDiamondPickaxeStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.GOLDEN_PICKAXE = ItemTypeBuilder
                .builder(ItemGoldenPickaxeStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.IRON_PICKAXE = ItemTypeBuilder
                .builder(ItemIronPickaxeStack.class)
                .vanillaItem(VanillaItemId.IRON_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.NETHERITE_PICKAXE = ItemTypeBuilder
                .builder(ItemNetheritePickaxeStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.STONE_PICKAXE = ItemTypeBuilder
                .builder(ItemStonePickaxeStack.class)
                .vanillaItem(VanillaItemId.STONE_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.WOODEN_PICKAXE = ItemTypeBuilder
                .builder(ItemWoodenPickaxeStack.class)
                .vanillaItem(VanillaItemId.WOODEN_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
    }

    public static void initAxe() {
        ItemTypes.DIAMOND_AXE = ItemTypeBuilder
                .builder(ItemDiamondAxeStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.GOLDEN_AXE = ItemTypeBuilder
                .builder(ItemGoldenAxeStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.IRON_AXE = ItemTypeBuilder
                .builder(ItemIronAxeStack.class)
                .vanillaItem(VanillaItemId.IRON_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.NETHERITE_AXE = ItemTypeBuilder
                .builder(ItemNetheriteAxeStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.STONE_AXE = ItemTypeBuilder
                .builder(ItemStoneAxeStack.class)
                .vanillaItem(VanillaItemId.STONE_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.WOODEN_AXE = ItemTypeBuilder
                .builder(ItemWoodenAxeStack.class)
                .vanillaItem(VanillaItemId.WOODEN_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
    }

    public static void initShovel() {
        ItemTypes.DIAMOND_SHOVEL = ItemTypeBuilder
                .builder(ItemDiamondShovelStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.GOLDEN_SHOVEL = ItemTypeBuilder
                .builder(ItemGoldenShovelStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.IRON_SHOVEL = ItemTypeBuilder
                .builder(ItemIronShovelStack.class)
                .vanillaItem(VanillaItemId.IRON_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.NETHERITE_SHOVEL = ItemTypeBuilder
                .builder(ItemNetheriteShovelStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.STONE_SHOVEL = ItemTypeBuilder
                .builder(ItemStoneShovelStack.class)
                .vanillaItem(VanillaItemId.STONE_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.WOODEN_SHOVEL = ItemTypeBuilder
                .builder(ItemWoodenShovelStack.class)
                .vanillaItem(VanillaItemId.WOODEN_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
    }

    public static void initHoe() {
        ItemTypes.DIAMOND_HOE = ItemTypeBuilder
                .builder(ItemDiamondHoeStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.GOLDEN_HOE = ItemTypeBuilder
                .builder(ItemGoldenHoeStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.IRON_HOE = ItemTypeBuilder
                .builder(ItemIronHoeStack.class)
                .vanillaItem(VanillaItemId.IRON_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.NETHERITE_HOE = ItemTypeBuilder
                .builder(ItemNetheriteHoeStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.STONE_HOE = ItemTypeBuilder
                .builder(ItemStoneHoeStack.class)
                .vanillaItem(VanillaItemId.STONE_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.WOODEN_HOE = ItemTypeBuilder
                .builder(ItemWoodenHoeStack.class)
                .vanillaItem(VanillaItemId.WOODEN_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
    }

    public static void initShulkerBox() {
        ItemTypes.BLACK_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemBlackShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.BLACK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BLUE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemBlueShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BROWN_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemBrownShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.BROWN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.CYAN_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemCyanShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.CYAN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GRAY_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemGrayShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GREEN_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemGreenShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.GREEN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_BLUE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemLightBlueShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.LIGHT_BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_GRAY_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemLightGrayShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.LIGHT_GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIME_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemLimeShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.LIME_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.MAGENTA_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemMagentaShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.MAGENTA_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.ORANGE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemOrangeShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.ORANGE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PINK_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemPinkShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.PINK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PURPLE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemPurpleShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.PURPLE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.RED_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemRedShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.RED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.SHULKER_BOX = ItemTypeBuilder
                .builder(ItemShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.UNDYED_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemUndyedShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.UNDYED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.WHITE_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemWhiteShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.WHITE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.YELLOW_SHULKER_BOX = ItemTypeBuilder
                .builder(ItemYellowShulkerBoxStack.class)
                .vanillaItem(VanillaItemId.YELLOW_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
    }

    public static void initAir() {
        ItemTypes.AIR = ItemTypeBuilder
                .builder(ItemAirStack.class)
                .vanillaItem(VanillaItemId.AIR)
                .addComponent(ItemAirBaseComponentImpl::new, ItemAirBaseComponentImpl.class)
                .build();
    }

    public static void initHelmets() {
        ItemTypes.CHAINMAIL_HELMET = ItemTypeBuilder
                .builder(ItemChainmailHelmetStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_HELMET = ItemTypeBuilder
                .builder(ItemDiamondHelmetStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_HELMET = ItemTypeBuilder
                .builder(ItemGoldenHelmetStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_HELMET = ItemTypeBuilder
                .builder(ItemIronHelmetStack.class)
                .vanillaItem(VanillaItemId.IRON_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_HELMET = ItemTypeBuilder
                .builder(ItemLeatherHelmetStack.class)
                .vanillaItem(VanillaItemId.LEATHER_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_HELMET = ItemTypeBuilder
                .builder(ItemNetheriteHelmetStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.TURTLE_HELMET = ItemTypeBuilder
                .builder(ItemTurtleHelmetStack.class)
                .vanillaItem(VanillaItemId.TURTLE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
    }


    public static void initChestplates() {
        ItemTypes.CHAINMAIL_CHESTPLATE = ItemTypeBuilder
                .builder(ItemChainmailChestplateStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_CHESTPLATE = ItemTypeBuilder
                .builder(ItemDiamondChestplateStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_CHESTPLATE = ItemTypeBuilder
                .builder(ItemGoldenChestplateStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_CHESTPLATE = ItemTypeBuilder
                .builder(ItemIronChestplateStack.class)
                .vanillaItem(VanillaItemId.IRON_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_CHESTPLATE = ItemTypeBuilder
                .builder(ItemLeatherChestplateStack.class)
                .vanillaItem(VanillaItemId.LEATHER_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_CHESTPLATE = ItemTypeBuilder
                .builder(ItemNetheriteChestplateStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.ELYTRA = ItemTypeBuilder
                .builder(ItemElytraStack.class)
                .vanillaItem(VanillaItemId.ELYTRA)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS = ItemTypeBuilder
                .builder(ItemChainmailLeggingsStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_LEGGINGS = ItemTypeBuilder
                .builder(ItemDiamondLeggingsStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_LEGGINGS = ItemTypeBuilder
                .builder(ItemGoldenLeggingsStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_LEGGINGS = ItemTypeBuilder
                .builder(ItemIronLeggingsStack.class)
                .vanillaItem(VanillaItemId.IRON_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_LEGGINGS = ItemTypeBuilder
                .builder(ItemLeatherLeggingsStack.class)
                .vanillaItem(VanillaItemId.LEATHER_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_LEGGINGS = ItemTypeBuilder
                .builder(ItemNetheriteLeggingsStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initBoots() {
        ItemTypes.CHAINMAIL_BOOTS = ItemTypeBuilder
                .builder(ItemChainmailBootsStack.class)
                .vanillaItem(VanillaItemId.CHAINMAIL_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_BOOTS = ItemTypeBuilder
                .builder(ItemDiamondBootsStack.class)
                .vanillaItem(VanillaItemId.DIAMOND_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_BOOTS = ItemTypeBuilder
                .builder(ItemGoldenBootsStack.class)
                .vanillaItem(VanillaItemId.GOLDEN_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_BOOTS = ItemTypeBuilder
                .builder(ItemIronBootsStack.class)
                .vanillaItem(VanillaItemId.IRON_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_BOOTS = ItemTypeBuilder
                .builder(ItemLeatherBootsStack.class)
                .vanillaItem(VanillaItemId.LEATHER_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_BOOTS = ItemTypeBuilder
                .builder(ItemNetheriteBootsStack.class)
                .vanillaItem(VanillaItemId.NETHERITE_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initCauldron() {
        ItemTypes.CAULDRON = ItemTypeBuilder
                .builder(ItemCauldronStack.class)
                .vanillaItem(VanillaItemId.CAULDRON)
                .addComponent(ItemCauldronBaseComponentImpl::new, ItemCauldronBaseComponentImpl.class)
                .build();
    }

    public static void initSigns() {
        ItemTypes.ACACIA_SIGN = ItemTypeBuilder
                .builder(ItemAcaciaSignStack.class)
                .vanillaItem(VanillaItemId.ACACIA_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.ACACIA_WALL_SIGN, VanillaBlockId.ACACIA_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.BAMBOO_SIGN = ItemTypeBuilder
                .builder(ItemBambooSignStack.class)
                .vanillaItem(VanillaItemId.BAMBOO_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.BAMBOO_WALL_SIGN, VanillaBlockId.BAMBOO_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.BIRCH_SIGN = ItemTypeBuilder
                .builder(ItemBirchSignStack.class)
                .vanillaItem(VanillaItemId.BIRCH_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.BIRCH_WALL_SIGN, VanillaBlockId.BIRCH_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.CHERRY_SIGN = ItemTypeBuilder
                .builder(ItemCherrySignStack.class)
                .vanillaItem(VanillaItemId.CHERRY_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.CHERRY_WALL_SIGN, VanillaBlockId.CHERRY_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.CRIMSON_SIGN = ItemTypeBuilder
                .builder(ItemCrimsonSignStack.class)
                .vanillaItem(VanillaItemId.CRIMSON_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.CRIMSON_WALL_SIGN, VanillaBlockId.CRIMSON_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.DARK_OAK_SIGN = ItemTypeBuilder
                .builder(ItemDarkOakSignStack.class)
                .vanillaItem(VanillaItemId.DARK_OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.DARKOAK_WALL_SIGN, VanillaBlockId.DARKOAK_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.JUNGLE_SIGN = ItemTypeBuilder
                .builder(ItemJungleSignStack.class)
                .vanillaItem(VanillaItemId.JUNGLE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.JUNGLE_WALL_SIGN, VanillaBlockId.JUNGLE_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.MANGROVE_SIGN = ItemTypeBuilder
                .builder(ItemMangroveSignStack.class)
                .vanillaItem(VanillaItemId.MANGROVE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.MANGROVE_WALL_SIGN, VanillaBlockId.MANGROVE_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.OAK_SIGN = ItemTypeBuilder
                .builder(ItemOakSignStack.class)
                .vanillaItem(VanillaItemId.OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.WALL_SIGN, VanillaBlockId.STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.SPRUCE_SIGN = ItemTypeBuilder
                .builder(ItemSpruceSignStack.class)
                .vanillaItem(VanillaItemId.SPRUCE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.SPRUCE_WALL_SIGN, VanillaBlockId.SPRUCE_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.WARPED_SIGN = ItemTypeBuilder
                .builder(ItemWarpedSignStack.class)
                .vanillaItem(VanillaItemId.WARPED_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, VanillaBlockId.WARPED_WALL_SIGN, VanillaBlockId.WARPED_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
    }
}
