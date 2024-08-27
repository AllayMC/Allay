package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.item.component.ItemArmorBaseComponent.ArmorType;
import org.allaymc.api.item.component.tool.ItemAxeComponent;
import org.allaymc.api.item.component.tool.ItemHoeComponent;
import org.allaymc.api.item.component.tool.ItemPickaxeComponent;
import org.allaymc.api.item.component.tool.ItemShovelComponent;
import org.allaymc.api.item.data.ItemId;
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
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.*;
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
    public static void initFoods() {
        ItemTypes.TROPICAL_FISH = AllayItemType
                .builder(ItemTropicalFishStack.class)
                .vanillaItem(ItemId.TROPICAL_FISH)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.SWEET_BERRIES = AllayItemType
                .builder(ItemSweetBerriesStack.class)
                .vanillaItem(ItemId.SWEET_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_BEEF = AllayItemType
                .builder(ItemCookedBeefStack.class)
                .vanillaItem(ItemId.COOKED_BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.SALMON = AllayItemType
                .builder(ItemSalmonStack.class)
                .vanillaItem(ItemId.SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.RABBIT = AllayItemType
                .builder(ItemRabbitStack.class)
                .vanillaItem(ItemId.RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.PORKCHOP = AllayItemType
                .builder(ItemPorkchopStack.class)
                .vanillaItem(ItemId.PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();ItemTypes.MUTTON = AllayItemType
                .builder(ItemMuttonStack.class)
                .vanillaItem(ItemId.MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COD = AllayItemType
                .builder(ItemCodStack.class)
                .vanillaItem(ItemId.COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BEEF = AllayItemType
                .builder(ItemBeefStack.class)
                .vanillaItem(ItemId.BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.RABBIT_STEW = AllayItemType
                .builder(ItemRabbitStewStack.class)
                .vanillaItem(ItemId.RABBIT_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(10, 12f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.PUMPKIN_PIE = AllayItemType
                .builder(ItemPumpkinPieStack.class)
                .vanillaItem(ItemId.PUMPKIN_PIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 4.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.POTATO = AllayItemType
                .builder(ItemPotatoStack.class)
                .vanillaItem(ItemId.POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.MUSHROOM_STEW = AllayItemType
                .builder(ItemMushroomStewStack.class)
                .vanillaItem(ItemId.MUSHROOM_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.MELON_SLICE = AllayItemType
                .builder(ItemMelonSliceStack.class)
                .vanillaItem(ItemId.MELON_SLICE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();ItemTypes.GOLDEN_CARROT = AllayItemType
                .builder(ItemGoldenCarrotStack.class)
                .vanillaItem(ItemId.GOLDEN_CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 14.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.GLOW_BERRIES = AllayItemType
                .builder(ItemGlowBerriesStack.class)
                .vanillaItem(ItemId.GLOW_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKIE = AllayItemType
                .builder(ItemCookieStack.class)
                .vanillaItem(ItemId.COOKIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.DRIED_KELP = AllayItemType
                .builder(ItemDriedKelpStack.class)
                .vanillaItem(ItemId.DRIED_KELP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f, 16),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_SALMON = AllayItemType
                .builder(ItemCookedSalmonStack.class)
                .vanillaItem(ItemId.COOKED_SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_RABBIT = AllayItemType
                .builder(ItemCookedRabbitStack.class)
                .vanillaItem(ItemId.COOKED_RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_PORKCHOP = AllayItemType
                .builder(ItemCookedPorkchopStack.class)
                .vanillaItem(ItemId.COOKED_PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_MUTTON = AllayItemType
                .builder(ItemCookedMuttonStack.class)
                .vanillaItem(ItemId.COOKED_MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_COD = AllayItemType
                .builder(ItemCookedCodStack.class)
                .vanillaItem(ItemId.COOKED_COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_CHICKEN = AllayItemType
                .builder(ItemCookedChickenStack.class)
                .vanillaItem(ItemId.COOKED_CHICKEN)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();ItemTypes.CARROT = AllayItemType
                .builder(ItemCarrotStack.class)
                .vanillaItem(ItemId.CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 3.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BREAD = AllayItemType
                .builder(ItemBreadStack.class)
                .vanillaItem(ItemId.BREAD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BEETROOT_SOUP = AllayItemType
                .builder(ItemBeetrootSoupStack.class)
                .vanillaItem(ItemId.BEETROOT_SOUP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BEETROOT = AllayItemType
                .builder(ItemBeetrootStack.class)
                .vanillaItem(ItemId.BEETROOT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BAKED_POTATO = AllayItemType
                .builder(ItemBakedPotatoStack.class)
                .vanillaItem(ItemId.BAKED_POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.APPLE = AllayItemType
                .builder(ItemAppleStack.class)
                .vanillaItem(ItemId.APPLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 2.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
    }

    public static void initSpiderEye() {
        ItemTypes.SPIDER_EYE = AllayItemType
                .builder(ItemSpiderEyeStack.class)
                .vanillaItem(ItemId.SPIDER_EYE)
                .addComponent(ItemSpiderEyeBaseComponentImpl::new, ItemSpiderEyeBaseComponentImpl.class)
                .build();
    }

    public static void initRottenFlesh() {
        ItemTypes.ROTTEN_FLESH = AllayItemType
                .builder(ItemRottenFleshStack.class)
                .vanillaItem(ItemId.ROTTEN_FLESH)
                .addComponent(ItemRottenFleshBaseComponentImpl::new, ItemRottenFleshBaseComponentImpl.class)
                .build();
    }

    public static void initChicken() {
        ItemTypes.CHICKEN = AllayItemType
                .builder(ItemChickenStack.class)
                .vanillaItem(ItemId.CHICKEN)
                .addComponent(ItemChickenBaseComponentImpl::new, ItemChickenBaseComponentImpl.class)
                .build();
    }

    public static void initPufferfish() {
        ItemTypes.PUFFERFISH = AllayItemType
                .builder(ItemPufferfishStack.class)
                .vanillaItem(ItemId.PUFFERFISH)
                .addComponent(ItemPufferfishBaseComponentImpl::new, ItemPufferfishBaseComponentImpl.class)
                .build();
    }

    public static void initPoisonousPotato() {
        ItemTypes.POISONOUS_POTATO = AllayItemType
                .builder(ItemPoisonousPotatoStack.class)
                .vanillaItem(ItemId.POISONOUS_POTATO)
                .addComponent(ItemPoisonousPotatoBaseComponentImpl::new, ItemPoisonousPotatoBaseComponentImpl.class)
                .build();
    }

    public static void initHoneyBottle() {
        ItemTypes.HONEY_BOTTLE = AllayItemType
                .builder(ItemHoneyBottleStack.class)
                .vanillaItem(ItemId.HONEY_BOTTLE)
                .addComponent(ItemHoneyBottleBaseComponentImpl::new, ItemHoneyBottleBaseComponentImpl.class)
                .build();
    }

    public static void initApples() {
        ItemTypes.GOLDEN_APPLE = AllayItemType
                .builder(ItemGoldenAppleStack.class)
                .vanillaItem(ItemId.GOLDEN_APPLE)
                .addComponent(ItemGoldenAppleBaseComponentImpl::new, ItemGoldenAppleBaseComponentImpl.class)
                .build();
        ItemTypes.ENCHANTED_GOLDEN_APPLE = AllayItemType
                .builder(ItemEnchantedGoldenAppleStack.class)
                .vanillaItem(ItemId.ENCHANTED_GOLDEN_APPLE)
                .addComponent(ItemEnchantedGoldenAppleBaseComponentImpl::new, ItemEnchantedGoldenAppleBaseComponentImpl.class)
                .build();
    }

    public static void initChorusFruit() {
        ItemTypes.CHORUS_FRUIT = AllayItemType
                .builder(ItemChorusFruitStack.class)
                .vanillaItem(ItemId.CHORUS_FRUIT)
                .addComponent(ItemChorusFruitBaseComponentImpl::new, ItemChorusFruitBaseComponentImpl.class)
                .build();
    }

    public static void initSpawnEgg() {
        ItemTypes.AGENT_SPAWN_EGG = AllayItemType
                .builder(ItemAgentSpawnEggStack.class)
                .vanillaItem(ItemId.AGENT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ALLAY_SPAWN_EGG = AllayItemType
                .builder(ItemAllaySpawnEggStack.class)
                .vanillaItem(ItemId.ALLAY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ARMADILLO_SPAWN_EGG = AllayItemType
                .builder(ItemArmadilloSpawnEggStack.class)
                .vanillaItem(ItemId.ARMADILLO_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.AXOLOTL_SPAWN_EGG = AllayItemType
                .builder(ItemAxolotlSpawnEggStack.class)
                .vanillaItem(ItemId.AXOLOTL_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BAT_SPAWN_EGG = AllayItemType
                .builder(ItemBatSpawnEggStack.class)
                .vanillaItem(ItemId.BAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BEE_SPAWN_EGG = AllayItemType
                .builder(ItemBeeSpawnEggStack.class)
                .vanillaItem(ItemId.BEE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BLAZE_SPAWN_EGG = AllayItemType
                .builder(ItemBlazeSpawnEggStack.class)
                .vanillaItem(ItemId.BLAZE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BOGGED_SPAWN_EGG = AllayItemType
                .builder(ItemBoggedSpawnEggStack.class)
                .vanillaItem(ItemId.BOGGED_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.BREEZE_SPAWN_EGG = AllayItemType
                .builder(ItemBreezeSpawnEggStack.class)
                .vanillaItem(ItemId.BREEZE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAMEL_SPAWN_EGG = AllayItemType
                .builder(ItemCamelSpawnEggStack.class)
                .vanillaItem(ItemId.CAMEL_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAT_SPAWN_EGG = AllayItemType
                .builder(ItemCatSpawnEggStack.class)
                .vanillaItem(ItemId.CAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CAVE_SPIDER_SPAWN_EGG = AllayItemType
                .builder(ItemCaveSpiderSpawnEggStack.class)
                .vanillaItem(ItemId.CAVE_SPIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CHICKEN_SPAWN_EGG = AllayItemType
                .builder(ItemChickenSpawnEggStack.class)
                .vanillaItem(ItemId.CHICKEN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.COD_SPAWN_EGG = AllayItemType
                .builder(ItemCodSpawnEggStack.class)
                .vanillaItem(ItemId.COD_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.COW_SPAWN_EGG = AllayItemType
                .builder(ItemCowSpawnEggStack.class)
                .vanillaItem(ItemId.COW_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.CREEPER_SPAWN_EGG = AllayItemType
                .builder(ItemCreeperSpawnEggStack.class)
                .vanillaItem(ItemId.CREEPER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DOLPHIN_SPAWN_EGG = AllayItemType
                .builder(ItemDolphinSpawnEggStack.class)
                .vanillaItem(ItemId.DOLPHIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DONKEY_SPAWN_EGG = AllayItemType
                .builder(ItemDonkeySpawnEggStack.class)
                .vanillaItem(ItemId.DONKEY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.DROWNED_SPAWN_EGG = AllayItemType
                .builder(ItemDrownedSpawnEggStack.class)
                .vanillaItem(ItemId.DROWNED_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ELDER_GUARDIAN_SPAWN_EGG = AllayItemType
                .builder(ItemElderGuardianSpawnEggStack.class)
                .vanillaItem(ItemId.ELDER_GUARDIAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDER_DRAGON_SPAWN_EGG = AllayItemType
                .builder(ItemEnderDragonSpawnEggStack.class)
                .vanillaItem(ItemId.ENDER_DRAGON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDERMAN_SPAWN_EGG = AllayItemType
                .builder(ItemEndermanSpawnEggStack.class)
                .vanillaItem(ItemId.ENDERMAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ENDERMITE_SPAWN_EGG = AllayItemType
                .builder(ItemEndermiteSpawnEggStack.class)
                .vanillaItem(ItemId.ENDERMITE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.EVOKER_SPAWN_EGG = AllayItemType
                .builder(ItemEvokerSpawnEggStack.class)
                .vanillaItem(ItemId.EVOKER_SPAWN_EGG)
                .addComponent(ItemEvokerSpawnEggBaseComponentImpl::new, ItemEvokerSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.FOX_SPAWN_EGG = AllayItemType
                .builder(ItemFoxSpawnEggStack.class)
                .vanillaItem(ItemId.FOX_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.FROG_SPAWN_EGG = AllayItemType
                .builder(ItemFrogSpawnEggStack.class)
                .vanillaItem(ItemId.FROG_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GHAST_SPAWN_EGG = AllayItemType
                .builder(ItemGhastSpawnEggStack.class)
                .vanillaItem(ItemId.GHAST_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GLOW_SQUID_SPAWN_EGG = AllayItemType
                .builder(ItemGlowSquidSpawnEggStack.class)
                .vanillaItem(ItemId.GLOW_SQUID_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GOAT_SPAWN_EGG = AllayItemType
                .builder(ItemGoatSpawnEggStack.class)
                .vanillaItem(ItemId.GOAT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.GUARDIAN_SPAWN_EGG = AllayItemType
                .builder(ItemGuardianSpawnEggStack.class)
                .vanillaItem(ItemId.GUARDIAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HOGLIN_SPAWN_EGG = AllayItemType
                .builder(ItemHoglinSpawnEggStack.class)
                .vanillaItem(ItemId.HOGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HORSE_SPAWN_EGG = AllayItemType
                .builder(ItemHorseSpawnEggStack.class)
                .vanillaItem(ItemId.HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.HUSK_SPAWN_EGG = AllayItemType
                .builder(ItemHuskSpawnEggStack.class)
                .vanillaItem(ItemId.HUSK_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_GOLEM_SPAWN_EGG = AllayItemType
                .builder(ItemIronGolemSpawnEggStack.class)
                .vanillaItem(ItemId.IRON_GOLEM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.LLAMA_SPAWN_EGG = AllayItemType
                .builder(ItemLlamaSpawnEggStack.class)
                .vanillaItem(ItemId.LLAMA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MAGMA_CUBE_SPAWN_EGG = AllayItemType
                .builder(ItemMagmaCubeSpawnEggStack.class)
                .vanillaItem(ItemId.MAGMA_CUBE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MOOSHROOM_SPAWN_EGG = AllayItemType
                .builder(ItemMooshroomSpawnEggStack.class)
                .vanillaItem(ItemId.MOOSHROOM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.MULE_SPAWN_EGG = AllayItemType
                .builder(ItemMuleSpawnEggStack.class)
                .vanillaItem(ItemId.MULE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.NPC_SPAWN_EGG = AllayItemType
                .builder(ItemNpcSpawnEggStack.class)
                .vanillaItem(ItemId.NPC_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.OCELOT_SPAWN_EGG = AllayItemType
                .builder(ItemOcelotSpawnEggStack.class)
                .vanillaItem(ItemId.OCELOT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PANDA_SPAWN_EGG = AllayItemType
                .builder(ItemPandaSpawnEggStack.class)
                .vanillaItem(ItemId.PANDA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PARROT_SPAWN_EGG = AllayItemType
                .builder(ItemParrotSpawnEggStack.class)
                .vanillaItem(ItemId.PARROT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PHANTOM_SPAWN_EGG = AllayItemType
                .builder(ItemPhantomSpawnEggStack.class)
                .vanillaItem(ItemId.PHANTOM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIG_SPAWN_EGG = AllayItemType
                .builder(ItemPigSpawnEggStack.class)
                .vanillaItem(ItemId.PIG_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIGLIN_BRUTE_SPAWN_EGG = AllayItemType
                .builder(ItemPiglinBruteSpawnEggStack.class)
                .vanillaItem(ItemId.PIGLIN_BRUTE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PIGLIN_SPAWN_EGG = AllayItemType
                .builder(ItemPiglinSpawnEggStack.class)
                .vanillaItem(ItemId.PIGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PILLAGER_SPAWN_EGG = AllayItemType
                .builder(ItemPillagerSpawnEggStack.class)
                .vanillaItem(ItemId.PILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.POLAR_BEAR_SPAWN_EGG = AllayItemType
                .builder(ItemPolarBearSpawnEggStack.class)
                .vanillaItem(ItemId.POLAR_BEAR_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.PUFFERFISH_SPAWN_EGG = AllayItemType
                .builder(ItemPufferfishSpawnEggStack.class)
                .vanillaItem(ItemId.PUFFERFISH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.RABBIT_SPAWN_EGG = AllayItemType
                .builder(ItemRabbitSpawnEggStack.class)
                .vanillaItem(ItemId.RABBIT_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.RAVAGER_SPAWN_EGG = AllayItemType
                .builder(ItemRavagerSpawnEggStack.class)
                .vanillaItem(ItemId.RAVAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SALMON_SPAWN_EGG = AllayItemType
                .builder(ItemSalmonSpawnEggStack.class)
                .vanillaItem(ItemId.SALMON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SHEEP_SPAWN_EGG = AllayItemType
                .builder(ItemSheepSpawnEggStack.class)
                .vanillaItem(ItemId.SHEEP_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SHULKER_SPAWN_EGG = AllayItemType
                .builder(ItemShulkerSpawnEggStack.class)
                .vanillaItem(ItemId.SHULKER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SILVERFISH_SPAWN_EGG = AllayItemType
                .builder(ItemSilverfishSpawnEggStack.class)
                .vanillaItem(ItemId.SILVERFISH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SKELETON_HORSE_SPAWN_EGG = AllayItemType
                .builder(ItemSkeletonHorseSpawnEggStack.class)
                .vanillaItem(ItemId.SKELETON_HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SKELETON_SPAWN_EGG = AllayItemType
                .builder(ItemSkeletonSpawnEggStack.class)
                .vanillaItem(ItemId.SKELETON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SLIME_SPAWN_EGG = AllayItemType
                .builder(ItemSlimeSpawnEggStack.class)
                .vanillaItem(ItemId.SLIME_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SNIFFER_SPAWN_EGG = AllayItemType
                .builder(ItemSnifferSpawnEggStack.class)
                .vanillaItem(ItemId.SNIFFER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SNOW_GOLEM_SPAWN_EGG = AllayItemType
                .builder(ItemSnowGolemSpawnEggStack.class)
                .vanillaItem(ItemId.SNOW_GOLEM_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SPAWN_EGG = AllayItemType
                .builder(ItemSpawnEggStack.class)
                .vanillaItem(ItemId.SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SPIDER_SPAWN_EGG = AllayItemType
                .builder(ItemSpiderSpawnEggStack.class)
                .vanillaItem(ItemId.SPIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.SQUID_SPAWN_EGG = AllayItemType
                .builder(ItemSquidSpawnEggStack.class)
                .vanillaItem(ItemId.SQUID_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.STRAY_SPAWN_EGG = AllayItemType
                .builder(ItemStraySpawnEggStack.class)
                .vanillaItem(ItemId.STRAY_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.STRIDER_SPAWN_EGG = AllayItemType
                .builder(ItemStriderSpawnEggStack.class)
                .vanillaItem(ItemId.STRIDER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TADPOLE_SPAWN_EGG = AllayItemType
                .builder(ItemTadpoleSpawnEggStack.class)
                .vanillaItem(ItemId.TADPOLE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TRADER_LLAMA_SPAWN_EGG = AllayItemType
                .builder(ItemTraderLlamaSpawnEggStack.class)
                .vanillaItem(ItemId.TRADER_LLAMA_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TROPICAL_FISH_SPAWN_EGG = AllayItemType
                .builder(ItemTropicalFishSpawnEggStack.class)
                .vanillaItem(ItemId.TROPICAL_FISH_SPAWN_EGG)
                .addComponent(ItemTropicalFishSpawnEggBaseComponentImpl::new, ItemTropicalFishSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.TURTLE_SPAWN_EGG = AllayItemType
                .builder(ItemTurtleSpawnEggStack.class)
                .vanillaItem(ItemId.TURTLE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VEX_SPAWN_EGG = AllayItemType
                .builder(ItemVexSpawnEggStack.class)
                .vanillaItem(ItemId.VEX_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VILLAGER_SPAWN_EGG = AllayItemType
                .builder(ItemVillagerSpawnEggStack.class)
                .vanillaItem(ItemId.VILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.VINDICATOR_SPAWN_EGG = AllayItemType
                .builder(ItemVindicatorSpawnEggStack.class)
                .vanillaItem(ItemId.VINDICATOR_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WANDERING_TRADER_SPAWN_EGG = AllayItemType
                .builder(ItemWanderingTraderSpawnEggStack.class)
                .vanillaItem(ItemId.WANDERING_TRADER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WARDEN_SPAWN_EGG = AllayItemType
                .builder(ItemWardenSpawnEggStack.class)
                .vanillaItem(ItemId.WARDEN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITCH_SPAWN_EGG = AllayItemType
                .builder(ItemWitchSpawnEggStack.class)
                .vanillaItem(ItemId.WITCH_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITHER_SKELETON_SPAWN_EGG = AllayItemType
                .builder(ItemWitherSkeletonSpawnEggStack.class)
                .vanillaItem(ItemId.WITHER_SKELETON_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WITHER_SPAWN_EGG = AllayItemType
                .builder(ItemWitherSpawnEggStack.class)
                .vanillaItem(ItemId.WITHER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.WOLF_SPAWN_EGG = AllayItemType
                .builder(ItemWolfSpawnEggStack.class)
                .vanillaItem(ItemId.WOLF_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOGLIN_SPAWN_EGG = AllayItemType
                .builder(ItemZoglinSpawnEggStack.class)
                .vanillaItem(ItemId.ZOGLIN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_HORSE_SPAWN_EGG = AllayItemType
                .builder(ItemZombieHorseSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_HORSE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG = AllayItemType
                .builder(ItemZombiePigmanSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_PIGMAN_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_SPAWN_EGG = AllayItemType
                .builder(ItemZombieSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG = AllayItemType
                .builder(ItemZombieVillagerSpawnEggStack.class)
                .vanillaItem(ItemId.ZOMBIE_VILLAGER_SPAWN_EGG)
                .addComponent(ItemSpawnEggBaseComponentImpl::new, ItemSpawnEggBaseComponentImpl.class)
                .build();
    }

    public static void initSword() {
        ItemTypes.DIAMOND_SWORD = AllayItemType
                .builder(ItemDiamondSwordStack.class)
                .vanillaItem(ItemId.DIAMOND_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_SWORD = AllayItemType
                .builder(ItemGoldenSwordStack.class)
                .vanillaItem(ItemId.GOLDEN_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.IRON_SWORD = AllayItemType
                .builder(ItemIronSwordStack.class)
                .vanillaItem(ItemId.IRON_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_SWORD = AllayItemType
                .builder(ItemNetheriteSwordStack.class)
                .vanillaItem(ItemId.NETHERITE_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.STONE_SWORD = AllayItemType
                .builder(ItemStoneSwordStack.class)
                .vanillaItem(ItemId.STONE_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
        ItemTypes.WOODEN_SWORD = AllayItemType
                .builder(ItemWoodenSwordStack.class)
                .vanillaItem(ItemId.WOODEN_SWORD)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
    }

    public static void initPickaxe() {
        ItemTypes.DIAMOND_PICKAXE = AllayItemType
                .builder(ItemDiamondPickaxeStack.class)
                .vanillaItem(ItemId.DIAMOND_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.GOLDEN_PICKAXE = AllayItemType
                .builder(ItemGoldenPickaxeStack.class)
                .vanillaItem(ItemId.GOLDEN_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.IRON_PICKAXE = AllayItemType
                .builder(ItemIronPickaxeStack.class)
                .vanillaItem(ItemId.IRON_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.NETHERITE_PICKAXE = AllayItemType
                .builder(ItemNetheritePickaxeStack.class)
                .vanillaItem(ItemId.NETHERITE_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.STONE_PICKAXE = AllayItemType
                .builder(ItemStonePickaxeStack.class)
                .vanillaItem(ItemId.STONE_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
        ItemTypes.WOODEN_PICKAXE = AllayItemType
                .builder(ItemWoodenPickaxeStack.class)
                .vanillaItem(ItemId.WOODEN_PICKAXE)
                .addComponent(ItemPickaxeComponentImpl::new, ItemPickaxeComponent.class)
                .build();
    }

    public static void initAxe() {
        ItemTypes.DIAMOND_AXE = AllayItemType
                .builder(ItemDiamondAxeStack.class)
                .vanillaItem(ItemId.DIAMOND_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.GOLDEN_AXE = AllayItemType
                .builder(ItemGoldenAxeStack.class)
                .vanillaItem(ItemId.GOLDEN_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.IRON_AXE = AllayItemType
                .builder(ItemIronAxeStack.class)
                .vanillaItem(ItemId.IRON_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.NETHERITE_AXE = AllayItemType
                .builder(ItemNetheriteAxeStack.class)
                .vanillaItem(ItemId.NETHERITE_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.STONE_AXE = AllayItemType
                .builder(ItemStoneAxeStack.class)
                .vanillaItem(ItemId.STONE_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
        ItemTypes.WOODEN_AXE = AllayItemType
                .builder(ItemWoodenAxeStack.class)
                .vanillaItem(ItemId.WOODEN_AXE)
                .addComponent(ItemAxeComponentImpl::new, ItemAxeComponent.class)
                .build();
    }

    public static void initShovel() {
        ItemTypes.DIAMOND_SHOVEL = AllayItemType
                .builder(ItemDiamondShovelStack.class)
                .vanillaItem(ItemId.DIAMOND_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.GOLDEN_SHOVEL = AllayItemType
                .builder(ItemGoldenShovelStack.class)
                .vanillaItem(ItemId.GOLDEN_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.IRON_SHOVEL = AllayItemType
                .builder(ItemIronShovelStack.class)
                .vanillaItem(ItemId.IRON_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.NETHERITE_SHOVEL = AllayItemType
                .builder(ItemNetheriteShovelStack.class)
                .vanillaItem(ItemId.NETHERITE_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.STONE_SHOVEL = AllayItemType
                .builder(ItemStoneShovelStack.class)
                .vanillaItem(ItemId.STONE_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
        ItemTypes.WOODEN_SHOVEL = AllayItemType
                .builder(ItemWoodenShovelStack.class)
                .vanillaItem(ItemId.WOODEN_SHOVEL)
                .addComponent(ItemShovelComponentImpl::new, ItemShovelComponent.class)
                .build();
    }

    public static void initHoe() {
        ItemTypes.DIAMOND_HOE = AllayItemType
                .builder(ItemDiamondHoeStack.class)
                .vanillaItem(ItemId.DIAMOND_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.GOLDEN_HOE = AllayItemType
                .builder(ItemGoldenHoeStack.class)
                .vanillaItem(ItemId.GOLDEN_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.IRON_HOE = AllayItemType
                .builder(ItemIronHoeStack.class)
                .vanillaItem(ItemId.IRON_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.NETHERITE_HOE = AllayItemType
                .builder(ItemNetheriteHoeStack.class)
                .vanillaItem(ItemId.NETHERITE_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.STONE_HOE = AllayItemType
                .builder(ItemStoneHoeStack.class)
                .vanillaItem(ItemId.STONE_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
        ItemTypes.WOODEN_HOE = AllayItemType
                .builder(ItemWoodenHoeStack.class)
                .vanillaItem(ItemId.WOODEN_HOE)
                .addComponent(ItemHoeComponentImpl::new, ItemHoeComponent.class)
                .build();
    }

    public static void initShulkerBox() {
        ItemTypes.BLACK_SHULKER_BOX = AllayItemType
                .builder(ItemBlackShulkerBoxStack.class)
                .vanillaItem(ItemId.BLACK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BLUE_SHULKER_BOX = AllayItemType
                .builder(ItemBlueShulkerBoxStack.class)
                .vanillaItem(ItemId.BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BROWN_SHULKER_BOX = AllayItemType
                .builder(ItemBrownShulkerBoxStack.class)
                .vanillaItem(ItemId.BROWN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.CYAN_SHULKER_BOX = AllayItemType
                .builder(ItemCyanShulkerBoxStack.class)
                .vanillaItem(ItemId.CYAN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GRAY_SHULKER_BOX = AllayItemType
                .builder(ItemGrayShulkerBoxStack.class)
                .vanillaItem(ItemId.GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GREEN_SHULKER_BOX = AllayItemType
                .builder(ItemGreenShulkerBoxStack.class)
                .vanillaItem(ItemId.GREEN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_BLUE_SHULKER_BOX = AllayItemType
                .builder(ItemLightBlueShulkerBoxStack.class)
                .vanillaItem(ItemId.LIGHT_BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_GRAY_SHULKER_BOX = AllayItemType
                .builder(ItemLightGrayShulkerBoxStack.class)
                .vanillaItem(ItemId.LIGHT_GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIME_SHULKER_BOX = AllayItemType
                .builder(ItemLimeShulkerBoxStack.class)
                .vanillaItem(ItemId.LIME_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.MAGENTA_SHULKER_BOX = AllayItemType
                .builder(ItemMagentaShulkerBoxStack.class)
                .vanillaItem(ItemId.MAGENTA_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.ORANGE_SHULKER_BOX = AllayItemType
                .builder(ItemOrangeShulkerBoxStack.class)
                .vanillaItem(ItemId.ORANGE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PINK_SHULKER_BOX = AllayItemType
                .builder(ItemPinkShulkerBoxStack.class)
                .vanillaItem(ItemId.PINK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PURPLE_SHULKER_BOX = AllayItemType
                .builder(ItemPurpleShulkerBoxStack.class)
                .vanillaItem(ItemId.PURPLE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.RED_SHULKER_BOX = AllayItemType
                .builder(ItemRedShulkerBoxStack.class)
                .vanillaItem(ItemId.RED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStack.class)
                .vanillaItem(ItemId.SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.UNDYED_SHULKER_BOX = AllayItemType
                .builder(ItemUndyedShulkerBoxStack.class)
                .vanillaItem(ItemId.UNDYED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.WHITE_SHULKER_BOX = AllayItemType
                .builder(ItemWhiteShulkerBoxStack.class)
                .vanillaItem(ItemId.WHITE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.YELLOW_SHULKER_BOX = AllayItemType
                .builder(ItemYellowShulkerBoxStack.class)
                .vanillaItem(ItemId.YELLOW_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
    }

    public static void initAir() {
        ItemTypes.AIR = AllayItemType
                .builder(ItemAirStack.class)
                .vanillaItem(ItemId.AIR)
                .addComponent(ItemAirBaseComponentImpl::new, ItemAirBaseComponentImpl.class)
                .build();
    }

    public static void initHelmets() {
        ItemTypes.CHAINMAIL_HELMET = AllayItemType
                .builder(ItemChainmailHelmetStack.class)
                .vanillaItem(ItemId.CHAINMAIL_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_HELMET = AllayItemType
                .builder(ItemDiamondHelmetStack.class)
                .vanillaItem(ItemId.DIAMOND_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_HELMET = AllayItemType
                .builder(ItemGoldenHelmetStack.class)
                .vanillaItem(ItemId.GOLDEN_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_HELMET = AllayItemType
                .builder(ItemIronHelmetStack.class)
                .vanillaItem(ItemId.IRON_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_HELMET = AllayItemType
                .builder(ItemLeatherHelmetStack.class)
                .vanillaItem(ItemId.LEATHER_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_HELMET = AllayItemType
                .builder(ItemNetheriteHelmetStack.class)
                .vanillaItem(ItemId.NETHERITE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.TURTLE_HELMET = AllayItemType
                .builder(ItemTurtleHelmetStack.class)
                .vanillaItem(ItemId.TURTLE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
    }


    public static void initChestplates() {
        ItemTypes.CHAINMAIL_CHESTPLATE = AllayItemType
                .builder(ItemChainmailChestplateStack.class)
                .vanillaItem(ItemId.CHAINMAIL_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_CHESTPLATE = AllayItemType
                .builder(ItemDiamondChestplateStack.class)
                .vanillaItem(ItemId.DIAMOND_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_CHESTPLATE = AllayItemType
                .builder(ItemGoldenChestplateStack.class)
                .vanillaItem(ItemId.GOLDEN_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_CHESTPLATE = AllayItemType
                .builder(ItemIronChestplateStack.class)
                .vanillaItem(ItemId.IRON_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_CHESTPLATE = AllayItemType
                .builder(ItemLeatherChestplateStack.class)
                .vanillaItem(ItemId.LEATHER_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_CHESTPLATE = AllayItemType
                .builder(ItemNetheriteChestplateStack.class)
                .vanillaItem(ItemId.NETHERITE_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.ELYTRA = AllayItemType
                .builder(ItemElytraStack.class)
                .vanillaItem(ItemId.ELYTRA)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS = AllayItemType
                .builder(ItemChainmailLeggingsStack.class)
                .vanillaItem(ItemId.CHAINMAIL_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_LEGGINGS = AllayItemType
                .builder(ItemDiamondLeggingsStack.class)
                .vanillaItem(ItemId.DIAMOND_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_LEGGINGS = AllayItemType
                .builder(ItemGoldenLeggingsStack.class)
                .vanillaItem(ItemId.GOLDEN_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_LEGGINGS = AllayItemType
                .builder(ItemIronLeggingsStack.class)
                .vanillaItem(ItemId.IRON_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_LEGGINGS = AllayItemType
                .builder(ItemLeatherLeggingsStack.class)
                .vanillaItem(ItemId.LEATHER_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_LEGGINGS = AllayItemType
                .builder(ItemNetheriteLeggingsStack.class)
                .vanillaItem(ItemId.NETHERITE_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initBoots() {
        ItemTypes.CHAINMAIL_BOOTS = AllayItemType
                .builder(ItemChainmailBootsStack.class)
                .vanillaItem(ItemId.CHAINMAIL_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_BOOTS = AllayItemType
                .builder(ItemDiamondBootsStack.class)
                .vanillaItem(ItemId.DIAMOND_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_BOOTS = AllayItemType
                .builder(ItemGoldenBootsStack.class)
                .vanillaItem(ItemId.GOLDEN_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_BOOTS = AllayItemType
                .builder(ItemIronBootsStack.class)
                .vanillaItem(ItemId.IRON_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_BOOTS = AllayItemType
                .builder(ItemLeatherBootsStack.class)
                .vanillaItem(ItemId.LEATHER_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_BOOTS = AllayItemType
                .builder(ItemNetheriteBootsStack.class)
                .vanillaItem(ItemId.NETHERITE_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initCauldron() {
        ItemTypes.CAULDRON = AllayItemType
                .builder(ItemCauldronStack.class)
                .vanillaItem(ItemId.CAULDRON)
                .addComponent(ItemCauldronBaseComponentImpl::new, ItemCauldronBaseComponentImpl.class)
                .build();
    }

    public static void initSigns() {
        ItemTypes.ACACIA_SIGN = AllayItemType
                .builder(ItemAcaciaSignStack.class)
                .vanillaItem(ItemId.ACACIA_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.ACACIA_WALL_SIGN, BlockId.ACACIA_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.BAMBOO_SIGN = AllayItemType
                .builder(ItemBambooSignStack.class)
                .vanillaItem(ItemId.BAMBOO_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.BAMBOO_WALL_SIGN, BlockId.BAMBOO_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.BIRCH_SIGN = AllayItemType
                .builder(ItemBirchSignStack.class)
                .vanillaItem(ItemId.BIRCH_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.BIRCH_WALL_SIGN, BlockId.BIRCH_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.CHERRY_SIGN = AllayItemType
                .builder(ItemCherrySignStack.class)
                .vanillaItem(ItemId.CHERRY_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.CHERRY_WALL_SIGN, BlockId.CHERRY_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.CRIMSON_SIGN = AllayItemType
                .builder(ItemCrimsonSignStack.class)
                .vanillaItem(ItemId.CRIMSON_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.CRIMSON_WALL_SIGN, BlockId.CRIMSON_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.DARK_OAK_SIGN = AllayItemType
                .builder(ItemDarkOakSignStack.class)
                .vanillaItem(ItemId.DARK_OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.DARKOAK_WALL_SIGN, BlockId.DARKOAK_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.JUNGLE_SIGN = AllayItemType
                .builder(ItemJungleSignStack.class)
                .vanillaItem(ItemId.JUNGLE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.JUNGLE_WALL_SIGN, BlockId.JUNGLE_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.MANGROVE_SIGN = AllayItemType
                .builder(ItemMangroveSignStack.class)
                .vanillaItem(ItemId.MANGROVE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.MANGROVE_WALL_SIGN, BlockId.MANGROVE_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.OAK_SIGN = AllayItemType
                .builder(ItemOakSignStack.class)
                .vanillaItem(ItemId.OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.WALL_SIGN, BlockId.STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.SPRUCE_SIGN = AllayItemType
                .builder(ItemSpruceSignStack.class)
                .vanillaItem(ItemId.SPRUCE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.SPRUCE_WALL_SIGN, BlockId.SPRUCE_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.WARPED_SIGN = AllayItemType
                .builder(ItemWarpedSignStack.class)
                .vanillaItem(ItemId.WARPED_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.WARPED_WALL_SIGN, BlockId.WARPED_STANDING_SIGN), ItemSignBaseComponentImpl.class)
                .build();
    }
}
