package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBellBaseComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityBellBaseComponentImpl} stores and advances a bell's ringing
 * animation. {@code ring} records {@code direction}, resets {@code ticks} to
 * zero, and enables {@code ringing} before emitting a sound and broadcasting.
 * {@code tick} advances {@code ticks} while {@code ringing} is active, then
 * clears the flag and counter at the configured duration and informs viewers.
 *
 * @author daoge_cmd
 */
public class BlockEntityBellBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBellBaseComponent {

    /**
     * {@code TAG_RINGING} names the persisted animation flag. The bell tag
     * constants identify the flag, elapsed ticks, and strike direction used for
     * NBT exchange.
     */
    protected static final String TAG_RINGING = "Ringing";
    /**
     * Persisted elapsed ringing ticks.
     */
    protected static final String TAG_TICKS = "Ticks";
    /**
     * Persisted horizontal strike direction.
     */
    protected static final String TAG_DIRECTION = "Direction";

    /**
     * {@code RING_DURATION_TICKS} limits one strike's animation duration in
     * game ticks; {@code tick} stops ringing when its elapsed counter reaches
     * this value.
     */
    protected static final int RING_DURATION_TICKS = 50;

    /**
     * Current ringing animation flag.
     */
    @Getter
    @Setter
    protected boolean ringing;

    /**
     * Elapsed ringing animation ticks.
     */
    @Getter
    @Setter
    protected int ticks;

    /**
     * Horizontal bell strike direction.
     */
    @Getter
    @Setter
    protected int direction;

    /**
     * Creates a bell component with common identity and inactive ringing state.
     */
    public BlockEntityBellBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Loads common state and any supplied bell animation fields. Absent
     * ringing, elapsed-tick, or direction tags preserve their current values,
     * and loading does not itself play a hit sound.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForBoolean(TAG_RINGING, value -> ringing = value);
        nbt.listenForInt(TAG_TICKS, value -> ticks = value);
        nbt.listenForInt(TAG_DIRECTION, value -> direction = value);
    }

    /**
     * Builds common NBT with the bell's current flag, counter, and direction.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean(TAG_RINGING, ringing)
                .putInt(TAG_TICKS, ticks)
                .putInt(TAG_DIRECTION, direction)
                .build();
    }

    /**
     * Starts or restarts ringing with a zero counter and the supplied
     * direction. The hit sound precedes the block-entity broadcast; failures
     * after state assignment do not restore the previous animation fields.
     */
    @Override
    public void ring(int direction) {
        this.direction = direction;
        this.ticks = 0;
        this.ringing = true;

        var pos = getPosition();
        pos.dimension().addSound(MathUtils.center(pos), SimpleSound.BELL_HIT);

        sendBlockEntityToViewers();
    }

    /**
     * Advances the elapsed counter only while the bell is ringing. Reaching the
     * configured duration clears the ringing flag, resets elapsed ticks, and
     * sends the stopped animation state to viewers.
     */
    @Override
    public void tick(long currentTick) {
        if (!ringing) {
            return;
        }

        ticks++;
        // End the transient ring after its fixed animation duration.
        if (ticks >= RING_DURATION_TICKS) {
            ringing = false;
            ticks = 0;
            sendBlockEntityToViewers();
        }
    }
}
