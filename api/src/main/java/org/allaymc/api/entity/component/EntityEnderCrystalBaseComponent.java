package org.allaymc.api.entity.component;

/**
 * {@code EntityEnderCrystalBaseComponent} exposes the visible-base setting of
 * an ender crystal. The setting controls the crystal's displayed base
 * independently of its common entity location and lifecycle, and entity state
 * encoding uses it when preparing the crystal metadata for viewers.
 *
 * @author daoge_cmd
 */
public interface EntityEnderCrystalBaseComponent extends EntityBaseComponent {

    /**
     * Returns whether the ender crystal's displayed base is visible.
     */
    boolean isBaseVisible();

    /**
     * Sets the visible-base flag and broadcasts updated crystal metadata to
     * current viewers. The server stores the value even when unchanged, and
     * subsequent NBT serialization includes that stored visibility setting.
     */
    void setBaseVisibility(boolean value);
}
