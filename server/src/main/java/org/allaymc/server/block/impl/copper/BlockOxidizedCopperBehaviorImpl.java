package org.allaymc.server.block.impl.copper;

import java.util.List;
import org.allaymc.api.block.interfaces.copper.BlockOxidizedCopperBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOxidizedCopperBehaviorImpl extends BlockBehaviorImpl implements BlockOxidizedCopperBehavior {
    public BlockOxidizedCopperBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
