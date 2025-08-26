package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.blockentity.data.BedColor;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author harry-xi
 */
public class ItemBedBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemBedBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if(super.placeBlock(dimension, placeBlockPos, placementInfo)) {
            var blockEntity = dimension.getBlockEntity(placeBlockPos);
            if(blockEntity instanceof BlockEntityBed bed) {
                bed.setColor(BedColor.fromInt(this.getMeta()));
            }
            return true;
        }
        return false;
    }
}
