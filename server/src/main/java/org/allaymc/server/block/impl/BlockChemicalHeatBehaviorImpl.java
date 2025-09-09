package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChemicalHeatBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChemicalHeatBehaviorImpl extends BlockBehaviorImpl implements BlockChemicalHeatBehavior {
    public BlockChemicalHeatBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
