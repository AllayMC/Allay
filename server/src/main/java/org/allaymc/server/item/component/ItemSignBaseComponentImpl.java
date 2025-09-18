package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class ItemSignBaseComponentImpl extends ItemBaseComponentImpl implements ItemSignBaseComponent {
    protected BlockId wallSignId;
    protected BlockId standingSignId;

    public ItemSignBaseComponentImpl(ItemStackInitInfo initInfo, BlockId wallSignId, BlockId standingSignId) {
        super(initInfo);
        this.wallSignId = wallSignId;
        this.standingSignId = standingSignId;
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() == BlockFace.UP) {
            return tryPlaceBlockState(dimension, getStandingSignType().getDefaultState(), placeBlockPos, placementInfo);
        } else {
            return tryPlaceBlockState(dimension, getWallSignType().getDefaultState(), placeBlockPos, placementInfo);
        }
    }

    @Override
    public BlockType<?> getWallSignType() {
        return wallSignId.getBlockType();
    }

    @Override
    public BlockType<?> getStandingSignType() {
        return standingSignId.getBlockType();
    }
}
