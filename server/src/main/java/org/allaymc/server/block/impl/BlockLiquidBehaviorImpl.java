package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLiquidBehaviorImpl extends BlockBehaviorImpl implements BlockLiquidBehavior {

    public BlockLiquidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    public BlockLiquidBaseComponent getBaseComponent() {
        return (BlockLiquidBaseComponent) super.getBaseComponent();
    }
}
