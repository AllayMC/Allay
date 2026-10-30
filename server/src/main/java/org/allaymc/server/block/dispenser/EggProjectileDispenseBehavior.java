package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.property.enums.ClimateVariant;
import org.allaymc.api.entity.property.type.EntityPropertyTypes;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;

/**
 * {@code EggProjectileDispenseBehavior} launches egg projectiles with a configured
 * climate variant. The inherited projectile helpers select the spawn position and
 * unit-speed launch motion, while this specialization assigns the variant before
 * requesting entity addition and publishing the dispense effects.
 *
 * @author daoge_cmd
 */
public class EggProjectileDispenseBehavior extends ProjectileDispenseBehavior {

    /**
     * Climate variant assigned to each launched egg.
     */
    protected final ClimateVariant climateVariant;

    /**
     * Creates an egg projectile handler retaining the supplied climate variant.
     * Launches use the egg entity type and a base speed multiplier of one before
     * random projectile spread.
     */
    public EggProjectileDispenseBehavior(ClimateVariant climateVariant) {
        super(EntityTypes.EGG, 1.0);
        this.climateVariant = climateVariant;
    }

    /**
     * Creates an egg projectile, sets its climate variant, and requests entity
     * addition. A {@code null} factory result returns failure; success follows
     * submission and effects without waiting for deferred admission.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);
        var motion = calculateMotion(face);

        var entity = entityType.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos.x, spawnPos.y, spawnPos.z)
                        .motion(motion)
                        .build()
        );

        if (entity == null) {
            return DispenseResult.fail();
        }

        entity.setPropertyValue(EntityPropertyTypes.CLIMATE_VARIANT, climateVariant);
        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }
}
