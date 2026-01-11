package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityNoteblockBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class BlockEntityNoteblockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityNoteblockBaseComponent {

    protected static final String TAG_NOTE = "note";
    protected static final String TAG_POWERED = "powered";

    @Getter
    @Setter
    protected int pitch;

    @Getter
    @Setter
    protected boolean powered;

    public BlockEntityNoteblockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_NOTE, value -> pitch = value);
        nbt.listenForBoolean(TAG_POWERED, value -> powered = value);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_NOTE, pitch)
                .putBoolean(TAG_POWERED, powered)
                .build();
    }

    @Override
    public int getNextPitch() {
        this.pitch = (this.pitch + 1) % 25;
        return this.pitch;
    }
}
