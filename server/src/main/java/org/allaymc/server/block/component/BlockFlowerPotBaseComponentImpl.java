package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

/**
 * @author Cdm2883
 */
public class BlockFlowerPotBaseComponentImpl extends BlockBaseComponentImpl {
    @Dependency
    BlockEntityHolderComponent<BlockEntityFlowerPot> blockEntityHolderComponent;

    public BlockFlowerPotBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
        if (face == BlockFace.DOWN && !isValidWeighBlock(neighbor.blockState())) {
            current.pos().dimension().breakBlock(current.pos());
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var downBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        if (!isValidWeighBlock(downBlock)) {
            return false;
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    private boolean isValidWeighBlock(BlockState block) {
        return block.getBlockStateData().collisionShape().isCenterFull(BlockFace.UP) &&
               !block.getBlockType().hasBlockTag(BlockTags.TRAPDOORS);
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

        var position = new Position3i(interactInfo.clickedBlockPos(), dimension);
        var flowerPot = blockEntityHolderComponent.getBlockEntity(position);
        var plant = flowerPot.getPlantItem();

        // Plants can only be pulled out when player is empty-handed.
        if (plant != null && itemStack.getItemType() == ItemTypes.AIR) {
            flowerPot.clearPlant();
            // When the flower is pulled, the item will place to the player's first empty slot instead of hand.
            // And the player needs to be empty-handed to pull out, there is no such thing as a failure.
            player.getContainer(FullContainerType.PLAYER_INVENTORY).tryAddItem(plant);
            return true;
        } else if (plant == null && flowerPot.setPlantItem(itemStack)) {
            player.tryConsumeItemInHand();
            return true;
        }

        return false;
    }
}
