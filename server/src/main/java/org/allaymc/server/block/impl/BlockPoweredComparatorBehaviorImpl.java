package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockPoweredComparatorBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPoweredComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockPoweredComparatorBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityComparator> blockEntityHolderComponent;

    public BlockPoweredComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
