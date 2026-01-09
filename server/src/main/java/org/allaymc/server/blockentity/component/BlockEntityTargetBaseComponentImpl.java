package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityTargetBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Implementation of the target block entity component.
 * Stores the active power level for target blocks.
 *
 * @author daoge_cmd
 */
public class BlockEntityTargetBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityTargetBaseComponent {

    protected static final String TAG_ACTIVE_POWER = "activePower";

    @Getter
    @Setter
    protected int activePower;

    public BlockEntityTargetBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_ACTIVE_POWER, value -> activePower = Math.clamp(value, 0, 15));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_ACTIVE_POWER, activePower)
                .build();
    }
}
