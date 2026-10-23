package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.ShulkerBoxContainerImpl;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityShulkerBoxBaseComponentImpl} maintains shulker-box facing
 * data and viewer animation effects. The first viewer emits opening sound and
 * action, while the last viewer emits closing effects; placement records the
 * clicked face and NBT carries its block-face index.
 *
 * @author IWareQ | daoge_cmd
 */
public class BlockEntityShulkerBoxBaseComponentImpl extends BlockEntityBaseComponentImpl {

    /**
     * {@code TAG_FACING} names the byte tag carrying the shulker box's
     * {@code BlockFace} index across base-component saves and loads.
     */
    protected static final String TAG_FACING = "facing";

    /**
     * {@code containerHolderComponent} supplies the shulker-box container whose
     * viewer callbacks are installed after dependency injection and base
     * loading complete.
     */
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    /**
     * {@code facing} starts as {@code BlockFace.UP} and changes on placement or
     * byte-tag loading; an invalid saved index can replace it with
     * {@code null}.
     */
    private BlockFace facing = BlockFace.UP;

    /**
     * Creates shulker-box base state using the supplied initialization
     * information.
     */
    public BlockEntityShulkerBoxBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    /**
     * Loads base state and registers shulker-box opening and closing callbacks.
     * The injected holder must contain a {@code ShulkerBoxContainerImpl};
     * replacing its container later leaves those callbacks on the original
     * instance.
     */
    @Override
    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        ShulkerBoxContainerImpl container = containerHolderComponent.getContainer();
        container.addOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                position.dimension().addSound(MathUtils.center(position), SimpleSound.SHULKER_BOX_OPEN);
                position.dimension().addBlockAction(position, SimpleBlockAction.OPEN);
            }
        });
        container.addCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                position.dimension().addSound(MathUtils.center(position), SimpleSound.SHULKER_BOX_CLOSE);
                position.dimension().addBlockAction(position, SimpleBlockAction.CLOSE);
            }
        });
    }

    /**
     * Loads base state before applying a present byte-encoded facing index. An
     * absent tag preserves the current face; an invalid index becomes
     * {@code null} and can cause a later save to fail.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // The saved byte uses BlockFace indices; invalid values remain
        // observable as null rather than silently selecting the default upward
        // face.
        nbt.listenForByte(TAG_FACING, facing -> this.facing = BlockFace.fromIndex(facing));
    }

    /**
     * Adds the current facing ordinal to the completed base save data. The byte
     * uses {@code BlockFace} declaration order; {@code facing} must remain
     * nonnull for its ordinal to be serialized.
     */
    @Override
    public NbtMap saveNBT() {
        // Use the BlockFace ordinal as the persisted byte because loading
        // decodes that byte with the same declared face order.
        return super.saveNBT().toBuilder()
                .putByte(TAG_FACING, (byte) facing.ordinal())
                .build();
    }

    /**
     * Records the clicked block face from available placement information. A
     * placement event without that information leaves the existing face
     * unchanged, allowing non-player placement paths to retain their
     * initialized state.
     */
    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) return;

        facing = placementInfo.blockFace();
    }
}
