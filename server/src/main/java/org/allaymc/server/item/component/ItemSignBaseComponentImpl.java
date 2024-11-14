package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
@Getter
public class ItemSignBaseComponentImpl extends ItemBaseComponentImpl implements ItemSignBaseComponent {

    // NOTICE: We shouldn't use BlockType<?> here because item types are initialized earlier than block types.
    // TODO: Replace with Identifier
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
            return tryPlaceBlockState(dimension, standingSignId.getBlockType().getDefaultState(), placeBlockPos, placementInfo);
        } else {
            return tryPlaceBlockState(dimension, wallSignId.getBlockType().getDefaultState(), placeBlockPos, placementInfo);
        }
    }
}
