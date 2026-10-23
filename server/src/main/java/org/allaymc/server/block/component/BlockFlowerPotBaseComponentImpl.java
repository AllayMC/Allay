package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

/**
 * {@code BlockFlowerPotBaseComponentImpl} implements flower-pot support checks and player
 * plant insertion or removal. Pots placed by a player require a full center on the support's
 * upper face and reject trapdoors; placement without interaction information writes directly.
 * The injected holder supplies the plant-bearing block entity.
 *
 * @author Cdm2883
 */
public class BlockFlowerPotBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code blockEntityHolderComponent} resolves the flower-pot entity used during
     * interaction. Component initialization injects this dependency before normal block
     * callbacks access a pot's stored plant.
     */
    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityFlowerPot> blockEntityHolderComponent;

    /**
     * Creates the flower pot behavior component for the supplied block type.
     */
    public BlockFlowerPotBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the inherited neighbor notification and breaks the pot after invalid lower
     * support changes. Only the lower face triggers the local support test; other neighboring
     * changes retain the pot.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !isValidWeighBlock(neighbor.getBlockState())) {
            block.breakBlock();
        }
    }

    /**
     * Places the pot after checking support for a player placement. A {@code null} {@code
     * placementInfo} bypasses support validation, while player placement rejects trapdoors and
     * supports without a full upper-face center.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var downBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        if (!isValidWeighBlock(downBlock)) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Returns whether the state supplies a full upper-face center outside trapdoors.
     */
    private boolean isValidWeighBlock(BlockState block) {
        return block.getBlockStateData().collisionShape().isCenterFull(BlockFace.UP) &&
               !block.getBlockType().hasBlockTag(BlockTags.TRAPDOORS);
    }

    /**
     * Handles an inherited interaction before attempting pot plant removal or insertion. A
     * stored plant can be removed only with an air item, and removal offers the item to the
     * player's inventory. Insertion consumes the held item only after the block entity accepts
     * it.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        var position = new Position3i(interactInfo.clickedBlockPos(), dimension);
        var flowerPot = blockEntityHolderComponent.getBlockEntity(position);
        var plant = flowerPot.getPlantItem();

        if (plant != null && itemStack.getItemType() == ItemTypes.AIR) {
            flowerPot.clearPlant();
            // Offer the removed plant to inventory after clearing the entity. The inventory
            // result is ignored here, so this callback does not restore a rejected item.
            player.getContainer(ContainerTypes.INVENTORY).tryAddItem(plant);
            return true;
        } else if (plant == null && flowerPot.setPlantItem(itemStack)) {
            player.tryConsumeItemInHand();
            return true;
        }

        return false;
    }
}
