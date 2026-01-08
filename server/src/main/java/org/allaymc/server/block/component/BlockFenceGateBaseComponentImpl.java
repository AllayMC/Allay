package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.FenceGateSound;
import org.allaymc.server.block.BlockPlaceHelper;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author IWareQ
 */
public class BlockFenceGateBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockFenceGateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);
        block.updateBlockProperty(IN_WALL_BIT, shouldBeLowered(block));
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        blockState = BlockPlaceHelper.processMinecraftCardinalDirectionProperty(blockState, placeBlockPos, placementInfo);
        var current = new Block(blockState, new Position3i(placeBlockPos, dimension));
        blockState = blockState.setPropertyValue(IN_WALL_BIT, shouldBeLowered(current));
        return dimension.setBlockState(placeBlockPos, blockState);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedPos = interactInfo.clickedBlockPos();
        var clickedBlockState = interactInfo.getClickedBlock();
        var open = !clickedBlockState.getPropertyValue(OPEN_BIT);
        if (open) {
            var playerFacing = interactInfo.player().getHorizontalFace();
            var direction = clickedBlockState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
            if (playerFacing == BlockFace.from(direction).opposite()) {
                dimension.updateBlockProperty(MINECRAFT_CARDINAL_DIRECTION, playerFacing.toMinecraftCardinalDirection(), clickedPos);
            }
        }

        clickedBlockState.updateBlockProperty(OPEN_BIT, open);
        clickedBlockState.addSound(new FenceGateSound(clickedBlockState.getBlockState(), open));
        return true;
    }

    private boolean shouldBeLowered(Block current) {
        var direction = current.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        blockFace = blockFace.rotateY();
        return current.offsetPos(blockFace).getBehavior() instanceof BlockWallBehavior ||
               current.offsetPos(blockFace.opposite()).getBehavior() instanceof BlockWallBehavior;
    }
}
