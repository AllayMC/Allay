package org.allaymc.api.world.sound;

/**
 * SimpleSound contains all sounds that do not require additional parameters.
 *
 * @author daoge_cmd
 */
public enum SimpleSound implements Sound {
    /* Block */

    // GLASS_BREAK is a sound played when a glass block or item is broken.
    GLASS_BREAK,
    // FIZZ is a sound sent when a lava block and a water block interact with each other in a way that one of them turns into a solid block.
    FIZZ,
    // ANVIL_LAND is played when an anvil lands on the ground.
    ANVIL_LAND,
    // ANVIL_USE is played when an anvil is used.
    ANVIL_USE,
    // ANVIL_BREAK is played when an anvil is broken.
    ANVIL_BREAK,
    // CHEST_OPEN is played when a chest is opened.
    CHEST_OPEN,
    // CHEST_CLOSE is played when a chest is closed.
    CHEST_CLOSE,
    // ENDER_CHEST_OPEN is played when an ender chest is opened.
    ENDER_CHEST_OPEN,
    // ENDER_CHEST_CLOSE is played when an ender chest is closed.
    ENDER_CHEST_CLOSE,
    // SHULKER_BOX_OPEN is played when a shulker box is opened.
    SHULKER_BOX_OPEN,
    // SHULKER_BOX_CLOSE is played when a shulker box is closed.
    SHULKER_BOX_CLOSE,
    // FENCE_GATE_OPEN is played when opening a fence gate
    FENCE_GATE_OPEN,
    // FENCE_GATE_CLOSE is played when closing a fence gate
    FENCE_GATE_CLOSE,
    // BARREL_OPEN is played when a barrel is opened.
    BARREL_OPEN,
    // BARREL_CLOSE is played when a barrel is closed.
    BARREL_CLOSE,
    // DENY is a sound played when a block is placed or broken above a 'Deny' block from Education edition.
    DENY,
    // DOOR_CRASH is a sound played when a door is forced open.
    DOOR_CRASH,
    // CLICK is a clicking sound.
    CLICK,
    // IGNITE is a sound played when using a flint & steel.
    IGNITE,
    // TNT is a sound played when TNT is ignited.
    TNT,
    // FIRE_EXTINGUISH is a sound played when a fire is extinguished.
    FIRE_EXTINGUISH,
    // MUSIC_DISC_END is a sound played when a music disc has stopped playing in a jukebox.
    MUSIC_DISC_END,
    // ITEM_ADD is a sound played when an item is added to an item frame or campfire.
    ITEM_ADD,
    // ITEM_FRAME_REMOVE is a sound played when an item is removed from an item frame.
    ITEM_FRAME_REMOVE,
    // ITEM_FRAME_ROTATE is a sound played when an item frame's item is rotated.
    ITEM_FRAME_ROTATE,
    // FURNACE_CRACKLE is a sound played every one to five seconds from a furnace.
    FURNACE_CRACKLE,
    // CAMPFIRE_CRACKLE is a sound played every one to five seconds from a campfire.
    CAMPFIRE_CRACKLE,
    // BLAST_FURNACE_CRACKLE is a sound played every one to five seconds from a blast furnace.
    BLAST_FURNACE_CRACKLE,
    // SMOKER_CRACKLE is a sound played every one to five seconds from a smoker.
    SMOKER_CRACKLE,
    // COMPOSTER_EMPTY is a sound played when a composter has been emptied.
    COMPOSTER_EMPTY,
    // COMPOSTER_FILL is a sound played when a composter has been filled, but not gone up a layer.
    COMPOSTER_FILL,
    // COMPOSTER_FILL_LAYER is a sound played when a composter has been filled and gone up a layer.
    COMPOSTER_FILL_LAYER,
    // COMPOSTER_READY is a sound played when a composter has produced bone meal and is ready to be collected.
    COMPOSTER_READY,
    // POTION_BREWED is a sound played when a potion is brewed.
    POTION_BREWED,
    // LECTERN_BOOK_PLACE is a sound played when a book is placed in a lectern.
    LECTERN_BOOK_PLACE,
    // WAXED is a sound played when a copper block or sign is waxed.
    WAXED,
    // WAX_REMOVED is a sound played when wax is removed from a copper block or sign.
    WAX_REMOVED,
    // WAXED_SIGN_FAILED_INTERACTION is a sound played when a player tries to interact with a waxed sign.
    WAXED_SIGN_FAILED_INTERACTION,
    // GLOW_INK_SAC_USED is a sound played when a player used glow ink sac in a sign.
    GLOW_INK_SAC_USED,
    // COPPER_SCRAPED is a sound played when a player scrapes a copper block to reduce its oxidation level.
    COPPER_SCRAPED,
    // DECORATED_POT_INSERT_FAILED is a sound played when an item fails to be inserted into a decorated pot.
    DECORATED_POT_INSERT_FAILED,
    // SPONGE_ABSORB is a sound played when sponge block absorbing water around.
    SPONGE_ABSORB,
    // CHORUS_FLOWER_GROW is a sound played when chorus flower grow.
    CHORUS_FLOWER_GROW,
    // END_PORTAL_FRAME_FILLED is a sound player when player fill an end portal.
    END_PORTAL_FRAME_FILLED,
    // END_PORTAL_SPAWN is a sound when end portal is activated.
    END_PORTAL_SPAWN,

