package org.allaymc.server.blockentity.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBedBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.server.block.component.BlockBedBaseComponentImpl;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Optional;

/**
 * @author harry-xi
 */
public class BlockEntityBedBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBedBaseComponent {

    private static final String TAG_COLOR = "color";

    @Getter
    protected DyeColor color;

    public BlockEntityBedBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @EventHandler
    @Override
    public void onBlockPlace(CBlockOnPlaceEvent event) {
        super.onBlockPlace(event);
        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) {
            this.color = DyeColor.RED;
        } else {
            this.color = Preconditions.checkNotNull(DyeColor.from(placementInfo.player().getItemInHand().getMeta()));
        }
    }

    public void setColor(DyeColor color) {
        this.color = color;
        var pair = BlockBedBaseComponentImpl.getPairBlock(new Block(this.getDimension(), this.getPosition())).getBlockEntity();
        if (pair instanceof BlockEntityBedBaseComponent bed) {
            if (bed.getColor() != color) {
                bed.setColor(color);
            }
        }
        sendBlockEntityToViewers();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_COLOR, b -> this.color = Optional.ofNullable(DyeColor.from(b)).orElse(DyeColor.RED));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putByte(TAG_COLOR, (byte) this.color.ordinal())
                .build();
    }
}
