package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;

/**
 * Dispense behavior for fire charges. Fire charges are launched as small fireballs
 * with no random spread (perfect accuracy) and a blaze shoot sound.
 *
 * @author daoge_cmd
 */
public class FireChargeDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        // Fire charges have no random spread - they fly in a straight line
        var motion = calculateMotion(face);

        var entity = EntityTypes.SMALL_FIREBALL.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos.x, spawnPos.y, spawnPos.z)
                        .motion(motion)
                        .build()
        );

        if (entity == null) {
            return DispenseResult.fail();
        }

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        // Fire charge uses fire charge sound
        dimension.addSound(center, SimpleSound.FIRE_CHARGE);
        dimension.addParticle(center, new ShootParticle(face));
    }

    protected Vector3d getSpawnPosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x();
        pos.y += face.getOffset().y();
        pos.z += face.getOffset().z();
        return pos;
    }

    protected Vector3d calculateMotion(BlockFace face) {
        // Fire charges have zero accuracy (no random spread)
        // They fly in a straight line in the direction the dispenser is facing
        var motion = new Vector3d();
        motion.x = face.getOffset().x();
        motion.y = face.getOffset().y();
        motion.z = face.getOffset().z();
        // Normalize to get unit direction vector
        motion.normalize();
        return motion;
    }
}
