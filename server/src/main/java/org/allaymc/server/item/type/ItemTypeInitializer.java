package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityLingeringPotion;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.data.DiscType;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.item.component.*;
import org.allaymc.server.item.component.edible.*;
import org.allaymc.server.item.component.projectile.ItemBottleProjectileComponentImpl;
import org.allaymc.server.item.component.projectile.ItemProjectileComponentImpl;
import org.allaymc.server.item.component.seeds.ItemBeetrootSeedsBaseComponentImpl;
import org.allaymc.server.item.component.seeds.ItemMelonSeedsBaseComponentImpl;
import org.allaymc.server.item.component.seeds.ItemPumpkinSeedsBaseComponentImpl;
import org.allaymc.server.item.component.seeds.ItemWheatSeedsBaseComponentImpl;
import org.allaymc.server.item.component.tool.ItemHoeToolComponentImpl;
import org.allaymc.server.item.component.tool.ItemSwordToolComponentImpl;
import org.allaymc.server.item.component.tool.ItemToolComponentImpl;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.impl.*;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class ItemTypeInitializer {
    public static void initHoneycomb() {
        ItemTypes.HONEYCOMB = AllayItemType
                .builder(ItemHoneycombStackImpl.class)
                .vanillaItem(ItemId.HONEYCOMB)
                .addComponent(ItemHoneycombBaseComponentImpl::new, ItemHoneycombBaseComponentImpl.class)
                .build();
    }

    public static void initMusicDiscs() {
        ItemTypes.MUSIC_DISC_13 = buildMusicDisc(ItemId.MUSIC_DISC_13, DiscType.DISC_13);
        ItemTypes.MUSIC_DISC_CAT = buildMusicDisc(ItemId.MUSIC_DISC_CAT, DiscType.DISC_CAT);
        ItemTypes.MUSIC_DISC_BLOCKS = buildMusicDisc(ItemId.MUSIC_DISC_BLOCKS, DiscType.DISC_BLOCKS);
        ItemTypes.MUSIC_DISC_CHIRP = buildMusicDisc(ItemId.MUSIC_DISC_CHIRP, DiscType.DISC_CHIRP);
        ItemTypes.MUSIC_DISC_FAR = buildMusicDisc(ItemId.MUSIC_DISC_FAR, DiscType.DISC_FAR);
        ItemTypes.MUSIC_DISC_LAVA_CHICKEN = buildMusicDisc(ItemId.MUSIC_DISC_LAVA_CHICKEN, DiscType.DISC_LAVA_CHICKEN);
        ItemTypes.MUSIC_DISC_MALL = buildMusicDisc(ItemId.MUSIC_DISC_MALL, DiscType.DISC_MALL);
        ItemTypes.MUSIC_DISC_MELLOHI = buildMusicDisc(ItemId.MUSIC_DISC_MELLOHI, DiscType.DISC_MELLOHI);
        ItemTypes.MUSIC_DISC_STAL = buildMusicDisc(ItemId.MUSIC_DISC_STAL, DiscType.DISC_STAL);
        ItemTypes.MUSIC_DISC_STRAD = buildMusicDisc(ItemId.MUSIC_DISC_STRAD, DiscType.DISC_STRAD);
        ItemTypes.MUSIC_DISC_TEARS = buildMusicDisc(ItemId.MUSIC_DISC_TEARS, DiscType.DISC_TEARS);
        ItemTypes.MUSIC_DISC_WARD = buildMusicDisc(ItemId.MUSIC_DISC_WARD, DiscType.DISC_WARD);
        ItemTypes.MUSIC_DISC_11 = buildMusicDisc(ItemId.MUSIC_DISC_11, DiscType.DISC_11);
        ItemTypes.MUSIC_DISC_WAIT = buildMusicDisc(ItemId.MUSIC_DISC_WAIT, DiscType.DISC_WAIT);
        ItemTypes.MUSIC_DISC_OTHERSIDE = buildMusicDisc(ItemId.MUSIC_DISC_OTHERSIDE, DiscType.DISC_OTHERSIDE);
        ItemTypes.MUSIC_DISC_5 = buildMusicDisc(ItemId.MUSIC_DISC_5, DiscType.DISC_5);
        ItemTypes.MUSIC_DISC_PIGSTEP = buildMusicDisc(ItemId.MUSIC_DISC_PIGSTEP, DiscType.DISC_PIGSTEP);
        ItemTypes.MUSIC_DISC_RELIC = buildMusicDisc(ItemId.MUSIC_DISC_RELIC, DiscType.DISC_RELIC);
        ItemTypes.MUSIC_DISC_CREATOR = buildMusicDisc(ItemId.MUSIC_DISC_CREATOR, DiscType.DISC_CREATOR);
        ItemTypes.MUSIC_DISC_CREATOR_MUSIC_BOX = buildMusicDisc(ItemId.MUSIC_DISC_CREATOR_MUSIC_BOX, DiscType.DISC_CREATOR_MUSIC_BOX);
        ItemTypes.MUSIC_DISC_PRECIPICE = buildMusicDisc(ItemId.MUSIC_DISC_PRECIPICE, DiscType.DISC_PRECIPICE);
    }

    private static ItemType<ItemMusicDiscStack> buildMusicDisc(ItemId itemId, DiscType discType) {
        return AllayItemType
                .builder(ItemMusicDiscStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(initInfo -> new ItemMusicDiscBaseComponentImpl(initInfo, discType), ItemMusicDiscBaseComponentImpl.class)
                .build();
    }

    public static void initFoods() {
        ItemTypes.TROPICAL_FISH = AllayItemType
                .builder(ItemTropicalFishStackImpl.class)
                .vanillaItem(ItemId.TROPICAL_FISH)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(1, 0.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.SWEET_BERRIES = AllayItemType
                .builder(ItemSweetBerriesStackImpl.class)
                .vanillaItem(ItemId.SWEET_BERRIES)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 1.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_BEEF = AllayItemType
                .builder(ItemCookedBeefStackImpl.class)
                .vanillaItem(ItemId.COOKED_BEEF)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(8, 12.8f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.SALMON = AllayItemType
                .builder(ItemSalmonStackImpl.class)
                .vanillaItem(ItemId.SALMON)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 0.4f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.RABBIT = AllayItemType
                .builder(ItemRabbitStackImpl.class)
                .vanillaItem(ItemId.RABBIT)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(3, 1.8f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.PORKCHOP = AllayItemType
                .builder(ItemPorkchopStackImpl.class)
                .vanillaItem(ItemId.PORKCHOP)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(3, 1.8f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.MUTTON = AllayItemType
                .builder(ItemMuttonStackImpl.class)
                .vanillaItem(ItemId.MUTTON)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 1.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COD = AllayItemType
                .builder(ItemCodStackImpl.class)
                .vanillaItem(ItemId.COD)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 0.4f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.BEEF = AllayItemType
                .builder(ItemBeefStackImpl.class)
                .vanillaItem(ItemId.BEEF)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(3, 1.8f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.RABBIT_STEW = AllayItemType
                .builder(ItemRabbitStewStackImpl.class)
                .vanillaItem(ItemId.RABBIT_STEW)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(10, 12f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.PUMPKIN_PIE = AllayItemType
                .builder(ItemPumpkinPieStackImpl.class)
                .vanillaItem(ItemId.PUMPKIN_PIE)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(8, 4.8f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.POTATO = AllayItemType
                .builder(ItemPotatoStackImpl.class)
                .vanillaItem(ItemId.POTATO)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(1, 0.6f),
                        ItemEdibleComponentImpl.class
                )
                .addComponent(ItemPotatoBaseComponentImpl::new, ItemPotatoBaseComponentImpl.class)
                .build();
        ItemTypes.MUSHROOM_STEW = AllayItemType
                .builder(ItemMushroomStewStackImpl.class)
                .vanillaItem(ItemId.MUSHROOM_STEW)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(6, 7.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.MELON_SLICE = AllayItemType
                .builder(ItemMelonSliceStackImpl.class)
                .vanillaItem(ItemId.MELON_SLICE)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 1.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.GOLDEN_CARROT = AllayItemType
                .builder(ItemGoldenCarrotStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_CARROT)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(6, 14.4f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.GLOW_BERRIES = AllayItemType
                .builder(ItemGlowBerriesStackImpl.class)
                .vanillaItem(ItemId.GLOW_BERRIES)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 0.4f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKIE = AllayItemType
                .builder(ItemCookieStackImpl.class)
                .vanillaItem(ItemId.COOKIE)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(2, 0.4f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.DRIED_KELP = AllayItemType
                .builder(ItemDriedKelpStackImpl.class)
                .vanillaItem(ItemId.DRIED_KELP)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(1, 0.2f, 16),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_SALMON = AllayItemType
                .builder(ItemCookedSalmonStackImpl.class)
                .vanillaItem(ItemId.COOKED_SALMON)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(6, 9.6f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_RABBIT = AllayItemType
                .builder(ItemCookedRabbitStackImpl.class)
                .vanillaItem(ItemId.COOKED_RABBIT)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(5, 6f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_PORKCHOP = AllayItemType
                .builder(ItemCookedPorkchopStackImpl.class)
                .vanillaItem(ItemId.COOKED_PORKCHOP)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(8, 12.8f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_MUTTON = AllayItemType
                .builder(ItemCookedMuttonStackImpl.class)
                .vanillaItem(ItemId.COOKED_MUTTON)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(6, 9.6f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_COD = AllayItemType
                .builder(ItemCookedCodStackImpl.class)
                .vanillaItem(ItemId.COOKED_COD)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(5, 6f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.COOKED_CHICKEN = AllayItemType
                .builder(ItemCookedChickenStackImpl.class)
                .vanillaItem(ItemId.COOKED_CHICKEN)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(6, 7.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.CARROT = AllayItemType
                .builder(ItemCarrotStackImpl.class)
                .vanillaItem(ItemId.CARROT)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(3, 3.6f),
                        ItemEdibleComponentImpl.class
                )
                .addComponent(ItemCarrotBaseComponentImpl::new, ItemCarrotBaseComponentImpl.class)
                .build();
        ItemTypes.BREAD = AllayItemType
                .builder(ItemBreadStackImpl.class)
                .vanillaItem(ItemId.BREAD)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(5, 6f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.BEETROOT_SOUP = AllayItemType
                .builder(ItemBeetrootSoupStackImpl.class)
                .vanillaItem(ItemId.BEETROOT_SOUP)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(6, 7.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.BEETROOT = AllayItemType
                .builder(ItemBeetrootStackImpl.class)
                .vanillaItem(ItemId.BEETROOT)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(1, 1.2f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.BAKED_POTATO = AllayItemType
                .builder(ItemBakedPotatoStackImpl.class)
                .vanillaItem(ItemId.BAKED_POTATO)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(5, 6f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.POISONOUS_POTATO = AllayItemType
                .builder(ItemPoisonousPotatoStackImpl.class)
                .vanillaItem(ItemId.POISONOUS_POTATO)
                .addComponent(ItemPoisonousPotatoEdibleComponentImpl::new, ItemPoisonousPotatoEdibleComponentImpl.class)
                .build();
        ItemTypes.SPIDER_EYE = AllayItemType
                .builder(ItemSpiderEyeStackImpl.class)
                .vanillaItem(ItemId.SPIDER_EYE)
                .addComponent(ItemSpiderEyeEdibleComponentImpl::new, ItemSpiderEyeEdibleComponentImpl.class)
                .build();
        ItemTypes.ROTTEN_FLESH = AllayItemType
                .builder(ItemRottenFleshStackImpl.class)
                .vanillaItem(ItemId.ROTTEN_FLESH)
                .addComponent(ItemRottenFleshEdibleComponentImpl::new, ItemRottenFleshEdibleComponentImpl.class)
                .build();
        ItemTypes.CHICKEN = AllayItemType
                .builder(ItemChickenStackImpl.class)
                .vanillaItem(ItemId.CHICKEN)
                .addComponent(ItemChickenEdibleComponentImpl::new, ItemChickenEdibleComponentImpl.class)
                .build();
        ItemTypes.PUFFERFISH = AllayItemType
                .builder(ItemPufferfishStackImpl.class)
                .vanillaItem(ItemId.PUFFERFISH)
                .addComponent(ItemPufferfishEdibleComponentImpl::new, ItemPufferfishEdibleComponentImpl.class)
                .build();
        ItemTypes.HONEY_BOTTLE = AllayItemType
                .builder(ItemHoneyBottleStackImpl.class)
                .vanillaItem(ItemId.HONEY_BOTTLE)
                .addComponent(ItemHoneyBottleEdibleComponentImpl::new, ItemHoneyBottleEdibleComponentImpl.class)
                .build();
        ItemTypes.APPLE = AllayItemType
                .builder(ItemAppleStackImpl.class)
                .vanillaItem(ItemId.APPLE)
                .addComponent(
                        $ -> new ItemEdibleComponentImpl(4, 2.4f),
                        ItemEdibleComponentImpl.class
                )
                .build();
        ItemTypes.GOLDEN_APPLE = AllayItemType
                .builder(ItemGoldenAppleStackImpl.class)
                .vanillaItem(ItemId.GOLDEN_APPLE)
                .addComponent(ItemGoldenAppleEdibleComponentImpl::new, ItemGoldenAppleEdibleComponentImpl.class)
                .build();
        ItemTypes.ENCHANTED_GOLDEN_APPLE = AllayItemType
                .builder(ItemEnchantedGoldenAppleStackImpl.class)
                .vanillaItem(ItemId.ENCHANTED_GOLDEN_APPLE)
                .addComponent(ItemEnchantedGoldenAppleEdibleComponentImpl::new, ItemEnchantedGoldenAppleEdibleComponentImpl.class)
                .build();
        ItemTypes.CHORUS_FRUIT = AllayItemType
                .builder(ItemChorusFruitStackImpl.class)
                .vanillaItem(ItemId.CHORUS_FRUIT)
                .addComponent(ItemChorusFruitEdibleComponentImpl::new, ItemChorusFruitEdibleComponentImpl.class)
                .build();
    }

    public static void initSpawnEgg() {
        ItemTypes.AGENT_SPAWN_EGG = buildSpawnEgg(ItemId.AGENT_SPAWN_EGG, EntityId.AGENT);
        ItemTypes.ALLAY_SPAWN_EGG = buildSpawnEgg(ItemId.ALLAY_SPAWN_EGG, EntityId.ALLAY);
        ItemTypes.ARMADILLO_SPAWN_EGG = buildSpawnEgg(ItemId.ARMADILLO_SPAWN_EGG, EntityId.ARMADILLO);
        ItemTypes.AXOLOTL_SPAWN_EGG = buildSpawnEgg(ItemId.AXOLOTL_SPAWN_EGG, EntityId.AXOLOTL);
        ItemTypes.BAT_SPAWN_EGG = buildSpawnEgg(ItemId.BAT_SPAWN_EGG, EntityId.BAT);
        ItemTypes.BEE_SPAWN_EGG = buildSpawnEgg(ItemId.BEE_SPAWN_EGG, EntityId.BEE);
        ItemTypes.BLAZE_SPAWN_EGG = buildSpawnEgg(ItemId.BLAZE_SPAWN_EGG, EntityId.BLAZE);
        ItemTypes.BOGGED_SPAWN_EGG = buildSpawnEgg(ItemId.BOGGED_SPAWN_EGG, EntityId.BOGGED);
        ItemTypes.BREEZE_SPAWN_EGG = buildSpawnEgg(ItemId.BREEZE_SPAWN_EGG, EntityId.BREEZE);
        ItemTypes.CAMEL_SPAWN_EGG = buildSpawnEgg(ItemId.CAMEL_SPAWN_EGG, EntityId.CAMEL);
        ItemTypes.CAT_SPAWN_EGG = buildSpawnEgg(ItemId.CAT_SPAWN_EGG, EntityId.CAT);
        ItemTypes.CAVE_SPIDER_SPAWN_EGG = buildSpawnEgg(ItemId.CAVE_SPIDER_SPAWN_EGG, EntityId.CAVE_SPIDER);
        ItemTypes.CHICKEN_SPAWN_EGG = buildSpawnEgg(ItemId.CHICKEN_SPAWN_EGG, EntityId.CHICKEN);
        ItemTypes.COD_SPAWN_EGG = buildSpawnEgg(ItemId.COD_SPAWN_EGG, EntityId.COD);
        ItemTypes.COW_SPAWN_EGG = buildSpawnEgg(ItemId.COW_SPAWN_EGG, EntityId.COW);
        ItemTypes.CREEPER_SPAWN_EGG = buildSpawnEgg(ItemId.CREEPER_SPAWN_EGG, EntityId.CREEPER);
        ItemTypes.DOLPHIN_SPAWN_EGG = buildSpawnEgg(ItemId.DOLPHIN_SPAWN_EGG, EntityId.DOLPHIN);
        ItemTypes.DONKEY_SPAWN_EGG = buildSpawnEgg(ItemId.DONKEY_SPAWN_EGG, EntityId.DONKEY);
        ItemTypes.DROWNED_SPAWN_EGG = buildSpawnEgg(ItemId.DROWNED_SPAWN_EGG, EntityId.DROWNED);
        ItemTypes.ELDER_GUARDIAN_SPAWN_EGG = buildSpawnEgg(ItemId.ELDER_GUARDIAN_SPAWN_EGG, EntityId.ELDER_GUARDIAN);
        ItemTypes.ENDER_DRAGON_SPAWN_EGG = buildSpawnEgg(ItemId.ENDER_DRAGON_SPAWN_EGG, EntityId.ENDER_DRAGON);
        ItemTypes.ENDERMAN_SPAWN_EGG = buildSpawnEgg(ItemId.ENDERMAN_SPAWN_EGG, EntityId.ENDERMAN);
        ItemTypes.ENDERMITE_SPAWN_EGG = buildSpawnEgg(ItemId.ENDERMITE_SPAWN_EGG, EntityId.ENDERMITE);
        ItemTypes.EVOKER_SPAWN_EGG = buildSpawnEgg(ItemId.EVOKER_SPAWN_EGG, EntityId.EVOCATION_ILLAGER);
        ItemTypes.FOX_SPAWN_EGG = buildSpawnEgg(ItemId.FOX_SPAWN_EGG, EntityId.FOX);
        ItemTypes.FROG_SPAWN_EGG = buildSpawnEgg(ItemId.FROG_SPAWN_EGG, EntityId.FROG);
        ItemTypes.GHAST_SPAWN_EGG = buildSpawnEgg(ItemId.GHAST_SPAWN_EGG, EntityId.GHAST);
        ItemTypes.GLOW_SQUID_SPAWN_EGG = buildSpawnEgg(ItemId.GLOW_SQUID_SPAWN_EGG, EntityId.GLOW_SQUID);
        ItemTypes.GOAT_SPAWN_EGG = buildSpawnEgg(ItemId.GOAT_SPAWN_EGG, EntityId.GOAT);
        ItemTypes.GUARDIAN_SPAWN_EGG = buildSpawnEgg(ItemId.GUARDIAN_SPAWN_EGG, EntityId.GUARDIAN);
        ItemTypes.HOGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.HOGLIN_SPAWN_EGG, EntityId.HOGLIN);
        ItemTypes.HORSE_SPAWN_EGG = buildSpawnEgg(ItemId.HORSE_SPAWN_EGG, EntityId.HORSE);
        ItemTypes.HUSK_SPAWN_EGG = buildSpawnEgg(ItemId.HUSK_SPAWN_EGG, EntityId.HUSK);
        ItemTypes.IRON_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.IRON_GOLEM_SPAWN_EGG, EntityId.IRON_GOLEM);
        ItemTypes.LLAMA_SPAWN_EGG = buildSpawnEgg(ItemId.LLAMA_SPAWN_EGG, EntityId.LLAMA);
        ItemTypes.MAGMA_CUBE_SPAWN_EGG = buildSpawnEgg(ItemId.MAGMA_CUBE_SPAWN_EGG, EntityId.MAGMA_CUBE);
        ItemTypes.MOOSHROOM_SPAWN_EGG = buildSpawnEgg(ItemId.MOOSHROOM_SPAWN_EGG, EntityId.MOOSHROOM);
        ItemTypes.MULE_SPAWN_EGG = buildSpawnEgg(ItemId.MULE_SPAWN_EGG, EntityId.MULE);
        ItemTypes.NPC_SPAWN_EGG = buildSpawnEgg(ItemId.NPC_SPAWN_EGG, EntityId.NPC);
        ItemTypes.OCELOT_SPAWN_EGG = buildSpawnEgg(ItemId.OCELOT_SPAWN_EGG, EntityId.OCELOT);
        ItemTypes.PANDA_SPAWN_EGG = buildSpawnEgg(ItemId.PANDA_SPAWN_EGG, EntityId.PANDA);
        ItemTypes.PARROT_SPAWN_EGG = buildSpawnEgg(ItemId.PARROT_SPAWN_EGG, EntityId.PARROT);
        ItemTypes.PHANTOM_SPAWN_EGG = buildSpawnEgg(ItemId.PHANTOM_SPAWN_EGG, EntityId.PHANTOM);
        ItemTypes.PIG_SPAWN_EGG = buildSpawnEgg(ItemId.PIG_SPAWN_EGG, EntityId.PIG);
        ItemTypes.PIGLIN_BRUTE_SPAWN_EGG = buildSpawnEgg(ItemId.PIGLIN_BRUTE_SPAWN_EGG, EntityId.PIGLIN_BRUTE);
        ItemTypes.PIGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.PIGLIN_SPAWN_EGG, EntityId.PIGLIN);
        ItemTypes.PILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.PILLAGER_SPAWN_EGG, EntityId.PILLAGER);
        ItemTypes.POLAR_BEAR_SPAWN_EGG = buildSpawnEgg(ItemId.POLAR_BEAR_SPAWN_EGG, EntityId.POLAR_BEAR);
        ItemTypes.PUFFERFISH_SPAWN_EGG = buildSpawnEgg(ItemId.PUFFERFISH_SPAWN_EGG, EntityId.PUFFERFISH);
        ItemTypes.RABBIT_SPAWN_EGG = buildSpawnEgg(ItemId.RABBIT_SPAWN_EGG, EntityId.RABBIT);
        ItemTypes.RAVAGER_SPAWN_EGG = buildSpawnEgg(ItemId.RAVAGER_SPAWN_EGG, EntityId.RAVAGER);
        ItemTypes.SALMON_SPAWN_EGG = buildSpawnEgg(ItemId.SALMON_SPAWN_EGG, EntityId.SALMON);
        ItemTypes.SHEEP_SPAWN_EGG = buildSpawnEgg(ItemId.SHEEP_SPAWN_EGG, EntityId.SHEEP);
        ItemTypes.SHULKER_SPAWN_EGG = buildSpawnEgg(ItemId.SHULKER_SPAWN_EGG, EntityId.SHULKER);
        ItemTypes.SILVERFISH_SPAWN_EGG = buildSpawnEgg(ItemId.SILVERFISH_SPAWN_EGG, EntityId.SILVERFISH);
        ItemTypes.SKELETON_SPAWN_EGG = buildSpawnEgg(ItemId.SKELETON_SPAWN_EGG, EntityId.SKELETON);
        ItemTypes.SLIME_SPAWN_EGG = buildSpawnEgg(ItemId.SLIME_SPAWN_EGG, EntityId.SLIME);
        ItemTypes.SNIFFER_SPAWN_EGG = buildSpawnEgg(ItemId.SNIFFER_SPAWN_EGG, EntityId.SNIFFER);
        ItemTypes.SNOW_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.SNOW_GOLEM_SPAWN_EGG, EntityId.SNOW_GOLEM);
//        ItemTypes.SPAWN_EGG = buildSpawnEgg(ItemId.SPAWN_EGG); // ??
        ItemTypes.SPIDER_SPAWN_EGG = buildSpawnEgg(ItemId.SPIDER_SPAWN_EGG, EntityId.SPIDER);
        ItemTypes.SQUID_SPAWN_EGG = buildSpawnEgg(ItemId.SQUID_SPAWN_EGG, EntityId.SQUID);
        ItemTypes.STRAY_SPAWN_EGG = buildSpawnEgg(ItemId.STRAY_SPAWN_EGG, EntityId.STRAY);
        ItemTypes.STRIDER_SPAWN_EGG = buildSpawnEgg(ItemId.STRIDER_SPAWN_EGG, EntityId.STRIDER);
        ItemTypes.TADPOLE_SPAWN_EGG = buildSpawnEgg(ItemId.TADPOLE_SPAWN_EGG, EntityId.TADPOLE);
        ItemTypes.TRADER_LLAMA_SPAWN_EGG = buildSpawnEgg(ItemId.TRADER_LLAMA_SPAWN_EGG, EntityId.TRADER_LLAMA);
        ItemTypes.TROPICAL_FISH_SPAWN_EGG = buildSpawnEgg(ItemId.TROPICAL_FISH_SPAWN_EGG, EntityId.TROPICALFISH);
        ItemTypes.TURTLE_SPAWN_EGG = buildSpawnEgg(ItemId.TURTLE_SPAWN_EGG, EntityId.TURTLE);
        ItemTypes.VEX_SPAWN_EGG = buildSpawnEgg(ItemId.VEX_SPAWN_EGG, EntityId.VEX);
        ItemTypes.VILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.VILLAGER_SPAWN_EGG, EntityId.VILLAGER_V2);
        ItemTypes.VINDICATOR_SPAWN_EGG = buildSpawnEgg(ItemId.VINDICATOR_SPAWN_EGG, EntityId.VINDICATOR);
        ItemTypes.WANDERING_TRADER_SPAWN_EGG = buildSpawnEgg(ItemId.WANDERING_TRADER_SPAWN_EGG, EntityId.WANDERING_TRADER);
        ItemTypes.WARDEN_SPAWN_EGG = buildSpawnEgg(ItemId.WARDEN_SPAWN_EGG, EntityId.WARDEN);
        ItemTypes.WITCH_SPAWN_EGG = buildSpawnEgg(ItemId.WITCH_SPAWN_EGG, EntityId.WITCH);
        ItemTypes.WITHER_SKELETON_SPAWN_EGG = buildSpawnEgg(ItemId.WITHER_SKELETON_SPAWN_EGG, EntityId.WITHER_SKELETON);
        ItemTypes.WITHER_SPAWN_EGG = buildSpawnEgg(ItemId.WITHER_SPAWN_EGG, EntityId.WITHER);
        ItemTypes.WOLF_SPAWN_EGG = buildSpawnEgg(ItemId.WOLF_SPAWN_EGG, EntityId.WOLF);
        ItemTypes.ZOGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.ZOGLIN_SPAWN_EGG, EntityId.ZOGLIN);
        ItemTypes.ZOMBIE_HORSE_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_HORSE_SPAWN_EGG, EntityId.ZOMBIE_HORSE);
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_PIGMAN_SPAWN_EGG, EntityId.ZOMBIE_PIGMAN);
        ItemTypes.ZOMBIE_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_SPAWN_EGG, EntityId.ZOMBIE);
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_VILLAGER_SPAWN_EGG, EntityId.ZOMBIE_VILLAGER);
    }

    private static ItemType<ItemSpawnEggStack> buildSpawnEgg(ItemId itemId, EntityId customEntityId) {
        return AllayItemType
                .builder(ItemSpawnEggStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(
                        initInfo -> new ItemSpawnEggBaseComponentImpl(initInfo, customEntityId),
                        ItemSpawnEggBaseComponentImpl.class
                )
                .build();
    }

    public static void initSword() {
        ItemTypes.DIAMOND_SWORD = buildSword(ItemId.DIAMOND_SWORD, ItemId.DIAMOND);
        ItemTypes.GOLDEN_SWORD = buildSword(ItemId.GOLDEN_SWORD, ItemId.GOLD_INGOT);
        ItemTypes.IRON_SWORD = buildSword(ItemId.IRON_SWORD, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_SWORD = buildSword(ItemId.NETHERITE_SWORD, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_SWORD = buildSword(ItemId.STONE_SWORD, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_SWORD = buildSword(ItemId.WOODEN_SWORD, ItemId.PLANKS);
        ItemTypes.COPPER_SWORD = buildSword(ItemId.COPPER_SWORD, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemSwordStack> buildSword(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemSwordStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemSwordToolComponentImpl::new, ItemSwordToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initPickaxe() {
        ItemTypes.DIAMOND_PICKAXE = buildPickaxe(ItemId.DIAMOND_PICKAXE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_PICKAXE = buildPickaxe(ItemId.GOLDEN_PICKAXE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_PICKAXE = buildPickaxe(ItemId.IRON_PICKAXE, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_PICKAXE = buildPickaxe(ItemId.NETHERITE_PICKAXE, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_PICKAXE = buildPickaxe(ItemId.STONE_PICKAXE, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_PICKAXE = buildPickaxe(ItemId.WOODEN_PICKAXE, ItemId.PLANKS);
        ItemTypes.COPPER_PICKAXE = buildPickaxe(ItemId.COPPER_PICKAXE, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemPickaxeStack> buildPickaxe(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemPickaxeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initAxe() {
        ItemTypes.DIAMOND_AXE = buildAxe(ItemId.DIAMOND_AXE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_AXE = buildAxe(ItemId.GOLDEN_AXE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_AXE = buildAxe(ItemId.IRON_AXE, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_AXE = buildAxe(ItemId.NETHERITE_AXE, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_AXE = buildAxe(ItemId.STONE_AXE, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_AXE = buildAxe(ItemId.WOODEN_AXE, ItemId.PLANKS);
        ItemTypes.COPPER_AXE = buildAxe(ItemId.COPPER_AXE, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemAxeStack> buildAxe(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemAxeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemAxeBaseComponentImpl::new, ItemAxeBaseComponentImpl.class)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initShovel() {
        ItemTypes.DIAMOND_SHOVEL = buildShovel(ItemId.DIAMOND_SHOVEL, ItemId.DIAMOND);
        ItemTypes.GOLDEN_SHOVEL = buildShovel(ItemId.GOLDEN_SHOVEL, ItemId.GOLD_INGOT);
        ItemTypes.IRON_SHOVEL = buildShovel(ItemId.IRON_SHOVEL, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_SHOVEL = buildShovel(ItemId.NETHERITE_SHOVEL, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_SHOVEL = buildShovel(ItemId.STONE_SHOVEL, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_SHOVEL = buildShovel(ItemId.WOODEN_SHOVEL, ItemId.PLANKS);
        ItemTypes.COPPER_SHOVEL = buildShovel(ItemId.COPPER_SHOVEL, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemShovelStack> buildShovel(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemShovelStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initHoe() {
        ItemTypes.DIAMOND_HOE = buildHoe(ItemId.DIAMOND_HOE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_HOE = buildHoe(ItemId.GOLDEN_HOE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_HOE = buildHoe(ItemId.IRON_HOE, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_HOE = buildHoe(ItemId.NETHERITE_HOE, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_HOE = buildHoe(ItemId.STONE_HOE, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_HOE = buildHoe(ItemId.WOODEN_HOE, ItemId.PLANKS);
        ItemTypes.COPPER_HOE = buildHoe(ItemId.COPPER_HOE, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemHoeStack> buildHoe(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemHoeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemHoeToolComponentImpl::new, ItemHoeToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initShulkerBox() {
        ItemTypes.BLACK_SHULKER_BOX = buildShulkerBox(ItemId.BLACK_SHULKER_BOX);
        ItemTypes.BLUE_SHULKER_BOX = buildShulkerBox(ItemId.BLUE_SHULKER_BOX);
        ItemTypes.BROWN_SHULKER_BOX = buildShulkerBox(ItemId.BROWN_SHULKER_BOX);
        ItemTypes.CYAN_SHULKER_BOX = buildShulkerBox(ItemId.CYAN_SHULKER_BOX);
        ItemTypes.GRAY_SHULKER_BOX = buildShulkerBox(ItemId.GRAY_SHULKER_BOX);
        ItemTypes.GREEN_SHULKER_BOX = buildShulkerBox(ItemId.GREEN_SHULKER_BOX);
        ItemTypes.LIGHT_BLUE_SHULKER_BOX = buildShulkerBox(ItemId.LIGHT_BLUE_SHULKER_BOX);
        ItemTypes.LIGHT_GRAY_SHULKER_BOX = buildShulkerBox(ItemId.LIGHT_GRAY_SHULKER_BOX);
        ItemTypes.LIME_SHULKER_BOX = buildShulkerBox(ItemId.LIME_SHULKER_BOX);
        ItemTypes.MAGENTA_SHULKER_BOX = buildShulkerBox(ItemId.MAGENTA_SHULKER_BOX);
        ItemTypes.ORANGE_SHULKER_BOX = buildShulkerBox(ItemId.ORANGE_SHULKER_BOX);
        ItemTypes.PINK_SHULKER_BOX = buildShulkerBox(ItemId.PINK_SHULKER_BOX);
        ItemTypes.PURPLE_SHULKER_BOX = buildShulkerBox(ItemId.PURPLE_SHULKER_BOX);
        ItemTypes.RED_SHULKER_BOX = buildShulkerBox(ItemId.RED_SHULKER_BOX);
        ItemTypes.SHULKER_BOX = buildShulkerBox(ItemId.SHULKER_BOX);
        ItemTypes.UNDYED_SHULKER_BOX = buildShulkerBox(ItemId.UNDYED_SHULKER_BOX);
        ItemTypes.WHITE_SHULKER_BOX = buildShulkerBox(ItemId.WHITE_SHULKER_BOX);
        ItemTypes.YELLOW_SHULKER_BOX = buildShulkerBox(ItemId.YELLOW_SHULKER_BOX);
    }

    private static ItemType<ItemShulkerBoxStack> buildShulkerBox(ItemId itemId) {
        return AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemStuffStorableComponentImpl::new, ItemStuffStorableComponentImpl.class)
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
        ItemTypes.CHAINMAIL_HELMET = buildHelmet(ItemId.CHAINMAIL_HELMET, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_HELMET = buildHelmet(ItemId.DIAMOND_HELMET, ItemId.DIAMOND);
        ItemTypes.GOLDEN_HELMET = buildHelmet(ItemId.GOLDEN_HELMET, ItemId.GOLD_INGOT);
        ItemTypes.IRON_HELMET = buildHelmet(ItemId.IRON_HELMET, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_HELMET = buildHelmet(ItemId.LEATHER_HELMET, ItemId.LEATHER);
        ItemTypes.NETHERITE_HELMET = buildHelmet(ItemId.NETHERITE_HELMET, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_HELMET = buildHelmet(ItemId.COPPER_HELMET, ItemId.COPPER_INGOT);
        ItemTypes.TURTLE_HELMET = AllayItemType
                .builder(ItemTurtleHelmetStackImpl.class)
                .vanillaItem(ItemId.TURTLE_HELMET)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.TURTLE_SCUTE), ItemRepairableComponentImpl.class)
                .build();
    }

    private static ItemType<ItemHelmetStack> buildHelmet(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemHelmetStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.HELMET), ItemArmorBaseComponentImpl.class)
                .addComponent(ItemTrimmableComponentImpl::new, ItemTrimmableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initChestplates() {
        ItemTypes.CHAINMAIL_CHESTPLATE = buildChestplate(ItemId.CHAINMAIL_CHESTPLATE, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_CHESTPLATE = buildChestplate(ItemId.DIAMOND_CHESTPLATE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_CHESTPLATE = buildChestplate(ItemId.GOLDEN_CHESTPLATE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_CHESTPLATE = buildChestplate(ItemId.IRON_CHESTPLATE, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_CHESTPLATE = buildChestplate(ItemId.LEATHER_CHESTPLATE, ItemId.LEATHER);
        ItemTypes.NETHERITE_CHESTPLATE = buildChestplate(ItemId.NETHERITE_CHESTPLATE, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_CHESTPLATE = buildChestplate(ItemId.COPPER_CHESTPLATE, ItemId.COPPER_INGOT);
        ItemTypes.ELYTRA = AllayItemType
                .builder(ItemElytraStackImpl.class)
                .vanillaItem(ItemId.ELYTRA)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.PHANTOM_MEMBRANE), ItemRepairableComponentImpl.class)
                .build();
    }

    private static ItemType<ItemChestplateStack> buildChestplate(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemChestplateStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.CHESTPLATE), ItemArmorBaseComponentImpl.class)
                .addComponent(ItemTrimmableComponentImpl::new, ItemTrimmableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS = buildLeggings(ItemId.CHAINMAIL_LEGGINGS, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_LEGGINGS = buildLeggings(ItemId.DIAMOND_LEGGINGS, ItemId.DIAMOND);
        ItemTypes.GOLDEN_LEGGINGS = buildLeggings(ItemId.GOLDEN_LEGGINGS, ItemId.GOLD_INGOT);
        ItemTypes.IRON_LEGGINGS = buildLeggings(ItemId.IRON_LEGGINGS, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_LEGGINGS = buildLeggings(ItemId.LEATHER_LEGGINGS, ItemId.LEATHER);
        ItemTypes.NETHERITE_LEGGINGS = buildLeggings(ItemId.NETHERITE_LEGGINGS, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_LEGGINGS = buildLeggings(ItemId.COPPER_LEGGINGS, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemLeggingsStack> buildLeggings(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemLeggingsStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.LEGGINGS), ItemArmorBaseComponentImpl.class)
                .addComponent(ItemTrimmableComponentImpl::new, ItemTrimmableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initBoots() {
        ItemTypes.CHAINMAIL_BOOTS = buildBoots(ItemId.CHAINMAIL_BOOTS, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_BOOTS = buildBoots(ItemId.DIAMOND_BOOTS, ItemId.DIAMOND);
        ItemTypes.GOLDEN_BOOTS = buildBoots(ItemId.GOLDEN_BOOTS, ItemId.GOLD_INGOT);
        ItemTypes.IRON_BOOTS = buildBoots(ItemId.IRON_BOOTS, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_BOOTS = buildBoots(ItemId.LEATHER_BOOTS, ItemId.LEATHER);
        ItemTypes.NETHERITE_BOOTS = buildBoots(ItemId.NETHERITE_BOOTS, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_BOOTS = buildBoots(ItemId.COPPER_BOOTS, ItemId.COPPER_INGOT);
    }

    private static ItemType<ItemBootsStack> buildBoots(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemBootsStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(initInfo -> new ItemArmorBaseComponentImpl(initInfo, ArmorType.BOOTS), ItemArmorBaseComponentImpl.class)
                .addComponent(ItemTrimmableComponentImpl::new, ItemTrimmableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initSigns() {
        ItemTypes.ACACIA_SIGN = buildSign(ItemId.ACACIA_SIGN, BlockId.ACACIA_WALL_SIGN, BlockId.ACACIA_STANDING_SIGN);
        ItemTypes.BAMBOO_SIGN = buildSign(ItemId.BAMBOO_SIGN, BlockId.BAMBOO_WALL_SIGN, BlockId.BAMBOO_STANDING_SIGN);
        ItemTypes.BIRCH_SIGN = buildSign(ItemId.BIRCH_SIGN, BlockId.BIRCH_WALL_SIGN, BlockId.BIRCH_STANDING_SIGN);
        ItemTypes.CHERRY_SIGN = buildSign(ItemId.CHERRY_SIGN, BlockId.CHERRY_WALL_SIGN, BlockId.CHERRY_STANDING_SIGN);
        ItemTypes.CRIMSON_SIGN = buildSign(ItemId.CRIMSON_SIGN, BlockId.CRIMSON_WALL_SIGN, BlockId.CRIMSON_STANDING_SIGN);
        ItemTypes.DARK_OAK_SIGN = buildSign(ItemId.DARK_OAK_SIGN, BlockId.DARKOAK_WALL_SIGN, BlockId.DARKOAK_STANDING_SIGN);
        ItemTypes.JUNGLE_SIGN = buildSign(ItemId.JUNGLE_SIGN, BlockId.JUNGLE_WALL_SIGN, BlockId.JUNGLE_STANDING_SIGN);
        ItemTypes.MANGROVE_SIGN = buildSign(ItemId.MANGROVE_SIGN, BlockId.MANGROVE_WALL_SIGN, BlockId.MANGROVE_STANDING_SIGN);
        ItemTypes.OAK_SIGN = buildSign(ItemId.OAK_SIGN, BlockId.WALL_SIGN, BlockId.STANDING_SIGN);
        ItemTypes.SPRUCE_SIGN = buildSign(ItemId.SPRUCE_SIGN, BlockId.SPRUCE_WALL_SIGN, BlockId.SPRUCE_STANDING_SIGN);
        ItemTypes.WARPED_SIGN = buildSign(ItemId.WARPED_SIGN, BlockId.WARPED_WALL_SIGN, BlockId.WARPED_STANDING_SIGN);
        ItemTypes.PALE_OAK_SIGN = buildSign(ItemId.PALE_OAK_SIGN, BlockId.PALE_OAK_WALL_SIGN, BlockId.PALE_OAK_STANDING_SIGN);
    }

    private static ItemType<ItemSignStack> buildSign(ItemId itemId, BlockId wallBlockId, BlockId standingBlockId) {
        return AllayItemType
                .builder(ItemSignStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(
                        initInfo -> new ItemSignBaseComponentImpl(initInfo, wallBlockId, standingBlockId),
                        ItemSignBaseComponentImpl.class
                )
                .build();
    }

    public static void initBuckets() {
        ItemTypes.AXOLOTL_BUCKET = buildBucket(ItemId.AXOLOTL_BUCKET, BlockId.WATER, EntityId.AXOLOTL);
        ItemTypes.BUCKET = buildBucket(ItemId.BUCKET, BlockId.AIR, null);
        ItemTypes.COD_BUCKET = buildBucket(ItemId.COD_BUCKET, BlockId.WATER, EntityId.COD);
        ItemTypes.LAVA_BUCKET = buildBucket(ItemId.LAVA_BUCKET, BlockId.LAVA, null);
        ItemTypes.POWDER_SNOW_BUCKET = buildBucket(ItemId.POWDER_SNOW_BUCKET, BlockId.POWDER_SNOW, null);
        ItemTypes.PUFFERFISH_BUCKET = buildBucket(ItemId.PUFFERFISH_BUCKET, BlockId.WATER, EntityId.PUFFERFISH);
        ItemTypes.SALMON_BUCKET = buildBucket(ItemId.SALMON_BUCKET, BlockId.WATER, EntityId.SALMON);
        ItemTypes.TADPOLE_BUCKET = buildBucket(ItemId.TADPOLE_BUCKET, BlockId.WATER, EntityId.TADPOLE);
        ItemTypes.TROPICAL_FISH_BUCKET = buildBucket(ItemId.TROPICAL_FISH_BUCKET, BlockId.WATER, EntityId.TROPICALFISH);
        ItemTypes.WATER_BUCKET = buildBucket(ItemId.WATER_BUCKET, BlockId.WATER, null);
    }

    private static ItemType<ItemBucketStack> buildBucket(ItemId itemId, BlockId liquidBlockId, EntityId entityId) {
        return AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemBucketComponentImpl(liquidBlockId, entityId), ItemBucketComponentImpl.class)
                .build();
    }

    public static void initFlintAndSteel() {
        ItemTypes.FLINT_AND_STEEL = AllayItemType
                .builder(ItemFlintAndSteelStackImpl.class)
                .vanillaItem(ItemId.FLINT_AND_STEEL)
                .addComponent(ItemFlintAndSteelBaseComponentImpl::new, ItemFlintAndSteelBaseComponentImpl.class)
                .build();
    }

    public static void initEndCrystal() {
        ItemTypes.END_CRYSTAL = AllayItemType
                .builder(ItemEndCrystalStackImpl.class)
                .vanillaItem(ItemId.END_CRYSTAL)
                .addComponent(ItemEndCrystalBaseComponentImpl::new, ItemEndCrystalBaseComponentImpl.class)
                .build();
    }

    public static void initSeeds() {
        ItemTypes.WHEAT_SEEDS = AllayItemType
                .builder(ItemWheatSeedsStackImpl.class)
                .vanillaItem(ItemId.WHEAT_SEEDS)
                .addComponent(ItemWheatSeedsBaseComponentImpl::new, ItemWheatSeedsBaseComponentImpl.class)
                .build();
        ItemTypes.BEETROOT_SEEDS = AllayItemType
                .builder(ItemBeetrootSeedsStackImpl.class)
                .vanillaItem(ItemId.BEETROOT_SEEDS)
                .addComponent(ItemBeetrootSeedsBaseComponentImpl::new, ItemBeetrootSeedsBaseComponentImpl.class)
                .build();
        ItemTypes.MELON_SEEDS = AllayItemType
                .builder(ItemMelonSeedsStackImpl.class)
                .vanillaItem(ItemId.MELON_SEEDS)
                .addComponent(ItemMelonSeedsBaseComponentImpl::new, ItemMelonSeedsBaseComponentImpl.class)
                .build();
        ItemTypes.PUMPKIN_SEEDS = AllayItemType
                .builder(ItemPumpkinSeedsStackImpl.class)
                .vanillaItem(ItemId.PUMPKIN_SEEDS)
                .addComponent(ItemPumpkinSeedsBaseComponentImpl::new, ItemPumpkinSeedsBaseComponentImpl.class)
                .build();
    }

    public static void initBook() {
        ItemTypes.BOOK = AllayItemType
                .builder(ItemBookStackImpl.class)
                .vanillaItem(ItemId.BOOK)
                .addComponent(ItemBookBaseComponentImpl::new, ItemBookBaseComponentImpl.class)
                .build();
        ItemTypes.WRITABLE_BOOK = AllayItemType
                .builder(ItemWritableBookStackImpl.class)
                .vanillaItem(ItemId.WRITABLE_BOOK)
                .addComponent(ItemWritableBookBaseComponentImpl::new, ItemWritableBookBaseComponentImpl.class)
                .build();
        ItemTypes.WRITTEN_BOOK = AllayItemType
                .builder(ItemWrittenBookStackImpl.class)
                .vanillaItem(ItemId.WRITTEN_BOOK)
                .addComponent(ItemWrittenBookBaseComponentImpl::new, ItemWrittenBookBaseComponentImpl.class)
                .build();
    }

    public static void initMap() {
        ItemTypes.FILLED_MAP = AllayItemType
                .builder(ItemFilledMapStackImpl.class)
                .vanillaItem(ItemId.FILLED_MAP)
                .addComponent(ItemFilledMapBaseComponentImpl::new, ItemFilledMapBaseComponentImpl.class)
                .build();
        ItemTypes.EMPTY_MAP = AllayItemType
                .builder(ItemEmptyMapStackImpl.class)
                .vanillaItem(ItemId.EMPTY_MAP)
                .addComponent(ItemEmptyMapBaseComponentImpl::new, ItemEmptyMapBaseComponentImpl.class)
                .build();
    }

    public static void initProjectile() {
        ItemTypes.SNOWBALL = AllayItemType
                .builder(ItemSnowballStackImpl.class)
                .vanillaItem(ItemId.SNOWBALL)
                .addComponent(() -> new ItemProjectileComponentImpl(EntityId.SNOWBALL, 1.5), ItemProjectileComponentImpl.class)
                .build();
        ItemTypes.ENDER_PEARL = AllayItemType
                .builder(ItemEnderPearlStackImpl.class)
                .vanillaItem(ItemId.ENDER_PEARL)
                .addComponent(() -> new ItemProjectileComponentImpl(EntityId.ENDER_PEARL, 1.5) {
                    @Override
                    public boolean shoot(Entity shooter) {
                        if (shooter instanceof EntityPlayer player) {
                            if (!player.isCooldownEnd(ItemTypes.ENDER_PEARL)) {
                                return false;
                            }

                            // NOTICE: No need to send cooldown to the client since the client will display cooldown
                            // automatically if the item/category has cool down after used it
                            player.setCooldown(ItemTypes.ENDER_PEARL, 20, false);
                        }

                        return super.shoot(shooter);
                    }
                }, ItemProjectileComponentImpl.class)
                .build();
        ItemTypes.EXPERIENCE_BOTTLE = AllayItemType
                .builder(ItemExperienceBottleStackImpl.class)
                .vanillaItem(ItemId.EXPERIENCE_BOTTLE)
                .addComponent(() -> new ItemProjectileComponentImpl(EntityId.XP_BOTTLE, 0.6), ItemProjectileComponentImpl.class)
                .build();
        ItemTypes.WIND_CHARGE = AllayItemType
                .builder(ItemWindChargeStackImpl.class)
                .vanillaItem(ItemId.WIND_CHARGE)
                .addComponent(() -> new ItemProjectileComponentImpl(EntityId.WIND_CHARGE_PROJECTILE, 1.5) {
                    @Override
                    public boolean shoot(Entity shooter) {
                        if (shooter instanceof EntityPlayer player) {
                            if (!player.isCooldownEnd(ItemTypes.WIND_CHARGE)) {
                                return false;
                            }

                            player.setCooldown(ItemTypes.WIND_CHARGE, 10, false);
                        }

                        return super.shoot(shooter);
                    }
                }, ItemProjectileComponentImpl.class)
                .build();
    }

    public static void initPotion() {
        ItemTypes.POTION = AllayItemType
                .builder(ItemPotionStackImpl.class)
                .vanillaItem(ItemId.POTION)
                .addComponent(ItemPotionComponentImpl::new, ItemPotionComponentImpl.class)
                .addComponent(ItemPotionEdibleComponentImpl::new, ItemPotionEdibleComponentImpl.class)
                .build();
        ItemTypes.SPLASH_POTION = AllayItemType
                .builder(ItemSplashPotionStackImpl.class)
                .vanillaItem(ItemId.SPLASH_POTION)
                .addComponent(ItemPotionComponentImpl::new, ItemPotionComponentImpl.class)
                .addComponent(() -> new ItemBottleProjectileComponentImpl(EntityId.SPLASH_POTION, 0.5) {
                    @Override
                    protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
                        var projectile = (EntitySplashPotion) super.createProjectile(shooter, shootPos);
                        projectile.setPotionType(PotionType.fromId(thisItemStack.getMeta()));
                        return projectile;
                    }
                }, ItemBottleProjectileComponentImpl.class)
                .build();
    }

    public static void initLingeringPotion() {
        ItemTypes.LINGERING_POTION = AllayItemType
                .builder(ItemLingeringPotionStackImpl.class)
                .vanillaItem(ItemId.LINGERING_POTION)
                .addComponent(ItemPotionComponentImpl::new, ItemPotionComponentImpl.class)
                .addComponent(() -> new ItemBottleProjectileComponentImpl(EntityId.LINGERING_POTION, 0.5) {
                    @Override
                    protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
                        var projectile = (EntityLingeringPotion) super.createProjectile(shooter, shootPos);
                        projectile.setPotionType(PotionType.fromId(thisItemStack.getMeta()));
                        return projectile;
                    }
                }, ItemBottleProjectileComponentImpl.class)
                .build();
    }

    public static void initBow() {
        ItemTypes.BOW = AllayItemType
                .builder(ItemBowStackImpl.class)
                .vanillaItem(ItemId.BOW)
                .addComponent(ItemBowBaseComponentImpl::new, ItemBowBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.STRING), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initCrossbow() {
        ItemTypes.CROSSBOW = AllayItemType
                .builder(ItemCrossbowStackImpl.class)
                .vanillaItem(ItemId.CROSSBOW)
                .addComponent(ItemCrossbowBaseComponentImpl::new, ItemCrossbowBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.STRING), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initBed() {
        ItemTypes.BED = AllayItemType
                .builder(ItemBedStackImpl.class)
                .addComponent(ItemBedBaseComponentImpl::new, ItemBedBaseComponentImpl.class)
                .vanillaItem(ItemId.BED)
                .build();
    }

    public static void initPainting() {
        ItemTypes.PAINTING = AllayItemType
                .builder(ItemPaintingStackImpl.class)
                .addComponent(ItemPaintingBaseComponentImpl::new, ItemPaintingBaseComponentImpl.class)
                .vanillaItem(ItemId.PAINTING)
                .build();
    }

    public static void initFireworkStar() {
        ItemTypes.FIREWORK_STAR = AllayItemType
                .builder(ItemFireworkStarStackImpl.class)
                .vanillaItem(ItemId.FIREWORK_STAR)
                .addComponent(ItemFireworkStarBaseComponentImpl::new, ItemFireworkStarBaseComponentImpl.class)
                .build();
    }

    public static void initFireworkRocket() {
        ItemTypes.FIREWORK_ROCKET = AllayItemType
                .builder(ItemFireworkRocketStackImpl.class)
                .vanillaItem(ItemId.FIREWORK_ROCKET)
                .addComponent(ItemFireworkRocketBaseComponentImpl::new, ItemFireworkRocketBaseComponentImpl.class)
                .build();
    }

    public static void initDye() {
        ItemTypes.WHITE_DYE = buildDye(ItemId.WHITE_DYE, DyeColor.WHITE);
        ItemTypes.ORANGE_DYE = buildDye(ItemId.ORANGE_DYE, DyeColor.ORANGE);
        ItemTypes.MAGENTA_DYE = buildDye(ItemId.MAGENTA_DYE, DyeColor.MAGENTA);
        ItemTypes.LIGHT_BLUE_DYE = buildDye(ItemId.LIGHT_BLUE_DYE, DyeColor.LIGHT_BLUE);
        ItemTypes.YELLOW_DYE = buildDye(ItemId.YELLOW_DYE, DyeColor.YELLOW);
        ItemTypes.LIME_DYE = buildDye(ItemId.LIME_DYE, DyeColor.LIME);
        ItemTypes.PINK_DYE = buildDye(ItemId.PINK_DYE, DyeColor.PINK);
        ItemTypes.GRAY_DYE = buildDye(ItemId.GRAY_DYE, DyeColor.GRAY);
        ItemTypes.LIGHT_GRAY_DYE = buildDye(ItemId.LIGHT_GRAY_DYE, DyeColor.LIGHT_GRAY);
        ItemTypes.CYAN_DYE = buildDye(ItemId.CYAN_DYE, DyeColor.CYAN);
        ItemTypes.PURPLE_DYE = buildDye(ItemId.PURPLE_DYE, DyeColor.PURPLE);
        ItemTypes.BLUE_DYE = buildDye(ItemId.BLUE_DYE, DyeColor.BLUE);
        ItemTypes.BROWN_DYE = buildDye(ItemId.BROWN_DYE, DyeColor.BROWN);
        ItemTypes.GREEN_DYE = buildDye(ItemId.GREEN_DYE, DyeColor.GREEN);
        ItemTypes.RED_DYE = buildDye(ItemId.RED_DYE, DyeColor.RED);
        ItemTypes.BLACK_DYE = buildDye(ItemId.BLACK_DYE, DyeColor.BLACK);
        // This item type is replaced by 'minecraft:ink_sac', but I don't know why mojang doesn't remove it
        ItemTypes.DYE = buildDye(ItemId.DYE, DyeColor.BLACK);
    }

    private static ItemType<ItemDyeStack> buildDye(ItemId itemId, DyeColor dyeColor) {
        return AllayItemType
                .builder(ItemDyeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemDyeComponentImpl(dyeColor), ItemDyeComponentImpl.class)
                .build();
    }

    public static void initSpear() {
        ItemTypes.COPPER_SPEAR = buildSpear(ItemId.COPPER_SPEAR, ItemId.COPPER_INGOT);
        ItemTypes.IRON_SPEAR = buildSpear(ItemId.IRON_SPEAR, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_SPEAR = buildSpear(ItemId.DIAMOND_SPEAR, ItemId.DIAMOND);
        ItemTypes.NETHERITE_SPEAR = buildSpear(ItemId.NETHERITE_SPEAR, ItemId.NETHERITE_INGOT);
        ItemTypes.GOLDEN_SPEAR = buildSpear(ItemId.GOLDEN_SPEAR, ItemId.GOLD_INGOT);
        ItemTypes.STONE_SPEAR = buildSpear(ItemId.STONE_SPEAR, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_SPEAR = buildSpear(ItemId.WOODEN_SPEAR, ItemId.PLANKS);
    }

    // TODO: implement spear
    private static ItemType<ItemSpearStack> buildSpear(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemSpearStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initRedstoneWire() {
        ItemTypes.REDSTONE = AllayItemType
                .builder(ItemRedstoneStackImpl.class)
                .vanillaItem(ItemId.REDSTONE)
                .addComponent(ItemRedstoneBaseComponentImpl::new, ItemRedstoneBaseComponentImpl.class)
                .build();
    }

    public static void initRepeater() {
        ItemTypes.REPEATER = AllayItemType
                .builder(ItemRepeaterStackImpl.class)
                .vanillaItem(ItemId.REPEATER)
                .addComponent(ItemRepeaterBaseComponentImpl::new, ItemRepeaterBaseComponentImpl.class)
                .build();
    }

    public static void initComparator() {
        ItemTypes.COMPARATOR = AllayItemType
                .builder(ItemComparatorStackImpl.class)
                .vanillaItem(ItemId.COMPARATOR)
                .addComponent(ItemComparatorBaseComponentImpl::new, ItemComparatorBaseComponentImpl.class)
                .build();
    }

    public static void initMace() {
        ItemTypes.MACE = AllayItemType
                .builder(ItemMaceStackImpl.class)
                .vanillaItem(ItemId.MACE)
                .addComponent(ItemMaceBaseComponentImpl::new, ItemMaceBaseComponentImpl.class)
                .addComponent(ItemSwordToolComponentImpl::new, ItemSwordToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.BREEZE_ROD), ItemRepairableComponentImpl.class)
                .build();
    }

    public static void initShield() {
        ItemTypes.SHIELD = AllayItemType
                .builder(ItemShieldStackImpl.class)
                .vanillaItem(ItemId.SHIELD)
                .addComponent(ItemShieldBaseComponentImpl::new, ItemShieldBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.PLANKS), ItemRepairableComponentImpl.class)
                .build();
    }
}