    /* Item */

    // ITEM_BREAK is a sound played when an item in the inventory is broken, such as when a tool reaches 0 durability and breaks.
    ITEM_BREAK,
    // ITEM_THROW is a sound played when a player throws an item, such as a snowball.
    ITEM_THROW,
    // BOW_SHOOT is a sound played when a bow is shot.
    BOW_SHOOT,
    // CROSSBOW_SHOOT is a sound played when a crossbow is shot.
    CROSSBOW_SHOOT,
    // ARROW_HIT is a sound played when an arrow hits ground.
    ARROW_HIT,
    // TELEPORT is a sound played upon teleportation of an enderman, or teleportation of a player by an ender pearl or a chorus fruit.
    TELEPORT,
    // USE_SPYGLASS is a sound played when a player uses a spyglass.
    USE_SPYGLASS,
    // STOP_USING_SPYGLASS is a sound played when a player stops using a spyglass.
    STOP_USING_SPYGLASS,
    // FIRE_CHARGE is a sound played when a player lights a block on fire with a fire charge, or when a dispenser or a blaze shoots a fireball.
    FIRE_CHARGE,
    // TOTEM is a sound played when a player uses a totem.
    TOTEM,
    // MACE_SMASH_AIR is a sound played when a player hit an entity in air using mace
    MACE_SMASH_AIR,
    // MACE_SMASH_AIR is a sound played when a player hit an entity on the ground using mace
    MACE_SMASH_GROUND,
    // MACE_SMASH_AIR is a sound played when a player hit an entity heavily on the ground using mace
    MACE_SMASH_HEAVY_GROUND,

    /* Entity */

    // DROWNING is a sound played when an entity is drowning in water.
    DROWNING,
    // BURNING is a sound played when an entity is on fire.
    BURNING,
    // BURP is a sound played when a player finishes eating an item.
    BURP,
    // POP is a sound played when a chicken lays an egg.
    POP,
    // EXPLOSION is a sound played when an explosion happens, such as from a creeper or TNT.
    EXPLOSION,
    // THUNDER is a sound played when lightning strikes the ground.
    THUNDER,
    // LEVEL_UP is a sound played for a player whenever they level up.
    LEVEL_UP,
    // EXPERIENCE is a sound played whenever a player picks up an XP orb.
    EXPERIENCE,
    // GHAST_WARNING is a sound played when a ghast is ready to attack.
    GHAST_WARNING,
    // GHAST_SHOOT is a sound played when a ghast shoots a fire charge.
    GHAST_SHOOT,
    // FIREWORK_LAUNCH is a sound played when a firework is launched.
    FIREWORK_LAUNCH,
    // FIREWORK_HUGE_BLAST is a sound played when a huge sphere firework explodes.
    FIREWORK_HUGE_BLAST,
    // FIREWORK_BLAST is a sound played when a small sphere firework explodes.
    FIREWORK_BLAST,
    // FIREWORK_FLICKER is a sound played when a firework explodes and should flicker.
    FIREWORK_FLICKER,
    // PAINTING_PLACE is a sound played when a painting is placed.
    PAINTING_PLACE,
    // WIND_CHARGE_BURST is a sound played when a player wind charge explodes.
    WIND_CHARGE_BURST,
    // BREEZE_WIND_CHARGE_BURST is a sound played when a breeze wind charge explodes.
    BREEZE_WIND_CHARGE_BURST
}

