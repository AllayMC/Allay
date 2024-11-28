package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMaterialReducerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMaterialReducerBehaviorImpl extends BlockBehaviorImpl implements BlockMaterialReducerBehavior {
    public BlockMaterialReducerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
