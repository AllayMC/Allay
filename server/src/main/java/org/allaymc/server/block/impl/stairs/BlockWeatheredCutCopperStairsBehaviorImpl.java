package org.allaymc.server.block.impl.stairs;

import java.util.List;
import org.allaymc.api.block.interfaces.stairs.BlockWeatheredCutCopperStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWeatheredCutCopperStairsBehaviorImpl extends BlockBehaviorImpl implements BlockWeatheredCutCopperStairsBehavior {
    public BlockWeatheredCutCopperStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
