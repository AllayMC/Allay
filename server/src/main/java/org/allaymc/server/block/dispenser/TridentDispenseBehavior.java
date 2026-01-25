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
 * Dispense behavior for tridents. Tridents preserve their enchantments when dispensed.
 *
 * @author daoge_cmd
 */
public class TridentDispenseBehavior implements DispenseBehavior {

    protected static final double SPEED_MULTIPLIER = 1.25;

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

        // Store the trident item (enchantments are read from item directly)
        var tridentItem = (ItemTridentStack) item.copy();
        tridentItem.setCount(1);
        entity.setTridentItem(tridentItem);
        // Dispensed tridents have no favored slot (default -1)

        dimension.getEntityManager().addEntity(entity);
        spawnDispenseEffects(block, face);

        return DispenseResult.success();
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        // Trident uses its own throw sound
        dimension.addSound(center, SimpleSound.TRIDENT_THROW);
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

        motion.normalize().mul(SPEED_MULTIPLIER);

        // Add random spread (50% accuracy compared to default)
        double accuracy = 6 * 0.5;
        motion.x += random.nextGaussian() * 0.0075 * accuracy;
        motion.y += random.nextGaussian() * 0.0075 * accuracy;
        motion.z += random.nextGaussian() * 0.0075 * accuracy;

        return motion;
    }
}
