package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityTargetBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityTargetBaseComponentImpl} stores a target block's active
 * redstone power and includes it in common block-entity persistence. NBT
 * loading clamps a present value to the redstone range, while the generated
 * setter retains caller-supplied integers directly.
 *
 * @author daoge_cmd
 */
public class BlockEntityTargetBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityTargetBaseComponent {

    /**
     * {@code TAG_ACTIVE_POWER} names the integer power entry exchanged by
     * target NBT loading and saving alongside the common block-entity fields.
     */
    protected static final String TAG_ACTIVE_POWER = "activePower";

    /**
     * {@code activePower} holds the retained redstone output. NBT loading
     * clamps its value to zero through fifteen, while the generated setter does
     * not.
     */
    @Getter
    @Setter
    protected int activePower;

    /**
     * Creates a target component with common identity and zero active power.
     */
    public BlockEntityTargetBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Loads common data and any supplied active-power integer. The power entry
     * is clamped to zero through fifteen before storage; an absent tag
     * preserves the previously retained value without broadcasting.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // Clamp persisted values to the target's supported redstone output
        // range.
        nbt.listenForInt(TAG_ACTIVE_POWER, value -> activePower = Math.clamp(value, 0, 15));
    }

    /**
     * Builds common NBT with the target's currently retained active-power
     * integer.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_ACTIVE_POWER, activePower)
                .build();
    }
}
