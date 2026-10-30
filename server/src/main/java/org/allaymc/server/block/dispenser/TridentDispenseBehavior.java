package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemTridentStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code TridentDispenseBehavior} launches trident projectiles carrying a copied
 * source trident item. The projectile reads enchantment and item properties from
 * that retained item, so the copy is assigned before the entity is submitted for
 * admission.
 *
 * <p>
 * The input must be a trident stack because the copied item is cast to
 * {@code ItemTridentStack}. The launched copy has count one, and no favored
 * inventory slot is assigned by this handler. Motion normalizes the biased face
 * direction before base-speed scaling and uses half the ordinary projectile spread
 * coefficient; a {@code null} factory result produces a failed dispense result
 * before item assignment or effects.
 *
 * @author daoge_cmd
 */
public class TridentDispenseBehavior implements DispenseBehavior {

    /**
     * {@code SPEED_MULTIPLIER} scales the normalized launch direction before
     * random spread. The final vector can have another magnitude after its
     * Gaussian offsets.
     */
    protected static final double SPEED_MULTIPLIER = 1.25;

    /**
     * Creates a thrown trident carrying a one-count copy of the supplied trident
     * item. The input must copy to {@code ItemTridentStack}; the projectile stores
     * that copy before entity submission so later enchantment processing observes
     * its item properties. A {@code null} factory result returns failure, while
     * successful submission emits effects and returns before deferred entity
     * admission completes. The handler leaves {@code favoredSlot} at the entity's
     * default {@code -1}.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        var motion = calculateMotion(face);
        var entity = EntityTypes.THROWN_TRIDENT.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos.x, spawnPos.y, spawnPos.z)
                        .motion(motion)
                        .build()
        );

        if (entity == null) {
            return DispenseResult.fail();
        }

        // Attach the source item before entity submission because the projectile
        // reads enchantments from that retained item instead of separate launch
        // metadata.
        var tridentItem = (ItemTridentStack) item.copy();
        tridentItem.setCount(1);
        entity.setTridentItem(tridentItem);

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }

    /**
     * Emits the trident-throw sound and directional dispenser shoot particle.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.TRIDENT_THROW);
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
     * Returns a new trident launch vector with upward bias and random spread. The
     * base direction uses {@code SPEED_MULTIPLIER}, then each coordinate receives
     * half the ordinary projectile spread coefficient.
     */
    protected Vector3d calculateMotion(BlockFace face) {
        var random = ThreadLocalRandom.current();
        var motion = new Vector3d();

        motion.x = face.getOffset().x();
        // Apply upward bias before normalization so the resulting direction
        // includes the lift.
        motion.y = face.getOffset().y() + 0.1;
        motion.z = face.getOffset().z();

        motion.normalize().mul(SPEED_MULTIPLIER);

        // Halve the ordinary projectile spread coefficient for trident launches.
        double accuracy = 6 * 0.5;
        motion.x += random.nextGaussian() * 0.0075 * accuracy;
        motion.y += random.nextGaussian() * 0.0075 * accuracy;
        motion.z += random.nextGaussian() * 0.0075 * accuracy;

        return motion;
    }
}
