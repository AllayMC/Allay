package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockUnpoweredComparatorBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockUnpoweredComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockUnpoweredComparatorBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityComparator> blockEntityHolderComponent;

    public BlockUnpoweredComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
