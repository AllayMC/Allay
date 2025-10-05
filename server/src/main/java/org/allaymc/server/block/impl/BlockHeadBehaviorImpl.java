package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockHeadBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityHead;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockHeadBehaviorImpl extends BlockBehaviorImpl implements BlockHeadBehavior {
    @Delegate
    protected BlockBlockEntityHolderComponent<BlockEntityHead> blockEntityHolderComponent;
    
    public BlockHeadBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
