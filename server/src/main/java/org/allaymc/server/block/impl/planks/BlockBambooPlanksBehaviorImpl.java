package org.allaymc.server.block.impl.planks;

import java.util.List;
import org.allaymc.api.block.interfaces.planks.BlockBambooPlanksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBambooPlanksBehaviorImpl extends BlockBehaviorImpl implements BlockBambooPlanksBehavior {
    public BlockBambooPlanksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
