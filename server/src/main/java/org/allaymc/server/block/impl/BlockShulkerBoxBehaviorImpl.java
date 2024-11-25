package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockShulkerBoxBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockShulkerBoxBehaviorImpl extends BlockBehaviorImpl implements BlockShulkerBoxBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityShulkerBox> blockEntityHolderComponent;

    public BlockShulkerBoxBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
