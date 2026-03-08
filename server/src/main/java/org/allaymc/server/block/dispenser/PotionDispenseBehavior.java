package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemPotionComponent;

/**
 * Dispense behavior for potion items (splash potion, lingering potion).
 * Transfers the potion type from the item to the projectile entity.
 *
 * @author daoge_cmd
 */
public class PotionDispenseBehavior extends ProjectileDispenseBehavior {

    public PotionDispenseBehavior(EntityType<?> entityType, double speedMultiplier) {
        super(entityType, speedMultiplier);
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

        // Transfer potion type from item to entity
        if (item instanceof ItemPotionComponent potionItem && entity instanceof EntityPotionComponent potionEntity) {
            potionEntity.setPotionType(potionItem.getPotionType());
        }

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }
}
