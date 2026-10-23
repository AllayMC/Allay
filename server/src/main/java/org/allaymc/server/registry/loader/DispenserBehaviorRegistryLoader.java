package org.allaymc.server.registry.loader;

import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.entity.property.enums.ClimateVariant;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.server.block.dispenser.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code DispenserBehaviorRegistryLoader} maps built-in items to specialized dispenser
 * behaviors. The loader shares one behavior across bucket variants and another across all
 * registered item identifiers ending in {@code "_spawn_egg"}, so item registration must
 * precede discovery of those egg entries.
 *
 * @author daoge_cmd
 */
public class DispenserBehaviorRegistryLoader implements RegistryLoader<Void, Map<ItemType<?>, DispenseBehavior>> {

    /**
     * Creates a mutable mapping of specialized dispenser behaviors. Item and entity types must be
     * initialized first, and spawn-egg entries are discovered from the current item registry
     * rather
     * than a fixed list of constants.
     */
    @Override
    public Map<ItemType<?>, DispenseBehavior> load(Void $) {
        var map = new HashMap<ItemType<?>, DispenseBehavior>();

        // Projectile behaviors
        map.put(ItemTypes.ARROW, new ProjectileDispenseBehavior(EntityTypes.ARROW, 1.5));
        map.put(ItemTypes.SNOWBALL, new ProjectileDispenseBehavior(EntityTypes.SNOWBALL, 1.0));
        map.put(ItemTypes.EGG, new EggProjectileDispenseBehavior(ClimateVariant.TEMPERATE));
        map.put(ItemTypes.BLUE_EGG, new EggProjectileDispenseBehavior(ClimateVariant.COLD));
        map.put(ItemTypes.BROWN_EGG, new EggProjectileDispenseBehavior(ClimateVariant.WARM));
        map.put(ItemTypes.SPLASH_POTION, new PotionDispenseBehavior(EntityTypes.SPLASH_POTION, 1.25));
        map.put(ItemTypes.EXPERIENCE_BOTTLE, new ProjectileDispenseBehavior(EntityTypes.XP_BOTTLE, 1.25));
        map.put(ItemTypes.TRIDENT, new TridentDispenseBehavior());
        map.put(ItemTypes.LINGERING_POTION, new PotionDispenseBehavior(EntityTypes.LINGERING_POTION, 1.25));

        // Fire charge - launches small fireball with no random spread
        map.put(ItemTypes.FIRE_CHARGE, new FireChargeDispenseBehavior());

        // TNT
        map.put(ItemTypes.TNT, new TNTDispenseBehavior());

        // Fireworks
        map.put(ItemTypes.FIREWORK_ROCKET, new FireworksDispenseBehavior());

        // Flint and steel
        // TODO: Add Nether portal creation to FlintAndSteelDispenseBehavior when dispensing ignition beside a valid frame.
        map.put(ItemTypes.FLINT_AND_STEEL, new FlintAndSteelDispenseBehavior());

        // Glass bottle
        map.put(ItemTypes.GLASS_BOTTLE, new GlassBottleDispenseBehavior());

        // Water bottle (potion) - handles conversion of dirt/coarse dirt to mud
        map.put(ItemTypes.POTION, new WaterBottleDispenseBehavior());

        // Buckets
        // TODO: Add entity milking to BucketDispenseBehavior before registering milk-bucket dispensing here.
        var bucketBehavior = new BucketDispenseBehavior();
        map.put(ItemTypes.BUCKET, bucketBehavior);
        map.put(ItemTypes.WATER_BUCKET, bucketBehavior);
        map.put(ItemTypes.LAVA_BUCKET, bucketBehavior);
        map.put(ItemTypes.POWDER_SNOW_BUCKET, bucketBehavior);
        map.put(ItemTypes.COD_BUCKET, bucketBehavior);
        map.put(ItemTypes.SALMON_BUCKET, bucketBehavior);
        map.put(ItemTypes.PUFFERFISH_BUCKET, bucketBehavior);
        map.put(ItemTypes.TROPICAL_FISH_BUCKET, bucketBehavior);
        map.put(ItemTypes.AXOLOTL_BUCKET, bucketBehavior);
        map.put(ItemTypes.TADPOLE_BUCKET, bucketBehavior);

        // Spawn eggs - register all spawn egg item types
        var spawnEggBehavior = new SpawnEggDispenseBehavior();
        for (var itemType : Registries.ITEMS.getContent().values()) {
            // Identify spawn eggs by their identifier suffix
            if (itemType.getIdentifier().path().endsWith("_spawn_egg")) {
                map.put(itemType, spawnEggBehavior);
            }
        }

        // TODO: Implement BoatDispenseBehavior and register it here. Spawn boat entities on water for each boat variant.
        // TODO: Implement ChestBoatDispenseBehavior and register it here. Spawn chest boat entities on water for each chest boat variant.
        // TODO: Implement DyeDispenseBehavior and register it here. Apply bone meal to fertilizable blocks in front of the dispenser.
        // TODO: Implement MinecartDispenseBehavior and register it here. Spawn minecart variants when the facing block supports rail placement.
        // TODO: Implement ShearsDispenseBehavior and register it here. Shear eligible entities in front of the dispenser.
        // TODO: Implement ShulkerBoxDispenseBehavior and register it here. Place shulker boxes in front of the dispenser.

        return map;
    }
}
