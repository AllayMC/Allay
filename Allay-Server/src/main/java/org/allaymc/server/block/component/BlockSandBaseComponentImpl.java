package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.SandType;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockFallableBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * Allay Project 27/06/2024
 *
 * @author IWareQ
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "sand_type")
public class BlockSandBaseComponentImpl extends BlockFallableBaseComponentImpl {
    public BlockSandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var sandType = placementInfo.player().getItemInHand().getMeta() == 0 ? SandType.NORMAL : SandType.RED;
        blockState = blockState.setProperty(VanillaBlockPropertyTypes.SAND_TYPE, sandType);
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }
}
