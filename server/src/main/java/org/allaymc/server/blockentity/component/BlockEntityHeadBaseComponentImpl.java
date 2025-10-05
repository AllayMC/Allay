package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.CompassRoseDirection;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityHeadBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockEntityHeadBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityHeadBaseComponent {

    protected static final String TAG_ROTATION = "Rotation";
    protected static final String TAG_DOING_ANIMATION = "DoingAnimation";
    protected static final String TAG_MOUTH_TICK_COUNT = "MouthTickCount";

    protected CompassRoseDirection direction;
    protected boolean playingAnimation;
    protected int animationTicks;

    public BlockEntityHeadBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (this.playingAnimation) {
            this.animationTicks++;
            sendBlockEntityToViewers();
        }
    }

    @Override
    public void setDirection(CompassRoseDirection direction) {
        this.direction = direction;
        sendBlockEntityToViewers();
    }

    @Override
    public void setPlayingAnimation(boolean playingAnimation) {
        this.playingAnimation = playingAnimation;
        sendBlockEntityToViewers();
    }

    @Override
    public void setAnimationTicks(int ticks) {
        this.animationTicks = ticks;
        sendBlockEntityToViewers();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForFloat(TAG_ROTATION, value -> this.direction = CompassRoseDirection.getClosestFromYaw(value));
        nbt.listenForBoolean(TAG_DOING_ANIMATION, value -> this.playingAnimation = value);
        nbt.listenForInt(TAG_MOUTH_TICK_COUNT, value -> this.animationTicks = value);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putFloat(TAG_ROTATION, this.direction.getYaw())
                .putBoolean(TAG_DOING_ANIMATION, this.playingAnimation)
                .putInt(TAG_MOUTH_TICK_COUNT, this.animationTicks)
                .build();
    }

    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo != null) {
            this.direction = CompassRoseDirection.getClosestFromYaw(placementInfo.player().getLocation().yaw());
        }
    }
}
