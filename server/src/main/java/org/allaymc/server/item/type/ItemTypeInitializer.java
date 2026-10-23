package org.allaymc.server.item.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityLingeringPotion;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.entity.property.enums.ClimateVariant;
import org.allaymc.api.item.ItemStack;
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
import org.allaymc.server.item.component.book.ItemBookBaseComponentImpl;
import org.allaymc.server.item.component.book.ItemWritableBookBaseComponentImpl;
import org.allaymc.server.item.component.book.ItemWrittenBookBaseComponentImpl;
import org.allaymc.server.item.component.edible.*;
import org.allaymc.server.item.component.firework.ItemFireworkRocketBaseComponentImpl;
import org.allaymc.server.item.component.firework.ItemFireworkStarBaseComponentImpl;
import org.allaymc.server.item.component.map.ItemEmptyMapBaseComponentImpl;
import org.allaymc.server.item.component.map.ItemFilledMapBaseComponentImpl;
import org.allaymc.server.item.component.planting.*;
import org.allaymc.server.item.component.projectile.ItemBottleProjectileComponentImpl;
import org.allaymc.server.item.component.projectile.ItemEggProjectileComponentImpl;
import org.allaymc.server.item.component.projectile.ItemProjectileComponentImpl;
import org.allaymc.server.item.component.tool.ItemHoeToolComponentImpl;
import org.allaymc.server.item.component.tool.ItemSwordToolComponentImpl;
import org.allaymc.server.item.component.tool.ItemToolComponentImpl;
import org.allaymc.server.item.component.weapon.*;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.impl.*;
import org.joml.Vector3d;

import java.util.function.Supplier;

