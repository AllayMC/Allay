package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockFlowerPotBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockFlowerPotBehaviorImpl} composes ordinary block behavior with flower-pot entity
 * access. Component construction selects the holder from supplied providers, and generated
 * delegation forwards holder operations to the injected instance used for pot contents and
 * block-entity lifecycle.
 */
public class BlockFlowerPotBehaviorImpl extends BlockBehaviorImpl implements BlockFlowerPotBehavior {

    /**
     * {@code blockEntityHolderComponent} holds the injected flower-pot entity delegate.
     * Component initialization requires one matching supplied component before generated
     * holder methods can forward calls to it.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityFlowerPot> blockEntityHolderComponent;

    /**
     * Creates the composed flower-pot behavior from the supplied component providers.
     * Initialization must resolve the inherited delegates and one flower-pot holder; provider
     * or injection failures abort construction without undoing earlier side effects.
     */
    public BlockFlowerPotBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
