package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class BlockChestBaseComponentImpl extends BlockBaseComponentImpl {
    @Dependency
    private BlockEntityHolderComponent<BlockEntityChest> blockEntityHolderComponent;

    public BlockChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlockState, newBlockState, placementInfo);

        var cardinalDirection = newBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.fromMinecraftCardinalDirection(cardinalDirection);
        for (var face : new BlockFace[]{blockFace.rotateY(), blockFace.rotateYCCW()}) {
            var other = oldBlockState.dimension().getBlockEntity(face.offsetPos(oldBlockState.pos()));
            if (other instanceof BlockEntityChest otherChest) {
                var otherState = otherChest.getBlockState();
                var otherCardinalDirection = otherState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
                var thisBlockEntity = blockEntityHolderComponent.getBlockEntity(oldBlockState.pos());
                if (cardinalDirection == otherCardinalDirection) {
                    if (otherChest.tryPairWith(thisBlockEntity)) {
                        thisBlockEntity.tryPairWith(otherChest);
                    }
                }
            }
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isSneaking()) {
            return false;
        }

        var upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(interactInfo.clickedBlockPos()));
        if (!upperBlock.getBlockStateData().isTransparent()) {
            return false;
        }

        var thisBlockEntity = blockEntityHolderComponent.getBlockEntity(new Position3i(interactInfo.clickedBlockPos(), dimension));
        if (thisBlockEntity.isPaired()) {
            var otherBlockEntity = thisBlockEntity.getPair();
            upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(otherBlockEntity.getPosition()));
            return upperBlock.getBlockStateData().isTransparent();
        }

        return true;
    }

    @Override
    public void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        super.onBreak(blockState, usedItem, entity);

        var thisBlockEntity = blockEntityHolderComponent.getBlockEntity(blockState.pos());
        if (thisBlockEntity.isPaired()) {
            // TODO
//            thisBlockEntity.unpair();
        }
    }
}
