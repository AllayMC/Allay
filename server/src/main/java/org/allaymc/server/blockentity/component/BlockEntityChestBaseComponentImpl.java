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
 * {@code BlockEntityChestBaseComponentImpl} emits chest opening and closing
 * sounds and block actions from container viewer changes. Single-container
 * callbacks affect this chest, while double-container callbacks affect both
 * currently paired positions; the pairing must still resolve when those
 * callbacks run.
 *
 * @author daoge_cmd
 */
public class BlockEntityChestBaseComponentImpl extends BlockEntityBaseComponentImpl {

    /**
     * {@code containerHolderComponent} supplies both chest views so
     * initialization can attach viewer callbacks before the combined view is
     * configured for use.
     */
    @Dependency
    private BlockEntityChestContainerHolderComponentImpl containerHolderComponent;
    /**
     * {@code pairableComponent} resolves the other chest when combined-view
     * callbacks run; the callbacks require that relationship to remain usable.
     */
    @Dependency
    private BlockEntityPairableComponent pairableComponent;

    /**
     * Creates chest base state using the supplied block-entity initialization
     * information.
     */
    public BlockEntityChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    /**
     * Loads base NBT and registers callbacks on single and combined chest
     * views. The direct combined view permits listener registration before
     * pairing configuration, but its viewer callbacks require a resolvable
     * partner.
     */
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

    /**
     * Emits the matching chest sound and opening or closing block action.
     * {@code pos} supplies both the dimension and block location; this helper
     * sends effects without changing viewer membership or persisted chest
     * state.
     */
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
