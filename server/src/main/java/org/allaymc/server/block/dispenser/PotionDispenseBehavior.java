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
 * {@code PotionDispenseBehavior} launches potion projectiles and transfers the
 * item's potion identity when both item and entity expose compatible potion
 * components. The configured entity type determines the projectile form, while
 * inherited helpers supply its spawn position and launch motion.
 *
 * <p>
 * A freshly constructed projectile initially has its own component defaults.
 * Assigning the source potion type before entity submission associates the
 * dispensed item with the projectile's later potion effect processing. Missing
 * potion capabilities skip the transfer while still submitting the entity and
 * returning success; a {@code null} factory result returns failure before effects
 * or submission.
 *
 * @author daoge_cmd
 */
public class PotionDispenseBehavior extends ProjectileDispenseBehavior {

    /**
     * Creates a potion launch handler with the supplied entity factory and base
     * speed multiplier. The superclass retains both values; random spread is added
     * after the base direction is normalized and scaled.
     */
    public PotionDispenseBehavior(EntityType<?> entityType, double speedMultiplier) {
        super(entityType, speedMultiplier);
    }

    /**
     * Creates a projectile and transfers the held potion identity before
     * requesting entity addition. A {@code null} entity returns failure before
     * effects. Compatible item and entity potion components copy the potion type;
     * otherwise the entity's default remains. Submission and effects then produce
     * success without waiting for deferred admission, so the success flag reports
     * this launch request rather than eventual entity membership. The caller
     * consumes the selected source item after this return.
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

        // Transfer the source potion identity before publishing the projectile to
        // the entity manager so later effect processing reads the dispensed potion
        // rather than its default.
        if (item instanceof ItemPotionComponent potionItem && entity instanceof EntityPotionComponent potionEntity) {
            potionEntity.setPotionType(potionItem.getPotionType());
        }

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }
}
