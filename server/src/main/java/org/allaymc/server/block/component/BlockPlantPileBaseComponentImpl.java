package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockPlantPileBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockPlantPileBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() != BlockFace.UP || !canPlaceOn(placementInfo.getClickedBlock().getBlockType())) {
            return false;
        }

        blockState = blockState.setPropertyValue(
                BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION,
                placementInfo.player().getHorizontalFace().opposite().toMinecraftCardinalDirection()
        );

        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();

        if (face == BlockFace.DOWN && !canPlaceOn(neighbor.getBlockType())) {
            block.breakBlock();
        }
    }

    protected boolean canPlaceOn(BlockType<?> blockType) {
        return blockType == GRASS_BLOCK ||
               blockType == MYCELIUM ||
               blockType == PODZOL ||
               blockType == DIRT ||
               blockType == DIRT_WITH_ROOTS ||
               blockType == FARMLAND ||
               blockType == MUD ||
               blockType == MUDDY_MANGROVE_ROOTS ||
               blockType == MOSS_BLOCK;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var center = MathUtils.center(interactInfo.clickedBlockPos());
        var block = interactInfo.getClickedBlock();
        var growth = block.getPropertyValue(BlockPropertyTypes.GROWTH);
        if (itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            if (growth < 3) {
                block.updateBlockProperty(BlockPropertyTypes.GROWTH, growth + 1);
            } else {
                interactInfo.player().getDimension().dropItem(this.blockType.getItemType().createItemStack(), center);
            }

            dimension.addParticle(center, SimpleParticle.BONE_MEAL);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        }

        if (itemStack.getItemType() == this.blockType.getItemType() && growth < 3) {
            block.updateBlockProperty(BlockPropertyTypes.GROWTH, growth + 1);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(block.getBlockType().getItemType().createItemStack(block.getPropertyValue(BlockPropertyTypes.GROWTH) + 1));
    }
}
