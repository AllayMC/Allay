package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;

import java.util.concurrent.ThreadLocalRandom;

public class ShearsDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());

        var entities = dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(new AABBd(
                        targetPos.x(), targetPos.y(), targetPos.z(),
                        targetPos.x() + 1, targetPos.y() + 1, targetPos.z() + 1
                ), entity -> entity instanceof EntitySheep sheep && !sheep.isSheared() && !sheep.isBaby());

        for (var entity : entities) {
            if (!(entity instanceof EntitySheep sheep)) {
                continue;
            }
            sheep.setSheared(true);
            dropWool(dimension, sheep);
            dimension.addSound(
                    new Vector3d(sheep.getLocation().x(), sheep.getLocation().y(), sheep.getLocation().z()),
                    new CustomSound(SoundNames.MOB_SHEEP_SHEAR)
            );
            return damageItem(item);
        }

        return DispenseResult.fail();
    }

    protected DispenseResult damageItem(ItemStack item) {
        item.tryIncreaseDamage(1);
        if (item.isBroken()) {
            return DispenseResult.success(ItemAirStack.AIR_STACK);
        }
        return DispenseResult.success(item);
    }

    protected void dropWool(Dimension dimension, EntitySheep sheep) {
        var rand = ThreadLocalRandom.current();
        int count = rand.nextInt(1, 4);
        var woolType = getWoolItemForColor(sheep.getColor());
        if (woolType != null) {
            var woolStack = woolType.createItemStack(count);
            dimension.dropItem(woolStack, new Vector3d(sheep.getLocation().x(), sheep.getLocation().y() + 0.5, sheep.getLocation().z()));
        }
    }

    protected ItemType<?> getWoolItemForColor(DyeColor color) {
        return switch (color) {
            case WHITE -> ItemTypes.WHITE_WOOL;
            case ORANGE -> ItemTypes.ORANGE_WOOL;
            case MAGENTA -> ItemTypes.MAGENTA_WOOL;
            case LIGHT_BLUE -> ItemTypes.LIGHT_BLUE_WOOL;
            case YELLOW -> ItemTypes.YELLOW_WOOL;
            case LIME -> ItemTypes.LIME_WOOL;
            case PINK -> ItemTypes.PINK_WOOL;
            case GRAY -> ItemTypes.GRAY_WOOL;
            case LIGHT_GRAY -> ItemTypes.LIGHT_GRAY_WOOL;
            case CYAN -> ItemTypes.CYAN_WOOL;
            case PURPLE -> ItemTypes.PURPLE_WOOL;
            case BLUE -> ItemTypes.BLUE_WOOL;
            case BROWN -> ItemTypes.BROWN_WOOL;
            case GREEN -> ItemTypes.GREEN_WOOL;
            case RED -> ItemTypes.RED_WOOL;
            case BLACK -> ItemTypes.BLACK_WOOL;
        };
    }
}