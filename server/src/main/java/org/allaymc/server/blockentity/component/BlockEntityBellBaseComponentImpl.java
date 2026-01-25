package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBellBaseComponent;
import org.allaymc.api.world.sound.CustomSound;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Implementation of bell block entity component.
 *
 * @author daoge_cmd
 */
public class BlockEntityBellBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBellBaseComponent {

    protected static final String TAG_RINGING = "Ringing";
    protected static final String TAG_TICKS = "Ticks";
    protected static final String TAG_DIRECTION = "Direction";

    protected static final int RING_DURATION_TICKS = 50; // Bell rings for 50 ticks (2.5 seconds)

    @Getter
    @Setter
    protected boolean ringing;

    @Getter
    @Setter
    protected int ticks;

    @Getter
    @Setter
    protected int direction;

    public BlockEntityBellBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForBoolean(TAG_RINGING, value -> ringing = value);
        nbt.listenForInt(TAG_TICKS, value -> ticks = value);
        nbt.listenForInt(TAG_DIRECTION, value -> direction = value);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean(TAG_RINGING, ringing)
                .putInt(TAG_TICKS, ticks)
                .putInt(TAG_DIRECTION, direction)
                .build();
    }

    @Override
    public void ring(int direction) {
        this.direction = direction;
        this.ticks = 0;
        this.ringing = true;

        // Play bell hit sound
        var pos = getPosition();
        pos.dimension().addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, new CustomSound("block.bell.hit"));

        // Send block entity update to clients
        sendBlockEntityToViewers();
    }

    @Override
    public void tick(long currentTick) {
        if (!ringing) {
            return;
        }

        ticks++;
        if (ticks >= RING_DURATION_TICKS) {
            ringing = false;
            ticks = 0;
            sendBlockEntityToViewers();
        }
    }
}
