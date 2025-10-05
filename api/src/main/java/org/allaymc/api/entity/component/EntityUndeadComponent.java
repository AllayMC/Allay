package org.allaymc.api.entity.component;

public interface EntityUndeadComponent extends EntityComponent {
    /**
     * Check if sunlight will ignite the entity.
     *
     * @return {@code true} if the entity will be ignited by sunlight, {@code false} otherwise
     */
    default boolean ignitedBySunlight() {
        return true;
    }
}
