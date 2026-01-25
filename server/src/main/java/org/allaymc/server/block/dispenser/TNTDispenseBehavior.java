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
import org.allaymc.server.entity.component.EntityTntBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dispense behavior for TNT items.
 * Spawns a primed TNT entity in front of the dispenser.
 *
 * @author daoge_cmd
 */
public class TNTDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

        var random = ThreadLocalRandom.current();
        var angle = random.nextFloat() * Math.PI * 2;
        var motion = new Vector3d(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);

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
