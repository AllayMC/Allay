package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityLightningBoltBaseComponent extends EntityBaseComponent {
    /**
     * Check if the lightning bolt has effects (damage, fire, etc.).
     *
     * @return {@code true} if the lightning bolt has effects, {@code false} otherwise.
     */
    boolean isEffect();

    /**
     * Set whether the lightning bolt has effects.
     *
     * @param effect Whether the lightning bolt should have effects
     */
    void setEffect(boolean effect);
}
