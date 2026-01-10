package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockComparatorBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockComparatorBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityComparator> blockEntityHolderComponent;

    public BlockComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
