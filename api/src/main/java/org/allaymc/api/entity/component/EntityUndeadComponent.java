package org.allaymc.api.entity.component;

public interface EntityUndeadComponent extends EntityComponent {
    /**
     * Check if the entity will catch fire under sunlight.
     *
     * @return {@code true} if the entity will catch fire under sunlight, otherwise {@code false}.
     */
    default boolean getCatchesFireUnderSunlight() { return true; };
}
