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
 * @author IWareQ | daoge_cmd
 */
public class BlockEntityShulkerBoxBaseComponentImpl extends BlockEntityBaseComponentImpl {

    protected static final String TAG_FACING = "facing";

    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    private BlockFace facing = BlockFace.UP;

    public BlockEntityShulkerBoxBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

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

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_FACING, facing -> this.facing = BlockFace.fromIndex(facing));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putByte(TAG_FACING, (byte) facing.ordinal())
                .build();
    }

    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) return;

        facing = placementInfo.blockFace();
    }
}
