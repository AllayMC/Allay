package org.allaymc.server.registry.loader;

import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.server.block.dispenser.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry loader for dispenser behaviors.
 * <p>
 * This class loads all built-in dispenser behaviors for different item types.
 *
 * @author daoge_cmd
 */
public class DispenserBehaviorRegistryLoader implements RegistryLoader<Void, Map<ItemType<?>, DispenseBehavior>> {

    @Override
    public Map<ItemType<?>, DispenseBehavior> load(Void $) {
        var map = new HashMap<ItemType<?>, DispenseBehavior>();

        // Projectile behaviors
        map.put(ItemTypes.ARROW, new ProjectileDispenseBehavior(EntityTypes.ARROW, 1.5));
        map.put(ItemTypes.SNOWBALL, new ProjectileDispenseBehavior(EntityTypes.SNOWBALL, 1.0));
        map.put(ItemTypes.EGG, new ProjectileDispenseBehavior(EntityTypes.EGG, 1.0));
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
        // TODO: FlintAndSteelDispenseBehavior - Nether portal creation not yet implemented
        map.put(ItemTypes.FLINT_AND_STEEL, new FlintAndSteelDispenseBehavior());

        // Glass bottle
        map.put(ItemTypes.GLASS_BOTTLE, new GlassBottleDispenseBehavior());

        // Water bottle (potion) - handles conversion of dirt/coarse dirt to mud
        map.put(ItemTypes.POTION, new WaterBottleDispenseBehavior());

        // Buckets
        // TODO: BucketDispenseBehavior - Milk bucket behavior (entity milking) not implemented
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

        // TODO: BoatDispenseBehavior - Spawns boat entity on water or above water (all boat variants)
        // TODO: ChestBoatDispenseBehavior - Spawns chest boat entity on water or above water (all chest boat variants)
        // TODO: DyeDispenseBehavior - Bone meal/fertilizer functionality on fertilizable blocks
        // TODO: MinecartDispenseBehavior - Spawns minecart entities on rail blocks (all minecart variants)
        // TODO: ShearsDispenseBehavior - Shears shearable entities (sheep, snow golem, mooshroom, etc.)
        // TODO: ShulkerBoxDispenseBehavior - Places shulker boxes in front of dispenser

        return map;
    }
}
