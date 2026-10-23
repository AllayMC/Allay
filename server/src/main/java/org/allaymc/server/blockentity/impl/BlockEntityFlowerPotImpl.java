package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityFlowerPotBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityFlowerPotImpl} delegates flower pot plant state to the
 * configured component assembly. The inherited block-entity implementation
 * creates components, injects their dependencies and owner fields, and runs
 * completion hooks before construction returns, making this class the concrete
 * public capability for those components.
 *
 * @author Cdm2883
 */
public class BlockEntityFlowerPotImpl extends BlockEntityImpl implements BlockEntityFlowerPot {

    /**
     * {@code flowerPotBaseComponent} supplies the injected
     * {@link BlockEntityFlowerPotBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityFlowerPotBaseComponent flowerPotBaseComponent;

    /**
     * Constructs the composed flower pot using the supplied component
     * providers. Superclass construction injects the inherited base and
     * declared component fields before invoking completion hooks; provider or
     * injection failures abort construction without rollback.
     */
    public BlockEntityFlowerPotImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
