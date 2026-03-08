package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityCauldron;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCauldronBehaviorImpl extends BlockBehaviorImpl implements BlockCauldronBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityCauldron> blockEntityHolderComponent;

    public BlockCauldronBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
