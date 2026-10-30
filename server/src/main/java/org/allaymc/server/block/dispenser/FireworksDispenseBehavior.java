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
 * {@code FireworksDispenseBehavior} launches firework entities one block beyond the dispenser
 * center. Item-provided duration and explosions are transferred when the item exposes the
 * firework component, and the behavior adds the entity before emitting launch sound and a
 * directional particle.
 *
 * @author daoge_cmd
 */
public class FireworksDispenseBehavior implements DispenseBehavior {

    /**
     * Creates a rocket with randomized yaw and transfers duration and explosion data from a
     * firework-capable item. The rocket retains the item's returned explosion set, so both
     * components share that storage. The entity is added before effects, and success tells the
     * caller to consume one selected item. This path assumes entity creation supplies a usable
     * rocket.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        // Randomize the initial yaw across a full turn while retaining the fixed
        // pitch supplied to rocket initialization; this orientation does not itself set motion.
        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos)
                        .rot(ThreadLocalRandom.current().nextDouble() * 360, 90)
                        .build()
        );

        if (item instanceof ItemFireworkRocketBaseComponent fireworkItem) {
            firework.setExistenceTicks(fireworkItem.getRandomizedDuration());
            firework.setExplosions(fireworkItem.getExplosions());
        }

        dimension.getEntityManager().addEntity(firework);
        spawnDispenseEffects(block, face, spawnPos);
        return DispenseResult.success();
    }

    /**
     * Returns a new mutable position one block beyond the dispenser center.
     */
    protected Vector3d getSpawnPosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x();
        pos.y += face.getOffset().y();
        pos.z += face.getOffset().z();
        return pos;
    }

    /**
     * Emits launch sound at {@code spawnPos} and the shoot particle at the dispenser center.
     * The caller supplies the rocket's spawn position so the sound follows the spawned entity
     * rather than the block center.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face, Vector3d spawnPos) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(spawnPos, SimpleSound.FIREWORK_LAUNCH);
        dimension.addParticle(center, new ShootParticle(face));
    }
}
