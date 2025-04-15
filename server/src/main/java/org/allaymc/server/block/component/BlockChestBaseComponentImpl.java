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

        if (placementInfo != null && placementInfo.player().isSneaking()) {
            // Do not check for pairing if the player is sneaking
            return;
        }

        var direction = newBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        var thisChest = blockEntityHolderComponent.getBlockEntity(oldBlockState.getPos());

        for (var face : new BlockFace[]{blockFace.rotateY(), blockFace.rotateYCCW()}) {
            var other = oldBlockState.offsetPos(face).getBlockEntity();
            if (other instanceof BlockEntityChest otherChest && !otherChest.isPaired()) {
                if (direction == otherChest.getBlockState().getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)) {
                    if (otherChest.tryPairWith(thisChest)) {
                        thisChest.tryPairWith(otherChest);
                        break;
                    }
                }
            }
        }
    }
}
