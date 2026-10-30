package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.BarrelContainerImpl;

/**
 * {@code BlockEntityBarrelBaseComponentImpl} connects barrel viewer changes to
 * the block's open property. The first viewer opens the block and the last
 * viewer closes it; the container owns viewer membership, while these callbacks
 * update the barrel at its current position.
 *
 * @author daoge_cmd
 */
public class BlockEntityBarrelBaseComponentImpl extends BlockEntityBaseComponentImpl {
    /**
     * {@code containerHolderComponent} supplies the barrel container whose
     * viewer listeners are installed after component dependencies have been
     * injected.
     */
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    /**
     * Creates barrel base state using the supplied block-entity initialization
     * information.
     */
    public BlockEntityBarrelBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    /**
     * Loads base NBT and installs the barrel's viewer-state callbacks. The
     * injected holder must contain a {@code BarrelContainerImpl}; later
     * replacement of its container does not move these registered callbacks.
     */
    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        BarrelContainerImpl container = containerHolderComponent.getContainer();
        container.addOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                position.dimension().updateBlockProperty(
                        BlockPropertyTypes.OPEN_BIT,
                        true,
                        position.x(),
                        position.y(),
                        position.z()
                );
            }
        });
        container.addCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                position.dimension().updateBlockProperty(
                        BlockPropertyTypes.OPEN_BIT,
                        false,
                        position.x(),
                        position.y(),
                        position.z()
                );
            }
        });
    }
}
