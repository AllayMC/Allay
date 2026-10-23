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
 * {@code ProjectileDispenseBehavior} creates configured projectile entities one
 * block in front of a dispenser. Launch motion combines the output face, a small
 * upward bias, the retained base speed multiplier, and independent random spread
 * before entity addition is requested. {@code entityType} and
 * {@code speedMultiplier} supply the factory and base motion settings for every
 * dispense request; random spread varies each launch while these retained settings
 * remain fixed.
 *
 * @author daoge_cmd
 */
public class ProjectileDispenseBehavior implements DispenseBehavior {

    /**
     * Entity type created for each launch.
     */
    protected final EntityType<?> entityType;
    /**
     * Base normalized-direction launch speed before random spread.
     */
    protected final double speedMultiplier;

    /**
     * Creates a projectile handler retaining the entity factory and base speed
     * multiplier. The multiplier scales the normalized direction before random
     * spread; this constructor imposes no positive-speed validation.
     */
    public ProjectileDispenseBehavior(EntityType<?> entityType, double speedMultiplier) {
        this.entityType = entityType;
        this.speedMultiplier = speedMultiplier;
    }

    /**
     * Creates a projectile with the calculated spawn point and motion, then
     * requests entity addition. A {@code null} factory result returns failure;
     * successful submission emits effects and returns before deferred admission
     * completes.
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

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }

    /**
     * Emits the bow-shoot sound and directional dispenser shoot particle.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BOW_SHOOT);
        dimension.addParticle(center, new ShootParticle(face));
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
     * Returns a new launch vector with upward bias and random spread. The face
     * direction is normalized and scaled first, then independent Gaussian offsets
     * change its final direction and magnitude.
     */
    protected Vector3d calculateMotion(BlockFace face) {
        var random = ThreadLocalRandom.current();
        var motion = new Vector3d();

        motion.x = face.getOffset().x();
        // Apply the upward bias before normalization so it affects direction as
        // well as vertical speed.
        motion.y = face.getOffset().y() + 0.1;
        motion.z = face.getOffset().z();

        motion.normalize().mul(speedMultiplier);

        // Add independent spread after base-speed scaling; the resulting
        // projectile speed can differ from speedMultiplier because this vector is
        // not normalized again.
        motion.x += random.nextGaussian() * 0.0075 * 6;
        motion.y += random.nextGaussian() * 0.0075 * 6;
        motion.z += random.nextGaussian() * 0.0075 * 6;

        return motion;
    }
}
