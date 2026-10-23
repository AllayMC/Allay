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
import org.allaymc.server.entity.component.item.EntityTntBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code TNTDispenseBehavior} creates primed TNT at the adjacent dispenser output
 * with a short random horizontal kick and upward motion. The entity receives an
 * eighty-tick fuse through initialization NBT, and the behavior reports success
 * after requesting entity addition and emitting dispenser effects.
 *
 * @author daoge_cmd
 */
public class TNTDispenseBehavior implements DispenseBehavior {

    /**
     * Creates primed TNT with an eighty-tick fuse and requests its addition to the
     * dimension. The behavior emits effects and returns success after submission
     * without waiting for deferred admission.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        var random = ThreadLocalRandom.current();
        // Convert the unit random sample into a full horizontal turn.
        var angle = random.nextFloat() * Math.PI * 2;
        // Use a small horizontal kick with a larger upward launch component.
        var motion = new Vector3d(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);

        // Provide the eighty-tick signed-short fuse in initialization NBT so the
        // new TNT begins its normal countdown when entity ticking starts.
        var entity = EntityTypes.TNT.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos.x, spawnPos.y, spawnPos.z)
                        .motion(motion)
                        .nbt(NbtMap.builder().putShort(EntityTntBaseComponentImpl.TAG_FUSE, (short) 80).build())
                        .build()
        );

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
