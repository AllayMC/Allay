package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityDecoratedPotBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDecoratedPot;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityDecoratedPotImpl} delegates decorated pot contents and
 * sherds to the configured component assembly. The inherited block-entity
 * implementation creates components, injects their dependencies and owner
 * fields, and runs completion hooks before construction returns, making this
 * class the concrete public capability for those components.
 *
 * @author IWareQ
 */
public class BlockEntityDecoratedPotImpl extends BlockEntityImpl implements BlockEntityDecoratedPot {
    /**
     * {@code decoratedPotBaseComponent} supplies the injected
     * {@link BlockEntityDecoratedPotBaseComponent} capability. Component
     * assembly requires exactly one provider result assignable to this field
     * before delegated operations are available.
     */
    @Delegate
    private BlockEntityDecoratedPotBaseComponent decoratedPotBaseComponent;

    /**
     * Constructs the composed decorated pot using the supplied component
     * providers. Superclass construction injects the inherited base and
     * declared component fields before invoking completion hooks; provider or
     * injection failures abort construction without rollback.
     */
    public BlockEntityDecoratedPotImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
