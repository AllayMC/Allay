package org.allaymc.api.block.data;

/**
 * {@code OxidationLevel} identifies the successive weathering stages used by
 * oxidizable blocks. The constants run from unaffected material to fully oxidized
 * material, allowing behavior components to select corresponding block variants
 * while retaining a common description of the stage.
 *
 * @author IWareQ
 */
public enum OxidationLevel {
    /**
     * Initial unoxidized material stage.
     */
    UNAFFECTED,
    /**
     * First exposed weathering stage.
     */
    EXPOSED,
    /**
     * Intermediate weathered material stage.
     */
    WEATHERED,
    /**
     * Final fully oxidized stage.
     */
    OXIDIZED
}
