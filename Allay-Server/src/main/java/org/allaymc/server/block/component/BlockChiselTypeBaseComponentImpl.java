package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.ChiselType;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.PlaceBlockHelper;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.CHISEL_TYPE;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "chisel_type")
public class BlockChiselTypeBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockChiselTypeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo != null) {
            blockState = blockState.setProperty(CHISEL_TYPE, switch (placementInfo.player().getItemInHand().getMeta()) {
                case 0 -> ChiselType.DEFAULT;
                case 1 -> ChiselType.CHISELED;
                case 2 -> ChiselType.LINES;
                case 3 -> ChiselType.SMOOTH;
                default -> null;
            });
        }
        
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                PlaceBlockHelper.processPillarAxisProperty(blockState, placeBlockPos, placementInfo),
                placementInfo
        );
        return true;
    }
}
