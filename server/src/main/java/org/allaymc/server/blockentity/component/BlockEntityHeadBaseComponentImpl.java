package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.CompassRoseDirection;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityHeadBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityHeadBaseComponentImpl} stores head orientation and
 * animation state and broadcasts elapsed animation progress while playing. Its
 * direction starts {@code null}; placement or supplied rotation NBT establishes
 * a compass value before saving can dereference it. NBT saving uses the
 * direction's yaw, and loading quantizes that yaw back to one of the sixteen
 * compass directions. The animation counter records elapsed ticks independently
 * of the flag that enables ticking.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityHeadBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityHeadBaseComponent {

    /**
     * {@code TAG_ROTATION} names the persisted yaw value. These head tag
     * constants identify orientation, animation activity, and elapsed ticks
     * during NBT exchange.
     */
    protected static final String TAG_ROTATION = "Rotation";
    /**
     * Persisted active animation flag.
     */
    protected static final String TAG_DOING_ANIMATION = "DoingAnimation";
    /**
     * Persisted elapsed animation ticks.
     */
    protected static final String TAG_MOUTH_TICK_COUNT = "MouthTickCount";

    /**
     * {@code direction} holds the compass orientation and initially remains
     * {@code null}. Placement, loading a rotation, or the setter must establish
     * it before saving dereferences the value.
     */
    protected CompassRoseDirection direction;
    /**
     * Current active animation flag.
     */
    protected boolean playingAnimation;
    /**
     * Elapsed head animation ticks.
     */
    protected int animationTicks;

    /**
     * Creates common head state with inactive animation and no initialized
     * direction.
     */
    public BlockEntityHeadBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Advances elapsed animation ticks and updates viewers while animation is
     * active. Inactive heads retain their counter unchanged; this method does
     * not stop animation at any particular elapsed value.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (this.playingAnimation) {
            this.animationTicks++;
            sendBlockEntityToViewers();
        }
    }

    /**
     * Stores the supplied compass direction and sends updated state to viewers.
     * The reference is retained without validation, so a {@code null}
     * assignment can fail when the update or a later save dereferences it.
     */
    @Override
    public void setDirection(CompassRoseDirection direction) {
        this.direction = direction;
        sendBlockEntityToViewers();
    }

    /**
     * Stores the animation-active flag and sends updated state to viewers. The
     * elapsed counter remains unchanged, allowing callers to resume from the
     * retained count or reset it through {@code setAnimationTicks}.
     */
    @Override
    public void setPlayingAnimation(boolean playingAnimation) {
        this.playingAnimation = playingAnimation;
        sendBlockEntityToViewers();
    }

    /**
     * Stores the elapsed animation counter and sends updated state to viewers.
     * The supplied integer is retained without clamping, and changing it does
     * not change whether the animation flag is active.
     */
    @Override
    public void setAnimationTicks(int ticks) {
        this.animationTicks = ticks;
        sendBlockEntityToViewers();
    }

    /**
     * Loads common state and supplied head orientation or animation fields.
     * Rotation is quantized through
     * {@code CompassRoseDirection.getClosestFromYaw}, which restores the stored
     * yaw of each sixteen-way direction; missing tags preserve the
     * corresponding fields.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // Quantize persisted yaw to the sixteen-way direction table, whose
        // index offset compensates for its south-first ordering and restores
        // each stored direction.
        nbt.listenForFloat(TAG_ROTATION, value -> this.direction = CompassRoseDirection.getClosestFromYaw(value));
        nbt.listenForBoolean(TAG_DOING_ANIMATION, value -> this.playingAnimation = value);
        nbt.listenForInt(TAG_MOUTH_TICK_COUNT, value -> this.animationTicks = value);
    }

    /**
     * Builds common NBT with the direction's yaw and current animation fields.
     * {@code direction} must be initialized before this method runs; its stored
     * yaw uses the convention provided by {@code getYaw}.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putFloat(TAG_ROTATION, this.direction.getYaw())
                .putBoolean(TAG_DOING_ANIMATION, this.playingAnimation)
                .putInt(TAG_MOUTH_TICK_COUNT, this.animationTicks)
                .build();
    }

    /**
     * Selects the closest compass orientation to the placing player's yaw.
     * Events without player placement information preserve the current
     * orientation, and this hook does not send a separate viewer update.
     */
    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo != null) {
            this.direction = CompassRoseDirection.getClosestFromYaw(placementInfo.player().getLocation().yaw());
        }
    }
}
