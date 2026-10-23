package org.allaymc.api.player;

/**
 * {@code HudElement} identifies a client HUD element controlled through
 * {@code Player.setHudElementVisibility}. These constants select individual
 * presentation elements for hiding or resetting; their appearance and placement
 * depend on the client's chosen interface, device layout, and gameplay state.
 *
 * @author daoge_cmd
 */
public enum HudElement {
    /**
     * Player model and equipment preview.
     */
    PAPER_DOLL,
    /**
     * Player armor status display.
     */
    ARMOR,
    /**
     * Client hint and tip display.
     */
    TOOL_TIPS,
    /**
     * Touchscreen input control overlay.
     */
    TOUCH_CONTROLS,
    /**
     * Center-screen aiming reticle display.
     */
    CROSSHAIR,
    /**
     * Player hotbar item display.
     */
    HOTBAR,
    /**
     * Player health status display.
     */
    HEALTH,
    /**
     * Player experience progress display.
     */
    EXPERIENCE_BAR,
    /**
     * Player hunger status display.
     */
    FOOD_BAR,
    /**
     * Player remaining-air status display.
     */
    AIR_SUPPLY_BAR,
    /**
     * Ridden entity health display.
     */
    VEHICLE_HEALTH,
    /**
     * Active status effect icons.
     */
    EFFECTS,
    /**
     * Selected item name display.
     */
    ITEM_TEXT
}
