package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockWaxedOxidizedCutCopperStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWaxedOxidizedCutCopperStairsBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedOxidizedCutCopperStairsBehavior {
    public BlockWaxedOxidizedCutCopperStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
