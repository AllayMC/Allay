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
 * Default dispense behavior that tries to equip armor on nearby entities,
 * or drops the item with random motion if no entity can be equipped.
 *
 * @author daoge_cmd
 */
public class DefaultDispenseBehavior implements DispenseBehavior {

    public static final DefaultDispenseBehavior INSTANCE = new DefaultDispenseBehavior();

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        if (tryEquipArmor(block, face, item)) {
            spawnDispenseEffects(block, face);
            return DispenseResult.success();
        }

        dropItem(block, face, item);
        spawnDispenseEffects(block, face);
        return DispenseResult.success();
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }

    protected boolean tryEquipArmor(Block block, BlockFace face, ItemStack item) {
        var armorSlot = getArmorSlot(item);
        if (armorSlot == -1) {
            return false;
        }

        var dimension = block.getDimension();
        var dispensePos = getDispensePosition(block, face);

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

    protected void dropItem(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var dispensePos = getDispensePosition(block, face);

        var random = ThreadLocalRandom.current();
        var offset = random.nextDouble() * 0.1 + 0.2;

        var motion = new Vector3d();
        motion.x = face.getOffset().x() * offset;
        motion.y = 0.2;
        motion.z = face.getOffset().z() * offset;

        motion.x += random.nextGaussian() * 0.0075 * 6;
        motion.y += random.nextGaussian() * 0.0075 * 6;
        motion.z += random.nextGaussian() * 0.0075 * 6;

        var dropItem = item.copy();
        dropItem.setCount(1);
        dimension.dropItem(dropItem, dispensePos, motion);
    }

    protected Vector3d getDispensePosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x() * 0.7;
        pos.y += face.getOffset().y() * 0.7;
        pos.z += face.getOffset().z() * 0.7;

        if (face.getAxis() == BlockFace.Axis.Y) {
            pos.y -= 0.125;
        } else {
            pos.y -= 0.15625;
        }

        return pos;
    }
}
