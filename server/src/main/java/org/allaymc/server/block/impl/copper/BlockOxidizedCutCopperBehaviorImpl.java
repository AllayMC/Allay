package org.allaymc.server.block.impl.copper;

import java.util.List;
import org.allaymc.api.block.interfaces.copper.BlockOxidizedCutCopperBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOxidizedCutCopperBehaviorImpl extends BlockBehaviorImpl implements BlockOxidizedCutCopperBehavior {
    public BlockOxidizedCutCopperBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
