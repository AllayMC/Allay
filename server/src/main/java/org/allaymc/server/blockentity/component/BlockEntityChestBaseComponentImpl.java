package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;

/**
 * @author daoge_cmd
 */
public class BlockEntityChestBaseComponentImpl extends BlockEntityBaseComponentImpl {

    @Dependency
    private BlockEntityChestContainerHolderComponentImpl containerHolderComponent;
    @Dependency
    private BlockEntityPairableComponent pairableComponent;

    public BlockEntityChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);

        var doubleChestContainer = containerHolderComponent.getDoubleChestContainerDirectly();
        doubleChestContainer.addOpenListener(viewer -> {
            if (doubleChestContainer.getViewers().size() == 1) {
                changeChestState(getPosition(), true);
                changeChestState(pairableComponent.getPair().getPosition(), true);
            }
        });
        doubleChestContainer.addCloseListener(viewer -> {
            if (doubleChestContainer.getViewers().isEmpty()) {
                changeChestState(getPosition(), false);
                changeChestState(pairableComponent.getPair().getPosition(), false);
            }
        });

        var container = containerHolderComponent.getContainer();
        container.addOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                changeChestState(getPosition(), true);
            }
        });
        container.addCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                changeChestState(getPosition(), false);
            }
        });
    }

    protected static void changeChestState(Position3ic pos, boolean open) {
        var dimension = pos.dimension();
        if (open) {
            dimension.addSound(MathUtils.center(pos), SimpleSound.CHEST_OPEN);
            dimension.addBlockAction(pos, SimpleBlockAction.OPEN);
        } else {
            dimension.addSound(MathUtils.center(pos), SimpleSound.CHEST_CLOSE);
            dimension.addBlockAction(pos, SimpleBlockAction.CLOSE);
        }
    }
}
