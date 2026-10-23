package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityShelf;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityShelfImpl} delegates shelf inventory access to the
 * configured component assembly. The inherited block-entity implementation
 * creates components, injects their dependencies and owner fields, and runs
 * completion hooks before construction returns, making this class the concrete
 * public capability for those components.
 *
 * @author daoge_cmd
 */
public class BlockEntityShelfImpl extends BlockEntityImpl implements BlockEntityShelf {
    /**
     * {@code containerHolderComponent} supplies the injected
     * {@link BlockEntityContainerHolderComponent} capability. Component
     * assembly requires exactly one provider result assignable to this field
     * before delegated operations are available.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;

    /**
     * Constructs the composed shelf using the supplied component providers.
     * Superclass construction injects the inherited base and declared component
     * fields before invoking completion hooks; provider or injection failures
     * abort construction without rollback.
     */
    public BlockEntityShelfImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
