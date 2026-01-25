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
 * Dispense behavior for spawn egg items.
 * Spawns the corresponding entity in front of the dispenser.
 *
 * @author daoge_cmd
 */
public class SpawnEggDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        // Check if item is a spawn egg
        if (!(item instanceof ItemSpawnEggBaseComponent spawnEggComponent)) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        var entityType = spawnEggComponent.getEntityType();
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

    protected Vector3d getSpawnPosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x();
        pos.y += face.getOffset().y();
        pos.z += face.getOffset().z();
        return pos;
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
