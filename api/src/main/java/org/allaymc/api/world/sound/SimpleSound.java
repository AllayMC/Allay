package org.allaymc.api.world.sound;

/**
 * {@code SimpleSound} identifies sound effects whose descriptors need no
 * additional parameters. The runtime maps each constant to a client sound or
 * level event, while callers choose the effect position; material, pitch, and
 * other parameterized sounds use separate records.
 *
 * @author daoge_cmd
 */
public enum SimpleSound implements Sound {
    // These block-effect constants select events without a separate material
    // payload; interactions requiring a block state use dedicated sound records.

    /**
     * Sound emitted when a glass block or item is broken.
     */
    GLASS_BREAK,
    /**
     * Sound emitted when a lava block and a water block interact with each
     * other in a way that one of them turns into a solid block.
     */
    FIZZ,
    /**
     * Sound emitted when an anvil lands on the ground.
     */
    ANVIL_LAND,
    /**
     * Sound emitted when an anvil is used.
     */
    ANVIL_USE,
    /**
     * Sound emitted when an anvil is broken.
     */
    ANVIL_BREAK,
    /**
     * Sound emitted when a chest is opened.
     */
    CHEST_OPEN,
    /**
     * Sound emitted when a chest is closed.
     */
    CHEST_CLOSE,
    /**
     * Sound emitted when an ender chest is opened.
     */
    ENDER_CHEST_OPEN,
    /**
     * Sound emitted when an ender chest is closed.
     */
    ENDER_CHEST_CLOSE,
    /**
     * Sound emitted when a shulker box is opened.
     */
    SHULKER_BOX_OPEN,
    /**
     * Sound emitted when a shulker box is closed.
     */
    SHULKER_BOX_CLOSE,
    /**
     * Sound emitted when opening a fence gate.
     */
    FENCE_GATE_OPEN,
    /**
     * Sound emitted when closing a fence gate.
     */
    FENCE_GATE_CLOSE,
    /**
     * Sound emitted when a barrel is opened.
     */
    BARREL_OPEN,
    /**
     * Sound emitted when a barrel is closed.
     */
    BARREL_CLOSE,
    /**
     * Sound emitted when a block is placed or broken above a 'Deny' block from
     * Education edition.
     */
    DENY,
    /**
     * Sound emitted when a door is forced open.
     */
    DOOR_CRASH,
    /**
     * A clicking sound.
     */
    CLICK,
    /**
     * Sound emitted when a dispenser or dropper dispenses an item.
     */
    BLOCK_CLICK,
    /**
     * Sound emitted when a dispenser or dropper fails to dispense.
     */
    BLOCK_CLICK_FAIL,
    /**
     * Sound emitted when using a flint and steel.
     */
    IGNITE,
    /**
     * Sound emitted when TNT is ignited.
     */
    TNT,
    /**
     * Sound emitted when a fire is extinguished.
     */
    FIRE_EXTINGUISH,
    /**
     * Sound emitted when a music disc has stopped playing in a jukebox.
     */
    MUSIC_DISC_END,
    /**
     * Sound emitted when an item is added to an item frame or campfire.
     */
    ITEM_ADD,
    /**
     * Sound emitted when an item is removed from an item frame.
     */
    ITEM_FRAME_REMOVE,
    /**
     * Sound emitted when an item frame's item is rotated.
     */
    ITEM_FRAME_ROTATE,
    /**
     * Furnace operation crackling sound.
     */
    FURNACE_CRACKLE,
    /**
     * Campfire operation crackling sound.
     */
    CAMPFIRE_CRACKLE,
    /**
     * Blast furnace operation crackling sound.
     */
    BLAST_FURNACE_CRACKLE,
    /**
     * Smoker operation crackling sound.
     */
    SMOKER_CRACKLE,
    /**
     * Sound emitted when a composter has been emptied.
     */
    COMPOSTER_EMPTY,
    /**
     * Sound emitted when a composter has been filled, but not gone up a layer.
     */
    COMPOSTER_FILL,
    /**
     * Sound emitted when a composter has been filled and gone up a layer.
     */
    COMPOSTER_FILL_LAYER,
    /**
     * Sound emitted when a composter has produced bone meal and is ready to be
     * collected.
     */
    COMPOSTER_READY,
    /**
     * Sound emitted when a potion is brewed.
     */
    POTION_BREWED,
    /**
     * Sound emitted when a book is placed in a lectern.
     */
    LECTERN_BOOK_PLACE,
    /**
     * Sound emitted when a copper block or sign is waxed.
     */
    WAXED,
    /**
     * Sound emitted when wax is removed from a copper block or sign.
     */
    WAX_REMOVED,
    /**
     * Sound emitted when a player tries to interact with a waxed sign.
     */
    WAXED_SIGN_FAILED_INTERACTION,
    /**
     * Sound emitted when a player used glow ink sac in a sign.
     */
    GLOW_INK_SAC_USED,
    /**
     * Sound emitted when a player scrapes a copper block to reduce its
     * oxidation level.
     */
    COPPER_SCRAPED,
    /**
     * Sound emitted when an item fails to be inserted into a decorated pot.
     */
    DECORATED_POT_INSERT_FAILED,
    /**
     * Sponge water absorption sound.
     */
    SPONGE_ABSORB,
    /**
     * Chorus flower growth sound.
     */
    CHORUS_FLOWER_GROW,
    /**
     * End portal frame filling sound.
     */
    END_PORTAL_FRAME_FILLED,
    /**
     * Sound emitted when end portal is activated.
     */
    END_PORTAL_SPAWN,
    /**
     * Sound emitted when a piston pushes.
     */
    PISTON_PUSH,
    /**
     * Sound emitted when a piston pulls.
     */
    PISTON_PULL,
    /**
     * Sound emitted when a redstone component is powered on.
     */
    POWER_ON,
    /**
     * Sound emitted when a redstone component is powered off.
     */
    POWER_OFF,
    /**
     * Sound emitted when a block is activated, such as shelf.
     */
    ACTIVATED,
    /**
     * Sound emitted when a block is deactivated, such as shelf.
     */
    DEACTIVATED,
    /**
     * Sound emitted when a cauldron is filled with water.
     */
    CAULDRON_FILL_WATER,
    /**
     * Sound emitted when water is taken from a cauldron.
     */
    CAULDRON_TAKE_WATER,
    /**
     * Sound emitted when a cauldron is filled with lava.
     */
    CAULDRON_FILL_LAVA,
    /**
     * Sound emitted when lava is taken from a cauldron.
     */
    CAULDRON_TAKE_LAVA,
    /**
     * Sound emitted when a cauldron is filled with powder snow.
     */
    CAULDRON_FILL_POWDER_SNOW,
    /**
     * Sound emitted when powder snow is taken from a cauldron.
     */
    CAULDRON_TAKE_POWDER_SNOW,
    /**
     * Sound emitted when a potion is added to a cauldron.
     */
    CAULDRON_FILL_POTION,
    /**
     * Sound emitted when a potion is taken from a cauldron.
     */
    CAULDRON_TAKE_POTION,
    /**
     * Sound emitted when dye is added to a cauldron.
     */
    CAULDRON_ADD_DYE,
    /**
     * Sound emitted when armor is dyed in a cauldron.
     */
    CAULDRON_DYE_ARMOR,
    /**
     * Sound emitted when armor is cleaned in a cauldron.
     */
    CAULDRON_CLEAN_ARMOR,
    /**
     * Sound emitted when a banner pattern is removed in a cauldron.
     */
    CAULDRON_CLEAN_BANNER,
    /**
     * Sound emitted when different potions are mixed in a cauldron, causing it
     * to explode.
     */
    CAULDRON_EXPLODE,
    /**
     * Sound emitted when a respawn anchor is charged with glowstone.
     */
    RESPAWN_ANCHOR_CHARGE,
    /**
     * Sound emitted when a player sets their spawn point at a respawn anchor.
     */
    RESPAWN_ANCHOR_SET_SPAWN,
    /**
     * Sound emitted when a respawn anchor loses a charge upon player respawn.
     */
    RESPAWN_ANCHOR_DEPLETE,
    /**
     * Sound emitted when a single item is swapped in a shelf.
     */
    SHELF_SWAP_SINGLE,
    /**
     * Sound emitted when multiple items are swapped in a shelf.
     */
    SHELF_SWAP_MULTI,

