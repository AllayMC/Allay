package org.allaymc.api.world.dimension;

/**
 * {@code DimensionTypes} exposes the three built-in dimension type references populated during
 * registry initialization. Their numeric identities remain the vanilla
 * identities, while configured height ranges can differ from vanilla defaults;
 * consumers should query each registered type for its effective bounds.
 *
 * @author daoge_cmd
 */
public final class DimensionTypes {
    /**
     * {@code OVERWORLD} holds the built-in Overworld type after registry
     * initialization. This reference is {@code null} beforehand; reassignment
     * does not update the registry.
     */
    public static DimensionType OVERWORLD;
    /**
     * {@code NETHER} holds the built-in Nether type after registry initialization.
     * This reference is {@code null} beforehand; reassignment does not update
     * the registry.
     */
    public static DimensionType NETHER;
    /**
     * {@code THE_END} holds the built-in End type after registry initialization.
     * This reference is {@code null} beforehand; reassignment does not update
     * the registry.
     */
    public static DimensionType THE_END;

    /**
     * Prevents instances of this static built-in dimension reference catalog.
     */
    private DimensionTypes() {
    }
}
