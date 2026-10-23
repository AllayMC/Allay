package org.allaymc.api.entity.component;

/**
 * {@code EntityUndeadComponent} identifies entities subject to undead-specific
 * sunlight handling. The default permits sunlight ignition; the server checks
 * environmental exposure, fire resistance, and helmet protection before
 * applying fire, so this flag alone does not make every daylight tick ignite
 * the entity.
 */
public interface EntityUndeadComponent extends EntityComponent {
    /**
     * Returns whether sunlight ignition is enabled, with the default enabled.
     */
    default boolean ignitedBySunlight() {
        return true;
    }
}
