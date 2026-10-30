package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code DefaultDispenseBehavior} attempts nearby armor equipping before falling
 * back to one-item ejection. Supported armor selects a slot in the first eligible
 * living entity with an empty armor position; other items and unsuccessful
 * searches produce a copied dropped stack with randomized motion.
 *
 * @author daoge_cmd
 */
public class DefaultDispenseBehavior implements DispenseBehavior {

    /**
     * {@code INSTANCE} is the shared ordinary dispensing fallback. Item-specific
     * handlers delegate unsupported targets here for armor equip or one-item
     * ejection.
     */
    public static final DefaultDispenseBehavior INSTANCE = new DefaultDispenseBehavior();

    /**
     * Equips eligible armor first, otherwise drops one copied item with randomized
     * motion. Both completed paths emit dispenser effects and return success,
     * leaving the caller to consume the selected original item.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        if (tryEquipArmor(block, face, item)) {
            spawnDispenseEffects(block, face);
            return DispenseResult.success();
        }

        // An unsupported armor item or a search without an eligible empty slot
        // still needs an output action, so eject one item through the shared
        // fallback path.
        dropItem(block, face, item);
        spawnDispenseEffects(block, face);
        return DispenseResult.success();
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

    /**
     * Equips a single copied armor item on the first eligible nearby living
     * entity. The candidate needs an armor container whose matching slot contains
     * {@code null} or {@code ItemAirStack.AIR_STACK}; other stored stacks are
     * retained. An unsupported item or an unsuccessful search returns
     * {@code false}.
     */
    protected boolean tryEquipArmor(Block block, BlockFace face, ItemStack item) {
        var armorSlot = getArmorSlot(item);
        if (armorSlot == -1) {
            return false;
        }

        var dimension = block.getDimension();
        var dispensePos = getDispensePosition(block, face);

        // Search one block across and two blocks vertically around the ejection
        // point.
        var aabb = new AABBd(
                dispensePos.x - 0.5, dispensePos.y - 1, dispensePos.z - 0.5,
                dispensePos.x + 0.5, dispensePos.y + 1, dispensePos.z + 0.5
        );

        var entities = dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(aabb, entity -> entity instanceof EntityLiving);

        for (var entity : entities) {
            if (!(entity instanceof EntityContainerHolderComponent containerHolder)) {
                continue;
            }

            if (!containerHolder.hasContainer(ContainerTypes.ARMOR)) {
                continue;
            }

            var armorContainer = containerHolder.getContainer(ContainerTypes.ARMOR);
            var currentArmor = armorContainer.getItemStack(armorSlot);
            if (currentArmor != null && currentArmor != ItemAirStack.AIR_STACK) {
                continue;
            }

            var armorItem = item.copy();
            armorItem.setCount(1);
            armorContainer.setItemStack(armorSlot, armorItem);
            return true;
        }

        return false;
    }

    /**
     * Returns the armor slot index for a supported wearable stack. Helmet,
     * chestplate, leggings, and boots use indices zero through three; an
     * unsupported item returns {@code -1}.
     */
    protected int getArmorSlot(ItemStack item) {
        if (item instanceof ItemHelmetStack) {
            return 0;
        } else if (item instanceof ItemChestplateStack) {
            return 1;
        } else if (item instanceof ItemLeggingsStack) {
            return 2;
        } else if (item instanceof ItemBootsStack) {
            return 3;
        }
        return -1;
    }

    /**
     * Drops a single copied item at the dispenser output with randomized motion.
     * The caller retains the original stack and performs inventory consumption
     * after this behavior returns its success result.
     */
    protected void dropItem(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var dispensePos = getDispensePosition(block, face);

        var random = ThreadLocalRandom.current();
        // Select an outward horizontal speed between 0.2 and 0.3 before spread.
        var offset = random.nextDouble() * 0.1 + 0.2;

        var motion = new Vector3d();
        motion.x = face.getOffset().x() * offset;
        motion.y = 0.2;
        motion.z = face.getOffset().z() * offset;

        // Independent Gaussian spread perturbs all axes after the outward motion
        // is set, so ejected items do not share one exact flight direction.
        motion.x += random.nextGaussian() * 0.0075 * 6;
        motion.y += random.nextGaussian() * 0.0075 * 6;
        motion.z += random.nextGaussian() * 0.0075 * 6;

        var dropItem = item.copy();
        dropItem.setCount(1);
        dimension.dropItem(dropItem, dispensePos, motion);
    }

    /**
     * Returns a new output position shared by item ejection and armor search
     * geometry. The position begins at the block center, moves outward along the
     * face, and lowers by the face-dependent mouth adjustment.
     */
    protected Vector3d getDispensePosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        // A 0.7-block face offset places the origin beyond the block boundary.
        pos.x += face.getOffset().x() * 0.7;
        pos.y += face.getOffset().y() * 0.7;
        pos.z += face.getOffset().z() * 0.7;

        // Keep horizontal ejections below mid-height; vertical faces use a smaller
        // downward adjustment.
        if (face.getAxis() == BlockFace.Axis.Y) {
            pos.y -= 0.125;
        } else {
            pos.y -= 0.15625;
        }

        return pos;
    }
}
