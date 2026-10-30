package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;

/**
 * {@code BlockCopperBarsBehavior} exposes general block operations and oxidation controls
 * for copper bars blocks. The inherited component contracts provide oxidation level, wax
 * state, and corresponding block type lookups, so variants share those operations through
 * one behavior type.
 */
public interface BlockCopperBarsBehavior extends BlockBehavior, BlockOxidationComponent {
}
