package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dispense behavior for projectile items like arrows, snowballs, eggs, etc.
 *
 * @author daoge_cmd
 */
public class ProjectileDispenseBehavior implements DispenseBehavior {

    protected final EntityType<?> entityType;
    protected final double speedMultiplier;

    public ProjectileDispenseBehavior(EntityType<?> entityType, double speedMultiplier) {
        this.entityType = entityType;
        this.speedMultiplier = speedMultiplier;
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

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BOW_SHOOT);
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
        var random = ThreadLocalRandom.current();
        var motion = new Vector3d();

        motion.x = face.getOffset().x();
        motion.y = face.getOffset().y() + 0.1;
        motion.z = face.getOffset().z();

        motion.normalize().mul(speedMultiplier);

        motion.x += random.nextGaussian() * 0.0075 * 6;
        motion.y += random.nextGaussian() * 0.0075 * 6;
        motion.z += random.nextGaussian() * 0.0075 * 6;

        return motion;
    }
}
