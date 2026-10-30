package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemSpawnEggBaseComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;

/**
 * {@code SpawnEggDispenseBehavior} creates the entity selected by a spawn egg at
 * the adjacent output position. An input without spawn-egg capabilities or without
 * a mapped entity type falls back to ordinary ejection, while a created entity is
 * submitted before success is returned.
 *
 * @author daoge_cmd
 */
public class SpawnEggDispenseBehavior implements DispenseBehavior {

    /**
     * Creates and submits the spawn egg's configured entity at the output
     * position. Unsupported input or a missing entity mapping uses ordinary
     * ejection; a {@code null} factory result instead returns failure without
     * submission or effects.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        // An incompatible input has no spawn-egg mapping to apply, so default
        // ejection preserves an output action instead of consuming the item
        // silently.
        if (!(item instanceof ItemSpawnEggBaseComponent spawnEggComponent)) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        var entityType = spawnEggComponent.getEntityType();
        // An unmapped egg cannot choose an entity factory. Eject the egg through
        // the fallback so the item remains recoverable after source consumption.
        if (entityType == null) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        var entity = entityType.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos.x, spawnPos.y, spawnPos.z)
                        .build()
        );

        if (entity == null) {
            return DispenseResult.fail();
        }

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);
        return DispenseResult.success();
    }

    /**
     * Returns a new centered position one block along the output face.
     */
    protected Vector3d getSpawnPosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x();
        pos.y += face.getOffset().y();
        pos.z += face.getOffset().z();
        return pos;
    }

    /**
     * Emits the dispenser click sound and directional shoot particle.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
