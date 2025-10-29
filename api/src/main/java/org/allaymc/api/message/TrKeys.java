package org.allaymc.api.message;

import org.allaymc.api.annotation.MinecraftVersionSensitive;

@MinecraftVersionSensitive
public interface TrKeys {
    /**
     * Freed %1 MB of memory
     */
    String ALLAY_COMMAND_GC_COMPLETED = "allay:command.gc.completed";

    /**
     * Perform a JVM garbage collection immediately
     */
    String ALLAY_COMMAND_GC_DESCRIPTION = "allay:command.gc.description";

    /**
     * This command can only be executed by an entity!
     */
    String ALLAY_COMMAND_GENERIC_SENDER_NOTENTITY = "allay:command.generic.sender.notEntity";

    /**
     * This command can only be executed by a player!
     */
    String ALLAY_COMMAND_GENERIC_SENDER_NOTPLAYER = "allay:command.generic.sender.notPlayer";

    /**
     * This command can only be executed by the server!
     */
    String ALLAY_COMMAND_GENERIC_SENDER_NOTSERVER = "allay:command.generic.sender.notServer";

    /**
     * Manage plugins
     */
    String ALLAY_COMMAND_PLUGIN_DESCRIPTION = "allay:command.plugin.description";

    /**
     * Get the status of the server
     */
    String ALLAY_COMMAND_STATUS_DESCRIPTION = "allay:command.status.description";

    /**
     * Show TPS or MSPT of the current world
     */
    String ALLAY_COMMAND_TPS_DESCRIPTION = "allay:command.tps.description";

    /**
     * Query server version information
     */
    String ALLAY_COMMAND_VERSION_DESCRIPTION = "allay:command.version.description";

    /**
     * This server is running §b%1§r §a%2§r with §ballay-api§r §a%3§r. Supports version §a%4§r (protocol version §a%5§r)
     */
    String ALLAY_COMMAND_VERSION_OUTPUT = "allay:command.version.output";

    /**
     * Nether Generator Preset
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_NETHER = "allay:command.world.create.dropdown.generatorpreset.nether";

    /**
     * Overworld Generator Preset
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_OVERWORLD = "allay:command.world.create.dropdown.generatorpreset.overworld";

    /**
     * The End Generator Preset
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_THEEND = "allay:command.world.create.dropdown.generatorpreset.theend";

    /**
     * Nether Generator Type
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_NETHER = "allay:command.world.create.dropdown.generatortype.nether";

    /**
     * Overworld Generator Type
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_OVERWORLD = "allay:command.world.create.dropdown.generatortype.overworld";

    /**
     * The End Generator Type
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_THEEND = "allay:command.world.create.dropdown.generatortype.theend";

    /**
     * Storage Type
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_STORAGETYPE = "allay:command.world.create.dropdown.storagetype";

    /**
     * World Name
     */
    String ALLAY_COMMAND_WORLD_CREATE_INPUT_NAME = "allay:command.world.create.input.name";

    /**
     * Create New World
     */
    String ALLAY_COMMAND_WORLD_CREATE_TITLE = "allay:command.world.create.title";

    /**
     * Enable Nether
     */
    String ALLAY_COMMAND_WORLD_CREATE_TOGGLE_ENABLE_NETHER = "allay:command.world.create.toggle.enable.nether";

    /**
     * Enable The End
     */
    String ALLAY_COMMAND_WORLD_CREATE_TOGGLE_ENABLE_THEEND = "allay:command.world.create.toggle.enable.theend";

    /**
     * Manage worlds
     */
    String ALLAY_COMMAND_WORLD_DESCRIPTION = "allay:command.world.description";

    /**
     * Dimension is disabled: %1
     */
    String ALLAY_COMMAND_WORLD_DIM_DISABLED = "allay:command.world.dim.disabled";

    /**
     * Unknown dimension: %1
     */
    String ALLAY_COMMAND_WORLD_DIM_UNKNOWN = "allay:command.world.dim.unknown";

    /**
     * Loaded worlds:
     */
    String ALLAY_COMMAND_WORLD_LIST = "allay:command.world.list";

    /**
     * You are teleported to world %1:%2
     */
    String ALLAY_COMMAND_WORLD_TP_SUCCESS = "allay:command.world.tp.success";

    /**
     * Unknown world: %1
     */
    String ALLAY_COMMAND_WORLD_UNKNOWN = "allay:command.world.unknown";

    /**
     * Default world can't be unloaded
     */
    String ALLAY_COMMAND_WORLD_UNLOAD_FAILED_DEFAULT = "allay:command.world.unload.failed.default";

    /**
     * Your IP have been banned from this server
     */
    String ALLAY_DISCONNECT_BANIP = "allay:disconnect.banIp";

    /**
     * Error while constructing extension instance for extension %1. Error: %2
     */
    String ALLAY_EXTENSION_CONSTRUCT_INSTANCE_ERROR = "allay:extension.construct.instance.error";

    /**
     * Can't find the entrance of extension %1
     */
    String ALLAY_EXTENSION_ENTRANCE_MISSING = "allay:extension.entrance.missing";

    /**
     * loading extension %1
     */
    String ALLAY_EXTENSION_LOADING = "allay:extension.loading";

    /**
     * The entrance class of extension %1 is not a subclass of PluginExtension
     */
    String ALLAY_EXTENSION_MAINCLASS_TYPEINVALID = "allay:extension.mainclass.typeinvalid";

    /**
     * Chunk
     */
    String ALLAY_GUI_CHUNK = "allay:gui.chunk";

    /**
     * Loaded Chunk Count: %1
     */
    String ALLAY_GUI_CHUNK_LABEL = "allay:gui.chunk.label";

    /**
     * Console
     */
    String ALLAY_GUI_CONSOLE = "allay:gui.console";

    /**
     * Change font size
     */
    String ALLAY_GUI_CONSOLE_CHANGEFONTSIZE = "allay:gui.console.changefontsize";

    /**
     * Please input the font size you want:
     */
    String ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_DIALOG = "allay:gui.console.changefontsize.dialog";

    /**
     * Invalid font size!
     */
    String ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_ERROR_DIALOG = "allay:gui.console.changefontsize.error.dialog";

    /**
     * Clear the console
     */
    String ALLAY_GUI_CONSOLE_CLEAR = "allay:gui.console.clear";

    /**
     * Entity
     */
    String ALLAY_GUI_ENTITY = "allay:gui.entity";

    /**
     * Loader Entity Count: %1
     */
    String ALLAY_GUI_ENTITY_LABEL = "allay:gui.entity.label";

    /**
     * Error
     */
    String ALLAY_GUI_ERROR = "allay:gui.error";

    /**
     * Information
     */
    String ALLAY_GUI_INFO = "allay:gui.info";

    /**
     * Memory
     */
    String ALLAY_GUI_MEMORY = "allay:gui.memory";

    /**
     * Dashboard
     */
    String ALLAY_GUI_NAME = "allay:gui.name";

    /**
     * Performance
     */
    String ALLAY_GUI_PERFORMANCE = "allay:gui.performance";

    /**
     * Player
     */
    String ALLAY_GUI_PLAYER = "allay:gui.player";

    /**
     * Address
     */
    String ALLAY_GUI_PLAYER_ADDRESS = "allay:gui.player.address";

    /**
     * Ban
     */
    String ALLAY_GUI_PLAYER_BAN = "allay:gui.player.ban";

    /**
     * BanIP
     */
    String ALLAY_GUI_PLAYER_BANIP = "allay:gui.player.banip";

    /**
     * Dimension
     */
    String ALLAY_GUI_PLAYER_DIMENSION = "allay:gui.player.dimension";

    /**
     * Player Information
     */
    String ALLAY_GUI_PLAYER_INFO = "allay:gui.player.info";

    /**
     * Kick
     */
    String ALLAY_GUI_PLAYER_KICK = "allay:gui.player.kick";

    /**
     * Player Name
     */
    String ALLAY_GUI_PLAYER_NAME = "allay:gui.player.name";

    /**
     * Online: %1
     */
    String ALLAY_GUI_PLAYER_ONLINE = "allay:gui.player.online";

    /**
     * Pos
     */
    String ALLAY_GUI_PLAYER_POS = "allay:gui.player.pos";

    /**
     * UUID
     */
    String ALLAY_GUI_PLAYER_UUID = "allay:gui.player.uuid";

    /**
     * World
     */
    String ALLAY_GUI_PLAYER_WORLD = "allay:gui.player.world";

    /**
     * Plugin
     */
    String ALLAY_GUI_PLUGIN = "allay:gui.plugin";

    /**
     * API Version
     */
    String ALLAY_GUI_PLUGIN_API_VERSION = "allay:gui.plugin.api_version";

    /**
     * Authors
     */
    String ALLAY_GUI_PLUGIN_AUTHORS = "allay:gui.plugin.authors";

    /**
     * Dependencies
     */
    String ALLAY_GUI_PLUGIN_DEPENDENCIES = "allay:gui.plugin.dependencies";

    /**
     * Description
     */
    String ALLAY_GUI_PLUGIN_DESCRIPTION = "allay:gui.plugin.description";

    /**
     * Entrance
     */
    String ALLAY_GUI_PLUGIN_ENTRANCE = "allay:gui.plugin.entrance";

    /**
     * Plugin Information
     */
    String ALLAY_GUI_PLUGIN_INFO = "allay:gui.plugin.info";

    /**
     * Name
     */
    String ALLAY_GUI_PLUGIN_NAME = "allay:gui.plugin.name";

    /**
     * Version
     */
    String ALLAY_GUI_PLUGIN_VERSION = "allay:gui.plugin.version";

    /**
     * Website
     */
    String ALLAY_GUI_PLUGIN_WEBSITE = "allay:gui.plugin.website";

    /**
     * Used: %1mb (%2% free)
     */
    String ALLAY_GUI_RAM_LABEL = "allay:gui.ram.label";

    /**
     * Server language was set to %1
     */
    String ALLAY_LANG_SET = "allay:lang.set";

    /**
     * Client connected, IP: %1
     */
    String ALLAY_NETWORK_CLIENT_CONNECTED = "allay:network.client.connected";

    /**
     * Client disconnected, IP: %1
     */
    String ALLAY_NETWORK_CLIENT_DISCONNECTED = "allay:network.client.disconnected";

    /**
     * Network interface started at %1:%2 and [%3]:%4 (%5 ms)
     */
    String ALLAY_NETWORK_INTERFACE_STARTED = "allay:network.interface.started";

    /**
     * Network interface started at %1:%2 (%3 ms)
     */
    String ALLAY_NETWORK_INTERFACE_STARTED_V4ONLY = "allay:network.interface.started.v4only";

    /**
     * Starting up network interface...
     */
    String ALLAY_NETWORK_INTERFACE_STARTING = "allay:network.interface.starting";

    /**
     * Pack %1 is encrypted. Key: %2
     */
    String ALLAY_PACK_ENCRYPTED = "allay:pack.encrypted";

    /**
     * Encrypting pack %1
     */
    String ALLAY_PACK_ENCRYPTING = "allay:pack.encrypting";

    /**
     * Loaded %1 packs
     */
    String ALLAY_PACK_LOADED = "allay:pack.loaded";

    /**
     * Pack loaded successfully %1
     */
    String ALLAY_PACK_LOADED_ENTRY = "allay:pack.loaded.entry";

    /**
     * Loading packs...
     */
    String ALLAY_PACK_LOADING = "allay:pack.loading";

    /**
     * Loading pack %1
     */
    String ALLAY_PACK_LOADING_ENTRY = "allay:pack.loading.entry";

    /**
     * The current API version %1 does not meet the API version requirement for plugin %2: %3
     */
    String ALLAY_PLUGIN_API_VERSION_MISMATCH = "allay:plugin.api_version.mismatch";

    /**
     * Error while constructing plugin instance for plugin %1. Error: %2
     */
    String ALLAY_PLUGIN_CONSTRUCT_INSTANCE_ERROR = "allay:plugin.construct.instance.error";

    /**
     * Circular dependencies appear in plugin %1: %2 The plugin will skip loading!
     */
    String ALLAY_PLUGIN_DEPENDENCY_CIRCULAR = "allay:plugin.dependency.circular";

    /**
     * Plugin %1 is depending on plugin %2, which is not found
     */
    String ALLAY_PLUGIN_DEPENDENCY_MISSING = "allay:plugin.dependency.missing";

    /**
     * Plugin %1 expects dependency %2 version in %3, but got version %4
     */
    String ALLAY_PLUGIN_DEPENDENCY_VERSION_MISMATCH = "allay:plugin.dependency.version.mismatch";

    /**
     * Error while loading the descriptor of plugin %1: %2
     */
    String ALLAY_PLUGIN_DESCRIPTOR_ERROR = "allay:plugin.descriptor.error";

    /**
     * Error while disabling plugin %1. Error: %2
     */
    String ALLAY_PLUGIN_DISABLE_ERROR = "allay:plugin.disable.error";

    /**
     * Disabling plugin %1
     */
    String ALLAY_PLUGIN_DISABLING = "allay:plugin.disabling";

    /**
     * Find duplicate plugin %1
     */
    String ALLAY_PLUGIN_DUPLICATE = "allay:plugin.duplicate";

    /**
     * Error while enabling plugin %1. Error: %2
     */
    String ALLAY_PLUGIN_ENABLE_ERROR = "allay:plugin.enable.error";

    /**
     * Enabling plugin %1
     */
    String ALLAY_PLUGIN_ENABLING = "allay:plugin.enabling";

    /**
     * Can't find the entrance of plugin %1
     */
    String ALLAY_PLUGIN_ENTRANCE_MISSING = "allay:plugin.entrance.missing";

    /**
     * The entrance class of plugin %1 is not a subclass of Plugin
     */
    String ALLAY_PLUGIN_JAR_ENTRANCE_TYPEINVALID = "allay:plugin.jar.entrance.typeinvalid";

    /**
     * Error while loading plugin %1. Error: %2
     */
    String ALLAY_PLUGIN_LOAD_ERROR = "allay:plugin.load.error";

    /**
     * Loading plugin %1
     */
    String ALLAY_PLUGIN_LOADING = "allay:plugin.loading";

    /**
     * §eYou are running a development version. The development version may have unexpected bugs, please do not use it in a production environment!
     */
    String ALLAY_SERVER_IS_DEV_VERSION = "allay:server.is_dev_version";

    /**
     * Starting up Minecraft: Bedrock Edition server
     */
    String ALLAY_SERVER_STARTING = "allay:server.starting";

    /**
     * Server stopped
     */
    String ALLAY_SERVER_STOPPED = "allay:server.stopped";

    /**
     * World %1 is loaded
     */
    String ALLAY_WORLD_LOADED = "allay:world.loaded";

    /**
     * Loading world %1
     */
    String ALLAY_WORLD_LOADING = "allay:world.loading";

    /**
     * World %1 is unloaded
     */
    String ALLAY_WORLD_UNLOADED = "allay:world.unloaded";

    /**
     * Unloading world %1
     */
    String ALLAY_WORLD_UNLOADING = "allay:world.unloading";

    /**
     * Account data has been copied to your clipboard.
     */
    String MC_ACCOUNT_TOAST_COPIED_BODY = "minecraft:account.toast.copied.body";

    /**
     * Copied data to clipboard
     */
    String MC_ACCOUNT_TOAST_COPIED_TITLE = "minecraft:account.toast.copied.title";

    /**
     * Acquire Hardware
     */
    String MC_ACHIEVEMENT_ACQUIREIRON = "minecraft:achievement.acquireIron";

    /**
     * Smelt an iron ingot
     */
    String MC_ACHIEVEMENT_ACQUIREIRON_DESC = "minecraft:achievement.acquireIron.desc";

    /**
     * Alternative Fuel
     */
    String MC_ACHIEVEMENT_ALTERNATIVEFUEL = "minecraft:achievement.alternativeFuel";

    /**
     * Power a furnace with a kelp block
     */
    String MC_ACHIEVEMENT_ALTERNATIVEFUEL_DESC = "minecraft:achievement.alternativeFuel.desc";

    /**
     * The Lie
     */
    String MC_ACHIEVEMENT_BAKECAKE = "minecraft:achievement.bakeCake";

    /**
     * Wheat, sugar, milk and eggs!
     */
    String MC_ACHIEVEMENT_BAKECAKE_DESC = "minecraft:achievement.bakeCake.desc";

    /**
     * Into Fire
     */
    String MC_ACHIEVEMENT_BLAZE_ROD = "minecraft:achievement.blaze_rod";

    /**
     * Relieve a Blaze of its rod
     */
    String MC_ACHIEVEMENT_BLAZE_ROD_DESC = "minecraft:achievement.blaze_rod.desc";

    /**
     * Librarian
     */
    String MC_ACHIEVEMENT_BOOKCASE = "minecraft:achievement.bookcase";

    /**
     * Build some bookshelves to improve your enchantment table
     */
    String MC_ACHIEVEMENT_BOOKCASE_DESC = "minecraft:achievement.bookcase.desc";

    /**
     * Repopulation
     */
    String MC_ACHIEVEMENT_BREEDCOW = "minecraft:achievement.breedCow";

    /**
     * Breed two cows with wheat
     */
    String MC_ACHIEVEMENT_BREEDCOW_DESC = "minecraft:achievement.breedCow.desc";

    /**
     * Getting an Upgrade
     */
    String MC_ACHIEVEMENT_BUILDBETTERPICKAXE = "minecraft:achievement.buildBetterPickaxe";

    /**
     * Construct a better pickaxe
     */
    String MC_ACHIEVEMENT_BUILDBETTERPICKAXE_DESC = "minecraft:achievement.buildBetterPickaxe.desc";

    /**
     * Hot Topic
     */
    String MC_ACHIEVEMENT_BUILDFURNACE = "minecraft:achievement.buildFurnace";

    /**
     * Construct a furnace out of eight stone blocks
     */
    String MC_ACHIEVEMENT_BUILDFURNACE_DESC = "minecraft:achievement.buildFurnace.desc";

    /**
     * Time to Farm!
     */
    String MC_ACHIEVEMENT_BUILDHOE = "minecraft:achievement.buildHoe";

    /**
     * Use planks and sticks to make a hoe
     */
    String MC_ACHIEVEMENT_BUILDHOE_DESC = "minecraft:achievement.buildHoe.desc";

    /**
     * Time to Mine!
     */
    String MC_ACHIEVEMENT_BUILDPICKAXE = "minecraft:achievement.buildPickaxe";

    /**
     * Use planks and sticks to make a pickaxe
     */
    String MC_ACHIEVEMENT_BUILDPICKAXE_DESC = "minecraft:achievement.buildPickaxe.desc";

    /**
     * Time to Strike!
     */
    String MC_ACHIEVEMENT_BUILDSWORD = "minecraft:achievement.buildSword";

    /**
     * Use planks and sticks to make a sword
     */
    String MC_ACHIEVEMENT_BUILDSWORD_DESC = "minecraft:achievement.buildSword.desc";

    /**
     * Benchmarking
     */
    String MC_ACHIEVEMENT_BUILDWORKBENCH = "minecraft:achievement.buildWorkBench";

    /**
     * Craft a workbench with four blocks of planks
     */
    String MC_ACHIEVEMENT_BUILDWORKBENCH_DESC = "minecraft:achievement.buildWorkBench.desc";

    /**
     * Delicious Fish
     */
    String MC_ACHIEVEMENT_COOKFISH = "minecraft:achievement.cookFish";

    /**
     * Catch and cook fish!
     */
    String MC_ACHIEVEMENT_COOKFISH_DESC = "minecraft:achievement.cookFish.desc";

    /**
     * DIAMONDS!
     */
    String MC_ACHIEVEMENT_DIAMONDS = "minecraft:achievement.diamonds";

    /**
     * Acquire diamonds with your iron tools
     */
    String MC_ACHIEVEMENT_DIAMONDS_DESC = "minecraft:achievement.diamonds.desc";

    /**
     * Diamonds to you!
     */
    String MC_ACHIEVEMENT_DIAMONDSTOYOU = "minecraft:achievement.diamondsToYou";

    /**
     * Throw diamonds at another player.
     */
    String MC_ACHIEVEMENT_DIAMONDSTOYOU_DESC = "minecraft:achievement.diamondsToYou.desc";

    /**
     * Enchanter
     */
    String MC_ACHIEVEMENT_ENCHANTMENTS = "minecraft:achievement.enchantments";

    /**
     * Use a book, obsidian and diamonds to construct an enchantment table
     */
    String MC_ACHIEVEMENT_ENCHANTMENTS_DESC = "minecraft:achievement.enchantments.desc";

    /**
     * Adventuring Time
     */
    String MC_ACHIEVEMENT_EXPLOREALLBIOMES = "minecraft:achievement.exploreAllBiomes";

    /**
     * Discover all biomes
     */
    String MC_ACHIEVEMENT_EXPLOREALLBIOMES_DESC = "minecraft:achievement.exploreAllBiomes.desc";

    /**
     * When Pigs Fly
     */
    String MC_ACHIEVEMENT_FLYPIG = "minecraft:achievement.flyPig";

    /**
     * Fly a pig off a cliff
     */
    String MC_ACHIEVEMENT_FLYPIG_DESC = "minecraft:achievement.flyPig.desc";

    /**
     * Beaconator
     */
    String MC_ACHIEVEMENT_FULLBEACON = "minecraft:achievement.fullBeacon";

    /**
     * Create a full beacon
     */
    String MC_ACHIEVEMENT_FULLBEACON_DESC = "minecraft:achievement.fullBeacon.desc";

    /**
     * Achievement get!
     */
    String MC_ACHIEVEMENT_GET = "minecraft:achievement.get";

    /**
     * Return to Sender
     */
    String MC_ACHIEVEMENT_GHAST = "minecraft:achievement.ghast";

    /**
     * Destroy a Ghast with a fireball
     */
    String MC_ACHIEVEMENT_GHAST_DESC = "minecraft:achievement.ghast.desc";

    /**
     * Cow Tipper
     */
    String MC_ACHIEVEMENT_KILLCOW = "minecraft:achievement.killCow";

    /**
     * Harvest some leather
     */
    String MC_ACHIEVEMENT_KILLCOW_DESC = "minecraft:achievement.killCow.desc";

    /**
     * Monster Hunter
     */
    String MC_ACHIEVEMENT_KILLENEMY = "minecraft:achievement.killEnemy";

    /**
     * Attack and destroy a monster
     */
    String MC_ACHIEVEMENT_KILLENEMY_DESC = "minecraft:achievement.killEnemy.desc";

    /**
     * The Beginning.
     */
    String MC_ACHIEVEMENT_KILLWITHER = "minecraft:achievement.killWither";

    /**
     * Kill the Wither
     */
    String MC_ACHIEVEMENT_KILLWITHER_DESC = "minecraft:achievement.killWither.desc";

    /**
     * Bake Bread
     */
    String MC_ACHIEVEMENT_MAKEBREAD = "minecraft:achievement.makeBread";

    /**
     * Turn wheat into bread
     */
    String MC_ACHIEVEMENT_MAKEBREAD_DESC = "minecraft:achievement.makeBread.desc";

    /**
     * Getting Wood
     */
    String MC_ACHIEVEMENT_MINEWOOD = "minecraft:achievement.mineWood";

    /**
     * Attack a tree until a block of wood pops out
     */
    String MC_ACHIEVEMENT_MINEWOOD_DESC = "minecraft:achievement.mineWood.desc";

    /**
     * Achievement unlocked
     */
    String MC_ACHIEVEMENT_NOTIFICATION_DESCRIPTION = "minecraft:achievement.notification.description";

    /**
     * On A Rail
     */
    String MC_ACHIEVEMENT_ONARAIL = "minecraft:achievement.onARail";

    /**
     * Travel by minecart at least 1 km from where you started
     */
    String MC_ACHIEVEMENT_ONARAIL_DESC = "minecraft:achievement.onARail.desc";

    /**
     * Taking Inventory
     */
    String MC_ACHIEVEMENT_OPENINVENTORY = "minecraft:achievement.openInventory";

    /**
     * Press '%1#s' to open your inventory.
     */
    String MC_ACHIEVEMENT_OPENINVENTORY_DESC = "minecraft:achievement.openInventory.desc";

    /**
     * Overkill
     */
    String MC_ACHIEVEMENT_OVERKILL = "minecraft:achievement.overkill";

    /**
     * Deal nine hearts of damage in a single hit
     */
    String MC_ACHIEVEMENT_OVERKILL_DESC = "minecraft:achievement.overkill.desc";

    /**
     * Overpowered
     */
    String MC_ACHIEVEMENT_OVERPOWERED = "minecraft:achievement.overpowered";

    /**
     * Build a Notch apple
     */
    String MC_ACHIEVEMENT_OVERPOWERED_DESC = "minecraft:achievement.overpowered.desc";

    /**
     * We Need to Go Deeper
     */
    String MC_ACHIEVEMENT_PORTAL = "minecraft:achievement.portal";

    /**
     * Build a portal to the Nether
     */
    String MC_ACHIEVEMENT_PORTAL_DESC = "minecraft:achievement.portal.desc";

    /**
     * Local Brewery
     */
    String MC_ACHIEVEMENT_POTION = "minecraft:achievement.potion";

    /**
     * Brew a potion
     */
    String MC_ACHIEVEMENT_POTION_DESC = "minecraft:achievement.potion.desc";

    /**
     * Requires '%1#s'
     */
    String MC_ACHIEVEMENT_REQUIRES = "minecraft:achievement.requires";

    /**
     * Sniper Duel
     */
    String MC_ACHIEVEMENT_SNIPESKELETON = "minecraft:achievement.snipeSkeleton";

    /**
     * Kill a skeleton with an arrow from more than 50 meters
     */
    String MC_ACHIEVEMENT_SNIPESKELETON_DESC = "minecraft:achievement.snipeSkeleton.desc";

    /**
     * The Beginning?
     */
    String MC_ACHIEVEMENT_SPAWNWITHER = "minecraft:achievement.spawnWither";

    /**
     * Spawn the Wither
     */
    String MC_ACHIEVEMENT_SPAWNWITHER_DESC = "minecraft:achievement.spawnWither.desc";

    /**
     * Taken!
     */
    String MC_ACHIEVEMENT_TAKEN = "minecraft:achievement.taken";

    /**
     * The End?
     */
    String MC_ACHIEVEMENT_THEEND = "minecraft:achievement.theEnd";

    /**
     * Locate the End
     */
    String MC_ACHIEVEMENT_THEEND_DESC = "minecraft:achievement.theEnd.desc";

    /**
     * The End.
     */
    String MC_ACHIEVEMENT_THEEND2 = "minecraft:achievement.theEnd2";

    /**
     * Defeat the Ender Dragon
     */
    String MC_ACHIEVEMENT_THEEND2_DESC = "minecraft:achievement.theEnd2.desc";

    /**
     * --
     */
    String MC_ACHIEVEMENT_UNINITSCORE = "minecraft:achievement.uninitScore";

    /**
     * ???
     */
    String MC_ACHIEVEMENT_UNKNOWN = "minecraft:achievement.unknown";

    /**
     * Tap jump to exit the boat
     */
    String MC_ACTION_HINT_EXIT_BOAT = "minecraft:action.hint.exit.boat";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_CAMEL = "minecraft:action.hint.exit.camel";

    /**
     * Tap jump to exit the boat
     */
    String MC_ACTION_HINT_EXIT_CHEST_BOAT = "minecraft:action.hint.exit.chest_boat";

    /**
     * Press :_input_key.jump: to exit the boat
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_BOAT = "minecraft:action.hint.exit.console.boat";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_CAMEL = "minecraft:action.hint.exit.console.camel";

    /**
     * Press :_input_key.jump: to exit the boat
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_CHEST_BOAT = "minecraft:action.hint.exit.console.chest_boat";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_DONKEY = "minecraft:action.hint.exit.console.donkey";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_HAPPY_GHAST = "minecraft:action.hint.exit.console.happy_ghast";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_HORSE = "minecraft:action.hint.exit.console.horse";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_LLAMA = "minecraft:action.hint.exit.console.llama";

    /**
     * Press :_input_key.jump: to exit the minecart
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_MINECART = "minecraft:action.hint.exit.console.minecart";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_MULE = "minecraft:action.hint.exit.console.mule";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_PIG = "minecraft:action.hint.exit.console.pig";

    /**
     * Press :_input_key.jump: to exit the raft
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_RAFT = "minecraft:action.hint.exit.console.raft";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_SKELETON_HORSE = "minecraft:action.hint.exit.console.skeleton_horse";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_STRIDER = "minecraft:action.hint.exit.console.strider";

    /**
     * Press :_input_key.sneak: to dismount
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_TRADER_LLAMA = "minecraft:action.hint.exit.console.trader_llama";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_DONKEY = "minecraft:action.hint.exit.donkey";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_HAPPY_GHAST = "minecraft:action.hint.exit.happy_ghast";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_HORSE = "minecraft:action.hint.exit.horse";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_LLAMA = "minecraft:action.hint.exit.llama";

    /**
     * Tap jump to exit the minecart
     */
    String MC_ACTION_HINT_EXIT_MINECART = "minecraft:action.hint.exit.minecart";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_MULE = "minecraft:action.hint.exit.mule";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_PIG = "minecraft:action.hint.exit.pig";

    /**
     * Tap jump to exit the raft
     */
    String MC_ACTION_HINT_EXIT_RAFT = "minecraft:action.hint.exit.raft";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_CAMEL = "minecraft:action.hint.exit.scheme.camel";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_DONKEY = "minecraft:action.hint.exit.scheme.donkey";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_HAPPY_GHAST = "minecraft:action.hint.exit.scheme.happy_ghast";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_HORSE = "minecraft:action.hint.exit.scheme.horse";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_LLAMA = "minecraft:action.hint.exit.scheme.llama";

    /**
     * Tap dismount to exit the minecart
     */
    String MC_ACTION_HINT_EXIT_SCHEME_MINECART = "minecraft:action.hint.exit.scheme.minecart";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_MULE = "minecraft:action.hint.exit.scheme.mule";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_PIG = "minecraft:action.hint.exit.scheme.pig";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_SKELETON_HORSE = "minecraft:action.hint.exit.scheme.skeleton_horse";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_STRIDER = "minecraft:action.hint.exit.scheme.strider";

    /**
     * Tap dismount to dismount
     */
    String MC_ACTION_HINT_EXIT_SCHEME_TRADER_LLAMA = "minecraft:action.hint.exit.scheme.trader_llama";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_SKELETON_HORSE = "minecraft:action.hint.exit.skeleton_horse";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_STRIDER = "minecraft:action.hint.exit.strider";

    /**
     * Tap sneak to dismount
     */
    String MC_ACTION_HINT_EXIT_TRADER_LLAMA = "minecraft:action.hint.exit.trader_llama";

    /**
     * Give item to Allay
     */
    String MC_ACTION_INTERACT_ALLAY = "minecraft:action.interact.allay";

    /**
     * Equip
     */
    String MC_ACTION_INTERACT_ARMORSTAND_EQUIP = "minecraft:action.interact.armorstand.equip";

    /**
     * Pose
     */
    String MC_ACTION_INTERACT_ARMORSTAND_POSE = "minecraft:action.interact.armorstand.pose";

    /**
     * Attach Chest
     */
    String MC_ACTION_INTERACT_ATTACHCHEST = "minecraft:action.interact.attachchest";

    /**
     * Barter
     */
    String MC_ACTION_INTERACT_BARTER = "minecraft:action.interact.barter";

    /**
     * Boost
     */
    String MC_ACTION_INTERACT_BOOST = "minecraft:action.interact.boost";

    /**
     * Brush
     */
    String MC_ACTION_INTERACT_BRUSH = "minecraft:action.interact.brush";

    /**
     * Create Map
     */
    String MC_ACTION_INTERACT_CREATEMAP = "minecraft:action.interact.createMap";

    /**
     * Ignite
     */
    String MC_ACTION_INTERACT_CREEPER = "minecraft:action.interact.creeper";

    /**
     * Cure
     */
    String MC_ACTION_INTERACT_CURE = "minecraft:action.interact.cure";

    /**
     * Cut 
     */
    String MC_ACTION_INTERACT_CUT = "minecraft:action.interact.cut";

    /**
     * Drop Item
     */
    String MC_ACTION_INTERACT_DROP_ITEM = "minecraft:action.interact.drop_item";

    /**
     * Dye
     */
    String MC_ACTION_INTERACT_DYE = "minecraft:action.interact.dye";

    /**
     * Edit
     */
    String MC_ACTION_INTERACT_EDIT = "minecraft:action.interact.edit";

    /**
     * Equip Carpet
     */
    String MC_ACTION_INTERACT_EQUIPCARPET = "minecraft:action.interact.equipcarpet";

    /**
     * Equip Harness
     */
    String MC_ACTION_INTERACT_EQUIPHARNESS = "minecraft:action.interact.equipharness";

    /**
     * Equip Horse Armor
     */
    String MC_ACTION_INTERACT_EQUIPHORSEARMOR = "minecraft:action.interact.equiphorsearmor";

    /**
     * Equip Wolf Armor
     */
    String MC_ACTION_INTERACT_EQUIPWOLFARMOR = "minecraft:action.interact.equipwolfarmor";

    /**
     * Leave Boat
     */
    String MC_ACTION_INTERACT_EXIT_BOAT = "minecraft:action.interact.exit.boat";

    /**
     * Leave Raft
     */
    String MC_ACTION_INTERACT_EXIT_RAFT = "minecraft:action.interact.exit.raft";

    /**
     * Feed
     */
    String MC_ACTION_INTERACT_FEED = "minecraft:action.interact.feed";

    /**
     * Fish
     */
    String MC_ACTION_INTERACT_FISHING = "minecraft:action.interact.fishing";

    /**
     * Leash
     */
    String MC_ACTION_INTERACT_LEASH = "minecraft:action.interact.leash";

    /**
     * Milk
     */
    String MC_ACTION_INTERACT_MILK = "minecraft:action.interact.milk";

    /**
     * Shear
     */
    String MC_ACTION_INTERACT_MOOSHEAR = "minecraft:action.interact.mooshear";

    /**
     * Milk Stew
     */
    String MC_ACTION_INTERACT_MOOSTEW = "minecraft:action.interact.moostew";

    /**
     * Mount
     */
    String MC_ACTION_INTERACT_MOUNT = "minecraft:action.interact.mount";

    /**
     * Name
     */
    String MC_ACTION_INTERACT_NAME = "minecraft:action.interact.name";

    /**
     * Open
     */
    String MC_ACTION_INTERACT_OPENCONTAINER = "minecraft:action.interact.opencontainer";

    /**
     * Read
     */
    String MC_ACTION_INTERACT_READ = "minecraft:action.interact.read";

    /**
     * Remove Carpet 
     */
    String MC_ACTION_INTERACT_REMOVECARPET = "minecraft:action.interact.removecarpet";

    /**
     * Remove Harness
     */
    String MC_ACTION_INTERACT_REMOVEHARNESS = "minecraft:action.interact.removeharness";

    /**
     * Remove Horse Armor 
     */
    String MC_ACTION_INTERACT_REMOVEHORSEARMOR = "minecraft:action.interact.removehorsearmor";

    /**
     * Remove Saddle 
     */
    String MC_ACTION_INTERACT_REMOVESADDLE = "minecraft:action.interact.removesaddle";

    /**
     * Remove Wolf Armor
     */
    String MC_ACTION_INTERACT_REMOVEWOLFARMOR = "minecraft:action.interact.removewolfarmor";

    /**
     * Repair
     */
    String MC_ACTION_INTERACT_REPAIR = "minecraft:action.interact.repair";

    /**
     * Repair Wolf Armor
     */
    String MC_ACTION_INTERACT_REPAIRWOLFARMOR = "minecraft:action.interact.repairwolfarmor";

    /**
     * Retrieve 
     */
    String MC_ACTION_INTERACT_RETRIEVE = "minecraft:action.interact.retrieve";

    /**
     * Board
     */
    String MC_ACTION_INTERACT_RIDE_BOAT = "minecraft:action.interact.ride.boat";

    /**
     * Ride
     */
    String MC_ACTION_INTERACT_RIDE_HORSE = "minecraft:action.interact.ride.horse";

    /**
     * Ride
     */
    String MC_ACTION_INTERACT_RIDE_MINECART = "minecraft:action.interact.ride.minecart";

    /**
     * Ride
     */
    String MC_ACTION_INTERACT_RIDE_STRIDER = "minecraft:action.interact.ride.strider";

    /**
     * Saddle
     */
    String MC_ACTION_INTERACT_SADDLE = "minecraft:action.interact.saddle";

    /**
     * Scrape
     */
    String MC_ACTION_INTERACT_SCRAPE = "minecraft:action.interact.scrape";

    /**
     * Shear
     */
    String MC_ACTION_INTERACT_SHEAR = "minecraft:action.interact.shear";

    /**
     * Sit
     */
    String MC_ACTION_INTERACT_SIT = "minecraft:action.interact.sit";

    /**
     * Stand
     */
    String MC_ACTION_INTERACT_STAND = "minecraft:action.interact.stand";

    /**
     * Take Picture
     */
    String MC_ACTION_INTERACT_TAKEPICTURE = "minecraft:action.interact.takepicture";

    /**
     * Talk
     */
    String MC_ACTION_INTERACT_TALK = "minecraft:action.interact.talk";

    /**
     * Tame
     */
    String MC_ACTION_INTERACT_TAME = "minecraft:action.interact.tame";

    /**
     * Trade
     */
    String MC_ACTION_INTERACT_TRADE = "minecraft:action.interact.trade";

    /**
     * Unleash
     */
    String MC_ACTION_INTERACT_UNLEASH = "minecraft:action.interact.unleash";

    /**
     * Use Item
     */
    String MC_ACTION_INTERACT_USE = "minecraft:action.interact.use";

    /**
     * Wake Villager
     */
    String MC_ACTION_INTERACT_WAKEVILLAGER = "minecraft:action.interact.wakevillager";

    /**
     * Wax Off
     */
    String MC_ACTION_INTERACT_WAX_OFF = "minecraft:action.interact.wax_off";

    /**
     * Wax On
     */
    String MC_ACTION_INTERACT_WAX_ON = "minecraft:action.interact.wax_on";

    /**
     * Write
     */
    String MC_ACTION_INTERACT_WRITE = "minecraft:action.interact.write";

    /**
     * @e = all entities
     */
    String MC_ADVMODE_ALLENTITIES = "minecraft:advMode.allEntities";

    /**
     * @a = all players
     */
    String MC_ADVMODE_ALLPLAYERS = "minecraft:advMode.allPlayers";

    /**
     * Command Input
     */
    String MC_ADVMODE_COMMAND = "minecraft:advMode.command";

    /**
     * Command Input exceeds 500 characters. It is not possible to open the keyboard input on this device type.
     */
    String MC_ADVMODE_COMMAND_CHARLIMITEXCEEDED = "minecraft:advMode.command.charLimitExceeded";

    /**
     * @n = nearest entity 
     */
    String MC_ADVMODE_NEAREST = "minecraft:advMode.nearest";

    /**
     * @p = nearest player
     */
    String MC_ADVMODE_NEARESTPLAYER = "minecraft:advMode.nearestPlayer";

    /**
     * Must be an opped player in creative mode
     */
    String MC_ADVMODE_NOTALLOWED = "minecraft:advMode.notAllowed";

    /**
     * Command blocks are not enabled on this server
     */
    String MC_ADVMODE_NOTENABLED = "minecraft:advMode.notEnabled";

    /**
     * Previous Output
     */
    String MC_ADVMODE_PREVIOUSOUTPUT = "minecraft:advMode.previousOutput";

    /**
     * @r = random player
     */
    String MC_ADVMODE_RANDOMPLAYER = "minecraft:advMode.randomPlayer";

    /**
     * @s = self
     */
    String MC_ADVMODE_SELF = "minecraft:advMode.self";

    /**
     * Set Console Command for Block
     */
    String MC_ADVMODE_SETCOMMAND = "minecraft:advMode.setCommand";

    /**
     * Command set: %s
     */
    String MC_ADVMODE_SETCOMMAND_SUCCESS = "minecraft:advMode.setCommand.success";

    /**
     * +%d %s
     */
    String MC_ATTRIBUTE_MODIFIER_PLUS_0 = "minecraft:attribute.modifier.plus.0";

    /**
     * +%d%% %s
     */
    String MC_ATTRIBUTE_MODIFIER_PLUS_1 = "minecraft:attribute.modifier.plus.1";

    /**
     * +%d%% %s
     */
    String MC_ATTRIBUTE_MODIFIER_PLUS_2 = "minecraft:attribute.modifier.plus.2";

    /**
     * -%d %s
     */
    String MC_ATTRIBUTE_MODIFIER_TAKE_0 = "minecraft:attribute.modifier.take.0";

    /**
     * -%d%% %s
     */
    String MC_ATTRIBUTE_MODIFIER_TAKE_1 = "minecraft:attribute.modifier.take.1";

    /**
     * -%d%% %s
     */
    String MC_ATTRIBUTE_MODIFIER_TAKE_2 = "minecraft:attribute.modifier.take.2";

    /**
     * Armor
     */
    String MC_ATTRIBUTE_NAME_GENERIC_ARMOR = "minecraft:attribute.name.generic.armor";

    /**
     * Armor Toughness
     */
    String MC_ATTRIBUTE_NAME_GENERIC_ARMORTOUGHNESS = "minecraft:attribute.name.generic.armorToughness";

    /**
     * Attack Damage
     */
    String MC_ATTRIBUTE_NAME_GENERIC_ATTACKDAMAGE = "minecraft:attribute.name.generic.attackDamage";

    /**
     * Knockback Resistance
     */
    String MC_ATTRIBUTE_NAME_GENERIC_KNOCKBACKRESISTANCE = "minecraft:attribute.name.generic.knockbackResistance";

    /**
     * Max Health
     */
    String MC_ATTRIBUTE_NAME_GENERIC_MAXHEALTH = "minecraft:attribute.name.generic.maxHealth";

    /**
     * Horse Jump Strength
     */
    String MC_ATTRIBUTE_NAME_HORSE_JUMPSTRENGTH = "minecraft:attribute.name.horse.jumpStrength";

    /**
     * Zombie Reinforcements
     */
    String MC_ATTRIBUTE_NAME_ZOMBIE_SPAWNREINFORCEMENTS = "minecraft:attribute.name.zombie.spawnReinforcements";

    /**
     * Go Back
     */
    String MC_ATTRIBUTION_GOBACK = "minecraft:attribution.goBack";

    /**
     * To view attributions, please visit https://minecraft.net/attribution in any web browser.
     */
    String MC_ATTRIBUTION_VIEWATTRIBUTION = "minecraft:attribution.viewAttribution";

    /**
     * This is bad. This server failed our verification test. Someone we don't trust might be pretending to be a trusted server.
     */
    String MC_AUTHSERVER_AUTHFAILED = "minecraft:authserver.authfailed";

    /**
     * Something went wrong. We can't verify that the server you are trying to connect to is one that we trust. We recommend that you try again later.
     */
    String MC_AUTHSERVER_NOTAVAILABLE = "minecraft:authserver.notavailable";

    /**
     * When you see this icon, we are saving your game. Do not turn off your computer while this icon is on screen.
     */
    String MC_AUTOSAVE_INFO_DESKTOP = "minecraft:autosave.info.desktop";

    /**
     * When you see this icon, we are saving your game. Do not turn off your device while this icon is on screen.
     */
    String MC_AUTOSAVE_INFO_GENERAL = "minecraft:autosave.info.general";

    /**
     * When you see this icon, we are saving your game. Do not turn off your Xbox while this icon is on screen.
     */
    String MC_AUTOSAVE_INFO_XBOX = "minecraft:autosave.info.xbox";

    /**
     * Autosave
     */
    String MC_AUTOSAVE_TITLE = "minecraft:autosave.title";

    /**
     * Most monsters only come out at night. During the day you're safe... probably.
     */
    String MC_BEGINNERTIPS_1 = "minecraft:beginnerTips.1";

    /**
     * Be careful when you swim. Stay under water for too long and you'll run out of air.
     */
    String MC_BEGINNERTIPS_10 = "minecraft:beginnerTips.10";

    /**
     * Survival mode has 4 different difficulty levels - Peaceful, Easy, Normal, and Hard. Or play Creative mode and get endless resources!
     */
    String MC_BEGINNERTIPS_11 = "minecraft:beginnerTips.11";

    /**
     * The world of Minecraft is endlessly surprising. You'll find lots of different animals, plants, and even §f§k§a§b§r §f§k§a§b§r
     */
    String MC_BEGINNERTIPS_12 = "minecraft:beginnerTips.12";

    /**
     * Break blocks, pick up resources. Use the crafting grid to make helpful items. Now you're crafting!
     */
    String MC_BEGINNERTIPS_2 = "minecraft:beginnerTips.2";

    /**
     * You have endless lives, but your items drop to the ground when you die. Hurry back to pick them up!
     */
    String MC_BEGINNERTIPS_3 = "minecraft:beginnerTips.3";

    /**
     * It's a good idea to build a place where you can safely spend the night.
     */
    String MC_BEGINNERTIPS_4 = "minecraft:beginnerTips.4";

    /**
     * Use the right tool for the job! Chopping wood with an axe is much faster than using your hands.
     */
    String MC_BEGINNERTIPS_5 = "minecraft:beginnerTips.5";

    /**
     * Even in Minecraft you'll get hungry, so try to find something to eat before that happens.
     */
    String MC_BEGINNERTIPS_6 = "minecraft:beginnerTips.6";

    /**
     * Digging straight down or straight up might not be a great idea.
     */
    String MC_BEGINNERTIPS_7 = "minecraft:beginnerTips.7";

    /**
     * Monsters spawn in the dark. Craft a torch to brighten up any space.
     */
    String MC_BEGINNERTIPS_8 = "minecraft:beginnerTips.8";

    /**
     * Beds are great! Craft one to quickly pass the night and set your respawn point.
     */
    String MC_BEGINNERTIPS_9 = "minecraft:beginnerTips.9";

    /**
     * by
     */
    String MC_BOOK_BYAUTHOR = "minecraft:book.byAuthor";

    /**
     * Author Unknown
     */
    String MC_BOOK_DEFAULTAUTHOR = "minecraft:book.defaultAuthor";

    /**
     * Enter Book Title:
     */
    String MC_BOOK_EDITTITLE = "minecraft:book.editTitle";

    /**
     * Export
     */
    String MC_BOOK_EXPORT = "minecraft:book.export";

    /**
     * Sign and Close
     */
    String MC_BOOK_FINALIZEBUTTON = "minecraft:book.finalizeButton";

    /**
     * Note! When you sign the book, it will no longer be editable.
     */
    String MC_BOOK_FINALIZEWARNING = "minecraft:book.finalizeWarning";

    /**
     * Original
     */
    String MC_BOOK_GENERATION_0 = "minecraft:book.generation.0";

    /**
     * Copy of original
     */
    String MC_BOOK_GENERATION_1 = "minecraft:book.generation.1";

    /**
     * Copy of a copy
     */
    String MC_BOOK_GENERATION_2 = "minecraft:book.generation.2";

    /**
     * Tattered
     */
    String MC_BOOK_GENERATION_3 = "minecraft:book.generation.3";

    /**
     * From Inventory
     */
    String MC_BOOK_HEADERINVENTORY = "minecraft:book.headerInventory";

    /**
     * From Portfolio
     */
    String MC_BOOK_HEADERPORTFOLIO = "minecraft:book.headerPortfolio";

    /**
     * Page %1#s of %2#s
     */
    String MC_BOOK_PAGEINDICATOR = "minecraft:book.pageIndicator";

    /**
     * Sign
     */
    String MC_BOOK_SIGNBUTTON = "minecraft:book.signButton";

    /**
     * [Enter title here]
     */
    String MC_BOOK_TITLEHERE = "minecraft:book.titleHere";

    /**
     * Height limit for building is %s blocks
     */
    String MC_BUILD_TOOHIGH = "minecraft:build.tooHigh";

    /**
     * Minimum height for building is %s blocks
     */
    String MC_BUILD_TOOLOW = "minecraft:build.tooLow";

    /**
     * Build information has been copied to your clipboard.
     */
    String MC_BUILD_INFO_TOAST_COPIED_BODY = "minecraft:build_info.toast.copied.body";

    /**
     * Copied data to clipboard
     */
    String MC_BUILD_INFO_TOAST_COPIED_TITLE = "minecraft:build_info.toast.copied.title";

    /**
     * Cauldron
     */
    String MC_CAULDRONSCREEN_HEADER = "minecraft:cauldronScreen.header";

    /**
     * Edit Text
     */
    String MC_CHALKBOARDSCREEN_HEADER = "minecraft:chalkboardScreen.header";

    /**
     * Locked
     */
    String MC_CHALKBOARDSCREEN_LOCKED = "minecraft:chalkboardScreen.locked";

    /**
     * Unlocked
     */
    String MC_CHALKBOARDSCREEN_UNLOCKED = "minecraft:chalkboardScreen.unlocked";

    /**
     * Cannot send chat message
     */
    String MC_CHAT_CANNOTSEND = "minecraft:chat.cannotSend";

    /**
     * The coordinate has been copied
     */
    String MC_CHAT_COORDINATECOPIEDTOAST = "minecraft:chat.coordinateCopiedToast";

    /**
     * Block Position
     */
    String MC_CHAT_COORDINATETYPEFACING = "minecraft:chat.coordinateTypeFacing";

    /**
     * My Position
     */
    String MC_CHAT_COORDINATETYPEPOSITION = "minecraft:chat.coordinateTypePosition";

    /**
     * Invalid Coordinates
     */
    String MC_CHAT_COORDINATESINVALID = "minecraft:chat.coordinatesInvalid";

    /**
     * Invalid Coordinates: make sure you are looking at a block
     */
    String MC_CHAT_COORDINATESINVALIDLONG = "minecraft:chat.coordinatesInvalidLong";

    /**
     * Copy to Clipboard
     */
    String MC_CHAT_COPY = "minecraft:chat.copy";

    /**
     * Are you sure you want to open the following website?
     */
    String MC_CHAT_LINK_CONFIRM = "minecraft:chat.link.confirm";

    /**
     * Do you want to open this link or copy it to your clipboard?
     */
    String MC_CHAT_LINK_CONFIRMTRUSTED = "minecraft:chat.link.confirmTrusted";

    /**
     * Open in browser
     */
    String MC_CHAT_LINK_OPEN = "minecraft:chat.link.open";

    /**
     * Never open links from people that you don't trust!
     */
    String MC_CHAT_LINK_WARNING = "minecraft:chat.link.warning";

    /**
     * mention all players
     */
    String MC_CHAT_MENTIONS_AUTOCOMPLETE_ALLPLAYERS = "minecraft:chat.mentions.autocomplete.allPlayers";

    /**
     * Mute Chat
     */
    String MC_CHAT_MUTE = "minecraft:chat.mute";

    /**
     * This Realm has chat filtering turned off.
     */
    String MC_CHAT_REALMSFILTERDISABLED = "minecraft:chat.realmsFilterDisabled";

    /**
     * [OBFUSCATED]
     */
    String MC_CHAT_REDACTOBFUSCATEDTEXT = "minecraft:chat.redactObfuscatedText";

    /**
     * You have been temporarily renamed to '%s' on this server
     */
    String MC_CHAT_RENAMED = "minecraft:chat.renamed";

    /**
     * Chat Settings
     */
    String MC_CHAT_SETTINGS = "minecraft:chat.settings";

    /**
     * Chat Color
     */
    String MC_CHAT_SETTINGS_CHATCOLOR = "minecraft:chat.settings.chatColor";

    /**
     * Chat Font
     */
    String MC_CHAT_SETTINGS_CHATFONT = "minecraft:chat.settings.chatFont";

    /**
     * Color
     */
    String MC_CHAT_SETTINGS_COLOR = "minecraft:chat.settings.color";

    /**
     * Default Chat Color
     */
    String MC_CHAT_SETTINGS_DEFAULTCHATCOLOR = "minecraft:chat.settings.defaultChatColor";

    /**
     * Font
     */
    String MC_CHAT_SETTINGS_FONT = "minecraft:chat.settings.font";

    /**
     * Font Color
     */
    String MC_CHAT_SETTINGS_FONTCOLOR = "minecraft:chat.settings.fontColor";

    /**
     * Size: %s
     */
    String MC_CHAT_SETTINGS_FONTSIZE = "minecraft:chat.settings.fontSize";

    /**
     * Size: Available with %s
     */
    String MC_CHAT_SETTINGS_FONTSIZE_DISABLED = "minecraft:chat.settings.fontSize.disabled";

    /**
     * Line Spacing
     */
    String MC_CHAT_SETTINGS_LINESPACING = "minecraft:chat.settings.lineSpacing";

    /**
     * x%s
     */
    String MC_CHAT_SETTINGS_LINESPACINGNUMBER = "minecraft:chat.settings.lineSpacingNumber";

    /**
     * My Mentions
     */
    String MC_CHAT_SETTINGS_MENTIONS = "minecraft:chat.settings.mentions";

    /**
     * My Mentions Color
     */
    String MC_CHAT_SETTINGS_MENTIONSCOLOR = "minecraft:chat.settings.mentionsColor";

    /**
     * Mute All Chat
     */
    String MC_CHAT_SETTINGS_MUTEALL = "minecraft:chat.settings.muteAll";

    /**
     * Mute Emote Chat
     */
    String MC_CHAT_SETTINGS_MUTEEMOTES = "minecraft:chat.settings.muteEmotes";

    /**
     * Text To Speech For Chat
     */
    String MC_CHAT_SETTINGS_TTS = "minecraft:chat.settings.tts";

    /**
     * Unmute All Chat
     */
    String MC_CHAT_SETTINGS_UNMUTEALL = "minecraft:chat.settings.unmuteAll";

    /**
     * (%s) * %s %s
     */
    String MC_CHAT_STREAM_EMOTE = "minecraft:chat.stream.emote";

    /**
     * (%s) <%s> %s
     */
    String MC_CHAT_STREAM_TEXT = "minecraft:chat.stream.text";

    /**
     * Chat
     */
    String MC_CHAT_TITLE = "minecraft:chat.title";

    /**
     * Chat and Commands
     */
    String MC_CHAT_TITLE_CHEATS = "minecraft:chat.title.cheats";

    /**
     * %s has just earned the achievement %s
     */
    String MC_CHAT_TYPE_ACHIEVEMENT = "minecraft:chat.type.achievement";

    /**
     * %s has lost the achievement %s
     */
    String MC_CHAT_TYPE_ACHIEVEMENT_TAKEN = "minecraft:chat.type.achievement.taken";

    /**
     * [%s: %s]
     */
    String MC_CHAT_TYPE_ADMIN = "minecraft:chat.type.admin";

    /**
     * [%s] %s
     */
    String MC_CHAT_TYPE_ANNOUNCEMENT = "minecraft:chat.type.announcement";

    /**
     * * %s %s
     */
    String MC_CHAT_TYPE_EMOTE = "minecraft:chat.type.emote";

    /**
     * %s is sleeping in a bed. To skip to dawn, %s more players need to sleep in beds at the same time.
     */
    String MC_CHAT_TYPE_SLEEPING = "minecraft:chat.type.sleeping";

    /**
     * <%s> %s
     */
    String MC_CHAT_TYPE_TEXT = "minecraft:chat.type.text";

    /**
     * Large Chest
     */
    String MC_CHESTSCREEN_HEADER_LARGE = "minecraft:chestScreen.header.large";

    /**
     * Inventory
     */
    String MC_CHESTSCREEN_HEADER_PLAYER = "minecraft:chestScreen.header.player";

    /**
     * Chest
     */
    String MC_CHESTSCREEN_HEADER_SMALL = "minecraft:chestScreen.header.small";

    /**
     * Ender Update (%s)
     */
    String MC_CLIENT_VERSION_1_0 = "minecraft:client.version.1.0";

    /**
     * Discovery Update (%s)
     */
    String MC_CLIENT_VERSION_1_1 = "minecraft:client.version.1.1";

    /**
     * Village & Pillage (%s)
     */
    String MC_CLIENT_VERSION_1_11 = "minecraft:client.version.1.11";

    /**
     * Better Together Update (%s)
     */
    String MC_CLIENT_VERSION_1_2 = "minecraft:client.version.1.2";

    /**
     * Update Aquatic (%s)
     */
    String MC_CLIENT_VERSION_1_4 = "minecraft:client.version.1.4";

    /**
     * Update Aquatic (%s)
     */
    String MC_CLIENT_VERSION_1_5 = "minecraft:client.version.1.5";

    /**
     * Update %s
     */
    String MC_CLIENT_VERSION_GENERIC = "minecraft:client.version.generic";

    /**
     * Privacy Statement
     */
    String MC_CLOUD_UPLOAD_BUTTON_PRIVACY_STATEMENT = "minecraft:cloud_upload.button.privacy_statement";

    /**
     * Terms of Use
     */
    String MC_CLOUD_UPLOAD_BUTTON_SERVICE_AGREEMENT = "minecraft:cloud_upload.button.service_agreement";

    /**
     * Upload World (%s)
     */
    String MC_CLOUD_UPLOAD_BUTTON_UPLOAD = "minecraft:cloud_upload.button.upload";

    /**
     * Your world could not be uploaded because %s
     */
    String MC_CLOUD_UPLOAD_FAILED_DESCRIPTION = "minecraft:cloud_upload.failed.description";

    /**
     * Your world could not be uploaded because %s
     */
    String MC_CLOUD_UPLOAD_FAILED_RETRYABLE_DESCRIPTION = "minecraft:cloud_upload.failed.retryable.description";

    /**
     * one of the files is too big. Please get in touch with LiveOps if you want to try again.
     */
    String MC_CLOUD_UPLOAD_INPUT_ERROR_MAXIMUM_SIZE = "minecraft:cloud_upload.input_error_maximum_size";

    /**
     * it consists of too many files. Please get in touch with LiveOps if you want to try again.
     */
    String MC_CLOUD_UPLOAD_INPUT_ERROR_NUMBER_OF_FILES = "minecraft:cloud_upload.input_error_number_of_files";

    /**
     * it has already been successfully uploaded. Please get in touch with LiveOps for more information.
     */
    String MC_CLOUD_UPLOAD_INPUT_ERROR_RECORD_ALREADY_COMPLETED = "minecraft:cloud_upload.input_error_record_already_completed";

    /**
     * of a network error. Please try again or get in touch with LiveOps.
     */
    String MC_CLOUD_UPLOAD_OPERATION_ERROR_MISSING_FILES_ON_COMPLETE_UPLOAD = "minecraft:cloud_upload.operation_error_missing_files_on_complete_upload";

    /**
     * Learn More
     */
    String MC_CLOUD_UPLOAD_PROMPT_BUTTON_LEARN_MORE = "minecraft:cloud_upload.prompt.button.learn_more";

    /**
     * Go to Worlds
     */
    String MC_CLOUD_UPLOAD_PROMPT_BUTTON_WORLDS = "minecraft:cloud_upload.prompt.button.worlds";

    /**
     * Based on your recent support ticket, Mojang Player Support has granted permission to upload your world. You can upload your affected world from the World Select screen or under World Settings.
     */
    String MC_CLOUD_UPLOAD_PROMPT_DESCRIPTION = "minecraft:cloud_upload.prompt.description";

    /**
     * Do not show this screen again
     */
    String MC_CLOUD_UPLOAD_PROMPT_DO_NOT_SHOW_TOGGLE = "minecraft:cloud_upload.prompt.do_not_show_toggle";

    /**
     * Your world upload has completed successfully. Thank you!
     */
    String MC_CLOUD_UPLOAD_SUCCEEDED_DESCRIPTION = "minecraft:cloud_upload.succeeded.description";

    /**
     * To upload your world, accept the Microsoft Services Agreement. Uploaded worlds are subject to the Microsoft Services Agreement, which can be found under Terms of Use. You can still play with your world once uploaded.
     */
    String MC_CLOUD_UPLOAD_TERMS_DESCRIPTION = "minecraft:cloud_upload.terms.description";

    /**
     * Terms & Conditions
     */
    String MC_CLOUD_UPLOAD_TERMS_TITLE = "minecraft:cloud_upload.terms.title";

    /**
     * Mojang Player Support
     */
    String MC_CLOUD_UPLOAD_TITLE = "minecraft:cloud_upload.title";

    /**
     * Accept Microsoft Services Agreement
     */
    String MC_CLOUD_UPLOAD_TOGGLE_ACCEPT_TERMS = "minecraft:cloud_upload.toggle.accept_terms";

    /**
     * Uploading world files
     */
    String MC_CLOUD_UPLOAD_TTS_MESSAGE = "minecraft:cloud_upload.tts.message";

    /**
     * of an unknown error. Please try again or get in touch with LiveOps. (Error code: %s)
     */
    String MC_CLOUD_UPLOAD_UNKNOWN_ERROR = "minecraft:cloud_upload.unknown_error";

    /**
     * Uploading your world files may take a few minutes. Do not turn off the game or leave this screen during the upload process.
     */
    String MC_CLOUD_UPLOAD_UPLOADING_DESCRIPTION_1 = "minecraft:cloud_upload.uploading.description_1";

    /**
     * World sizes can be large. Please connect to WiFi to avoid using mobile data.
     */
    String MC_CLOUD_UPLOAD_UPLOADING_DESCRIPTION_2 = "minecraft:cloud_upload.uploading.description_2";

    /**
     * Uploading world files [%d/%d]
     */
    String MC_CLOUD_UPLOAD_UPLOADING_TITLE = "minecraft:cloud_upload.uploading.title";

    /**
     * Aqua
     */
    String MC_COLOR_AQUA = "minecraft:color.aqua";

    /**
     * Black
     */
    String MC_COLOR_BLACK = "minecraft:color.black";

    /**
     * Blue
     */
    String MC_COLOR_BLUE = "minecraft:color.blue";

    /**
     * Dark Aqua
     */
    String MC_COLOR_DARK_AQUA = "minecraft:color.dark_aqua";

    /**
     * Dark Blue
     */
    String MC_COLOR_DARK_BLUE = "minecraft:color.dark_blue";

    /**
     * Dark Gray
     */
    String MC_COLOR_DARK_GRAY = "minecraft:color.dark_gray";

    /**
     * Dark Green
     */
    String MC_COLOR_DARK_GREEN = "minecraft:color.dark_green";

    /**
     * Dark Purple
     */
    String MC_COLOR_DARK_PURPLE = "minecraft:color.dark_purple";

    /**
     * Dark Red
     */
    String MC_COLOR_DARK_RED = "minecraft:color.dark_red";

    /**
     * Gold
     */
    String MC_COLOR_GOLD = "minecraft:color.gold";

    /**
     * Gray
     */
    String MC_COLOR_GRAY = "minecraft:color.gray";

    /**
     * Green
     */
    String MC_COLOR_GREEN = "minecraft:color.green";

    /**
     * Light Purple
     */
    String MC_COLOR_LIGHT_PURPLE = "minecraft:color.light_purple";

    /**
     * Red
     */
    String MC_COLOR_RED = "minecraft:color.red";

    /**
     * White
     */
    String MC_COLOR_WHITE = "minecraft:color.white";

    /**
     * Yellow
     */
    String MC_COLOR_YELLOW = "minecraft:color.yellow";

    /**
     * Failed to list permissions from file, file not found.
     */
    String MC_COMMAND_PERMISSIONS_LIST_FAIL_FILENOTFOUND = "minecraft:command.permissions.list.fail.filenotfound";

    /**
     * Failed to reload permissions from file, file not found.
     */
    String MC_COMMAND_PERMISSIONS_RELOAD_FAIL_FILENOTFOUND = "minecraft:command.permissions.reload.fail.filenotfound";

    /**
     * Command Block
     */
    String MC_COMMANDBLOCK_GENERICNAME = "minecraft:commandBlock.genericName";

    /**
     * @
     */
    String MC_COMMANDBLOCK_SHORTNAME = "minecraft:commandBlock.shortName";

    /**
     * Block Type:
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE = "minecraft:commandBlockScreen.blockType";

    /**
     * Chain
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE_CHAIN = "minecraft:commandBlockScreen.blockType.chain";

    /**
     * Impulse
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE_IMPULSE = "minecraft:commandBlockScreen.blockType.impulse";

    /**
     * Repeat
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE_REPEAT = "minecraft:commandBlockScreen.blockType.repeat";

    /**
     * Condition:
     */
    String MC_COMMANDBLOCKSCREEN_CONDITION = "minecraft:commandBlockScreen.condition";

    /**
     * Conditional
     */
    String MC_COMMANDBLOCKSCREEN_CONDITION_CONDITIONAL = "minecraft:commandBlockScreen.condition.conditional";

    /**
     * Unconditional
     */
    String MC_COMMANDBLOCKSCREEN_CONDITION_UNCONDITIONAL = "minecraft:commandBlockScreen.condition.unconditional";

    /**
     * O
     */
    String MC_COMMANDBLOCKSCREEN_DISPLAYOUTPUTMODE = "minecraft:commandBlockScreen.displayOutputMode";

    /**
     * Execute on First Tick
     */
    String MC_COMMANDBLOCKSCREEN_EXECUTEFIRSTTICK = "minecraft:commandBlockScreen.executeFirstTick";

    /**
     * X
     */
    String MC_COMMANDBLOCKSCREEN_HIDEOUTPUTMODE = "minecraft:commandBlockScreen.hideOutputMode";

    /**
     * Hover Note
     */
    String MC_COMMANDBLOCKSCREEN_HOVERNOTE = "minecraft:commandBlockScreen.hoverNote";

    /**
     * Redstone:
     */
    String MC_COMMANDBLOCKSCREEN_REDSTONE = "minecraft:commandBlockScreen.redstone";

    /**
     * Always Active
     */
    String MC_COMMANDBLOCKSCREEN_REDSTONE_ALWAYS_ON = "minecraft:commandBlockScreen.redstone.always_on";

    /**
     * Needs Redstone
     */
    String MC_COMMANDBLOCKSCREEN_REDSTONE_NEEDS_REDSTONE = "minecraft:commandBlockScreen.redstone.needs_redstone";

    /**
     * Delay in Ticks:
     */
    String MC_COMMANDBLOCKSCREEN_TICKDELAY = "minecraft:commandBlockScreen.tickDelay";

    /**
     * Command Block
     */
    String MC_COMMANDBLOCKSCREEN_TITLE = "minecraft:commandBlockScreen.title";

    /**
     * Sets a player's ability.
     */
    String MC_COMMANDS_ABILITY_DESCRIPTION = "minecraft:commands.ability.description";

    /**
     * The '%1#s' ability has been granted to you
     */
    String MC_COMMANDS_ABILITY_GRANTED = "minecraft:commands.ability.granted";

    /**
     * No ability called '%1#s' is available
     */
    String MC_COMMANDS_ABILITY_NOABILITY = "minecraft:commands.ability.noability";

    /**
     * The '%1#s' ability has been revoked from you
     */
    String MC_COMMANDS_ABILITY_REVOKED = "minecraft:commands.ability.revoked";

    /**
     * Ability has been updated
     */
    String MC_COMMANDS_ABILITY_SUCCESS = "minecraft:commands.ability.success";

    /**
     * Player %1#s already has achievement %2#s
     */
    String MC_COMMANDS_ACHIEVEMENT_ALREADYHAVE = "minecraft:commands.achievement.alreadyHave";

    /**
     * Gives or removes an achievement from a player.
     */
    String MC_COMMANDS_ACHIEVEMENT_DESCRIPTION = "minecraft:commands.achievement.description";

    /**
     * Player %1#s doesn't have achievement %2#s
     */
    String MC_COMMANDS_ACHIEVEMENT_DONTHAVE = "minecraft:commands.achievement.dontHave";

    /**
     * Successfully given all achievements to %1#s
     */
    String MC_COMMANDS_ACHIEVEMENT_GIVE_SUCCESS_ALL = "minecraft:commands.achievement.give.success.all";

    /**
     * Successfully given %1#s the stat %2#s
     */
    String MC_COMMANDS_ACHIEVEMENT_GIVE_SUCCESS_ONE = "minecraft:commands.achievement.give.success.one";

    /**
     * Player %1#s does not have the stat %2#s
     */
    String MC_COMMANDS_ACHIEVEMENT_STATTOOLOW = "minecraft:commands.achievement.statTooLow";

    /**
     * Successfully taken all achievements from %1#s
     */
    String MC_COMMANDS_ACHIEVEMENT_TAKE_SUCCESS_ALL = "minecraft:commands.achievement.take.success.all";

    /**
     * Successfully taken the stat %1#s from %2#s
     */
    String MC_COMMANDS_ACHIEVEMENT_TAKE_SUCCESS_ONE = "minecraft:commands.achievement.take.success.one";

    /**
     * Unknown achievement or statistic '%1#s'
     */
    String MC_COMMANDS_ACHIEVEMENT_UNKNOWNACHIEVEMENT = "minecraft:commands.achievement.unknownAchievement";

    /**
     * Agent failed to attack
     */
    String MC_COMMANDS_AGENT_ATTACK_FAILED = "minecraft:commands.agent.attack.failed";

    /**
     * Agent attack successful
     */
    String MC_COMMANDS_AGENT_ATTACK_SUCCESS = "minecraft:commands.agent.attack.success";

    /**
     * Agent failed to collect
     */
    String MC_COMMANDS_AGENT_COLLECT_FAILED = "minecraft:commands.agent.collect.failed";

    /**
     * Agent collect successful
     */
    String MC_COMMANDS_AGENT_COLLECT_SUCCESS = "minecraft:commands.agent.collect.success";

    /**
     * Unable to create Agent
     */
    String MC_COMMANDS_AGENT_CREATEAGENT_FAILED = "minecraft:commands.agent.createagent.failed";

    /**
     * Created Agent
     */
    String MC_COMMANDS_AGENT_CREATEAGENT_SUCCESS = "minecraft:commands.agent.createagent.success";

    /**
     * Agent destroy failed
     */
    String MC_COMMANDS_AGENT_DESTROY_FAILED = "minecraft:commands.agent.destroy.failed";

    /**
     * Agent destroyed a block
     */
    String MC_COMMANDS_AGENT_DESTROY_SUCCESS = "minecraft:commands.agent.destroy.success";

    /**
     * Agent failed to detect
     */
    String MC_COMMANDS_AGENT_DETECT_FAILED = "minecraft:commands.agent.detect.failed";

    /**
     * Agent detect successful
     */
    String MC_COMMANDS_AGENT_DETECT_SUCCESS = "minecraft:commands.agent.detect.success";

    /**
     * Agent failed to detectredstone
     */
    String MC_COMMANDS_AGENT_DETECTREDSTONE_FAILED = "minecraft:commands.agent.detectredstone.failed";

    /**
     * Agent detectredstone successful
     */
    String MC_COMMANDS_AGENT_DETECTREDSTONE_SUCCESS = "minecraft:commands.agent.detectredstone.success";

    /**
     * Agent failed to drop
     */
    String MC_COMMANDS_AGENT_DROP_FAILED = "minecraft:commands.agent.drop.failed";

    /**
     * Agent drop successful
     */
    String MC_COMMANDS_AGENT_DROP_SUCCESS = "minecraft:commands.agent.drop.success";

    /**
     * Agent failed to dropall
     */
    String MC_COMMANDS_AGENT_DROPALL_FAILED = "minecraft:commands.agent.dropall.failed";

    /**
     * Agent dropall successful
     */
    String MC_COMMANDS_AGENT_DROPALL_SUCCESS = "minecraft:commands.agent.dropall.success";

    /**
     * Agent failed to getitemcount
     */
    String MC_COMMANDS_AGENT_GETITEMCOUNT_FAILED = "minecraft:commands.agent.getitemcount.failed";

    /**
     * Agent getitemcount successful
     */
    String MC_COMMANDS_AGENT_GETITEMCOUNT_SUCCESS = "minecraft:commands.agent.getitemcount.success";

    /**
     * Agent failed to getitemdetail
     */
    String MC_COMMANDS_AGENT_GETITEMDETAIL_FAILED = "minecraft:commands.agent.getitemdetail.failed";

    /**
     * Agent getitemdetail successful
     */
    String MC_COMMANDS_AGENT_GETITEMDETAIL_SUCCESS = "minecraft:commands.agent.getitemdetail.success";

    /**
     * Agent failed to getitemspace
     */
    String MC_COMMANDS_AGENT_GETITEMSPACE_FAILED = "minecraft:commands.agent.getitemspace.failed";

    /**
     * Agent getitemspace successful
     */
    String MC_COMMANDS_AGENT_GETITEMSPACE_SUCCESS = "minecraft:commands.agent.getitemspace.success";

    /**
     * Agent getposition failed
     */
    String MC_COMMANDS_AGENT_GETPOSITION_FAILED = "minecraft:commands.agent.getposition.failed";

    /**
     * Agent getposition successful
     */
    String MC_COMMANDS_AGENT_GETPOSITION_SUCCESS = "minecraft:commands.agent.getposition.success";

    /**
     * Agent failed to inspect
     */
    String MC_COMMANDS_AGENT_INSPECT_FAILED = "minecraft:commands.agent.inspect.failed";

    /**
     * Agent inspect successful
     */
    String MC_COMMANDS_AGENT_INSPECT_SUCCESS = "minecraft:commands.agent.inspect.success";

    /**
     * Agent failed to inspect data
     */
    String MC_COMMANDS_AGENT_INSPECTDATA_FAILED = "minecraft:commands.agent.inspectdata.failed";

    /**
     * Agent inspect data successful
     */
    String MC_COMMANDS_AGENT_INSPECTDATA_SUCCESS = "minecraft:commands.agent.inspectdata.success";

    /**
     * Unable to move Agent
     */
    String MC_COMMANDS_AGENT_MOVE_FAILED = "minecraft:commands.agent.move.failed";

    /**
     * Agent move successful
     */
    String MC_COMMANDS_AGENT_MOVE_SUCCESS = "minecraft:commands.agent.move.success";

    /**
     * Cannot issue command, Agent is out of range
     */
    String MC_COMMANDS_AGENT_OUTOFRANGE = "minecraft:commands.agent.outofrange";

    /**
     * Agent failed to place
     */
    String MC_COMMANDS_AGENT_PLACE_FAILED = "minecraft:commands.agent.place.failed";

    /**
     * Agent place successful
     */
    String MC_COMMANDS_AGENT_PLACE_SUCCESS = "minecraft:commands.agent.place.success";

    /**
     * Agent failed to set item
     */
    String MC_COMMANDS_AGENT_SETITEM_FAILED = "minecraft:commands.agent.setitem.failed";

    /**
     * Agent set item successful
     */
    String MC_COMMANDS_AGENT_SETITEM_SUCCESS = "minecraft:commands.agent.setitem.success";

    /**
     * Agent failed to till
     */
    String MC_COMMANDS_AGENT_TILL_FAILED = "minecraft:commands.agent.till.failed";

    /**
     * Agent till successful
     */
    String MC_COMMANDS_AGENT_TILL_SUCCESS = "minecraft:commands.agent.till.success";

    /**
     * Teleport your Agent.
     */
    String MC_COMMANDS_AGENT_TPAGENT_DESCRIPTION = "minecraft:commands.agent.tpagent.description";

    /**
     * Agent failed to teleport
     */
    String MC_COMMANDS_AGENT_TPAGENT_FAILED = "minecraft:commands.agent.tpagent.failed";

    /**
     * Agent teleported
     */
    String MC_COMMANDS_AGENT_TPAGENT_SUCCESS = "minecraft:commands.agent.tpagent.success";

    /**
     * Agent failed to transfer
     */
    String MC_COMMANDS_AGENT_TRANSFER_FAILED = "minecraft:commands.agent.transfer.failed";

    /**
     * Agent transfer successful
     */
    String MC_COMMANDS_AGENT_TRANSFER_SUCCESS = "minecraft:commands.agent.transfer.success";

    /**
     * Unable to turn Agent
     */
    String MC_COMMANDS_AGENT_TURN_FAILED = "minecraft:commands.agent.turn.failed";

    /**
     * Agent turn successful
     */
    String MC_COMMANDS_AGENT_TURN_SUCCESS = "minecraft:commands.agent.turn.success";

    /**
     * Enable Aim Assist
     */
    String MC_COMMANDS_AIMASSIST_DESCRIPTION = "minecraft:commands.aimassist.description";

    /**
     * Invalid aim assist preset name. Preset name should be in the format "namespace:name" or "name" (defaults to "minecraft" namespace)
     */
    String MC_COMMANDS_AIMASSIST_ERROR_INVALIDNAMESPACE = "minecraft:commands.aimassist.error.invalidNamespace";

    /**
     * Unknown aim assist preset name
     */
    String MC_COMMANDS_AIMASSIST_ERROR_UNKNOWNPRESET = "minecraft:commands.aimassist.error.unknownPreset";

    /**
     * Aim assist distance must be between 1 and 16
     */
    String MC_COMMANDS_AIMASSIST_INVALIDDISTANCE = "minecraft:commands.aimassist.invaliddistance";

    /**
     * Aim assist X angle must be between 10 and 90
     */
    String MC_COMMANDS_AIMASSIST_INVALIDXANGLE = "minecraft:commands.aimassist.invalidxangle";

    /**
     * Aim assist Y angle must be between 10 and 90
     */
    String MC_COMMANDS_AIMASSIST_INVALIDYANGLE = "minecraft:commands.aimassist.invalidyangle";

    /**
     * Applied aim assist settings to the following players: %s
     */
    String MC_COMMANDS_AIMASSIST_SUCCESS = "minecraft:commands.aimassist.success";

    /**
     * Current camera type does not support aim assist. The following types are supported: third_person, fixed_boom, follow_orbit, free
     */
    String MC_COMMANDS_AIMASSIST_UNSUPPORTEDCAMERATYPE = "minecraft:commands.aimassist.unsupportedCameraType";

    /**
     * Could not add %1#s to the allowlist
     */
    String MC_COMMANDS_ALLOWLIST_ADD_FAILED = "minecraft:commands.allowlist.add.failed";

    /**
     * Added %1#s to the allowlist
     */
    String MC_COMMANDS_ALLOWLIST_ADD_SUCCESS = "minecraft:commands.allowlist.add.success";

    /**
     * Manages the server allowlist.
     */
    String MC_COMMANDS_ALLOWLIST_DESCRIPTION = "minecraft:commands.allowlist.description";

    /**
     * Turned off the allowlist
     */
    String MC_COMMANDS_ALLOWLIST_DISABLED = "minecraft:commands.allowlist.disabled";

    /**
     * Turned on the allowlist
     */
    String MC_COMMANDS_ALLOWLIST_ENABLED = "minecraft:commands.allowlist.enabled";

    /**
     * There are %1#d (out of %2#d seen) allowlisted players:
     */
    String MC_COMMANDS_ALLOWLIST_LIST = "minecraft:commands.allowlist.list";

    /**
     * Allowlist reloaded from file.
     */
    String MC_COMMANDS_ALLOWLIST_RELOADED = "minecraft:commands.allowlist.reloaded";

    /**
     * Could not remove %1#s from the allowlist
     */
    String MC_COMMANDS_ALLOWLIST_REMOVE_FAILED = "minecraft:commands.allowlist.remove.failed";

    /**
     * Removed %1#s from the allowlist
     */
    String MC_COMMANDS_ALLOWLIST_REMOVE_SUCCESS = "minecraft:commands.allowlist.remove.success";

    /**
     * Day-Night cycle %1#s
     */
    String MC_COMMANDS_ALWAYS_DAY = "minecraft:commands.always.day";

    /**
     * Day-Night cycle locked
     */
    String MC_COMMANDS_ALWAYS_DAY_LOCKED = "minecraft:commands.always.day.locked";

    /**
     * Day-Night cycle unlocked
     */
    String MC_COMMANDS_ALWAYS_DAY_UNLOCKED = "minecraft:commands.always.day.unlocked";

    /**
     * all players
     */
    String MC_COMMANDS_AUTOCOMPLETE_A = "minecraft:commands.autocomplete.a";

    /**
     * my Agent
     */
    String MC_COMMANDS_AUTOCOMPLETE_C = "minecraft:commands.autocomplete.c";

    /**
     * all entities
     */
    String MC_COMMANDS_AUTOCOMPLETE_E = "minecraft:commands.autocomplete.e";

    /**
     * nearest entity
     */
    String MC_COMMANDS_AUTOCOMPLETE_N = "minecraft:commands.autocomplete.n";

    /**
     * closest player
     */
    String MC_COMMANDS_AUTOCOMPLETE_P = "minecraft:commands.autocomplete.p";

    /**
     * random player
     */
    String MC_COMMANDS_AUTOCOMPLETE_R = "minecraft:commands.autocomplete.r";

    /**
     * yourself
     */
    String MC_COMMANDS_AUTOCOMPLETE_S = "minecraft:commands.autocomplete.s";

    /**
     * all Agents
     */
    String MC_COMMANDS_AUTOCOMPLETE_V = "minecraft:commands.autocomplete.v";

    /**
     * Adds player to banlist.
     */
    String MC_COMMANDS_BAN_DESCRIPTION = "minecraft:commands.ban.description";

    /**
     * Could not ban player %1#s
     */
    String MC_COMMANDS_BAN_FAILED = "minecraft:commands.ban.failed";

    /**
     * Banned player %1#s
     */
    String MC_COMMANDS_BAN_SUCCESS = "minecraft:commands.ban.success";

    /**
     * Adds IP address to banlist.
     */
    String MC_COMMANDS_BANIP_DESCRIPTION = "minecraft:commands.banip.description";

    /**
     * You have entered an invalid IP address or a player that is not online
     */
    String MC_COMMANDS_BANIP_INVALID = "minecraft:commands.banip.invalid";

    /**
     * Banned IP address %1#s
     */
    String MC_COMMANDS_BANIP_SUCCESS = "minecraft:commands.banip.success";

    /**
     * Banned IP address %1#s belonging to %2#s
     */
    String MC_COMMANDS_BANIP_SUCCESS_PLAYERS = "minecraft:commands.banip.success.players";

    /**
     * There are %1#d total banned IP addresses:
     */
    String MC_COMMANDS_BANLIST_IPS = "minecraft:commands.banlist.ips";

    /**
     * There are %1#d total banned players:
     */
    String MC_COMMANDS_BANLIST_PLAYERS = "minecraft:commands.banlist.players";

    /**
     * Modifies the data tag of a block.
     */
    String MC_COMMANDS_BLOCKDATA_DESCRIPTION = "minecraft:commands.blockdata.description";

    /**
     * You cannot dig here
     */
    String MC_COMMANDS_BLOCKDATA_DESTROYFAILED = "minecraft:commands.blockdata.destroyFailed";

    /**
     * The data tag did not change: %1#s
     */
    String MC_COMMANDS_BLOCKDATA_FAILED = "minecraft:commands.blockdata.failed";

    /**
     * The target block is not a data holder block
     */
    String MC_COMMANDS_BLOCKDATA_NOTVALID = "minecraft:commands.blockdata.notValid";

    /**
     * Cannot change block outside of the world
     */
    String MC_COMMANDS_BLOCKDATA_OUTOFWORLD = "minecraft:commands.blockdata.outOfWorld";

    /**
     * You cannot place blocks here
     */
    String MC_COMMANDS_BLOCKDATA_PLACEFAILED = "minecraft:commands.blockdata.placeFailed";

    /**
     * Block data updated to: %1#s
     */
    String MC_COMMANDS_BLOCKDATA_SUCCESS = "minecraft:commands.blockdata.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_BLOCKDATA_TAGERROR = "minecraft:commands.blockdata.tagError";

    /**
     * '%1#s' is not a valid Block State.
     */
    String MC_COMMANDS_BLOCKSTATE_INVALIDSTATE = "minecraft:commands.blockstate.invalidState";

    /**
     * Block State: '%1#s' is invalid on Block: '%2#s'.
     */
    String MC_COMMANDS_BLOCKSTATE_STATEERROR = "minecraft:commands.blockstate.stateError";

    /**
     * Invalid value type for Block State: '%1#s'.
     */
    String MC_COMMANDS_BLOCKSTATE_TYPEERROR = "minecraft:commands.blockstate.typeError";

    /**
     * Invalid value for Block State: '%1#s'.
     */
    String MC_COMMANDS_BLOCKSTATE_VALUEERROR = "minecraft:commands.blockstate.valueError";

    /**
     * A bossbar already exists with the ID '%1#s'
     */
    String MC_COMMANDS_BOSSBAR_ADD_FAILURE_EXISTS = "minecraft:commands.bossbar.add.failure.exists";

    /**
     * Invalid bossbar ID. IDs should be of the form namespace:id or id (defaults to minecraft namespace).
     */
    String MC_COMMANDS_BOSSBAR_ADD_FAILURE_INVALID = "minecraft:commands.bossbar.add.failure.invalid";

    /**
     * Created custom bossbar [%1#s]
     */
    String MC_COMMANDS_BOSSBAR_ADD_SUCCESS = "minecraft:commands.bossbar.add.success";

    /**
     * Creates and modifies boss bars
     */
    String MC_COMMANDS_BOSSBAR_DESCRIPTION = "minecraft:commands.bossbar.description";

    /**
     * Custom bossbar [%1#s] has a maximum of %2#d
     */
    String MC_COMMANDS_BOSSBAR_GET_MAX = "minecraft:commands.bossbar.get.max";

    /**
     * Custom bossbar [%1#s] has %2#s players currently online: %3#s
     */
    String MC_COMMANDS_BOSSBAR_GET_PLAYERS = "minecraft:commands.bossbar.get.players";

    /**
     * Custom bossbar [%1#s] has no players currently online
     */
    String MC_COMMANDS_BOSSBAR_GET_PLAYERS_NONE = "minecraft:commands.bossbar.get.players.none";

    /**
     * Custom bossbar [%1#s] has 1 player currently online: %2#s
     */
    String MC_COMMANDS_BOSSBAR_GET_PLAYERS_ONE = "minecraft:commands.bossbar.get.players.one";

    /**
     * Custom bossbar [%1#s] has a value of %2#d
     */
    String MC_COMMANDS_BOSSBAR_GET_VALUE = "minecraft:commands.bossbar.get.value";

    /**
     * Custom bossbar [%1#s] is currently hidden
     */
    String MC_COMMANDS_BOSSBAR_GET_VISIBLE_FALSE = "minecraft:commands.bossbar.get.visible.false";

    /**
     * Custom bossbar [%1#s] is currently shown
     */
    String MC_COMMANDS_BOSSBAR_GET_VISIBLE_TRUE = "minecraft:commands.bossbar.get.visible.true";

    /**
     * There are %1#s custom bossbars active: %2#s
     */
    String MC_COMMANDS_BOSSBAR_LIST = "minecraft:commands.bossbar.list";

    /**
     * There are no custom bossbars active
     */
    String MC_COMMANDS_BOSSBAR_LIST_NONE = "minecraft:commands.bossbar.list.none";

    /**
     * There is 1 custom bossbar active: %1#s
     */
    String MC_COMMANDS_BOSSBAR_LIST_ONE = "minecraft:commands.bossbar.list.one";

    /**
     * No bossbar exists with the ID '%1#s'
     */
    String MC_COMMANDS_BOSSBAR_NOTFOUND = "minecraft:commands.bossbar.notFound";

    /**
     * Removed custom bossbar [%1#s]
     */
    String MC_COMMANDS_BOSSBAR_REMOVE = "minecraft:commands.bossbar.remove";

    /**
     * Issues a camera instruction
     */
    String MC_COMMANDS_CAMERA_DESCRIPTION = "minecraft:commands.camera.description";

    /**
     * Invalid camera command mode
     */
    String MC_COMMANDS_CAMERA_INVALID = "minecraft:commands.camera.invalid";

    /**
     * Fade color values must be integers from 0 to 255
     */
    String MC_COMMANDS_CAMERA_INVALID_COLOR = "minecraft:commands.camera.invalid.color";

    /**
     * Easing to non-stationary cameras is not supported
     */
    String MC_COMMANDS_CAMERA_INVALID_EASING = "minecraft:commands.camera.invalid.easing";

    /**
     * Entity offset must be between -64 and 64
     */
    String MC_COMMANDS_CAMERA_INVALID_ENTITY_OFFSET = "minecraft:commands.camera.invalid.entity.offset";

    /**
     * Entity and view offset can only be applied to the follow_orbit camera
     */
    String MC_COMMANDS_CAMERA_INVALID_OFFSET_CAMERA = "minecraft:commands.camera.invalid.offset.camera";

    /**
     * Camera pitch (X rotation) must be between -90 and 90 degrees
     */
    String MC_COMMANDS_CAMERA_INVALID_PITCH = "minecraft:commands.camera.invalid.pitch";

    /**
     * Fade durations must be between 0 and 10 seconds
     */
    String MC_COMMANDS_CAMERA_INVALID_TIME = "minecraft:commands.camera.invalid.time";

    /**
     * View offset must be between -64 and 64
     */
    String MC_COMMANDS_CAMERA_INVALID_VIEW_OFFSET = "minecraft:commands.camera.invalid.view.offset";

    /**
     * Perspective cannot be changed while using custom cameras
     */
    String MC_COMMANDS_CAMERA_PERSPECTIVE_LOCKED = "minecraft:commands.camera.perspective.locked";

    /**
     * Your selected camera will change when the custom camera is not active
     */
    String MC_COMMANDS_CAMERA_PERSPECTIVE_OPTION = "minecraft:commands.camera.perspective.option";

    /**
     * Sent a camera instruction to the following players: %s
     */
    String MC_COMMANDS_CAMERA_SUCCESS = "minecraft:commands.camera.success";

    /**
     * Unknown camera preset name
     */
    String MC_COMMANDS_CAMERA_UNKNOWN_PRESET = "minecraft:commands.camera.unknown.preset";

    /**
     * Changes a setting on the dedicated server while it's running.
     */
    String MC_COMMANDS_CHANGE_SETTING_DESCRIPTION = "minecraft:commands.change-setting.description";

    /**
     * %1#s has been changed
     */
    String MC_COMMANDS_CHANGE_SETTING_SUCCESS = "minecraft:commands.change-setting.success";

    /**
     * Chunk is compiled.
     */
    String MC_COMMANDS_CHUNKINFO_COMPILED = "minecraft:commands.chunkinfo.compiled";

    /**
     * First 64 vertices are: %1#s
     */
    String MC_COMMANDS_CHUNKINFO_DATA = "minecraft:commands.chunkinfo.data";

    /**
     * Chunk is empty.
     */
    String MC_COMMANDS_CHUNKINFO_EMPTY = "minecraft:commands.chunkinfo.empty";

    /**
     * Chunk has layers: %1#s
     */
    String MC_COMMANDS_CHUNKINFO_HASLAYERS = "minecraft:commands.chunkinfo.hasLayers";

    /**
     * Chunk has no renderable layers.
     */
    String MC_COMMANDS_CHUNKINFO_HASNORENDERABLELAYERS = "minecraft:commands.chunkinfo.hasNoRenderableLayers";

    /**
     * Chunk has empty layers: %1#s
     */
    String MC_COMMANDS_CHUNKINFO_ISEMPTY = "minecraft:commands.chunkinfo.isEmpty";

    /**
     * Chunk location: (%1#d, %2#d, %3#d)
     */
    String MC_COMMANDS_CHUNKINFO_LOCATION = "minecraft:commands.chunkinfo.location";

    /**
     * No chunk found at chunk position %1#d, %2#d, %3#d
     */
    String MC_COMMANDS_CHUNKINFO_NOCHUNK = "minecraft:commands.chunkinfo.noChunk";

    /**
     * Chunk is not compiled.
     */
    String MC_COMMANDS_CHUNKINFO_NOTCOMPILED = "minecraft:commands.chunkinfo.notCompiled";

    /**
     * Chunk is not empty.
     */
    String MC_COMMANDS_CHUNKINFO_NOTEMPTY = "minecraft:commands.chunkinfo.notEmpty";

    /**
     * %1#s layer's buffer contains %2#d vertices
     */
    String MC_COMMANDS_CHUNKINFO_VERTICES = "minecraft:commands.chunkinfo.vertices";

    /**
     * Attempt to launch and connect to Classroom Mode.
     */
    String MC_COMMANDS_CLASSROOMMODE_DESCRIPTION = "minecraft:commands.classroommode.description";

    /**
     * Attempting to launch Classroom Mode...
     */
    String MC_COMMANDS_CLASSROOMMODE_SUCCESS = "minecraft:commands.classroommode.success";

    /**
     * Clears items from player inventory.
     */
    String MC_COMMANDS_CLEAR_DESCRIPTION = "minecraft:commands.clear.description";

    /**
     * Could not clear the inventory of %1#s
     */
    String MC_COMMANDS_CLEAR_FAILURE = "minecraft:commands.clear.failure";

    /**
     * Could not clear the inventory of %1#s, no items to remove
     */
    String MC_COMMANDS_CLEAR_FAILURE_NO_ITEMS = "minecraft:commands.clear.failure.no.items";

    /**
     * Cleared the inventory of %1#s, removing %2#d items
     */
    String MC_COMMANDS_CLEAR_SUCCESS = "minecraft:commands.clear.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_CLEAR_TAGERROR = "minecraft:commands.clear.tagError";

    /**
     * %1#s has %2#d items that match the criteria
     */
    String MC_COMMANDS_CLEAR_TESTING = "minecraft:commands.clear.testing";

    /**
     * Removes all Fixed Inventory Slots.
     */
    String MC_COMMANDS_CLEARFIXEDINV_DESCRIPTION = "minecraft:commands.clearfixedinv.description";

    /**
     * Cleared the Fixed Inventory
     */
    String MC_COMMANDS_CLEARFIXEDINV_SUCCESS = "minecraft:commands.clearfixedinv.success";

    /**
     * Clears all Realm Events stored in the world data.
     */
    String MC_COMMANDS_CLEARREALMEVENTS_DESCRIPTION = "minecraft:commands.clearrealmevents.description";

    /**
     * Clearing all Realm Events
     */
    String MC_COMMANDS_CLEARREALMEVENTS_START = "minecraft:commands.clearrealmevents.start";

    /**
     * Removes the spawn point for a player.
     */
    String MC_COMMANDS_CLEARSPAWNPOINT_DESCRIPTION = "minecraft:commands.clearspawnpoint.description";

    /**
     * Removed spawn points for %1#s
     */
    String MC_COMMANDS_CLEARSPAWNPOINT_SUCCESS_MULTIPLE = "minecraft:commands.clearspawnpoint.success.multiple";

    /**
     * Removed %1#s's spawn point
     */
    String MC_COMMANDS_CLEARSPAWNPOINT_SUCCESS_SINGLE = "minecraft:commands.clearspawnpoint.success.single";

    /**
     * Clones blocks from one region to another.
     */
    String MC_COMMANDS_CLONE_DESCRIPTION = "minecraft:commands.clone.description";

    /**
     * No blocks cloned
     */
    String MC_COMMANDS_CLONE_FAILED = "minecraft:commands.clone.failed";

    /**
     * Filtered usage requires a filter block to be specified
     */
    String MC_COMMANDS_CLONE_FILTERED_ERROR = "minecraft:commands.clone.filtered.error";

    /**
     * Source and destination can not overlap
     */
    String MC_COMMANDS_CLONE_NOOVERLAP = "minecraft:commands.clone.noOverlap";

    /**
     * %1#d blocks cloned
     */
    String MC_COMMANDS_CLONE_SUCCESS = "minecraft:commands.clone.success";

    /**
     * Too many blocks in the specified area (%1#d > %2#d)
     */
    String MC_COMMANDS_CLONE_TOOMANYBLOCKS = "minecraft:commands.clone.tooManyBlocks";

    /**
     * Closes the chat window of the local player if it is open.
     */
    String MC_COMMANDS_CLOSECHAT_DESCRIPTION = "minecraft:commands.closechat.description";

    /**
     * Chat was not open
     */
    String MC_COMMANDS_CLOSECHAT_FAILURE = "minecraft:commands.closechat.failure";

    /**
     * Chat closed
     */
    String MC_COMMANDS_CLOSECHAT_SUCCESS = "minecraft:commands.closechat.success";

    /**
     * Closes websocket connection if there is one.
     */
    String MC_COMMANDS_CLOSEWEBSOCKET_DESCRIPTION = "minecraft:commands.closewebsocket.description";

    /**
     * Launches Code Builder.
     */
    String MC_COMMANDS_CODE_DESCRIPTION = "minecraft:commands.code.description";

    /**
     * Launched Code Builder.
     */
    String MC_COMMANDS_CODE_SUCCESS = "minecraft:commands.code.success";

    /**
     * Explicitly compacts the levelDB log.
     */
    String MC_COMMANDS_COMPACTDB_DESCRIPTION = "minecraft:commands.compactdb.description";

    /**
     * Successfully compacted levelDB log.
     */
    String MC_COMMANDS_COMPACTDB_SUCCESS = "minecraft:commands.compactdb.success";

    /**
     * Source and destination are not identical
     */
    String MC_COMMANDS_COMPARE_FAILED = "minecraft:commands.compare.failed";

    /**
     * %1#d blocks compared
     */
    String MC_COMMANDS_COMPARE_SUCCESS = "minecraft:commands.compare.success";

    /**
     * Too many blocks in the specified area (%1#d > %2#d)
     */
    String MC_COMMANDS_COMPARE_TOOMANYBLOCKS = "minecraft:commands.compare.tooManyBlocks";

    /**
     * Sets or clears control scheme.
     */
    String MC_COMMANDS_CONTROLSCHEME_DESCRIPTION = "minecraft:commands.controlscheme.description";

    /**
     * Failed to apply control scheme to the following player(s): %s
     */
    String MC_COMMANDS_CONTROLSCHEME_FAILED = "minecraft:commands.controlscheme.failed";

    /**
     * Applied control scheme to the following player(s): %s
     */
    String MC_COMMANDS_CONTROLSCHEME_SUCCESS = "minecraft:commands.controlscheme.success";

    /**
     * Corrupts the world loaded on the server.
     */
    String MC_COMMANDS_CORRUPTWORLD_DESCRIPTION = "minecraft:commands.corruptworld.description";

    /**
     * Successfully corrupted the world.
     */
    String MC_COMMANDS_CORRUPTWORLD_SUCCESS = "minecraft:commands.corruptworld.success";

    /**
     * Apply damage to the specified entities.
     */
    String MC_COMMANDS_DAMAGE_DESCRIPTION = "minecraft:commands.damage.description";

    /**
     * Could not apply damage to %1#s
     */
    String MC_COMMANDS_DAMAGE_FAILED = "minecraft:commands.damage.failed";

    /**
     * Please specify a valid damage value.
     */
    String MC_COMMANDS_DAMAGE_SPECIFY_DAMAGE = "minecraft:commands.damage.specify.damage";

    /**
     * Applied damage to %1#s
     */
    String MC_COMMANDS_DAMAGE_SUCCESS = "minecraft:commands.damage.success";

    /**
     * There can only be one source entity. Please adjust your selector to limit selection to one entity.
     */
    String MC_COMMANDS_DAMAGE_TOOMANYSOURCES = "minecraft:commands.damage.tooManySources";

    /**
     * Locks and unlocks the day-night cycle.
     */
    String MC_COMMANDS_DAYLOCK_DESCRIPTION = "minecraft:commands.daylock.description";

    /**
     * Starts or stops a debugging session.
     */
    String MC_COMMANDS_DEBUG_DESCRIPTION = "minecraft:commands.debug.description";

    /**
     * Can't stop profiling when we haven't started yet!
     */
    String MC_COMMANDS_DEBUG_NOTSTARTED = "minecraft:commands.debug.notStarted";

    /**
     * Started debug profiling
     */
    String MC_COMMANDS_DEBUG_START = "minecraft:commands.debug.start";

    /**
     * Stopped debug profiling after %.2f seconds (%1#d ticks)
     */
    String MC_COMMANDS_DEBUG_STOP = "minecraft:commands.debug.stop";

    /**
     * Sets the default game mode.
     */
    String MC_COMMANDS_DEFAULTGAMEMODE_DESCRIPTION = "minecraft:commands.defaultgamemode.description";

    /**
     * The world's default game mode is now %1#s
     */
    String MC_COMMANDS_DEFAULTGAMEMODE_SUCCESS = "minecraft:commands.defaultgamemode.success";

    /**
     * Revokes operator status from a player.
     */
    String MC_COMMANDS_DEOP_DESCRIPTION = "minecraft:commands.deop.description";

    /**
     * Could not de-op (permission level too high): %s
     */
    String MC_COMMANDS_DEOP_FAILED = "minecraft:commands.deop.failed";

    /**
     * You have been de-opped
     */
    String MC_COMMANDS_DEOP_MESSAGE = "minecraft:commands.deop.message";

    /**
     * De-opped: %s
     */
    String MC_COMMANDS_DEOP_SUCCESS = "minecraft:commands.deop.success";

    /**
     * Failed to change the scene for the NPC.
     */
    String MC_COMMANDS_DIALOGUE_CHANGEFAILED = "minecraft:commands.dialogue.changeFailed";

    /**
     * Dialogue successfully changed NPC text just for %1#s.
     */
    String MC_COMMANDS_DIALOGUE_CHANGEPERPLAYERSUCCESS = "minecraft:commands.dialogue.changePerPlayerSuccess";

    /**
     * Dialogue successfully changed the scene for the NPC globally.
     */
    String MC_COMMANDS_DIALOGUE_CHANGESUCCESS = "minecraft:commands.dialogue.changeSuccess";

    /**
     * Opens NPC dialogue for a player.
     */
    String MC_COMMANDS_DIALOGUE_DESCRIPTION = "minecraft:commands.dialogue.description";

    /**
     * Invalid scene was referenced.
     */
    String MC_COMMANDS_DIALOGUE_INVALIDSCENE = "minecraft:commands.dialogue.invalidScene";

    /**
     * Selected actor did not have an NPC component.
     */
    String MC_COMMANDS_DIALOGUE_NONPCCOMPONENT = "minecraft:commands.dialogue.noNpcComponent";

    /**
     * Failed to find an NPC.
     */
    String MC_COMMANDS_DIALOGUE_NPCNOTFOUND = "minecraft:commands.dialogue.npcNotFound";

    /**
     * An NPC was found, but was not in the receiving player %1#s's ticking range.
     */
    String MC_COMMANDS_DIALOGUE_NPCNOTINRANGE = "minecraft:commands.dialogue.npcNotInRange";

    /**
     * Dialogue sent to %1#s.
     */
    String MC_COMMANDS_DIALOGUE_SUCCESS = "minecraft:commands.dialogue.success";

    /**
     * Selector for NPCs was ambiguous and resulted in too many selected. Please select only one NPC.
     */
    String MC_COMMANDS_DIALOGUE_TOOMANYNPCS = "minecraft:commands.dialogue.tooManyNPCs";

    /**
     * Sets the difficulty level.
     */
    String MC_COMMANDS_DIFFICULTY_DESCRIPTION = "minecraft:commands.difficulty.description";

    /**
     * Set game difficulty to %1#s
     */
    String MC_COMMANDS_DIFFICULTY_SUCCESS = "minecraft:commands.difficulty.success";

    /**
     * /difficulty <new difficulty>
     */
    String MC_COMMANDS_DIFFICULTY_USAGE = "minecraft:commands.difficulty.usage";

    /**
     * Toggled downfall
     */
    String MC_COMMANDS_DOWNFALL_SUCCESS = "minecraft:commands.downfall.success";

    /**
     * Get tenant ID and host status. Intended for CM.
     */
    String MC_COMMANDS_EDUCLIENTINFO_DESCRIPTION = "minecraft:commands.educlientinfo.description";

    /**
     * Add or remove status effects.
     */
    String MC_COMMANDS_EFFECT_DESCRIPTION = "minecraft:commands.effect.description";

    /**
     * %1#s cannot have effects
     */
    String MC_COMMANDS_EFFECT_FAILURE_NOTAMOB = "minecraft:commands.effect.failure.notAMob";

    /**
     * Couldn't take %1#s from %2#s as they do not have the effect
     */
    String MC_COMMANDS_EFFECT_FAILURE_NOTACTIVE = "minecraft:commands.effect.failure.notActive";

    /**
     * Couldn't take any effects from %1#s as they do not have any
     */
    String MC_COMMANDS_EFFECT_FAILURE_NOTACTIVE_ALL = "minecraft:commands.effect.failure.notActive.all";

    /**
     * There is no such mob effect with ID %s
     */
    String MC_COMMANDS_EFFECT_NOTFOUND = "minecraft:commands.effect.notFound";

    /**
     * Gave %1#s * %2#d to %3#s for %4#d seconds
     */
    String MC_COMMANDS_EFFECT_SUCCESS = "minecraft:commands.effect.success";

    /**
     * Gave %1#s * %2#d to %3#s for an infinite duration
     */
    String MC_COMMANDS_EFFECT_SUCCESS_INFINITE = "minecraft:commands.effect.success.infinite";

    /**
     * Took %1#s from %2#s
     */
    String MC_COMMANDS_EFFECT_SUCCESS_REMOVED = "minecraft:commands.effect.success.removed";

    /**
     * Took all effects from %1#s
     */
    String MC_COMMANDS_EFFECT_SUCCESS_REMOVED_ALL = "minecraft:commands.effect.success.removed.all";

    /**
     * %1#s can't be combined with %2#s
     */
    String MC_COMMANDS_ENCHANT_CANTCOMBINE = "minecraft:commands.enchant.cantCombine";

    /**
     * The selected enchantment can't be added to the target item: %1#s
     */
    String MC_COMMANDS_ENCHANT_CANTENCHANT = "minecraft:commands.enchant.cantEnchant";

    /**
     * Adds an enchantment to a player's selected item.
     */
    String MC_COMMANDS_ENCHANT_DESCRIPTION = "minecraft:commands.enchant.description";

    /**
     * %1#s does not support level %2#d
     */
    String MC_COMMANDS_ENCHANT_INVALIDLEVEL = "minecraft:commands.enchant.invalidLevel";

    /**
     * The target doesn't hold an item: %1#s
     */
    String MC_COMMANDS_ENCHANT_NOITEM = "minecraft:commands.enchant.noItem";

    /**
     * There is no such enchantment with ID %1#d
     */
    String MC_COMMANDS_ENCHANT_NOTFOUND = "minecraft:commands.enchant.notFound";

    /**
     * Enchanting succeeded for %1#s
     */
    String MC_COMMANDS_ENCHANT_SUCCESS = "minecraft:commands.enchant.success";

    /**
     * Modifies the data tag of an entity.
     */
    String MC_COMMANDS_ENTITYDATA_DESCRIPTION = "minecraft:commands.entitydata.description";

    /**
     * The data tag did not change: %1#s
     */
    String MC_COMMANDS_ENTITYDATA_FAILED = "minecraft:commands.entitydata.failed";

    /**
     * %1#s is a player and cannot be changed
     */
    String MC_COMMANDS_ENTITYDATA_NOPLAYERS = "minecraft:commands.entitydata.noPlayers";

    /**
     * Entity data updated to: %1#s
     */
    String MC_COMMANDS_ENTITYDATA_SUCCESS = "minecraft:commands.entitydata.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_ENTITYDATA_TAGERROR = "minecraft:commands.entitydata.tagError";

    /**
     * Triggers an event for the specified object(s)
     */
    String MC_COMMANDS_EVENT_DESCRIPTION = "minecraft:commands.event.description";

    /**
     * Event name cannot be empty
     */
    String MC_COMMANDS_EVENT_ERROR_EMPTY = "minecraft:commands.event.error.empty";

    /**
     * %1#s could not be executed on: %2#s
     */
    String MC_COMMANDS_EVENT_ERROR_FAILED = "minecraft:commands.event.error.failed";

    /**
     * %1#s successfully executed on: %2#s
     */
    String MC_COMMANDS_EVENT_SUCCESS = "minecraft:commands.event.success";

    /**
     * Invalid swizzle, expected combination of 'x', 'y', and 'z'
     */
    String MC_COMMANDS_EXECUTE_ALIGN_INVALIDINPUT = "minecraft:commands.execute.align.invalidInput";

    /**
     * All invocations failed: '%1#s'
     */
    String MC_COMMANDS_EXECUTE_ALLINVOCATIONSFAILED = "minecraft:commands.execute.allInvocationsFailed";

    /**
     * Executes a command on behalf of one or more entities.
     */
    String MC_COMMANDS_EXECUTE_DESCRIPTION = "minecraft:commands.execute.description";

    /**
     * Failed to execute '%1#s' as %2#s
     */
    String MC_COMMANDS_EXECUTE_FAILED = "minecraft:commands.execute.failed";

    /**
     * Execute subcommand %1#s %2#s test failed.
     */
    String MC_COMMANDS_EXECUTE_FALSECONDITION = "minecraft:commands.execute.falseCondition";

    /**
     * Execute subcommand %1#s %2#s test failed, count: %3#s.
     */
    String MC_COMMANDS_EXECUTE_FALSECONDITIONWITHCOUNT = "minecraft:commands.execute.falseConditionWithCount";

    /**
     * Checking too many blocks. Max block count: %1#s. Current block count: %2#s.
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSBLOCKS_TOOMANYBLOCKS = "minecraft:commands.execute.ifUnlessBlocks.tooManyBlocks";

    /**
     * No objective was found by the name '%1#s'
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_OBJECTIVENOTFOUND = "minecraft:commands.execute.ifUnlessScore.objectiveNotFound";

    /**
     * Invalid operation %1#s.
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_PLAYERS_COMPAREOPERATION_INVALIDOPERATION = "minecraft:commands.execute.ifUnlessScore.players.compareOperation.invalidOperation";

    /**
     * No %1#s score for %2#s found
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_SCORE_NOTFOUND = "minecraft:commands.execute.ifUnlessScore.score.notFound";

    /**
     * There is no tracked player '%1#s' on the scoreboard
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_TARGETS_EMPTY = "minecraft:commands.execute.ifUnlessScore.targets.empty";

    /**
     * %1#s targets matched the selector. Only 1 target is allowed.
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_TARGETS_TOOMANY = "minecraft:commands.execute.ifUnlessScore.targets.tooMany";

    /**
     * Detect position: %1#s %2#s %3#s is out of range.
     */
    String MC_COMMANDS_EXECUTE_OUTRANGEDDETECTPOSITION = "minecraft:commands.execute.outRangedDetectPosition";

    /**
     * Test passed.
     */
    String MC_COMMANDS_EXECUTE_TRUECONDITION = "minecraft:commands.execute.trueCondition";

    /**
     * Test passed, count: %1#s.
     */
    String MC_COMMANDS_EXECUTE_TRUECONDITIONWITHCOUNT = "minecraft:commands.execute.trueConditionWithCount";

    /**
     * Fills all or parts of a region with a specific block.
     */
    String MC_COMMANDS_FILL_DESCRIPTION = "minecraft:commands.fill.description";

    /**
     * No blocks filled
     */
    String MC_COMMANDS_FILL_FAILED = "minecraft:commands.fill.failed";

    /**
     * Cannot place blocks outside of the world
     */
    String MC_COMMANDS_FILL_OUTOFWORLD = "minecraft:commands.fill.outOfWorld";

    /**
     * Invalid replace data value for block %1#s
     */
    String MC_COMMANDS_FILL_REPLACE_AUXVALUE_INVALID = "minecraft:commands.fill.replace.auxvalue.invalid";

    /**
     * %1#d blocks filled
     */
    String MC_COMMANDS_FILL_SUCCESS = "minecraft:commands.fill.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_FILL_TAGERROR = "minecraft:commands.fill.tagError";

    /**
     * Too many blocks in the specified area (%1#d > %2#d)
     */
    String MC_COMMANDS_FILL_TOOMANYBLOCKS = "minecraft:commands.fill.tooManyBlocks";

    /**
     * Add or remove fog settings file
     */
    String MC_COMMANDS_FOG_DESCRIPTION = "minecraft:commands.fog.description";

    /**
     * Invalid Fog Setting Id : %1#s
     */
    String MC_COMMANDS_FOG_INVALIDFOGID = "minecraft:commands.fog.invalidFogId";

    /**
     * No fog settings with the id were found for %1#s
     */
    String MC_COMMANDS_FOG_INVALIDUSERID = "minecraft:commands.fog.invalidUserId";

    /**
     * Size limit reached for fog identifiers for %1#s
     */
    String MC_COMMANDS_FOG_LIMITREACHED = "minecraft:commands.fog.limitReached";

    /**
     * Last pushed fog setting with id %2#s was successfully removed from %1#s
     */
    String MC_COMMANDS_FOG_SUCCESS_POP = "minecraft:commands.fog.success.pop";

    /**
     * Fog setting was successfully added for %1#s with id %2#s
     */
    String MC_COMMANDS_FOG_SUCCESS_PUSH = "minecraft:commands.fog.success.push";

    /**
     * Fog settings with id %2#s were successfully removed from %1#s
     */
    String MC_COMMANDS_FOG_SUCCESS_REMOVE = "minecraft:commands.fog.success.remove";

    /**
     * Runs commands found in the corresponding function file.
     */
    String MC_COMMANDS_FUNCTION_DESCRIPTION = "minecraft:commands.function.description";

    /**
     * Function %1#s not found.
     */
    String MC_COMMANDS_FUNCTION_FUNCTIONNAMENOTFOUND = "minecraft:commands.function.functionNameNotFound";

    /**
     * Function with title '%s' is invalid, character '%s' is not allowed in function names.
     */
    String MC_COMMANDS_FUNCTION_INVALIDCHARACTERS = "minecraft:commands.function.invalidCharacters";

    /**
     * Function %s could not be run. You must specify a min_engine_version in the behavior pack's manifest.json.
     */
    String MC_COMMANDS_FUNCTION_NOENGINEVERSIONSPECIFIED = "minecraft:commands.function.noEngineVersionSpecified";

    /**
     * Successfully executed %1#d function entries.
     */
    String MC_COMMANDS_FUNCTION_SUCCESS = "minecraft:commands.function.success";

    /**
     * Sets a player's game mode.
     */
    String MC_COMMANDS_GAMEMODE_DESCRIPTION = "minecraft:commands.gamemode.description";

    /**
     * Game mode '%1#s' is invalid
     */
    String MC_COMMANDS_GAMEMODE_FAIL_INVALID = "minecraft:commands.gamemode.fail.invalid";

    /**
     * Set %2#s's game mode to %1#s
     */
    String MC_COMMANDS_GAMEMODE_SUCCESS_OTHER = "minecraft:commands.gamemode.success.other";

    /**
     * Set own game mode to %1#s
     */
    String MC_COMMANDS_GAMEMODE_SUCCESS_SELF = "minecraft:commands.gamemode.success.self";

    /**
     * Sets or queries a game rule value.
     */
    String MC_COMMANDS_GAMERULE_DESCRIPTION = "minecraft:commands.gamerule.description";

    /**
     * Only server owners can change '%1#s'
     */
    String MC_COMMANDS_GAMERULE_NOPERMISSION = "minecraft:commands.gamerule.nopermission";

    /**
     * No game rule called '%1#s' is available
     */
    String MC_COMMANDS_GAMERULE_NORULE = "minecraft:commands.gamerule.norule";

    /**
     * Game rule %1#s has been updated to %2#s
     */
    String MC_COMMANDS_GAMERULE_SUCCESS = "minecraft:commands.gamerule.success";

    /**
     * Invalid type used for game rule '%1#s'
     */
    String MC_COMMANDS_GAMERULE_TYPE_INVALID = "minecraft:commands.gamerule.type.invalid";

    /**
     * Game rule '%1#s' can only be used if cheats are enabled in this world.
     */
    String MC_COMMANDS_GAMERULE_TYPE_NOCHEATSENABLED = "minecraft:commands.gamerule.type.nocheatsenabled";

    /**
     * Disable game tips on this device
     */
    String MC_COMMANDS_GAMETIPS_OFF_DESCRIPTION = "minecraft:commands.gametips-off.description";

    /**
     * Game tips are now disabled
     */
    String MC_COMMANDS_GAMETIPS_OFF_SUCCESS = "minecraft:commands.gametips-off.success";

    /**
     * Enable game tips on this device
     */
    String MC_COMMANDS_GAMETIPS_ON_DESCRIPTION = "minecraft:commands.gametips-on.description";

    /**
     * Game tips are now enabled
     */
    String MC_COMMANDS_GAMETIPS_ON_SUCCESS = "minecraft:commands.gametips-on.success";

    /**
     * Reset game tips on this device
     */
    String MC_COMMANDS_GAMETIPS_RESET_DESCRIPTION = "minecraft:commands.gametips-reset.description";

    /**
     * Game tips are now reset
     */
    String MC_COMMANDS_GAMETIPS_RESET_SUCCESS = "minecraft:commands.gametips-reset.success";

    /**
     * Enable or disable the game tips on this device
     */
    String MC_COMMANDS_GAMETIPS_DESCRIPTION = "minecraft:commands.gametips.description";

    /**
     * Game tips are now disabled
     */
    String MC_COMMANDS_GAMETIPS_DISABLED = "minecraft:commands.gametips.disabled";

    /**
     * Game tips are now enabled
     */
    String MC_COMMANDS_GAMETIPS_ENABLED = "minecraft:commands.gametips.enabled";

    /**
     * '%1#s' command started (async step %2#d)
     */
    String MC_COMMANDS_GENERIC_ASYNC_INITIATED = "minecraft:commands.generic.async.initiated";

    /**
     * Sorry! The %s command is not available in this world.
     */
    String MC_COMMANDS_GENERIC_BLOCKED_EDU = "minecraft:commands.generic.blocked_edu";

    /**
     * '%1#s' is not true or false
     */
    String MC_COMMANDS_GENERIC_BOOLEAN_INVALID = "minecraft:commands.generic.boolean.invalid";

    /**
     * Specified chunk not found
     */
    String MC_COMMANDS_GENERIC_CHUNK_NOTFOUND = "minecraft:commands.generic.chunk.notFound";

    /**
     * Component list parsing failed
     */
    String MC_COMMANDS_GENERIC_COMPONENTERROR = "minecraft:commands.generic.componentError";

    /**
     * Destination volume is not loaded
     */
    String MC_COMMANDS_GENERIC_DESTINATIONNOTLOADED = "minecraft:commands.generic.destinationNotLoaded";

    /**
     * Specified dimension not found
     */
    String MC_COMMANDS_GENERIC_DIMENSION_NOTFOUND = "minecraft:commands.generic.dimension.notFound";

    /**
     * Cheats aren't enabled in this world.
     */
    String MC_COMMANDS_GENERIC_DISABLED = "minecraft:commands.generic.disabled";

    /**
     * The command you entered, %s, is not supported in the Editor.
     */
    String MC_COMMANDS_GENERIC_DISABLED_EDITORLOCKED = "minecraft:commands.generic.disabled.editorLocked";

    /**
     * Settings are currently locked. Unlock Template World Options in the Game Settings menu to change them.
     */
    String MC_COMMANDS_GENERIC_DISABLED_TEMPLATELOCKED = "minecraft:commands.generic.disabled.templateLocked";

    /**
     * The number you have entered (%.2f) is too big, it must be at most %.2f
     */
    String MC_COMMANDS_GENERIC_DOUBLE_TOOBIG = "minecraft:commands.generic.double.tooBig";

    /**
     * The number you have entered (%.2f) is too small, it must be at least %.2f
     */
    String MC_COMMANDS_GENERIC_DOUBLE_TOOSMALL = "minecraft:commands.generic.double.tooSmall";

    /**
     * Duplicate %s selector arguments
     */
    String MC_COMMANDS_GENERIC_DUPLICATESELECTORARGUMENT = "minecraft:commands.generic.duplicateSelectorArgument";

    /**
     * Duplicate type arguments
     */
    String MC_COMMANDS_GENERIC_DUPLICATETYPE = "minecraft:commands.generic.duplicateType";

    /**
     * Bad public key given. Expected 120 byte key after PEM formatting.
     */
    String MC_COMMANDS_GENERIC_ENCRYPTION_BADKEY = "minecraft:commands.generic.encryption.badkey";

    /**
     * Bad salt given. Expected a 16 bytes before base 64 encoding.
     */
    String MC_COMMANDS_GENERIC_ENCRYPTION_BADSALT = "minecraft:commands.generic.encryption.badsalt";

    /**
     * Encrypted session required
     */
    String MC_COMMANDS_GENERIC_ENCRYPTION_REQUIRED = "minecraft:commands.generic.encryption.required";

    /**
     * Target entity cannot be in a different dimension
     */
    String MC_COMMANDS_GENERIC_ENTITY_DIFFERENTDIMENSION = "minecraft:commands.generic.entity.differentDimension";

    /**
     * Entity type '%1#s' is invalid
     */
    String MC_COMMANDS_GENERIC_ENTITY_INVALIDTYPE = "minecraft:commands.generic.entity.invalidType";

    /**
     * The entity UUID provided is in an invalid format
     */
    String MC_COMMANDS_GENERIC_ENTITY_INVALIDUUID = "minecraft:commands.generic.entity.invalidUuid";

    /**
     * That entity cannot be found
     */
    String MC_COMMANDS_GENERIC_ENTITY_NOTFOUND = "minecraft:commands.generic.entity.notFound";

    /**
     * Incorrect permission level for command: %s.  
     */
    String MC_COMMANDS_GENERIC_ERROR_PERMISSIONS = "minecraft:commands.generic.error.permissions";

    /**
     * An unknown error occurred while attempting to perform this command
     */
    String MC_COMMANDS_GENERIC_EXCEPTION = "minecraft:commands.generic.exception";

    /**
     * Type argument applied to Agent-only selector
     */
    String MC_COMMANDS_GENERIC_INVALIDAGENTTYPE = "minecraft:commands.generic.invalidAgentType";

    /**
     * The command you entered, %s, is not supported on this device
     */
    String MC_COMMANDS_GENERIC_INVALIDDEVICE = "minecraft:commands.generic.invalidDevice";

    /**
     * Invalid message length
     */
    String MC_COMMANDS_GENERIC_INVALIDMESSAGE = "minecraft:commands.generic.invalidMessage";

    /**
     * Command origin was invalid at command execution time
     */
    String MC_COMMANDS_GENERIC_INVALIDORIGIN = "minecraft:commands.generic.invalidOrigin";

    /**
     * Type argument applied to player-only selector
     */
    String MC_COMMANDS_GENERIC_INVALIDPLAYERTYPE = "minecraft:commands.generic.invalidPlayerType";

    /**
     * Unknown type argument
     */
    String MC_COMMANDS_GENERIC_INVALIDTYPE = "minecraft:commands.generic.invalidType";

    /**
     * Invalid context provided for given command type
     */
    String MC_COMMANDS_GENERIC_INVALIDCONTEXT = "minecraft:commands.generic.invalidcontext";

    /**
     * Provided JSON failed to parse with the following error: %1#s
     */
    String MC_COMMANDS_GENERIC_JSON_INVALID = "minecraft:commands.generic.json.invalid";

    /**
     * Max level has to be larger than min level
     */
    String MC_COMMANDS_GENERIC_LEVELERROR = "minecraft:commands.generic.levelError";

    /**
     * Body is missing or malformed
     */
    String MC_COMMANDS_GENERIC_MALFORMED_BODY = "minecraft:commands.generic.malformed.body";

    /**
     * Invalid request type
     */
    String MC_COMMANDS_GENERIC_MALFORMED_TYPE = "minecraft:commands.generic.malformed.type";

    /**
     * No targets matched selector
     */
    String MC_COMMANDS_GENERIC_NOTARGETMATCH = "minecraft:commands.generic.noTargetMatch";

    /**
     * Not implemented
     */
    String MC_COMMANDS_GENERIC_NOTIMPLEMENTED = "minecraft:commands.generic.notimplemented";

    /**
     * '%1#s' is not a valid number
     */
    String MC_COMMANDS_GENERIC_NUM_INVALID = "minecraft:commands.generic.num.invalid";

    /**
     * The number you have entered (%1#d) is too big, it must be at most %2#d
     */
    String MC_COMMANDS_GENERIC_NUM_TOOBIG = "minecraft:commands.generic.num.tooBig";

    /**
     * The number you have entered (%1#d) is too small, it must be at least %2#d
     */
    String MC_COMMANDS_GENERIC_NUM_TOOSMALL = "minecraft:commands.generic.num.tooSmall";

    /**
     * Cannot access blocks outside of the world
     */
    String MC_COMMANDS_GENERIC_OUTOFWORLD = "minecraft:commands.generic.outOfWorld";

    /**
     * '%1#s' is not a valid parameter
     */
    String MC_COMMANDS_GENERIC_PARAMETER_INVALID = "minecraft:commands.generic.parameter.invalid";

    /**
     * <insufficient permissions for selector expansion>
     */
    String MC_COMMANDS_GENERIC_PERMISSION_SELECTOR = "minecraft:commands.generic.permission.selector";

    /**
     * That player cannot be found
     */
    String MC_COMMANDS_GENERIC_PLAYER_NOTFOUND = "minecraft:commands.generic.player.notFound";

    /**
     * Provided protocol version doesn't match Minecraft's protocol version
     */
    String MC_COMMANDS_GENERIC_PROTOCOL_MISMATCH = "minecraft:commands.generic.protocol.mismatch";

    /**
     * Minimum selector radius must be smaller than maximum
     */
    String MC_COMMANDS_GENERIC_RADIUSERROR = "minecraft:commands.generic.radiusError";

    /**
     * Radius cannot be negative
     */
    String MC_COMMANDS_GENERIC_RADIUSNEGATIVE = "minecraft:commands.generic.radiusNegative";

    /**
     * Rotation out of range
     */
    String MC_COMMANDS_GENERIC_ROTATIONERROR = "minecraft:commands.generic.rotationError";

    /**
     * The command is already running
     */
    String MC_COMMANDS_GENERIC_RUNNING = "minecraft:commands.generic.running";

    /**
     * Source volume is not loaded
     */
    String MC_COMMANDS_GENERIC_SOURCENOTLOADED = "minecraft:commands.generic.sourceNotLoaded";

    /**
     * Command step failed
     */
    String MC_COMMANDS_GENERIC_STEP_FAILED = "minecraft:commands.generic.step.failed";

    /**
     * Syntax error: Unexpected "%2#s": at "%1#s>>%2#s<<%3#s"
     */
    String MC_COMMANDS_GENERIC_SYNTAX = "minecraft:commands.generic.syntax";

    /**
     * Selector must be player-type
     */
    String MC_COMMANDS_GENERIC_TARGETNOTPLAYER = "minecraft:commands.generic.targetNotPlayer";

    /**
     * Too many commands have been requested, wait for one to be done
     */
    String MC_COMMANDS_GENERIC_TOO_MANY_REQUESTS = "minecraft:commands.generic.too.many.requests";

    /**
     * Too many target name arguments
     */
    String MC_COMMANDS_GENERIC_TOOMANYNAMES = "minecraft:commands.generic.tooManyNames";

    /**
     * Too many targets matched selector
     */
    String MC_COMMANDS_GENERIC_TOOMANYTARGETS = "minecraft:commands.generic.tooManyTargets";

    /**
     * Unknown command: %s. Please check that the command exists and that you have permission to use it.
     */
    String MC_COMMANDS_GENERIC_UNKNOWN = "minecraft:commands.generic.unknown";

    /**
     * Usage: %1#s
     */
    String MC_COMMANDS_GENERIC_USAGE = "minecraft:commands.generic.usage";

    /**
     * Usage:
     */
    String MC_COMMANDS_GENERIC_USAGE_NOPARAM = "minecraft:commands.generic.usage.noparam";

    /**
     * The requested version doesn't exist for this command
     */
    String MC_COMMANDS_GENERIC_VERSION_MISMATCH = "minecraft:commands.generic.version.mismatch";

    /**
     * Command calls not from chat should specify the command's version
     */
    String MC_COMMANDS_GENERIC_VERSION_MISSING = "minecraft:commands.generic.version.missing";

    /**
     * Active camera has to be a free camera to target an entity
     */
    String MC_COMMANDS_GENERIC_WRONGTARGETCAMERA = "minecraft:commands.generic.wrongTargetCamera";

    /**
     * Gets pixels for a specific chunk.
     */
    String MC_COMMANDS_GETCHUNKDATA_DESCRIPTION = "minecraft:commands.getchunkdata.description";

    /**
     * Chunk data received
     */
    String MC_COMMANDS_GETCHUNKDATA_SUCCESS = "minecraft:commands.getchunkdata.success";

    /**
     * Gets list of chunks that are loaded.
     */
    String MC_COMMANDS_GETCHUNKS_DESCRIPTION = "minecraft:commands.getchunks.description";

    /**
     * Chunk list received
     */
    String MC_COMMANDS_GETCHUNKS_SUCCESS = "minecraft:commands.getchunks.success";

    /**
     * Returns the local player name.
     */
    String MC_COMMANDS_GETLOCALPLAYERNAME_DESCRIPTION = "minecraft:commands.getlocalplayername.description";

    /**
     * Gets the spawn position of the specified player(s).
     */
    String MC_COMMANDS_GETSPAWNPOINT_DESCRIPTION = "minecraft:commands.getspawnpoint.description";

    /**
     * Gets the position of the top non-air block below the specified position
     */
    String MC_COMMANDS_GETTOPSOLIDBLOCK_DESCRIPTION = "minecraft:commands.gettopsolidblock.description";

    /**
     * No solid blocks under specified position
     */
    String MC_COMMANDS_GETTOPSOLIDBLOCK_NOTFOUND = "minecraft:commands.gettopsolidblock.notfound";

    /**
     * There is no such block with name %1#d
     */
    String MC_COMMANDS_GIVE_BLOCK_NOTFOUND = "minecraft:commands.give.block.notFound";

    /**
     * Gives an item to a player.
     */
    String MC_COMMANDS_GIVE_DESCRIPTION = "minecraft:commands.give.description";

    /**
     * Invalid command syntax: no such %s exists with that data value
     */
    String MC_COMMANDS_GIVE_ITEM_INVALID = "minecraft:commands.give.item.invalid";

    /**
     * There is no such item with name %1#d
     */
    String MC_COMMANDS_GIVE_ITEM_NOTFOUND = "minecraft:commands.give.item.notFound";

    /**
     * Could not make exploration map. Feature not found on this dimension
     */
    String MC_COMMANDS_GIVE_MAP_FEATURENOTFOUND = "minecraft:commands.give.map.featureNotFound";

    /**
     * Invalid map data provided
     */
    String MC_COMMANDS_GIVE_MAP_INVALIDDATA = "minecraft:commands.give.map.invalidData";

    /**
     * Gave %1#s * %2#d to %3#s
     */
    String MC_COMMANDS_GIVE_SUCCESS = "minecraft:commands.give.success";

    /**
     * You have been given %1#s * %2#d
     */
    String MC_COMMANDS_GIVE_SUCCESSRECIPIENT = "minecraft:commands.give.successRecipient";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_GIVE_TAGERROR = "minecraft:commands.give.tagError";

    /**
     * Sets or gets the paused state of the game for all players.
     */
    String MC_COMMANDS_GLOBALPAUSE_DESCRIPTION = "minecraft:commands.globalpause.description";

    /**
     * Invalid conditions for setting pause state
     */
    String MC_COMMANDS_GLOBALPAUSE_ERROR = "minecraft:commands.globalpause.error";

    /**
     * Set or got pause state
     */
    String MC_COMMANDS_GLOBALPAUSE_SUCCESS = "minecraft:commands.globalpause.success";

    /**
     * "data" has an invalid input, must be an integer.
     */
    String MC_COMMANDS_HASITEM_FAIL_INVALIDDATA = "minecraft:commands.hasitem.fail.invalidData";

    /**
     * "quantity" has an invalid input, must be an integer or range of integers.
     */
    String MC_COMMANDS_HASITEM_FAIL_INVALIDQUANTITY = "minecraft:commands.hasitem.fail.invalidQuantity";

    /**
     * "slot" has an invalid input, must be an integer or range of integers.
     */
    String MC_COMMANDS_HASITEM_FAIL_INVALIDSLOT = "minecraft:commands.hasitem.fail.invalidSlot";

    /**
     * "item" missing, required for "hasitem" filter.
     */
    String MC_COMMANDS_HASITEM_FAIL_NOITEM = "minecraft:commands.hasitem.fail.noItem";

    /**
     * Specifying a "slot" is invalid if a "location" is not given.
     */
    String MC_COMMANDS_HASITEM_FAIL_SLOTNOLOCATION = "minecraft:commands.hasitem.fail.slotNoLocation";

    /**
     * %s (also %s):
     */
    String MC_COMMANDS_HELP_COMMAND_ALIASES = "minecraft:commands.help.command.aliases";

    /**
     * Provides help/list of commands.
     */
    String MC_COMMANDS_HELP_DESCRIPTION = "minecraft:commands.help.description";

    /**
     * Tip: Use the <tab> key while typing a command to auto-complete the command or its arguments
     */
    String MC_COMMANDS_HELP_FOOTER = "minecraft:commands.help.footer";

    /**
     * --- Showing help page %1#d of %2#d (/help <page>) ---
     */
    String MC_COMMANDS_HELP_HEADER = "minecraft:commands.help.header";

    /**
     * Changes the visibility of hud elements.
     */
    String MC_COMMANDS_HUD_DESCRIPTION = "minecraft:commands.hud.description";

    /**
     * Hud command successfully executed
     */
    String MC_COMMANDS_HUD_SUCCESS = "minecraft:commands.hud.success";

    /**
     * Sets the immutable state of the world.
     */
    String MC_COMMANDS_IMMUTABLEWORLD_DESCRIPTION = "minecraft:commands.immutableworld.description";

    /**
     * immutableworld = %s
     */
    String MC_COMMANDS_IMMUTABLEWORLD_INFO = "minecraft:commands.immutableworld.info";

    /**
     * Camera
     */
    String MC_COMMANDS_INPUTPERMISSION_CAMERA = "minecraft:commands.inputpermission.camera";

    /**
     * Sets whether or not a player's input can affect their character.
     */
    String MC_COMMANDS_INPUTPERMISSION_DESCRIPTION = "minecraft:commands.inputpermission.description";

    /**
     * disabled
     */
    String MC_COMMANDS_INPUTPERMISSION_DISABLED = "minecraft:commands.inputpermission.disabled";

    /**
     * Dismount 
     */
    String MC_COMMANDS_INPUTPERMISSION_DISMOUNT = "minecraft:commands.inputpermission.dismount";

    /**
     * enabled
     */
    String MC_COMMANDS_INPUTPERMISSION_ENABLED = "minecraft:commands.inputpermission.enabled";

    /**
     * Jump
     */
    String MC_COMMANDS_INPUTPERMISSION_JUMP = "minecraft:commands.inputpermission.jump";

    /**
     * Lateral Movement 
     */
    String MC_COMMANDS_INPUTPERMISSION_LATERAL_MOVEMENT = "minecraft:commands.inputpermission.lateral_movement";

    /**
     * Mount 
     */
    String MC_COMMANDS_INPUTPERMISSION_MOUNT = "minecraft:commands.inputpermission.mount";

    /**
     * Move Backward
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_BACKWARD = "minecraft:commands.inputpermission.move_backward";

    /**
     * Move Forward 
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_FORWARD = "minecraft:commands.inputpermission.move_forward";

    /**
     * Move Left
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_LEFT = "minecraft:commands.inputpermission.move_left";

    /**
     * Move Right
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_RIGHT = "minecraft:commands.inputpermission.move_right";

    /**
     * Movement
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVEMENT = "minecraft:commands.inputpermission.movement";

    /**
     * <%s>: %d %s
     */
    String MC_COMMANDS_INPUTPERMISSION_QUERY = "minecraft:commands.inputpermission.query";

    /**
     * <%s>: %d enabled | %d disabled
     */
    String MC_COMMANDS_INPUTPERMISSION_QUERYVERBOSE = "minecraft:commands.inputpermission.queryverbose";

    /**
     * '%s' is an invalid filter argument
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_INVALIDFILTER = "minecraft:commands.inputpermission.set.invalidfilter";

    /**
     * 'permission' has an invalid input
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_MISSINGPERMISSION = "minecraft:commands.inputpermission.set.missingpermission";

    /**
     * 'state' has an invalid input
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_MISSINGSTATE = "minecraft:commands.inputpermission.set.missingstate";

    /**
     * %s input %s for %d players  
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_OUTPUTMULTIPLEPLAYERS = "minecraft:commands.inputpermission.set.outputmultipleplayers";

    /**
     * %s input %s for %s  
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_OUTPUTONEPLAYER = "minecraft:commands.inputpermission.set.outputoneplayer";

    /**
     * Sneak
     */
    String MC_COMMANDS_INPUTPERMISSION_SNEAK = "minecraft:commands.inputpermission.sneak";

    /**
     * Lists all items with the given tag.
     */
    String MC_COMMANDS_ITEMSWITHTAG_DESCRIPTION = "minecraft:commands.itemswithtag.description";

    /**
     * No items contain the tag %s.
     */
    String MC_COMMANDS_ITEMSWITHTAG_FAIL_NOITEMSFOUND = "minecraft:commands.itemswithtag.fail.noitemsfound";

    /**
     * %s
     */
    String MC_COMMANDS_ITEMSWITHTAG_SUCCESS_LIST_ITEMS = "minecraft:commands.itemswithtag.success.list.items";

    /**
     * There are %d items with the tag %s:
     */
    String MC_COMMANDS_ITEMSWITHTAG_SUCCESS_MULTIPLE = "minecraft:commands.itemswithtag.success.multiple";

    /**
     * There is %d item with the tag %s:
     */
    String MC_COMMANDS_ITEMSWITHTAG_SUCCESS_SINGLE = "minecraft:commands.itemswithtag.success.single";

    /**
     * Kicks a player from the server.
     */
    String MC_COMMANDS_KICK_DESCRIPTION = "minecraft:commands.kick.description";

    /**
     * Removes a player from the game.
     */
    String MC_COMMANDS_KICK_DESCRIPTION_EDU = "minecraft:commands.kick.description.edu";

    /**
     * The host may not be kicked from the game.
     */
    String MC_COMMANDS_KICK_NO_HOST = "minecraft:commands.kick.no.host";

    /**
     * Please provide the name of the player you want to kick
     */
    String MC_COMMANDS_KICK_NO_PLAYER = "minecraft:commands.kick.no.player";

    /**
     * Teachers may not be removed from the game.
     */
    String MC_COMMANDS_KICK_NO_TEACHER = "minecraft:commands.kick.no.teacher";

    /**
     * Could not find player %1#s
     */
    String MC_COMMANDS_KICK_NOT_FOUND = "minecraft:commands.kick.not.found";

    /**
     * You may not remove yourself from the game
     */
    String MC_COMMANDS_KICK_NOT_YOURSELF = "minecraft:commands.kick.not.yourself";

    /**
     * Kicked %1#s from the game
     */
    String MC_COMMANDS_KICK_SUCCESS = "minecraft:commands.kick.success";

    /**
     * Kicked %1#s from the game: '%2#s'
     */
    String MC_COMMANDS_KICK_SUCCESS_REASON = "minecraft:commands.kick.success.reason";

    /**
     * Removed %1#s from the game: '%2#s'
     */
    String MC_COMMANDS_KICK_SUCCESS_REASONEDU = "minecraft:commands.kick.success.reasonedu";

    /**
     * Players cannot be killed while they are in Creative mode.
     */
    String MC_COMMANDS_KILL_ATTEMPTKILLPLAYERCREATIVE = "minecraft:commands.kill.attemptKillPlayerCreative";

    /**
     * Players cannot be removed while they are in Creative mode.
     */
    String MC_COMMANDS_KILL_ATTEMPTKILLPLAYERCREATIVE_EDU = "minecraft:commands.kill.attemptKillPlayerCreative.edu";

    /**
     * Kills entities (players, mobs, etc.).
     */
    String MC_COMMANDS_KILL_DESCRIPTION = "minecraft:commands.kill.description";

    /**
     * Removes entities (players, mobs, etc.).
     */
    String MC_COMMANDS_KILL_DESCRIPTION_EDU = "minecraft:commands.kill.description.edu";

    /**
     * Killed %1#s
     */
    String MC_COMMANDS_KILL_SUCCESSFUL = "minecraft:commands.kill.successful";

    /**
     * Removed %1#s
     */
    String MC_COMMANDS_KILL_SUCCESSFUL_EDU = "minecraft:commands.kill.successful.edu";

    /**
     * Handle Educational Lesson reporting.
     */
    String MC_COMMANDS_LESSON_DESCRIPTION = "minecraft:commands.lesson.description";

    /**
     * Success
     */
    String MC_COMMANDS_LESSON_SUCCESS = "minecraft:commands.lesson.success";

    /**
     * Lists players on the server.
     */
    String MC_COMMANDS_LIST_DESCRIPTION = "minecraft:commands.list.description";

    /**
     * Could not find a biome of type %1#s within a reasonable distance
     */
    String MC_COMMANDS_LOCATE_BIOME_FAIL = "minecraft:commands.locate.biome.fail";

    /**
     * The nearest %1#s is at block %2#s, %3#s, %4#s (%5 blocks away)
     */
    String MC_COMMANDS_LOCATE_BIOME_SUCCESS = "minecraft:commands.locate.biome.success";

    /**
     * Displays the coordinates for the closest structure or biome of a given type.
     */
    String MC_COMMANDS_LOCATE_DESCRIPTION = "minecraft:commands.locate.description";

    /**
     * The command can only be used by a valid player
     */
    String MC_COMMANDS_LOCATE_STRUCTURE_FAIL_NOPLAYER = "minecraft:commands.locate.structure.fail.noplayer";

    /**
     * No valid structure found within a reasonable distance
     */
    String MC_COMMANDS_LOCATE_STRUCTURE_FAIL_NOSTRUCTUREFOUND = "minecraft:commands.locate.structure.fail.nostructurefound";

    /**
     * The nearest %1#s is at block %2#s, (y?), %3#s (%4 blocks away)
     */
    String MC_COMMANDS_LOCATE_STRUCTURE_SUCCESS = "minecraft:commands.locate.structure.success";

    /**
     * Drops the given loot table into the specified inventory or into the world.
     */
    String MC_COMMANDS_LOOT_DESCRIPTION = "minecraft:commands.loot.description";

    /**
     * slotId + count exceeds slot container size %d
     */
    String MC_COMMANDS_LOOT_FAILURE_COUNTEXCEEDSCONTAINERSIZE = "minecraft:commands.loot.failure.countExceedsContainerSize";

    /**
     * Entity %1#s has no loot table
     */
    String MC_COMMANDS_LOOT_FAILURE_ENTITYNOLOOTTABLE = "minecraft:commands.loot.failure.entityNoLootTable";

    /**
     * Loot table '%1#s' not found
     */
    String MC_COMMANDS_LOOT_FAILURE_INVALIDLOOTTABLE = "minecraft:commands.loot.failure.invalidLootTable";

    /**
     * Could not replace slot %s, slot must be between %d and %d for %s
     */
    String MC_COMMANDS_LOOT_FAILURE_INVALIDSLOTIDFORSLOTTYPE = "minecraft:commands.loot.failure.invalidSlotIdForSlotType";

    /**
     * Entity %s does not have SlotType %s
     */
    String MC_COMMANDS_LOOT_FAILURE_INVALIDSLOTTYPEFORENTITY = "minecraft:commands.loot.failure.invalidSlotTypeForEntity";

    /**
     * Mainhand or offhand should not be used on non entity object.
     */
    String MC_COMMANDS_LOOT_FAILURE_MAINHANDOFFHANDONNONENTITY = "minecraft:commands.loot.failure.mainhandOffhandOnNonEntity";

    /**
     * count cannot be negative a negative number
     */
    String MC_COMMANDS_LOOT_FAILURE_NEGATIVECOUNT = "minecraft:commands.loot.failure.negativeCount";

    /**
     * Target position %s is not a container
     */
    String MC_COMMANDS_LOOT_FAILURE_NOCONTAINER = "minecraft:commands.loot.failure.noContainer";

    /**
     * Replaced %s %d of %s with %d item(s) over %d slot(s)
     */
    String MC_COMMANDS_LOOT_REPLACESUCCESS = "minecraft:commands.loot.replaceSuccess";

    /**
     * Replaced %s slot %d with %d item(s) over %d slot(s)
     */
    String MC_COMMANDS_LOOT_REPLACESUCCESS_BLOCK = "minecraft:commands.loot.replaceSuccess.block";

    /**
     * %1#s items were dropped.
     */
    String MC_COMMANDS_LOOT_SUCCESS = "minecraft:commands.loot.success";

    /**
     * Displays a message about yourself.
     */
    String MC_COMMANDS_ME_DESCRIPTION = "minecraft:commands.me.description";

    /**
     * %1#s whispers to you: %2#s
     */
    String MC_COMMANDS_MESSAGE_DISPLAY_INCOMING = "minecraft:commands.message.display.incoming";

    /**
     * You whisper to %1#s: %2#s
     */
    String MC_COMMANDS_MESSAGE_DISPLAY_OUTGOING = "minecraft:commands.message.display.outgoing";

    /**
     * You can't send a private message to yourself!
     */
    String MC_COMMANDS_MESSAGE_SAMETARGET = "minecraft:commands.message.sameTarget";

    /**
     * Controls what mob events are allowed to run.
     */
    String MC_COMMANDS_MOBEVENT_DESCRIPTION = "minecraft:commands.mobevent.description";

    /**
     * Mob Event %s (id: %s) is set to false.
     */
    String MC_COMMANDS_MOBEVENT_EVENTISFALSE = "minecraft:commands.mobevent.eventIsFalse";

    /**
     * Mob Event %s (id: %s) is set to true.
     */
    String MC_COMMANDS_MOBEVENT_EVENTISTRUE = "minecraft:commands.mobevent.eventIsTrue";

    /**
     * Mob Event %s (id: %s) is set to true, but Mob Events are disabled.
     */
    String MC_COMMANDS_MOBEVENT_EVENTISTRUEBUTEVENTSDISABLED = "minecraft:commands.mobevent.eventIsTrueButEventsDisabled";

    /**
     * Mob Event %s (id: %s) set to false.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSETTOFALSE = "minecraft:commands.mobevent.eventSetToFalse";

    /**
     * Mob Event %s (id: %s) set to true.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSETTOTRUE = "minecraft:commands.mobevent.eventSetToTrue";

    /**
     * Mob Event %s (id: %s) set to true, but Mob Events are disabled.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSETTOTRUEBUTEVENTSDISABLED = "minecraft:commands.mobevent.eventSetToTrueButEventsDisabled";

    /**
     * Mob Events are disabled. Individual events will not run.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDISFALSE = "minecraft:commands.mobevent.eventsEnabledIsFalse";

    /**
     * Mob Events are enabled. Individual events which are set to false will not run.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDISTRUE = "minecraft:commands.mobevent.eventsEnabledIsTrue";

    /**
     * Mob Events are now disabled. Individual events will not run.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDSETTOFALSE = "minecraft:commands.mobevent.eventsEnabledSetToFalse";

    /**
     * Mob Events are now enabled. Individual events which are set to false will not run.
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDSETTOTRUE = "minecraft:commands.mobevent.eventsEnabledSetToTrue";

    /**
     * Allows you to control playing music tracks.
     */
    String MC_COMMANDS_MUSIC_DESCRIPTION = "minecraft:commands.music.description";

    /**
     * You must provide a non-empty track name.
     */
    String MC_COMMANDS_MUSIC_FAILURE_EMPTYTRACKNAME = "minecraft:commands.music.failure.emptyTrackName";

    /**
     * %s has been queued to start playing.
     */
    String MC_COMMANDS_MUSIC_SUCCESS_PLAYACTION = "minecraft:commands.music.success.playAction";

    /**
     * %s has been queued and it will start playing when the current track stops.
     */
    String MC_COMMANDS_MUSIC_SUCCESS_QUEUEACTION = "minecraft:commands.music.success.queueAction";

    /**
     * Custom music will stop now.
     */
    String MC_COMMANDS_MUSIC_SUCCESS_STOPACTION = "minecraft:commands.music.success.stopAction";

    /**
     * Music volume will be updated to %d.
     */
    String MC_COMMANDS_MUSIC_SUCCESS_VOLUMEACTION = "minecraft:commands.music.success.volumeAction";

    /**
     * Grants operator status to a player.
     */
    String MC_COMMANDS_OP_DESCRIPTION = "minecraft:commands.op.description";

    /**
     * Could not op (already op or higher): %s
     */
    String MC_COMMANDS_OP_FAILED = "minecraft:commands.op.failed";

    /**
     * You have been opped
     */
    String MC_COMMANDS_OP_MESSAGE = "minecraft:commands.op.message";

    /**
     * Opped: %s
     */
    String MC_COMMANDS_OP_SUCCESS = "minecraft:commands.op.success";

    /**
     * Invalid operation %1#s.
     */
    String MC_COMMANDS_OPERATOR_INVALID = "minecraft:commands.operator.invalid";

    /**
     * Reloads and applies Op permissions.
     */
    String MC_COMMANDS_OPS_DESCRIPTION = "minecraft:commands.ops.description";

    /**
     * Ops reloaded from file.
     */
    String MC_COMMANDS_OPS_RELOADED = "minecraft:commands.ops.reloaded";

    /**
     * Succeeded in setting operator level for player %s.
     */
    String MC_COMMANDS_OPS_SET_SUCCESS = "minecraft:commands.ops.set.success";

    /**
     * CommandBlock
     */
    String MC_COMMANDS_ORIGIN_COMMANDBLOCK = "minecraft:commands.origin.commandblock";

    /**
     * DevConsole
     */
    String MC_COMMANDS_ORIGIN_DEVCONSOLE = "minecraft:commands.origin.devconsole";

    /**
     * External
     */
    String MC_COMMANDS_ORIGIN_EXTERNAL = "minecraft:commands.origin.external";

    /**
     * Script Engine
     */
    String MC_COMMANDS_ORIGIN_SCRIPT = "minecraft:commands.origin.script";

    /**
     * Script Debugger
     */
    String MC_COMMANDS_ORIGIN_SCRIPTDEBUGGER = "minecraft:commands.origin.scriptDebugger";

    /**
     * Server
     */
    String MC_COMMANDS_ORIGIN_SERVER = "minecraft:commands.origin.server";

    /**
     * Teacher
     */
    String MC_COMMANDS_ORIGIN_TEACHER = "minecraft:commands.origin.teacher";

    /**
     * Creates particles.
     */
    String MC_COMMANDS_PARTICLE_DESCRIPTION = "minecraft:commands.particle.description";

    /**
     * Unknown effect name (%1#s)
     */
    String MC_COMMANDS_PARTICLE_NOTFOUND = "minecraft:commands.particle.notFound";

    /**
     * Playing effect %1#s for %2#d times
     */
    String MC_COMMANDS_PARTICLE_SUCCESS = "minecraft:commands.particle.success";

    /**
     * Reloads and applies permissions.
     */
    String MC_COMMANDS_PERMISSIONS_DESCRIPTION = "minecraft:commands.permissions.description";

    /**
     * Permissions reloaded from file.
     */
    String MC_COMMANDS_PERMISSIONS_RELOADED = "minecraft:commands.permissions.reloaded";

    /**
     * Could not save permission level %s for player %s.
     */
    String MC_COMMANDS_PERMISSIONS_SAVE_FAILED = "minecraft:commands.permissions.save.failed";

    /**
     * Succeeded in saving permission level %s for player %s.
     */
    String MC_COMMANDS_PERMISSIONS_SAVE_SUCCESS = "minecraft:commands.permissions.save.success";

    /**
     * Could not set permission level %s for player %s.
     */
    String MC_COMMANDS_PERMISSIONS_SET_FAILED = "minecraft:commands.permissions.set.failed";

    /**
     * Succeeded in setting permission level %s for player %s.
     */
    String MC_COMMANDS_PERMISSIONS_SET_SUCCESS = "minecraft:commands.permissions.set.success";

    /**
     * Feature placement failed. Not all chunks are loaded.
     */
    String MC_COMMANDS_PLACE_CHUNKNOTLOADED_ERROR = "minecraft:commands.place.chunknotloaded.error";

    /**
     * Places a jigsaw structure, feature, or feature rule in the world.
     */
    String MC_COMMANDS_PLACE_DESCRIPTION = "minecraft:commands.place.description";

    /**
     * Place command failed.
     */
    String MC_COMMANDS_PLACE_ERROR = "minecraft:commands.place.error";

    /**
     * Jigsaw structure generation failed.
     */
    String MC_COMMANDS_PLACE_GENERATION_ERROR = "minecraft:commands.place.generation.error";

    /**
     * Invalid feature name.
     */
    String MC_COMMANDS_PLACE_INVALIDFEATURE_ERROR = "minecraft:commands.place.invalidfeature.error";

    /**
     * Invalid feature rule name.
     */
    String MC_COMMANDS_PLACE_INVALIDFEATURERULE_ERROR = "minecraft:commands.place.invalidfeaturerule.error";

    /**
     * Feature could not be placed at this location.
     */
    String MC_COMMANDS_PLACE_INVALIDPOSITION_ERROR = "minecraft:commands.place.invalidposition.error";

    /**
     * Invalid structure name.
     */
    String MC_COMMANDS_PLACE_INVALIDSTRUCTURE_ERROR = "minecraft:commands.place.invalidstructure.error";

    /**
     * Invalid max depth. Value must be between 1 and 20.
     */
    String MC_COMMANDS_PLACE_MAXDEPTH_ERROR = "minecraft:commands.place.maxdepth.error";

    /**
     * Jigsaw structure generation failed. Structure placed out of bounds.
     */
    String MC_COMMANDS_PLACE_OUTOFBOUNDS_ERROR = "minecraft:commands.place.outofbounds.error";

    /**
     * Place command succeeded.
     */
    String MC_COMMANDS_PLACE_SUCCESS = "minecraft:commands.place.success";

    /**
     * Makes one or more entities play a one-off animation. Assumes all variables are setup correctly.
     */
    String MC_COMMANDS_PLAYANIMATION_DESCRIPTION = "minecraft:commands.playanimation.description";

    /**
     * Animation request sent to clients for processing.
     */
    String MC_COMMANDS_PLAYANIMATION_SUCCESS = "minecraft:commands.playanimation.success";

    /**
     * There are %1#d/%2#d players online:
     */
    String MC_COMMANDS_PLAYERS_LIST = "minecraft:commands.players.list";

    /**
     * %s
     */
    String MC_COMMANDS_PLAYERS_LIST_NAMES = "minecraft:commands.players.list.names";

    /**
     * Plays a sound.
     */
    String MC_COMMANDS_PLAYSOUND_DESCRIPTION = "minecraft:commands.playsound.description";

    /**
     * Player %1#s is too far away to hear the sound
     */
    String MC_COMMANDS_PLAYSOUND_PLAYERTOOFAR = "minecraft:commands.playsound.playerTooFar";

    /**
     * Played sound '%1#s' to %2#s
     */
    String MC_COMMANDS_PLAYSOUND_SUCCESS = "minecraft:commands.playsound.success";

    /**
     * Toggles on/off coordinates for player.
     */
    String MC_COMMANDS_POSITION_DESCRIPTION = "minecraft:commands.position.description";

    /**
     * Unable to host local game
     */
    String MC_COMMANDS_PUBLISH_FAILED = "minecraft:commands.publish.failed";

    /**
     * Local game hosted on port %1#s
     */
    String MC_COMMANDS_PUBLISH_STARTED = "minecraft:commands.publish.started";

    /**
     * Gets transform, name, and id information about the given target entity or entities.
     */
    String MC_COMMANDS_QUERYTARGET_DESCRIPTION = "minecraft:commands.querytarget.description";

    /**
     * Target data: %1#s
     */
    String MC_COMMANDS_QUERYTARGET_SUCCESS = "minecraft:commands.querytarget.success";

    /**
     * Unlocks recipe in the recipe book for a player.
     */
    String MC_COMMANDS_RECIPES_DESCRIPTION = "minecraft:commands.recipes.description";

    /**
     * The recipe could not be found.
     */
    String MC_COMMANDS_RECIPES_NOTFOUND = "minecraft:commands.recipes.notFound";

    /**
     * The recipe is not unlockable in the recipe book.
     */
    String MC_COMMANDS_RECIPES_NOTUNLOCKABLE = "minecraft:commands.recipes.notUnlockable";

    /**
     * %1#s does not have the recipe: %2#s
     */
    String MC_COMMANDS_RECIPES_PLAYERDOESNOTHAVERECIPE = "minecraft:commands.recipes.playerDoesNotHaveRecipe";

    /**
     * %1#s already has recipe unlocked: %2#s
     */
    String MC_COMMANDS_RECIPES_PLAYERHASRECIPE = "minecraft:commands.recipes.playerHasRecipe";

    /**
     * Removed all recipes for %1#s.
     */
    String MC_COMMANDS_RECIPES_REMOVEDALLRECIPES = "minecraft:commands.recipes.removedAllRecipes";

    /**
     * Removed recipe for %1#s: %2#s
     */
    String MC_COMMANDS_RECIPES_REMOVEDRECIPE = "minecraft:commands.recipes.removedRecipe";

    /**
     * Unlocked all recipes for %1#s.
     */
    String MC_COMMANDS_RECIPES_UNLOCKEDALLRECIPES = "minecraft:commands.recipes.unlockedAllRecipes";

    /**
     * Unlocked recipe for %1#s: %2#s
     */
    String MC_COMMANDS_RECIPES_UNLOCKEDRECIPE = "minecraft:commands.recipes.unlockedRecipe";

    /**
     * Reloads all function and script files from all behavior packs, or optionally reloads the world and all resource and behavior packs.
     */
    String MC_COMMANDS_RELOAD_ALL_DESCRIPTION = "minecraft:commands.reload.all.description";

    /**
     * Reloads all function and script files from all behavior packs.
     */
    String MC_COMMANDS_RELOAD_DESCRIPTION = "minecraft:commands.reload.description";

    /**
     * An unexpected error occurred.
     */
    String MC_COMMANDS_RELOAD_ERROR = "minecraft:commands.reload.error";

    /**
     * Only host player can reload the world.
     */
    String MC_COMMANDS_RELOAD_ONLYHOST = "minecraft:commands.reload.onlyHost";

    /**
     * Reloading the world has been started.
     */
    String MC_COMMANDS_RELOAD_STARTED = "minecraft:commands.reload.started";

    /**
     * Function and script files have been reloaded.
     */
    String MC_COMMANDS_RELOAD_SUCCESS = "minecraft:commands.reload.success";

    /**
     * Reloads configuration files relating to variables, secrets, permissions, etc.
     */
    String MC_COMMANDS_RELOADCONFIG_DESCRIPTION = "minecraft:commands.reloadconfig.description";

    /**
     * Pack configurations have been reloaded from disk from '%1#s'
     */
    String MC_COMMANDS_RELOADCONFIG_SUCCESS = "minecraft:commands.reloadconfig.success";

    /**
     * Could not replace slot %d, must be a value between %d and %d.
     */
    String MC_COMMANDS_REPLACEITEM_BADSLOTNUMBER = "minecraft:commands.replaceitem.badSlotNumber";

    /**
     * Replaces items in inventories.
     */
    String MC_COMMANDS_REPLACEITEM_DESCRIPTION = "minecraft:commands.replaceitem.description";

    /**
     * Could not replace %s slot %d with %d * %s
     */
    String MC_COMMANDS_REPLACEITEM_FAILED = "minecraft:commands.replaceitem.failed";

    /**
     * There is already an item occupying %s slot %d.
     */
    String MC_COMMANDS_REPLACEITEM_KEEPFAILED = "minecraft:commands.replaceitem.keepFailed";

    /**
     * Block at %s is not a container
     */
    String MC_COMMANDS_REPLACEITEM_NOCONTAINER = "minecraft:commands.replaceitem.noContainer";

    /**
     * Replaced %s slot %d with %d * %s
     */
    String MC_COMMANDS_REPLACEITEM_SUCCESS = "minecraft:commands.replaceitem.success";

    /**
     * Replaced %s slot %d of %s with %d * %s
     */
    String MC_COMMANDS_REPLACEITEM_SUCCESS_ENTITY = "minecraft:commands.replaceitem.success.entity";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_REPLACEITEM_TAGERROR = "minecraft:commands.replaceitem.tagError";

    /**
     * Makes entities ride other entities, stops entities from riding, makes rides evict their riders, or summons rides or riders.
     */
    String MC_COMMANDS_RIDE_DESCRIPTION = "minecraft:commands.ride.description";

    /**
     * Rides that have successfully evicted their riders: %1#s
     */
    String MC_COMMANDS_RIDE_EVICTRIDERS_SUCCESS = "minecraft:commands.ride.evictRiders.success";

    /**
     * Riders that did not meet ride conditions: %1#s
     */
    String MC_COMMANDS_RIDE_STARTRIDING_FAILURE = "minecraft:commands.ride.startRiding.failure";

    /**
     * The group of riders does not fit on the ride.
     */
    String MC_COMMANDS_RIDE_STARTRIDING_GROUPDOESNTFIT = "minecraft:commands.ride.startRiding.groupDoesntFit";

    /**
     * The selected entity is not rideable.
     */
    String MC_COMMANDS_RIDE_STARTRIDING_NOTRIDEABLE = "minecraft:commands.ride.startRiding.notRideable";

    /**
     * This ride is already full.
     */
    String MC_COMMANDS_RIDE_STARTRIDING_RIDEFULL = "minecraft:commands.ride.startRiding.rideFull";

    /**
     * The ride is full, so some riders were not added.
     */
    String MC_COMMANDS_RIDE_STARTRIDING_RIDEFULLRIDERSLEFT = "minecraft:commands.ride.startRiding.rideFullRidersLeft";

    /**
     * Riders successfully added to ride: %1#s
     */
    String MC_COMMANDS_RIDE_STARTRIDING_SUCCESS = "minecraft:commands.ride.startRiding.success";

    /**
     * Too many riders were specified. Only one is allowed at a time for teleport_ride.
     */
    String MC_COMMANDS_RIDE_STARTRIDING_TOOMANYRIDERS = "minecraft:commands.ride.startRiding.tooManyRiders";

    /**
     * Too many rides were specified. Only one is allowed at a time.
     */
    String MC_COMMANDS_RIDE_STARTRIDING_TOOMANYRIDES = "minecraft:commands.ride.startRiding.tooManyRides";

    /**
     * Riders that have successfully stopped riding: %1#s
     */
    String MC_COMMANDS_RIDE_STOPRIDING_SUCCESS = "minecraft:commands.ride.stopRiding.success";

    /**
     * Rides were summoned, but these riders could not be added to them: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_FAILED = "minecraft:commands.ride.summonRide.failed";

    /**
     * Entity was summoned, but it is not rideable.
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_NOTRIDEABLE = "minecraft:commands.ride.summonRide.notRideable";

    /**
     * Rides were not summoned for the following entities that already have rides: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_SKIPPED = "minecraft:commands.ride.summonRide.skipped";

    /**
     * Ride successfully summoned for these riders: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_SUCCESS = "minecraft:commands.ride.summonRide.success";

    /**
     * Riders were summoned, but could not be added to rides: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_FAILED = "minecraft:commands.ride.summonRider.failed";

    /**
     * Riders could not ride these rides because they are full: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_FULLRIDES = "minecraft:commands.ride.summonRider.fullRides";

    /**
     * The following entities are not rideable: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_RIDESWITHNORIDEABLECOMP = "minecraft:commands.ride.summonRider.ridesWithNoRideableComp";

    /**
     * Riders successfully summoned for rides: %1#s
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_SUCCESS = "minecraft:commands.ride.summonRider.success";

    /**
     * An error occurred when trying to pause the world storage.
     */
    String MC_COMMANDS_SAVE_ALL_ERROR = "minecraft:commands.save-all.error";

    /**
     * Data saved. Files are now ready to be copied.
     */
    String MC_COMMANDS_SAVE_ALL_SUCCESS = "minecraft:commands.save-all.success";

    /**
     * Saving is already turned off.
     */
    String MC_COMMANDS_SAVE_OFF_ALREADYOFF = "minecraft:commands.save-off.alreadyOff";

    /**
     * Saving is already turned on.
     */
    String MC_COMMANDS_SAVE_ON_ALREADYON = "minecraft:commands.save-on.alreadyOn";

    /**
     * Enables automatic server saves.
     */
    String MC_COMMANDS_SAVE_ON_DESCRIPTION = "minecraft:commands.save-on.description";

    /**
     * A previous save has not been completed.
     */
    String MC_COMMANDS_SAVE_ON_NOTDONE = "minecraft:commands.save-on.notDone";

    /**
     * Changes to the world are resumed.
     */
    String MC_COMMANDS_SAVE_ON_SUCCESS = "minecraft:commands.save-on.success";

    /**
     * Checks if a previous save-all has finished and lists the files involved.
     */
    String MC_COMMANDS_SAVE_STATE_DESCRIPTION = "minecraft:commands.save-state.description";

    /**
     * Control or check how the game saves data to disk.
     */
    String MC_COMMANDS_SAVE_DESCRIPTION = "minecraft:commands.save.description";

    /**
     * Turned off world auto-saving
     */
    String MC_COMMANDS_SAVE_DISABLED = "minecraft:commands.save.disabled";

    /**
     * Turned on world auto-saving
     */
    String MC_COMMANDS_SAVE_ENABLED = "minecraft:commands.save.enabled";

    /**
     * Saving failed: %1#s
     */
    String MC_COMMANDS_SAVE_FAILED = "minecraft:commands.save.failed";

    /**
     * Saving...
     */
    String MC_COMMANDS_SAVE_START = "minecraft:commands.save.start";

    /**
     * Saved the world
     */
    String MC_COMMANDS_SAVE_SUCCESS = "minecraft:commands.save.success";

    /**
     * Sends a message in the chat to other players.
     */
    String MC_COMMANDS_SAY_DESCRIPTION = "minecraft:commands.say.description";

    /**
     * No functions were removed with the name %1#s.
     */
    String MC_COMMANDS_SCHEDULE_CLEAR_FAILURE = "minecraft:commands.schedule.clear.failure";

    /**
     * Removed %1#d functions with the name %2#s.
     */
    String MC_COMMANDS_SCHEDULE_CLEAR_SUCCESS = "minecraft:commands.schedule.clear.success";

    /**
     * Scheduled function %1#s in %2#d ticks at gametime %3#d.
     */
    String MC_COMMANDS_SCHEDULE_DELAY_FUNCTIONQUEUED = "minecraft:commands.schedule.delay.functionQueued";

    /**
     * Time cannot be negative.
     */
    String MC_COMMANDS_SCHEDULE_DELAY_NEGATIVETIME = "minecraft:commands.schedule.delay.negativeTime";

    /**
     * Schedules an action to be executed once an area is loaded, or after a certain amount of time.
     */
    String MC_COMMANDS_SCHEDULE_DESCRIPTION = "minecraft:commands.schedule.description";

    /**
     * Function has been queued and it will be executed when the specified area is fully loaded.
     */
    String MC_COMMANDS_SCHEDULE_FUNCTIONQUEUED = "minecraft:commands.schedule.functionQueued";

    /**
     * Successfully executed %1#d function entries in function %2#s.
     */
    String MC_COMMANDS_SCHEDULE_FUNCTIONRAN = "minecraft:commands.schedule.functionRan";

    /**
     * The origin needed to execute this function was invalid.
     */
    String MC_COMMANDS_SCHEDULE_INVALIDORIGIN = "minecraft:commands.schedule.invalidOrigin";

    /**
     * Ticking area name cannot be empty.
     */
    String MC_COMMANDS_SCHEDULE_NAME_EMPTY = "minecraft:commands.schedule.name.empty";

    /**
     * No functions were removed with the ticking area name %1#s.
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_CLEAR_FAILURE = "minecraft:commands.schedule.tickingarea.clear.failure";

    /**
     * Removed %1#d functions with the ticking area name %2#s.
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_CLEAR_SUCCESS = "minecraft:commands.schedule.tickingarea.clear.success";

    /**
     * No functions were removed with the ticking area name %1#s and with name %2#s.
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_FUNC_CLEAR_FAILURE = "minecraft:commands.schedule.tickingarea.func.clear.failure";

    /**
     * Removed %1#d functions with the ticking area name %2#s and with name %3#s.
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_FUNC_CLEAR_SUCCESS = "minecraft:commands.schedule.tickingarea.func.clear.success";

    /**
     * More than one ticking areas were found with the name %s. Please use a single area.
     */
    String MC_COMMANDS_SCHEDULE_TOO_MANY_AREAS = "minecraft:commands.schedule.too.many.areas";

    /**
     * All matches failed
     */
    String MC_COMMANDS_SCOREBOARD_ALLMATCHESFAILED = "minecraft:commands.scoreboard.allMatchesFailed";

    /**
     * Tracks and displays scores for various objectives.
     */
    String MC_COMMANDS_SCOREBOARD_DESCRIPTION = "minecraft:commands.scoreboard.description";

    /**
     * Only one user wildcard allowed
     */
    String MC_COMMANDS_SCOREBOARD_NOMULTIWILDCARD = "minecraft:commands.scoreboard.noMultiWildcard";

    /**
     * No objective was found by the name '%1#s'
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVENOTFOUND = "minecraft:commands.scoreboard.objectiveNotFound";

    /**
     * The objective '%1#s' is read-only and cannot be set
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVEREADONLY = "minecraft:commands.scoreboard.objectiveReadOnly";

    /**
     * An objective with the name '%1#s' already exists
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_ALREADYEXISTS = "minecraft:commands.scoreboard.objectives.add.alreadyExists";

    /**
     * The display name '%1#s' is too long for an objective, it can be at most %2#d characters long
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_DISPLAYTOOLONG = "minecraft:commands.scoreboard.objectives.add.displayTooLong";

    /**
     * An objective needs a name.
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_NEEDNAME = "minecraft:commands.scoreboard.objectives.add.needName";

    /**
     * Added new objective '%1#s' successfully
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_SUCCESS = "minecraft:commands.scoreboard.objectives.add.success";

    /**
     * The name '%1#s' is too long for an objective, it can be at most %2#d characters long
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_TOOLONG = "minecraft:commands.scoreboard.objectives.add.tooLong";

    /**
     * Invalid objective criteria type '%1#s'
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_WRONGTYPE = "minecraft:commands.scoreboard.objectives.add.wrongType";

    /**
     * Modify scoreboard objectives.
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_DESCRIPTION = "minecraft:commands.scoreboard.objectives.description";

    /**
     * Showing %1#d objective(s) on scoreboard:
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_LIST_COUNT = "minecraft:commands.scoreboard.objectives.list.count";

    /**
     * There are no objectives on the scoreboard
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_LIST_EMPTY = "minecraft:commands.scoreboard.objectives.list.empty";

    /**
     * - %1#s: displays as '%2#s' and is type '%3#s'
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_LIST_ENTRY = "minecraft:commands.scoreboard.objectives.list.entry";

    /**
     * Removed objective '%1#s' successfully
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_REMOVE_SUCCESS = "minecraft:commands.scoreboard.objectives.remove.success";

    /**
     * No such display slot '%1#s'
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_INVALIDSLOT = "minecraft:commands.scoreboard.objectives.setdisplay.invalidSlot";

    /**
     * Cleared objective display slot '%1#s'
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSCLEARED = "minecraft:commands.scoreboard.objectives.setdisplay.successCleared";

    /**
     * Set the display objective in slot '%1#s' to '%2#s'
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSSET = "minecraft:commands.scoreboard.objectives.setdisplay.successSet";

    /**
     * Added %1#d to [%2#s] for %3#d entities
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ADD_MULTIPLE_SUCCESS = "minecraft:commands.scoreboard.players.add.multiple.success";

    /**
     * Added %1#d to [%2#s] for %3#s (now %4#d)
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ADD_SUCCESS = "minecraft:commands.scoreboard.players.add.success";

    /**
     * Objective %1#s is not a trigger
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ENABLE_NOTRIGGER = "minecraft:commands.scoreboard.players.enable.noTrigger";

    /**
     * Enabled trigger %1#s for %2#s
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ENABLE_SUCCESS = "minecraft:commands.scoreboard.players.enable.success";

    /**
     * %1#d
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_GET_SUCCESS = "minecraft:commands.scoreboard.players.get.success";

    /**
     * Showing %1#d tracked players on the scoreboard:
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_COUNT = "minecraft:commands.scoreboard.players.list.count";

    /**
     * There are no tracked players on the scoreboard
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_EMPTY = "minecraft:commands.scoreboard.players.list.empty";

    /**
     * Showing %1#d tracked objective(s) for %2#s:
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_PLAYER_COUNT = "minecraft:commands.scoreboard.players.list.player.count";

    /**
     * Player %1#s has no scores recorded
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_PLAYER_EMPTY = "minecraft:commands.scoreboard.players.list.player.empty";

    /**
     * - %2#s: %1#d (%3#s)
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_PLAYER_ENTRY = "minecraft:commands.scoreboard.players.list.player.entry";

    /**
     * A player name must be given.
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_NAMENOTFOUND = "minecraft:commands.scoreboard.players.nameNotFound";

    /**
     * Player Offline
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OFFLINEPLAYERNAME = "minecraft:commands.scoreboard.players.offlinePlayerName";

    /**
     * Operation results in integer overflow
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_INTEGEROVERFLOW = "minecraft:commands.scoreboard.players.operation.integerOverflow";

    /**
     * Invalid operation %1#s
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_INVALIDOPERATION = "minecraft:commands.scoreboard.players.operation.invalidOperation";

    /**
     * No %1#s score for %2#s found
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_NOTFOUND = "minecraft:commands.scoreboard.players.operation.notFound";

    /**
     * Updated [%1#s] for %2#d entities
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_SUCCESS = "minecraft:commands.scoreboard.players.operation.success";

    /**
     * Min %1#d is not less than max %2#d
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_RANDOM_INVALIDRANGE = "minecraft:commands.scoreboard.players.random.invalidRange";

    /**
     * Removed %1#d from [%2#s] for %3#d entities
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_MULTIPLE_SUCCESS = "minecraft:commands.scoreboard.players.remove.multiple.success";

    /**
     * Removed %1#d from [%2#s] for %3#s (now %4#d)
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_SUCCESS = "minecraft:commands.scoreboard.players.remove.success";

    /**
     * Reset scores of player %1#s
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_RESET_SUCCESS = "minecraft:commands.scoreboard.players.reset.success";

    /**
     * Reset score %1#s of player %2#s
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_RESETSCORE_SUCCESS = "minecraft:commands.scoreboard.players.resetscore.success";

    /**
     * No %1#s score for %2#s found
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SCORE_NOTFOUND = "minecraft:commands.scoreboard.players.score.notFound";

    /**
     * Set [%1#s] for %2#d entities to %3#d
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_MULTIPLE_SUCCESS = "minecraft:commands.scoreboard.players.set.multiple.success";

    /**
     * Set [%1#s] for %2#s to %3#d
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_SUCCESS = "minecraft:commands.scoreboard.players.set.success";

    /**
     * Could not parse dataTag, reason: %1#s
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_TAGERROR = "minecraft:commands.scoreboard.players.set.tagError";

    /**
     * The dataTag does not match for %1#s
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_TAGMISMATCH = "minecraft:commands.scoreboard.players.set.tagMismatch";

    /**
     * Score %1#d is NOT in range %2#d to %3#d
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_TEST_FAILED = "minecraft:commands.scoreboard.players.test.failed";

    /**
     * Score %1#d is in range %2#d to %3#d
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_TEST_SUCCESS = "minecraft:commands.scoreboard.players.test.success";

    /**
     * No team was found by the name '%1#s'
     */
    String MC_COMMANDS_SCOREBOARD_TEAMNOTFOUND = "minecraft:commands.scoreboard.teamNotFound";

    /**
     * A team with the name '%1#s' already exists
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_ALREADYEXISTS = "minecraft:commands.scoreboard.teams.add.alreadyExists";

    /**
     * The display name '%1#s' is too long for a team, it can be at most %2#d characters long
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_DISPLAYTOOLONG = "minecraft:commands.scoreboard.teams.add.displayTooLong";

    /**
     * Added new team '%1#s' successfully
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_SUCCESS = "minecraft:commands.scoreboard.teams.add.success";

    /**
     * The name '%1#s' is too long for a team, it can be at most %2#d characters long
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_TOOLONG = "minecraft:commands.scoreboard.teams.add.tooLong";

    /**
     * Team %1#s is already empty, cannot remove nonexistent players
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_EMPTY_ALREADYEMPTY = "minecraft:commands.scoreboard.teams.empty.alreadyEmpty";

    /**
     * Removed all %1#d player(s) from team %2#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_EMPTY_SUCCESS = "minecraft:commands.scoreboard.teams.empty.success";

    /**
     * Could not add %1#d player(s) to team %2#s: %3#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_JOIN_FAILURE = "minecraft:commands.scoreboard.teams.join.failure";

    /**
     * Added %1#d player(s) to team %2#s: %3#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_JOIN_SUCCESS = "minecraft:commands.scoreboard.teams.join.success";

    /**
     * Could not remove %1#d player(s) from their teams: %2#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LEAVE_FAILURE = "minecraft:commands.scoreboard.teams.leave.failure";

    /**
     * You are not in a team
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LEAVE_NOTEAM = "minecraft:commands.scoreboard.teams.leave.noTeam";

    /**
     * Removed %1#d player(s) from their teams: %2#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LEAVE_SUCCESS = "minecraft:commands.scoreboard.teams.leave.success";

    /**
     * Showing %1#d teams on the scoreboard:
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_COUNT = "minecraft:commands.scoreboard.teams.list.count";

    /**
     * There are no teams registered on the scoreboard
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_EMPTY = "minecraft:commands.scoreboard.teams.list.empty";

    /**
     * - %1#s: '%2#s' has %3#d players
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_ENTRY = "minecraft:commands.scoreboard.teams.list.entry";

    /**
     * Showing %1#d player(s) in team %2#s:
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_PLAYER_COUNT = "minecraft:commands.scoreboard.teams.list.player.count";

    /**
     * Team %1#s has no players
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_PLAYER_EMPTY = "minecraft:commands.scoreboard.teams.list.player.empty";

    /**
     * - %2#s: %1#d (%3#s)
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_PLAYER_ENTRY = "minecraft:commands.scoreboard.teams.list.player.entry";

    /**
     * Valid values for option %1#s are: %2#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_OPTION_NOVALUE = "minecraft:commands.scoreboard.teams.option.noValue";

    /**
     * Set option %1#s for team %2#s to %3#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_OPTION_SUCCESS = "minecraft:commands.scoreboard.teams.option.success";

    /**
     * Removed team %1#s
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_REMOVE_SUCCESS = "minecraft:commands.scoreboard.teams.remove.success";

    /**
     * Applies shaking to the players' camera with a specified intensity and duration.
     */
    String MC_COMMANDS_SCREENSHAKE_DESCRIPTION = "minecraft:commands.screenshake.description";

    /**
     * Sent a request to the following players for their camera to shake: %s
     */
    String MC_COMMANDS_SCREENSHAKE_SUCCESS = "minecraft:commands.screenshake.success";

    /**
     * Stopping camera shake for the following players: %s
     */
    String MC_COMMANDS_SCREENSHAKE_SUCCESSSTOP = "minecraft:commands.screenshake.successStop";

    /**
     * Triggers a script event with an ID and message.
     */
    String MC_COMMANDS_SCRIPTEVENT_DESCRIPTION = "minecraft:commands.scriptevent.description";

    /**
     * Identifier must have a namespace that is not "minecraft"
     */
    String MC_COMMANDS_SCRIPTEVENT_INVALIDNAMESPACE = "minecraft:commands.scriptevent.invalidNamespace";

    /**
     * Message length exceeds (%1#d) character limit!
     */
    String MC_COMMANDS_SCRIPTEVENT_MESSAGESIZEEXCEED = "minecraft:commands.scriptevent.messageSizeExceed";

    /**
     * Seed: %1#s
     */
    String MC_COMMANDS_SEED_SUCCESS = "minecraft:commands.seed.success";

    /**
     * Only one entity is allowed, but the provided selector allows more than one
     */
    String MC_COMMANDS_SELECTOR_ERROR_MORETHANONE = "minecraft:commands.selector.error.moreThanOne";

    /**
     * Changes a block to another block.
     */
    String MC_COMMANDS_SETBLOCK_DESCRIPTION = "minecraft:commands.setblock.description";

    /**
     * Unable to place block
     */
    String MC_COMMANDS_SETBLOCK_FAILED = "minecraft:commands.setblock.failed";

    /**
     * The block couldn't be placed
     */
    String MC_COMMANDS_SETBLOCK_NOCHANGE = "minecraft:commands.setblock.noChange";

    /**
     * There is no such block with ID/name %1#s
     */
    String MC_COMMANDS_SETBLOCK_NOTFOUND = "minecraft:commands.setblock.notFound";

    /**
     * Cannot place block outside of the world
     */
    String MC_COMMANDS_SETBLOCK_OUTOFWORLD = "minecraft:commands.setblock.outOfWorld";

    /**
     * Block placed
     */
    String MC_COMMANDS_SETBLOCK_SUCCESS = "minecraft:commands.setblock.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_SETBLOCK_TAGERROR = "minecraft:commands.setblock.tagError";

    /**
     * Sets a fixed slot to a specified item.
     */
    String MC_COMMANDS_SETFIXEDINVSLOT_DESCRIPTION = "minecraft:commands.setfixedinvslot.description";

    /**
     * Fixed Inventory Slot %1#d set to %2#s
     */
    String MC_COMMANDS_SETFIXEDINVSLOT_SUCCESS = "minecraft:commands.setfixedinvslot.success";

    /**
     * Sets the number of fixed inventory slots for the server.
     */
    String MC_COMMANDS_SETFIXEDINVSLOTS_DESCRIPTION = "minecraft:commands.setfixedinvslots.description";

    /**
     * Number of Fixed Inventory Slots set to %1#d
     */
    String MC_COMMANDS_SETFIXEDINVSLOTS_SUCCESS = "minecraft:commands.setfixedinvslots.success";

    /**
     * Successfully set the idle timeout to %1#d minutes.
     */
    String MC_COMMANDS_SETIDLETIMEOUT_SUCCESS = "minecraft:commands.setidletimeout.success";

    /**
     * Sets the maximum number of players for this game session.
     */
    String MC_COMMANDS_SETMAXPLAYERS_DESCRIPTION = "minecraft:commands.setmaxplayers.description";

    /**
     * Set max players to %1#d.
     */
    String MC_COMMANDS_SETMAXPLAYERS_SUCCESS = "minecraft:commands.setmaxplayers.success";

    /**
     * (Bound to current player count)
     */
    String MC_COMMANDS_SETMAXPLAYERS_SUCCESS_LOWERBOUND = "minecraft:commands.setmaxplayers.success.lowerbound";

    /**
     * (Bound to maximum allowed connections)
     */
    String MC_COMMANDS_SETMAXPLAYERS_SUCCESS_UPPERBOUND = "minecraft:commands.setmaxplayers.success.upperbound";

    /**
     * Controls whether the client or the server is responsible for checking if a player's movement is valid. Use "client" to set to client, or "server" to set to server.
     */
    String MC_COMMANDS_SETMOVEMENTAUTHORITY_DESCRIPTION = "minecraft:commands.setmovementauthority.description";

    /**
     * Not allowed to set movement authority to %s, current value is %s
     */
    String MC_COMMANDS_SETMOVEMENTAUTHORITY_ERROR = "minecraft:commands.setmovementauthority.error";

    /**
     * Set movement authority to %s
     */
    String MC_COMMANDS_SETMOVEMENTAUTHORITY_SUCCESS = "minecraft:commands.setmovementauthority.success";

    /**
     * Sets the world spawn.
     */
    String MC_COMMANDS_SETWORLDSPAWN_DESCRIPTION = "minecraft:commands.setworldspawn.description";

    /**
     * The world spawn can not be set in legacy worlds
     */
    String MC_COMMANDS_SETWORLDSPAWN_NOTALLOWEDINLEGACYWORLDS = "minecraft:commands.setworldspawn.notAllowedInLegacyWorlds";

    /**
     * Set the world spawn point to (%1#d, %2#d, %3#d)
     */
    String MC_COMMANDS_SETWORLDSPAWN_SUCCESS = "minecraft:commands.setworldspawn.success";

    /**
     * The world spawn can not be set in this dimension
     */
    String MC_COMMANDS_SETWORLDSPAWN_WRONGDIMENSION = "minecraft:commands.setworldspawn.wrongDimension";

    /**
     * Creates a particle emitter
     */
    String MC_COMMANDS_SPAWNPARTICLEEMITTER_DESCRIPTION = "minecraft:commands.spawnParticleEmitter.description";

    /**
     * Request to create %1#s sent to all players.
     */
    String MC_COMMANDS_SPAWNPARTICLEEMITTER_SUCCESS = "minecraft:commands.spawnParticleEmitter.success";

    /**
     * Sets the spawn point for a player.
     */
    String MC_COMMANDS_SPAWNPOINT_DESCRIPTION = "minecraft:commands.spawnpoint.description";

    /**
     * Set spawn point for %1#s
     */
    String MC_COMMANDS_SPAWNPOINT_SUCCESS_MULTIPLE_GENERIC = "minecraft:commands.spawnpoint.success.multiple.generic";

    /**
     * Set spawn point for %1#s to (%2#d, %3#d, %4#d)
     */
    String MC_COMMANDS_SPAWNPOINT_SUCCESS_MULTIPLE_SPECIFIC = "minecraft:commands.spawnpoint.success.multiple.specific";

    /**
     * Set %1#s's spawn point to (%2#d, %3#d, %4#d)
     */
    String MC_COMMANDS_SPAWNPOINT_SUCCESS_SINGLE = "minecraft:commands.spawnpoint.success.single";

    /**
     * The spawn point cannot be set in this dimension
     */
    String MC_COMMANDS_SPAWNPOINT_WRONGDIMENSION = "minecraft:commands.spawnpoint.wrongDimension";

    /**
     * Teleports entities to random locations.
     */
    String MC_COMMANDS_SPREADPLAYERS_DESCRIPTION = "minecraft:commands.spreadplayers.description";

    /**
     * Could not spread %1#s players around %2#s,%3#s (too many players for space - try using spread of at most %4#s)
     */
    String MC_COMMANDS_SPREADPLAYERS_FAILURE_PLAYERS = "minecraft:commands.spreadplayers.failure.players";

    /**
     * Could not spread %1#s players around %2#s,%3#s (no safe locations found after %4#s attempts)
     */
    String MC_COMMANDS_SPREADPLAYERS_FAILURE_PLAYERS_UNSAFE = "minecraft:commands.spreadplayers.failure.players.unsafe";

    /**
     * Could not spread %1#s teams around %2#s,%3#s (too many players for space - try using spread of at most %4#s)
     */
    String MC_COMMANDS_SPREADPLAYERS_FAILURE_TEAMS = "minecraft:commands.spreadplayers.failure.teams";

    /**
     * (Average distance between players is %1#s blocks apart after %2#s iterations)
     */
    String MC_COMMANDS_SPREADPLAYERS_INFO_PLAYERS = "minecraft:commands.spreadplayers.info.players";

    /**
     * (Average distance between teams is %1#s blocks apart after %2#s iterations)
     */
    String MC_COMMANDS_SPREADPLAYERS_INFO_TEAMS = "minecraft:commands.spreadplayers.info.teams";

    /**
     * Spreading %1#s players %2#s blocks around %3#s,%4#s (min %5#s blocks apart)
     */
    String MC_COMMANDS_SPREADPLAYERS_SPREADING_PLAYERS = "minecraft:commands.spreadplayers.spreading.players";

    /**
     * Spreading %1#s teams %2#s blocks around %3#s,%4#s (min %5#s blocks apart)
     */
    String MC_COMMANDS_SPREADPLAYERS_SPREADING_TEAMS = "minecraft:commands.spreadplayers.spreading.teams";

    /**
     * Successfully spread %1#s players around %2#s,%3#s
     */
    String MC_COMMANDS_SPREADPLAYERS_SUCCESS_PLAYERS = "minecraft:commands.spreadplayers.success.players";

    /**
     * Successfully spread %1#s teams around %2#s,%3#s
     */
    String MC_COMMANDS_SPREADPLAYERS_SUCCESS_TEAMS = "minecraft:commands.spreadplayers.success.teams";

    /**
     * Cleared %1#s stats
     */
    String MC_COMMANDS_STATS_CLEARED = "minecraft:commands.stats.cleared";

    /**
     * Invalid parameters
     */
    String MC_COMMANDS_STATS_FAILED = "minecraft:commands.stats.failed";

    /**
     * Block at %1#d, %2#d, %3#d can not track stats
     */
    String MC_COMMANDS_STATS_NOCOMPATIBLEBLOCK = "minecraft:commands.stats.noCompatibleBlock";

    /**
     * Storing %1#s stats in %2#s on %3#s
     */
    String MC_COMMANDS_STATS_SUCCESS = "minecraft:commands.stats.success";

    /**
     * Stops the server.
     */
    String MC_COMMANDS_STOP_DESCRIPTION = "minecraft:commands.stop.description";

    /**
     * Stopping the server
     */
    String MC_COMMANDS_STOP_START = "minecraft:commands.stop.start";

    /**
     * Stops a sound.
     */
    String MC_COMMANDS_STOPSOUND_DESCRIPTION = "minecraft:commands.stopsound.description";

    /**
     * Stopped sound %s for %s
     */
    String MC_COMMANDS_STOPSOUND_SUCCESS = "minecraft:commands.stopsound.success";

    /**
     * Stopped all sounds for %s
     */
    String MC_COMMANDS_STOPSOUND_SUCCESS_ALL = "minecraft:commands.stopsound.success.all";

    /**
     * Structure %s deleted.
     */
    String MC_COMMANDS_STRUCTURE_DELETE_SUCCESS = "minecraft:commands.structure.delete.success";

    /**
     * Saves or loads a structure in the world.
     */
    String MC_COMMANDS_STRUCTURE_DESCRIPTION = "minecraft:commands.structure.description";

    /**
     * Tried to find a structure with name %s but had %1#s results for that name
     */
    String MC_COMMANDS_STRUCTURE_EDITOR_AMBIGUOUSQUERY = "minecraft:commands.structure.editor.ambiguousquery";

    /**
     * Can't load a structure with no entities and no blocks
     */
    String MC_COMMANDS_STRUCTURE_EMPTY_LOAD = "minecraft:commands.structure.empty.load";

    /**
     * Can't save a structure with no entities and no blocks
     */
    String MC_COMMANDS_STRUCTURE_EMPTY_SAVE = "minecraft:commands.structure.empty.save";

    /**
     * An unexpected error occurred
     */
    String MC_COMMANDS_STRUCTURE_ERROR = "minecraft:commands.structure.error";

    /**
     * Animation time must be 0 or higher
     */
    String MC_COMMANDS_STRUCTURE_INVALID_ANIMATIONTIME = "minecraft:commands.structure.invalid.animationTime";

    /**
     * Integrity must be in the range of 0 to 100
     */
    String MC_COMMANDS_STRUCTURE_INVALID_INTEGRITY = "minecraft:commands.structure.invalid.integrity";

    /**
     * Name should be in the format of "name" or "namespace:name". If not namespace is provided, the default one will be assigned.
     */
    String MC_COMMANDS_STRUCTURE_INVALID_NAME = "minecraft:commands.structure.invalid.name";

    /**
     * A placement request has been queued and it will be executed when the specified area is fully loaded.
     */
    String MC_COMMANDS_STRUCTURE_LOAD_QUEUED = "minecraft:commands.structure.load.queued";

    /**
     * Loaded a structure of name %s
     */
    String MC_COMMANDS_STRUCTURE_LOAD_SUCCESS = "minecraft:commands.structure.load.success";

    /**
     * The structure %s can't be found. Make sure the name was spelled correctly and try again.
     */
    String MC_COMMANDS_STRUCTURE_NOTFOUND = "minecraft:commands.structure.notFound";

    /**
     * Saved a structure with name %s
     */
    String MC_COMMANDS_STRUCTURE_SAVE_SUCCESS = "minecraft:commands.structure.save.success";

    /**
     * A structure's size cannot be larger than (%1#d, %2#d, %3#d), it was (%4#d, %5#d, %6#d)
     */
    String MC_COMMANDS_STRUCTURE_SIZE_TOOBIG = "minecraft:commands.structure.size.tooBig";

    /**
     * A structure's size cannot be smaller than (%1#d, %2#d, %3#d), it was (%4#d, %5#d, %6#d)
     */
    String MC_COMMANDS_STRUCTURE_SIZE_TOOSMALL = "minecraft:commands.structure.size.tooSmall";

    /**
     * Unknown structure action provided
     */
    String MC_COMMANDS_STRUCTURE_UNKNOWN_ACTION = "minecraft:commands.structure.unknown.action";

    /**
     * The feature to load a structure in an unloaded area hasn't been implemented yet
     */
    String MC_COMMANDS_STRUCTURE_UNLOADEDCHUNKS = "minecraft:commands.structure.unloadedChunks";

    /**
     * Cannot place blocks outside of the world. Try a value of %d or lower for the Y component of <to: x y z>.
     */
    String MC_COMMANDS_STRUCTURE_Y_TOOHIGH = "minecraft:commands.structure.y.tooHigh";

    /**
     * Cannot place blocks outside of the world. Try a value greater than %d for the Y component of <to: x y z>.
     */
    String MC_COMMANDS_STRUCTURE_Y_TOOLOW = "minecraft:commands.structure.y.tooLow";

    /**
     * Summons an entity.
     */
    String MC_COMMANDS_SUMMON_DESCRIPTION = "minecraft:commands.summon.description";

    /**
     * Unable to summon object
     */
    String MC_COMMANDS_SUMMON_FAILED = "minecraft:commands.summon.failed";

    /**
     * You need Creative + Operator to summon an NPC
     */
    String MC_COMMANDS_SUMMON_FAILED_NPC = "minecraft:commands.summon.failed.npc";

    /**
     * Cannot summon the object out of the world
     */
    String MC_COMMANDS_SUMMON_OUTOFWORLD = "minecraft:commands.summon.outOfWorld";

    /**
     * Object successfully summoned
     */
    String MC_COMMANDS_SUMMON_SUCCESS = "minecraft:commands.summon.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_SUMMON_TAGERROR = "minecraft:commands.summon.tagError";

    /**
     * Target either already has the tag or has too many tags
     */
    String MC_COMMANDS_TAG_ADD_FAILED = "minecraft:commands.tag.add.failed";

    /**
     * Added tag '%1#s' to %2#d entities
     */
    String MC_COMMANDS_TAG_ADD_SUCCESS_MULTIPLE = "minecraft:commands.tag.add.success.multiple";

    /**
     * Added tag '%1#s' to %2#s
     */
    String MC_COMMANDS_TAG_ADD_SUCCESS_SINGLE = "minecraft:commands.tag.add.success.single";

    /**
     * Manages tags stored in entities.
     */
    String MC_COMMANDS_TAG_DESCRIPTION = "minecraft:commands.tag.description";

    /**
     * There are no tags on the %d entities
     */
    String MC_COMMANDS_TAG_LIST_MULTIPLE_EMPTY = "minecraft:commands.tag.list.multiple.empty";

    /**
     * The %1#d entities have %2#d total tags: %3#s
     */
    String MC_COMMANDS_TAG_LIST_MULTIPLE_SUCCESS = "minecraft:commands.tag.list.multiple.success";

    /**
     * %s has no tags
     */
    String MC_COMMANDS_TAG_LIST_SINGLE_EMPTY = "minecraft:commands.tag.list.single.empty";

    /**
     * %1#s has %2#d tags: %3#s
     */
    String MC_COMMANDS_TAG_LIST_SINGLE_SUCCESS = "minecraft:commands.tag.list.single.success";

    /**
     * Target does not have this tag
     */
    String MC_COMMANDS_TAG_REMOVE_FAILED = "minecraft:commands.tag.remove.failed";

    /**
     * Removed tag '%1#s' from %2#d entities
     */
    String MC_COMMANDS_TAG_REMOVE_SUCCESS_MULTIPLE = "minecraft:commands.tag.remove.success.multiple";

    /**
     * Removed tag '%1#s' from %2#s
     */
    String MC_COMMANDS_TAG_REMOVE_SUCCESS_SINGLE = "minecraft:commands.tag.remove.success.single";

    /**
     * Lists all tags associated with the given item.
     */
    String MC_COMMANDS_TAGSFROMITEM_DESCRIPTION = "minecraft:commands.tagsfromitem.description";

    /**
     * No tags found on the item: %s.
     */
    String MC_COMMANDS_TAGSFROMITEM_FAIL_NOTAGSFOUND = "minecraft:commands.tagsfromitem.fail.notagsfound";

    /**
     * %s
     */
    String MC_COMMANDS_TAGSFROMITEM_SUCCESS_LIST_TAGS = "minecraft:commands.tagsfromitem.success.list.tags";

    /**
     * There are %d tags on the item %s:
     */
    String MC_COMMANDS_TAGSFROMITEM_SUCCESS_MULTIPLE = "minecraft:commands.tagsfromitem.success.multiple";

    /**
     * There is %d tag on the item %s:
     */
    String MC_COMMANDS_TAGSFROMITEM_SUCCESS_SINGLE = "minecraft:commands.tagsfromitem.success.single";

    /**
     * Sends a private message to one or more players.
     */
    String MC_COMMANDS_TELL_DESCRIPTION = "minecraft:commands.tell.description";

    /**
     * Sends a JSON message to players.
     */
    String MC_COMMANDS_TELLRAW_DESCRIPTION = "minecraft:commands.tellraw.description";

    /**
     * Json value in rawtext array was not an object. Example: "rawtext": [{ "text" : "my text" }]
     */
    String MC_COMMANDS_TELLRAW_ERROR_ITEMISNOTOBJECT = "minecraft:commands.tellraw.error.itemIsNotObject";

    /**
     * No data was provided.
     */
    String MC_COMMANDS_TELLRAW_ERROR_NODATA = "minecraft:commands.tellraw.error.noData";

    /**
     * Rawtext object must contain an array. Example: "rawtext":[{..}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_NOTARRAY = "minecraft:commands.tellraw.error.notArray";

    /**
     * name field in the score field must be a string. Example "rawtext":[{"score": {"name": "my_name", "objective": "my_objective"}}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_SCORENAMENOTSTRING = "minecraft:commands.tellraw.error.scoreNameNotString";

    /**
     * score field in rawtext must contain an object. Example "rawtext":[{"score": {"name": "my_name", "objective": "my_objective"}}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_SCORENOTOBJECT = "minecraft:commands.tellraw.error.scoreNotObject";

    /**
     * objective field in the score field must be a string. Example "rawtext":[{"score": {"name": "my_name", "objective": "my_objective"}}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_SCOREOBJECTIVENOTSTRING = "minecraft:commands.tellraw.error.scoreObjectiveNotString";

    /**
     * selector field in rawtext must contain a selector as a string. Example: "rawtext":[{"selector": "@e"}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_SELECTORNOTSTRING = "minecraft:commands.tellraw.error.selectorNotString";

    /**
     * text field in rawtext must contain a string. Example: "rawtext":[{"text": "text to display"}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_TEXTNOTSTRING = "minecraft:commands.tellraw.error.textNotString";

    /**
     * translate field in rawtext must contain a language key. Example: "rawtext":[{"translate": "gui.ok"}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_TRANSLATENOTSTRING = "minecraft:commands.tellraw.error.translateNotString";

    /**
     * with field in rawtext must contain a array or another rawtext object. Example 1: "rawtext":[{"translate": "chat.type.announcement", "with": [ "value1", "value2" ]}]  Example 2: "rawtext":[{"translate": "chat.type.announcement", "with":{"rawtext":[{"text": "value1"}, {"text": "value2"}]}}]
     */
    String MC_COMMANDS_TELLRAW_ERROR_WITHNOTARRAYORRAWTEXT = "minecraft:commands.tellraw.error.withNotArrayOrRawText";

    /**
     * Invalid json string data.
     */
    String MC_COMMANDS_TELLRAW_JSONSTRINGEXCEPTION = "minecraft:commands.tellraw.jsonStringException";

    /**
     * Counts entities (players, mobs, items, etc.) matching specified conditions.
     */
    String MC_COMMANDS_TESTFOR_DESCRIPTION = "minecraft:commands.testfor.description";

    /**
     * %1#s did not match the required data structure
     */
    String MC_COMMANDS_TESTFOR_FAILURE = "minecraft:commands.testfor.failure";

    /**
     * Found %1#s
     */
    String MC_COMMANDS_TESTFOR_SUCCESS = "minecraft:commands.testfor.success";

    /**
     * Data tag parsing failed: %1#s
     */
    String MC_COMMANDS_TESTFOR_TAGERROR = "minecraft:commands.testfor.tagError";

    /**
     * Tests whether a certain block is in a specific location.
     */
    String MC_COMMANDS_TESTFORBLOCK_DESCRIPTION = "minecraft:commands.testforblock.description";

    /**
     * The block at %1#d,%2#d,%3#d did not match the expected block state.
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_DATA = "minecraft:commands.testforblock.failed.data";

    /**
     * The block at %1#d,%2#d,%3#d did not have the required NBT keys.
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_NBT = "minecraft:commands.testforblock.failed.nbt";

    /**
     * The block at %1#d,%2#d,%3#d is %4#s (expected: %5#s).
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_TILE = "minecraft:commands.testforblock.failed.tile";

    /**
     * The block at %1#d,%2#d,%3#d is not a tile entity and cannot support tag matching.
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_TILEENTITY = "minecraft:commands.testforblock.failed.tileEntity";

    /**
     * Cannot test for block outside of the world
     */
    String MC_COMMANDS_TESTFORBLOCK_OUTOFWORLD = "minecraft:commands.testforblock.outOfWorld";

    /**
     * Successfully found the block at %1#d,%2#d,%3#d.
     */
    String MC_COMMANDS_TESTFORBLOCK_SUCCESS = "minecraft:commands.testforblock.success";

    /**
     * Tests whether the blocks in two regions match.
     */
    String MC_COMMANDS_TESTFORBLOCKS_DESCRIPTION = "minecraft:commands.testforblocks.description";

    /**
     * Added ticking area from %1#d to %2#d marked for preload.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_BOUNDS_PRELOAD_SUCCESS = "minecraft:commands.tickingarea-add-bounds.preload.success";

    /**
     * Added ticking area from %1#d to %2#d.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_BOUNDS_SUCCESS = "minecraft:commands.tickingarea-add-bounds.success";

    /**
     * Added ticking area centered at %1#d with a radius of %2#d chunks marked for preload.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CIRCLE_PRELOAD_SUCCESS = "minecraft:commands.tickingarea-add-circle.preload.success";

    /**
     * Added ticking area centered at %1#d with a radius of %2#d chunks.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CIRCLE_SUCCESS = "minecraft:commands.tickingarea-add-circle.success";

    /**
     * Ticking area contains more than %1#d chunks, ticking area is too large and cannot be created.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CHUNKFAILURE = "minecraft:commands.tickingarea-add.chunkfailure";

    /**
     * A ticking area with the name %1#s already exists.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CONFLICTINGNAME = "minecraft:commands.tickingarea-add.conflictingname";

    /**
     * Max number of ticking areas (%1#d) has already been reached. Cannot add more ticking areas.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_FAILURE = "minecraft:commands.tickingarea-add.failure";

    /**
     * Radius cannot be larger than %1#d, ticking area is too large and cannot be created.
     */
    String MC_COMMANDS_TICKINGAREA_ADD_RADIUSFAILURE = "minecraft:commands.tickingarea-add.radiusfailure";

    /**
     * chunks
     */
    String MC_COMMANDS_TICKINGAREA_LIST_CHUNKS = "minecraft:commands.tickingarea-list.chunks";

    /**
     * Radius
     */
    String MC_COMMANDS_TICKINGAREA_LIST_CIRCLE_RADIUS = "minecraft:commands.tickingarea-list.circle.radius";

    /**
     * No ticking areas exist in any dimension.
     */
    String MC_COMMANDS_TICKINGAREA_LIST_FAILURE_ALLDIMENSIONS = "minecraft:commands.tickingarea-list.failure.allDimensions";

    /**
     * Preload
     */
    String MC_COMMANDS_TICKINGAREA_LIST_PRELOAD = "minecraft:commands.tickingarea-list.preload";

    /**
     * List of all ticking areas in all dimensions
     */
    String MC_COMMANDS_TICKINGAREA_LIST_SUCCESS_ALLDIMENSIONS = "minecraft:commands.tickingarea-list.success.allDimensions";

    /**
     * List of all ticking areas in current dimension
     */
    String MC_COMMANDS_TICKINGAREA_LIST_SUCCESS_CURRENTDIMENSION = "minecraft:commands.tickingarea-list.success.currentDimension";

    /**
     * to
     */
    String MC_COMMANDS_TICKINGAREA_LIST_TO = "minecraft:commands.tickingarea-list.to";

    /**
     * Circle
     */
    String MC_COMMANDS_TICKINGAREA_LIST_TYPE_CIRCLE = "minecraft:commands.tickingarea-list.type.circle";

    /**
     * No ticking areas named %1#s exist in the current dimension.
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_BYNAME_FAILURE = "minecraft:commands.tickingarea-preload.byname.failure";

    /**
     * No ticking areas containing the block position %1#d exist in the current dimension.
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_BYPOSITION_FAILURE = "minecraft:commands.tickingarea-preload.byposition.failure";

    /**
     * %1#d matching area(s) are marked for preload.
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_COUNT = "minecraft:commands.tickingarea-preload.count";

    /**
     * Updated ticking area(s)
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_SUCCESS = "minecraft:commands.tickingarea-preload.success";

    /**
     * No ticking areas named %1#s exist in the current dimension.
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_BYNAME_FAILURE = "minecraft:commands.tickingarea-remove.byname.failure";

    /**
     * No ticking areas containing the block position %1#d exist in the current dimension.
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_FAILURE = "minecraft:commands.tickingarea-remove.failure";

    /**
     * Removed ticking area(s)
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_SUCCESS = "minecraft:commands.tickingarea-remove.success";

    /**
     * No ticking areas exist in the current dimension.
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_ALL_FAILURE = "minecraft:commands.tickingarea-remove_all.failure";

    /**
     * Removed ticking area(s)
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_ALL_SUCCESS = "minecraft:commands.tickingarea-remove_all.success";

    /**
     * Add, remove, or list ticking areas.
     */
    String MC_COMMANDS_TICKINGAREA_DESCRIPTION = "minecraft:commands.tickingarea.description";

    /**
     * There are %1#d ticking area(s) from entities with the tick_world component.
     */
    String MC_COMMANDS_TICKINGAREA_ENTITYCOUNT = "minecraft:commands.tickingarea.entitycount";

    /**
     * %1#d/%2#d ticking areas in use.
     */
    String MC_COMMANDS_TICKINGAREA_INUSE = "minecraft:commands.tickingarea.inuse";

    /**
     * No ticking areas exist in the current dimension.
     */
    String MC_COMMANDS_TICKINGAREA_NONEEXIST_CURRENTDIMENSION = "minecraft:commands.tickingarea.noneExist.currentDimension";

    /**
     * Added %1#d to the time
     */
    String MC_COMMANDS_TIME_ADDED = "minecraft:commands.time.added";

    /**
     * Changes or queries the world's game time.
     */
    String MC_COMMANDS_TIME_DESCRIPTION = "minecraft:commands.time.description";

    /**
     * Always Day is enabled in this world.
     */
    String MC_COMMANDS_TIME_DISABLED = "minecraft:commands.time.disabled";

    /**
     * Day is %d
     */
    String MC_COMMANDS_TIME_QUERY_DAY = "minecraft:commands.time.query.day";

    /**
     * Daytime is %d
     */
    String MC_COMMANDS_TIME_QUERY_DAYTIME = "minecraft:commands.time.query.daytime";

    /**
     * Gametime is %d
     */
    String MC_COMMANDS_TIME_QUERY_GAMETIME = "minecraft:commands.time.query.gametime";

    /**
     * Set the time to %1#d
     */
    String MC_COMMANDS_TIME_SET = "minecraft:commands.time.set";

    /**
     * Time %1#s
     */
    String MC_COMMANDS_TIME_STOP = "minecraft:commands.time.stop";

    /**
     * Controls screen titles.
     */
    String MC_COMMANDS_TITLE_DESCRIPTION = "minecraft:commands.title.description";

    /**
     * Title command successfully executed
     */
    String MC_COMMANDS_TITLE_SUCCESS = "minecraft:commands.title.success";

    /**
     * Controls screen titles with JSON messages.
     */
    String MC_COMMANDS_TITLERAW_DESCRIPTION = "minecraft:commands.titleraw.description";

    /**
     * Titleraw command successfully executed
     */
    String MC_COMMANDS_TITLERAW_SUCCESS = "minecraft:commands.titleraw.success";

    /**
     * Content Log Disabled
     */
    String MC_COMMANDS_TOGGLECONTENTLOG_DISABLED = "minecraft:commands.togglecontentlog.disabled";

    /**
     * Content Log Enabled
     */
    String MC_COMMANDS_TOGGLECONTENTLOG_ENABLED = "minecraft:commands.togglecontentlog.enabled";

    /**
     * Enables/Disables the content log command
     */
    String MC_COMMANDS_TOGGLECONTENTLOG_TOGGLE = "minecraft:commands.togglecontentlog.toggle";

    /**
     * Toggles the weather.
     */
    String MC_COMMANDS_TOGGLEDOWNFALL_DESCRIPTION = "minecraft:commands.toggledownfall.description";

    /**
     * Teleports entities (players, mobs, etc.).
     */
    String MC_COMMANDS_TP_DESCRIPTION = "minecraft:commands.tp.description";

    /**
     * Unable to teleport %1#s to the unloaded area at %2#s
     */
    String MC_COMMANDS_TP_FAR = "minecraft:commands.tp.far";

    /**
     * Unable to teleport because players are not in the same dimension
     */
    String MC_COMMANDS_TP_NOTSAMEDIMENSION = "minecraft:commands.tp.notSameDimension";

    /**
     * Cannot teleport entities outside of the world
     */
    String MC_COMMANDS_TP_OUTOFWORLD = "minecraft:commands.tp.outOfWorld";

    /**
     * You do not have permission to use this slash command.
     */
    String MC_COMMANDS_TP_PERMISSION = "minecraft:commands.tp.permission";

    /**
     * Unable to teleport %1#s to %2#s because the area wasn't clear of blocks.
     */
    String MC_COMMANDS_TP_SAFETELEPORTFAIL = "minecraft:commands.tp.safeTeleportFail";

    /**
     * Teleported %1#s to %2#s
     */
    String MC_COMMANDS_TP_SUCCESS = "minecraft:commands.tp.success";

    /**
     * Teleported %1#s to %2#s, %3#s, %4#s
     */
    String MC_COMMANDS_TP_SUCCESS_COORDINATES = "minecraft:commands.tp.success.coordinates";

    /**
     * You have been teleported to %1#s
     */
    String MC_COMMANDS_TP_SUCCESSVICTIM = "minecraft:commands.tp.successVictim";

    /**
     * Transfers a player to another server.
     */
    String MC_COMMANDS_TRANSFERSERVER_DESCRIPTION = "minecraft:commands.transferserver.description";

    /**
     * Invalid port (0-65535)
     */
    String MC_COMMANDS_TRANSFERSERVER_INVALID_PORT = "minecraft:commands.transferserver.invalid.port";

    /**
     * Transferred player
     */
    String MC_COMMANDS_TRANSFERSERVER_SUCCESSFUL = "minecraft:commands.transferserver.successful";

    /**
     * Sets a trigger to be activated.
     */
    String MC_COMMANDS_TRIGGER_DESCRIPTION = "minecraft:commands.trigger.description";

    /**
     * Trigger %1#s is not enabled
     */
    String MC_COMMANDS_TRIGGER_DISABLED = "minecraft:commands.trigger.disabled";

    /**
     * Invalid trigger mode %1#s
     */
    String MC_COMMANDS_TRIGGER_INVALIDMODE = "minecraft:commands.trigger.invalidMode";

    /**
     * Invalid trigger name %1#s
     */
    String MC_COMMANDS_TRIGGER_INVALIDOBJECTIVE = "minecraft:commands.trigger.invalidObjective";

    /**
     * Only players can use the /trigger command
     */
    String MC_COMMANDS_TRIGGER_INVALIDPLAYER = "minecraft:commands.trigger.invalidPlayer";

    /**
     * Trigger %1#s changed with %2#s %3#s
     */
    String MC_COMMANDS_TRIGGER_SUCCESS = "minecraft:commands.trigger.success";

    /**
     * Could not unban player %1#s
     */
    String MC_COMMANDS_UNBAN_FAILED = "minecraft:commands.unban.failed";

    /**
     * Unbanned player %1#s
     */
    String MC_COMMANDS_UNBAN_SUCCESS = "minecraft:commands.unban.success";

    /**
     * You have entered an invalid IP address
     */
    String MC_COMMANDS_UNBANIP_INVALID = "minecraft:commands.unbanip.invalid";

    /**
     * Unbanned IP address %1#s
     */
    String MC_COMMANDS_UNBANIP_SUCCESS = "minecraft:commands.unbanip.success";

    /**
     * Region to check for lighting is too big! (%1#d > %2#d)
     */
    String MC_COMMANDS_VALIDATEGAMELIGHTING_CHECKREGIONTOOBIG = "minecraft:commands.validategamelighting.checkRegionTooBig";

    /**
     * Validate game lighting for a specified region
     */
    String MC_COMMANDS_VALIDATEGAMELIGHTING_DESCRIPTION = "minecraft:commands.validategamelighting.description";

    /**
     * Cannot check lighting outside of the world
     */
    String MC_COMMANDS_VALIDATEGAMELIGHTING_OUTOFWORLD = "minecraft:commands.validategamelighting.outOfWorld";

    /**
     * No volume definition matching identifier "%1#s".
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_DEFINITIONDOESNTEXIST_FAILURE = "minecraft:commands.volumearea.add.definitionDoesntExist.failure";

    /**
     * Max number of volumes %1#d has already been reached. Cannot add more volumes.
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_EXCEEDEDLIMIT_FAILURE = "minecraft:commands.volumearea.add.exceededLimit.failure";

    /**
     * Failed to create volume.
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_FAILURE = "minecraft:commands.volumearea.add.failure";

    /**
     * Added volume "%1#s" from %2#d to %3#d.
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_SUCCESS = "minecraft:commands.volumearea.add.success";

    /**
     * Add, remove, or list volumes in the current dimension.
     */
    String MC_COMMANDS_VOLUMEAREA_DESCRIPTION = "minecraft:commands.volumearea.description";

    /**
     * - %s: %s from %d %d %d to %d %d %d
     */
    String MC_COMMANDS_VOLUMEAREA_ENTRY_WITHIDENTIFIER = "minecraft:commands.volumearea.entry.withIdentifier";

    /**
     * - %s: from %d %d %d to %d %d %d
     */
    String MC_COMMANDS_VOLUMEAREA_ENTRY_WITHOUTIDENTIFIER = "minecraft:commands.volumearea.entry.withoutIdentifier";

    /**
     * %1#d/%2#d volumes in use.
     */
    String MC_COMMANDS_VOLUMEAREA_INUSE = "minecraft:commands.volumearea.inUse";

    /**
     * No volumes exist in any dimension.
     */
    String MC_COMMANDS_VOLUMEAREA_LIST_FAILURE_ALLDIMENSIONS = "minecraft:commands.volumearea.list.failure.allDimensions";

    /**
     * List of all volumes in all dimensions
     */
    String MC_COMMANDS_VOLUMEAREA_LIST_SUCCESS_ALLDIMENSIONS = "minecraft:commands.volumearea.list.success.allDimensions";

    /**
     * List of all volumes in current dimension
     */
    String MC_COMMANDS_VOLUMEAREA_LIST_SUCCESS_CURRENTDIMENSION = "minecraft:commands.volumearea.list.success.currentDimension";

    /**
     * No volumes exist in the current dimension.
     */
    String MC_COMMANDS_VOLUMEAREA_NONEEXIST_CURRENTDIMENSION = "minecraft:commands.volumearea.noneExist.currentDimension";

    /**
     * No volumes named %1#s exist in the current dimension.
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_BYNAME_FAILURE = "minecraft:commands.volumearea.remove.byName.failure";

    /**
     * No volumes containing the block position %1#d exist in the current dimension.
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_BYPOSITION_FAILURE = "minecraft:commands.volumearea.remove.byPosition.failure";

    /**
     * Removed volume(s) in current dimension
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_SUCCESS = "minecraft:commands.volumearea.remove.success";

    /**
     * Removed volume(s) in current dimension
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_ALL_SUCCESS = "minecraft:commands.volumearea.remove_all.success";

    /**
     * Changing to clear weather
     */
    String MC_COMMANDS_WEATHER_CLEAR = "minecraft:commands.weather.clear";

    /**
     * Sets the weather.
     */
    String MC_COMMANDS_WEATHER_DESCRIPTION = "minecraft:commands.weather.description";

    /**
     * Weather Cycle isn't enabled in this world.
     */
    String MC_COMMANDS_WEATHER_DISABLED = "minecraft:commands.weather.disabled";

    /**
     * Weather state is: %s
     */
    String MC_COMMANDS_WEATHER_QUERY = "minecraft:commands.weather.query";

    /**
     * clear
     */
    String MC_COMMANDS_WEATHER_QUERY_CLEAR = "minecraft:commands.weather.query.clear";

    /**
     * rain
     */
    String MC_COMMANDS_WEATHER_QUERY_RAIN = "minecraft:commands.weather.query.rain";

    /**
     * thunder
     */
    String MC_COMMANDS_WEATHER_QUERY_THUNDER = "minecraft:commands.weather.query.thunder";

    /**
     * Changing to rainy weather
     */
    String MC_COMMANDS_WEATHER_RAIN = "minecraft:commands.weather.rain";

    /**
     * Changing to rain and thunder
     */
    String MC_COMMANDS_WEATHER_THUNDER = "minecraft:commands.weather.thunder";

    /**
     * Added %1#d to the world's age
     */
    String MC_COMMANDS_WORLD_AGE_ADDED = "minecraft:commands.world_age.added";

    /**
     * Changes or queries the world's age (time since creation).
     */
    String MC_COMMANDS_WORLD_AGE_DESCRIPTION = "minecraft:commands.world_age.description";

    /**
     * World age is %d
     */
    String MC_COMMANDS_WORLD_AGE_QUERY = "minecraft:commands.world_age.query";

    /**
     * Set the world's age to %1#d
     */
    String MC_COMMANDS_WORLD_AGE_SET = "minecraft:commands.world_age.set";

    /**
     * Set world border center to %1#s,%2#s
     */
    String MC_COMMANDS_WORLDBORDER_CENTER_SUCCESS = "minecraft:commands.worldborder.center.success";

    /**
     * Set world border damage amount to %1#s per block (from %2#s per block)
     */
    String MC_COMMANDS_WORLDBORDER_DAMAGE_AMOUNT_SUCCESS = "minecraft:commands.worldborder.damage.amount.success";

    /**
     * Set world border damage buffer to %1#s blocks (from %2#s blocks)
     */
    String MC_COMMANDS_WORLDBORDER_DAMAGE_BUFFER_SUCCESS = "minecraft:commands.worldborder.damage.buffer.success";

    /**
     * World border is currently %1#s blocks wide
     */
    String MC_COMMANDS_WORLDBORDER_GET_SUCCESS = "minecraft:commands.worldborder.get.success";

    /**
     * Set world border to %1#s blocks wide (from %2#s blocks)
     */
    String MC_COMMANDS_WORLDBORDER_SET_SUCCESS = "minecraft:commands.worldborder.set.success";

    /**
     * Growing world border to %1#s blocks wide (up from %2#s blocks) over %3#s seconds
     */
    String MC_COMMANDS_WORLDBORDER_SETSLOWLY_GROW_SUCCESS = "minecraft:commands.worldborder.setSlowly.grow.success";

    /**
     * Shrinking world border to %1#s blocks wide (down from %2#s blocks) over %3#s seconds
     */
    String MC_COMMANDS_WORLDBORDER_SETSLOWLY_SHRINK_SUCCESS = "minecraft:commands.worldborder.setSlowly.shrink.success";

    /**
     * Set world border warning to %1#s blocks away (from %2#s blocks)
     */
    String MC_COMMANDS_WORLDBORDER_WARNING_DISTANCE_SUCCESS = "minecraft:commands.worldborder.warning.distance.success";

    /**
     * Set world border warning to %1#s seconds away (from %2#s seconds)
     */
    String MC_COMMANDS_WORLDBORDER_WARNING_TIME_SUCCESS = "minecraft:commands.worldborder.warning.time.success";

    /**
     * Toggle World Builder status of caller.
     */
    String MC_COMMANDS_WORLDBUILDER_DESCRIPTION = "minecraft:commands.worldbuilder.description";

    /**
     * World Builder status updated to %1#s
     */
    String MC_COMMANDS_WORLDBUILDER_SUCCESS = "minecraft:commands.worldbuilder.success";

    /**
     * Attempts to connect to the websocket server on the provided URL.
     */
    String MC_COMMANDS_WSSERVER_DESCRIPTION = "minecraft:commands.wsserver.description";

    /**
     * The provided server URL is invalid
     */
    String MC_COMMANDS_WSSERVER_INVALID_URL = "minecraft:commands.wsserver.invalid.url";

    /**
     * Another connection request is currently running
     */
    String MC_COMMANDS_WSSERVER_REQUEST_EXISTING = "minecraft:commands.wsserver.request.existing";

    /**
     * Could not connect to server: %1#s
     */
    String MC_COMMANDS_WSSERVER_REQUEST_FAILED = "minecraft:commands.wsserver.request.failed";

    /**
     * Websocket server request rejected, go to Settings to enable.
     */
    String MC_COMMANDS_WSSERVER_REQUEST_FAILED_DISABLED = "minecraft:commands.wsserver.request.failed.disabled";

    /**
     * Connection established to server: %1#s
     */
    String MC_COMMANDS_WSSERVER_SUCCESS = "minecraft:commands.wsserver.success";

    /**
     * Adds or removes player experience.
     */
    String MC_COMMANDS_XP_DESCRIPTION = "minecraft:commands.xp.description";

    /**
     * Cannot give player negative experience points
     */
    String MC_COMMANDS_XP_FAILURE_WIDTHDRAWXP = "minecraft:commands.xp.failure.widthdrawXp";

    /**
     * Gave %1#d experience to %2#s
     */
    String MC_COMMANDS_XP_SUCCESS = "minecraft:commands.xp.success";

    /**
     * Gave %1#d levels to %2#s
     */
    String MC_COMMANDS_XP_SUCCESS_LEVELS = "minecraft:commands.xp.success.levels";

    /**
     * Taken %1#d levels from %2#s
     */
    String MC_COMMANDS_XP_SUCCESS_NEGATIVE_LEVELS = "minecraft:commands.xp.success.negative.levels";

    /**
     * You've been playing for greater than 24 hours
     */
    String MC_COMPLIANCE_PLAYTIME_GREATERTHAN24HOURS = "minecraft:compliance.playtime.greaterThan24Hours";

    /**
     * Excessive gaming may interfere with normal daily life
     */
    String MC_COMPLIANCE_PLAYTIME_MESSAGE = "minecraft:compliance.playtime.message";

    /**
     * You've been playing for %d hours
     */
    String MC_COMPLIANCE_PLAYTIME_MULTIPLEHOURS = "minecraft:compliance.playtime.multipleHours";

    /**
     * You've been playing for 1 hour
     */
    String MC_COMPLIANCE_PLAYTIME_ONEHOUR = "minecraft:compliance.playtime.oneHour";

    /**
     * Logging in...
     */
    String MC_CONNECT_AUTHORIZING = "minecraft:connect.authorizing";

    /**
     * Connecting to the server...
     */
    String MC_CONNECT_CONNECTING = "minecraft:connect.connecting";

    /**
     * Failed to connect to the server
     */
    String MC_CONNECT_FAILED = "minecraft:connect.failed";

    /**
     * Please press the A button on your controller to continue
     */
    String MC_CONNECT_GAMEPAD_PRESSBUTTONTOCONTINUE = "minecraft:connect_gamepad.pressButtonToContinue";

    /**
     * Game controller required
     */
    String MC_CONNECT_GAMEPAD_WARNING_CONTROLLERREQUIRED = "minecraft:connect_gamepad.warning.controllerRequired";

    /**
     * Barrel
     */
    String MC_CONTAINER_BARREL = "minecraft:container.barrel";

    /**
     * Beacon
     */
    String MC_CONTAINER_BEACON = "minecraft:container.beacon";

    /**
     * Brewing Stand
     */
    String MC_CONTAINER_BREWING = "minecraft:container.brewing";

    /**
     * Chest
     */
    String MC_CONTAINER_CHEST = "minecraft:container.chest";

    /**
     * Copper Chest
     */
    String MC_CONTAINER_CHESTCOPPER = "minecraft:container.chestCopper";

    /**
     * Large Chest
     */
    String MC_CONTAINER_CHESTDOUBLE = "minecraft:container.chestDouble";

    /**
     * Large Copper Chest
     */
    String MC_CONTAINER_CHESTDOUBLECOPPER = "minecraft:container.chestDoubleCopper";

    /**
     * Crafter
     */
    String MC_CONTAINER_CRAFTER = "minecraft:container.crafter";

    /**
     * Crafting
     */
    String MC_CONTAINER_CRAFTING = "minecraft:container.crafting";

    /**
     * Item Selection
     */
    String MC_CONTAINER_CREATIVE = "minecraft:container.creative";

    /**
     * Dispenser
     */
    String MC_CONTAINER_DISPENSER = "minecraft:container.dispenser";

    /**
     * Dropper
     */
    String MC_CONTAINER_DROPPER = "minecraft:container.dropper";

    /**
     * Enchant
     */
    String MC_CONTAINER_ENCHANT = "minecraft:container.enchant";

    /**
     * %s . . . ?
     */
    String MC_CONTAINER_ENCHANT_CLUE = "minecraft:container.enchant.clue";

    /**
     * %d Lapis Lazuli
     */
    String MC_CONTAINER_ENCHANT_LAPIS_MANY = "minecraft:container.enchant.lapis.many";

    /**
     * 1 Lapis Lazuli
     */
    String MC_CONTAINER_ENCHANT_LAPIS_ONE = "minecraft:container.enchant.lapis.one";

    /**
     * %d Enchantment Levels
     */
    String MC_CONTAINER_ENCHANT_LEVEL_MANY = "minecraft:container.enchant.level.many";

    /**
     * 1 Enchantment Level
     */
    String MC_CONTAINER_ENCHANT_LEVEL_ONE = "minecraft:container.enchant.level.one";

    /**
     * Level Requirement: %d
     */
    String MC_CONTAINER_ENCHANT_LEVELREQUIREMENT = "minecraft:container.enchant.levelrequirement";

    /**
     * Ender Chest
     */
    String MC_CONTAINER_ENDERCHEST = "minecraft:container.enderchest";

    /**
     * Furnace
     */
    String MC_CONTAINER_FURNACE = "minecraft:container.furnace";

    /**
     * Item Hopper
     */
    String MC_CONTAINER_HOPPER = "minecraft:container.hopper";

    /**
     * Inventory
     */
    String MC_CONTAINER_INVENTORY = "minecraft:container.inventory";

    /**
     * %s is locked!
     */
    String MC_CONTAINER_ISLOCKED = "minecraft:container.isLocked";

    /**
     * Loom
     */
    String MC_CONTAINER_LOOM = "minecraft:container.loom";

    /**
     * Minecart
     */
    String MC_CONTAINER_MINECART = "minecraft:container.minecart";

    /**
     * Repair & Name
     */
    String MC_CONTAINER_REPAIR = "minecraft:container.repair";

    /**
     * Enchantment Cost: %1#d
     */
    String MC_CONTAINER_REPAIR_COST = "minecraft:container.repair.cost";

    /**
     * Too Expensive!
     */
    String MC_CONTAINER_REPAIR_EXPENSIVE = "minecraft:container.repair.expensive";

    /**
     * Repair & Disenchant
     */
    String MC_CONTAINER_REPAIRANDDISENCHANT = "minecraft:container.repairAndDisenchant";

    /**
     * Shulker Box
     */
    String MC_CONTAINER_SHULKERBOX = "minecraft:container.shulkerbox";

    /**
     * and %d more...
     */
    String MC_CONTAINER_SHULKERBOXCONTAINS = "minecraft:container.shulkerboxContains";

    /**
     * Smithing Table
     */
    String MC_CONTAINER_SMITHING_TABLE = "minecraft:container.smithing_table";

    /**
     * Item can't be upgraded this way
     */
    String MC_CONTAINER_SMITHING_TABLE_ARROW_TOOLTIP_CANNOT_CRAFT = "minecraft:container.smithing_table.arrow_tooltip_cannot_craft";

    /**
     * Add a piece of armor
     */
    String MC_CONTAINER_SMITHING_TABLE_INPUT_SLOT_TOOLTIP_ARMOR_TRIM = "minecraft:container.smithing_table.input_slot_tooltip_armor_trim";

    /**
     * Add Diamond armor, weapon, or tool
     */
    String MC_CONTAINER_SMITHING_TABLE_INPUT_SLOT_TOOLTIP_NETHERITE = "minecraft:container.smithing_table.input_slot_tooltip_netherite";

    /**
     * Add ingot or crystal
     */
    String MC_CONTAINER_SMITHING_TABLE_MATERIAL_SLOT_TOOLTIP_ARMOR_TRIM = "minecraft:container.smithing_table.material_slot_tooltip_armor_trim";

    /**
     * Add Netherite Ingot
     */
    String MC_CONTAINER_SMITHING_TABLE_MATERIAL_SLOT_TOOLTIP_NETHERITE = "minecraft:container.smithing_table.material_slot_tooltip_netherite";

    /**
     * Add Smithing Template
     */
    String MC_CONTAINER_SMITHING_TABLE_TEMPLATE_SLOT_TOOLTIP = "minecraft:container.smithing_table.template_slot_tooltip";

    /**
     * Upgrade Gear
     */
    String MC_CONTAINER_SMITHING_TABLE_UPGRADE = "minecraft:container.smithing_table.upgrade";

    /**
     * Smithing Table
     */
    String MC_CONTAINER_SMITHING_TABLE_BLOCK = "minecraft:container.smithing_table_block";

    /**
     * Stonecutter
     */
    String MC_CONTAINER_STONECUTTER = "minecraft:container.stonecutter";

    /**
     * Stonecutter
     */
    String MC_CONTAINER_STONECUTTER_BLOCK = "minecraft:container.stonecutter_block";

    /**
     * Failed to import '%s'
     */
    String MC_CONTENT_IMPORT_FAILED = "minecraft:content.import.failed";

    /**
     * Unsupported file format
     */
    String MC_CONTENT_IMPORT_FAILED_INCOMPATIBLE = "minecraft:content.import.failed.incompatible";

    /**
     * See output log for more details
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE = "minecraft:content.import.failed.subtitle";

    /**
     * Duplicate pack detected
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE_DUPLICATE = "minecraft:content.import.failed.subtitle_duplicate";

    /**
     * Not a valid zip archive
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE_MALFORMED_ZIP = "minecraft:content.import.failed.subtitle_malformed_zip";

    /**
     * Content in this world is not supported by Minecraft Education.
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE_PREMIUMCONTENT = "minecraft:content.import.failed.subtitle_premiumcontent";

    /**
     * Content Not Supported
     */
    String MC_CONTENT_IMPORT_FAILED_TITLE_PREMIUMCONTENT = "minecraft:content.import.failed.title_premiumcontent";

    /**
     * imported
     */
    String MC_CONTENT_IMPORT_IMPORTEDWORLDPREFIX = "minecraft:content.import.importedWorldPrefix";

    /**
     * Import started...
     */
    String MC_CONTENT_IMPORT_STARTED = "minecraft:content.import.started";

    /**
     * Successfully imported '%s'
     */
    String MC_CONTENT_IMPORT_SUCCEEDED = "minecraft:content.import.succeeded";

    /**
     * Successfully imported '%s' with warnings
     */
    String MC_CONTENT_IMPORT_SUCCEEDED_WITH_WARNINGS = "minecraft:content.import.succeeded_with_warnings";

    /**
     * Click here for more info
     */
    String MC_CONTENT_IMPORT_SUCCEEDED_WITH_WARNINGS_SUBTITLE = "minecraft:content.import.succeeded_with_warnings.subtitle";

    /**
     * Failed to load content for pack %s  
     */
    String MC_CONTENT_LOAD_FAILED = "minecraft:content.load.failed";

    /**
     * Check your pack for errors and try importing again 
     */
    String MC_CONTENT_LOAD_FAILED_SUBTITLE = "minecraft:content.load.failed.subtitle";

    /**
     * Delete Old Logs
     */
    String MC_CONTENTLOG_CLEAR_FILES = "minecraft:contentlog.clear_files";

    /**
     * Select "Delete Now" to delete all previous log files.
     */
    String MC_CONTENTLOG_DELETE_BODY1 = "minecraft:contentlog.delete.body1";

    /**
     * Note: The log file for your currently active session will remain.
     */
    String MC_CONTENTLOG_DELETE_BODY2 = "minecraft:contentlog.delete.body2";

    /**
     * Cancel
     */
    String MC_CONTENTLOG_DELETE_CANCEL = "minecraft:contentlog.delete.cancel";

    /**
     * Delete Now
     */
    String MC_CONTENTLOG_DELETE_DELETE = "minecraft:contentlog.delete.delete";

    /**
     * Deleting Content Logs
     */
    String MC_CONTENTLOG_DELETE_PROGRESS = "minecraft:contentlog.delete.progress";

    /**
     * Delete Old Log Files?
     */
    String MC_CONTENTLOG_DELETE_TITLE = "minecraft:contentlog.delete.title";

    /**
     * Clear
     */
    String MC_CONTENTLOG_HISTORY_CLEAR = "minecraft:contentlog.history.clear";

    /**
     * Copy to Clipboard
     */
    String MC_CONTENTLOG_HISTORY_COPYTOCLIPBOARD = "minecraft:contentlog.history.copyToClipboard";

    /**
     * Content Log History
     */
    String MC_CONTENTLOG_HISTORY_TITLE = "minecraft:contentlog.history.title";

    /**
     * Content Log Location:
     */
    String MC_CONTENTLOG_LOGLOCATIONSECTIONNAME = "minecraft:contentlog.logLocationSectionName";

    /**
     * Content Log Settings
     */
    String MC_CONTENTLOG_SETTINGSSECTIONNAME = "minecraft:contentlog.settingsSectionName";

    /**
     * Reset
     */
    String MC_CONTROLS_RESET = "minecraft:controls.reset";

    /**
     * Reset Keys
     */
    String MC_CONTROLS_RESETALL = "minecraft:controls.resetAll";

    /**
     * Controls
     */
    String MC_CONTROLS_TITLE = "minecraft:controls.title";

    /**
     * Copied facing position to clipboard: "%s"
     */
    String MC_COPYCOORDINATES_COPY_FACING_MESSAGE = "minecraft:copyCoordinates.copy_facing_message";

    /**
     * Copied current position to clipboard: "%s"
     */
    String MC_COPYCOORDINATES_COPY_POSITION_MESSAGE = "minecraft:copyCoordinates.copy_position_message";

    /**
     * No valid output for that item
     */
    String MC_CRAFTING_BADCOMBINATION = "minecraft:crafting.badCombination";

    /**
     * You don't have all ingredients
     */
    String MC_CRAFTING_CANNOTCREATE = "minecraft:crafting.cannotCreate";

    /**
     * Your level is too low
     */
    String MC_CRAFTING_INSUFFICIENTLEVEL = "minecraft:crafting.insufficientLevel";

    /**
     * You need to collect blocks to craft!
     */
    String MC_CRAFTING_NORECIPESINVENTORY = "minecraft:crafting.noRecipesInventory";

    /**
     * You need stone materials to craft!
     */
    String MC_CRAFTING_NORECIPESSTONECUTTER = "minecraft:crafting.noRecipesStonecutter";

    /**
     * You need stone materials to craft!
     */
    String MC_CRAFTING_NORECIPESSTONECUTTER_BLOCK = "minecraft:crafting.noRecipesStonecutter_block";

    /**
     * You need to collect blocks to craft!
     */
    String MC_CRAFTING_NORECIPESWORKBENCH = "minecraft:crafting.noRecipesWorkbench";

    /**
     * Armor
     */
    String MC_CRAFTINGSCREEN_TAB_ARMOR = "minecraft:craftingScreen.tab.armor";

    /**
     * Construction
     */
    String MC_CRAFTINGSCREEN_TAB_CONSTRUCTION = "minecraft:craftingScreen.tab.construction";

    /**
     * Equipment
     */
    String MC_CRAFTINGSCREEN_TAB_EQUIPMENT = "minecraft:craftingScreen.tab.equipment";

    /**
     * Items
     */
    String MC_CRAFTINGSCREEN_TAB_ITEMS = "minecraft:craftingScreen.tab.items";

    /**
     * Nature
     */
    String MC_CRAFTINGSCREEN_TAB_NATURE = "minecraft:craftingScreen.tab.nature";

    /**
     * All
     */
    String MC_CRAFTINGSCREEN_TAB_SEARCH = "minecraft:craftingScreen.tab.search";

    /**
     * Craftable
     */
    String MC_CRAFTINGSCREEN_TAB_SEARCH_FILTER = "minecraft:craftingScreen.tab.search.filter";

    /**
     * Inventory
     */
    String MC_CRAFTINGSCREEN_TAB_SURVIVAL = "minecraft:craftingScreen.tab.survival";

    /**
     * Imported Templates
     */
    String MC_CUSTOMTEMPLATESSCREEN_HEADER = "minecraft:customTemplatesScreen.header";

    /**
     * %s day
     */
    String MC_DATE_DAYSINGULAR = "minecraft:date.daySingular";

    /**
     * %sd
     */
    String MC_DATE_DAYSABBREVIATED1CHAR = "minecraft:date.daysAbbreviated1Char";

    /**
     * %sd
     */
    String MC_DATE_DAYSABBREVIATED3CHAR = "minecraft:date.daysAbbreviated3Char";

    /**
     * %s days
     */
    String MC_DATE_DAYSPLURAL = "minecraft:date.daysPlural";

    /**
     * %s %d %d
     */
    String MC_DATE_FORMATTED = "minecraft:date.formatted";

    /**
     * %s at %s
     */
    String MC_DATE_FORMATTEDSPECIFIC = "minecraft:date.formattedSpecific";

    /**
     * Today at %s
     */
    String MC_DATE_FORMATTEDSPECIFIC_TODAY = "minecraft:date.formattedSpecific.today";

    /**
     * Yesterday at %s
     */
    String MC_DATE_FORMATTEDSPECIFIC_YESTERDAY = "minecraft:date.formattedSpecific.yesterday";

    /**
     * %1#s:%2#sam
     */
    String MC_DATE_FORMATTEDTIMEAM = "minecraft:date.formattedTimeAM";

    /**
     * %1#s:%2#spm
     */
    String MC_DATE_FORMATTEDTIMEPM = "minecraft:date.formattedTimePm";

    /**
     * %d %s %d
     */
    String MC_DATE_FORMATTED_ALT = "minecraft:date.formatted_alt";

    /**
     * April
     */
    String MC_DATE_FULL_APRIL = "minecraft:date.full_april";

    /**
     * August
     */
    String MC_DATE_FULL_AUGUST = "minecraft:date.full_august";

    /**
     * December
     */
    String MC_DATE_FULL_DECEMBER = "minecraft:date.full_december";

    /**
     * February
     */
    String MC_DATE_FULL_FEBRUARY = "minecraft:date.full_february";

    /**
     * January
     */
    String MC_DATE_FULL_JANUARY = "minecraft:date.full_january";

    /**
     * July
     */
    String MC_DATE_FULL_JULY = "minecraft:date.full_july";

    /**
     * June
     */
    String MC_DATE_FULL_JUNE = "minecraft:date.full_june";

    /**
     * March
     */
    String MC_DATE_FULL_MARCH = "minecraft:date.full_march";

    /**
     * May
     */
    String MC_DATE_FULL_MAY = "minecraft:date.full_may";

    /**
     * November
     */
    String MC_DATE_FULL_NOVEMBER = "minecraft:date.full_november";

    /**
     * October
     */
    String MC_DATE_FULL_OCTOBER = "minecraft:date.full_october";

    /**
     * September
     */
    String MC_DATE_FULL_SEPTEMBER = "minecraft:date.full_september";

    /**
     * %s hour
     */
    String MC_DATE_HOURSINGULAR = "minecraft:date.hourSingular";

    /**
     * %sh
     */
    String MC_DATE_HOURSABBREVIATED1CHAR = "minecraft:date.hoursAbbreviated1Char";

    /**
     * %shr
     */
    String MC_DATE_HOURSABBREVIATED3CHAR = "minecraft:date.hoursAbbreviated3Char";

    /**
     * %s hours
     */
    String MC_DATE_HOURSPLURAL = "minecraft:date.hoursPlural";

    /**
     * %s minute
     */
    String MC_DATE_MINUTESINGULAR = "minecraft:date.minuteSingular";

    /**
     * %sm
     */
    String MC_DATE_MINUTESABBREVIATED1CHAR = "minecraft:date.minutesAbbreviated1Char";

    /**
     * %smin
     */
    String MC_DATE_MINUTESABBREVIATED3CHAR = "minecraft:date.minutesAbbreviated3Char";

    /**
     * %s minutes
     */
    String MC_DATE_MINUTESPLURAL = "minecraft:date.minutesPlural";

    /**
     * %s second
     */
    String MC_DATE_SECONDSINGULAR = "minecraft:date.secondSingular";

    /**
     * %ss
     */
    String MC_DATE_SECONDSABBREVIATED1CHAR = "minecraft:date.secondsAbbreviated1Char";

    /**
     * %ssec
     */
    String MC_DATE_SECONDSABBREVIATED3CHAR = "minecraft:date.secondsAbbreviated3Char";

    /**
     * %s seconds
     */
    String MC_DATE_SECONDSPLURAL = "minecraft:date.secondsPlural";

    /**
     * Apr
     */
    String MC_DATE_SHORT_APRIL = "minecraft:date.short_april";

    /**
     * Aug
     */
    String MC_DATE_SHORT_AUGUST = "minecraft:date.short_august";

    /**
     * Dec
     */
    String MC_DATE_SHORT_DECEMBER = "minecraft:date.short_december";

    /**
     * Feb
     */
    String MC_DATE_SHORT_FEBRUARY = "minecraft:date.short_february";

    /**
     * Jan
     */
    String MC_DATE_SHORT_JANUARY = "minecraft:date.short_january";

    /**
     * Jul
     */
    String MC_DATE_SHORT_JULY = "minecraft:date.short_july";

    /**
     * Jun
     */
    String MC_DATE_SHORT_JUNE = "minecraft:date.short_june";

    /**
     * Mar
     */
    String MC_DATE_SHORT_MARCH = "minecraft:date.short_march";

    /**
     * May
     */
    String MC_DATE_SHORT_MAY = "minecraft:date.short_may";

    /**
     * Nov
     */
    String MC_DATE_SHORT_NOVEMBER = "minecraft:date.short_november";

    /**
     * Oct
     */
    String MC_DATE_SHORT_OCTOBER = "minecraft:date.short_october";

    /**
     * Sep
     */
    String MC_DATE_SHORT_SEPTEMBER = "minecraft:date.short_september";

    /**
     * %s left
     */
    String MC_DATE_TIMELEFT = "minecraft:date.timeLeft";

    /**
     * UTC
     */
    String MC_DATE_TIME_ZONE_UTC = "minecraft:date.time_zone_utc";

    /**
     * %1#s was squashed by a falling anvil
     */
    String MC_DEATH_ATTACK_ANVIL = "minecraft:death.attack.anvil";

    /**
     * %1#s was shot by %2#s
     */
    String MC_DEATH_ATTACK_ARROW = "minecraft:death.attack.arrow";

    /**
     * %1#s was shot by %2#s using %3#s
     */
    String MC_DEATH_ATTACK_ARROW_ITEM = "minecraft:death.attack.arrow.item";

    /**
     * %1#s was sniped by %2#s
     */
    String MC_DEATH_ATTACK_BULLET = "minecraft:death.attack.bullet";

    /**
     * %1#s was pricked to death
     */
    String MC_DEATH_ATTACK_CACTUS = "minecraft:death.attack.cactus";

    /**
     * %1#s walked into a cactus whilst trying to escape %2#s
     */
    String MC_DEATH_ATTACK_CACTUS_PLAYER = "minecraft:death.attack.cactus.player";

    /**
     * %1#s drowned
     */
    String MC_DEATH_ATTACK_DROWN = "minecraft:death.attack.drown";

    /**
     * %1#s drowned whilst trying to escape %2#s
     */
    String MC_DEATH_ATTACK_DROWN_PLAYER = "minecraft:death.attack.drown.player";

    /**
     * %1#s blew up
     */
    String MC_DEATH_ATTACK_EXPLOSION = "minecraft:death.attack.explosion";

    /**
     * %1#s was killed by [Intentional Game Design]
     */
    String MC_DEATH_ATTACK_EXPLOSION_BY_BED = "minecraft:death.attack.explosion.by.bed";

    /**
     * %1#s was blown up by %2#s
     */
    String MC_DEATH_ATTACK_EXPLOSION_PLAYER = "minecraft:death.attack.explosion.player";

    /**
     * %1#s hit the ground too hard
     */
    String MC_DEATH_ATTACK_FALL = "minecraft:death.attack.fall";

    /**
     * %1#s was squashed by a falling block
     */
    String MC_DEATH_ATTACK_FALLINGBLOCK = "minecraft:death.attack.fallingBlock";

    /**
     * %1#s was fireballed by %2#s
     */
    String MC_DEATH_ATTACK_FIREBALL = "minecraft:death.attack.fireball";

    /**
     * %1#s was fireballed by %2#s using %3#s
     */
    String MC_DEATH_ATTACK_FIREBALL_ITEM = "minecraft:death.attack.fireball.item";

    /**
     * %1#s went off with a bang
     */
    String MC_DEATH_ATTACK_FIREWORKS = "minecraft:death.attack.fireworks";

    /**
     * %1#s experienced kinetic energy
     */
    String MC_DEATH_ATTACK_FLYINTOWALL = "minecraft:death.attack.flyIntoWall";

    /**
     * %1#s froze to death
     */
    String MC_DEATH_ATTACK_FREEZE = "minecraft:death.attack.freeze";

    /**
     * %1#s died
     */
    String MC_DEATH_ATTACK_GENERIC = "minecraft:death.attack.generic";

    /**
     * %1#s went up in flames
     */
    String MC_DEATH_ATTACK_INFIRE = "minecraft:death.attack.inFire";

    /**
     * %1#s walked into fire whilst fighting %2#s
     */
    String MC_DEATH_ATTACK_INFIRE_PLAYER = "minecraft:death.attack.inFire.player";

    /**
     * %1#s suffocated in a wall
     */
    String MC_DEATH_ATTACK_INWALL = "minecraft:death.attack.inWall";

    /**
     * %1#s was killed by %2#s using magic
     */
    String MC_DEATH_ATTACK_INDIRECTMAGIC = "minecraft:death.attack.indirectMagic";

    /**
     * %1#s was killed by %2#s using %3#s
     */
    String MC_DEATH_ATTACK_INDIRECTMAGIC_ITEM = "minecraft:death.attack.indirectMagic.item";

    /**
     * %1#s tried to swim in lava
     */
    String MC_DEATH_ATTACK_LAVA = "minecraft:death.attack.lava";

    /**
     * %1#s tried to swim in lava to escape %2#s
     */
    String MC_DEATH_ATTACK_LAVA_PLAYER = "minecraft:death.attack.lava.player";

    /**
     * %1#s was struck by lightning
     */
    String MC_DEATH_ATTACK_LIGHTNINGBOLT = "minecraft:death.attack.lightningBolt";

    /**
     * %1#s was smashed by %2#s
     */
    String MC_DEATH_ATTACK_MACESMASH_PLAYER = "minecraft:death.attack.maceSmash.player";

    /**
     * %1#s was smashed by %2#s with %3#s
     */
    String MC_DEATH_ATTACK_MACESMASH_PLAYER_ITEM = "minecraft:death.attack.maceSmash.player.item";

    /**
     * %1#s was killed by magic
     */
    String MC_DEATH_ATTACK_MAGIC = "minecraft:death.attack.magic";

    /**
     * %1#s discovered the floor was lava
     */
    String MC_DEATH_ATTACK_MAGMA = "minecraft:death.attack.magma";

    /**
     * %1#s walked on danger zone due to %2#s
     */
    String MC_DEATH_ATTACK_MAGMA_PLAYER = "minecraft:death.attack.magma.player";

    /**
     * %1#s was slain by %2#s
     */
    String MC_DEATH_ATTACK_MOB = "minecraft:death.attack.mob";

    /**
     * %1#s was slain by %2#s using %3#s
     */
    String MC_DEATH_ATTACK_MOB_ITEM = "minecraft:death.attack.mob.item";

    /**
     * %1#s burned to death
     */
    String MC_DEATH_ATTACK_ONFIRE = "minecraft:death.attack.onFire";

    /**
     * %1#s was burnt to a crisp whilst fighting %2#s
     */
    String MC_DEATH_ATTACK_ONFIRE_PLAYER = "minecraft:death.attack.onFire.player";

    /**
     * %1#s fell out of the world
     */
    String MC_DEATH_ATTACK_OUTOFWORLD = "minecraft:death.attack.outOfWorld";

    /**
     * %1#s was slain by %2#s
     */
    String MC_DEATH_ATTACK_PLAYER = "minecraft:death.attack.player";

    /**
     * %1#s was slain by %2#s using %3#s
     */
    String MC_DEATH_ATTACK_PLAYER_ITEM = "minecraft:death.attack.player.item";

    /**
     * %1#s was obliterated by a sonically-charged shriek
     */
    String MC_DEATH_ATTACK_SONICBOOM = "minecraft:death.attack.sonicBoom";

    /**
     * %1#s was obliterated by a sonically-charged shriek whilst trying to escape %2#s
     */
    String MC_DEATH_ATTACK_SONICBOOM_PLAYER = "minecraft:death.attack.sonicBoom.player";

    /**
     * %1#s was spitballed by %2#s
     */
    String MC_DEATH_ATTACK_SPIT = "minecraft:death.attack.spit";

    /**
     * %1#s was skewered by a falling stalactite
     */
    String MC_DEATH_ATTACK_STALACTITE = "minecraft:death.attack.stalactite";

    /**
     * %1#s was impaled on a stalagmite
     */
    String MC_DEATH_ATTACK_STALAGMITE = "minecraft:death.attack.stalagmite";

    /**
     * %1#s starved to death
     */
    String MC_DEATH_ATTACK_STARVE = "minecraft:death.attack.starve";

    /**
     * %1#s was poked to death by a sweet berry bush
     */
    String MC_DEATH_ATTACK_SWEETBERRY = "minecraft:death.attack.sweetBerry";

    /**
     * %1#s was killed trying to hurt %2#s
     */
    String MC_DEATH_ATTACK_THORNS = "minecraft:death.attack.thorns";

    /**
     * %1#s was pummeled by %2#s
     */
    String MC_DEATH_ATTACK_THROWN = "minecraft:death.attack.thrown";

    /**
     * %1#s was pummeled by %2#s using %3#s
     */
    String MC_DEATH_ATTACK_THROWN_ITEM = "minecraft:death.attack.thrown.item";

    /**
     * %1#s was impaled to death by %2#s
     */
    String MC_DEATH_ATTACK_TRIDENT = "minecraft:death.attack.trident";

    /**
     * %1#s withered away
     */
    String MC_DEATH_ATTACK_WITHER = "minecraft:death.attack.wither";

    /**
     * %1#s fell from a high place
     */
    String MC_DEATH_FELL_ACCIDENT_GENERIC = "minecraft:death.fell.accident.generic";

    /**
     * %1#s fell off a ladder
     */
    String MC_DEATH_FELL_ACCIDENT_LADDER = "minecraft:death.fell.accident.ladder";

    /**
     * %1#s fell off some vines
     */
    String MC_DEATH_FELL_ACCIDENT_VINES = "minecraft:death.fell.accident.vines";

    /**
     * %1#s fell out of the water
     */
    String MC_DEATH_FELL_ACCIDENT_WATER = "minecraft:death.fell.accident.water";

    /**
     * %1#s was doomed to fall by %2#s
     */
    String MC_DEATH_FELL_ASSIST = "minecraft:death.fell.assist";

    /**
     * %1#s was doomed to fall by %2#s using %3#s
     */
    String MC_DEATH_FELL_ASSIST_ITEM = "minecraft:death.fell.assist.item";

    /**
     * %1#s fell too far and was finished by %2#s
     */
    String MC_DEATH_FELL_FINISH = "minecraft:death.fell.finish";

    /**
     * %1#s fell too far and was finished by %2#s using %3#s
     */
    String MC_DEATH_FELL_FINISH_ITEM = "minecraft:death.fell.finish.item";

    /**
     * %1#s was doomed to fall
     */
    String MC_DEATH_FELL_KILLER = "minecraft:death.fell.killer";

    /**
     * Delete world
     */
    String MC_DEATHSCREEN_DELETEWORLD = "minecraft:deathScreen.deleteWorld";

    /**
     * You cannot respawn in hardcore mode!
     */
    String MC_DEATHSCREEN_HARDCOREINFO = "minecraft:deathScreen.hardcoreInfo";

    /**
     * Leave server
     */
    String MC_DEATHSCREEN_LEAVESERVER = "minecraft:deathScreen.leaveServer";

    /**
     * You died!
     */
    String MC_DEATHSCREEN_MESSAGE = "minecraft:deathScreen.message";

    /**
     * Main menu
     */
    String MC_DEATHSCREEN_QUIT = "minecraft:deathScreen.quit";

    /**
     * Are you sure you want to quit?
     */
    String MC_DEATHSCREEN_QUIT_CONFIRM = "minecraft:deathScreen.quit.confirm";

    /**
     * Quit to Main Menu?
     */
    String MC_DEATHSCREEN_QUIT_CONFIRMTOMAINMENUTITLEWARNING = "minecraft:deathScreen.quit.confirmToMainMenuTitleWarning";

    /**
     * Are you sure you want to exit the game to the main menu?
     */
    String MC_DEATHSCREEN_QUIT_CONFIRMTOMAINMENUWARNING = "minecraft:deathScreen.quit.confirmToMainMenuWarning";

    /**
     * Save and Leave
     */
    String MC_DEATHSCREEN_QUIT_SECONDARYCLIENT = "minecraft:deathScreen.quit.secondaryClient";

    /**
     * Save and Leave
     */
    String MC_DEATHSCREEN_QUIT_SECONDARYCLIENT_CONFIRMLEAVETITLEWARNING = "minecraft:deathScreen.quit.secondaryClient.confirmLeaveTitleWarning";

    /**
     * Are you sure you want to save and leave the game?
     */
    String MC_DEATHSCREEN_QUIT_SECONDARYCLIENT_CONFIRMLEAVEWARNING = "minecraft:deathScreen.quit.secondaryClient.confirmLeaveWarning";

    /**
     * Respawn
     */
    String MC_DEATHSCREEN_RESPAWN = "minecraft:deathScreen.respawn";

    /**
     * Score
     */
    String MC_DEATHSCREEN_SCORE = "minecraft:deathScreen.score";

    /**
     * You died!
     */
    String MC_DEATHSCREEN_TITLE = "minecraft:deathScreen.title";

    /**
     * Game over!
     */
    String MC_DEATHSCREEN_TITLE_HARDCORE = "minecraft:deathScreen.title.hardcore";

    /**
     * Title screen
     */
    String MC_DEATHSCREEN_TITLESCREEN = "minecraft:deathScreen.titleScreen";

    /**
     * Are you sure you want to lock the difficulty of this world? This will set this world to always be %1#s, and you will never be able to change that again.
     */
    String MC_DIFFICULTY_LOCK_QUESTION = "minecraft:difficulty.lock.question";

    /**
     * Lock World Difficulty
     */
    String MC_DIFFICULTY_LOCK_TITLE = "minecraft:difficulty.lock.title";

    /**
     * Overworld
     */
    String MC_DIMENSION_DIMENSIONNAME0 = "minecraft:dimension.dimensionName0";

    /**
     * Nether
     */
    String MC_DIMENSION_DIMENSIONNAME1 = "minecraft:dimension.dimensionName1";

    /**
     * The End
     */
    String MC_DIMENSION_DIMENSIONNAME2 = "minecraft:dimension.dimensionName2";

    /**
     * This server has %s disabled. Are you sure want to keep this skin equipped?
     */
    String MC_DISABLEDSKIN_BODY_DRESSINGROOM = "minecraft:disabledSkin.body.dressingRoom";

    /**
     * This server has %s disabled. You can switch to a different skin in the dressing room.
     */
    String MC_DISABLEDSKIN_BODY_ONJOIN = "minecraft:disabledSkin.body.onJoin";

    /**
     * You are being displayed as a default character
     */
    String MC_DISABLEDSKIN_TITLE = "minecraft:disabledSkin.title";

    /**
     * custom skins and personas
     */
    String MC_DISABLEDSKIN_TYPE_BOTH = "minecraft:disabledSkin.type.both";

    /**
     * custom skins
     */
    String MC_DISABLEDSKIN_TYPE_CUSTOMSKINS = "minecraft:disabledSkin.type.customSkins";

    /**
     * personas
     */
    String MC_DISABLEDSKIN_TYPE_PERSONA = "minecraft:disabledSkin.type.persona";

    /**
     * Connection closed
     */
    String MC_DISCONNECT_CLOSED = "minecraft:disconnect.closed";

    /**
     * Disconnected by Server
     */
    String MC_DISCONNECT_DISCONNECTED = "minecraft:disconnect.disconnected";

    /**
     * Error while downloading packs from server. Please try again.
     */
    String MC_DISCONNECT_DOWNLOADPACK = "minecraft:disconnect.downloadPack";

    /**
     * End of stream
     */
    String MC_DISCONNECT_ENDOFSTREAM = "minecraft:disconnect.endOfStream";

    /**
     * %s
     */
    String MC_DISCONNECT_GENERICREASON = "minecraft:disconnect.genericReason";

    /**
     * You were kicked from the game
     */
    String MC_DISCONNECT_KICKED = "minecraft:disconnect.kicked";

    /**
     * You were kicked from the game:
     */
    String MC_DISCONNECT_KICKED_REASON = "minecraft:disconnect.kicked.reason";

    /**
     * Failed to login
     */
    String MC_DISCONNECT_LOGINFAILED = "minecraft:disconnect.loginFailed";

    /**
     * Failed to login: %s
     */
    String MC_DISCONNECT_LOGINFAILEDINFO = "minecraft:disconnect.loginFailedInfo";

    /**
     * Playfab environment mismatch - Discovery=%s/%s, Playfab=%s/%s
     */
    String MC_DISCONNECT_LOGINFAILEDINFO_ENVIRONMENTMISMATCH = "minecraft:disconnect.loginFailedInfo.environmentMismatch";

    /**
     * Invalid session (Try restarting your game)
     */
    String MC_DISCONNECT_LOGINFAILEDINFO_INVALIDSESSION = "minecraft:disconnect.loginFailedInfo.invalidSession";

    /**
     * The authentication servers are currently down for maintenance.
     */
    String MC_DISCONNECT_LOGINFAILEDINFO_SERVERSUNAVAILABLE = "minecraft:disconnect.loginFailedInfo.serversUnavailable";

    /**
     * Connection Lost
     */
    String MC_DISCONNECT_LOST = "minecraft:disconnect.lost";

    /**
     * Buffer overflow
     */
    String MC_DISCONNECT_OVERFLOW = "minecraft:disconnect.overflow";

    /**
     * Quitting
     */
    String MC_DISCONNECT_QUITTING = "minecraft:disconnect.quitting";

    /**
     * You were removed from the game
     */
    String MC_DISCONNECT_REMOVED = "minecraft:disconnect.removed";

    /**
     * You were removed from the game:
     */
    String MC_DISCONNECT_REMOVED_REASON = "minecraft:disconnect.removed.reason";

    /**
     * The server was shut down due to an unhandled scripting watchdog exception.
     */
    String MC_DISCONNECT_SCRIPTWATCHDOG = "minecraft:disconnect.scriptWatchdog";

    /**
     * The server was shut down due to exceeding the scripting memory limit.
     */
    String MC_DISCONNECT_SCRIPTWATCHDOGOUTOFMEMORY = "minecraft:disconnect.scriptWatchdogOutOfMemory";

    /**
     * Kicked for spamming
     */
    String MC_DISCONNECT_SPAM = "minecraft:disconnect.spam";

    /**
     * Timed out
     */
    String MC_DISCONNECT_TIMEOUT = "minecraft:disconnect.timeout";

    /**
     * Server sent broken packet.
     */
    String MC_DISCONNECTIONSCREEN_BADPACKET = "minecraft:disconnectionScreen.badPacket";

    /**
     * %s ID collides with %s
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_IDDUPLICATION = "minecraft:disconnectionScreen.biomeData.IdDuplication";

    /**
     * Malformed Biome Data
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_MALFORMEDDATA = "minecraft:disconnectionScreen.biomeData.MalformedData";

    /**
     * Biome Packet has mixed id presence for Custom Biomes
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_MIXEDIDPRESENCE = "minecraft:disconnectionScreen.biomeData.MixedIdPresence";

    /**
     * Custom Biome %s uses ID from a reserved range. ID range for Custom Biomes is: %s-%s
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_RESERVEDRANGE = "minecraft:disconnectionScreen.biomeData.ReservedRange";

    /**
     * Vanilla Biome data should not contain 'id' field
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_VANILLABIOMESWITHID = "minecraft:disconnectionScreen.biomeData.VanillaBiomesWithId";

    /**
     * Blocks between client and server do not match.
     */
    String MC_DISCONNECTIONSCREEN_BLOCKMISMATCH = "minecraft:disconnectionScreen.blockMismatch";

    /**
     * We are unable to connect you to this server.
     */
    String MC_DISCONNECTIONSCREEN_BODY_CANTCONNECTSERVER = "minecraft:disconnectionScreen.body.cantConnectServer";

    /**
     * Unable to find Client.
     */
    String MC_DISCONNECTIONSCREEN_BODY_CANTFINDCLIENT = "minecraft:disconnectionScreen.body.cantFindClient";

    /**
     * Server not found.
     */
    String MC_DISCONNECTIONSCREEN_BODY_CANTFINDSERVER = "minecraft:disconnectionScreen.body.cantFindServer";

    /**
     * Check your connection and try again
     */
    String MC_DISCONNECTIONSCREEN_BODY_CHECKCONNECTIONANDTRY = "minecraft:disconnectionScreen.body.checkConnectionAndTry";

    /**
     * Check the storage space on your device, delete items to free up more space.
     */
    String MC_DISCONNECTIONSCREEN_BODY_CHECKDEVICESTORAGE = "minecraft:disconnectionScreen.body.checkDeviceStorage";

    /**
     * Your discovery environment does not match the host's.
     */
    String MC_DISCONNECTIONSCREEN_BODY_DISCOVERYMISMATCH = "minecraft:disconnectionScreen.body.discoveryMismatch";

    /**
     * The game version you are using is out of date. Update your game to the latest version and try again.
     */
    String MC_DISCONNECTIONSCREEN_BODY_GAMEOUTOFDATE = "minecraft:disconnectionScreen.body.gameOutOfDate";

    /**
     * The host you are trying to join is using an older version of Minecraft. Try again when the host has updated its version.
     */
    String MC_DISCONNECTIONSCREEN_BODY_HOSTVERSIONOLDER = "minecraft:disconnectionScreen.body.hostVersionOlder";

    /**
     * The host you are trying to join is not prepared to accept authenticated connections. Try again in a few minutes.
     */
    String MC_DISCONNECTIONSCREEN_BODY_HOSTWITHOUTKEYS = "minecraft:disconnectionScreen.body.hostWithoutKeys";

    /**
     * You have been idle for too long.
     */
    String MC_DISCONNECTIONSCREEN_BODY_IDLETOOLONG = "minecraft:disconnectionScreen.body.idleTooLong";

    /**
     * You may be logged in at another location.
     */
    String MC_DISCONNECTIONSCREEN_BODY_LOGGEDINELSEWHERE = "minecraft:disconnectionScreen.body.loggedInElsewhere";

    /**
     * Your client is having trouble establishing a connection to multiplayer services.  Please check your internet connection, restart your client, and try again.
     */
    String MC_DISCONNECTIONSCREEN_BODY_MULTIPLAYERCONNECTION = "minecraft:disconnectionScreen.body.multiplayerConnection";

    /**
     * You can't join the server unless the host unblocks you.
     */
    String MC_DISCONNECTIONSCREEN_BODY_NEEDSHOSTUNBLOCK = "minecraft:disconnectionScreen.body.needsHostUnblock";

    /**
     * Permissions do not allow you to join.
     */
    String MC_DISCONNECTIONSCREEN_BODY_PERMISSIONSDONOTALLOW = "minecraft:disconnectionScreen.body.permissionsDoNotAllow";

    /**
     * Unable to join this specific server.
     */
    String MC_DISCONNECTIONSCREEN_BODY_REALMS_HIDDEN = "minecraft:disconnectionScreen.body.realms_hidden";

    /**
     * The server you are attempting to join may not exist or be locked.
     */
    String MC_DISCONNECTIONSCREEN_BODY_SERVERNONEORLOCKED = "minecraft:disconnectionScreen.body.serverNoneOrLocked";

    /**
     * Your skin appears to be banned.
     */
    String MC_DISCONNECTIONSCREEN_BODY_SKINBANNED = "minecraft:disconnectionScreen.body.skinBanned";

    /**
     * Your skin may be locked to a specific platform.
     */
    String MC_DISCONNECTIONSCREEN_BODY_SKINLOCKEDPLATFORM = "minecraft:disconnectionScreen.body.skinLockedPlatform";

    /**
     * You may be out of storage space on your device.
     */
    String MC_DISCONNECTIONSCREEN_BODY_STORAGEOUT = "minecraft:disconnectionScreen.body.storageOut";

    /**
     * Your version does not match the host's version.
     */
    String MC_DISCONNECTIONSCREEN_BODY_VERSIONMISMATCHHOST = "minecraft:disconnectionScreen.body.versionMismatchHost";

    /**
     * Your version is not supported.
     */
    String MC_DISCONNECTIONSCREEN_BODY_VERSIONNOTSUPPORTED = "minecraft:disconnectionScreen.body.versionNotSupported";

    /**
     * This world was made with a more recent version.
     */
    String MC_DISCONNECTIONSCREEN_BODY_WORLDNEWERVERSION = "minecraft:disconnectionScreen.body.worldNewerVersion";

    /**
     * The world you're connecting to is no longer active.
     */
    String MC_DISCONNECTIONSCREEN_BODY_WORLDNOLONGERACTIVE = "minecraft:disconnectionScreen.body.worldNoLongerActive";

    /**
     * This world is no longer available.
     */
    String MC_DISCONNECTIONSCREEN_BODY_WORLDNOLONGERAVAILABLE = "minecraft:disconnectionScreen.body.worldNoLongerAvailable";

    /**
     * Unable to connect to world.
     */
    String MC_DISCONNECTIONSCREEN_CANTCONNECT = "minecraft:disconnectionScreen.cantConnect";

    /**
     * Unable to connect to Realm.
     */
    String MC_DISCONNECTIONSCREEN_CANTCONNECTTOREALM = "minecraft:disconnectionScreen.cantConnectToRealm";

    /**
     * Unable to connect to Realms.
     */
    String MC_DISCONNECTIONSCREEN_CANTCONNECTTOREALMS = "minecraft:disconnectionScreen.cantConnectToRealms";

    /**
     * Server and Client have different values set for feature toggle 'Client Side Generation'
     */
    String MC_DISCONNECTIONSCREEN_CLIENTSIDEGENERATIONDESYNC = "minecraft:disconnectionScreen.clientsideGenerationDesync";

    /**
     * The connection to the host was lost. If that world is still being hosted, please check your internet connection and try to connect again.
     */
    String MC_DISCONNECTIONSCREEN_CONNECTIONLOST = "minecraft:disconnectionScreen.connectionLost";

    /**
     * Enable cross-platform play in the main menu to connect with friends on other platforms. To play with other friends online without cross-play, the host of the game must also have cross-play disabled via the main menu.
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLATFORMDISABLED = "minecraft:disconnectionScreen.crossPlatformDisabled";

    /**
     * Cross-Platform Play Disabled.
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLATFORMDISABLED_HEADER = "minecraft:disconnectionScreen.crossPlatformDisabled.header";

    /**
     * CrossPlay is temporarily disabled!
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLAYDISABLED = "minecraft:disconnectionScreen.crossPlayDisabled";

    /**
     * CrossPlay Disabled
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLAYDISABLED_TITLE = "minecraft:disconnectionScreen.crossPlayDisabled.title";

    /**
     * Try opening this world in demo mode.
     */
    String MC_DISCONNECTIONSCREEN_DEEPLINK_TRYINGTOOPENDEMOWORLDWHILESIGNEDIN_BODY = "minecraft:disconnectionScreen.deepLink.TryingToOpenDemoWorldWhileSignedIn.body";

    /**
     * Unable to load world.
     */
    String MC_DISCONNECTIONSCREEN_DEEPLINK_TRYINGTOOPENDEMOWORLDWHILESIGNEDIN_TITLE = "minecraft:disconnectionScreen.deepLink.TryingToOpenDemoWorldWhileSignedIn.title";

    /**
     * Disconnected from Server
     */
    String MC_DISCONNECTIONSCREEN_DISCONNECTED = "minecraft:disconnectionScreen.disconnected";

    /**
     * This world was saved from Minecraft Education. It cannot be loaded.
     */
    String MC_DISCONNECTIONSCREEN_EDITIONMISMATCH = "minecraft:disconnectionScreen.editionMismatch";

    /**
     * The server is not running Minecraft Education. Failed to connect.
     */
    String MC_DISCONNECTIONSCREEN_EDITIONMISMATCHEDUTOVANILLA = "minecraft:disconnectionScreen.editionMismatchEduToVanilla";

    /**
     * The server is running an incompatible edition of Minecraft. Failed to connect.
     */
    String MC_DISCONNECTIONSCREEN_EDITIONMISMATCHVANILLATOEDU = "minecraft:disconnectionScreen.editionMismatchVanillaToEdu";

    /**
     * The world you selected cannot be opened in this version of Minecraft Education.
     */
    String MC_DISCONNECTIONSCREEN_EDITIONVERSIONMISMATCH_BODY = "minecraft:disconnectionScreen.editionVersionMismatch.body";

    /**
     * Unable to load world.
     */
    String MC_DISCONNECTIONSCREEN_EDITIONVERSIONMISMATCH_TITLE = "minecraft:disconnectionScreen.editionVersionMismatch.title";

    /**
     * Ray Tracing enabled resource packs are not yet compatible with Editor projects.
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_CANNOTUSERAYTRACING = "minecraft:disconnectionScreen.editor.cannotUseRayTracing";

    /**
     * The server is not in Editor Mode. Failed to connect.
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHEDITORTOVANILLA = "minecraft:disconnectionScreen.editor.mismatchEditorToVanilla";

    /**
     * This world is in Editor Mode. It cannot be loaded.
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHEDITORWORLD = "minecraft:disconnectionScreen.editor.mismatchEditorWorld";

    /**
     * The server is in Editor Mode. Failed to connect.
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHVANILLATOEDITOR = "minecraft:disconnectionScreen.editor.mismatchVanillaToEditor";

    /**
     * This world is a not in Editor Mode. It cannot be loaded.
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHVANILLAWORLD = "minecraft:disconnectionScreen.editor.mismatchVanillaWorld";

    /**
     * Error Code: 
     */
    String MC_DISCONNECTIONSCREEN_ERRORCODE = "minecraft:disconnectionScreen.errorCode";

    /**
     * There was a problem loading into the server. 
     */
    String MC_DISCONNECTIONSCREEN_FAILEDASYNCCHECK = "minecraft:disconnectionScreen.failedAsyncCheck";

    /**
     * Realm not found, or you are not a member.
     */
    String MC_DISCONNECTIONSCREEN_FORBIDDEN = "minecraft:disconnectionScreen.forbidden";

    /**
     * A newer version of the game has saved this world. It cannot be loaded.
     */
    String MC_DISCONNECTIONSCREEN_FUTUREVERSION = "minecraft:disconnectionScreen.futureVersion";

    /**
     * Multiplayer Invitation
     */
    String MC_DISCONNECTIONSCREEN_HEADER_REALMS_HIDDEN = "minecraft:disconnectionScreen.header.realms_hidden";

    /**
     * Disconnected due to the server being unavailable
     */
    String MC_DISCONNECTIONSCREEN_HOSTSUSPENDED = "minecraft:disconnectionScreen.hostSuspended";

    /**
     * You are unable to join the world because you have the following incompatible packs: %s
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEPACK = "minecraft:disconnectionScreen.incompatiblePack";

    /**
     * You are unable to join the world because your device doesn't have compatible hardware to support ray tracing
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEPACK_GRAPHICS_RAYTRACING = "minecraft:disconnectionScreen.incompatiblePack.graphics.raytracing";

    /**
     * You are unable to join the world because your device doesn't have enough memory for the following packs: %s
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEPACK_MEMORY = "minecraft:disconnectionScreen.incompatiblePack.memory";

    /**
     * Your client is incompatible or out of date. Update your client to connect to Realms.
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEREALM = "minecraft:disconnectionScreen.incompatibleRealm";

    /**
     * We were unable to connect you.
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTCONNECT = "minecraft:disconnectionScreen.internalError.cantConnect";

    /**
     * There was a problem finding this server.
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTEDIT = "minecraft:disconnectionScreen.internalError.cantEdit";

    /**
     * We were unable to connect you. Try adding a new server.
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDEDIT = "minecraft:disconnectionScreen.internalError.cantFindEdit";

    /**
     * There was a problem loading this world.
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDLOCAL = "minecraft:disconnectionScreen.internalError.cantFindLocal";

    /**
     * There was a problem finding this Realm.
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDREALM = "minecraft:disconnectionScreen.internalError.cantFindRealm";

    /**
     * There was a problem finding this server.
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDSERVER = "minecraft:disconnectionScreen.internalError.cantFindServer";

    /**
     * Invalid IP address!
     */
    String MC_DISCONNECTIONSCREEN_INVALIDIP = "minecraft:disconnectionScreen.invalidIP";

    /**
     * Invalid Level!
     */
    String MC_DISCONNECTIONSCREEN_INVALIDLEVEL = "minecraft:disconnectionScreen.invalidLevel";

    /**
     * Invalid name!
     */
    String MC_DISCONNECTIONSCREEN_INVALIDNAME = "minecraft:disconnectionScreen.invalidName";

    /**
     * The server provided invalid height values for the overworld dimension
     */
    String MC_DISCONNECTIONSCREEN_INVALIDOVERWORLDDIMENSIONHEIGHTS = "minecraft:disconnectionScreen.invalidOverworldDimensionHeights";

    /**
     * This world's multiplayer setting is set to friends only. You must be friends with the host of this world to join.
     */
    String MC_DISCONNECTIONSCREEN_INVALIDPLAYER = "minecraft:disconnectionScreen.invalidPlayer";

    /**
     * Invalid or corrupt skin!
     */
    String MC_DISCONNECTIONSCREEN_INVALIDSKIN = "minecraft:disconnectionScreen.invalidSkin";

    /**
     * Unable to connect to the world. Please check your join code and try again.
     */
    String MC_DISCONNECTIONSCREEN_INVALIDTENANT = "minecraft:disconnectionScreen.invalidTenant";

    /**
     * The Skin that you have equipped is from a content pack that is not allowed in cross-platform multiplayer games.
     */
    String MC_DISCONNECTIONSCREEN_LOCKEDSKIN = "minecraft:disconnectionScreen.lockedSkin";

    /**
     * Platform Restricted Skin!
     */
    String MC_DISCONNECTIONSCREEN_LOCKEDSKIN_TITLE = "minecraft:disconnectionScreen.lockedSkin.title";

    /**
     * Logged in from other location
     */
    String MC_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION = "minecraft:disconnectionScreen.loggedinOtherLocation";

    /**
     * The world has been set to single player mode.
     */
    String MC_DISCONNECTIONSCREEN_MULTIPLAYERDISABLED = "minecraft:disconnectionScreen.multiplayerDisabled";

    /**
     * The Skin that you have equipped is from a content pack that is not allowed in multiplayer games.
     */
    String MC_DISCONNECTIONSCREEN_MULTIPLAYERLOCKEDSKIN = "minecraft:disconnectionScreen.multiplayerLockedSkin";

    /**
     * Multiplayer Restricted Skin!
     */
    String MC_DISCONNECTIONSCREEN_MULTIPLAYERLOCKEDSKIN_TITLE = "minecraft:disconnectionScreen.multiplayerLockedSkin.title";

    /**
     * Please check your connection to the internet and try again.
     */
    String MC_DISCONNECTIONSCREEN_NOINTERNET = "minecraft:disconnectionScreen.noInternet";

    /**
     * You have been disconnected
     */
    String MC_DISCONNECTIONSCREEN_NOREASON = "minecraft:disconnectionScreen.noReason";

    /**
     * You're not invited to play on this server.
     */
    String MC_DISCONNECTIONSCREEN_NOTALLOWED = "minecraft:disconnectionScreen.notAllowed";

    /**
     * You need to authenticate to Microsoft services.
     */
    String MC_DISCONNECTIONSCREEN_NOTAUTHENTICATED = "minecraft:disconnectionScreen.notAuthenticated";

    /**
     * It appears you are unable to connect to your network, please connect to a Wi-Fi network or enable 'Use Mobile Data' in profile settings.
     */
    String MC_DISCONNECTIONSCREEN_NOWIFI = "minecraft:disconnectionScreen.nowifi";

    /**
     * Could not connect: Outdated client!
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDCLIENT = "minecraft:disconnectionScreen.outdatedClient";

    /**
     * You need to update your game to continue playing on this Realm.
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDCLIENTREALMS = "minecraft:disconnectionScreen.outdatedClientRealms";

    /**
     * Realms Has Updated
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDCLIENTREALMS_TITLE = "minecraft:disconnectionScreen.outdatedClientRealms.title";

    /**
     * The host is using an older version of Minecraft. Everyone should update to the latest version of Minecraft and try again.
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVER = "minecraft:disconnectionScreen.outdatedServer";

    /**
     * Your game is up-to-date but this Realm is not.
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVERREALMS = "minecraft:disconnectionScreen.outdatedServerRealms";

    /**
     * We can't update your Realm server right now because other players are currently online. You can close and reopen your Realm from the Manage Realm screen to make the other players quit and then join your Realm to update it.
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVERREALMS_TITLE = "minecraft:disconnectionScreen.outdatedServerRealms.title";

    /**
     * The Realm you are attempting to access is a different version. Contact the owner of the Realm to address the issue.
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVERREALMSNONOWNER_TITLE = "minecraft:disconnectionScreen.outdatedServerRealmsNonOwner.title";

    /**
     * Realms is not available right now.
     */
    String MC_DISCONNECTIONSCREEN_REALMSALPHAENDEDMESSAGE = "minecraft:disconnectionScreen.realmsAlphaEndedMessage";

    /**
     * Testing the Realms Alpha has ended. Thank you for helping!
     */
    String MC_DISCONNECTIONSCREEN_REALMSALPHAENDEDTITLE = "minecraft:disconnectionScreen.realmsAlphaEndedTitle";

    /**
     * Realms are disabled.
     */
    String MC_DISCONNECTIONSCREEN_REALMSDISABLED = "minecraft:disconnectionScreen.realmsDisabled";

    /**
     * Realms are disabled for the beta.
     */
    String MC_DISCONNECTIONSCREEN_REALMSDISABLEDBETA = "minecraft:disconnectionScreen.realmsDisabledBeta";

    /**
     * Your Realm will soon be updated. You will need an update too.
     */
    String MC_DISCONNECTIONSCREEN_REALMSSERVERUPDATEINCOMING = "minecraft:disconnectionScreen.realmsServerUpdateIncoming";

    /**
     * Check For Game Update Now?
     */
    String MC_DISCONNECTIONSCREEN_REALMSSERVERUPDATEINCOMING_TITLE = "minecraft:disconnectionScreen.realmsServerUpdateIncoming.title";

    /**
     * Assign version in dev options.
     */
    String MC_DISCONNECTIONSCREEN_REALMSWORLDUNASSIGNED = "minecraft:disconnectionScreen.realmsWorldUnassigned";

    /**
     * Realm is Unassigned!
     */
    String MC_DISCONNECTIONSCREEN_REALMSWORLDUNASSIGNED_TITLE = "minecraft:disconnectionScreen.realmsWorldUnassigned.title";

    /**
     * Encountered a problem while downloading or applying resource pack.
     */
    String MC_DISCONNECTIONSCREEN_RESOURCEPACK = "minecraft:disconnectionScreen.resourcePack";

    /**
     * Unable to connect, please restart your client.
     */
    String MC_DISCONNECTIONSCREEN_RESTARTCLIENT = "minecraft:disconnectionScreen.restartClient";

    /**
     * Wow this server is popular! Check back later to see if space opens up.
     */
    String MC_DISCONNECTIONSCREEN_SERVERFULL = "minecraft:disconnectionScreen.serverFull";

    /**
     * Server Full
     */
    String MC_DISCONNECTIONSCREEN_SERVERFULL_TITLE = "minecraft:disconnectionScreen.serverFull.title";

    /**
     * Cannot join world. The account you are signed in to is currently playing in this world on a different device.
     */
    String MC_DISCONNECTIONSCREEN_SERVERIDCONFLICT = "minecraft:disconnectionScreen.serverIdConflict";

    /**
     * Unable to connect to world. The world is no longer available to join.
     */
    String MC_DISCONNECTIONSCREEN_SESSIONNOTFOUND = "minecraft:disconnectionScreen.sessionNotFound";

    /**
     * There was a problem connecting to the world. Please try again. If this error continues, check your internet connection or try restarting Minecraft.
     */
    String MC_DISCONNECTIONSCREEN_TIMEOUT = "minecraft:disconnectionScreen.timeout";

    /**
     * You are banned by the host.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_BANNEDBYHOST = "minecraft:disconnectionScreen.title.bannedByHost";

    /**
     * Connection timed out.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_CONNECTIONTIMEOUT = "minecraft:disconnectionScreen.title.connectionTimeout";

    /**
     * Disconnected.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_DISCONNECTED = "minecraft:disconnectionScreen.title.disconnected";

    /**
     * Disconnected from host.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_DISCONNECTEDFROMHOST = "minecraft:disconnectionScreen.title.disconnectedFromHost";

    /**
     * You have encountered an error.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_ERRORENCOUNTERED = "minecraft:disconnectionScreen.title.errorEncountered";

    /**
     * An error has occurred.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_ERROROCCURRED = "minecraft:disconnectionScreen.title.errorOccurred";

    /**
     * Incompatible Pack.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INCOMPATIBLEPACK = "minecraft:disconnectionScreen.title.incompatiblePack";

    /**
     * Incompatible World.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INCOMPATIBLEWORLD = "minecraft:disconnectionScreen.title.incompatibleWorld";

    /**
     * Invalid Skin.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INVALIDSKIN = "minecraft:disconnectionScreen.title.invalidSkin";

    /**
     * Invalid Tenant ID
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INVALIDTENANTID = "minecraft:disconnectionScreen.title.invalidTenantId";

    /**
     * You have been kicked from the world.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_KICKEDFROMWORLD = "minecraft:disconnectionScreen.title.kickedFromWorld";

    /**
     * Local server not found.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_LOCALSERVERNOTFOUND = "minecraft:disconnectionScreen.title.localServerNotFound";

    /**
     * Multiplayer Connection Failed
     */
    String MC_DISCONNECTIONSCREEN_TITLE_MULTIPLAYERCONNECTION = "minecraft:disconnectionScreen.title.multiplayerConnection";

    /**
     * Multiplayer is disabled.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_MULTIPLAYERDISABLED = "minecraft:disconnectionScreen.title.multiplayerDisabled";

    /**
     * No WiFi Connection
     */
    String MC_DISCONNECTIONSCREEN_TITLE_NOWIFI = "minecraft:disconnectionScreen.title.noWifi";

    /**
     * Out of storage space
     */
    String MC_DISCONNECTIONSCREEN_TITLE_OUTOFSTORAGE = "minecraft:disconnectionScreen.title.outOfStorage";

    /**
     * This Realm has no world assigned.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_REALMSWORLDUNASSIGNED = "minecraft:disconnectionScreen.title.realmsWorldUnassigned";

    /**
     * Resource pack failed to load.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_RESOURCEPACKLOAD = "minecraft:disconnectionScreen.title.resourcePackLoad";

    /**
     * Server not found.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_SERVERNOTFOUND = "minecraft:disconnectionScreen.title.serverNotFound";

    /**
     * Skin error.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_SKINERROR = "minecraft:disconnectionScreen.title.skinError";

    /**
     * There is an issue with your skin.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_SKINISSUE = "minecraft:disconnectionScreen.title.skinIssue";

    /**
     * Unable to connect.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_UNABLETOCONNECT = "minecraft:disconnectionScreen.title.unableToConnect";

    /**
     * Version mismatch
     */
    String MC_DISCONNECTIONSCREEN_TITLE_VERSIONMISMATCH = "minecraft:disconnectionScreen.title.versionMismatch";

    /**
     * Your version does not match the host's version
     */
    String MC_DISCONNECTIONSCREEN_TITLE_VERSIONMISMATCHHOST = "minecraft:disconnectionScreen.title.versionMismatchHost";

    /**
     * Version is out of date.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_VERSIONOUTOFDATE = "minecraft:disconnectionScreen.title.versionOutOfDate";

    /**
     * World failed to load.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_WORLDFAILEDLOAD = "minecraft:disconnectionScreen.title.worldFailedLoad";

    /**
     * The World you are trying to join is full.
     */
    String MC_DISCONNECTIONSCREEN_TITLE_WORLDFULL = "minecraft:disconnectionScreen.title.worldFull";

    /**
     * Disconnected due to an unexpected type of packet received which cannot be processed.  This could be due to a version mismatch or corruption at the networking level.
     */
    String MC_DISCONNECTIONSCREEN_UNEXPECTEDPACKET = "minecraft:disconnectionScreen.unexpectedPacket";

    /**
     * Disconnected due to world corruption
     */
    String MC_DISCONNECTIONSCREEN_WORLDCORRUPTION = "minecraft:disconnectionScreen.worldCorruption";

    /**
     * Try reopening the world or restoring it from a previous backup
     */
    String MC_DISCONNECTIONSCREEN_WORLDCORRUPTION_MESSAGE = "minecraft:disconnectionScreen.worldCorruption.message";

    /**
     * Accents
     */
    String MC_DR_ACCENT_CHANNEL = "minecraft:dr.accent_channel";

    /**
     * Narrow Arms
     */
    String MC_DR_ACCESSIBILITY_ARM_SIZE_NARROW = "minecraft:dr.accessibility.arm_size.narrow";

    /**
     * Wide Arms
     */
    String MC_DR_ACCESSIBILITY_ARM_SIZE_WIDE = "minecraft:dr.accessibility.arm_size.wide";

    /**
     * Select previous character
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_LEFT = "minecraft:dr.accessibility.character_left";

    /**
     * Select next character
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_RIGHT = "minecraft:dr.accessibility.character_right";

    /**
     * Medium Character
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_MEDIUM = "minecraft:dr.accessibility.character_size.medium";

    /**
     * Small Character
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_SMALL = "minecraft:dr.accessibility.character_size.small";

    /**
     * Smaller character
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_SMALLER = "minecraft:dr.accessibility.character_size.smaller";

    /**
     * Tall Character
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_TALL = "minecraft:dr.accessibility.character_size.tall";

    /**
     * Toggle. Open Color Picker
     */
    String MC_DR_ACCESSIBILITY_COLOR_PICKER = "minecraft:dr.accessibility.color_picker";

    /**
     * Custom Classic Skin
     */
    String MC_DR_ACCESSIBILITY_CUSTOM_SKIN = "minecraft:dr.accessibility.custom_skin";

    /**
     * Toggle. View Character in full screen
     */
    String MC_DR_ACCESSIBILITY_EXPAND_APPEARANCE = "minecraft:dr.accessibility.expand_appearance";

    /**
     * Switch to both limbs
     */
    String MC_DR_ACCESSIBILITY_LIMB_BOTH = "minecraft:dr.accessibility.limb_both";

    /**
     * Switch to left side
     */
    String MC_DR_ACCESSIBILITY_LIMB_LEFT = "minecraft:dr.accessibility.limb_left";

    /**
     * Switch to right side
     */
    String MC_DR_ACCESSIBILITY_LIMB_RIGHT = "minecraft:dr.accessibility.limb_right";

    /**
     * Locked
     */
    String MC_DR_ACCESSIBILITY_LOCKED = "minecraft:dr.accessibility.locked";

    /**
     * Owned
     */
    String MC_DR_ACCESSIBILITY_OWNED = "minecraft:dr.accessibility.owned";

    /**
     * Character Arms
     */
    String MC_DR_ACCESSIBILITY_PERSONA_ARMS = "minecraft:dr.accessibility.persona_arms";

    /**
     * Character Back Item
     */
    String MC_DR_ACCESSIBILITY_PERSONA_BACK = "minecraft:dr.accessibility.persona_back";

    /**
     * Character Body
     */
    String MC_DR_ACCESSIBILITY_PERSONA_BODY = "minecraft:dr.accessibility.persona_body";

    /**
     * Character Bottom
     */
    String MC_DR_ACCESSIBILITY_PERSONA_BOTTOM = "minecraft:dr.accessibility.persona_bottom";

    /**
     * Character Cape
     */
    String MC_DR_ACCESSIBILITY_PERSONA_CAPES = "minecraft:dr.accessibility.persona_capes";

    /**
     * Character Skin
     */
    String MC_DR_ACCESSIBILITY_PERSONA_CLASSIC_SKIN = "minecraft:dr.accessibility.persona_classic_skin";

    /**
     * Character Emote
     */
    String MC_DR_ACCESSIBILITY_PERSONA_EMOTE = "minecraft:dr.accessibility.persona_emote";

    /**
     * Character Eyes
     */
    String MC_DR_ACCESSIBILITY_PERSONA_EYES = "minecraft:dr.accessibility.persona_eyes";

    /**
     * Character Face Accessory
     */
    String MC_DR_ACCESSIBILITY_PERSONA_FACE_ACCESSORY = "minecraft:dr.accessibility.persona_face_accessory";

    /**
     * Character Facial Hair
     */
    String MC_DR_ACCESSIBILITY_PERSONA_FACIAL_HAIR = "minecraft:dr.accessibility.persona_facial_hair";

    /**
     * Character Footwear
     */
    String MC_DR_ACCESSIBILITY_PERSONA_FEET = "minecraft:dr.accessibility.persona_feet";

    /**
     * Character Hair
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HAIR = "minecraft:dr.accessibility.persona_hair";

    /**
     * Character Gloves
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HAND = "minecraft:dr.accessibility.persona_hand";

    /**
     * Character Headwear
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HEAD = "minecraft:dr.accessibility.persona_head";

    /**
     * Character Face Item
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HOOD = "minecraft:dr.accessibility.persona_hood";

    /**
     * Character Legs
     */
    String MC_DR_ACCESSIBILITY_PERSONA_LEGS = "minecraft:dr.accessibility.persona_legs";

    /**
     * Character Mouth
     */
    String MC_DR_ACCESSIBILITY_PERSONA_MOUTH = "minecraft:dr.accessibility.persona_mouth";

    /**
     * Character Outerwear
     */
    String MC_DR_ACCESSIBILITY_PERSONA_OUTERWEAR = "minecraft:dr.accessibility.persona_outerwear";

    /**
     * Character Base
     */
    String MC_DR_ACCESSIBILITY_PERSONA_SKIN = "minecraft:dr.accessibility.persona_skin";

    /**
     * Character Top
     */
    String MC_DR_ACCESSIBILITY_PERSONA_TOP = "minecraft:dr.accessibility.persona_top";

    /**
     * Purchaseable
     */
    String MC_DR_ACCESSIBILITY_PURCHASEABLE = "minecraft:dr.accessibility.purchaseable";

    /**
     * Redeemable
     */
    String MC_DR_ACCESSIBILITY_REDEEMABLE = "minecraft:dr.accessibility.redeemable";

    /**
     * Switch to %s
     */
    String MC_DR_ACCESSIBILITY_SWITCH_COLOR_CHANNEL = "minecraft:dr.accessibility.switch_color_channel";

    /**
     * This item is unlocked through completing an achievement.
     */
    String MC_DR_ACHIEVEMENT_ACHIEVEMENT_LOCKED_NOT_SIGNED_IN = "minecraft:dr.achievement.achievement_locked.not_signed_in";

    /**
     * This item is unlocked by completing the %s achievement.
     */
    String MC_DR_ACHIEVEMENT_ACHIEVEMENT_LOCKED_SIGNED_IN = "minecraft:dr.achievement.achievement_locked.signed_in";

    /**
     * This item was unlocked by completing the %s achievement.
     */
    String MC_DR_ACHIEVEMENT_ACHIEVEMENT_UNLOCKED = "minecraft:dr.achievement.achievement_unlocked";

    /**
     * This item is unlocked by completing a challenge from Minecraft Earth.
     */
    String MC_DR_ACHIEVEMENT_CHALLENGE_LOCKED = "minecraft:dr.achievement.challenge_locked";

    /**
     * This item was unlocked by completing a challenge from Minecraft Earth.
     */
    String MC_DR_ACHIEVEMENT_CHALLENGE_UNLOCKED = "minecraft:dr.achievement.challenge_unlocked";

    /**
     * Arm Size
     */
    String MC_DR_ARM_SIZE_LABEL_TEXT = "minecraft:dr.arm_size_label_text";

    /**
     * Limited Time Offer!
     */
    String MC_DR_BANNER_LIMITEDTIMEOFFER = "minecraft:dr.banner.limitedTimeOffer";

    /**
     * Sale!
     */
    String MC_DR_BANNER_SALE = "minecraft:dr.banner.sale";

    /**
     * Height
     */
    String MC_DR_BODY_SIZE_LABEL_TEXT = "minecraft:dr.body_size_label_text";

    /**
     * Both Arms
     */
    String MC_DR_BOTH_ARMS = "minecraft:dr.both_arms";

    /**
     * Both Legs
     */
    String MC_DR_BOTH_LEGS = "minecraft:dr.both_legs";

    /**
     * Ok
     */
    String MC_DR_BUTTON_OK = "minecraft:dr.button.ok";

    /**
     * Emotes
     */
    String MC_DR_CATEGORIES_ANIMATION = "minecraft:dr.categories.animation";

    /**
     * Arms
     */
    String MC_DR_CATEGORIES_ARMS = "minecraft:dr.categories.arms";

    /**
     * Back Items
     */
    String MC_DR_CATEGORIES_BACK_ITEM = "minecraft:dr.categories.back_item";

    /**
     * Bases
     */
    String MC_DR_CATEGORIES_BASE = "minecraft:dr.categories.base";

    /**
     * Body
     */
    String MC_DR_CATEGORIES_BODY = "minecraft:dr.categories.body";

    /**
     * Bottoms
     */
    String MC_DR_CATEGORIES_BOTTOM = "minecraft:dr.categories.bottom";

    /**
     * Capes
     */
    String MC_DR_CATEGORIES_CAPES = "minecraft:dr.categories.capes";

    /**
     * Emotes
     */
    String MC_DR_CATEGORIES_EMOTES = "minecraft:dr.categories.emotes";

    /**
     * Eyes
     */
    String MC_DR_CATEGORIES_EYES = "minecraft:dr.categories.eyes";

    /**
     * Face Items
     */
    String MC_DR_CATEGORIES_FACE_ITEM = "minecraft:dr.categories.face_item";

    /**
     * Facial Hairs
     */
    String MC_DR_CATEGORIES_FACIAL_HAIR = "minecraft:dr.categories.facial_hair";

    /**
     * Footwear
     */
    String MC_DR_CATEGORIES_FOOTWEAR = "minecraft:dr.categories.footwear";

    /**
     * Gloves
     */
    String MC_DR_CATEGORIES_GLOVES = "minecraft:dr.categories.gloves";

    /**
     * Hairs
     */
    String MC_DR_CATEGORIES_HAIR = "minecraft:dr.categories.hair";

    /**
     * Headwear
     */
    String MC_DR_CATEGORIES_HEADWEAR = "minecraft:dr.categories.headwear";

    /**
     * Legs
     */
    String MC_DR_CATEGORIES_LEGS = "minecraft:dr.categories.legs";

    /**
     * Mouths
     */
    String MC_DR_CATEGORIES_MOUTH = "minecraft:dr.categories.mouth";

    /**
     * Outerwear
     */
    String MC_DR_CATEGORIES_OUTERWEAR = "minecraft:dr.categories.outerwear";

    /**
     * Size
     */
    String MC_DR_CATEGORIES_SIZE = "minecraft:dr.categories.size";

    /**
     * Style
     */
    String MC_DR_CATEGORIES_STYLE = "minecraft:dr.categories.style";

    /**
     * Tops
     */
    String MC_DR_CATEGORIES_TOP = "minecraft:dr.categories.top";

    /**
     * Select items on the left to see how they look on your character!
     */
    String MC_DR_CHARACTER_CREATOR_GETTING_STARTED_DETAIL = "minecraft:dr.character_creator_getting_started_detail";

    /**
     * Getting Started
     */
    String MC_DR_CHARACTER_CREATOR_GETTING_STARTED_TITLE = "minecraft:dr.character_creator_getting_started_title";

    /**
     * +%s
     */
    String MC_DR_CLASSIC_SKINS_ADDITION_SKIN_COUNT = "minecraft:dr.classic_skins.addition_skin_count";

    /**
     * Choose New Skin
     */
    String MC_DR_CLASSIC_SKINS_CHOOSE_CUSTOM_SKIN = "minecraft:dr.classic_skins.choose_custom_skin";

    /**
     * Import a png (64x32, 64x64, or 128x128) from your device to use as your skin. This will not sync between devices or games.
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_DESCRIPTION = "minecraft:dr.classic_skins.custom_skin_description";

    /**
     * Your device cannot select a custom skin while in a multiplayer game. Please return to the main menu to equip one.
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_DESCRIPTION_DISABLED = "minecraft:dr.classic_skins.custom_skin_description_disabled";

    /**
     * Players with custom skins will not be visible to you if the “Only Allow Trusted Skins” option is enabled
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_DESCRIPTION_POPUP = "minecraft:dr.classic_skins.custom_skin_description_popup";

    /**
     * Owned Skins
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_SECTION_TITLE = "minecraft:dr.classic_skins.custom_skin_section_title";

    /**
     * Import Skin
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_TITLE = "minecraft:dr.classic_skins.custom_skin_title";

    /**
     * Please import a .png file of dimensions 64x64, 64x32, or 128x128
     */
    String MC_DR_CLASSIC_SKINS_INVALIDCUSTOMSKIN = "minecraft:dr.classic_skins.invalidCustomSkin";

    /**
     * Owned
     */
    String MC_DR_CLASSIC_SKINS_OWNED = "minecraft:dr.classic_skins.owned";

    /**
     * Get More
     */
    String MC_DR_CLASSIC_SKINS_PURCHASABLE = "minecraft:dr.classic_skins.purchasable";

    /**
     * Realms Plus
     */
    String MC_DR_CLASSIC_SKINS_REALMS_PLUS = "minecraft:dr.classic_skins.realms_plus";

    /**
     * Realms Plus
     */
    String MC_DR_CLASSIC_SKINS_REALMS_PLUS_BUTTON = "minecraft:dr.classic_skins.realms_plus_button";

    /**
     * by Minecraft
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_AUTHOR_MINECRAFT = "minecraft:dr.classic_skins.right_side.author_minecraft";

    /**
     * by %s
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_AUTHOR_NAME = "minecraft:dr.classic_skins.right_side.author_name";

    /**
     * Custom Skin
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_CUSTOM_SKIN = "minecraft:dr.classic_skins.right_side.custom_skin";

    /**
     * Featured & Recommended Skin Packs
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_FEATURED_TITLE = "minecraft:dr.classic_skins.right_side.featured_title";

    /**
     * Usable everywhere
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_FREE_USAGE = "minecraft:dr.classic_skins.right_side.free_usage";

    /**
     * This skin will roam wherever you've logged in.
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_FREE_USAGE_TOOLTIP = "minecraft:dr.classic_skins.right_side.free_usage.tooltip";

    /**
     * Limited usage
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_LIMITED_USAGE = "minecraft:dr.classic_skins.right_side.limited_usage";

    /**
     * This skin can be used here, but may not roam.
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_LIMITED_USAGE_TOOLTIP = "minecraft:dr.classic_skins.right_side.limited_usage.tooltip";

    /**
     * *Buys %s Minecoins for %s and redeems this pack for %s Minecoins leaving %s left over.
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_PURCHASING_DISCLAIMER = "minecraft:dr.classic_skins.right_side.purchasing_disclaimer";

    /**
     * %s Skins
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_SKIN_COUNT_PLURAL = "minecraft:dr.classic_skins.right_side.skin_count.plural";

    /**
     * %s Skin
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_SKIN_COUNT_SINGULAR = "minecraft:dr.classic_skins.right_side.skin_count.singular";

    /**
     * %s Texture Packs
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_TEXTURE_COUNT_PLURAL = "minecraft:dr.classic_skins.right_side.texture_count.plural";

    /**
     * %s Texture Pack
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_TEXTURE_COUNT_SINGULAR = "minecraft:dr.classic_skins.right_side.texture_count.singular";

    /**
     * %s Worlds
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_WORLD_COUNT_PLURAL = "minecraft:dr.classic_skins.right_side.world_count.plural";

    /**
     * %s World
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_WORLD_COUNT_SINGULAR = "minecraft:dr.classic_skins.right_side.world_count.singular";

    /**
     * See in Store
     */
    String MC_DR_CLASSIC_SKINS_SEE_IN_STORE = "minecraft:dr.classic_skins.see_in_store";

    /**
     * See Pack in Store
     */
    String MC_DR_CLASSIC_SKINS_SEE_PACK_IN_STORE = "minecraft:dr.classic_skins.see_pack_in_store";

    /**
     * Choose the correct model type for your skin
     */
    String MC_DR_CLASSIC_SKINS_SELECT_SKIN = "minecraft:dr.classic_skins.select_skin";

    /**
     * Skin Type
     */
    String MC_DR_CLASSIC_SKINS_SELECT_SKIN_TITLE = "minecraft:dr.classic_skins.select_skin.title";

    /**
     * Show More
     */
    String MC_DR_CLASSIC_SKINS_SHOW_MORE = "minecraft:dr.classic_skins.show_more";

    /**
     * You must purchase the skin pack to use that skin, and we can't connect to the Marketplace.
     */
    String MC_DR_CLASSIC_SKINS_UPSELLWITHOUTSTORE = "minecraft:dr.classic_skins.upsellWithoutStore";

    /**
     * Collect
     */
    String MC_DR_COLLECT_ACHIEVEMENT_ITEM = "minecraft:dr.collect_achievement_item";

    /**
     * Featured %s
     */
    String MC_DR_COLLECTOR_TITLE_FEATURED = "minecraft:dr.collector_title.featured";

    /**
     * All %s
     */
    String MC_DR_COLLECTOR_TITLE_GENERAL = "minecraft:dr.collector_title.general";

    /**
     * Owned %s
     */
    String MC_DR_COLLECTOR_TITLE_OWNED = "minecraft:dr.collector_title.owned";

    /**
     * Amber - Dark
     */
    String MC_DR_COLOR_AMBER_DARK = "minecraft:dr.color.amber_dark";

    /**
     * Amber - Dark Soft
     */
    String MC_DR_COLOR_AMBER_DARK_SOFT = "minecraft:dr.color.amber_dark_soft";

    /**
     * Amber - Light
     */
    String MC_DR_COLOR_AMBER_LIGHT = "minecraft:dr.color.amber_light";

    /**
     * Auburn Red
     */
    String MC_DR_COLOR_AUBURN_RED = "minecraft:dr.color.auburn_red";

    /**
     * Blue - Dark
     */
    String MC_DR_COLOR_BLUE_DARK = "minecraft:dr.color.blue_dark";

    /**
     * Blue - Light
     */
    String MC_DR_COLOR_BLUE_LIGHT = "minecraft:dr.color.blue_light";

    /**
     * Blue - Mid
     */
    String MC_DR_COLOR_BLUE_MID = "minecraft:dr.color.blue_mid";

    /**
     * Blue - Vivid
     */
    String MC_DR_COLOR_BLUE_VIVID = "minecraft:dr.color.blue_vivid";

    /**
     * Blueish Black
     */
    String MC_DR_COLOR_BLUEISH_BLACK = "minecraft:dr.color.blueish_black";

    /**
     * Brown - Dark
     */
    String MC_DR_COLOR_BROWN_DARK = "minecraft:dr.color.brown_dark";

    /**
     * Brown - Light
     */
    String MC_DR_COLOR_BROWN_LIGHT = "minecraft:dr.color.brown_light";

    /**
     * Brown - Mid
     */
    String MC_DR_COLOR_BROWN_MID = "minecraft:dr.color.brown_mid";

    /**
     * Brown – Very Dark
     */
    String MC_DR_COLOR_BROWN_VERY_DARK = "minecraft:dr.color.brown_very_dark";

    /**
     * Color
     */
    String MC_DR_COLOR_BUTTON_NAME = "minecraft:dr.color.button_name";

    /**
     * Copper Red
     */
    String MC_DR_COLOR_COPPER_RED = "minecraft:dr.color.copper_red";

    /**
     * Dark Blonde
     */
    String MC_DR_COLOR_DARK_BLONDE = "minecraft:dr.color.dark_blonde";

    /**
     * Dark Brown
     */
    String MC_DR_COLOR_DARK_BROWN = "minecraft:dr.color.dark_brown";

    /**
     * Dark - Cool
     */
    String MC_DR_COLOR_DARK_COOL = "minecraft:dr.color.dark_cool";

    /**
     * Dark Gray
     */
    String MC_DR_COLOR_DARK_GRAY = "minecraft:dr.color.dark_gray";

    /**
     * Dark Red
     */
    String MC_DR_COLOR_DARK_RED = "minecraft:dr.color.dark_red";

    /**
     * Dark - Soft
     */
    String MC_DR_COLOR_DARK_SOFT = "minecraft:dr.color.dark_soft";

    /**
     * Dark - Warm
     */
    String MC_DR_COLOR_DARK_WARM = "minecraft:dr.color.dark_warm";

    /**
     * Deep Black
     */
    String MC_DR_COLOR_DEEP_BLACK = "minecraft:dr.color.deep_black";

    /**
     * Deep Brown
     */
    String MC_DR_COLOR_DEEP_BROWN = "minecraft:dr.color.deep_brown";

    /**
     * Gray Blue
     */
    String MC_DR_COLOR_GRAY_BLUE = "minecraft:dr.color.gray_blue";

    /**
     * Gray - Dark
     */
    String MC_DR_COLOR_GRAY_DARK = "minecraft:dr.color.gray_dark";

    /**
     * Gray - Light
     */
    String MC_DR_COLOR_GRAY_LIGHT = "minecraft:dr.color.gray_light";

    /**
     * Green - Bright
     */
    String MC_DR_COLOR_GREEN_BRIGHT = "minecraft:dr.color.green_bright";

    /**
     * Green – Cool Dark
     */
    String MC_DR_COLOR_GREEN_COOL_DARK = "minecraft:dr.color.green_cool_dark";

    /**
     * Green - Light
     */
    String MC_DR_COLOR_GREEN_LIGHT = "minecraft:dr.color.green_light";

    /**
     * Green - Mid
     */
    String MC_DR_COLOR_GREEN_MID = "minecraft:dr.color.green_mid";

    /**
     * Green – Warm Dark
     */
    String MC_DR_COLOR_GREEN_WARM_DARK = "minecraft:dr.color.green_warm_dark";

    /**
     * Light Blonde
     */
    String MC_DR_COLOR_LIGHT_BLONDE = "minecraft:dr.color.light_blonde";

    /**
     * Light Brown
     */
    String MC_DR_COLOR_LIGHT_BROWN = "minecraft:dr.color.light_brown";

    /**
     * Light - Cool
     */
    String MC_DR_COLOR_LIGHT_COOL = "minecraft:dr.color.light_cool";

    /**
     * Light Gray
     */
    String MC_DR_COLOR_LIGHT_GRAY = "minecraft:dr.color.light_gray";

    /**
     * Light - Medium
     */
    String MC_DR_COLOR_LIGHT_MEDIUM = "minecraft:dr.color.light_medium";

    /**
     * Light Red
     */
    String MC_DR_COLOR_LIGHT_RED = "minecraft:dr.color.light_red";

    /**
     * Light - Warm
     */
    String MC_DR_COLOR_LIGHT_WARM = "minecraft:dr.color.light_warm";

    /**
     * MC Black
     */
    String MC_DR_COLOR_MC_BLACK = "minecraft:dr.color.mc_black";

    /**
     * Medium Blonde
     */
    String MC_DR_COLOR_MEDIUM_BLONDE = "minecraft:dr.color.medium_blonde";

    /**
     * Medium Brown
     */
    String MC_DR_COLOR_MEDIUM_BROWN = "minecraft:dr.color.medium_brown";

    /**
     * Medium - Cool
     */
    String MC_DR_COLOR_MEDIUM_COOL = "minecraft:dr.color.medium_cool";

    /**
     * Medium Dark - Cool
     */
    String MC_DR_COLOR_MEDIUM_DARK_COOL = "minecraft:dr.color.medium_dark_cool";

    /**
     * Medium Dark - Soft
     */
    String MC_DR_COLOR_MEDIUM_DARK_SOFT = "minecraft:dr.color.medium_dark_soft";

    /**
     * Medium Dark - Warm
     */
    String MC_DR_COLOR_MEDIUM_DARK_WARM = "minecraft:dr.color.medium_dark_warm";

    /**
     * Medium Light Blonde
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_BLONDE = "minecraft:dr.color.medium_light_blonde";

    /**
     * Medium Light - Cool
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_COOL = "minecraft:dr.color.medium_light_cool";

    /**
     * Medium Light - Deep
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_DEEP = "minecraft:dr.color.medium_light_deep";

    /**
     * Medium Light - Vibrant
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_VIBRANT = "minecraft:dr.color.medium_light_vibrant";

    /**
     * Medium Light - Warm
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_WARM = "minecraft:dr.color.medium_light_warm";

    /**
     * Medium - Soft
     */
    String MC_DR_COLOR_MEDIUM_SOFT = "minecraft:dr.color.medium_soft";

    /**
     * Medium - Warm
     */
    String MC_DR_COLOR_MEDIUM_WARM = "minecraft:dr.color.medium_warm";

    /**
     * Minecraft Black
     */
    String MC_DR_COLOR_MINECRAFT_BLACK = "minecraft:dr.color.minecraft_black";

    /**
     * Minecraft Blue
     */
    String MC_DR_COLOR_MINECRAFT_BLUE = "minecraft:dr.color.minecraft_blue";

    /**
     * Minecraft Cyan
     */
    String MC_DR_COLOR_MINECRAFT_CYAN = "minecraft:dr.color.minecraft_cyan";

    /**
     * Minecraft Gray
     */
    String MC_DR_COLOR_MINECRAFT_GRAY = "minecraft:dr.color.minecraft_gray";

    /**
     * Minecraft Green
     */
    String MC_DR_COLOR_MINECRAFT_GREEN = "minecraft:dr.color.minecraft_green";

    /**
     * Minecraft Light Blue
     */
    String MC_DR_COLOR_MINECRAFT_LIGHT_BLUE = "minecraft:dr.color.minecraft_light_blue";

    /**
     * Minecraft Light Gray
     */
    String MC_DR_COLOR_MINECRAFT_LIGHT_GRAY = "minecraft:dr.color.minecraft_light_gray";

    /**
     * Minecraft Lime Green
     */
    String MC_DR_COLOR_MINECRAFT_LIME_GREEN = "minecraft:dr.color.minecraft_lime_green";

    /**
     * Minecraft Magenta
     */
    String MC_DR_COLOR_MINECRAFT_MAGENTA = "minecraft:dr.color.minecraft_magenta";

    /**
     * Minecraft Orange
     */
    String MC_DR_COLOR_MINECRAFT_ORANGE = "minecraft:dr.color.minecraft_orange";

    /**
     * Minecraft Pink
     */
    String MC_DR_COLOR_MINECRAFT_PINK = "minecraft:dr.color.minecraft_pink";

    /**
     * Minecraft Purple
     */
    String MC_DR_COLOR_MINECRAFT_PURPLE = "minecraft:dr.color.minecraft_purple";

    /**
     * Minecraft Red
     */
    String MC_DR_COLOR_MINECRAFT_RED = "minecraft:dr.color.minecraft_red";

    /**
     * Minecraft White
     */
    String MC_DR_COLOR_MINECRAFT_WHITE = "minecraft:dr.color.minecraft_white";

    /**
     * Minecraft Yellow
     */
    String MC_DR_COLOR_MINECRAFT_YELLOW = "minecraft:dr.color.minecraft_yellow";

    /**
     * Muted Gray
     */
    String MC_DR_COLOR_MUTED_GRAY = "minecraft:dr.color.muted_gray";

    /**
     * Purpleish Black
     */
    String MC_DR_COLOR_PURPLEISH_BLACK = "minecraft:dr.color.purpleish_black";

    /**
     * Red - Dark
     */
    String MC_DR_COLOR_RED_DARK = "minecraft:dr.color.red_dark";

    /**
     * Reddish Black
     */
    String MC_DR_COLOR_REDDISH_BLACK = "minecraft:dr.color.reddish_black";

    /**
     * Soft Black
     */
    String MC_DR_COLOR_SOFT_BLACK = "minecraft:dr.color.soft_black";

    /**
     * Soft Blonde
     */
    String MC_DR_COLOR_SOFT_BLONDE = "minecraft:dr.color.soft_blonde";

    /**
     * Soft Magenta
     */
    String MC_DR_COLOR_SOFT_MAGENTA = "minecraft:dr.color.soft_magenta";

    /**
     * Very Dark - Cool
     */
    String MC_DR_COLOR_VERY_DARK_COOL = "minecraft:dr.color.very_dark_cool";

    /**
     * Very Dark - Warm
     */
    String MC_DR_COLOR_VERY_DARK_WARM = "minecraft:dr.color.very_dark_warm";

    /**
     * Very Light Blonde
     */
    String MC_DR_COLOR_VERY_LIGHT_BLONDE = "minecraft:dr.color.very_light_blonde";

    /**
     * Very Light - Cool
     */
    String MC_DR_COLOR_VERY_LIGHT_COOL = "minecraft:dr.color.very_light_cool";

    /**
     * Very Light - Soft
     */
    String MC_DR_COLOR_VERY_LIGHT_SOFT = "minecraft:dr.color.very_light_soft";

    /**
     * Very Light - Warm
     */
    String MC_DR_COLOR_VERY_LIGHT_WARM = "minecraft:dr.color.very_light_warm";

    /**
     * Vibrant Orange
     */
    String MC_DR_COLOR_VIBRANT_ORANGE = "minecraft:dr.color.vibrant_orange";

    /**
     * White
     */
    String MC_DR_COLOR_WHITE = "minecraft:dr.color.white";

    /**
     * White - Deep
     */
    String MC_DR_COLOR_WHITE_DEEP = "minecraft:dr.color.white_deep";

    /**
     * White - Light
     */
    String MC_DR_COLOR_WHITE_LIGHT = "minecraft:dr.color.white_light";

    /**
     * White - Mid
     */
    String MC_DR_COLOR_WHITE_MID = "minecraft:dr.color.white_mid";

    /**
     * Yellow Green
     */
    String MC_DR_COLOR_YELLOW_GREEN = "minecraft:dr.color.yellow_green";

    /**
     * Deep Linking Failed: Unable to open offer
     */
    String MC_DR_DEEP_LINK_FAIL_DRESSING_ROOM = "minecraft:dr.deep_link_fail.dressing_room";

    /**
     * Deep Linking Failed: Unable to load profile
     */
    String MC_DR_DEEP_LINK_FAIL_PROFILE = "minecraft:dr.deep_link_fail.profile";

    /**
     * Alex's Pants
     */
    String MC_DR_DEFAULT_ALEX_BOTTOM = "minecraft:dr.default.alex.bottom";

    /**
     * Alex's Hair
     */
    String MC_DR_DEFAULT_ALEX_HAIR = "minecraft:dr.default.alex.hair";

    /**
     * Alex's Mouth
     */
    String MC_DR_DEFAULT_ALEX_MOUTH = "minecraft:dr.default.alex.mouth";

    /**
     * Alex's Shoes
     */
    String MC_DR_DEFAULT_ALEX_SHOES = "minecraft:dr.default.alex.shoes";

    /**
     * Alex
     */
    String MC_DR_DEFAULT_ALEX_SKIN = "minecraft:dr.default.alex.skin";

    /**
     * Alex's Shirt
     */
    String MC_DR_DEFAULT_ALEX_TOP = "minecraft:dr.default.alex.top";

    /**
     * Simple Clap
     */
    String MC_DR_DEFAULT_EMOTE_BASICCLAP = "minecraft:dr.default.emote.basicClap";

    /**
     * Follow Me
     */
    String MC_DR_DEFAULT_EMOTE_COMMUNICATIONFOLLOWME = "minecraft:dr.default.emote.communicationFollowMe";

    /**
     * Over There!
     */
    String MC_DR_DEFAULT_EMOTE_COMMUNICATIONPOINT = "minecraft:dr.default.emote.communicationPoint";

    /**
     * Wave
     */
    String MC_DR_DEFAULT_EMOTE_OVERHEADWAVE = "minecraft:dr.default.emote.overheadWave";

    /**
     * Standard Stare
     */
    String MC_DR_DEFAULT_EYES = "minecraft:dr.default.eyes";

    /**
     * Steve's Pants
     */
    String MC_DR_DEFAULT_STEVE_BOTTOM = "minecraft:dr.default.steve.bottom";

    /**
     * Steve's Hair
     */
    String MC_DR_DEFAULT_STEVE_HAIR = "minecraft:dr.default.steve.hair";

    /**
     * Steve's Mouth
     */
    String MC_DR_DEFAULT_STEVE_MOUTH = "minecraft:dr.default.steve.mouth";

    /**
     * Steve's Shoes
     */
    String MC_DR_DEFAULT_STEVE_SHOES = "minecraft:dr.default.steve.shoes";

    /**
     * Steve
     */
    String MC_DR_DEFAULT_STEVE_SKIN = "minecraft:dr.default.steve.skin";

    /**
     * Steve's Shirt
     */
    String MC_DR_DEFAULT_STEVE_TOP = "minecraft:dr.default.steve.top";

    /**
     * Default
     */
    String MC_DR_DEFAULT_PIECE_BUTTON_TEXT = "minecraft:dr.default_piece_button_text";

    /**
     * Assign "%s" to a Slot
     */
    String MC_DR_EMOTES_ASSIGNMENT = "minecraft:dr.emotes.assignment";

    /**
     * Key %d
     */
    String MC_DR_EMOTES_KEY = "minecraft:dr.emotes.key";

    /**
     * Play Again
     */
    String MC_DR_EMOTES_PLAY_AGAIN = "minecraft:dr.emotes.play_again";

    /**
     * Replace
     */
    String MC_DR_EMOTES_REPLACE = "minecraft:dr.emotes.replace";

    /**
     * Emote %d
     */
    String MC_DR_EMOTES_SLOT = "minecraft:dr.emotes.slot";

    /**
     * Equip
     */
    String MC_DR_EQUIP_PIECE = "minecraft:dr.equip_piece";

    /**
     * Eyebrows
     */
    String MC_DR_EYEBROWS_COLOR = "minecraft:dr.eyebrows_color";

    /**
     * Facial Hair
     */
    String MC_DR_FACIAL_HAIR_COLOR = "minecraft:dr.facial_hair_color";

    /**
     * Go to Settings
     */
    String MC_DR_GO_TO_PROFILE_SETTINGS_TEXT = "minecraft:dr.go_to_profile_settings_text";

    /**
     * Hair
     */
    String MC_DR_HAIR_COLOR = "minecraft:dr.hair_color";

    /**
     * Emotes
     */
    String MC_DR_HEADER_ANIMATION = "minecraft:dr.header.animation";

    /**
     * Capes
     */
    String MC_DR_HEADER_CAPES = "minecraft:dr.header.capes";

    /**
     * %s Featured  
     */
    String MC_DR_HEADER_CATEGORYFEATUREDLIST = "minecraft:dr.header.categoryFeaturedList";

    /**
     * Classic Skins
     */
    String MC_DR_HEADER_CLASSIC_SKINS = "minecraft:dr.header.classic_skins";

    /**
     * Color Picker
     */
    String MC_DR_HEADER_COLORPICKER = "minecraft:dr.header.colorPicker";

    /**
     * All by %s
     */
    String MC_DR_HEADER_CREATOR_PIECE_LIST = "minecraft:dr.header.creator_piece_list";

    /**
     * Character Creator
     */
    String MC_DR_HEADER_CUSTOMIZATION = "minecraft:dr.header.customization";

    /**
     * Featured Items
     */
    String MC_DR_HEADER_FEATURED = "minecraft:dr.header.featured";

    /**
     * Featured Skins
     */
    String MC_DR_HEADER_FEATURED_SKINS = "minecraft:dr.header.featured_skins";

    /**
     * Preview Appearance
     */
    String MC_DR_HEADER_PREVIEWAPPEARANCE = "minecraft:dr.header.previewAppearance";

    /**
     * Dressing Room Search
     */
    String MC_DR_HEADER_SEARCH_HOME = "minecraft:dr.header.search.home";

    /**
     * Iris
     */
    String MC_DR_IRIS_COLOR = "minecraft:dr.iris_color";

    /**
     * This is your current Character
     */
    String MC_DR_LABEL_CURRENT_PERSONA = "minecraft:dr.label.current_persona";

    /**
     * This is your current Skin
     */
    String MC_DR_LABEL_CURRENT_SKIN = "minecraft:dr.label.current_skin";

    /**
     * Left Arm
     */
    String MC_DR_LEFT_ARM = "minecraft:dr.left_arm";

    /**
     * Left Leg
     */
    String MC_DR_LEFT_LEG = "minecraft:dr.left_leg";

    /**
     * Limb Selection
     */
    String MC_DR_LIMB_SWITCHER_DEFAULT_TITLE = "minecraft:dr.limb_switcher_default_title";

    /**
     * Loading...
     */
    String MC_DR_LOADING = "minecraft:dr.loading";

    /**
     * Get More Skins
     */
    String MC_DR_MARKETPLACE_GET_MORE_SKINS = "minecraft:dr.marketplace.get_more_skins";

    /**
     * Character and Classic Skin Differences
     */
    String MC_DR_MODAL_DIFFERENCE_INFORMATION_TITLE = "minecraft:dr.modal.difference_information_title";

    /**
     * The current character will be deleted. Are you sure you want to continue?
     */
    String MC_DR_MODAL_PERSONA_DELETE_CONFIRM = "minecraft:dr.modal.persona_delete_confirm";

    /**
     * Delete Character?
     */
    String MC_DR_MODAL_PERSONA_DELETE_CONFIRM_TITLE = "minecraft:dr.modal.persona_delete_confirm_title";

    /**
     * This content is available in most geographic regions, but restricted in a few.
     */
    String MC_DR_MODAL_RESTRICTED_CONTENT_TEXT = "minecraft:dr.modal.restricted_content_text";

    /**
     * Restriction warning
     */
    String MC_DR_MODAL_RESTRICTED_CONTENT_TITLE = "minecraft:dr.modal.restricted_content_title";

    /**
     * Mouth
     */
    String MC_DR_MOUTH_COLOR = "minecraft:dr.mouth_color";

    /**
     * None
     */
    String MC_DR_NONE_BUTTON_TEXT = "minecraft:dr.none_button_text";

    /**
     * Clear All
     */
    String MC_DR_NONE_EMOTE_BUTTON_TEXT = "minecraft:dr.none_emote_button_text";

    /**
     * Failed to load character %s
     */
    String MC_DR_NOTIFICATION_CHARACTER_FAILED = "minecraft:dr.notification.character_failed";

    /**
     * Failed to load character %s. You can reload it from your Profile.
     */
    String MC_DR_NOTIFICATION_CHARACTER_FAILED_START = "minecraft:dr.notification.character_failed_start";

    /**
     * New Skin has been created
     */
    String MC_DR_NOTIFICATION_CLASSIC_SKIN_CREATED_NEW = "minecraft:dr.notification.classic_skin_created_new";

    /**
     * Skin blocked from multiplayer for violating Community Standards.
     */
    String MC_DR_NOTIFICATION_CUSTOM_BANNED = "minecraft:dr.notification.custom_banned";

    /**
     * You just equipped %s
     */
    String MC_DR_NOTIFICATION_EQUIPPED_CLASSIC_SKIN = "minecraft:dr.notification.equipped_classic_skin";

    /**
     * You just equipped the %s
     */
    String MC_DR_NOTIFICATION_EQUIPPED_PIECE = "minecraft:dr.notification.equipped_piece";

    /**
     * Failed to select item
     */
    String MC_DR_NOTIFICATION_ITEM_SELECTION_FAILED = "minecraft:dr.notification.item_selection_failed";

    /**
     * The Character has been created
     */
    String MC_DR_NOTIFICATION_PERSONA_CREATE = "minecraft:dr.notification.persona_create";

    /**
     * New Character has been created
     */
    String MC_DR_NOTIFICATION_PERSONA_CREATE_NEW = "minecraft:dr.notification.persona_create_new";

    /**
     * The Character has been deleted
     */
    String MC_DR_NOTIFICATION_PERSONA_DELETE = "minecraft:dr.notification.persona_delete";

    /**
     * The Character has been saved
     */
    String MC_DR_NOTIFICATION_PERSONA_SAVE = "minecraft:dr.notification.persona_save";

    /**
     * A local or platform skin could not be loaded.
     */
    String MC_DR_NOTIFICATION_SKIN_NOT_FOUND = "minecraft:dr.notification.skin_not_found";

    /**
     * Switching to Character Creator
     */
    String MC_DR_NOTIFICATION_TO_PERSONA = "minecraft:dr.notification.to_persona";

    /**
     * Switching to Classic Skins
     */
    String MC_DR_NOTIFICATION_TO_SKINS = "minecraft:dr.notification.to_skins";

    /**
     * Primary
     */
    String MC_DR_PRIMARY_CHANNEL = "minecraft:dr.primary_channel";

    /**
     * Don't show this again
     */
    String MC_DR_PROMPT_DO_NOT_SHOW_AGAIN = "minecraft:dr.prompt.do_not_show_again";

    /**
     * You are switching over to the character creator in this slot. Character creator allows you to build a character in Minecraft item by item. Your classic skin will be saved as a back up in this slot if you want to switch back later.
     */
    String MC_DR_PROMPT_SWITCHING_TO_PERSONA_DETAIL = "minecraft:dr.prompt.switching_to_persona_detail";

    /**
     * Switch to Character Creator
     */
    String MC_DR_PROMPT_SWITCHING_TO_PERSONA_TITLE = "minecraft:dr.prompt.switching_to_persona_title";

    /**
     * You are switching over to a classic skin in this slot.  Classic skins are applied to the full character and cannot be customized. Your character creator look will be saved as back up in this slot if you want to switch back later.
     */
    String MC_DR_PROMPT_SWITCHING_TO_SKIN_DETAIL = "minecraft:dr.prompt.switching_to_skin_detail";

    /**
     * Switch to Classic Skins
     */
    String MC_DR_PROMPT_SWITCHING_TO_SKIN_TITLE = "minecraft:dr.prompt.switching_to_skin_title";

    /**
     * Rarity
     */
    String MC_DR_RARITY = "minecraft:dr.rarity";

    /**
     * Common
     */
    String MC_DR_RARITY_COMMON = "minecraft:dr.rarity.common";

    /**
     * Epic
     */
    String MC_DR_RARITY_EPIC = "minecraft:dr.rarity.epic";

    /**
     * Legendary
     */
    String MC_DR_RARITY_LEGENDARY = "minecraft:dr.rarity.legendary";

    /**
     * Rare
     */
    String MC_DR_RARITY_RARE = "minecraft:dr.rarity.rare";

    /**
     * Uncommon
     */
    String MC_DR_RARITY_UNCOMMON = "minecraft:dr.rarity.uncommon";

    /**
     * Right Arm
     */
    String MC_DR_RIGHT_ARM = "minecraft:dr.right_arm";

    /**
     * Right Leg
     */
    String MC_DR_RIGHT_LEG = "minecraft:dr.right_leg";

    /**
     * %s
     */
    String MC_DR_RIGHT_SIDE_CATEGORY_TITLE = "minecraft:dr.right_side.category_title";

    /**
     * Sclera
     */
    String MC_DR_SCLERA_COLOR = "minecraft:dr.sclera_color";

    /**
     * Secondary
     */
    String MC_DR_SECONDARY_CHANNEL = "minecraft:dr.secondary_channel";

    /**
     * Base
     */
    String MC_DR_SKIN_COLOR = "minecraft:dr.skin_color";

    /**
     * Zooming
     */
    String MC_DR_ZOOMING = "minecraft:dr.zooming";

    /**
     * Skin Color
     */
    String MC_DRESSINGROOM_SKIN_COLOR_PICKER_TITLE = "minecraft:dressingRoom.skin_color_picker_title";

    /**
     * coming soon
     */
    String MC_EDITORMODE_TEXT = "minecraft:editorMode.text";

    /**
     * Bad Omen
     */
    String MC_EFFECT_BADOMEN = "minecraft:effect.badOmen";

    /**
     * Darkness
     */
    String MC_EFFECT_DARKNESS = "minecraft:effect.darkness";

    /**
     * ∞
     */
    String MC_EFFECT_DURATION_INFINITE = "minecraft:effect.duration.infinite";

    /**
     * Infested
     */
    String MC_EFFECT_INFESTED = "minecraft:effect.infested";

    /**
     * Oozing
     */
    String MC_EFFECT_OOZING = "minecraft:effect.oozing";

    /**
     * Raid Omen
     */
    String MC_EFFECT_RAID_OMEN = "minecraft:effect.raid_omen";

    /**
     * Trial Omen
     */
    String MC_EFFECT_TRIAL_OMEN = "minecraft:effect.trial_omen";

    /**
     * Hero of the Village
     */
    String MC_EFFECT_VILLAGEHERO = "minecraft:effect.villageHero";

    /**
     * Weaving
     */
    String MC_EFFECT_WEAVING = "minecraft:effect.weaving";

    /**
     * Wind Charged
     */
    String MC_EFFECT_WIND_CHARGED = "minecraft:effect.wind_charged";

    /**
     * Press to Select
     */
    String MC_EMOTE_WHEEL_GAMEPAD_HELPER_SELECT = "minecraft:emote_wheel.gamepad_helper.select";

    /**
     * Change Emotes
     */
    String MC_EMOTES_CHANGE_EMOTES = "minecraft:emotes.change_emotes";

    /**
     * Use hot Key or Mouse to activate Emote
     */
    String MC_EMOTES_EMOTES_INSTRUCTIONS_GENERAL = "minecraft:emotes.emotes_instructions_general";

    /**
     * Execute
     */
    String MC_EMOTES_EXECUTE = "minecraft:emotes.execute";

    /**
     * %s Exit
     */
    String MC_EMOTES_EXIT_GAMEPAD = "minecraft:emotes.exit_gamepad";

    /**
     * Emotes
     */
    String MC_EMOTES_HEADER_TEXT = "minecraft:emotes.header_text";

    /**
     * To quickly play your emotes in game, you can always press and hold %s for a quick menu
     */
    String MC_EMOTES_INSTRUCTIONS_GAMEPAD = "minecraft:emotes.instructions_gamepad";

    /**
     * Use Hotkey or Mouse to activate Emote
     */
    String MC_EMOTES_INSTRUCTIONS_KEYBOARD = "minecraft:emotes.instructions_keyboard";

    /**
     * Tap emote to activate
     */
    String MC_EMOTES_INSTRUCTIONS_TOUCH = "minecraft:emotes.instructions_touch";

    /**
     * Power
     */
    String MC_ENCHANTMENT_ARROWDAMAGE = "minecraft:enchantment.arrowDamage";

    /**
     * Flame
     */
    String MC_ENCHANTMENT_ARROWFIRE = "minecraft:enchantment.arrowFire";

    /**
     * Infinity
     */
    String MC_ENCHANTMENT_ARROWINFINITE = "minecraft:enchantment.arrowInfinite";

    /**
     * Punch
     */
    String MC_ENCHANTMENT_ARROWKNOCKBACK = "minecraft:enchantment.arrowKnockback";

    /**
     * Multishot
     */
    String MC_ENCHANTMENT_CROSSBOWMULTISHOT = "minecraft:enchantment.crossbowMultishot";

    /**
     * Piercing
     */
    String MC_ENCHANTMENT_CROSSBOWPIERCING = "minecraft:enchantment.crossbowPiercing";

    /**
     * Quick Charge
     */
    String MC_ENCHANTMENT_CROSSBOWQUICKCHARGE = "minecraft:enchantment.crossbowQuickCharge";

    /**
     * Curse of Binding
     */
    String MC_ENCHANTMENT_CURSE_BINDING = "minecraft:enchantment.curse.binding";

    /**
     * Curse of Vanishing
     */
    String MC_ENCHANTMENT_CURSE_VANISHING = "minecraft:enchantment.curse.vanishing";

    /**
     * Sharpness
     */
    String MC_ENCHANTMENT_DAMAGE_ALL = "minecraft:enchantment.damage.all";

    /**
     * Bane of Arthropods
     */
    String MC_ENCHANTMENT_DAMAGE_ARTHROPODS = "minecraft:enchantment.damage.arthropods";

    /**
     * Smite
     */
    String MC_ENCHANTMENT_DAMAGE_UNDEAD = "minecraft:enchantment.damage.undead";

    /**
     * Efficiency
     */
    String MC_ENCHANTMENT_DIGGING = "minecraft:enchantment.digging";

    /**
     * Unbreaking
     */
    String MC_ENCHANTMENT_DURABILITY = "minecraft:enchantment.durability";

    /**
     * Fire Aspect
     */
    String MC_ENCHANTMENT_FIRE = "minecraft:enchantment.fire";

    /**
     * Lure
     */
    String MC_ENCHANTMENT_FISHINGSPEED = "minecraft:enchantment.fishingSpeed";

    /**
     * Frost Walker
     */
    String MC_ENCHANTMENT_FROSTWALKER = "minecraft:enchantment.frostwalker";

    /**
     * Breach
     */
    String MC_ENCHANTMENT_HEAVY_WEAPON_BREACH = "minecraft:enchantment.heavy_weapon.breach";

    /**
     * Density
     */
    String MC_ENCHANTMENT_HEAVY_WEAPON_DENSITY = "minecraft:enchantment.heavy_weapon.density";

    /**
     * Wind Burst
     */
    String MC_ENCHANTMENT_HEAVY_WEAPON_WINDBURST = "minecraft:enchantment.heavy_weapon.windburst";

    /**
     * Knockback
     */
    String MC_ENCHANTMENT_KNOCKBACK = "minecraft:enchantment.knockback";

    /**
     * I
     */
    String MC_ENCHANTMENT_LEVEL_1 = "minecraft:enchantment.level.1";

    /**
     * X
     */
    String MC_ENCHANTMENT_LEVEL_10 = "minecraft:enchantment.level.10";

    /**
     * II
     */
    String MC_ENCHANTMENT_LEVEL_2 = "minecraft:enchantment.level.2";

    /**
     * III
     */
    String MC_ENCHANTMENT_LEVEL_3 = "minecraft:enchantment.level.3";

    /**
     * IV
     */
    String MC_ENCHANTMENT_LEVEL_4 = "minecraft:enchantment.level.4";

    /**
     * V
     */
    String MC_ENCHANTMENT_LEVEL_5 = "minecraft:enchantment.level.5";

    /**
     * VI
     */
    String MC_ENCHANTMENT_LEVEL_6 = "minecraft:enchantment.level.6";

    /**
     * VII
     */
    String MC_ENCHANTMENT_LEVEL_7 = "minecraft:enchantment.level.7";

    /**
     * VIII
     */
    String MC_ENCHANTMENT_LEVEL_8 = "minecraft:enchantment.level.8";

    /**
     * IX
     */
    String MC_ENCHANTMENT_LEVEL_9 = "minecraft:enchantment.level.9";

    /**
     * Looting
     */
    String MC_ENCHANTMENT_LOOTBONUS = "minecraft:enchantment.lootBonus";

    /**
     * Fortune
     */
    String MC_ENCHANTMENT_LOOTBONUSDIGGER = "minecraft:enchantment.lootBonusDigger";

    /**
     * Luck of the Sea
     */
    String MC_ENCHANTMENT_LOOTBONUSFISHING = "minecraft:enchantment.lootBonusFishing";

    /**
     * Mending
     */
    String MC_ENCHANTMENT_MENDING = "minecraft:enchantment.mending";

    /**
     * Respiration
     */
    String MC_ENCHANTMENT_OXYGEN = "minecraft:enchantment.oxygen";

    /**
     * Protection
     */
    String MC_ENCHANTMENT_PROTECT_ALL = "minecraft:enchantment.protect.all";

    /**
     * Blast Protection
     */
    String MC_ENCHANTMENT_PROTECT_EXPLOSION = "minecraft:enchantment.protect.explosion";

    /**
     * Feather Falling
     */
    String MC_ENCHANTMENT_PROTECT_FALL = "minecraft:enchantment.protect.fall";

    /**
     * Fire Protection
     */
    String MC_ENCHANTMENT_PROTECT_FIRE = "minecraft:enchantment.protect.fire";

    /**
     * Projectile Protection
     */
    String MC_ENCHANTMENT_PROTECT_PROJECTILE = "minecraft:enchantment.protect.projectile";

    /**
     * Soul Speed
     */
    String MC_ENCHANTMENT_SOUL_SPEED = "minecraft:enchantment.soul_speed";

    /**
     * Swift Sneak
     */
    String MC_ENCHANTMENT_SWIFT_SNEAK = "minecraft:enchantment.swift_sneak";

    /**
     * Thorns
     */
    String MC_ENCHANTMENT_THORNS = "minecraft:enchantment.thorns";

    /**
     * Channeling
     */
    String MC_ENCHANTMENT_TRIDENTCHANNELING = "minecraft:enchantment.tridentChanneling";

    /**
     * Impaling
     */
    String MC_ENCHANTMENT_TRIDENTIMPALING = "minecraft:enchantment.tridentImpaling";

    /**
     * Loyalty
     */
    String MC_ENCHANTMENT_TRIDENTLOYALTY = "minecraft:enchantment.tridentLoyalty";

    /**
     * Riptide
     */
    String MC_ENCHANTMENT_TRIDENTRIPTIDE = "minecraft:enchantment.tridentRiptide";

    /**
     * Silk Touch
     */
    String MC_ENCHANTMENT_UNTOUCHING = "minecraft:enchantment.untouching";

    /**
     * Depth Strider
     */
    String MC_ENCHANTMENT_WATERWALKER = "minecraft:enchantment.waterWalker";

    /**
     * Aqua Affinity
     */
    String MC_ENCHANTMENT_WATERWORKER = "minecraft:enchantment.waterWorker";

    /**
     * Allay
     */
    String MC_ENTITY_ALLAY_NAME = "minecraft:entity.allay.name";

    /**
     * Area Effect Cloud
     */
    String MC_ENTITY_AREA_EFFECT_CLOUD_NAME = "minecraft:entity.area_effect_cloud.name";

    /**
     * Armadillo
     */
    String MC_ENTITY_ARMADILLO_NAME = "minecraft:entity.armadillo.name";

    /**
     * Armor Stand
     */
    String MC_ENTITY_ARMOR_STAND_NAME = "minecraft:entity.armor_stand.name";

    /**
     * Arrow
     */
    String MC_ENTITY_ARROW_NAME = "minecraft:entity.arrow.name";

    /**
     * Axolotl
     */
    String MC_ENTITY_AXOLOTL_NAME = "minecraft:entity.axolotl.name";

    /**
     * Bat
     */
    String MC_ENTITY_BAT_NAME = "minecraft:entity.bat.name";

    /**
     * Bee
     */
    String MC_ENTITY_BEE_NAME = "minecraft:entity.bee.name";

    /**
     * Blaze
     */
    String MC_ENTITY_BLAZE_NAME = "minecraft:entity.blaze.name";

    /**
     * Boat
     */
    String MC_ENTITY_BOAT_NAME = "minecraft:entity.boat.name";

    /**
     * Bogged
     */
    String MC_ENTITY_BOGGED_NAME = "minecraft:entity.bogged.name";

    /**
     * Breeze
     */
    String MC_ENTITY_BREEZE_NAME = "minecraft:entity.breeze.name";

    /**
     * Wind Charge
     */
    String MC_ENTITY_BREEZE_WIND_CHARGE_PROJECTILE_NAME = "minecraft:entity.breeze_wind_charge_projectile.name";

    /**
     * Camel
     */
    String MC_ENTITY_CAMEL_NAME = "minecraft:entity.camel.name";

    /**
     * Cat
     */
    String MC_ENTITY_CAT_NAME = "minecraft:entity.cat.name";

    /**
     * Cave Spider
     */
    String MC_ENTITY_CAVE_SPIDER_NAME = "minecraft:entity.cave_spider.name";

    /**
     * Boat with Chest
     */
    String MC_ENTITY_CHEST_BOAT_NAME = "minecraft:entity.chest_boat.name";

    /**
     * Minecart with Chest
     */
    String MC_ENTITY_CHEST_MINECART_NAME = "minecraft:entity.chest_minecart.name";

    /**
     * Raft with Chest
     */
    String MC_ENTITY_CHEST_RAFT_NAME = "minecraft:entity.chest_raft.name";

    /**
     * Chicken
     */
    String MC_ENTITY_CHICKEN_NAME = "minecraft:entity.chicken.name";

    /**
     * Cod
     */
    String MC_ENTITY_COD_NAME = "minecraft:entity.cod.name";

    /**
     * Minecart with Command Block
     */
    String MC_ENTITY_COMMAND_BLOCK_MINECART_NAME = "minecraft:entity.command_block_minecart.name";

    /**
     * Copper Golem
     */
    String MC_ENTITY_COPPER_GOLEM_NAME = "minecraft:entity.copper_golem.name";

    /**
     * Cow
     */
    String MC_ENTITY_COW_NAME = "minecraft:entity.cow.name";

    /**
     * Creaking
     */
    String MC_ENTITY_CREAKING_NAME = "minecraft:entity.creaking.name";

    /**
     * Creeper
     */
    String MC_ENTITY_CREEPER_NAME = "minecraft:entity.creeper.name";

    /**
     * Dolphin
     */
    String MC_ENTITY_DOLPHIN_NAME = "minecraft:entity.dolphin.name";

    /**
     * Donkey
     */
    String MC_ENTITY_DONKEY_NAME = "minecraft:entity.donkey.name";

    /**
     * Dragon Fireball
     */
    String MC_ENTITY_DRAGON_FIREBALL_NAME = "minecraft:entity.dragon_fireball.name";

    /**
     * Drowned
     */
    String MC_ENTITY_DROWNED_NAME = "minecraft:entity.drowned.name";

    /**
     * Egg
     */
    String MC_ENTITY_EGG_NAME = "minecraft:entity.egg.name";

    /**
     * Elder Guardian
     */
    String MC_ENTITY_ELDER_GUARDIAN_NAME = "minecraft:entity.elder_guardian.name";

    /**
     * Ender Crystal
     */
    String MC_ENTITY_ENDER_CRYSTAL_NAME = "minecraft:entity.ender_crystal.name";

    /**
     * Ender Dragon
     */
    String MC_ENTITY_ENDER_DRAGON_NAME = "minecraft:entity.ender_dragon.name";

    /**
     * Ender Pearl
     */
    String MC_ENTITY_ENDER_PEARL_NAME = "minecraft:entity.ender_pearl.name";

    /**
     * Enderman
     */
    String MC_ENTITY_ENDERMAN_NAME = "minecraft:entity.enderman.name";

    /**
     * Endermite
     */
    String MC_ENTITY_ENDERMITE_NAME = "minecraft:entity.endermite.name";

    /**
     * Evoker Fang
     */
    String MC_ENTITY_EVOCATION_FANG_NAME = "minecraft:entity.evocation_fang.name";

    /**
     * Evoker
     */
    String MC_ENTITY_EVOCATION_ILLAGER_NAME = "minecraft:entity.evocation_illager.name";

    /**
     * Eye of Ender
     */
    String MC_ENTITY_EYE_OF_ENDER_SIGNAL_NAME = "minecraft:entity.eye_of_ender_signal.name";

    /**
     * Falling Block
     */
    String MC_ENTITY_FALLING_BLOCK_NAME = "minecraft:entity.falling_block.name";

    /**
     * Fireball
     */
    String MC_ENTITY_FIREBALL_NAME = "minecraft:entity.fireball.name";

    /**
     * Firework Rocket
     */
    String MC_ENTITY_FIREWORKS_ROCKET_NAME = "minecraft:entity.fireworks_rocket.name";

    /**
     * Clownfish
     */
    String MC_ENTITY_FISH_CLOWNFISH_NAME = "minecraft:entity.fish.clownfish.name";

    /**
     * Fishing Hook
     */
    String MC_ENTITY_FISHING_HOOK_NAME = "minecraft:entity.fishing_hook.name";

    /**
     * Fox
     */
    String MC_ENTITY_FOX_NAME = "minecraft:entity.fox.name";

    /**
     * Frog
     */
    String MC_ENTITY_FROG_NAME = "minecraft:entity.frog.name";

    /**
     * Minecart with Furnace
     */
    String MC_ENTITY_FURNACE_MINECART_NAME = "minecraft:entity.furnace_minecart.name";

    /**
     * Ghast
     */
    String MC_ENTITY_GHAST_NAME = "minecraft:entity.ghast.name";

    /**
     * Glow Squid
     */
    String MC_ENTITY_GLOW_SQUID_NAME = "minecraft:entity.glow_squid.name";

    /**
     * Goat
     */
    String MC_ENTITY_GOAT_NAME = "minecraft:entity.goat.name";

    /**
     * Guardian
     */
    String MC_ENTITY_GUARDIAN_NAME = "minecraft:entity.guardian.name";

    /**
     * Happy Ghast
     */
    String MC_ENTITY_HAPPY_GHAST_NAME = "minecraft:entity.happy_ghast.name";

    /**
     * Hoglin
     */
    String MC_ENTITY_HOGLIN_NAME = "minecraft:entity.hoglin.name";

    /**
     * Minecart with Hopper
     */
    String MC_ENTITY_HOPPER_MINECART_NAME = "minecraft:entity.hopper_minecart.name";

    /**
     * Horse
     */
    String MC_ENTITY_HORSE_NAME = "minecraft:entity.horse.name";

    /**
     * Husk
     */
    String MC_ENTITY_HUSK_NAME = "minecraft:entity.husk.name";

    /**
     * Iron Golem
     */
    String MC_ENTITY_IRON_GOLEM_NAME = "minecraft:entity.iron_golem.name";

    /**
     * Item
     */
    String MC_ENTITY_ITEM_NAME = "minecraft:entity.item.name";

    /**
     * Leash Knot
     */
    String MC_ENTITY_LEASH_KNOT_NAME = "minecraft:entity.leash_knot.name";

    /**
     * Lightning Bolt
     */
    String MC_ENTITY_LIGHTNING_BOLT_NAME = "minecraft:entity.lightning_bolt.name";

    /**
     * Lingering Potion
     */
    String MC_ENTITY_LINGERING_POTION_NAME = "minecraft:entity.lingering_potion.name";

    /**
     * Llama
     */
    String MC_ENTITY_LLAMA_NAME = "minecraft:entity.llama.name";

    /**
     * Llama Spit
     */
    String MC_ENTITY_LLAMA_SPIT_NAME = "minecraft:entity.llama_spit.name";

    /**
     * Magma Cube
     */
    String MC_ENTITY_MAGMA_CUBE_NAME = "minecraft:entity.magma_cube.name";

    /**
     * Minecart
     */
    String MC_ENTITY_MINECART_NAME = "minecraft:entity.minecart.name";

    /**
     * Mooshroom
     */
    String MC_ENTITY_MOOSHROOM_NAME = "minecraft:entity.mooshroom.name";

    /**
     * Moving Block
     */
    String MC_ENTITY_MOVING_BLOCK_NAME = "minecraft:entity.moving_block.name";

    /**
     * Mule
     */
    String MC_ENTITY_MULE_NAME = "minecraft:entity.mule.name";

    /**
     * Ocelot
     */
    String MC_ENTITY_OCELOT_NAME = "minecraft:entity.ocelot.name";

    /**
     * Painting
     */
    String MC_ENTITY_PAINTING_NAME = "minecraft:entity.painting.name";

    /**
     * Panda
     */
    String MC_ENTITY_PANDA_NAME = "minecraft:entity.panda.name";

    /**
     * Parrot
     */
    String MC_ENTITY_PARROT_NAME = "minecraft:entity.parrot.name";

    /**
     * Phantom
     */
    String MC_ENTITY_PHANTOM_NAME = "minecraft:entity.phantom.name";

    /**
     * Pig
     */
    String MC_ENTITY_PIG_NAME = "minecraft:entity.pig.name";

    /**
     * Piglin
     */
    String MC_ENTITY_PIGLIN_NAME = "minecraft:entity.piglin.name";

    /**
     * Piglin Brute
     */
    String MC_ENTITY_PIGLIN_BRUTE_NAME = "minecraft:entity.piglin_brute.name";

    /**
     * Pillager
     */
    String MC_ENTITY_PILLAGER_NAME = "minecraft:entity.pillager.name";

    /**
     * Polar Bear
     */
    String MC_ENTITY_POLAR_BEAR_NAME = "minecraft:entity.polar_bear.name";

    /**
     * Pufferfish
     */
    String MC_ENTITY_PUFFERFISH_NAME = "minecraft:entity.pufferfish.name";

    /**
     * Rabbit
     */
    String MC_ENTITY_RABBIT_NAME = "minecraft:entity.rabbit.name";

    /**
     * Ravager
     */
    String MC_ENTITY_RAVAGER_NAME = "minecraft:entity.ravager.name";

    /**
     * Salmon
     */
    String MC_ENTITY_SALMON_NAME = "minecraft:entity.salmon.name";

    /**
     * Sheep
     */
    String MC_ENTITY_SHEEP_NAME = "minecraft:entity.sheep.name";

    /**
     * Shulker
     */
    String MC_ENTITY_SHULKER_NAME = "minecraft:entity.shulker.name";

    /**
     * Shulker Bullet
     */
    String MC_ENTITY_SHULKER_BULLET_NAME = "minecraft:entity.shulker_bullet.name";

    /**
     * Silverfish
     */
    String MC_ENTITY_SILVERFISH_NAME = "minecraft:entity.silverfish.name";

    /**
     * Skeleton
     */
    String MC_ENTITY_SKELETON_NAME = "minecraft:entity.skeleton.name";

    /**
     * Skeleton Horse
     */
    String MC_ENTITY_SKELETON_HORSE_NAME = "minecraft:entity.skeleton_horse.name";

    /**
     * Slime
     */
    String MC_ENTITY_SLIME_NAME = "minecraft:entity.slime.name";

    /**
     * Small Fireball
     */
    String MC_ENTITY_SMALL_FIREBALL_NAME = "minecraft:entity.small_fireball.name";

    /**
     * Sniffer
     */
    String MC_ENTITY_SNIFFER_NAME = "minecraft:entity.sniffer.name";

    /**
     * Snow Golem
     */
    String MC_ENTITY_SNOW_GOLEM_NAME = "minecraft:entity.snow_golem.name";

    /**
     * Snowball
     */
    String MC_ENTITY_SNOWBALL_NAME = "minecraft:entity.snowball.name";

    /**
     * Spider
     */
    String MC_ENTITY_SPIDER_NAME = "minecraft:entity.spider.name";

    /**
     * Potion
     */
    String MC_ENTITY_SPLASH_POTION_NAME = "minecraft:entity.splash_potion.name";

    /**
     * Squid
     */
    String MC_ENTITY_SQUID_NAME = "minecraft:entity.squid.name";

    /**
     * Stray
     */
    String MC_ENTITY_STRAY_NAME = "minecraft:entity.stray.name";

    /**
     * Strider
     */
    String MC_ENTITY_STRIDER_NAME = "minecraft:entity.strider.name";

    /**
     * Tadpole
     */
    String MC_ENTITY_TADPOLE_NAME = "minecraft:entity.tadpole.name";

    /**
     * Trident
     */
    String MC_ENTITY_THROWN_TRIDENT_NAME = "minecraft:entity.thrown_trident.name";

    /**
     * Block of TNT
     */
    String MC_ENTITY_TNT_NAME = "minecraft:entity.tnt.name";

    /**
     * Minecart with TNT
     */
    String MC_ENTITY_TNT_MINECART_NAME = "minecraft:entity.tnt_minecart.name";

    /**
     * Trader Llama
     */
    String MC_ENTITY_TRADER_LLAMA_NAME = "minecraft:entity.trader_llama.name";

    /**
     * Tripod Camera
     */
    String MC_ENTITY_TRIPOD_CAMERA_NAME = "minecraft:entity.tripod_camera.name";

    /**
     * Tropical Fish
     */
    String MC_ENTITY_TROPICALFISH_NAME = "minecraft:entity.tropicalfish.name";

    /**
     * Turtle
     */
    String MC_ENTITY_TURTLE_NAME = "minecraft:entity.turtle.name";

    /**
     * Unknown
     */
    String MC_ENTITY_UNKNOWN_NAME = "minecraft:entity.unknown.name";

    /**
     * Vex
     */
    String MC_ENTITY_VEX_NAME = "minecraft:entity.vex.name";

    /**
     * Armorer
     */
    String MC_ENTITY_VILLAGER_ARMOR = "minecraft:entity.villager.armor";

    /**
     * Butcher
     */
    String MC_ENTITY_VILLAGER_BUTCHER = "minecraft:entity.villager.butcher";

    /**
     * Cartographer
     */
    String MC_ENTITY_VILLAGER_CARTOGRAPHER = "minecraft:entity.villager.cartographer";

    /**
     * Cleric
     */
    String MC_ENTITY_VILLAGER_CLERIC = "minecraft:entity.villager.cleric";

    /**
     * Farmer
     */
    String MC_ENTITY_VILLAGER_FARMER = "minecraft:entity.villager.farmer";

    /**
     * Fisherman
     */
    String MC_ENTITY_VILLAGER_FISHERMAN = "minecraft:entity.villager.fisherman";

    /**
     * Fletcher
     */
    String MC_ENTITY_VILLAGER_FLETCHER = "minecraft:entity.villager.fletcher";

    /**
     * Leatherworker
     */
    String MC_ENTITY_VILLAGER_LEATHER = "minecraft:entity.villager.leather";

    /**
     * Librarian
     */
    String MC_ENTITY_VILLAGER_LIBRARIAN = "minecraft:entity.villager.librarian";

    /**
     * Mason
     */
    String MC_ENTITY_VILLAGER_MASON = "minecraft:entity.villager.mason";

    /**
     * Villager
     */
    String MC_ENTITY_VILLAGER_NAME = "minecraft:entity.villager.name";

    /**
     * Shepherd
     */
    String MC_ENTITY_VILLAGER_SHEPHERD = "minecraft:entity.villager.shepherd";

    /**
     * Tool Smith
     */
    String MC_ENTITY_VILLAGER_TOOL = "minecraft:entity.villager.tool";

    /**
     * Unskilled Villager
     */
    String MC_ENTITY_VILLAGER_UNSKILLED = "minecraft:entity.villager.unskilled";

    /**
     * Weapon Smith
     */
    String MC_ENTITY_VILLAGER_WEAPON = "minecraft:entity.villager.weapon";

    /**
     * Villager
     */
    String MC_ENTITY_VILLAGER_V2_NAME = "minecraft:entity.villager_v2.name";

    /**
     * Vindicator
     */
    String MC_ENTITY_VINDICATOR_NAME = "minecraft:entity.vindicator.name";

    /**
     * Wandering Trader
     */
    String MC_ENTITY_WANDERING_TRADER_NAME = "minecraft:entity.wandering_trader.name";

    /**
     * Warden
     */
    String MC_ENTITY_WARDEN_NAME = "minecraft:entity.warden.name";

    /**
     * Wind Charge
     */
    String MC_ENTITY_WIND_CHARGE_PROJECTILE_NAME = "minecraft:entity.wind_charge_projectile.name";

    /**
     * Witch
     */
    String MC_ENTITY_WITCH_NAME = "minecraft:entity.witch.name";

    /**
     * Wither
     */
    String MC_ENTITY_WITHER_NAME = "minecraft:entity.wither.name";

    /**
     * Wither Skeleton
     */
    String MC_ENTITY_WITHER_SKELETON_NAME = "minecraft:entity.wither_skeleton.name";

    /**
     * Wither Skull
     */
    String MC_ENTITY_WITHER_SKULL_NAME = "minecraft:entity.wither_skull.name";

    /**
     * Wither Skull
     */
    String MC_ENTITY_WITHER_SKULL_DANGEROUS_NAME = "minecraft:entity.wither_skull_dangerous.name";

    /**
     * Wolf
     */
    String MC_ENTITY_WOLF_NAME = "minecraft:entity.wolf.name";

    /**
     * Bottle o' Enchanting
     */
    String MC_ENTITY_XP_BOTTLE_NAME = "minecraft:entity.xp_bottle.name";

    /**
     * Experience Orb
     */
    String MC_ENTITY_XP_ORB_NAME = "minecraft:entity.xp_orb.name";

    /**
     * Zoglin
     */
    String MC_ENTITY_ZOGLIN_NAME = "minecraft:entity.zoglin.name";

    /**
     * Zombie
     */
    String MC_ENTITY_ZOMBIE_NAME = "minecraft:entity.zombie.name";

    /**
     * Zombie Horse
     */
    String MC_ENTITY_ZOMBIE_HORSE_NAME = "minecraft:entity.zombie_horse.name";

    /**
     * Zombified Piglin
     */
    String MC_ENTITY_ZOMBIE_PIGMAN_NAME = "minecraft:entity.zombie_pigman.name";

    /**
     * Zombie Villager
     */
    String MC_ENTITY_ZOMBIE_VILLAGER_NAME = "minecraft:entity.zombie_villager.name";

    /**
     * Zombie Villager
     */
    String MC_ENTITY_ZOMBIE_VILLAGER_V2_NAME = "minecraft:entity.zombie_villager_v2.name";

    /**
     * Add Server
     */
    String MC_EXTERNALSERVERSCREEN_ADDSERVER = "minecraft:externalServerScreen.addServer";

    /**
     * Adding an external server requires knowing the server name and server IP address. Once added, the server can be found in the server list for play.
     */
    String MC_EXTERNALSERVERSCREEN_ADDSERVERDESCRIPTION = "minecraft:externalServerScreen.addServerDescription";

    /**
     * Add Server:
     */
    String MC_EXTERNALSERVERSCREEN_ADDSERVERTITLE = "minecraft:externalServerScreen.addServerTitle";

    /**
     * Add External Server
     */
    String MC_EXTERNALSERVERSCREEN_HEADER = "minecraft:externalServerScreen.header";

    /**
     * Add server by IP/Address.
     */
    String MC_EXTERNALSERVERSCREEN_LABEL = "minecraft:externalServerScreen.label";

    /**
     * IP/Address
     */
    String MC_EXTERNALSERVERSCREEN_SERVERADDRESS = "minecraft:externalServerScreen.serverAddress";

    /**
     * Server IP or Address
     */
    String MC_EXTERNALSERVERSCREEN_SERVERADDRESSINPUT = "minecraft:externalServerScreen.serverAddressInput";

    /**
     * Name
     */
    String MC_EXTERNALSERVERSCREEN_SERVERNAME = "minecraft:externalServerScreen.serverName";

    /**
     * Server Name
     */
    String MC_EXTERNALSERVERSCREEN_SERVERNAMEINPUT = "minecraft:externalServerScreen.serverNameInput";

    /**
     * Port
     */
    String MC_EXTERNALSERVERSCREEN_SERVERPORT = "minecraft:externalServerScreen.serverPort";

    /**
     * Server Port
     */
    String MC_EXTERNALSERVERSCREEN_SERVERPORTINPUT = "minecraft:externalServerScreen.serverPortInput";

    /**
     * Ancient City
     */
    String MC_FEATURE_ANCIENT_CITY = "minecraft:feature.ancient_city";

    /**
     * Bastion Remnant
     */
    String MC_FEATURE_BASTION_REMNANT = "minecraft:feature.bastion_remnant";

    /**
     * Buried Treasure
     */
    String MC_FEATURE_BURIED_TREASURE = "minecraft:feature.buried_treasure";

    /**
     * End City
     */
    String MC_FEATURE_END_CITY = "minecraft:feature.end_city";

    /**
     * Nether Fortress
     */
    String MC_FEATURE_FORTRESS = "minecraft:feature.fortress";

    /**
     * Woodland Mansion
     */
    String MC_FEATURE_MANSION = "minecraft:feature.mansion";

    /**
     * Mineshaft
     */
    String MC_FEATURE_MINESHAFT = "minecraft:feature.mineshaft";

    /**
     * Unknown Feature
     */
    String MC_FEATURE_MISSINGNO = "minecraft:feature.missingno";

    /**
     * Ocean Monument
     */
    String MC_FEATURE_MONUMENT = "minecraft:feature.monument";

    /**
     * Pillager Outpost
     */
    String MC_FEATURE_PILLAGER_OUTPOST = "minecraft:feature.pillager_outpost";

    /**
     * Ruined Portal
     */
    String MC_FEATURE_RUINED_PORTAL = "minecraft:feature.ruined_portal";

    /**
     * Ocean Ruins
     */
    String MC_FEATURE_RUINS = "minecraft:feature.ruins";

    /**
     * Shipwreck
     */
    String MC_FEATURE_SHIPWRECK = "minecraft:feature.shipwreck";

    /**
     * Stronghold
     */
    String MC_FEATURE_STRONGHOLD = "minecraft:feature.stronghold";

    /**
     * Temple
     */
    String MC_FEATURE_TEMPLE = "minecraft:feature.temple";

    /**
     * Trail Ruins
     */
    String MC_FEATURE_TRAIL_RUINS = "minecraft:feature.trail_ruins";

    /**
     * Trial Chambers
     */
    String MC_FEATURE_TRIAL_CHAMBERS = "minecraft:feature.trial_chambers";

    /**
     * Village
     */
    String MC_FEATURE_VILLAGE = "minecraft:feature.village";

    /**
     * Adventure Mode
     */
    String MC_GAMEMODE_ADVENTURE = "minecraft:gameMode.adventure";

    /**
     * Your game mode has been updated to %s
     */
    String MC_GAMEMODE_CHANGED = "minecraft:gameMode.changed";

    /**
     * Creative Mode
     */
    String MC_GAMEMODE_CREATIVE = "minecraft:gameMode.creative";

    /**
     * Hardcore Mode!
     */
    String MC_GAMEMODE_HARDCORE = "minecraft:gameMode.hardcore";

    /**
     * Spectator Mode
     */
    String MC_GAMEMODE_SPECTATOR = "minecraft:gameMode.spectator";

    /**
     * Survival Mode
     */
    String MC_GAMEMODE_SURVIVAL = "minecraft:gameMode.survival";

    /**
     * Could not setBlock '%s'
     */
    String MC_GAMETEST_ASSERT_COULDNOTSETBLOCK = "minecraft:gameTest.assert.couldNotSetBlock";

    /**
     * Could not spawn Entity of type '%s'
     */
    String MC_GAMETEST_ASSERT_COULDNOTSPAWNENTITY = "minecraft:gameTest.assert.couldNotSpawnEntity";

    /**
     * Did not expect entity instance of type '%s'
     */
    String MC_GAMETEST_ASSERT_DIDNOTEXPECTENTITYINSTANCE = "minecraft:gameTest.assert.didNotExpectEntityInstance";

    /**
     * Did not expect Entity of type '%s'
     */
    String MC_GAMETEST_ASSERT_DIDNOTEXPECTENTITYOFTYPE = "minecraft:gameTest.assert.didNotExpectEntityOfType";

    /**
     * Duplicated entity with the same ID as another entity
     */
    String MC_GAMETEST_ASSERT_ERRORDUPLICATEDENTITY = "minecraft:gameTest.assert.errorDuplicatedEntity";

    /**
     * Duplicated entity, entity trying to load with player's ActorUniqueId
     */
    String MC_GAMETEST_ASSERT_ERRORDUPLICATEDENTITYPLAYERUNIQUEID = "minecraft:gameTest.assert.errorDuplicatedEntityPlayerUniqueID";

    /**
     * Attempting to spawn a hostile mob in a peaceful world
     */
    String MC_GAMETEST_ASSERT_ERRORSPAWNHOSTILEINPEACEFULWORLD = "minecraft:gameTest.assert.errorSpawnHostileInPeacefulWorld";

    /**
     * Expected Block of type '%s'
     */
    String MC_GAMETEST_ASSERT_EXPECTBLOCK = "minecraft:gameTest.assert.expectBlock";

    /**
     * Expected block with state '%s'
     */
    String MC_GAMETEST_ASSERT_EXPECTBLOCKWITHSTATE = "minecraft:gameTest.assert.expectBlockWithState";

    /**
     * Expected block of type Cauldron
     */
    String MC_GAMETEST_ASSERT_EXPECTCAULDRON = "minecraft:gameTest.assert.expectCauldron";

    /**
     * Expected empty container
     */
    String MC_GAMETEST_ASSERT_EXPECTEMPTYCONTAINER = "minecraft:gameTest.assert.expectEmptyContainer";

    /**
     * Expected entity instance of type '%s'
     */
    String MC_GAMETEST_ASSERT_EXPECTENTITYINSTANCE = "minecraft:gameTest.assert.expectEntityInstance";

    /**
     * Expected Entity of type '%s'
     */
    String MC_GAMETEST_ASSERT_EXPECTENTITYOFTYPE = "minecraft:gameTest.assert.expectEntityOfType";

    /**
     * Container does not contain item of type %s
     */
    String MC_GAMETEST_ASSERT_EXPECTITEMINCONTAINER = "minecraft:gameTest.assert.expectItemInContainer";

    /**
     * Expected Item Entity of type %s
     */
    String MC_GAMETEST_ASSERT_EXPECTITEMOFTYPE = "minecraft:gameTest.assert.expectItemOfType";

    /**
     * Expected waterlogged block
     */
    String MC_GAMETEST_ASSERT_EXPECTWATERLOGGED = "minecraft:gameTest.assert.expectWaterLogged";

    /**
     * Entity has armor %s in slot %s with data %s
     */
    String MC_GAMETEST_ASSERT_HASARMOR = "minecraft:gameTest.assert.hasArmor";

    /**
     * Entity has component '%s'
     */
    String MC_GAMETEST_ASSERT_HASCOMPONENT = "minecraft:gameTest.assert.hasComponent";

    /**
     * Invalid entity instance
     */
    String MC_GAMETEST_ASSERT_INVALIDENTITYINSTANCE = "minecraft:gameTest.assert.invalidEntityInstance";

    /**
     * Invalid fluid type
     */
    String MC_GAMETEST_ASSERT_INVALIDFLUIDTYPE = "minecraft:gameTest.assert.invalidFluidType";

    /**
     * Entity missing armor %s in slot %s with data %s
     */
    String MC_GAMETEST_ASSERT_MISSINGARMOR = "minecraft:gameTest.assert.missingArmor";

    /**
     * Expected a button
     */
    String MC_GAMETEST_ASSERT_MISSINGBUTTON = "minecraft:gameTest.assert.missingButton";

    /**
     * Entity missing component '%s'
     */
    String MC_GAMETEST_ASSERT_MISSINGCOMPONENT = "minecraft:gameTest.assert.missingComponent";

    /**
     * Can not set fuse of entity because it does not have an explosion component
     */
    String MC_GAMETEST_ASSERT_MISSINGEXPLOSIONCOMPONENT = "minecraft:gameTest.assert.missingExplosionComponent";

    /**
     * Expected a lever
     */
    String MC_GAMETEST_ASSERT_MISSINGLEVER = "minecraft:gameTest.assert.missingLever";

    /**
     * Could not make the mob move '%s'
     */
    String MC_GAMETEST_ASSERT_MOBWONTMOVE = "minecraft:gameTest.assert.mobWontMove";

    /**
     * No container found
     */
    String MC_GAMETEST_ASSERT_NOCONTAINERFOUND = "minecraft:gameTest.assert.noContainerFound";

    /**
     * No path found for Entity of type '%s'
     */
    String MC_GAMETEST_ASSERT_NOPATHFOUND = "minecraft:gameTest.assert.noPathFound";

    /**
     * spawnWithoutBehaviors() only works with mobs
     */
    String MC_GAMETEST_ASSERT_ONLYWORKSWITHMOBS = "minecraft:gameTest.assert.onlyWorksWithMobs";

    /**
     * Path found for Entity of type '%s'
     */
    String MC_GAMETEST_ASSERT_PATHFOUND = "minecraft:gameTest.assert.pathFound";

    /**
     * Did not expect Block of type '%s'
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDBLOCK = "minecraft:gameTest.assert.unexpectedBlock";

    /**
     * Block does not have the expected Block State
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDBLOCKSTATE = "minecraft:gameTest.assert.unexpectedBlockState";

    /**
     * Unexpected Entity data result
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDDATARESULT = "minecraft:gameTest.assert.unexpectedDataResult";

    /**
     * Did not expect Item Entity of type %s
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDITEMOFTYPE = "minecraft:gameTest.assert.unexpectedItemOfType";

    /**
     * Unexpected value for parameter '%s'
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDPARAMETERVALUE = "minecraft:gameTest.assert.unexpectedParameterValue";

    /**
     * Unexpected Redstone power level. Expected: %s. Actual: %s.
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDREDSTONEPOWERLEVEL = "minecraft:gameTest.assert.unexpectedRedstonePowerLevel";

    /**
     * Did not expect waterlogged block
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDWATERLOGGED = "minecraft:gameTest.assert.unexpectedWaterLogged";

    /**
     * Unexpected Item Entity count of type %s. Expected: %s. Actual: %s.
     */
    String MC_GAMETEST_ASSERT_WRONGENTITYITEMCOUNT = "minecraft:gameTest.assert.wrongEntityItemCount";

    /**
     * Interacts with gametest.
     */
    String MC_GAMETEST_DESCRIPTION = "minecraft:gameTest.description";

    /**
     * Can't find the block you're looking at
     */
    String MC_GAMETEST_ERROR_CANNOTFINDLOOKEDATBLOCK = "minecraft:gameTest.error.cannotFindLookedAtBlock";

    /**
     * Can't find a structure block that contains the targeted pos %s, %s, %s
     */
    String MC_GAMETEST_ERROR_CANNOTFINDSTUCTUREBLOCKCONTAININGBLOCK = "minecraft:gameTest.error.cannotFindStuctureBlockContainingBlock";

    /**
     * Fail conditions met
     */
    String MC_GAMETEST_ERROR_FAILCONDITIONMET = "minecraft:gameTest.error.failConditionMet";

    /**
     * %s failed!
     */
    String MC_GAMETEST_ERROR_FAILED = "minecraft:gameTest.error.failed";

    /**
     * Flaky test %s failed, attempt: %s / %s
     */
    String MC_GAMETEST_ERROR_FLAKYFAILED = "minecraft:gameTest.error.flakyFailed";

    /**
     * , successes: %s (%s required)           
     */
    String MC_GAMETEST_ERROR_FLAKYFAILEDSHOWSUCCESSES = "minecraft:gameTest.error.flakyFailedShowSuccesses";

    /**
     * Not enough successes: %s out of %s attempts. Required successes: %s. Max attempts: %s.
     */
    String MC_GAMETEST_ERROR_FLAKYTESTERROR = "minecraft:gameTest.error.flakyTestError";

    /**
     * Couldn't find any structure block within %s radius
     */
    String MC_GAMETEST_ERROR_MISSINGSTRUCTURE = "minecraft:gameTest.error.missingStructure";

    /**
     * No sequences finished
     */
    String MC_GAMETEST_ERROR_NOFINISHEDSEQUENCES = "minecraft:gameTest.error.noFinishedSequences";

    /**
     * No tests found for tag '%s'
     */
    String MC_GAMETEST_ERROR_NOTESTSFOUND = "minecraft:gameTest.error.noTestsFound";

    /**
     * Repeat count must be greater than 0
     */
    String MC_GAMETEST_ERROR_REPEATCOUNTTOOSMALL = "minecraft:gameTest.error.repeatCountTooSmall";

    /**
     * Failed to spawn test structure with path '%s' 
     */
    String MC_GAMETEST_ERROR_STRUCTURENOTSPAWNED = "minecraft:gameTest.error.structureNotSpawned";

    /**
     * The structure must be less than %s blocks big in each axis
     */
    String MC_GAMETEST_ERROR_STRUCTURETOOBIG = "minecraft:gameTest.error.structureTooBig";

    /**
     * Succeeded in invalid tick: expected %s, but current tick is %s
     */
    String MC_GAMETEST_ERROR_SUCCEEDEDININVALIDTICK = "minecraft:gameTest.error.succeededInInvalidTick";

    /**
     * Could not find test with name '%s'.
     */
    String MC_GAMETEST_ERROR_TESTMISSING = "minecraft:gameTest.error.testMissing";

    /**
     * Didn't succeed or fail within %s ticks
     */
    String MC_GAMETEST_ERROR_TIMEOUT = "minecraft:gameTest.error.timeout";

    /**
     * Unknown command mode provided
     */
    String MC_GAMETEST_ERROR_UNKNOWNCOMMANDMODE = "minecraft:gameTest.error.unknownCommandMode";

    /**
     * Unknown event name '%s'
     */
    String MC_GAMETEST_ERROR_UNKOWNEVENT = "minecraft:gameTest.error.unkownEvent";

    /**
     * (optional)
     */
    String MC_GAMETEST_OPTIONALPREFIX = "minecraft:gameTest.optionalPrefix";

    /**
     * All required tests passed :)
     */
    String MC_GAMETEST_REPORT_ALLREQUIREDTESTSPASSED = "minecraft:gameTest.report.allRequiredTestsPassed";

    /**
     * Game Test Report                                      
     */
    String MC_GAMETEST_REPORT_BOOKTITLE = "minecraft:gameTest.report.bookTitle";

    /**
     * %s optional tests failed
     */
    String MC_GAMETEST_REPORT_OPTIONALFAILURES = "minecraft:gameTest.report.optionalFailures";

    /**
     * %s required tests failed :(
     */
    String MC_GAMETEST_REPORT_SOMETESTSFAILED = "minecraft:gameTest.report.someTestsFailed";

    /**
     * GameTest done! %s tests were run
     */
    String MC_GAMETEST_REPORT_TESTCOUNT = "minecraft:gameTest.report.testCount";

    /**
     * At: %s, %s, %s (relative: %s, %s, %s)
     */
    String MC_GAMETEST_REPORT_TESTLOCATION = "minecraft:gameTest.report.testLocation";

    /**
     * Tick: %s                                              
     */
    String MC_GAMETEST_REPORT_TICKCOUNT = "minecraft:gameTest.report.tickCount";

    /**
     * Flaky Test %s succeeded, attempt: %s successes: %s.
     */
    String MC_GAMETEST_SUCCESS_FLAKYPASSED = "minecraft:gameTest.success.flakyPassed";

    /**
     * %s passed %s times of %s attempts.           
     */
    String MC_GAMETEST_SUCCESS_FLAKYPASSEDENOUGH = "minecraft:gameTest.success.flakyPassedEnough";

    /**
     * %s passed!
     */
    String MC_GAMETEST_SUCCESS_PASSED = "minecraft:gameTest.success.passed";

    /**
     * Position relative to %s: %s
     */
    String MC_GAMETEST_SUCCESS_RELATIVEPOSITION = "minecraft:gameTest.success.relativePosition";

    /**
     * Running %s tests...
     */
    String MC_GAMETEST_SUCCESS_RUNNINGTESTS = "minecraft:gameTest.success.runningTests";

    /**
     * Running %s tests with tag '%s'...
     */
    String MC_GAMETEST_SUCCESS_TESTSSTARTED = "minecraft:gameTest.success.testsStarted";

    /**
     * Your game is missing a required component. To resolve this, choose Install to initiate installation and restart the game.
     */
    String MC_GAMEINPUT_LOAD_ERROR_MESSAGE = "minecraft:gameinput_load.error.message";

    /**
     * Your game is missing a required component. To resolve this, choose Ok to initiate installation and restart the game.
     */
    String MC_GAMEINPUT_LOAD_ERROR_MESSAGE2 = "minecraft:gameinput_load.error.message2";

    /**
     * Your game is missing a required component. To resolve this, run GameInputRedist.msi (on your local installation) and restart the game.
     */
    String MC_GAMEINPUT_LOAD_ERROR_MESSAGE3 = "minecraft:gameinput_load.error.message3";

    /**
     * Error
     */
    String MC_GAMEINPUT_LOAD_ERROR_TITLE = "minecraft:gameinput_load.error.title";

    /**
     * Install
     */
    String MC_GAMEINPUT_LOAD_INSTALLBUTTON = "minecraft:gameinput_load.installButton";

    /**
     * Page %1 of %2
     */
    String MC_IMMERSIVE_READER_BOOK_PAGE_HEADER = "minecraft:immersive_reader.book_page_header";

    /**
     * There was a problem connecting to Immersive Reader. Please restart Minecraft Education and try again.
     */
    String MC_IMMERSIVE_READER_ERROR_IDENTITY_FAILURE = "minecraft:immersive_reader.error.identity_failure";

    /**
     * There was a problem connecting to Immersive Reader.
     */
    String MC_IMMERSIVE_READER_ERROR_WEBVIEW_FAILURE = "minecraft:immersive_reader.error.webview_failure";

    /**
     *  Page %1
     */
    String MC_IMMERSIVE_READER_PORTFOLIO_PAGE_HEADER = "minecraft:immersive_reader.portfolio_page_header";

    /**
     * Game Invite from %s
     */
    String MC_INBOX_GAMEINVITE_TITLE = "minecraft:inbox.gameInvite.title";

    /**
     * Realm invite to %s
     */
    String MC_INBOX_REALMINVITE_TITLE = "minecraft:inbox.realmInvite.title";

    /**
     * Destroy Item
     */
    String MC_INVENTORY_BINSLOT = "minecraft:inventory.binSlot";

    /**
     * Offline Friends
     */
    String MC_INVITE_OFFLINEFRIENDS = "minecraft:invite.OfflineFriends";

    /**
     * Online Friends
     */
    String MC_INVITE_ONLINEFRIENDS = "minecraft:invite.OnlineFriends";

    /**
     * Suggested Friends
     */
    String MC_INVITE_SUGGESTEDFRIENDS = "minecraft:invite.SuggestedFriends";

    /**
     * Clear Selection
     */
    String MC_INVITE_CLEAR = "minecraft:invite.clear";

    /**
     * %s has invited you to play Minecraft
     */
    String MC_INVITE_CONFIRMATION_DESCRIPTION = "minecraft:invite.confirmation.description";

    /**
     * Some of your invites may have failed to send.
     */
    String MC_INVITE_ERROR_MESSAGE = "minecraft:invite.error.message";

    /**
     * Something went wrong. We couldn't load your friends list.
     */
    String MC_INVITE_ERROR1 = "minecraft:invite.error1";

    /**
     * Something went wrong. We couldn't load some of your friends details.
     */
    String MC_INVITE_ERROR2 = "minecraft:invite.error2";

    /**
     * You haven't added anyone to your friend's list!
     */
    String MC_INVITE_NOFRIENDS = "minecraft:invite.noFriends";

    /**
     * You haven't added anyone to your Realm!
     */
    String MC_INVITE_NOREALMFRIENDS = "minecraft:invite.noRealmFriends";

    /**
     * Press %s to accept
     */
    String MC_INVITE_NOTIFICATION_ACCEPTINVITETTSGAMEPAD = "minecraft:invite.notification.acceptInviteTtsGamepad";

    /**
     * Double tap the invite at the top of the screen to accept
     */
    String MC_INVITE_NOTIFICATION_ACCEPTINVITETTSTOUCH = "minecraft:invite.notification.acceptInviteTtsTouch";

    /**
     * %s has invited you to play Minecraft
     */
    String MC_INVITE_NOTIFICATION_DESCRIPTION = "minecraft:invite.notification.description";

    /**
     * Game Invite
     */
    String MC_INVITE_NOTIFICATION_TITLE = "minecraft:invite.notification.title";

    /**
     * %d/%d
     */
    String MC_INVITE_PAGECOUNTER = "minecraft:invite.pageCounter";

    /**
     * Add Realm Members
     */
    String MC_INVITE_REALM_ADD_MEMBER = "minecraft:invite.realm.add_member";

    /**
     * Send %d Invites
     */
    String MC_INVITE_SEND = "minecraft:invite.send";

    /**
     * Send 1 Invite
     */
    String MC_INVITE_SENDONE = "minecraft:invite.sendOne";

    /**
     * Send Invites
     */
    String MC_INVITE_SENDUNNUMBERED = "minecraft:invite.sendUnnumbered";

    /**
     * Invite Friends to your Game
     */
    String MC_INVITE_TITLE = "minecraft:invite.title";

    /**
     * Invite Members to your Realm Server
     */
    String MC_INVITE_TITLEREALMS = "minecraft:invite.titleRealms";

    /**
     * Acacia Door
     */
    String MC_ITEM_ACACIA_DOOR_NAME = "minecraft:item.acacia_door.name";

    /**
     * Acacia Hanging Sign
     */
    String MC_ITEM_ACACIA_HANGING_SIGN_NAME = "minecraft:item.acacia_hanging_sign.name";

    /**
     * Acacia Sign
     */
    String MC_ITEM_ACACIA_SIGN_NAME = "minecraft:item.acacia_sign.name";

    /**
     * Air
     */
    String MC_ITEM_AIR_NAME = "minecraft:item.air.name";

    /**
     * Amethyst Shard
     */
    String MC_ITEM_AMETHYST_SHARD_NAME = "minecraft:item.amethyst_shard.name";

    /**
     * Angler Pottery Sherd
     */
    String MC_ITEM_ANGLER_POTTERY_SHERD_NAME = "minecraft:item.angler_pottery_sherd.name";

    /**
     * Apple
     */
    String MC_ITEM_APPLE_NAME = "minecraft:item.apple.name";

    /**
     * Enchanted Golden Apple
     */
    String MC_ITEM_APPLEENCHANTED_NAME = "minecraft:item.appleEnchanted.name";

    /**
     * Archer Pottery Sherd
     */
    String MC_ITEM_ARCHER_POTTERY_SHERD_NAME = "minecraft:item.archer_pottery_sherd.name";

    /**
     * Armadillo Scute
     */
    String MC_ITEM_ARMADILLO_SCUTE_NAME = "minecraft:item.armadillo_scute.name";

    /**
     * Upgrade:
     */
    String MC_ITEM_ARMOR_UPGRADE = "minecraft:item.armor.upgrade";

    /**
     * Armor Stand
     */
    String MC_ITEM_ARMOR_STAND_NAME = "minecraft:item.armor_stand.name";

    /**
     * Arms Up Pottery Sherd
     */
    String MC_ITEM_ARMS_UP_POTTERY_SHERD_NAME = "minecraft:item.arms_up_pottery_sherd.name";

    /**
     * Arrow
     */
    String MC_ITEM_ARROW_NAME = "minecraft:item.arrow.name";

    /**
     * Adult %1#s Axolotl
     */
    String MC_ITEM_AXOLOTLADULTBODYSINGLE_NAME = "minecraft:item.axolotlAdultBodySingle.name";

    /**
     * Baby %1#s Axolotl
     */
    String MC_ITEM_AXOLOTLBABYBODYSINGLE_NAME = "minecraft:item.axolotlBabyBodySingle.name";

    /**
     * Blue
     */
    String MC_ITEM_AXOLOTLCOLORBLUE_NAME = "minecraft:item.axolotlColorBlue.name";

    /**
     * Cyan
     */
    String MC_ITEM_AXOLOTLCOLORCYAN_NAME = "minecraft:item.axolotlColorCyan.name";

    /**
     * Gold
     */
    String MC_ITEM_AXOLOTLCOLORGOLD_NAME = "minecraft:item.axolotlColorGold.name";

    /**
     * Leucistic
     */
    String MC_ITEM_AXOLOTLCOLORLUCY_NAME = "minecraft:item.axolotlColorLucy.name";

    /**
     * Brown
     */
    String MC_ITEM_AXOLOTLCOLORWILD_NAME = "minecraft:item.axolotlColorWild.name";

    /**
     * Baked Potato
     */
    String MC_ITEM_BAKED_POTATO_NAME = "minecraft:item.baked_potato.name";

    /**
     * Bamboo Door
     */
    String MC_ITEM_BAMBOO_DOOR_NAME = "minecraft:item.bamboo_door.name";

    /**
     * Bamboo Hanging Sign
     */
    String MC_ITEM_BAMBOO_HANGING_SIGN_NAME = "minecraft:item.bamboo_hanging_sign.name";

    /**
     * Bamboo Sign
     */
    String MC_ITEM_BAMBOO_SIGN_NAME = "minecraft:item.bamboo_sign.name";

    /**
     * Black Banner
     */
    String MC_ITEM_BANNER_BLACK_NAME = "minecraft:item.banner.black.name";

    /**
     * Blue Banner
     */
    String MC_ITEM_BANNER_BLUE_NAME = "minecraft:item.banner.blue.name";

    /**
     * Black Bordure
     */
    String MC_ITEM_BANNER_BORDER_BLACK = "minecraft:item.banner.border.black";

    /**
     * Blue Bordure
     */
    String MC_ITEM_BANNER_BORDER_BLUE = "minecraft:item.banner.border.blue";

    /**
     * Brown Bordure
     */
    String MC_ITEM_BANNER_BORDER_BROWN = "minecraft:item.banner.border.brown";

    /**
     * Cyan Bordure
     */
    String MC_ITEM_BANNER_BORDER_CYAN = "minecraft:item.banner.border.cyan";

    /**
     * Gray Bordure
     */
    String MC_ITEM_BANNER_BORDER_GRAY = "minecraft:item.banner.border.gray";

    /**
     * Green Bordure
     */
    String MC_ITEM_BANNER_BORDER_GREEN = "minecraft:item.banner.border.green";

    /**
     * Light Blue Bordure
     */
    String MC_ITEM_BANNER_BORDER_LIGHTBLUE = "minecraft:item.banner.border.lightBlue";

    /**
     * Lime Bordure
     */
    String MC_ITEM_BANNER_BORDER_LIME = "minecraft:item.banner.border.lime";

    /**
     * Magenta Bordure
     */
    String MC_ITEM_BANNER_BORDER_MAGENTA = "minecraft:item.banner.border.magenta";

    /**
     * Orange Bordure
     */
    String MC_ITEM_BANNER_BORDER_ORANGE = "minecraft:item.banner.border.orange";

    /**
     * Pink Bordure
     */
    String MC_ITEM_BANNER_BORDER_PINK = "minecraft:item.banner.border.pink";

    /**
     * Purple Bordure
     */
    String MC_ITEM_BANNER_BORDER_PURPLE = "minecraft:item.banner.border.purple";

    /**
     * Red Bordure
     */
    String MC_ITEM_BANNER_BORDER_RED = "minecraft:item.banner.border.red";

    /**
     * Light Gray Bordure
     */
    String MC_ITEM_BANNER_BORDER_SILVER = "minecraft:item.banner.border.silver";

    /**
     * White Bordure
     */
    String MC_ITEM_BANNER_BORDER_WHITE = "minecraft:item.banner.border.white";

    /**
     * Yellow Bordure
     */
    String MC_ITEM_BANNER_BORDER_YELLOW = "minecraft:item.banner.border.yellow";

    /**
     * Black Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_BLACK = "minecraft:item.banner.bricks.black";

    /**
     * Blue Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_BLUE = "minecraft:item.banner.bricks.blue";

    /**
     * Brown Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_BROWN = "minecraft:item.banner.bricks.brown";

    /**
     * Cyan Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_CYAN = "minecraft:item.banner.bricks.cyan";

    /**
     * Gray Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_GRAY = "minecraft:item.banner.bricks.gray";

    /**
     * Green Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_GREEN = "minecraft:item.banner.bricks.green";

    /**
     * Light Blue Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_LIGHTBLUE = "minecraft:item.banner.bricks.lightBlue";

    /**
     * Lime Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_LIME = "minecraft:item.banner.bricks.lime";

    /**
     * Magenta Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_MAGENTA = "minecraft:item.banner.bricks.magenta";

    /**
     * Orange Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_ORANGE = "minecraft:item.banner.bricks.orange";

    /**
     * Pink Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_PINK = "minecraft:item.banner.bricks.pink";

    /**
     * Purple Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_PURPLE = "minecraft:item.banner.bricks.purple";

    /**
     * Red Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_RED = "minecraft:item.banner.bricks.red";

    /**
     * Light Gray Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_SILVER = "minecraft:item.banner.bricks.silver";

    /**
     * White Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_WHITE = "minecraft:item.banner.bricks.white";

    /**
     * Yellow Field Masoned
     */
    String MC_ITEM_BANNER_BRICKS_YELLOW = "minecraft:item.banner.bricks.yellow";

    /**
     * Brown Banner
     */
    String MC_ITEM_BANNER_BROWN_NAME = "minecraft:item.banner.brown.name";

    /**
     * Black Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_BLACK = "minecraft:item.banner.circle.black";

    /**
     * Blue Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_BLUE = "minecraft:item.banner.circle.blue";

    /**
     * Brown Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_BROWN = "minecraft:item.banner.circle.brown";

    /**
     * Cyan Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_CYAN = "minecraft:item.banner.circle.cyan";

    /**
     * Gray Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_GRAY = "minecraft:item.banner.circle.gray";

    /**
     * Green Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_GREEN = "minecraft:item.banner.circle.green";

    /**
     * Light Blue Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_LIGHTBLUE = "minecraft:item.banner.circle.lightBlue";

    /**
     * Lime Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_LIME = "minecraft:item.banner.circle.lime";

    /**
     * Magenta Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_MAGENTA = "minecraft:item.banner.circle.magenta";

    /**
     * Orange Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_ORANGE = "minecraft:item.banner.circle.orange";

    /**
     * Pink Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_PINK = "minecraft:item.banner.circle.pink";

    /**
     * Purple Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_PURPLE = "minecraft:item.banner.circle.purple";

    /**
     * Red Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_RED = "minecraft:item.banner.circle.red";

    /**
     * Light Gray Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_SILVER = "minecraft:item.banner.circle.silver";

    /**
     * White Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_WHITE = "minecraft:item.banner.circle.white";

    /**
     * Yellow Roundel
     */
    String MC_ITEM_BANNER_CIRCLE_YELLOW = "minecraft:item.banner.circle.yellow";

    /**
     * Black Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_BLACK = "minecraft:item.banner.creeper.black";

    /**
     * Blue Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_BLUE = "minecraft:item.banner.creeper.blue";

    /**
     * Brown Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_BROWN = "minecraft:item.banner.creeper.brown";

    /**
     * Cyan Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_CYAN = "minecraft:item.banner.creeper.cyan";

    /**
     * Gray Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_GRAY = "minecraft:item.banner.creeper.gray";

    /**
     * Green Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_GREEN = "minecraft:item.banner.creeper.green";

    /**
     * Light Blue Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_LIGHTBLUE = "minecraft:item.banner.creeper.lightBlue";

    /**
     * Lime Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_LIME = "minecraft:item.banner.creeper.lime";

    /**
     * Magenta Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_MAGENTA = "minecraft:item.banner.creeper.magenta";

    /**
     * Orange Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_ORANGE = "minecraft:item.banner.creeper.orange";

    /**
     * Pink Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_PINK = "minecraft:item.banner.creeper.pink";

    /**
     * Purple Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_PURPLE = "minecraft:item.banner.creeper.purple";

    /**
     * Red Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_RED = "minecraft:item.banner.creeper.red";

    /**
     * Light Gray Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_SILVER = "minecraft:item.banner.creeper.silver";

    /**
     * White Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_WHITE = "minecraft:item.banner.creeper.white";

    /**
     * Yellow Creeper Charge
     */
    String MC_ITEM_BANNER_CREEPER_YELLOW = "minecraft:item.banner.creeper.yellow";

    /**
     * Black Saltire
     */
    String MC_ITEM_BANNER_CROSS_BLACK = "minecraft:item.banner.cross.black";

    /**
     * Blue Saltire
     */
    String MC_ITEM_BANNER_CROSS_BLUE = "minecraft:item.banner.cross.blue";

    /**
     * Brown Saltire
     */
    String MC_ITEM_BANNER_CROSS_BROWN = "minecraft:item.banner.cross.brown";

    /**
     * Cyan Saltire
     */
    String MC_ITEM_BANNER_CROSS_CYAN = "minecraft:item.banner.cross.cyan";

    /**
     * Gray Saltire
     */
    String MC_ITEM_BANNER_CROSS_GRAY = "minecraft:item.banner.cross.gray";

    /**
     * Green Saltire
     */
    String MC_ITEM_BANNER_CROSS_GREEN = "minecraft:item.banner.cross.green";

    /**
     * Light Blue Saltire
     */
    String MC_ITEM_BANNER_CROSS_LIGHTBLUE = "minecraft:item.banner.cross.lightBlue";

    /**
     * Lime Saltire
     */
    String MC_ITEM_BANNER_CROSS_LIME = "minecraft:item.banner.cross.lime";

    /**
     * Magenta Saltire
     */
    String MC_ITEM_BANNER_CROSS_MAGENTA = "minecraft:item.banner.cross.magenta";

    /**
     * Orange Saltire
     */
    String MC_ITEM_BANNER_CROSS_ORANGE = "minecraft:item.banner.cross.orange";

    /**
     * Pink Saltire
     */
    String MC_ITEM_BANNER_CROSS_PINK = "minecraft:item.banner.cross.pink";

    /**
     * Purple Saltire
     */
    String MC_ITEM_BANNER_CROSS_PURPLE = "minecraft:item.banner.cross.purple";

    /**
     * Red Saltire
     */
    String MC_ITEM_BANNER_CROSS_RED = "minecraft:item.banner.cross.red";

    /**
     * Light Gray Saltire
     */
    String MC_ITEM_BANNER_CROSS_SILVER = "minecraft:item.banner.cross.silver";

    /**
     * White Saltire
     */
    String MC_ITEM_BANNER_CROSS_WHITE = "minecraft:item.banner.cross.white";

    /**
     * Yellow Saltire
     */
    String MC_ITEM_BANNER_CROSS_YELLOW = "minecraft:item.banner.cross.yellow";

    /**
     * Black Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_BLACK = "minecraft:item.banner.curly_border.black";

    /**
     * Blue Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_BLUE = "minecraft:item.banner.curly_border.blue";

    /**
     * Brown Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_BROWN = "minecraft:item.banner.curly_border.brown";

    /**
     * Cyan Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_CYAN = "minecraft:item.banner.curly_border.cyan";

    /**
     * Gray Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_GRAY = "minecraft:item.banner.curly_border.gray";

    /**
     * Green Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_GREEN = "minecraft:item.banner.curly_border.green";

    /**
     * Light Blue Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_LIGHTBLUE = "minecraft:item.banner.curly_border.lightBlue";

    /**
     * Lime Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_LIME = "minecraft:item.banner.curly_border.lime";

    /**
     * Magenta Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_MAGENTA = "minecraft:item.banner.curly_border.magenta";

    /**
     * Orange Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_ORANGE = "minecraft:item.banner.curly_border.orange";

    /**
     * Pink Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_PINK = "minecraft:item.banner.curly_border.pink";

    /**
     * Purple Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_PURPLE = "minecraft:item.banner.curly_border.purple";

    /**
     * Red Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_RED = "minecraft:item.banner.curly_border.red";

    /**
     * Light Gray Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_SILVER = "minecraft:item.banner.curly_border.silver";

    /**
     * White Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_WHITE = "minecraft:item.banner.curly_border.white";

    /**
     * Yellow Bordure Indented
     */
    String MC_ITEM_BANNER_CURLY_BORDER_YELLOW = "minecraft:item.banner.curly_border.yellow";

    /**
     * Cyan Banner
     */
    String MC_ITEM_BANNER_CYAN_NAME = "minecraft:item.banner.cyan.name";

    /**
     * Black Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_BLACK = "minecraft:item.banner.diagonal_left.black";

    /**
     * Blue Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_BLUE = "minecraft:item.banner.diagonal_left.blue";

    /**
     * Brown Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_BROWN = "minecraft:item.banner.diagonal_left.brown";

    /**
     * Cyan Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_CYAN = "minecraft:item.banner.diagonal_left.cyan";

    /**
     * Gray Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_GRAY = "minecraft:item.banner.diagonal_left.gray";

    /**
     * Green Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_GREEN = "minecraft:item.banner.diagonal_left.green";

    /**
     * Light Blue Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_LIGHTBLUE = "minecraft:item.banner.diagonal_left.lightBlue";

    /**
     * Lime Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_LIME = "minecraft:item.banner.diagonal_left.lime";

    /**
     * Magenta Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_MAGENTA = "minecraft:item.banner.diagonal_left.magenta";

    /**
     * Orange Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_ORANGE = "minecraft:item.banner.diagonal_left.orange";

    /**
     * Pink Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_PINK = "minecraft:item.banner.diagonal_left.pink";

    /**
     * Purple Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_PURPLE = "minecraft:item.banner.diagonal_left.purple";

    /**
     * Red Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_RED = "minecraft:item.banner.diagonal_left.red";

    /**
     * Light Gray Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_SILVER = "minecraft:item.banner.diagonal_left.silver";

    /**
     * White Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_WHITE = "minecraft:item.banner.diagonal_left.white";

    /**
     * Yellow Per Bend Sinister
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_YELLOW = "minecraft:item.banner.diagonal_left.yellow";

    /**
     * Black Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_BLACK = "minecraft:item.banner.diagonal_right.black";

    /**
     * Blue Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_BLUE = "minecraft:item.banner.diagonal_right.blue";

    /**
     * Brown Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_BROWN = "minecraft:item.banner.diagonal_right.brown";

    /**
     * Cyan Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_CYAN = "minecraft:item.banner.diagonal_right.cyan";

    /**
     * Gray Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_GRAY = "minecraft:item.banner.diagonal_right.gray";

    /**
     * Green Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_GREEN = "minecraft:item.banner.diagonal_right.green";

    /**
     * Light Blue Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_LIGHTBLUE = "minecraft:item.banner.diagonal_right.lightBlue";

    /**
     * Lime Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_LIME = "minecraft:item.banner.diagonal_right.lime";

    /**
     * Magenta Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_MAGENTA = "minecraft:item.banner.diagonal_right.magenta";

    /**
     * Orange Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_ORANGE = "minecraft:item.banner.diagonal_right.orange";

    /**
     * Pink Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_PINK = "minecraft:item.banner.diagonal_right.pink";

    /**
     * Purple Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_PURPLE = "minecraft:item.banner.diagonal_right.purple";

    /**
     * Red Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_RED = "minecraft:item.banner.diagonal_right.red";

    /**
     * Light Gray Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_SILVER = "minecraft:item.banner.diagonal_right.silver";

    /**
     * White Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_WHITE = "minecraft:item.banner.diagonal_right.white";

    /**
     * Yellow Per Bend
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_YELLOW = "minecraft:item.banner.diagonal_right.yellow";

    /**
     * Black Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_BLACK = "minecraft:item.banner.diagonal_up_left.black";

    /**
     * Blue Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_BLUE = "minecraft:item.banner.diagonal_up_left.blue";

    /**
     * Brown Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_BROWN = "minecraft:item.banner.diagonal_up_left.brown";

    /**
     * Cyan Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_CYAN = "minecraft:item.banner.diagonal_up_left.cyan";

    /**
     * Gray Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_GRAY = "minecraft:item.banner.diagonal_up_left.gray";

    /**
     * Green Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_GREEN = "minecraft:item.banner.diagonal_up_left.green";

    /**
     * Light Blue Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_LIGHTBLUE = "minecraft:item.banner.diagonal_up_left.lightBlue";

    /**
     * Lime Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_LIME = "minecraft:item.banner.diagonal_up_left.lime";

    /**
     * Magenta Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_MAGENTA = "minecraft:item.banner.diagonal_up_left.magenta";

    /**
     * Orange Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_ORANGE = "minecraft:item.banner.diagonal_up_left.orange";

    /**
     * Pink Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_PINK = "minecraft:item.banner.diagonal_up_left.pink";

    /**
     * Purple Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_PURPLE = "minecraft:item.banner.diagonal_up_left.purple";

    /**
     * Red Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_RED = "minecraft:item.banner.diagonal_up_left.red";

    /**
     * Light Gray Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_SILVER = "minecraft:item.banner.diagonal_up_left.silver";

    /**
     * White Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_WHITE = "minecraft:item.banner.diagonal_up_left.white";

    /**
     * Yellow Per Bend Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_YELLOW = "minecraft:item.banner.diagonal_up_left.yellow";

    /**
     * Black Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_BLACK = "minecraft:item.banner.diagonal_up_right.black";

    /**
     * Blue Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_BLUE = "minecraft:item.banner.diagonal_up_right.blue";

    /**
     * Brown Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_BROWN = "minecraft:item.banner.diagonal_up_right.brown";

    /**
     * Cyan Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_CYAN = "minecraft:item.banner.diagonal_up_right.cyan";

    /**
     * Gray Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_GRAY = "minecraft:item.banner.diagonal_up_right.gray";

    /**
     * Green Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_GREEN = "minecraft:item.banner.diagonal_up_right.green";

    /**
     * Light Blue Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_LIGHTBLUE = "minecraft:item.banner.diagonal_up_right.lightBlue";

    /**
     * Lime Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_LIME = "minecraft:item.banner.diagonal_up_right.lime";

    /**
     * Magenta Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_MAGENTA = "minecraft:item.banner.diagonal_up_right.magenta";

    /**
     * Orange Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_ORANGE = "minecraft:item.banner.diagonal_up_right.orange";

    /**
     * Pink Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_PINK = "minecraft:item.banner.diagonal_up_right.pink";

    /**
     * Purple Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_PURPLE = "minecraft:item.banner.diagonal_up_right.purple";

    /**
     * Red Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_RED = "minecraft:item.banner.diagonal_up_right.red";

    /**
     * Light Gray Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_SILVER = "minecraft:item.banner.diagonal_up_right.silver";

    /**
     * White Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_WHITE = "minecraft:item.banner.diagonal_up_right.white";

    /**
     * Yellow Per Bend Sinister Inverted
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_YELLOW = "minecraft:item.banner.diagonal_up_right.yellow";

    /**
     * Black Flow
     */
    String MC_ITEM_BANNER_FLOW_BLACK = "minecraft:item.banner.flow.black";

    /**
     * Blue Flow
     */
    String MC_ITEM_BANNER_FLOW_BLUE = "minecraft:item.banner.flow.blue";

    /**
     * Brown Flow
     */
    String MC_ITEM_BANNER_FLOW_BROWN = "minecraft:item.banner.flow.brown";

    /**
     * Cyan Flow
     */
    String MC_ITEM_BANNER_FLOW_CYAN = "minecraft:item.banner.flow.cyan";

    /**
     * Gray Flow
     */
    String MC_ITEM_BANNER_FLOW_GRAY = "minecraft:item.banner.flow.gray";

    /**
     * Green Flow
     */
    String MC_ITEM_BANNER_FLOW_GREEN = "minecraft:item.banner.flow.green";

    /**
     * Light Blue Flow
     */
    String MC_ITEM_BANNER_FLOW_LIGHTBLUE = "minecraft:item.banner.flow.lightBlue";

    /**
     * Lime Flow
     */
    String MC_ITEM_BANNER_FLOW_LIME = "minecraft:item.banner.flow.lime";

    /**
     * Magenta Flow
     */
    String MC_ITEM_BANNER_FLOW_MAGENTA = "minecraft:item.banner.flow.magenta";

    /**
     * Orange Flow
     */
    String MC_ITEM_BANNER_FLOW_ORANGE = "minecraft:item.banner.flow.orange";

    /**
     * Pink Flow
     */
    String MC_ITEM_BANNER_FLOW_PINK = "minecraft:item.banner.flow.pink";

    /**
     * Purple Flow
     */
    String MC_ITEM_BANNER_FLOW_PURPLE = "minecraft:item.banner.flow.purple";

    /**
     * Red Flow
     */
    String MC_ITEM_BANNER_FLOW_RED = "minecraft:item.banner.flow.red";

    /**
     * Light Gray Flow
     */
    String MC_ITEM_BANNER_FLOW_SILVER = "minecraft:item.banner.flow.silver";

    /**
     * White Flow
     */
    String MC_ITEM_BANNER_FLOW_WHITE = "minecraft:item.banner.flow.white";

    /**
     * Yellow Flow
     */
    String MC_ITEM_BANNER_FLOW_YELLOW = "minecraft:item.banner.flow.yellow";

    /**
     * Black Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_BLACK = "minecraft:item.banner.flower.black";

    /**
     * Blue Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_BLUE = "minecraft:item.banner.flower.blue";

    /**
     * Brown Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_BROWN = "minecraft:item.banner.flower.brown";

    /**
     * Cyan Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_CYAN = "minecraft:item.banner.flower.cyan";

    /**
     * Gray Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_GRAY = "minecraft:item.banner.flower.gray";

    /**
     * Green Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_GREEN = "minecraft:item.banner.flower.green";

    /**
     * Light Blue Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_LIGHTBLUE = "minecraft:item.banner.flower.lightBlue";

    /**
     * Lime Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_LIME = "minecraft:item.banner.flower.lime";

    /**
     * Magenta Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_MAGENTA = "minecraft:item.banner.flower.magenta";

    /**
     * Orange Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_ORANGE = "minecraft:item.banner.flower.orange";

    /**
     * Pink Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_PINK = "minecraft:item.banner.flower.pink";

    /**
     * Purple Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_PURPLE = "minecraft:item.banner.flower.purple";

    /**
     * Red Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_RED = "minecraft:item.banner.flower.red";

    /**
     * Light Gray Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_SILVER = "minecraft:item.banner.flower.silver";

    /**
     * White Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_WHITE = "minecraft:item.banner.flower.white";

    /**
     * Yellow Flower Charge
     */
    String MC_ITEM_BANNER_FLOWER_YELLOW = "minecraft:item.banner.flower.yellow";

    /**
     * Black Globe
     */
    String MC_ITEM_BANNER_GLOBE_BLACK = "minecraft:item.banner.globe.black";

    /**
     * Blue Globe
     */
    String MC_ITEM_BANNER_GLOBE_BLUE = "minecraft:item.banner.globe.blue";

    /**
     * Brown Globe
     */
    String MC_ITEM_BANNER_GLOBE_BROWN = "minecraft:item.banner.globe.brown";

    /**
     * Cyan Globe
     */
    String MC_ITEM_BANNER_GLOBE_CYAN = "minecraft:item.banner.globe.cyan";

    /**
     * Gray Globe
     */
    String MC_ITEM_BANNER_GLOBE_GRAY = "minecraft:item.banner.globe.gray";

    /**
     * Green Globe
     */
    String MC_ITEM_BANNER_GLOBE_GREEN = "minecraft:item.banner.globe.green";

    /**
     * Light Blue Globe
     */
    String MC_ITEM_BANNER_GLOBE_LIGHTBLUE = "minecraft:item.banner.globe.lightBlue";

    /**
     * Lime Globe
     */
    String MC_ITEM_BANNER_GLOBE_LIME = "minecraft:item.banner.globe.lime";

    /**
     * Magenta Globe
     */
    String MC_ITEM_BANNER_GLOBE_MAGENTA = "minecraft:item.banner.globe.magenta";

    /**
     * Orange Globe
     */
    String MC_ITEM_BANNER_GLOBE_ORANGE = "minecraft:item.banner.globe.orange";

    /**
     * Pink Globe
     */
    String MC_ITEM_BANNER_GLOBE_PINK = "minecraft:item.banner.globe.pink";

    /**
     * Purple Globe
     */
    String MC_ITEM_BANNER_GLOBE_PURPLE = "minecraft:item.banner.globe.purple";

    /**
     * Red Globe
     */
    String MC_ITEM_BANNER_GLOBE_RED = "minecraft:item.banner.globe.red";

    /**
     * Light Gray Globe
     */
    String MC_ITEM_BANNER_GLOBE_SILVER = "minecraft:item.banner.globe.silver";

    /**
     * White Globe
     */
    String MC_ITEM_BANNER_GLOBE_WHITE = "minecraft:item.banner.globe.white";

    /**
     * Yellow Globe
     */
    String MC_ITEM_BANNER_GLOBE_YELLOW = "minecraft:item.banner.globe.yellow";

    /**
     * Black Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_BLACK = "minecraft:item.banner.gradient.black";

    /**
     * Blue Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_BLUE = "minecraft:item.banner.gradient.blue";

    /**
     * Brown Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_BROWN = "minecraft:item.banner.gradient.brown";

    /**
     * Cyan Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_CYAN = "minecraft:item.banner.gradient.cyan";

    /**
     * Gray Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_GRAY = "minecraft:item.banner.gradient.gray";

    /**
     * Green Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_GREEN = "minecraft:item.banner.gradient.green";

    /**
     * Light Blue Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_LIGHTBLUE = "minecraft:item.banner.gradient.lightBlue";

    /**
     * Lime Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_LIME = "minecraft:item.banner.gradient.lime";

    /**
     * Magenta Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_MAGENTA = "minecraft:item.banner.gradient.magenta";

    /**
     * Orange Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_ORANGE = "minecraft:item.banner.gradient.orange";

    /**
     * Pink Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_PINK = "minecraft:item.banner.gradient.pink";

    /**
     * Purple Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_PURPLE = "minecraft:item.banner.gradient.purple";

    /**
     * Red Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_RED = "minecraft:item.banner.gradient.red";

    /**
     * Light Gray Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_SILVER = "minecraft:item.banner.gradient.silver";

    /**
     * White Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_WHITE = "minecraft:item.banner.gradient.white";

    /**
     * Yellow Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_YELLOW = "minecraft:item.banner.gradient.yellow";

    /**
     * Black Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_BLACK = "minecraft:item.banner.gradient_up.black";

    /**
     * Blue Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_BLUE = "minecraft:item.banner.gradient_up.blue";

    /**
     * Brown Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_BROWN = "minecraft:item.banner.gradient_up.brown";

    /**
     * Cyan Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_CYAN = "minecraft:item.banner.gradient_up.cyan";

    /**
     * Gray Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_GRAY = "minecraft:item.banner.gradient_up.gray";

    /**
     * Green Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_GREEN = "minecraft:item.banner.gradient_up.green";

    /**
     * Light Blue Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_LIGHTBLUE = "minecraft:item.banner.gradient_up.lightBlue";

    /**
     * Lime Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_LIME = "minecraft:item.banner.gradient_up.lime";

    /**
     * Magenta Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_MAGENTA = "minecraft:item.banner.gradient_up.magenta";

    /**
     * Orange Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_ORANGE = "minecraft:item.banner.gradient_up.orange";

    /**
     * Pink Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_PINK = "minecraft:item.banner.gradient_up.pink";

    /**
     * Purple Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_PURPLE = "minecraft:item.banner.gradient_up.purple";

    /**
     * Red Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_RED = "minecraft:item.banner.gradient_up.red";

    /**
     * Light Gray Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_SILVER = "minecraft:item.banner.gradient_up.silver";

    /**
     * White Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_WHITE = "minecraft:item.banner.gradient_up.white";

    /**
     * Yellow Base Gradient
     */
    String MC_ITEM_BANNER_GRADIENT_UP_YELLOW = "minecraft:item.banner.gradient_up.yellow";

    /**
     * Gray Banner
     */
    String MC_ITEM_BANNER_GRAY_NAME = "minecraft:item.banner.gray.name";

    /**
     * Green Banner
     */
    String MC_ITEM_BANNER_GREEN_NAME = "minecraft:item.banner.green.name";

    /**
     * Black Guster
     */
    String MC_ITEM_BANNER_GUSTER_BLACK = "minecraft:item.banner.guster.black";

    /**
     * Blue Guster
     */
    String MC_ITEM_BANNER_GUSTER_BLUE = "minecraft:item.banner.guster.blue";

    /**
     * Brown Guster
     */
    String MC_ITEM_BANNER_GUSTER_BROWN = "minecraft:item.banner.guster.brown";

    /**
     * Cyan Guster
     */
    String MC_ITEM_BANNER_GUSTER_CYAN = "minecraft:item.banner.guster.cyan";

    /**
     * Gray Guster
     */
    String MC_ITEM_BANNER_GUSTER_GRAY = "minecraft:item.banner.guster.gray";

    /**
     * Green Guster
     */
    String MC_ITEM_BANNER_GUSTER_GREEN = "minecraft:item.banner.guster.green";

    /**
     * Light Blue Guster
     */
    String MC_ITEM_BANNER_GUSTER_LIGHTBLUE = "minecraft:item.banner.guster.lightBlue";

    /**
     * Lime Guster
     */
    String MC_ITEM_BANNER_GUSTER_LIME = "minecraft:item.banner.guster.lime";

    /**
     * Magenta Guster
     */
    String MC_ITEM_BANNER_GUSTER_MAGENTA = "minecraft:item.banner.guster.magenta";

    /**
     * Orange Guster
     */
    String MC_ITEM_BANNER_GUSTER_ORANGE = "minecraft:item.banner.guster.orange";

    /**
     * Pink Guster
     */
    String MC_ITEM_BANNER_GUSTER_PINK = "minecraft:item.banner.guster.pink";

    /**
     * Purple Guster
     */
    String MC_ITEM_BANNER_GUSTER_PURPLE = "minecraft:item.banner.guster.purple";

    /**
     * Red Guster
     */
    String MC_ITEM_BANNER_GUSTER_RED = "minecraft:item.banner.guster.red";

    /**
     * Light Gray Guster
     */
    String MC_ITEM_BANNER_GUSTER_SILVER = "minecraft:item.banner.guster.silver";

    /**
     * White Guster
     */
    String MC_ITEM_BANNER_GUSTER_WHITE = "minecraft:item.banner.guster.white";

    /**
     * Yellow Guster
     */
    String MC_ITEM_BANNER_GUSTER_YELLOW = "minecraft:item.banner.guster.yellow";

    /**
     * Black Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BLACK = "minecraft:item.banner.half_horizontal.black";

    /**
     * Blue Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BLUE = "minecraft:item.banner.half_horizontal.blue";

    /**
     * Brown Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BROWN = "minecraft:item.banner.half_horizontal.brown";

    /**
     * Cyan Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_CYAN = "minecraft:item.banner.half_horizontal.cyan";

    /**
     * Gray Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_GRAY = "minecraft:item.banner.half_horizontal.gray";

    /**
     * Green Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_GREEN = "minecraft:item.banner.half_horizontal.green";

    /**
     * Light Blue Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_LIGHTBLUE = "minecraft:item.banner.half_horizontal.lightBlue";

    /**
     * Lime Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_LIME = "minecraft:item.banner.half_horizontal.lime";

    /**
     * Magenta Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_MAGENTA = "minecraft:item.banner.half_horizontal.magenta";

    /**
     * Orange Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_ORANGE = "minecraft:item.banner.half_horizontal.orange";

    /**
     * Pink Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_PINK = "minecraft:item.banner.half_horizontal.pink";

    /**
     * Purple Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_PURPLE = "minecraft:item.banner.half_horizontal.purple";

    /**
     * Red Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_RED = "minecraft:item.banner.half_horizontal.red";

    /**
     * Light Gray Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_SILVER = "minecraft:item.banner.half_horizontal.silver";

    /**
     * White Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_WHITE = "minecraft:item.banner.half_horizontal.white";

    /**
     * Yellow Per Fess
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_YELLOW = "minecraft:item.banner.half_horizontal.yellow";

    /**
     * Black Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_BLACK = "minecraft:item.banner.half_horizontal_bottom.black";

    /**
     * Blue Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_BLUE = "minecraft:item.banner.half_horizontal_bottom.blue";

    /**
     * Brown Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_BROWN = "minecraft:item.banner.half_horizontal_bottom.brown";

    /**
     * Cyan Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_CYAN = "minecraft:item.banner.half_horizontal_bottom.cyan";

    /**
     * Gray Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_GRAY = "minecraft:item.banner.half_horizontal_bottom.gray";

    /**
     * Green Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_GREEN = "minecraft:item.banner.half_horizontal_bottom.green";

    /**
     * Light Blue Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_LIGHTBLUE = "minecraft:item.banner.half_horizontal_bottom.lightBlue";

    /**
     * Lime Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_LIME = "minecraft:item.banner.half_horizontal_bottom.lime";

    /**
     * Magenta Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_MAGENTA = "minecraft:item.banner.half_horizontal_bottom.magenta";

    /**
     * Orange Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_ORANGE = "minecraft:item.banner.half_horizontal_bottom.orange";

    /**
     * Pink Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_PINK = "minecraft:item.banner.half_horizontal_bottom.pink";

    /**
     * Purple Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_PURPLE = "minecraft:item.banner.half_horizontal_bottom.purple";

    /**
     * Red Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_RED = "minecraft:item.banner.half_horizontal_bottom.red";

    /**
     * Light Gray Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_SILVER = "minecraft:item.banner.half_horizontal_bottom.silver";

    /**
     * White Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_WHITE = "minecraft:item.banner.half_horizontal_bottom.white";

    /**
     * Yellow Per Fess Inverted
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_YELLOW = "minecraft:item.banner.half_horizontal_bottom.yellow";

    /**
     * Black Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_BLACK = "minecraft:item.banner.half_vertical.black";

    /**
     * Blue Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_BLUE = "minecraft:item.banner.half_vertical.blue";

    /**
     * Brown Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_BROWN = "minecraft:item.banner.half_vertical.brown";

    /**
     * Cyan Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_CYAN = "minecraft:item.banner.half_vertical.cyan";

    /**
     * Gray Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_GRAY = "minecraft:item.banner.half_vertical.gray";

    /**
     * Green Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_GREEN = "minecraft:item.banner.half_vertical.green";

    /**
     * Light Blue Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_LIGHTBLUE = "minecraft:item.banner.half_vertical.lightBlue";

    /**
     * Lime Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_LIME = "minecraft:item.banner.half_vertical.lime";

    /**
     * Magenta Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_MAGENTA = "minecraft:item.banner.half_vertical.magenta";

    /**
     * Orange Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_ORANGE = "minecraft:item.banner.half_vertical.orange";

    /**
     * Pink Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_PINK = "minecraft:item.banner.half_vertical.pink";

    /**
     * Purple Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_PURPLE = "minecraft:item.banner.half_vertical.purple";

    /**
     * Red Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RED = "minecraft:item.banner.half_vertical.red";

    /**
     * Light Gray Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_SILVER = "minecraft:item.banner.half_vertical.silver";

    /**
     * White Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_WHITE = "minecraft:item.banner.half_vertical.white";

    /**
     * Yellow Per Pale
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_YELLOW = "minecraft:item.banner.half_vertical.yellow";

    /**
     * Black Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_BLACK = "minecraft:item.banner.half_vertical_right.black";

    /**
     * Blue Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_BLUE = "minecraft:item.banner.half_vertical_right.blue";

    /**
     * Brown Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_BROWN = "minecraft:item.banner.half_vertical_right.brown";

    /**
     * Cyan Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_CYAN = "minecraft:item.banner.half_vertical_right.cyan";

    /**
     * Gray Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_GRAY = "minecraft:item.banner.half_vertical_right.gray";

    /**
     * Green Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_GREEN = "minecraft:item.banner.half_vertical_right.green";

    /**
     * Light Blue Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_LIGHTBLUE = "minecraft:item.banner.half_vertical_right.lightBlue";

    /**
     * Lime Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_LIME = "minecraft:item.banner.half_vertical_right.lime";

    /**
     * Magenta Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_MAGENTA = "minecraft:item.banner.half_vertical_right.magenta";

    /**
     * Orange Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_ORANGE = "minecraft:item.banner.half_vertical_right.orange";

    /**
     * Pink Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_PINK = "minecraft:item.banner.half_vertical_right.pink";

    /**
     * Purple Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_PURPLE = "minecraft:item.banner.half_vertical_right.purple";

    /**
     * Red Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_RED = "minecraft:item.banner.half_vertical_right.red";

    /**
     * Light Gray Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_SILVER = "minecraft:item.banner.half_vertical_right.silver";

    /**
     * White Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_WHITE = "minecraft:item.banner.half_vertical_right.white";

    /**
     * Yellow Per Pale Inverted
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_YELLOW = "minecraft:item.banner.half_vertical_right.yellow";

    /**
     * Illager
     */
    String MC_ITEM_BANNER_ILLAGER = "minecraft:item.banner.illager";

    /**
     * Ominous Banner
     */
    String MC_ITEM_BANNER_ILLAGER_CAPTAIN_NAME = "minecraft:item.banner.illager_captain.name";

    /**
     * Light Blue Banner
     */
    String MC_ITEM_BANNER_LIGHTBLUE_NAME = "minecraft:item.banner.lightBlue.name";

    /**
     * Lime Banner
     */
    String MC_ITEM_BANNER_LIME_NAME = "minecraft:item.banner.lime.name";

    /**
     * Magenta Banner
     */
    String MC_ITEM_BANNER_MAGENTA_NAME = "minecraft:item.banner.magenta.name";

    /**
     * Black Thing
     */
    String MC_ITEM_BANNER_MOJANG_BLACK = "minecraft:item.banner.mojang.black";

    /**
     * Blue Thing
     */
    String MC_ITEM_BANNER_MOJANG_BLUE = "minecraft:item.banner.mojang.blue";

    /**
     * Brown Thing
     */
    String MC_ITEM_BANNER_MOJANG_BROWN = "minecraft:item.banner.mojang.brown";

    /**
     * Cyan Thing
     */
    String MC_ITEM_BANNER_MOJANG_CYAN = "minecraft:item.banner.mojang.cyan";

    /**
     * Gray Thing
     */
    String MC_ITEM_BANNER_MOJANG_GRAY = "minecraft:item.banner.mojang.gray";

    /**
     * Green Thing
     */
    String MC_ITEM_BANNER_MOJANG_GREEN = "minecraft:item.banner.mojang.green";

    /**
     * Light Blue Thing
     */
    String MC_ITEM_BANNER_MOJANG_LIGHTBLUE = "minecraft:item.banner.mojang.lightBlue";

    /**
     * Lime Thing
     */
    String MC_ITEM_BANNER_MOJANG_LIME = "minecraft:item.banner.mojang.lime";

    /**
     * Magenta Thing
     */
    String MC_ITEM_BANNER_MOJANG_MAGENTA = "minecraft:item.banner.mojang.magenta";

    /**
     * Orange Thing
     */
    String MC_ITEM_BANNER_MOJANG_ORANGE = "minecraft:item.banner.mojang.orange";

    /**
     * Pink Thing
     */
    String MC_ITEM_BANNER_MOJANG_PINK = "minecraft:item.banner.mojang.pink";

    /**
     * Purple Thing
     */
    String MC_ITEM_BANNER_MOJANG_PURPLE = "minecraft:item.banner.mojang.purple";

    /**
     * Red Thing
     */
    String MC_ITEM_BANNER_MOJANG_RED = "minecraft:item.banner.mojang.red";

    /**
     * Light Gray Thing
     */
    String MC_ITEM_BANNER_MOJANG_SILVER = "minecraft:item.banner.mojang.silver";

    /**
     * White Thing
     */
    String MC_ITEM_BANNER_MOJANG_WHITE = "minecraft:item.banner.mojang.white";

    /**
     * Yellow Thing
     */
    String MC_ITEM_BANNER_MOJANG_YELLOW = "minecraft:item.banner.mojang.yellow";

    /**
     * Orange Banner
     */
    String MC_ITEM_BANNER_ORANGE_NAME = "minecraft:item.banner.orange.name";

    /**
     * Black Snout
     */
    String MC_ITEM_BANNER_PIGLIN_BLACK = "minecraft:item.banner.piglin.black";

    /**
     * Blue Snout
     */
    String MC_ITEM_BANNER_PIGLIN_BLUE = "minecraft:item.banner.piglin.blue";

    /**
     * Brown Snout
     */
    String MC_ITEM_BANNER_PIGLIN_BROWN = "minecraft:item.banner.piglin.brown";

    /**
     * Cyan Snout
     */
    String MC_ITEM_BANNER_PIGLIN_CYAN = "minecraft:item.banner.piglin.cyan";

    /**
     * Gray Snout
     */
    String MC_ITEM_BANNER_PIGLIN_GRAY = "minecraft:item.banner.piglin.gray";

    /**
     * Green Snout
     */
    String MC_ITEM_BANNER_PIGLIN_GREEN = "minecraft:item.banner.piglin.green";

    /**
     * Light Blue Snout
     */
    String MC_ITEM_BANNER_PIGLIN_LIGHTBLUE = "minecraft:item.banner.piglin.lightBlue";

    /**
     * Lime Snout
     */
    String MC_ITEM_BANNER_PIGLIN_LIME = "minecraft:item.banner.piglin.lime";

    /**
     * Magenta Snout
     */
    String MC_ITEM_BANNER_PIGLIN_MAGENTA = "minecraft:item.banner.piglin.magenta";

    /**
     * Orange Snout
     */
    String MC_ITEM_BANNER_PIGLIN_ORANGE = "minecraft:item.banner.piglin.orange";

    /**
     * Pink Snout
     */
    String MC_ITEM_BANNER_PIGLIN_PINK = "minecraft:item.banner.piglin.pink";

    /**
     * Purple Snout
     */
    String MC_ITEM_BANNER_PIGLIN_PURPLE = "minecraft:item.banner.piglin.purple";

    /**
     * Red Snout
     */
    String MC_ITEM_BANNER_PIGLIN_RED = "minecraft:item.banner.piglin.red";

    /**
     * Light Gray Snout
     */
    String MC_ITEM_BANNER_PIGLIN_SILVER = "minecraft:item.banner.piglin.silver";

    /**
     * White Snout
     */
    String MC_ITEM_BANNER_PIGLIN_WHITE = "minecraft:item.banner.piglin.white";

    /**
     * Yellow Snout
     */
    String MC_ITEM_BANNER_PIGLIN_YELLOW = "minecraft:item.banner.piglin.yellow";

    /**
     * Pink Banner
     */
    String MC_ITEM_BANNER_PINK_NAME = "minecraft:item.banner.pink.name";

    /**
     * Purple Banner
     */
    String MC_ITEM_BANNER_PURPLE_NAME = "minecraft:item.banner.purple.name";

    /**
     * Red Banner
     */
    String MC_ITEM_BANNER_RED_NAME = "minecraft:item.banner.red.name";

    /**
     * Black Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_BLACK = "minecraft:item.banner.rhombus.black";

    /**
     * Blue Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_BLUE = "minecraft:item.banner.rhombus.blue";

    /**
     * Brown Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_BROWN = "minecraft:item.banner.rhombus.brown";

    /**
     * Cyan Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_CYAN = "minecraft:item.banner.rhombus.cyan";

    /**
     * Gray Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_GRAY = "minecraft:item.banner.rhombus.gray";

    /**
     * Green Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_GREEN = "minecraft:item.banner.rhombus.green";

    /**
     * Light Blue Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_LIGHTBLUE = "minecraft:item.banner.rhombus.lightBlue";

    /**
     * Lime Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_LIME = "minecraft:item.banner.rhombus.lime";

    /**
     * Magenta Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_MAGENTA = "minecraft:item.banner.rhombus.magenta";

    /**
     * Orange Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_ORANGE = "minecraft:item.banner.rhombus.orange";

    /**
     * Pink Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_PINK = "minecraft:item.banner.rhombus.pink";

    /**
     * Purple Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_PURPLE = "minecraft:item.banner.rhombus.purple";

    /**
     * Red Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_RED = "minecraft:item.banner.rhombus.red";

    /**
     * Light Gray Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_SILVER = "minecraft:item.banner.rhombus.silver";

    /**
     * White Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_WHITE = "minecraft:item.banner.rhombus.white";

    /**
     * Yellow Lozenge
     */
    String MC_ITEM_BANNER_RHOMBUS_YELLOW = "minecraft:item.banner.rhombus.yellow";

    /**
     * Light Gray Banner
     */
    String MC_ITEM_BANNER_SILVER_NAME = "minecraft:item.banner.silver.name";

    /**
     * Black Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_BLACK = "minecraft:item.banner.skull.black";

    /**
     * Blue Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_BLUE = "minecraft:item.banner.skull.blue";

    /**
     * Brown Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_BROWN = "minecraft:item.banner.skull.brown";

    /**
     * Cyan Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_CYAN = "minecraft:item.banner.skull.cyan";

    /**
     * Gray Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_GRAY = "minecraft:item.banner.skull.gray";

    /**
     * Green Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_GREEN = "minecraft:item.banner.skull.green";

    /**
     * Light Blue Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_LIGHTBLUE = "minecraft:item.banner.skull.lightBlue";

    /**
     * Lime Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_LIME = "minecraft:item.banner.skull.lime";

    /**
     * Magenta Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_MAGENTA = "minecraft:item.banner.skull.magenta";

    /**
     * Orange Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_ORANGE = "minecraft:item.banner.skull.orange";

    /**
     * Pink Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_PINK = "minecraft:item.banner.skull.pink";

    /**
     * Purple Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_PURPLE = "minecraft:item.banner.skull.purple";

    /**
     * Red Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_RED = "minecraft:item.banner.skull.red";

    /**
     * Light Gray Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_SILVER = "minecraft:item.banner.skull.silver";

    /**
     * White Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_WHITE = "minecraft:item.banner.skull.white";

    /**
     * Yellow Skull Charge
     */
    String MC_ITEM_BANNER_SKULL_YELLOW = "minecraft:item.banner.skull.yellow";

    /**
     * Black Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_BLACK = "minecraft:item.banner.small_stripes.black";

    /**
     * Blue Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_BLUE = "minecraft:item.banner.small_stripes.blue";

    /**
     * Brown Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_BROWN = "minecraft:item.banner.small_stripes.brown";

    /**
     * Cyan Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_CYAN = "minecraft:item.banner.small_stripes.cyan";

    /**
     * Gray Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_GRAY = "minecraft:item.banner.small_stripes.gray";

    /**
     * Green Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_GREEN = "minecraft:item.banner.small_stripes.green";

    /**
     * Light Blue Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_LIGHTBLUE = "minecraft:item.banner.small_stripes.lightBlue";

    /**
     * Lime Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_LIME = "minecraft:item.banner.small_stripes.lime";

    /**
     * Magenta Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_MAGENTA = "minecraft:item.banner.small_stripes.magenta";

    /**
     * Orange Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_ORANGE = "minecraft:item.banner.small_stripes.orange";

    /**
     * Pink Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_PINK = "minecraft:item.banner.small_stripes.pink";

    /**
     * Purple Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_PURPLE = "minecraft:item.banner.small_stripes.purple";

    /**
     * Red Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_RED = "minecraft:item.banner.small_stripes.red";

    /**
     * Light Gray Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_SILVER = "minecraft:item.banner.small_stripes.silver";

    /**
     * White Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_WHITE = "minecraft:item.banner.small_stripes.white";

    /**
     * Yellow Paly
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_YELLOW = "minecraft:item.banner.small_stripes.yellow";

    /**
     * Black Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_BLACK = "minecraft:item.banner.square_bottom_left.black";

    /**
     * Blue Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_BLUE = "minecraft:item.banner.square_bottom_left.blue";

    /**
     * Brown Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_BROWN = "minecraft:item.banner.square_bottom_left.brown";

    /**
     * Cyan Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_CYAN = "minecraft:item.banner.square_bottom_left.cyan";

    /**
     * Gray Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_GRAY = "minecraft:item.banner.square_bottom_left.gray";

    /**
     * Green Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_GREEN = "minecraft:item.banner.square_bottom_left.green";

    /**
     * Light Blue Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_LIGHTBLUE = "minecraft:item.banner.square_bottom_left.lightBlue";

    /**
     * Lime Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_LIME = "minecraft:item.banner.square_bottom_left.lime";

    /**
     * Magenta Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_MAGENTA = "minecraft:item.banner.square_bottom_left.magenta";

    /**
     * Orange Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_ORANGE = "minecraft:item.banner.square_bottom_left.orange";

    /**
     * Pink Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_PINK = "minecraft:item.banner.square_bottom_left.pink";

    /**
     * Purple Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_PURPLE = "minecraft:item.banner.square_bottom_left.purple";

    /**
     * Red Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_RED = "minecraft:item.banner.square_bottom_left.red";

    /**
     * Light Gray Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_SILVER = "minecraft:item.banner.square_bottom_left.silver";

    /**
     * White Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_WHITE = "minecraft:item.banner.square_bottom_left.white";

    /**
     * Yellow Base Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_YELLOW = "minecraft:item.banner.square_bottom_left.yellow";

    /**
     * Black Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_BLACK = "minecraft:item.banner.square_bottom_right.black";

    /**
     * Blue Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_BLUE = "minecraft:item.banner.square_bottom_right.blue";

    /**
     * Brown Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_BROWN = "minecraft:item.banner.square_bottom_right.brown";

    /**
     * Cyan Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_CYAN = "minecraft:item.banner.square_bottom_right.cyan";

    /**
     * Gray Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_GRAY = "minecraft:item.banner.square_bottom_right.gray";

    /**
     * Green Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_GREEN = "minecraft:item.banner.square_bottom_right.green";

    /**
     * Light Blue Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_LIGHTBLUE = "minecraft:item.banner.square_bottom_right.lightBlue";

    /**
     * Lime Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_LIME = "minecraft:item.banner.square_bottom_right.lime";

    /**
     * Magenta Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_MAGENTA = "minecraft:item.banner.square_bottom_right.magenta";

    /**
     * Orange Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_ORANGE = "minecraft:item.banner.square_bottom_right.orange";

    /**
     * Pink Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_PINK = "minecraft:item.banner.square_bottom_right.pink";

    /**
     * Purple Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_PURPLE = "minecraft:item.banner.square_bottom_right.purple";

    /**
     * Red Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_RED = "minecraft:item.banner.square_bottom_right.red";

    /**
     * Light Gray Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_SILVER = "minecraft:item.banner.square_bottom_right.silver";

    /**
     * White Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_WHITE = "minecraft:item.banner.square_bottom_right.white";

    /**
     * Yellow Base Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_YELLOW = "minecraft:item.banner.square_bottom_right.yellow";

    /**
     * Black Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_BLACK = "minecraft:item.banner.square_top_left.black";

    /**
     * Blue Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_BLUE = "minecraft:item.banner.square_top_left.blue";

    /**
     * Brown Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_BROWN = "minecraft:item.banner.square_top_left.brown";

    /**
     * Cyan Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_CYAN = "minecraft:item.banner.square_top_left.cyan";

    /**
     * Gray Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_GRAY = "minecraft:item.banner.square_top_left.gray";

    /**
     * Green Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_GREEN = "minecraft:item.banner.square_top_left.green";

    /**
     * Light Blue Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_LIGHTBLUE = "minecraft:item.banner.square_top_left.lightBlue";

    /**
     * Lime Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_LIME = "minecraft:item.banner.square_top_left.lime";

    /**
     * Magenta Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_MAGENTA = "minecraft:item.banner.square_top_left.magenta";

    /**
     * Orange Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_ORANGE = "minecraft:item.banner.square_top_left.orange";

    /**
     * Pink Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_PINK = "minecraft:item.banner.square_top_left.pink";

    /**
     * Purple Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_PURPLE = "minecraft:item.banner.square_top_left.purple";

    /**
     * Red Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_RED = "minecraft:item.banner.square_top_left.red";

    /**
     * Light Gray Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_SILVER = "minecraft:item.banner.square_top_left.silver";

    /**
     * White Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_WHITE = "minecraft:item.banner.square_top_left.white";

    /**
     * Yellow Chief Dexter Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_YELLOW = "minecraft:item.banner.square_top_left.yellow";

    /**
     * Black Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_BLACK = "minecraft:item.banner.square_top_right.black";

    /**
     * Blue Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_BLUE = "minecraft:item.banner.square_top_right.blue";

    /**
     * Brown Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_BROWN = "minecraft:item.banner.square_top_right.brown";

    /**
     * Cyan Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_CYAN = "minecraft:item.banner.square_top_right.cyan";

    /**
     * Gray Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_GRAY = "minecraft:item.banner.square_top_right.gray";

    /**
     * Green Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_GREEN = "minecraft:item.banner.square_top_right.green";

    /**
     * Light Blue Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_LIGHTBLUE = "minecraft:item.banner.square_top_right.lightBlue";

    /**
     * Lime Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_LIME = "minecraft:item.banner.square_top_right.lime";

    /**
     * Magenta Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_MAGENTA = "minecraft:item.banner.square_top_right.magenta";

    /**
     * Orange Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_ORANGE = "minecraft:item.banner.square_top_right.orange";

    /**
     * Pink Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_PINK = "minecraft:item.banner.square_top_right.pink";

    /**
     * Purple Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_PURPLE = "minecraft:item.banner.square_top_right.purple";

    /**
     * Red Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_RED = "minecraft:item.banner.square_top_right.red";

    /**
     * Light Gray Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_SILVER = "minecraft:item.banner.square_top_right.silver";

    /**
     * White Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_WHITE = "minecraft:item.banner.square_top_right.white";

    /**
     * Yellow Chief Sinister Canton
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_YELLOW = "minecraft:item.banner.square_top_right.yellow";

    /**
     * Black Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_BLACK = "minecraft:item.banner.straight_cross.black";

    /**
     * Blue Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_BLUE = "minecraft:item.banner.straight_cross.blue";

    /**
     * Brown Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_BROWN = "minecraft:item.banner.straight_cross.brown";

    /**
     * Cyan Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_CYAN = "minecraft:item.banner.straight_cross.cyan";

    /**
     * Gray Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_GRAY = "minecraft:item.banner.straight_cross.gray";

    /**
     * Green Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_GREEN = "minecraft:item.banner.straight_cross.green";

    /**
     * Light Blue Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_LIGHTBLUE = "minecraft:item.banner.straight_cross.lightBlue";

    /**
     * Lime Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_LIME = "minecraft:item.banner.straight_cross.lime";

    /**
     * Magenta Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_MAGENTA = "minecraft:item.banner.straight_cross.magenta";

    /**
     * Orange Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_ORANGE = "minecraft:item.banner.straight_cross.orange";

    /**
     * Pink Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_PINK = "minecraft:item.banner.straight_cross.pink";

    /**
     * Purple Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_PURPLE = "minecraft:item.banner.straight_cross.purple";

    /**
     * Red Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_RED = "minecraft:item.banner.straight_cross.red";

    /**
     * Light Gray Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_SILVER = "minecraft:item.banner.straight_cross.silver";

    /**
     * White Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_WHITE = "minecraft:item.banner.straight_cross.white";

    /**
     * Yellow Cross
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_YELLOW = "minecraft:item.banner.straight_cross.yellow";

    /**
     * Black Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_BLACK = "minecraft:item.banner.stripe_bottom.black";

    /**
     * Blue Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_BLUE = "minecraft:item.banner.stripe_bottom.blue";

    /**
     * Brown Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_BROWN = "minecraft:item.banner.stripe_bottom.brown";

    /**
     * Cyan Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_CYAN = "minecraft:item.banner.stripe_bottom.cyan";

    /**
     * Gray Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_GRAY = "minecraft:item.banner.stripe_bottom.gray";

    /**
     * Green Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_GREEN = "minecraft:item.banner.stripe_bottom.green";

    /**
     * Light Blue Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_LIGHTBLUE = "minecraft:item.banner.stripe_bottom.lightBlue";

    /**
     * Lime Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_LIME = "minecraft:item.banner.stripe_bottom.lime";

    /**
     * Magenta Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_MAGENTA = "minecraft:item.banner.stripe_bottom.magenta";

    /**
     * Orange Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_ORANGE = "minecraft:item.banner.stripe_bottom.orange";

    /**
     * Pink Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_PINK = "minecraft:item.banner.stripe_bottom.pink";

    /**
     * Purple Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_PURPLE = "minecraft:item.banner.stripe_bottom.purple";

    /**
     * Red Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_RED = "minecraft:item.banner.stripe_bottom.red";

    /**
     * Light Gray Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_SILVER = "minecraft:item.banner.stripe_bottom.silver";

    /**
     * White Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_WHITE = "minecraft:item.banner.stripe_bottom.white";

    /**
     * Yellow Base Fess
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_YELLOW = "minecraft:item.banner.stripe_bottom.yellow";

    /**
     * Black Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_BLACK = "minecraft:item.banner.stripe_center.black";

    /**
     * Blue Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_BLUE = "minecraft:item.banner.stripe_center.blue";

    /**
     * Brown Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_BROWN = "minecraft:item.banner.stripe_center.brown";

    /**
     * Cyan Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_CYAN = "minecraft:item.banner.stripe_center.cyan";

    /**
     * Gray Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_GRAY = "minecraft:item.banner.stripe_center.gray";

    /**
     * Green Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_GREEN = "minecraft:item.banner.stripe_center.green";

    /**
     * Light Blue Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_LIGHTBLUE = "minecraft:item.banner.stripe_center.lightBlue";

    /**
     * Lime Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_LIME = "minecraft:item.banner.stripe_center.lime";

    /**
     * Magenta Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_MAGENTA = "minecraft:item.banner.stripe_center.magenta";

    /**
     * Orange Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_ORANGE = "minecraft:item.banner.stripe_center.orange";

    /**
     * Pink Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_PINK = "minecraft:item.banner.stripe_center.pink";

    /**
     * Purple Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_PURPLE = "minecraft:item.banner.stripe_center.purple";

    /**
     * Red Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_RED = "minecraft:item.banner.stripe_center.red";

    /**
     * Light Gray Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_SILVER = "minecraft:item.banner.stripe_center.silver";

    /**
     * White Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_WHITE = "minecraft:item.banner.stripe_center.white";

    /**
     * Yellow Pale
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_YELLOW = "minecraft:item.banner.stripe_center.yellow";

    /**
     * Black Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_BLACK = "minecraft:item.banner.stripe_downleft.black";

    /**
     * Blue Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_BLUE = "minecraft:item.banner.stripe_downleft.blue";

    /**
     * Brown Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_BROWN = "minecraft:item.banner.stripe_downleft.brown";

    /**
     * Cyan Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_CYAN = "minecraft:item.banner.stripe_downleft.cyan";

    /**
     * Gray Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_GRAY = "minecraft:item.banner.stripe_downleft.gray";

    /**
     * Green Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_GREEN = "minecraft:item.banner.stripe_downleft.green";

    /**
     * Light Blue Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_LIGHTBLUE = "minecraft:item.banner.stripe_downleft.lightBlue";

    /**
     * Lime Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_LIME = "minecraft:item.banner.stripe_downleft.lime";

    /**
     * Magenta Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_MAGENTA = "minecraft:item.banner.stripe_downleft.magenta";

    /**
     * Orange Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_ORANGE = "minecraft:item.banner.stripe_downleft.orange";

    /**
     * Pink Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_PINK = "minecraft:item.banner.stripe_downleft.pink";

    /**
     * Purple Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_PURPLE = "minecraft:item.banner.stripe_downleft.purple";

    /**
     * Red Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_RED = "minecraft:item.banner.stripe_downleft.red";

    /**
     * Light Gray Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_SILVER = "minecraft:item.banner.stripe_downleft.silver";

    /**
     * White Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_WHITE = "minecraft:item.banner.stripe_downleft.white";

    /**
     * Yellow Bend Sinister
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_YELLOW = "minecraft:item.banner.stripe_downleft.yellow";

    /**
     * Black Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_BLACK = "minecraft:item.banner.stripe_downright.black";

    /**
     * Blue Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_BLUE = "minecraft:item.banner.stripe_downright.blue";

    /**
     * Brown Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_BROWN = "minecraft:item.banner.stripe_downright.brown";

    /**
     * Cyan Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_CYAN = "minecraft:item.banner.stripe_downright.cyan";

    /**
     * Gray Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_GRAY = "minecraft:item.banner.stripe_downright.gray";

    /**
     * Green Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_GREEN = "minecraft:item.banner.stripe_downright.green";

    /**
     * Light Blue Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_LIGHTBLUE = "minecraft:item.banner.stripe_downright.lightBlue";

    /**
     * Lime Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_LIME = "minecraft:item.banner.stripe_downright.lime";

    /**
     * Magenta Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_MAGENTA = "minecraft:item.banner.stripe_downright.magenta";

    /**
     * Orange Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_ORANGE = "minecraft:item.banner.stripe_downright.orange";

    /**
     * Pink Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_PINK = "minecraft:item.banner.stripe_downright.pink";

    /**
     * Purple Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_PURPLE = "minecraft:item.banner.stripe_downright.purple";

    /**
     * Red Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_RED = "minecraft:item.banner.stripe_downright.red";

    /**
     * Light Gray Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_SILVER = "minecraft:item.banner.stripe_downright.silver";

    /**
     * White Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_WHITE = "minecraft:item.banner.stripe_downright.white";

    /**
     * Yellow Bend
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_YELLOW = "minecraft:item.banner.stripe_downright.yellow";

    /**
     * Black Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_BLACK = "minecraft:item.banner.stripe_left.black";

    /**
     * Blue Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_BLUE = "minecraft:item.banner.stripe_left.blue";

    /**
     * Brown Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_BROWN = "minecraft:item.banner.stripe_left.brown";

    /**
     * Cyan Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_CYAN = "minecraft:item.banner.stripe_left.cyan";

    /**
     * Gray Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_GRAY = "minecraft:item.banner.stripe_left.gray";

    /**
     * Green Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_GREEN = "minecraft:item.banner.stripe_left.green";

    /**
     * Light Blue Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_LIGHTBLUE = "minecraft:item.banner.stripe_left.lightBlue";

    /**
     * Lime Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_LIME = "minecraft:item.banner.stripe_left.lime";

    /**
     * Magenta Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_MAGENTA = "minecraft:item.banner.stripe_left.magenta";

    /**
     * Orange Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_ORANGE = "minecraft:item.banner.stripe_left.orange";

    /**
     * Pink Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_PINK = "minecraft:item.banner.stripe_left.pink";

    /**
     * Purple Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_PURPLE = "minecraft:item.banner.stripe_left.purple";

    /**
     * Red Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_RED = "minecraft:item.banner.stripe_left.red";

    /**
     * Light Gray Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_SILVER = "minecraft:item.banner.stripe_left.silver";

    /**
     * White Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_WHITE = "minecraft:item.banner.stripe_left.white";

    /**
     * Yellow Pale Dexter
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_YELLOW = "minecraft:item.banner.stripe_left.yellow";

    /**
     * Black Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_BLACK = "minecraft:item.banner.stripe_middle.black";

    /**
     * Blue Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_BLUE = "minecraft:item.banner.stripe_middle.blue";

    /**
     * Brown Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_BROWN = "minecraft:item.banner.stripe_middle.brown";

    /**
     * Cyan Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_CYAN = "minecraft:item.banner.stripe_middle.cyan";

    /**
     * Gray Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_GRAY = "minecraft:item.banner.stripe_middle.gray";

    /**
     * Green Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_GREEN = "minecraft:item.banner.stripe_middle.green";

    /**
     * Light Blue Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_LIGHTBLUE = "minecraft:item.banner.stripe_middle.lightBlue";

    /**
     * Lime Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_LIME = "minecraft:item.banner.stripe_middle.lime";

    /**
     * Magenta Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_MAGENTA = "minecraft:item.banner.stripe_middle.magenta";

    /**
     * Orange Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_ORANGE = "minecraft:item.banner.stripe_middle.orange";

    /**
     * Pink Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_PINK = "minecraft:item.banner.stripe_middle.pink";

    /**
     * Purple Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_PURPLE = "minecraft:item.banner.stripe_middle.purple";

    /**
     * Red Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_RED = "minecraft:item.banner.stripe_middle.red";

    /**
     * Light Gray Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_SILVER = "minecraft:item.banner.stripe_middle.silver";

    /**
     * White Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_WHITE = "minecraft:item.banner.stripe_middle.white";

    /**
     * Yellow Fess
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_YELLOW = "minecraft:item.banner.stripe_middle.yellow";

    /**
     * Black Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_BLACK = "minecraft:item.banner.stripe_right.black";

    /**
     * Blue Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_BLUE = "minecraft:item.banner.stripe_right.blue";

    /**
     * Brown Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_BROWN = "minecraft:item.banner.stripe_right.brown";

    /**
     * Cyan Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_CYAN = "minecraft:item.banner.stripe_right.cyan";

    /**
     * Gray Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_GRAY = "minecraft:item.banner.stripe_right.gray";

    /**
     * Green Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_GREEN = "minecraft:item.banner.stripe_right.green";

    /**
     * Light Blue Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_LIGHTBLUE = "minecraft:item.banner.stripe_right.lightBlue";

    /**
     * Lime Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_LIME = "minecraft:item.banner.stripe_right.lime";

    /**
     * Magenta Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_MAGENTA = "minecraft:item.banner.stripe_right.magenta";

    /**
     * Orange Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_ORANGE = "minecraft:item.banner.stripe_right.orange";

    /**
     * Pink Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_PINK = "minecraft:item.banner.stripe_right.pink";

    /**
     * Purple Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_PURPLE = "minecraft:item.banner.stripe_right.purple";

    /**
     * Red Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_RED = "minecraft:item.banner.stripe_right.red";

    /**
     * Light Gray Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_SILVER = "minecraft:item.banner.stripe_right.silver";

    /**
     * White Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_WHITE = "minecraft:item.banner.stripe_right.white";

    /**
     * Yellow Pale Sinister
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_YELLOW = "minecraft:item.banner.stripe_right.yellow";

    /**
     * Black Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_BLACK = "minecraft:item.banner.stripe_top.black";

    /**
     * Blue Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_BLUE = "minecraft:item.banner.stripe_top.blue";

    /**
     * Brown Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_BROWN = "minecraft:item.banner.stripe_top.brown";

    /**
     * Cyan Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_CYAN = "minecraft:item.banner.stripe_top.cyan";

    /**
     * Gray Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_GRAY = "minecraft:item.banner.stripe_top.gray";

    /**
     * Green Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_GREEN = "minecraft:item.banner.stripe_top.green";

    /**
     * Light Blue Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_LIGHTBLUE = "minecraft:item.banner.stripe_top.lightBlue";

    /**
     * Lime Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_LIME = "minecraft:item.banner.stripe_top.lime";

    /**
     * Magenta Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_MAGENTA = "minecraft:item.banner.stripe_top.magenta";

    /**
     * Orange Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_ORANGE = "minecraft:item.banner.stripe_top.orange";

    /**
     * Pink Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_PINK = "minecraft:item.banner.stripe_top.pink";

    /**
     * Purple Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_PURPLE = "minecraft:item.banner.stripe_top.purple";

    /**
     * Red Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_RED = "minecraft:item.banner.stripe_top.red";

    /**
     * Light Gray Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_SILVER = "minecraft:item.banner.stripe_top.silver";

    /**
     * White Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_WHITE = "minecraft:item.banner.stripe_top.white";

    /**
     * Yellow Chief Fess
     */
    String MC_ITEM_BANNER_STRIPE_TOP_YELLOW = "minecraft:item.banner.stripe_top.yellow";

    /**
     * Black Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_BLACK = "minecraft:item.banner.triangle_bottom.black";

    /**
     * Blue Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_BLUE = "minecraft:item.banner.triangle_bottom.blue";

    /**
     * Brown Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_BROWN = "minecraft:item.banner.triangle_bottom.brown";

    /**
     * Cyan Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_CYAN = "minecraft:item.banner.triangle_bottom.cyan";

    /**
     * Gray Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_GRAY = "minecraft:item.banner.triangle_bottom.gray";

    /**
     * Green Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_GREEN = "minecraft:item.banner.triangle_bottom.green";

    /**
     * Light Blue Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_LIGHTBLUE = "minecraft:item.banner.triangle_bottom.lightBlue";

    /**
     * Lime Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_LIME = "minecraft:item.banner.triangle_bottom.lime";

    /**
     * Magenta Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_MAGENTA = "minecraft:item.banner.triangle_bottom.magenta";

    /**
     * Orange Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_ORANGE = "minecraft:item.banner.triangle_bottom.orange";

    /**
     * Pink Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_PINK = "minecraft:item.banner.triangle_bottom.pink";

    /**
     * Purple Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_PURPLE = "minecraft:item.banner.triangle_bottom.purple";

    /**
     * Red Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_RED = "minecraft:item.banner.triangle_bottom.red";

    /**
     * Light Gray Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_SILVER = "minecraft:item.banner.triangle_bottom.silver";

    /**
     * White Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_WHITE = "minecraft:item.banner.triangle_bottom.white";

    /**
     * Yellow Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_YELLOW = "minecraft:item.banner.triangle_bottom.yellow";

    /**
     * Black Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_BLACK = "minecraft:item.banner.triangle_top.black";

    /**
     * Blue Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_BLUE = "minecraft:item.banner.triangle_top.blue";

    /**
     * Brown Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_BROWN = "minecraft:item.banner.triangle_top.brown";

    /**
     * Cyan Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_CYAN = "minecraft:item.banner.triangle_top.cyan";

    /**
     * Gray Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_GRAY = "minecraft:item.banner.triangle_top.gray";

    /**
     * Green Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_GREEN = "minecraft:item.banner.triangle_top.green";

    /**
     * Light Blue Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_LIGHTBLUE = "minecraft:item.banner.triangle_top.lightBlue";

    /**
     * Lime Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_LIME = "minecraft:item.banner.triangle_top.lime";

    /**
     * Magenta Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_MAGENTA = "minecraft:item.banner.triangle_top.magenta";

    /**
     * Orange Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_ORANGE = "minecraft:item.banner.triangle_top.orange";

    /**
     * Pink Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_PINK = "minecraft:item.banner.triangle_top.pink";

    /**
     * Purple Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_PURPLE = "minecraft:item.banner.triangle_top.purple";

    /**
     * Red Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_RED = "minecraft:item.banner.triangle_top.red";

    /**
     * Light Gray Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_SILVER = "minecraft:item.banner.triangle_top.silver";

    /**
     * White Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_WHITE = "minecraft:item.banner.triangle_top.white";

    /**
     * Yellow Inverted Chevron
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_YELLOW = "minecraft:item.banner.triangle_top.yellow";

    /**
     * Black Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_BLACK = "minecraft:item.banner.triangles_bottom.black";

    /**
     * Blue Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_BLUE = "minecraft:item.banner.triangles_bottom.blue";

    /**
     * Brown Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_BROWN = "minecraft:item.banner.triangles_bottom.brown";

    /**
     * Cyan Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_CYAN = "minecraft:item.banner.triangles_bottom.cyan";

    /**
     * Gray Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_GRAY = "minecraft:item.banner.triangles_bottom.gray";

    /**
     * Green Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_GREEN = "minecraft:item.banner.triangles_bottom.green";

    /**
     * Light Blue Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_LIGHTBLUE = "minecraft:item.banner.triangles_bottom.lightBlue";

    /**
     * Lime Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_LIME = "minecraft:item.banner.triangles_bottom.lime";

    /**
     * Magenta Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_MAGENTA = "minecraft:item.banner.triangles_bottom.magenta";

    /**
     * Orange Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_ORANGE = "minecraft:item.banner.triangles_bottom.orange";

    /**
     * Pink Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_PINK = "minecraft:item.banner.triangles_bottom.pink";

    /**
     * Purple Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_PURPLE = "minecraft:item.banner.triangles_bottom.purple";

    /**
     * Red Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_RED = "minecraft:item.banner.triangles_bottom.red";

    /**
     * Light Gray Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_SILVER = "minecraft:item.banner.triangles_bottom.silver";

    /**
     * White Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_WHITE = "minecraft:item.banner.triangles_bottom.white";

    /**
     * Yellow Base Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_YELLOW = "minecraft:item.banner.triangles_bottom.yellow";

    /**
     * Black Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_BLACK = "minecraft:item.banner.triangles_top.black";

    /**
     * Blue Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_BLUE = "minecraft:item.banner.triangles_top.blue";

    /**
     * Brown Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_BROWN = "minecraft:item.banner.triangles_top.brown";

    /**
     * Cyan Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_CYAN = "minecraft:item.banner.triangles_top.cyan";

    /**
     * Gray Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_GRAY = "minecraft:item.banner.triangles_top.gray";

    /**
     * Green Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_GREEN = "minecraft:item.banner.triangles_top.green";

    /**
     * Light Blue Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_LIGHTBLUE = "minecraft:item.banner.triangles_top.lightBlue";

    /**
     * Lime Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_LIME = "minecraft:item.banner.triangles_top.lime";

    /**
     * Magenta Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_MAGENTA = "minecraft:item.banner.triangles_top.magenta";

    /**
     * Orange Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_ORANGE = "minecraft:item.banner.triangles_top.orange";

    /**
     * Pink Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_PINK = "minecraft:item.banner.triangles_top.pink";

    /**
     * Purple Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_PURPLE = "minecraft:item.banner.triangles_top.purple";

    /**
     * Red Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_RED = "minecraft:item.banner.triangles_top.red";

    /**
     * Light Gray Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_SILVER = "minecraft:item.banner.triangles_top.silver";

    /**
     * White Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_WHITE = "minecraft:item.banner.triangles_top.white";

    /**
     * Yellow Chief Indented
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_YELLOW = "minecraft:item.banner.triangles_top.yellow";

    /**
     * White Banner
     */
    String MC_ITEM_BANNER_WHITE_NAME = "minecraft:item.banner.white.name";

    /**
     * Yellow Banner
     */
    String MC_ITEM_BANNER_YELLOW_NAME = "minecraft:item.banner.yellow.name";

    /**
     * Field Masoned
     */
    String MC_ITEM_BANNER_PATTERN_BRICKS = "minecraft:item.banner_pattern.bricks";

    /**
     * Creeper Charge
     */
    String MC_ITEM_BANNER_PATTERN_CREEPER = "minecraft:item.banner_pattern.creeper";

    /**
     * Flow
     */
    String MC_ITEM_BANNER_PATTERN_FLOW = "minecraft:item.banner_pattern.flow";

    /**
     * Flower Charge
     */
    String MC_ITEM_BANNER_PATTERN_FLOWER = "minecraft:item.banner_pattern.flower";

    /**
     * Globe
     */
    String MC_ITEM_BANNER_PATTERN_GLOBE = "minecraft:item.banner_pattern.globe";

    /**
     * Guster
     */
    String MC_ITEM_BANNER_PATTERN_GUSTER = "minecraft:item.banner_pattern.guster";

    /**
     * Banner Pattern
     */
    String MC_ITEM_BANNER_PATTERN_NAME = "minecraft:item.banner_pattern.name";

    /**
     * Snout
     */
    String MC_ITEM_BANNER_PATTERN_PIGLIN = "minecraft:item.banner_pattern.piglin";

    /**
     * Skull Charge
     */
    String MC_ITEM_BANNER_PATTERN_SKULL = "minecraft:item.banner_pattern.skull";

    /**
     * Thing
     */
    String MC_ITEM_BANNER_PATTERN_THING = "minecraft:item.banner_pattern.thing";

    /**
     * Bordure Indented
     */
    String MC_ITEM_BANNER_PATTERN_VINES = "minecraft:item.banner_pattern.vines";

    /**
     * Black Bed
     */
    String MC_ITEM_BED_BLACK_NAME = "minecraft:item.bed.black.name";

    /**
     * Blue Bed
     */
    String MC_ITEM_BED_BLUE_NAME = "minecraft:item.bed.blue.name";

    /**
     * Brown Bed
     */
    String MC_ITEM_BED_BROWN_NAME = "minecraft:item.bed.brown.name";

    /**
     * Cyan Bed
     */
    String MC_ITEM_BED_CYAN_NAME = "minecraft:item.bed.cyan.name";

    /**
     * Gray Bed
     */
    String MC_ITEM_BED_GRAY_NAME = "minecraft:item.bed.gray.name";

    /**
     * Green Bed
     */
    String MC_ITEM_BED_GREEN_NAME = "minecraft:item.bed.green.name";

    /**
     * Light Blue Bed
     */
    String MC_ITEM_BED_LIGHTBLUE_NAME = "minecraft:item.bed.lightBlue.name";

    /**
     * Lime Bed
     */
    String MC_ITEM_BED_LIME_NAME = "minecraft:item.bed.lime.name";

    /**
     * Magenta Bed
     */
    String MC_ITEM_BED_MAGENTA_NAME = "minecraft:item.bed.magenta.name";

    /**
     * Orange Bed
     */
    String MC_ITEM_BED_ORANGE_NAME = "minecraft:item.bed.orange.name";

    /**
     * Pink Bed
     */
    String MC_ITEM_BED_PINK_NAME = "minecraft:item.bed.pink.name";

    /**
     * Purple Bed
     */
    String MC_ITEM_BED_PURPLE_NAME = "minecraft:item.bed.purple.name";

    /**
     * Red Bed
     */
    String MC_ITEM_BED_RED_NAME = "minecraft:item.bed.red.name";

    /**
     * Light Gray Bed
     */
    String MC_ITEM_BED_SILVER_NAME = "minecraft:item.bed.silver.name";

    /**
     * White Bed
     */
    String MC_ITEM_BED_WHITE_NAME = "minecraft:item.bed.white.name";

    /**
     * Yellow Bed
     */
    String MC_ITEM_BED_YELLOW_NAME = "minecraft:item.bed.yellow.name";

    /**
     * Raw Beef
     */
    String MC_ITEM_BEEF_NAME = "minecraft:item.beef.name";

    /**
     * Beetroot
     */
    String MC_ITEM_BEETROOT_NAME = "minecraft:item.beetroot.name";

    /**
     * Beetroot Seeds
     */
    String MC_ITEM_BEETROOT_SEEDS_NAME = "minecraft:item.beetroot_seeds.name";

    /**
     * Beetroot Soup
     */
    String MC_ITEM_BEETROOT_SOUP_NAME = "minecraft:item.beetroot_soup.name";

    /**
     * Bell
     */
    String MC_ITEM_BELL_NAME = "minecraft:item.bell.name";

    /**
     * Birch Door
     */
    String MC_ITEM_BIRCH_DOOR_NAME = "minecraft:item.birch_door.name";

    /**
     * Birch Hanging Sign
     */
    String MC_ITEM_BIRCH_HANGING_SIGN_NAME = "minecraft:item.birch_hanging_sign.name";

    /**
     * Birch Sign
     */
    String MC_ITEM_BIRCH_SIGN_NAME = "minecraft:item.birch_sign.name";

    /**
     * Black Bundle
     */
    String MC_ITEM_BLACK_BUNDLE = "minecraft:item.black_bundle";

    /**
     * Black Harness
     */
    String MC_ITEM_BLACK_HARNESS_NAME = "minecraft:item.black_harness.name";

    /**
     * Blade Pottery Sherd
     */
    String MC_ITEM_BLADE_POTTERY_SHERD_NAME = "minecraft:item.blade_pottery_sherd.name";

    /**
     * Blaze Powder
     */
    String MC_ITEM_BLAZE_POWDER_NAME = "minecraft:item.blaze_powder.name";

    /**
     * Blaze Rod
     */
    String MC_ITEM_BLAZE_ROD_NAME = "minecraft:item.blaze_rod.name";

    /**
     * Blue Bundle
     */
    String MC_ITEM_BLUE_BUNDLE = "minecraft:item.blue_bundle";

    /**
     * Blue Egg
     */
    String MC_ITEM_BLUE_EGG_NAME = "minecraft:item.blue_egg.name";

    /**
     * Blue Harness
     */
    String MC_ITEM_BLUE_HARNESS_NAME = "minecraft:item.blue_harness.name";

    /**
     * Acacia Boat
     */
    String MC_ITEM_BOAT_ACACIA_NAME = "minecraft:item.boat.acacia.name";

    /**
     * Bamboo Raft
     */
    String MC_ITEM_BOAT_BAMBOO_NAME = "minecraft:item.boat.bamboo.name";

    /**
     * Dark Oak Boat
     */
    String MC_ITEM_BOAT_BIG_OAK_NAME = "minecraft:item.boat.big_oak.name";

    /**
     * Birch Boat
     */
    String MC_ITEM_BOAT_BIRCH_NAME = "minecraft:item.boat.birch.name";

    /**
     * Cherry Boat
     */
    String MC_ITEM_BOAT_CHERRY_NAME = "minecraft:item.boat.cherry.name";

    /**
     * Jungle Boat
     */
    String MC_ITEM_BOAT_JUNGLE_NAME = "minecraft:item.boat.jungle.name";

    /**
     * Mangrove Boat
     */
    String MC_ITEM_BOAT_MANGROVE_NAME = "minecraft:item.boat.mangrove.name";

    /**
     * Oak Boat
     */
    String MC_ITEM_BOAT_OAK_NAME = "minecraft:item.boat.oak.name";

    /**
     * Pale Oak Boat
     */
    String MC_ITEM_BOAT_PALE_OAK_NAME = "minecraft:item.boat.pale_oak.name";

    /**
     * Spruce Boat
     */
    String MC_ITEM_BOAT_SPRUCE_NAME = "minecraft:item.boat.spruce.name";

    /**
     * Bolt Armor Trim
     */
    String MC_ITEM_BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.bolt_armor_trim_smithing_template.name";

    /**
     * Bone
     */
    String MC_ITEM_BONE_NAME = "minecraft:item.bone.name";

    /**
     * Book
     */
    String MC_ITEM_BOOK_NAME = "minecraft:item.book.name";

    /**
     * Bordure Indented Banner Pattern
     */
    String MC_ITEM_BORDURE_INDENTED_BANNER_PATTERN_NAME = "minecraft:item.bordure_indented_banner_pattern.name";

    /**
     * Bow
     */
    String MC_ITEM_BOW_NAME = "minecraft:item.bow.name";

    /**
     * Bowl
     */
    String MC_ITEM_BOWL_NAME = "minecraft:item.bowl.name";

    /**
     * Bread
     */
    String MC_ITEM_BREAD_NAME = "minecraft:item.bread.name";

    /**
     * Breeze Rod
     */
    String MC_ITEM_BREEZE_ROD_NAME = "minecraft:item.breeze_rod.name";

    /**
     * Brewer Pottery Sherd
     */
    String MC_ITEM_BREWER_POTTERY_SHERD_NAME = "minecraft:item.brewer_pottery_sherd.name";

    /**
     * Brewing Stand
     */
    String MC_ITEM_BREWING_STAND_NAME = "minecraft:item.brewing_stand.name";

    /**
     * Brick
     */
    String MC_ITEM_BRICK_NAME = "minecraft:item.brick.name";

    /**
     * Brown Bundle
     */
    String MC_ITEM_BROWN_BUNDLE = "minecraft:item.brown_bundle";

    /**
     * Brown Egg
     */
    String MC_ITEM_BROWN_EGG_NAME = "minecraft:item.brown_egg.name";

    /**
     * Brown Harness
     */
    String MC_ITEM_BROWN_HARNESS_NAME = "minecraft:item.brown_harness.name";

    /**
     * Brush
     */
    String MC_ITEM_BRUSH_NAME = "minecraft:item.brush.name";

    /**
     * Bucket
     */
    String MC_ITEM_BUCKET_NAME = "minecraft:item.bucket.name";

    /**
     * Bucket of Axolotl
     */
    String MC_ITEM_BUCKETAXOLOTL_NAME = "minecraft:item.bucketAxolotl.name";

    /**
     * Bucket of 
     */
    String MC_ITEM_BUCKETCUSTOMFISH_NAME = "minecraft:item.bucketCustomFish.name";

    /**
     * Bucket of Cod
     */
    String MC_ITEM_BUCKETFISH_NAME = "minecraft:item.bucketFish.name";

    /**
     * Lava Bucket
     */
    String MC_ITEM_BUCKETLAVA_NAME = "minecraft:item.bucketLava.name";

    /**
     * Powder Snow Bucket
     */
    String MC_ITEM_BUCKETPOWDERSNOW_NAME = "minecraft:item.bucketPowderSnow.name";

    /**
     * Bucket of Pufferfish
     */
    String MC_ITEM_BUCKETPUFFER_NAME = "minecraft:item.bucketPuffer.name";

    /**
     * Bucket of Salmon
     */
    String MC_ITEM_BUCKETSALMON_NAME = "minecraft:item.bucketSalmon.name";

    /**
     * Bucket of Tadpole
     */
    String MC_ITEM_BUCKETTADPOLE_NAME = "minecraft:item.bucketTadpole.name";

    /**
     * Bucket of Tropical Fish
     */
    String MC_ITEM_BUCKETTROPICAL_NAME = "minecraft:item.bucketTropical.name";

    /**
     * Water Bucket
     */
    String MC_ITEM_BUCKETWATER_NAME = "minecraft:item.bucketWater.name";

    /**
     * Bundle
     */
    String MC_ITEM_BUNDLE = "minecraft:item.bundle";

    /**
     * Empty
     */
    String MC_ITEM_BUNDLE_EMPTY = "minecraft:item.bundle.empty";

    /**
     * Can hold a mixed stack of items
     */
    String MC_ITEM_BUNDLE_EMPTY_DESCRIPTION = "minecraft:item.bundle.empty.description";

    /**
     * Full
     */
    String MC_ITEM_BUNDLE_FULL = "minecraft:item.bundle.full";

    /**
     * %s left
     */
    String MC_ITEM_BUNDLE_FULLNESS = "minecraft:item.bundle.fullness";

    /**
     * Burn Pottery Sherd
     */
    String MC_ITEM_BURN_POTTERY_SHERD_NAME = "minecraft:item.burn_pottery_sherd.name";

    /**
     * Cake
     */
    String MC_ITEM_CAKE_NAME = "minecraft:item.cake.name";

    /**
     * Camera
     */
    String MC_ITEM_CAMERA_NAME = "minecraft:item.camera.name";

    /**
     * Can break:
     */
    String MC_ITEM_CANBREAK = "minecraft:item.canBreak";

    /**
     * Can be placed on:
     */
    String MC_ITEM_CANPLACE = "minecraft:item.canPlace";

    /**
     * Carrot
     */
    String MC_ITEM_CARROT_NAME = "minecraft:item.carrot.name";

    /**
     * Carrot on a Stick
     */
    String MC_ITEM_CARROTONASTICK_NAME = "minecraft:item.carrotOnAStick.name";

    /**
     * Cauldron
     */
    String MC_ITEM_CAULDRON_NAME = "minecraft:item.cauldron.name";

    /**
     * Chainmail Boots
     */
    String MC_ITEM_CHAINMAIL_BOOTS_NAME = "minecraft:item.chainmail_boots.name";

    /**
     * Chainmail Chestplate
     */
    String MC_ITEM_CHAINMAIL_CHESTPLATE_NAME = "minecraft:item.chainmail_chestplate.name";

    /**
     * Chainmail Helmet
     */
    String MC_ITEM_CHAINMAIL_HELMET_NAME = "minecraft:item.chainmail_helmet.name";

    /**
     * Chainmail Leggings
     */
    String MC_ITEM_CHAINMAIL_LEGGINGS_NAME = "minecraft:item.chainmail_leggings.name";

    /**
     * Charcoal
     */
    String MC_ITEM_CHARCOAL_NAME = "minecraft:item.charcoal.name";

    /**
     * Cherry Door
     */
    String MC_ITEM_CHERRY_DOOR_NAME = "minecraft:item.cherry_door.name";

    /**
     * Cherry Hanging Sign
     */
    String MC_ITEM_CHERRY_HANGING_SIGN_NAME = "minecraft:item.cherry_hanging_sign.name";

    /**
     * Cherry Sign
     */
    String MC_ITEM_CHERRY_SIGN_NAME = "minecraft:item.cherry_sign.name";

    /**
     * Acacia Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_ACACIA_NAME = "minecraft:item.chest_boat.acacia.name";

    /**
     * Bamboo Raft with Chest
     */
    String MC_ITEM_CHEST_BOAT_BAMBOO_NAME = "minecraft:item.chest_boat.bamboo.name";

    /**
     * Dark Oak Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_BIG_OAK_NAME = "minecraft:item.chest_boat.big_oak.name";

    /**
     * Birch Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_BIRCH_NAME = "minecraft:item.chest_boat.birch.name";

    /**
     * Cherry Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_CHERRY_NAME = "minecraft:item.chest_boat.cherry.name";

    /**
     * Jungle Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_JUNGLE_NAME = "minecraft:item.chest_boat.jungle.name";

    /**
     * Mangrove Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_MANGROVE_NAME = "minecraft:item.chest_boat.mangrove.name";

    /**
     * Oak Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_OAK_NAME = "minecraft:item.chest_boat.oak.name";

    /**
     * Pale Oak Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_PALE_OAK_NAME = "minecraft:item.chest_boat.pale_oak.name";

    /**
     * Spruce Boat with Chest
     */
    String MC_ITEM_CHEST_BOAT_SPRUCE_NAME = "minecraft:item.chest_boat.spruce.name";

    /**
     * Minecart with Chest
     */
    String MC_ITEM_CHEST_MINECART_NAME = "minecraft:item.chest_minecart.name";

    /**
     * Raw Chicken
     */
    String MC_ITEM_CHICKEN_NAME = "minecraft:item.chicken.name";

    /**
     * Chorus Fruit
     */
    String MC_ITEM_CHORUS_FRUIT_NAME = "minecraft:item.chorus_fruit.name";

    /**
     * Popped Chorus Fruit
     */
    String MC_ITEM_CHORUS_FRUIT_POPPED_NAME = "minecraft:item.chorus_fruit_popped.name";

    /**
     * Clay Ball
     */
    String MC_ITEM_CLAY_BALL_NAME = "minecraft:item.clay_ball.name";

    /**
     * Clock
     */
    String MC_ITEM_CLOCK_NAME = "minecraft:item.clock.name";

    /**
     * Tropical Fish
     */
    String MC_ITEM_CLOWNFISH_NAME = "minecraft:item.clownfish.name";

    /**
     * Coal
     */
    String MC_ITEM_COAL_NAME = "minecraft:item.coal.name";

    /**
     * Coast Armor Trim
     */
    String MC_ITEM_COAST_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.coast_armor_trim_smithing_template.name";

    /**
     * Minecart with Command Block
     */
    String MC_ITEM_COMMAND_BLOCK_MINECART_NAME = "minecraft:item.command_block_minecart.name";

    /**
     * Redstone Comparator
     */
    String MC_ITEM_COMPARATOR_NAME = "minecraft:item.comparator.name";

    /**
     * Compass
     */
    String MC_ITEM_COMPASS_NAME = "minecraft:item.compass.name";

    /**
     * Steak
     */
    String MC_ITEM_COOKED_BEEF_NAME = "minecraft:item.cooked_beef.name";

    /**
     * Cooked Chicken
     */
    String MC_ITEM_COOKED_CHICKEN_NAME = "minecraft:item.cooked_chicken.name";

    /**
     * Cooked Cod
     */
    String MC_ITEM_COOKED_FISH_NAME = "minecraft:item.cooked_fish.name";

    /**
     * Cooked Porkchop
     */
    String MC_ITEM_COOKED_PORKCHOP_NAME = "minecraft:item.cooked_porkchop.name";

    /**
     * Cooked Rabbit
     */
    String MC_ITEM_COOKED_RABBIT_NAME = "minecraft:item.cooked_rabbit.name";

    /**
     * Cooked Salmon
     */
    String MC_ITEM_COOKED_SALMON_NAME = "minecraft:item.cooked_salmon.name";

    /**
     * Cookie
     */
    String MC_ITEM_COOKIE_NAME = "minecraft:item.cookie.name";

    /**
     * Copper Axe
     */
    String MC_ITEM_COPPER_AXE_NAME = "minecraft:item.copper_axe.name";

    /**
     * Copper Boots
     */
    String MC_ITEM_COPPER_BOOTS_NAME = "minecraft:item.copper_boots.name";

    /**
     * Copper Chestplate
     */
    String MC_ITEM_COPPER_CHESTPLATE_NAME = "minecraft:item.copper_chestplate.name";

    /**
     * Copper Door
     */
    String MC_ITEM_COPPER_DOOR_NAME = "minecraft:item.copper_door.name";

    /**
     * Copper Helmet
     */
    String MC_ITEM_COPPER_HELMET_NAME = "minecraft:item.copper_helmet.name";

    /**
     * Copper Hoe
     */
    String MC_ITEM_COPPER_HOE_NAME = "minecraft:item.copper_hoe.name";

    /**
     * Copper Horse Armor
     */
    String MC_ITEM_COPPER_HORSE_ARMOR_NAME = "minecraft:item.copper_horse_armor.name";

    /**
     * Copper Ingot
     */
    String MC_ITEM_COPPER_INGOT_NAME = "minecraft:item.copper_ingot.name";

    /**
     * Copper Leggings
     */
    String MC_ITEM_COPPER_LEGGINGS_NAME = "minecraft:item.copper_leggings.name";

    /**
     * Copper Nugget
     */
    String MC_ITEM_COPPER_NUGGET_NAME = "minecraft:item.copper_nugget.name";

    /**
     * Copper Pickaxe
     */
    String MC_ITEM_COPPER_PICKAXE_NAME = "minecraft:item.copper_pickaxe.name";

    /**
     * Copper Shovel
     */
    String MC_ITEM_COPPER_SHOVEL_NAME = "minecraft:item.copper_shovel.name";

    /**
     * Copper Sword
     */
    String MC_ITEM_COPPER_SWORD_NAME = "minecraft:item.copper_sword.name";

    /**
     * Creeper Charge Banner Pattern
     */
    String MC_ITEM_CREEPER_BANNER_PATTERN_NAME = "minecraft:item.creeper_banner_pattern.name";

    /**
     * Crimson Door
     */
    String MC_ITEM_CRIMSON_DOOR_NAME = "minecraft:item.crimson_door.name";

    /**
     * Crimson Hanging Sign
     */
    String MC_ITEM_CRIMSON_HANGING_SIGN_NAME = "minecraft:item.crimson_hanging_sign.name";

    /**
     * Crimson Sign
     */
    String MC_ITEM_CRIMSON_SIGN_NAME = "minecraft:item.crimson_sign.name";

    /**
     * Crossbow
     */
    String MC_ITEM_CROSSBOW_NAME = "minecraft:item.crossbow.name";

    /**
     * Has Custom Properties
     */
    String MC_ITEM_CUSTOMPROPERTIES = "minecraft:item.customProperties";

    /**
     * Cyan Bundle
     */
    String MC_ITEM_CYAN_BUNDLE = "minecraft:item.cyan_bundle";

    /**
     * Cyan Harness
     */
    String MC_ITEM_CYAN_HARNESS_NAME = "minecraft:item.cyan_harness.name";

    /**
     * Danger Pottery Sherd
     */
    String MC_ITEM_DANGER_POTTERY_SHERD_NAME = "minecraft:item.danger_pottery_sherd.name";

    /**
     * Dark Oak Door
     */
    String MC_ITEM_DARK_OAK_DOOR_NAME = "minecraft:item.dark_oak_door.name";

    /**
     * Dark Oak Hanging Sign
     */
    String MC_ITEM_DARK_OAK_HANGING_SIGN_NAME = "minecraft:item.dark_oak_hanging_sign.name";

    /**
     * Dark Oak Sign
     */
    String MC_ITEM_DARKOAK_SIGN_NAME = "minecraft:item.darkoak_sign.name";

    /**
     * Diamond
     */
    String MC_ITEM_DIAMOND_NAME = "minecraft:item.diamond.name";

    /**
     * Diamond Axe
     */
    String MC_ITEM_DIAMOND_AXE_NAME = "minecraft:item.diamond_axe.name";

    /**
     * Diamond Boots
     */
    String MC_ITEM_DIAMOND_BOOTS_NAME = "minecraft:item.diamond_boots.name";

    /**
     * Diamond Chestplate
     */
    String MC_ITEM_DIAMOND_CHESTPLATE_NAME = "minecraft:item.diamond_chestplate.name";

    /**
     * Diamond Helmet
     */
    String MC_ITEM_DIAMOND_HELMET_NAME = "minecraft:item.diamond_helmet.name";

    /**
     * Diamond Hoe
     */
    String MC_ITEM_DIAMOND_HOE_NAME = "minecraft:item.diamond_hoe.name";

    /**
     * Diamond Leggings
     */
    String MC_ITEM_DIAMOND_LEGGINGS_NAME = "minecraft:item.diamond_leggings.name";

    /**
     * Diamond Pickaxe
     */
    String MC_ITEM_DIAMOND_PICKAXE_NAME = "minecraft:item.diamond_pickaxe.name";

    /**
     * Diamond Shovel
     */
    String MC_ITEM_DIAMOND_SHOVEL_NAME = "minecraft:item.diamond_shovel.name";

    /**
     * Diamond Sword
     */
    String MC_ITEM_DIAMOND_SWORD_NAME = "minecraft:item.diamond_sword.name";

    /**
     * Disc Fragment
     */
    String MC_ITEM_DISC_FRAGMENT_NAME = "minecraft:item.disc_fragment.name";

    /**
     * Music Disc - 5
     */
    String MC_ITEM_DISC_FRAGMENT_5_DESC = "minecraft:item.disc_fragment_5.desc";

    /**
     * Dragon's Breath
     */
    String MC_ITEM_DRAGON_BREATH_NAME = "minecraft:item.dragon_breath.name";

    /**
     * Dried Kelp
     */
    String MC_ITEM_DRIED_KELP_NAME = "minecraft:item.dried_kelp.name";

    /**
     * Dune Armor Trim
     */
    String MC_ITEM_DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.dune_armor_trim_smithing_template.name";

    /**
     * Ink Sac
     */
    String MC_ITEM_DYE_BLACK_NAME = "minecraft:item.dye.black.name";

    /**
     * Black Dye
     */
    String MC_ITEM_DYE_BLACK_NEW_NAME = "minecraft:item.dye.black_new.name";

    /**
     * Lapis Lazuli
     */
    String MC_ITEM_DYE_BLUE_NAME = "minecraft:item.dye.blue.name";

    /**
     * Blue Dye
     */
    String MC_ITEM_DYE_BLUE_NEW_NAME = "minecraft:item.dye.blue_new.name";

    /**
     * Cocoa Beans
     */
    String MC_ITEM_DYE_BROWN_NAME = "minecraft:item.dye.brown.name";

    /**
     * Brown Dye
     */
    String MC_ITEM_DYE_BROWN_NEW_NAME = "minecraft:item.dye.brown_new.name";

    /**
     * Cyan Dye
     */
    String MC_ITEM_DYE_CYAN_NAME = "minecraft:item.dye.cyan.name";

    /**
     * Gray Dye
     */
    String MC_ITEM_DYE_GRAY_NAME = "minecraft:item.dye.gray.name";

    /**
     * Green Dye
     */
    String MC_ITEM_DYE_GREEN_NAME = "minecraft:item.dye.green.name";

    /**
     * Light Blue Dye
     */
    String MC_ITEM_DYE_LIGHTBLUE_NAME = "minecraft:item.dye.lightBlue.name";

    /**
     * Lime Dye
     */
    String MC_ITEM_DYE_LIME_NAME = "minecraft:item.dye.lime.name";

    /**
     * Magenta Dye
     */
    String MC_ITEM_DYE_MAGENTA_NAME = "minecraft:item.dye.magenta.name";

    /**
     * Orange Dye
     */
    String MC_ITEM_DYE_ORANGE_NAME = "minecraft:item.dye.orange.name";

    /**
     * Pink Dye
     */
    String MC_ITEM_DYE_PINK_NAME = "minecraft:item.dye.pink.name";

    /**
     * Purple Dye
     */
    String MC_ITEM_DYE_PURPLE_NAME = "minecraft:item.dye.purple.name";

    /**
     * Red Dye
     */
    String MC_ITEM_DYE_RED_NAME = "minecraft:item.dye.red.name";

    /**
     * Light Gray Dye
     */
    String MC_ITEM_DYE_SILVER_NAME = "minecraft:item.dye.silver.name";

    /**
     * Bone Meal
     */
    String MC_ITEM_DYE_WHITE_NAME = "minecraft:item.dye.white.name";

    /**
     * White Dye
     */
    String MC_ITEM_DYE_WHITE_NEW_NAME = "minecraft:item.dye.white_new.name";

    /**
     * Yellow Dye
     */
    String MC_ITEM_DYE_YELLOW_NAME = "minecraft:item.dye.yellow.name";

    /**
     * Dyed
     */
    String MC_ITEM_DYED = "minecraft:item.dyed";

    /**
     * Echo Shard
     */
    String MC_ITEM_ECHO_SHARD_NAME = "minecraft:item.echo_shard.name";

    /**
     * Egg
     */
    String MC_ITEM_EGG_NAME = "minecraft:item.egg.name";

    /**
     * Elytra
     */
    String MC_ITEM_ELYTRA_NAME = "minecraft:item.elytra.name";

    /**
     * Emerald
     */
    String MC_ITEM_EMERALD_NAME = "minecraft:item.emerald.name";

    /**
     * Empty Locator Map
     */
    String MC_ITEM_EMPTYLOCATORMAP_NAME = "minecraft:item.emptyLocatorMap.name";

    /**
     * Empty Map
     */
    String MC_ITEM_EMPTYMAP_NAME = "minecraft:item.emptyMap.name";

    /**
     * Enchanted Book
     */
    String MC_ITEM_ENCHANTED_BOOK_NAME = "minecraft:item.enchanted_book.name";

    /**
     * End Crystal
     */
    String MC_ITEM_END_CRYSTAL_NAME = "minecraft:item.end_crystal.name";

    /**
     * Eye of Ender
     */
    String MC_ITEM_ENDER_EYE_NAME = "minecraft:item.ender_eye.name";

    /**
     * Ender Pearl
     */
    String MC_ITEM_ENDER_PEARL_NAME = "minecraft:item.ender_pearl.name";

    /**
     * Bottle o' Enchanting
     */
    String MC_ITEM_EXPERIENCE_BOTTLE_NAME = "minecraft:item.experience_bottle.name";

    /**
     * Explorer Pottery Sherd
     */
    String MC_ITEM_EXPLORER_POTTERY_SHERD_NAME = "minecraft:item.explorer_pottery_sherd.name";

    /**
     * Exposed Copper Door
     */
    String MC_ITEM_EXPOSED_COPPER_DOOR_NAME = "minecraft:item.exposed_copper_door.name";

    /**
     * Eye Armor Trim
     */
    String MC_ITEM_EYE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.eye_armor_trim_smithing_template.name";

    /**
     * Feather
     */
    String MC_ITEM_FEATHER_NAME = "minecraft:item.feather.name";

    /**
     * Fermented Spider Eye
     */
    String MC_ITEM_FERMENTED_SPIDER_EYE_NAME = "minecraft:item.fermented_spider_eye.name";

    /**
     * Field Masoned Banner Pattern
     */
    String MC_ITEM_FIELD_MASONED_BANNER_PATTERN_NAME = "minecraft:item.field_masoned_banner_pattern.name";

    /**
     * Fire Charge
     */
    String MC_ITEM_FIREBALL_NAME = "minecraft:item.fireball.name";

    /**
     * Flight Duration:
     */
    String MC_ITEM_FIREWORKS_FLIGHT = "minecraft:item.fireworks.flight";

    /**
     * Firework Rocket
     */
    String MC_ITEM_FIREWORKS_NAME = "minecraft:item.fireworks.name";

    /**
     * Black
     */
    String MC_ITEM_FIREWORKSCHARGE_BLACK = "minecraft:item.fireworksCharge.black";

    /**
     * Blue
     */
    String MC_ITEM_FIREWORKSCHARGE_BLUE = "minecraft:item.fireworksCharge.blue";

    /**
     * Brown
     */
    String MC_ITEM_FIREWORKSCHARGE_BROWN = "minecraft:item.fireworksCharge.brown";

    /**
     * Custom
     */
    String MC_ITEM_FIREWORKSCHARGE_CUSTOMCOLOR = "minecraft:item.fireworksCharge.customColor";

    /**
     * Cyan
     */
    String MC_ITEM_FIREWORKSCHARGE_CYAN = "minecraft:item.fireworksCharge.cyan";

    /**
     * Fade to
     */
    String MC_ITEM_FIREWORKSCHARGE_FADETO = "minecraft:item.fireworksCharge.fadeTo";

    /**
     * Twinkle
     */
    String MC_ITEM_FIREWORKSCHARGE_FLICKER = "minecraft:item.fireworksCharge.flicker";

    /**
     * Gray
     */
    String MC_ITEM_FIREWORKSCHARGE_GRAY = "minecraft:item.fireworksCharge.gray";

    /**
     * Green
     */
    String MC_ITEM_FIREWORKSCHARGE_GREEN = "minecraft:item.fireworksCharge.green";

    /**
     * Light Blue
     */
    String MC_ITEM_FIREWORKSCHARGE_LIGHTBLUE = "minecraft:item.fireworksCharge.lightBlue";

    /**
     * Lime
     */
    String MC_ITEM_FIREWORKSCHARGE_LIME = "minecraft:item.fireworksCharge.lime";

    /**
     * Magenta
     */
    String MC_ITEM_FIREWORKSCHARGE_MAGENTA = "minecraft:item.fireworksCharge.magenta";

    /**
     * Firework Star
     */
    String MC_ITEM_FIREWORKSCHARGE_NAME = "minecraft:item.fireworksCharge.name";

    /**
     * Orange
     */
    String MC_ITEM_FIREWORKSCHARGE_ORANGE = "minecraft:item.fireworksCharge.orange";

    /**
     * Pink
     */
    String MC_ITEM_FIREWORKSCHARGE_PINK = "minecraft:item.fireworksCharge.pink";

    /**
     * Purple
     */
    String MC_ITEM_FIREWORKSCHARGE_PURPLE = "minecraft:item.fireworksCharge.purple";

    /**
     * Red
     */
    String MC_ITEM_FIREWORKSCHARGE_RED = "minecraft:item.fireworksCharge.red";

    /**
     * Light Gray
     */
    String MC_ITEM_FIREWORKSCHARGE_SILVER = "minecraft:item.fireworksCharge.silver";

    /**
     * Trail
     */
    String MC_ITEM_FIREWORKSCHARGE_TRAIL = "minecraft:item.fireworksCharge.trail";

    /**
     * Unknown Shape
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE = "minecraft:item.fireworksCharge.type";

    /**
     * Small Ball
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_0 = "minecraft:item.fireworksCharge.type.0";

    /**
     * Large Ball
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_1 = "minecraft:item.fireworksCharge.type.1";

    /**
     * Star-shaped
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_2 = "minecraft:item.fireworksCharge.type.2";

    /**
     * Creeper-shaped
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_3 = "minecraft:item.fireworksCharge.type.3";

    /**
     * Burst
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_4 = "minecraft:item.fireworksCharge.type.4";

    /**
     * White
     */
    String MC_ITEM_FIREWORKSCHARGE_WHITE = "minecraft:item.fireworksCharge.white";

    /**
     * Yellow
     */
    String MC_ITEM_FIREWORKSCHARGE_YELLOW = "minecraft:item.fireworksCharge.yellow";

    /**
     * Raw Cod
     */
    String MC_ITEM_FISH_NAME = "minecraft:item.fish.name";

    /**
     * Fishing Rod
     */
    String MC_ITEM_FISHING_ROD_NAME = "minecraft:item.fishing_rod.name";

    /**
     * Flint
     */
    String MC_ITEM_FLINT_NAME = "minecraft:item.flint.name";

    /**
     * Flint and Steel
     */
    String MC_ITEM_FLINT_AND_STEEL_NAME = "minecraft:item.flint_and_steel.name";

    /**
     * Flow Armor Trim
     */
    String MC_ITEM_FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.flow_armor_trim_smithing_template.name";

    /**
     * Flow Banner Pattern
     */
    String MC_ITEM_FLOW_BANNER_PATTERN_NAME = "minecraft:item.flow_banner_pattern.name";

    /**
     * Flow Pottery Sherd
     */
    String MC_ITEM_FLOW_POTTERY_SHERD_NAME = "minecraft:item.flow_pottery_sherd.name";

    /**
     * Flower Charge Banner Pattern
     */
    String MC_ITEM_FLOWER_BANNER_PATTERN_NAME = "minecraft:item.flower_banner_pattern.name";

    /**
     * Flower Pot
     */
    String MC_ITEM_FLOWER_POT_NAME = "minecraft:item.flower_pot.name";

    /**
     * Item Frame
     */
    String MC_ITEM_FRAME_NAME = "minecraft:item.frame.name";

    /**
     * Friend Pottery Sherd
     */
    String MC_ITEM_FRIEND_POTTERY_SHERD_NAME = "minecraft:item.friend_pottery_sherd.name";

    /**
     * Ghast Tear
     */
    String MC_ITEM_GHAST_TEAR_NAME = "minecraft:item.ghast_tear.name";

    /**
     * Glass Bottle
     */
    String MC_ITEM_GLASS_BOTTLE_NAME = "minecraft:item.glass_bottle.name";

    /**
     * Globe Banner Pattern
     */
    String MC_ITEM_GLOBE_BANNER_PATTERN_NAME = "minecraft:item.globe_banner_pattern.name";

    /**
     * Glow Berries
     */
    String MC_ITEM_GLOW_BERRIES_NAME = "minecraft:item.glow_berries.name";

    /**
     * Glow Item Frame
     */
    String MC_ITEM_GLOW_FRAME_NAME = "minecraft:item.glow_frame.name";

    /**
     * Glow Ink Sac
     */
    String MC_ITEM_GLOW_INK_SAC_NAME = "minecraft:item.glow_ink_sac.name";

    /**
     * Glowstone Dust
     */
    String MC_ITEM_GLOWSTONE_DUST_NAME = "minecraft:item.glowstone_dust.name";

    /**
     * Goat Horn
     */
    String MC_ITEM_GOAT_HORN_NAME = "minecraft:item.goat_horn.name";

    /**
     * Gold Ingot
     */
    String MC_ITEM_GOLD_INGOT_NAME = "minecraft:item.gold_ingot.name";

    /**
     * Gold Nugget
     */
    String MC_ITEM_GOLD_NUGGET_NAME = "minecraft:item.gold_nugget.name";

    /**
     * Golden Apple
     */
    String MC_ITEM_GOLDEN_APPLE_NAME = "minecraft:item.golden_apple.name";

    /**
     * Golden Axe
     */
    String MC_ITEM_GOLDEN_AXE_NAME = "minecraft:item.golden_axe.name";

    /**
     * Golden Boots
     */
    String MC_ITEM_GOLDEN_BOOTS_NAME = "minecraft:item.golden_boots.name";

    /**
     * Golden Carrot
     */
    String MC_ITEM_GOLDEN_CARROT_NAME = "minecraft:item.golden_carrot.name";

    /**
     * Golden Chestplate
     */
    String MC_ITEM_GOLDEN_CHESTPLATE_NAME = "minecraft:item.golden_chestplate.name";

    /**
     * Golden Helmet
     */
    String MC_ITEM_GOLDEN_HELMET_NAME = "minecraft:item.golden_helmet.name";

    /**
     * Golden Hoe
     */
    String MC_ITEM_GOLDEN_HOE_NAME = "minecraft:item.golden_hoe.name";

    /**
     * Golden Leggings
     */
    String MC_ITEM_GOLDEN_LEGGINGS_NAME = "minecraft:item.golden_leggings.name";

    /**
     * Golden Pickaxe
     */
    String MC_ITEM_GOLDEN_PICKAXE_NAME = "minecraft:item.golden_pickaxe.name";

    /**
     * Golden Shovel
     */
    String MC_ITEM_GOLDEN_SHOVEL_NAME = "minecraft:item.golden_shovel.name";

    /**
     * Golden Sword
     */
    String MC_ITEM_GOLDEN_SWORD_NAME = "minecraft:item.golden_sword.name";

    /**
     * Gray Bundle
     */
    String MC_ITEM_GRAY_BUNDLE = "minecraft:item.gray_bundle";

    /**
     * Gray Harness
     */
    String MC_ITEM_GRAY_HARNESS_NAME = "minecraft:item.gray_harness.name";

    /**
     * Green Bundle
     */
    String MC_ITEM_GREEN_BUNDLE = "minecraft:item.green_bundle";

    /**
     * Green Harness
     */
    String MC_ITEM_GREEN_HARNESS_NAME = "minecraft:item.green_harness.name";

    /**
     * Gunpowder
     */
    String MC_ITEM_GUNPOWDER_NAME = "minecraft:item.gunpowder.name";

    /**
     * Guster Banner Pattern
     */
    String MC_ITEM_GUSTER_BANNER_PATTERN_NAME = "minecraft:item.guster_banner_pattern.name";

    /**
     * Guster Pottery Sherd
     */
    String MC_ITEM_GUSTER_POTTERY_SHERD_NAME = "minecraft:item.guster_pottery_sherd.name";

    /**
     * Heart of the Sea
     */
    String MC_ITEM_HEART_OF_THE_SEA_NAME = "minecraft:item.heart_of_the_sea.name";

    /**
     * Heart Pottery Sherd
     */
    String MC_ITEM_HEART_POTTERY_SHERD_NAME = "minecraft:item.heart_pottery_sherd.name";

    /**
     * Heartbreak Pottery Sherd
     */
    String MC_ITEM_HEARTBREAK_POTTERY_SHERD_NAME = "minecraft:item.heartbreak_pottery_sherd.name";

    /**
     * Honey Bottle
     */
    String MC_ITEM_HONEY_BOTTLE_NAME = "minecraft:item.honey_bottle.name";

    /**
     * Honeycomb
     */
    String MC_ITEM_HONEYCOMB_NAME = "minecraft:item.honeycomb.name";

    /**
     * Minecart with Hopper
     */
    String MC_ITEM_HOPPER_MINECART_NAME = "minecraft:item.hopper_minecart.name";

    /**
     * Diamond Horse Armor
     */
    String MC_ITEM_HORSEARMORDIAMOND_NAME = "minecraft:item.horsearmordiamond.name";

    /**
     * Gold Horse Armor
     */
    String MC_ITEM_HORSEARMORGOLD_NAME = "minecraft:item.horsearmorgold.name";

    /**
     * Iron Horse Armor
     */
    String MC_ITEM_HORSEARMORIRON_NAME = "minecraft:item.horsearmoriron.name";

    /**
     * Leather Horse Armor
     */
    String MC_ITEM_HORSEARMORLEATHER_NAME = "minecraft:item.horsearmorleather.name";

    /**
     * Host Armor Trim
     */
    String MC_ITEM_HOST_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.host_armor_trim_smithing_template.name";

    /**
     * Howl Pottery Sherd
     */
    String MC_ITEM_HOWL_POTTERY_SHERD_NAME = "minecraft:item.howl_pottery_sherd.name";

    /**
     * Iron Axe
     */
    String MC_ITEM_IRON_AXE_NAME = "minecraft:item.iron_axe.name";

    /**
     * Iron Boots
     */
    String MC_ITEM_IRON_BOOTS_NAME = "minecraft:item.iron_boots.name";

    /**
     * Iron Chestplate
     */
    String MC_ITEM_IRON_CHESTPLATE_NAME = "minecraft:item.iron_chestplate.name";

    /**
     * Iron Door
     */
    String MC_ITEM_IRON_DOOR_NAME = "minecraft:item.iron_door.name";

    /**
     * Iron Helmet
     */
    String MC_ITEM_IRON_HELMET_NAME = "minecraft:item.iron_helmet.name";

    /**
     * Iron Hoe
     */
    String MC_ITEM_IRON_HOE_NAME = "minecraft:item.iron_hoe.name";

    /**
     * Iron Ingot
     */
    String MC_ITEM_IRON_INGOT_NAME = "minecraft:item.iron_ingot.name";

    /**
     * Iron Leggings
     */
    String MC_ITEM_IRON_LEGGINGS_NAME = "minecraft:item.iron_leggings.name";

    /**
     * Iron Nugget
     */
    String MC_ITEM_IRON_NUGGET_NAME = "minecraft:item.iron_nugget.name";

    /**
     * Iron Pickaxe
     */
    String MC_ITEM_IRON_PICKAXE_NAME = "minecraft:item.iron_pickaxe.name";

    /**
     * Iron Shovel
     */
    String MC_ITEM_IRON_SHOVEL_NAME = "minecraft:item.iron_shovel.name";

    /**
     * Iron Sword
     */
    String MC_ITEM_IRON_SWORD_NAME = "minecraft:item.iron_sword.name";

    /**
     * :hollow_star: Can't Drop Items can't be:
     */
    String MC_ITEM_ITEMLOCK_CANTDROP = "minecraft:item.itemLock.cantDrop";

    /**
     * :solid_star: Can't Move Items can't be:
     */
    String MC_ITEM_ITEMLOCK_CANTMOVE = "minecraft:item.itemLock.cantMove";

    /**
     * crafted with
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_CRAFTEDWITH = "minecraft:item.itemLock.hoverText.cantBe.craftedWith";

    /**
     * dropped
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_DROPPED = "minecraft:item.itemLock.hoverText.cantBe.dropped";

    /**
     * moved
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_MOVED = "minecraft:item.itemLock.hoverText.cantBe.moved";

    /**
     * removed
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_REMOVED = "minecraft:item.itemLock.hoverText.cantBe.removed";

    /**
     * This item is not lost on death
     */
    String MC_ITEM_ITEMLOCK_KEEPONDEATH = "minecraft:item.itemLock.keepOnDeath";

    /**
     * :hollow_star: Can't Drop Items can't be: dropped, removed, crafted with
     */
    String MC_ITEM_ITEMLOCK_POPUPNOTICE_CANTDROP = "minecraft:item.itemLock.popupNotice.cantDrop";

    /**
     * :solid_star: Can't Move Items can't be: moved, dropped, removed, crafted with
     */
    String MC_ITEM_ITEMLOCK_POPUPNOTICE_CANTMOVE = "minecraft:item.itemLock.popupNotice.cantMove";

    /**
     * Jungle Door
     */
    String MC_ITEM_JUNGLE_DOOR_NAME = "minecraft:item.jungle_door.name";

    /**
     * Jungle Hanging Sign
     */
    String MC_ITEM_JUNGLE_HANGING_SIGN_NAME = "minecraft:item.jungle_hanging_sign.name";

    /**
     * Jungle Sign
     */
    String MC_ITEM_JUNGLE_SIGN_NAME = "minecraft:item.jungle_sign.name";

    /**
     * Kelp
     */
    String MC_ITEM_KELP_NAME = "minecraft:item.kelp.name";

    /**
     * Lead
     */
    String MC_ITEM_LEAD_NAME = "minecraft:item.lead.name";

    /**
     * Leather
     */
    String MC_ITEM_LEATHER_NAME = "minecraft:item.leather.name";

    /**
     * Leather Boots
     */
    String MC_ITEM_LEATHER_BOOTS_NAME = "minecraft:item.leather_boots.name";

    /**
     * Leather Tunic
     */
    String MC_ITEM_LEATHER_CHESTPLATE_NAME = "minecraft:item.leather_chestplate.name";

    /**
     * Leather Cap
     */
    String MC_ITEM_LEATHER_HELMET_NAME = "minecraft:item.leather_helmet.name";

    /**
     * Leather Pants
     */
    String MC_ITEM_LEATHER_LEGGINGS_NAME = "minecraft:item.leather_leggings.name";

    /**
     * Leaves
     */
    String MC_ITEM_LEAVES_NAME = "minecraft:item.leaves.name";

    /**
     * Light Blue Bundle
     */
    String MC_ITEM_LIGHT_BLUE_BUNDLE = "minecraft:item.light_blue_bundle";

    /**
     * Light Blue Harness
     */
    String MC_ITEM_LIGHT_BLUE_HARNESS_NAME = "minecraft:item.light_blue_harness.name";

    /**
     * Light Gray Bundle
     */
    String MC_ITEM_LIGHT_GRAY_BUNDLE = "minecraft:item.light_gray_bundle";

    /**
     * Light Gray Harness
     */
    String MC_ITEM_LIGHT_GRAY_HARNESS_NAME = "minecraft:item.light_gray_harness.name";

    /**
     * Lime Bundle
     */
    String MC_ITEM_LIME_BUNDLE = "minecraft:item.lime_bundle";

    /**
     * Lime Harness
     */
    String MC_ITEM_LIME_HARNESS_NAME = "minecraft:item.lime_harness.name";

    /**
     * Lodestone Compass
     */
    String MC_ITEM_LODESTONECOMPASS_NAME = "minecraft:item.lodestonecompass.name";

    /**
     * Mace
     */
    String MC_ITEM_MACE_NAME = "minecraft:item.mace.name";

    /**
     * Magenta Bundle
     */
    String MC_ITEM_MAGENTA_BUNDLE = "minecraft:item.magenta_bundle";

    /**
     * Magenta Harness
     */
    String MC_ITEM_MAGENTA_HARNESS_NAME = "minecraft:item.magenta_harness.name";

    /**
     * Magma Cream
     */
    String MC_ITEM_MAGMA_CREAM_NAME = "minecraft:item.magma_cream.name";

    /**
     * Mangrove Door
     */
    String MC_ITEM_MANGROVE_DOOR_NAME = "minecraft:item.mangrove_door.name";

    /**
     * Mangrove Hanging Sign
     */
    String MC_ITEM_MANGROVE_HANGING_SIGN_NAME = "minecraft:item.mangrove_hanging_sign.name";

    /**
     * Mangrove Sign
     */
    String MC_ITEM_MANGROVE_SIGN_NAME = "minecraft:item.mangrove_sign.name";

    /**
     * Treasure Map
     */
    String MC_ITEM_MAP_EXPLORATION_BURIED_TREASURE_NAME = "minecraft:item.map.exploration.buried_treasure.name";

    /**
     * Jungle Explorer Map
     */
    String MC_ITEM_MAP_EXPLORATION_JUNGLE_TEMPLE_NAME = "minecraft:item.map.exploration.jungle_temple.name";

    /**
     * Woodland Explorer Map
     */
    String MC_ITEM_MAP_EXPLORATION_MANSION_NAME = "minecraft:item.map.exploration.mansion.name";

    /**
     * Ocean Explorer Map
     */
    String MC_ITEM_MAP_EXPLORATION_MONUMENT_NAME = "minecraft:item.map.exploration.monument.name";

    /**
     * Swamp Explorer Map
     */
    String MC_ITEM_MAP_EXPLORATION_SWAMP_HUT_NAME = "minecraft:item.map.exploration.swamp_hut.name";

    /**
     * Treasure Map
     */
    String MC_ITEM_MAP_EXPLORATION_TREASURE_NAME = "minecraft:item.map.exploration.treasure.name";

    /**
     * Trial Explorer Map
     */
    String MC_ITEM_MAP_EXPLORATION_TRIAL_CHAMBERS_NAME = "minecraft:item.map.exploration.trial_chambers.name";

    /**
     * Desert Village Map
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_DESERT_NAME = "minecraft:item.map.exploration.village_desert.name";

    /**
     * Plains Village Map
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_PLAINS_NAME = "minecraft:item.map.exploration.village_plains.name";

    /**
     * Savanna Village Map
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_SAVANNA_NAME = "minecraft:item.map.exploration.village_savanna.name";

    /**
     * Snowy Village Map
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_SNOWY_NAME = "minecraft:item.map.exploration.village_snowy.name";

    /**
     * Taiga Village Map
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_TAIGA_NAME = "minecraft:item.map.exploration.village_taiga.name";

    /**
     * Map
     */
    String MC_ITEM_MAP_NAME = "minecraft:item.map.name";

    /**
     * Melon Slice
     */
    String MC_ITEM_MELON_NAME = "minecraft:item.melon.name";

    /**
     * Melon Seeds
     */
    String MC_ITEM_MELON_SEEDS_NAME = "minecraft:item.melon_seeds.name";

    /**
     * Milk Bucket
     */
    String MC_ITEM_MILK_NAME = "minecraft:item.milk.name";

    /**
     * Minecart
     */
    String MC_ITEM_MINECART_NAME = "minecraft:item.minecart.name";

    /**
     * Minecart with Furnace
     */
    String MC_ITEM_MINECARTFURNACE_NAME = "minecraft:item.minecartFurnace.name";

    /**
     * Ponder
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_0 = "minecraft:item.minecraft.goat_horn.sound.0";

    /**
     * Sing
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_1 = "minecraft:item.minecraft.goat_horn.sound.1";

    /**
     * Seek
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_2 = "minecraft:item.minecraft.goat_horn.sound.2";

    /**
     * Feel
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_3 = "minecraft:item.minecraft.goat_horn.sound.3";

    /**
     * Admire
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_4 = "minecraft:item.minecraft.goat_horn.sound.4";

    /**
     * Call
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_5 = "minecraft:item.minecraft.goat_horn.sound.5";

    /**
     * Yearn
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_6 = "minecraft:item.minecraft.goat_horn.sound.6";

    /**
     * Dream
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_7 = "minecraft:item.minecraft.goat_horn.sound.7";

    /**
     * Miner Pottery Sherd
     */
    String MC_ITEM_MINER_POTTERY_SHERD_NAME = "minecraft:item.miner_pottery_sherd.name";

    /**
     * Thing Banner Pattern
     */
    String MC_ITEM_MOJANG_BANNER_PATTERN_NAME = "minecraft:item.mojang_banner_pattern.name";

    /**
     * Mourner Pottery Sherd
     */
    String MC_ITEM_MOURNER_POTTERY_SHERD_NAME = "minecraft:item.mourner_pottery_sherd.name";

    /**
     * Mushroom Stew
     */
    String MC_ITEM_MUSHROOM_STEW_NAME = "minecraft:item.mushroom_stew.name";

    /**
     * Cooked Mutton
     */
    String MC_ITEM_MUTTONCOOKED_NAME = "minecraft:item.muttonCooked.name";

    /**
     * Raw Mutton
     */
    String MC_ITEM_MUTTONRAW_NAME = "minecraft:item.muttonRaw.name";

    /**
     * Name Tag
     */
    String MC_ITEM_NAME_TAG_NAME = "minecraft:item.name_tag.name";

    /**
     * Nautilus Shell
     */
    String MC_ITEM_NAUTILUS_SHELL_NAME = "minecraft:item.nautilus_shell.name";

    /**
     * Nether Star
     */
    String MC_ITEM_NETHERSTAR_NAME = "minecraft:item.netherStar.name";

    /**
     * Nether Wart
     */
    String MC_ITEM_NETHER_WART_NAME = "minecraft:item.nether_wart.name";

    /**
     * Nether Brick
     */
    String MC_ITEM_NETHERBRICK_NAME = "minecraft:item.netherbrick.name";

    /**
     * Netherite Axe
     */
    String MC_ITEM_NETHERITE_AXE_NAME = "minecraft:item.netherite_axe.name";

    /**
     * Netherite Boots
     */
    String MC_ITEM_NETHERITE_BOOTS_NAME = "minecraft:item.netherite_boots.name";

    /**
     * Netherite Chestplate
     */
    String MC_ITEM_NETHERITE_CHESTPLATE_NAME = "minecraft:item.netherite_chestplate.name";

    /**
     * Netherite Helmet
     */
    String MC_ITEM_NETHERITE_HELMET_NAME = "minecraft:item.netherite_helmet.name";

    /**
     * Netherite Hoe
     */
    String MC_ITEM_NETHERITE_HOE_NAME = "minecraft:item.netherite_hoe.name";

    /**
     * Netherite Ingot
     */
    String MC_ITEM_NETHERITE_INGOT_NAME = "minecraft:item.netherite_ingot.name";

    /**
     * Netherite Leggings
     */
    String MC_ITEM_NETHERITE_LEGGINGS_NAME = "minecraft:item.netherite_leggings.name";

    /**
     * Netherite Pickaxe
     */
    String MC_ITEM_NETHERITE_PICKAXE_NAME = "minecraft:item.netherite_pickaxe.name";

    /**
     * Netherite Scrap
     */
    String MC_ITEM_NETHERITE_SCRAP_NAME = "minecraft:item.netherite_scrap.name";

    /**
     * Netherite Shovel
     */
    String MC_ITEM_NETHERITE_SHOVEL_NAME = "minecraft:item.netherite_shovel.name";

    /**
     * Netherite Sword
     */
    String MC_ITEM_NETHERITE_SWORD_NAME = "minecraft:item.netherite_sword.name";

    /**
     * Netherite Upgrade
     */
    String MC_ITEM_NETHERITE_UPGRADE_SMITHING_TEMPLATE_NAME = "minecraft:item.netherite_upgrade_smithing_template.name";

    /**
     * Oak Hanging Sign
     */
    String MC_ITEM_OAK_HANGING_SIGN_NAME = "minecraft:item.oak_hanging_sign.name";

    /**
     * Ominous Bottle
     */
    String MC_ITEM_OMINOUS_BOTTLE_NAME = "minecraft:item.ominous_bottle.name";

    /**
     * Ominous Trial Key
     */
    String MC_ITEM_OMINOUS_TRIAL_KEY_NAME = "minecraft:item.ominous_trial_key.name";

    /**
     * Orange Bundle
     */
    String MC_ITEM_ORANGE_BUNDLE = "minecraft:item.orange_bundle";

    /**
     * Orange Harness
     */
    String MC_ITEM_ORANGE_HARNESS_NAME = "minecraft:item.orange_harness.name";

    /**
     * Oxidized Copper Door
     */
    String MC_ITEM_OXIDIZED_COPPER_DOOR_NAME = "minecraft:item.oxidized_copper_door.name";

    /**
     * Painting
     */
    String MC_ITEM_PAINTING_NAME = "minecraft:item.painting.name";

    /**
     * Pale Oak Door
     */
    String MC_ITEM_PALE_OAK_DOOR_NAME = "minecraft:item.pale_oak_door.name";

    /**
     * Pale Oak Hanging Sign
     */
    String MC_ITEM_PALE_OAK_HANGING_SIGN_NAME = "minecraft:item.pale_oak_hanging_sign.name";

    /**
     * Pale Oak Sign
     */
    String MC_ITEM_PALE_OAK_SIGN_NAME = "minecraft:item.pale_oak_sign.name";

    /**
     * Paper
     */
    String MC_ITEM_PAPER_NAME = "minecraft:item.paper.name";

    /**
     * Phantom Membrane
     */
    String MC_ITEM_PHANTOM_MEMBRANE_NAME = "minecraft:item.phantom_membrane.name";

    /**
     * Photo
     */
    String MC_ITEM_PHOTO_NAME = "minecraft:item.photo.name";

    /**
     * Snout Banner Pattern
     */
    String MC_ITEM_PIGLIN_BANNER_PATTERN_NAME = "minecraft:item.piglin_banner_pattern.name";

    /**
     * Pink Bundle
     */
    String MC_ITEM_PINK_BUNDLE = "minecraft:item.pink_bundle";

    /**
     * Pink Harness
     */
    String MC_ITEM_PINK_HARNESS_NAME = "minecraft:item.pink_harness.name";

    /**
     * Pitcher Pod
     */
    String MC_ITEM_PITCHER_POD_NAME = "minecraft:item.pitcher_pod.name";

    /**
     * Plenty Pottery Sherd
     */
    String MC_ITEM_PLENTY_POTTERY_SHERD_NAME = "minecraft:item.plenty_pottery_sherd.name";

    /**
     * Poisonous Potato
     */
    String MC_ITEM_POISONOUS_POTATO_NAME = "minecraft:item.poisonous_potato.name";

    /**
     * Raw Porkchop
     */
    String MC_ITEM_PORKCHOP_NAME = "minecraft:item.porkchop.name";

    /**
     * Cooked Porkchop
     */
    String MC_ITEM_PORKCHOP_COOKED_NAME = "minecraft:item.porkchop_cooked.name";

    /**
     * Portfolio
     */
    String MC_ITEM_PORTFOLIO_NAME = "minecraft:item.portfolio.name";

    /**
     * Potato
     */
    String MC_ITEM_POTATO_NAME = "minecraft:item.potato.name";

    /**
     * Prismarine Crystals
     */
    String MC_ITEM_PRISMARINE_CRYSTALS_NAME = "minecraft:item.prismarine_crystals.name";

    /**
     * Prismarine Shard
     */
    String MC_ITEM_PRISMARINE_SHARD_NAME = "minecraft:item.prismarine_shard.name";

    /**
     * Prize Pottery Sherd
     */
    String MC_ITEM_PRIZE_POTTERY_SHERD_NAME = "minecraft:item.prize_pottery_sherd.name";

    /**
     * Pufferfish
     */
    String MC_ITEM_PUFFERFISH_NAME = "minecraft:item.pufferfish.name";

    /**
     * Pumpkin Pie
     */
    String MC_ITEM_PUMPKIN_PIE_NAME = "minecraft:item.pumpkin_pie.name";

    /**
     * Pumpkin Seeds
     */
    String MC_ITEM_PUMPKIN_SEEDS_NAME = "minecraft:item.pumpkin_seeds.name";

    /**
     * Purple Bundle
     */
    String MC_ITEM_PURPLE_BUNDLE = "minecraft:item.purple_bundle";

    /**
     * Purple Harness
     */
    String MC_ITEM_PURPLE_HARNESS_NAME = "minecraft:item.purple_harness.name";

    /**
     * Nether Quartz
     */
    String MC_ITEM_QUARTZ_NAME = "minecraft:item.quartz.name";

    /**
     * Raw Rabbit
     */
    String MC_ITEM_RABBIT_NAME = "minecraft:item.rabbit.name";

    /**
     * Rabbit's Foot
     */
    String MC_ITEM_RABBIT_FOOT_NAME = "minecraft:item.rabbit_foot.name";

    /**
     * Rabbit Hide
     */
    String MC_ITEM_RABBIT_HIDE_NAME = "minecraft:item.rabbit_hide.name";

    /**
     * Rabbit Stew
     */
    String MC_ITEM_RABBIT_STEW_NAME = "minecraft:item.rabbit_stew.name";

    /**
     * Raiser Armor Trim
     */
    String MC_ITEM_RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.raiser_armor_trim_smithing_template.name";

    /**
     * Raw Copper
     */
    String MC_ITEM_RAW_COPPER_NAME = "minecraft:item.raw_copper.name";

    /**
     * Raw Gold
     */
    String MC_ITEM_RAW_GOLD_NAME = "minecraft:item.raw_gold.name";

    /**
     * Raw Iron
     */
    String MC_ITEM_RAW_IRON_NAME = "minecraft:item.raw_iron.name";

    /**
     * Music Disc
     */
    String MC_ITEM_RECORD_NAME = "minecraft:item.record.name";

    /**
     * C418 - 11
     */
    String MC_ITEM_RECORD_11_DESC = "minecraft:item.record_11.desc";

    /**
     * C418 - 13
     */
    String MC_ITEM_RECORD_13_DESC = "minecraft:item.record_13.desc";

    /**
     * Samuel Åberg - 5
     */
    String MC_ITEM_RECORD_5_DESC = "minecraft:item.record_5.desc";

    /**
     * C418 - blocks
     */
    String MC_ITEM_RECORD_BLOCKS_DESC = "minecraft:item.record_blocks.desc";

    /**
     * C418 - cat
     */
    String MC_ITEM_RECORD_CAT_DESC = "minecraft:item.record_cat.desc";

    /**
     * C418 - chirp
     */
    String MC_ITEM_RECORD_CHIRP_DESC = "minecraft:item.record_chirp.desc";

    /**
     * Lena Raine - Creator
     */
    String MC_ITEM_RECORD_CREATOR_DESC = "minecraft:item.record_creator.desc";

    /**
     * Lena Raine - Creator (Music Box)
     */
    String MC_ITEM_RECORD_CREATOR_MUSIC_BOX_DESC = "minecraft:item.record_creator_music_box.desc";

    /**
     * C418 - far
     */
    String MC_ITEM_RECORD_FAR_DESC = "minecraft:item.record_far.desc";

    /**
     * Hyper Potions - Lava Chicken
     */
    String MC_ITEM_RECORD_LAVA_CHICKEN_DESC = "minecraft:item.record_lava_chicken.desc";

    /**
     * C418 - mall
     */
    String MC_ITEM_RECORD_MALL_DESC = "minecraft:item.record_mall.desc";

    /**
     * C418 - mellohi
     */
    String MC_ITEM_RECORD_MELLOHI_DESC = "minecraft:item.record_mellohi.desc";

    /**
     * Lena Raine - otherside
     */
    String MC_ITEM_RECORD_OTHERSIDE_DESC = "minecraft:item.record_otherside.desc";

    /**
     * Lena Raine - Pigstep
     */
    String MC_ITEM_RECORD_PIGSTEP_DESC = "minecraft:item.record_pigstep.desc";

    /**
     * Aaron Cherof - Precipice
     */
    String MC_ITEM_RECORD_PRECIPICE_DESC = "minecraft:item.record_precipice.desc";

    /**
     * Aaron Cherof - Relic
     */
    String MC_ITEM_RECORD_RELIC_DESC = "minecraft:item.record_relic.desc";

    /**
     * C418 - stal
     */
    String MC_ITEM_RECORD_STAL_DESC = "minecraft:item.record_stal.desc";

    /**
     * C418 - strad
     */
    String MC_ITEM_RECORD_STRAD_DESC = "minecraft:item.record_strad.desc";

    /**
     * Amos Roddy - Tears
     */
    String MC_ITEM_RECORD_TEARS_DESC = "minecraft:item.record_tears.desc";

    /**
     * C418 - wait
     */
    String MC_ITEM_RECORD_WAIT_DESC = "minecraft:item.record_wait.desc";

    /**
     * C418 - ward
     */
    String MC_ITEM_RECORD_WARD_DESC = "minecraft:item.record_ward.desc";

    /**
     * Recovery Compass
     */
    String MC_ITEM_RECOVERY_COMPASS_NAME = "minecraft:item.recovery_compass.name";

    /**
     * Red Bundle
     */
    String MC_ITEM_RED_BUNDLE = "minecraft:item.red_bundle";

    /**
     * Red Harness
     */
    String MC_ITEM_RED_HARNESS_NAME = "minecraft:item.red_harness.name";

    /**
     * Redstone Dust
     */
    String MC_ITEM_REDSTONE_NAME = "minecraft:item.redstone.name";

    /**
     * Sugar Cane
     */
    String MC_ITEM_REEDS_NAME = "minecraft:item.reeds.name";

    /**
     * Redstone Repeater
     */
    String MC_ITEM_REPEATER_NAME = "minecraft:item.repeater.name";

    /**
     * Resin Brick
     */
    String MC_ITEM_RESIN_BRICK_NAME = "minecraft:item.resin_brick.name";

    /**
     * Rib Armor Trim
     */
    String MC_ITEM_RIB_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.rib_armor_trim_smithing_template.name";

    /**
     * Rotten Flesh
     */
    String MC_ITEM_ROTTEN_FLESH_NAME = "minecraft:item.rotten_flesh.name";

    /**
     * Ruby
     */
    String MC_ITEM_RUBY_NAME = "minecraft:item.ruby.name";

    /**
     * Saddle
     */
    String MC_ITEM_SADDLE_NAME = "minecraft:item.saddle.name";

    /**
     * Raw Salmon
     */
    String MC_ITEM_SALMON_NAME = "minecraft:item.salmon.name";

    /**
     * Scrape Pottery Sherd
     */
    String MC_ITEM_SCRAPE_POTTERY_SHERD_NAME = "minecraft:item.scrape_pottery_sherd.name";

    /**
     * Sentry Armor Trim
     */
    String MC_ITEM_SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.sentry_armor_trim_smithing_template.name";

    /**
     * Shaper Armor Trim
     */
    String MC_ITEM_SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.shaper_armor_trim_smithing_template.name";

    /**
     * Sheaf Pottery Sherd
     */
    String MC_ITEM_SHEAF_POTTERY_SHERD_NAME = "minecraft:item.sheaf_pottery_sherd.name";

    /**
     * Shears
     */
    String MC_ITEM_SHEARS_NAME = "minecraft:item.shears.name";

    /**
     * Shelter Pottery Sherd
     */
    String MC_ITEM_SHELTER_POTTERY_SHERD_NAME = "minecraft:item.shelter_pottery_sherd.name";

    /**
     * Black Shield
     */
    String MC_ITEM_SHIELD_BLACK_NAME = "minecraft:item.shield.black.name";

    /**
     * Blue Shield
     */
    String MC_ITEM_SHIELD_BLUE_NAME = "minecraft:item.shield.blue.name";

    /**
     * Brown Shield
     */
    String MC_ITEM_SHIELD_BROWN_NAME = "minecraft:item.shield.brown.name";

    /**
     * Cyan Shield
     */
    String MC_ITEM_SHIELD_CYAN_NAME = "minecraft:item.shield.cyan.name";

    /**
     * Gray Shield
     */
    String MC_ITEM_SHIELD_GRAY_NAME = "minecraft:item.shield.gray.name";

    /**
     * Green Shield
     */
    String MC_ITEM_SHIELD_GREEN_NAME = "minecraft:item.shield.green.name";

    /**
     * Light Blue Shield
     */
    String MC_ITEM_SHIELD_LIGHTBLUE_NAME = "minecraft:item.shield.lightBlue.name";

    /**
     * Lime Shield
     */
    String MC_ITEM_SHIELD_LIME_NAME = "minecraft:item.shield.lime.name";

    /**
     * Magenta Shield
     */
    String MC_ITEM_SHIELD_MAGENTA_NAME = "minecraft:item.shield.magenta.name";

    /**
     * Shield
     */
    String MC_ITEM_SHIELD_NAME = "minecraft:item.shield.name";

    /**
     * Orange Shield
     */
    String MC_ITEM_SHIELD_ORANGE_NAME = "minecraft:item.shield.orange.name";

    /**
     * Pink Shield
     */
    String MC_ITEM_SHIELD_PINK_NAME = "minecraft:item.shield.pink.name";

    /**
     * Purple Shield
     */
    String MC_ITEM_SHIELD_PURPLE_NAME = "minecraft:item.shield.purple.name";

    /**
     * Red Shield
     */
    String MC_ITEM_SHIELD_RED_NAME = "minecraft:item.shield.red.name";

    /**
     * Light Gray Shield
     */
    String MC_ITEM_SHIELD_SILVER_NAME = "minecraft:item.shield.silver.name";

    /**
     * White Shield
     */
    String MC_ITEM_SHIELD_WHITE_NAME = "minecraft:item.shield.white.name";

    /**
     * Yellow Shield
     */
    String MC_ITEM_SHIELD_YELLOW_NAME = "minecraft:item.shield.yellow.name";

    /**
     * Shulker Shell
     */
    String MC_ITEM_SHULKER_SHELL_NAME = "minecraft:item.shulker_shell.name";

    /**
     * Oak Sign
     */
    String MC_ITEM_SIGN_NAME = "minecraft:item.sign.name";

    /**
     * Silence Armor Trim
     */
    String MC_ITEM_SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.silence_armor_trim_smithing_template.name";

    /**
     * Player Head
     */
    String MC_ITEM_SKULL_CHAR_NAME = "minecraft:item.skull.char.name";

    /**
     * Creeper Head
     */
    String MC_ITEM_SKULL_CREEPER_NAME = "minecraft:item.skull.creeper.name";

    /**
     * Dragon Head
     */
    String MC_ITEM_SKULL_DRAGON_NAME = "minecraft:item.skull.dragon.name";

    /**
     * Piglin Head
     */
    String MC_ITEM_SKULL_PIGLIN_NAME = "minecraft:item.skull.piglin.name";

    /**
     * %s's Head
     */
    String MC_ITEM_SKULL_PLAYER_NAME = "minecraft:item.skull.player.name";

    /**
     * Skeleton Skull
     */
    String MC_ITEM_SKULL_SKELETON_NAME = "minecraft:item.skull.skeleton.name";

    /**
     * Wither Skeleton Skull
     */
    String MC_ITEM_SKULL_WITHER_NAME = "minecraft:item.skull.wither.name";

    /**
     * Zombie Head
     */
    String MC_ITEM_SKULL_ZOMBIE_NAME = "minecraft:item.skull.zombie.name";

    /**
     * Skull Charge Banner Pattern
     */
    String MC_ITEM_SKULL_BANNER_PATTERN_NAME = "minecraft:item.skull_banner_pattern.name";

    /**
     * Skull Pottery Sherd
     */
    String MC_ITEM_SKULL_POTTERY_SHERD_NAME = "minecraft:item.skull_pottery_sherd.name";

    /**
     * Slimeball
     */
    String MC_ITEM_SLIME_BALL_NAME = "minecraft:item.slime_ball.name";

    /**
     * Applies to:
     */
    String MC_ITEM_SMITHING_TEMPLATE_APPLIES_TO = "minecraft:item.smithing_template.applies_to";

    /**
     * Armor
     */
    String MC_ITEM_SMITHING_TEMPLATE_ARMOR_TRIM_APPLIES_TO = "minecraft:item.smithing_template.armor_trim.applies_to";

    /**
     * Ingots & Crystals
     */
    String MC_ITEM_SMITHING_TEMPLATE_ARMOR_TRIM_INGREDIENTS = "minecraft:item.smithing_template.armor_trim.ingredients";

    /**
     * Ingredients:
     */
    String MC_ITEM_SMITHING_TEMPLATE_INGREDIENTS = "minecraft:item.smithing_template.ingredients";

    /**
     * Smithing Template
     */
    String MC_ITEM_SMITHING_TEMPLATE_NAME = "minecraft:item.smithing_template.name";

    /**
     * Diamond Equipment
     */
    String MC_ITEM_SMITHING_TEMPLATE_NETHERITE_UPGRADE_APPLIES_TO = "minecraft:item.smithing_template.netherite_upgrade.applies_to";

    /**
     * Netherite Ingot
     */
    String MC_ITEM_SMITHING_TEMPLATE_NETHERITE_UPGRADE_INGREDIENTS = "minecraft:item.smithing_template.netherite_upgrade.ingredients";

    /**
     * Snort Pottery Sherd
     */
    String MC_ITEM_SNORT_POTTERY_SHERD_NAME = "minecraft:item.snort_pottery_sherd.name";

    /**
     * Snout Armor Trim
     */
    String MC_ITEM_SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.snout_armor_trim_smithing_template.name";

    /**
     * Snowball
     */
    String MC_ITEM_SNOWBALL_NAME = "minecraft:item.snowball.name";

    /**
     * Agent Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_AGENT_NAME = "minecraft:item.spawn_egg.entity.agent.name";

    /**
     * Allay Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ALLAY_NAME = "minecraft:item.spawn_egg.entity.allay.name";

    /**
     * Armadillo Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ARMADILLO_NAME = "minecraft:item.spawn_egg.entity.armadillo.name";

    /**
     * Axolotl Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_AXOLOTL_NAME = "minecraft:item.spawn_egg.entity.axolotl.name";

    /**
     * Bat Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BAT_NAME = "minecraft:item.spawn_egg.entity.bat.name";

    /**
     * Bee Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BEE_NAME = "minecraft:item.spawn_egg.entity.bee.name";

    /**
     * Blaze Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BLAZE_NAME = "minecraft:item.spawn_egg.entity.blaze.name";

    /**
     * Bogged Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BOGGED_NAME = "minecraft:item.spawn_egg.entity.bogged.name";

    /**
     * Breeze Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BREEZE_NAME = "minecraft:item.spawn_egg.entity.breeze.name";

    /**
     * Camel Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAMEL_NAME = "minecraft:item.spawn_egg.entity.camel.name";

    /**
     * Cat Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAT_NAME = "minecraft:item.spawn_egg.entity.cat.name";

    /**
     * Cave Spider Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAVE_SPIDER_NAME = "minecraft:item.spawn_egg.entity.cave_spider.name";

    /**
     * Chicken Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CHICKEN_NAME = "minecraft:item.spawn_egg.entity.chicken.name";

    /**
     * Cod Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_COD_NAME = "minecraft:item.spawn_egg.entity.cod.name";

    /**
     * Copper Golem Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_COPPER_GOLEM_NAME = "minecraft:item.spawn_egg.entity.copper_golem.name";

    /**
     * Cow Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_COW_NAME = "minecraft:item.spawn_egg.entity.cow.name";

    /**
     * Creaking Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CREAKING_NAME = "minecraft:item.spawn_egg.entity.creaking.name";

    /**
     * Creeper Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CREEPER_NAME = "minecraft:item.spawn_egg.entity.creeper.name";

    /**
     * Dolphin Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_DOLPHIN_NAME = "minecraft:item.spawn_egg.entity.dolphin.name";

    /**
     * Donkey Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_DONKEY_NAME = "minecraft:item.spawn_egg.entity.donkey.name";

    /**
     * Drowned Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_DROWNED_NAME = "minecraft:item.spawn_egg.entity.drowned.name";

    /**
     * Elder Guardian Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ELDER_GUARDIAN_NAME = "minecraft:item.spawn_egg.entity.elder_guardian.name";

    /**
     * Ender Dragon Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ENDER_DRAGON_NAME = "minecraft:item.spawn_egg.entity.ender_dragon.name";

    /**
     * Enderman Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ENDERMAN_NAME = "minecraft:item.spawn_egg.entity.enderman.name";

    /**
     * Endermite Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ENDERMITE_NAME = "minecraft:item.spawn_egg.entity.endermite.name";

    /**
     * Evoker Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_EVOCATION_ILLAGER_NAME = "minecraft:item.spawn_egg.entity.evocation_illager.name";

    /**
     * Fox Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_FOX_NAME = "minecraft:item.spawn_egg.entity.fox.name";

    /**
     * Frog Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_FROG_NAME = "minecraft:item.spawn_egg.entity.frog.name";

    /**
     * Ghast Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GHAST_NAME = "minecraft:item.spawn_egg.entity.ghast.name";

    /**
     * Glow Squid Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GLOW_SQUID_NAME = "minecraft:item.spawn_egg.entity.glow_squid.name";

    /**
     * Goat Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GOAT_NAME = "minecraft:item.spawn_egg.entity.goat.name";

    /**
     * Guardian Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GUARDIAN_NAME = "minecraft:item.spawn_egg.entity.guardian.name";

    /**
     * Happy Ghast Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HAPPY_GHAST_NAME = "minecraft:item.spawn_egg.entity.happy_ghast.name";

    /**
     * Hoglin Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HOGLIN_NAME = "minecraft:item.spawn_egg.entity.hoglin.name";

    /**
     * Horse Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HORSE_NAME = "minecraft:item.spawn_egg.entity.horse.name";

    /**
     * Husk Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HUSK_NAME = "minecraft:item.spawn_egg.entity.husk.name";

    /**
     * Iron Golem Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_IRON_GOLEM_NAME = "minecraft:item.spawn_egg.entity.iron_golem.name";

    /**
     * Llama Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_LLAMA_NAME = "minecraft:item.spawn_egg.entity.llama.name";

    /**
     * Magma Cube Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_MAGMA_CUBE_NAME = "minecraft:item.spawn_egg.entity.magma_cube.name";

    /**
     * Mooshroom Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_MOOSHROOM_NAME = "minecraft:item.spawn_egg.entity.mooshroom.name";

    /**
     * Mule Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_MULE_NAME = "minecraft:item.spawn_egg.entity.mule.name";

    /**
     * You need Creative + Operator to spawn an NPC
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_NPC_FAILED = "minecraft:item.spawn_egg.entity.npc.failed";

    /**
     * NPC Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_NPC_NAME = "minecraft:item.spawn_egg.entity.npc.name";

    /**
     * Ocelot Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_OCELOT_NAME = "minecraft:item.spawn_egg.entity.ocelot.name";

    /**
     * Panda Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PANDA_NAME = "minecraft:item.spawn_egg.entity.panda.name";

    /**
     * Parrot Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PARROT_NAME = "minecraft:item.spawn_egg.entity.parrot.name";

    /**
     * Phantom Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PHANTOM_NAME = "minecraft:item.spawn_egg.entity.phantom.name";

    /**
     * Pig Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PIG_NAME = "minecraft:item.spawn_egg.entity.pig.name";

    /**
     * Piglin Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PIGLIN_NAME = "minecraft:item.spawn_egg.entity.piglin.name";

    /**
     * Piglin Brute Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PIGLIN_BRUTE_NAME = "minecraft:item.spawn_egg.entity.piglin_brute.name";

    /**
     * Pillager Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PILLAGER_NAME = "minecraft:item.spawn_egg.entity.pillager.name";

    /**
     * Polar Bear Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_POLAR_BEAR_NAME = "minecraft:item.spawn_egg.entity.polar_bear.name";

    /**
     * Pufferfish Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PUFFERFISH_NAME = "minecraft:item.spawn_egg.entity.pufferfish.name";

    /**
     * Rabbit Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_RABBIT_NAME = "minecraft:item.spawn_egg.entity.rabbit.name";

    /**
     * Ravager Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_RAVAGER_NAME = "minecraft:item.spawn_egg.entity.ravager.name";

    /**
     * Salmon Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SALMON_NAME = "minecraft:item.spawn_egg.entity.salmon.name";

    /**
     * Sheep Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SHEEP_NAME = "minecraft:item.spawn_egg.entity.sheep.name";

    /**
     * Shulker Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SHULKER_NAME = "minecraft:item.spawn_egg.entity.shulker.name";

    /**
     * Silverfish Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SILVERFISH_NAME = "minecraft:item.spawn_egg.entity.silverfish.name";

    /**
     * Skeleton Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SKELETON_NAME = "minecraft:item.spawn_egg.entity.skeleton.name";

    /**
     * Skeleton Horse Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SKELETON_HORSE_NAME = "minecraft:item.spawn_egg.entity.skeleton_horse.name";

    /**
     * Slime Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SLIME_NAME = "minecraft:item.spawn_egg.entity.slime.name";

    /**
     * Sniffer Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SNIFFER_NAME = "minecraft:item.spawn_egg.entity.sniffer.name";

    /**
     * Snow Golem Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SNOW_GOLEM_NAME = "minecraft:item.spawn_egg.entity.snow_golem.name";

    /**
     * Spider Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SPIDER_NAME = "minecraft:item.spawn_egg.entity.spider.name";

    /**
     * Squid Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SQUID_NAME = "minecraft:item.spawn_egg.entity.squid.name";

    /**
     * Stray Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_STRAY_NAME = "minecraft:item.spawn_egg.entity.stray.name";

    /**
     * Strider Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_STRIDER_NAME = "minecraft:item.spawn_egg.entity.strider.name";

    /**
     * Tadpole Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TADPOLE_NAME = "minecraft:item.spawn_egg.entity.tadpole.name";

    /**
     * Trader Llama Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TRADER_LLAMA_NAME = "minecraft:item.spawn_egg.entity.trader_llama.name";

    /**
     * Tropical Fish Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TROPICALFISH_NAME = "minecraft:item.spawn_egg.entity.tropicalfish.name";

    /**
     * Turtle Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TURTLE_NAME = "minecraft:item.spawn_egg.entity.turtle.name";

    /**
     * Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_UNKNOWN_NAME = "minecraft:item.spawn_egg.entity.unknown.name";

    /**
     * Vex Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VEX_NAME = "minecraft:item.spawn_egg.entity.vex.name";

    /**
     * Villager Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VILLAGER_NAME = "minecraft:item.spawn_egg.entity.villager.name";

    /**
     * Villager Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VILLAGER_V2_NAME = "minecraft:item.spawn_egg.entity.villager_v2.name";

    /**
     * Vindicator Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VINDICATOR_NAME = "minecraft:item.spawn_egg.entity.vindicator.name";

    /**
     * Wandering Trader Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WANDERING_TRADER_NAME = "minecraft:item.spawn_egg.entity.wandering_trader.name";

    /**
     * Warden Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WARDEN_NAME = "minecraft:item.spawn_egg.entity.warden.name";

    /**
     * Witch Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WITCH_NAME = "minecraft:item.spawn_egg.entity.witch.name";

    /**
     * Wither Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WITHER_NAME = "minecraft:item.spawn_egg.entity.wither.name";

    /**
     * Wither Skeleton Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WITHER_SKELETON_NAME = "minecraft:item.spawn_egg.entity.wither_skeleton.name";

    /**
     * Wolf Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WOLF_NAME = "minecraft:item.spawn_egg.entity.wolf.name";

    /**
     * Zoglin Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOGLIN_NAME = "minecraft:item.spawn_egg.entity.zoglin.name";

    /**
     * Zombie Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_NAME = "minecraft:item.spawn_egg.entity.zombie.name";

    /**
     * Zombie Horse Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_HORSE_NAME = "minecraft:item.spawn_egg.entity.zombie_horse.name";

    /**
     * Zombified Piglin Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_PIGMAN_NAME = "minecraft:item.spawn_egg.entity.zombie_pigman.name";

    /**
     * Zombie Villager Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_VILLAGER_NAME = "minecraft:item.spawn_egg.entity.zombie_villager.name";

    /**
     * Zombie Villager Spawn Egg
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_VILLAGER_V2_NAME = "minecraft:item.spawn_egg.entity.zombie_villager_v2.name";

    /**
     * Glistering Melon Slice
     */
    String MC_ITEM_SPECKLED_MELON_NAME = "minecraft:item.speckled_melon.name";

    /**
     * Spider Eye
     */
    String MC_ITEM_SPIDER_EYE_NAME = "minecraft:item.spider_eye.name";

    /**
     * Spire Armor Trim
     */
    String MC_ITEM_SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.spire_armor_trim_smithing_template.name";

    /**
     * Spruce Door
     */
    String MC_ITEM_SPRUCE_DOOR_NAME = "minecraft:item.spruce_door.name";

    /**
     * Spruce Hanging Sign
     */
    String MC_ITEM_SPRUCE_HANGING_SIGN_NAME = "minecraft:item.spruce_hanging_sign.name";

    /**
     * Spruce Sign
     */
    String MC_ITEM_SPRUCE_SIGN_NAME = "minecraft:item.spruce_sign.name";

    /**
     * Spyglass
     */
    String MC_ITEM_SPYGLASS_NAME = "minecraft:item.spyglass.name";

    /**
     * Steak
     */
    String MC_ITEM_STEAK_NAME = "minecraft:item.steak.name";

    /**
     * Stick
     */
    String MC_ITEM_STICK_NAME = "minecraft:item.stick.name";

    /**
     * Stone Axe
     */
    String MC_ITEM_STONE_AXE_NAME = "minecraft:item.stone_axe.name";

    /**
     * Stone Hoe
     */
    String MC_ITEM_STONE_HOE_NAME = "minecraft:item.stone_hoe.name";

    /**
     * Stone Pickaxe
     */
    String MC_ITEM_STONE_PICKAXE_NAME = "minecraft:item.stone_pickaxe.name";

    /**
     * Stone Shovel
     */
    String MC_ITEM_STONE_SHOVEL_NAME = "minecraft:item.stone_shovel.name";

    /**
     * Stone Sword
     */
    String MC_ITEM_STONE_SWORD_NAME = "minecraft:item.stone_sword.name";

    /**
     * String
     */
    String MC_ITEM_STRING_NAME = "minecraft:item.string.name";

    /**
     * Sugar
     */
    String MC_ITEM_SUGAR_NAME = "minecraft:item.sugar.name";

    /**
     * Suspicious Stew
     */
    String MC_ITEM_SUSPICIOUS_STEW_NAME = "minecraft:item.suspicious_stew.name";

    /**
     * Sweet Berries
     */
    String MC_ITEM_SWEET_BERRIES_NAME = "minecraft:item.sweet_berries.name";

    /**
     * Tide Armor Trim
     */
    String MC_ITEM_TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.tide_armor_trim_smithing_template.name";

    /**
     * Tipped Arrow
     */
    String MC_ITEM_TIPPED_ARROW_NAME = "minecraft:item.tipped_arrow.name";

    /**
     * Minecart with TNT
     */
    String MC_ITEM_TNT_MINECART_NAME = "minecraft:item.tnt_minecart.name";

    /**
     * Torchflower Seeds
     */
    String MC_ITEM_TORCHFLOWER_SEEDS_NAME = "minecraft:item.torchflower_seeds.name";

    /**
     * Totem of Undying
     */
    String MC_ITEM_TOTEM_NAME = "minecraft:item.totem.name";

    /**
     * Trial Key
     */
    String MC_ITEM_TRIAL_KEY_NAME = "minecraft:item.trial_key.name";

    /**
     * Trident
     */
    String MC_ITEM_TRIDENT_NAME = "minecraft:item.trident.name";

    /**
     * %1#s-%2#s Betty
     */
    String MC_ITEM_TROPICALBODYBETTYMULTI_NAME = "minecraft:item.tropicalBodyBettyMulti.name";

    /**
     * %1#s Betty
     */
    String MC_ITEM_TROPICALBODYBETTYSINGLE_NAME = "minecraft:item.tropicalBodyBettySingle.name";

    /**
     * %1#s-%2#s Blockfish
     */
    String MC_ITEM_TROPICALBODYBLOCKFISHMULTI_NAME = "minecraft:item.tropicalBodyBlockfishMulti.name";

    /**
     * %1#s Blockfish
     */
    String MC_ITEM_TROPICALBODYBLOCKFISHSINGLE_NAME = "minecraft:item.tropicalBodyBlockfishSingle.name";

    /**
     * %1#s-%2#s Brinely
     */
    String MC_ITEM_TROPICALBODYBRINELYMULTI_NAME = "minecraft:item.tropicalBodyBrinelyMulti.name";

    /**
     * %1#s Brinely
     */
    String MC_ITEM_TROPICALBODYBRINELYSINGLE_NAME = "minecraft:item.tropicalBodyBrinelySingle.name";

    /**
     * %1#s-%2#s Clayfish
     */
    String MC_ITEM_TROPICALBODYCLAYFISHMULTI_NAME = "minecraft:item.tropicalBodyClayfishMulti.name";

    /**
     * %1#s Clayfish
     */
    String MC_ITEM_TROPICALBODYCLAYFISHSINGLE_NAME = "minecraft:item.tropicalBodyClayfishSingle.name";

    /**
     * %1#s-%2#s Dasher
     */
    String MC_ITEM_TROPICALBODYDASHERMULTI_NAME = "minecraft:item.tropicalBodyDasherMulti.name";

    /**
     * %1#s Dasher
     */
    String MC_ITEM_TROPICALBODYDASHERSINGLE_NAME = "minecraft:item.tropicalBodyDasherSingle.name";

    /**
     * %1#s-%2#s Flopper
     */
    String MC_ITEM_TROPICALBODYFLOPPERMULTI_NAME = "minecraft:item.tropicalBodyFlopperMulti.name";

    /**
     * %1#s Flopper
     */
    String MC_ITEM_TROPICALBODYFLOPPERSINGLE_NAME = "minecraft:item.tropicalBodyFlopperSingle.name";

    /**
     * %1#s-%2#s Glitter
     */
    String MC_ITEM_TROPICALBODYGLITTERMULTI_NAME = "minecraft:item.tropicalBodyGlitterMulti.name";

    /**
     * %1#s Glitter
     */
    String MC_ITEM_TROPICALBODYGLITTERSINGLE_NAME = "minecraft:item.tropicalBodyGlitterSingle.name";

    /**
     * %1#s-%2#s Kob
     */
    String MC_ITEM_TROPICALBODYKOBMULTI_NAME = "minecraft:item.tropicalBodyKobMulti.name";

    /**
     * %1#s Kob
     */
    String MC_ITEM_TROPICALBODYKOBSINGLE_NAME = "minecraft:item.tropicalBodyKobSingle.name";

    /**
     * %1#s-%2#s Snooper
     */
    String MC_ITEM_TROPICALBODYSNOOPERMULTI_NAME = "minecraft:item.tropicalBodySnooperMulti.name";

    /**
     * %1#s Snooper
     */
    String MC_ITEM_TROPICALBODYSNOOPERSINGLE_NAME = "minecraft:item.tropicalBodySnooperSingle.name";

    /**
     * %1#s-%2#s Spotty
     */
    String MC_ITEM_TROPICALBODYSPOTTYMULTI_NAME = "minecraft:item.tropicalBodySpottyMulti.name";

    /**
     * %1#s Spotty
     */
    String MC_ITEM_TROPICALBODYSPOTTYSINGLE_NAME = "minecraft:item.tropicalBodySpottySingle.name";

    /**
     * %1#s-%2#s Stripey
     */
    String MC_ITEM_TROPICALBODYSTRIPEYMULTI_NAME = "minecraft:item.tropicalBodyStripeyMulti.name";

    /**
     * %1#s Stripey
     */
    String MC_ITEM_TROPICALBODYSTRIPEYSINGLE_NAME = "minecraft:item.tropicalBodyStripeySingle.name";

    /**
     * %1#s-%2#s SunStreak
     */
    String MC_ITEM_TROPICALBODYSUNSTREAKMULTI_NAME = "minecraft:item.tropicalBodySunstreakMulti.name";

    /**
     * %1#s SunStreak
     */
    String MC_ITEM_TROPICALBODYSUNSTREAKSINGLE_NAME = "minecraft:item.tropicalBodySunstreakSingle.name";

    /**
     * Blue
     */
    String MC_ITEM_TROPICALCOLORBLUE_NAME = "minecraft:item.tropicalColorBlue.name";

    /**
     * Brown
     */
    String MC_ITEM_TROPICALCOLORBROWN_NAME = "minecraft:item.tropicalColorBrown.name";

    /**
     * Gray
     */
    String MC_ITEM_TROPICALCOLORGRAY_NAME = "minecraft:item.tropicalColorGray.name";

    /**
     * Green
     */
    String MC_ITEM_TROPICALCOLORGREEN_NAME = "minecraft:item.tropicalColorGreen.name";

    /**
     * Lime
     */
    String MC_ITEM_TROPICALCOLORLIME_NAME = "minecraft:item.tropicalColorLime.name";

    /**
     * Magenta
     */
    String MC_ITEM_TROPICALCOLORMAGENTA_NAME = "minecraft:item.tropicalColorMagenta.name";

    /**
     * Orange
     */
    String MC_ITEM_TROPICALCOLORORANGE_NAME = "minecraft:item.tropicalColorOrange.name";

    /**
     * Plum
     */
    String MC_ITEM_TROPICALCOLORPLUM_NAME = "minecraft:item.tropicalColorPlum.name";

    /**
     * Red
     */
    String MC_ITEM_TROPICALCOLORRED_NAME = "minecraft:item.tropicalColorRed.name";

    /**
     * Rose
     */
    String MC_ITEM_TROPICALCOLORROSE_NAME = "minecraft:item.tropicalColorRose.name";

    /**
     * Silver
     */
    String MC_ITEM_TROPICALCOLORSILVER_NAME = "minecraft:item.tropicalColorSilver.name";

    /**
     * Sky
     */
    String MC_ITEM_TROPICALCOLORSKY_NAME = "minecraft:item.tropicalColorSky.name";

    /**
     * Teal
     */
    String MC_ITEM_TROPICALCOLORTEAL_NAME = "minecraft:item.tropicalColorTeal.name";

    /**
     * White
     */
    String MC_ITEM_TROPICALCOLORWHITE_NAME = "minecraft:item.tropicalColorWhite.name";

    /**
     * Yellow
     */
    String MC_ITEM_TROPICALCOLORYELLOW_NAME = "minecraft:item.tropicalColorYellow.name";

    /**
     * Anemone
     */
    String MC_ITEM_TROPICALSCHOOLANEMONE_NAME = "minecraft:item.tropicalSchoolAnemone.name";

    /**
     * Black Tang
     */
    String MC_ITEM_TROPICALSCHOOLBLACKTANG_NAME = "minecraft:item.tropicalSchoolBlackTang.name";

    /**
     * Blue Dory
     */
    String MC_ITEM_TROPICALSCHOOLBLUEDORY_NAME = "minecraft:item.tropicalSchoolBlueDory.name";

    /**
     * Butterfly Fish
     */
    String MC_ITEM_TROPICALSCHOOLBUTTERFLYFISH_NAME = "minecraft:item.tropicalSchoolButterflyFish.name";

    /**
     * Chichlid
     */
    String MC_ITEM_TROPICALSCHOOLCICHLID_NAME = "minecraft:item.tropicalSchoolCichlid.name";

    /**
     * Clownfish
     */
    String MC_ITEM_TROPICALSCHOOLCLOWNFISH_NAME = "minecraft:item.tropicalSchoolClownfish.name";

    /**
     * Cotton Candy Betta
     */
    String MC_ITEM_TROPICALSCHOOLCOTTONCANDYBETTA_NAME = "minecraft:item.tropicalSchoolCottonCandyBetta.name";

    /**
     * Dottyback
     */
    String MC_ITEM_TROPICALSCHOOLDOTTYBACK_NAME = "minecraft:item.tropicalSchoolDottyback.name";

    /**
     * Emperor Red Snapper
     */
    String MC_ITEM_TROPICALSCHOOLEMPERORREDSNAPPER_NAME = "minecraft:item.tropicalSchoolEmperorRedSnapper.name";

    /**
     * Goatfish
     */
    String MC_ITEM_TROPICALSCHOOLGOATFISH_NAME = "minecraft:item.tropicalSchoolGoatfish.name";

    /**
     * Moorish Idol
     */
    String MC_ITEM_TROPICALSCHOOLMOORISHIDOL_NAME = "minecraft:item.tropicalSchoolMoorishIdol.name";

    /**
     * Ornate Butterfly
     */
    String MC_ITEM_TROPICALSCHOOLORNATEBUTTERFLY_NAME = "minecraft:item.tropicalSchoolOrnateButterfly.name";

    /**
     * Parrotfish
     */
    String MC_ITEM_TROPICALSCHOOLPARROTFISH_NAME = "minecraft:item.tropicalSchoolParrotfish.name";

    /**
     * Queen Angel Fish
     */
    String MC_ITEM_TROPICALSCHOOLQUEENANGELFISH_NAME = "minecraft:item.tropicalSchoolQueenAngelFish.name";

    /**
     * Red Cichlid
     */
    String MC_ITEM_TROPICALSCHOOLREDCICHLID_NAME = "minecraft:item.tropicalSchoolRedCichlid.name";

    /**
     * Red Lipped Blenny
     */
    String MC_ITEM_TROPICALSCHOOLREDLIPPEDBLENNY_NAME = "minecraft:item.tropicalSchoolRedLippedBlenny.name";

    /**
     * Red Snapper
     */
    String MC_ITEM_TROPICALSCHOOLREDSNAPPER_NAME = "minecraft:item.tropicalSchoolRedSnapper.name";

    /**
     * Threadfin
     */
    String MC_ITEM_TROPICALSCHOOLTHREADFIN_NAME = "minecraft:item.tropicalSchoolThreadfin.name";

    /**
     * Tomato Clown
     */
    String MC_ITEM_TROPICALSCHOOLTOMATOCLOWN_NAME = "minecraft:item.tropicalSchoolTomatoClown.name";

    /**
     * Triggerfish
     */
    String MC_ITEM_TROPICALSCHOOLTRIGGERFISH_NAME = "minecraft:item.tropicalSchoolTriggerfish.name";

    /**
     * Yellow Tang
     */
    String MC_ITEM_TROPICALSCHOOLYELLOWTANG_NAME = "minecraft:item.tropicalSchoolYellowTang.name";

    /**
     * Yellowtail Parrot
     */
    String MC_ITEM_TROPICALSCHOOLYELLOWTAILPARROT_NAME = "minecraft:item.tropicalSchoolYellowtailParrot.name";

    /**
     * Turtle Shell
     */
    String MC_ITEM_TURTLE_HELMET_NAME = "minecraft:item.turtle_helmet.name";

    /**
     * Turtle Scute
     */
    String MC_ITEM_TURTLE_SHELL_PIECE_NAME = "minecraft:item.turtle_shell_piece.name";

    /**
     * Unbreakable
     */
    String MC_ITEM_UNBREAKABLE = "minecraft:item.unbreakable";

    /**
     * Vex Armor Trim
     */
    String MC_ITEM_VEX_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.vex_armor_trim_smithing_template.name";

    /**
     * Ward Armor Trim
     */
    String MC_ITEM_WARD_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.ward_armor_trim_smithing_template.name";

    /**
     * Warped Door
     */
    String MC_ITEM_WARPED_DOOR_NAME = "minecraft:item.warped_door.name";

    /**
     * Warped Fungus on a Stick
     */
    String MC_ITEM_WARPED_FUNGUS_ON_A_STICK_NAME = "minecraft:item.warped_fungus_on_a_stick.name";

    /**
     * Warped Hanging Sign
     */
    String MC_ITEM_WARPED_HANGING_SIGN_NAME = "minecraft:item.warped_hanging_sign.name";

    /**
     * Warped Sign
     */
    String MC_ITEM_WARPED_SIGN_NAME = "minecraft:item.warped_sign.name";

    /**
     * Waxed Copper Door
     */
    String MC_ITEM_WAXED_COPPER_DOOR_NAME = "minecraft:item.waxed_copper_door.name";

    /**
     * Waxed Exposed Copper Door
     */
    String MC_ITEM_WAXED_EXPOSED_COPPER_DOOR_NAME = "minecraft:item.waxed_exposed_copper_door.name";

    /**
     * Waxed Oxidized Copper Door
     */
    String MC_ITEM_WAXED_OXIDIZED_COPPER_DOOR_NAME = "minecraft:item.waxed_oxidized_copper_door.name";

    /**
     * Waxed Weathered Copper Door
     */
    String MC_ITEM_WAXED_WEATHERED_COPPER_DOOR_NAME = "minecraft:item.waxed_weathered_copper_door.name";

    /**
     * Wayfinder Armor Trim
     */
    String MC_ITEM_WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.wayfinder_armor_trim_smithing_template.name";

    /**
     * Weathered Copper Door
     */
    String MC_ITEM_WEATHERED_COPPER_DOOR_NAME = "minecraft:item.weathered_copper_door.name";

    /**
     * Wheat
     */
    String MC_ITEM_WHEAT_NAME = "minecraft:item.wheat.name";

    /**
     * Wheat Seeds
     */
    String MC_ITEM_WHEAT_SEEDS_NAME = "minecraft:item.wheat_seeds.name";

    /**
     * White Bundle
     */
    String MC_ITEM_WHITE_BUNDLE = "minecraft:item.white_bundle";

    /**
     * White Harness
     */
    String MC_ITEM_WHITE_HARNESS_NAME = "minecraft:item.white_harness.name";

    /**
     * Wild Armor Trim
     */
    String MC_ITEM_WILD_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.wild_armor_trim_smithing_template.name";

    /**
     * Wind Charge
     */
    String MC_ITEM_WIND_CHARGE_NAME = "minecraft:item.wind_charge.name";

    /**
     * Wolf Armor
     */
    String MC_ITEM_WOLF_ARMOR_NAME = "minecraft:item.wolf_armor.name";

    /**
     * Wooden Axe
     */
    String MC_ITEM_WOODEN_AXE_NAME = "minecraft:item.wooden_axe.name";

    /**
     * Oak Door
     */
    String MC_ITEM_WOODEN_DOOR_NAME = "minecraft:item.wooden_door.name";

    /**
     * Wooden Hoe
     */
    String MC_ITEM_WOODEN_HOE_NAME = "minecraft:item.wooden_hoe.name";

    /**
     * Wooden Pickaxe
     */
    String MC_ITEM_WOODEN_PICKAXE_NAME = "minecraft:item.wooden_pickaxe.name";

    /**
     * Wooden Shovel
     */
    String MC_ITEM_WOODEN_SHOVEL_NAME = "minecraft:item.wooden_shovel.name";

    /**
     * Wooden Sword
     */
    String MC_ITEM_WOODEN_SWORD_NAME = "minecraft:item.wooden_sword.name";

    /**
     * You need Creative + Operator to place this block
     */
    String MC_ITEM_WORLDBUILDER_BLOCK_FAILED = "minecraft:item.worldbuilder.block.failed";

    /**
     * Book and Quill
     */
    String MC_ITEM_WRITABLE_BOOK_NAME = "minecraft:item.writable_book.name";

    /**
     * Written Book
     */
    String MC_ITEM_WRITTEN_BOOK_NAME = "minecraft:item.written_book.name";

    /**
     * Yellow Bundle
     */
    String MC_ITEM_YELLOW_BUNDLE = "minecraft:item.yellow_bundle";

    /**
     * Yellow Harness
     */
    String MC_ITEM_YELLOW_HARNESS_NAME = "minecraft:item.yellow_harness.name";

    /**
     * Anvils
     */
    String MC_ITEMGROUP_NAME_ANVIL = "minecraft:itemGroup.name.anvil";

    /**
     * Arrows
     */
    String MC_ITEMGROUP_NAME_ARROW = "minecraft:itemGroup.name.arrow";

    /**
     * Axes
     */
    String MC_ITEMGROUP_NAME_AXE = "minecraft:itemGroup.name.axe";

    /**
     * Banners
     */
    String MC_ITEMGROUP_NAME_BANNER = "minecraft:itemGroup.name.banner";

    /**
     * Banner Patterns
     */
    String MC_ITEMGROUP_NAME_BANNER_PATTERN = "minecraft:itemGroup.name.banner_pattern";

    /**
     * Bars
     */
    String MC_ITEMGROUP_NAME_BARS = "minecraft:itemGroup.name.bars";

    /**
     * Beds
     */
    String MC_ITEMGROUP_NAME_BED = "minecraft:itemGroup.name.bed";

    /**
     * Boats
     */
    String MC_ITEMGROUP_NAME_BOAT = "minecraft:itemGroup.name.boat";

    /**
     * Boots
     */
    String MC_ITEMGROUP_NAME_BOOTS = "minecraft:itemGroup.name.boots";

    /**
     * Bundles
     */
    String MC_ITEMGROUP_NAME_BUNDLES = "minecraft:itemGroup.name.bundles";

    /**
     * Buttons
     */
    String MC_ITEMGROUP_NAME_BUTTONS = "minecraft:itemGroup.name.buttons";

    /**
     * Candles
     */
    String MC_ITEMGROUP_NAME_CANDLES = "minecraft:itemGroup.name.candles";

    /**
     * Chains
     */
    String MC_ITEMGROUP_NAME_CHAINS = "minecraft:itemGroup.name.chains";

    /**
     * Chalkboards
     */
    String MC_ITEMGROUP_NAME_CHALKBOARD = "minecraft:itemGroup.name.chalkboard";

    /**
     * Chests
     */
    String MC_ITEMGROUP_NAME_CHEST = "minecraft:itemGroup.name.chest";

    /**
     * Boats with Chest
     */
    String MC_ITEMGROUP_NAME_CHESTBOAT = "minecraft:itemGroup.name.chestboat";

    /**
     * Chestplates
     */
    String MC_ITEMGROUP_NAME_CHESTPLATE = "minecraft:itemGroup.name.chestplate";

    /**
     * Compounds
     */
    String MC_ITEMGROUP_NAME_COMPOUNDS = "minecraft:itemGroup.name.compounds";

    /**
     * Concrete
     */
    String MC_ITEMGROUP_NAME_CONCRETE = "minecraft:itemGroup.name.concrete";

    /**
     * Concrete Powder
     */
    String MC_ITEMGROUP_NAME_CONCRETEPOWDER = "minecraft:itemGroup.name.concretePowder";

    /**
     * Cooked Food
     */
    String MC_ITEMGROUP_NAME_COOKEDFOOD = "minecraft:itemGroup.name.cookedFood";

    /**
     * Copper
     */
    String MC_ITEMGROUP_NAME_COPPER = "minecraft:itemGroup.name.copper";

    /**
     * Copper Golem Statues
     */
    String MC_ITEMGROUP_NAME_COPPER_GOLEM_STATUE = "minecraft:itemGroup.name.copper_golem_statue";

    /**
     * Coral Blocks
     */
    String MC_ITEMGROUP_NAME_CORAL = "minecraft:itemGroup.name.coral";

    /**
     * Coral Decorations
     */
    String MC_ITEMGROUP_NAME_CORAL_DECORATIONS = "minecraft:itemGroup.name.coral_decorations";

    /**
     * Crops
     */
    String MC_ITEMGROUP_NAME_CROP = "minecraft:itemGroup.name.crop";

    /**
     * Doors
     */
    String MC_ITEMGROUP_NAME_DOOR = "minecraft:itemGroup.name.door";

    /**
     * Dyes
     */
    String MC_ITEMGROUP_NAME_DYE = "minecraft:itemGroup.name.dye";

    /**
     * Enchanted Books
     */
    String MC_ITEMGROUP_NAME_ENCHANTEDBOOK = "minecraft:itemGroup.name.enchantedBook";

    /**
     * Fences
     */
    String MC_ITEMGROUP_NAME_FENCE = "minecraft:itemGroup.name.fence";

    /**
     * Fence Gates
     */
    String MC_ITEMGROUP_NAME_FENCEGATE = "minecraft:itemGroup.name.fenceGate";

    /**
     * Fireworks
     */
    String MC_ITEMGROUP_NAME_FIREWORK = "minecraft:itemGroup.name.firework";

    /**
     * Firework Charges
     */
    String MC_ITEMGROUP_NAME_FIREWORKSTARS = "minecraft:itemGroup.name.fireworkStars";

    /**
     * Flowers
     */
    String MC_ITEMGROUP_NAME_FLOWER = "minecraft:itemGroup.name.flower";

    /**
     * Glass
     */
    String MC_ITEMGROUP_NAME_GLASS = "minecraft:itemGroup.name.glass";

    /**
     * Glass Panes
     */
    String MC_ITEMGROUP_NAME_GLASSPANE = "minecraft:itemGroup.name.glassPane";

    /**
     * Glazed Terracottas
     */
    String MC_ITEMGROUP_NAME_GLAZEDTERRACOTTA = "minecraft:itemGroup.name.glazedTerracotta";

    /**
     * Goat Horns
     */
    String MC_ITEMGROUP_NAME_GOATHORN = "minecraft:itemGroup.name.goatHorn";

    /**
     * Ground Cover
     */
    String MC_ITEMGROUP_NAME_GRASS = "minecraft:itemGroup.name.grass";

    /**
     * Hanging Signs
     */
    String MC_ITEMGROUP_NAME_HANGING_SIGN = "minecraft:itemGroup.name.hanging_sign";

    /**
     * Harnesses
     */
    String MC_ITEMGROUP_NAME_HARNESSES = "minecraft:itemGroup.name.harnesses";

    /**
     * Helmets
     */
    String MC_ITEMGROUP_NAME_HELMET = "minecraft:itemGroup.name.helmet";

    /**
     * Hoes
     */
    String MC_ITEMGROUP_NAME_HOE = "minecraft:itemGroup.name.hoe";

    /**
     * Horse Armor
     */
    String MC_ITEMGROUP_NAME_HORSEARMOR = "minecraft:itemGroup.name.horseArmor";

    /**
     * Lanterns
     */
    String MC_ITEMGROUP_NAME_LANTERNS = "minecraft:itemGroup.name.lanterns";

    /**
     * Leaves
     */
    String MC_ITEMGROUP_NAME_LEAVES = "minecraft:itemGroup.name.leaves";

    /**
     * Leggings
     */
    String MC_ITEMGROUP_NAME_LEGGINGS = "minecraft:itemGroup.name.leggings";

    /**
     * Lightning Rods
     */
    String MC_ITEMGROUP_NAME_LIGHTNING_ROD = "minecraft:itemGroup.name.lightning_rod";

    /**
     * Lingering Potions
     */
    String MC_ITEMGROUP_NAME_LINGERINGPOTION = "minecraft:itemGroup.name.lingeringPotion";

    /**
     * Logs
     */
    String MC_ITEMGROUP_NAME_LOG = "minecraft:itemGroup.name.log";

    /**
     * Minecarts
     */
    String MC_ITEMGROUP_NAME_MINECART = "minecraft:itemGroup.name.minecart";

    /**
     * Miscellaneous Foods
     */
    String MC_ITEMGROUP_NAME_MISCFOOD = "minecraft:itemGroup.name.miscFood";

    /**
     * Mob Eggs
     */
    String MC_ITEMGROUP_NAME_MOBEGG = "minecraft:itemGroup.name.mobEgg";

    /**
     * Infested Stone
     */
    String MC_ITEMGROUP_NAME_MONSTERSTONEEGG = "minecraft:itemGroup.name.monsterStoneEgg";

    /**
     * Mushrooms
     */
    String MC_ITEMGROUP_NAME_MUSHROOM = "minecraft:itemGroup.name.mushroom";

    /**
     * Nether Warts
     */
    String MC_ITEMGROUP_NAME_NETHERWARTBLOCK = "minecraft:itemGroup.name.netherWartBlock";

    /**
     * Ominous Bottles
     */
    String MC_ITEMGROUP_NAME_OMINOUSBOTTLE = "minecraft:itemGroup.name.ominousBottle";

    /**
     * Ores
     */
    String MC_ITEMGROUP_NAME_ORE = "minecraft:itemGroup.name.ore";

    /**
     * Permission Blocks
     */
    String MC_ITEMGROUP_NAME_PERMISSION = "minecraft:itemGroup.name.permission";

    /**
     * Pickaxes
     */
    String MC_ITEMGROUP_NAME_PICKAXE = "minecraft:itemGroup.name.pickaxe";

    /**
     * Planks
     */
    String MC_ITEMGROUP_NAME_PLANKS = "minecraft:itemGroup.name.planks";

    /**
     * Potions
     */
    String MC_ITEMGROUP_NAME_POTION = "minecraft:itemGroup.name.potion";

    /**
     * Pottery Sherds
     */
    String MC_ITEMGROUP_NAME_POTTERYSHERDS = "minecraft:itemGroup.name.potterySherds";

    /**
     * Pressure Plates
     */
    String MC_ITEMGROUP_NAME_PRESSUREPLATE = "minecraft:itemGroup.name.pressurePlate";

    /**
     * Products
     */
    String MC_ITEMGROUP_NAME_PRODUCTS = "minecraft:itemGroup.name.products";

    /**
     * Rails
     */
    String MC_ITEMGROUP_NAME_RAIL = "minecraft:itemGroup.name.rail";

    /**
     * Raw Food
     */
    String MC_ITEMGROUP_NAME_RAWFOOD = "minecraft:itemGroup.name.rawFood";

    /**
     * Records
     */
    String MC_ITEMGROUP_NAME_RECORD = "minecraft:itemGroup.name.record";

    /**
     * Sandstone
     */
    String MC_ITEMGROUP_NAME_SANDSTONE = "minecraft:itemGroup.name.sandstone";

    /**
     * Saplings
     */
    String MC_ITEMGROUP_NAME_SAPLING = "minecraft:itemGroup.name.sapling";

    /**
     * Sculk
     */
    String MC_ITEMGROUP_NAME_SCULK = "minecraft:itemGroup.name.sculk";

    /**
     * Seeds
     */
    String MC_ITEMGROUP_NAME_SEED = "minecraft:itemGroup.name.seed";

    /**
     * Shelves
     */
    String MC_ITEMGROUP_NAME_SHELF = "minecraft:itemGroup.name.shelf";

    /**
     * Shovels
     */
    String MC_ITEMGROUP_NAME_SHOVEL = "minecraft:itemGroup.name.shovel";

    /**
     * Shulker Boxes
     */
    String MC_ITEMGROUP_NAME_SHULKERBOX = "minecraft:itemGroup.name.shulkerBox";

    /**
     * Signs
     */
    String MC_ITEMGROUP_NAME_SIGN = "minecraft:itemGroup.name.sign";

    /**
     * Mob Skulls
     */
    String MC_ITEMGROUP_NAME_SKULL = "minecraft:itemGroup.name.skull";

    /**
     * Slabs
     */
    String MC_ITEMGROUP_NAME_SLAB = "minecraft:itemGroup.name.slab";

    /**
     * Smithing Templates
     */
    String MC_ITEMGROUP_NAME_SMITHING_TEMPLATES = "minecraft:itemGroup.name.smithing_templates";

    /**
     * Splash Potions
     */
    String MC_ITEMGROUP_NAME_SPLASHPOTION = "minecraft:itemGroup.name.splashPotion";

    /**
     * Terracotta
     */
    String MC_ITEMGROUP_NAME_STAINEDCLAY = "minecraft:itemGroup.name.stainedClay";

    /**
     * Stairs
     */
    String MC_ITEMGROUP_NAME_STAIRS = "minecraft:itemGroup.name.stairs";

    /**
     * Stone
     */
    String MC_ITEMGROUP_NAME_STONE = "minecraft:itemGroup.name.stone";

    /**
     * Decorative Stone
     */
    String MC_ITEMGROUP_NAME_STONEBRICK = "minecraft:itemGroup.name.stoneBrick";

    /**
     * Swords
     */
    String MC_ITEMGROUP_NAME_SWORD = "minecraft:itemGroup.name.sword";

    /**
     * Trapdoors
     */
    String MC_ITEMGROUP_NAME_TRAPDOOR = "minecraft:itemGroup.name.trapdoor";

    /**
     * Walls
     */
    String MC_ITEMGROUP_NAME_WALLS = "minecraft:itemGroup.name.walls";

    /**
     * Woods
     */
    String MC_ITEMGROUP_NAME_WOOD = "minecraft:itemGroup.name.wood";

    /**
     * Wool
     */
    String MC_ITEMGROUP_NAME_WOOL = "minecraft:itemGroup.name.wool";

    /**
     * Wool Carpet
     */
    String MC_ITEMGROUP_NAME_WOOLCARPET = "minecraft:itemGroup.name.woolCarpet";

    /**
     * Search Items
     */
    String MC_ITEMGROUP_SEARCH = "minecraft:itemGroup.search";

    /**
     * Done
     */
    String MC_JIGSAW_EXIT_DONE = "minecraft:jigsaw.exit.done";

    /**
     * Aligned
     */
    String MC_JIGSAW_JOINT_TYPE_ALIGNED = "minecraft:jigsaw.joint_type.aligned";

    /**
     * Rollable
     */
    String MC_JIGSAW_JOINT_TYPE_ROLLABLE = "minecraft:jigsaw.joint_type.rollable";

    /**
     * Turns Into:
     */
    String MC_JIGSAW_TITLE_FINAL_BLOCK = "minecraft:jigsaw.title.final_block";

    /**
     * Joint Type:
     */
    String MC_JIGSAW_TITLE_JOINT_TYPE = "minecraft:jigsaw.title.joint_type";

    /**
     * Aligned
     */
    String MC_JIGSAW_TITLE_JOINT_TYPE_ALIGNED = "minecraft:jigsaw.title.joint_type.aligned";

    /**
     * Name:
     */
    String MC_JIGSAW_TITLE_NAME = "minecraft:jigsaw.title.name";

    /**
     * Placement Priority:
     */
    String MC_JIGSAW_TITLE_PLACEMENT_PRIORITY = "minecraft:jigsaw.title.placement_priority";

    /**
     * Selection Priority:
     */
    String MC_JIGSAW_TITLE_SELECTION_PRIORITY = "minecraft:jigsaw.title.selection_priority";

    /**
     * Target Name:
     */
    String MC_JIGSAW_TITLE_TARGET = "minecraft:jigsaw.title.target";

    /**
     * Target Pool:
     */
    String MC_JIGSAW_TITLE_TARGET_POOL = "minecraft:jigsaw.title.target_pool";

    /**
     * Jigsaw Block
     */
    String MC_JIGSAW_BLOCK_TITLE = "minecraft:jigsaw_block.title";

    /**
     * %s: Did not find valid value for property. Expected %s
     */
    String MC_JSONVALIDATIONERROR_INVALIDVALUEERROR = "minecraft:jsonValidationError.invalidValueError";

    /**
     * %s: Could not find required property '%s'
     */
    String MC_JSONVALIDATIONERROR_REQUIREDPROPERTYERROR = "minecraft:jsonValidationError.requiredPropertyError";

    /**
     * %s: Invalid type for property. Expected %s got %s
     */
    String MC_JSONVALIDATIONERROR_TYPEERROR = "minecraft:jsonValidationError.typeError";

    /**
     * Attack/Destroy
     */
    String MC_KEY_ATTACK = "minecraft:key.attack";

    /**
     * Walk Backwards
     */
    String MC_KEY_BACK = "minecraft:key.back";

    /**
     * Cancel    
     */
    String MC_KEY_CANCELTOAST = "minecraft:key.cancelToast";

    /**
     * Gameplay
     */
    String MC_KEY_CATEGORIES_GAMEPLAY = "minecraft:key.categories.gameplay";

    /**
     * Inventory
     */
    String MC_KEY_CATEGORIES_INVENTORY = "minecraft:key.categories.inventory";

    /**
     * Miscellaneous
     */
    String MC_KEY_CATEGORIES_MISC = "minecraft:key.categories.misc";

    /**
     * Movement
     */
    String MC_KEY_CATEGORIES_MOVEMENT = "minecraft:key.categories.movement";

    /**
     * Multiplayer
     */
    String MC_KEY_CATEGORIES_MULTIPLAYER = "minecraft:key.categories.multiplayer";

    /**
     * Streaming
     */
    String MC_KEY_CATEGORIES_STREAM = "minecraft:key.categories.stream";

    /**
     * Game Interface
     */
    String MC_KEY_CATEGORIES_UI = "minecraft:key.categories.ui";

    /**
     * Chat button
     */
    String MC_KEY_CHAT = "minecraft:key.chat";

    /**
     * Code Builder
     */
    String MC_KEY_CODEBUILDER = "minecraft:key.codeBuilder";

    /**
     * Open Command
     */
    String MC_KEY_COMMAND = "minecraft:key.command";

    /**
     * Copy Coordinates
     */
    String MC_KEY_COPYCOORDINATES = "minecraft:key.copyCoordinates";

    /**
     * Copy Facing Coordinates
     */
    String MC_KEY_COPYFACINGCOORDINATES = "minecraft:key.copyFacingCoordinates";

    /**
     * Cycle Item Left
     */
    String MC_KEY_CYCLEITEMLEFT = "minecraft:key.cycleItemLeft";

    /**
     * Cycle Item Right
     */
    String MC_KEY_CYCLEITEMRIGHT = "minecraft:key.cycleItemRight";

    /**
     * Cycle Fixed Inventory
     */
    String MC_KEY_CYCLEFIXEDINVENTORY = "minecraft:key.cyclefixedinventory";

    /**
     * Decline    
     */
    String MC_KEY_DECLINETOAST = "minecraft:key.declineToast";

    /**
     * Drop Item
     */
    String MC_KEY_DROP = "minecraft:key.drop";

    /**
     * Emote
     */
    String MC_KEY_EMOTE = "minecraft:key.emote";

    /**
     * Fly Down Slow
     */
    String MC_KEY_FLYDOWNSLOW = "minecraft:key.flyDownSlow";

    /**
     * Fly Up Slow
     */
    String MC_KEY_FLYUPSLOW = "minecraft:key.flyUpSlow";

    /**
     * Walk Forwards
     */
    String MC_KEY_FORWARD = "minecraft:key.forward";

    /**
     * Toggle Fullscreen
     */
    String MC_KEY_FULLSCREEN = "minecraft:key.fullscreen";

    /**
     * Hotbar Slot 1
     */
    String MC_KEY_HOTBAR_1 = "minecraft:key.hotbar.1";

    /**
     * Hotbar Slot 2
     */
    String MC_KEY_HOTBAR_2 = "minecraft:key.hotbar.2";

    /**
     * Hotbar Slot 3
     */
    String MC_KEY_HOTBAR_3 = "minecraft:key.hotbar.3";

    /**
     * Hotbar Slot 4
     */
    String MC_KEY_HOTBAR_4 = "minecraft:key.hotbar.4";

    /**
     * Hotbar Slot 5
     */
    String MC_KEY_HOTBAR_5 = "minecraft:key.hotbar.5";

    /**
     * Hotbar Slot 6
     */
    String MC_KEY_HOTBAR_6 = "minecraft:key.hotbar.6";

    /**
     * Hotbar Slot 7
     */
    String MC_KEY_HOTBAR_7 = "minecraft:key.hotbar.7";

    /**
     * Hotbar Slot 8
     */
    String MC_KEY_HOTBAR_8 = "minecraft:key.hotbar.8";

    /**
     * Hotbar Slot 9
     */
    String MC_KEY_HOTBAR_9 = "minecraft:key.hotbar.9";

    /**
     * Immersive Reader
     */
    String MC_KEY_IMMERSIVEREADER = "minecraft:key.immersivereader";

    /**
     * Game Menu
     */
    String MC_KEY_INGAMEMENU = "minecraft:key.inGameMenu";

    /**
     * Open Notification
     */
    String MC_KEY_INTERACTWITHTOAST = "minecraft:key.interactwithtoast";

    /**
     * Inventory
     */
    String MC_KEY_INVENTORY = "minecraft:key.inventory";

    /**
     * Jump/Fly Up
     */
    String MC_KEY_JUMP = "minecraft:key.jump";

    /**
     * Strafe Left
     */
    String MC_KEY_LEFT = "minecraft:key.left";

    /**
     * Look Center
     */
    String MC_KEY_LOOKCENTER = "minecraft:key.lookCenter";

    /**
     * Look Down
     */
    String MC_KEY_LOOKDOWN = "minecraft:key.lookDown";

    /**
     * Look Down Left
     */
    String MC_KEY_LOOKDOWNLEFT = "minecraft:key.lookDownLeft";

    /**
     * Look Down Right
     */
    String MC_KEY_LOOKDOWNRIGHT = "minecraft:key.lookDownRight";

    /**
     * Look Down Slight
     */
    String MC_KEY_LOOKDOWNSLIGHT = "minecraft:key.lookDownSlight";

    /**
     * Look Down Smooth
     */
    String MC_KEY_LOOKDOWNSMOOTH = "minecraft:key.lookDownSmooth";

    /**
     * Look Left
     */
    String MC_KEY_LOOKLEFT = "minecraft:key.lookLeft";

    /**
     * Look Left Smooth
     */
    String MC_KEY_LOOKLEFTSMOOTH = "minecraft:key.lookLeftSmooth";

    /**
     * Look Right
     */
    String MC_KEY_LOOKRIGHT = "minecraft:key.lookRight";

    /**
     * Look Right Smooth
     */
    String MC_KEY_LOOKRIGHTSMOOTH = "minecraft:key.lookRightSmooth";

    /**
     * Look Up
     */
    String MC_KEY_LOOKUP = "minecraft:key.lookUp";

    /**
     * Look Up Left
     */
    String MC_KEY_LOOKUPLEFT = "minecraft:key.lookUpLeft";

    /**
     * Look Up Right
     */
    String MC_KEY_LOOKUPRIGHT = "minecraft:key.lookUpRight";

    /**
     * Look Up Slight
     */
    String MC_KEY_LOOKUPSLIGHT = "minecraft:key.lookUpSlight";

    /**
     * Look Up Smooth
     */
    String MC_KEY_LOOKUPSMOOTH = "minecraft:key.lookUpSmooth";

    /**
     * Menu Cancel
     */
    String MC_KEY_MENUCANCEL = "minecraft:key.menuCancel";

    /**
     * Menu Tab Left
     */
    String MC_KEY_MENUTABLEFT = "minecraft:key.menuTabLeft";

    /**
     * Menu Tab Right
     */
    String MC_KEY_MENUTABRIGHT = "minecraft:key.menuTabRight";

    /**
     * Mob Effects
     */
    String MC_KEY_MOBEFFECTS = "minecraft:key.mobEffects";

    /**
     * Open Notification/Mob Effects
     */
    String MC_KEY_MOBEFFECTSANDINTERACTWITHTOAST = "minecraft:key.mobeffectsandinteractwithtoast";

    /**
     * Button %1#s
     */
    String MC_KEY_MOUSEBUTTON = "minecraft:key.mouseButton";

    /**
     * Move Back
     */
    String MC_KEY_MOVEBACK = "minecraft:key.moveBack";

    /**
     * Move Forward
     */
    String MC_KEY_MOVEFORWARD = "minecraft:key.moveForward";

    /**
     * Move Left
     */
    String MC_KEY_MOVELEFT = "minecraft:key.moveLeft";

    /**
     * Move Right
     */
    String MC_KEY_MOVERIGHT = "minecraft:key.moveRight";

    /**
     * Pause
     */
    String MC_KEY_PAUSE = "minecraft:key.pause";

    /**
     * Pick Block
     */
    String MC_KEY_PICKITEM = "minecraft:key.pickItem";

    /**
     * List Players
     */
    String MC_KEY_PLAYERLIST = "minecraft:key.playerlist";

    /**
     * Strafe Right
     */
    String MC_KEY_RIGHT = "minecraft:key.right";

    /**
     * Show Scoreboard
     */
    String MC_KEY_SCOREBOARD = "minecraft:key.scoreboard";

    /**
     * Take Screenshot
     */
    String MC_KEY_SCREENSHOT = "minecraft:key.screenshot";

    /**
     * Cancel Current Action
     */
    String MC_KEY_SCRIPT_CANCEL = "minecraft:key.script_cancel";

    /**
     * Open Chat UI
     */
    String MC_KEY_SCRIPT_OPEN_CHAT = "minecraft:key.script_open_chat";

    /**
     * Open Inventory UI
     */
    String MC_KEY_SCRIPT_OPEN_INVENTORY = "minecraft:key.script_open_inventory";

    /**
     * Open Map UI
     */
    String MC_KEY_SCRIPT_OPEN_MAP = "minecraft:key.script_open_map";

    /**
     * Open Mission UI
     */
    String MC_KEY_SCRIPT_OPEN_MISSION = "minecraft:key.script_open_mission";

    /**
     * Toggle Mouse Mode
     */
    String MC_KEY_SCRIPT_TOGGLE = "minecraft:key.script_toggle";

    /**
     * Toggle Cinematic Camera
     */
    String MC_KEY_SMOOTHCAMERA = "minecraft:key.smoothCamera";

    /**
     * Sneak/Fly Down
     */
    String MC_KEY_SNEAK = "minecraft:key.sneak";

    /**
     * Highlight Players (Spectators)
     */
    String MC_KEY_SPECTATOROUTLINES = "minecraft:key.spectatorOutlines";

    /**
     * Sprint
     */
    String MC_KEY_SPRINT = "minecraft:key.sprint";

    /**
     * Show Stream Commercials
     */
    String MC_KEY_STREAMCOMMERCIAL = "minecraft:key.streamCommercial";

    /**
     * Pause/Unpause Stream
     */
    String MC_KEY_STREAMPAUSEUNPAUSE = "minecraft:key.streamPauseUnpause";

    /**
     * Start/Stop Stream
     */
    String MC_KEY_STREAMSTARTSTOP = "minecraft:key.streamStartStop";

    /**
     * Push To Talk/Mute
     */
    String MC_KEY_STREAMTOGGLEMIC = "minecraft:key.streamToggleMic";

    /**
     * Control Tips
     */
    String MC_KEY_TOGGLECONTROLTIPS = "minecraft:key.toggleControlTips";

    /**
     * Toggle Perspective
     */
    String MC_KEY_TOGGLEPERSPECTIVE = "minecraft:key.togglePerspective";

    /**
     * Toggle Renderer
     */
    String MC_KEY_TOGGLERENDERER = "minecraft:key.toggleRenderer";

    /**
     * Use Item/Place Block
     */
    String MC_KEY_USE = "minecraft:key.use";

    /**
     * View    
     */
    String MC_KEY_VIEWTOAST = "minecraft:key.viewToast";

    /**
     * 0
     */
    String MC_KEYBOARD_KEYNAME_0 = "minecraft:keyboard.keyName.0";

    /**
     * 1
     */
    String MC_KEYBOARD_KEYNAME_1 = "minecraft:keyboard.keyName.1";

    /**
     * 2
     */
    String MC_KEYBOARD_KEYNAME_2 = "minecraft:keyboard.keyName.2";

    /**
     * 3
     */
    String MC_KEYBOARD_KEYNAME_3 = "minecraft:keyboard.keyName.3";

    /**
     * 4
     */
    String MC_KEYBOARD_KEYNAME_4 = "minecraft:keyboard.keyName.4";

    /**
     * 5
     */
    String MC_KEYBOARD_KEYNAME_5 = "minecraft:keyboard.keyName.5";

    /**
     * 6
     */
    String MC_KEYBOARD_KEYNAME_6 = "minecraft:keyboard.keyName.6";

    /**
     * 7
     */
    String MC_KEYBOARD_KEYNAME_7 = "minecraft:keyboard.keyName.7";

    /**
     * 8
     */
    String MC_KEYBOARD_KEYNAME_8 = "minecraft:keyboard.keyName.8";

    /**
     * 9
     */
    String MC_KEYBOARD_KEYNAME_9 = "minecraft:keyboard.keyName.9";

    /**
     * A
     */
    String MC_KEYBOARD_KEYNAME_A = "minecraft:keyboard.keyName.a";

    /**
     * ADD
     */
    String MC_KEYBOARD_KEYNAME_ADD = "minecraft:keyboard.keyName.add";

    /**
     * N+
     */
    String MC_KEYBOARD_KEYNAME_ADD_SHORT = "minecraft:keyboard.keyName.add.short";

    /**
     * ALTERNATIVE
     */
    String MC_KEYBOARD_KEYNAME_ALT = "minecraft:keyboard.keyName.alt";

    /**
     * ALT
     */
    String MC_KEYBOARD_KEYNAME_ALT_SHORT = "minecraft:keyboard.keyName.alt.short";

    /**
     * APOSTROPHE
     */
    String MC_KEYBOARD_KEYNAME_APOSTROPHE = "minecraft:keyboard.keyName.apostrophe";

    /**
     * '
     */
    String MC_KEYBOARD_KEYNAME_APOSTROPHE_SHORT = "minecraft:keyboard.keyName.apostrophe.short";

    /**
     * B
     */
    String MC_KEYBOARD_KEYNAME_B = "minecraft:keyboard.keyName.b";

    /**
     * BACKSLASH
     */
    String MC_KEYBOARD_KEYNAME_BACKSLASH = "minecraft:keyboard.keyName.backslash";

    /**
     * \
     */
    String MC_KEYBOARD_KEYNAME_BACKSLASH_SHORT = "minecraft:keyboard.keyName.backslash.short";

    /**
     * BACK
     */
    String MC_KEYBOARD_KEYNAME_BACKSPACE = "minecraft:keyboard.keyName.backspace";

    /**
     * BACK
     */
    String MC_KEYBOARD_KEYNAME_BACKSPACE_SHORT = "minecraft:keyboard.keyName.backspace.short";

    /**
     * C
     */
    String MC_KEYBOARD_KEYNAME_C = "minecraft:keyboard.keyName.c";

    /**
     * CAPITAL
     */
    String MC_KEYBOARD_KEYNAME_CAPSLOCK = "minecraft:keyboard.keyName.capsLock";

    /**
     * CAPS
     */
    String MC_KEYBOARD_KEYNAME_CAPSLOCK_SHORT = "minecraft:keyboard.keyName.capsLock.short";

    /**
     * COMMA
     */
    String MC_KEYBOARD_KEYNAME_COMMA = "minecraft:keyboard.keyName.comma";

    /**
     * ,
     */
    String MC_KEYBOARD_KEYNAME_COMMA_SHORT = "minecraft:keyboard.keyName.comma.short";

    /**
     * CONTROL
     */
    String MC_KEYBOARD_KEYNAME_CONTROL = "minecraft:keyboard.keyName.control";

    /**
     * CTRL
     */
    String MC_KEYBOARD_KEYNAME_CONTROL_SHORT = "minecraft:keyboard.keyName.control.short";

    /**
     * D
     */
    String MC_KEYBOARD_KEYNAME_D = "minecraft:keyboard.keyName.d";

    /**
     * DECIMAL
     */
    String MC_KEYBOARD_KEYNAME_DECIMAL = "minecraft:keyboard.keyName.decimal";

    /**
     * N.
     */
    String MC_KEYBOARD_KEYNAME_DECIMAL_SHORT = "minecraft:keyboard.keyName.decimal.short";

    /**
     * DELETE
     */
    String MC_KEYBOARD_KEYNAME_DELETE = "minecraft:keyboard.keyName.delete";

    /**
     * DEL
     */
    String MC_KEYBOARD_KEYNAME_DELETE_SHORT = "minecraft:keyboard.keyName.delete.short";

    /**
     * DIVIDE
     */
    String MC_KEYBOARD_KEYNAME_DIVIDE = "minecraft:keyboard.keyName.divide";

    /**
     * N/
     */
    String MC_KEYBOARD_KEYNAME_DIVIDE_SHORT = "minecraft:keyboard.keyName.divide.short";

    /**
     * DOWN
     */
    String MC_KEYBOARD_KEYNAME_DOWN = "minecraft:keyboard.keyName.down";

    /**
     * DOWN
     */
    String MC_KEYBOARD_KEYNAME_DOWN_SHORT = "minecraft:keyboard.keyName.down.short";

    /**
     * E
     */
    String MC_KEYBOARD_KEYNAME_E = "minecraft:keyboard.keyName.e";

    /**
     * END
     */
    String MC_KEYBOARD_KEYNAME_END = "minecraft:keyboard.keyName.end";

    /**
     * END
     */
    String MC_KEYBOARD_KEYNAME_END_SHORT = "minecraft:keyboard.keyName.end.short";

    /**
     * EQUALS
     */
    String MC_KEYBOARD_KEYNAME_EQUALS = "minecraft:keyboard.keyName.equals";

    /**
     * =
     */
    String MC_KEYBOARD_KEYNAME_EQUALS_SHORT = "minecraft:keyboard.keyName.equals.short";

    /**
     * ESCAPE
     */
    String MC_KEYBOARD_KEYNAME_ESCAPE = "minecraft:keyboard.keyName.escape";

    /**
     * ESC
     */
    String MC_KEYBOARD_KEYNAME_ESCAPE_SHORT = "minecraft:keyboard.keyName.escape.short";

    /**
     * F
     */
    String MC_KEYBOARD_KEYNAME_F = "minecraft:keyboard.keyName.f";

    /**
     * F1
     */
    String MC_KEYBOARD_KEYNAME_F1 = "minecraft:keyboard.keyName.f1";

    /**
     * F10
     */
    String MC_KEYBOARD_KEYNAME_F10 = "minecraft:keyboard.keyName.f10";

    /**
     * F11
     */
    String MC_KEYBOARD_KEYNAME_F11 = "minecraft:keyboard.keyName.f11";

    /**
     * F12
     */
    String MC_KEYBOARD_KEYNAME_F12 = "minecraft:keyboard.keyName.f12";

    /**
     * F13
     */
    String MC_KEYBOARD_KEYNAME_F13 = "minecraft:keyboard.keyName.f13";

    /**
     * F2
     */
    String MC_KEYBOARD_KEYNAME_F2 = "minecraft:keyboard.keyName.f2";

    /**
     * F3
     */
    String MC_KEYBOARD_KEYNAME_F3 = "minecraft:keyboard.keyName.f3";

    /**
     * F4
     */
    String MC_KEYBOARD_KEYNAME_F4 = "minecraft:keyboard.keyName.f4";

    /**
     * F5
     */
    String MC_KEYBOARD_KEYNAME_F5 = "minecraft:keyboard.keyName.f5";

    /**
     * F6
     */
    String MC_KEYBOARD_KEYNAME_F6 = "minecraft:keyboard.keyName.f6";

    /**
     * F7
     */
    String MC_KEYBOARD_KEYNAME_F7 = "minecraft:keyboard.keyName.f7";

    /**
     * F8
     */
    String MC_KEYBOARD_KEYNAME_F8 = "minecraft:keyboard.keyName.f8";

    /**
     * F9
     */
    String MC_KEYBOARD_KEYNAME_F9 = "minecraft:keyboard.keyName.f9";

    /**
     * G
     */
    String MC_KEYBOARD_KEYNAME_G = "minecraft:keyboard.keyName.g";

    /**
     * GRAVE
     */
    String MC_KEYBOARD_KEYNAME_GRAVE = "minecraft:keyboard.keyName.grave";

    /**
     * `
     */
    String MC_KEYBOARD_KEYNAME_GRAVE_SHORT = "minecraft:keyboard.keyName.grave.short";

    /**
     * H
     */
    String MC_KEYBOARD_KEYNAME_H = "minecraft:keyboard.keyName.h";

    /**
     * HOME
     */
    String MC_KEYBOARD_KEYNAME_HOME = "minecraft:keyboard.keyName.home";

    /**
     * HOME
     */
    String MC_KEYBOARD_KEYNAME_HOME_SHORT = "minecraft:keyboard.keyName.home.short";

    /**
     * I
     */
    String MC_KEYBOARD_KEYNAME_I = "minecraft:keyboard.keyName.i";

    /**
     * INSERT
     */
    String MC_KEYBOARD_KEYNAME_INSERT = "minecraft:keyboard.keyName.insert";

    /**
     * INS
     */
    String MC_KEYBOARD_KEYNAME_INSERT_SHORT = "minecraft:keyboard.keyName.insert.short";

    /**
     * J
     */
    String MC_KEYBOARD_KEYNAME_J = "minecraft:keyboard.keyName.j";

    /**
     * K
     */
    String MC_KEYBOARD_KEYNAME_K = "minecraft:keyboard.keyName.k";

    /**
     * L
     */
    String MC_KEYBOARD_KEYNAME_L = "minecraft:keyboard.keyName.l";

    /**
     * LBRACKET
     */
    String MC_KEYBOARD_KEYNAME_LBRACKET = "minecraft:keyboard.keyName.lbracket";

    /**
     * [
     */
    String MC_KEYBOARD_KEYNAME_LBRACKET_SHORT = "minecraft:keyboard.keyName.lbracket.short";

    /**
     * LEFT
     */
    String MC_KEYBOARD_KEYNAME_LEFT = "minecraft:keyboard.keyName.left";

    /**
     * LEFT
     */
    String MC_KEYBOARD_KEYNAME_LEFT_SHORT = "minecraft:keyboard.keyName.left.short";

    /**
     * SHIFT
     */
    String MC_KEYBOARD_KEYNAME_LSHIFT = "minecraft:keyboard.keyName.lshift";

    /**
     * SHIFT
     */
    String MC_KEYBOARD_KEYNAME_LSHIFT_SHORT = "minecraft:keyboard.keyName.lshift.short";

    /**
     * M
     */
    String MC_KEYBOARD_KEYNAME_M = "minecraft:keyboard.keyName.m";

    /**
     * MINUS
     */
    String MC_KEYBOARD_KEYNAME_MINUS = "minecraft:keyboard.keyName.minus";

    /**
     * -
     */
    String MC_KEYBOARD_KEYNAME_MINUS_SHORT = "minecraft:keyboard.keyName.minus.short";

    /**
     * MULTIPLY
     */
    String MC_KEYBOARD_KEYNAME_MULTIPLY = "minecraft:keyboard.keyName.multiply";

    /**
     * N*
     */
    String MC_KEYBOARD_KEYNAME_MULTIPLY_SHORT = "minecraft:keyboard.keyName.multiply.short";

    /**
     * N
     */
    String MC_KEYBOARD_KEYNAME_N = "minecraft:keyboard.keyName.n";

    /**
     * NUMLOCK
     */
    String MC_KEYBOARD_KEYNAME_NUMLOCK = "minecraft:keyboard.keyName.numLock";

    /**
     * NUMLOCK
     */
    String MC_KEYBOARD_KEYNAME_NUMLOCK_SHORT = "minecraft:keyboard.keyName.numLock.short";

    /**
     * NUMPAD0
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD0 = "minecraft:keyboard.keyName.numpad0";

    /**
     * N0
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD0_SHORT = "minecraft:keyboard.keyName.numpad0.short";

    /**
     * NUMPAD1
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD1 = "minecraft:keyboard.keyName.numpad1";

    /**
     * N1
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD1_SHORT = "minecraft:keyboard.keyName.numpad1.short";

    /**
     * NUMPAD2
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD2 = "minecraft:keyboard.keyName.numpad2";

    /**
     * N2
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD2_SHORT = "minecraft:keyboard.keyName.numpad2.short";

    /**
     * NUMPAD3
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD3 = "minecraft:keyboard.keyName.numpad3";

    /**
     * N3
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD3_SHORT = "minecraft:keyboard.keyName.numpad3.short";

    /**
     * NUMPAD4
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD4 = "minecraft:keyboard.keyName.numpad4";

    /**
     * N4
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD4_SHORT = "minecraft:keyboard.keyName.numpad4.short";

    /**
     * NUMPAD5
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD5 = "minecraft:keyboard.keyName.numpad5";

    /**
     * N5
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD5_SHORT = "minecraft:keyboard.keyName.numpad5.short";

    /**
     * NUMPAD6
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD6 = "minecraft:keyboard.keyName.numpad6";

    /**
     * N6
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD6_SHORT = "minecraft:keyboard.keyName.numpad6.short";

    /**
     * NUMPAD7
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD7 = "minecraft:keyboard.keyName.numpad7";

    /**
     * N7
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD7_SHORT = "minecraft:keyboard.keyName.numpad7.short";

    /**
     * NUMPAD8
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD8 = "minecraft:keyboard.keyName.numpad8";

    /**
     * N8
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD8_SHORT = "minecraft:keyboard.keyName.numpad8.short";

    /**
     * NUMPAD9
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD9 = "minecraft:keyboard.keyName.numpad9";

    /**
     * N9
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD9_SHORT = "minecraft:keyboard.keyName.numpad9.short";

    /**
     * O
     */
    String MC_KEYBOARD_KEYNAME_O = "minecraft:keyboard.keyName.o";

    /**
     * P
     */
    String MC_KEYBOARD_KEYNAME_P = "minecraft:keyboard.keyName.p";

    /**
     * PAUSE
     */
    String MC_KEYBOARD_KEYNAME_PAUSE = "minecraft:keyboard.keyName.pause";

    /**
     * PAUSE
     */
    String MC_KEYBOARD_KEYNAME_PAUSE_SHORT = "minecraft:keyboard.keyName.pause.short";

    /**
     * PERIOD
     */
    String MC_KEYBOARD_KEYNAME_PERIOD = "minecraft:keyboard.keyName.period";

    /**
     * .
     */
    String MC_KEYBOARD_KEYNAME_PERIOD_SHORT = "minecraft:keyboard.keyName.period.short";

    /**
     * PAGE DOWN
     */
    String MC_KEYBOARD_KEYNAME_PGDOWN = "minecraft:keyboard.keyName.pgDown";

    /**
     * PgDn
     */
    String MC_KEYBOARD_KEYNAME_PGDOWN_SHORT = "minecraft:keyboard.keyName.pgDown.short";

    /**
     * PAGE UP
     */
    String MC_KEYBOARD_KEYNAME_PGUP = "minecraft:keyboard.keyName.pgUp";

    /**
     * PgUp
     */
    String MC_KEYBOARD_KEYNAME_PGUP_SHORT = "minecraft:keyboard.keyName.pgUp.short";

    /**
     * Q
     */
    String MC_KEYBOARD_KEYNAME_Q = "minecraft:keyboard.keyName.q";

    /**
     * R
     */
    String MC_KEYBOARD_KEYNAME_R = "minecraft:keyboard.keyName.r";

    /**
     * RBRACKET
     */
    String MC_KEYBOARD_KEYNAME_RBRACKET = "minecraft:keyboard.keyName.rbracket";

    /**
     * ]
     */
    String MC_KEYBOARD_KEYNAME_RBRACKET_SHORT = "minecraft:keyboard.keyName.rbracket.short";

    /**
     * RETURN
     */
    String MC_KEYBOARD_KEYNAME_RETURN = "minecraft:keyboard.keyName.return";

    /**
     * RETURN
     */
    String MC_KEYBOARD_KEYNAME_RETURN_SHORT = "minecraft:keyboard.keyName.return.short";

    /**
     * RIGHT
     */
    String MC_KEYBOARD_KEYNAME_RIGHT = "minecraft:keyboard.keyName.right";

    /**
     * RIGHT
     */
    String MC_KEYBOARD_KEYNAME_RIGHT_SHORT = "minecraft:keyboard.keyName.right.short";

    /**
     * S
     */
    String MC_KEYBOARD_KEYNAME_S = "minecraft:keyboard.keyName.s";

    /**
     * SCROLL
     */
    String MC_KEYBOARD_KEYNAME_SCROLL = "minecraft:keyboard.keyName.scroll";

    /**
     * SCROLL
     */
    String MC_KEYBOARD_KEYNAME_SCROLL_SHORT = "minecraft:keyboard.keyName.scroll.short";

    /**
     * SEMICOLON
     */
    String MC_KEYBOARD_KEYNAME_SEMICOLON = "minecraft:keyboard.keyName.semicolon";

    /**
     * ;
     */
    String MC_KEYBOARD_KEYNAME_SEMICOLON_SHORT = "minecraft:keyboard.keyName.semicolon.short";

    /**
     * SLASH
     */
    String MC_KEYBOARD_KEYNAME_SLASH = "minecraft:keyboard.keyName.slash";

    /**
     * /
     */
    String MC_KEYBOARD_KEYNAME_SLASH_SHORT = "minecraft:keyboard.keyName.slash.short";

    /**
     * SPACE
     */
    String MC_KEYBOARD_KEYNAME_SPACE = "minecraft:keyboard.keyName.space";

    /**
     * SPACE
     */
    String MC_KEYBOARD_KEYNAME_SPACE_SHORT = "minecraft:keyboard.keyName.space.short";

    /**
     * SUBTRACT
     */
    String MC_KEYBOARD_KEYNAME_SUBTRACT = "minecraft:keyboard.keyName.subtract";

    /**
     * N-
     */
    String MC_KEYBOARD_KEYNAME_SUBTRACT_SHORT = "minecraft:keyboard.keyName.subtract.short";

    /**
     * T
     */
    String MC_KEYBOARD_KEYNAME_T = "minecraft:keyboard.keyName.t";

    /**
     * TAB
     */
    String MC_KEYBOARD_KEYNAME_TAB = "minecraft:keyboard.keyName.tab";

    /**
     * TAB
     */
    String MC_KEYBOARD_KEYNAME_TAB_SHORT = "minecraft:keyboard.keyName.tab.short";

    /**
     * U
     */
    String MC_KEYBOARD_KEYNAME_U = "minecraft:keyboard.keyName.u";

    /**
     * UP
     */
    String MC_KEYBOARD_KEYNAME_UP = "minecraft:keyboard.keyName.up";

    /**
     * UP
     */
    String MC_KEYBOARD_KEYNAME_UP_SHORT = "minecraft:keyboard.keyName.up.short";

    /**
     * V
     */
    String MC_KEYBOARD_KEYNAME_V = "minecraft:keyboard.keyName.v";

    /**
     * W
     */
    String MC_KEYBOARD_KEYNAME_W = "minecraft:keyboard.keyName.w";

    /**
     * X
     */
    String MC_KEYBOARD_KEYNAME_X = "minecraft:keyboard.keyName.x";

    /**
     * Y
     */
    String MC_KEYBOARD_KEYNAME_Y = "minecraft:keyboard.keyName.y";

    /**
     * Z
     */
    String MC_KEYBOARD_KEYNAME_Z = "minecraft:keyboard.keyName.z";

    /**
     * Line %s
     */
    String MC_KEYBOARD_ONSCREEN_LINENUMBER = "minecraft:keyboard.onScreen.lineNumber";

    /**
     * Go Back
     */
    String MC_LICENSED_CONTENT_GOBACK = "minecraft:licensed_content.goBack";

    /**
     * To view licensed content, please visit https://minecraft.net/licensed-content/ in any web browser.
     */
    String MC_LICENSED_CONTENT_VIEWLICENSEDCONTENT = "minecraft:licensed_content.viewLicensedContent";

    /**
     * Basic Map
     */
    String MC_MAP_BASICMAP = "minecraft:map.basicMap";

    /**
     * Copy Map
     */
    String MC_MAP_CLONE = "minecraft:map.clone";

    /**
     * Zoom Out & Clear
     */
    String MC_MAP_EXTENDANDCLEAR = "minecraft:map.extendAndClear";

    /**
     * Map that Shows Players
     */
    String MC_MAP_LOCATORMAP = "minecraft:map.locatorMap";

    /**
     * Lock Map
     */
    String MC_MAP_LOCK = "minecraft:map.lock";

    /**
     * Map Name
     */
    String MC_MAP_NAME = "minecraft:map.name";

    /**
     * Position: %s, %s, %s
     */
    String MC_MAP_POSITION = "minecraft:map.position";

    /**
     * Agent Pos: %s, %s, %s
     */
    String MC_MAP_POSITION_AGENT = "minecraft:map.position.agent";

    /**
     * Rename Map
     */
    String MC_MAP_RENAME = "minecraft:map.rename";

    /**
     * Display Markers
     */
    String MC_MAP_TOOLTIP_DISPLAYMARKERS = "minecraft:map.toolTip.displayMarkers";

    /**
     * Level %s/%s
     */
    String MC_MAP_TOOLTIP_LEVEL = "minecraft:map.toolTip.level";

    /**
     * Locked
     */
    String MC_MAP_TOOLTIP_LOCKED = "minecraft:map.toolTip.locked";

    /**
     * Scaling at 1:%s
     */
    String MC_MAP_TOOLTIP_SCALING = "minecraft:map.toolTip.scaling";

    /**
     * Unknown Map
     */
    String MC_MAP_TOOLTIP_UNKOWN = "minecraft:map.toolTip.unkown";

    /**
     * Press %1#s to dismount
     */
    String MC_MOUNT_ONBOARD = "minecraft:mount.onboard";

    /**
     * Connect
     */
    String MC_MULTIPLAYER_CONNECT = "minecraft:multiplayer.connect";

    /**
     * Downloading statistics & achievements...
     */
    String MC_MULTIPLAYER_DOWNLOADINGSTATS = "minecraft:multiplayer.downloadingStats";

    /**
     * Downloading terrain
     */
    String MC_MULTIPLAYER_DOWNLOADINGTERRAIN = "minecraft:multiplayer.downloadingTerrain";

    /**
     * Open Chat
     */
    String MC_MULTIPLAYER_INBEDOPENCHAT = "minecraft:multiplayer.inBedOpenChat";

    /**
     * Minecraft Multiplayer is currently not finished, but there
     */
    String MC_MULTIPLAYER_INFO1 = "minecraft:multiplayer.info1";

    /**
     * is some buggy early testing going on.
     */
    String MC_MULTIPLAYER_INFO2 = "minecraft:multiplayer.info2";

    /**
     * Enter the IP of a server to connect to it:
     */
    String MC_MULTIPLAYER_IPINFO = "minecraft:multiplayer.ipinfo";

    /**
     * The game's join code has been updated.
     */
    String MC_MULTIPLAYER_JOINCODE_REFRESHED = "minecraft:multiplayer.joincode.refreshed";

    /**
     * At least one of your resource or behavior packs failed to load.
     */
    String MC_MULTIPLAYER_PACKERRORS = "minecraft:multiplayer.packErrors";

    /**
     * At least one of your resource or behavior packs failed to load. Try downloading this world from your Realm settings to see more details on the error.
     */
    String MC_MULTIPLAYER_PACKERRORS_REALMS = "minecraft:multiplayer.packErrors.realms";

    /**
     * %s edited the character appearance.
     */
    String MC_MULTIPLAYER_PLAYER_CHANGETOPERSONA = "minecraft:multiplayer.player.changeToPersona";

    /**
     * %s has changed their skin.
     */
    String MC_MULTIPLAYER_PLAYER_CHANGETOSKIN = "minecraft:multiplayer.player.changeToSkin";

    /**
     * Inventory recovered. Find a safe place, and we'll place a chest near you the next time you join the world.
     */
    String MC_MULTIPLAYER_PLAYER_INVENTORY_FAILED = "minecraft:multiplayer.player.inventory.failed";

    /**
     * Inventory recovered and placed in chests near you.
     */
    String MC_MULTIPLAYER_PLAYER_INVENTORY_RECOVERED = "minecraft:multiplayer.player.inventory.recovered";

    /**
     * %s joined the game
     */
    String MC_MULTIPLAYER_PLAYER_JOINED = "minecraft:multiplayer.player.joined";

    /**
     * %s joined the Realm
     */
    String MC_MULTIPLAYER_PLAYER_JOINED_REALMS = "minecraft:multiplayer.player.joined.realms";

    /**
     * %s (formerly known as %s) joined the Realm
     */
    String MC_MULTIPLAYER_PLAYER_JOINED_REALMS_RENAMED = "minecraft:multiplayer.player.joined.realms.renamed";

    /**
     * %s (formerly known as %s) joined the game
     */
    String MC_MULTIPLAYER_PLAYER_JOINED_RENAMED = "minecraft:multiplayer.player.joined.renamed";

    /**
     * %s left the game
     */
    String MC_MULTIPLAYER_PLAYER_LEFT = "minecraft:multiplayer.player.left";

    /**
     * %s left the Realm
     */
    String MC_MULTIPLAYER_PLAYER_LEFT_REALMS = "minecraft:multiplayer.player.left.realms";

    /**
     * Sleeping through this night
     */
    String MC_MULTIPLAYER_PLAYERSSKIPPINGNIGHT = "minecraft:multiplayer.playersSkippingNight";

    /**
     * %s/%s players sleeping
     */
    String MC_MULTIPLAYER_PLAYERSSLEEPING = "minecraft:multiplayer.playersSleeping";

    /**
     * No amount of rest can pass this night
     */
    String MC_MULTIPLAYER_PLAYERSSLEEPINGNOTPOSSIBLE = "minecraft:multiplayer.playersSleepingNotPossible";

    /**
     * Leave Bed
     */
    String MC_MULTIPLAYER_STOPSLEEPING = "minecraft:multiplayer.stopSleeping";

    /**
     * This server recommends the use of a custom resource pack.
     */
    String MC_MULTIPLAYER_TEXTUREPROMPT_LINE1 = "minecraft:multiplayer.texturePrompt.line1";

    /**
     * Would you like to download and install it automagically?
     */
    String MC_MULTIPLAYER_TEXTUREPROMPT_LINE2 = "minecraft:multiplayer.texturePrompt.line2";

    /**
     * Play Multiplayer
     */
    String MC_MULTIPLAYER_TITLE = "minecraft:multiplayer.title";

    /**
     * This item is locked and can't be crafted with
     */
    String MC_NOTIFICATION_LOCK_NOCRAFT = "minecraft:notification.lock.noCraft";

    /**
     * This item is locked and can't be dropped
     */
    String MC_NOTIFICATION_LOCK_NODROP = "minecraft:notification.lock.noDrop";

    /**
     * This item is locked and can't be removed from the inventory
     */
    String MC_NOTIFICATION_LOCK_NOREMOVE = "minecraft:notification.lock.noRemove";

    /**
     * This item is locked and can't be replaced
     */
    String MC_NOTIFICATION_LOCK_NOREPLACE = "minecraft:notification.lock.noReplace";

    /**
     * Unable to open the NPC Link. This doesn't look like a supported URL.
     */
    String MC_NPCURI_LAUNCH_FAILURE = "minecraft:npcUri.launch.failure";

    /**
     * Opened the NPC Link.
     */
    String MC_NPCURI_LAUNCH_SUCCESS = "minecraft:npcUri.launch.success";

    /**
     * Back
     */
    String MC_ONLINEPLAY_BACK = "minecraft:onlinePlay.Back";

    /**
     * Do not show this screen again
     */
    String MC_ONLINEPLAY_DONOTSHOWAGAIN = "minecraft:onlinePlay.doNotShowAgain";

    /**
     * During online editing you may be exposed to chat messages or other types of user generated content that has not been rated, and may not be suitable for all ages.
     */
    String MC_ONLINEPLAY_EDITOR_MESSAGE = "minecraft:onlinePlay.editor.message";

    /**
     * Online editing is not rated
     */
    String MC_ONLINEPLAY_EDITOR_NOTRATED = "minecraft:onlinePlay.editor.notRated";

    /**
     * During online play you might see chat messages or other user generated content that might not be suitable for all ages.
     */
    String MC_ONLINEPLAY_MESSAGE = "minecraft:onlinePlay.message";

    /**
     * Online play is not rated
     */
    String MC_ONLINEPLAY_NOTRATED = "minecraft:onlinePlay.notRated";

    /**
     * Proceed
     */
    String MC_ONLINEPLAY_PROCEED = "minecraft:onlinePlay.proceed";

    /**
     * You should only connect to servers you trust! Do you still wish to join?
     */
    String MC_ONLINEPLAY_UNTRUSTEDIP_MESSAGE = "minecraft:onlinePlay.untrustedIp.message";

    /**
     * Unknown External Server
     */
    String MC_ONLINEPLAY_UNTRUSTEDIP_TITLE = "minecraft:onlinePlay.untrustedIp.title";

    /**
     * Author: %s
     */
    String MC_PACK_AUTHORS_LABEL = "minecraft:pack.authors.label";

    /**
     * Unknown
     */
    String MC_PACK_AUTHORS_NONE = "minecraft:pack.authors.none";

    /**
     * Failed to accept invite
     */
    String MC_PARTIES_TOAST_ACCEPTINVITEFAILED = "minecraft:parties.toast.acceptInviteFailed";

    /**
     * Failed to create party
     */
    String MC_PARTIES_TOAST_CREATEFAILED = "minecraft:parties.toast.createFailed";

    /**
     * Failed to join party
     */
    String MC_PARTIES_TOAST_JOINFAILED = "minecraft:parties.toast.joinFailed";

    /**
     * You joined a party
     */
    String MC_PARTIES_TOAST_JOINEDPARTY = "minecraft:parties.toast.joinedParty";

    /**
     * %s is now the party leader
     */
    String MC_PARTIES_TOAST_LEADERCHANGED = "minecraft:parties.toast.leaderChanged";

    /**
     * Failed to leave party
     */
    String MC_PARTIES_TOAST_LEAVEFAILED = "minecraft:parties.toast.leaveFailed";

    /**
     * You left the party
     */
    String MC_PARTIES_TOAST_LEFTPARTY = "minecraft:parties.toast.leftParty";

    /**
     * %s joined the party
     */
    String MC_PARTIES_TOAST_MEMBERJOINED = "minecraft:parties.toast.memberJoined";

    /**
     * %s left the party
     */
    String MC_PARTIES_TOAST_MEMBERLEFT = "minecraft:parties.toast.memberLeft";

    /**
     * Party invite
     */
    String MC_PARTIES_TOAST_PARTYINVITE = "minecraft:parties.toast.partyInvite";

    /**
     * Failed to remove party member
     */
    String MC_PARTIES_TOAST_REMOVEMEMBERFAILED = "minecraft:parties.toast.removeMemberFailed";

    /**
     * Failed to send invite
     */
    String MC_PARTIES_TOAST_SENDINVITEFAILED = "minecraft:parties.toast.sendInviteFailed";

    /**
     * Failed to set party leader
     */
    String MC_PARTIES_TOAST_SETLEADERFAILED = "minecraft:parties.toast.setLeaderFailed";

    /**
     * Party loading - Main Menu
     */
    String MC_PARTIES_TOAST_TRAVELTOMENU = "minecraft:parties.toast.travelToMenu";

    /**
     * Party loading - Player world
     */
    String MC_PARTIES_TOAST_TRAVELTOWORLD = "minecraft:parties.toast.travelToWorld";

    /**
     * Continue
     */
    String MC_PATCHNOTES_CONTINUE = "minecraft:patchNotes.continue";

    /**
     * Oops! Something went wrong. Maybe check your internet connection?
     */
    String MC_PATCHNOTES_ERROR_NOINTERNET_MSG = "minecraft:patchNotes.error.noInternet.msg";

    /**
     * Disconnected from the Internet
     */
    String MC_PATCHNOTES_ERROR_NOINTERNET_TITLE = "minecraft:patchNotes.error.noInternet.title";

    /**
     * We fixed a few bugs in this release. We'll send you new patch notes when we have more to report.
     */
    String MC_PATCHNOTES_ERROR_NOTFOUND_MSG = "minecraft:patchNotes.error.notFound.msg";

    /**
     * Patch Notes %1
     */
    String MC_PATCHNOTES_ERROR_NOTFOUND_TITLE = "minecraft:patchNotes.error.notFound.title";

    /**
     * Loading Patch Notes
     */
    String MC_PATCHNOTES_LOADING = "minecraft:patchNotes.loading";

    /**
     * Unlock
     */
    String MC_PATCHNOTES_UNLOCK = "minecraft:patchNotes.unlock";

    /**
     * Play
     */
    String MC_PAUSENOTIFICATION_PLAYBUTTON = "minecraft:pauseNotification.playButton";

    /**
     * Watch out, game is no longer paused!
     */
    String MC_PAUSENOTIFICATION_SUBTITLE_GAMEUNPAUSED = "minecraft:pauseNotification.subtitle.gameUnpaused";

    /**
     * Multiple people joined your game!
     */
    String MC_PAUSENOTIFICATION_TITLE_MORETHANTWOPLAYERS = "minecraft:pauseNotification.title.moreThanTwoPlayers";

    /**
     * %s joined your game!
     */
    String MC_PAUSENOTIFICATION_TITLE_ONEPLAYER = "minecraft:pauseNotification.title.onePlayer";

    /**
     * %s and %s joined your game!
     */
    String MC_PAUSENOTIFICATION_TITLE_TWOPLAYERS = "minecraft:pauseNotification.title.twoPlayers";

    /**
     * Back to Game
     */
    String MC_PAUSESCREEN_BACK = "minecraft:pauseScreen.back";

    /**
     * Beta Feedback
     */
    String MC_PAUSESCREEN_BETAFEEDBACK = "minecraft:pauseScreen.betaFeedback";

    /**
     * Current World
     */
    String MC_PAUSESCREEN_CURRENTWORLD = "minecraft:pauseScreen.currentWorld";

    /**
     * Return to Editor
     */
    String MC_PAUSESCREEN_EDITOR_QUIT = "minecraft:pauseScreen.editor.quit";

    /**
     * Resume Editing
     */
    String MC_PAUSESCREEN_EDITOR_RETURNTOEDITING = "minecraft:pauseScreen.editor.returnToEditing";

    /**
     * Resume Testing
     */
    String MC_PAUSESCREEN_EDITOR_RETURNTOTESTING = "minecraft:pauseScreen.editor.returnToTesting";

    /**
     * No IP found
     */
    String MC_PAUSESCREEN_ERROR_NOIPADDRESS = "minecraft:pauseScreen.error.noIpAddress";

    /**
     * No Port found
     */
    String MC_PAUSESCREEN_ERROR_NOPORT = "minecraft:pauseScreen.error.noPort";

    /**
     * Feed
     */
    String MC_PAUSESCREEN_FEED = "minecraft:pauseScreen.feed";

    /**
     * Game is paused
     */
    String MC_PAUSESCREEN_GAMEISPAUSED = "minecraft:pauseScreen.gameIsPaused";

    /**
     * Game Menu
     */
    String MC_PAUSESCREEN_HEADER = "minecraft:pauseScreen.header";

    /**
     * Invite to Game
     */
    String MC_PAUSESCREEN_INVITE = "minecraft:pauseScreen.invite";

    /**
     * IP: %1
     */
    String MC_PAUSESCREEN_IPADDRESS = "minecraft:pauseScreen.ipAddress";

    /**
     * %3 join code icon, %1 of %2
     */
    String MC_PAUSESCREEN_JOINCODE_ICON = "minecraft:pauseScreen.joinCode.Icon";

    /**
     * JOIN CODE
     */
    String MC_PAUSESCREEN_JOINCODE_LABEL = "minecraft:pauseScreen.joinCode.Label";

    /**
     * Options
     */
    String MC_PAUSESCREEN_OPTIONS = "minecraft:pauseScreen.options";

    /**
     * Players in %s
     */
    String MC_PAUSESCREEN_PLAYERSTITLE = "minecraft:pauseScreen.playersTitle";

    /**
     * Save & Quit
     */
    String MC_PAUSESCREEN_QUIT = "minecraft:pauseScreen.quit";

    /**
     * Realms Stories
     */
    String MC_PAUSESCREEN_REALMSSTORIES = "minecraft:pauseScreen.realmsStories";

    /**
     * Save & Leave
     */
    String MC_PAUSESCREEN_SECONDARYCLIENTLEAVE = "minecraft:pauseScreen.secondaryClientLeave";

    /**
     * Game Paused
     */
    String MC_PAUSESCREEN_TITLE = "minecraft:pauseScreen.title";

    /**
     * Oops! Your account was disconnected. To access online features, please sign in at the Main Menu and restart your world.
     */
    String MC_PAUSESCREEN_XBOXLIVEDISCONNECT = "minecraft:pauseScreen.xboxLiveDisconnect";

    /**
     * You save %s minecoins with your Marketplace Pass subscription!
     */
    String MC_PERSONA_CSB_SAVINGS = "minecraft:persona.csb.savings";

    /**
     * In Pass
     */
    String MC_PERSONA_CSB_SEE_SUBSCRIPTION = "minecraft:persona.csb.see.subscription";

    /**
     * medium
     */
    String MC_PERSONA_MEDIUM_TITLE = "minecraft:persona.medium.title";

    /**
     * Play Emote
     */
    String MC_PERSONA_PREVIEW_EMOTE = "minecraft:persona.preview.emote";

    /**
     * Redeem
     */
    String MC_PERSONA_REALMS_REDEEM = "minecraft:persona.realms.redeem";

    /**
     * You save %s minecoins with your Realms Plus subscription!
     */
    String MC_PERSONA_REALMS_SAVINGS = "minecraft:persona.realms.savings";

    /**
     * In Realms Plus
     */
    String MC_PERSONA_REALMS_SEE_SUBSCRIPTION = "minecraft:persona.realms.see.subscription";

    /**
     * %s to redeem
     */
    String MC_PERSONA_REALMS_TIME_REMAINING = "minecraft:persona.realms.time.remaining";

    /**
     * narrow
     */
    String MC_PERSONA_SLIM_TITLE = "minecraft:persona.slim.title";

    /**
     * small
     */
    String MC_PERSONA_SMALL_TITLE = "minecraft:persona.small.title";

    /**
     * smaller
     */
    String MC_PERSONA_SMALLER_TITLE = "minecraft:persona.smaller.title";

    /**
     * tall
     */
    String MC_PERSONA_TALL_TITLE = "minecraft:persona.tall.title";

    /**
     * wide
     */
    String MC_PERSONA_WIDE_TITLE = "minecraft:persona.wide.title";

    /**
     * UNKNOWN
     */
    String MC_PLATFORM_MODEL_UNKNOWN = "minecraft:platform.model.unknown";

    /**
     * Multiplayer
     */
    String MC_PLAYERCOUNT_MULTIPLAYER = "minecraft:playercount.multiplayer";

    /**
     * Single Player
     */
    String MC_PLAYERCOUNT_SINGLE_PLAYER = "minecraft:playercount.single_player";

    /**
     * Add Photo
     */
    String MC_PORTFOLIOSCREEN_ADDPHOTO = "minecraft:portfolioScreen.addPhoto";

    /**
     * [ caption ]
     */
    String MC_PORTFOLIOSCREEN_CAPTION = "minecraft:portfolioScreen.caption";

    /**
     * Export Portfolio
     */
    String MC_PORTFOLIOSCREEN_EXPORT = "minecraft:portfolioScreen.export";

    /**
     * You do not have any photos in your inventory. Try using the camera to take a picture.
     */
    String MC_PORTFOLIOSCREEN_NOINVENTORY = "minecraft:portfolioScreen.noInventory";

    /**
     * You currently have no photos in your portfolio. Photos taken with the camera will show up here. You can also use the button below to add some.
     */
    String MC_PORTFOLIOSCREEN_NOPICS0 = "minecraft:portfolioScreen.nopics0";

    /**
     * Photos you take with the camera will show up here.
     */
    String MC_PORTFOLIOSCREEN_NOPICS1 = "minecraft:portfolioScreen.nopics1";

    /**
     * Page %s
     */
    String MC_PORTFOLIOSCREEN_PAGE = "minecraft:portfolioScreen.page";

    /**
     * Absorption
     */
    String MC_POTION_ABSORPTION = "minecraft:potion.absorption";

    /**
     * Lingering Awkward Potion
     */
    String MC_POTION_AWKWARD_LINGER_NAME = "minecraft:potion.awkward.linger.name";

    /**
     * Awkward Potion
     */
    String MC_POTION_AWKWARD_NAME = "minecraft:potion.awkward.name";

    /**
     * Splash Awkward Potion
     */
    String MC_POTION_AWKWARD_SPLASH_NAME = "minecraft:potion.awkward.splash.name";

    /**
     * Blindness
     */
    String MC_POTION_BLINDNESS = "minecraft:potion.blindness";

    /**
     * Conduit Power
     */
    String MC_POTION_CONDUITPOWER = "minecraft:potion.conduitPower";

    /**
     * Nausea
     */
    String MC_POTION_CONFUSION = "minecraft:potion.confusion";

    /**
     * Strength
     */
    String MC_POTION_DAMAGEBOOST = "minecraft:potion.damageBoost";

    /**
     * Lingering Potion of Strength
     */
    String MC_POTION_DAMAGEBOOST_LINGER_NAME = "minecraft:potion.damageBoost.linger.name";

    /**
     * Potion of Strength
     */
    String MC_POTION_DAMAGEBOOST_NAME = "minecraft:potion.damageBoost.name";

    /**
     * Splash Potion of Strength
     */
    String MC_POTION_DAMAGEBOOST_SPLASH_NAME = "minecraft:potion.damageBoost.splash.name";

    /**
     * Mining Fatigue
     */
    String MC_POTION_DIGSLOWDOWN = "minecraft:potion.digSlowDown";

    /**
     * Haste
     */
    String MC_POTION_DIGSPEED = "minecraft:potion.digSpeed";

    /**
     * When Applied:
     */
    String MC_POTION_EFFECTS_WHENDRANK = "minecraft:potion.effects.whenDrank";

    /**
     * No Effects
     */
    String MC_POTION_EMPTY = "minecraft:potion.empty";

    /**
     * Lingering Water Bottle
     */
    String MC_POTION_EMPTYPOTION_LINGER_NAME = "minecraft:potion.emptyPotion.linger.name";

    /**
     * Water Bottle
     */
    String MC_POTION_EMPTYPOTION_NAME = "minecraft:potion.emptyPotion.name";

    /**
     * Splash Water Bottle
     */
    String MC_POTION_EMPTYPOTION_SPLASH_NAME = "minecraft:potion.emptyPotion.splash.name";

    /**
     * Fire Resistance
     */
    String MC_POTION_FIRERESISTANCE = "minecraft:potion.fireResistance";

    /**
     * Lingering Potion of Fire Resistance
     */
    String MC_POTION_FIRERESISTANCE_LINGER_NAME = "minecraft:potion.fireResistance.linger.name";

    /**
     * Potion of Fire Resistance
     */
    String MC_POTION_FIRERESISTANCE_NAME = "minecraft:potion.fireResistance.name";

    /**
     * Splash Potion of Fire Resistance
     */
    String MC_POTION_FIRERESISTANCE_SPLASH_NAME = "minecraft:potion.fireResistance.splash.name";

    /**
     * Instant Damage
     */
    String MC_POTION_HARM = "minecraft:potion.harm";

    /**
     * Lingering Potion of Harming
     */
    String MC_POTION_HARM_LINGER_NAME = "minecraft:potion.harm.linger.name";

    /**
     * Potion of Harming
     */
    String MC_POTION_HARM_NAME = "minecraft:potion.harm.name";

    /**
     * Splash Potion of Harming
     */
    String MC_POTION_HARM_SPLASH_NAME = "minecraft:potion.harm.splash.name";

    /**
     * Instant Health
     */
    String MC_POTION_HEAL = "minecraft:potion.heal";

    /**
     * Lingering Potion of Healing
     */
    String MC_POTION_HEAL_LINGER_NAME = "minecraft:potion.heal.linger.name";

    /**
     * Potion of Healing
     */
    String MC_POTION_HEAL_NAME = "minecraft:potion.heal.name";

    /**
     * Splash Potion of Healing
     */
    String MC_POTION_HEAL_SPLASH_NAME = "minecraft:potion.heal.splash.name";

    /**
     * Health Boost
     */
    String MC_POTION_HEALTHBOOST = "minecraft:potion.healthBoost";

    /**
     * Hunger
     */
    String MC_POTION_HUNGER = "minecraft:potion.hunger";

    /**
     * Infested
     */
    String MC_POTION_INFESTED = "minecraft:potion.infested";

    /**
     * Lingering Potion of Infestation
     */
    String MC_POTION_INFESTED_LINGER_NAME = "minecraft:potion.infested.linger.name";

    /**
     * Potion of Infestation
     */
    String MC_POTION_INFESTED_NAME = "minecraft:potion.infested.name";

    /**
     * Splash Potion of Infestation
     */
    String MC_POTION_INFESTED_SPLASH_NAME = "minecraft:potion.infested.splash.name";

    /**
     * Invisibility
     */
    String MC_POTION_INVISIBILITY = "minecraft:potion.invisibility";

    /**
     * Lingering Potion of Invisibility
     */
    String MC_POTION_INVISIBILITY_LINGER_NAME = "minecraft:potion.invisibility.linger.name";

    /**
     * Potion of Invisibility
     */
    String MC_POTION_INVISIBILITY_NAME = "minecraft:potion.invisibility.name";

    /**
     * Splash Potion of Invisibility
     */
    String MC_POTION_INVISIBILITY_SPLASH_NAME = "minecraft:potion.invisibility.splash.name";

    /**
     * Jump Boost
     */
    String MC_POTION_JUMP = "minecraft:potion.jump";

    /**
     * Lingering Potion of Leaping
     */
    String MC_POTION_JUMP_LINGER_NAME = "minecraft:potion.jump.linger.name";

    /**
     * Potion of Leaping
     */
    String MC_POTION_JUMP_NAME = "minecraft:potion.jump.name";

    /**
     * Splash Potion of Leaping
     */
    String MC_POTION_JUMP_SPLASH_NAME = "minecraft:potion.jump.splash.name";

    /**
     * Levitation
     */
    String MC_POTION_LEVITATION = "minecraft:potion.levitation";

    /**
     * Slowness
     */
    String MC_POTION_MOVESLOWDOWN = "minecraft:potion.moveSlowdown";

    /**
     * Lingering Potion of Slowness
     */
    String MC_POTION_MOVESLOWDOWN_LINGER_NAME = "minecraft:potion.moveSlowdown.linger.name";

    /**
     * Potion of Slowness
     */
    String MC_POTION_MOVESLOWDOWN_NAME = "minecraft:potion.moveSlowdown.name";

    /**
     * Splash Potion of Slowness
     */
    String MC_POTION_MOVESLOWDOWN_SPLASH_NAME = "minecraft:potion.moveSlowdown.splash.name";

    /**
     * Speed
     */
    String MC_POTION_MOVESPEED = "minecraft:potion.moveSpeed";

    /**
     * Lingering Potion of Swiftness
     */
    String MC_POTION_MOVESPEED_LINGER_NAME = "minecraft:potion.moveSpeed.linger.name";

    /**
     * Potion of Swiftness
     */
    String MC_POTION_MOVESPEED_NAME = "minecraft:potion.moveSpeed.name";

    /**
     * Splash Potion of Swiftness
     */
    String MC_POTION_MOVESPEED_SPLASH_NAME = "minecraft:potion.moveSpeed.splash.name";

    /**
     * Lingering Long Mundane Potion
     */
    String MC_POTION_MUNDANE_EXTENDED_LINGER_NAME = "minecraft:potion.mundane.extended.linger.name";

    /**
     * Long Mundane Potion
     */
    String MC_POTION_MUNDANE_EXTENDED_NAME = "minecraft:potion.mundane.extended.name";

    /**
     * Splash Long Mundane Potion
     */
    String MC_POTION_MUNDANE_EXTENDED_SPLASH_NAME = "minecraft:potion.mundane.extended.splash.name";

    /**
     * Lingering Mundane Potion
     */
    String MC_POTION_MUNDANE_LINGER_NAME = "minecraft:potion.mundane.linger.name";

    /**
     * Mundane Potion
     */
    String MC_POTION_MUNDANE_NAME = "minecraft:potion.mundane.name";

    /**
     * Splash Mundane Potion
     */
    String MC_POTION_MUNDANE_SPLASH_NAME = "minecraft:potion.mundane.splash.name";

    /**
     * Night Vision
     */
    String MC_POTION_NIGHTVISION = "minecraft:potion.nightVision";

    /**
     * Lingering Potion of Night Vision
     */
    String MC_POTION_NIGHTVISION_LINGER_NAME = "minecraft:potion.nightVision.linger.name";

    /**
     * Potion of Night Vision
     */
    String MC_POTION_NIGHTVISION_NAME = "minecraft:potion.nightVision.name";

    /**
     * Splash Potion of Night Vision
     */
    String MC_POTION_NIGHTVISION_SPLASH_NAME = "minecraft:potion.nightVision.splash.name";

    /**
     * Oozing
     */
    String MC_POTION_OOZING = "minecraft:potion.oozing";

    /**
     * Lingering Potion of Oozing
     */
    String MC_POTION_OOZING_LINGER_NAME = "minecraft:potion.oozing.linger.name";

    /**
     * Potion of Oozing
     */
    String MC_POTION_OOZING_NAME = "minecraft:potion.oozing.name";

    /**
     * Splash Potion of Oozing
     */
    String MC_POTION_OOZING_SPLASH_NAME = "minecraft:potion.oozing.splash.name";

    /**
     * Poison
     */
    String MC_POTION_POISON = "minecraft:potion.poison";

    /**
     * Lingering Potion of Poison
     */
    String MC_POTION_POISON_LINGER_NAME = "minecraft:potion.poison.linger.name";

    /**
     * Potion of Poison
     */
    String MC_POTION_POISON_NAME = "minecraft:potion.poison.name";

    /**
     * Splash Potion of Poison
     */
    String MC_POTION_POISON_SPLASH_NAME = "minecraft:potion.poison.splash.name";

    String MC_POTION_POTENCY_0 = "minecraft:potion.potency.0";

    /**
     * II
     */
    String MC_POTION_POTENCY_1 = "minecraft:potion.potency.1";

    /**
     * III
     */
    String MC_POTION_POTENCY_2 = "minecraft:potion.potency.2";

    /**
     * IV
     */
    String MC_POTION_POTENCY_3 = "minecraft:potion.potency.3";

    /**
     * V
     */
    String MC_POTION_POTENCY_4 = "minecraft:potion.potency.4";

    /**
     * VI
     */
    String MC_POTION_POTENCY_5 = "minecraft:potion.potency.5";

    /**
     * Regeneration
     */
    String MC_POTION_REGENERATION = "minecraft:potion.regeneration";

    /**
     * Lingering Potion of Regeneration
     */
    String MC_POTION_REGENERATION_LINGER_NAME = "minecraft:potion.regeneration.linger.name";

    /**
     * Potion of Regeneration
     */
    String MC_POTION_REGENERATION_NAME = "minecraft:potion.regeneration.name";

    /**
     * Splash Potion of Regeneration
     */
    String MC_POTION_REGENERATION_SPLASH_NAME = "minecraft:potion.regeneration.splash.name";

    /**
     * Resistance
     */
    String MC_POTION_RESISTANCE = "minecraft:potion.resistance";

    /**
     * Saturation
     */
    String MC_POTION_SATURATION = "minecraft:potion.saturation";

    /**
     * Slow Falling
     */
    String MC_POTION_SLOWFALLING = "minecraft:potion.slowFalling";

    /**
     * Lingering Potion of Slow Falling
     */
    String MC_POTION_SLOWFALLING_LINGER_NAME = "minecraft:potion.slowFalling.linger.name";

    /**
     * Potion of Slow Falling
     */
    String MC_POTION_SLOWFALLING_NAME = "minecraft:potion.slowFalling.name";

    /**
     * Splash Potion of Slow Falling
     */
    String MC_POTION_SLOWFALLING_SPLASH_NAME = "minecraft:potion.slowFalling.splash.name";

    /**
     * Lingering Thick Potion
     */
    String MC_POTION_THICK_LINGER_NAME = "minecraft:potion.thick.linger.name";

    /**
     * Thick Potion
     */
    String MC_POTION_THICK_NAME = "minecraft:potion.thick.name";

    /**
     * Splash Thick Potion
     */
    String MC_POTION_THICK_SPLASH_NAME = "minecraft:potion.thick.splash.name";

    /**
     * Slowness
     */
    String MC_POTION_TURTLEMASTER = "minecraft:potion.turtleMaster";

    /**
     * Lingering Potion of the Turtle Master
     */
    String MC_POTION_TURTLEMASTER_LINGER_NAME = "minecraft:potion.turtleMaster.linger.name";

    /**
     * Potion of the Turtle Master
     */
    String MC_POTION_TURTLEMASTER_NAME = "minecraft:potion.turtleMaster.name";

    /**
     * Splash Potion of the Turtle Master
     */
    String MC_POTION_TURTLEMASTER_SPLASH_NAME = "minecraft:potion.turtleMaster.splash.name";

    /**
     * Resistance
     */
    String MC_POTION_TURTLEMASTER2 = "minecraft:potion.turtleMaster2";

    /**
     * Water Breathing
     */
    String MC_POTION_WATERBREATHING = "minecraft:potion.waterBreathing";

    /**
     * Lingering Potion of Water Breathing
     */
    String MC_POTION_WATERBREATHING_LINGER_NAME = "minecraft:potion.waterBreathing.linger.name";

    /**
     * Potion of Water Breathing
     */
    String MC_POTION_WATERBREATHING_NAME = "minecraft:potion.waterBreathing.name";

    /**
     * Splash Potion of Water Breathing
     */
    String MC_POTION_WATERBREATHING_SPLASH_NAME = "minecraft:potion.waterBreathing.splash.name";

    /**
     * Weakness
     */
    String MC_POTION_WEAKNESS = "minecraft:potion.weakness";

    /**
     * Lingering Potion of Weakness
     */
    String MC_POTION_WEAKNESS_LINGER_NAME = "minecraft:potion.weakness.linger.name";

    /**
     * Potion of Weakness
     */
    String MC_POTION_WEAKNESS_NAME = "minecraft:potion.weakness.name";

    /**
     * Splash Potion of Weakness
     */
    String MC_POTION_WEAKNESS_SPLASH_NAME = "minecraft:potion.weakness.splash.name";

    /**
     * Weaving
     */
    String MC_POTION_WEAVING = "minecraft:potion.weaving";

    /**
     * Lingering Potion of Weaving
     */
    String MC_POTION_WEAVING_LINGER_NAME = "minecraft:potion.weaving.linger.name";

    /**
     * Potion of Weaving
     */
    String MC_POTION_WEAVING_NAME = "minecraft:potion.weaving.name";

    /**
     * Splash Potion of Weaving
     */
    String MC_POTION_WEAVING_SPLASH_NAME = "minecraft:potion.weaving.splash.name";

    /**
     * Wind Charged
     */
    String MC_POTION_WINDCHARGED = "minecraft:potion.windCharged";

    /**
     * Lingering Potion of Wind Charging
     */
    String MC_POTION_WINDCHARGED_LINGER_NAME = "minecraft:potion.windCharged.linger.name";

    /**
     * Potion of Wind Charging
     */
    String MC_POTION_WINDCHARGED_NAME = "minecraft:potion.windCharged.name";

    /**
     * Splash Potion of Wind Charging
     */
    String MC_POTION_WINDCHARGED_SPLASH_NAME = "minecraft:potion.windCharged.splash.name";

    /**
     * Wither
     */
    String MC_POTION_WITHER = "minecraft:potion.wither";

    /**
     * Lingering Potion of Decay
     */
    String MC_POTION_WITHER_LINGER_NAME = "minecraft:potion.wither.linger.name";

    /**
     * Potion of Decay
     */
    String MC_POTION_WITHER_NAME = "minecraft:potion.wither.name";

    /**
     * Splash Potion of Decay
     */
    String MC_POTION_WITHER_SPLASH_NAME = "minecraft:potion.wither.splash.name";

    /**
     * The text you entered contains words that others may find offensive and cannot be used. Please try again.
     */
    String MC_PROFANITY_FILTER_MSG = "minecraft:profanity_filter.msg";

    /**
     * Profanity Filter
     */
    String MC_PROFANITY_FILTER_TITLE = "minecraft:profanity_filter.title";

    /**
     * Take the Quiz
     */
    String MC_QUIZ_POPUP_OK = "minecraft:quiz.popup.ok";

    /**
     * The quiz will open in a new window. You may return to Minecraft after you have completed the quiz.
     */
    String MC_QUIZ_POPUP_TEXT = "minecraft:quiz.popup.text";

    /**
     * Quiz
     */
    String MC_QUIZ_POPUP_TITLE = "minecraft:quiz.popup.title";

    /**
     * Defeat
     */
    String MC_RAID_DEFEAT = "minecraft:raid.defeat";

    /**
     * A raid has expired
     */
    String MC_RAID_EXPIRY = "minecraft:raid.expiry";

    /**
     * Raid
     */
    String MC_RAID_NAME = "minecraft:raid.name";

    /**
     * Mobs remaining:
     */
    String MC_RAID_PROGRESS = "minecraft:raid.progress";

    /**
     * Victory
     */
    String MC_RAID_VICTORY = "minecraft:raid.victory";

    /**
     * We have detected that your device does not meet the system requirements to run this pack so you are unable to download this pack. You can review the minimum system requirements below: %s+ GPU: Hardware ray tracing capable GPU such as NVIDIA GeForce RTX 2060 or better. See our FAQ on minecraft.net for more details. %s+ RAM: 8GB or better %s+ CPU: Intel Core i5 or equivalent 
     */
    String MC_RAYCASTING_PURCHASE_ERROR = "minecraft:raycasting.purchase.error";

    /**
     * System Requirements Warning
     */
    String MC_RAYCASTING_PURCHASE_ERROR_TITLE = "minecraft:raycasting.purchase.error.title";

    /**
     * This pack is not available to download as your device doesn't meet the minimum system requirements. These requirements can be found in the pack description.
     */
    String MC_RAYCASTING_PURCHASE_ERROR_TOOLTIP = "minecraft:raycasting.purchase.error.tooltip";

    /**
     * Joining Realm...
     */
    String MC_REALMJOINING_PROGRESSTITLE = "minecraft:realmJoining.progressTitle";

    /**
     * Now playing: %s
     */
    String MC_RECORD_NOWPLAYING = "minecraft:record.nowPlaying";

    /**
     * Candy Texture Pack
     */
    String MC_RESOURCEPACK_CANDY = "minecraft:resourcepack.Candy";

    /**
     * Cartoon Texture Pack
     */
    String MC_RESOURCEPACK_CARTOON = "minecraft:resourcepack.Cartoon";

    /**
     * Chinese Mythology Mash-up
     */
    String MC_RESOURCEPACK_CHINESEMYTHOLOGY = "minecraft:resourcepack.ChineseMythology";

    /**
     * City Texture Pack
     */
    String MC_RESOURCEPACK_CITY = "minecraft:resourcepack.City";

    /**
     * Fallout Mash-up
     */
    String MC_RESOURCEPACK_FALLOUT = "minecraft:resourcepack.Fallout";

    /**
     * Fantasy Texture Pack
     */
    String MC_RESOURCEPACK_FANTASY = "minecraft:resourcepack.Fantasy";

    /**
     * Festive Mash-up 2016
     */
    String MC_RESOURCEPACK_FESTIVEMASHUP2016 = "minecraft:resourcepack.FestiveMashup2016";

    /**
     * Magic: The Gathering
     */
    String MC_RESOURCEPACK_MAGICTHEGATHERING_NAME = "minecraft:resourcepack.MagicTheGathering.name";

    /**
     * Natural Texture Pack
     */
    String MC_RESOURCEPACK_NATURAL = "minecraft:resourcepack.Natural";

    /**
     * Plastic Texture Pack
     */
    String MC_RESOURCEPACK_PLASTIC = "minecraft:resourcepack.Plastic";

    /**
     * We're sorry to hear you didn't enjoy your pack at all. Use the button below to let the creator know how they can improve.
     */
    String MC_REVIEW_ITEM_POST_RATING_1STAR = "minecraft:review.item.post.rating.1star";

    /**
     * That's no good. Use the button below to let the creator know how they can improve.
     */
    String MC_REVIEW_ITEM_POST_RATING_2STAR = "minecraft:review.item.post.rating.2star";

    /**
     * There's always room for improvement, but we're glad you're still having fun.
     */
    String MC_REVIEW_ITEM_POST_RATING_3STAR = "minecraft:review.item.post.rating.3star";

    /**
     * We're glad you're having a great time!
     */
    String MC_REVIEW_ITEM_POST_RATING_4STAR = "minecraft:review.item.post.rating.4star";

    /**
     * Woohoo! We're glad you're having a good time!
     */
    String MC_REVIEW_ITEM_POST_RATING_5STAR = "minecraft:review.item.post.rating.5star";

    /**
     * Choose a star rating
     */
    String MC_REVIEW_ITEM_POST_RATING_DROPDOWN_LABEL = "minecraft:review.item.post.rating.dropdown.label";

    /**
     * You will be able to change your rating.
     */
    String MC_REVIEW_ITEM_POST_RATING_FOOTER = "minecraft:review.item.post.rating.footer";

    /**
     * Rate this pack
     */
    String MC_REVIEW_ITEM_POST_RATING_SUBMIT_BUTTON = "minecraft:review.item.post.rating.submit.button";

    /**
     * Thanks for rating this pack!
     */
    String MC_REVIEW_ITEM_POST_RATING_SUBMIT_TOAST_LINE1 = "minecraft:review.item.post.rating.submit.toast.line1";

    /**
     * It may take some time for us to show your rating.
     */
    String MC_REVIEW_ITEM_POST_RATING_SUBMIT_TOAST_LINE2 = "minecraft:review.item.post.rating.submit.toast.line2";

    /**
     * Limited usage
     */
    String MC_ROAMING_STATUS_BRIEF_LIMITED_USAGE = "minecraft:roaming.status_brief.limited_usage";

    /**
     * Usable cross-platform
     */
    String MC_ROAMING_STATUS_BRIEF_NO_RESTRICTIONS = "minecraft:roaming.status_brief.no_restrictions";

    /**
     * Only available on specific platforms.
     */
    String MC_ROAMING_STATUS_HOVER_LIMITED_USAGE = "minecraft:roaming.status_hover.limited_usage";

    /**
     * Available on any Bedrock platform!
     */
    String MC_ROAMING_STATUS_HOVER_NO_RESTRICTIONS = "minecraft:roaming.status_hover.no_restrictions";

    /**
     * Your current skin violates our Community Standards. Please select a different skin to join a multiplayer game. Click "%s" to view our Community Standards or submit a case review for your current skin.
     */
    String MC_SAFETY_BANNEDSKIN = "minecraft:safety.bannedSkin";

    /**
     * Learn More
     */
    String MC_SAFETY_BANNEDSKIN_BUTTON = "minecraft:safety.bannedSkin.button";

    /**
     * Continue with default skin
     */
    String MC_SAFETY_BANNEDSKIN_CONTINUE = "minecraft:safety.bannedSkin.continue";

    /**
     * Skin Not Allowed In Multiplayer
     */
    String MC_SAFETY_BANNEDSKIN_TITLE = "minecraft:safety.bannedSkin.title";

    /**
     * Remember to follow the Minecraft Community Standards.
     */
    String MC_SAFETY_MUTED_MESSAGE = "minecraft:safety.muted.message";

    /**
     * You've been temporarily muted in chat.
     */
    String MC_SAFETY_MUTED_TITLE = "minecraft:safety.muted.title";

    /**
     * §eChat and other user generated text is not available right now. Please try again later.§f
     */
    String MC_SAFETY_TEXTPROCESSOROFFLINE_MESSAGE = "minecraft:safety.textProcessorOffline.message";

    /**
     * Add a caption and share?
     */
    String MC_SCREENSHOT_CAPTION = "minecraft:screenshot.caption";

    /**
     * Couldn't save screenshot: %s
     */
    String MC_SCREENSHOT_FAILURE = "minecraft:screenshot.failure";

    /**
     * Share
     */
    String MC_SCREENSHOT_POST = "minecraft:screenshot.post";

    /**
     * Saved screenshot as %s
     */
    String MC_SCREENSHOT_SUCCESS = "minecraft:screenshot.success";

    /**
     * Screenshot Captured!
     */
    String MC_SCREENSHOT_TITLE = "minecraft:screenshot.title";

    /**
     * Search
     */
    String MC_SEEDPICKER_SEARCH = "minecraft:seedPicker.search";

    /**
     * Seed Picker
     */
    String MC_SEEDPICKER_TITLE = "minecraft:seedPicker.title";

    /**
     * Received invalid form json. Error:%s
     */
    String MC_SERVERUI_ERRORDESCRIPTION = "minecraft:serverUI.errorDescription";

    /**
     * Error creating form.
     */
    String MC_SERVERUI_ERRORTITLE = "minecraft:serverUI.errorTitle";

    /**
     * Edit sign message
     */
    String MC_SIGN_EDIT = "minecraft:sign.edit";

    /**
     * When on Chest:
     */
    String MC_SLOT_ARMOR_CHEST_WHEN_EQUIPPED = "minecraft:slot.armor.chest.when_equipped";

    /**
     * When on Feet:
     */
    String MC_SLOT_ARMOR_FEET_WHEN_EQUIPPED = "minecraft:slot.armor.feet.when_equipped";

    /**
     * When on Head:
     */
    String MC_SLOT_ARMOR_HEAD_WHEN_EQUIPPED = "minecraft:slot.armor.head.when_equipped";

    /**
     * When on Legs:
     */
    String MC_SLOT_ARMOR_LEGS_WHEN_EQUIPPED = "minecraft:slot.armor.legs.when_equipped";

    /**
     * Go Back
     */
    String MC_TERMS_AND_CONDITIONS_GOBACK = "minecraft:terms_and_conditions.goBack";

    /**
     * To view terms and conditions, please visit https://minecraft.net/terms in any web browser.
     */
    String MC_TERMS_AND_CONDITIONS_VIEWTERMSANDCONDITIONS = "minecraft:terms_and_conditions.viewTermsAndConditions";

    /**
     * TAP
     */
    String MC_TEXTTOICON_TOUCH_ASSIGMENT_ATTACK = "minecraft:textToIcon.touch.assigment.attack";

    /**
     * LONG TAP
     */
    String MC_TEXTTOICON_TOUCH_ASSIGMENT_USE = "minecraft:textToIcon.touch.assigment.use";

    /**
     * Acacia Fence
     */
    String MC_TILE_ACACIAFENCE_NAME = "minecraft:tile.acaciaFence.name";

    /**
     * Acacia Button
     */
    String MC_TILE_ACACIA_BUTTON_NAME = "minecraft:tile.acacia_button.name";

    /**
     * Acacia Fence Gate
     */
    String MC_TILE_ACACIA_FENCE_GATE_NAME = "minecraft:tile.acacia_fence_gate.name";

    /**
     * Acacia Pressure Plate
     */
    String MC_TILE_ACACIA_PRESSURE_PLATE_NAME = "minecraft:tile.acacia_pressure_plate.name";

    /**
     * Acacia Shelf
     */
    String MC_TILE_ACACIA_SHELF_NAME = "minecraft:tile.acacia_shelf.name";

    /**
     * Acacia Stairs
     */
    String MC_TILE_ACACIA_STAIRS_NAME = "minecraft:tile.acacia_stairs.name";

    /**
     * Acacia Sign
     */
    String MC_TILE_ACACIA_STANDING_SIGN_NAME = "minecraft:tile.acacia_standing_sign.name";

    /**
     * Acacia Trapdoor
     */
    String MC_TILE_ACACIA_TRAPDOOR_NAME = "minecraft:tile.acacia_trapdoor.name";

    /**
     * Activator Rail
     */
    String MC_TILE_ACTIVATOR_RAIL_NAME = "minecraft:tile.activator_rail.name";

    /**
     * Air
     */
    String MC_TILE_AIR_NAME = "minecraft:tile.air.name";

    /**
     * Allow
     */
    String MC_TILE_ALLOW_NAME = "minecraft:tile.allow.name";

    /**
     * Block of Amethyst
     */
    String MC_TILE_AMETHYST_BLOCK_NAME = "minecraft:tile.amethyst_block.name";

    /**
     * Amethyst Cluster
     */
    String MC_TILE_AMETHYST_CLUSTER_NAME = "minecraft:tile.amethyst_cluster.name";

    /**
     * Ancient Debris
     */
    String MC_TILE_ANCIENT_DEBRIS_NAME = "minecraft:tile.ancient_debris.name";

    /**
     * Andesite Stairs
     */
    String MC_TILE_ANDESITE_STAIRS_NAME = "minecraft:tile.andesite_stairs.name";

    /**
     * Anvil
     */
    String MC_TILE_ANVIL_INTACT_NAME = "minecraft:tile.anvil.intact.name";

    /**
     * Anvil
     */
    String MC_TILE_ANVIL_NAME = "minecraft:tile.anvil.name";

    /**
     * Chipped Anvil
     */
    String MC_TILE_ANVIL_SLIGHTLYDAMAGED_NAME = "minecraft:tile.anvil.slightlyDamaged.name";

    /**
     * Damaged Anvil
     */
    String MC_TILE_ANVIL_VERYDAMAGED_NAME = "minecraft:tile.anvil.veryDamaged.name";

    /**
     * Azalea
     */
    String MC_TILE_AZALEA_NAME = "minecraft:tile.azalea.name";

    /**
     * Azalea Leaves
     */
    String MC_TILE_AZALEA_LEAVES_NAME = "minecraft:tile.azalea_leaves.name";

    /**
     * Flowering Azalea Leaves
     */
    String MC_TILE_AZALEA_LEAVES_FLOWERED_NAME = "minecraft:tile.azalea_leaves_flowered.name";

    /**
     * Bamboo
     */
    String MC_TILE_BAMBOO_NAME = "minecraft:tile.bamboo.name";

    /**
     * Block of Bamboo
     */
    String MC_TILE_BAMBOO_BLOCK_NAME = "minecraft:tile.bamboo_block.name";

    /**
     * Bamboo Button
     */
    String MC_TILE_BAMBOO_BUTTON_NAME = "minecraft:tile.bamboo_button.name";

    /**
     * Bamboo Fence
     */
    String MC_TILE_BAMBOO_FENCE_NAME = "minecraft:tile.bamboo_fence.name";

    /**
     * Bamboo Fence Gate
     */
    String MC_TILE_BAMBOO_FENCE_GATE_NAME = "minecraft:tile.bamboo_fence_gate.name";

    /**
     * Bamboo Mosaic
     */
    String MC_TILE_BAMBOO_MOSAIC_NAME = "minecraft:tile.bamboo_mosaic.name";

    /**
     * Bamboo Mosaic Slab
     */
    String MC_TILE_BAMBOO_MOSAIC_SLAB_NAME = "minecraft:tile.bamboo_mosaic_slab.name";

    /**
     * Bamboo Mosaic Stairs
     */
    String MC_TILE_BAMBOO_MOSAIC_STAIRS_NAME = "minecraft:tile.bamboo_mosaic_stairs.name";

    /**
     * Bamboo Planks
     */
    String MC_TILE_BAMBOO_PLANKS_NAME = "minecraft:tile.bamboo_planks.name";

    /**
     * Bamboo Pressure Plate
     */
    String MC_TILE_BAMBOO_PRESSURE_PLATE_NAME = "minecraft:tile.bamboo_pressure_plate.name";

    /**
     * Bamboo Shelf
     */
    String MC_TILE_BAMBOO_SHELF_NAME = "minecraft:tile.bamboo_shelf.name";

    /**
     * Bamboo Slab
     */
    String MC_TILE_BAMBOO_SLAB_NAME = "minecraft:tile.bamboo_slab.name";

    /**
     * Bamboo Stairs
     */
    String MC_TILE_BAMBOO_STAIRS_NAME = "minecraft:tile.bamboo_stairs.name";

    /**
     * Bamboo Trapdoor
     */
    String MC_TILE_BAMBOO_TRAPDOOR_NAME = "minecraft:tile.bamboo_trapdoor.name";

    /**
     * Barrel
     */
    String MC_TILE_BARREL_NAME = "minecraft:tile.barrel.name";

    /**
     * Barrier
     */
    String MC_TILE_BARRIER_NAME = "minecraft:tile.barrier.name";

    /**
     * Basalt
     */
    String MC_TILE_BASALT_NAME = "minecraft:tile.basalt.name";

    /**
     * Beacon
     */
    String MC_TILE_BEACON_NAME = "minecraft:tile.beacon.name";

    /**
     * Primary Power
     */
    String MC_TILE_BEACON_PRIMARY = "minecraft:tile.beacon.primary";

    /**
     * Primary
     */
    String MC_TILE_BEACON_PRIMARY_POCKET = "minecraft:tile.beacon.primary.pocket";

    /**
     * Secondary Power
     */
    String MC_TILE_BEACON_SECONDARY = "minecraft:tile.beacon.secondary";

    /**
     * Secondary
     */
    String MC_TILE_BEACON_SECONDARY_POCKET = "minecraft:tile.beacon.secondary.pocket";

    /**
     * Bed
     */
    String MC_TILE_BED_NAME = "minecraft:tile.bed.name";

    /**
     * You can only sleep at night and during thunderstorms
     */
    String MC_TILE_BED_NOSLEEP = "minecraft:tile.bed.noSleep";

    /**
     * You may not rest now, there are monsters nearby
     */
    String MC_TILE_BED_NOTSAFE = "minecraft:tile.bed.notSafe";

    /**
     * Your home bed was missing or obstructed
     */
    String MC_TILE_BED_NOTVALID = "minecraft:tile.bed.notValid";

    /**
     * Bed is obstructed
     */
    String MC_TILE_BED_OBSTRUCTED = "minecraft:tile.bed.obstructed";

    /**
     * This bed is occupied
     */
    String MC_TILE_BED_OCCUPIED = "minecraft:tile.bed.occupied";

    /**
     * Respawn point set
     */
    String MC_TILE_BED_RESPAWNSET = "minecraft:tile.bed.respawnSet";

    /**
     * Bed is too far away
     */
    String MC_TILE_BED_TOOFAR = "minecraft:tile.bed.tooFar";

    /**
     * Bedrock
     */
    String MC_TILE_BEDROCK_NAME = "minecraft:tile.bedrock.name";

    /**
     * Bee Nest
     */
    String MC_TILE_BEE_NEST_NAME = "minecraft:tile.bee_nest.name";

    /**
     * Beehive
     */
    String MC_TILE_BEEHIVE_NAME = "minecraft:tile.beehive.name";

    /**
     * Beetroot
     */
    String MC_TILE_BEETROOT_NAME = "minecraft:tile.beetroot.name";

    /**
     * Bell
     */
    String MC_TILE_BELL_NAME = "minecraft:tile.bell.name";

    /**
     * Big Dripleaf
     */
    String MC_TILE_BIG_DRIPLEAF_NAME = "minecraft:tile.big_dripleaf.name";

    /**
     * Birch Fence
     */
    String MC_TILE_BIRCHFENCE_NAME = "minecraft:tile.birchFence.name";

    /**
     * Birch Button
     */
    String MC_TILE_BIRCH_BUTTON_NAME = "minecraft:tile.birch_button.name";

    /**
     * Birch Fence Gate
     */
    String MC_TILE_BIRCH_FENCE_GATE_NAME = "minecraft:tile.birch_fence_gate.name";

    /**
     * Birch Pressure Plate
     */
    String MC_TILE_BIRCH_PRESSURE_PLATE_NAME = "minecraft:tile.birch_pressure_plate.name";

    /**
     * Birch Shelf
     */
    String MC_TILE_BIRCH_SHELF_NAME = "minecraft:tile.birch_shelf.name";

    /**
     * Birch Stairs
     */
    String MC_TILE_BIRCH_STAIRS_NAME = "minecraft:tile.birch_stairs.name";

    /**
     * Birch Sign
     */
    String MC_TILE_BIRCH_STANDING_SIGN_NAME = "minecraft:tile.birch_standing_sign.name";

    /**
     * Birch Trapdoor
     */
    String MC_TILE_BIRCH_TRAPDOOR_NAME = "minecraft:tile.birch_trapdoor.name";

    /**
     * Black Candle
     */
    String MC_TILE_BLACK_CANDLE_NAME = "minecraft:tile.black_candle.name";

    /**
     * Cake with Black Candle
     */
    String MC_TILE_BLACK_CANDLE_CAKE_NAME = "minecraft:tile.black_candle_cake.name";

    /**
     * Blackstone
     */
    String MC_TILE_BLACKSTONE_NAME = "minecraft:tile.blackstone.name";

    /**
     * Blackstone Slab
     */
    String MC_TILE_BLACKSTONE_SLAB_NAME = "minecraft:tile.blackstone_slab.name";

    /**
     * Blackstone Stairs
     */
    String MC_TILE_BLACKSTONE_STAIRS_NAME = "minecraft:tile.blackstone_stairs.name";

    /**
     * Blackstone Wall
     */
    String MC_TILE_BLACKSTONE_WALL_NAME = "minecraft:tile.blackstone_wall.name";

    /**
     * Blast Furnace
     */
    String MC_TILE_BLAST_FURNACE_NAME = "minecraft:tile.blast_furnace.name";

    /**
     * Blue Candle
     */
    String MC_TILE_BLUE_CANDLE_NAME = "minecraft:tile.blue_candle.name";

    /**
     * Cake with Blue Candle
     */
    String MC_TILE_BLUE_CANDLE_CAKE_NAME = "minecraft:tile.blue_candle_cake.name";

    /**
     * Blue Ice
     */
    String MC_TILE_BLUE_ICE_NAME = "minecraft:tile.blue_ice.name";

    /**
     * Bone Block
     */
    String MC_TILE_BONE_BLOCK_NAME = "minecraft:tile.bone_block.name";

    /**
     * Bookshelf
     */
    String MC_TILE_BOOKSHELF_NAME = "minecraft:tile.bookshelf.name";

    /**
     * Border
     */
    String MC_TILE_BORDER_BLOCK_NAME = "minecraft:tile.border_block.name";

    /**
     * Bricks
     */
    String MC_TILE_BRICK_BLOCK_NAME = "minecraft:tile.brick_block.name";

    /**
     * Brick Stairs
     */
    String MC_TILE_BRICK_STAIRS_NAME = "minecraft:tile.brick_stairs.name";

    /**
     * Brown Candle
     */
    String MC_TILE_BROWN_CANDLE_NAME = "minecraft:tile.brown_candle.name";

    /**
     * Cake with Brown Candle
     */
    String MC_TILE_BROWN_CANDLE_CAKE_NAME = "minecraft:tile.brown_candle_cake.name";

    /**
     * Brown Mushroom
     */
    String MC_TILE_BROWN_MUSHROOM_NAME = "minecraft:tile.brown_mushroom.name";

    /**
     * Brown Mushroom Block
     */
    String MC_TILE_BROWN_MUSHROOM_BLOCK_CAP_NAME = "minecraft:tile.brown_mushroom_block.cap.name";

    /**
     * Mushroom
     */
    String MC_TILE_BROWN_MUSHROOM_BLOCK_MUSHROOM_NAME = "minecraft:tile.brown_mushroom_block.mushroom.name";

    /**
     * Mushroom Stem
     */
    String MC_TILE_BROWN_MUSHROOM_BLOCK_STEM_NAME = "minecraft:tile.brown_mushroom_block.stem.name";

    /**
     * Budding Amethyst
     */
    String MC_TILE_BUDDING_AMETHYST_NAME = "minecraft:tile.budding_amethyst.name";

    /**
     * Bush
     */
    String MC_TILE_BUSH_NAME = "minecraft:tile.bush.name";

    /**
     * Cactus
     */
    String MC_TILE_CACTUS_NAME = "minecraft:tile.cactus.name";

    /**
     * Cactus Flower
     */
    String MC_TILE_CACTUS_FLOWER_NAME = "minecraft:tile.cactus_flower.name";

    /**
     * Cake
     */
    String MC_TILE_CAKE_NAME = "minecraft:tile.cake.name";

    /**
     * Calcite
     */
    String MC_TILE_CALCITE_NAME = "minecraft:tile.calcite.name";

    /**
     * Calibrated Sculk Sensor
     */
    String MC_TILE_CALIBRATED_SCULK_SENSOR_NAME = "minecraft:tile.calibrated_sculk_sensor.name";

    /**
     * Camera
     */
    String MC_TILE_CAMERA_NAME = "minecraft:tile.camera.name";

    /**
     * Campfire
     */
    String MC_TILE_CAMPFIRE_NAME = "minecraft:tile.campfire.name";

    /**
     * Candle
     */
    String MC_TILE_CANDLE_NAME = "minecraft:tile.candle.name";

    /**
     * Cake with Candle
     */
    String MC_TILE_CANDLE_CAKE_NAME = "minecraft:tile.candle_cake.name";

    /**
     * Black Carpet
     */
    String MC_TILE_CARPET_BLACK_NAME = "minecraft:tile.carpet.black.name";

    /**
     * Blue Carpet
     */
    String MC_TILE_CARPET_BLUE_NAME = "minecraft:tile.carpet.blue.name";

    /**
     * Brown Carpet
     */
    String MC_TILE_CARPET_BROWN_NAME = "minecraft:tile.carpet.brown.name";

    /**
     * Cyan Carpet
     */
    String MC_TILE_CARPET_CYAN_NAME = "minecraft:tile.carpet.cyan.name";

    /**
     * Gray Carpet
     */
    String MC_TILE_CARPET_GRAY_NAME = "minecraft:tile.carpet.gray.name";

    /**
     * Green Carpet
     */
    String MC_TILE_CARPET_GREEN_NAME = "minecraft:tile.carpet.green.name";

    /**
     * Light Blue Carpet
     */
    String MC_TILE_CARPET_LIGHTBLUE_NAME = "minecraft:tile.carpet.lightBlue.name";

    /**
     * Lime Carpet
     */
    String MC_TILE_CARPET_LIME_NAME = "minecraft:tile.carpet.lime.name";

    /**
     * Magenta Carpet
     */
    String MC_TILE_CARPET_MAGENTA_NAME = "minecraft:tile.carpet.magenta.name";

    /**
     * Carpet
     */
    String MC_TILE_CARPET_NAME = "minecraft:tile.carpet.name";

    /**
     * Orange Carpet
     */
    String MC_TILE_CARPET_ORANGE_NAME = "minecraft:tile.carpet.orange.name";

    /**
     * Pink Carpet
     */
    String MC_TILE_CARPET_PINK_NAME = "minecraft:tile.carpet.pink.name";

    /**
     * Purple Carpet
     */
    String MC_TILE_CARPET_PURPLE_NAME = "minecraft:tile.carpet.purple.name";

    /**
     * Red Carpet
     */
    String MC_TILE_CARPET_RED_NAME = "minecraft:tile.carpet.red.name";

    /**
     * Light Gray Carpet
     */
    String MC_TILE_CARPET_SILVER_NAME = "minecraft:tile.carpet.silver.name";

    /**
     * White Carpet
     */
    String MC_TILE_CARPET_WHITE_NAME = "minecraft:tile.carpet.white.name";

    /**
     * Yellow Carpet
     */
    String MC_TILE_CARPET_YELLOW_NAME = "minecraft:tile.carpet.yellow.name";

    /**
     * Carrots
     */
    String MC_TILE_CARROTS_NAME = "minecraft:tile.carrots.name";

    /**
     * Cartography Table
     */
    String MC_TILE_CARTOGRAPHY_TABLE_NAME = "minecraft:tile.cartography_table.name";

    /**
     * Carved Pumpkin
     */
    String MC_TILE_CARVED_PUMPKIN_NAME = "minecraft:tile.carved_pumpkin.name";

    /**
     * Cauldron
     */
    String MC_TILE_CAULDRON_NAME = "minecraft:tile.cauldron.name";

    /**
     * Cave Vines
     */
    String MC_TILE_CAVE_VINES_NAME = "minecraft:tile.cave_vines.name";

    /**
     * Cave Vines
     */
    String MC_TILE_CAVE_VINES_BODY_WITH_BERRIES_NAME = "minecraft:tile.cave_vines_body_with_berries.name";

    /**
     * Cave Vines
     */
    String MC_TILE_CAVE_VINES_HEAD_WITH_BERRIES_NAME = "minecraft:tile.cave_vines_head_with_berries.name";

    /**
     * Chain
     */
    String MC_TILE_CHAIN_NAME = "minecraft:tile.chain.name";

    /**
     * Chain Command Block
     */
    String MC_TILE_CHAIN_COMMAND_BLOCK_NAME = "minecraft:tile.chain_command_block.name";

    /**
     * Slate
     */
    String MC_TILE_CHALKBOARD_ONEBYONE_NAME = "minecraft:tile.chalkboard.oneByOne.name";

    /**
     * Board
     */
    String MC_TILE_CHALKBOARD_THREEBYTWO_NAME = "minecraft:tile.chalkboard.threeByTwo.name";

    /**
     * Poster
     */
    String MC_TILE_CHALKBOARD_TWOBYONE_NAME = "minecraft:tile.chalkboard.twoByOne.name";

    /**
     * Cherry Button
     */
    String MC_TILE_CHERRY_BUTTON_NAME = "minecraft:tile.cherry_button.name";

    /**
     * Cherry Double Slab
     */
    String MC_TILE_CHERRY_DOUBLE_SLAB_NAME = "minecraft:tile.cherry_double_slab.name";

    /**
     * Cherry Fence
     */
    String MC_TILE_CHERRY_FENCE_NAME = "minecraft:tile.cherry_fence.name";

    /**
     * Cherry Fence Gate
     */
    String MC_TILE_CHERRY_FENCE_GATE_NAME = "minecraft:tile.cherry_fence_gate.name";

    /**
     * Cherry Leaves
     */
    String MC_TILE_CHERRY_LEAVES_NAME = "minecraft:tile.cherry_leaves.name";

    /**
     * Cherry Log
     */
    String MC_TILE_CHERRY_LOG_NAME = "minecraft:tile.cherry_log.name";

    /**
     * Cherry Planks
     */
    String MC_TILE_CHERRY_PLANKS_NAME = "minecraft:tile.cherry_planks.name";

    /**
     * Cherry Pressure Plate
     */
    String MC_TILE_CHERRY_PRESSURE_PLATE_NAME = "minecraft:tile.cherry_pressure_plate.name";

    /**
     * Cherry Sapling
     */
    String MC_TILE_CHERRY_SAPLING_NAME = "minecraft:tile.cherry_sapling.name";

    /**
     * Cherry Shelf
     */
    String MC_TILE_CHERRY_SHELF_NAME = "minecraft:tile.cherry_shelf.name";

    /**
     * Cherry Slab
     */
    String MC_TILE_CHERRY_SLAB_NAME = "minecraft:tile.cherry_slab.name";

    /**
     * Cherry Stairs
     */
    String MC_TILE_CHERRY_STAIRS_NAME = "minecraft:tile.cherry_stairs.name";

    /**
     * Cherry Trapdoor
     */
    String MC_TILE_CHERRY_TRAPDOOR_NAME = "minecraft:tile.cherry_trapdoor.name";

    /**
     * Cherry Wood
     */
    String MC_TILE_CHERRY_WOOD_NAME = "minecraft:tile.cherry_wood.name";

    /**
     * Chest
     */
    String MC_TILE_CHEST_NAME = "minecraft:tile.chest.name";

    /**
     * Chiseled Bookshelf
     */
    String MC_TILE_CHISELED_BOOKSHELF_NAME = "minecraft:tile.chiseled_bookshelf.name";

    /**
     * Chiseled Copper
     */
    String MC_TILE_CHISELED_COPPER_NAME = "minecraft:tile.chiseled_copper.name";

    /**
     * Chiseled Deepslate
     */
    String MC_TILE_CHISELED_DEEPSLATE_NAME = "minecraft:tile.chiseled_deepslate.name";

    /**
     * Chiseled Nether Bricks
     */
    String MC_TILE_CHISELED_NETHER_BRICKS_NAME = "minecraft:tile.chiseled_nether_bricks.name";

    /**
     * Chiseled Polished Blackstone
     */
    String MC_TILE_CHISELED_POLISHED_BLACKSTONE_NAME = "minecraft:tile.chiseled_polished_blackstone.name";

    /**
     * Chiseled Resin Bricks
     */
    String MC_TILE_CHISELED_RESIN_BRICKS_NAME = "minecraft:tile.chiseled_resin_bricks.name";

    /**
     * Chiseled Tuff
     */
    String MC_TILE_CHISELED_TUFF_NAME = "minecraft:tile.chiseled_tuff.name";

    /**
     * Chiseled Tuff Bricks
     */
    String MC_TILE_CHISELED_TUFF_BRICKS_NAME = "minecraft:tile.chiseled_tuff_bricks.name";

    /**
     * Chorus Flower
     */
    String MC_TILE_CHORUS_FLOWER_NAME = "minecraft:tile.chorus_flower.name";

    /**
     * Chorus Plant
     */
    String MC_TILE_CHORUS_PLANT_NAME = "minecraft:tile.chorus_plant.name";

    /**
     * Clay
     */
    String MC_TILE_CLAY_NAME = "minecraft:tile.clay.name";

    /**
     * Closed Eyeblossom
     */
    String MC_TILE_CLOSED_EYEBLOSSOM_NAME = "minecraft:tile.closed_eyeblossom.name";

    /**
     * Block of Coal
     */
    String MC_TILE_COAL_BLOCK_NAME = "minecraft:tile.coal_block.name";

    /**
     * Coal Ore
     */
    String MC_TILE_COAL_ORE_NAME = "minecraft:tile.coal_ore.name";

    /**
     * Cobbled Deepslate
     */
    String MC_TILE_COBBLED_DEEPSLATE_NAME = "minecraft:tile.cobbled_deepslate.name";

    /**
     * Cobbled Deepslate Double Slab
     */
    String MC_TILE_COBBLED_DEEPSLATE_DOUBLE_SLAB_NAME = "minecraft:tile.cobbled_deepslate_double_slab.name";

    /**
     * Cobbled Deepslate Slab
     */
    String MC_TILE_COBBLED_DEEPSLATE_SLAB_NAME = "minecraft:tile.cobbled_deepslate_slab.name";

    /**
     * Cobbled Deepslate Stairs
     */
    String MC_TILE_COBBLED_DEEPSLATE_STAIRS_NAME = "minecraft:tile.cobbled_deepslate_stairs.name";

    /**
     * Cobbled Deepslate Wall
     */
    String MC_TILE_COBBLED_DEEPSLATE_WALL_NAME = "minecraft:tile.cobbled_deepslate_wall.name";

    /**
     * Cobblestone
     */
    String MC_TILE_COBBLESTONE_NAME = "minecraft:tile.cobblestone.name";

    /**
     * Andesite Wall
     */
    String MC_TILE_COBBLESTONE_WALL_ANDESITE_NAME = "minecraft:tile.cobblestone_wall.andesite.name";

    /**
     * Brick Wall
     */
    String MC_TILE_COBBLESTONE_WALL_BRICK_NAME = "minecraft:tile.cobblestone_wall.brick.name";

    /**
     * Diorite Wall
     */
    String MC_TILE_COBBLESTONE_WALL_DIORITE_NAME = "minecraft:tile.cobblestone_wall.diorite.name";

    /**
     * End Stone Brick Wall
     */
    String MC_TILE_COBBLESTONE_WALL_END_BRICK_NAME = "minecraft:tile.cobblestone_wall.end_brick.name";

    /**
     * Granite Wall
     */
    String MC_TILE_COBBLESTONE_WALL_GRANITE_NAME = "minecraft:tile.cobblestone_wall.granite.name";

    /**
     * Mossy Cobblestone Wall
     */
    String MC_TILE_COBBLESTONE_WALL_MOSSY_NAME = "minecraft:tile.cobblestone_wall.mossy.name";

    /**
     * Mossy Stone Brick Wall
     */
    String MC_TILE_COBBLESTONE_WALL_MOSSY_STONE_BRICK_NAME = "minecraft:tile.cobblestone_wall.mossy_stone_brick.name";

    /**
     * Nether Brick Wall
     */
    String MC_TILE_COBBLESTONE_WALL_NETHER_BRICK_NAME = "minecraft:tile.cobblestone_wall.nether_brick.name";

    /**
     * Cobblestone Wall 
     */
    String MC_TILE_COBBLESTONE_WALL_NORMAL_NAME = "minecraft:tile.cobblestone_wall.normal.name";

    /**
     * Prismarine Wall
     */
    String MC_TILE_COBBLESTONE_WALL_PRISMARINE_NAME = "minecraft:tile.cobblestone_wall.prismarine.name";

    /**
     * Red Nether Brick Wall
     */
    String MC_TILE_COBBLESTONE_WALL_RED_NETHER_BRICK_NAME = "minecraft:tile.cobblestone_wall.red_nether_brick.name";

    /**
     * Red Sandstone Wall
     */
    String MC_TILE_COBBLESTONE_WALL_RED_SANDSTONE_NAME = "minecraft:tile.cobblestone_wall.red_sandstone.name";

    /**
     * Sandstone Wall
     */
    String MC_TILE_COBBLESTONE_WALL_SANDSTONE_NAME = "minecraft:tile.cobblestone_wall.sandstone.name";

    /**
     * Stone Brick Wall
     */
    String MC_TILE_COBBLESTONE_WALL_STONE_BRICK_NAME = "minecraft:tile.cobblestone_wall.stone_brick.name";

    /**
     * Cocoa
     */
    String MC_TILE_COCOA_NAME = "minecraft:tile.cocoa.name";

    /**
     * Command Block
     */
    String MC_TILE_COMMAND_BLOCK_NAME = "minecraft:tile.command_block.name";

    /**
     * Composter
     */
    String MC_TILE_COMPOSTER_NAME = "minecraft:tile.composter.name";

    /**
     * Black Concrete
     */
    String MC_TILE_CONCRETE_BLACK_NAME = "minecraft:tile.concrete.black.name";

    /**
     * Blue Concrete
     */
    String MC_TILE_CONCRETE_BLUE_NAME = "minecraft:tile.concrete.blue.name";

    /**
     * Brown Concrete
     */
    String MC_TILE_CONCRETE_BROWN_NAME = "minecraft:tile.concrete.brown.name";

    /**
     * Cyan Concrete
     */
    String MC_TILE_CONCRETE_CYAN_NAME = "minecraft:tile.concrete.cyan.name";

    /**
     * Gray Concrete
     */
    String MC_TILE_CONCRETE_GRAY_NAME = "minecraft:tile.concrete.gray.name";

    /**
     * Green Concrete
     */
    String MC_TILE_CONCRETE_GREEN_NAME = "minecraft:tile.concrete.green.name";

    /**
     * Light Blue Concrete
     */
    String MC_TILE_CONCRETE_LIGHTBLUE_NAME = "minecraft:tile.concrete.lightBlue.name";

    /**
     * Lime Concrete
     */
    String MC_TILE_CONCRETE_LIME_NAME = "minecraft:tile.concrete.lime.name";

    /**
     * Magenta Concrete
     */
    String MC_TILE_CONCRETE_MAGENTA_NAME = "minecraft:tile.concrete.magenta.name";

    /**
     * Orange Concrete
     */
    String MC_TILE_CONCRETE_ORANGE_NAME = "minecraft:tile.concrete.orange.name";

    /**
     * Pink Concrete
     */
    String MC_TILE_CONCRETE_PINK_NAME = "minecraft:tile.concrete.pink.name";

    /**
     * Purple Concrete
     */
    String MC_TILE_CONCRETE_PURPLE_NAME = "minecraft:tile.concrete.purple.name";

    /**
     * Red Concrete
     */
    String MC_TILE_CONCRETE_RED_NAME = "minecraft:tile.concrete.red.name";

    /**
     * Light Gray Concrete
     */
    String MC_TILE_CONCRETE_SILVER_NAME = "minecraft:tile.concrete.silver.name";

    /**
     * White Concrete
     */
    String MC_TILE_CONCRETE_WHITE_NAME = "minecraft:tile.concrete.white.name";

    /**
     * Yellow Concrete
     */
    String MC_TILE_CONCRETE_YELLOW_NAME = "minecraft:tile.concrete.yellow.name";

    /**
     * Black Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_BLACK_NAME = "minecraft:tile.concretePowder.black.name";

    /**
     * Blue Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_BLUE_NAME = "minecraft:tile.concretePowder.blue.name";

    /**
     * Brown Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_BROWN_NAME = "minecraft:tile.concretePowder.brown.name";

    /**
     * Cyan Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_CYAN_NAME = "minecraft:tile.concretePowder.cyan.name";

    /**
     * Gray Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_GRAY_NAME = "minecraft:tile.concretePowder.gray.name";

    /**
     * Green Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_GREEN_NAME = "minecraft:tile.concretePowder.green.name";

    /**
     * Light Blue Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_LIGHTBLUE_NAME = "minecraft:tile.concretePowder.lightBlue.name";

    /**
     * Lime Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_LIME_NAME = "minecraft:tile.concretePowder.lime.name";

    /**
     * Magenta Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_MAGENTA_NAME = "minecraft:tile.concretePowder.magenta.name";

    /**
     * Orange Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_ORANGE_NAME = "minecraft:tile.concretePowder.orange.name";

    /**
     * Pink Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_PINK_NAME = "minecraft:tile.concretePowder.pink.name";

    /**
     * Purple Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_PURPLE_NAME = "minecraft:tile.concretePowder.purple.name";

    /**
     * Red Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_RED_NAME = "minecraft:tile.concretePowder.red.name";

    /**
     * Light Gray Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_SILVER_NAME = "minecraft:tile.concretePowder.silver.name";

    /**
     * White Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_WHITE_NAME = "minecraft:tile.concretePowder.white.name";

    /**
     * Yellow Concrete Powder
     */
    String MC_TILE_CONCRETEPOWDER_YELLOW_NAME = "minecraft:tile.concretePowder.yellow.name";

    /**
     * Conduit
     */
    String MC_TILE_CONDUIT_NAME = "minecraft:tile.conduit.name";

    /**
     * Copper Bars
     */
    String MC_TILE_COPPER_BARS_NAME = "minecraft:tile.copper_bars.name";

    /**
     * Block of Copper
     */
    String MC_TILE_COPPER_BLOCK_NAME = "minecraft:tile.copper_block.name";

    /**
     * Copper Bulb
     */
    String MC_TILE_COPPER_BULB_NAME = "minecraft:tile.copper_bulb.name";

    /**
     * Copper Chain
     */
    String MC_TILE_COPPER_CHAIN_NAME = "minecraft:tile.copper_chain.name";

    /**
     * Copper Chest
     */
    String MC_TILE_COPPER_CHEST_NAME = "minecraft:tile.copper_chest.name";

    /**
     * Copper Door
     */
    String MC_TILE_COPPER_DOOR_NAME = "minecraft:tile.copper_door.name";

    /**
     * Copper Golem Statue
     */
    String MC_TILE_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.copper_golem_statue.name";

    /**
     * Copper Grate
     */
    String MC_TILE_COPPER_GRATE_NAME = "minecraft:tile.copper_grate.name";

    /**
     * Copper Lantern
     */
    String MC_TILE_COPPER_LANTERN_NAME = "minecraft:tile.copper_lantern.name";

    /**
     * Copper Ore
     */
    String MC_TILE_COPPER_ORE_NAME = "minecraft:tile.copper_ore.name";

    /**
     * Copper Torch
     */
    String MC_TILE_COPPER_TORCH_NAME = "minecraft:tile.copper_torch.name";

    /**
     * Copper Trapdoor
     */
    String MC_TILE_COPPER_TRAPDOOR_NAME = "minecraft:tile.copper_trapdoor.name";

    /**
     * Tube Coral
     */
    String MC_TILE_CORAL_BLUE_NAME = "minecraft:tile.coral.blue.name";

    /**
     * Dead Tube Coral
     */
    String MC_TILE_CORAL_BLUE_DEAD_NAME = "minecraft:tile.coral.blue_dead.name";

    /**
     * Brain Coral
     */
    String MC_TILE_CORAL_PINK_NAME = "minecraft:tile.coral.pink.name";

    /**
     * Dead Brain Coral
     */
    String MC_TILE_CORAL_PINK_DEAD_NAME = "minecraft:tile.coral.pink_dead.name";

    /**
     * Bubble Coral
     */
    String MC_TILE_CORAL_PURPLE_NAME = "minecraft:tile.coral.purple.name";

    /**
     * Dead Bubble Coral
     */
    String MC_TILE_CORAL_PURPLE_DEAD_NAME = "minecraft:tile.coral.purple_dead.name";

    /**
     * Fire Coral
     */
    String MC_TILE_CORAL_RED_NAME = "minecraft:tile.coral.red.name";

    /**
     * Dead Fire Coral
     */
    String MC_TILE_CORAL_RED_DEAD_NAME = "minecraft:tile.coral.red_dead.name";

    /**
     * Horn Coral
     */
    String MC_TILE_CORAL_YELLOW_NAME = "minecraft:tile.coral.yellow.name";

    /**
     * Dead Horn Coral
     */
    String MC_TILE_CORAL_YELLOW_DEAD_NAME = "minecraft:tile.coral.yellow_dead.name";

    /**
     * Tube Coral Block
     */
    String MC_TILE_CORAL_BLOCK_BLUE_NAME = "minecraft:tile.coral_block.blue.name";

    /**
     * Dead Tube Coral Block
     */
    String MC_TILE_CORAL_BLOCK_BLUE_DEAD_NAME = "minecraft:tile.coral_block.blue_dead.name";

    /**
     * Brain Coral Block
     */
    String MC_TILE_CORAL_BLOCK_PINK_NAME = "minecraft:tile.coral_block.pink.name";

    /**
     * Dead Brain Coral Block
     */
    String MC_TILE_CORAL_BLOCK_PINK_DEAD_NAME = "minecraft:tile.coral_block.pink_dead.name";

    /**
     * Bubble Coral Block
     */
    String MC_TILE_CORAL_BLOCK_PURPLE_NAME = "minecraft:tile.coral_block.purple.name";

    /**
     * Dead Bubble Coral Block
     */
    String MC_TILE_CORAL_BLOCK_PURPLE_DEAD_NAME = "minecraft:tile.coral_block.purple_dead.name";

    /**
     * Fire Coral Block
     */
    String MC_TILE_CORAL_BLOCK_RED_NAME = "minecraft:tile.coral_block.red.name";

    /**
     * Dead Fire Coral Block
     */
    String MC_TILE_CORAL_BLOCK_RED_DEAD_NAME = "minecraft:tile.coral_block.red_dead.name";

    /**
     * Horn Coral Block
     */
    String MC_TILE_CORAL_BLOCK_YELLOW_NAME = "minecraft:tile.coral_block.yellow.name";

    /**
     * Dead Horn Coral Block
     */
    String MC_TILE_CORAL_BLOCK_YELLOW_DEAD_NAME = "minecraft:tile.coral_block.yellow_dead.name";

    /**
     * Tube Coral Fan
     */
    String MC_TILE_CORAL_FAN_BLUE_FAN_NAME = "minecraft:tile.coral_fan.blue_fan.name";

    /**
     * Brain Coral Fan
     */
    String MC_TILE_CORAL_FAN_PINK_FAN_NAME = "minecraft:tile.coral_fan.pink_fan.name";

    /**
     * Bubble Coral Fan
     */
    String MC_TILE_CORAL_FAN_PURPLE_FAN_NAME = "minecraft:tile.coral_fan.purple_fan.name";

    /**
     * Fire Coral Fan
     */
    String MC_TILE_CORAL_FAN_RED_FAN_NAME = "minecraft:tile.coral_fan.red_fan.name";

    /**
     * Horn Coral Fan
     */
    String MC_TILE_CORAL_FAN_YELLOW_FAN_NAME = "minecraft:tile.coral_fan.yellow_fan.name";

    /**
     * Dead Tube Coral Fan
     */
    String MC_TILE_CORAL_FAN_DEAD_BLUE_FAN_NAME = "minecraft:tile.coral_fan_dead.blue_fan.name";

    /**
     * Dead Brain Coral Fan
     */
    String MC_TILE_CORAL_FAN_DEAD_PINK_FAN_NAME = "minecraft:tile.coral_fan_dead.pink_fan.name";

    /**
     * Dead Bubble Coral Fan
     */
    String MC_TILE_CORAL_FAN_DEAD_PURPLE_FAN_NAME = "minecraft:tile.coral_fan_dead.purple_fan.name";

    /**
     * Dead Fire Coral Fan
     */
    String MC_TILE_CORAL_FAN_DEAD_RED_FAN_NAME = "minecraft:tile.coral_fan_dead.red_fan.name";

    /**
     * Dead Horn Coral Fan
     */
    String MC_TILE_CORAL_FAN_DEAD_YELLOW_FAN_NAME = "minecraft:tile.coral_fan_dead.yellow_fan.name";

    /**
     * Cracked Deepslate Bricks
     */
    String MC_TILE_CRACKED_DEEPSLATE_BRICKS_NAME = "minecraft:tile.cracked_deepslate_bricks.name";

    /**
     * Cracked Deepslate Tiles
     */
    String MC_TILE_CRACKED_DEEPSLATE_TILES_NAME = "minecraft:tile.cracked_deepslate_tiles.name";

    /**
     * Cracked Nether Bricks
     */
    String MC_TILE_CRACKED_NETHER_BRICKS_NAME = "minecraft:tile.cracked_nether_bricks.name";

    /**
     * Cracked Polished Blackstone Bricks
     */
    String MC_TILE_CRACKED_POLISHED_BLACKSTONE_BRICKS_NAME = "minecraft:tile.cracked_polished_blackstone_bricks.name";

    /**
     * Crafter
     */
    String MC_TILE_CRAFTER_NAME = "minecraft:tile.crafter.name";

    /**
     * Crafting Table
     */
    String MC_TILE_CRAFTING_TABLE_NAME = "minecraft:tile.crafting_table.name";

    /**
     * Creaking Heart
     */
    String MC_TILE_CREAKING_HEART_NAME = "minecraft:tile.creaking_heart.name";

    /**
     * Crimson Button
     */
    String MC_TILE_CRIMSON_BUTTON_NAME = "minecraft:tile.crimson_button.name";

    /**
     * Crimson Door
     */
    String MC_TILE_CRIMSON_DOOR_NAME = "minecraft:tile.crimson_door.name";

    /**
     * Crimson Slab
     */
    String MC_TILE_CRIMSON_DOUBLE_SLAB_NAME = "minecraft:tile.crimson_double_slab.name";

    /**
     * Crimson Fence
     */
    String MC_TILE_CRIMSON_FENCE_NAME = "minecraft:tile.crimson_fence.name";

    /**
     * Crimson Fence Gate
     */
    String MC_TILE_CRIMSON_FENCE_GATE_NAME = "minecraft:tile.crimson_fence_gate.name";

    /**
     * Crimson Fungus
     */
    String MC_TILE_CRIMSON_FUNGUS_NAME = "minecraft:tile.crimson_fungus.name";

    /**
     * Crimson Hyphae
     */
    String MC_TILE_CRIMSON_HYPHAE_NAME = "minecraft:tile.crimson_hyphae.name";

    /**
     * Crimson Nylium
     */
    String MC_TILE_CRIMSON_NYLIUM_NAME = "minecraft:tile.crimson_nylium.name";

    /**
     * Crimson Planks
     */
    String MC_TILE_CRIMSON_PLANKS_NAME = "minecraft:tile.crimson_planks.name";

    /**
     * Crimson Pressure Plate
     */
    String MC_TILE_CRIMSON_PRESSURE_PLATE_NAME = "minecraft:tile.crimson_pressure_plate.name";

    /**
     * Crimson Roots
     */
    String MC_TILE_CRIMSON_ROOTS_CRIMSONROOTS_NAME = "minecraft:tile.crimson_roots.crimsonRoots.name";

    /**
     * Crimson Shelf
     */
    String MC_TILE_CRIMSON_SHELF_NAME = "minecraft:tile.crimson_shelf.name";

    /**
     * Crimson Slab
     */
    String MC_TILE_CRIMSON_SLAB_NAME = "minecraft:tile.crimson_slab.name";

    /**
     * Crimson Stairs
     */
    String MC_TILE_CRIMSON_STAIRS_NAME = "minecraft:tile.crimson_stairs.name";

    /**
     * Crimson Sign
     */
    String MC_TILE_CRIMSON_STANDING_SIGN_NAME = "minecraft:tile.crimson_standing_sign.name";

    /**
     * Crimson Stem
     */
    String MC_TILE_CRIMSON_STEM_NAME = "minecraft:tile.crimson_stem.name";

    /**
     * Crimson Trapdoor
     */
    String MC_TILE_CRIMSON_TRAPDOOR_NAME = "minecraft:tile.crimson_trapdoor.name";

    /**
     * Crimson Sign
     */
    String MC_TILE_CRIMSON_WALL_SIGN_NAME = "minecraft:tile.crimson_wall_sign.name";

    /**
     * Crying Obsidian
     */
    String MC_TILE_CRYING_OBSIDIAN_NAME = "minecraft:tile.crying_obsidian.name";

    /**
     * Cut Copper
     */
    String MC_TILE_CUT_COPPER_NAME = "minecraft:tile.cut_copper.name";

    /**
     * Cut Copper Slab
     */
    String MC_TILE_CUT_COPPER_SLAB_NAME = "minecraft:tile.cut_copper_slab.name";

    /**
     * Cut Copper Stairs
     */
    String MC_TILE_CUT_COPPER_STAIRS_NAME = "minecraft:tile.cut_copper_stairs.name";

    /**
     * Cyan Candle
     */
    String MC_TILE_CYAN_CANDLE_NAME = "minecraft:tile.cyan_candle.name";

    /**
     * Cake with Cyan Candle
     */
    String MC_TILE_CYAN_CANDLE_CAKE_NAME = "minecraft:tile.cyan_candle_cake.name";

    /**
     * Dark Oak Fence
     */
    String MC_TILE_DARKOAKFENCE_NAME = "minecraft:tile.darkOakFence.name";

    /**
     * Dark Oak Button
     */
    String MC_TILE_DARK_OAK_BUTTON_NAME = "minecraft:tile.dark_oak_button.name";

    /**
     * Dark Oak Fence Gate
     */
    String MC_TILE_DARK_OAK_FENCE_GATE_NAME = "minecraft:tile.dark_oak_fence_gate.name";

    /**
     * Dark Oak Pressure Plate
     */
    String MC_TILE_DARK_OAK_PRESSURE_PLATE_NAME = "minecraft:tile.dark_oak_pressure_plate.name";

    /**
     * Dark Oak Shelf
     */
    String MC_TILE_DARK_OAK_SHELF_NAME = "minecraft:tile.dark_oak_shelf.name";

    /**
     * Dark Oak Stairs
     */
    String MC_TILE_DARK_OAK_STAIRS_NAME = "minecraft:tile.dark_oak_stairs.name";

    /**
     * Dark Oak Trapdoor
     */
    String MC_TILE_DARK_OAK_TRAPDOOR_NAME = "minecraft:tile.dark_oak_trapdoor.name";

    /**
     * Dark Prismarine Stairs
     */
    String MC_TILE_DARK_PRISMARINE_STAIRS_NAME = "minecraft:tile.dark_prismarine_stairs.name";

    /**
     * Dark Oak Sign
     */
    String MC_TILE_DARKOAK_STANDING_SIGN_NAME = "minecraft:tile.darkoak_standing_sign.name";

    /**
     * Daylight Detector
     */
    String MC_TILE_DAYLIGHT_DETECTOR_NAME = "minecraft:tile.daylight_detector.name";

    /**
     * Dead Bush
     */
    String MC_TILE_DEADBUSH_NAME = "minecraft:tile.deadbush.name";

    /**
     * Decorated Pot
     */
    String MC_TILE_DECORATED_POT_NAME = "minecraft:tile.decorated_pot.name";

    /**
     * Deepslate
     */
    String MC_TILE_DEEPSLATE_NAME = "minecraft:tile.deepslate.name";

    /**
     * Deepslate Brick Double Slab
     */
    String MC_TILE_DEEPSLATE_BRICK_DOUBLE_SLAB_NAME = "minecraft:tile.deepslate_brick_double_slab.name";

    /**
     * Deepslate Brick Slab
     */
    String MC_TILE_DEEPSLATE_BRICK_SLAB_NAME = "minecraft:tile.deepslate_brick_slab.name";

    /**
     * Deepslate Brick Stairs
     */
    String MC_TILE_DEEPSLATE_BRICK_STAIRS_NAME = "minecraft:tile.deepslate_brick_stairs.name";

    /**
     * Deepslate Brick Wall
     */
    String MC_TILE_DEEPSLATE_BRICK_WALL_NAME = "minecraft:tile.deepslate_brick_wall.name";

    /**
     * Deepslate Bricks
     */
    String MC_TILE_DEEPSLATE_BRICKS_NAME = "minecraft:tile.deepslate_bricks.name";

    /**
     * Deepslate Coal Ore
     */
    String MC_TILE_DEEPSLATE_COAL_ORE_NAME = "minecraft:tile.deepslate_coal_ore.name";

    /**
     * Deepslate Copper Ore
     */
    String MC_TILE_DEEPSLATE_COPPER_ORE_NAME = "minecraft:tile.deepslate_copper_ore.name";

    /**
     * Deepslate Diamond Ore
     */
    String MC_TILE_DEEPSLATE_DIAMOND_ORE_NAME = "minecraft:tile.deepslate_diamond_ore.name";

    /**
     * Deepslate Emerald Ore
     */
    String MC_TILE_DEEPSLATE_EMERALD_ORE_NAME = "minecraft:tile.deepslate_emerald_ore.name";

    /**
     * Deepslate Gold Ore
     */
    String MC_TILE_DEEPSLATE_GOLD_ORE_NAME = "minecraft:tile.deepslate_gold_ore.name";

    /**
     * Deepslate Iron Ore
     */
    String MC_TILE_DEEPSLATE_IRON_ORE_NAME = "minecraft:tile.deepslate_iron_ore.name";

    /**
     * Deepslate Lapis Lazuli Ore
     */
    String MC_TILE_DEEPSLATE_LAPIS_ORE_NAME = "minecraft:tile.deepslate_lapis_ore.name";

    /**
     * Deepslate Redstone Ore
     */
    String MC_TILE_DEEPSLATE_REDSTONE_ORE_NAME = "minecraft:tile.deepslate_redstone_ore.name";

    /**
     * Deepslate Tile Double Slab
     */
    String MC_TILE_DEEPSLATE_TILE_DOUBLE_SLAB_NAME = "minecraft:tile.deepslate_tile_double_slab.name";

    /**
     * Deepslate Tile Slab
     */
    String MC_TILE_DEEPSLATE_TILE_SLAB_NAME = "minecraft:tile.deepslate_tile_slab.name";

    /**
     * Deepslate Tile Stairs
     */
    String MC_TILE_DEEPSLATE_TILE_STAIRS_NAME = "minecraft:tile.deepslate_tile_stairs.name";

    /**
     * Deepslate Tile Wall
     */
    String MC_TILE_DEEPSLATE_TILE_WALL_NAME = "minecraft:tile.deepslate_tile_wall.name";

    /**
     * Deepslate Tiles
     */
    String MC_TILE_DEEPSLATE_TILES_NAME = "minecraft:tile.deepslate_tiles.name";

    /**
     * Deny
     */
    String MC_TILE_DENY_NAME = "minecraft:tile.deny.name";

    /**
     * Detector Rail
     */
    String MC_TILE_DETECTOR_RAIL_NAME = "minecraft:tile.detector_rail.name";

    /**
     * Block of Diamond
     */
    String MC_TILE_DIAMOND_BLOCK_NAME = "minecraft:tile.diamond_block.name";

    /**
     * Diamond Ore
     */
    String MC_TILE_DIAMOND_ORE_NAME = "minecraft:tile.diamond_ore.name";

    /**
     * Diorite Stairs
     */
    String MC_TILE_DIORITE_STAIRS_NAME = "minecraft:tile.diorite_stairs.name";

    /**
     * Coarse Dirt
     */
    String MC_TILE_DIRT_COARSE_NAME = "minecraft:tile.dirt.coarse.name";

    /**
     * Dirt
     */
    String MC_TILE_DIRT_DEFAULT_NAME = "minecraft:tile.dirt.default.name";

    /**
     * Dirt
     */
    String MC_TILE_DIRT_NAME = "minecraft:tile.dirt.name";

    /**
     * Rooted Dirt
     */
    String MC_TILE_DIRT_WITH_ROOTS_NAME = "minecraft:tile.dirt_with_roots.name";

    /**
     * Dispenser
     */
    String MC_TILE_DISPENSER_NAME = "minecraft:tile.dispenser.name";

    /**
     * Wooden Door
     */
    String MC_TILE_DOORWOOD_NAME = "minecraft:tile.doorWood.name";

    /**
     * Large Fern
     */
    String MC_TILE_DOUBLE_PLANT_FERN_NAME = "minecraft:tile.double_plant.fern.name";

    /**
     * Tall Grass
     */
    String MC_TILE_DOUBLE_PLANT_GRASS_NAME = "minecraft:tile.double_plant.grass.name";

    /**
     * Plant
     */
    String MC_TILE_DOUBLE_PLANT_NAME = "minecraft:tile.double_plant.name";

    /**
     * Peony
     */
    String MC_TILE_DOUBLE_PLANT_PAEONIA_NAME = "minecraft:tile.double_plant.paeonia.name";

    /**
     * Rose Bush
     */
    String MC_TILE_DOUBLE_PLANT_ROSE_NAME = "minecraft:tile.double_plant.rose.name";

    /**
     * Sunflower
     */
    String MC_TILE_DOUBLE_PLANT_SUNFLOWER_NAME = "minecraft:tile.double_plant.sunflower.name";

    /**
     * Lilac
     */
    String MC_TILE_DOUBLE_PLANT_SYRINGA_NAME = "minecraft:tile.double_plant.syringa.name";

    /**
     * Brick Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_BRICK_NAME = "minecraft:tile.double_stone_slab.brick.name";

    /**
     * Cobblestone Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_COBBLE_NAME = "minecraft:tile.double_stone_slab.cobble.name";

    /**
     * Stone Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_NAME = "minecraft:tile.double_stone_slab.name";

    /**
     * Nether Brick Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_NETHER_BRICK_NAME = "minecraft:tile.double_stone_slab.nether_brick.name";

    /**
     * Quartz Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_QUARTZ_NAME = "minecraft:tile.double_stone_slab.quartz.name";

    /**
     * Sandstone Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_SAND_NAME = "minecraft:tile.double_stone_slab.sand.name";

    /**
     * Stone Brick Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_SMOOTHSTONEBRICK_NAME = "minecraft:tile.double_stone_slab.smoothStoneBrick.name";

    /**
     * Stone Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_STONE_NAME = "minecraft:tile.double_stone_slab.stone.name";

    /**
     * Wooden Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB_WOOD_NAME = "minecraft:tile.double_stone_slab.wood.name";

    /**
     * Red Sandstone Slab
     */
    String MC_TILE_DOUBLE_STONE_SLAB2_RED_SANDSTONE_NAME = "minecraft:tile.double_stone_slab2.red_sandstone.name";

    /**
     * Dragon Egg
     */
    String MC_TILE_DRAGON_EGG_NAME = "minecraft:tile.dragon_egg.name";

    /**
     * Dried Ghast
     */
    String MC_TILE_DRIED_GHAST_NAME = "minecraft:tile.dried_ghast.name";

    /**
     * Dried Kelp Block
     */
    String MC_TILE_DRIED_KELP_BLOCK_NAME = "minecraft:tile.dried_kelp_block.name";

    /**
     * Dripstone Block
     */
    String MC_TILE_DRIPSTONE_BLOCK_NAME = "minecraft:tile.dripstone_block.name";

    /**
     * Dropper
     */
    String MC_TILE_DROPPER_NAME = "minecraft:tile.dropper.name";

    /**
     * Block of Emerald
     */
    String MC_TILE_EMERALD_BLOCK_NAME = "minecraft:tile.emerald_block.name";

    /**
     * Emerald Ore
     */
    String MC_TILE_EMERALD_ORE_NAME = "minecraft:tile.emerald_ore.name";

    /**
     * Enchanting Table
     */
    String MC_TILE_ENCHANTING_TABLE_NAME = "minecraft:tile.enchanting_table.name";

    /**
     * End Stone Brick Stairs
     */
    String MC_TILE_END_BRICK_STAIRS_NAME = "minecraft:tile.end_brick_stairs.name";

    /**
     * End Stone Bricks
     */
    String MC_TILE_END_BRICKS_NAME = "minecraft:tile.end_bricks.name";

    /**
     * End Portal Frame
     */
    String MC_TILE_END_PORTAL_FRAME_NAME = "minecraft:tile.end_portal_frame.name";

    /**
     * End Rod
     */
    String MC_TILE_END_ROD_NAME = "minecraft:tile.end_rod.name";

    /**
     * End Stone
     */
    String MC_TILE_END_STONE_NAME = "minecraft:tile.end_stone.name";

    /**
     * Ender Chest
     */
    String MC_TILE_ENDERCHEST_NAME = "minecraft:tile.enderChest.name";

    /**
     * Ender Chest
     */
    String MC_TILE_ENDER_CHEST_NAME = "minecraft:tile.ender_chest.name";

    /**
     * Exposed Chiseled Copper
     */
    String MC_TILE_EXPOSED_CHISELED_COPPER_NAME = "minecraft:tile.exposed_chiseled_copper.name";

    /**
     * Exposed Copper
     */
    String MC_TILE_EXPOSED_COPPER_NAME = "minecraft:tile.exposed_copper.name";

    /**
     * Exposed Copper Bars
     */
    String MC_TILE_EXPOSED_COPPER_BARS_NAME = "minecraft:tile.exposed_copper_bars.name";

    /**
     * Exposed Copper Bulb
     */
    String MC_TILE_EXPOSED_COPPER_BULB_NAME = "minecraft:tile.exposed_copper_bulb.name";

    /**
     * Exposed Copper Chain
     */
    String MC_TILE_EXPOSED_COPPER_CHAIN_NAME = "minecraft:tile.exposed_copper_chain.name";

    /**
     * Exposed Copper Chest
     */
    String MC_TILE_EXPOSED_COPPER_CHEST_NAME = "minecraft:tile.exposed_copper_chest.name";

    /**
     * Exposed Copper Door
     */
    String MC_TILE_EXPOSED_COPPER_DOOR_NAME = "minecraft:tile.exposed_copper_door.name";

    /**
     * Exposed Copper Golem Statue
     */
    String MC_TILE_EXPOSED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.exposed_copper_golem_statue.name";

    /**
     * Exposed Copper Grate
     */
    String MC_TILE_EXPOSED_COPPER_GRATE_NAME = "minecraft:tile.exposed_copper_grate.name";

    /**
     * Exposed Copper Lantern
     */
    String MC_TILE_EXPOSED_COPPER_LANTERN_NAME = "minecraft:tile.exposed_copper_lantern.name";

    /**
     * Exposed Copper Trapdoor
     */
    String MC_TILE_EXPOSED_COPPER_TRAPDOOR_NAME = "minecraft:tile.exposed_copper_trapdoor.name";

    /**
     * Exposed Cut Copper
     */
    String MC_TILE_EXPOSED_CUT_COPPER_NAME = "minecraft:tile.exposed_cut_copper.name";

    /**
     * Exposed Cut Copper Slab
     */
    String MC_TILE_EXPOSED_CUT_COPPER_SLAB_NAME = "minecraft:tile.exposed_cut_copper_slab.name";

    /**
     * Exposed Cut Copper Stairs
     */
    String MC_TILE_EXPOSED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.exposed_cut_copper_stairs.name";

    /**
     * Exposed Lightning Rod
     */
    String MC_TILE_EXPOSED_LIGHTNING_ROD_NAME = "minecraft:tile.exposed_lightning_rod.name";

    /**
     * Farmland
     */
    String MC_TILE_FARMLAND_NAME = "minecraft:tile.farmland.name";

    /**
     * Oak Fence
     */
    String MC_TILE_FENCE_NAME = "minecraft:tile.fence.name";

    /**
     * Oak Fence Gate
     */
    String MC_TILE_FENCE_GATE_NAME = "minecraft:tile.fence_gate.name";

    /**
     * Fire
     */
    String MC_TILE_FIRE_NAME = "minecraft:tile.fire.name";

    /**
     * Firefly Bush
     */
    String MC_TILE_FIREFLY_BUSH_NAME = "minecraft:tile.firefly_bush.name";

    /**
     * Fletching Table
     */
    String MC_TILE_FLETCHING_TABLE_NAME = "minecraft:tile.fletching_table.name";

    /**
     * Flowering Azalea
     */
    String MC_TILE_FLOWERING_AZALEA_NAME = "minecraft:tile.flowering_azalea.name";

    /**
     * Lava
     */
    String MC_TILE_FLOWING_LAVA_NAME = "minecraft:tile.flowing_lava.name";

    /**
     * Water
     */
    String MC_TILE_FLOWING_WATER_NAME = "minecraft:tile.flowing_water.name";

    /**
     * Frogspawn
     */
    String MC_TILE_FROG_SPAWN_NAME = "minecraft:tile.frog_spawn.name";

    /**
     * Frosted Ice
     */
    String MC_TILE_FROSTED_ICE_NAME = "minecraft:tile.frosted_ice.name";

    /**
     * Furnace
     */
    String MC_TILE_FURNACE_NAME = "minecraft:tile.furnace.name";

    /**
     * Gilded Blackstone
     */
    String MC_TILE_GILDED_BLACKSTONE_NAME = "minecraft:tile.gilded_blackstone.name";

    /**
     * Glass
     */
    String MC_TILE_GLASS_NAME = "minecraft:tile.glass.name";

    /**
     * Glass Pane
     */
    String MC_TILE_GLASS_PANE_NAME = "minecraft:tile.glass_pane.name";

    /**
     * Black Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_BLACK_NAME = "minecraft:tile.glazedTerracotta.black.name";

    /**
     * Blue Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_BLUE_NAME = "minecraft:tile.glazedTerracotta.blue.name";

    /**
     * Brown Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_BROWN_NAME = "minecraft:tile.glazedTerracotta.brown.name";

    /**
     * Cyan Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_CYAN_NAME = "minecraft:tile.glazedTerracotta.cyan.name";

    /**
     * Gray Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_GRAY_NAME = "minecraft:tile.glazedTerracotta.gray.name";

    /**
     * Green Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_GREEN_NAME = "minecraft:tile.glazedTerracotta.green.name";

    /**
     * Light Blue Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_LIGHT_BLUE_NAME = "minecraft:tile.glazedTerracotta.light_blue.name";

    /**
     * Lime Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_LIME_NAME = "minecraft:tile.glazedTerracotta.lime.name";

    /**
     * Magenta Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_MAGENTA_NAME = "minecraft:tile.glazedTerracotta.magenta.name";

    /**
     * Orange Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_ORANGE_NAME = "minecraft:tile.glazedTerracotta.orange.name";

    /**
     * Pink Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_PINK_NAME = "minecraft:tile.glazedTerracotta.pink.name";

    /**
     * Purple Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_PURPLE_NAME = "minecraft:tile.glazedTerracotta.purple.name";

    /**
     * Red Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_RED_NAME = "minecraft:tile.glazedTerracotta.red.name";

    /**
     * Light Gray Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_SILVER_NAME = "minecraft:tile.glazedTerracotta.silver.name";

    /**
     * White Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_WHITE_NAME = "minecraft:tile.glazedTerracotta.white.name";

    /**
     * Yellow Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTA_YELLOW_NAME = "minecraft:tile.glazedTerracotta.yellow.name";

    /**
     * Black Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTABLACK_NAME = "minecraft:tile.glazedTerracottaBlack.name";

    /**
     * Blue Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTABLUE_NAME = "minecraft:tile.glazedTerracottaBlue.name";

    /**
     * Brown Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTABROWN_NAME = "minecraft:tile.glazedTerracottaBrown.name";

    /**
     * Cyan Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTACYAN_NAME = "minecraft:tile.glazedTerracottaCyan.name";

    /**
     * Gray Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAGRAY_NAME = "minecraft:tile.glazedTerracottaGray.name";

    /**
     * Green Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAGREEN_NAME = "minecraft:tile.glazedTerracottaGreen.name";

    /**
     * Light Blue Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTALIGHTBLUE_NAME = "minecraft:tile.glazedTerracottaLightBlue.name";

    /**
     * Lime Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTALIME_NAME = "minecraft:tile.glazedTerracottaLime.name";

    /**
     * Magenta Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAMAGENTA_NAME = "minecraft:tile.glazedTerracottaMagenta.name";

    /**
     * Orange Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAORANGE_NAME = "minecraft:tile.glazedTerracottaOrange.name";

    /**
     * Pink Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAPINK_NAME = "minecraft:tile.glazedTerracottaPink.name";

    /**
     * Purple Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAPURPLE_NAME = "minecraft:tile.glazedTerracottaPurple.name";

    /**
     * Red Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTARED_NAME = "minecraft:tile.glazedTerracottaRed.name";

    /**
     * Light Gray Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTASILVER_NAME = "minecraft:tile.glazedTerracottaSilver.name";

    /**
     * White Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAWHITE_NAME = "minecraft:tile.glazedTerracottaWhite.name";

    /**
     * Yellow Glazed Terracotta
     */
    String MC_TILE_GLAZEDTERRACOTTAYELLOW_NAME = "minecraft:tile.glazedTerracottaYellow.name";

    /**
     * Glow Lichen
     */
    String MC_TILE_GLOW_LICHEN_NAME = "minecraft:tile.glow_lichen.name";

    /**
     * Glowstone
     */
    String MC_TILE_GLOWSTONE_NAME = "minecraft:tile.glowstone.name";

    /**
     * Block of Gold
     */
    String MC_TILE_GOLD_BLOCK_NAME = "minecraft:tile.gold_block.name";

    /**
     * Gold Ore
     */
    String MC_TILE_GOLD_ORE_NAME = "minecraft:tile.gold_ore.name";

    /**
     * Powered Rail
     */
    String MC_TILE_GOLDEN_RAIL_NAME = "minecraft:tile.golden_rail.name";

    /**
     * Granite Stairs
     */
    String MC_TILE_GRANITE_STAIRS_NAME = "minecraft:tile.granite_stairs.name";

    /**
     * Grass Block
     */
    String MC_TILE_GRASS_NAME = "minecraft:tile.grass.name";

    /**
     * Dirt Path
     */
    String MC_TILE_GRASS_PATH_NAME = "minecraft:tile.grass_path.name";

    /**
     * Gravel
     */
    String MC_TILE_GRAVEL_NAME = "minecraft:tile.gravel.name";

    /**
     * Gray Candle
     */
    String MC_TILE_GRAY_CANDLE_NAME = "minecraft:tile.gray_candle.name";

    /**
     * Cake with Gray Candle
     */
    String MC_TILE_GRAY_CANDLE_CAKE_NAME = "minecraft:tile.gray_candle_cake.name";

    /**
     * Green Candle
     */
    String MC_TILE_GREEN_CANDLE_NAME = "minecraft:tile.green_candle.name";

    /**
     * Cake with Green Candle
     */
    String MC_TILE_GREEN_CANDLE_CAKE_NAME = "minecraft:tile.green_candle_cake.name";

    /**
     * Grindstone
     */
    String MC_TILE_GRINDSTONE_NAME = "minecraft:tile.grindstone.name";

    /**
     * Hanging Roots
     */
    String MC_TILE_HANGING_ROOTS_NAME = "minecraft:tile.hanging_roots.name";

    /**
     * Terracotta
     */
    String MC_TILE_HARDENED_CLAY_NAME = "minecraft:tile.hardened_clay.name";

    /**
     * Hay Bale
     */
    String MC_TILE_HAY_BLOCK_NAME = "minecraft:tile.hay_block.name";

    /**
     * Heavy Core
     */
    String MC_TILE_HEAVY_CORE_NAME = "minecraft:tile.heavy_core.name";

    /**
     * Heavy Weighted Pressure Plate
     */
    String MC_TILE_HEAVY_WEIGHTED_PRESSURE_PLATE_NAME = "minecraft:tile.heavy_weighted_pressure_plate.name";

    /**
     * Honey Block
     */
    String MC_TILE_HONEY_BLOCK_NAME = "minecraft:tile.honey_block.name";

    /**
     * Honeycomb Block
     */
    String MC_TILE_HONEYCOMB_BLOCK_NAME = "minecraft:tile.honeycomb_block.name";

    /**
     * Hopper
     */
    String MC_TILE_HOPPER_NAME = "minecraft:tile.hopper.name";

    /**
     * Ice
     */
    String MC_TILE_ICE_NAME = "minecraft:tile.ice.name";

    /**
     * Infested Deepslate
     */
    String MC_TILE_INFESTED_DEEPSLATE_NAME = "minecraft:tile.infested_deepslate.name";

    /**
     * Invisible Bedrock
     */
    String MC_TILE_INVISIBLEBEDROCK_NAME = "minecraft:tile.invisibleBedrock.name";

    /**
     * Iron Bars
     */
    String MC_TILE_IRON_BARS_NAME = "minecraft:tile.iron_bars.name";

    /**
     * Block of Iron
     */
    String MC_TILE_IRON_BLOCK_NAME = "minecraft:tile.iron_block.name";

    /**
     * Iron Chain
     */
    String MC_TILE_IRON_CHAIN_NAME = "minecraft:tile.iron_chain.name";

    /**
     * Iron Door
     */
    String MC_TILE_IRON_DOOR_NAME = "minecraft:tile.iron_door.name";

    /**
     * Iron Ore
     */
    String MC_TILE_IRON_ORE_NAME = "minecraft:tile.iron_ore.name";

    /**
     * Iron Trapdoor
     */
    String MC_TILE_IRON_TRAPDOOR_NAME = "minecraft:tile.iron_trapdoor.name";

    /**
     * Jigsaw Block
     */
    String MC_TILE_JIGSAW_NAME = "minecraft:tile.jigsaw.name";

    /**
     * Jukebox
     */
    String MC_TILE_JUKEBOX_NAME = "minecraft:tile.jukebox.name";

    /**
     * Jungle Fence
     */
    String MC_TILE_JUNGLEFENCE_NAME = "minecraft:tile.jungleFence.name";

    /**
     * Jungle Button
     */
    String MC_TILE_JUNGLE_BUTTON_NAME = "minecraft:tile.jungle_button.name";

    /**
     * Jungle Fence Gate
     */
    String MC_TILE_JUNGLE_FENCE_GATE_NAME = "minecraft:tile.jungle_fence_gate.name";

    /**
     * Jungle Pressure Plate
     */
    String MC_TILE_JUNGLE_PRESSURE_PLATE_NAME = "minecraft:tile.jungle_pressure_plate.name";

    /**
     * Jungle Shelf
     */
    String MC_TILE_JUNGLE_SHELF_NAME = "minecraft:tile.jungle_shelf.name";

    /**
     * Jungle Stairs
     */
    String MC_TILE_JUNGLE_STAIRS_NAME = "minecraft:tile.jungle_stairs.name";

    /**
     * Jungle Sign
     */
    String MC_TILE_JUNGLE_STANDING_SIGN_NAME = "minecraft:tile.jungle_standing_sign.name";

    /**
     * Jungle Trapdoor
     */
    String MC_TILE_JUNGLE_TRAPDOOR_NAME = "minecraft:tile.jungle_trapdoor.name";

    /**
     * Ladder
     */
    String MC_TILE_LADDER_NAME = "minecraft:tile.ladder.name";

    /**
     * Lantern
     */
    String MC_TILE_LANTERN_NAME = "minecraft:tile.lantern.name";

    /**
     * Block of Lapis Lazuli
     */
    String MC_TILE_LAPIS_BLOCK_NAME = "minecraft:tile.lapis_block.name";

    /**
     * Lapis Lazuli Ore
     */
    String MC_TILE_LAPIS_ORE_NAME = "minecraft:tile.lapis_ore.name";

    /**
     * Large Amethyst Bud
     */
    String MC_TILE_LARGE_AMETHYST_BUD_NAME = "minecraft:tile.large_amethyst_bud.name";

    /**
     * Lava
     */
    String MC_TILE_LAVA_NAME = "minecraft:tile.lava.name";

    /**
     * Leaf Litter
     */
    String MC_TILE_LEAF_LITTER_NAME = "minecraft:tile.leaf_litter.name";

    /**
     * Acacia Leaves
     */
    String MC_TILE_LEAVES_ACACIA_NAME = "minecraft:tile.leaves.acacia.name";

    /**
     * Dark Oak Leaves
     */
    String MC_TILE_LEAVES_BIG_OAK_NAME = "minecraft:tile.leaves.big_oak.name";

    /**
     * Birch Leaves
     */
    String MC_TILE_LEAVES_BIRCH_NAME = "minecraft:tile.leaves.birch.name";

    /**
     * Jungle Leaves
     */
    String MC_TILE_LEAVES_JUNGLE_NAME = "minecraft:tile.leaves.jungle.name";

    /**
     * Leaves
     */
    String MC_TILE_LEAVES_NAME = "minecraft:tile.leaves.name";

    /**
     * Oak Leaves
     */
    String MC_TILE_LEAVES_OAK_NAME = "minecraft:tile.leaves.oak.name";

    /**
     * Spruce Leaves
     */
    String MC_TILE_LEAVES_SPRUCE_NAME = "minecraft:tile.leaves.spruce.name";

    /**
     * Acacia Leaves
     */
    String MC_TILE_LEAVES2_ACACIA_NAME = "minecraft:tile.leaves2.acacia.name";

    /**
     * Dark Oak Leaves
     */
    String MC_TILE_LEAVES2_BIG_OAK_NAME = "minecraft:tile.leaves2.big_oak.name";

    /**
     * Lectern
     */
    String MC_TILE_LECTERN_NAME = "minecraft:tile.lectern.name";

    /**
     * Lever
     */
    String MC_TILE_LEVER_NAME = "minecraft:tile.lever.name";

    /**
     * Light
     */
    String MC_TILE_LIGHT_BLOCK_NAME = "minecraft:tile.light_block.name";

    /**
     * Light Blue Candle
     */
    String MC_TILE_LIGHT_BLUE_CANDLE_NAME = "minecraft:tile.light_blue_candle.name";

    /**
     * Cake with Light Blue Candle
     */
    String MC_TILE_LIGHT_BLUE_CANDLE_CAKE_NAME = "minecraft:tile.light_blue_candle_cake.name";

    /**
     * Light Gray Candle
     */
    String MC_TILE_LIGHT_GRAY_CANDLE_NAME = "minecraft:tile.light_gray_candle.name";

    /**
     * Cake with Light Gray Candle
     */
    String MC_TILE_LIGHT_GRAY_CANDLE_CAKE_NAME = "minecraft:tile.light_gray_candle_cake.name";

    /**
     * Light Weighted Pressure Plate
     */
    String MC_TILE_LIGHT_WEIGHTED_PRESSURE_PLATE_NAME = "minecraft:tile.light_weighted_pressure_plate.name";

    /**
     * Lightning Rod
     */
    String MC_TILE_LIGHTNING_ROD_NAME = "minecraft:tile.lightning_rod.name";

    /**
     * Lime Candle
     */
    String MC_TILE_LIME_CANDLE_NAME = "minecraft:tile.lime_candle.name";

    /**
     * Cake with Lime Candle
     */
    String MC_TILE_LIME_CANDLE_CAKE_NAME = "minecraft:tile.lime_candle_cake.name";

    /**
     * Jack o'Lantern
     */
    String MC_TILE_LIT_PUMPKIN_NAME = "minecraft:tile.lit_pumpkin.name";

    /**
     * Locked chest
     */
    String MC_TILE_LOCKEDCHEST_NAME = "minecraft:tile.lockedchest.name";

    /**
     * Lodestone
     */
    String MC_TILE_LODESTONE_NAME = "minecraft:tile.lodestone.name";

    /**
     * Acacia Log
     */
    String MC_TILE_LOG_ACACIA_NAME = "minecraft:tile.log.acacia.name";

    /**
     * Dark Oak Log
     */
    String MC_TILE_LOG_BIG_OAK_NAME = "minecraft:tile.log.big_oak.name";

    /**
     * Birch Log
     */
    String MC_TILE_LOG_BIRCH_NAME = "minecraft:tile.log.birch.name";

    /**
     * Jungle Log
     */
    String MC_TILE_LOG_JUNGLE_NAME = "minecraft:tile.log.jungle.name";

    /**
     * Log
     */
    String MC_TILE_LOG_NAME = "minecraft:tile.log.name";

    /**
     * Oak Log
     */
    String MC_TILE_LOG_OAK_NAME = "minecraft:tile.log.oak.name";

    /**
     * Spruce Log
     */
    String MC_TILE_LOG_SPRUCE_NAME = "minecraft:tile.log.spruce.name";

    /**
     * Loom
     */
    String MC_TILE_LOOM_NAME = "minecraft:tile.loom.name";

    /**
     * Magenta Candle
     */
    String MC_TILE_MAGENTA_CANDLE_NAME = "minecraft:tile.magenta_candle.name";

    /**
     * Cake with Magenta Candle
     */
    String MC_TILE_MAGENTA_CANDLE_CAKE_NAME = "minecraft:tile.magenta_candle_cake.name";

    /**
     * Magma Block
     */
    String MC_TILE_MAGMA_NAME = "minecraft:tile.magma.name";

    /**
     * Mangrove Button
     */
    String MC_TILE_MANGROVE_BUTTON_NAME = "minecraft:tile.mangrove_button.name";

    /**
     * Mangrove Fence
     */
    String MC_TILE_MANGROVE_FENCE_NAME = "minecraft:tile.mangrove_fence.name";

    /**
     * Mangrove Fence Gate
     */
    String MC_TILE_MANGROVE_FENCE_GATE_NAME = "minecraft:tile.mangrove_fence_gate.name";

    /**
     * Mangrove Leaves
     */
    String MC_TILE_MANGROVE_LEAVES_NAME = "minecraft:tile.mangrove_leaves.name";

    /**
     * Mangrove Log
     */
    String MC_TILE_MANGROVE_LOG_NAME = "minecraft:tile.mangrove_log.name";

    /**
     * Mangrove Planks
     */
    String MC_TILE_MANGROVE_PLANKS_NAME = "minecraft:tile.mangrove_planks.name";

    /**
     * Mangrove Pressure Plate
     */
    String MC_TILE_MANGROVE_PRESSURE_PLATE_NAME = "minecraft:tile.mangrove_pressure_plate.name";

    /**
     * Mangrove Propagule
     */
    String MC_TILE_MANGROVE_PROPAGULE_NAME = "minecraft:tile.mangrove_propagule.name";

    /**
     * Mangrove Roots
     */
    String MC_TILE_MANGROVE_ROOTS_NAME = "minecraft:tile.mangrove_roots.name";

    /**
     * Mangrove Shelf
     */
    String MC_TILE_MANGROVE_SHELF_NAME = "minecraft:tile.mangrove_shelf.name";

    /**
     * Mangrove Slab
     */
    String MC_TILE_MANGROVE_SLAB_NAME = "minecraft:tile.mangrove_slab.name";

    /**
     * Mangrove Stairs
     */
    String MC_TILE_MANGROVE_STAIRS_NAME = "minecraft:tile.mangrove_stairs.name";

    /**
     * Mangrove Trapdoor
     */
    String MC_TILE_MANGROVE_TRAPDOOR_NAME = "minecraft:tile.mangrove_trapdoor.name";

    /**
     * Mangrove Wood
     */
    String MC_TILE_MANGROVE_WOOD_NAME = "minecraft:tile.mangrove_wood.name";

    /**
     * Medium Amethyst Bud
     */
    String MC_TILE_MEDIUM_AMETHYST_BUD_NAME = "minecraft:tile.medium_amethyst_bud.name";

    /**
     * Melon
     */
    String MC_TILE_MELON_BLOCK_NAME = "minecraft:tile.melon_block.name";

    /**
     * Monster Spawner
     */
    String MC_TILE_MOB_SPAWNER_NAME = "minecraft:tile.mob_spawner.name";

    /**
     * Infested Stone Bricks
     */
    String MC_TILE_MONSTER_EGG_BRICK_NAME = "minecraft:tile.monster_egg.brick.name";

    /**
     * Infested Chiseled Stone Brick
     */
    String MC_TILE_MONSTER_EGG_CHISELEDBRICK_NAME = "minecraft:tile.monster_egg.chiseledbrick.name";

    /**
     * Infested Cobblestone
     */
    String MC_TILE_MONSTER_EGG_COBBLE_NAME = "minecraft:tile.monster_egg.cobble.name";

    /**
     * Infested Cracked Stone Brick
     */
    String MC_TILE_MONSTER_EGG_CRACKEDBRICK_NAME = "minecraft:tile.monster_egg.crackedbrick.name";

    /**
     * Infested Mossy Stone Brick
     */
    String MC_TILE_MONSTER_EGG_MOSSYBRICK_NAME = "minecraft:tile.monster_egg.mossybrick.name";

    /**
     * Infested Stone
     */
    String MC_TILE_MONSTER_EGG_NAME = "minecraft:tile.monster_egg.name";

    /**
     * Infested Stone
     */
    String MC_TILE_MONSTER_EGG_STONE_NAME = "minecraft:tile.monster_egg.stone.name";

    /**
     * Moss Block
     */
    String MC_TILE_MOSS_BLOCK_NAME = "minecraft:tile.moss_block.name";

    /**
     * Moss Carpet
     */
    String MC_TILE_MOSS_CARPET_NAME = "minecraft:tile.moss_carpet.name";

    /**
     * Mossy Cobblestone
     */
    String MC_TILE_MOSSY_COBBLESTONE_NAME = "minecraft:tile.mossy_cobblestone.name";

    /**
     * Mossy Cobblestone Stairs
     */
    String MC_TILE_MOSSY_COBBLESTONE_STAIRS_NAME = "minecraft:tile.mossy_cobblestone_stairs.name";

    /**
     * Mossy Stone Brick Stairs
     */
    String MC_TILE_MOSSY_STONE_BRICK_STAIRS_NAME = "minecraft:tile.mossy_stone_brick_stairs.name";

    /**
     * Mud
     */
    String MC_TILE_MUD_NAME = "minecraft:tile.mud.name";

    /**
     * Mud Brick Double Slab
     */
    String MC_TILE_MUD_BRICK_DOUBLE_SLAB = "minecraft:tile.mud_brick_double_slab";

    /**
     * Mud Brick Slab
     */
    String MC_TILE_MUD_BRICK_SLAB_NAME = "minecraft:tile.mud_brick_slab.name";

    /**
     * Mud Brick Stairs
     */
    String MC_TILE_MUD_BRICK_STAIRS_NAME = "minecraft:tile.mud_brick_stairs.name";

    /**
     * Mud Brick Wall
     */
    String MC_TILE_MUD_BRICK_WALL_NAME = "minecraft:tile.mud_brick_wall.name";

    /**
     * Mud Bricks
     */
    String MC_TILE_MUD_BRICKS_NAME = "minecraft:tile.mud_bricks.name";

    /**
     * Muddy Mangrove Roots
     */
    String MC_TILE_MUDDY_MANGROVE_ROOTS_NAME = "minecraft:tile.muddy_mangrove_roots.name";

    /**
     * Mushroom
     */
    String MC_TILE_MUSHROOM_NAME = "minecraft:tile.mushroom.name";

    /**
     * Mycelium
     */
    String MC_TILE_MYCELIUM_NAME = "minecraft:tile.mycelium.name";

    /**
     * Nether Bricks
     */
    String MC_TILE_NETHER_BRICK_NAME = "minecraft:tile.nether_brick.name";

    /**
     * Nether Brick Fence
     */
    String MC_TILE_NETHER_BRICK_FENCE_NAME = "minecraft:tile.nether_brick_fence.name";

    /**
     * Nether Brick Stairs
     */
    String MC_TILE_NETHER_BRICK_STAIRS_NAME = "minecraft:tile.nether_brick_stairs.name";

    /**
     * Nether Gold Ore
     */
    String MC_TILE_NETHER_GOLD_ORE_NAME = "minecraft:tile.nether_gold_ore.name";

    /**
     * Nether Sprouts
     */
    String MC_TILE_NETHER_SPROUTS_NAME = "minecraft:tile.nether_sprouts.name";

    /**
     * Nether Wart
     */
    String MC_TILE_NETHER_WART_NAME = "minecraft:tile.nether_wart.name";

    /**
     * Nether Wart Block
     */
    String MC_TILE_NETHER_WART_BLOCK_NAME = "minecraft:tile.nether_wart_block.name";

    /**
     * Block of Netherite
     */
    String MC_TILE_NETHERITE_BLOCK_NAME = "minecraft:tile.netherite_block.name";

    /**
     * Netherrack
     */
    String MC_TILE_NETHERRACK_NAME = "minecraft:tile.netherrack.name";

    /**
     * Active!
     */
    String MC_TILE_NETHERREACTOR_ACTIVE = "minecraft:tile.netherreactor.active";

    /**
     * The nether reactor needs to be built lower down.
     */
    String MC_TILE_NETHERREACTOR_BUILTTOOHIGH = "minecraft:tile.netherreactor.builtTooHigh";

    /**
     * The nether reactor needs to be built higher up.
     */
    String MC_TILE_NETHERREACTOR_BUILTTOOLOW = "minecraft:tile.netherreactor.builtTooLow";

    /**
     * Nether Reactor Core
     */
    String MC_TILE_NETHERREACTOR_NAME = "minecraft:tile.netherreactor.name";

    /**
     * All players need to be close to the reactor.
     */
    String MC_TILE_NETHERREACTOR_PLAYERSTOOFAR = "minecraft:tile.netherreactor.playersTooFar";

    /**
     * Not the correct pattern!
     */
    String MC_TILE_NETHERREACTOR_WRONGPATTERN = "minecraft:tile.netherreactor.wrongPattern";

    /**
     * Stone Stairs
     */
    String MC_TILE_NORMAL_STONE_STAIRS_NAME = "minecraft:tile.normal_stone_stairs.name";

    /**
     * Note Block
     */
    String MC_TILE_NOTEBLOCK_NAME = "minecraft:tile.noteblock.name";

    /**
     * Oak Shelf
     */
    String MC_TILE_OAK_SHELF_NAME = "minecraft:tile.oak_shelf.name";

    /**
     * Oak Stairs
     */
    String MC_TILE_OAK_STAIRS_NAME = "minecraft:tile.oak_stairs.name";

    /**
     * Observer
     */
    String MC_TILE_OBSERVER_NAME = "minecraft:tile.observer.name";

    /**
     * Obsidian
     */
    String MC_TILE_OBSIDIAN_NAME = "minecraft:tile.obsidian.name";

    /**
     * Ochre Froglight
     */
    String MC_TILE_OCHRE_FROGLIGHT_NAME = "minecraft:tile.ochre_froglight.name";

    /**
     * Open Eyeblossom
     */
    String MC_TILE_OPEN_EYEBLOSSOM_NAME = "minecraft:tile.open_eyeblossom.name";

    /**
     * Orange Candle
     */
    String MC_TILE_ORANGE_CANDLE_NAME = "minecraft:tile.orange_candle.name";

    /**
     * Cake with Orange Candle
     */
    String MC_TILE_ORANGE_CANDLE_CAKE_NAME = "minecraft:tile.orange_candle_cake.name";

    /**
     * Ruby Ore
     */
    String MC_TILE_ORERUBY_NAME = "minecraft:tile.oreRuby.name";

    /**
     * Oxidized Chiseled Copper
     */
    String MC_TILE_OXIDIZED_CHISELED_COPPER_NAME = "minecraft:tile.oxidized_chiseled_copper.name";

    /**
     * Oxidized Copper
     */
    String MC_TILE_OXIDIZED_COPPER_NAME = "minecraft:tile.oxidized_copper.name";

    /**
     * Oxidized Copper Bars
     */
    String MC_TILE_OXIDIZED_COPPER_BARS_NAME = "minecraft:tile.oxidized_copper_bars.name";

    /**
     * Oxidized Copper Bulb
     */
    String MC_TILE_OXIDIZED_COPPER_BULB_NAME = "minecraft:tile.oxidized_copper_bulb.name";

    /**
     * Oxidized Copper Chain
     */
    String MC_TILE_OXIDIZED_COPPER_CHAIN_NAME = "minecraft:tile.oxidized_copper_chain.name";

    /**
     * Oxidized Copper Chest
     */
    String MC_TILE_OXIDIZED_COPPER_CHEST_NAME = "minecraft:tile.oxidized_copper_chest.name";

    /**
     * Oxidized Copper Door
     */
    String MC_TILE_OXIDIZED_COPPER_DOOR_NAME = "minecraft:tile.oxidized_copper_door.name";

    /**
     * Oxidized Copper Golem Statue
     */
    String MC_TILE_OXIDIZED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.oxidized_copper_golem_statue.name";

    /**
     * Oxidized Copper Grate
     */
    String MC_TILE_OXIDIZED_COPPER_GRATE_NAME = "minecraft:tile.oxidized_copper_grate.name";

    /**
     * Oxidized Copper Lantern
     */
    String MC_TILE_OXIDIZED_COPPER_LANTERN_NAME = "minecraft:tile.oxidized_copper_lantern.name";

    /**
     * Oxidized Copper Trapdoor
     */
    String MC_TILE_OXIDIZED_COPPER_TRAPDOOR_NAME = "minecraft:tile.oxidized_copper_trapdoor.name";

    /**
     * Oxidized Cut Copper
     */
    String MC_TILE_OXIDIZED_CUT_COPPER_NAME = "minecraft:tile.oxidized_cut_copper.name";

    /**
     * Oxidized Cut Copper Slab
     */
    String MC_TILE_OXIDIZED_CUT_COPPER_SLAB_NAME = "minecraft:tile.oxidized_cut_copper_slab.name";

    /**
     * Oxidized Cut Copper Stairs
     */
    String MC_TILE_OXIDIZED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.oxidized_cut_copper_stairs.name";

    /**
     * Oxidized Lightning Rod
     */
    String MC_TILE_OXIDIZED_LIGHTNING_ROD_NAME = "minecraft:tile.oxidized_lightning_rod.name";

    /**
     * Packed Ice
     */
    String MC_TILE_PACKED_ICE_NAME = "minecraft:tile.packed_ice.name";

    /**
     * Packed Mud
     */
    String MC_TILE_PACKED_MUD_NAME = "minecraft:tile.packed_mud.name";

    /**
     * Pale Hanging Moss
     */
    String MC_TILE_PALE_HANGING_MOSS_NAME = "minecraft:tile.pale_hanging_moss.name";

    /**
     * Pale Moss Block
     */
    String MC_TILE_PALE_MOSS_BLOCK_NAME = "minecraft:tile.pale_moss_block.name";

    /**
     * Pale Moss Carpet
     */
    String MC_TILE_PALE_MOSS_CARPET_NAME = "minecraft:tile.pale_moss_carpet.name";

    /**
     * Pale Oak Button
     */
    String MC_TILE_PALE_OAK_BUTTON_NAME = "minecraft:tile.pale_oak_button.name";

    /**
     * Pale Oak Double Slab
     */
    String MC_TILE_PALE_OAK_DOUBLE_SLAB_NAME = "minecraft:tile.pale_oak_double_slab.name";

    /**
     * Pale Oak Fence
     */
    String MC_TILE_PALE_OAK_FENCE_NAME = "minecraft:tile.pale_oak_fence.name";

    /**
     * Pale Oak Fence Gate
     */
    String MC_TILE_PALE_OAK_FENCE_GATE_NAME = "minecraft:tile.pale_oak_fence_gate.name";

    /**
     * Pale Oak Leaves
     */
    String MC_TILE_PALE_OAK_LEAVES_NAME = "minecraft:tile.pale_oak_leaves.name";

    /**
     * Pale Oak Log
     */
    String MC_TILE_PALE_OAK_LOG_NAME = "minecraft:tile.pale_oak_log.name";

    /**
     * Pale Oak Planks
     */
    String MC_TILE_PALE_OAK_PLANKS_NAME = "minecraft:tile.pale_oak_planks.name";

    /**
     * Pale Oak Pressure Plate
     */
    String MC_TILE_PALE_OAK_PRESSURE_PLATE_NAME = "minecraft:tile.pale_oak_pressure_plate.name";

    /**
     * Pale Oak Sapling
     */
    String MC_TILE_PALE_OAK_SAPLING_NAME = "minecraft:tile.pale_oak_sapling.name";

    /**
     * Pale Oak Shelf
     */
    String MC_TILE_PALE_OAK_SHELF_NAME = "minecraft:tile.pale_oak_shelf.name";

    /**
     * Pale Oak Slab
     */
    String MC_TILE_PALE_OAK_SLAB_NAME = "minecraft:tile.pale_oak_slab.name";

    /**
     * Pale Oak Stairs
     */
    String MC_TILE_PALE_OAK_STAIRS_NAME = "minecraft:tile.pale_oak_stairs.name";

    /**
     * Pale Oak Trapdoor
     */
    String MC_TILE_PALE_OAK_TRAPDOOR_NAME = "minecraft:tile.pale_oak_trapdoor.name";

    /**
     * Pale Oak Wood
     */
    String MC_TILE_PALE_OAK_WOOD_NAME = "minecraft:tile.pale_oak_wood.name";

    /**
     * Pearlescent Froglight
     */
    String MC_TILE_PEARLESCENT_FROGLIGHT_NAME = "minecraft:tile.pearlescent_froglight.name";

    /**
     * Pink Candle
     */
    String MC_TILE_PINK_CANDLE_NAME = "minecraft:tile.pink_candle.name";

    /**
     * Cake with Pink Candle
     */
    String MC_TILE_PINK_CANDLE_CAKE_NAME = "minecraft:tile.pink_candle_cake.name";

    /**
     * Pink Petals
     */
    String MC_TILE_PINK_PETALS_NAME = "minecraft:tile.pink_petals.name";

    /**
     * Piston
     */
    String MC_TILE_PISTON_NAME = "minecraft:tile.piston.name";

    /**
     * Pitcher Plant
     */
    String MC_TILE_PITCHER_PLANT_NAME = "minecraft:tile.pitcher_plant.name";

    /**
     * Acacia Planks
     */
    String MC_TILE_PLANKS_ACACIA_NAME = "minecraft:tile.planks.acacia.name";

    /**
     * Dark Oak Planks
     */
    String MC_TILE_PLANKS_BIG_OAK_NAME = "minecraft:tile.planks.big_oak.name";

    /**
     * Birch Planks
     */
    String MC_TILE_PLANKS_BIRCH_NAME = "minecraft:tile.planks.birch.name";

    /**
     * Jungle Planks
     */
    String MC_TILE_PLANKS_JUNGLE_NAME = "minecraft:tile.planks.jungle.name";

    /**
     * Wooden Planks
     */
    String MC_TILE_PLANKS_NAME = "minecraft:tile.planks.name";

    /**
     * Oak Planks
     */
    String MC_TILE_PLANKS_OAK_NAME = "minecraft:tile.planks.oak.name";

    /**
     * Spruce Planks
     */
    String MC_TILE_PLANKS_SPRUCE_NAME = "minecraft:tile.planks.spruce.name";

    /**
     * Podzol
     */
    String MC_TILE_PODZOL_NAME = "minecraft:tile.podzol.name";

    /**
     * Pointed Dripstone
     */
    String MC_TILE_POINTED_DRIPSTONE_NAME = "minecraft:tile.pointed_dripstone.name";

    /**
     * Polished Andesite Stairs
     */
    String MC_TILE_POLISHED_ANDESITE_STAIRS_NAME = "minecraft:tile.polished_andesite_stairs.name";

    /**
     * Polished Basalt
     */
    String MC_TILE_POLISHED_BASALT_NAME = "minecraft:tile.polished_basalt.name";

    /**
     * Polished Blackstone
     */
    String MC_TILE_POLISHED_BLACKSTONE_NAME = "minecraft:tile.polished_blackstone.name";

    /**
     * Polished Blackstone Brick Slab
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICK_SLAB_NAME = "minecraft:tile.polished_blackstone_brick_slab.name";

    /**
     * Polished Blackstone Brick Stairs
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICK_STAIRS_NAME = "minecraft:tile.polished_blackstone_brick_stairs.name";

    /**
     * Polished Blackstone Brick Wall
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICK_WALL_NAME = "minecraft:tile.polished_blackstone_brick_wall.name";

    /**
     * Polished Blackstone Bricks
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICKS_NAME = "minecraft:tile.polished_blackstone_bricks.name";

    /**
     * Polished Blackstone Button
     */
    String MC_TILE_POLISHED_BLACKSTONE_BUTTON_NAME = "minecraft:tile.polished_blackstone_button.name";

    /**
     * Polished Blackstone Pressure Plate
     */
    String MC_TILE_POLISHED_BLACKSTONE_PRESSURE_PLATE_NAME = "minecraft:tile.polished_blackstone_pressure_plate.name";

    /**
     * Polished Blackstone Slab
     */
    String MC_TILE_POLISHED_BLACKSTONE_SLAB_NAME = "minecraft:tile.polished_blackstone_slab.name";

    /**
     * Polished Blackstone Stairs
     */
    String MC_TILE_POLISHED_BLACKSTONE_STAIRS_NAME = "minecraft:tile.polished_blackstone_stairs.name";

    /**
     * Polished Blackstone Wall
     */
    String MC_TILE_POLISHED_BLACKSTONE_WALL_NAME = "minecraft:tile.polished_blackstone_wall.name";

    /**
     * Polished Deepslate
     */
    String MC_TILE_POLISHED_DEEPSLATE_NAME = "minecraft:tile.polished_deepslate.name";

    /**
     * Polished Deepslate Double Slab
     */
    String MC_TILE_POLISHED_DEEPSLATE_DOUBLE_SLAB_NAME = "minecraft:tile.polished_deepslate_double_slab.name";

    /**
     * Polished Deepslate Slab
     */
    String MC_TILE_POLISHED_DEEPSLATE_SLAB_NAME = "minecraft:tile.polished_deepslate_slab.name";

    /**
     * Polished Deepslate Stairs
     */
    String MC_TILE_POLISHED_DEEPSLATE_STAIRS_NAME = "minecraft:tile.polished_deepslate_stairs.name";

    /**
     * Polished Deepslate Wall
     */
    String MC_TILE_POLISHED_DEEPSLATE_WALL_NAME = "minecraft:tile.polished_deepslate_wall.name";

    /**
     * Polished Diorite Stairs
     */
    String MC_TILE_POLISHED_DIORITE_STAIRS_NAME = "minecraft:tile.polished_diorite_stairs.name";

    /**
     * Polished Granite Stairs
     */
    String MC_TILE_POLISHED_GRANITE_STAIRS_NAME = "minecraft:tile.polished_granite_stairs.name";

    /**
     * Polished Tuff
     */
    String MC_TILE_POLISHED_TUFF_NAME = "minecraft:tile.polished_tuff.name";

    /**
     * Polished Tuff Slab
     */
    String MC_TILE_POLISHED_TUFF_SLAB_NAME = "minecraft:tile.polished_tuff_slab.name";

    /**
     * Polished Tuff Stairs
     */
    String MC_TILE_POLISHED_TUFF_STAIRS_NAME = "minecraft:tile.polished_tuff_stairs.name";

    /**
     * Polished Tuff Wall
     */
    String MC_TILE_POLISHED_TUFF_WALL_NAME = "minecraft:tile.polished_tuff_wall.name";

    /**
     * Portal
     */
    String MC_TILE_PORTAL_NAME = "minecraft:tile.portal.name";

    /**
     * Potatoes
     */
    String MC_TILE_POTATOES_NAME = "minecraft:tile.potatoes.name";

    /**
     * Powder Snow
     */
    String MC_TILE_POWDER_SNOW_NAME = "minecraft:tile.powder_snow.name";

    /**
     * Prismarine Bricks
     */
    String MC_TILE_PRISMARINE_BRICKS_NAME = "minecraft:tile.prismarine.bricks.name";

    /**
     * Dark Prismarine
     */
    String MC_TILE_PRISMARINE_DARK_NAME = "minecraft:tile.prismarine.dark.name";

    /**
     * Prismarine
     */
    String MC_TILE_PRISMARINE_ROUGH_NAME = "minecraft:tile.prismarine.rough.name";

    /**
     * Prismarine Brick Stairs
     */
    String MC_TILE_PRISMARINE_BRICKS_STAIRS_NAME = "minecraft:tile.prismarine_bricks_stairs.name";

    /**
     * Prismarine Stairs
     */
    String MC_TILE_PRISMARINE_STAIRS_NAME = "minecraft:tile.prismarine_stairs.name";

    /**
     * Pumpkin
     */
    String MC_TILE_PUMPKIN_NAME = "minecraft:tile.pumpkin.name";

    /**
     * Pumpkin Stem
     */
    String MC_TILE_PUMPKIN_STEM_NAME = "minecraft:tile.pumpkin_stem.name";

    /**
     * Purple Candle
     */
    String MC_TILE_PURPLE_CANDLE_NAME = "minecraft:tile.purple_candle.name";

    /**
     * Cake with Purple Candle
     */
    String MC_TILE_PURPLE_CANDLE_CAKE_NAME = "minecraft:tile.purple_candle_cake.name";

    /**
     * Chiseled Purpur
     */
    String MC_TILE_PURPUR_BLOCK_CHISELED_NAME = "minecraft:tile.purpur_block.chiseled.name";

    /**
     * Purpur Block
     */
    String MC_TILE_PURPUR_BLOCK_DEFAULT_NAME = "minecraft:tile.purpur_block.default.name";

    /**
     * Purpur Pillar
     */
    String MC_TILE_PURPUR_BLOCK_LINES_NAME = "minecraft:tile.purpur_block.lines.name";

    /**
     * Purpur Stairs
     */
    String MC_TILE_PURPUR_STAIRS_NAME = "minecraft:tile.purpur_stairs.name";

    /**
     * Chiseled Quartz Block
     */
    String MC_TILE_QUARTZ_BLOCK_CHISELED_NAME = "minecraft:tile.quartz_block.chiseled.name";

    /**
     * Block of Quartz
     */
    String MC_TILE_QUARTZ_BLOCK_DEFAULT_NAME = "minecraft:tile.quartz_block.default.name";

    /**
     * Quartz Pillar
     */
    String MC_TILE_QUARTZ_BLOCK_LINES_NAME = "minecraft:tile.quartz_block.lines.name";

    /**
     * Block of Quartz
     */
    String MC_TILE_QUARTZ_BLOCK_NAME = "minecraft:tile.quartz_block.name";

    /**
     * Smooth Quartz Block
     */
    String MC_TILE_QUARTZ_BLOCK_SMOOTH_NAME = "minecraft:tile.quartz_block.smooth.name";

    /**
     * Quartz Bricks
     */
    String MC_TILE_QUARTZ_BRICKS_NAME = "minecraft:tile.quartz_bricks.name";

    /**
     * Nether Quartz Ore
     */
    String MC_TILE_QUARTZ_ORE_NAME = "minecraft:tile.quartz_ore.name";

    /**
     * Quartz Stairs
     */
    String MC_TILE_QUARTZ_STAIRS_NAME = "minecraft:tile.quartz_stairs.name";

    /**
     * Rail
     */
    String MC_TILE_RAIL_NAME = "minecraft:tile.rail.name";

    /**
     * Block of Raw Copper
     */
    String MC_TILE_RAW_COPPER_BLOCK_NAME = "minecraft:tile.raw_copper_block.name";

    /**
     * Block of Raw Gold
     */
    String MC_TILE_RAW_GOLD_BLOCK_NAME = "minecraft:tile.raw_gold_block.name";

    /**
     * Block of Raw Iron
     */
    String MC_TILE_RAW_IRON_BLOCK_NAME = "minecraft:tile.raw_iron_block.name";

    /**
     * Red Candle
     */
    String MC_TILE_RED_CANDLE_NAME = "minecraft:tile.red_candle.name";

    /**
     * Cake with Red Candle
     */
    String MC_TILE_RED_CANDLE_CAKE_NAME = "minecraft:tile.red_candle_cake.name";

    /**
     * Allium
     */
    String MC_TILE_RED_FLOWER_ALLIUM_NAME = "minecraft:tile.red_flower.allium.name";

    /**
     * Blue Orchid
     */
    String MC_TILE_RED_FLOWER_BLUEORCHID_NAME = "minecraft:tile.red_flower.blueOrchid.name";

    /**
     * Cornflower
     */
    String MC_TILE_RED_FLOWER_CORNFLOWER_NAME = "minecraft:tile.red_flower.cornflower.name";

    /**
     * Azure Bluet
     */
    String MC_TILE_RED_FLOWER_HOUSTONIA_NAME = "minecraft:tile.red_flower.houstonia.name";

    /**
     * Lily of the Valley
     */
    String MC_TILE_RED_FLOWER_LILYOFTHEVALLEY_NAME = "minecraft:tile.red_flower.lilyOfTheValley.name";

    /**
     * Flower
     */
    String MC_TILE_RED_FLOWER_NAME = "minecraft:tile.red_flower.name";

    /**
     * Oxeye Daisy
     */
    String MC_TILE_RED_FLOWER_OXEYEDAISY_NAME = "minecraft:tile.red_flower.oxeyeDaisy.name";

    /**
     * Poppy
     */
    String MC_TILE_RED_FLOWER_POPPY_NAME = "minecraft:tile.red_flower.poppy.name";

    /**
     * Orange Tulip
     */
    String MC_TILE_RED_FLOWER_TULIPORANGE_NAME = "minecraft:tile.red_flower.tulipOrange.name";

    /**
     * Pink Tulip
     */
    String MC_TILE_RED_FLOWER_TULIPPINK_NAME = "minecraft:tile.red_flower.tulipPink.name";

    /**
     * Red Tulip
     */
    String MC_TILE_RED_FLOWER_TULIPRED_NAME = "minecraft:tile.red_flower.tulipRed.name";

    /**
     * White Tulip
     */
    String MC_TILE_RED_FLOWER_TULIPWHITE_NAME = "minecraft:tile.red_flower.tulipWhite.name";

    /**
     * Red Mushroom
     */
    String MC_TILE_RED_MUSHROOM_NAME = "minecraft:tile.red_mushroom.name";

    /**
     * Red Mushroom Block
     */
    String MC_TILE_RED_MUSHROOM_BLOCK_NAME = "minecraft:tile.red_mushroom_block.name";

    /**
     * Red Nether Bricks
     */
    String MC_TILE_RED_NETHER_BRICK_NAME = "minecraft:tile.red_nether_brick.name";

    /**
     * Red Nether Brick Stairs
     */
    String MC_TILE_RED_NETHER_BRICK_STAIRS_NAME = "minecraft:tile.red_nether_brick_stairs.name";

    /**
     * Chiseled Red Sandstone
     */
    String MC_TILE_RED_SANDSTONE_CHISELED_NAME = "minecraft:tile.red_sandstone.chiseled.name";

    /**
     * Cut Red Sandstone
     */
    String MC_TILE_RED_SANDSTONE_CUT_NAME = "minecraft:tile.red_sandstone.cut.name";

    /**
     * Red Sandstone
     */
    String MC_TILE_RED_SANDSTONE_DEFAULT_NAME = "minecraft:tile.red_sandstone.default.name";

    /**
     * Red Sandstone
     */
    String MC_TILE_RED_SANDSTONE_NAME = "minecraft:tile.red_sandstone.name";

    /**
     * Smooth Red Sandstone
     */
    String MC_TILE_RED_SANDSTONE_SMOOTH_NAME = "minecraft:tile.red_sandstone.smooth.name";

    /**
     * Red Sandstone Stairs
     */
    String MC_TILE_RED_SANDSTONE_STAIRS_NAME = "minecraft:tile.red_sandstone_stairs.name";

    /**
     * Block of Redstone
     */
    String MC_TILE_REDSTONE_BLOCK_NAME = "minecraft:tile.redstone_block.name";

    /**
     * Redstone Lamp
     */
    String MC_TILE_REDSTONE_LAMP_NAME = "minecraft:tile.redstone_lamp.name";

    /**
     * Redstone Ore
     */
    String MC_TILE_REDSTONE_ORE_NAME = "minecraft:tile.redstone_ore.name";

    /**
     * Redstone Torch
     */
    String MC_TILE_REDSTONE_TORCH_NAME = "minecraft:tile.redstone_torch.name";

    /**
     * Redstone Dust
     */
    String MC_TILE_REDSTONE_WIRE_NAME = "minecraft:tile.redstone_wire.name";

    /**
     * Sugar cane
     */
    String MC_TILE_REEDS_NAME = "minecraft:tile.reeds.name";

    /**
     * Reinforced Deepslate
     */
    String MC_TILE_REINFORCED_DEEPSLATE_NAME = "minecraft:tile.reinforced_deepslate.name";

    /**
     * Repeating Command Block
     */
    String MC_TILE_REPEATING_COMMAND_BLOCK_NAME = "minecraft:tile.repeating_command_block.name";

    /**
     * Block of Resin
     */
    String MC_TILE_RESIN_BLOCK_NAME = "minecraft:tile.resin_block.name";

    /**
     * Resin Brick Double Slab
     */
    String MC_TILE_RESIN_BRICK_DOUBLE_SLAB_NAME = "minecraft:tile.resin_brick_double_slab.name";

    /**
     * Resin Brick Slab
     */
    String MC_TILE_RESIN_BRICK_SLAB_NAME = "minecraft:tile.resin_brick_slab.name";

    /**
     * Resin Brick Stairs
     */
    String MC_TILE_RESIN_BRICK_STAIRS_NAME = "minecraft:tile.resin_brick_stairs.name";

    /**
     * Resin Brick Wall
     */
    String MC_TILE_RESIN_BRICK_WALL_NAME = "minecraft:tile.resin_brick_wall.name";

    /**
     * Resin Bricks
     */
    String MC_TILE_RESIN_BRICKS_NAME = "minecraft:tile.resin_bricks.name";

    /**
     * Resin Clump
     */
    String MC_TILE_RESIN_CLUMP_NAME = "minecraft:tile.resin_clump.name";

    /**
     * Respawn Anchor
     */
    String MC_TILE_RESPAWN_ANCHOR_NAME = "minecraft:tile.respawn_anchor.name";

    /**
     * Your respawn anchor was out of charges, missing or obstructed
     */
    String MC_TILE_RESPAWN_ANCHOR_NOTVALID = "minecraft:tile.respawn_anchor.notValid";

    /**
     * Respawn point set
     */
    String MC_TILE_RESPAWN_ANCHOR_RESPAWNSET = "minecraft:tile.respawn_anchor.respawnSet";

    /**
     * Sand
     */
    String MC_TILE_SAND_DEFAULT_NAME = "minecraft:tile.sand.default.name";

    /**
     * Sand
     */
    String MC_TILE_SAND_NAME = "minecraft:tile.sand.name";

    /**
     * Red Sand
     */
    String MC_TILE_SAND_RED_NAME = "minecraft:tile.sand.red.name";

    /**
     * Chiseled Sandstone
     */
    String MC_TILE_SANDSTONE_CHISELED_NAME = "minecraft:tile.sandstone.chiseled.name";

    /**
     * Cut Sandstone
     */
    String MC_TILE_SANDSTONE_CUT_NAME = "minecraft:tile.sandstone.cut.name";

    /**
     * Sandstone
     */
    String MC_TILE_SANDSTONE_DEFAULT_NAME = "minecraft:tile.sandstone.default.name";

    /**
     * Sandstone
     */
    String MC_TILE_SANDSTONE_NAME = "minecraft:tile.sandstone.name";

    /**
     * Smooth Sandstone
     */
    String MC_TILE_SANDSTONE_SMOOTH_NAME = "minecraft:tile.sandstone.smooth.name";

    /**
     * Sandstone Stairs
     */
    String MC_TILE_SANDSTONE_STAIRS_NAME = "minecraft:tile.sandstone_stairs.name";

    /**
     * Acacia Sapling
     */
    String MC_TILE_SAPLING_ACACIA_NAME = "minecraft:tile.sapling.acacia.name";

    /**
     * Dark Oak Sapling
     */
    String MC_TILE_SAPLING_BIG_OAK_NAME = "minecraft:tile.sapling.big_oak.name";

    /**
     * Birch Sapling
     */
    String MC_TILE_SAPLING_BIRCH_NAME = "minecraft:tile.sapling.birch.name";

    /**
     * Jungle Sapling
     */
    String MC_TILE_SAPLING_JUNGLE_NAME = "minecraft:tile.sapling.jungle.name";

    /**
     * Oak Sapling
     */
    String MC_TILE_SAPLING_OAK_NAME = "minecraft:tile.sapling.oak.name";

    /**
     * Spruce Sapling
     */
    String MC_TILE_SAPLING_SPRUCE_NAME = "minecraft:tile.sapling.spruce.name";

    /**
     * Scaffolding
     */
    String MC_TILE_SCAFFOLDING_NAME = "minecraft:tile.scaffolding.name";

    /**
     * Sculk
     */
    String MC_TILE_SCULK_NAME = "minecraft:tile.sculk.name";

    /**
     * Sculk Catalyst
     */
    String MC_TILE_SCULK_CATALYST_NAME = "minecraft:tile.sculk_catalyst.name";

    /**
     * Sculk Sensor
     */
    String MC_TILE_SCULK_SENSOR_NAME = "minecraft:tile.sculk_sensor.name";

    /**
     * Sculk Shrieker
     */
    String MC_TILE_SCULK_SHRIEKER_NAME = "minecraft:tile.sculk_shrieker.name";

    /**
     * Sculk Vein
     */
    String MC_TILE_SCULK_VEIN_NAME = "minecraft:tile.sculk_vein.name";

    /**
     * Sea Lantern
     */
    String MC_TILE_SEALANTERN_NAME = "minecraft:tile.seaLantern.name";

    /**
     * Sea Pickle
     */
    String MC_TILE_SEA_PICKLE_NAME = "minecraft:tile.sea_pickle.name";

    /**
     * Seagrass
     */
    String MC_TILE_SEAGRASS_SEAGRASS_NAME = "minecraft:tile.seagrass.seagrass.name";

    /**
     * Short Dry Grass
     */
    String MC_TILE_SHORT_DRY_GRASS_NAME = "minecraft:tile.short_dry_grass.name";

    /**
     * Shroomlight
     */
    String MC_TILE_SHROOMLIGHT_NAME = "minecraft:tile.shroomlight.name";

    /**
     * Shulker Box
     */
    String MC_TILE_SHULKERBOX_NAME = "minecraft:tile.shulkerBox.name";

    /**
     * Black Shulker Box
     */
    String MC_TILE_SHULKERBOXBLACK_NAME = "minecraft:tile.shulkerBoxBlack.name";

    /**
     * Blue Shulker Box
     */
    String MC_TILE_SHULKERBOXBLUE_NAME = "minecraft:tile.shulkerBoxBlue.name";

    /**
     * Brown Shulker Box
     */
    String MC_TILE_SHULKERBOXBROWN_NAME = "minecraft:tile.shulkerBoxBrown.name";

    /**
     * Cyan Shulker Box
     */
    String MC_TILE_SHULKERBOXCYAN_NAME = "minecraft:tile.shulkerBoxCyan.name";

    /**
     * Gray Shulker Box
     */
    String MC_TILE_SHULKERBOXGRAY_NAME = "minecraft:tile.shulkerBoxGray.name";

    /**
     * Green Shulker Box
     */
    String MC_TILE_SHULKERBOXGREEN_NAME = "minecraft:tile.shulkerBoxGreen.name";

    /**
     * Light Blue Shulker Box
     */
    String MC_TILE_SHULKERBOXLIGHTBLUE_NAME = "minecraft:tile.shulkerBoxLightBlue.name";

    /**
     * Lime Shulker Box
     */
    String MC_TILE_SHULKERBOXLIME_NAME = "minecraft:tile.shulkerBoxLime.name";

    /**
     * Magenta Shulker Box
     */
    String MC_TILE_SHULKERBOXMAGENTA_NAME = "minecraft:tile.shulkerBoxMagenta.name";

    /**
     * Orange Shulker Box
     */
    String MC_TILE_SHULKERBOXORANGE_NAME = "minecraft:tile.shulkerBoxOrange.name";

    /**
     * Pink Shulker Box
     */
    String MC_TILE_SHULKERBOXPINK_NAME = "minecraft:tile.shulkerBoxPink.name";

    /**
     * Purple Shulker Box
     */
    String MC_TILE_SHULKERBOXPURPLE_NAME = "minecraft:tile.shulkerBoxPurple.name";

    /**
     * Red Shulker Box
     */
    String MC_TILE_SHULKERBOXRED_NAME = "minecraft:tile.shulkerBoxRed.name";

    /**
     * Light Gray Shulker Box
     */
    String MC_TILE_SHULKERBOXSILVER_NAME = "minecraft:tile.shulkerBoxSilver.name";

    /**
     * White Shulker Box
     */
    String MC_TILE_SHULKERBOXWHITE_NAME = "minecraft:tile.shulkerBoxWhite.name";

    /**
     * Yellow Shulker Box
     */
    String MC_TILE_SHULKERBOXYELLOW_NAME = "minecraft:tile.shulkerBoxYellow.name";

    /**
     * Slime Block
     */
    String MC_TILE_SLIME_NAME = "minecraft:tile.slime.name";

    /**
     * Small Amethyst Bud
     */
    String MC_TILE_SMALL_AMETHYST_BUD_NAME = "minecraft:tile.small_amethyst_bud.name";

    /**
     * Small Dripleaf
     */
    String MC_TILE_SMALL_DRIPLEAF_BLOCK_NAME = "minecraft:tile.small_dripleaf_block.name";

    /**
     * Smithing Table
     */
    String MC_TILE_SMITHING_TABLE_NAME = "minecraft:tile.smithing_table.name";

    /**
     * Smoker
     */
    String MC_TILE_SMOKER_NAME = "minecraft:tile.smoker.name";

    /**
     * Smooth Basalt
     */
    String MC_TILE_SMOOTH_BASALT_NAME = "minecraft:tile.smooth_basalt.name";

    /**
     * Smooth Quartz Stairs
     */
    String MC_TILE_SMOOTH_QUARTZ_STAIRS_NAME = "minecraft:tile.smooth_quartz_stairs.name";

    /**
     * Smooth Red Sandstone Stairs
     */
    String MC_TILE_SMOOTH_RED_SANDSTONE_STAIRS_NAME = "minecraft:tile.smooth_red_sandstone_stairs.name";

    /**
     * Smooth Sandstone Stairs
     */
    String MC_TILE_SMOOTH_SANDSTONE_STAIRS_NAME = "minecraft:tile.smooth_sandstone_stairs.name";

    /**
     * Smooth Stone
     */
    String MC_TILE_SMOOTH_STONE_NAME = "minecraft:tile.smooth_stone.name";

    /**
     * Sniffer Egg
     */
    String MC_TILE_SNIFFER_EGG_NAME = "minecraft:tile.sniffer_egg.name";

    /**
     * Snow Block
     */
    String MC_TILE_SNOW_NAME = "minecraft:tile.snow.name";

    /**
     * Snow
     */
    String MC_TILE_SNOW_LAYER_NAME = "minecraft:tile.snow_layer.name";

    /**
     * Soul Campfire
     */
    String MC_TILE_SOUL_CAMPFIRE_NAME = "minecraft:tile.soul_campfire.name";

    /**
     * Soul Fire
     */
    String MC_TILE_SOUL_FIRE_NAME = "minecraft:tile.soul_fire.name";

    /**
     * Soul Lantern
     */
    String MC_TILE_SOUL_LANTERN_NAME = "minecraft:tile.soul_lantern.name";

    /**
     * Soul Sand
     */
    String MC_TILE_SOUL_SAND_NAME = "minecraft:tile.soul_sand.name";

    /**
     * Soul Soil
     */
    String MC_TILE_SOUL_SOIL_NAME = "minecraft:tile.soul_soil.name";

    /**
     * Soul Torch
     */
    String MC_TILE_SOUL_TORCH_NAME = "minecraft:tile.soul_torch.name";

    /**
     * Sponge
     */
    String MC_TILE_SPONGE_DRY_NAME = "minecraft:tile.sponge.dry.name";

    /**
     * Wet Sponge
     */
    String MC_TILE_SPONGE_WET_NAME = "minecraft:tile.sponge.wet.name";

    /**
     * Spore Blossom
     */
    String MC_TILE_SPORE_BLOSSOM_NAME = "minecraft:tile.spore_blossom.name";

    /**
     * Spruce Fence
     */
    String MC_TILE_SPRUCEFENCE_NAME = "minecraft:tile.spruceFence.name";

    /**
     * Spruce Button
     */
    String MC_TILE_SPRUCE_BUTTON_NAME = "minecraft:tile.spruce_button.name";

    /**
     * Spruce Fence Gate
     */
    String MC_TILE_SPRUCE_FENCE_GATE_NAME = "minecraft:tile.spruce_fence_gate.name";

    /**
     * Spruce Pressure Plate
     */
    String MC_TILE_SPRUCE_PRESSURE_PLATE_NAME = "minecraft:tile.spruce_pressure_plate.name";

    /**
     * Spruce Shelf
     */
    String MC_TILE_SPRUCE_SHELF_NAME = "minecraft:tile.spruce_shelf.name";

    /**
     * Spruce Stairs
     */
    String MC_TILE_SPRUCE_STAIRS_NAME = "minecraft:tile.spruce_stairs.name";

    /**
     * Spruce Sign
     */
    String MC_TILE_SPRUCE_STANDING_SIGN_NAME = "minecraft:tile.spruce_standing_sign.name";

    /**
     * Spruce Trapdoor
     */
    String MC_TILE_SPRUCE_TRAPDOOR_NAME = "minecraft:tile.spruce_trapdoor.name";

    /**
     * Black Stained Glass
     */
    String MC_TILE_STAINED_GLASS_BLACK_NAME = "minecraft:tile.stained_glass.black.name";

    /**
     * Blue Stained Glass
     */
    String MC_TILE_STAINED_GLASS_BLUE_NAME = "minecraft:tile.stained_glass.blue.name";

    /**
     * Brown Stained Glass
     */
    String MC_TILE_STAINED_GLASS_BROWN_NAME = "minecraft:tile.stained_glass.brown.name";

    /**
     * Cyan Stained Glass
     */
    String MC_TILE_STAINED_GLASS_CYAN_NAME = "minecraft:tile.stained_glass.cyan.name";

    /**
     * Gray Stained Glass
     */
    String MC_TILE_STAINED_GLASS_GRAY_NAME = "minecraft:tile.stained_glass.gray.name";

    /**
     * Green Stained Glass
     */
    String MC_TILE_STAINED_GLASS_GREEN_NAME = "minecraft:tile.stained_glass.green.name";

    /**
     * Light Blue Stained Glass
     */
    String MC_TILE_STAINED_GLASS_LIGHT_BLUE_NAME = "minecraft:tile.stained_glass.light_blue.name";

    /**
     * Lime Stained Glass
     */
    String MC_TILE_STAINED_GLASS_LIME_NAME = "minecraft:tile.stained_glass.lime.name";

    /**
     * Magenta Stained Glass
     */
    String MC_TILE_STAINED_GLASS_MAGENTA_NAME = "minecraft:tile.stained_glass.magenta.name";

    /**
     * Orange Stained Glass
     */
    String MC_TILE_STAINED_GLASS_ORANGE_NAME = "minecraft:tile.stained_glass.orange.name";

    /**
     * Pink Stained Glass
     */
    String MC_TILE_STAINED_GLASS_PINK_NAME = "minecraft:tile.stained_glass.pink.name";

    /**
     * Purple Stained Glass
     */
    String MC_TILE_STAINED_GLASS_PURPLE_NAME = "minecraft:tile.stained_glass.purple.name";

    /**
     * Red Stained Glass
     */
    String MC_TILE_STAINED_GLASS_RED_NAME = "minecraft:tile.stained_glass.red.name";

    /**
     * Light Gray Stained Glass
     */
    String MC_TILE_STAINED_GLASS_SILVER_NAME = "minecraft:tile.stained_glass.silver.name";

    /**
     * White Stained Glass
     */
    String MC_TILE_STAINED_GLASS_WHITE_NAME = "minecraft:tile.stained_glass.white.name";

    /**
     * Yellow Stained Glass
     */
    String MC_TILE_STAINED_GLASS_YELLOW_NAME = "minecraft:tile.stained_glass.yellow.name";

    /**
     * Black Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_BLACK_NAME = "minecraft:tile.stained_glass_pane.black.name";

    /**
     * Blue Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_BLUE_NAME = "minecraft:tile.stained_glass_pane.blue.name";

    /**
     * Brown Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_BROWN_NAME = "minecraft:tile.stained_glass_pane.brown.name";

    /**
     * Cyan Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_CYAN_NAME = "minecraft:tile.stained_glass_pane.cyan.name";

    /**
     * Gray Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_GRAY_NAME = "minecraft:tile.stained_glass_pane.gray.name";

    /**
     * Green Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_GREEN_NAME = "minecraft:tile.stained_glass_pane.green.name";

    /**
     * Light Blue Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_LIGHT_BLUE_NAME = "minecraft:tile.stained_glass_pane.light_blue.name";

    /**
     * Lime Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_LIME_NAME = "minecraft:tile.stained_glass_pane.lime.name";

    /**
     * Magenta Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_MAGENTA_NAME = "minecraft:tile.stained_glass_pane.magenta.name";

    /**
     * Orange Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_ORANGE_NAME = "minecraft:tile.stained_glass_pane.orange.name";

    /**
     * Pink Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_PINK_NAME = "minecraft:tile.stained_glass_pane.pink.name";

    /**
     * Purple Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_PURPLE_NAME = "minecraft:tile.stained_glass_pane.purple.name";

    /**
     * Red Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_RED_NAME = "minecraft:tile.stained_glass_pane.red.name";

    /**
     * Light Gray Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_SILVER_NAME = "minecraft:tile.stained_glass_pane.silver.name";

    /**
     * White Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_WHITE_NAME = "minecraft:tile.stained_glass_pane.white.name";

    /**
     * Yellow Stained Glass Pane
     */
    String MC_TILE_STAINED_GLASS_PANE_YELLOW_NAME = "minecraft:tile.stained_glass_pane.yellow.name";

    /**
     * Black Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_BLACK_NAME = "minecraft:tile.stained_hardened_clay.black.name";

    /**
     * Blue Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_BLUE_NAME = "minecraft:tile.stained_hardened_clay.blue.name";

    /**
     * Brown Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_BROWN_NAME = "minecraft:tile.stained_hardened_clay.brown.name";

    /**
     * Cyan Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_CYAN_NAME = "minecraft:tile.stained_hardened_clay.cyan.name";

    /**
     * Gray Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_GRAY_NAME = "minecraft:tile.stained_hardened_clay.gray.name";

    /**
     * Green Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_GREEN_NAME = "minecraft:tile.stained_hardened_clay.green.name";

    /**
     * Light Blue Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_LIGHTBLUE_NAME = "minecraft:tile.stained_hardened_clay.lightBlue.name";

    /**
     * Lime Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_LIME_NAME = "minecraft:tile.stained_hardened_clay.lime.name";

    /**
     * Magenta Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_MAGENTA_NAME = "minecraft:tile.stained_hardened_clay.magenta.name";

    /**
     * Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_NAME = "minecraft:tile.stained_hardened_clay.name";

    /**
     * Orange Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_ORANGE_NAME = "minecraft:tile.stained_hardened_clay.orange.name";

    /**
     * Pink Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_PINK_NAME = "minecraft:tile.stained_hardened_clay.pink.name";

    /**
     * Purple Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_PURPLE_NAME = "minecraft:tile.stained_hardened_clay.purple.name";

    /**
     * Red Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_RED_NAME = "minecraft:tile.stained_hardened_clay.red.name";

    /**
     * Light Gray Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_SILVER_NAME = "minecraft:tile.stained_hardened_clay.silver.name";

    /**
     * White Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_WHITE_NAME = "minecraft:tile.stained_hardened_clay.white.name";

    /**
     * Yellow Terracotta
     */
    String MC_TILE_STAINED_HARDENED_CLAY_YELLOW_NAME = "minecraft:tile.stained_hardened_clay.yellow.name";

    /**
     * Black Banner
     */
    String MC_TILE_STANDING_BANNER_BLACK_NAME = "minecraft:tile.standing_banner.black.name";

    /**
     * Blue Banner
     */
    String MC_TILE_STANDING_BANNER_BLUE_NAME = "minecraft:tile.standing_banner.blue.name";

    /**
     * Brown Banner
     */
    String MC_TILE_STANDING_BANNER_BROWN_NAME = "minecraft:tile.standing_banner.brown.name";

    /**
     * Cyan Banner
     */
    String MC_TILE_STANDING_BANNER_CYAN_NAME = "minecraft:tile.standing_banner.cyan.name";

    /**
     * Gray Banner
     */
    String MC_TILE_STANDING_BANNER_GRAY_NAME = "minecraft:tile.standing_banner.gray.name";

    /**
     * Green Banner
     */
    String MC_TILE_STANDING_BANNER_GREEN_NAME = "minecraft:tile.standing_banner.green.name";

    /**
     * Light Blue Banner
     */
    String MC_TILE_STANDING_BANNER_LIGHTBLUE_NAME = "minecraft:tile.standing_banner.lightBlue.name";

    /**
     * Lime Banner
     */
    String MC_TILE_STANDING_BANNER_LIME_NAME = "minecraft:tile.standing_banner.lime.name";

    /**
     * Magenta Banner
     */
    String MC_TILE_STANDING_BANNER_MAGENTA_NAME = "minecraft:tile.standing_banner.magenta.name";

    /**
     * Banner
     */
    String MC_TILE_STANDING_BANNER_NAME = "minecraft:tile.standing_banner.name";

    /**
     * Orange Banner
     */
    String MC_TILE_STANDING_BANNER_ORANGE_NAME = "minecraft:tile.standing_banner.orange.name";

    /**
     * Pink Banner
     */
    String MC_TILE_STANDING_BANNER_PINK_NAME = "minecraft:tile.standing_banner.pink.name";

    /**
     * Purple Banner
     */
    String MC_TILE_STANDING_BANNER_PURPLE_NAME = "minecraft:tile.standing_banner.purple.name";

    /**
     * Red Banner
     */
    String MC_TILE_STANDING_BANNER_RED_NAME = "minecraft:tile.standing_banner.red.name";

    /**
     * Light Gray Banner
     */
    String MC_TILE_STANDING_BANNER_SILVER_NAME = "minecraft:tile.standing_banner.silver.name";

    /**
     * Banner
     */
    String MC_TILE_STANDING_BANNER_WHITE_NAME = "minecraft:tile.standing_banner.white.name";

    /**
     * Yellow Banner
     */
    String MC_TILE_STANDING_BANNER_YELLOW_NAME = "minecraft:tile.standing_banner.yellow.name";

    /**
     * Sign
     */
    String MC_TILE_STANDING_SIGN_NAME = "minecraft:tile.standing_sign.name";

    /**
     * Sticky Piston
     */
    String MC_TILE_STICKY_PISTON_NAME = "minecraft:tile.sticky_piston.name";

    /**
     * Andesite
     */
    String MC_TILE_STONE_ANDESITE_NAME = "minecraft:tile.stone.andesite.name";

    /**
     * Polished Andesite
     */
    String MC_TILE_STONE_ANDESITESMOOTH_NAME = "minecraft:tile.stone.andesiteSmooth.name";

    /**
     * Diorite
     */
    String MC_TILE_STONE_DIORITE_NAME = "minecraft:tile.stone.diorite.name";

    /**
     * Polished Diorite
     */
    String MC_TILE_STONE_DIORITESMOOTH_NAME = "minecraft:tile.stone.dioriteSmooth.name";

    /**
     * Granite
     */
    String MC_TILE_STONE_GRANITE_NAME = "minecraft:tile.stone.granite.name";

    /**
     * Polished Granite
     */
    String MC_TILE_STONE_GRANITESMOOTH_NAME = "minecraft:tile.stone.graniteSmooth.name";

    /**
     * Stone
     */
    String MC_TILE_STONE_STONE_NAME = "minecraft:tile.stone.stone.name";

    /**
     * Stone Brick Stairs
     */
    String MC_TILE_STONE_BRICK_STAIRS_NAME = "minecraft:tile.stone_brick_stairs.name";

    /**
     * Stone Button
     */
    String MC_TILE_STONE_BUTTON_NAME = "minecraft:tile.stone_button.name";

    /**
     * Stone Pressure Plate
     */
    String MC_TILE_STONE_PRESSURE_PLATE_NAME = "minecraft:tile.stone_pressure_plate.name";

    /**
     * Brick Slab
     */
    String MC_TILE_STONE_SLAB_BRICK_NAME = "minecraft:tile.stone_slab.brick.name";

    /**
     * Cobblestone Slab
     */
    String MC_TILE_STONE_SLAB_COBBLE_NAME = "minecraft:tile.stone_slab.cobble.name";

    /**
     * Stone Slab
     */
    String MC_TILE_STONE_SLAB_NAME = "minecraft:tile.stone_slab.name";

    /**
     * Nether Brick Slab
     */
    String MC_TILE_STONE_SLAB_NETHER_BRICK_NAME = "minecraft:tile.stone_slab.nether_brick.name";

    /**
     * Quartz Slab
     */
    String MC_TILE_STONE_SLAB_QUARTZ_NAME = "minecraft:tile.stone_slab.quartz.name";

    /**
     * Sandstone Slab
     */
    String MC_TILE_STONE_SLAB_SAND_NAME = "minecraft:tile.stone_slab.sand.name";

    /**
     * Stone Brick Slab
     */
    String MC_TILE_STONE_SLAB_SMOOTHSTONEBRICK_NAME = "minecraft:tile.stone_slab.smoothStoneBrick.name";

    /**
     * Smooth Stone Slab
     */
    String MC_TILE_STONE_SLAB_STONE_NAME = "minecraft:tile.stone_slab.stone.name";

    /**
     * Wooden Slab
     */
    String MC_TILE_STONE_SLAB_WOOD_NAME = "minecraft:tile.stone_slab.wood.name";

    /**
     * Mossy Cobblestone Slab
     */
    String MC_TILE_STONE_SLAB2_MOSSY_COBBLESTONE_NAME = "minecraft:tile.stone_slab2.mossy_cobblestone.name";

    /**
     * Prismarine Brick Slab
     */
    String MC_TILE_STONE_SLAB2_PRISMARINE_BRICKS_NAME = "minecraft:tile.stone_slab2.prismarine.bricks.name";

    /**
     * Dark Prismarine Slab
     */
    String MC_TILE_STONE_SLAB2_PRISMARINE_DARK_NAME = "minecraft:tile.stone_slab2.prismarine.dark.name";

    /**
     * Prismarine Slab
     */
    String MC_TILE_STONE_SLAB2_PRISMARINE_ROUGH_NAME = "minecraft:tile.stone_slab2.prismarine.rough.name";

    /**
     * Purpur Slab
     */
    String MC_TILE_STONE_SLAB2_PURPUR_NAME = "minecraft:tile.stone_slab2.purpur.name";

    /**
     * Red Nether Brick Slab
     */
    String MC_TILE_STONE_SLAB2_RED_NETHER_BRICK_NAME = "minecraft:tile.stone_slab2.red_nether_brick.name";

    /**
     * Red Sandstone Slab
     */
    String MC_TILE_STONE_SLAB2_RED_SANDSTONE_NAME = "minecraft:tile.stone_slab2.red_sandstone.name";

    /**
     * Smooth Sandstone Slab
     */
    String MC_TILE_STONE_SLAB2_SANDSTONE_SMOOTH_NAME = "minecraft:tile.stone_slab2.sandstone.smooth.name";

    /**
     * Andesite Slab
     */
    String MC_TILE_STONE_SLAB3_ANDESITE_NAME = "minecraft:tile.stone_slab3.andesite.name";

    /**
     * Polished Andesite Slab
     */
    String MC_TILE_STONE_SLAB3_ANDESITE_SMOOTH_NAME = "minecraft:tile.stone_slab3.andesite.smooth.name";

    /**
     * Diorite Slab
     */
    String MC_TILE_STONE_SLAB3_DIORITE_NAME = "minecraft:tile.stone_slab3.diorite.name";

    /**
     * Polished Diorite Slab
     */
    String MC_TILE_STONE_SLAB3_DIORITE_SMOOTH_NAME = "minecraft:tile.stone_slab3.diorite.smooth.name";

    /**
     * End Stone Brick Slab
     */
    String MC_TILE_STONE_SLAB3_END_BRICK_NAME = "minecraft:tile.stone_slab3.end_brick.name";

    /**
     * Granite Slab
     */
    String MC_TILE_STONE_SLAB3_GRANITE_NAME = "minecraft:tile.stone_slab3.granite.name";

    /**
     * Polished Granite Slab
     */
    String MC_TILE_STONE_SLAB3_GRANITE_SMOOTH_NAME = "minecraft:tile.stone_slab3.granite.smooth.name";

    /**
     * Smooth Red Sandstone Slab
     */
    String MC_TILE_STONE_SLAB3_RED_SANDSTONE_SMOOTH_NAME = "minecraft:tile.stone_slab3.red_sandstone.smooth.name";

    /**
     * Cut Red Sandstone Slab
     */
    String MC_TILE_STONE_SLAB4_CUT_RED_SANDSTONE_NAME = "minecraft:tile.stone_slab4.cut_red_sandstone.name";

    /**
     * Cut Sandstone Slab
     */
    String MC_TILE_STONE_SLAB4_CUT_SANDSTONE_NAME = "minecraft:tile.stone_slab4.cut_sandstone.name";

    /**
     * Mossy Stone Brick Slab
     */
    String MC_TILE_STONE_SLAB4_MOSSY_STONE_BRICK_NAME = "minecraft:tile.stone_slab4.mossy_stone_brick.name";

    /**
     * Smooth Quartz Slab
     */
    String MC_TILE_STONE_SLAB4_SMOOTH_QUARTZ_NAME = "minecraft:tile.stone_slab4.smooth_quartz.name";

    /**
     * Stone Slab
     */
    String MC_TILE_STONE_SLAB4_STONE_NAME = "minecraft:tile.stone_slab4.stone.name";

    /**
     * Cobblestone Stairs
     */
    String MC_TILE_STONE_STAIRS_NAME = "minecraft:tile.stone_stairs.name";

    /**
     * Chiseled Stone Bricks
     */
    String MC_TILE_STONEBRICK_CHISELED_NAME = "minecraft:tile.stonebrick.chiseled.name";

    /**
     * Cracked Stone Bricks
     */
    String MC_TILE_STONEBRICK_CRACKED_NAME = "minecraft:tile.stonebrick.cracked.name";

    /**
     * Stone Bricks
     */
    String MC_TILE_STONEBRICK_DEFAULT_NAME = "minecraft:tile.stonebrick.default.name";

    /**
     * Mossy Stone Bricks
     */
    String MC_TILE_STONEBRICK_MOSSY_NAME = "minecraft:tile.stonebrick.mossy.name";

    /**
     * Stone Bricks
     */
    String MC_TILE_STONEBRICK_NAME = "minecraft:tile.stonebrick.name";

    /**
     * Smooth Stone Bricks
     */
    String MC_TILE_STONEBRICK_SMOOTH_NAME = "minecraft:tile.stonebrick.smooth.name";

    /**
     * Stonecutter
     */
    String MC_TILE_STONECUTTER_NAME = "minecraft:tile.stonecutter.name";

    /**
     * Stonecutter
     */
    String MC_TILE_STONECUTTER_BLOCK_NAME = "minecraft:tile.stonecutter_block.name";

    /**
     * Stripped Acacia Log
     */
    String MC_TILE_STRIPPED_ACACIA_LOG_NAME = "minecraft:tile.stripped_acacia_log.name";

    /**
     * Block of Stripped Bamboo
     */
    String MC_TILE_STRIPPED_BAMBOO_BLOCK_NAME = "minecraft:tile.stripped_bamboo_block.name";

    /**
     * Stripped Birch Log
     */
    String MC_TILE_STRIPPED_BIRCH_LOG_NAME = "minecraft:tile.stripped_birch_log.name";

    /**
     * Stripped Cherry Log
     */
    String MC_TILE_STRIPPED_CHERRY_LOG_NAME = "minecraft:tile.stripped_cherry_log.name";

    /**
     * Stripped Cherry Wood
     */
    String MC_TILE_STRIPPED_CHERRY_WOOD_NAME = "minecraft:tile.stripped_cherry_wood.name";

    /**
     * Stripped Crimson Hyphae
     */
    String MC_TILE_STRIPPED_CRIMSON_HYPHAE_NAME = "minecraft:tile.stripped_crimson_hyphae.name";

    /**
     * Stripped Crimson Stem
     */
    String MC_TILE_STRIPPED_CRIMSON_STEM_NAME = "minecraft:tile.stripped_crimson_stem.name";

    /**
     * Stripped Dark Oak Log
     */
    String MC_TILE_STRIPPED_DARK_OAK_LOG_NAME = "minecraft:tile.stripped_dark_oak_log.name";

    /**
     * Stripped Jungle Log
     */
    String MC_TILE_STRIPPED_JUNGLE_LOG_NAME = "minecraft:tile.stripped_jungle_log.name";

    /**
     * Stripped Mangrove Log
     */
    String MC_TILE_STRIPPED_MANGROVE_LOG_NAME = "minecraft:tile.stripped_mangrove_log.name";

    /**
     * Stripped Mangrove Wood
     */
    String MC_TILE_STRIPPED_MANGROVE_WOOD_NAME = "minecraft:tile.stripped_mangrove_wood.name";

    /**
     * Stripped Oak Log
     */
    String MC_TILE_STRIPPED_OAK_LOG_NAME = "minecraft:tile.stripped_oak_log.name";

    /**
     * Stripped Pale Oak Log
     */
    String MC_TILE_STRIPPED_PALE_OAK_LOG_NAME = "minecraft:tile.stripped_pale_oak_log.name";

    /**
     * Stripped Pale Oak Wood
     */
    String MC_TILE_STRIPPED_PALE_OAK_WOOD_NAME = "minecraft:tile.stripped_pale_oak_wood.name";

    /**
     * Stripped Spruce Log
     */
    String MC_TILE_STRIPPED_SPRUCE_LOG_NAME = "minecraft:tile.stripped_spruce_log.name";

    /**
     * Stripped Warped Hyphae
     */
    String MC_TILE_STRIPPED_WARPED_HYPHAE_NAME = "minecraft:tile.stripped_warped_hyphae.name";

    /**
     * Stripped Warped Stem
     */
    String MC_TILE_STRIPPED_WARPED_STEM_NAME = "minecraft:tile.stripped_warped_stem.name";

    /**
     * Structure Block
     */
    String MC_TILE_STRUCTURE_BLOCK_NAME = "minecraft:tile.structure_block.name";

    /**
     * Structure Void
     */
    String MC_TILE_STRUCTURE_VOID_NAME = "minecraft:tile.structure_void.name";

    /**
     * Suspicious Gravel
     */
    String MC_TILE_SUSPICIOUS_GRAVEL_NAME = "minecraft:tile.suspicious_gravel.name";

    /**
     * Suspicious Sand
     */
    String MC_TILE_SUSPICIOUS_SAND_NAME = "minecraft:tile.suspicious_sand.name";

    /**
     * Sweet Berry Bush
     */
    String MC_TILE_SWEET_BERRY_BUSH_NAME = "minecraft:tile.sweet_berry_bush.name";

    /**
     * Tall Dry Grass
     */
    String MC_TILE_TALL_DRY_GRASS_NAME = "minecraft:tile.tall_dry_grass.name";

    /**
     * Fern
     */
    String MC_TILE_TALLGRASS_FERN_NAME = "minecraft:tile.tallgrass.fern.name";

    /**
     * Short Grass
     */
    String MC_TILE_TALLGRASS_GRASS_NAME = "minecraft:tile.tallgrass.grass.name";

    /**
     * Short Grass
     */
    String MC_TILE_TALLGRASS_NAME = "minecraft:tile.tallgrass.name";

    /**
     * Shrub
     */
    String MC_TILE_TALLGRASS_SHRUB_NAME = "minecraft:tile.tallgrass.shrub.name";

    /**
     * Target
     */
    String MC_TILE_TARGET_NAME = "minecraft:tile.target.name";

    /**
     * Tinted Glass
     */
    String MC_TILE_TINTED_GLASS_NAME = "minecraft:tile.tinted_glass.name";

    /**
     * TNT
     */
    String MC_TILE_TNT_NAME = "minecraft:tile.tnt.name";

    /**
     * Torch
     */
    String MC_TILE_TORCH_NAME = "minecraft:tile.torch.name";

    /**
     * Torchflower
     */
    String MC_TILE_TORCHFLOWER_NAME = "minecraft:tile.torchflower.name";

    /**
     * Oak Trapdoor
     */
    String MC_TILE_TRAPDOOR_NAME = "minecraft:tile.trapdoor.name";

    /**
     * Trapped Chest
     */
    String MC_TILE_TRAPPED_CHEST_NAME = "minecraft:tile.trapped_chest.name";

    /**
     * Trial Spawner
     */
    String MC_TILE_TRIAL_SPAWNER_NAME = "minecraft:tile.trial_spawner.name";

    /**
     * Tripwire
     */
    String MC_TILE_TRIPWIRE_NAME = "minecraft:tile.tripWire.name";

    /**
     * Tripwire Hook
     */
    String MC_TILE_TRIPWIRE_HOOK_NAME = "minecraft:tile.tripwire_hook.name";

    /**
     * Tuff
     */
    String MC_TILE_TUFF_NAME = "minecraft:tile.tuff.name";

    /**
     * Tuff Brick Slab
     */
    String MC_TILE_TUFF_BRICK_SLAB_NAME = "minecraft:tile.tuff_brick_slab.name";

    /**
     * Tuff Brick Stairs
     */
    String MC_TILE_TUFF_BRICK_STAIRS_NAME = "minecraft:tile.tuff_brick_stairs.name";

    /**
     * Tuff Brick Wall
     */
    String MC_TILE_TUFF_BRICK_WALL_NAME = "minecraft:tile.tuff_brick_wall.name";

    /**
     * Tuff Bricks
     */
    String MC_TILE_TUFF_BRICKS_NAME = "minecraft:tile.tuff_bricks.name";

    /**
     * Tuff Slab
     */
    String MC_TILE_TUFF_SLAB_NAME = "minecraft:tile.tuff_slab.name";

    /**
     * Tuff Stairs
     */
    String MC_TILE_TUFF_STAIRS_NAME = "minecraft:tile.tuff_stairs.name";

    /**
     * Tuff Wall
     */
    String MC_TILE_TUFF_WALL_NAME = "minecraft:tile.tuff_wall.name";

    /**
     * Turtle Egg
     */
    String MC_TILE_TURTLE_EGG_NAME = "minecraft:tile.turtle_egg.name";

    /**
     * Twisting Vines
     */
    String MC_TILE_TWISTING_VINES_NAME = "minecraft:tile.twisting_vines.name";

    /**
     * Unknown
     */
    String MC_TILE_UNKNOWN_NAME = "minecraft:tile.unknown.name";

    /**
     * Redstone Torch
     */
    String MC_TILE_UNLIT_REDSTONE_TORCH_NAME = "minecraft:tile.unlit_redstone_torch.name";

    /**
     * Vault
     */
    String MC_TILE_VAULT_NAME = "minecraft:tile.vault.name";

    /**
     * Verdant Froglight
     */
    String MC_TILE_VERDANT_FROGLIGHT_NAME = "minecraft:tile.verdant_froglight.name";

    /**
     * Vines
     */
    String MC_TILE_VINE_NAME = "minecraft:tile.vine.name";

    /**
     * Warped Button
     */
    String MC_TILE_WARPED_BUTTON_NAME = "minecraft:tile.warped_button.name";

    /**
     * Warped Door
     */
    String MC_TILE_WARPED_DOOR_NAME = "minecraft:tile.warped_door.name";

    /**
     * Warped Slab
     */
    String MC_TILE_WARPED_DOUBLE_SLAB_NAME = "minecraft:tile.warped_double_slab.name";

    /**
     * Warped Fence
     */
    String MC_TILE_WARPED_FENCE_NAME = "minecraft:tile.warped_fence.name";

    /**
     * Warped Fence Gate
     */
    String MC_TILE_WARPED_FENCE_GATE_NAME = "minecraft:tile.warped_fence_gate.name";

    /**
     * Warped Fungus
     */
    String MC_TILE_WARPED_FUNGUS_NAME = "minecraft:tile.warped_fungus.name";

    /**
     * Warped Hyphae
     */
    String MC_TILE_WARPED_HYPHAE_NAME = "minecraft:tile.warped_hyphae.name";

    /**
     * Warped Nylium
     */
    String MC_TILE_WARPED_NYLIUM_NAME = "minecraft:tile.warped_nylium.name";

    /**
     * Warped Planks
     */
    String MC_TILE_WARPED_PLANKS_NAME = "minecraft:tile.warped_planks.name";

    /**
     * Warped Pressure Plate
     */
    String MC_TILE_WARPED_PRESSURE_PLATE_NAME = "minecraft:tile.warped_pressure_plate.name";

    /**
     * Warped Roots
     */
    String MC_TILE_WARPED_ROOTS_WARPEDROOTS_NAME = "minecraft:tile.warped_roots.warpedRoots.name";

    /**
     * Warped Shelf
     */
    String MC_TILE_WARPED_SHELF_NAME = "minecraft:tile.warped_shelf.name";

    /**
     * Warped Slab
     */
    String MC_TILE_WARPED_SLAB_NAME = "minecraft:tile.warped_slab.name";

    /**
     * Warped Stairs
     */
    String MC_TILE_WARPED_STAIRS_NAME = "minecraft:tile.warped_stairs.name";

    /**
     * Warped Sign
     */
    String MC_TILE_WARPED_STANDING_SIGN_NAME = "minecraft:tile.warped_standing_sign.name";

    /**
     * Warped Stem
     */
    String MC_TILE_WARPED_STEM_NAME = "minecraft:tile.warped_stem.name";

    /**
     * Warped Trapdoor
     */
    String MC_TILE_WARPED_TRAPDOOR_NAME = "minecraft:tile.warped_trapdoor.name";

    /**
     * Warped Sign
     */
    String MC_TILE_WARPED_WALL_SIGN_NAME = "minecraft:tile.warped_wall_sign.name";

    /**
     * Warped Wart Block
     */
    String MC_TILE_WARPED_WART_BLOCK_NAME = "minecraft:tile.warped_wart_block.name";

    /**
     * Water
     */
    String MC_TILE_WATER_NAME = "minecraft:tile.water.name";

    /**
     * Lily Pad
     */
    String MC_TILE_WATERLILY_NAME = "minecraft:tile.waterlily.name";

    /**
     * Waxed Chiseled Copper
     */
    String MC_TILE_WAXED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_chiseled_copper.name";

    /**
     * Waxed Block of Copper
     */
    String MC_TILE_WAXED_COPPER_NAME = "minecraft:tile.waxed_copper.name";

    /**
     * Waxed Copper Bars
     */
    String MC_TILE_WAXED_COPPER_BARS_NAME = "minecraft:tile.waxed_copper_bars.name";

    /**
     * Waxed Copper Bulb
     */
    String MC_TILE_WAXED_COPPER_BULB_NAME = "minecraft:tile.waxed_copper_bulb.name";

    /**
     * Waxed Copper Chain
     */
    String MC_TILE_WAXED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_copper_chain.name";

    /**
     * Waxed Copper Chest
     */
    String MC_TILE_WAXED_COPPER_CHEST_NAME = "minecraft:tile.waxed_copper_chest.name";

    /**
     * Waxed Copper Door
     */
    String MC_TILE_WAXED_COPPER_DOOR_NAME = "minecraft:tile.waxed_copper_door.name";

    /**
     * Waxed Copper Golem Statue
     */
    String MC_TILE_WAXED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_copper_golem_statue.name";

    /**
     * Waxed Copper Grate
     */
    String MC_TILE_WAXED_COPPER_GRATE_NAME = "minecraft:tile.waxed_copper_grate.name";

    /**
     * Waxed Copper Lantern
     */
    String MC_TILE_WAXED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_copper_lantern.name";

    /**
     * Waxed Copper Trapdoor
     */
    String MC_TILE_WAXED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_copper_trapdoor.name";

    /**
     * Waxed Cut Copper
     */
    String MC_TILE_WAXED_CUT_COPPER_NAME = "minecraft:tile.waxed_cut_copper.name";

    /**
     * Waxed Cut Copper Slab
     */
    String MC_TILE_WAXED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_cut_copper_slab.name";

    /**
     * Waxed Cut Copper Stairs
     */
    String MC_TILE_WAXED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_cut_copper_stairs.name";

    /**
     * Waxed Exposed Chiseled Copper
     */
    String MC_TILE_WAXED_EXPOSED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_exposed_chiseled_copper.name";

    /**
     * Waxed Exposed Copper
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_NAME = "minecraft:tile.waxed_exposed_copper.name";

    /**
     * Waxed Exposed Copper Bars
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_BARS_NAME = "minecraft:tile.waxed_exposed_copper_bars.name";

    /**
     * Waxed Exposed Copper Bulb
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_BULB_NAME = "minecraft:tile.waxed_exposed_copper_bulb.name";

    /**
     * Waxed Exposed Copper Chain
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_exposed_copper_chain.name";

    /**
     * Waxed Exposed Copper Chest
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_CHEST_NAME = "minecraft:tile.waxed_exposed_copper_chest.name";

    /**
     * Waxed Exposed Copper Door
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_DOOR_NAME = "minecraft:tile.waxed_exposed_copper_door.name";

    /**
     * Waxed Exposed Copper Golem Statue
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_exposed_copper_golem_statue.name";

    /**
     * Waxed Exposed Copper Grate
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_GRATE_NAME = "minecraft:tile.waxed_exposed_copper_grate.name";

    /**
     * Waxed Exposed Copper Lantern
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_exposed_copper_lantern.name";

    /**
     * Waxed Exposed Copper Trapdoor
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_exposed_copper_trapdoor.name";

    /**
     * Waxed Exposed Cut Copper
     */
    String MC_TILE_WAXED_EXPOSED_CUT_COPPER_NAME = "minecraft:tile.waxed_exposed_cut_copper.name";

    /**
     * Waxed Exposed Cut Copper Slab
     */
    String MC_TILE_WAXED_EXPOSED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_exposed_cut_copper_slab.name";

    /**
     * Waxed Exposed Cut Copper Stairs
     */
    String MC_TILE_WAXED_EXPOSED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_exposed_cut_copper_stairs.name";

    /**
     * Waxed Exposed Lightning Rod
     */
    String MC_TILE_WAXED_EXPOSED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_exposed_lightning_rod.name";

    /**
     * Waxed Lightning Rod
     */
    String MC_TILE_WAXED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_lightning_rod.name";

    /**
     * Waxed Oxidized Chiseled Copper
     */
    String MC_TILE_WAXED_OXIDIZED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_oxidized_chiseled_copper.name";

    /**
     * Waxed Oxidized Copper
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_NAME = "minecraft:tile.waxed_oxidized_copper.name";

    /**
     * Waxed Oxidized Copper Bars
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_BARS_NAME = "minecraft:tile.waxed_oxidized_copper_bars.name";

    /**
     * Waxed Oxidized Copper Bulb
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_BULB_NAME = "minecraft:tile.waxed_oxidized_copper_bulb.name";

    /**
     * Waxed Oxidized Copper Chain
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_oxidized_copper_chain.name";

    /**
     * Waxed Oxidized Copper Chest
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_CHEST_NAME = "minecraft:tile.waxed_oxidized_copper_chest.name";

    /**
     * Waxed Oxidized Copper Door
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_DOOR_NAME = "minecraft:tile.waxed_oxidized_copper_door.name";

    /**
     * Waxed Oxidized Copper Golem Statue
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_oxidized_copper_golem_statue.name";

    /**
     * Waxed Oxidized Copper Grate
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_GRATE_NAME = "minecraft:tile.waxed_oxidized_copper_grate.name";

    /**
     * Waxed Oxidized Copper Lantern
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_oxidized_copper_lantern.name";

    /**
     * Waxed Oxidized Copper Trapdoor
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_oxidized_copper_trapdoor.name";

    /**
     * Waxed Oxidized Cut Copper
     */
    String MC_TILE_WAXED_OXIDIZED_CUT_COPPER_NAME = "minecraft:tile.waxed_oxidized_cut_copper.name";

    /**
     * Waxed Oxidized Cut Copper Slab
     */
    String MC_TILE_WAXED_OXIDIZED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_oxidized_cut_copper_slab.name";

    /**
     * Waxed Oxidized Cut Copper Stairs
     */
    String MC_TILE_WAXED_OXIDIZED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_oxidized_cut_copper_stairs.name";

    /**
     * Waxed Oxidized Lightning Rod
     */
    String MC_TILE_WAXED_OXIDIZED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_oxidized_lightning_rod.name";

    /**
     * Waxed Weathered Chiseled Copper
     */
    String MC_TILE_WAXED_WEATHERED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_weathered_chiseled_copper.name";

    /**
     * Waxed Weathered Copper
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_NAME = "minecraft:tile.waxed_weathered_copper.name";

    /**
     * Waxed Weathered Copper Bars
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_BARS_NAME = "minecraft:tile.waxed_weathered_copper_bars.name";

    /**
     * Waxed Weathered Copper Bulb
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_BULB_NAME = "minecraft:tile.waxed_weathered_copper_bulb.name";

    /**
     * Waxed Weathered Copper Chain
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_weathered_copper_chain.name";

    /**
     * Waxed Weathered Copper Chest
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_CHEST_NAME = "minecraft:tile.waxed_weathered_copper_chest.name";

    /**
     * Waxed Weathered Copper Door
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_DOOR_NAME = "minecraft:tile.waxed_weathered_copper_door.name";

    /**
     * Waxed Weathered Copper Golem Statue
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_weathered_copper_golem_statue.name";

    /**
     * Waxed Weathered Copper Grate
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_GRATE_NAME = "minecraft:tile.waxed_weathered_copper_grate.name";

    /**
     * Waxed Weathered Copper Lantern
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_weathered_copper_lantern.name";

    /**
     * Waxed Weathered Copper Trapdoor
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_weathered_copper_trapdoor.name";

    /**
     * Waxed Weathered Cut Copper
     */
    String MC_TILE_WAXED_WEATHERED_CUT_COPPER_NAME = "minecraft:tile.waxed_weathered_cut_copper.name";

    /**
     * Waxed Weathered Cut Copper Slab
     */
    String MC_TILE_WAXED_WEATHERED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_weathered_cut_copper_slab.name";

    /**
     * Waxed Weathered Cut Copper Stairs
     */
    String MC_TILE_WAXED_WEATHERED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_weathered_cut_copper_stairs.name";

    /**
     * Waxed Weathered Lightning Rod
     */
    String MC_TILE_WAXED_WEATHERED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_weathered_lightning_rod.name";

    /**
     * Weathered Chiseled Copper
     */
    String MC_TILE_WEATHERED_CHISELED_COPPER_NAME = "minecraft:tile.weathered_chiseled_copper.name";

    /**
     * Weathered Copper
     */
    String MC_TILE_WEATHERED_COPPER_NAME = "minecraft:tile.weathered_copper.name";

    /**
     * Weathered Copper Bars
     */
    String MC_TILE_WEATHERED_COPPER_BARS_NAME = "minecraft:tile.weathered_copper_bars.name";

    /**
     * Weathered Copper Bulb
     */
    String MC_TILE_WEATHERED_COPPER_BULB_NAME = "minecraft:tile.weathered_copper_bulb.name";

    /**
     * Weathered Copper Chain
     */
    String MC_TILE_WEATHERED_COPPER_CHAIN_NAME = "minecraft:tile.weathered_copper_chain.name";

    /**
     * Weathered Copper Chest
     */
    String MC_TILE_WEATHERED_COPPER_CHEST_NAME = "minecraft:tile.weathered_copper_chest.name";

    /**
     * Weathered Copper Door
     */
    String MC_TILE_WEATHERED_COPPER_DOOR_NAME = "minecraft:tile.weathered_copper_door.name";

    /**
     * Weathered Copper Golem Statue
     */
    String MC_TILE_WEATHERED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.weathered_copper_golem_statue.name";

    /**
     * Weathered Copper Grate
     */
    String MC_TILE_WEATHERED_COPPER_GRATE_NAME = "minecraft:tile.weathered_copper_grate.name";

    /**
     * Weathered Copper Lantern
     */
    String MC_TILE_WEATHERED_COPPER_LANTERN_NAME = "minecraft:tile.weathered_copper_lantern.name";

    /**
     * Weathered Copper Trapdoor
     */
    String MC_TILE_WEATHERED_COPPER_TRAPDOOR_NAME = "minecraft:tile.weathered_copper_trapdoor.name";

    /**
     * Weathered Cut Copper
     */
    String MC_TILE_WEATHERED_CUT_COPPER_NAME = "minecraft:tile.weathered_cut_copper.name";

    /**
     * Weathered Cut Copper Slab
     */
    String MC_TILE_WEATHERED_CUT_COPPER_SLAB_NAME = "minecraft:tile.weathered_cut_copper_slab.name";

    /**
     * Weathered Cut Copper Stairs
     */
    String MC_TILE_WEATHERED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.weathered_cut_copper_stairs.name";

    /**
     * Weathered Lightning Rod
     */
    String MC_TILE_WEATHERED_LIGHTNING_ROD_NAME = "minecraft:tile.weathered_lightning_rod.name";

    /**
     * Cobweb
     */
    String MC_TILE_WEB_NAME = "minecraft:tile.web.name";

    /**
     * Weeping Vines
     */
    String MC_TILE_WEEPING_VINES_NAME = "minecraft:tile.weeping_vines.name";

    /**
     * Crops
     */
    String MC_TILE_WHEAT_NAME = "minecraft:tile.wheat.name";

    /**
     * White Candle
     */
    String MC_TILE_WHITE_CANDLE_NAME = "minecraft:tile.white_candle.name";

    /**
     * Cake with White Candle
     */
    String MC_TILE_WHITE_CANDLE_CAKE_NAME = "minecraft:tile.white_candle_cake.name";

    /**
     * Wildflowers
     */
    String MC_TILE_WILDFLOWERS_NAME = "minecraft:tile.wildflowers.name";

    /**
     * Wither Rose
     */
    String MC_TILE_WITHER_ROSE_NAME = "minecraft:tile.wither_rose.name";

    /**
     * Acacia Wood
     */
    String MC_TILE_WOOD_ACACIA_NAME = "minecraft:tile.wood.acacia.name";

    /**
     * Birch Wood
     */
    String MC_TILE_WOOD_BIRCH_NAME = "minecraft:tile.wood.birch.name";

    /**
     * Dark Oak Wood
     */
    String MC_TILE_WOOD_DARK_OAK_NAME = "minecraft:tile.wood.dark_oak.name";

    /**
     * Jungle Wood
     */
    String MC_TILE_WOOD_JUNGLE_NAME = "minecraft:tile.wood.jungle.name";

    /**
     * Oak Wood
     */
    String MC_TILE_WOOD_OAK_NAME = "minecraft:tile.wood.oak.name";

    /**
     * Spruce Wood
     */
    String MC_TILE_WOOD_SPRUCE_NAME = "minecraft:tile.wood.spruce.name";

    /**
     * Stripped Acacia Wood
     */
    String MC_TILE_WOOD_STRIPPED_ACACIA_NAME = "minecraft:tile.wood.stripped.acacia.name";

    /**
     * Stripped Birch Wood
     */
    String MC_TILE_WOOD_STRIPPED_BIRCH_NAME = "minecraft:tile.wood.stripped.birch.name";

    /**
     * Stripped Dark Oak Wood
     */
    String MC_TILE_WOOD_STRIPPED_DARK_OAK_NAME = "minecraft:tile.wood.stripped.dark_oak.name";

    /**
     * Stripped Jungle Wood
     */
    String MC_TILE_WOOD_STRIPPED_JUNGLE_NAME = "minecraft:tile.wood.stripped.jungle.name";

    /**
     * Stripped Oak Wood
     */
    String MC_TILE_WOOD_STRIPPED_OAK_NAME = "minecraft:tile.wood.stripped.oak.name";

    /**
     * Stripped Spruce Wood
     */
    String MC_TILE_WOOD_STRIPPED_SPRUCE_NAME = "minecraft:tile.wood.stripped.spruce.name";

    /**
     * Oak Button
     */
    String MC_TILE_WOODEN_BUTTON_NAME = "minecraft:tile.wooden_button.name";

    /**
     * Oak Pressure Plate
     */
    String MC_TILE_WOODEN_PRESSURE_PLATE_NAME = "minecraft:tile.wooden_pressure_plate.name";

    /**
     * Acacia Slab
     */
    String MC_TILE_WOODEN_SLAB_ACACIA_NAME = "minecraft:tile.wooden_slab.acacia.name";

    /**
     * Dark Oak Slab
     */
    String MC_TILE_WOODEN_SLAB_BIG_OAK_NAME = "minecraft:tile.wooden_slab.big_oak.name";

    /**
     * Birch Slab
     */
    String MC_TILE_WOODEN_SLAB_BIRCH_NAME = "minecraft:tile.wooden_slab.birch.name";

    /**
     * Jungle Slab
     */
    String MC_TILE_WOODEN_SLAB_JUNGLE_NAME = "minecraft:tile.wooden_slab.jungle.name";

    /**
     * Wood Slab
     */
    String MC_TILE_WOODEN_SLAB_NAME = "minecraft:tile.wooden_slab.name";

    /**
     * Oak Slab
     */
    String MC_TILE_WOODEN_SLAB_OAK_NAME = "minecraft:tile.wooden_slab.oak.name";

    /**
     * Spruce Slab
     */
    String MC_TILE_WOODEN_SLAB_SPRUCE_NAME = "minecraft:tile.wooden_slab.spruce.name";

    /**
     * Black Wool
     */
    String MC_TILE_WOOL_BLACK_NAME = "minecraft:tile.wool.black.name";

    /**
     * Blue Wool
     */
    String MC_TILE_WOOL_BLUE_NAME = "minecraft:tile.wool.blue.name";

    /**
     * Brown Wool
     */
    String MC_TILE_WOOL_BROWN_NAME = "minecraft:tile.wool.brown.name";

    /**
     * Cyan Wool
     */
    String MC_TILE_WOOL_CYAN_NAME = "minecraft:tile.wool.cyan.name";

    /**
     * Gray Wool
     */
    String MC_TILE_WOOL_GRAY_NAME = "minecraft:tile.wool.gray.name";

    /**
     * Green Wool
     */
    String MC_TILE_WOOL_GREEN_NAME = "minecraft:tile.wool.green.name";

    /**
     * Light Blue Wool
     */
    String MC_TILE_WOOL_LIGHTBLUE_NAME = "minecraft:tile.wool.lightBlue.name";

    /**
     * Lime Wool
     */
    String MC_TILE_WOOL_LIME_NAME = "minecraft:tile.wool.lime.name";

    /**
     * Magenta Wool
     */
    String MC_TILE_WOOL_MAGENTA_NAME = "minecraft:tile.wool.magenta.name";

    /**
     * Wool
     */
    String MC_TILE_WOOL_NAME = "minecraft:tile.wool.name";

    /**
     * Orange Wool
     */
    String MC_TILE_WOOL_ORANGE_NAME = "minecraft:tile.wool.orange.name";

    /**
     * Pink Wool
     */
    String MC_TILE_WOOL_PINK_NAME = "minecraft:tile.wool.pink.name";

    /**
     * Purple Wool
     */
    String MC_TILE_WOOL_PURPLE_NAME = "minecraft:tile.wool.purple.name";

    /**
     * Red Wool
     */
    String MC_TILE_WOOL_RED_NAME = "minecraft:tile.wool.red.name";

    /**
     * Light Gray Wool
     */
    String MC_TILE_WOOL_SILVER_NAME = "minecraft:tile.wool.silver.name";

    /**
     * White Wool
     */
    String MC_TILE_WOOL_WHITE_NAME = "minecraft:tile.wool.white.name";

    /**
     * Yellow Wool
     */
    String MC_TILE_WOOL_YELLOW_NAME = "minecraft:tile.wool.yellow.name";

    /**
     * Yellow Candle
     */
    String MC_TILE_YELLOW_CANDLE_NAME = "minecraft:tile.yellow_candle.name";

    /**
     * Cake with Yellow Candle
     */
    String MC_TILE_YELLOW_CANDLE_CAKE_NAME = "minecraft:tile.yellow_candle_cake.name";

    /**
     * Dandelion
     */
    String MC_TILE_YELLOW_FLOWER_DANDELION_NAME = "minecraft:tile.yellow_flower.dandelion.name";

    /**
     * Flower
     */
    String MC_TILE_YELLOW_FLOWER_NAME = "minecraft:tile.yellow_flower.name";

    /**
     * Tipped Arrow
     */
    String MC_TIPPED_ARROW_EFFECT_AWKWARD = "minecraft:tipped_arrow.effect.awkward";

    /**
     * Arrow of Strength
     */
    String MC_TIPPED_ARROW_EFFECT_DAMAGEBOOST = "minecraft:tipped_arrow.effect.damageBoost";

    /**
     * Tipped Arrow
     */
    String MC_TIPPED_ARROW_EFFECT_EMPTY = "minecraft:tipped_arrow.effect.empty";

    /**
     * Arrow of Fire Resistance
     */
    String MC_TIPPED_ARROW_EFFECT_FIRERESISTANCE = "minecraft:tipped_arrow.effect.fireResistance";

    /**
     * Arrow of Harming
     */
    String MC_TIPPED_ARROW_EFFECT_HARM = "minecraft:tipped_arrow.effect.harm";

    /**
     * Arrow of Healing
     */
    String MC_TIPPED_ARROW_EFFECT_HEAL = "minecraft:tipped_arrow.effect.heal";

    /**
     * Arrow of Infestation
     */
    String MC_TIPPED_ARROW_EFFECT_INFESTED = "minecraft:tipped_arrow.effect.infested";

    /**
     * Arrow of Invisibility
     */
    String MC_TIPPED_ARROW_EFFECT_INVISIBILITY = "minecraft:tipped_arrow.effect.invisibility";

    /**
     * Arrow of Leaping
     */
    String MC_TIPPED_ARROW_EFFECT_JUMP = "minecraft:tipped_arrow.effect.jump";

    /**
     * Arrow of Levitation
     */
    String MC_TIPPED_ARROW_EFFECT_LEVITATION = "minecraft:tipped_arrow.effect.levitation";

    /**
     * Arrow of Luck
     */
    String MC_TIPPED_ARROW_EFFECT_LUCK = "minecraft:tipped_arrow.effect.luck";

    /**
     * Arrow of Slowness
     */
    String MC_TIPPED_ARROW_EFFECT_MOVESLOWDOWN = "minecraft:tipped_arrow.effect.moveSlowdown";

    /**
     * Arrow of Swiftness
     */
    String MC_TIPPED_ARROW_EFFECT_MOVESPEED = "minecraft:tipped_arrow.effect.moveSpeed";

    /**
     * Tipped Arrow
     */
    String MC_TIPPED_ARROW_EFFECT_MUNDANE = "minecraft:tipped_arrow.effect.mundane";

    /**
     * Arrow of Night Vision
     */
    String MC_TIPPED_ARROW_EFFECT_NIGHTVISION = "minecraft:tipped_arrow.effect.nightVision";

    /**
     * Arrow of Oozing
     */
    String MC_TIPPED_ARROW_EFFECT_OOZING = "minecraft:tipped_arrow.effect.oozing";

    /**
     * Arrow of Poison
     */
    String MC_TIPPED_ARROW_EFFECT_POISON = "minecraft:tipped_arrow.effect.poison";

    /**
     * Arrow of Regeneration
     */
    String MC_TIPPED_ARROW_EFFECT_REGENERATION = "minecraft:tipped_arrow.effect.regeneration";

    /**
     * Arrow of Slow Falling
     */
    String MC_TIPPED_ARROW_EFFECT_SLOWFALLING = "minecraft:tipped_arrow.effect.slowFalling";

    /**
     * Tipped Arrow
     */
    String MC_TIPPED_ARROW_EFFECT_THICK = "minecraft:tipped_arrow.effect.thick";

    /**
     * Arrow of the Turtle Master
     */
    String MC_TIPPED_ARROW_EFFECT_TURTLEMASTER = "minecraft:tipped_arrow.effect.turtleMaster";

    /**
     * Arrow of Splashing
     */
    String MC_TIPPED_ARROW_EFFECT_WATER = "minecraft:tipped_arrow.effect.water";

    /**
     * Arrow of Water Breathing
     */
    String MC_TIPPED_ARROW_EFFECT_WATERBREATHING = "minecraft:tipped_arrow.effect.waterBreathing";

    /**
     * Arrow of Weakness
     */
    String MC_TIPPED_ARROW_EFFECT_WEAKNESS = "minecraft:tipped_arrow.effect.weakness";

    /**
     * Arrow of Weaving
     */
    String MC_TIPPED_ARROW_EFFECT_WEAVING = "minecraft:tipped_arrow.effect.weaving";

    /**
     * Arrow of Wind Charging
     */
    String MC_TIPPED_ARROW_EFFECT_WINDCHARGED = "minecraft:tipped_arrow.effect.windCharged";

    /**
     * Arrow of Decay
     */
    String MC_TIPPED_ARROW_EFFECT_WITHER = "minecraft:tipped_arrow.effect.wither";

    /**
     * Trader does not want this
     */
    String MC_TRADE_DOESNOTWANT = "minecraft:trade.doesNotWant";

    /**
     * !
     */
    String MC_TRADE_EXCALAMATION = "minecraft:trade.excalamation";

    /**
     * Experience: MAX
     */
    String MC_TRADE_EXPMAX = "minecraft:trade.expMax";

    /**
     * Experience: %d/%d
     */
    String MC_TRADE_EXPPROGRESS = "minecraft:trade.expProgress";

    /**
     * Novice
     */
    String MC_TRADE_LEVEL_1 = "minecraft:trade.level.1";

    /**
     * Apprentice
     */
    String MC_TRADE_LEVEL_2 = "minecraft:trade.level.2";

    /**
     * Journeyman
     */
    String MC_TRADE_LEVEL_3 = "minecraft:trade.level.3";

    /**
     * Expert
     */
    String MC_TRADE_LEVEL_4 = "minecraft:trade.level.4";

    /**
     * Master
     */
    String MC_TRADE_LEVEL_5 = "minecraft:trade.level.5";

    /**
     * Level %d
     */
    String MC_TRADE_LEVELPREFIX = "minecraft:trade.levelPrefix";

    /**
     * Level %d - %s
     */
    String MC_TRADE_LEVELPREFIXANDNAME = "minecraft:trade.levelPrefixAndName";

    /**
     * Max Level - %s
     */
    String MC_TRADE_LEVELPREFIXANDNAME_MAX = "minecraft:trade.levelPrefixAndName.max";

    /**
     * dab
     */
    String MC_TRADE_MYSTERIOUSTEXT = "minecraft:trade.mysteriousText";

    /**
     * %s - %s
     */
    String MC_TRADE_NAMEANDLEVEL = "minecraft:trade.nameAndLevel";

    /**
     * Not enough %s
     */
    String MC_TRADE_NOTENOUGH = "minecraft:trade.notEnough";

    /**
     * ?
     */
    String MC_TRADE_QUESTION = "minecraft:trade.question";

    /**
     * Trade
     */
    String MC_TRADE_TRADE = "minecraft:trade.trade";

    /**
     * %s %s
     */
    String MC_TRANSLATION_TEST_ARGS = "minecraft:translation.test.args";

    /**
     * Prefix, %s%2#s again %s and %1#s lastly %s and also %1#s again!
     */
    String MC_TRANSLATION_TEST_COMPLEX = "minecraft:translation.test.complex";

    /**
     * %%s %%%s %%%%s %%%%%s
     */
    String MC_TRANSLATION_TEST_ESCAPE = "minecraft:translation.test.escape";

    /**
     * hi %
     */
    String MC_TRANSLATION_TEST_INVALID = "minecraft:translation.test.invalid";

    /**
     * hi %  s
     */
    String MC_TRANSLATION_TEST_INVALID2 = "minecraft:translation.test.invalid2";

    /**
     * Hello, world!
     */
    String MC_TRANSLATION_TEST_NONE = "minecraft:translation.test.none";

    /**
     * world
     */
    String MC_TRANSLATION_TEST_WORLD = "minecraft:translation.test.world";

    /**
     * Travel to new World
     */
    String MC_TRAVEL_TOAST = "minecraft:travel.toast";

    /**
     * Amethyst Material
     */
    String MC_TRIM_MATERIAL_AMETHYST_NAME = "minecraft:trim_material.amethyst.name";

    /**
     * Copper Material
     */
    String MC_TRIM_MATERIAL_COPPER_NAME = "minecraft:trim_material.copper.name";

    /**
     * Diamond Material
     */
    String MC_TRIM_MATERIAL_DIAMOND_NAME = "minecraft:trim_material.diamond.name";

    /**
     * Emerald Material
     */
    String MC_TRIM_MATERIAL_EMERALD_NAME = "minecraft:trim_material.emerald.name";

    /**
     * Gold Material
     */
    String MC_TRIM_MATERIAL_GOLD_NAME = "minecraft:trim_material.gold.name";

    /**
     * Iron Material
     */
    String MC_TRIM_MATERIAL_IRON_NAME = "minecraft:trim_material.iron.name";

    /**
     * Lapis Material
     */
    String MC_TRIM_MATERIAL_LAPIS_NAME = "minecraft:trim_material.lapis.name";

    /**
     * Netherite Material
     */
    String MC_TRIM_MATERIAL_NETHERITE_NAME = "minecraft:trim_material.netherite.name";

    /**
     * Quartz Material
     */
    String MC_TRIM_MATERIAL_QUARTZ_NAME = "minecraft:trim_material.quartz.name";

    /**
     * Redstone Material
     */
    String MC_TRIM_MATERIAL_REDSTONE_NAME = "minecraft:trim_material.redstone.name";

    /**
     * Resin Material
     */
    String MC_TRIM_MATERIAL_RESIN_NAME = "minecraft:trim_material.resin.name";

    /**
     * Bolt Armor Trim
     */
    String MC_TRIM_PATTERN_BOLT_NAME = "minecraft:trim_pattern.bolt.name";

    /**
     * Coast Armor Trim
     */
    String MC_TRIM_PATTERN_COAST_NAME = "minecraft:trim_pattern.coast.name";

    /**
     * Dune Armor Trim
     */
    String MC_TRIM_PATTERN_DUNE_NAME = "minecraft:trim_pattern.dune.name";

    /**
     * Eye Armor Trim
     */
    String MC_TRIM_PATTERN_EYE_NAME = "minecraft:trim_pattern.eye.name";

    /**
     * Flow Armor Trim
     */
    String MC_TRIM_PATTERN_FLOW_NAME = "minecraft:trim_pattern.flow.name";

    /**
     * Host Armor Trim
     */
    String MC_TRIM_PATTERN_HOST_NAME = "minecraft:trim_pattern.host.name";

    /**
     * Raiser Armor Trim
     */
    String MC_TRIM_PATTERN_RAISER_NAME = "minecraft:trim_pattern.raiser.name";

    /**
     * Rib Armor Trim
     */
    String MC_TRIM_PATTERN_RIB_NAME = "minecraft:trim_pattern.rib.name";

    /**
     * Sentry Armor Trim
     */
    String MC_TRIM_PATTERN_SENTRY_NAME = "minecraft:trim_pattern.sentry.name";

    /**
     * Shaper Armor Trim
     */
    String MC_TRIM_PATTERN_SHAPER_NAME = "minecraft:trim_pattern.shaper.name";

    /**
     * Silence Armor Trim
     */
    String MC_TRIM_PATTERN_SILENCE_NAME = "minecraft:trim_pattern.silence.name";

    /**
     * Snout Armor Trim
     */
    String MC_TRIM_PATTERN_SNOUT_NAME = "minecraft:trim_pattern.snout.name";

    /**
     * Spire Armor Trim
     */
    String MC_TRIM_PATTERN_SPIRE_NAME = "minecraft:trim_pattern.spire.name";

    /**
     * Tide Armor Trim
     */
    String MC_TRIM_PATTERN_TIDE_NAME = "minecraft:trim_pattern.tide.name";

    /**
     * Vex Armor Trim
     */
    String MC_TRIM_PATTERN_VEX_NAME = "minecraft:trim_pattern.vex.name";

    /**
     * Ward Armor Trim
     */
    String MC_TRIM_PATTERN_WARD_NAME = "minecraft:trim_pattern.ward.name";

    /**
     * Wayfinder Armor Trim
     */
    String MC_TRIM_PATTERN_WAYFINDER_NAME = "minecraft:trim_pattern.wayfinder.name";

    /**
     * Wild Armor Trim
     */
    String MC_TRIM_PATTERN_WILD_NAME = "minecraft:trim_pattern.wild.name";

    /**
     * Chunk still loading. You may encounter some mild performance issues
     */
    String MC_UNLOADEDCHUNK_MESSAGE_WARNING = "minecraft:unloadedchunk.message.warning";

    /**
     * We detected that your user settings have become corrupted but failed to recover them.
     */
    String MC_USERDATA_UNRECOVERABLE_TEXT = "minecraft:userdata.unrecoverable.text";

    /**
     * The requested operation is estimated to take %d minutes to complete. Please do not turn off your device during this time.
     */
    String MC_WRITETHROTTLING_OSDIALOG_BODY = "minecraft:writeThrottling.osDialog.body";

    /**
     * Cancel Operation
     */
    String MC_WRITETHROTTLING_OSDIALOG_CANCEL = "minecraft:writeThrottling.osDialog.cancel";

    /**
     * Proceed
     */
    String MC_WRITETHROTTLING_OSDIALOG_CONFIRM = "minecraft:writeThrottling.osDialog.confirm";

    /**
     * Getting your files ready...
     */
    String MC_WRITETHROTTLING_PROGRESSBAR_BODY = "minecraft:writeThrottling.progressBar.body";

    /**
     * Preparing Data
     */
    String MC_WRITETHROTTLING_PROGRESSBAR_TITLE = "minecraft:writeThrottling.progressBar.title";
}
