package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockCopperGolemStatueBehavior} exposes general block operations and oxidation
 * controls for copper golem statue blocks. The inherited component contracts provide
 * oxidation level, wax state, and corresponding block type lookups, so variants share those
 * operations through one behavior type.
 */
public interface BlockCopperGolemStatueBehavior extends BlockBehavior, BlockOxidationComponent {
}
