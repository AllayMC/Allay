package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockWaxedWeatheredCutCopperStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWaxedWeatheredCutCopperStairsBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedWeatheredCutCopperStairsBehavior {
    public BlockWaxedWeatheredCutCopperStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
