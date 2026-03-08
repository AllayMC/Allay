package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockDecoratedPotBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityDecoratedPot;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDecoratedPotBehaviorImpl extends BlockBehaviorImpl implements BlockDecoratedPotBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityDecoratedPot> blockEntityHolderComponent;

    public BlockDecoratedPotBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