    // These item-effect constants select parameter-free use, breakage, and
    // projectile events; callers supply the effect position when broadcasting.

    /**
     * Sound emitted when an item in the inventory is broken, such as when a
     * tool reaches zero durability and breaks.
     */
    ITEM_BREAK,
    /**
     * Sound emitted when a player throws an item, such as a snowball.
     */
    ITEM_THROW,
    /**
     * Sound emitted when a bow is shot.
     */
    BOW_SHOOT,
    /**
     * Sound emitted when a crossbow is shot.
     */
    CROSSBOW_SHOOT,
    /**
     * Sound emitted when an arrow hits ground.
     */
    ARROW_HIT,
    /**
     * Upon teleportation of an enderman, or teleportation of a player by an
     * ender pearl or a chorus fruit.
     */
    TELEPORT,
    /**
     * Sound emitted when a player uses a spyglass.
     */
    USE_SPYGLASS,
    /**
     * Sound emitted when a player stops using a spyglass.
     */
    STOP_USING_SPYGLASS,
    /**
     * Sound emitted when a player lights a block on fire with a fire charge,
     * or when a dispenser or a blaze shoots a fireball.
     */
    FIRE_CHARGE,
    /**
     * Sound emitted when a player uses a totem.
     */
    TOTEM,
    /**
     * Sound emitted when a player hit an entity in air using mace.
     */
    MACE_SMASH_AIR,
    /**
     * Mace smash sound for a target on the ground.
     */
    MACE_SMASH_GROUND,
    /**
     * Heavy mace smash sound for a target on the ground.
     */
    MACE_SMASH_HEAVY_GROUND,
    /**
     * Sound emitted when a shield successfully blocks damage.
     */
    SHIELD_BLOCK,
    /**
     * Sound emitted when a trident is thrown.
     */
    TRIDENT_THROW,
    /**
     * Sound emitted when a trident hits an entity.
     */
    TRIDENT_HIT,
    /**
     * Sound emitted when a trident hits the ground.
     */
    TRIDENT_HIT_GROUND,
    /**
     * Sound emitted when a trident with loyalty returns to the player.
     */
    TRIDENT_RETURN,
    /**
     * Sound emitted when a trident with channeling summons lightning.
     */
    TRIDENT_THUNDER,

