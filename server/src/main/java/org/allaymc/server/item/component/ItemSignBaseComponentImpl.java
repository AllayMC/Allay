package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
@Getter
public class ItemSignBaseComponentImpl extends ItemBaseComponentImpl implements ItemSignBaseComponent {

    // NOTICE: We shouldn't use BlockType<?> here because item types are initialized earlier than block types.
    protected Identifier wallSignId;
    protected Identifier standingSignId;

    public ItemSignBaseComponentImpl(ItemStackInitInfo initInfo, Identifier wallSignId, Identifier standingSignId) {
        super(initInfo);
        this.wallSignId = wallSignId;
        this.standingSignId = standingSignId;
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() == BlockFace.UP) {
            return tryPlaceBlockState(dimension, Registries.BLOCKS.get(standingSignId).getDefaultState(), placeBlockPos, placementInfo);
        } else {
            return tryPlaceBlockState(dimension, Registries.BLOCKS.get(wallSignId).getDefaultState(), placeBlockPos, placementInfo);
        }
    }
}
