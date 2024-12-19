package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.item.component.ItemArmorBaseComponent.ArmorType;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.*;
import org.allaymc.server.item.component.food.*;
import org.allaymc.server.item.component.tool.ItemSwordComponentImpl;
import org.allaymc.server.item.component.tool.ItemToolComponentImpl;
import org.allaymc.server.item.impl.*;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class ItemTypeInitializer {
    public static void initMusicDiscs() {
        ItemTypes.MUSIC_DISC_13 = buildMusicDisc(ItemId.MUSIC_DISC_13, SoundEvent.RECORD_13);
        ItemTypes.MUSIC_DISC_CAT = buildMusicDisc(ItemId.MUSIC_DISC_CAT, SoundEvent.RECORD_CAT);
        ItemTypes.MUSIC_DISC_BLOCKS = buildMusicDisc(ItemId.MUSIC_DISC_BLOCKS, SoundEvent.RECORD_BLOCKS);
        ItemTypes.MUSIC_DISC_CHIRP = buildMusicDisc(ItemId.MUSIC_DISC_CHIRP, SoundEvent.RECORD_CHIRP);
        ItemTypes.MUSIC_DISC_FAR = buildMusicDisc(ItemId.MUSIC_DISC_FAR, SoundEvent.RECORD_FAR);
        ItemTypes.MUSIC_DISC_MALL = buildMusicDisc(ItemId.MUSIC_DISC_MALL, SoundEvent.RECORD_MALL);
        ItemTypes.MUSIC_DISC_MELLOHI = buildMusicDisc(ItemId.MUSIC_DISC_MELLOHI, SoundEvent.RECORD_MELLOHI);
        ItemTypes.MUSIC_DISC_STAL = buildMusicDisc(ItemId.MUSIC_DISC_STAL, SoundEvent.RECORD_STAL);
        ItemTypes.MUSIC_DISC_STRAD = buildMusicDisc(ItemId.MUSIC_DISC_STRAD, SoundEvent.RECORD_STRAD);
        ItemTypes.MUSIC_DISC_WARD = buildMusicDisc(ItemId.MUSIC_DISC_WARD, SoundEvent.RECORD_WARD);
        ItemTypes.MUSIC_DISC_11 = buildMusicDisc(ItemId.MUSIC_DISC_11, SoundEvent.RECORD_11);
        ItemTypes.MUSIC_DISC_WAIT = buildMusicDisc(ItemId.MUSIC_DISC_WAIT, SoundEvent.RECORD_WAIT);
        ItemTypes.MUSIC_DISC_OTHERSIDE = buildMusicDisc(ItemId.MUSIC_DISC_OTHERSIDE, SoundEvent.RECORD_OTHERSIDE);
        ItemTypes.MUSIC_DISC_5 = buildMusicDisc(ItemId.MUSIC_DISC_5, SoundEvent.RECORD_5);
        ItemTypes.MUSIC_DISC_PIGSTEP = buildMusicDisc(ItemId.MUSIC_DISC_PIGSTEP, SoundEvent.RECORD_PIGSTEP);
        ItemTypes.MUSIC_DISC_RELIC = buildMusicDisc(ItemId.MUSIC_DISC_RELIC, SoundEvent.RECORD_RELIC);
        ItemTypes.MUSIC_DISC_CREATOR = buildMusicDisc(ItemId.MUSIC_DISC_CREATOR, SoundEvent.RECORD_CREATOR);
        ItemTypes.MUSIC_DISC_CREATOR_MUSIC_BOX = buildMusicDisc(ItemId.MUSIC_DISC_CREATOR_MUSIC_BOX, SoundEvent.RECORD_CREATOR_MUSIC_BOX);
        ItemTypes.MUSIC_DISC_PRECIPICE = buildMusicDisc(ItemId.MUSIC_DISC_PRECIPICE, SoundEvent.RECORD_PRECIPICE);
    }

    private static ItemType<ItemMusicDiscStack> buildMusicDisc(ItemId itemId, SoundEvent sound) {
        return AllayItemType
                .builder(ItemMusicDiscStackImpl.class)
                .vanillaItem(itemId)
                .addComponent($ -> new ItemMusicDiscComponentImpl(sound), ItemMusicDiscComponentImpl.class)
                .build();
    }

    public static void initFoods() {
        ItemTypes.TROPICAL_FISH = AllayItemType
                .builder(ItemTropicalFishStackImpl.class)
                .vanillaItem(ItemId.TROPICAL_FISH)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.SWEET_BERRIES = AllayItemType
                .builder(ItemSweetBerriesStackImpl.class)
                .vanillaItem(ItemId.SWEET_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_BEEF = AllayItemType
                .builder(ItemCookedBeefStackImpl.class)
                .vanillaItem(ItemId.COOKED_BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.SALMON = AllayItemType
                .builder(ItemSalmonStackImpl.class)
                .vanillaItem(ItemId.SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.RABBIT = AllayItemType
                .builder(ItemRabbitStackImpl.class)
                .vanillaItem(ItemId.RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.PORKCHOP = AllayItemType
                .builder(ItemPorkchopStackImpl.class)
                .vanillaItem(ItemId.PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.MUTTON = AllayItemType
                .builder(ItemMuttonStackImpl.class)
                .vanillaItem(ItemId.MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COD = AllayItemType
                .builder(ItemCodStackImpl.class)
                .vanillaItem(ItemId.COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BEEF = AllayItemType
                .builder(ItemBeefStackImpl.class)
                .vanillaItem(ItemId.BEEF)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 1.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.RABBIT_STEW = AllayItemType
                .builder(ItemRabbitStewStackImpl.class)
                .vanillaItem(ItemId.RABBIT_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(10, 12f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.PUMPKIN_PIE = AllayItemType
                .builder(ItemPumpkinPieStackImpl.class)
                .vanillaItem(ItemId.PUMPKIN_PIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 4.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.POTATO = AllayItemType
                .builder(ItemPotatoStackImpl.class)
                .vanillaItem(ItemId.POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.MUSHROOM_STEW = AllayItemType
                .builder(ItemMushroomStewStackImpl.class)
                .vanillaItem(ItemId.MUSHROOM_STEW)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.MELON_SLICE = AllayItemType
                .builder(ItemMelonSliceStackImpl.class)
                .vanillaItem(ItemId.MELON_SLICE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.GOLDEN_CARROT = AllayItemType
                .builder(ItemGoldenCarrotStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 14.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.GLOW_BERRIES = AllayItemType
                .builder(ItemGlowBerriesStackImpl.class)
                .vanillaItem(ItemId.GLOW_BERRIES)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKIE = AllayItemType
                .builder(ItemCookieStackImpl.class)
                .vanillaItem(ItemId.COOKIE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 0.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.DRIED_KELP = AllayItemType
                .builder(ItemDriedKelpStackImpl.class)
                .vanillaItem(ItemId.DRIED_KELP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f, 16),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_SALMON = AllayItemType
                .builder(ItemCookedSalmonStackImpl.class)
                .vanillaItem(ItemId.COOKED_SALMON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_RABBIT = AllayItemType
                .builder(ItemCookedRabbitStackImpl.class)
                .vanillaItem(ItemId.COOKED_RABBIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_PORKCHOP = AllayItemType
                .builder(ItemCookedPorkchopStackImpl.class)
                .vanillaItem(ItemId.COOKED_PORKCHOP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(8, 12.8f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_MUTTON = AllayItemType
                .builder(ItemCookedMuttonStackImpl.class)
                .vanillaItem(ItemId.COOKED_MUTTON)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_COD = AllayItemType
                .builder(ItemCookedCodStackImpl.class)
                .vanillaItem(ItemId.COOKED_COD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_CHICKEN = AllayItemType
                .builder(ItemCookedChickenStackImpl.class)
                .vanillaItem(ItemId.COOKED_CHICKEN)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.CARROT = AllayItemType
                .builder(ItemCarrotStackImpl.class)
                .vanillaItem(ItemId.CARROT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(3, 3.6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BREAD = AllayItemType
                .builder(ItemBreadStackImpl.class)
                .vanillaItem(ItemId.BREAD)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(5, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BEETROOT_SOUP = AllayItemType
                .builder(ItemBeetrootSoupStackImpl.class)
                .vanillaItem(ItemId.BEETROOT_SOUP)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 7.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BEETROOT = AllayItemType
                .builder(ItemBeetrootStackImpl.class)
                .vanillaItem(ItemId.BEETROOT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.BAKED_POTATO = AllayItemType
                .builder(ItemBakedPotatoStackImpl.class)
                .vanillaItem(ItemId.BAKED_POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 6f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.POISONOUS_POTATO = AllayItemType
                .builder(ItemPoisonousPotatoStackImpl.class)
                .vanillaItem(ItemId.POISONOUS_POTATO)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemPoisonousPotatoBaseComponentImpl::new, ItemPoisonousPotatoBaseComponentImpl.class)
                .build();
        ItemTypes.SPIDER_EYE = AllayItemType
                .builder(ItemSpiderEyeStackImpl.class)
                .vanillaItem(ItemId.SPIDER_EYE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 3.2f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemSpiderEyeBaseComponentImpl::new, ItemSpiderEyeBaseComponentImpl.class)
                .build();
        ItemTypes.ROTTEN_FLESH = AllayItemType
                .builder(ItemRottenFleshStackImpl.class)
                .vanillaItem(ItemId.ROTTEN_FLESH)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 0.8f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemRottenFleshBaseComponentImpl::new, ItemRottenFleshBaseComponentImpl.class)
                .build();
        ItemTypes.CHICKEN = AllayItemType
                .builder(ItemChickenStackImpl.class)
                .vanillaItem(ItemId.CHICKEN)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 1.2f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemChickenBaseComponentImpl::new, ItemChickenBaseComponentImpl.class)
                .build();
        ItemTypes.PUFFERFISH = AllayItemType
                .builder(ItemPufferfishStackImpl.class)
                .vanillaItem(ItemId.PUFFERFISH)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(1, 0.2f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemPufferfishBaseComponentImpl::new, ItemPufferfishBaseComponentImpl.class)
                .build();
        ItemTypes.HONEY_BOTTLE = AllayItemType
                .builder(ItemHoneyBottleStackImpl.class)
                .vanillaItem(ItemId.HONEY_BOTTLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(6, 2.4f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemHoneyBottleBaseComponentImpl::new, ItemHoneyBottleBaseComponentImpl.class)
                .build();
        ItemTypes.APPLE = AllayItemType
                .builder(ItemAppleStackImpl.class)
                .vanillaItem(ItemId.APPLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 2.4f),
                        ItemFoodComponentImpl.class
                )
                .build();
        ItemTypes.GOLDEN_APPLE = AllayItemType
                .builder(ItemGoldenAppleStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_APPLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemGoldenAppleBaseComponentImpl::new, ItemGoldenAppleBaseComponentImpl.class)
                .build();
        ItemTypes.ENCHANTED_GOLDEN_APPLE = AllayItemType
                .builder(ItemEnchantedGoldenAppleStackImpl.class)
                .vanillaItem(ItemId.ENCHANTED_GOLDEN_APPLE)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(4, 9.6f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemEnchantedGoldenAppleBaseComponentImpl::new, ItemEnchantedGoldenAppleBaseComponentImpl.class)
                .build();
        ItemTypes.CHORUS_FRUIT = AllayItemType
                .builder(ItemChorusFruitStackImpl.class)
                .vanillaItem(ItemId.CHORUS_FRUIT)
                .addComponent(
                        $ -> new ItemFoodComponentImpl(2, 2.4f),
                        ItemFoodComponentImpl.class
                )
                .addComponent(ItemChorusFruitBaseComponentImpl::new, ItemChorusFruitBaseComponentImpl.class)
                .build();
    }

    public static void initSpawnEgg() {
        ItemTypes.AGENT_SPAWN_EGG = buildSpawnEgg(ItemId.AGENT_SPAWN_EGG);
        ItemTypes.ALLAY_SPAWN_EGG = buildSpawnEgg(ItemId.ALLAY_SPAWN_EGG);
        ItemTypes.ARMADILLO_SPAWN_EGG = buildSpawnEgg(ItemId.ARMADILLO_SPAWN_EGG);
        ItemTypes.AXOLOTL_SPAWN_EGG = buildSpawnEgg(ItemId.AXOLOTL_SPAWN_EGG);
        ItemTypes.BAT_SPAWN_EGG = buildSpawnEgg(ItemId.BAT_SPAWN_EGG);
        ItemTypes.BEE_SPAWN_EGG = buildSpawnEgg(ItemId.BEE_SPAWN_EGG);
        ItemTypes.BLAZE_SPAWN_EGG = buildSpawnEgg(ItemId.BLAZE_SPAWN_EGG);
        ItemTypes.BOGGED_SPAWN_EGG = buildSpawnEgg(ItemId.BOGGED_SPAWN_EGG);
        ItemTypes.BREEZE_SPAWN_EGG = buildSpawnEgg(ItemId.BREEZE_SPAWN_EGG);
        ItemTypes.CAMEL_SPAWN_EGG = buildSpawnEgg(ItemId.CAMEL_SPAWN_EGG);
        ItemTypes.CAT_SPAWN_EGG = buildSpawnEgg(ItemId.CAT_SPAWN_EGG);
        ItemTypes.CAVE_SPIDER_SPAWN_EGG = buildSpawnEgg(ItemId.CAVE_SPIDER_SPAWN_EGG);
        ItemTypes.CHICKEN_SPAWN_EGG = buildSpawnEgg(ItemId.CHICKEN_SPAWN_EGG);
        ItemTypes.COD_SPAWN_EGG = buildSpawnEgg(ItemId.COD_SPAWN_EGG);
        ItemTypes.COW_SPAWN_EGG = buildSpawnEgg(ItemId.COW_SPAWN_EGG);
        ItemTypes.CREEPER_SPAWN_EGG = buildSpawnEgg(ItemId.CREEPER_SPAWN_EGG);
        ItemTypes.DOLPHIN_SPAWN_EGG = buildSpawnEgg(ItemId.DOLPHIN_SPAWN_EGG);
        ItemTypes.DONKEY_SPAWN_EGG = buildSpawnEgg(ItemId.DONKEY_SPAWN_EGG);
        ItemTypes.DROWNED_SPAWN_EGG = buildSpawnEgg(ItemId.DROWNED_SPAWN_EGG);
        ItemTypes.ELDER_GUARDIAN_SPAWN_EGG = buildSpawnEgg(ItemId.ELDER_GUARDIAN_SPAWN_EGG);
        ItemTypes.ENDER_DRAGON_SPAWN_EGG = buildSpawnEgg(ItemId.ENDER_DRAGON_SPAWN_EGG);
        ItemTypes.ENDERMAN_SPAWN_EGG = buildSpawnEgg(ItemId.ENDERMAN_SPAWN_EGG);
        ItemTypes.ENDERMITE_SPAWN_EGG = buildSpawnEgg(ItemId.ENDERMITE_SPAWN_EGG);
        ItemTypes.EVOKER_SPAWN_EGG = buildSpawnEgg(ItemId.EVOKER_SPAWN_EGG, EntityId.EVOCATION_ILLAGER);
        ItemTypes.FOX_SPAWN_EGG = buildSpawnEgg(ItemId.FOX_SPAWN_EGG);
        ItemTypes.FROG_SPAWN_EGG = buildSpawnEgg(ItemId.FROG_SPAWN_EGG);
        ItemTypes.GHAST_SPAWN_EGG = buildSpawnEgg(ItemId.GHAST_SPAWN_EGG);
        ItemTypes.GLOW_SQUID_SPAWN_EGG = buildSpawnEgg(ItemId.GLOW_SQUID_SPAWN_EGG);
        ItemTypes.GOAT_SPAWN_EGG = buildSpawnEgg(ItemId.GOAT_SPAWN_EGG);
        ItemTypes.GUARDIAN_SPAWN_EGG = buildSpawnEgg(ItemId.GUARDIAN_SPAWN_EGG);
        ItemTypes.HOGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.HOGLIN_SPAWN_EGG);
        ItemTypes.HORSE_SPAWN_EGG = buildSpawnEgg(ItemId.HORSE_SPAWN_EGG);
        ItemTypes.HUSK_SPAWN_EGG = buildSpawnEgg(ItemId.HUSK_SPAWN_EGG);
        ItemTypes.IRON_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.IRON_GOLEM_SPAWN_EGG);
        ItemTypes.LLAMA_SPAWN_EGG = buildSpawnEgg(ItemId.LLAMA_SPAWN_EGG);
        ItemTypes.MAGMA_CUBE_SPAWN_EGG = buildSpawnEgg(ItemId.MAGMA_CUBE_SPAWN_EGG);
        ItemTypes.MOOSHROOM_SPAWN_EGG = buildSpawnEgg(ItemId.MOOSHROOM_SPAWN_EGG);
        ItemTypes.MULE_SPAWN_EGG = buildSpawnEgg(ItemId.MULE_SPAWN_EGG);
        ItemTypes.NPC_SPAWN_EGG = buildSpawnEgg(ItemId.NPC_SPAWN_EGG);
        ItemTypes.OCELOT_SPAWN_EGG = buildSpawnEgg(ItemId.OCELOT_SPAWN_EGG);
        ItemTypes.PANDA_SPAWN_EGG = buildSpawnEgg(ItemId.PANDA_SPAWN_EGG);
        ItemTypes.PARROT_SPAWN_EGG = buildSpawnEgg(ItemId.PARROT_SPAWN_EGG);
        ItemTypes.PHANTOM_SPAWN_EGG = buildSpawnEgg(ItemId.PHANTOM_SPAWN_EGG);
        ItemTypes.PIG_SPAWN_EGG = buildSpawnEgg(ItemId.PIG_SPAWN_EGG);
        ItemTypes.PIGLIN_BRUTE_SPAWN_EGG = buildSpawnEgg(ItemId.PIGLIN_BRUTE_SPAWN_EGG);
        ItemTypes.PIGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.PIGLIN_SPAWN_EGG);
        ItemTypes.PILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.PILLAGER_SPAWN_EGG);
        ItemTypes.POLAR_BEAR_SPAWN_EGG = buildSpawnEgg(ItemId.POLAR_BEAR_SPAWN_EGG);
        ItemTypes.PUFFERFISH_SPAWN_EGG = buildSpawnEgg(ItemId.PUFFERFISH_SPAWN_EGG);
        ItemTypes.RABBIT_SPAWN_EGG = buildSpawnEgg(ItemId.RABBIT_SPAWN_EGG);
        ItemTypes.RAVAGER_SPAWN_EGG = buildSpawnEgg(ItemId.RAVAGER_SPAWN_EGG);
        ItemTypes.SALMON_SPAWN_EGG = buildSpawnEgg(ItemId.SALMON_SPAWN_EGG);
        ItemTypes.SHEEP_SPAWN_EGG = buildSpawnEgg(ItemId.SHEEP_SPAWN_EGG);
        ItemTypes.SHULKER_SPAWN_EGG = buildSpawnEgg(ItemId.SHULKER_SPAWN_EGG);
        ItemTypes.SILVERFISH_SPAWN_EGG = buildSpawnEgg(ItemId.SILVERFISH_SPAWN_EGG);
        ItemTypes.SKELETON_SPAWN_EGG = buildSpawnEgg(ItemId.SKELETON_SPAWN_EGG);
        ItemTypes.SLIME_SPAWN_EGG = buildSpawnEgg(ItemId.SLIME_SPAWN_EGG);
        ItemTypes.SNIFFER_SPAWN_EGG = buildSpawnEgg(ItemId.SNIFFER_SPAWN_EGG);
        ItemTypes.SNOW_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.SNOW_GOLEM_SPAWN_EGG);
        ItemTypes.SPAWN_EGG = buildSpawnEgg(ItemId.SPAWN_EGG);
        ItemTypes.SPIDER_SPAWN_EGG = buildSpawnEgg(ItemId.SPIDER_SPAWN_EGG);
        ItemTypes.SQUID_SPAWN_EGG = buildSpawnEgg(ItemId.SQUID_SPAWN_EGG);
        ItemTypes.STRAY_SPAWN_EGG = buildSpawnEgg(ItemId.STRAY_SPAWN_EGG);
        ItemTypes.STRIDER_SPAWN_EGG = buildSpawnEgg(ItemId.STRIDER_SPAWN_EGG);
        ItemTypes.TADPOLE_SPAWN_EGG = buildSpawnEgg(ItemId.TADPOLE_SPAWN_EGG);
        ItemTypes.TRADER_LLAMA_SPAWN_EGG = buildSpawnEgg(ItemId.TRADER_LLAMA_SPAWN_EGG);
        ItemTypes.TROPICAL_FISH_SPAWN_EGG = buildSpawnEgg(ItemId.TROPICAL_FISH_SPAWN_EGG, EntityId.TROPICALFISH);
        ItemTypes.TURTLE_SPAWN_EGG = buildSpawnEgg(ItemId.TURTLE_SPAWN_EGG);
        ItemTypes.VEX_SPAWN_EGG = buildSpawnEgg(ItemId.VEX_SPAWN_EGG);
        ItemTypes.VILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.VILLAGER_SPAWN_EGG, EntityId.VILLAGER_V2);
        ItemTypes.VINDICATOR_SPAWN_EGG = buildSpawnEgg(ItemId.VINDICATOR_SPAWN_EGG);
        ItemTypes.WANDERING_TRADER_SPAWN_EGG = buildSpawnEgg(ItemId.WANDERING_TRADER_SPAWN_EGG);
        ItemTypes.WARDEN_SPAWN_EGG = buildSpawnEgg(ItemId.WARDEN_SPAWN_EGG);
        ItemTypes.WITCH_SPAWN_EGG = buildSpawnEgg(ItemId.WITCH_SPAWN_EGG);
        ItemTypes.WITHER_SKELETON_SPAWN_EGG = buildSpawnEgg(ItemId.WITHER_SKELETON_SPAWN_EGG);
        ItemTypes.WITHER_SPAWN_EGG = buildSpawnEgg(ItemId.WITHER_SPAWN_EGG);
        ItemTypes.WOLF_SPAWN_EGG = buildSpawnEgg(ItemId.WOLF_SPAWN_EGG);
        ItemTypes.ZOGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.ZOGLIN_SPAWN_EGG);
        ItemTypes.ZOMBIE_HORSE_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_HORSE_SPAWN_EGG);
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_PIGMAN_SPAWN_EGG);
        ItemTypes.ZOMBIE_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_SPAWN_EGG);
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_VILLAGER_SPAWN_EGG);
    }

    private static ItemType<ItemSpawnEggStack> buildSpawnEgg(ItemId itemId) {
        return buildSpawnEgg(itemId, null);
    }

    private static ItemType<ItemSpawnEggStack> buildSpawnEgg(ItemId itemId, EntityId entityId) {
        return AllayItemType
                .builder(ItemSpawnEggStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(
                        initInfo -> new ItemSpawnEggBaseComponentImpl(initInfo, entityId != null ? entityId.getIdentifier() : null),
                        ItemSpawnEggBaseComponentImpl.class
                )
                .build();
    }

    public static void initSword() {
        ItemTypes.DIAMOND_SWORD = buildSword(ItemId.DIAMOND_SWORD);
        ItemTypes.GOLDEN_SWORD = buildSword(ItemId.GOLDEN_SWORD);
        ItemTypes.IRON_SWORD = buildSword(ItemId.IRON_SWORD);
        ItemTypes.NETHERITE_SWORD = buildSword(ItemId.NETHERITE_SWORD);
        ItemTypes.STONE_SWORD = buildSword(ItemId.STONE_SWORD);
        ItemTypes.WOODEN_SWORD = buildSword(ItemId.WOODEN_SWORD);
    }

    private static ItemType<ItemSwordStack> buildSword(ItemId itemId) {
        return AllayItemType
                .builder(ItemSwordStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemSwordComponentImpl::new, ItemSwordComponentImpl.class)
                .build();
    }

    public static void initPickaxe() {
        ItemTypes.DIAMOND_PICKAXE = buildPickaxe(ItemId.DIAMOND_PICKAXE);
        ItemTypes.GOLDEN_PICKAXE = buildPickaxe(ItemId.GOLDEN_PICKAXE);
        ItemTypes.IRON_PICKAXE = buildPickaxe(ItemId.IRON_PICKAXE);
        ItemTypes.NETHERITE_PICKAXE = buildPickaxe(ItemId.NETHERITE_PICKAXE);
        ItemTypes.STONE_PICKAXE = buildPickaxe(ItemId.STONE_PICKAXE);
        ItemTypes.WOODEN_PICKAXE = buildPickaxe(ItemId.WOODEN_PICKAXE);
    }

    private static ItemType<ItemPickaxeStack> buildPickaxe(ItemId itemId) {
        return AllayItemType
                .builder(ItemPickaxeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .build();
    }

    public static void initAxe() {
        ItemTypes.DIAMOND_AXE = buildAxe(ItemId.DIAMOND_AXE);
        ItemTypes.GOLDEN_AXE = buildAxe(ItemId.GOLDEN_AXE);
        ItemTypes.IRON_AXE = buildAxe(ItemId.IRON_AXE);
        ItemTypes.NETHERITE_AXE = buildAxe(ItemId.NETHERITE_AXE);
        ItemTypes.STONE_AXE = buildAxe(ItemId.STONE_AXE);
        ItemTypes.WOODEN_AXE = buildAxe(ItemId.WOODEN_AXE);
    }

    private static ItemType<ItemAxeStack> buildAxe(ItemId itemId) {
        return AllayItemType
                .builder(ItemAxeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemAxeBaseComponentImpl::new, ItemAxeBaseComponentImpl.class)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .build();
    }

    public static void initShovel() {
        ItemTypes.DIAMOND_SHOVEL = buildShovel(ItemId.DIAMOND_SHOVEL);
        ItemTypes.GOLDEN_SHOVEL = buildShovel(ItemId.GOLDEN_SHOVEL);
        ItemTypes.IRON_SHOVEL = buildShovel(ItemId.IRON_SHOVEL);
        ItemTypes.NETHERITE_SHOVEL = buildShovel(ItemId.NETHERITE_SHOVEL);
        ItemTypes.STONE_SHOVEL = buildShovel(ItemId.STONE_SHOVEL);
        ItemTypes.WOODEN_SHOVEL = buildShovel(ItemId.WOODEN_SHOVEL);
    }

    private static ItemType<ItemShovelStack> buildShovel(ItemId itemId) {
        return AllayItemType
                .builder(ItemShovelStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .build();
    }

    public static void initHoe() {
        ItemTypes.DIAMOND_HOE = buildHoe(ItemId.DIAMOND_HOE);
        ItemTypes.GOLDEN_HOE = buildHoe(ItemId.GOLDEN_HOE);
        ItemTypes.IRON_HOE = buildHoe(ItemId.IRON_HOE);
        ItemTypes.NETHERITE_HOE = buildHoe(ItemId.NETHERITE_HOE);
        ItemTypes.STONE_HOE = buildHoe(ItemId.STONE_HOE);
        ItemTypes.WOODEN_HOE = buildHoe(ItemId.WOODEN_HOE);
    }

    private static ItemType<ItemHoeStack> buildHoe(ItemId itemId) {
        return AllayItemType
                .builder(ItemHoeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .build();
    }

    public static void initShulkerBox() {
        ItemTypes.BLACK_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.BLACK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BLUE_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.BROWN_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.BROWN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.CYAN_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.CYAN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GRAY_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.GREEN_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.GREEN_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_BLUE_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.LIGHT_BLUE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIGHT_GRAY_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.LIGHT_GRAY_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.LIME_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.LIME_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.MAGENTA_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.MAGENTA_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.ORANGE_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.ORANGE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PINK_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.PINK_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.PURPLE_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.PURPLE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.RED_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.RED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.UNDYED_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.UNDYED_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.WHITE_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.WHITE_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
        ItemTypes.YELLOW_SHULKER_BOX = AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(ItemId.YELLOW_SHULKER_BOX)
                .addComponent(ItemItemStorableComponentImpl::new, ItemItemStorableComponentImpl.class)
                .build();
    }

    public static void initAir() {
        ItemTypes.AIR = AllayItemType
                .builder(ItemAirStackImpl.class)
                .vanillaItem(ItemId.AIR)
                .addComponent(ItemAirBaseComponentImpl::new, ItemAirBaseComponentImpl.class)
                .build();
    }

    public static void initHelmets() {
        ItemTypes.CHAINMAIL_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.CHAINMAIL_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.DIAMOND_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.IRON_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.LEATHER_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.NETHERITE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.TURTLE_HELMET = AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(ItemId.TURTLE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initChestplates() {
        ItemTypes.CHAINMAIL_CHESTPLATE = AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(ItemId.CHAINMAIL_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_CHESTPLATE = AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(ItemId.DIAMOND_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_CHESTPLATE = AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_CHESTPLATE = AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(ItemId.IRON_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_CHESTPLATE = AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(ItemId.LEATHER_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_CHESTPLATE = AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(ItemId.NETHERITE_CHESTPLATE)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.ELYTRA = AllayItemType
                .builder(ItemElytraStackImpl.class)
                .vanillaItem(ItemId.ELYTRA)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS = AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(ItemId.CHAINMAIL_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_LEGGINGS = AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(ItemId.DIAMOND_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_LEGGINGS = AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_LEGGINGS = AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(ItemId.IRON_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_LEGGINGS = AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(ItemId.LEATHER_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_LEGGINGS = AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(ItemId.NETHERITE_LEGGINGS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initBoots() {
        ItemTypes.CHAINMAIL_BOOTS = AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(ItemId.CHAINMAIL_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.DIAMOND_BOOTS = AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(ItemId.DIAMOND_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.GOLDEN_BOOTS = AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.IRON_BOOTS = AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(ItemId.IRON_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.LEATHER_BOOTS = AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(ItemId.LEATHER_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
        ItemTypes.NETHERITE_BOOTS = AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(ItemId.NETHERITE_BOOTS)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .build();
    }

    public static void initCauldron() {
        ItemTypes.CAULDRON = AllayItemType
                .builder(ItemCauldronStackImpl.class)
                .vanillaItem(ItemId.CAULDRON)
                .addComponent(ItemCauldronBaseComponentImpl::new, ItemCauldronBaseComponentImpl.class)
                .build();
    }

    public static void initSigns() {
        ItemTypes.ACACIA_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.ACACIA_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.ACACIA_WALL_SIGN.getIdentifier(), BlockId.ACACIA_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.BAMBOO_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.BAMBOO_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.BAMBOO_WALL_SIGN.getIdentifier(), BlockId.BAMBOO_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.BIRCH_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.BIRCH_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.BIRCH_WALL_SIGN.getIdentifier(), BlockId.BIRCH_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.CHERRY_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.CHERRY_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.CHERRY_WALL_SIGN.getIdentifier(), BlockId.CHERRY_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.CRIMSON_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.CRIMSON_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.CRIMSON_WALL_SIGN.getIdentifier(), BlockId.CRIMSON_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.DARK_OAK_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.DARK_OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.DARKOAK_WALL_SIGN.getIdentifier(), BlockId.DARKOAK_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.JUNGLE_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.JUNGLE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.JUNGLE_WALL_SIGN.getIdentifier(), BlockId.JUNGLE_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.MANGROVE_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.MANGROVE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.MANGROVE_WALL_SIGN.getIdentifier(), BlockId.MANGROVE_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.OAK_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.WALL_SIGN.getIdentifier(), BlockId.STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.SPRUCE_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.SPRUCE_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.SPRUCE_WALL_SIGN.getIdentifier(), BlockId.SPRUCE_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.WARPED_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.WARPED_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.WARPED_WALL_SIGN.getIdentifier(), BlockId.WARPED_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();
        ItemTypes.PALE_OAK_SIGN = AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(ItemId.PALE_OAK_SIGN)
                .addComponent(initInfo -> new ItemSignBaseComponentImpl(initInfo, BlockId.PALE_OAK_WALL_SIGN.getIdentifier(), BlockId.PALE_OAK_STANDING_SIGN.getIdentifier()), ItemSignBaseComponentImpl.class)
                .build();

    }

    public static void initBuckets() {
        ItemTypes.AXOLOTL_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.AXOLOTL_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), EntityId.AXOLOTL.getIdentifier()), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.AIR.getIdentifier(), null), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.COD_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.COD_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), EntityId.COD.getIdentifier()), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.LAVA_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.LAVA_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.LAVA.getIdentifier(), null), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.POWDER_SNOW_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.POWDER_SNOW_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.POWDER_SNOW.getIdentifier(), null), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.PUFFERFISH_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.PUFFERFISH_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), EntityId.PUFFERFISH.getIdentifier()), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.SALMON_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.SALMON_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), EntityId.SALMON.getIdentifier()), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.TADPOLE_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.TADPOLE_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), EntityId.TADPOLE.getIdentifier()), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.TROPICAL_FISH_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.TROPICAL_FISH_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), EntityId.TROPICALFISH.getIdentifier()), ItemBucketComponentImpl.class)
                .build();
        ItemTypes.WATER_BUCKET = AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(ItemId.WATER_BUCKET)
                .addComponent(() -> new ItemBucketComponentImpl(BlockId.WATER.getIdentifier(), null), ItemBucketComponentImpl.class)
                .build();
    }

    public static void initFlintAndSteel() {
        ItemTypes.FLINT_AND_STEEL = AllayItemType
                .builder(ItemFlintAndSteelStackImpl.class)
                .vanillaItem(ItemId.FLINT_AND_STEEL)
                .addComponent(ItemFlintAndSteelBaseComponentImpl::new, ItemFlintAndSteelBaseComponentImpl.class)
                .build();
    }
}
