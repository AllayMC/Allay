package org.allaymc.api.player;

/**
 * HudElement contains a set of HUD elements in the game that can either be hidden or shown.
 *
 * @author daoge_cmd
 */
public enum HudElement {
    /**
     * The element that shows the player's paper doll, which is a visual representation of the
     * player's character model and equipment, as well as any currently played animations. It is
     * located in the top left corner of the screen.
     */
    PAPER_DOLL,
    /**
     * The element that shows the player's armour level, sitting either above the hotbar or at the
     * top of the screen on in non-classic views.
     */
    ARMOR,
    /**
     * The element that shows useful hints and tips to the player, such as how to use items or how
     * to perform certain actions in the game. These tips are displayed at the top right of the
     * screen.
     */
    TOOL_TIPS,
    /**
     * The element that shows the touch controls on the screen, which is used for touch-based
     * devices.
     */
    TOUCH_CONTROLS,
    /**
     * The element that shows the crosshair in the middle of the screen, which is used for aiming
     * and targeting entities or blocks.
     */
    CROSSHAIR,
    /**
     * The element that shows all the items in the player's hotbar, located at the bottom of the
     * screen.
     */
    HOTBAR,
    /**
     * The element that shows the player's health bar, sitting either above the hotbar or at the
     * top of the screen on in non-classic views.
     */
    HEALTH,
    /**
     * The element that shows the player's experience bar. It is always located just above the
     * hotbar.
     */
    EXPERIENCE_BAR,
    /**
     * The element that shows the player's hunger bar, which indicates how hungry the player is and
     * how much food they need to consume to restore their hunger. It is located either above the
     * hotbar or at the top of the screen on in non-classic views.
     */
    FOOD_BAR,
    /**
     * The element that shows the player's air bubbles, which indicate how much air the player has
     * left when underwater. It is located either above the hotbar or at the top of the screen on
     * in non-classic views. It is only visible when the player is underwater or they are
     * regenerating air after being underwater.
     */
    AIR_SUPPLY_BAR,
    /**
     * The element that shows the health of the player's vehicle, which replaces the player's own
     * health bar when riding a horse/other entity with health.
     */
    VEHICLE_HEALTH,
    /**
     * The element that shows the icons of the currently active status effects, located on the
     * right side of the screen.
     */
    EFFECTS,
    /**
     * The element that shows the text of the item currently held in the player's hand, which is
     * displayed just above the hotbar when switching to a new item.
     */
    ITEM_TEXT
}
