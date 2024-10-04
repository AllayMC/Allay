package org.allaymc.api.entity.component;

public interface EntityUndeadComponent extends EntityBaseComponent {
    /**
     * Check if the entity will catch fire under sunlight.
     *
     * @return {@code true} if the entity will catch fire under sunlight, otherwise {@code false}.
     */
    boolean getCatchesFireUnderSunlight();

    /**
     * Set if the entity will catch fire under sunlight.
     *
     * @param catchesFireUnderSunlight whether the entity will catch fire under sunlight.
     */
    void setCatchesFireUnderSunlight(boolean catchesFireUnderSunlight);
}
