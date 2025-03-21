package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockSignBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntitySign;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSignBehaviorImpl extends BlockBehaviorImpl implements BlockSignBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntitySign> blockEntityHolderComponent;

    public BlockSignBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