    // These entity-effect constants select parameter-free gameplay events,
    // while the caller controls which viewers receive the positional sound.

    /**
     * Sound emitted when an entity is drowning in water.
     */
    DROWNING,
    /**
     * Sound emitted when an entity is on fire.
     */
    BURNING,
    /**
     * Sound emitted when a player finishes eating an item.
     */
    BURP,
    /**
     * Arrow-pickup popping sound in the current runtime encoder.
     */
    POP,
    /**
     * Sound emitted when an explosion happens, such as from a creeper or TNT.
     */
    EXPLOSION,
    /**
     * Sound emitted when lightning strikes the ground.
     */
    THUNDER,
    /**
     * For a player whenever they level up.
     */
    LEVEL_UP,
    /**
     * Whenever a player picks up an XP orb.
     */
    EXPERIENCE,
    /**
     * Sound emitted when a ghast is ready to attack.
     */
    GHAST_WARNING,
    /**
     * Sound emitted when a ghast shoots a fire charge.
     */
    GHAST_SHOOT,
    /**
     * Sound emitted when a firework is launched.
     */
    FIREWORK_LAUNCH,
    /**
     * Sound emitted when a huge sphere firework explodes.
     */
    FIREWORK_HUGE_BLAST,
    /**
     * Sound emitted when a small sphere firework explodes.
     */
    FIREWORK_BLAST,
    /**
     * Sound emitted when a firework explodes and should flicker.
     */
    FIREWORK_FLICKER,
    /**
     * Sound emitted when a painting is placed.
     */
    PAINTING_PLACE,
    /**
     * Sound emitted when a player wind charge explodes.
     */
    WIND_CHARGE_BURST,
    /**
     * Sound emitted when a breeze wind charge explodes.
     */
    BREEZE_WIND_CHARGE_BURST,
    /**
     * Sound emitted when an armor stand is placed.
     */
    ARMOR_STAND_PLACE,
    /**
     * Sound emitted when an armor stand is hit.
     */
    ARMOR_STAND_HIT,
    /**
     * Sound emitted when an armor stand is broken.
     */
    ARMOR_STAND_BREAK,
    /**
     * Sound emitted when an armor stand drops from a height to the ground.
     */
    ARMOR_STAND_LAND,
    /**
     * Sound emitted when a big dripleaf tilts down.
     */
    BIG_DRIPLEAF_TILT_DOWN,
    /**
     * Sound emitted when a big dripleaf tilts back up.
     */
    BIG_DRIPLEAF_TILT_UP,
    /**
     * Sound emitted when a bell is rung.
     */
    BELL_HIT,
    /**
     * Sound emitted when a player gets a milk bucket from a cow.
     */
    MILKING,
    /**
     * Sound emitted when a chicken lays an egg.
     */
    EGG_LAY,
}

