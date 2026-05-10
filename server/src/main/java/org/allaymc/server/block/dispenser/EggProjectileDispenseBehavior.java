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
 * Dispense behavior for climate-variant eggs (blue egg, brown egg).
 *
 * @author daoge_cmd
 */
public class EggProjectileDispenseBehavior extends ProjectileDispenseBehavior {

    protected final ClimateVariant climateVariant;

    public EggProjectileDispenseBehavior(ClimateVariant climateVariant) {
        super(EntityTypes.EGG, 1.0);
        this.climateVariant = climateVariant;
    }

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
