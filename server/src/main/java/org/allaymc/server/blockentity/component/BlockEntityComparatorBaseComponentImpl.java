package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityComparatorBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Implementation of the comparator block entity component.
 * Stores the output signal value for redstone comparators.
 *
 * @author daoge_cmd
 */
public class BlockEntityComparatorBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityComparatorBaseComponent {

    protected static final String TAG_OUTPUT_SIGNAL = "OutputSignal";

    @Getter
    @Setter
    protected int outputSignal;

    public BlockEntityComparatorBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_OUTPUT_SIGNAL, value -> outputSignal = value);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_OUTPUT_SIGNAL, outputSignal)
                .build();
    }
}
