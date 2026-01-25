package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemFireworkRocketBaseComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dispense behavior for firework rocket items.
 * Spawns a firework rocket entity in front of the dispenser.
 *
 * @author daoge_cmd
 */
public class FireworksDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos)
                        .rot(ThreadLocalRandom.current().nextDouble() * 360, 90)
                        .build()
        );

        // Transfer firework data from item to entity
        if (item instanceof ItemFireworkRocketBaseComponent fireworkItem) {
            firework.setExistenceTicks(fireworkItem.getRandomizedDuration());
            firework.setExplosions(fireworkItem.getExplosions());
        }

        dimension.getEntityManager().addEntity(firework);
        spawnDispenseEffects(block, face, spawnPos);
        return DispenseResult.success();
    }

    protected Vector3d getSpawnPosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x();
        pos.y += face.getOffset().y();
        pos.z += face.getOffset().z();
        return pos;
    }

    protected void spawnDispenseEffects(Block block, BlockFace face, Vector3d spawnPos) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(spawnPos, SimpleSound.FIREWORK_LAUNCH);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
