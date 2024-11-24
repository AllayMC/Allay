package org.allaymc.server.block.impl.glass;

import java.util.List;
import org.allaymc.api.block.interfaces.glass.BlockTintedGlassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockTintedGlassBehaviorImpl extends BlockBehaviorImpl implements BlockTintedGlassBehavior {
    public BlockTintedGlassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
