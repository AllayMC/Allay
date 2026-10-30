package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityComparatorBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityComparatorBaseComponentImpl} stores a comparator's output
 * signal independently of common block-entity state. The generated accessors
 * provide direct redstone-state access, and NBT loading or saving exchanges the
 * integer without clamping it or recalculating the comparator's output.
 *
 * @author daoge_cmd
 */
public class BlockEntityComparatorBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityComparatorBaseComponent {

    /**
     * {@code TAG_OUTPUT_SIGNAL} names the stored integer used by comparator NBT
     * loading and saving to retain the last calculated output signal.
     */
    protected static final String TAG_OUTPUT_SIGNAL = "OutputSignal";

    /**
     * {@code outputSignal} holds the last calculated redstone output. Its
     * generated setter and NBT loader retain values directly without enforcing
     * the API range.
     */
    @Getter
    @Setter
    protected int outputSignal;

    /**
     * Creates a comparator component with common identity and zero output
     * signal.
     */
    public BlockEntityComparatorBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Loads common data and a supplied comparator output signal. An absent
     * {@code "OutputSignal"} tag leaves the current value unchanged; present
     * integers are retained without range clamping or viewer updates.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_OUTPUT_SIGNAL, value -> outputSignal = value);
    }

    /**
     * Builds common NBT with the currently stored comparator output integer.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_OUTPUT_SIGNAL, outputSignal)
                .build();
    }
}
