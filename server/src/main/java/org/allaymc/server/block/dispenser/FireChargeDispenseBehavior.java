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
 * {@code FireChargeDispenseBehavior} launches small fireballs from a dispenser with a
 * deterministic unit direction. The spawn position is one block beyond the dispenser center,
 * and successful entity creation is followed by entity-manager insertion, fire-charge sound,
 * and a directional shoot particle.
 *
 * @author daoge_cmd
 */
public class FireChargeDispenseBehavior implements DispenseBehavior {

    /**
     * Creates a small fireball with unit motion in the dispenser's facing direction. A {@code
     * null} creation result reports failure before insertion or effects; otherwise the entity
     * is added and success tells the dispenser caller to consume its selected item.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var spawnPos = getSpawnPosition(block, face);

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

    /**
     * Emits the fire-charge sound and shoot particle at the dispenser center.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.FIRE_CHARGE);
        dimension.addParticle(center, new ShootParticle(face));
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
     * Returns a new unit vector in the dispenser face's axis direction. No random spread is
     * added by this helper; later fireball movement remains the responsibility of its entity
     * components.
     */
    protected Vector3d calculateMotion(BlockFace face) {
        var motion = new Vector3d();
        motion.x = face.getOffset().x();
        motion.y = face.getOffset().y();
        motion.z = face.getOffset().z();
        motion.normalize();
        return motion;
    }
}
