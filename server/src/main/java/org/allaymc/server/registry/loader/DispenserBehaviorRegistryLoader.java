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
        map.put(ItemTypes.FLINT_AND_STEEL, new FlintAndSteelDispenseBehavior());

        // Glass bottle
        map.put(ItemTypes.GLASS_BOTTLE, new GlassBottleDispenseBehavior());

        // Water bottle (potion) - handles conversion of dirt/coarse dirt to mud
        map.put(ItemTypes.POTION, new WaterBottleDispenseBehavior());

        // Buckets
        var bucketBehavior = new BucketDispenseBehavior();
        map.put(ItemTypes.BUCKET, bucketBehavior);
        map.put(ItemTypes.MILK_BUCKET, bucketBehavior);
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

        var boatBehavior = new BoatDispenseBehavior();
        var chestBoatBehavior = new ChestBoatDispenseBehavior();
        map.put(ItemTypes.BOAT, boatBehavior);
        map.put(ItemTypes.ACACIA_BOAT, boatBehavior);
        map.put(ItemTypes.BIRCH_BOAT, boatBehavior);
        map.put(ItemTypes.CHERRY_BOAT, boatBehavior);
        map.put(ItemTypes.DARK_OAK_BOAT, boatBehavior);
        map.put(ItemTypes.JUNGLE_BOAT, boatBehavior);
        map.put(ItemTypes.MANGROVE_BOAT, boatBehavior);
        map.put(ItemTypes.OAK_BOAT, boatBehavior);
        map.put(ItemTypes.PALE_OAK_BOAT, boatBehavior);
        map.put(ItemTypes.SPRUCE_BOAT, boatBehavior);
        map.put(ItemTypes.CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.ACACIA_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.BIRCH_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.CHERRY_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.DARK_OAK_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.JUNGLE_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.MANGROVE_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.OAK_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.PALE_OAK_CHEST_BOAT, chestBoatBehavior);
        map.put(ItemTypes.SPRUCE_CHEST_BOAT, chestBoatBehavior);

        var dyeBehavior = new DyeDispenseBehavior();
        map.put(ItemTypes.BONE_MEAL, dyeBehavior);
        map.put(ItemTypes.RAPID_FERTILIZER, dyeBehavior);

        var minecartBehavior = new MinecartDispenseBehavior();
        map.put(ItemTypes.MINECART, minecartBehavior);
        map.put(ItemTypes.CHEST_MINECART, minecartBehavior);
        map.put(ItemTypes.HOPPER_MINECART, minecartBehavior);
        map.put(ItemTypes.TNT_MINECART, minecartBehavior);
        map.put(ItemTypes.COMMAND_BLOCK_MINECART, minecartBehavior);
        map.put(ItemTypes.SHEARS, new ShearsDispenseBehavior());

        var shulkerBoxBehavior = new ShulkerBoxDispenseBehavior();
        map.put(ItemTypes.UNDYED_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.WHITE_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.ORANGE_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.MAGENTA_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.LIGHT_BLUE_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.YELLOW_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.LIME_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.PINK_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.GRAY_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.LIGHT_GRAY_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.CYAN_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.PURPLE_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.BLUE_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.BROWN_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.GREEN_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.RED_SHULKER_BOX, shulkerBoxBehavior);
        map.put(ItemTypes.BLACK_SHULKER_BOX, shulkerBoxBehavior);

        return map;
    }
}
