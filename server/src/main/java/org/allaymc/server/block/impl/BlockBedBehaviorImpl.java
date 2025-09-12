package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBedBehaviorImpl extends BlockBehaviorImpl implements BlockBedBehavior {

    @Delegate
    protected BlockEntityHolderComponent<BlockEntityBed> blockEntityHolderComponent;

    public BlockBedBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
