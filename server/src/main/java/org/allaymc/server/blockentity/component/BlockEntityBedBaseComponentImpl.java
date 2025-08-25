package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.blockentity.component.BlockEntityBedBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.server.block.component.BlockBedBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author harry-xi
 */
public class BlockEntityBedBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBedBaseComponent {

    private static final String TAG_COLOR = "color";

    @Getter
    protected byte color;

    public void setColor(byte color) {
        this.color = color;
        var pair = BlockBedBaseComponentImpl.getPairBlock(new Block(this.getDimension(),this.getPosition())).getBlockEntity();
        if(pair instanceof BlockEntityBedBaseComponent bed){
            if(bed.getColor() != color){
                bed.setColor(color);
            }
        }
        sendBlockEntityDataPacketToViewers(); // I hope it can let player see the right color, but it seems not working.
    }

    public BlockEntityBedBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_COLOR, b -> this.color = b);
    }

    @Override
    public NbtMap saveNBT() {
        var builder=  super.saveNBT().toBuilder();
        builder.putByte(TAG_COLOR, color);
        return builder.build();
    }
}
