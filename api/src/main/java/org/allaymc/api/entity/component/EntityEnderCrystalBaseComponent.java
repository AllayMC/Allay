package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityEnderCrystalBaseComponent extends EntityBaseComponent {

    /**
     * Checks whether the base of the Ender Crystal is visible.
     *
     * @return {@code true} if the base is visible, {@code false} otherwise
     */
    boolean isBaseVisible();

    /**
     * Sets the visibility of the base of the ender crystal.
     *
     * @param value {@code true} to make the base visible, {@code false} to hide it
     */
    void setBaseVisibility(boolean value);
}