/**
 * {@code ItemTypeInitializer} registers specialized vanilla item types and assigns
 * their {@code ItemTypes} catalog fields. The startup populator invokes its
 * public parameterless initializers before the generated default initializer,
 * so non-{@code null} specialized fields retain their component composition.
 *
 * <p>
 * Each builder registers the type before the surrounding assignment publishes
 * its catalog reference. Family helpers retain separate vanilla identities even
 * when their stacks share implementation classes; their component factories
 * select repair materials, colors, equipment slots, or deferred block and entity
 * lookups. Required item-data registries must already be loaded and registration
 * must remain mutable. A later failure does not undo types installed by earlier
 * statements, and invoking these initializers again can replace existing registry
 * and catalog references.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class ItemTypeInitializer {
    /**
     * Registers the banner item type and assigns {@code ItemTypes.BANNER}.
     * The specialized base component supplies banner color, kind, pattern storage,
     * and standing-versus-wall placement instead of the generic base provider.
     */
    public static void initBanner() {
        ItemTypes.BANNER = AllayItemType
                .builder(ItemBannerStackImpl.class)
                .vanillaItem(ItemId.BANNER)
                .addComponent(ItemBannerBaseComponentImpl::new, ItemBannerBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the honeycomb item type with its specialized base component.
     * The selected component supplies honeycomb block interactions, and the completed
     * builder result becomes the {@code ItemTypes.HONEYCOMB} catalog reference.
     */
    public static void initHoneycomb() {
        ItemTypes.HONEYCOMB = AllayItemType
                .builder(ItemHoneycombStackImpl.class)
                .vanillaItem(ItemId.HONEYCOMB)
                .addComponent(ItemHoneycombBaseComponentImpl::new, ItemHoneycombBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the music-disc catalog entries with their explicit disc-kind mappings.
     * Each identifier receives a separate type through {@code buildMusicDisc}, while
     * the component's {@code DiscType} supplies display and comparator data.
     */
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
        ItemTypes.MUSIC_DISC_BOUNCE = buildMusicDisc(ItemId.MUSIC_DISC_BOUNCE, DiscType.DISC_BOUNCE);
    }

    /**
     * Builds and registers a music-disc type retaining the supplied disc kind.
     * Each stack receives a disc base component initialized from its stack values;
     * the caller publishes the returned type to its catalog field.
     */
    private static ItemType<ItemMusicDiscStack> buildMusicDisc(ItemId itemId, DiscType discType) {
        return AllayItemType
                .builder(ItemMusicDiscStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(initInfo -> new ItemMusicDiscBaseComponentImpl(initInfo, discType), ItemMusicDiscBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers ordinary and effect-bearing food types with their nutrition components.
     * Ordinary foods select explicit food and saturation values, while specialized
     * edible components provide additional effects. Plantable berry and potato items
     * also receive their specialized base interactions.
     */
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
                .addComponent(ItemSweetBerriesBaseComponentImpl::new, ItemSweetBerriesBaseComponentImpl.class)
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
                .addComponent(ItemGlowBerriesBaseComponentImpl::new, ItemGlowBerriesBaseComponentImpl.class)
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

    /**
     * Registers spawn-egg catalog entries with their configured entity identities.
     * Each egg keeps its own vanilla item identifier, and {@code buildSpawnEgg}
     * binds the entity mapping without resolving an entity type during registration.
     */
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
        ItemTypes.CAMEL_HUSK_SPAWN_EGG = buildSpawnEgg(ItemId.CAMEL_HUSK_SPAWN_EGG, EntityId.CAMEL_HUSK);
        ItemTypes.CAT_SPAWN_EGG = buildSpawnEgg(ItemId.CAT_SPAWN_EGG, EntityId.CAT);
        ItemTypes.CAVE_SPIDER_SPAWN_EGG = buildSpawnEgg(ItemId.CAVE_SPIDER_SPAWN_EGG, EntityId.CAVE_SPIDER);
        ItemTypes.CHICKEN_SPAWN_EGG = buildSpawnEgg(ItemId.CHICKEN_SPAWN_EGG, EntityId.CHICKEN);
        ItemTypes.COD_SPAWN_EGG = buildSpawnEgg(ItemId.COD_SPAWN_EGG, EntityId.COD);
        ItemTypes.COPPER_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.COPPER_GOLEM_SPAWN_EGG, EntityId.COPPER_GOLEM);
        ItemTypes.COW_SPAWN_EGG = buildSpawnEgg(ItemId.COW_SPAWN_EGG, EntityId.COW);
        ItemTypes.CREAKING_SPAWN_EGG = buildSpawnEgg(ItemId.CREAKING_SPAWN_EGG, EntityId.CREAKING);
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
        ItemTypes.HAPPY_GHAST_SPAWN_EGG = buildSpawnEgg(ItemId.HAPPY_GHAST_SPAWN_EGG, EntityId.HAPPY_GHAST);
        ItemTypes.HOGLIN_SPAWN_EGG = buildSpawnEgg(ItemId.HOGLIN_SPAWN_EGG, EntityId.HOGLIN);
        ItemTypes.HORSE_SPAWN_EGG = buildSpawnEgg(ItemId.HORSE_SPAWN_EGG, EntityId.HORSE);
        ItemTypes.HUSK_SPAWN_EGG = buildSpawnEgg(ItemId.HUSK_SPAWN_EGG, EntityId.HUSK);
        ItemTypes.IRON_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.IRON_GOLEM_SPAWN_EGG, EntityId.IRON_GOLEM);
        ItemTypes.LLAMA_SPAWN_EGG = buildSpawnEgg(ItemId.LLAMA_SPAWN_EGG, EntityId.LLAMA);
        ItemTypes.MAGMA_CUBE_SPAWN_EGG = buildSpawnEgg(ItemId.MAGMA_CUBE_SPAWN_EGG, EntityId.MAGMA_CUBE);
        ItemTypes.MOOSHROOM_SPAWN_EGG = buildSpawnEgg(ItemId.MOOSHROOM_SPAWN_EGG, EntityId.MOOSHROOM);
        ItemTypes.MULE_SPAWN_EGG = buildSpawnEgg(ItemId.MULE_SPAWN_EGG, EntityId.MULE);
        ItemTypes.NAUTILUS_SPAWN_EGG = buildSpawnEgg(ItemId.NAUTILUS_SPAWN_EGG, EntityId.NAUTILUS);
        ItemTypes.NPC_SPAWN_EGG = buildSpawnEgg(ItemId.NPC_SPAWN_EGG, EntityId.NPC);
        ItemTypes.OCELOT_SPAWN_EGG = buildSpawnEgg(ItemId.OCELOT_SPAWN_EGG, EntityId.OCELOT);
        ItemTypes.PANDA_SPAWN_EGG = buildSpawnEgg(ItemId.PANDA_SPAWN_EGG, EntityId.PANDA);
        ItemTypes.PARCHED_SPAWN_EGG = buildSpawnEgg(ItemId.PARCHED_SPAWN_EGG, EntityId.PARCHED);
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
        ItemTypes.SKELETON_HORSE_SPAWN_EGG = buildSpawnEgg(ItemId.SKELETON_HORSE_SPAWN_EGG, EntityId.SKELETON_HORSE);
        ItemTypes.SLIME_SPAWN_EGG = buildSpawnEgg(ItemId.SLIME_SPAWN_EGG, EntityId.SLIME);
        ItemTypes.SNIFFER_SPAWN_EGG = buildSpawnEgg(ItemId.SNIFFER_SPAWN_EGG, EntityId.SNIFFER);
        ItemTypes.SNOW_GOLEM_SPAWN_EGG = buildSpawnEgg(ItemId.SNOW_GOLEM_SPAWN_EGG, EntityId.SNOW_GOLEM);
        ItemTypes.SULFUR_CUBE_SPAWN_EGG = buildSpawnEgg(ItemId.SULFUR_CUBE_SPAWN_EGG, EntityId.SULFUR_CUBE);
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
        ItemTypes.ZOMBIE_NAUTILUS_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_NAUTILUS_SPAWN_EGG, EntityId.ZOMBIE_NAUTILUS);
        ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_PIGMAN_SPAWN_EGG, EntityId.ZOMBIE_PIGMAN);
        ItemTypes.ZOMBIE_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_SPAWN_EGG, EntityId.ZOMBIE);
        ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG = buildSpawnEgg(ItemId.ZOMBIE_VILLAGER_SPAWN_EGG, EntityId.ZOMBIE_VILLAGER);
    }

    /**
     * Builds and registers an egg type for the supplied entity identity.
     * The stack base component retains {@code entityId} for later spawn handling;
     * the returned item type remains specific to {@code itemId}.
     */
    private static ItemType<ItemSpawnEggStack> buildSpawnEgg(ItemId itemId, EntityId entityId) {
        return AllayItemType
                .builder(ItemSpawnEggStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(
                        initInfo -> new ItemSpawnEggBaseComponentImpl(initInfo, entityId),
                        ItemSpawnEggBaseComponentImpl.class
                )
                .build();
    }

    /**
     * Registers sword catalog entries with sword-tool and repair capabilities.
     * Each vanilla identifier selects its configured material through
     * {@code buildSword}, so stacks share the sword implementation while retaining
     * their own item data and repair mapping.
     */
    public static void initSword() {
        ItemTypes.DIAMOND_SWORD = buildSword(ItemId.DIAMOND_SWORD, ItemId.DIAMOND);
        ItemTypes.GOLDEN_SWORD = buildSword(ItemId.GOLDEN_SWORD, ItemId.GOLD_INGOT);
        ItemTypes.IRON_SWORD = buildSword(ItemId.IRON_SWORD, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_SWORD = buildSword(ItemId.NETHERITE_SWORD, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_SWORD = buildSword(ItemId.STONE_SWORD, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_SWORD = buildSword(ItemId.WOODEN_SWORD, ItemId.PLANKS);
        ItemTypes.COPPER_SWORD = buildSword(ItemId.COPPER_SWORD, ItemId.COPPER_INGOT);
    }

    /**
     * Builds and registers a sword with its tool behavior and repair material.
     * The repair component resolves {@code repairItemId} lazily, while the sword
     * tool component receives each stack's initialization values.
     */
    private static ItemType<ItemSwordStack> buildSword(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemSwordStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemSwordToolComponentImpl::new, ItemSwordToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers pickaxe catalog entries with tool and repair capabilities.
     * Each vanilla identifier keeps its own gameplay data, while the shared helper
     * binds the material used by that type's repair component.
     */
    public static void initPickaxe() {
        ItemTypes.DIAMOND_PICKAXE = buildPickaxe(ItemId.DIAMOND_PICKAXE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_PICKAXE = buildPickaxe(ItemId.GOLDEN_PICKAXE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_PICKAXE = buildPickaxe(ItemId.IRON_PICKAXE, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_PICKAXE = buildPickaxe(ItemId.NETHERITE_PICKAXE, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_PICKAXE = buildPickaxe(ItemId.STONE_PICKAXE, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_PICKAXE = buildPickaxe(ItemId.WOODEN_PICKAXE, ItemId.PLANKS);
        ItemTypes.COPPER_PICKAXE = buildPickaxe(ItemId.COPPER_PICKAXE, ItemId.COPPER_INGOT);
    }

    /**
     * Builds and registers a pickaxe with standard tool behavior and repair support.
     * The tool component uses stack initialization, while the repair component
     * defers resolving the supplied material identity until queried.
     */
    private static ItemType<ItemPickaxeStack> buildPickaxe(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemPickaxeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers axe catalog entries with surface interactions, tool behavior, and
     * repair support. Each type retains its vanilla identity and data while
     * {@code buildAxe} attaches the corresponding repair-material resolver.
     */
    public static void initAxe() {
        ItemTypes.DIAMOND_AXE = buildAxe(ItemId.DIAMOND_AXE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_AXE = buildAxe(ItemId.GOLDEN_AXE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_AXE = buildAxe(ItemId.IRON_AXE, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_AXE = buildAxe(ItemId.NETHERITE_AXE, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_AXE = buildAxe(ItemId.STONE_AXE, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_AXE = buildAxe(ItemId.WOODEN_AXE, ItemId.PLANKS);
        ItemTypes.COPPER_AXE = buildAxe(ItemId.COPPER_AXE, ItemId.COPPER_INGOT);
    }

    /**
     * Builds and registers an axe with specialized base and standard tool components.
     * The base handles axe block interactions, while the separate repair capability
     * resolves the supplied repair material on demand.
     */
    private static ItemType<ItemAxeStack> buildAxe(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemAxeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemAxeBaseComponentImpl::new, ItemAxeBaseComponentImpl.class)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers shovel catalog entries with standard tool and repair components.
     * The shared stack class retains each type's vanilla data, and the helper
     * selects the material accepted for that type's repairs.
     */
    public static void initShovel() {
        ItemTypes.DIAMOND_SHOVEL = buildShovel(ItemId.DIAMOND_SHOVEL, ItemId.DIAMOND);
        ItemTypes.GOLDEN_SHOVEL = buildShovel(ItemId.GOLDEN_SHOVEL, ItemId.GOLD_INGOT);
        ItemTypes.IRON_SHOVEL = buildShovel(ItemId.IRON_SHOVEL, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_SHOVEL = buildShovel(ItemId.NETHERITE_SHOVEL, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_SHOVEL = buildShovel(ItemId.STONE_SHOVEL, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_SHOVEL = buildShovel(ItemId.WOODEN_SHOVEL, ItemId.PLANKS);
        ItemTypes.COPPER_SHOVEL = buildShovel(ItemId.COPPER_SHOVEL, ItemId.COPPER_INGOT);
    }

    /**
     * Builds and registers a shovel with standard tool and repair capabilities.
     * The generic base provider remains available through the builder, while the
     * repair component resolves the selected material lazily.
     */
    private static ItemType<ItemShovelStack> buildShovel(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemShovelStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemToolComponentImpl::new, ItemToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers hoe catalog entries with hoe-specific tool behavior and repair support.
     * Each selected material is bound through the helper while vanilla item data
     * and identity remain specific to the catalog entry.
     */
    public static void initHoe() {
        ItemTypes.DIAMOND_HOE = buildHoe(ItemId.DIAMOND_HOE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_HOE = buildHoe(ItemId.GOLDEN_HOE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_HOE = buildHoe(ItemId.IRON_HOE, ItemId.IRON_INGOT);
        ItemTypes.NETHERITE_HOE = buildHoe(ItemId.NETHERITE_HOE, ItemId.NETHERITE_INGOT);
        ItemTypes.STONE_HOE = buildHoe(ItemId.STONE_HOE, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_HOE = buildHoe(ItemId.WOODEN_HOE, ItemId.PLANKS);
        ItemTypes.COPPER_HOE = buildHoe(ItemId.COPPER_HOE, ItemId.COPPER_INGOT);
    }

    /**
     * Builds and registers a hoe with its specialized tool component and repair
     * material. The tool provider receives stack initialization; the repair provider
     * defers material lookup until a compatibility query runs.
     */
    private static ItemType<ItemHoeStack> buildHoe(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemHoeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemHoeToolComponentImpl::new, ItemHoeToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers colored and uncolored shulker-box catalog entries with stored-item
     * base behavior. The entries retain separate vanilla identities while using the
     * same component implementation for slot persistence and placed-container
     * contents.
     */
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

    /**
     * Builds and registers one shulker-box type with stored-inventory base behavior.
     * The helper preserves {@code itemId} as its vanilla identity; later stack
     * construction supplies the initialization used to restore carried contents.
     */
    private static ItemType<ItemShulkerBoxStack> buildShulkerBox(ItemId itemId) {
        return AllayItemType
                .builder(ItemShulkerBoxStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(ItemShulkerBoxBaseComponentImpl::new, ItemShulkerBoxBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the air item type and assigns {@code ItemTypes.AIR}.
     * The selected air base component uses predefined zero-count initialization and
     * skips factory extra-tag loading, replacing the generic base provider.
     */
    public static void initAir() {
        ItemTypes.AIR = AllayItemType
                .builder(ItemAirStackImpl.class)
                .vanillaItem(ItemId.AIR)
                .addComponent(ItemAirBaseComponentImpl::new, ItemAirBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers helmet catalog entries with their armor-slot and repair mappings.
     * Leather helmets also receive dye support; the turtle helmet uses its own base
     * stack and scute repair component without the shared trim provider.
     */
    public static void initHelmets() {
        ItemTypes.CHAINMAIL_HELMET = buildArmor(ItemHelmetStackImpl.class, ItemId.CHAINMAIL_HELMET, ArmorType.HELMET, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_HELMET = buildArmor(ItemHelmetStackImpl.class, ItemId.DIAMOND_HELMET, ArmorType.HELMET, ItemId.DIAMOND);
        ItemTypes.GOLDEN_HELMET = buildArmor(ItemHelmetStackImpl.class, ItemId.GOLDEN_HELMET, ArmorType.HELMET, ItemId.GOLD_INGOT);
        ItemTypes.IRON_HELMET = buildArmor(ItemHelmetStackImpl.class, ItemId.IRON_HELMET, ArmorType.HELMET, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_HELMET = buildLeatherArmor(ItemLeatherHelmetStackImpl.class, ItemId.LEATHER_HELMET, ArmorType.HELMET);
        ItemTypes.NETHERITE_HELMET = buildArmor(ItemHelmetStackImpl.class, ItemId.NETHERITE_HELMET, ArmorType.HELMET, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_HELMET = buildArmor(ItemHelmetStackImpl.class, ItemId.COPPER_HELMET, ArmorType.HELMET, ItemId.COPPER_INGOT);
        ItemTypes.TURTLE_HELMET = AllayItemType
                .builder(ItemTurtleHelmetStackImpl.class)
                .vanillaItem(ItemId.TURTLE_HELMET)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .addComponent(() -> new ItemWearableComponentImpl(ArmorType.HELMET), ItemWearableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.TURTLE_SCUTE), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers chestplate catalog entries and the chest-slot elytra type.
     * Leather chestplates add dye support, while elytra uses armor base behavior and
     * phantom-membrane repair without the shared armor trim provider.
     */
    public static void initChestplates() {
        ItemTypes.CHAINMAIL_CHESTPLATE = buildArmor(ItemChestplateStackImpl.class, ItemId.CHAINMAIL_CHESTPLATE, ArmorType.CHESTPLATE, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_CHESTPLATE = buildArmor(ItemChestplateStackImpl.class, ItemId.DIAMOND_CHESTPLATE, ArmorType.CHESTPLATE, ItemId.DIAMOND);
        ItemTypes.GOLDEN_CHESTPLATE = buildArmor(ItemChestplateStackImpl.class, ItemId.GOLDEN_CHESTPLATE, ArmorType.CHESTPLATE, ItemId.GOLD_INGOT);
        ItemTypes.IRON_CHESTPLATE = buildArmor(ItemChestplateStackImpl.class, ItemId.IRON_CHESTPLATE, ArmorType.CHESTPLATE, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_CHESTPLATE = buildLeatherArmor(ItemLeatherChestplateStackImpl.class, ItemId.LEATHER_CHESTPLATE, ArmorType.CHESTPLATE);
        ItemTypes.NETHERITE_CHESTPLATE = buildArmor(ItemChestplateStackImpl.class, ItemId.NETHERITE_CHESTPLATE, ArmorType.CHESTPLATE, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_CHESTPLATE = buildArmor(ItemChestplateStackImpl.class, ItemId.COPPER_CHESTPLATE, ArmorType.CHESTPLATE, ItemId.COPPER_INGOT);
        ItemTypes.ELYTRA = AllayItemType
                .builder(ItemElytraStackImpl.class)
                .vanillaItem(ItemId.ELYTRA)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .addComponent(() -> new ItemWearableComponentImpl(ArmorType.CHESTPLATE), ItemWearableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.PHANTOM_MEMBRANE), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers leggings catalog entries with the leggings equipment slot and their
     * repair materials. The leather entry uses the dyeable helper; other entries
     * use the shared armor helper with trim support.
     */
    public static void initLeggings() {
        ItemTypes.CHAINMAIL_LEGGINGS = buildArmor(ItemLeggingsStackImpl.class, ItemId.CHAINMAIL_LEGGINGS, ArmorType.LEGGINGS, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_LEGGINGS = buildArmor(ItemLeggingsStackImpl.class, ItemId.DIAMOND_LEGGINGS, ArmorType.LEGGINGS, ItemId.DIAMOND);
        ItemTypes.GOLDEN_LEGGINGS = buildArmor(ItemLeggingsStackImpl.class, ItemId.GOLDEN_LEGGINGS, ArmorType.LEGGINGS, ItemId.GOLD_INGOT);
        ItemTypes.IRON_LEGGINGS = buildArmor(ItemLeggingsStackImpl.class, ItemId.IRON_LEGGINGS, ArmorType.LEGGINGS, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_LEGGINGS = buildLeatherArmor(ItemLeatherLeggingsStackImpl.class, ItemId.LEATHER_LEGGINGS, ArmorType.LEGGINGS);
        ItemTypes.NETHERITE_LEGGINGS = buildArmor(ItemLeggingsStackImpl.class, ItemId.NETHERITE_LEGGINGS, ArmorType.LEGGINGS, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_LEGGINGS = buildArmor(ItemLeggingsStackImpl.class, ItemId.COPPER_LEGGINGS, ArmorType.LEGGINGS, ItemId.COPPER_INGOT);
    }

    /**
     * Registers boot catalog entries with the boots equipment slot and repair
     * materials. Leather boots add custom-color support, while the common armor
     * composition supplies wearability, durability behavior, and trim persistence.
     */
    public static void initBoots() {
        ItemTypes.CHAINMAIL_BOOTS = buildArmor(ItemBootsStackImpl.class, ItemId.CHAINMAIL_BOOTS, ArmorType.BOOTS, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_BOOTS = buildArmor(ItemBootsStackImpl.class, ItemId.DIAMOND_BOOTS, ArmorType.BOOTS, ItemId.DIAMOND);
        ItemTypes.GOLDEN_BOOTS = buildArmor(ItemBootsStackImpl.class, ItemId.GOLDEN_BOOTS, ArmorType.BOOTS, ItemId.GOLD_INGOT);
        ItemTypes.IRON_BOOTS = buildArmor(ItemBootsStackImpl.class, ItemId.IRON_BOOTS, ArmorType.BOOTS, ItemId.IRON_INGOT);
        ItemTypes.LEATHER_BOOTS = buildLeatherArmor(ItemLeatherBootsStackImpl.class, ItemId.LEATHER_BOOTS, ArmorType.BOOTS);
        ItemTypes.NETHERITE_BOOTS = buildArmor(ItemBootsStackImpl.class, ItemId.NETHERITE_BOOTS, ArmorType.BOOTS, ItemId.NETHERITE_INGOT);
        ItemTypes.COPPER_BOOTS = buildArmor(ItemBootsStackImpl.class, ItemId.COPPER_BOOTS, ArmorType.BOOTS, ItemId.COPPER_INGOT);
    }

    /**
     * Builds and registers armor with base, wearable, trim, and repair components.
     * The caller must keep {@code stackClass} consistent with the inferred return
     * type because its generic cast is unchecked; the supplied armor type selects
     * the equipment slot.
     */
    @SuppressWarnings("unchecked")
    private static <T extends ItemStack> ItemType<T> buildArmor(Class<? extends ItemStack> stackClass, ItemId itemId, ArmorType armorType, ItemId repairItemId) {
        return AllayItemType
                .builder((Class<T>) stackClass)
                .vanillaItem(itemId)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .addComponent(() -> new ItemWearableComponentImpl(armorType), ItemWearableComponentImpl.class)
                .addComponent(ItemTrimmableComponentImpl::new, ItemTrimmableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Builds and registers dyeable leather armor with wear, trim, and leather-repair
     * capabilities. The caller must match {@code stackClass} to the inferred return
     * type because its generic cast is unchecked; the armor type selects the slot.
     */
    @SuppressWarnings("unchecked")
    private static <T extends ItemStack> ItemType<T> buildLeatherArmor(Class<? extends ItemStack> stackClass, ItemId itemId, ArmorType armorType) {
        return AllayItemType
                .builder((Class<T>) stackClass)
                .vanillaItem(itemId)
                .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
                .addComponent(() -> new ItemWearableComponentImpl(armorType), ItemWearableComponentImpl.class)
                .addComponent(ItemTrimmableComponentImpl::new, ItemTrimmableComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.LEATHER), ItemRepairableComponentImpl.class)
                .addComponent(ItemDyeableComponentImpl::new, ItemDyeableComponentImpl.class)
                .build();
    }

    /**
     * Registers leather horse armor with custom-color capability and vanilla data.
     * This composition adds the dyeable component only; it does not attach the
     * player armor-slot transfer behavior used by ordinary wearable armor.
     */
    public static void initLeatherHorseArmor() {
        ItemTypes.LEATHER_HORSE_ARMOR = AllayItemType
                .builder(ItemLeatherHorseArmorStackImpl.class)
                .vanillaItem(ItemId.LEATHER_HORSE_ARMOR)
                .addComponent(ItemDyeableComponentImpl::new, ItemDyeableComponentImpl.class)
                .build();
    }

    /**
     * Registers each listed sign item with its matching standing and wall block
     * identities. The shared sign component resolves those block types later, so
     * registration preserves material-specific placement without performing block
     * lookup immediately.
     */
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

    /**
     * Builds and registers a sign type with deferred standing and wall block
     * lookups. The supplied block identities remain separate from the item identity,
     * and the sign base component selects their placement path later.
     */
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

    /**
     * Registers empty, liquid, and entity-bearing buckets alongside the drinkable
     * milk bucket. Ordinary bucket compositions resolve their liquid and optional
     * entity later; milk uses an edible component instead of bucket-emptying
     * behavior.
     */
    public static void initBuckets() {
        ItemTypes.AXOLOTL_BUCKET = buildBucket(ItemId.AXOLOTL_BUCKET, BlockId.WATER, EntityId.AXOLOTL);
        ItemTypes.BUCKET = buildBucket(ItemId.BUCKET, BlockId.AIR, null);
        ItemTypes.COD_BUCKET = buildBucket(ItemId.COD_BUCKET, BlockId.WATER, EntityId.COD);
        ItemTypes.LAVA_BUCKET = buildBucket(ItemId.LAVA_BUCKET, BlockId.LAVA, null);
        ItemTypes.MILK_BUCKET = buildMilkBucket();
        ItemTypes.POWDER_SNOW_BUCKET = buildBucket(ItemId.POWDER_SNOW_BUCKET, BlockId.POWDER_SNOW, null);
        ItemTypes.PUFFERFISH_BUCKET = buildBucket(ItemId.PUFFERFISH_BUCKET, BlockId.WATER, EntityId.PUFFERFISH);
        ItemTypes.SALMON_BUCKET = buildBucket(ItemId.SALMON_BUCKET, BlockId.WATER, EntityId.SALMON);
        ItemTypes.SULFUR_CUBE_BUCKET = buildBucket(ItemId.SULFUR_CUBE_BUCKET, BlockId.WATER, EntityId.SULFUR_CUBE);
        ItemTypes.TADPOLE_BUCKET = buildBucket(ItemId.TADPOLE_BUCKET, BlockId.WATER, EntityId.TADPOLE);
        ItemTypes.TROPICAL_FISH_BUCKET = buildBucket(ItemId.TROPICAL_FISH_BUCKET, BlockId.WATER, EntityId.TROPICALFISH);
        ItemTypes.WATER_BUCKET = buildBucket(ItemId.WATER_BUCKET, BlockId.WATER, null);
    }

    /**
     * Builds and registers a bucket with configured liquid and optional entity
     * identities. {@code entityId} may be {@code null} for liquid-only or empty
     * buckets; the component resolves both content types when queried.
     */
    private static ItemType<ItemBucketStack> buildBucket(ItemId itemId, BlockId liquidBlockId, EntityId entityId) {
        return AllayItemType
                .builder(ItemBucketStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemBucketComponentImpl(liquidBlockId, entityId), ItemBucketComponentImpl.class)
                .build();
    }

    /**
     * Builds and registers milk with its dedicated drinkable consumption component.
     * The composition leaves the generic base provider in place and does not add
     * the ordinary liquid-placement bucket component.
     */
    private static ItemType<ItemMilkBucketStack> buildMilkBucket() {
        return AllayItemType
                .builder(ItemMilkBucketStackImpl.class)
                .vanillaItem(ItemId.MILK_BUCKET)
                .addComponent(ItemMilkBucketEdibleComponentImpl::new, ItemMilkBucketEdibleComponentImpl.class)
                .build();
    }

    /**
     * Registers flint and steel with its specialized item base behavior.
     * The resulting type retains vanilla gameplay data and replaces the generic
     * base provider with the ignition-capable implementation.
     */
    public static void initFlintAndSteel() {
        ItemTypes.FLINT_AND_STEEL = AllayItemType
                .builder(ItemFlintAndSteelStackImpl.class)
                .vanillaItem(ItemId.FLINT_AND_STEEL)
                .addComponent(ItemFlintAndSteelBaseComponentImpl::new, ItemFlintAndSteelBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the end-crystal item type with its entity-placement base component.
     * The completed type uses vanilla data, while the specialized component handles
     * support, space, and player restrictions when the item is used.
     */
    public static void initEndCrystal() {
        ItemTypes.END_CRYSTAL = AllayItemType
                .builder(ItemEndCrystalStackImpl.class)
                .vanillaItem(ItemId.END_CRYSTAL)
                .addComponent(ItemEndCrystalBaseComponentImpl::new, ItemEndCrystalBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the listed seed and pod types with their planting base components.
     * Each item retains its own vanilla identity and uses its corresponding
     * component to decide the crop and placement behavior.
     */
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
        ItemTypes.TORCHFLOWER_SEEDS = AllayItemType
                .builder(ItemTorchflowerSeedsStackImpl.class)
                .vanillaItem(ItemId.TORCHFLOWER_SEEDS)
                .addComponent(ItemTorchflowerSeedsBaseComponentImpl::new, ItemTorchflowerSeedsBaseComponentImpl.class)
                .build();
        ItemTypes.PITCHER_POD = AllayItemType
                .builder(ItemPitcherPodStackImpl.class)
                .vanillaItem(ItemId.PITCHER_POD)
                .addComponent(ItemPitcherPodBaseComponentImpl::new, ItemPitcherPodBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers ordinary, writable, and written books with separate base components.
     * Each composition retains its own vanilla data, while writable and written
     * implementations supply the corresponding stored-book behavior to their stacks.
     */
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

    /**
     * Registers empty and filled map types with their specialized base components.
     * The two catalog fields keep distinct vanilla identities, allowing stack
     * behavior to distinguish map creation from existing map data.
     */
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

    /**
     * Registers throwable items, including climate-specific eggs and cooldown-aware
     * launchers. Ender pearls start a twenty-tick player cooldown and wind charges
     * start ten ticks before inherited launch processing; a later launch rejection
     * does not undo those cooldowns.
     */
    public static void initProjectile() {
        ItemTypes.SNOWBALL = AllayItemType
                .builder(ItemSnowballStackImpl.class)
                .vanillaItem(ItemId.SNOWBALL)
                .addComponent(() -> new ItemProjectileComponentImpl(EntityId.SNOWBALL, 1.5), ItemProjectileComponentImpl.class)
                .build();
        ItemTypes.EGG = buildEgg(ItemId.EGG, ClimateVariant.TEMPERATE);
        ItemTypes.BLUE_EGG = buildEgg(ItemId.BLUE_EGG, ClimateVariant.COLD);
        ItemTypes.BROWN_EGG = buildEgg(ItemId.BROWN_EGG, ClimateVariant.WARM);
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

                            // Start the twenty-tick server cooldown before inherited
                            // launch dispatch; a cancelled launch does not refund it,
                            // and this call does not send a cooldown packet.
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

                            // Start the ten-tick server cooldown before inherited
                            // launch dispatch, retaining it even if projectile launch
                            // later fails or its event is cancelled.
                            player.setCooldown(ItemTypes.WIND_CHARGE, 10, false);
                        }

                        return super.shoot(shooter);
                    }
                }, ItemProjectileComponentImpl.class)
                .build();
    }

    /**
     * Builds and registers an egg item with the supplied climate variant.
     * The egg projectile component captures that variant for later projectile
     * behavior while the type retains the selected vanilla egg identity.
     */
    private static ItemType<ItemEggStack> buildEgg(ItemId itemId, ClimateVariant climateVariant) {
        return AllayItemType
                .builder(ItemEggStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemEggProjectileComponentImpl(climateVariant), ItemProjectileComponentImpl.class)
                .build();
    }

    /**
     * Registers drinkable and splash potion types with potion-state components.
     * The splash launcher transfers the item's metadata-selected potion kind to
     * its created projectile, while drinkable potions also receive their dedicated
     * edible component.
     */
    public static void initPotion() {
        ItemTypes.POTION = AllayItemType
                .builder(ItemPotionStackImpl.class)
                .vanillaItem(ItemId.POTION)
                .addComponent(ItemPotionComponentImpl::new, ItemPotionComponentImpl.class)
                .addComponent(ItemPotionEdibleComponentImpl::new, ItemPotionEdibleComponentImpl.class)
                .build();
        ItemTypes.SPLASH_POTION = buildThrowablePotion(ItemSplashPotionStackImpl.class, ItemId.SPLASH_POTION, () -> new ItemBottleProjectileComponentImpl(EntityId.SPLASH_POTION, 0.5) {
            @Override
            protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
                var projectile = (EntitySplashPotion) super.createProjectile(shooter, shootPos);
                // Potion identity lives in item metadata, so transfer its decoded
                // kind to the projectile before inherited launch handling proceeds.
                projectile.setPotionType(PotionType.fromId(thisItemStack.getMeta()));
                return projectile;
            }
        });
    }

    /**
     * Registers lingering potions with potion-state and bottle-projectile components.
     * The specialized projectile callback transfers the item's metadata-selected
     * potion kind to the created lingering entity before inherited launch handling
     * continues.
     */
    public static void initLingeringPotion() {
        ItemTypes.LINGERING_POTION = buildThrowablePotion(ItemLingeringPotionStackImpl.class, ItemId.LINGERING_POTION, () -> new ItemBottleProjectileComponentImpl(EntityId.LINGERING_POTION, 0.5) {
            @Override
            protected EntityProjectile createProjectile(Entity shooter, Vector3d shootPos) {
                var projectile = (EntityLingeringPotion) super.createProjectile(shooter, shootPos);
                // Potion identity lives in item metadata, so transfer its decoded
                // kind to the projectile before inherited launch handling proceeds.
                projectile.setPotionType(PotionType.fromId(thisItemStack.getMeta()));
                return projectile;
            }
        });
    }

    /**
     * Builds and registers a potion type with state and supplied bottle-launcher
     * components. The supplier creates the specialized projectile behavior later;
     * the caller must match {@code stackClass} to the inferred result type because
     * the class cast is unchecked.
     */
    @SuppressWarnings("unchecked")
    private static <T extends ItemStack> ItemType<T> buildThrowablePotion(
            Class<? extends ItemStack> stackClass,
            ItemId itemId,
            Supplier<ItemBottleProjectileComponentImpl> projectileComponentSupplier
    ) {
        return AllayItemType
                .builder((Class<T>) stackClass)
                .vanillaItem(itemId)
                .addComponent(ItemPotionComponentImpl::new, ItemPotionComponentImpl.class)
                .addComponent(projectileComponentSupplier, ItemBottleProjectileComponentImpl.class)
                .build();
    }

    /**
     * Registers the bow with its specialized base behavior and string repair
     * capability. The component composition retains vanilla gameplay data and
     * resolves the configured repair material when compatibility is queried.
     */
    public static void initBow() {
        ItemTypes.BOW = AllayItemType
                .builder(ItemBowStackImpl.class)
                .vanillaItem(ItemId.BOW)
                .addComponent(ItemBowBaseComponentImpl::new, ItemBowBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.STRING), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers goat horns with the instrument-aware cooldown and sound base
     * component. Instrument variants remain metadata values of this one item type
     * rather than separate registered types for each horn sound.
     */
    public static void initGoatHorn() {
        ItemTypes.GOAT_HORN = AllayItemType
                .builder(ItemGoatHornStackImpl.class)
                .vanillaItem(ItemId.GOAT_HORN)
                .addComponent(ItemGoatHornBaseComponentImpl::new, ItemGoatHornBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the crossbow with its specialized base behavior and string repair
     * capability. The type retains vanilla gameplay data, while the selected base
     * component owns the stack's loading and firing behavior.
     */
    public static void initCrossbow() {
        ItemTypes.CROSSBOW = AllayItemType
                .builder(ItemCrossbowStackImpl.class)
                .vanillaItem(ItemId.CROSSBOW)
                .addComponent(ItemCrossbowBaseComponentImpl::new, ItemCrossbowBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.STRING), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers the bed item type with its specialized placement base component.
     * The composition retains vanilla item data while choosing the helper path
     * that disables the generic entity-collision precheck for bed placement.
     */
    public static void initBed() {
        ItemTypes.BED = AllayItemType
                .builder(ItemBedStackImpl.class)
                .addComponent(ItemBedBaseComponentImpl::new, ItemBedBaseComponentImpl.class)
                .vanillaItem(ItemId.BED)
                .build();
    }

    /**
     * Registers paintings with the fitting-painting selection and placement base
     * component. The type retains vanilla gameplay data, while its specialized base
     * constructs painting entities rather than relying on ordinary block placement.
     */
    public static void initPainting() {
        ItemTypes.PAINTING = AllayItemType
                .builder(ItemPaintingStackImpl.class)
                .addComponent(ItemPaintingBaseComponentImpl::new, ItemPaintingBaseComponentImpl.class)
                .vanillaItem(ItemId.PAINTING)
                .build();
    }

    /**
     * Registers firework stars with their specialized base data component.
     * The selected implementation supplies stored explosion metadata through the
     * stack's base behavior while the type retains its vanilla item identity.
     */
    public static void initFireworkStar() {
        ItemTypes.FIREWORK_STAR = AllayItemType
                .builder(ItemFireworkStarStackImpl.class)
                .vanillaItem(ItemId.FIREWORK_STAR)
                .addComponent(ItemFireworkStarBaseComponentImpl::new, ItemFireworkStarBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers firework rockets with their specialized base data and use behavior.
     * The builder keeps the vanilla item identity and selects the rocket component
     * instead of adding the generic base provider.
     */
    public static void initFireworkRocket() {
        ItemTypes.FIREWORK_ROCKET = AllayItemType
                .builder(ItemFireworkRocketStackImpl.class)
                .vanillaItem(ItemId.FIREWORK_ROCKET)
                .addComponent(ItemFireworkRocketBaseComponentImpl::new, ItemFireworkRocketBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers decorated-pot items with their sherd-persisting base component.
     * The type retains vanilla data, and later stacks carry four-face decoration
     * values through the specialized base's extra-tag persistence path.
     */
    public static void initDecoratedPot() {
        ItemTypes.DECORATED_POT = AllayItemType
                .builder(ItemDecoratedPotStackImpl.class)
                .vanillaItem(ItemId.DECORATED_POT)
                .addComponent(ItemDecoratedPotBaseComponentImpl::new, ItemDecoratedPotBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers color-specific dye types and the separate black-color {@code DYE}
     * catalog entry. Each item retains its own vanilla identity while the dye
     * component exposes its explicitly configured color without reading stack
     * metadata.
     */
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
        // DYE receives the same black color through a separate registered item
        // type, preserving the catalog entry's own vanilla identifier.
        ItemTypes.DYE = buildDye(ItemId.DYE, DyeColor.BLACK);
    }

    /**
     * Builds and registers a dye type with a fixed configured color.
     * The supplier creates a dye capability for later stacks, while the builder
     * retains the generic item base and the supplied vanilla identity.
     */
    private static ItemType<ItemDyeStack> buildDye(ItemId itemId, DyeColor dyeColor) {
        return AllayItemType
                .builder(ItemDyeStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemDyeComponentImpl(dyeColor), ItemDyeComponentImpl.class)
                .build();
    }

    /**
     * Registers spear catalog entries with their configured repair materials.
     * The current helper adds repair capability only; these definitions do not yet
     * bind a specialized spear attack or tool behavior component.
     */
    public static void initSpear() {
        ItemTypes.COPPER_SPEAR = buildSpear(ItemId.COPPER_SPEAR, ItemId.COPPER_INGOT);
        ItemTypes.IRON_SPEAR = buildSpear(ItemId.IRON_SPEAR, ItemId.IRON_INGOT);
        ItemTypes.DIAMOND_SPEAR = buildSpear(ItemId.DIAMOND_SPEAR, ItemId.DIAMOND);
        ItemTypes.NETHERITE_SPEAR = buildSpear(ItemId.NETHERITE_SPEAR, ItemId.NETHERITE_INGOT);
        ItemTypes.GOLDEN_SPEAR = buildSpear(ItemId.GOLDEN_SPEAR, ItemId.GOLD_INGOT);
        ItemTypes.STONE_SPEAR = buildSpear(ItemId.STONE_SPEAR, ItemId.COBBLESTONE);
        ItemTypes.WOODEN_SPEAR = buildSpear(ItemId.WOODEN_SPEAR, ItemId.PLANKS);
    }

    // TODO: Add the specialized spear attack and tool providers here when their
    // implementations are available; this helper currently binds repair support only.
    /**
     * Builds and registers a spear type with repair capability and generic base
     * state. The supplied material is resolved lazily; specialized spear gameplay
     * is not attached by this current component composition.
     */
    private static ItemType<ItemSpearStack> buildSpear(ItemId itemId, ItemId repairItemId) {
        return AllayItemType
                .builder(ItemSpearStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemRepairableComponentImpl(repairItemId), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers redstone dust with its redstone-wire placement base component.
     * The type retains vanilla identity and gameplay data while the specialized
     * base chooses the wire block independently of generic block mapping.
     */
    public static void initRedstoneWire() {
        ItemTypes.REDSTONE = AllayItemType
                .builder(ItemRedstoneStackImpl.class)
                .vanillaItem(ItemId.REDSTONE)
                .addComponent(ItemRedstoneBaseComponentImpl::new, ItemRedstoneBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers string with its tripwire-placement base component and vanilla data.
     * The selected base supplies the tripwire block state for placement instead
     * of relying only on the item's generic block mapping.
     */
    public static void initString() {
        ItemTypes.STRING = AllayItemType
                .builder(ItemStringStackImpl.class)
                .vanillaItem(ItemId.STRING)
                .addComponent(ItemStringBaseComponentImpl::new, ItemStringBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the repeater item with its unpowered-repeater placement base.
     * The resulting type retains vanilla gameplay data, and the specialized base
     * selects the initial block variant before shared placement handling.
     */
    public static void initRepeater() {
        ItemTypes.REPEATER = AllayItemType
                .builder(ItemRepeaterStackImpl.class)
                .vanillaItem(ItemId.REPEATER)
                .addComponent(ItemRepeaterBaseComponentImpl::new, ItemRepeaterBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the comparator item with its unpowered-comparator placement base.
     * The resulting type retains vanilla gameplay data, and the specialized base
     * selects the initial block variant before shared placement handling.
     */
    public static void initComparator() {
        ItemTypes.COMPARATOR = AllayItemType
                .builder(ItemComparatorStackImpl.class)
                .vanillaItem(ItemId.COMPARATOR)
                .addComponent(ItemComparatorBaseComponentImpl::new, ItemComparatorBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the mace with specialized combat base behavior and sword-tool
     * capability. The composition also binds breeze-rod repair support while keeping
     * the mace's own vanilla gameplay data and identifier.
     */
    public static void initMace() {
        ItemTypes.MACE = AllayItemType
                .builder(ItemMaceStackImpl.class)
                .vanillaItem(ItemId.MACE)
                .addComponent(ItemMaceBaseComponentImpl::new, ItemMaceBaseComponentImpl.class)
                .addComponent(ItemSwordToolComponentImpl::new, ItemSwordToolComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.BREEZE_ROD), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers the shield with its specialized base component and plank repair
     * capability. The type keeps vanilla gameplay data, while the selected base
     * provides shield-specific stack and use behavior.
     */
    public static void initShield() {
        ItemTypes.SHIELD = AllayItemType
                .builder(ItemShieldStackImpl.class)
                .vanillaItem(ItemId.SHIELD)
                .addComponent(ItemShieldBaseComponentImpl::new, ItemShieldBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.PLANKS), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers the trident item with its specialized weapon base component.
     * The builder retains vanilla gameplay data and selects trident behavior in
     * place of the generic base provider for later stack construction.
     */
    public static void initTrident() {
        ItemTypes.TRIDENT = AllayItemType
                .builder(ItemTridentStackImpl.class)
                .vanillaItem(ItemId.TRIDENT)
                .addComponent(ItemTridentBaseComponentImpl::new, ItemTridentBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers fishing rods with their specialized base behavior and string repair
     * capability. The composition retains vanilla gameplay data while the rod base
     * manages the stack's fishing interaction behavior.
     */
    public static void initFishingRod() {
        ItemTypes.FISHING_ROD = AllayItemType
                .builder(ItemFishingRodStackImpl.class)
                .vanillaItem(ItemId.FISHING_ROD)
                .addComponent(ItemFishingRodBaseComponentImpl::new, ItemFishingRodBaseComponentImpl.class)
                .addComponent(() -> new ItemRepairableComponentImpl(ItemId.STRING), ItemRepairableComponentImpl.class)
                .build();
    }

    /**
     * Registers the armor-stand item with its specialized entity-placement base.
     * The completed type retains vanilla gameplay data, while the component creates
     * the stand through the item's interaction path rather than generic block
     * placement.
     */
    public static void initArmorStand() {
        ItemTypes.ARMOR_STAND = AllayItemType
                .builder(ItemArmorStandStackImpl.class)
                .vanillaItem(ItemId.ARMOR_STAND)
                .addComponent(ItemArmorStandBaseComponentImpl::new, ItemArmorStandBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers the inventory nether-wart item with its planting base component.
     * The specialized composition belongs to {@code ItemTypes.ITEM_NETHER_WART},
     * retaining that catalog identity while supplying the corresponding planting
     * behavior.
     */
    public static void initNetherWart() {
        ItemTypes.ITEM_NETHER_WART = AllayItemType
                .builder(ItemItemNetherWartStackImpl.class)
                .vanillaItem(ItemId.ITEM_NETHER_WART)
                .addComponent(ItemNetherWartBaseComponentImpl::new, ItemNetherWartBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers cocoa beans with their specialized planting base component.
     * The type retains vanilla item data and selects cocoa planting behavior rather
     * than relying solely on the generic mapped-block placement path.
     */
    public static void initCocoaBeans() {
        ItemTypes.COCOA_BEANS = AllayItemType
                .builder(ItemCocoaBeansStackImpl.class)
                .vanillaItem(ItemId.COCOA_BEANS)
                .addComponent(ItemCocoaBeansBaseComponentImpl::new, ItemCocoaBeansBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers kelp with its specialized planting base component and vanilla data.
     * The selected component supplies the item's placement rules while the builder
     * retains the kelp catalog identity for later stack creation.
     */
    public static void initKelp() {
        ItemTypes.KELP = AllayItemType
                .builder(ItemKelpStackImpl.class)
                .vanillaItem(ItemId.KELP)
                .addComponent(ItemKelpBaseComponentImpl::new, ItemKelpBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers waterlilies with their specialized placement base component.
     * The item keeps vanilla identity and gameplay data while the selected base
     * supplies its placement behavior instead of the generic base provider.
     */
    public static void initWaterlily() {
        ItemTypes.WATERLILY = AllayItemType
                .builder(ItemWaterlilyStackImpl.class)
                .vanillaItem(ItemId.WATERLILY)
                .addComponent(ItemWaterlilyBaseComponentImpl::new, ItemWaterlilyBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers carved pumpkins with helmet-slot wearable capability and generic
     * base state. The wearable constructor enables interaction-based equipment by
     * default, so this composition can transfer the item into a player's helmet
     * slot.
     */
    public static void initCarvedPumpkin() {
        ItemTypes.CARVED_PUMPKIN = AllayItemType
                .builder(ItemCarvedPumpkinStackImpl.class)
                .vanillaItem(ItemId.CARVED_PUMPKIN)
                .addComponent(() -> new ItemWearableComponentImpl(ArmorType.HELMET), ItemWearableComponentImpl.class)
                .build();
    }

    /**
     * Registers head and skull catalog entries with helmet-slot wearable metadata.
     * The helper disables automatic equipment transfer during interactions, while
     * each entry retains its separate vanilla item identity and gameplay data.
     */
    public static void initHeads() {
        ItemTypes.CREEPER_HEAD = buildHead(ItemId.CREEPER_HEAD);
        ItemTypes.DRAGON_HEAD = buildHead(ItemId.DRAGON_HEAD);
        ItemTypes.PIGLIN_HEAD = buildHead(ItemId.PIGLIN_HEAD);
        ItemTypes.PLAYER_HEAD = buildHead(ItemId.PLAYER_HEAD);
        ItemTypes.SKELETON_SKULL = buildHead(ItemId.SKELETON_SKULL);
        ItemTypes.SKULL = buildHead(ItemId.SKULL);
        ItemTypes.WITHER_SKELETON_SKULL = buildHead(ItemId.WITHER_SKELETON_SKULL);
        ItemTypes.ZOMBIE_HEAD = buildHead(ItemId.ZOMBIE_HEAD);
    }

    /**
     * Builds and registers a head item with helmet-slot wearable capability.
     * The wearable flag is {@code false}, which disables its inventory-transfer
     * helper while keeping the armor type available to capability consumers.
     */
    private static ItemType<ItemHeadStack> buildHead(ItemId itemId) {
        return AllayItemType
                .builder(ItemHeadStackImpl.class)
                .vanillaItem(itemId)
                .addComponent(() -> new ItemWearableComponentImpl(ArmorType.HELMET, false), ItemWearableComponentImpl.class)
                .build();
    }
}
