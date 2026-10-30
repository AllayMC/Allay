package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityMovingBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityMovingBlockImpl} delegates temporary piston-moved block
 * state to the configured component assembly. The inherited block-entity
 * implementation creates components, injects their dependencies and owner
 * fields, and runs completion hooks before construction returns, making this
 * class the concrete public capability for those components.
 *
 * @author daoge_cmd
 */
public class BlockEntityMovingBlockImpl extends BlockEntityImpl implements BlockEntityMovingBlock {

    /**
     * {@code movingBlockBaseComponent} supplies the injected
     * {@link BlockEntityMovingBlockBaseComponent} capability. Component
     * assembly requires exactly one provider result assignable to this field
     * before delegated operations are available.
     */
    @Delegate
    private BlockEntityMovingBlockBaseComponent movingBlockBaseComponent;

    /**
     * Constructs the composed moving block using the supplied component
     * providers. Superclass construction injects the inherited base and
     * declared component fields before invoking completion hooks; provider or
     * injection failures abort construction without rollback.
     */
    public BlockEntityMovingBlockImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
