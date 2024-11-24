package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBarrelBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBarrelBehaviorImpl extends BlockBehaviorImpl implements BlockBarrelBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityBarrel> blockEntityHolderComponent;

    public BlockBarrelBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
