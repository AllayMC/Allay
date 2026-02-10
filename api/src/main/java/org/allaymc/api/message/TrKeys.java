package org.allaymc.api.message;

import org.allaymc.api.annotation.MinecraftVersionSensitive;

@MinecraftVersionSensitive
public interface TrKeys {
    /**
     * {@literal Freed %1 MB of memory}
     */
    String ALLAY_COMMAND_GC_COMPLETED = "allay:command.gc.completed";

    /**
     * {@literal Perform a JVM garbage collection immediately}
     */
    String ALLAY_COMMAND_GC_DESCRIPTION = "allay:command.gc.description";

    /**
     * {@literal This command can only be executed by an entity!}
     */
    String ALLAY_COMMAND_GENERIC_SENDER_NOTENTITY = "allay:command.generic.sender.notEntity";

    /**
     * {@literal This command can only be executed by a player!}
     */
    String ALLAY_COMMAND_GENERIC_SENDER_NOTPLAYER = "allay:command.generic.sender.notPlayer";

    /**
     * {@literal This command can only be executed by the server!}
     */
    String ALLAY_COMMAND_GENERIC_SENDER_NOTSERVER = "allay:command.generic.sender.notServer";

    /**
     * {@literal Manage plugins}
     */
    String ALLAY_COMMAND_PLUGIN_DESCRIPTION = "allay:command.plugin.description";

    /**
     * {@literal Get the status of the server}
     */
    String ALLAY_COMMAND_STATUS_DESCRIPTION = "allay:command.status.description";

    /**
     * {@literal Show TPS or MSPT of the current world}
     */
    String ALLAY_COMMAND_TPS_DESCRIPTION = "allay:command.tps.description";

    /**
     * {@literal Query server version information}
     */
    String ALLAY_COMMAND_VERSION_DESCRIPTION = "allay:command.version.description";

    /**
     * {@literal This server is running §b%1§r §a%2§r with §ballay-api§r §a%3§r. Supports version §a%4§r (protocol version §a%5§r)}
     */
    String ALLAY_COMMAND_VERSION_OUTPUT = "allay:command.version.output";

    /**
     * {@literal Nether Generator Preset}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_NETHER = "allay:command.world.create.dropdown.generatorpreset.nether";

    /**
     * {@literal Overworld Generator Preset}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_OVERWORLD = "allay:command.world.create.dropdown.generatorpreset.overworld";

    /**
     * {@literal The End Generator Preset}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_THEEND = "allay:command.world.create.dropdown.generatorpreset.theend";

    /**
     * {@literal Nether Generator Type}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_NETHER = "allay:command.world.create.dropdown.generatortype.nether";

    /**
     * {@literal Overworld Generator Type}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_OVERWORLD = "allay:command.world.create.dropdown.generatortype.overworld";

    /**
     * {@literal The End Generator Type}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_THEEND = "allay:command.world.create.dropdown.generatortype.theend";

    /**
     * {@literal Storage Type}
     */
    String ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_STORAGETYPE = "allay:command.world.create.dropdown.storagetype";

    /**
     * {@literal World Name}
     */
    String ALLAY_COMMAND_WORLD_CREATE_INPUT_NAME = "allay:command.world.create.input.name";

    /**
     * {@literal Create New World}
     */
    String ALLAY_COMMAND_WORLD_CREATE_TITLE = "allay:command.world.create.title";

    /**
     * {@literal Enable Nether}
     */
    String ALLAY_COMMAND_WORLD_CREATE_TOGGLE_ENABLE_NETHER = "allay:command.world.create.toggle.enable.nether";

    /**
     * {@literal Enable The End}
     */
    String ALLAY_COMMAND_WORLD_CREATE_TOGGLE_ENABLE_THEEND = "allay:command.world.create.toggle.enable.theend";

    /**
     * {@literal Manage worlds}
     */
    String ALLAY_COMMAND_WORLD_DESCRIPTION = "allay:command.world.description";

    /**
     * {@literal Dimension is disabled: %1}
     */
    String ALLAY_COMMAND_WORLD_DIM_DISABLED = "allay:command.world.dim.disabled";

    /**
     * {@literal Unknown dimension: %1}
     */
    String ALLAY_COMMAND_WORLD_DIM_UNKNOWN = "allay:command.world.dim.unknown";

    /**
     * {@literal Loaded worlds:}
     */
    String ALLAY_COMMAND_WORLD_LIST = "allay:command.world.list";

    /**
     * {@literal You are teleported to world %1:%2}
     */
    String ALLAY_COMMAND_WORLD_TP_SUCCESS = "allay:command.world.tp.success";

    /**
     * {@literal Unknown world: %1}
     */
    String ALLAY_COMMAND_WORLD_UNKNOWN = "allay:command.world.unknown";

    /**
     * {@literal Default world can't be unloaded}
     */
    String ALLAY_COMMAND_WORLD_UNLOAD_FAILED_DEFAULT = "allay:command.world.unload.failed.default";

    /**
     * {@literal Your IP have been banned from this server}
     */
    String ALLAY_DISCONNECT_BANIP = "allay:disconnect.banIp";

    /**
     * {@literal Error while constructing extension instance for extension %1. Error: %2}
     */
    String ALLAY_EXTENSION_CONSTRUCT_INSTANCE_ERROR = "allay:extension.construct.instance.error";

    /**
     * {@literal Can't find the entrance of extension %1}
     */
    String ALLAY_EXTENSION_ENTRANCE_MISSING = "allay:extension.entrance.missing";

    /**
     * {@literal loading extension %1}
     */
    String ALLAY_EXTENSION_LOADING = "allay:extension.loading";

    /**
     * {@literal The entrance class of extension %1 is not a subclass of PluginExtension}
     */
    String ALLAY_EXTENSION_MAINCLASS_TYPEINVALID = "allay:extension.mainclass.typeinvalid";

    /**
     * {@literal Chunk}
     */
    String ALLAY_GUI_CHUNK = "allay:gui.chunk";

    /**
     * {@literal Loaded Chunk Count: %1}
     */
    String ALLAY_GUI_CHUNK_LABEL = "allay:gui.chunk.label";

    /**
     * {@literal Console}
     */
    String ALLAY_GUI_CONSOLE = "allay:gui.console";

    /**
     * {@literal Change font size}
     */
    String ALLAY_GUI_CONSOLE_CHANGEFONTSIZE = "allay:gui.console.changefontsize";

    /**
     * {@literal Please input the font size you want:}
     */
    String ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_DIALOG = "allay:gui.console.changefontsize.dialog";

    /**
     * {@literal Invalid font size!}
     */
    String ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_ERROR_DIALOG = "allay:gui.console.changefontsize.error.dialog";

    /**
     * {@literal Clear the console}
     */
    String ALLAY_GUI_CONSOLE_CLEAR = "allay:gui.console.clear";

    /**
     * {@literal Entity}
     */
    String ALLAY_GUI_ENTITY = "allay:gui.entity";

    /**
     * {@literal Loader Entity Count: %1}
     */
    String ALLAY_GUI_ENTITY_LABEL = "allay:gui.entity.label";

    /**
     * {@literal Error}
     */
    String ALLAY_GUI_ERROR = "allay:gui.error";

    /**
     * {@literal Information}
     */
    String ALLAY_GUI_INFO = "allay:gui.info";

    /**
     * {@literal Memory}
     */
    String ALLAY_GUI_MEMORY = "allay:gui.memory";

    /**
     * {@literal Dashboard}
     */
    String ALLAY_GUI_NAME = "allay:gui.name";

    /**
     * {@literal Performance}
     */
    String ALLAY_GUI_PERFORMANCE = "allay:gui.performance";

    /**
     * {@literal Player}
     */
    String ALLAY_GUI_PLAYER = "allay:gui.player";

    /**
     * {@literal Address}
     */
    String ALLAY_GUI_PLAYER_ADDRESS = "allay:gui.player.address";

    /**
     * {@literal Ban}
     */
    String ALLAY_GUI_PLAYER_BAN = "allay:gui.player.ban";

    /**
     * {@literal BanIP}
     */
    String ALLAY_GUI_PLAYER_BANIP = "allay:gui.player.banip";

    /**
     * {@literal Remove OP}
     */
    String ALLAY_GUI_PLAYER_DEOP = "allay:gui.player.deop";

    /**
     * {@literal Dimension}
     */
    String ALLAY_GUI_PLAYER_DIMENSION = "allay:gui.player.dimension";

    /**
     * {@literal Player Information}
     */
    String ALLAY_GUI_PLAYER_INFO = "allay:gui.player.info";

    /**
     * {@literal Kick}
     */
    String ALLAY_GUI_PLAYER_KICK = "allay:gui.player.kick";

    /**
     * {@literal Player Name}
     */
    String ALLAY_GUI_PLAYER_NAME = "allay:gui.player.name";

    /**
     * {@literal Online: %1}
     */
    String ALLAY_GUI_PLAYER_ONLINE = "allay:gui.player.online";

    /**
     * {@literal Set as OP}
     */
    String ALLAY_GUI_PLAYER_OP = "allay:gui.player.op";

    /**
     * {@literal Pos}
     */
    String ALLAY_GUI_PLAYER_POS = "allay:gui.player.pos";

    /**
     * {@literal UUID}
     */
    String ALLAY_GUI_PLAYER_UUID = "allay:gui.player.uuid";

    /**
     * {@literal World}
     */
    String ALLAY_GUI_PLAYER_WORLD = "allay:gui.player.world";

    /**
     * {@literal Plugin}
     */
    String ALLAY_GUI_PLUGIN = "allay:gui.plugin";

    /**
     * {@literal API Version}
     */
    String ALLAY_GUI_PLUGIN_API_VERSION = "allay:gui.plugin.api_version";

    /**
     * {@literal Authors}
     */
    String ALLAY_GUI_PLUGIN_AUTHORS = "allay:gui.plugin.authors";

    /**
     * {@literal Dependencies}
     */
    String ALLAY_GUI_PLUGIN_DEPENDENCIES = "allay:gui.plugin.dependencies";

    /**
     * {@literal Description}
     */
    String ALLAY_GUI_PLUGIN_DESCRIPTION = "allay:gui.plugin.description";

    /**
     * {@literal Entrance}
     */
    String ALLAY_GUI_PLUGIN_ENTRANCE = "allay:gui.plugin.entrance";

    /**
     * {@literal Plugin Information}
     */
    String ALLAY_GUI_PLUGIN_INFO = "allay:gui.plugin.info";

    /**
     * {@literal Name}
     */
    String ALLAY_GUI_PLUGIN_NAME = "allay:gui.plugin.name";

    /**
     * {@literal Version}
     */
    String ALLAY_GUI_PLUGIN_VERSION = "allay:gui.plugin.version";

    /**
     * {@literal Website}
     */
    String ALLAY_GUI_PLUGIN_WEBSITE = "allay:gui.plugin.website";

    /**
     * {@literal Used: %1mb (%2% free)}
     */
    String ALLAY_GUI_RAM_LABEL = "allay:gui.ram.label";

    /**
     * {@literal Server language was set to %1}
     */
    String ALLAY_LANG_SET = "allay:lang.set";

    /**
     * {@literal Client connected, IP: %1}
     */
    String ALLAY_NETWORK_CLIENT_CONNECTED = "allay:network.client.connected";

    /**
     * {@literal Client disconnected, IP: %1}
     */
    String ALLAY_NETWORK_CLIENT_DISCONNECTED = "allay:network.client.disconnected";

    /**
     * {@literal Network interface started at %1:%2 and [%3]:%4 (%5 ms)}
     */
    String ALLAY_NETWORK_INTERFACE_STARTED = "allay:network.interface.started";

    /**
     * {@literal Network interface started at %1:%2 (%3 ms)}
     */
    String ALLAY_NETWORK_INTERFACE_STARTED_V4ONLY = "allay:network.interface.started.v4only";

    /**
     * {@literal Starting up network interface...}
     */
    String ALLAY_NETWORK_INTERFACE_STARTING = "allay:network.interface.starting";

    /**
     * {@literal Pack %1 is encrypted. Key: %2}
     */
    String ALLAY_PACK_ENCRYPTED = "allay:pack.encrypted";

    /**
     * {@literal Encrypting pack %1}
     */
    String ALLAY_PACK_ENCRYPTING = "allay:pack.encrypting";

    /**
     * {@literal Loaded %1 packs}
     */
    String ALLAY_PACK_LOADED = "allay:pack.loaded";

    /**
     * {@literal Pack loaded successfully %1}
     */
    String ALLAY_PACK_LOADED_ENTRY = "allay:pack.loaded.entry";

    /**
     * {@literal Loading packs...}
     */
    String ALLAY_PACK_LOADING = "allay:pack.loading";

    /**
     * {@literal Loading pack %1}
     */
    String ALLAY_PACK_LOADING_ENTRY = "allay:pack.loading.entry";

    /**
     * {@literal The current API version %1 does not meet the API version requirement for plugin %2: %3}
     */
    String ALLAY_PLUGIN_API_VERSION_MISMATCH = "allay:plugin.api_version.mismatch";

    /**
     * {@literal Error while constructing plugin instance for plugin %1. Error: %2}
     */
    String ALLAY_PLUGIN_CONSTRUCT_INSTANCE_ERROR = "allay:plugin.construct.instance.error";

    /**
     * {@literal Circular dependencies appear in plugin %1: %2 The plugin will skip loading!}
     */
    String ALLAY_PLUGIN_DEPENDENCY_CIRCULAR = "allay:plugin.dependency.circular";

    /**
     * {@literal Plugin %1 is depending on plugin %2, which is not found}
     */
    String ALLAY_PLUGIN_DEPENDENCY_MISSING = "allay:plugin.dependency.missing";

    /**
     * {@literal Plugin %1 expects dependency %2 version in %3, but got version %4}
     */
    String ALLAY_PLUGIN_DEPENDENCY_VERSION_MISMATCH = "allay:plugin.dependency.version.mismatch";

    /**
     * {@literal Error while loading the descriptor of plugin %1: %2}
     */
    String ALLAY_PLUGIN_DESCRIPTOR_ERROR = "allay:plugin.descriptor.error";

    /**
     * {@literal Error while disabling plugin %1. Error: %2}
     */
    String ALLAY_PLUGIN_DISABLE_ERROR = "allay:plugin.disable.error";

    /**
     * {@literal Disabling plugin %1}
     */
    String ALLAY_PLUGIN_DISABLING = "allay:plugin.disabling";

    /**
     * {@literal Find duplicate plugin %1}
     */
    String ALLAY_PLUGIN_DUPLICATE = "allay:plugin.duplicate";

    /**
     * {@literal Error while enabling plugin %1. Error: %2}
     */
    String ALLAY_PLUGIN_ENABLE_ERROR = "allay:plugin.enable.error";

    /**
     * {@literal Enabling plugin %1}
     */
    String ALLAY_PLUGIN_ENABLING = "allay:plugin.enabling";

    /**
     * {@literal Can't find the entrance of plugin %1}
     */
    String ALLAY_PLUGIN_ENTRANCE_MISSING = "allay:plugin.entrance.missing";

    /**
     * {@literal The entrance class of plugin %1 is not a subclass of Plugin}
     */
    String ALLAY_PLUGIN_JAR_ENTRANCE_TYPEINVALID = "allay:plugin.jar.entrance.typeinvalid";

    /**
     * {@literal Error while loading plugin %1. Error: %2}
     */
    String ALLAY_PLUGIN_LOAD_ERROR = "allay:plugin.load.error";

    /**
     * {@literal Loading plugin %1}
     */
    String ALLAY_PLUGIN_LOADING = "allay:plugin.loading";

    /**
     * {@literal §eYou are running a development version. The development version may have unexpected bugs, please do not use it in a production environment!}
     */
    String ALLAY_SERVER_IS_DEV_VERSION = "allay:server.is_dev_version";

    /**
     * {@literal Starting up Minecraft: Bedrock Edition server}
     */
    String ALLAY_SERVER_STARTING = "allay:server.starting";

    /**
     * {@literal Server stopped}
     */
    String ALLAY_SERVER_STOPPED = "allay:server.stopped";

    /**
     * {@literal World %1 is loaded}
     */
    String ALLAY_WORLD_LOADED = "allay:world.loaded";

    /**
     * {@literal Loading world %1}
     */
    String ALLAY_WORLD_LOADING = "allay:world.loading";

    /**
     * {@literal World %1 is unloaded}
     */
    String ALLAY_WORLD_UNLOADED = "allay:world.unloaded";

    /**
     * {@literal Unloading world %1}
     */
    String ALLAY_WORLD_UNLOADING = "allay:world.unloading";

    /**
     * {@literal Account data has been copied to your clipboard.}
     */
    String MC_ACCOUNT_TOAST_COPIED_BODY = "minecraft:account.toast.copied.body";

    /**
     * {@literal Copied data to clipboard}
     */
    String MC_ACCOUNT_TOAST_COPIED_TITLE = "minecraft:account.toast.copied.title";

    /**
     * {@literal Acquire Hardware}
     */
    String MC_ACHIEVEMENT_ACQUIREIRON = "minecraft:achievement.acquireIron";

    /**
     * {@literal Smelt an iron ingot}
     */
    String MC_ACHIEVEMENT_ACQUIREIRON_DESC = "minecraft:achievement.acquireIron.desc";

    /**
     * {@literal Alternative Fuel}
     */
    String MC_ACHIEVEMENT_ALTERNATIVEFUEL = "minecraft:achievement.alternativeFuel";

    /**
     * {@literal Power a furnace with a kelp block}
     */
    String MC_ACHIEVEMENT_ALTERNATIVEFUEL_DESC = "minecraft:achievement.alternativeFuel.desc";

    /**
     * {@literal The Lie}
     */
    String MC_ACHIEVEMENT_BAKECAKE = "minecraft:achievement.bakeCake";

    /**
     * {@literal Wheat, sugar, milk and eggs!}
     */
    String MC_ACHIEVEMENT_BAKECAKE_DESC = "minecraft:achievement.bakeCake.desc";

    /**
     * {@literal Into Fire}
     */
    String MC_ACHIEVEMENT_BLAZE_ROD = "minecraft:achievement.blaze_rod";

    /**
     * {@literal Relieve a Blaze of its rod}
     */
    String MC_ACHIEVEMENT_BLAZE_ROD_DESC = "minecraft:achievement.blaze_rod.desc";

    /**
     * {@literal Librarian}
     */
    String MC_ACHIEVEMENT_BOOKCASE = "minecraft:achievement.bookcase";

    /**
     * {@literal Build some bookshelves to improve your enchantment table}
     */
    String MC_ACHIEVEMENT_BOOKCASE_DESC = "minecraft:achievement.bookcase.desc";

    /**
     * {@literal Repopulation}
     */
    String MC_ACHIEVEMENT_BREEDCOW = "minecraft:achievement.breedCow";

    /**
     * {@literal Breed two cows with wheat}
     */
    String MC_ACHIEVEMENT_BREEDCOW_DESC = "minecraft:achievement.breedCow.desc";

    /**
     * {@literal Getting an Upgrade}
     */
    String MC_ACHIEVEMENT_BUILDBETTERPICKAXE = "minecraft:achievement.buildBetterPickaxe";

    /**
     * {@literal Construct a better pickaxe}
     */
    String MC_ACHIEVEMENT_BUILDBETTERPICKAXE_DESC = "minecraft:achievement.buildBetterPickaxe.desc";

    /**
     * {@literal Hot Topic}
     */
    String MC_ACHIEVEMENT_BUILDFURNACE = "minecraft:achievement.buildFurnace";

    /**
     * {@literal Construct a furnace out of eight stone blocks}
     */
    String MC_ACHIEVEMENT_BUILDFURNACE_DESC = "minecraft:achievement.buildFurnace.desc";

    /**
     * {@literal Time to Farm!}
     */
    String MC_ACHIEVEMENT_BUILDHOE = "minecraft:achievement.buildHoe";

    /**
     * {@literal Use planks and sticks to make a hoe}
     */
    String MC_ACHIEVEMENT_BUILDHOE_DESC = "minecraft:achievement.buildHoe.desc";

    /**
     * {@literal Time to Mine!}
     */
    String MC_ACHIEVEMENT_BUILDPICKAXE = "minecraft:achievement.buildPickaxe";

    /**
     * {@literal Use planks and sticks to make a pickaxe}
     */
    String MC_ACHIEVEMENT_BUILDPICKAXE_DESC = "minecraft:achievement.buildPickaxe.desc";

    /**
     * {@literal Time to Strike!}
     */
    String MC_ACHIEVEMENT_BUILDSWORD = "minecraft:achievement.buildSword";

    /**
     * {@literal Use planks and sticks to make a sword}
     */
    String MC_ACHIEVEMENT_BUILDSWORD_DESC = "minecraft:achievement.buildSword.desc";

    /**
     * {@literal Benchmarking}
     */
    String MC_ACHIEVEMENT_BUILDWORKBENCH = "minecraft:achievement.buildWorkBench";

    /**
     * {@literal Craft a workbench with four blocks of planks}
     */
    String MC_ACHIEVEMENT_BUILDWORKBENCH_DESC = "minecraft:achievement.buildWorkBench.desc";

    /**
     * {@literal Delicious Fish}
     */
    String MC_ACHIEVEMENT_COOKFISH = "minecraft:achievement.cookFish";

    /**
     * {@literal Catch and cook fish!}
     */
    String MC_ACHIEVEMENT_COOKFISH_DESC = "minecraft:achievement.cookFish.desc";

    /**
     * {@literal DIAMONDS!}
     */
    String MC_ACHIEVEMENT_DIAMONDS = "minecraft:achievement.diamonds";

    /**
     * {@literal Acquire diamonds with your iron tools}
     */
    String MC_ACHIEVEMENT_DIAMONDS_DESC = "minecraft:achievement.diamonds.desc";

    /**
     * {@literal Diamonds to you!}
     */
    String MC_ACHIEVEMENT_DIAMONDSTOYOU = "minecraft:achievement.diamondsToYou";

    /**
     * {@literal Throw diamonds at another player.}
     */
    String MC_ACHIEVEMENT_DIAMONDSTOYOU_DESC = "minecraft:achievement.diamondsToYou.desc";

    /**
     * {@literal Enchanter}
     */
    String MC_ACHIEVEMENT_ENCHANTMENTS = "minecraft:achievement.enchantments";

    /**
     * {@literal Use a book, obsidian and diamonds to construct an enchantment table}
     */
    String MC_ACHIEVEMENT_ENCHANTMENTS_DESC = "minecraft:achievement.enchantments.desc";

    /**
     * {@literal Adventuring Time}
     */
    String MC_ACHIEVEMENT_EXPLOREALLBIOMES = "minecraft:achievement.exploreAllBiomes";

    /**
     * {@literal Discover all biomes}
     */
    String MC_ACHIEVEMENT_EXPLOREALLBIOMES_DESC = "minecraft:achievement.exploreAllBiomes.desc";

    /**
     * {@literal When Pigs Fly}
     */
    String MC_ACHIEVEMENT_FLYPIG = "minecraft:achievement.flyPig";

    /**
     * {@literal Fly a pig off a cliff}
     */
    String MC_ACHIEVEMENT_FLYPIG_DESC = "minecraft:achievement.flyPig.desc";

    /**
     * {@literal Beaconator}
     */
    String MC_ACHIEVEMENT_FULLBEACON = "minecraft:achievement.fullBeacon";

    /**
     * {@literal Create a full beacon}
     */
    String MC_ACHIEVEMENT_FULLBEACON_DESC = "minecraft:achievement.fullBeacon.desc";

    /**
     * {@literal Achievement get!}
     */
    String MC_ACHIEVEMENT_GET = "minecraft:achievement.get";

    /**
     * {@literal Return to Sender}
     */
    String MC_ACHIEVEMENT_GHAST = "minecraft:achievement.ghast";

    /**
     * {@literal Destroy a Ghast with a fireball}
     */
    String MC_ACHIEVEMENT_GHAST_DESC = "minecraft:achievement.ghast.desc";

    /**
     * {@literal Cow Tipper}
     */
    String MC_ACHIEVEMENT_KILLCOW = "minecraft:achievement.killCow";

    /**
     * {@literal Harvest some leather}
     */
    String MC_ACHIEVEMENT_KILLCOW_DESC = "minecraft:achievement.killCow.desc";

    /**
     * {@literal Monster Hunter}
     */
    String MC_ACHIEVEMENT_KILLENEMY = "minecraft:achievement.killEnemy";

    /**
     * {@literal Attack and destroy a monster}
     */
    String MC_ACHIEVEMENT_KILLENEMY_DESC = "minecraft:achievement.killEnemy.desc";

    /**
     * {@literal The Beginning.}
     */
    String MC_ACHIEVEMENT_KILLWITHER = "minecraft:achievement.killWither";

    /**
     * {@literal Kill the Wither}
     */
    String MC_ACHIEVEMENT_KILLWITHER_DESC = "minecraft:achievement.killWither.desc";

    /**
     * {@literal Bake Bread}
     */
    String MC_ACHIEVEMENT_MAKEBREAD = "minecraft:achievement.makeBread";

    /**
     * {@literal Turn wheat into bread}
     */
    String MC_ACHIEVEMENT_MAKEBREAD_DESC = "minecraft:achievement.makeBread.desc";

    /**
     * {@literal Getting Wood}
     */
    String MC_ACHIEVEMENT_MINEWOOD = "minecraft:achievement.mineWood";

    /**
     * {@literal Attack a tree until a block of wood pops out}
     */
    String MC_ACHIEVEMENT_MINEWOOD_DESC = "minecraft:achievement.mineWood.desc";

    /**
     * {@literal Achievement unlocked}
     */
    String MC_ACHIEVEMENT_NOTIFICATION_DESCRIPTION = "minecraft:achievement.notification.description";

    /**
     * {@literal On A Rail}
     */
    String MC_ACHIEVEMENT_ONARAIL = "minecraft:achievement.onARail";

    /**
     * {@literal Travel by minecart at least 1 km from where you started}
     */
    String MC_ACHIEVEMENT_ONARAIL_DESC = "minecraft:achievement.onARail.desc";

    /**
     * {@literal Taking Inventory}
     */
    String MC_ACHIEVEMENT_OPENINVENTORY = "minecraft:achievement.openInventory";

    /**
     * {@literal Press '%1#s' to open your inventory.}
     */
    String MC_ACHIEVEMENT_OPENINVENTORY_DESC = "minecraft:achievement.openInventory.desc";

    /**
     * {@literal Overkill}
     */
    String MC_ACHIEVEMENT_OVERKILL = "minecraft:achievement.overkill";

    /**
     * {@literal Deal nine hearts of damage in a single hit}
     */
    String MC_ACHIEVEMENT_OVERKILL_DESC = "minecraft:achievement.overkill.desc";

    /**
     * {@literal Overpowered}
     */
    String MC_ACHIEVEMENT_OVERPOWERED = "minecraft:achievement.overpowered";

    /**
     * {@literal Build a Notch apple}
     */
    String MC_ACHIEVEMENT_OVERPOWERED_DESC = "minecraft:achievement.overpowered.desc";

    /**
     * {@literal We Need to Go Deeper}
     */
    String MC_ACHIEVEMENT_PORTAL = "minecraft:achievement.portal";

    /**
     * {@literal Build a portal to the Nether}
     */
    String MC_ACHIEVEMENT_PORTAL_DESC = "minecraft:achievement.portal.desc";

    /**
     * {@literal Local Brewery}
     */
    String MC_ACHIEVEMENT_POTION = "minecraft:achievement.potion";

    /**
     * {@literal Brew a potion}
     */
    String MC_ACHIEVEMENT_POTION_DESC = "minecraft:achievement.potion.desc";

    /**
     * {@literal Requires '%1#s'}
     */
    String MC_ACHIEVEMENT_REQUIRES = "minecraft:achievement.requires";

    /**
     * {@literal Sniper Duel}
     */
    String MC_ACHIEVEMENT_SNIPESKELETON = "minecraft:achievement.snipeSkeleton";

    /**
     * {@literal Kill a skeleton with an arrow from more than 50 meters}
     */
    String MC_ACHIEVEMENT_SNIPESKELETON_DESC = "minecraft:achievement.snipeSkeleton.desc";

    /**
     * {@literal The Beginning?}
     */
    String MC_ACHIEVEMENT_SPAWNWITHER = "minecraft:achievement.spawnWither";

    /**
     * {@literal Spawn the Wither}
     */
    String MC_ACHIEVEMENT_SPAWNWITHER_DESC = "minecraft:achievement.spawnWither.desc";

    /**
     * {@literal Taken!}
     */
    String MC_ACHIEVEMENT_TAKEN = "minecraft:achievement.taken";

    /**
     * {@literal The End?}
     */
    String MC_ACHIEVEMENT_THEEND = "minecraft:achievement.theEnd";

    /**
     * {@literal Locate the End}
     */
    String MC_ACHIEVEMENT_THEEND_DESC = "minecraft:achievement.theEnd.desc";

    /**
     * {@literal The End.}
     */
    String MC_ACHIEVEMENT_THEEND2 = "minecraft:achievement.theEnd2";

    /**
     * {@literal Defeat the Ender Dragon}
     */
    String MC_ACHIEVEMENT_THEEND2_DESC = "minecraft:achievement.theEnd2.desc";

    /**
     * {@literal --}
     */
    String MC_ACHIEVEMENT_UNINITSCORE = "minecraft:achievement.uninitScore";

    /**
     * {@literal ???}
     */
    String MC_ACHIEVEMENT_UNKNOWN = "minecraft:achievement.unknown";

    /**
     * {@literal Tap jump to exit the boat}
     */
    String MC_ACTION_HINT_EXIT_BOAT = "minecraft:action.hint.exit.boat";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_CAMEL = "minecraft:action.hint.exit.camel";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CAMEL_HUSK = "minecraft:action.hint.exit.camel_husk";

    /**
     * {@literal Tap jump to exit the boat}
     */
    String MC_ACTION_HINT_EXIT_CHEST_BOAT = "minecraft:action.hint.exit.chest_boat";

    /**
     * {@literal Press :_input_key.jump: to exit the boat}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_BOAT = "minecraft:action.hint.exit.console.boat";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_CAMEL = "minecraft:action.hint.exit.console.camel";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_CAMEL_HUSK = "minecraft:action.hint.exit.console.camel_husk";

    /**
     * {@literal Press :_input_key.jump: to exit the boat}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_CHEST_BOAT = "minecraft:action.hint.exit.console.chest_boat";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_DONKEY = "minecraft:action.hint.exit.console.donkey";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_HAPPY_GHAST = "minecraft:action.hint.exit.console.happy_ghast";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_HORSE = "minecraft:action.hint.exit.console.horse";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_LLAMA = "minecraft:action.hint.exit.console.llama";

    /**
     * {@literal Press :_input_key.jump: to exit the minecart}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_MINECART = "minecraft:action.hint.exit.console.minecart";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_MULE = "minecraft:action.hint.exit.console.mule";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_NAUTILUS = "minecraft:action.hint.exit.console.nautilus";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_PIG = "minecraft:action.hint.exit.console.pig";

    /**
     * {@literal Press :_input_key.jump: to exit the raft}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_RAFT = "minecraft:action.hint.exit.console.raft";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_SKELETON_HORSE = "minecraft:action.hint.exit.console.skeleton_horse";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_STRIDER = "minecraft:action.hint.exit.console.strider";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_TRADER_LLAMA = "minecraft:action.hint.exit.console.trader_llama";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_CONSOLE_ZOMBIE_NAUTILUS = "minecraft:action.hint.exit.console.zombie_nautilus";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_DONKEY = "minecraft:action.hint.exit.donkey";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_HAPPY_GHAST = "minecraft:action.hint.exit.happy_ghast";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_HORSE = "minecraft:action.hint.exit.horse";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_LLAMA = "minecraft:action.hint.exit.llama";

    /**
     * {@literal Tap jump to exit the minecart}
     */
    String MC_ACTION_HINT_EXIT_MINECART = "minecraft:action.hint.exit.minecart";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_MULE = "minecraft:action.hint.exit.mule";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_NAUTILUS = "minecraft:action.hint.exit.nautilus";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_PIG = "minecraft:action.hint.exit.pig";

    /**
     * {@literal Tap jump to exit the raft}
     */
    String MC_ACTION_HINT_EXIT_RAFT = "minecraft:action.hint.exit.raft";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_CAMEL = "minecraft:action.hint.exit.scheme.camel";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_CAMEL_HUSK = "minecraft:action.hint.exit.scheme.camel_husk";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_DONKEY = "minecraft:action.hint.exit.scheme.donkey";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_HAPPY_GHAST = "minecraft:action.hint.exit.scheme.happy_ghast";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_HORSE = "minecraft:action.hint.exit.scheme.horse";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_LLAMA = "minecraft:action.hint.exit.scheme.llama";

    /**
     * {@literal Tap dismount to exit the minecart}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_MINECART = "minecraft:action.hint.exit.scheme.minecart";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_MULE = "minecraft:action.hint.exit.scheme.mule";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_NAUTILUS = "minecraft:action.hint.exit.scheme.nautilus";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_PIG = "minecraft:action.hint.exit.scheme.pig";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_SKELETON_HORSE = "minecraft:action.hint.exit.scheme.skeleton_horse";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_STRIDER = "minecraft:action.hint.exit.scheme.strider";

    /**
     * {@literal Tap dismount to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_TRADER_LLAMA = "minecraft:action.hint.exit.scheme.trader_llama";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_SCHEME_ZOMBIE_NAUTILUS = "minecraft:action.hint.exit.scheme.zombie_nautilus";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_SKELETON_HORSE = "minecraft:action.hint.exit.skeleton_horse";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_STRIDER = "minecraft:action.hint.exit.strider";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_TRADER_LLAMA = "minecraft:action.hint.exit.trader_llama";

    /**
     * {@literal Press :_input_key.sneak: to dismount}
     */
    String MC_ACTION_HINT_EXIT_ZOMBIE_HORSE = "minecraft:action.hint.exit.zombie_horse";

    /**
     * {@literal Tap sneak to dismount}
     */
    String MC_ACTION_HINT_EXIT_ZOMBIE_NAUTILUS = "minecraft:action.hint.exit.zombie_nautilus";

    /**
     * {@literal Give item to Allay}
     */
    String MC_ACTION_INTERACT_ALLAY = "minecraft:action.interact.allay";

    /**
     * {@literal Equip}
     */
    String MC_ACTION_INTERACT_ARMORSTAND_EQUIP = "minecraft:action.interact.armorstand.equip";

    /**
     * {@literal Pose}
     */
    String MC_ACTION_INTERACT_ARMORSTAND_POSE = "minecraft:action.interact.armorstand.pose";

    /**
     * {@literal Attach Chest}
     */
    String MC_ACTION_INTERACT_ATTACHCHEST = "minecraft:action.interact.attachchest";

    /**
     * {@literal Barter}
     */
    String MC_ACTION_INTERACT_BARTER = "minecraft:action.interact.barter";

    /**
     * {@literal Boost}
     */
    String MC_ACTION_INTERACT_BOOST = "minecraft:action.interact.boost";

    /**
     * {@literal Brush}
     */
    String MC_ACTION_INTERACT_BRUSH = "minecraft:action.interact.brush";

    /**
     * {@literal Create Map}
     */
    String MC_ACTION_INTERACT_CREATEMAP = "minecraft:action.interact.createMap";

    /**
     * {@literal Ignite}
     */
    String MC_ACTION_INTERACT_CREEPER = "minecraft:action.interact.creeper";

    /**
     * {@literal Cure}
     */
    String MC_ACTION_INTERACT_CURE = "minecraft:action.interact.cure";

    /**
     * {@literal Cut }
     */
    String MC_ACTION_INTERACT_CUT = "minecraft:action.interact.cut";

    /**
     * {@literal Drop Item}
     */
    String MC_ACTION_INTERACT_DROP_ITEM = "minecraft:action.interact.drop_item";

    /**
     * {@literal Dye}
     */
    String MC_ACTION_INTERACT_DYE = "minecraft:action.interact.dye";

    /**
     * {@literal Edit}
     */
    String MC_ACTION_INTERACT_EDIT = "minecraft:action.interact.edit";

    /**
     * {@literal Equip Carpet}
     */
    String MC_ACTION_INTERACT_EQUIPCARPET = "minecraft:action.interact.equipcarpet";

    /**
     * {@literal Equip Harness}
     */
    String MC_ACTION_INTERACT_EQUIPHARNESS = "minecraft:action.interact.equipharness";

    /**
     * {@literal Equip Horse Armor}
     */
    String MC_ACTION_INTERACT_EQUIPHORSEARMOR = "minecraft:action.interact.equiphorsearmor";

    /**
     * {@literal Equip Nautilus Armor}
     */
    String MC_ACTION_INTERACT_EQUIPNAUTILUSARMOR = "minecraft:action.interact.equipnautilusarmor";

    /**
     * {@literal Equip Wolf Armor}
     */
    String MC_ACTION_INTERACT_EQUIPWOLFARMOR = "minecraft:action.interact.equipwolfarmor";

    /**
     * {@literal Leave Boat}
     */
    String MC_ACTION_INTERACT_EXIT_BOAT = "minecraft:action.interact.exit.boat";

    /**
     * {@literal Leave Raft}
     */
    String MC_ACTION_INTERACT_EXIT_RAFT = "minecraft:action.interact.exit.raft";

    /**
     * {@literal Feed}
     */
    String MC_ACTION_INTERACT_FEED = "minecraft:action.interact.feed";

    /**
     * {@literal Fish}
     */
    String MC_ACTION_INTERACT_FISHING = "minecraft:action.interact.fishing";

    /**
     * {@literal Leash}
     */
    String MC_ACTION_INTERACT_LEASH = "minecraft:action.interact.leash";

    /**
     * {@literal Milk}
     */
    String MC_ACTION_INTERACT_MILK = "minecraft:action.interact.milk";

    /**
     * {@literal Shear}
     */
    String MC_ACTION_INTERACT_MOOSHEAR = "minecraft:action.interact.mooshear";

    /**
     * {@literal Milk Stew}
     */
    String MC_ACTION_INTERACT_MOOSTEW = "minecraft:action.interact.moostew";

    /**
     * {@literal Mount}
     */
    String MC_ACTION_INTERACT_MOUNT = "minecraft:action.interact.mount";

    /**
     * {@literal Name}
     */
    String MC_ACTION_INTERACT_NAME = "minecraft:action.interact.name";

    /**
     * {@literal Open}
     */
    String MC_ACTION_INTERACT_OPENCONTAINER = "minecraft:action.interact.opencontainer";

    /**
     * {@literal Read}
     */
    String MC_ACTION_INTERACT_READ = "minecraft:action.interact.read";

    /**
     * {@literal Remove Carpet }
     */
    String MC_ACTION_INTERACT_REMOVECARPET = "minecraft:action.interact.removecarpet";

    /**
     * {@literal Remove Harness}
     */
    String MC_ACTION_INTERACT_REMOVEHARNESS = "minecraft:action.interact.removeharness";

    /**
     * {@literal Remove Horse Armor }
     */
    String MC_ACTION_INTERACT_REMOVEHORSEARMOR = "minecraft:action.interact.removehorsearmor";

    /**
     * {@literal Remove Nautilus Armor}
     */
    String MC_ACTION_INTERACT_REMOVENAUTILUSARMOR = "minecraft:action.interact.removenautilusarmor";

    /**
     * {@literal Remove Saddle }
     */
    String MC_ACTION_INTERACT_REMOVESADDLE = "minecraft:action.interact.removesaddle";

    /**
     * {@literal Remove Wolf Armor}
     */
    String MC_ACTION_INTERACT_REMOVEWOLFARMOR = "minecraft:action.interact.removewolfarmor";

    /**
     * {@literal Repair}
     */
    String MC_ACTION_INTERACT_REPAIR = "minecraft:action.interact.repair";

    /**
     * {@literal Repair Wolf Armor}
     */
    String MC_ACTION_INTERACT_REPAIRWOLFARMOR = "minecraft:action.interact.repairwolfarmor";

    /**
     * {@literal Retrieve }
     */
    String MC_ACTION_INTERACT_RETRIEVE = "minecraft:action.interact.retrieve";

    /**
     * {@literal Board}
     */
    String MC_ACTION_INTERACT_RIDE_BOAT = "minecraft:action.interact.ride.boat";

    /**
     * {@literal Ride}
     */
    String MC_ACTION_INTERACT_RIDE_HORSE = "minecraft:action.interact.ride.horse";

    /**
     * {@literal Ride}
     */
    String MC_ACTION_INTERACT_RIDE_MINECART = "minecraft:action.interact.ride.minecart";

    /**
     * {@literal Ride}
     */
    String MC_ACTION_INTERACT_RIDE_STRIDER = "minecraft:action.interact.ride.strider";

    /**
     * {@literal Saddle}
     */
    String MC_ACTION_INTERACT_SADDLE = "minecraft:action.interact.saddle";

    /**
     * {@literal Scrape}
     */
    String MC_ACTION_INTERACT_SCRAPE = "minecraft:action.interact.scrape";

    /**
     * {@literal Shear}
     */
    String MC_ACTION_INTERACT_SHEAR = "minecraft:action.interact.shear";

    /**
     * {@literal Sit}
     */
    String MC_ACTION_INTERACT_SIT = "minecraft:action.interact.sit";

    /**
     * {@literal Stand}
     */
    String MC_ACTION_INTERACT_STAND = "minecraft:action.interact.stand";

    /**
     * {@literal Take Picture}
     */
    String MC_ACTION_INTERACT_TAKEPICTURE = "minecraft:action.interact.takepicture";

    /**
     * {@literal Talk}
     */
    String MC_ACTION_INTERACT_TALK = "minecraft:action.interact.talk";

    /**
     * {@literal Tame}
     */
    String MC_ACTION_INTERACT_TAME = "minecraft:action.interact.tame";

    /**
     * {@literal Trade}
     */
    String MC_ACTION_INTERACT_TRADE = "minecraft:action.interact.trade";

    /**
     * {@literal Unleash}
     */
    String MC_ACTION_INTERACT_UNLEASH = "minecraft:action.interact.unleash";

    /**
     * {@literal Use Item}
     */
    String MC_ACTION_INTERACT_USE = "minecraft:action.interact.use";

    /**
     * {@literal Wake Villager}
     */
    String MC_ACTION_INTERACT_WAKEVILLAGER = "minecraft:action.interact.wakevillager";

    /**
     * {@literal Wax Off}
     */
    String MC_ACTION_INTERACT_WAX_OFF = "minecraft:action.interact.wax_off";

    /**
     * {@literal Wax On}
     */
    String MC_ACTION_INTERACT_WAX_ON = "minecraft:action.interact.wax_on";

    /**
     * {@literal Write}
     */
    String MC_ACTION_INTERACT_WRITE = "minecraft:action.interact.write";

    /**
     * {@literal @e = all entities}
     */
    String MC_ADVMODE_ALLENTITIES = "minecraft:advMode.allEntities";

    /**
     * {@literal @a = all players}
     */
    String MC_ADVMODE_ALLPLAYERS = "minecraft:advMode.allPlayers";

    /**
     * {@literal Command Input}
     */
    String MC_ADVMODE_COMMAND = "minecraft:advMode.command";

    /**
     * {@literal Command Input exceeds 500 characters. It is not possible to open the keyboard input on this device type.}
     */
    String MC_ADVMODE_COMMAND_CHARLIMITEXCEEDED = "minecraft:advMode.command.charLimitExceeded";

    /**
     * {@literal @n = nearest entity }
     */
    String MC_ADVMODE_NEAREST = "minecraft:advMode.nearest";

    /**
     * {@literal @p = nearest player}
     */
    String MC_ADVMODE_NEARESTPLAYER = "minecraft:advMode.nearestPlayer";

    /**
     * {@literal Must be an opped player in creative mode}
     */
    String MC_ADVMODE_NOTALLOWED = "minecraft:advMode.notAllowed";

    /**
     * {@literal Command blocks are not enabled on this server}
     */
    String MC_ADVMODE_NOTENABLED = "minecraft:advMode.notEnabled";

    /**
     * {@literal Previous Output}
     */
    String MC_ADVMODE_PREVIOUSOUTPUT = "minecraft:advMode.previousOutput";

    /**
     * {@literal @r = random player}
     */
    String MC_ADVMODE_RANDOMPLAYER = "minecraft:advMode.randomPlayer";

    /**
     * {@literal @s = self}
     */
    String MC_ADVMODE_SELF = "minecraft:advMode.self";

    /**
     * {@literal Set Console Command for Block}
     */
    String MC_ADVMODE_SETCOMMAND = "minecraft:advMode.setCommand";

    /**
     * {@literal Command set: %s}
     */
    String MC_ADVMODE_SETCOMMAND_SUCCESS = "minecraft:advMode.setCommand.success";

    /**
     * {@literal +%d %s}
     */
    String MC_ATTRIBUTE_MODIFIER_PLUS_0 = "minecraft:attribute.modifier.plus.0";

    /**
     * {@literal +%d%% %s}
     */
    String MC_ATTRIBUTE_MODIFIER_PLUS_1 = "minecraft:attribute.modifier.plus.1";

    /**
     * {@literal +%d%% %s}
     */
    String MC_ATTRIBUTE_MODIFIER_PLUS_2 = "minecraft:attribute.modifier.plus.2";

    /**
     * {@literal -%d %s}
     */
    String MC_ATTRIBUTE_MODIFIER_TAKE_0 = "minecraft:attribute.modifier.take.0";

    /**
     * {@literal -%d%% %s}
     */
    String MC_ATTRIBUTE_MODIFIER_TAKE_1 = "minecraft:attribute.modifier.take.1";

    /**
     * {@literal -%d%% %s}
     */
    String MC_ATTRIBUTE_MODIFIER_TAKE_2 = "minecraft:attribute.modifier.take.2";

    /**
     * {@literal Armor}
     */
    String MC_ATTRIBUTE_NAME_GENERIC_ARMOR = "minecraft:attribute.name.generic.armor";

    /**
     * {@literal Armor Toughness}
     */
    String MC_ATTRIBUTE_NAME_GENERIC_ARMORTOUGHNESS = "minecraft:attribute.name.generic.armorToughness";

    /**
     * {@literal Attack Damage}
     */
    String MC_ATTRIBUTE_NAME_GENERIC_ATTACKDAMAGE = "minecraft:attribute.name.generic.attackDamage";

    /**
     * {@literal Knockback Resistance}
     */
    String MC_ATTRIBUTE_NAME_GENERIC_KNOCKBACKRESISTANCE = "minecraft:attribute.name.generic.knockbackResistance";

    /**
     * {@literal Max Health}
     */
    String MC_ATTRIBUTE_NAME_GENERIC_MAXHEALTH = "minecraft:attribute.name.generic.maxHealth";

    /**
     * {@literal Horse Jump Strength}
     */
    String MC_ATTRIBUTE_NAME_HORSE_JUMPSTRENGTH = "minecraft:attribute.name.horse.jumpStrength";

    /**
     * {@literal Zombie Reinforcements}
     */
    String MC_ATTRIBUTE_NAME_ZOMBIE_SPAWNREINFORCEMENTS = "minecraft:attribute.name.zombie.spawnReinforcements";

    /**
     * {@literal Go Back}
     */
    String MC_ATTRIBUTION_GOBACK = "minecraft:attribution.goBack";

    /**
     * {@literal To view attributions, please visit https://minecraft.net/attribution in any web browser.}
     */
    String MC_ATTRIBUTION_VIEWATTRIBUTION = "minecraft:attribution.viewAttribution";

    /**
     * {@literal This is bad. This server failed our verification test. Someone we don't trust might be pretending to be a trusted server.}
     */
    String MC_AUTHSERVER_AUTHFAILED = "minecraft:authserver.authfailed";

    /**
     * {@literal Something went wrong. We can't verify that the server you are trying to connect to is one that we trust. We recommend that you try again later.}
     */
    String MC_AUTHSERVER_NOTAVAILABLE = "minecraft:authserver.notavailable";

    /**
     * {@literal When you see this icon, we are saving your game. Do not turn off your computer while this icon is on screen.}
     */
    String MC_AUTOSAVE_INFO_DESKTOP = "minecraft:autosave.info.desktop";

    /**
     * {@literal When you see this icon, we are saving your game. Do not turn off your device while this icon is on screen.}
     */
    String MC_AUTOSAVE_INFO_GENERAL = "minecraft:autosave.info.general";

    /**
     * {@literal When you see this icon, we are saving your game. Do not turn off your Xbox while this icon is on screen.}
     */
    String MC_AUTOSAVE_INFO_XBOX = "minecraft:autosave.info.xbox";

    /**
     * {@literal Autosave}
     */
    String MC_AUTOSAVE_TITLE = "minecraft:autosave.title";

    /**
     * {@literal Most monsters only come out at night. During the day you're safe... probably.}
     */
    String MC_BEGINNERTIPS_1 = "minecraft:beginnerTips.1";

    /**
     * {@literal Be careful when you swim. Stay under water for too long and you'll run out of air.}
     */
    String MC_BEGINNERTIPS_10 = "minecraft:beginnerTips.10";

    /**
     * {@literal Survival mode has 4 different difficulty levels - Peaceful, Easy, Normal, and Hard. Or play Creative mode and get endless resources!}
     */
    String MC_BEGINNERTIPS_11 = "minecraft:beginnerTips.11";

    /**
     * {@literal The world of Minecraft is endlessly surprising. You'll find lots of different animals, plants, and even §f§k§a§b§r §f§k§a§b§r}
     */
    String MC_BEGINNERTIPS_12 = "minecraft:beginnerTips.12";

    /**
     * {@literal Break blocks, pick up resources. Use the crafting grid to make helpful items. Now you're crafting!}
     */
    String MC_BEGINNERTIPS_2 = "minecraft:beginnerTips.2";

    /**
     * {@literal You have endless lives, but your items drop to the ground when you die. Hurry back to pick them up!}
     */
    String MC_BEGINNERTIPS_3 = "minecraft:beginnerTips.3";

    /**
     * {@literal It's a good idea to build a place where you can safely spend the night.}
     */
    String MC_BEGINNERTIPS_4 = "minecraft:beginnerTips.4";

    /**
     * {@literal Use the right tool for the job! Chopping wood with an axe is much faster than using your hands.}
     */
    String MC_BEGINNERTIPS_5 = "minecraft:beginnerTips.5";

    /**
     * {@literal Even in Minecraft you'll get hungry, so try to find something to eat before that happens.}
     */
    String MC_BEGINNERTIPS_6 = "minecraft:beginnerTips.6";

    /**
     * {@literal Digging straight down or straight up might not be a great idea.}
     */
    String MC_BEGINNERTIPS_7 = "minecraft:beginnerTips.7";

    /**
     * {@literal Monsters spawn in the dark. Craft a torch to brighten up any space.}
     */
    String MC_BEGINNERTIPS_8 = "minecraft:beginnerTips.8";

    /**
     * {@literal Beds are great! Craft one to quickly pass the night and set your respawn point.}
     */
    String MC_BEGINNERTIPS_9 = "minecraft:beginnerTips.9";

    /**
     * {@literal by}
     */
    String MC_BOOK_BYAUTHOR = "minecraft:book.byAuthor";

    /**
     * {@literal Author Unknown}
     */
    String MC_BOOK_DEFAULTAUTHOR = "minecraft:book.defaultAuthor";

    /**
     * {@literal Enter Book Title:}
     */
    String MC_BOOK_EDITTITLE = "minecraft:book.editTitle";

    /**
     * {@literal Export}
     */
    String MC_BOOK_EXPORT = "minecraft:book.export";

    /**
     * {@literal Sign and Close}
     */
    String MC_BOOK_FINALIZEBUTTON = "minecraft:book.finalizeButton";

    /**
     * {@literal Note! When you sign the book, it will no longer be editable.}
     */
    String MC_BOOK_FINALIZEWARNING = "minecraft:book.finalizeWarning";

    /**
     * {@literal Original}
     */
    String MC_BOOK_GENERATION_0 = "minecraft:book.generation.0";

    /**
     * {@literal Copy of original}
     */
    String MC_BOOK_GENERATION_1 = "minecraft:book.generation.1";

    /**
     * {@literal Copy of a copy}
     */
    String MC_BOOK_GENERATION_2 = "minecraft:book.generation.2";

    /**
     * {@literal Tattered}
     */
    String MC_BOOK_GENERATION_3 = "minecraft:book.generation.3";

    /**
     * {@literal From Inventory}
     */
    String MC_BOOK_HEADERINVENTORY = "minecraft:book.headerInventory";

    /**
     * {@literal From Portfolio}
     */
    String MC_BOOK_HEADERPORTFOLIO = "minecraft:book.headerPortfolio";

    /**
     * {@literal Page %1#s of %2#s}
     */
    String MC_BOOK_PAGEINDICATOR = "minecraft:book.pageIndicator";

    /**
     * {@literal Sign}
     */
    String MC_BOOK_SIGNBUTTON = "minecraft:book.signButton";

    /**
     * {@literal [Enter title here]}
     */
    String MC_BOOK_TITLEHERE = "minecraft:book.titleHere";

    /**
     * {@literal Height limit for building is %s blocks}
     */
    String MC_BUILD_TOOHIGH = "minecraft:build.tooHigh";

    /**
     * {@literal Minimum height for building is %s blocks}
     */
    String MC_BUILD_TOOLOW = "minecraft:build.tooLow";

    /**
     * {@literal Build information has been copied to your clipboard.}
     */
    String MC_BUILD_INFO_TOAST_COPIED_BODY = "minecraft:build_info.toast.copied.body";

    /**
     * {@literal Copied data to clipboard}
     */
    String MC_BUILD_INFO_TOAST_COPIED_TITLE = "minecraft:build_info.toast.copied.title";

    /**
     * {@literal Cauldron}
     */
    String MC_CAULDRONSCREEN_HEADER = "minecraft:cauldronScreen.header";

    /**
     * {@literal Edit Text}
     */
    String MC_CHALKBOARDSCREEN_HEADER = "minecraft:chalkboardScreen.header";

    /**
     * {@literal Locked}
     */
    String MC_CHALKBOARDSCREEN_LOCKED = "minecraft:chalkboardScreen.locked";

    /**
     * {@literal Unlocked}
     */
    String MC_CHALKBOARDSCREEN_UNLOCKED = "minecraft:chalkboardScreen.unlocked";

    /**
     * {@literal Cannot send chat message}
     */
    String MC_CHAT_CANNOTSEND = "minecraft:chat.cannotSend";

    /**
     * {@literal The coordinate has been copied}
     */
    String MC_CHAT_COORDINATECOPIEDTOAST = "minecraft:chat.coordinateCopiedToast";

    /**
     * {@literal Block Position}
     */
    String MC_CHAT_COORDINATETYPEFACING = "minecraft:chat.coordinateTypeFacing";

    /**
     * {@literal My Position}
     */
    String MC_CHAT_COORDINATETYPEPOSITION = "minecraft:chat.coordinateTypePosition";

    /**
     * {@literal Invalid Coordinates}
     */
    String MC_CHAT_COORDINATESINVALID = "minecraft:chat.coordinatesInvalid";

    /**
     * {@literal Invalid Coordinates: make sure you are looking at a block}
     */
    String MC_CHAT_COORDINATESINVALIDLONG = "minecraft:chat.coordinatesInvalidLong";

    /**
     * {@literal Copy to Clipboard}
     */
    String MC_CHAT_COPY = "minecraft:chat.copy";

    /**
     * {@literal Are you sure you want to open the following website?}
     */
    String MC_CHAT_LINK_CONFIRM = "minecraft:chat.link.confirm";

    /**
     * {@literal Do you want to open this link or copy it to your clipboard?}
     */
    String MC_CHAT_LINK_CONFIRMTRUSTED = "minecraft:chat.link.confirmTrusted";

    /**
     * {@literal Open in browser}
     */
    String MC_CHAT_LINK_OPEN = "minecraft:chat.link.open";

    /**
     * {@literal Never open links from people that you don't trust!}
     */
    String MC_CHAT_LINK_WARNING = "minecraft:chat.link.warning";

    /**
     * {@literal mention all players}
     */
    String MC_CHAT_MENTIONS_AUTOCOMPLETE_ALLPLAYERS = "minecraft:chat.mentions.autocomplete.allPlayers";

    /**
     * {@literal Mute Chat}
     */
    String MC_CHAT_MUTE = "minecraft:chat.mute";

    /**
     * {@literal This Realm has chat filtering turned off.}
     */
    String MC_CHAT_REALMSFILTERDISABLED = "minecraft:chat.realmsFilterDisabled";

    /**
     * {@literal [OBFUSCATED]}
     */
    String MC_CHAT_REDACTOBFUSCATEDTEXT = "minecraft:chat.redactObfuscatedText";

    /**
     * {@literal You have been temporarily renamed to '%s' on this server}
     */
    String MC_CHAT_RENAMED = "minecraft:chat.renamed";

    /**
     * {@literal Chat Settings}
     */
    String MC_CHAT_SETTINGS = "minecraft:chat.settings";

    /**
     * {@literal Chat Color}
     */
    String MC_CHAT_SETTINGS_CHATCOLOR = "minecraft:chat.settings.chatColor";

    /**
     * {@literal Chat Font}
     */
    String MC_CHAT_SETTINGS_CHATFONT = "minecraft:chat.settings.chatFont";

    /**
     * {@literal Color}
     */
    String MC_CHAT_SETTINGS_COLOR = "minecraft:chat.settings.color";

    /**
     * {@literal Default Chat Color}
     */
    String MC_CHAT_SETTINGS_DEFAULTCHATCOLOR = "minecraft:chat.settings.defaultChatColor";

    /**
     * {@literal Font}
     */
    String MC_CHAT_SETTINGS_FONT = "minecraft:chat.settings.font";

    /**
     * {@literal Font Color}
     */
    String MC_CHAT_SETTINGS_FONTCOLOR = "minecraft:chat.settings.fontColor";

    /**
     * {@literal Size: %s}
     */
    String MC_CHAT_SETTINGS_FONTSIZE = "minecraft:chat.settings.fontSize";

    /**
     * {@literal Size: Available with %s}
     */
    String MC_CHAT_SETTINGS_FONTSIZE_DISABLED = "minecraft:chat.settings.fontSize.disabled";

    /**
     * {@literal Line Spacing}
     */
    String MC_CHAT_SETTINGS_LINESPACING = "minecraft:chat.settings.lineSpacing";

    /**
     * {@literal x%s}
     */
    String MC_CHAT_SETTINGS_LINESPACINGNUMBER = "minecraft:chat.settings.lineSpacingNumber";

    /**
     * {@literal My Mentions}
     */
    String MC_CHAT_SETTINGS_MENTIONS = "minecraft:chat.settings.mentions";

    /**
     * {@literal My Mentions Color}
     */
    String MC_CHAT_SETTINGS_MENTIONSCOLOR = "minecraft:chat.settings.mentionsColor";

    /**
     * {@literal Mute All Chat}
     */
    String MC_CHAT_SETTINGS_MUTEALL = "minecraft:chat.settings.muteAll";

    /**
     * {@literal Mute Emote Chat}
     */
    String MC_CHAT_SETTINGS_MUTEEMOTES = "minecraft:chat.settings.muteEmotes";

    /**
     * {@literal Text To Speech For Chat}
     */
    String MC_CHAT_SETTINGS_TTS = "minecraft:chat.settings.tts";

    /**
     * {@literal Unmute All Chat}
     */
    String MC_CHAT_SETTINGS_UNMUTEALL = "minecraft:chat.settings.unmuteAll";

    /**
     * {@literal (%s) * %s %s}
     */
    String MC_CHAT_STREAM_EMOTE = "minecraft:chat.stream.emote";

    /**
     * {@literal (%s) <%s> %s}
     */
    String MC_CHAT_STREAM_TEXT = "minecraft:chat.stream.text";

    /**
     * {@literal Chat}
     */
    String MC_CHAT_TITLE = "minecraft:chat.title";

    /**
     * {@literal Chat and Commands}
     */
    String MC_CHAT_TITLE_CHEATS = "minecraft:chat.title.cheats";

    /**
     * {@literal %s has just earned the achievement %s}
     */
    String MC_CHAT_TYPE_ACHIEVEMENT = "minecraft:chat.type.achievement";

    /**
     * {@literal %s has lost the achievement %s}
     */
    String MC_CHAT_TYPE_ACHIEVEMENT_TAKEN = "minecraft:chat.type.achievement.taken";

    /**
     * {@literal [%s: %s]}
     */
    String MC_CHAT_TYPE_ADMIN = "minecraft:chat.type.admin";

    /**
     * {@literal [%s] %s}
     */
    String MC_CHAT_TYPE_ANNOUNCEMENT = "minecraft:chat.type.announcement";

    /**
     * {@literal * %s %s}
     */
    String MC_CHAT_TYPE_EMOTE = "minecraft:chat.type.emote";

    /**
     * {@literal %s is sleeping in a bed. To skip to dawn, %s more players need to sleep in beds at the same time.}
     */
    String MC_CHAT_TYPE_SLEEPING = "minecraft:chat.type.sleeping";

    /**
     * {@literal <%s> %s}
     */
    String MC_CHAT_TYPE_TEXT = "minecraft:chat.type.text";

    /**
     * {@literal Large Chest}
     */
    String MC_CHESTSCREEN_HEADER_LARGE = "minecraft:chestScreen.header.large";

    /**
     * {@literal Inventory}
     */
    String MC_CHESTSCREEN_HEADER_PLAYER = "minecraft:chestScreen.header.player";

    /**
     * {@literal Chest}
     */
    String MC_CHESTSCREEN_HEADER_SMALL = "minecraft:chestScreen.header.small";

    /**
     * {@literal Ender Update (%s)}
     */
    String MC_CLIENT_VERSION_1_0 = "minecraft:client.version.1.0";

    /**
     * {@literal Discovery Update (%s)}
     */
    String MC_CLIENT_VERSION_1_1 = "minecraft:client.version.1.1";

    /**
     * {@literal Village & Pillage (%s)}
     */
    String MC_CLIENT_VERSION_1_11 = "minecraft:client.version.1.11";

    /**
     * {@literal Better Together Update (%s)}
     */
    String MC_CLIENT_VERSION_1_2 = "minecraft:client.version.1.2";

    /**
     * {@literal Update Aquatic (%s)}
     */
    String MC_CLIENT_VERSION_1_4 = "minecraft:client.version.1.4";

    /**
     * {@literal Update Aquatic (%s)}
     */
    String MC_CLIENT_VERSION_1_5 = "minecraft:client.version.1.5";

    /**
     * {@literal Update %s}
     */
    String MC_CLIENT_VERSION_GENERIC = "minecraft:client.version.generic";

    /**
     * {@literal Aqua}
     */
    String MC_COLOR_AQUA = "minecraft:color.aqua";

    /**
     * {@literal Black}
     */
    String MC_COLOR_BLACK = "minecraft:color.black";

    /**
     * {@literal Blue}
     */
    String MC_COLOR_BLUE = "minecraft:color.blue";

    /**
     * {@literal Dark Aqua}
     */
    String MC_COLOR_DARK_AQUA = "minecraft:color.dark_aqua";

    /**
     * {@literal Dark Blue}
     */
    String MC_COLOR_DARK_BLUE = "minecraft:color.dark_blue";

    /**
     * {@literal Dark Gray}
     */
    String MC_COLOR_DARK_GRAY = "minecraft:color.dark_gray";

    /**
     * {@literal Dark Green}
     */
    String MC_COLOR_DARK_GREEN = "minecraft:color.dark_green";

    /**
     * {@literal Dark Purple}
     */
    String MC_COLOR_DARK_PURPLE = "minecraft:color.dark_purple";

    /**
     * {@literal Dark Red}
     */
    String MC_COLOR_DARK_RED = "minecraft:color.dark_red";

    /**
     * {@literal Gold}
     */
    String MC_COLOR_GOLD = "minecraft:color.gold";

    /**
     * {@literal Gray}
     */
    String MC_COLOR_GRAY = "minecraft:color.gray";

    /**
     * {@literal Green}
     */
    String MC_COLOR_GREEN = "minecraft:color.green";

    /**
     * {@literal Light Purple}
     */
    String MC_COLOR_LIGHT_PURPLE = "minecraft:color.light_purple";

    /**
     * {@literal Red}
     */
    String MC_COLOR_RED = "minecraft:color.red";

    /**
     * {@literal White}
     */
    String MC_COLOR_WHITE = "minecraft:color.white";

    /**
     * {@literal Yellow}
     */
    String MC_COLOR_YELLOW = "minecraft:color.yellow";

    /**
     * {@literal Failed to list permissions from file, file not found.}
     */
    String MC_COMMAND_PERMISSIONS_LIST_FAIL_FILENOTFOUND = "minecraft:command.permissions.list.fail.filenotfound";

    /**
     * {@literal Failed to reload permissions from file, file not found.}
     */
    String MC_COMMAND_PERMISSIONS_RELOAD_FAIL_FILENOTFOUND = "minecraft:command.permissions.reload.fail.filenotfound";

    /**
     * {@literal Command Block}
     */
    String MC_COMMANDBLOCK_GENERICNAME = "minecraft:commandBlock.genericName";

    /**
     * {@literal @}
     */
    String MC_COMMANDBLOCK_SHORTNAME = "minecraft:commandBlock.shortName";

    /**
     * {@literal Block Type:}
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE = "minecraft:commandBlockScreen.blockType";

    /**
     * {@literal Chain}
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE_CHAIN = "minecraft:commandBlockScreen.blockType.chain";

    /**
     * {@literal Impulse}
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE_IMPULSE = "minecraft:commandBlockScreen.blockType.impulse";

    /**
     * {@literal Repeat}
     */
    String MC_COMMANDBLOCKSCREEN_BLOCKTYPE_REPEAT = "minecraft:commandBlockScreen.blockType.repeat";

    /**
     * {@literal Condition:}
     */
    String MC_COMMANDBLOCKSCREEN_CONDITION = "minecraft:commandBlockScreen.condition";

    /**
     * {@literal Conditional}
     */
    String MC_COMMANDBLOCKSCREEN_CONDITION_CONDITIONAL = "minecraft:commandBlockScreen.condition.conditional";

    /**
     * {@literal Unconditional}
     */
    String MC_COMMANDBLOCKSCREEN_CONDITION_UNCONDITIONAL = "minecraft:commandBlockScreen.condition.unconditional";

    /**
     * {@literal O}
     */
    String MC_COMMANDBLOCKSCREEN_DISPLAYOUTPUTMODE = "minecraft:commandBlockScreen.displayOutputMode";

    /**
     * {@literal Execute on First Tick}
     */
    String MC_COMMANDBLOCKSCREEN_EXECUTEFIRSTTICK = "minecraft:commandBlockScreen.executeFirstTick";

    /**
     * {@literal X}
     */
    String MC_COMMANDBLOCKSCREEN_HIDEOUTPUTMODE = "minecraft:commandBlockScreen.hideOutputMode";

    /**
     * {@literal Hover Note}
     */
    String MC_COMMANDBLOCKSCREEN_HOVERNOTE = "minecraft:commandBlockScreen.hoverNote";

    /**
     * {@literal Redstone:}
     */
    String MC_COMMANDBLOCKSCREEN_REDSTONE = "minecraft:commandBlockScreen.redstone";

    /**
     * {@literal Always Active}
     */
    String MC_COMMANDBLOCKSCREEN_REDSTONE_ALWAYS_ON = "minecraft:commandBlockScreen.redstone.always_on";

    /**
     * {@literal Needs Redstone}
     */
    String MC_COMMANDBLOCKSCREEN_REDSTONE_NEEDS_REDSTONE = "minecraft:commandBlockScreen.redstone.needs_redstone";

    /**
     * {@literal Delay in Ticks:}
     */
    String MC_COMMANDBLOCKSCREEN_TICKDELAY = "minecraft:commandBlockScreen.tickDelay";

    /**
     * {@literal Command Block}
     */
    String MC_COMMANDBLOCKSCREEN_TITLE = "minecraft:commandBlockScreen.title";

    /**
     * {@literal Sets a player's ability.}
     */
    String MC_COMMANDS_ABILITY_DESCRIPTION = "minecraft:commands.ability.description";

    /**
     * {@literal The '%1#s' ability has been granted to you}
     */
    String MC_COMMANDS_ABILITY_GRANTED = "minecraft:commands.ability.granted";

    /**
     * {@literal No ability called '%1#s' is available}
     */
    String MC_COMMANDS_ABILITY_NOABILITY = "minecraft:commands.ability.noability";

    /**
     * {@literal The '%1#s' ability has been revoked from you}
     */
    String MC_COMMANDS_ABILITY_REVOKED = "minecraft:commands.ability.revoked";

    /**
     * {@literal Ability has been updated}
     */
    String MC_COMMANDS_ABILITY_SUCCESS = "minecraft:commands.ability.success";

    /**
     * {@literal Player %1#s already has achievement %2#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_ALREADYHAVE = "minecraft:commands.achievement.alreadyHave";

    /**
     * {@literal Gives or removes an achievement from a player.}
     */
    String MC_COMMANDS_ACHIEVEMENT_DESCRIPTION = "minecraft:commands.achievement.description";

    /**
     * {@literal Player %1#s doesn't have achievement %2#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_DONTHAVE = "minecraft:commands.achievement.dontHave";

    /**
     * {@literal Successfully given all achievements to %1#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_GIVE_SUCCESS_ALL = "minecraft:commands.achievement.give.success.all";

    /**
     * {@literal Successfully given %1#s the stat %2#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_GIVE_SUCCESS_ONE = "minecraft:commands.achievement.give.success.one";

    /**
     * {@literal Player %1#s does not have the stat %2#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_STATTOOLOW = "minecraft:commands.achievement.statTooLow";

    /**
     * {@literal Successfully taken all achievements from %1#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_TAKE_SUCCESS_ALL = "minecraft:commands.achievement.take.success.all";

    /**
     * {@literal Successfully taken the stat %1#s from %2#s}
     */
    String MC_COMMANDS_ACHIEVEMENT_TAKE_SUCCESS_ONE = "minecraft:commands.achievement.take.success.one";

    /**
     * {@literal Unknown achievement or statistic '%1#s'}
     */
    String MC_COMMANDS_ACHIEVEMENT_UNKNOWNACHIEVEMENT = "minecraft:commands.achievement.unknownAchievement";

    /**
     * {@literal Agent failed to attack}
     */
    String MC_COMMANDS_AGENT_ATTACK_FAILED = "minecraft:commands.agent.attack.failed";

    /**
     * {@literal Agent attack successful}
     */
    String MC_COMMANDS_AGENT_ATTACK_SUCCESS = "minecraft:commands.agent.attack.success";

    /**
     * {@literal Agent failed to collect}
     */
    String MC_COMMANDS_AGENT_COLLECT_FAILED = "minecraft:commands.agent.collect.failed";

    /**
     * {@literal Agent collect successful}
     */
    String MC_COMMANDS_AGENT_COLLECT_SUCCESS = "minecraft:commands.agent.collect.success";

    /**
     * {@literal Unable to create Agent}
     */
    String MC_COMMANDS_AGENT_CREATEAGENT_FAILED = "minecraft:commands.agent.createagent.failed";

    /**
     * {@literal Created Agent}
     */
    String MC_COMMANDS_AGENT_CREATEAGENT_SUCCESS = "minecraft:commands.agent.createagent.success";

    /**
     * {@literal Agent destroy failed}
     */
    String MC_COMMANDS_AGENT_DESTROY_FAILED = "minecraft:commands.agent.destroy.failed";

    /**
     * {@literal Agent destroyed a block}
     */
    String MC_COMMANDS_AGENT_DESTROY_SUCCESS = "minecraft:commands.agent.destroy.success";

    /**
     * {@literal Agent failed to detect}
     */
    String MC_COMMANDS_AGENT_DETECT_FAILED = "minecraft:commands.agent.detect.failed";

    /**
     * {@literal Agent detect successful}
     */
    String MC_COMMANDS_AGENT_DETECT_SUCCESS = "minecraft:commands.agent.detect.success";

    /**
     * {@literal Agent failed to detectredstone}
     */
    String MC_COMMANDS_AGENT_DETECTREDSTONE_FAILED = "minecraft:commands.agent.detectredstone.failed";

    /**
     * {@literal Agent detectredstone successful}
     */
    String MC_COMMANDS_AGENT_DETECTREDSTONE_SUCCESS = "minecraft:commands.agent.detectredstone.success";

    /**
     * {@literal Agent failed to drop}
     */
    String MC_COMMANDS_AGENT_DROP_FAILED = "minecraft:commands.agent.drop.failed";

    /**
     * {@literal Agent drop successful}
     */
    String MC_COMMANDS_AGENT_DROP_SUCCESS = "minecraft:commands.agent.drop.success";

    /**
     * {@literal Agent failed to dropall}
     */
    String MC_COMMANDS_AGENT_DROPALL_FAILED = "minecraft:commands.agent.dropall.failed";

    /**
     * {@literal Agent dropall successful}
     */
    String MC_COMMANDS_AGENT_DROPALL_SUCCESS = "minecraft:commands.agent.dropall.success";

    /**
     * {@literal Agent failed to getitemcount}
     */
    String MC_COMMANDS_AGENT_GETITEMCOUNT_FAILED = "minecraft:commands.agent.getitemcount.failed";

    /**
     * {@literal Agent getitemcount successful}
     */
    String MC_COMMANDS_AGENT_GETITEMCOUNT_SUCCESS = "minecraft:commands.agent.getitemcount.success";

    /**
     * {@literal Agent failed to getitemdetail}
     */
    String MC_COMMANDS_AGENT_GETITEMDETAIL_FAILED = "minecraft:commands.agent.getitemdetail.failed";

    /**
     * {@literal Agent getitemdetail successful}
     */
    String MC_COMMANDS_AGENT_GETITEMDETAIL_SUCCESS = "minecraft:commands.agent.getitemdetail.success";

    /**
     * {@literal Agent failed to getitemspace}
     */
    String MC_COMMANDS_AGENT_GETITEMSPACE_FAILED = "minecraft:commands.agent.getitemspace.failed";

    /**
     * {@literal Agent getitemspace successful}
     */
    String MC_COMMANDS_AGENT_GETITEMSPACE_SUCCESS = "minecraft:commands.agent.getitemspace.success";

    /**
     * {@literal Agent getposition failed}
     */
    String MC_COMMANDS_AGENT_GETPOSITION_FAILED = "minecraft:commands.agent.getposition.failed";

    /**
     * {@literal Agent getposition successful}
     */
    String MC_COMMANDS_AGENT_GETPOSITION_SUCCESS = "minecraft:commands.agent.getposition.success";

    /**
     * {@literal Agent failed to inspect}
     */
    String MC_COMMANDS_AGENT_INSPECT_FAILED = "minecraft:commands.agent.inspect.failed";

    /**
     * {@literal Agent inspect successful}
     */
    String MC_COMMANDS_AGENT_INSPECT_SUCCESS = "minecraft:commands.agent.inspect.success";

    /**
     * {@literal Agent failed to inspect data}
     */
    String MC_COMMANDS_AGENT_INSPECTDATA_FAILED = "minecraft:commands.agent.inspectdata.failed";

    /**
     * {@literal Agent inspect data successful}
     */
    String MC_COMMANDS_AGENT_INSPECTDATA_SUCCESS = "minecraft:commands.agent.inspectdata.success";

    /**
     * {@literal Unable to move Agent}
     */
    String MC_COMMANDS_AGENT_MOVE_FAILED = "minecraft:commands.agent.move.failed";

    /**
     * {@literal Agent move successful}
     */
    String MC_COMMANDS_AGENT_MOVE_SUCCESS = "minecraft:commands.agent.move.success";

    /**
     * {@literal Cannot issue command, Agent is out of range}
     */
    String MC_COMMANDS_AGENT_OUTOFRANGE = "minecraft:commands.agent.outofrange";

    /**
     * {@literal Agent failed to place}
     */
    String MC_COMMANDS_AGENT_PLACE_FAILED = "minecraft:commands.agent.place.failed";

    /**
     * {@literal Agent place successful}
     */
    String MC_COMMANDS_AGENT_PLACE_SUCCESS = "minecraft:commands.agent.place.success";

    /**
     * {@literal Agent failed to set item}
     */
    String MC_COMMANDS_AGENT_SETITEM_FAILED = "minecraft:commands.agent.setitem.failed";

    /**
     * {@literal Agent set item successful}
     */
    String MC_COMMANDS_AGENT_SETITEM_SUCCESS = "minecraft:commands.agent.setitem.success";

    /**
     * {@literal Agent failed to till}
     */
    String MC_COMMANDS_AGENT_TILL_FAILED = "minecraft:commands.agent.till.failed";

    /**
     * {@literal Agent till successful}
     */
    String MC_COMMANDS_AGENT_TILL_SUCCESS = "minecraft:commands.agent.till.success";

    /**
     * {@literal Teleport your Agent.}
     */
    String MC_COMMANDS_AGENT_TPAGENT_DESCRIPTION = "minecraft:commands.agent.tpagent.description";

    /**
     * {@literal Agent failed to teleport}
     */
    String MC_COMMANDS_AGENT_TPAGENT_FAILED = "minecraft:commands.agent.tpagent.failed";

    /**
     * {@literal Agent teleported}
     */
    String MC_COMMANDS_AGENT_TPAGENT_SUCCESS = "minecraft:commands.agent.tpagent.success";

    /**
     * {@literal Agent failed to transfer}
     */
    String MC_COMMANDS_AGENT_TRANSFER_FAILED = "minecraft:commands.agent.transfer.failed";

    /**
     * {@literal Agent transfer successful}
     */
    String MC_COMMANDS_AGENT_TRANSFER_SUCCESS = "minecraft:commands.agent.transfer.success";

    /**
     * {@literal Unable to turn Agent}
     */
    String MC_COMMANDS_AGENT_TURN_FAILED = "minecraft:commands.agent.turn.failed";

    /**
     * {@literal Agent turn successful}
     */
    String MC_COMMANDS_AGENT_TURN_SUCCESS = "minecraft:commands.agent.turn.success";

    /**
     * {@literal Enable Aim Assist}
     */
    String MC_COMMANDS_AIMASSIST_DESCRIPTION = "minecraft:commands.aimassist.description";

    /**
     * {@literal Invalid aim assist preset name. Preset name should be in the format "namespace:name" or "name" (defaults to "minecraft" namespace)}
     */
    String MC_COMMANDS_AIMASSIST_ERROR_INVALIDNAMESPACE = "minecraft:commands.aimassist.error.invalidNamespace";

    /**
     * {@literal Unknown aim assist preset name}
     */
    String MC_COMMANDS_AIMASSIST_ERROR_UNKNOWNPRESET = "minecraft:commands.aimassist.error.unknownPreset";

    /**
     * {@literal Aim assist distance must be between 1 and 16}
     */
    String MC_COMMANDS_AIMASSIST_INVALIDDISTANCE = "minecraft:commands.aimassist.invaliddistance";

    /**
     * {@literal Aim assist X angle must be between 10 and 90}
     */
    String MC_COMMANDS_AIMASSIST_INVALIDXANGLE = "minecraft:commands.aimassist.invalidxangle";

    /**
     * {@literal Aim assist Y angle must be between 10 and 90}
     */
    String MC_COMMANDS_AIMASSIST_INVALIDYANGLE = "minecraft:commands.aimassist.invalidyangle";

    /**
     * {@literal Applied aim assist settings to the following players: %s}
     */
    String MC_COMMANDS_AIMASSIST_SUCCESS = "minecraft:commands.aimassist.success";

    /**
     * {@literal Current camera type does not support aim assist. The following types are supported: third_person, fixed_boom, follow_orbit, free}
     */
    String MC_COMMANDS_AIMASSIST_UNSUPPORTEDCAMERATYPE = "minecraft:commands.aimassist.unsupportedCameraType";

    /**
     * {@literal Could not add %1#s to the allowlist}
     */
    String MC_COMMANDS_ALLOWLIST_ADD_FAILED = "minecraft:commands.allowlist.add.failed";

    /**
     * {@literal Added %1#s to the allowlist}
     */
    String MC_COMMANDS_ALLOWLIST_ADD_SUCCESS = "minecraft:commands.allowlist.add.success";

    /**
     * {@literal Manages the server allowlist.}
     */
    String MC_COMMANDS_ALLOWLIST_DESCRIPTION = "minecraft:commands.allowlist.description";

    /**
     * {@literal Turned off the allowlist}
     */
    String MC_COMMANDS_ALLOWLIST_DISABLED = "minecraft:commands.allowlist.disabled";

    /**
     * {@literal Turned on the allowlist}
     */
    String MC_COMMANDS_ALLOWLIST_ENABLED = "minecraft:commands.allowlist.enabled";

    /**
     * {@literal There are %1#d (out of %2#d seen) allowlisted players:}
     */
    String MC_COMMANDS_ALLOWLIST_LIST = "minecraft:commands.allowlist.list";

    /**
     * {@literal Allowlist reloaded from file.}
     */
    String MC_COMMANDS_ALLOWLIST_RELOADED = "minecraft:commands.allowlist.reloaded";

    /**
     * {@literal Could not remove %1#s from the allowlist}
     */
    String MC_COMMANDS_ALLOWLIST_REMOVE_FAILED = "minecraft:commands.allowlist.remove.failed";

    /**
     * {@literal Removed %1#s from the allowlist}
     */
    String MC_COMMANDS_ALLOWLIST_REMOVE_SUCCESS = "minecraft:commands.allowlist.remove.success";

    /**
     * {@literal Day-Night cycle %1#s}
     */
    String MC_COMMANDS_ALWAYS_DAY = "minecraft:commands.always.day";

    /**
     * {@literal Day-Night cycle locked}
     */
    String MC_COMMANDS_ALWAYS_DAY_LOCKED = "minecraft:commands.always.day.locked";

    /**
     * {@literal Day-Night cycle unlocked}
     */
    String MC_COMMANDS_ALWAYS_DAY_UNLOCKED = "minecraft:commands.always.day.unlocked";

    /**
     * {@literal all players}
     */
    String MC_COMMANDS_AUTOCOMPLETE_A = "minecraft:commands.autocomplete.a";

    /**
     * {@literal my Agent}
     */
    String MC_COMMANDS_AUTOCOMPLETE_C = "minecraft:commands.autocomplete.c";

    /**
     * {@literal all entities}
     */
    String MC_COMMANDS_AUTOCOMPLETE_E = "minecraft:commands.autocomplete.e";

    /**
     * {@literal nearest entity}
     */
    String MC_COMMANDS_AUTOCOMPLETE_N = "minecraft:commands.autocomplete.n";

    /**
     * {@literal closest player}
     */
    String MC_COMMANDS_AUTOCOMPLETE_P = "minecraft:commands.autocomplete.p";

    /**
     * {@literal random player}
     */
    String MC_COMMANDS_AUTOCOMPLETE_R = "minecraft:commands.autocomplete.r";

    /**
     * {@literal yourself}
     */
    String MC_COMMANDS_AUTOCOMPLETE_S = "minecraft:commands.autocomplete.s";

    /**
     * {@literal all Agents}
     */
    String MC_COMMANDS_AUTOCOMPLETE_V = "minecraft:commands.autocomplete.v";

    /**
     * {@literal Adds player to banlist.}
     */
    String MC_COMMANDS_BAN_DESCRIPTION = "minecraft:commands.ban.description";

    /**
     * {@literal Could not ban player %1#s}
     */
    String MC_COMMANDS_BAN_FAILED = "minecraft:commands.ban.failed";

    /**
     * {@literal Banned player %1#s}
     */
    String MC_COMMANDS_BAN_SUCCESS = "minecraft:commands.ban.success";

    /**
     * {@literal Adds IP address to banlist.}
     */
    String MC_COMMANDS_BANIP_DESCRIPTION = "minecraft:commands.banip.description";

    /**
     * {@literal You have entered an invalid IP address or a player that is not online}
     */
    String MC_COMMANDS_BANIP_INVALID = "minecraft:commands.banip.invalid";

    /**
     * {@literal Banned IP address %1#s}
     */
    String MC_COMMANDS_BANIP_SUCCESS = "minecraft:commands.banip.success";

    /**
     * {@literal Banned IP address %1#s belonging to %2#s}
     */
    String MC_COMMANDS_BANIP_SUCCESS_PLAYERS = "minecraft:commands.banip.success.players";

    /**
     * {@literal There are %1#d total banned IP addresses:}
     */
    String MC_COMMANDS_BANLIST_IPS = "minecraft:commands.banlist.ips";

    /**
     * {@literal There are %1#d total banned players:}
     */
    String MC_COMMANDS_BANLIST_PLAYERS = "minecraft:commands.banlist.players";

    /**
     * {@literal Modifies the data tag of a block.}
     */
    String MC_COMMANDS_BLOCKDATA_DESCRIPTION = "minecraft:commands.blockdata.description";

    /**
     * {@literal You cannot dig here}
     */
    String MC_COMMANDS_BLOCKDATA_DESTROYFAILED = "minecraft:commands.blockdata.destroyFailed";

    /**
     * {@literal The data tag did not change: %1#s}
     */
    String MC_COMMANDS_BLOCKDATA_FAILED = "minecraft:commands.blockdata.failed";

    /**
     * {@literal The target block is not a data holder block}
     */
    String MC_COMMANDS_BLOCKDATA_NOTVALID = "minecraft:commands.blockdata.notValid";

    /**
     * {@literal Cannot change block outside of the world}
     */
    String MC_COMMANDS_BLOCKDATA_OUTOFWORLD = "minecraft:commands.blockdata.outOfWorld";

    /**
     * {@literal You cannot place blocks here}
     */
    String MC_COMMANDS_BLOCKDATA_PLACEFAILED = "minecraft:commands.blockdata.placeFailed";

    /**
     * {@literal Block data updated to: %1#s}
     */
    String MC_COMMANDS_BLOCKDATA_SUCCESS = "minecraft:commands.blockdata.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_BLOCKDATA_TAGERROR = "minecraft:commands.blockdata.tagError";

    /**
     * {@literal '%1#s' is not a valid Block State.}
     */
    String MC_COMMANDS_BLOCKSTATE_INVALIDSTATE = "minecraft:commands.blockstate.invalidState";

    /**
     * {@literal Block State: '%1#s' is invalid on Block: '%2#s'.}
     */
    String MC_COMMANDS_BLOCKSTATE_STATEERROR = "minecraft:commands.blockstate.stateError";

    /**
     * {@literal Invalid value type for Block State: '%1#s'.}
     */
    String MC_COMMANDS_BLOCKSTATE_TYPEERROR = "minecraft:commands.blockstate.typeError";

    /**
     * {@literal Invalid value for Block State: '%1#s'.}
     */
    String MC_COMMANDS_BLOCKSTATE_VALUEERROR = "minecraft:commands.blockstate.valueError";

    /**
     * {@literal A bossbar already exists with the ID '%1#s'}
     */
    String MC_COMMANDS_BOSSBAR_ADD_FAILURE_EXISTS = "minecraft:commands.bossbar.add.failure.exists";

    /**
     * {@literal Invalid bossbar ID. IDs should be of the form namespace:id or id (defaults to minecraft namespace).}
     */
    String MC_COMMANDS_BOSSBAR_ADD_FAILURE_INVALID = "minecraft:commands.bossbar.add.failure.invalid";

    /**
     * {@literal Created custom bossbar [%1#s]}
     */
    String MC_COMMANDS_BOSSBAR_ADD_SUCCESS = "minecraft:commands.bossbar.add.success";

    /**
     * {@literal Creates and modifies boss bars}
     */
    String MC_COMMANDS_BOSSBAR_DESCRIPTION = "minecraft:commands.bossbar.description";

    /**
     * {@literal Custom bossbar [%1#s] has a maximum of %2#d}
     */
    String MC_COMMANDS_BOSSBAR_GET_MAX = "minecraft:commands.bossbar.get.max";

    /**
     * {@literal Custom bossbar [%1#s] has %2#s players currently online: %3#s}
     */
    String MC_COMMANDS_BOSSBAR_GET_PLAYERS = "minecraft:commands.bossbar.get.players";

    /**
     * {@literal Custom bossbar [%1#s] has no players currently online}
     */
    String MC_COMMANDS_BOSSBAR_GET_PLAYERS_NONE = "minecraft:commands.bossbar.get.players.none";

    /**
     * {@literal Custom bossbar [%1#s] has 1 player currently online: %2#s}
     */
    String MC_COMMANDS_BOSSBAR_GET_PLAYERS_ONE = "minecraft:commands.bossbar.get.players.one";

    /**
     * {@literal Custom bossbar [%1#s] has a value of %2#d}
     */
    String MC_COMMANDS_BOSSBAR_GET_VALUE = "minecraft:commands.bossbar.get.value";

    /**
     * {@literal Custom bossbar [%1#s] is currently hidden}
     */
    String MC_COMMANDS_BOSSBAR_GET_VISIBLE_FALSE = "minecraft:commands.bossbar.get.visible.false";

    /**
     * {@literal Custom bossbar [%1#s] is currently shown}
     */
    String MC_COMMANDS_BOSSBAR_GET_VISIBLE_TRUE = "minecraft:commands.bossbar.get.visible.true";

    /**
     * {@literal There are %1#s custom bossbars active: %2#s}
     */
    String MC_COMMANDS_BOSSBAR_LIST = "minecraft:commands.bossbar.list";

    /**
     * {@literal There are no custom bossbars active}
     */
    String MC_COMMANDS_BOSSBAR_LIST_NONE = "minecraft:commands.bossbar.list.none";

    /**
     * {@literal There is 1 custom bossbar active: %1#s}
     */
    String MC_COMMANDS_BOSSBAR_LIST_ONE = "minecraft:commands.bossbar.list.one";

    /**
     * {@literal No bossbar exists with the ID '%1#s'}
     */
    String MC_COMMANDS_BOSSBAR_NOTFOUND = "minecraft:commands.bossbar.notFound";

    /**
     * {@literal Removed custom bossbar [%1#s]}
     */
    String MC_COMMANDS_BOSSBAR_REMOVE = "minecraft:commands.bossbar.remove";

    /**
     * {@literal Issues a camera instruction}
     */
    String MC_COMMANDS_CAMERA_DESCRIPTION = "minecraft:commands.camera.description";

    /**
     * {@literal Invalid camera command mode}
     */
    String MC_COMMANDS_CAMERA_INVALID = "minecraft:commands.camera.invalid";

    /**
     * {@literal Fade color values must be integers from 0 to 255}
     */
    String MC_COMMANDS_CAMERA_INVALID_COLOR = "minecraft:commands.camera.invalid.color";

    /**
     * {@literal Easing to non-stationary cameras is not supported}
     */
    String MC_COMMANDS_CAMERA_INVALID_EASING = "minecraft:commands.camera.invalid.easing";

    /**
     * {@literal Entity offset must be between -64 and 64}
     */
    String MC_COMMANDS_CAMERA_INVALID_ENTITY_OFFSET = "minecraft:commands.camera.invalid.entity.offset";

    /**
     * {@literal Entity and view offset can only be applied to the follow_orbit camera}
     */
    String MC_COMMANDS_CAMERA_INVALID_OFFSET_CAMERA = "minecraft:commands.camera.invalid.offset.camera";

    /**
     * {@literal Camera pitch (X rotation) must be between -90 and 90 degrees}
     */
    String MC_COMMANDS_CAMERA_INVALID_PITCH = "minecraft:commands.camera.invalid.pitch";

    /**
     * {@literal Fade durations must be between 0 and 10 seconds}
     */
    String MC_COMMANDS_CAMERA_INVALID_TIME = "minecraft:commands.camera.invalid.time";

    /**
     * {@literal View offset must be between -64 and 64}
     */
    String MC_COMMANDS_CAMERA_INVALID_VIEW_OFFSET = "minecraft:commands.camera.invalid.view.offset";

    /**
     * {@literal Perspective cannot be changed while using custom cameras}
     */
    String MC_COMMANDS_CAMERA_PERSPECTIVE_LOCKED = "minecraft:commands.camera.perspective.locked";

    /**
     * {@literal Your selected camera will change when the custom camera is not active}
     */
    String MC_COMMANDS_CAMERA_PERSPECTIVE_OPTION = "minecraft:commands.camera.perspective.option";

    /**
     * {@literal Sent a camera instruction to the following players: %s}
     */
    String MC_COMMANDS_CAMERA_SUCCESS = "minecraft:commands.camera.success";

    /**
     * {@literal Unknown camera preset name}
     */
    String MC_COMMANDS_CAMERA_UNKNOWN_PRESET = "minecraft:commands.camera.unknown.preset";

    /**
     * {@literal Changes a setting on the dedicated server while it's running.}
     */
    String MC_COMMANDS_CHANGE_SETTING_DESCRIPTION = "minecraft:commands.change-setting.description";

    /**
     * {@literal %1#s has been changed}
     */
    String MC_COMMANDS_CHANGE_SETTING_SUCCESS = "minecraft:commands.change-setting.success";

    /**
     * {@literal Chunk is compiled.}
     */
    String MC_COMMANDS_CHUNKINFO_COMPILED = "minecraft:commands.chunkinfo.compiled";

    /**
     * {@literal First 64 vertices are: %1#s}
     */
    String MC_COMMANDS_CHUNKINFO_DATA = "minecraft:commands.chunkinfo.data";

    /**
     * {@literal Chunk is empty.}
     */
    String MC_COMMANDS_CHUNKINFO_EMPTY = "minecraft:commands.chunkinfo.empty";

    /**
     * {@literal Chunk has layers: %1#s}
     */
    String MC_COMMANDS_CHUNKINFO_HASLAYERS = "minecraft:commands.chunkinfo.hasLayers";

    /**
     * {@literal Chunk has no renderable layers.}
     */
    String MC_COMMANDS_CHUNKINFO_HASNORENDERABLELAYERS = "minecraft:commands.chunkinfo.hasNoRenderableLayers";

    /**
     * {@literal Chunk has empty layers: %1#s}
     */
    String MC_COMMANDS_CHUNKINFO_ISEMPTY = "minecraft:commands.chunkinfo.isEmpty";

    /**
     * {@literal Chunk location: (%1#d, %2#d, %3#d)}
     */
    String MC_COMMANDS_CHUNKINFO_LOCATION = "minecraft:commands.chunkinfo.location";

    /**
     * {@literal No chunk found at chunk position %1#d, %2#d, %3#d}
     */
    String MC_COMMANDS_CHUNKINFO_NOCHUNK = "minecraft:commands.chunkinfo.noChunk";

    /**
     * {@literal Chunk is not compiled.}
     */
    String MC_COMMANDS_CHUNKINFO_NOTCOMPILED = "minecraft:commands.chunkinfo.notCompiled";

    /**
     * {@literal Chunk is not empty.}
     */
    String MC_COMMANDS_CHUNKINFO_NOTEMPTY = "minecraft:commands.chunkinfo.notEmpty";

    /**
     * {@literal %1#s layer's buffer contains %2#d vertices}
     */
    String MC_COMMANDS_CHUNKINFO_VERTICES = "minecraft:commands.chunkinfo.vertices";

    /**
     * {@literal Attempt to launch and connect to Classroom Mode.}
     */
    String MC_COMMANDS_CLASSROOMMODE_DESCRIPTION = "minecraft:commands.classroommode.description";

    /**
     * {@literal Attempting to launch Classroom Mode...}
     */
    String MC_COMMANDS_CLASSROOMMODE_SUCCESS = "minecraft:commands.classroommode.success";

    /**
     * {@literal Clears items from player inventory.}
     */
    String MC_COMMANDS_CLEAR_DESCRIPTION = "minecraft:commands.clear.description";

    /**
     * {@literal Could not clear the inventory of %1#s}
     */
    String MC_COMMANDS_CLEAR_FAILURE = "minecraft:commands.clear.failure";

    /**
     * {@literal Could not clear the inventory of %1#s, no items to remove}
     */
    String MC_COMMANDS_CLEAR_FAILURE_NO_ITEMS = "minecraft:commands.clear.failure.no.items";

    /**
     * {@literal Cleared the inventory of %1#s, removing %2#d items}
     */
    String MC_COMMANDS_CLEAR_SUCCESS = "minecraft:commands.clear.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_CLEAR_TAGERROR = "minecraft:commands.clear.tagError";

    /**
     * {@literal %1#s has %2#d items that match the criteria}
     */
    String MC_COMMANDS_CLEAR_TESTING = "minecraft:commands.clear.testing";

    /**
     * {@literal Removes all Fixed Inventory Slots.}
     */
    String MC_COMMANDS_CLEARFIXEDINV_DESCRIPTION = "minecraft:commands.clearfixedinv.description";

    /**
     * {@literal Cleared the Fixed Inventory}
     */
    String MC_COMMANDS_CLEARFIXEDINV_SUCCESS = "minecraft:commands.clearfixedinv.success";

    /**
     * {@literal Clears all Realm Events stored in the world data.}
     */
    String MC_COMMANDS_CLEARREALMEVENTS_DESCRIPTION = "minecraft:commands.clearrealmevents.description";

    /**
     * {@literal Clearing all Realm Events}
     */
    String MC_COMMANDS_CLEARREALMEVENTS_START = "minecraft:commands.clearrealmevents.start";

    /**
     * {@literal Removes the spawn point for a player.}
     */
    String MC_COMMANDS_CLEARSPAWNPOINT_DESCRIPTION = "minecraft:commands.clearspawnpoint.description";

    /**
     * {@literal Removed spawn points for %1#s}
     */
    String MC_COMMANDS_CLEARSPAWNPOINT_SUCCESS_MULTIPLE = "minecraft:commands.clearspawnpoint.success.multiple";

    /**
     * {@literal Removed %1#s's spawn point}
     */
    String MC_COMMANDS_CLEARSPAWNPOINT_SUCCESS_SINGLE = "minecraft:commands.clearspawnpoint.success.single";

    /**
     * {@literal Clones blocks from one region to another.}
     */
    String MC_COMMANDS_CLONE_DESCRIPTION = "minecraft:commands.clone.description";

    /**
     * {@literal No blocks cloned}
     */
    String MC_COMMANDS_CLONE_FAILED = "minecraft:commands.clone.failed";

    /**
     * {@literal Filtered usage requires a filter block to be specified}
     */
    String MC_COMMANDS_CLONE_FILTERED_ERROR = "minecraft:commands.clone.filtered.error";

    /**
     * {@literal Source and destination can not overlap}
     */
    String MC_COMMANDS_CLONE_NOOVERLAP = "minecraft:commands.clone.noOverlap";

    /**
     * {@literal %1#d blocks cloned}
     */
    String MC_COMMANDS_CLONE_SUCCESS = "minecraft:commands.clone.success";

    /**
     * {@literal Too many blocks in the specified area (%1#d > %2#d)}
     */
    String MC_COMMANDS_CLONE_TOOMANYBLOCKS = "minecraft:commands.clone.tooManyBlocks";

    /**
     * {@literal Closes the chat window of the local player if it is open.}
     */
    String MC_COMMANDS_CLOSECHAT_DESCRIPTION = "minecraft:commands.closechat.description";

    /**
     * {@literal Chat was not open}
     */
    String MC_COMMANDS_CLOSECHAT_FAILURE = "minecraft:commands.closechat.failure";

    /**
     * {@literal Chat closed}
     */
    String MC_COMMANDS_CLOSECHAT_SUCCESS = "minecraft:commands.closechat.success";

    /**
     * {@literal Closes websocket connection if there is one.}
     */
    String MC_COMMANDS_CLOSEWEBSOCKET_DESCRIPTION = "minecraft:commands.closewebsocket.description";

    /**
     * {@literal Launches Code Builder.}
     */
    String MC_COMMANDS_CODE_DESCRIPTION = "minecraft:commands.code.description";

    /**
     * {@literal Launched Code Builder.}
     */
    String MC_COMMANDS_CODE_SUCCESS = "minecraft:commands.code.success";

    /**
     * {@literal Explicitly compacts the levelDB log.}
     */
    String MC_COMMANDS_COMPACTDB_DESCRIPTION = "minecraft:commands.compactdb.description";

    /**
     * {@literal Successfully compacted levelDB log.}
     */
    String MC_COMMANDS_COMPACTDB_SUCCESS = "minecraft:commands.compactdb.success";

    /**
     * {@literal Source and destination are not identical}
     */
    String MC_COMMANDS_COMPARE_FAILED = "minecraft:commands.compare.failed";

    /**
     * {@literal %1#d blocks compared}
     */
    String MC_COMMANDS_COMPARE_SUCCESS = "minecraft:commands.compare.success";

    /**
     * {@literal Too many blocks in the specified area (%1#d > %2#d)}
     */
    String MC_COMMANDS_COMPARE_TOOMANYBLOCKS = "minecraft:commands.compare.tooManyBlocks";

    /**
     * {@literal Sets or clears control scheme.}
     */
    String MC_COMMANDS_CONTROLSCHEME_DESCRIPTION = "minecraft:commands.controlscheme.description";

    /**
     * {@literal Failed to apply control scheme to the following player(s): %s}
     */
    String MC_COMMANDS_CONTROLSCHEME_FAILED = "minecraft:commands.controlscheme.failed";

    /**
     * {@literal Applied control scheme to the following player(s): %s}
     */
    String MC_COMMANDS_CONTROLSCHEME_SUCCESS = "minecraft:commands.controlscheme.success";

    /**
     * {@literal Corrupts the world loaded on the server.}
     */
    String MC_COMMANDS_CORRUPTWORLD_DESCRIPTION = "minecraft:commands.corruptworld.description";

    /**
     * {@literal Successfully corrupted the world.}
     */
    String MC_COMMANDS_CORRUPTWORLD_SUCCESS = "minecraft:commands.corruptworld.success";

    /**
     * {@literal Apply damage to the specified entities.}
     */
    String MC_COMMANDS_DAMAGE_DESCRIPTION = "minecraft:commands.damage.description";

    /**
     * {@literal Could not apply damage to %1#s}
     */
    String MC_COMMANDS_DAMAGE_FAILED = "minecraft:commands.damage.failed";

    /**
     * {@literal Please specify a valid damage value.}
     */
    String MC_COMMANDS_DAMAGE_SPECIFY_DAMAGE = "minecraft:commands.damage.specify.damage";

    /**
     * {@literal Applied damage to %1#s}
     */
    String MC_COMMANDS_DAMAGE_SUCCESS = "minecraft:commands.damage.success";

    /**
     * {@literal There can only be one source entity. Please adjust your selector to limit selection to one entity.}
     */
    String MC_COMMANDS_DAMAGE_TOOMANYSOURCES = "minecraft:commands.damage.tooManySources";

    /**
     * {@literal Locks and unlocks the day-night cycle.}
     */
    String MC_COMMANDS_DAYLOCK_DESCRIPTION = "minecraft:commands.daylock.description";

    /**
     * {@literal Starts or stops a debugging session.}
     */
    String MC_COMMANDS_DEBUG_DESCRIPTION = "minecraft:commands.debug.description";

    /**
     * {@literal Can't stop profiling when we haven't started yet!}
     */
    String MC_COMMANDS_DEBUG_NOTSTARTED = "minecraft:commands.debug.notStarted";

    /**
     * {@literal Started debug profiling}
     */
    String MC_COMMANDS_DEBUG_START = "minecraft:commands.debug.start";

    /**
     * {@literal Stopped debug profiling after %.2f seconds (%1#d ticks)}
     */
    String MC_COMMANDS_DEBUG_STOP = "minecraft:commands.debug.stop";

    /**
     * {@literal Sets the default game mode.}
     */
    String MC_COMMANDS_DEFAULTGAMEMODE_DESCRIPTION = "minecraft:commands.defaultgamemode.description";

    /**
     * {@literal The world's default game mode is now %1#s}
     */
    String MC_COMMANDS_DEFAULTGAMEMODE_SUCCESS = "minecraft:commands.defaultgamemode.success";

    /**
     * {@literal Revokes operator status from a player.}
     */
    String MC_COMMANDS_DEOP_DESCRIPTION = "minecraft:commands.deop.description";

    /**
     * {@literal Could not de-op (permission level too high): %s}
     */
    String MC_COMMANDS_DEOP_FAILED = "minecraft:commands.deop.failed";

    /**
     * {@literal You have been de-opped}
     */
    String MC_COMMANDS_DEOP_MESSAGE = "minecraft:commands.deop.message";

    /**
     * {@literal De-opped: %s}
     */
    String MC_COMMANDS_DEOP_SUCCESS = "minecraft:commands.deop.success";

    /**
     * {@literal Failed to change the scene for the NPC.}
     */
    String MC_COMMANDS_DIALOGUE_CHANGEFAILED = "minecraft:commands.dialogue.changeFailed";

    /**
     * {@literal Dialogue successfully changed NPC text just for %1#s.}
     */
    String MC_COMMANDS_DIALOGUE_CHANGEPERPLAYERSUCCESS = "minecraft:commands.dialogue.changePerPlayerSuccess";

    /**
     * {@literal Dialogue successfully changed the scene for the NPC globally.}
     */
    String MC_COMMANDS_DIALOGUE_CHANGESUCCESS = "minecraft:commands.dialogue.changeSuccess";

    /**
     * {@literal Opens NPC dialogue for a player.}
     */
    String MC_COMMANDS_DIALOGUE_DESCRIPTION = "minecraft:commands.dialogue.description";

    /**
     * {@literal Invalid scene was referenced.}
     */
    String MC_COMMANDS_DIALOGUE_INVALIDSCENE = "minecraft:commands.dialogue.invalidScene";

    /**
     * {@literal Selected actor did not have an NPC component.}
     */
    String MC_COMMANDS_DIALOGUE_NONPCCOMPONENT = "minecraft:commands.dialogue.noNpcComponent";

    /**
     * {@literal Failed to find an NPC.}
     */
    String MC_COMMANDS_DIALOGUE_NPCNOTFOUND = "minecraft:commands.dialogue.npcNotFound";

    /**
     * {@literal An NPC was found, but was not in the receiving player %1#s's ticking range.}
     */
    String MC_COMMANDS_DIALOGUE_NPCNOTINRANGE = "minecraft:commands.dialogue.npcNotInRange";

    /**
     * {@literal Dialogue sent to %1#s.}
     */
    String MC_COMMANDS_DIALOGUE_SUCCESS = "minecraft:commands.dialogue.success";

    /**
     * {@literal Selector for NPCs was ambiguous and resulted in too many selected. Please select only one NPC.}
     */
    String MC_COMMANDS_DIALOGUE_TOOMANYNPCS = "minecraft:commands.dialogue.tooManyNPCs";

    /**
     * {@literal Sets the difficulty level.}
     */
    String MC_COMMANDS_DIFFICULTY_DESCRIPTION = "minecraft:commands.difficulty.description";

    /**
     * {@literal Set game difficulty to %1#s}
     */
    String MC_COMMANDS_DIFFICULTY_SUCCESS = "minecraft:commands.difficulty.success";

    /**
     * {@literal /difficulty <new difficulty>}
     */
    String MC_COMMANDS_DIFFICULTY_USAGE = "minecraft:commands.difficulty.usage";

    /**
     * {@literal Toggled downfall}
     */
    String MC_COMMANDS_DOWNFALL_SUCCESS = "minecraft:commands.downfall.success";

    /**
     * {@literal Get tenant ID and host status. Intended for CM.}
     */
    String MC_COMMANDS_EDUCLIENTINFO_DESCRIPTION = "minecraft:commands.educlientinfo.description";

    /**
     * {@literal Add or remove status effects.}
     */
    String MC_COMMANDS_EFFECT_DESCRIPTION = "minecraft:commands.effect.description";

    /**
     * {@literal %1#s cannot have effects}
     */
    String MC_COMMANDS_EFFECT_FAILURE_NOTAMOB = "minecraft:commands.effect.failure.notAMob";

    /**
     * {@literal Couldn't take %1#s from %2#s as they do not have the effect}
     */
    String MC_COMMANDS_EFFECT_FAILURE_NOTACTIVE = "minecraft:commands.effect.failure.notActive";

    /**
     * {@literal Couldn't take any effects from %1#s as they do not have any}
     */
    String MC_COMMANDS_EFFECT_FAILURE_NOTACTIVE_ALL = "minecraft:commands.effect.failure.notActive.all";

    /**
     * {@literal There is no such mob effect with ID %s}
     */
    String MC_COMMANDS_EFFECT_NOTFOUND = "minecraft:commands.effect.notFound";

    /**
     * {@literal Gave %1#s * %2#d to %3#s for %4#d seconds}
     */
    String MC_COMMANDS_EFFECT_SUCCESS = "minecraft:commands.effect.success";

    /**
     * {@literal Gave %1#s * %2#d to %3#s for an infinite duration}
     */
    String MC_COMMANDS_EFFECT_SUCCESS_INFINITE = "minecraft:commands.effect.success.infinite";

    /**
     * {@literal Took %1#s from %2#s}
     */
    String MC_COMMANDS_EFFECT_SUCCESS_REMOVED = "minecraft:commands.effect.success.removed";

    /**
     * {@literal Took all effects from %1#s}
     */
    String MC_COMMANDS_EFFECT_SUCCESS_REMOVED_ALL = "minecraft:commands.effect.success.removed.all";

    /**
     * {@literal %1#s can't be combined with %2#s}
     */
    String MC_COMMANDS_ENCHANT_CANTCOMBINE = "minecraft:commands.enchant.cantCombine";

    /**
     * {@literal The selected enchantment can't be added to the target item: %1#s}
     */
    String MC_COMMANDS_ENCHANT_CANTENCHANT = "minecraft:commands.enchant.cantEnchant";

    /**
     * {@literal Adds an enchantment to a player's selected item.}
     */
    String MC_COMMANDS_ENCHANT_DESCRIPTION = "minecraft:commands.enchant.description";

    /**
     * {@literal %1#s does not support level %2#d}
     */
    String MC_COMMANDS_ENCHANT_INVALIDLEVEL = "minecraft:commands.enchant.invalidLevel";

    /**
     * {@literal The target doesn't hold an item: %1#s}
     */
    String MC_COMMANDS_ENCHANT_NOITEM = "minecraft:commands.enchant.noItem";

    /**
     * {@literal There is no such enchantment with ID %1#d}
     */
    String MC_COMMANDS_ENCHANT_NOTFOUND = "minecraft:commands.enchant.notFound";

    /**
     * {@literal Enchanting succeeded for %1#s}
     */
    String MC_COMMANDS_ENCHANT_SUCCESS = "minecraft:commands.enchant.success";

    /**
     * {@literal Modifies the data tag of an entity.}
     */
    String MC_COMMANDS_ENTITYDATA_DESCRIPTION = "minecraft:commands.entitydata.description";

    /**
     * {@literal The data tag did not change: %1#s}
     */
    String MC_COMMANDS_ENTITYDATA_FAILED = "minecraft:commands.entitydata.failed";

    /**
     * {@literal %1#s is a player and cannot be changed}
     */
    String MC_COMMANDS_ENTITYDATA_NOPLAYERS = "minecraft:commands.entitydata.noPlayers";

    /**
     * {@literal Entity data updated to: %1#s}
     */
    String MC_COMMANDS_ENTITYDATA_SUCCESS = "minecraft:commands.entitydata.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_ENTITYDATA_TAGERROR = "minecraft:commands.entitydata.tagError";

    /**
     * {@literal Triggers an event for the specified object(s)}
     */
    String MC_COMMANDS_EVENT_DESCRIPTION = "minecraft:commands.event.description";

    /**
     * {@literal Event name cannot be empty}
     */
    String MC_COMMANDS_EVENT_ERROR_EMPTY = "minecraft:commands.event.error.empty";

    /**
     * {@literal %1#s could not be executed on: %2#s}
     */
    String MC_COMMANDS_EVENT_ERROR_FAILED = "minecraft:commands.event.error.failed";

    /**
     * {@literal %1#s successfully executed on: %2#s}
     */
    String MC_COMMANDS_EVENT_SUCCESS = "minecraft:commands.event.success";

    /**
     * {@literal Invalid swizzle, expected combination of 'x', 'y', and 'z'}
     */
    String MC_COMMANDS_EXECUTE_ALIGN_INVALIDINPUT = "minecraft:commands.execute.align.invalidInput";

    /**
     * {@literal All invocations failed: '%1#s'}
     */
    String MC_COMMANDS_EXECUTE_ALLINVOCATIONSFAILED = "minecraft:commands.execute.allInvocationsFailed";

    /**
     * {@literal Executes a command on behalf of one or more entities.}
     */
    String MC_COMMANDS_EXECUTE_DESCRIPTION = "minecraft:commands.execute.description";

    /**
     * {@literal Failed to execute '%1#s' as %2#s}
     */
    String MC_COMMANDS_EXECUTE_FAILED = "minecraft:commands.execute.failed";

    /**
     * {@literal Execute subcommand %1#s %2#s test failed.}
     */
    String MC_COMMANDS_EXECUTE_FALSECONDITION = "minecraft:commands.execute.falseCondition";

    /**
     * {@literal Execute subcommand %1#s %2#s test failed, count: %3#s.}
     */
    String MC_COMMANDS_EXECUTE_FALSECONDITIONWITHCOUNT = "minecraft:commands.execute.falseConditionWithCount";

    /**
     * {@literal Checking too many blocks. Max block count: %1#s. Current block count: %2#s.}
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSBLOCKS_TOOMANYBLOCKS = "minecraft:commands.execute.ifUnlessBlocks.tooManyBlocks";

    /**
     * {@literal No objective was found by the name '%1#s'}
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_OBJECTIVENOTFOUND = "minecraft:commands.execute.ifUnlessScore.objectiveNotFound";

    /**
     * {@literal Invalid operation %1#s.}
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_PLAYERS_COMPAREOPERATION_INVALIDOPERATION = "minecraft:commands.execute.ifUnlessScore.players.compareOperation.invalidOperation";

    /**
     * {@literal No %1#s score for %2#s found}
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_SCORE_NOTFOUND = "minecraft:commands.execute.ifUnlessScore.score.notFound";

    /**
     * {@literal There is no tracked player '%1#s' on the scoreboard}
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_TARGETS_EMPTY = "minecraft:commands.execute.ifUnlessScore.targets.empty";

    /**
     * {@literal %1#s targets matched the selector. Only 1 target is allowed.}
     */
    String MC_COMMANDS_EXECUTE_IFUNLESSSCORE_TARGETS_TOOMANY = "minecraft:commands.execute.ifUnlessScore.targets.tooMany";

    /**
     * {@literal Detect position: %1#s %2#s %3#s is out of range.}
     */
    String MC_COMMANDS_EXECUTE_OUTRANGEDDETECTPOSITION = "minecraft:commands.execute.outRangedDetectPosition";

    /**
     * {@literal Test passed.}
     */
    String MC_COMMANDS_EXECUTE_TRUECONDITION = "minecraft:commands.execute.trueCondition";

    /**
     * {@literal Test passed, count: %1#s.}
     */
    String MC_COMMANDS_EXECUTE_TRUECONDITIONWITHCOUNT = "minecraft:commands.execute.trueConditionWithCount";

    /**
     * {@literal Fills all or parts of a region with a specific block.}
     */
    String MC_COMMANDS_FILL_DESCRIPTION = "minecraft:commands.fill.description";

    /**
     * {@literal No blocks filled}
     */
    String MC_COMMANDS_FILL_FAILED = "minecraft:commands.fill.failed";

    /**
     * {@literal Cannot place blocks outside of the world}
     */
    String MC_COMMANDS_FILL_OUTOFWORLD = "minecraft:commands.fill.outOfWorld";

    /**
     * {@literal Invalid replace data value for block %1#s}
     */
    String MC_COMMANDS_FILL_REPLACE_AUXVALUE_INVALID = "minecraft:commands.fill.replace.auxvalue.invalid";

    /**
     * {@literal %1#d blocks filled}
     */
    String MC_COMMANDS_FILL_SUCCESS = "minecraft:commands.fill.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_FILL_TAGERROR = "minecraft:commands.fill.tagError";

    /**
     * {@literal Too many blocks in the specified area (%1#d > %2#d)}
     */
    String MC_COMMANDS_FILL_TOOMANYBLOCKS = "minecraft:commands.fill.tooManyBlocks";

    /**
     * {@literal Add or remove fog settings file}
     */
    String MC_COMMANDS_FOG_DESCRIPTION = "minecraft:commands.fog.description";

    /**
     * {@literal Invalid Fog Setting Id : %1#s}
     */
    String MC_COMMANDS_FOG_INVALIDFOGID = "minecraft:commands.fog.invalidFogId";

    /**
     * {@literal No fog settings with the id were found for %1#s}
     */
    String MC_COMMANDS_FOG_INVALIDUSERID = "minecraft:commands.fog.invalidUserId";

    /**
     * {@literal Size limit reached for fog identifiers for %1#s}
     */
    String MC_COMMANDS_FOG_LIMITREACHED = "minecraft:commands.fog.limitReached";

    /**
     * {@literal Last pushed fog setting with id %2#s was successfully removed from %1#s}
     */
    String MC_COMMANDS_FOG_SUCCESS_POP = "minecraft:commands.fog.success.pop";

    /**
     * {@literal Fog setting was successfully added for %1#s with id %2#s}
     */
    String MC_COMMANDS_FOG_SUCCESS_PUSH = "minecraft:commands.fog.success.push";

    /**
     * {@literal Fog settings with id %2#s were successfully removed from %1#s}
     */
    String MC_COMMANDS_FOG_SUCCESS_REMOVE = "minecraft:commands.fog.success.remove";

    /**
     * {@literal Runs commands found in the corresponding function file.}
     */
    String MC_COMMANDS_FUNCTION_DESCRIPTION = "minecraft:commands.function.description";

    /**
     * {@literal Function %1#s not found.}
     */
    String MC_COMMANDS_FUNCTION_FUNCTIONNAMENOTFOUND = "minecraft:commands.function.functionNameNotFound";

    /**
     * {@literal Function with title '%s' is invalid, character '%s' is not allowed in function names.}
     */
    String MC_COMMANDS_FUNCTION_INVALIDCHARACTERS = "minecraft:commands.function.invalidCharacters";

    /**
     * {@literal Function %s could not be run. You must specify a min_engine_version in the behavior pack's manifest.json.}
     */
    String MC_COMMANDS_FUNCTION_NOENGINEVERSIONSPECIFIED = "minecraft:commands.function.noEngineVersionSpecified";

    /**
     * {@literal Successfully executed %1#d function entries.}
     */
    String MC_COMMANDS_FUNCTION_SUCCESS = "minecraft:commands.function.success";

    /**
     * {@literal Sets a player's game mode.}
     */
    String MC_COMMANDS_GAMEMODE_DESCRIPTION = "minecraft:commands.gamemode.description";

    /**
     * {@literal Game mode '%1#s' is invalid}
     */
    String MC_COMMANDS_GAMEMODE_FAIL_INVALID = "minecraft:commands.gamemode.fail.invalid";

    /**
     * {@literal Set %2#s's game mode to %1#s}
     */
    String MC_COMMANDS_GAMEMODE_SUCCESS_OTHER = "minecraft:commands.gamemode.success.other";

    /**
     * {@literal Set own game mode to %1#s}
     */
    String MC_COMMANDS_GAMEMODE_SUCCESS_SELF = "minecraft:commands.gamemode.success.self";

    /**
     * {@literal Sets or queries a game rule value.}
     */
    String MC_COMMANDS_GAMERULE_DESCRIPTION = "minecraft:commands.gamerule.description";

    /**
     * {@literal Only server owners can change '%1#s'}
     */
    String MC_COMMANDS_GAMERULE_NOPERMISSION = "minecraft:commands.gamerule.nopermission";

    /**
     * {@literal No game rule called '%1#s' is available}
     */
    String MC_COMMANDS_GAMERULE_NORULE = "minecraft:commands.gamerule.norule";

    /**
     * {@literal Game rule %1#s has been updated to %2#s}
     */
    String MC_COMMANDS_GAMERULE_SUCCESS = "minecraft:commands.gamerule.success";

    /**
     * {@literal Invalid type used for game rule '%1#s'}
     */
    String MC_COMMANDS_GAMERULE_TYPE_INVALID = "minecraft:commands.gamerule.type.invalid";

    /**
     * {@literal Game rule '%1#s' can only be used if cheats are enabled in this world.}
     */
    String MC_COMMANDS_GAMERULE_TYPE_NOCHEATSENABLED = "minecraft:commands.gamerule.type.nocheatsenabled";

    /**
     * {@literal Disable game tips on this device}
     */
    String MC_COMMANDS_GAMETIPS_OFF_DESCRIPTION = "minecraft:commands.gametips-off.description";

    /**
     * {@literal Game tips are now disabled}
     */
    String MC_COMMANDS_GAMETIPS_OFF_SUCCESS = "minecraft:commands.gametips-off.success";

    /**
     * {@literal Enable game tips on this device}
     */
    String MC_COMMANDS_GAMETIPS_ON_DESCRIPTION = "minecraft:commands.gametips-on.description";

    /**
     * {@literal Game tips are now enabled}
     */
    String MC_COMMANDS_GAMETIPS_ON_SUCCESS = "minecraft:commands.gametips-on.success";

    /**
     * {@literal Reset game tips on this device}
     */
    String MC_COMMANDS_GAMETIPS_RESET_DESCRIPTION = "minecraft:commands.gametips-reset.description";

    /**
     * {@literal Game tips are now reset}
     */
    String MC_COMMANDS_GAMETIPS_RESET_SUCCESS = "minecraft:commands.gametips-reset.success";

    /**
     * {@literal Enable or disable the game tips on this device}
     */
    String MC_COMMANDS_GAMETIPS_DESCRIPTION = "minecraft:commands.gametips.description";

    /**
     * {@literal Game tips are now disabled}
     */
    String MC_COMMANDS_GAMETIPS_DISABLED = "minecraft:commands.gametips.disabled";

    /**
     * {@literal Game tips are now enabled}
     */
    String MC_COMMANDS_GAMETIPS_ENABLED = "minecraft:commands.gametips.enabled";

    /**
     * {@literal '%1#s' command started (async step %2#d)}
     */
    String MC_COMMANDS_GENERIC_ASYNC_INITIATED = "minecraft:commands.generic.async.initiated";

    /**
     * {@literal Sorry! The %s command is not available in this world.}
     */
    String MC_COMMANDS_GENERIC_BLOCKED_EDU = "minecraft:commands.generic.blocked_edu";

    /**
     * {@literal '%1#s' is not true or false}
     */
    String MC_COMMANDS_GENERIC_BOOLEAN_INVALID = "minecraft:commands.generic.boolean.invalid";

    /**
     * {@literal Specified chunk not found}
     */
    String MC_COMMANDS_GENERIC_CHUNK_NOTFOUND = "minecraft:commands.generic.chunk.notFound";

    /**
     * {@literal Component list parsing failed}
     */
    String MC_COMMANDS_GENERIC_COMPONENTERROR = "minecraft:commands.generic.componentError";

    /**
     * {@literal Destination volume is not loaded}
     */
    String MC_COMMANDS_GENERIC_DESTINATIONNOTLOADED = "minecraft:commands.generic.destinationNotLoaded";

    /**
     * {@literal Specified dimension not found}
     */
    String MC_COMMANDS_GENERIC_DIMENSION_NOTFOUND = "minecraft:commands.generic.dimension.notFound";

    /**
     * {@literal Cheats aren't enabled in this world.}
     */
    String MC_COMMANDS_GENERIC_DISABLED = "minecraft:commands.generic.disabled";

    /**
     * {@literal The command you entered, %s, is not supported in the Editor.}
     */
    String MC_COMMANDS_GENERIC_DISABLED_EDITORLOCKED = "minecraft:commands.generic.disabled.editorLocked";

    /**
     * {@literal Settings are currently locked. Unlock Template World Options in the Game Settings menu to change them.}
     */
    String MC_COMMANDS_GENERIC_DISABLED_TEMPLATELOCKED = "minecraft:commands.generic.disabled.templateLocked";

    /**
     * {@literal The number you have entered (%.2f) is too big, it must be at most %.2f}
     */
    String MC_COMMANDS_GENERIC_DOUBLE_TOOBIG = "minecraft:commands.generic.double.tooBig";

    /**
     * {@literal The number you have entered (%.2f) is too small, it must be at least %.2f}
     */
    String MC_COMMANDS_GENERIC_DOUBLE_TOOSMALL = "minecraft:commands.generic.double.tooSmall";

    /**
     * {@literal Duplicate %s selector arguments}
     */
    String MC_COMMANDS_GENERIC_DUPLICATESELECTORARGUMENT = "minecraft:commands.generic.duplicateSelectorArgument";

    /**
     * {@literal Duplicate type arguments}
     */
    String MC_COMMANDS_GENERIC_DUPLICATETYPE = "minecraft:commands.generic.duplicateType";

    /**
     * {@literal Bad public key given. Expected 120 byte key after PEM formatting.}
     */
    String MC_COMMANDS_GENERIC_ENCRYPTION_BADKEY = "minecraft:commands.generic.encryption.badkey";

    /**
     * {@literal Bad salt given. Expected a 16 bytes before base 64 encoding.}
     */
    String MC_COMMANDS_GENERIC_ENCRYPTION_BADSALT = "minecraft:commands.generic.encryption.badsalt";

    /**
     * {@literal Encrypted session required}
     */
    String MC_COMMANDS_GENERIC_ENCRYPTION_REQUIRED = "minecraft:commands.generic.encryption.required";

    /**
     * {@literal Target entity cannot be in a different dimension}
     */
    String MC_COMMANDS_GENERIC_ENTITY_DIFFERENTDIMENSION = "minecraft:commands.generic.entity.differentDimension";

    /**
     * {@literal Entity type '%1#s' is invalid}
     */
    String MC_COMMANDS_GENERIC_ENTITY_INVALIDTYPE = "minecraft:commands.generic.entity.invalidType";

    /**
     * {@literal The entity UUID provided is in an invalid format}
     */
    String MC_COMMANDS_GENERIC_ENTITY_INVALIDUUID = "minecraft:commands.generic.entity.invalidUuid";

    /**
     * {@literal That entity cannot be found}
     */
    String MC_COMMANDS_GENERIC_ENTITY_NOTFOUND = "minecraft:commands.generic.entity.notFound";

    /**
     * {@literal Incorrect permission level for command: %s.  }
     */
    String MC_COMMANDS_GENERIC_ERROR_PERMISSIONS = "minecraft:commands.generic.error.permissions";

    /**
     * {@literal An unknown error occurred while attempting to perform this command}
     */
    String MC_COMMANDS_GENERIC_EXCEPTION = "minecraft:commands.generic.exception";

    /**
     * {@literal Type argument applied to Agent-only selector}
     */
    String MC_COMMANDS_GENERIC_INVALIDAGENTTYPE = "minecraft:commands.generic.invalidAgentType";

    /**
     * {@literal The command you entered, %s, is not supported on this device}
     */
    String MC_COMMANDS_GENERIC_INVALIDDEVICE = "minecraft:commands.generic.invalidDevice";

    /**
     * {@literal Invalid message length}
     */
    String MC_COMMANDS_GENERIC_INVALIDMESSAGE = "minecraft:commands.generic.invalidMessage";

    /**
     * {@literal Command origin was invalid at command execution time}
     */
    String MC_COMMANDS_GENERIC_INVALIDORIGIN = "minecraft:commands.generic.invalidOrigin";

    /**
     * {@literal Type argument applied to player-only selector}
     */
    String MC_COMMANDS_GENERIC_INVALIDPLAYERTYPE = "minecraft:commands.generic.invalidPlayerType";

    /**
     * {@literal Unknown type argument}
     */
    String MC_COMMANDS_GENERIC_INVALIDTYPE = "minecraft:commands.generic.invalidType";

    /**
     * {@literal Invalid context provided for given command type}
     */
    String MC_COMMANDS_GENERIC_INVALIDCONTEXT = "minecraft:commands.generic.invalidcontext";

    /**
     * {@literal Provided JSON failed to parse with the following error: %1#s}
     */
    String MC_COMMANDS_GENERIC_JSON_INVALID = "minecraft:commands.generic.json.invalid";

    /**
     * {@literal Max level has to be larger than min level}
     */
    String MC_COMMANDS_GENERIC_LEVELERROR = "minecraft:commands.generic.levelError";

    /**
     * {@literal Body is missing or malformed}
     */
    String MC_COMMANDS_GENERIC_MALFORMED_BODY = "minecraft:commands.generic.malformed.body";

    /**
     * {@literal Invalid request type}
     */
    String MC_COMMANDS_GENERIC_MALFORMED_TYPE = "minecraft:commands.generic.malformed.type";

    /**
     * {@literal No targets matched selector}
     */
    String MC_COMMANDS_GENERIC_NOTARGETMATCH = "minecraft:commands.generic.noTargetMatch";

    /**
     * {@literal Not implemented}
     */
    String MC_COMMANDS_GENERIC_NOTIMPLEMENTED = "minecraft:commands.generic.notimplemented";

    /**
     * {@literal '%1#s' is not a valid number}
     */
    String MC_COMMANDS_GENERIC_NUM_INVALID = "minecraft:commands.generic.num.invalid";

    /**
     * {@literal The number you have entered (%1#d) is too big, it must be at most %2#d}
     */
    String MC_COMMANDS_GENERIC_NUM_TOOBIG = "minecraft:commands.generic.num.tooBig";

    /**
     * {@literal The number you have entered (%1#d) is too small, it must be at least %2#d}
     */
    String MC_COMMANDS_GENERIC_NUM_TOOSMALL = "minecraft:commands.generic.num.tooSmall";

    /**
     * {@literal Cannot access blocks outside of the world}
     */
    String MC_COMMANDS_GENERIC_OUTOFWORLD = "minecraft:commands.generic.outOfWorld";

    /**
     * {@literal '%1#s' is not a valid parameter}
     */
    String MC_COMMANDS_GENERIC_PARAMETER_INVALID = "minecraft:commands.generic.parameter.invalid";

    /**
     * {@literal <insufficient permissions for selector expansion>}
     */
    String MC_COMMANDS_GENERIC_PERMISSION_SELECTOR = "minecraft:commands.generic.permission.selector";

    /**
     * {@literal That player cannot be found}
     */
    String MC_COMMANDS_GENERIC_PLAYER_NOTFOUND = "minecraft:commands.generic.player.notFound";

    /**
     * {@literal Provided protocol version doesn't match Minecraft's protocol version}
     */
    String MC_COMMANDS_GENERIC_PROTOCOL_MISMATCH = "minecraft:commands.generic.protocol.mismatch";

    /**
     * {@literal Minimum selector radius must be smaller than maximum}
     */
    String MC_COMMANDS_GENERIC_RADIUSERROR = "minecraft:commands.generic.radiusError";

    /**
     * {@literal Radius cannot be negative}
     */
    String MC_COMMANDS_GENERIC_RADIUSNEGATIVE = "minecraft:commands.generic.radiusNegative";

    /**
     * {@literal Rotation out of range}
     */
    String MC_COMMANDS_GENERIC_ROTATIONERROR = "minecraft:commands.generic.rotationError";

    /**
     * {@literal The command is already running}
     */
    String MC_COMMANDS_GENERIC_RUNNING = "minecraft:commands.generic.running";

    /**
     * {@literal Source volume is not loaded}
     */
    String MC_COMMANDS_GENERIC_SOURCENOTLOADED = "minecraft:commands.generic.sourceNotLoaded";

    /**
     * {@literal Command step failed}
     */
    String MC_COMMANDS_GENERIC_STEP_FAILED = "minecraft:commands.generic.step.failed";

    /**
     * {@literal Syntax error: Unexpected "%2#s": at "%1#s>>%2#s<<%3#s"}
     */
    String MC_COMMANDS_GENERIC_SYNTAX = "minecraft:commands.generic.syntax";

    /**
     * {@literal Selector must be player-type}
     */
    String MC_COMMANDS_GENERIC_TARGETNOTPLAYER = "minecraft:commands.generic.targetNotPlayer";

    /**
     * {@literal Too many commands have been requested, wait for one to be done}
     */
    String MC_COMMANDS_GENERIC_TOO_MANY_REQUESTS = "minecraft:commands.generic.too.many.requests";

    /**
     * {@literal Too many target name arguments}
     */
    String MC_COMMANDS_GENERIC_TOOMANYNAMES = "minecraft:commands.generic.tooManyNames";

    /**
     * {@literal Too many targets matched selector}
     */
    String MC_COMMANDS_GENERIC_TOOMANYTARGETS = "minecraft:commands.generic.tooManyTargets";

    /**
     * {@literal Unknown command: %s. Please check that the command exists and that you have permission to use it.}
     */
    String MC_COMMANDS_GENERIC_UNKNOWN = "minecraft:commands.generic.unknown";

    /**
     * {@literal Usage: %1#s}
     */
    String MC_COMMANDS_GENERIC_USAGE = "minecraft:commands.generic.usage";

    /**
     * {@literal Usage:}
     */
    String MC_COMMANDS_GENERIC_USAGE_NOPARAM = "minecraft:commands.generic.usage.noparam";

    /**
     * {@literal The requested version doesn't exist for this command}
     */
    String MC_COMMANDS_GENERIC_VERSION_MISMATCH = "minecraft:commands.generic.version.mismatch";

    /**
     * {@literal Command calls not from chat should specify the command's version}
     */
    String MC_COMMANDS_GENERIC_VERSION_MISSING = "minecraft:commands.generic.version.missing";

    /**
     * {@literal Active camera has to be a free camera to target an entity}
     */
    String MC_COMMANDS_GENERIC_WRONGTARGETCAMERA = "minecraft:commands.generic.wrongTargetCamera";

    /**
     * {@literal Gets pixels for a specific chunk.}
     */
    String MC_COMMANDS_GETCHUNKDATA_DESCRIPTION = "minecraft:commands.getchunkdata.description";

    /**
     * {@literal Chunk data received}
     */
    String MC_COMMANDS_GETCHUNKDATA_SUCCESS = "minecraft:commands.getchunkdata.success";

    /**
     * {@literal Gets list of chunks that are loaded.}
     */
    String MC_COMMANDS_GETCHUNKS_DESCRIPTION = "minecraft:commands.getchunks.description";

    /**
     * {@literal Chunk list received}
     */
    String MC_COMMANDS_GETCHUNKS_SUCCESS = "minecraft:commands.getchunks.success";

    /**
     * {@literal Returns the local player name.}
     */
    String MC_COMMANDS_GETLOCALPLAYERNAME_DESCRIPTION = "minecraft:commands.getlocalplayername.description";

    /**
     * {@literal Gets the spawn position of the specified player(s).}
     */
    String MC_COMMANDS_GETSPAWNPOINT_DESCRIPTION = "minecraft:commands.getspawnpoint.description";

    /**
     * {@literal Gets the position of the top non-air block below the specified position}
     */
    String MC_COMMANDS_GETTOPSOLIDBLOCK_DESCRIPTION = "minecraft:commands.gettopsolidblock.description";

    /**
     * {@literal No solid blocks under specified position}
     */
    String MC_COMMANDS_GETTOPSOLIDBLOCK_NOTFOUND = "minecraft:commands.gettopsolidblock.notfound";

    /**
     * {@literal There is no such block with name %1#d}
     */
    String MC_COMMANDS_GIVE_BLOCK_NOTFOUND = "minecraft:commands.give.block.notFound";

    /**
     * {@literal Gives an item to a player.}
     */
    String MC_COMMANDS_GIVE_DESCRIPTION = "minecraft:commands.give.description";

    /**
     * {@literal Invalid command syntax: no such %s exists with that data value}
     */
    String MC_COMMANDS_GIVE_ITEM_INVALID = "minecraft:commands.give.item.invalid";

    /**
     * {@literal There is no such item with name %1#d}
     */
    String MC_COMMANDS_GIVE_ITEM_NOTFOUND = "minecraft:commands.give.item.notFound";

    /**
     * {@literal Could not make exploration map. Feature not found on this dimension}
     */
    String MC_COMMANDS_GIVE_MAP_FEATURENOTFOUND = "minecraft:commands.give.map.featureNotFound";

    /**
     * {@literal Invalid map data provided}
     */
    String MC_COMMANDS_GIVE_MAP_INVALIDDATA = "minecraft:commands.give.map.invalidData";

    /**
     * {@literal Gave %1#s * %2#d to %3#s}
     */
    String MC_COMMANDS_GIVE_SUCCESS = "minecraft:commands.give.success";

    /**
     * {@literal You have been given %1#s * %2#d}
     */
    String MC_COMMANDS_GIVE_SUCCESSRECIPIENT = "minecraft:commands.give.successRecipient";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_GIVE_TAGERROR = "minecraft:commands.give.tagError";

    /**
     * {@literal Sets or gets the paused state of the game for all players.}
     */
    String MC_COMMANDS_GLOBALPAUSE_DESCRIPTION = "minecraft:commands.globalpause.description";

    /**
     * {@literal Invalid conditions for setting pause state}
     */
    String MC_COMMANDS_GLOBALPAUSE_ERROR = "minecraft:commands.globalpause.error";

    /**
     * {@literal Set or got pause state}
     */
    String MC_COMMANDS_GLOBALPAUSE_SUCCESS = "minecraft:commands.globalpause.success";

    /**
     * {@literal "data" has an invalid input, must be an integer.}
     */
    String MC_COMMANDS_HASITEM_FAIL_INVALIDDATA = "minecraft:commands.hasitem.fail.invalidData";

    /**
     * {@literal "quantity" has an invalid input, must be an integer or range of integers.}
     */
    String MC_COMMANDS_HASITEM_FAIL_INVALIDQUANTITY = "minecraft:commands.hasitem.fail.invalidQuantity";

    /**
     * {@literal "slot" has an invalid input, must be an integer or range of integers.}
     */
    String MC_COMMANDS_HASITEM_FAIL_INVALIDSLOT = "minecraft:commands.hasitem.fail.invalidSlot";

    /**
     * {@literal "item" missing, required for "hasitem" filter.}
     */
    String MC_COMMANDS_HASITEM_FAIL_NOITEM = "minecraft:commands.hasitem.fail.noItem";

    /**
     * {@literal Specifying a "slot" is invalid if a "location" is not given.}
     */
    String MC_COMMANDS_HASITEM_FAIL_SLOTNOLOCATION = "minecraft:commands.hasitem.fail.slotNoLocation";

    /**
     * {@literal %s (also %s):}
     */
    String MC_COMMANDS_HELP_COMMAND_ALIASES = "minecraft:commands.help.command.aliases";

    /**
     * {@literal Provides help/list of commands.}
     */
    String MC_COMMANDS_HELP_DESCRIPTION = "minecraft:commands.help.description";

    /**
     * {@literal Tip: Use the <tab> key while typing a command to auto-complete the command or its arguments}
     */
    String MC_COMMANDS_HELP_FOOTER = "minecraft:commands.help.footer";

    /**
     * {@literal --- Showing help page %1#d of %2#d (/help <page>) ---}
     */
    String MC_COMMANDS_HELP_HEADER = "minecraft:commands.help.header";

    /**
     * {@literal Changes the visibility of hud elements.}
     */
    String MC_COMMANDS_HUD_DESCRIPTION = "minecraft:commands.hud.description";

    /**
     * {@literal Hud command successfully executed}
     */
    String MC_COMMANDS_HUD_SUCCESS = "minecraft:commands.hud.success";

    /**
     * {@literal Sets the immutable state of the world.}
     */
    String MC_COMMANDS_IMMUTABLEWORLD_DESCRIPTION = "minecraft:commands.immutableworld.description";

    /**
     * {@literal immutableworld = %s}
     */
    String MC_COMMANDS_IMMUTABLEWORLD_INFO = "minecraft:commands.immutableworld.info";

    /**
     * {@literal Camera}
     */
    String MC_COMMANDS_INPUTPERMISSION_CAMERA = "minecraft:commands.inputpermission.camera";

    /**
     * {@literal Sets whether or not a player's input can affect their character.}
     */
    String MC_COMMANDS_INPUTPERMISSION_DESCRIPTION = "minecraft:commands.inputpermission.description";

    /**
     * {@literal disabled}
     */
    String MC_COMMANDS_INPUTPERMISSION_DISABLED = "minecraft:commands.inputpermission.disabled";

    /**
     * {@literal Dismount }
     */
    String MC_COMMANDS_INPUTPERMISSION_DISMOUNT = "minecraft:commands.inputpermission.dismount";

    /**
     * {@literal enabled}
     */
    String MC_COMMANDS_INPUTPERMISSION_ENABLED = "minecraft:commands.inputpermission.enabled";

    /**
     * {@literal Jump}
     */
    String MC_COMMANDS_INPUTPERMISSION_JUMP = "minecraft:commands.inputpermission.jump";

    /**
     * {@literal Lateral Movement }
     */
    String MC_COMMANDS_INPUTPERMISSION_LATERAL_MOVEMENT = "minecraft:commands.inputpermission.lateral_movement";

    /**
     * {@literal Mount }
     */
    String MC_COMMANDS_INPUTPERMISSION_MOUNT = "minecraft:commands.inputpermission.mount";

    /**
     * {@literal Move Backward}
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_BACKWARD = "minecraft:commands.inputpermission.move_backward";

    /**
     * {@literal Move Forward }
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_FORWARD = "minecraft:commands.inputpermission.move_forward";

    /**
     * {@literal Move Left}
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_LEFT = "minecraft:commands.inputpermission.move_left";

    /**
     * {@literal Move Right}
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVE_RIGHT = "minecraft:commands.inputpermission.move_right";

    /**
     * {@literal Movement}
     */
    String MC_COMMANDS_INPUTPERMISSION_MOVEMENT = "minecraft:commands.inputpermission.movement";

    /**
     * {@literal <%s>: %d %s}
     */
    String MC_COMMANDS_INPUTPERMISSION_QUERY = "minecraft:commands.inputpermission.query";

    /**
     * {@literal <%s>: %d enabled | %d disabled}
     */
    String MC_COMMANDS_INPUTPERMISSION_QUERYVERBOSE = "minecraft:commands.inputpermission.queryverbose";

    /**
     * {@literal '%s' is an invalid filter argument}
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_INVALIDFILTER = "minecraft:commands.inputpermission.set.invalidfilter";

    /**
     * {@literal 'permission' has an invalid input}
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_MISSINGPERMISSION = "minecraft:commands.inputpermission.set.missingpermission";

    /**
     * {@literal 'state' has an invalid input}
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_MISSINGSTATE = "minecraft:commands.inputpermission.set.missingstate";

    /**
     * {@literal %s input %s for %d players  }
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_OUTPUTMULTIPLEPLAYERS = "minecraft:commands.inputpermission.set.outputmultipleplayers";

    /**
     * {@literal %s input %s for %s  }
     */
    String MC_COMMANDS_INPUTPERMISSION_SET_OUTPUTONEPLAYER = "minecraft:commands.inputpermission.set.outputoneplayer";

    /**
     * {@literal Sneak}
     */
    String MC_COMMANDS_INPUTPERMISSION_SNEAK = "minecraft:commands.inputpermission.sneak";

    /**
     * {@literal Lists all items with the given tag.}
     */
    String MC_COMMANDS_ITEMSWITHTAG_DESCRIPTION = "minecraft:commands.itemswithtag.description";

    /**
     * {@literal No items contain the tag %s.}
     */
    String MC_COMMANDS_ITEMSWITHTAG_FAIL_NOITEMSFOUND = "minecraft:commands.itemswithtag.fail.noitemsfound";

    /**
     * {@literal %s}
     */
    String MC_COMMANDS_ITEMSWITHTAG_SUCCESS_LIST_ITEMS = "minecraft:commands.itemswithtag.success.list.items";

    /**
     * {@literal There are %d items with the tag %s:}
     */
    String MC_COMMANDS_ITEMSWITHTAG_SUCCESS_MULTIPLE = "minecraft:commands.itemswithtag.success.multiple";

    /**
     * {@literal There is %d item with the tag %s:}
     */
    String MC_COMMANDS_ITEMSWITHTAG_SUCCESS_SINGLE = "minecraft:commands.itemswithtag.success.single";

    /**
     * {@literal Kicks a player from the server.}
     */
    String MC_COMMANDS_KICK_DESCRIPTION = "minecraft:commands.kick.description";

    /**
     * {@literal Removes a player from the game.}
     */
    String MC_COMMANDS_KICK_DESCRIPTION_EDU = "minecraft:commands.kick.description.edu";

    /**
     * {@literal The host may not be kicked from the game.}
     */
    String MC_COMMANDS_KICK_NO_HOST = "minecraft:commands.kick.no.host";

    /**
     * {@literal Please provide the name of the player you want to kick}
     */
    String MC_COMMANDS_KICK_NO_PLAYER = "minecraft:commands.kick.no.player";

    /**
     * {@literal Teachers may not be removed from the game.}
     */
    String MC_COMMANDS_KICK_NO_TEACHER = "minecraft:commands.kick.no.teacher";

    /**
     * {@literal Could not find player %1#s}
     */
    String MC_COMMANDS_KICK_NOT_FOUND = "minecraft:commands.kick.not.found";

    /**
     * {@literal You may not remove yourself from the game}
     */
    String MC_COMMANDS_KICK_NOT_YOURSELF = "minecraft:commands.kick.not.yourself";

    /**
     * {@literal Kicked %1#s from the game}
     */
    String MC_COMMANDS_KICK_SUCCESS = "minecraft:commands.kick.success";

    /**
     * {@literal Kicked %1#s from the game: '%2#s'}
     */
    String MC_COMMANDS_KICK_SUCCESS_REASON = "minecraft:commands.kick.success.reason";

    /**
     * {@literal Removed %1#s from the game: '%2#s'}
     */
    String MC_COMMANDS_KICK_SUCCESS_REASONEDU = "minecraft:commands.kick.success.reasonedu";

    /**
     * {@literal Players cannot be killed while they are in Creative mode.}
     */
    String MC_COMMANDS_KILL_ATTEMPTKILLPLAYERCREATIVE = "minecraft:commands.kill.attemptKillPlayerCreative";

    /**
     * {@literal Players cannot be removed while they are in Creative mode.}
     */
    String MC_COMMANDS_KILL_ATTEMPTKILLPLAYERCREATIVE_EDU = "minecraft:commands.kill.attemptKillPlayerCreative.edu";

    /**
     * {@literal Kills entities (players, mobs, etc.).}
     */
    String MC_COMMANDS_KILL_DESCRIPTION = "minecraft:commands.kill.description";

    /**
     * {@literal Removes entities (players, mobs, etc.).}
     */
    String MC_COMMANDS_KILL_DESCRIPTION_EDU = "minecraft:commands.kill.description.edu";

    /**
     * {@literal Killed %1#s}
     */
    String MC_COMMANDS_KILL_SUCCESSFUL = "minecraft:commands.kill.successful";

    /**
     * {@literal Removed %1#s}
     */
    String MC_COMMANDS_KILL_SUCCESSFUL_EDU = "minecraft:commands.kill.successful.edu";

    /**
     * {@literal Handle Educational Lesson reporting.}
     */
    String MC_COMMANDS_LESSON_DESCRIPTION = "minecraft:commands.lesson.description";

    /**
     * {@literal Success}
     */
    String MC_COMMANDS_LESSON_SUCCESS = "minecraft:commands.lesson.success";

    /**
     * {@literal Lists players on the server.}
     */
    String MC_COMMANDS_LIST_DESCRIPTION = "minecraft:commands.list.description";

    /**
     * {@literal Could not find a biome of type %1#s within a reasonable distance}
     */
    String MC_COMMANDS_LOCATE_BIOME_FAIL = "minecraft:commands.locate.biome.fail";

    /**
     * {@literal The nearest %1#s is at block %2#s, %3#s, %4#s (%5 blocks away)}
     */
    String MC_COMMANDS_LOCATE_BIOME_SUCCESS = "minecraft:commands.locate.biome.success";

    /**
     * {@literal Displays the coordinates for the closest structure or biome of a given type.}
     */
    String MC_COMMANDS_LOCATE_DESCRIPTION = "minecraft:commands.locate.description";

    /**
     * {@literal The command can only be used by a valid player}
     */
    String MC_COMMANDS_LOCATE_STRUCTURE_FAIL_NOPLAYER = "minecraft:commands.locate.structure.fail.noplayer";

    /**
     * {@literal No valid structure found within a reasonable distance}
     */
    String MC_COMMANDS_LOCATE_STRUCTURE_FAIL_NOSTRUCTUREFOUND = "minecraft:commands.locate.structure.fail.nostructurefound";

    /**
     * {@literal The nearest %1#s is at block %2#s, (y?), %3#s (%4 blocks away)}
     */
    String MC_COMMANDS_LOCATE_STRUCTURE_SUCCESS = "minecraft:commands.locate.structure.success";

    /**
     * {@literal Drops the given loot table into the specified inventory or into the world.}
     */
    String MC_COMMANDS_LOOT_DESCRIPTION = "minecraft:commands.loot.description";

    /**
     * {@literal slotId + count exceeds slot container size %d}
     */
    String MC_COMMANDS_LOOT_FAILURE_COUNTEXCEEDSCONTAINERSIZE = "minecraft:commands.loot.failure.countExceedsContainerSize";

    /**
     * {@literal Entity %1#s has no loot table}
     */
    String MC_COMMANDS_LOOT_FAILURE_ENTITYNOLOOTTABLE = "minecraft:commands.loot.failure.entityNoLootTable";

    /**
     * {@literal Loot table '%1#s' not found}
     */
    String MC_COMMANDS_LOOT_FAILURE_INVALIDLOOTTABLE = "minecraft:commands.loot.failure.invalidLootTable";

    /**
     * {@literal Could not replace slot %s, slot must be between %d and %d for %s}
     */
    String MC_COMMANDS_LOOT_FAILURE_INVALIDSLOTIDFORSLOTTYPE = "minecraft:commands.loot.failure.invalidSlotIdForSlotType";

    /**
     * {@literal Entity %s does not have SlotType %s}
     */
    String MC_COMMANDS_LOOT_FAILURE_INVALIDSLOTTYPEFORENTITY = "minecraft:commands.loot.failure.invalidSlotTypeForEntity";

    /**
     * {@literal Mainhand or offhand should not be used on non entity object.}
     */
    String MC_COMMANDS_LOOT_FAILURE_MAINHANDOFFHANDONNONENTITY = "minecraft:commands.loot.failure.mainhandOffhandOnNonEntity";

    /**
     * {@literal count cannot be negative a negative number}
     */
    String MC_COMMANDS_LOOT_FAILURE_NEGATIVECOUNT = "minecraft:commands.loot.failure.negativeCount";

    /**
     * {@literal Target position %s is not a container}
     */
    String MC_COMMANDS_LOOT_FAILURE_NOCONTAINER = "minecraft:commands.loot.failure.noContainer";

    /**
     * {@literal Replaced %s %d of %s with %d item(s) over %d slot(s)}
     */
    String MC_COMMANDS_LOOT_REPLACESUCCESS = "minecraft:commands.loot.replaceSuccess";

    /**
     * {@literal Replaced %s slot %d with %d item(s) over %d slot(s)}
     */
    String MC_COMMANDS_LOOT_REPLACESUCCESS_BLOCK = "minecraft:commands.loot.replaceSuccess.block";

    /**
     * {@literal %1#s items were dropped.}
     */
    String MC_COMMANDS_LOOT_SUCCESS = "minecraft:commands.loot.success";

    /**
     * {@literal Displays a message about yourself.}
     */
    String MC_COMMANDS_ME_DESCRIPTION = "minecraft:commands.me.description";

    /**
     * {@literal %1#s whispers to you: %2#s}
     */
    String MC_COMMANDS_MESSAGE_DISPLAY_INCOMING = "minecraft:commands.message.display.incoming";

    /**
     * {@literal You whisper to %1#s: %2#s}
     */
    String MC_COMMANDS_MESSAGE_DISPLAY_OUTGOING = "minecraft:commands.message.display.outgoing";

    /**
     * {@literal You can't send a private message to yourself!}
     */
    String MC_COMMANDS_MESSAGE_SAMETARGET = "minecraft:commands.message.sameTarget";

    /**
     * {@literal Controls what mob events are allowed to run.}
     */
    String MC_COMMANDS_MOBEVENT_DESCRIPTION = "minecraft:commands.mobevent.description";

    /**
     * {@literal Mob Event %s (id: %s) is set to false.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTISFALSE = "minecraft:commands.mobevent.eventIsFalse";

    /**
     * {@literal Mob Event %s (id: %s) is set to true.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTISTRUE = "minecraft:commands.mobevent.eventIsTrue";

    /**
     * {@literal Mob Event %s (id: %s) is set to true, but Mob Events are disabled.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTISTRUEBUTEVENTSDISABLED = "minecraft:commands.mobevent.eventIsTrueButEventsDisabled";

    /**
     * {@literal Mob Event %s (id: %s) set to false.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSETTOFALSE = "minecraft:commands.mobevent.eventSetToFalse";

    /**
     * {@literal Mob Event %s (id: %s) set to true.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSETTOTRUE = "minecraft:commands.mobevent.eventSetToTrue";

    /**
     * {@literal Mob Event %s (id: %s) set to true, but Mob Events are disabled.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSETTOTRUEBUTEVENTSDISABLED = "minecraft:commands.mobevent.eventSetToTrueButEventsDisabled";

    /**
     * {@literal Mob Events are disabled. Individual events will not run.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDISFALSE = "minecraft:commands.mobevent.eventsEnabledIsFalse";

    /**
     * {@literal Mob Events are enabled. Individual events which are set to false will not run.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDISTRUE = "minecraft:commands.mobevent.eventsEnabledIsTrue";

    /**
     * {@literal Mob Events are now disabled. Individual events will not run.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDSETTOFALSE = "minecraft:commands.mobevent.eventsEnabledSetToFalse";

    /**
     * {@literal Mob Events are now enabled. Individual events which are set to false will not run.}
     */
    String MC_COMMANDS_MOBEVENT_EVENTSENABLEDSETTOTRUE = "minecraft:commands.mobevent.eventsEnabledSetToTrue";

    /**
     * {@literal Allows you to control playing music tracks.}
     */
    String MC_COMMANDS_MUSIC_DESCRIPTION = "minecraft:commands.music.description";

    /**
     * {@literal You must provide a non-empty track name.}
     */
    String MC_COMMANDS_MUSIC_FAILURE_EMPTYTRACKNAME = "minecraft:commands.music.failure.emptyTrackName";

    /**
     * {@literal %s has been queued to start playing.}
     */
    String MC_COMMANDS_MUSIC_SUCCESS_PLAYACTION = "minecraft:commands.music.success.playAction";

    /**
     * {@literal %s has been queued and it will start playing when the current track stops.}
     */
    String MC_COMMANDS_MUSIC_SUCCESS_QUEUEACTION = "minecraft:commands.music.success.queueAction";

    /**
     * {@literal Custom music will stop now.}
     */
    String MC_COMMANDS_MUSIC_SUCCESS_STOPACTION = "minecraft:commands.music.success.stopAction";

    /**
     * {@literal Music volume will be updated to %d.}
     */
    String MC_COMMANDS_MUSIC_SUCCESS_VOLUMEACTION = "minecraft:commands.music.success.volumeAction";

    /**
     * {@literal Grants operator status to a player.}
     */
    String MC_COMMANDS_OP_DESCRIPTION = "minecraft:commands.op.description";

    /**
     * {@literal Could not op (already op or higher): %s}
     */
    String MC_COMMANDS_OP_FAILED = "minecraft:commands.op.failed";

    /**
     * {@literal You have been opped}
     */
    String MC_COMMANDS_OP_MESSAGE = "minecraft:commands.op.message";

    /**
     * {@literal Opped: %s}
     */
    String MC_COMMANDS_OP_SUCCESS = "minecraft:commands.op.success";

    /**
     * {@literal Invalid operation %1#s.}
     */
    String MC_COMMANDS_OPERATOR_INVALID = "minecraft:commands.operator.invalid";

    /**
     * {@literal Reloads and applies Op permissions.}
     */
    String MC_COMMANDS_OPS_DESCRIPTION = "minecraft:commands.ops.description";

    /**
     * {@literal Ops reloaded from file.}
     */
    String MC_COMMANDS_OPS_RELOADED = "minecraft:commands.ops.reloaded";

    /**
     * {@literal Succeeded in setting operator level for player %s.}
     */
    String MC_COMMANDS_OPS_SET_SUCCESS = "minecraft:commands.ops.set.success";

    /**
     * {@literal CommandBlock}
     */
    String MC_COMMANDS_ORIGIN_COMMANDBLOCK = "minecraft:commands.origin.commandblock";

    /**
     * {@literal DevConsole}
     */
    String MC_COMMANDS_ORIGIN_DEVCONSOLE = "minecraft:commands.origin.devconsole";

    /**
     * {@literal External}
     */
    String MC_COMMANDS_ORIGIN_EXTERNAL = "minecraft:commands.origin.external";

    /**
     * {@literal Script Engine}
     */
    String MC_COMMANDS_ORIGIN_SCRIPT = "minecraft:commands.origin.script";

    /**
     * {@literal Script Debugger}
     */
    String MC_COMMANDS_ORIGIN_SCRIPTDEBUGGER = "minecraft:commands.origin.scriptDebugger";

    /**
     * {@literal Server}
     */
    String MC_COMMANDS_ORIGIN_SERVER = "minecraft:commands.origin.server";

    /**
     * {@literal Teacher}
     */
    String MC_COMMANDS_ORIGIN_TEACHER = "minecraft:commands.origin.teacher";

    /**
     * {@literal Creates particles.}
     */
    String MC_COMMANDS_PARTICLE_DESCRIPTION = "minecraft:commands.particle.description";

    /**
     * {@literal Unknown effect name (%1#s)}
     */
    String MC_COMMANDS_PARTICLE_NOTFOUND = "minecraft:commands.particle.notFound";

    /**
     * {@literal Playing effect %1#s for %2#d times}
     */
    String MC_COMMANDS_PARTICLE_SUCCESS = "minecraft:commands.particle.success";

    /**
     * {@literal Reloads and applies permissions.}
     */
    String MC_COMMANDS_PERMISSIONS_DESCRIPTION = "minecraft:commands.permissions.description";

    /**
     * {@literal Permissions reloaded from file.}
     */
    String MC_COMMANDS_PERMISSIONS_RELOADED = "minecraft:commands.permissions.reloaded";

    /**
     * {@literal Could not save permission level %s for player %s.}
     */
    String MC_COMMANDS_PERMISSIONS_SAVE_FAILED = "minecraft:commands.permissions.save.failed";

    /**
     * {@literal Succeeded in saving permission level %s for player %s.}
     */
    String MC_COMMANDS_PERMISSIONS_SAVE_SUCCESS = "minecraft:commands.permissions.save.success";

    /**
     * {@literal Could not set permission level %s for player %s.}
     */
    String MC_COMMANDS_PERMISSIONS_SET_FAILED = "minecraft:commands.permissions.set.failed";

    /**
     * {@literal Succeeded in setting permission level %s for player %s.}
     */
    String MC_COMMANDS_PERMISSIONS_SET_SUCCESS = "minecraft:commands.permissions.set.success";

    /**
     * {@literal Feature placement failed. Not all chunks are loaded.}
     */
    String MC_COMMANDS_PLACE_CHUNKNOTLOADED_ERROR = "minecraft:commands.place.chunknotloaded.error";

    /**
     * {@literal Places a jigsaw structure, feature, or feature rule in the world.}
     */
    String MC_COMMANDS_PLACE_DESCRIPTION = "minecraft:commands.place.description";

    /**
     * {@literal Place command failed.}
     */
    String MC_COMMANDS_PLACE_ERROR = "minecraft:commands.place.error";

    /**
     * {@literal Jigsaw structure generation failed.}
     */
    String MC_COMMANDS_PLACE_GENERATION_ERROR = "minecraft:commands.place.generation.error";

    /**
     * {@literal Invalid feature name.}
     */
    String MC_COMMANDS_PLACE_INVALIDFEATURE_ERROR = "minecraft:commands.place.invalidfeature.error";

    /**
     * {@literal Invalid feature rule name.}
     */
    String MC_COMMANDS_PLACE_INVALIDFEATURERULE_ERROR = "minecraft:commands.place.invalidfeaturerule.error";

    /**
     * {@literal Feature could not be placed at this location.}
     */
    String MC_COMMANDS_PLACE_INVALIDPOSITION_ERROR = "minecraft:commands.place.invalidposition.error";

    /**
     * {@literal Invalid structure name.}
     */
    String MC_COMMANDS_PLACE_INVALIDSTRUCTURE_ERROR = "minecraft:commands.place.invalidstructure.error";

    /**
     * {@literal Invalid max depth. Value must be between 1 and 20.}
     */
    String MC_COMMANDS_PLACE_MAXDEPTH_ERROR = "minecraft:commands.place.maxdepth.error";

    /**
     * {@literal Jigsaw structure generation failed. Structure placed out of bounds.}
     */
    String MC_COMMANDS_PLACE_OUTOFBOUNDS_ERROR = "minecraft:commands.place.outofbounds.error";

    /**
     * {@literal Place command succeeded.}
     */
    String MC_COMMANDS_PLACE_SUCCESS = "minecraft:commands.place.success";

    /**
     * {@literal Makes one or more entities play a one-off animation. Assumes all variables are setup correctly.}
     */
    String MC_COMMANDS_PLAYANIMATION_DESCRIPTION = "minecraft:commands.playanimation.description";

    /**
     * {@literal Animation request sent to clients for processing.}
     */
    String MC_COMMANDS_PLAYANIMATION_SUCCESS = "minecraft:commands.playanimation.success";

    /**
     * {@literal There are %1#d/%2#d players online:}
     */
    String MC_COMMANDS_PLAYERS_LIST = "minecraft:commands.players.list";

    /**
     * {@literal %s}
     */
    String MC_COMMANDS_PLAYERS_LIST_NAMES = "minecraft:commands.players.list.names";

    /**
     * {@literal Plays a sound.}
     */
    String MC_COMMANDS_PLAYSOUND_DESCRIPTION = "minecraft:commands.playsound.description";

    /**
     * {@literal Player %1#s is too far away to hear the sound}
     */
    String MC_COMMANDS_PLAYSOUND_PLAYERTOOFAR = "minecraft:commands.playsound.playerTooFar";

    /**
     * {@literal Played sound '%1#s' to %2#s}
     */
    String MC_COMMANDS_PLAYSOUND_SUCCESS = "minecraft:commands.playsound.success";

    /**
     * {@literal Toggles on/off coordinates for player.}
     */
    String MC_COMMANDS_POSITION_DESCRIPTION = "minecraft:commands.position.description";

    /**
     * {@literal Unable to host local game}
     */
    String MC_COMMANDS_PUBLISH_FAILED = "minecraft:commands.publish.failed";

    /**
     * {@literal Local game hosted on port %1#s}
     */
    String MC_COMMANDS_PUBLISH_STARTED = "minecraft:commands.publish.started";

    /**
     * {@literal Gets transform, name, and id information about the given target entity or entities.}
     */
    String MC_COMMANDS_QUERYTARGET_DESCRIPTION = "minecraft:commands.querytarget.description";

    /**
     * {@literal Target data: %1#s}
     */
    String MC_COMMANDS_QUERYTARGET_SUCCESS = "minecraft:commands.querytarget.success";

    /**
     * {@literal Unlocks recipe in the recipe book for a player.}
     */
    String MC_COMMANDS_RECIPES_DESCRIPTION = "minecraft:commands.recipes.description";

    /**
     * {@literal The recipe could not be found.}
     */
    String MC_COMMANDS_RECIPES_NOTFOUND = "minecraft:commands.recipes.notFound";

    /**
     * {@literal The recipe is not unlockable in the recipe book.}
     */
    String MC_COMMANDS_RECIPES_NOTUNLOCKABLE = "minecraft:commands.recipes.notUnlockable";

    /**
     * {@literal %1#s does not have the recipe: %2#s}
     */
    String MC_COMMANDS_RECIPES_PLAYERDOESNOTHAVERECIPE = "minecraft:commands.recipes.playerDoesNotHaveRecipe";

    /**
     * {@literal %1#s already has recipe unlocked: %2#s}
     */
    String MC_COMMANDS_RECIPES_PLAYERHASRECIPE = "minecraft:commands.recipes.playerHasRecipe";

    /**
     * {@literal Removed all recipes for %1#s.}
     */
    String MC_COMMANDS_RECIPES_REMOVEDALLRECIPES = "minecraft:commands.recipes.removedAllRecipes";

    /**
     * {@literal Removed recipe for %1#s: %2#s}
     */
    String MC_COMMANDS_RECIPES_REMOVEDRECIPE = "minecraft:commands.recipes.removedRecipe";

    /**
     * {@literal Unlocked all recipes for %1#s.}
     */
    String MC_COMMANDS_RECIPES_UNLOCKEDALLRECIPES = "minecraft:commands.recipes.unlockedAllRecipes";

    /**
     * {@literal Unlocked recipe for %1#s: %2#s}
     */
    String MC_COMMANDS_RECIPES_UNLOCKEDRECIPE = "minecraft:commands.recipes.unlockedRecipe";

    /**
     * {@literal Reloads all function and script files from all behavior packs, or optionally reloads the world and all resource and behavior packs.}
     */
    String MC_COMMANDS_RELOAD_ALL_DESCRIPTION = "minecraft:commands.reload.all.description";

    /**
     * {@literal Reloads all function and script files from all behavior packs.}
     */
    String MC_COMMANDS_RELOAD_DESCRIPTION = "minecraft:commands.reload.description";

    /**
     * {@literal An unexpected error occurred.}
     */
    String MC_COMMANDS_RELOAD_ERROR = "minecraft:commands.reload.error";

    /**
     * {@literal Only host player can reload the world.}
     */
    String MC_COMMANDS_RELOAD_ONLYHOST = "minecraft:commands.reload.onlyHost";

    /**
     * {@literal Reloading the world has been started.}
     */
    String MC_COMMANDS_RELOAD_STARTED = "minecraft:commands.reload.started";

    /**
     * {@literal Function and script files have been reloaded.}
     */
    String MC_COMMANDS_RELOAD_SUCCESS = "minecraft:commands.reload.success";

    /**
     * {@literal Reloads configuration files relating to variables, secrets, permissions, etc.}
     */
    String MC_COMMANDS_RELOADCONFIG_DESCRIPTION = "minecraft:commands.reloadconfig.description";

    /**
     * {@literal Pack configurations have been reloaded from disk from '%1#s'}
     */
    String MC_COMMANDS_RELOADCONFIG_SUCCESS = "minecraft:commands.reloadconfig.success";

    /**
     * {@literal Could not replace slot %d, must be a value between %d and %d.}
     */
    String MC_COMMANDS_REPLACEITEM_BADSLOTNUMBER = "minecraft:commands.replaceitem.badSlotNumber";

    /**
     * {@literal Replaces items in inventories.}
     */
    String MC_COMMANDS_REPLACEITEM_DESCRIPTION = "minecraft:commands.replaceitem.description";

    /**
     * {@literal Could not replace %s slot %d with %d * %s}
     */
    String MC_COMMANDS_REPLACEITEM_FAILED = "minecraft:commands.replaceitem.failed";

    /**
     * {@literal There is already an item occupying %s slot %d.}
     */
    String MC_COMMANDS_REPLACEITEM_KEEPFAILED = "minecraft:commands.replaceitem.keepFailed";

    /**
     * {@literal Block at %s is not a container}
     */
    String MC_COMMANDS_REPLACEITEM_NOCONTAINER = "minecraft:commands.replaceitem.noContainer";

    /**
     * {@literal Replaced %s slot %d with %d * %s}
     */
    String MC_COMMANDS_REPLACEITEM_SUCCESS = "minecraft:commands.replaceitem.success";

    /**
     * {@literal Replaced %s slot %d of %s with %d * %s}
     */
    String MC_COMMANDS_REPLACEITEM_SUCCESS_ENTITY = "minecraft:commands.replaceitem.success.entity";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_REPLACEITEM_TAGERROR = "minecraft:commands.replaceitem.tagError";

    /**
     * {@literal Makes entities ride other entities, stops entities from riding, makes rides evict their riders, or summons rides or riders.}
     */
    String MC_COMMANDS_RIDE_DESCRIPTION = "minecraft:commands.ride.description";

    /**
     * {@literal Rides that have successfully evicted their riders: %1#s}
     */
    String MC_COMMANDS_RIDE_EVICTRIDERS_SUCCESS = "minecraft:commands.ride.evictRiders.success";

    /**
     * {@literal Riders that did not meet ride conditions: %1#s}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_FAILURE = "minecraft:commands.ride.startRiding.failure";

    /**
     * {@literal The group of riders does not fit on the ride.}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_GROUPDOESNTFIT = "minecraft:commands.ride.startRiding.groupDoesntFit";

    /**
     * {@literal The selected entity is not rideable.}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_NOTRIDEABLE = "minecraft:commands.ride.startRiding.notRideable";

    /**
     * {@literal This ride is already full.}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_RIDEFULL = "minecraft:commands.ride.startRiding.rideFull";

    /**
     * {@literal The ride is full, so some riders were not added.}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_RIDEFULLRIDERSLEFT = "minecraft:commands.ride.startRiding.rideFullRidersLeft";

    /**
     * {@literal Riders successfully added to ride: %1#s}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_SUCCESS = "minecraft:commands.ride.startRiding.success";

    /**
     * {@literal Too many riders were specified. Only one is allowed at a time for teleport_ride.}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_TOOMANYRIDERS = "minecraft:commands.ride.startRiding.tooManyRiders";

    /**
     * {@literal Too many rides were specified. Only one is allowed at a time.}
     */
    String MC_COMMANDS_RIDE_STARTRIDING_TOOMANYRIDES = "minecraft:commands.ride.startRiding.tooManyRides";

    /**
     * {@literal Riders that have successfully stopped riding: %1#s}
     */
    String MC_COMMANDS_RIDE_STOPRIDING_SUCCESS = "minecraft:commands.ride.stopRiding.success";

    /**
     * {@literal Rides were summoned, but these riders could not be added to them: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_FAILED = "minecraft:commands.ride.summonRide.failed";

    /**
     * {@literal Entity was summoned, but it is not rideable.}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_NOTRIDEABLE = "minecraft:commands.ride.summonRide.notRideable";

    /**
     * {@literal Rides were not summoned for the following entities that already have rides: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_SKIPPED = "minecraft:commands.ride.summonRide.skipped";

    /**
     * {@literal Ride successfully summoned for these riders: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDE_SUCCESS = "minecraft:commands.ride.summonRide.success";

    /**
     * {@literal Riders were summoned, but could not be added to rides: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_FAILED = "minecraft:commands.ride.summonRider.failed";

    /**
     * {@literal Riders could not ride these rides because they are full: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_FULLRIDES = "minecraft:commands.ride.summonRider.fullRides";

    /**
     * {@literal The following entities are not rideable: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_RIDESWITHNORIDEABLECOMP = "minecraft:commands.ride.summonRider.ridesWithNoRideableComp";

    /**
     * {@literal Riders successfully summoned for rides: %1#s}
     */
    String MC_COMMANDS_RIDE_SUMMONRIDER_SUCCESS = "minecraft:commands.ride.summonRider.success";

    /**
     * {@literal An error occurred when trying to pause the world storage.}
     */
    String MC_COMMANDS_SAVE_ALL_ERROR = "minecraft:commands.save-all.error";

    /**
     * {@literal Data saved. Files are now ready to be copied.}
     */
    String MC_COMMANDS_SAVE_ALL_SUCCESS = "minecraft:commands.save-all.success";

    /**
     * {@literal Saving is already turned off.}
     */
    String MC_COMMANDS_SAVE_OFF_ALREADYOFF = "minecraft:commands.save-off.alreadyOff";

    /**
     * {@literal Saving is already turned on.}
     */
    String MC_COMMANDS_SAVE_ON_ALREADYON = "minecraft:commands.save-on.alreadyOn";

    /**
     * {@literal Enables automatic server saves.}
     */
    String MC_COMMANDS_SAVE_ON_DESCRIPTION = "minecraft:commands.save-on.description";

    /**
     * {@literal A previous save has not been completed.}
     */
    String MC_COMMANDS_SAVE_ON_NOTDONE = "minecraft:commands.save-on.notDone";

    /**
     * {@literal Changes to the world are resumed.}
     */
    String MC_COMMANDS_SAVE_ON_SUCCESS = "minecraft:commands.save-on.success";

    /**
     * {@literal Checks if a previous save-all has finished and lists the files involved.}
     */
    String MC_COMMANDS_SAVE_STATE_DESCRIPTION = "minecraft:commands.save-state.description";

    /**
     * {@literal Control or check how the game saves data to disk.}
     */
    String MC_COMMANDS_SAVE_DESCRIPTION = "minecraft:commands.save.description";

    /**
     * {@literal Turned off world auto-saving}
     */
    String MC_COMMANDS_SAVE_DISABLED = "minecraft:commands.save.disabled";

    /**
     * {@literal Turned on world auto-saving}
     */
    String MC_COMMANDS_SAVE_ENABLED = "minecraft:commands.save.enabled";

    /**
     * {@literal Saving failed: %1#s}
     */
    String MC_COMMANDS_SAVE_FAILED = "minecraft:commands.save.failed";

    /**
     * {@literal Saving...}
     */
    String MC_COMMANDS_SAVE_START = "minecraft:commands.save.start";

    /**
     * {@literal Saved the world}
     */
    String MC_COMMANDS_SAVE_SUCCESS = "minecraft:commands.save.success";

    /**
     * {@literal Sends a message in the chat to other players.}
     */
    String MC_COMMANDS_SAY_DESCRIPTION = "minecraft:commands.say.description";

    /**
     * {@literal No functions were removed with the name %1#s.}
     */
    String MC_COMMANDS_SCHEDULE_CLEAR_FAILURE = "minecraft:commands.schedule.clear.failure";

    /**
     * {@literal Removed %1#d functions with the name %2#s.}
     */
    String MC_COMMANDS_SCHEDULE_CLEAR_SUCCESS = "minecraft:commands.schedule.clear.success";

    /**
     * {@literal Scheduled function %1#s in %2#d ticks at gametime %3#d.}
     */
    String MC_COMMANDS_SCHEDULE_DELAY_FUNCTIONQUEUED = "minecraft:commands.schedule.delay.functionQueued";

    /**
     * {@literal Time cannot be negative.}
     */
    String MC_COMMANDS_SCHEDULE_DELAY_NEGATIVETIME = "minecraft:commands.schedule.delay.negativeTime";

    /**
     * {@literal Schedules an action to be executed once an area is loaded, or after a certain amount of time.}
     */
    String MC_COMMANDS_SCHEDULE_DESCRIPTION = "minecraft:commands.schedule.description";

    /**
     * {@literal Function has been queued and it will be executed when the specified area is fully loaded.}
     */
    String MC_COMMANDS_SCHEDULE_FUNCTIONQUEUED = "minecraft:commands.schedule.functionQueued";

    /**
     * {@literal Successfully executed %1#d function entries in function %2#s.}
     */
    String MC_COMMANDS_SCHEDULE_FUNCTIONRAN = "minecraft:commands.schedule.functionRan";

    /**
     * {@literal The origin needed to execute this function was invalid.}
     */
    String MC_COMMANDS_SCHEDULE_INVALIDORIGIN = "minecraft:commands.schedule.invalidOrigin";

    /**
     * {@literal Ticking area name cannot be empty.}
     */
    String MC_COMMANDS_SCHEDULE_NAME_EMPTY = "minecraft:commands.schedule.name.empty";

    /**
     * {@literal No functions were removed with the ticking area name %1#s.}
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_CLEAR_FAILURE = "minecraft:commands.schedule.tickingarea.clear.failure";

    /**
     * {@literal Removed %1#d functions with the ticking area name %2#s.}
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_CLEAR_SUCCESS = "minecraft:commands.schedule.tickingarea.clear.success";

    /**
     * {@literal No functions were removed with the ticking area name %1#s and with name %2#s.}
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_FUNC_CLEAR_FAILURE = "minecraft:commands.schedule.tickingarea.func.clear.failure";

    /**
     * {@literal Removed %1#d functions with the ticking area name %2#s and with name %3#s.}
     */
    String MC_COMMANDS_SCHEDULE_TICKINGAREA_FUNC_CLEAR_SUCCESS = "minecraft:commands.schedule.tickingarea.func.clear.success";

    /**
     * {@literal More than one ticking areas were found with the name %s. Please use a single area.}
     */
    String MC_COMMANDS_SCHEDULE_TOO_MANY_AREAS = "minecraft:commands.schedule.too.many.areas";

    /**
     * {@literal All matches failed}
     */
    String MC_COMMANDS_SCOREBOARD_ALLMATCHESFAILED = "minecraft:commands.scoreboard.allMatchesFailed";

    /**
     * {@literal Tracks and displays scores for various objectives.}
     */
    String MC_COMMANDS_SCOREBOARD_DESCRIPTION = "minecraft:commands.scoreboard.description";

    /**
     * {@literal Only one user wildcard allowed}
     */
    String MC_COMMANDS_SCOREBOARD_NOMULTIWILDCARD = "minecraft:commands.scoreboard.noMultiWildcard";

    /**
     * {@literal No objective was found by the name '%1#s'}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVENOTFOUND = "minecraft:commands.scoreboard.objectiveNotFound";

    /**
     * {@literal The objective '%1#s' is read-only and cannot be set}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVEREADONLY = "minecraft:commands.scoreboard.objectiveReadOnly";

    /**
     * {@literal An objective with the name '%1#s' already exists}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_ALREADYEXISTS = "minecraft:commands.scoreboard.objectives.add.alreadyExists";

    /**
     * {@literal The display name '%1#s' is too long for an objective, it can be at most %2#d characters long}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_DISPLAYTOOLONG = "minecraft:commands.scoreboard.objectives.add.displayTooLong";

    /**
     * {@literal An objective needs a name.}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_NEEDNAME = "minecraft:commands.scoreboard.objectives.add.needName";

    /**
     * {@literal Added new objective '%1#s' successfully}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_SUCCESS = "minecraft:commands.scoreboard.objectives.add.success";

    /**
     * {@literal The name '%1#s' is too long for an objective, it can be at most %2#d characters long}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_TOOLONG = "minecraft:commands.scoreboard.objectives.add.tooLong";

    /**
     * {@literal Invalid objective criteria type '%1#s'}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_ADD_WRONGTYPE = "minecraft:commands.scoreboard.objectives.add.wrongType";

    /**
     * {@literal Modify scoreboard objectives.}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_DESCRIPTION = "minecraft:commands.scoreboard.objectives.description";

    /**
     * {@literal Showing %1#d objective(s) on scoreboard:}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_LIST_COUNT = "minecraft:commands.scoreboard.objectives.list.count";

    /**
     * {@literal There are no objectives on the scoreboard}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_LIST_EMPTY = "minecraft:commands.scoreboard.objectives.list.empty";

    /**
     * {@literal - %1#s: displays as '%2#s' and is type '%3#s'}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_LIST_ENTRY = "minecraft:commands.scoreboard.objectives.list.entry";

    /**
     * {@literal Removed objective '%1#s' successfully}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_REMOVE_SUCCESS = "minecraft:commands.scoreboard.objectives.remove.success";

    /**
     * {@literal No such display slot '%1#s'}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_INVALIDSLOT = "minecraft:commands.scoreboard.objectives.setdisplay.invalidSlot";

    /**
     * {@literal Cleared objective display slot '%1#s'}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSCLEARED = "minecraft:commands.scoreboard.objectives.setdisplay.successCleared";

    /**
     * {@literal Set the display objective in slot '%1#s' to '%2#s'}
     */
    String MC_COMMANDS_SCOREBOARD_OBJECTIVES_SETDISPLAY_SUCCESSSET = "minecraft:commands.scoreboard.objectives.setdisplay.successSet";

    /**
     * {@literal Added %1#d to [%2#s] for %3#d entities}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ADD_MULTIPLE_SUCCESS = "minecraft:commands.scoreboard.players.add.multiple.success";

    /**
     * {@literal Added %1#d to [%2#s] for %3#s (now %4#d)}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ADD_SUCCESS = "minecraft:commands.scoreboard.players.add.success";

    /**
     * {@literal Objective %1#s is not a trigger}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ENABLE_NOTRIGGER = "minecraft:commands.scoreboard.players.enable.noTrigger";

    /**
     * {@literal Enabled trigger %1#s for %2#s}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_ENABLE_SUCCESS = "minecraft:commands.scoreboard.players.enable.success";

    /**
     * {@literal %1#d}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_GET_SUCCESS = "minecraft:commands.scoreboard.players.get.success";

    /**
     * {@literal Showing %1#d tracked players on the scoreboard:}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_COUNT = "minecraft:commands.scoreboard.players.list.count";

    /**
     * {@literal There are no tracked players on the scoreboard}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_EMPTY = "minecraft:commands.scoreboard.players.list.empty";

    /**
     * {@literal Showing %1#d tracked objective(s) for %2#s:}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_PLAYER_COUNT = "minecraft:commands.scoreboard.players.list.player.count";

    /**
     * {@literal Player %1#s has no scores recorded}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_PLAYER_EMPTY = "minecraft:commands.scoreboard.players.list.player.empty";

    /**
     * {@literal - %2#s: %1#d (%3#s)}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_LIST_PLAYER_ENTRY = "minecraft:commands.scoreboard.players.list.player.entry";

    /**
     * {@literal A player name must be given.}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_NAMENOTFOUND = "minecraft:commands.scoreboard.players.nameNotFound";

    /**
     * {@literal Player Offline}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OFFLINEPLAYERNAME = "minecraft:commands.scoreboard.players.offlinePlayerName";

    /**
     * {@literal Operation results in integer overflow}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_INTEGEROVERFLOW = "minecraft:commands.scoreboard.players.operation.integerOverflow";

    /**
     * {@literal Invalid operation %1#s}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_INVALIDOPERATION = "minecraft:commands.scoreboard.players.operation.invalidOperation";

    /**
     * {@literal No %1#s score for %2#s found}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_NOTFOUND = "minecraft:commands.scoreboard.players.operation.notFound";

    /**
     * {@literal Updated [%1#s] for %2#d entities}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_OPERATION_SUCCESS = "minecraft:commands.scoreboard.players.operation.success";

    /**
     * {@literal Min %1#d is not less than max %2#d}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_RANDOM_INVALIDRANGE = "minecraft:commands.scoreboard.players.random.invalidRange";

    /**
     * {@literal Removed %1#d from [%2#s] for %3#d entities}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_MULTIPLE_SUCCESS = "minecraft:commands.scoreboard.players.remove.multiple.success";

    /**
     * {@literal Removed %1#d from [%2#s] for %3#s (now %4#d)}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_REMOVE_SUCCESS = "minecraft:commands.scoreboard.players.remove.success";

    /**
     * {@literal Reset scores of player %1#s}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_RESET_SUCCESS = "minecraft:commands.scoreboard.players.reset.success";

    /**
     * {@literal Reset score %1#s of player %2#s}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_RESETSCORE_SUCCESS = "minecraft:commands.scoreboard.players.resetscore.success";

    /**
     * {@literal No %1#s score for %2#s found}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SCORE_NOTFOUND = "minecraft:commands.scoreboard.players.score.notFound";

    /**
     * {@literal Set [%1#s] for %2#d entities to %3#d}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_MULTIPLE_SUCCESS = "minecraft:commands.scoreboard.players.set.multiple.success";

    /**
     * {@literal Set [%1#s] for %2#s to %3#d}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_SUCCESS = "minecraft:commands.scoreboard.players.set.success";

    /**
     * {@literal Could not parse dataTag, reason: %1#s}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_TAGERROR = "minecraft:commands.scoreboard.players.set.tagError";

    /**
     * {@literal The dataTag does not match for %1#s}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_SET_TAGMISMATCH = "minecraft:commands.scoreboard.players.set.tagMismatch";

    /**
     * {@literal Score %1#d is NOT in range %2#d to %3#d}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_TEST_FAILED = "minecraft:commands.scoreboard.players.test.failed";

    /**
     * {@literal Score %1#d is in range %2#d to %3#d}
     */
    String MC_COMMANDS_SCOREBOARD_PLAYERS_TEST_SUCCESS = "minecraft:commands.scoreboard.players.test.success";

    /**
     * {@literal No team was found by the name '%1#s'}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMNOTFOUND = "minecraft:commands.scoreboard.teamNotFound";

    /**
     * {@literal A team with the name '%1#s' already exists}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_ALREADYEXISTS = "minecraft:commands.scoreboard.teams.add.alreadyExists";

    /**
     * {@literal The display name '%1#s' is too long for a team, it can be at most %2#d characters long}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_DISPLAYTOOLONG = "minecraft:commands.scoreboard.teams.add.displayTooLong";

    /**
     * {@literal Added new team '%1#s' successfully}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_SUCCESS = "minecraft:commands.scoreboard.teams.add.success";

    /**
     * {@literal The name '%1#s' is too long for a team, it can be at most %2#d characters long}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_ADD_TOOLONG = "minecraft:commands.scoreboard.teams.add.tooLong";

    /**
     * {@literal Team %1#s is already empty, cannot remove nonexistent players}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_EMPTY_ALREADYEMPTY = "minecraft:commands.scoreboard.teams.empty.alreadyEmpty";

    /**
     * {@literal Removed all %1#d player(s) from team %2#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_EMPTY_SUCCESS = "minecraft:commands.scoreboard.teams.empty.success";

    /**
     * {@literal Could not add %1#d player(s) to team %2#s: %3#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_JOIN_FAILURE = "minecraft:commands.scoreboard.teams.join.failure";

    /**
     * {@literal Added %1#d player(s) to team %2#s: %3#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_JOIN_SUCCESS = "minecraft:commands.scoreboard.teams.join.success";

    /**
     * {@literal Could not remove %1#d player(s) from their teams: %2#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LEAVE_FAILURE = "minecraft:commands.scoreboard.teams.leave.failure";

    /**
     * {@literal You are not in a team}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LEAVE_NOTEAM = "minecraft:commands.scoreboard.teams.leave.noTeam";

    /**
     * {@literal Removed %1#d player(s) from their teams: %2#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LEAVE_SUCCESS = "minecraft:commands.scoreboard.teams.leave.success";

    /**
     * {@literal Showing %1#d teams on the scoreboard:}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_COUNT = "minecraft:commands.scoreboard.teams.list.count";

    /**
     * {@literal There are no teams registered on the scoreboard}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_EMPTY = "minecraft:commands.scoreboard.teams.list.empty";

    /**
     * {@literal - %1#s: '%2#s' has %3#d players}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_ENTRY = "minecraft:commands.scoreboard.teams.list.entry";

    /**
     * {@literal Showing %1#d player(s) in team %2#s:}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_PLAYER_COUNT = "minecraft:commands.scoreboard.teams.list.player.count";

    /**
     * {@literal Team %1#s has no players}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_PLAYER_EMPTY = "minecraft:commands.scoreboard.teams.list.player.empty";

    /**
     * {@literal - %2#s: %1#d (%3#s)}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_LIST_PLAYER_ENTRY = "minecraft:commands.scoreboard.teams.list.player.entry";

    /**
     * {@literal Valid values for option %1#s are: %2#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_OPTION_NOVALUE = "minecraft:commands.scoreboard.teams.option.noValue";

    /**
     * {@literal Set option %1#s for team %2#s to %3#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_OPTION_SUCCESS = "minecraft:commands.scoreboard.teams.option.success";

    /**
     * {@literal Removed team %1#s}
     */
    String MC_COMMANDS_SCOREBOARD_TEAMS_REMOVE_SUCCESS = "minecraft:commands.scoreboard.teams.remove.success";

    /**
     * {@literal Applies shaking to the players' camera with a specified intensity and duration.}
     */
    String MC_COMMANDS_SCREENSHAKE_DESCRIPTION = "minecraft:commands.screenshake.description";

    /**
     * {@literal Sent a request to the following players for their camera to shake: %s}
     */
    String MC_COMMANDS_SCREENSHAKE_SUCCESS = "minecraft:commands.screenshake.success";

    /**
     * {@literal Stopping camera shake for the following players: %s}
     */
    String MC_COMMANDS_SCREENSHAKE_SUCCESSSTOP = "minecraft:commands.screenshake.successStop";

    /**
     * {@literal Triggers a script event with an ID and message.}
     */
    String MC_COMMANDS_SCRIPTEVENT_DESCRIPTION = "minecraft:commands.scriptevent.description";

    /**
     * {@literal Identifier must have a namespace that is not "minecraft"}
     */
    String MC_COMMANDS_SCRIPTEVENT_INVALIDNAMESPACE = "minecraft:commands.scriptevent.invalidNamespace";

    /**
     * {@literal Message length exceeds (%1#d) character limit!}
     */
    String MC_COMMANDS_SCRIPTEVENT_MESSAGESIZEEXCEED = "minecraft:commands.scriptevent.messageSizeExceed";

    /**
     * {@literal Seed: %1#s}
     */
    String MC_COMMANDS_SEED_SUCCESS = "minecraft:commands.seed.success";

    /**
     * {@literal Only one entity is allowed, but the provided selector allows more than one}
     */
    String MC_COMMANDS_SELECTOR_ERROR_MORETHANONE = "minecraft:commands.selector.error.moreThanOne";

    /**
     * {@literal Changes a block to another block.}
     */
    String MC_COMMANDS_SETBLOCK_DESCRIPTION = "minecraft:commands.setblock.description";

    /**
     * {@literal Unable to place block}
     */
    String MC_COMMANDS_SETBLOCK_FAILED = "minecraft:commands.setblock.failed";

    /**
     * {@literal The block couldn't be placed}
     */
    String MC_COMMANDS_SETBLOCK_NOCHANGE = "minecraft:commands.setblock.noChange";

    /**
     * {@literal There is no such block with ID/name %1#s}
     */
    String MC_COMMANDS_SETBLOCK_NOTFOUND = "minecraft:commands.setblock.notFound";

    /**
     * {@literal Cannot place block outside of the world}
     */
    String MC_COMMANDS_SETBLOCK_OUTOFWORLD = "minecraft:commands.setblock.outOfWorld";

    /**
     * {@literal Block placed}
     */
    String MC_COMMANDS_SETBLOCK_SUCCESS = "minecraft:commands.setblock.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_SETBLOCK_TAGERROR = "minecraft:commands.setblock.tagError";

    /**
     * {@literal Sets a fixed slot to a specified item.}
     */
    String MC_COMMANDS_SETFIXEDINVSLOT_DESCRIPTION = "minecraft:commands.setfixedinvslot.description";

    /**
     * {@literal Fixed Inventory Slot %1#d set to %2#s}
     */
    String MC_COMMANDS_SETFIXEDINVSLOT_SUCCESS = "minecraft:commands.setfixedinvslot.success";

    /**
     * {@literal Sets the number of fixed inventory slots for the server.}
     */
    String MC_COMMANDS_SETFIXEDINVSLOTS_DESCRIPTION = "minecraft:commands.setfixedinvslots.description";

    /**
     * {@literal Number of Fixed Inventory Slots set to %1#d}
     */
    String MC_COMMANDS_SETFIXEDINVSLOTS_SUCCESS = "minecraft:commands.setfixedinvslots.success";

    /**
     * {@literal Successfully set the idle timeout to %1#d minutes.}
     */
    String MC_COMMANDS_SETIDLETIMEOUT_SUCCESS = "minecraft:commands.setidletimeout.success";

    /**
     * {@literal Sets the maximum number of players for this game session.}
     */
    String MC_COMMANDS_SETMAXPLAYERS_DESCRIPTION = "minecraft:commands.setmaxplayers.description";

    /**
     * {@literal Set max players to %1#d.}
     */
    String MC_COMMANDS_SETMAXPLAYERS_SUCCESS = "minecraft:commands.setmaxplayers.success";

    /**
     * {@literal (Bound to current player count)}
     */
    String MC_COMMANDS_SETMAXPLAYERS_SUCCESS_LOWERBOUND = "minecraft:commands.setmaxplayers.success.lowerbound";

    /**
     * {@literal (Bound to maximum allowed connections)}
     */
    String MC_COMMANDS_SETMAXPLAYERS_SUCCESS_UPPERBOUND = "minecraft:commands.setmaxplayers.success.upperbound";

    /**
     * {@literal Controls whether the client or the server is responsible for checking if a player's movement is valid. Use "client" to set to client, or "server" to set to server.}
     */
    String MC_COMMANDS_SETMOVEMENTAUTHORITY_DESCRIPTION = "minecraft:commands.setmovementauthority.description";

    /**
     * {@literal Not allowed to set movement authority to %s, current value is %s}
     */
    String MC_COMMANDS_SETMOVEMENTAUTHORITY_ERROR = "minecraft:commands.setmovementauthority.error";

    /**
     * {@literal Set movement authority to %s}
     */
    String MC_COMMANDS_SETMOVEMENTAUTHORITY_SUCCESS = "minecraft:commands.setmovementauthority.success";

    /**
     * {@literal Sets the world spawn.}
     */
    String MC_COMMANDS_SETWORLDSPAWN_DESCRIPTION = "minecraft:commands.setworldspawn.description";

    /**
     * {@literal The world spawn can not be set in legacy worlds}
     */
    String MC_COMMANDS_SETWORLDSPAWN_NOTALLOWEDINLEGACYWORLDS = "minecraft:commands.setworldspawn.notAllowedInLegacyWorlds";

    /**
     * {@literal Set the world spawn point to (%1#d, %2#d, %3#d)}
     */
    String MC_COMMANDS_SETWORLDSPAWN_SUCCESS = "minecraft:commands.setworldspawn.success";

    /**
     * {@literal The world spawn can not be set in this dimension}
     */
    String MC_COMMANDS_SETWORLDSPAWN_WRONGDIMENSION = "minecraft:commands.setworldspawn.wrongDimension";

    /**
     * {@literal Creates a particle emitter}
     */
    String MC_COMMANDS_SPAWNPARTICLEEMITTER_DESCRIPTION = "minecraft:commands.spawnParticleEmitter.description";

    /**
     * {@literal Request to create %1#s sent to all players.}
     */
    String MC_COMMANDS_SPAWNPARTICLEEMITTER_SUCCESS = "minecraft:commands.spawnParticleEmitter.success";

    /**
     * {@literal Sets the spawn point for a player.}
     */
    String MC_COMMANDS_SPAWNPOINT_DESCRIPTION = "minecraft:commands.spawnpoint.description";

    /**
     * {@literal Set spawn point for %1#s}
     */
    String MC_COMMANDS_SPAWNPOINT_SUCCESS_MULTIPLE_GENERIC = "minecraft:commands.spawnpoint.success.multiple.generic";

    /**
     * {@literal Set spawn point for %1#s to (%2#d, %3#d, %4#d)}
     */
    String MC_COMMANDS_SPAWNPOINT_SUCCESS_MULTIPLE_SPECIFIC = "minecraft:commands.spawnpoint.success.multiple.specific";

    /**
     * {@literal Set %1#s's spawn point to (%2#d, %3#d, %4#d)}
     */
    String MC_COMMANDS_SPAWNPOINT_SUCCESS_SINGLE = "minecraft:commands.spawnpoint.success.single";

    /**
     * {@literal The spawn point cannot be set in this dimension}
     */
    String MC_COMMANDS_SPAWNPOINT_WRONGDIMENSION = "minecraft:commands.spawnpoint.wrongDimension";

    /**
     * {@literal Teleports entities to random locations.}
     */
    String MC_COMMANDS_SPREADPLAYERS_DESCRIPTION = "minecraft:commands.spreadplayers.description";

    /**
     * {@literal Could not spread %1#s players around %2#s,%3#s (too many players for space - try using spread of at most %4#s)}
     */
    String MC_COMMANDS_SPREADPLAYERS_FAILURE_PLAYERS = "minecraft:commands.spreadplayers.failure.players";

    /**
     * {@literal Could not spread %1#s players around %2#s,%3#s (no safe locations found after %4#s attempts)}
     */
    String MC_COMMANDS_SPREADPLAYERS_FAILURE_PLAYERS_UNSAFE = "minecraft:commands.spreadplayers.failure.players.unsafe";

    /**
     * {@literal Could not spread %1#s teams around %2#s,%3#s (too many players for space - try using spread of at most %4#s)}
     */
    String MC_COMMANDS_SPREADPLAYERS_FAILURE_TEAMS = "minecraft:commands.spreadplayers.failure.teams";

    /**
     * {@literal (Average distance between players is %1#s blocks apart after %2#s iterations)}
     */
    String MC_COMMANDS_SPREADPLAYERS_INFO_PLAYERS = "minecraft:commands.spreadplayers.info.players";

    /**
     * {@literal (Average distance between teams is %1#s blocks apart after %2#s iterations)}
     */
    String MC_COMMANDS_SPREADPLAYERS_INFO_TEAMS = "minecraft:commands.spreadplayers.info.teams";

    /**
     * {@literal Spreading %1#s players %2#s blocks around %3#s,%4#s (min %5#s blocks apart)}
     */
    String MC_COMMANDS_SPREADPLAYERS_SPREADING_PLAYERS = "minecraft:commands.spreadplayers.spreading.players";

    /**
     * {@literal Spreading %1#s teams %2#s blocks around %3#s,%4#s (min %5#s blocks apart)}
     */
    String MC_COMMANDS_SPREADPLAYERS_SPREADING_TEAMS = "minecraft:commands.spreadplayers.spreading.teams";

    /**
     * {@literal Successfully spread %1#s players around %2#s,%3#s}
     */
    String MC_COMMANDS_SPREADPLAYERS_SUCCESS_PLAYERS = "minecraft:commands.spreadplayers.success.players";

    /**
     * {@literal Successfully spread %1#s teams around %2#s,%3#s}
     */
    String MC_COMMANDS_SPREADPLAYERS_SUCCESS_TEAMS = "minecraft:commands.spreadplayers.success.teams";

    /**
     * {@literal Cleared %1#s stats}
     */
    String MC_COMMANDS_STATS_CLEARED = "minecraft:commands.stats.cleared";

    /**
     * {@literal Invalid parameters}
     */
    String MC_COMMANDS_STATS_FAILED = "minecraft:commands.stats.failed";

    /**
     * {@literal Block at %1#d, %2#d, %3#d can not track stats}
     */
    String MC_COMMANDS_STATS_NOCOMPATIBLEBLOCK = "minecraft:commands.stats.noCompatibleBlock";

    /**
     * {@literal Storing %1#s stats in %2#s on %3#s}
     */
    String MC_COMMANDS_STATS_SUCCESS = "minecraft:commands.stats.success";

    /**
     * {@literal Stops the server.}
     */
    String MC_COMMANDS_STOP_DESCRIPTION = "minecraft:commands.stop.description";

    /**
     * {@literal Stopping the server}
     */
    String MC_COMMANDS_STOP_START = "minecraft:commands.stop.start";

    /**
     * {@literal Stops a sound.}
     */
    String MC_COMMANDS_STOPSOUND_DESCRIPTION = "minecraft:commands.stopsound.description";

    /**
     * {@literal Stopped sound %s for %s}
     */
    String MC_COMMANDS_STOPSOUND_SUCCESS = "minecraft:commands.stopsound.success";

    /**
     * {@literal Stopped all sounds for %s}
     */
    String MC_COMMANDS_STOPSOUND_SUCCESS_ALL = "minecraft:commands.stopsound.success.all";

    /**
     * {@literal Structure %s deleted.}
     */
    String MC_COMMANDS_STRUCTURE_DELETE_SUCCESS = "minecraft:commands.structure.delete.success";

    /**
     * {@literal Saves or loads a structure in the world.}
     */
    String MC_COMMANDS_STRUCTURE_DESCRIPTION = "minecraft:commands.structure.description";

    /**
     * {@literal Tried to find a structure with name %s but had %1#s results for that name}
     */
    String MC_COMMANDS_STRUCTURE_EDITOR_AMBIGUOUSQUERY = "minecraft:commands.structure.editor.ambiguousquery";

    /**
     * {@literal Can't load a structure with no entities and no blocks}
     */
    String MC_COMMANDS_STRUCTURE_EMPTY_LOAD = "minecraft:commands.structure.empty.load";

    /**
     * {@literal Can't save a structure with no entities and no blocks}
     */
    String MC_COMMANDS_STRUCTURE_EMPTY_SAVE = "minecraft:commands.structure.empty.save";

    /**
     * {@literal An unexpected error occurred}
     */
    String MC_COMMANDS_STRUCTURE_ERROR = "minecraft:commands.structure.error";

    /**
     * {@literal Animation time must be 0 or higher}
     */
    String MC_COMMANDS_STRUCTURE_INVALID_ANIMATIONTIME = "minecraft:commands.structure.invalid.animationTime";

    /**
     * {@literal Integrity must be in the range of 0 to 100}
     */
    String MC_COMMANDS_STRUCTURE_INVALID_INTEGRITY = "minecraft:commands.structure.invalid.integrity";

    /**
     * {@literal Name should be in the format of "name" or "namespace:name". If not namespace is provided, the default one will be assigned.}
     */
    String MC_COMMANDS_STRUCTURE_INVALID_NAME = "minecraft:commands.structure.invalid.name";

    /**
     * {@literal A placement request has been queued and it will be executed when the specified area is fully loaded.}
     */
    String MC_COMMANDS_STRUCTURE_LOAD_QUEUED = "minecraft:commands.structure.load.queued";

    /**
     * {@literal Loaded a structure of name %s}
     */
    String MC_COMMANDS_STRUCTURE_LOAD_SUCCESS = "minecraft:commands.structure.load.success";

    /**
     * {@literal The structure %s can't be found. Make sure the name was spelled correctly and try again.}
     */
    String MC_COMMANDS_STRUCTURE_NOTFOUND = "minecraft:commands.structure.notFound";

    /**
     * {@literal Saved a structure with name %s}
     */
    String MC_COMMANDS_STRUCTURE_SAVE_SUCCESS = "minecraft:commands.structure.save.success";

    /**
     * {@literal A structure's size cannot be larger than (%1#d, %2#d, %3#d), it was (%4#d, %5#d, %6#d)}
     */
    String MC_COMMANDS_STRUCTURE_SIZE_TOOBIG = "minecraft:commands.structure.size.tooBig";

    /**
     * {@literal A structure's size cannot be smaller than (%1#d, %2#d, %3#d), it was (%4#d, %5#d, %6#d)}
     */
    String MC_COMMANDS_STRUCTURE_SIZE_TOOSMALL = "minecraft:commands.structure.size.tooSmall";

    /**
     * {@literal Unknown structure action provided}
     */
    String MC_COMMANDS_STRUCTURE_UNKNOWN_ACTION = "minecraft:commands.structure.unknown.action";

    /**
     * {@literal The feature to load a structure in an unloaded area hasn't been implemented yet}
     */
    String MC_COMMANDS_STRUCTURE_UNLOADEDCHUNKS = "minecraft:commands.structure.unloadedChunks";

    /**
     * {@literal Cannot place blocks outside of the world. Try a value of %d or lower for the Y component of <to: x y z>.}
     */
    String MC_COMMANDS_STRUCTURE_Y_TOOHIGH = "minecraft:commands.structure.y.tooHigh";

    /**
     * {@literal Cannot place blocks outside of the world. Try a value greater than %d for the Y component of <to: x y z>.}
     */
    String MC_COMMANDS_STRUCTURE_Y_TOOLOW = "minecraft:commands.structure.y.tooLow";

    /**
     * {@literal Summons an entity.}
     */
    String MC_COMMANDS_SUMMON_DESCRIPTION = "minecraft:commands.summon.description";

    /**
     * {@literal Unable to summon object}
     */
    String MC_COMMANDS_SUMMON_FAILED = "minecraft:commands.summon.failed";

    /**
     * {@literal You need Creative + Operator to summon an NPC}
     */
    String MC_COMMANDS_SUMMON_FAILED_NPC = "minecraft:commands.summon.failed.npc";

    /**
     * {@literal Cannot summon the object out of the world}
     */
    String MC_COMMANDS_SUMMON_OUTOFWORLD = "minecraft:commands.summon.outOfWorld";

    /**
     * {@literal Object successfully summoned}
     */
    String MC_COMMANDS_SUMMON_SUCCESS = "minecraft:commands.summon.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_SUMMON_TAGERROR = "minecraft:commands.summon.tagError";

    /**
     * {@literal Target either already has the tag or has too many tags}
     */
    String MC_COMMANDS_TAG_ADD_FAILED = "minecraft:commands.tag.add.failed";

    /**
     * {@literal Added tag '%1#s' to %2#d entities}
     */
    String MC_COMMANDS_TAG_ADD_SUCCESS_MULTIPLE = "minecraft:commands.tag.add.success.multiple";

    /**
     * {@literal Added tag '%1#s' to %2#s}
     */
    String MC_COMMANDS_TAG_ADD_SUCCESS_SINGLE = "minecraft:commands.tag.add.success.single";

    /**
     * {@literal Manages tags stored in entities.}
     */
    String MC_COMMANDS_TAG_DESCRIPTION = "minecraft:commands.tag.description";

    /**
     * {@literal There are no tags on the %d entities}
     */
    String MC_COMMANDS_TAG_LIST_MULTIPLE_EMPTY = "minecraft:commands.tag.list.multiple.empty";

    /**
     * {@literal The %1#d entities have %2#d total tags: %3#s}
     */
    String MC_COMMANDS_TAG_LIST_MULTIPLE_SUCCESS = "minecraft:commands.tag.list.multiple.success";

    /**
     * {@literal %s has no tags}
     */
    String MC_COMMANDS_TAG_LIST_SINGLE_EMPTY = "minecraft:commands.tag.list.single.empty";

    /**
     * {@literal %1#s has %2#d tags: %3#s}
     */
    String MC_COMMANDS_TAG_LIST_SINGLE_SUCCESS = "minecraft:commands.tag.list.single.success";

    /**
     * {@literal Target does not have this tag}
     */
    String MC_COMMANDS_TAG_REMOVE_FAILED = "minecraft:commands.tag.remove.failed";

    /**
     * {@literal Removed tag '%1#s' from %2#d entities}
     */
    String MC_COMMANDS_TAG_REMOVE_SUCCESS_MULTIPLE = "minecraft:commands.tag.remove.success.multiple";

    /**
     * {@literal Removed tag '%1#s' from %2#s}
     */
    String MC_COMMANDS_TAG_REMOVE_SUCCESS_SINGLE = "minecraft:commands.tag.remove.success.single";

    /**
     * {@literal Lists all tags associated with the given item.}
     */
    String MC_COMMANDS_TAGSFROMITEM_DESCRIPTION = "minecraft:commands.tagsfromitem.description";

    /**
     * {@literal No tags found on the item: %s.}
     */
    String MC_COMMANDS_TAGSFROMITEM_FAIL_NOTAGSFOUND = "minecraft:commands.tagsfromitem.fail.notagsfound";

    /**
     * {@literal %s}
     */
    String MC_COMMANDS_TAGSFROMITEM_SUCCESS_LIST_TAGS = "minecraft:commands.tagsfromitem.success.list.tags";

    /**
     * {@literal There are %d tags on the item %s:}
     */
    String MC_COMMANDS_TAGSFROMITEM_SUCCESS_MULTIPLE = "minecraft:commands.tagsfromitem.success.multiple";

    /**
     * {@literal There is %d tag on the item %s:}
     */
    String MC_COMMANDS_TAGSFROMITEM_SUCCESS_SINGLE = "minecraft:commands.tagsfromitem.success.single";

    /**
     * {@literal Sends a private message to one or more players.}
     */
    String MC_COMMANDS_TELL_DESCRIPTION = "minecraft:commands.tell.description";

    /**
     * {@literal Sends a JSON message to players.}
     */
    String MC_COMMANDS_TELLRAW_DESCRIPTION = "minecraft:commands.tellraw.description";

    /**
     * {@literal Json value in rawtext array was not an object. Example: "rawtext": [{ "text" : "my text" }]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_ITEMISNOTOBJECT = "minecraft:commands.tellraw.error.itemIsNotObject";

    /**
     * {@literal No data was provided.}
     */
    String MC_COMMANDS_TELLRAW_ERROR_NODATA = "minecraft:commands.tellraw.error.noData";

    /**
     * {@literal Rawtext object must contain an array. Example: "rawtext":[{..}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_NOTARRAY = "minecraft:commands.tellraw.error.notArray";

    /**
     * {@literal name field in the score field must be a string. Example "rawtext":[{"score": {"name": "my_name", "objective": "my_objective"}}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_SCORENAMENOTSTRING = "minecraft:commands.tellraw.error.scoreNameNotString";

    /**
     * {@literal score field in rawtext must contain an object. Example "rawtext":[{"score": {"name": "my_name", "objective": "my_objective"}}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_SCORENOTOBJECT = "minecraft:commands.tellraw.error.scoreNotObject";

    /**
     * {@literal objective field in the score field must be a string. Example "rawtext":[{"score": {"name": "my_name", "objective": "my_objective"}}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_SCOREOBJECTIVENOTSTRING = "minecraft:commands.tellraw.error.scoreObjectiveNotString";

    /**
     * {@literal selector field in rawtext must contain a selector as a string. Example: "rawtext":[{"selector": "@e"}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_SELECTORNOTSTRING = "minecraft:commands.tellraw.error.selectorNotString";

    /**
     * {@literal text field in rawtext must contain a string. Example: "rawtext":[{"text": "text to display"}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_TEXTNOTSTRING = "minecraft:commands.tellraw.error.textNotString";

    /**
     * {@literal translate field in rawtext must contain a language key. Example: "rawtext":[{"translate": "gui.ok"}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_TRANSLATENOTSTRING = "minecraft:commands.tellraw.error.translateNotString";

    /**
     * {@literal with field in rawtext must contain a array or another rawtext object. Example 1: "rawtext":[{"translate": "chat.type.announcement", "with": [ "value1", "value2" ]}]  Example 2: "rawtext":[{"translate": "chat.type.announcement", "with":{"rawtext":[{"text": "value1"}, {"text": "value2"}]}}]}
     */
    String MC_COMMANDS_TELLRAW_ERROR_WITHNOTARRAYORRAWTEXT = "minecraft:commands.tellraw.error.withNotArrayOrRawText";

    /**
     * {@literal Invalid json string data.}
     */
    String MC_COMMANDS_TELLRAW_JSONSTRINGEXCEPTION = "minecraft:commands.tellraw.jsonStringException";

    /**
     * {@literal Counts entities (players, mobs, items, etc.) matching specified conditions.}
     */
    String MC_COMMANDS_TESTFOR_DESCRIPTION = "minecraft:commands.testfor.description";

    /**
     * {@literal %1#s did not match the required data structure}
     */
    String MC_COMMANDS_TESTFOR_FAILURE = "minecraft:commands.testfor.failure";

    /**
     * {@literal Found %1#s}
     */
    String MC_COMMANDS_TESTFOR_SUCCESS = "minecraft:commands.testfor.success";

    /**
     * {@literal Data tag parsing failed: %1#s}
     */
    String MC_COMMANDS_TESTFOR_TAGERROR = "minecraft:commands.testfor.tagError";

    /**
     * {@literal Tests whether a certain block is in a specific location.}
     */
    String MC_COMMANDS_TESTFORBLOCK_DESCRIPTION = "minecraft:commands.testforblock.description";

    /**
     * {@literal The block at %1#d,%2#d,%3#d did not match the expected block state.}
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_DATA = "minecraft:commands.testforblock.failed.data";

    /**
     * {@literal The block at %1#d,%2#d,%3#d did not have the required NBT keys.}
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_NBT = "minecraft:commands.testforblock.failed.nbt";

    /**
     * {@literal The block at %1#d,%2#d,%3#d is %4#s (expected: %5#s).}
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_TILE = "minecraft:commands.testforblock.failed.tile";

    /**
     * {@literal The block at %1#d,%2#d,%3#d is not a tile entity and cannot support tag matching.}
     */
    String MC_COMMANDS_TESTFORBLOCK_FAILED_TILEENTITY = "minecraft:commands.testforblock.failed.tileEntity";

    /**
     * {@literal Cannot test for block outside of the world}
     */
    String MC_COMMANDS_TESTFORBLOCK_OUTOFWORLD = "minecraft:commands.testforblock.outOfWorld";

    /**
     * {@literal Successfully found the block at %1#d,%2#d,%3#d.}
     */
    String MC_COMMANDS_TESTFORBLOCK_SUCCESS = "minecraft:commands.testforblock.success";

    /**
     * {@literal Tests whether the blocks in two regions match.}
     */
    String MC_COMMANDS_TESTFORBLOCKS_DESCRIPTION = "minecraft:commands.testforblocks.description";

    /**
     * {@literal Added ticking area from %1#d to %2#d marked for preload.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_BOUNDS_PRELOAD_SUCCESS = "minecraft:commands.tickingarea-add-bounds.preload.success";

    /**
     * {@literal Added ticking area from %1#d to %2#d.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_BOUNDS_SUCCESS = "minecraft:commands.tickingarea-add-bounds.success";

    /**
     * {@literal Added ticking area centered at %1#d with a radius of %2#d chunks marked for preload.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CIRCLE_PRELOAD_SUCCESS = "minecraft:commands.tickingarea-add-circle.preload.success";

    /**
     * {@literal Added ticking area centered at %1#d with a radius of %2#d chunks.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CIRCLE_SUCCESS = "minecraft:commands.tickingarea-add-circle.success";

    /**
     * {@literal Ticking area contains more than %1#d chunks, ticking area is too large and cannot be created.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CHUNKFAILURE = "minecraft:commands.tickingarea-add.chunkfailure";

    /**
     * {@literal A ticking area with the name %1#s already exists.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_CONFLICTINGNAME = "minecraft:commands.tickingarea-add.conflictingname";

    /**
     * {@literal Max number of ticking areas (%1#d) has already been reached. Cannot add more ticking areas.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_FAILURE = "minecraft:commands.tickingarea-add.failure";

    /**
     * {@literal Radius cannot be larger than %1#d, ticking area is too large and cannot be created.}
     */
    String MC_COMMANDS_TICKINGAREA_ADD_RADIUSFAILURE = "minecraft:commands.tickingarea-add.radiusfailure";

    /**
     * {@literal chunks}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_CHUNKS = "minecraft:commands.tickingarea-list.chunks";

    /**
     * {@literal Radius}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_CIRCLE_RADIUS = "minecraft:commands.tickingarea-list.circle.radius";

    /**
     * {@literal No ticking areas exist in any dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_FAILURE_ALLDIMENSIONS = "minecraft:commands.tickingarea-list.failure.allDimensions";

    /**
     * {@literal Preload}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_PRELOAD = "minecraft:commands.tickingarea-list.preload";

    /**
     * {@literal List of all ticking areas in all dimensions}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_SUCCESS_ALLDIMENSIONS = "minecraft:commands.tickingarea-list.success.allDimensions";

    /**
     * {@literal List of all ticking areas in current dimension}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_SUCCESS_CURRENTDIMENSION = "minecraft:commands.tickingarea-list.success.currentDimension";

    /**
     * {@literal to}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_TO = "minecraft:commands.tickingarea-list.to";

    /**
     * {@literal Circle}
     */
    String MC_COMMANDS_TICKINGAREA_LIST_TYPE_CIRCLE = "minecraft:commands.tickingarea-list.type.circle";

    /**
     * {@literal No ticking areas named %1#s exist in the current dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_BYNAME_FAILURE = "minecraft:commands.tickingarea-preload.byname.failure";

    /**
     * {@literal No ticking areas containing the block position %1#d exist in the current dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_BYPOSITION_FAILURE = "minecraft:commands.tickingarea-preload.byposition.failure";

    /**
     * {@literal %1#d matching area(s) are marked for preload.}
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_COUNT = "minecraft:commands.tickingarea-preload.count";

    /**
     * {@literal Updated ticking area(s)}
     */
    String MC_COMMANDS_TICKINGAREA_PRELOAD_SUCCESS = "minecraft:commands.tickingarea-preload.success";

    /**
     * {@literal No ticking areas named %1#s exist in the current dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_BYNAME_FAILURE = "minecraft:commands.tickingarea-remove.byname.failure";

    /**
     * {@literal No ticking areas containing the block position %1#d exist in the current dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_FAILURE = "minecraft:commands.tickingarea-remove.failure";

    /**
     * {@literal Removed ticking area(s)}
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_SUCCESS = "minecraft:commands.tickingarea-remove.success";

    /**
     * {@literal No ticking areas exist in the current dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_ALL_FAILURE = "minecraft:commands.tickingarea-remove_all.failure";

    /**
     * {@literal Removed ticking area(s)}
     */
    String MC_COMMANDS_TICKINGAREA_REMOVE_ALL_SUCCESS = "minecraft:commands.tickingarea-remove_all.success";

    /**
     * {@literal Add, remove, or list ticking areas.}
     */
    String MC_COMMANDS_TICKINGAREA_DESCRIPTION = "minecraft:commands.tickingarea.description";

    /**
     * {@literal There are %1#d ticking area(s) from entities with the tick_world component.}
     */
    String MC_COMMANDS_TICKINGAREA_ENTITYCOUNT = "minecraft:commands.tickingarea.entitycount";

    /**
     * {@literal %1#d/%2#d ticking areas in use.}
     */
    String MC_COMMANDS_TICKINGAREA_INUSE = "minecraft:commands.tickingarea.inuse";

    /**
     * {@literal No ticking areas exist in the current dimension.}
     */
    String MC_COMMANDS_TICKINGAREA_NONEEXIST_CURRENTDIMENSION = "minecraft:commands.tickingarea.noneExist.currentDimension";

    /**
     * {@literal Added %1#d to the time}
     */
    String MC_COMMANDS_TIME_ADDED = "minecraft:commands.time.added";

    /**
     * {@literal Changes or queries the world's game time.}
     */
    String MC_COMMANDS_TIME_DESCRIPTION = "minecraft:commands.time.description";

    /**
     * {@literal Always Day is enabled in this world.}
     */
    String MC_COMMANDS_TIME_DISABLED = "minecraft:commands.time.disabled";

    /**
     * {@literal Day is %d}
     */
    String MC_COMMANDS_TIME_QUERY_DAY = "minecraft:commands.time.query.day";

    /**
     * {@literal Daytime is %d}
     */
    String MC_COMMANDS_TIME_QUERY_DAYTIME = "minecraft:commands.time.query.daytime";

    /**
     * {@literal Gametime is %d}
     */
    String MC_COMMANDS_TIME_QUERY_GAMETIME = "minecraft:commands.time.query.gametime";

    /**
     * {@literal Set the time to %1#d}
     */
    String MC_COMMANDS_TIME_SET = "minecraft:commands.time.set";

    /**
     * {@literal Time %1#s}
     */
    String MC_COMMANDS_TIME_STOP = "minecraft:commands.time.stop";

    /**
     * {@literal Controls screen titles.}
     */
    String MC_COMMANDS_TITLE_DESCRIPTION = "minecraft:commands.title.description";

    /**
     * {@literal Title command successfully executed}
     */
    String MC_COMMANDS_TITLE_SUCCESS = "minecraft:commands.title.success";

    /**
     * {@literal Controls screen titles with JSON messages.}
     */
    String MC_COMMANDS_TITLERAW_DESCRIPTION = "minecraft:commands.titleraw.description";

    /**
     * {@literal Titleraw command successfully executed}
     */
    String MC_COMMANDS_TITLERAW_SUCCESS = "minecraft:commands.titleraw.success";

    /**
     * {@literal Content Log Disabled}
     */
    String MC_COMMANDS_TOGGLECONTENTLOG_DISABLED = "minecraft:commands.togglecontentlog.disabled";

    /**
     * {@literal Content Log Enabled}
     */
    String MC_COMMANDS_TOGGLECONTENTLOG_ENABLED = "minecraft:commands.togglecontentlog.enabled";

    /**
     * {@literal Enables/Disables the content log command}
     */
    String MC_COMMANDS_TOGGLECONTENTLOG_TOGGLE = "minecraft:commands.togglecontentlog.toggle";

    /**
     * {@literal Toggles the weather.}
     */
    String MC_COMMANDS_TOGGLEDOWNFALL_DESCRIPTION = "minecraft:commands.toggledownfall.description";

    /**
     * {@literal Teleports entities (players, mobs, etc.).}
     */
    String MC_COMMANDS_TP_DESCRIPTION = "minecraft:commands.tp.description";

    /**
     * {@literal Unable to teleport %1#s to the unloaded area at %2#s}
     */
    String MC_COMMANDS_TP_FAR = "minecraft:commands.tp.far";

    /**
     * {@literal Unable to teleport because players are not in the same dimension}
     */
    String MC_COMMANDS_TP_NOTSAMEDIMENSION = "minecraft:commands.tp.notSameDimension";

    /**
     * {@literal Cannot teleport entities outside of the world}
     */
    String MC_COMMANDS_TP_OUTOFWORLD = "minecraft:commands.tp.outOfWorld";

    /**
     * {@literal You do not have permission to use this slash command.}
     */
    String MC_COMMANDS_TP_PERMISSION = "minecraft:commands.tp.permission";

    /**
     * {@literal Unable to teleport %1#s to %2#s because the area wasn't clear of blocks.}
     */
    String MC_COMMANDS_TP_SAFETELEPORTFAIL = "minecraft:commands.tp.safeTeleportFail";

    /**
     * {@literal Teleported %1#s to %2#s}
     */
    String MC_COMMANDS_TP_SUCCESS = "minecraft:commands.tp.success";

    /**
     * {@literal Teleported %1#s to %2#s, %3#s, %4#s}
     */
    String MC_COMMANDS_TP_SUCCESS_COORDINATES = "minecraft:commands.tp.success.coordinates";

    /**
     * {@literal You have been teleported to %1#s}
     */
    String MC_COMMANDS_TP_SUCCESSVICTIM = "minecraft:commands.tp.successVictim";

    /**
     * {@literal Transfers a player to another server.}
     */
    String MC_COMMANDS_TRANSFERSERVER_DESCRIPTION = "minecraft:commands.transferserver.description";

    /**
     * {@literal Invalid port (0-65535)}
     */
    String MC_COMMANDS_TRANSFERSERVER_INVALID_PORT = "minecraft:commands.transferserver.invalid.port";

    /**
     * {@literal Transferred player}
     */
    String MC_COMMANDS_TRANSFERSERVER_SUCCESSFUL = "minecraft:commands.transferserver.successful";

    /**
     * {@literal Sets a trigger to be activated.}
     */
    String MC_COMMANDS_TRIGGER_DESCRIPTION = "minecraft:commands.trigger.description";

    /**
     * {@literal Trigger %1#s is not enabled}
     */
    String MC_COMMANDS_TRIGGER_DISABLED = "minecraft:commands.trigger.disabled";

    /**
     * {@literal Invalid trigger mode %1#s}
     */
    String MC_COMMANDS_TRIGGER_INVALIDMODE = "minecraft:commands.trigger.invalidMode";

    /**
     * {@literal Invalid trigger name %1#s}
     */
    String MC_COMMANDS_TRIGGER_INVALIDOBJECTIVE = "minecraft:commands.trigger.invalidObjective";

    /**
     * {@literal Only players can use the /trigger command}
     */
    String MC_COMMANDS_TRIGGER_INVALIDPLAYER = "minecraft:commands.trigger.invalidPlayer";

    /**
     * {@literal Trigger %1#s changed with %2#s %3#s}
     */
    String MC_COMMANDS_TRIGGER_SUCCESS = "minecraft:commands.trigger.success";

    /**
     * {@literal Could not unban player %1#s}
     */
    String MC_COMMANDS_UNBAN_FAILED = "minecraft:commands.unban.failed";

    /**
     * {@literal Unbanned player %1#s}
     */
    String MC_COMMANDS_UNBAN_SUCCESS = "minecraft:commands.unban.success";

    /**
     * {@literal You have entered an invalid IP address}
     */
    String MC_COMMANDS_UNBANIP_INVALID = "minecraft:commands.unbanip.invalid";

    /**
     * {@literal Unbanned IP address %1#s}
     */
    String MC_COMMANDS_UNBANIP_SUCCESS = "minecraft:commands.unbanip.success";

    /**
     * {@literal Region to check for lighting is too big! (%1#d > %2#d)}
     */
    String MC_COMMANDS_VALIDATEGAMELIGHTING_CHECKREGIONTOOBIG = "minecraft:commands.validategamelighting.checkRegionTooBig";

    /**
     * {@literal Validate game lighting for a specified region}
     */
    String MC_COMMANDS_VALIDATEGAMELIGHTING_DESCRIPTION = "minecraft:commands.validategamelighting.description";

    /**
     * {@literal Cannot check lighting outside of the world}
     */
    String MC_COMMANDS_VALIDATEGAMELIGHTING_OUTOFWORLD = "minecraft:commands.validategamelighting.outOfWorld";

    /**
     * {@literal No volume definition matching identifier "%1#s".}
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_DEFINITIONDOESNTEXIST_FAILURE = "minecraft:commands.volumearea.add.definitionDoesntExist.failure";

    /**
     * {@literal Max number of volumes %1#d has already been reached. Cannot add more volumes.}
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_EXCEEDEDLIMIT_FAILURE = "minecraft:commands.volumearea.add.exceededLimit.failure";

    /**
     * {@literal Failed to create volume.}
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_FAILURE = "minecraft:commands.volumearea.add.failure";

    /**
     * {@literal Added volume "%1#s" from %2#d to %3#d.}
     */
    String MC_COMMANDS_VOLUMEAREA_ADD_SUCCESS = "minecraft:commands.volumearea.add.success";

    /**
     * {@literal Add, remove, or list volumes in the current dimension.}
     */
    String MC_COMMANDS_VOLUMEAREA_DESCRIPTION = "minecraft:commands.volumearea.description";

    /**
     * {@literal - %s: %s from %d %d %d to %d %d %d}
     */
    String MC_COMMANDS_VOLUMEAREA_ENTRY_WITHIDENTIFIER = "minecraft:commands.volumearea.entry.withIdentifier";

    /**
     * {@literal - %s: from %d %d %d to %d %d %d}
     */
    String MC_COMMANDS_VOLUMEAREA_ENTRY_WITHOUTIDENTIFIER = "minecraft:commands.volumearea.entry.withoutIdentifier";

    /**
     * {@literal %1#d/%2#d volumes in use.}
     */
    String MC_COMMANDS_VOLUMEAREA_INUSE = "minecraft:commands.volumearea.inUse";

    /**
     * {@literal No volumes exist in any dimension.}
     */
    String MC_COMMANDS_VOLUMEAREA_LIST_FAILURE_ALLDIMENSIONS = "minecraft:commands.volumearea.list.failure.allDimensions";

    /**
     * {@literal List of all volumes in all dimensions}
     */
    String MC_COMMANDS_VOLUMEAREA_LIST_SUCCESS_ALLDIMENSIONS = "minecraft:commands.volumearea.list.success.allDimensions";

    /**
     * {@literal List of all volumes in current dimension}
     */
    String MC_COMMANDS_VOLUMEAREA_LIST_SUCCESS_CURRENTDIMENSION = "minecraft:commands.volumearea.list.success.currentDimension";

    /**
     * {@literal No volumes exist in the current dimension.}
     */
    String MC_COMMANDS_VOLUMEAREA_NONEEXIST_CURRENTDIMENSION = "minecraft:commands.volumearea.noneExist.currentDimension";

    /**
     * {@literal No volumes named %1#s exist in the current dimension.}
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_BYNAME_FAILURE = "minecraft:commands.volumearea.remove.byName.failure";

    /**
     * {@literal No volumes containing the block position %1#d exist in the current dimension.}
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_BYPOSITION_FAILURE = "minecraft:commands.volumearea.remove.byPosition.failure";

    /**
     * {@literal Removed volume(s) in current dimension}
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_SUCCESS = "minecraft:commands.volumearea.remove.success";

    /**
     * {@literal Removed volume(s) in current dimension}
     */
    String MC_COMMANDS_VOLUMEAREA_REMOVE_ALL_SUCCESS = "minecraft:commands.volumearea.remove_all.success";

    /**
     * {@literal Changing to clear weather}
     */
    String MC_COMMANDS_WEATHER_CLEAR = "minecraft:commands.weather.clear";

    /**
     * {@literal Sets the weather.}
     */
    String MC_COMMANDS_WEATHER_DESCRIPTION = "minecraft:commands.weather.description";

    /**
     * {@literal Weather Cycle isn't enabled in this world.}
     */
    String MC_COMMANDS_WEATHER_DISABLED = "minecraft:commands.weather.disabled";

    /**
     * {@literal Weather state is: %s}
     */
    String MC_COMMANDS_WEATHER_QUERY = "minecraft:commands.weather.query";

    /**
     * {@literal clear}
     */
    String MC_COMMANDS_WEATHER_QUERY_CLEAR = "minecraft:commands.weather.query.clear";

    /**
     * {@literal rain}
     */
    String MC_COMMANDS_WEATHER_QUERY_RAIN = "minecraft:commands.weather.query.rain";

    /**
     * {@literal thunder}
     */
    String MC_COMMANDS_WEATHER_QUERY_THUNDER = "minecraft:commands.weather.query.thunder";

    /**
     * {@literal Changing to rainy weather}
     */
    String MC_COMMANDS_WEATHER_RAIN = "minecraft:commands.weather.rain";

    /**
     * {@literal Changing to rain and thunder}
     */
    String MC_COMMANDS_WEATHER_THUNDER = "minecraft:commands.weather.thunder";

    /**
     * {@literal Added %1#d to the world's age}
     */
    String MC_COMMANDS_WORLD_AGE_ADDED = "minecraft:commands.world_age.added";

    /**
     * {@literal Changes or queries the world's age (time since creation).}
     */
    String MC_COMMANDS_WORLD_AGE_DESCRIPTION = "minecraft:commands.world_age.description";

    /**
     * {@literal World age is %d}
     */
    String MC_COMMANDS_WORLD_AGE_QUERY = "minecraft:commands.world_age.query";

    /**
     * {@literal Set the world's age to %1#d}
     */
    String MC_COMMANDS_WORLD_AGE_SET = "minecraft:commands.world_age.set";

    /**
     * {@literal Set world border center to %1#s,%2#s}
     */
    String MC_COMMANDS_WORLDBORDER_CENTER_SUCCESS = "minecraft:commands.worldborder.center.success";

    /**
     * {@literal Set world border damage amount to %1#s per block (from %2#s per block)}
     */
    String MC_COMMANDS_WORLDBORDER_DAMAGE_AMOUNT_SUCCESS = "minecraft:commands.worldborder.damage.amount.success";

    /**
     * {@literal Set world border damage buffer to %1#s blocks (from %2#s blocks)}
     */
    String MC_COMMANDS_WORLDBORDER_DAMAGE_BUFFER_SUCCESS = "minecraft:commands.worldborder.damage.buffer.success";

    /**
     * {@literal World border is currently %1#s blocks wide}
     */
    String MC_COMMANDS_WORLDBORDER_GET_SUCCESS = "minecraft:commands.worldborder.get.success";

    /**
     * {@literal Set world border to %1#s blocks wide (from %2#s blocks)}
     */
    String MC_COMMANDS_WORLDBORDER_SET_SUCCESS = "minecraft:commands.worldborder.set.success";

    /**
     * {@literal Growing world border to %1#s blocks wide (up from %2#s blocks) over %3#s seconds}
     */
    String MC_COMMANDS_WORLDBORDER_SETSLOWLY_GROW_SUCCESS = "minecraft:commands.worldborder.setSlowly.grow.success";

    /**
     * {@literal Shrinking world border to %1#s blocks wide (down from %2#s blocks) over %3#s seconds}
     */
    String MC_COMMANDS_WORLDBORDER_SETSLOWLY_SHRINK_SUCCESS = "minecraft:commands.worldborder.setSlowly.shrink.success";

    /**
     * {@literal Set world border warning to %1#s blocks away (from %2#s blocks)}
     */
    String MC_COMMANDS_WORLDBORDER_WARNING_DISTANCE_SUCCESS = "minecraft:commands.worldborder.warning.distance.success";

    /**
     * {@literal Set world border warning to %1#s seconds away (from %2#s seconds)}
     */
    String MC_COMMANDS_WORLDBORDER_WARNING_TIME_SUCCESS = "minecraft:commands.worldborder.warning.time.success";

    /**
     * {@literal Toggle World Builder status of caller.}
     */
    String MC_COMMANDS_WORLDBUILDER_DESCRIPTION = "minecraft:commands.worldbuilder.description";

    /**
     * {@literal World Builder status updated to %1#s}
     */
    String MC_COMMANDS_WORLDBUILDER_SUCCESS = "minecraft:commands.worldbuilder.success";

    /**
     * {@literal Attempts to connect to the websocket server on the provided URL.}
     */
    String MC_COMMANDS_WSSERVER_DESCRIPTION = "minecraft:commands.wsserver.description";

    /**
     * {@literal The provided server URL is invalid}
     */
    String MC_COMMANDS_WSSERVER_INVALID_URL = "minecraft:commands.wsserver.invalid.url";

    /**
     * {@literal Another connection request is currently running}
     */
    String MC_COMMANDS_WSSERVER_REQUEST_EXISTING = "minecraft:commands.wsserver.request.existing";

    /**
     * {@literal Could not connect to server: %1#s}
     */
    String MC_COMMANDS_WSSERVER_REQUEST_FAILED = "minecraft:commands.wsserver.request.failed";

    /**
     * {@literal Websocket server request rejected, go to Settings to enable.}
     */
    String MC_COMMANDS_WSSERVER_REQUEST_FAILED_DISABLED = "minecraft:commands.wsserver.request.failed.disabled";

    /**
     * {@literal Connection established to server: %1#s}
     */
    String MC_COMMANDS_WSSERVER_SUCCESS = "minecraft:commands.wsserver.success";

    /**
     * {@literal Adds or removes player experience.}
     */
    String MC_COMMANDS_XP_DESCRIPTION = "minecraft:commands.xp.description";

    /**
     * {@literal Cannot give player negative experience points}
     */
    String MC_COMMANDS_XP_FAILURE_WIDTHDRAWXP = "minecraft:commands.xp.failure.widthdrawXp";

    /**
     * {@literal Gave %1#d experience to %2#s}
     */
    String MC_COMMANDS_XP_SUCCESS = "minecraft:commands.xp.success";

    /**
     * {@literal Gave %1#d levels to %2#s}
     */
    String MC_COMMANDS_XP_SUCCESS_LEVELS = "minecraft:commands.xp.success.levels";

    /**
     * {@literal Taken %1#d levels from %2#s}
     */
    String MC_COMMANDS_XP_SUCCESS_NEGATIVE_LEVELS = "minecraft:commands.xp.success.negative.levels";

    /**
     * {@literal You've been playing for greater than 24 hours}
     */
    String MC_COMPLIANCE_PLAYTIME_GREATERTHAN24HOURS = "minecraft:compliance.playtime.greaterThan24Hours";

    /**
     * {@literal Excessive gaming may interfere with normal daily life}
     */
    String MC_COMPLIANCE_PLAYTIME_MESSAGE = "minecraft:compliance.playtime.message";

    /**
     * {@literal You've been playing for %d hours}
     */
    String MC_COMPLIANCE_PLAYTIME_MULTIPLEHOURS = "minecraft:compliance.playtime.multipleHours";

    /**
     * {@literal You've been playing for 1 hour}
     */
    String MC_COMPLIANCE_PLAYTIME_ONEHOUR = "minecraft:compliance.playtime.oneHour";

    /**
     * {@literal Logging in...}
     */
    String MC_CONNECT_AUTHORIZING = "minecraft:connect.authorizing";

    /**
     * {@literal Connecting to the server...}
     */
    String MC_CONNECT_CONNECTING = "minecraft:connect.connecting";

    /**
     * {@literal Failed to connect to the server}
     */
    String MC_CONNECT_FAILED = "minecraft:connect.failed";

    /**
     * {@literal Please press the A button on your controller to continue}
     */
    String MC_CONNECT_GAMEPAD_PRESSBUTTONTOCONTINUE = "minecraft:connect_gamepad.pressButtonToContinue";

    /**
     * {@literal Game controller required}
     */
    String MC_CONNECT_GAMEPAD_WARNING_CONTROLLERREQUIRED = "minecraft:connect_gamepad.warning.controllerRequired";

    /**
     * {@literal Barrel}
     */
    String MC_CONTAINER_BARREL = "minecraft:container.barrel";

    /**
     * {@literal Beacon}
     */
    String MC_CONTAINER_BEACON = "minecraft:container.beacon";

    /**
     * {@literal Brewing Stand}
     */
    String MC_CONTAINER_BREWING = "minecraft:container.brewing";

    /**
     * {@literal Chest}
     */
    String MC_CONTAINER_CHEST = "minecraft:container.chest";

    /**
     * {@literal Copper Chest}
     */
    String MC_CONTAINER_CHESTCOPPER = "minecraft:container.chestCopper";

    /**
     * {@literal Large Chest}
     */
    String MC_CONTAINER_CHESTDOUBLE = "minecraft:container.chestDouble";

    /**
     * {@literal Large Copper Chest}
     */
    String MC_CONTAINER_CHESTDOUBLECOPPER = "minecraft:container.chestDoubleCopper";

    /**
     * {@literal Crafter}
     */
    String MC_CONTAINER_CRAFTER = "minecraft:container.crafter";

    /**
     * {@literal Crafting}
     */
    String MC_CONTAINER_CRAFTING = "minecraft:container.crafting";

    /**
     * {@literal Item Selection}
     */
    String MC_CONTAINER_CREATIVE = "minecraft:container.creative";

    /**
     * {@literal Dispenser}
     */
    String MC_CONTAINER_DISPENSER = "minecraft:container.dispenser";

    /**
     * {@literal Dropper}
     */
    String MC_CONTAINER_DROPPER = "minecraft:container.dropper";

    /**
     * {@literal Enchant}
     */
    String MC_CONTAINER_ENCHANT = "minecraft:container.enchant";

    /**
     * {@literal %s . . . ?}
     */
    String MC_CONTAINER_ENCHANT_CLUE = "minecraft:container.enchant.clue";

    /**
     * {@literal %d Lapis Lazuli}
     */
    String MC_CONTAINER_ENCHANT_LAPIS_MANY = "minecraft:container.enchant.lapis.many";

    /**
     * {@literal 1 Lapis Lazuli}
     */
    String MC_CONTAINER_ENCHANT_LAPIS_ONE = "minecraft:container.enchant.lapis.one";

    /**
     * {@literal %d Enchantment Levels}
     */
    String MC_CONTAINER_ENCHANT_LEVEL_MANY = "minecraft:container.enchant.level.many";

    /**
     * {@literal 1 Enchantment Level}
     */
    String MC_CONTAINER_ENCHANT_LEVEL_ONE = "minecraft:container.enchant.level.one";

    /**
     * {@literal Level Requirement: %d}
     */
    String MC_CONTAINER_ENCHANT_LEVELREQUIREMENT = "minecraft:container.enchant.levelrequirement";

    /**
     * {@literal Ender Chest}
     */
    String MC_CONTAINER_ENDERCHEST = "minecraft:container.enderchest";

    /**
     * {@literal Furnace}
     */
    String MC_CONTAINER_FURNACE = "minecraft:container.furnace";

    /**
     * {@literal Item Hopper}
     */
    String MC_CONTAINER_HOPPER = "minecraft:container.hopper";

    /**
     * {@literal Inventory}
     */
    String MC_CONTAINER_INVENTORY = "minecraft:container.inventory";

    /**
     * {@literal %s is locked!}
     */
    String MC_CONTAINER_ISLOCKED = "minecraft:container.isLocked";

    /**
     * {@literal Loom}
     */
    String MC_CONTAINER_LOOM = "minecraft:container.loom";

    /**
     * {@literal Minecart}
     */
    String MC_CONTAINER_MINECART = "minecraft:container.minecart";

    /**
     * {@literal Repair & Name}
     */
    String MC_CONTAINER_REPAIR = "minecraft:container.repair";

    /**
     * {@literal Enchantment Cost: %1#d}
     */
    String MC_CONTAINER_REPAIR_COST = "minecraft:container.repair.cost";

    /**
     * {@literal Too Expensive!}
     */
    String MC_CONTAINER_REPAIR_EXPENSIVE = "minecraft:container.repair.expensive";

    /**
     * {@literal Repair & Disenchant}
     */
    String MC_CONTAINER_REPAIRANDDISENCHANT = "minecraft:container.repairAndDisenchant";

    /**
     * {@literal Shulker Box}
     */
    String MC_CONTAINER_SHULKERBOX = "minecraft:container.shulkerbox";

    /**
     * {@literal and %d more...}
     */
    String MC_CONTAINER_SHULKERBOXCONTAINS = "minecraft:container.shulkerboxContains";

    /**
     * {@literal Smithing Table}
     */
    String MC_CONTAINER_SMITHING_TABLE = "minecraft:container.smithing_table";

    /**
     * {@literal Item can't be upgraded this way}
     */
    String MC_CONTAINER_SMITHING_TABLE_ARROW_TOOLTIP_CANNOT_CRAFT = "minecraft:container.smithing_table.arrow_tooltip_cannot_craft";

    /**
     * {@literal Add a piece of armor}
     */
    String MC_CONTAINER_SMITHING_TABLE_INPUT_SLOT_TOOLTIP_ARMOR_TRIM = "minecraft:container.smithing_table.input_slot_tooltip_armor_trim";

    /**
     * {@literal Add Diamond armor, weapon, or tool}
     */
    String MC_CONTAINER_SMITHING_TABLE_INPUT_SLOT_TOOLTIP_NETHERITE = "minecraft:container.smithing_table.input_slot_tooltip_netherite";

    /**
     * {@literal Add ingot or crystal}
     */
    String MC_CONTAINER_SMITHING_TABLE_MATERIAL_SLOT_TOOLTIP_ARMOR_TRIM = "minecraft:container.smithing_table.material_slot_tooltip_armor_trim";

    /**
     * {@literal Add Netherite Ingot}
     */
    String MC_CONTAINER_SMITHING_TABLE_MATERIAL_SLOT_TOOLTIP_NETHERITE = "minecraft:container.smithing_table.material_slot_tooltip_netherite";

    /**
     * {@literal Add Smithing Template}
     */
    String MC_CONTAINER_SMITHING_TABLE_TEMPLATE_SLOT_TOOLTIP = "minecraft:container.smithing_table.template_slot_tooltip";

    /**
     * {@literal Upgrade Gear}
     */
    String MC_CONTAINER_SMITHING_TABLE_UPGRADE = "minecraft:container.smithing_table.upgrade";

    /**
     * {@literal Smithing Table}
     */
    String MC_CONTAINER_SMITHING_TABLE_BLOCK = "minecraft:container.smithing_table_block";

    /**
     * {@literal Stonecutter}
     */
    String MC_CONTAINER_STONECUTTER = "minecraft:container.stonecutter";

    /**
     * {@literal Stonecutter}
     */
    String MC_CONTAINER_STONECUTTER_BLOCK = "minecraft:container.stonecutter_block";

    /**
     * {@literal Failed to import '%s'}
     */
    String MC_CONTENT_IMPORT_FAILED = "minecraft:content.import.failed";

    /**
     * {@literal Unsupported file format}
     */
    String MC_CONTENT_IMPORT_FAILED_INCOMPATIBLE = "minecraft:content.import.failed.incompatible";

    /**
     * {@literal See output log for more details}
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE = "minecraft:content.import.failed.subtitle";

    /**
     * {@literal Duplicate pack detected}
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE_DUPLICATE = "minecraft:content.import.failed.subtitle_duplicate";

    /**
     * {@literal Not a valid zip archive}
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE_MALFORMED_ZIP = "minecraft:content.import.failed.subtitle_malformed_zip";

    /**
     * {@literal Content in this world is not supported by Minecraft Education.}
     */
    String MC_CONTENT_IMPORT_FAILED_SUBTITLE_PREMIUMCONTENT = "minecraft:content.import.failed.subtitle_premiumcontent";

    /**
     * {@literal Content Not Supported}
     */
    String MC_CONTENT_IMPORT_FAILED_TITLE_PREMIUMCONTENT = "minecraft:content.import.failed.title_premiumcontent";

    /**
     * {@literal imported}
     */
    String MC_CONTENT_IMPORT_IMPORTEDWORLDPREFIX = "minecraft:content.import.importedWorldPrefix";

    /**
     * {@literal Import started...}
     */
    String MC_CONTENT_IMPORT_STARTED = "minecraft:content.import.started";

    /**
     * {@literal Successfully imported '%s'}
     */
    String MC_CONTENT_IMPORT_SUCCEEDED = "minecraft:content.import.succeeded";

    /**
     * {@literal Successfully imported '%s' with warnings}
     */
    String MC_CONTENT_IMPORT_SUCCEEDED_WITH_WARNINGS = "minecraft:content.import.succeeded_with_warnings";

    /**
     * {@literal Click here for more info}
     */
    String MC_CONTENT_IMPORT_SUCCEEDED_WITH_WARNINGS_SUBTITLE = "minecraft:content.import.succeeded_with_warnings.subtitle";

    /**
     * {@literal Failed to load content for pack %s  }
     */
    String MC_CONTENT_LOAD_FAILED = "minecraft:content.load.failed";

    /**
     * {@literal Check your pack for errors and try importing again }
     */
    String MC_CONTENT_LOAD_FAILED_SUBTITLE = "minecraft:content.load.failed.subtitle";

    /**
     * {@literal Delete Old Logs}
     */
    String MC_CONTENTLOG_CLEAR_FILES = "minecraft:contentlog.clear_files";

    /**
     * {@literal Select "Delete Now" to delete all previous log files.}
     */
    String MC_CONTENTLOG_DELETE_BODY1 = "minecraft:contentlog.delete.body1";

    /**
     * {@literal Note: The log file for your currently active session will remain.}
     */
    String MC_CONTENTLOG_DELETE_BODY2 = "minecraft:contentlog.delete.body2";

    /**
     * {@literal Cancel}
     */
    String MC_CONTENTLOG_DELETE_CANCEL = "minecraft:contentlog.delete.cancel";

    /**
     * {@literal Delete Now}
     */
    String MC_CONTENTLOG_DELETE_DELETE = "minecraft:contentlog.delete.delete";

    /**
     * {@literal Deleting Content Logs}
     */
    String MC_CONTENTLOG_DELETE_PROGRESS = "minecraft:contentlog.delete.progress";

    /**
     * {@literal Delete Old Log Files?}
     */
    String MC_CONTENTLOG_DELETE_TITLE = "minecraft:contentlog.delete.title";

    /**
     * {@literal Clear}
     */
    String MC_CONTENTLOG_HISTORY_CLEAR = "minecraft:contentlog.history.clear";

    /**
     * {@literal Copy to Clipboard}
     */
    String MC_CONTENTLOG_HISTORY_COPYTOCLIPBOARD = "minecraft:contentlog.history.copyToClipboard";

    /**
     * {@literal Content Log History}
     */
    String MC_CONTENTLOG_HISTORY_TITLE = "minecraft:contentlog.history.title";

    /**
     * {@literal Content Log Location:}
     */
    String MC_CONTENTLOG_LOGLOCATIONSECTIONNAME = "minecraft:contentlog.logLocationSectionName";

    /**
     * {@literal Content Log Settings}
     */
    String MC_CONTENTLOG_SETTINGSSECTIONNAME = "minecraft:contentlog.settingsSectionName";

    /**
     * {@literal Reset}
     */
    String MC_CONTROLS_RESET = "minecraft:controls.reset";

    /**
     * {@literal Reset Keys}
     */
    String MC_CONTROLS_RESETALL = "minecraft:controls.resetAll";

    /**
     * {@literal Controls}
     */
    String MC_CONTROLS_TITLE = "minecraft:controls.title";

    /**
     * {@literal Copied facing position to clipboard: "%s"}
     */
    String MC_COPYCOORDINATES_COPY_FACING_MESSAGE = "minecraft:copyCoordinates.copy_facing_message";

    /**
     * {@literal Copied current position to clipboard: "%s"}
     */
    String MC_COPYCOORDINATES_COPY_POSITION_MESSAGE = "minecraft:copyCoordinates.copy_position_message";

    /**
     * {@literal No valid output for that item}
     */
    String MC_CRAFTING_BADCOMBINATION = "minecraft:crafting.badCombination";

    /**
     * {@literal You don't have all ingredients}
     */
    String MC_CRAFTING_CANNOTCREATE = "minecraft:crafting.cannotCreate";

    /**
     * {@literal Your level is too low}
     */
    String MC_CRAFTING_INSUFFICIENTLEVEL = "minecraft:crafting.insufficientLevel";

    /**
     * {@literal You need to collect blocks to craft!}
     */
    String MC_CRAFTING_NORECIPESINVENTORY = "minecraft:crafting.noRecipesInventory";

    /**
     * {@literal You need stone materials to craft!}
     */
    String MC_CRAFTING_NORECIPESSTONECUTTER = "minecraft:crafting.noRecipesStonecutter";

    /**
     * {@literal You need stone materials to craft!}
     */
    String MC_CRAFTING_NORECIPESSTONECUTTER_BLOCK = "minecraft:crafting.noRecipesStonecutter_block";

    /**
     * {@literal You need to collect blocks to craft!}
     */
    String MC_CRAFTING_NORECIPESWORKBENCH = "minecraft:crafting.noRecipesWorkbench";

    /**
     * {@literal Armor}
     */
    String MC_CRAFTINGSCREEN_TAB_ARMOR = "minecraft:craftingScreen.tab.armor";

    /**
     * {@literal Construction}
     */
    String MC_CRAFTINGSCREEN_TAB_CONSTRUCTION = "minecraft:craftingScreen.tab.construction";

    /**
     * {@literal Equipment}
     */
    String MC_CRAFTINGSCREEN_TAB_EQUIPMENT = "minecraft:craftingScreen.tab.equipment";

    /**
     * {@literal Items}
     */
    String MC_CRAFTINGSCREEN_TAB_ITEMS = "minecraft:craftingScreen.tab.items";

    /**
     * {@literal Nature}
     */
    String MC_CRAFTINGSCREEN_TAB_NATURE = "minecraft:craftingScreen.tab.nature";

    /**
     * {@literal All}
     */
    String MC_CRAFTINGSCREEN_TAB_SEARCH = "minecraft:craftingScreen.tab.search";

    /**
     * {@literal Craftable}
     */
    String MC_CRAFTINGSCREEN_TAB_SEARCH_FILTER = "minecraft:craftingScreen.tab.search.filter";

    /**
     * {@literal Inventory}
     */
    String MC_CRAFTINGSCREEN_TAB_SURVIVAL = "minecraft:craftingScreen.tab.survival";

    /**
     * {@literal Imported Templates}
     */
    String MC_CUSTOMTEMPLATESSCREEN_HEADER = "minecraft:customTemplatesScreen.header";

    /**
     * {@literal %s day}
     */
    String MC_DATE_DAYSINGULAR = "minecraft:date.daySingular";

    /**
     * {@literal %sd}
     */
    String MC_DATE_DAYSABBREVIATED1CHAR = "minecraft:date.daysAbbreviated1Char";

    /**
     * {@literal %sd}
     */
    String MC_DATE_DAYSABBREVIATED3CHAR = "minecraft:date.daysAbbreviated3Char";

    /**
     * {@literal %s days}
     */
    String MC_DATE_DAYSPLURAL = "minecraft:date.daysPlural";

    /**
     * {@literal %s %d %d}
     */
    String MC_DATE_FORMATTED = "minecraft:date.formatted";

    /**
     * {@literal %s at %s}
     */
    String MC_DATE_FORMATTEDSPECIFIC = "minecraft:date.formattedSpecific";

    /**
     * {@literal Today at %s}
     */
    String MC_DATE_FORMATTEDSPECIFIC_TODAY = "minecraft:date.formattedSpecific.today";

    /**
     * {@literal Yesterday at %s}
     */
    String MC_DATE_FORMATTEDSPECIFIC_YESTERDAY = "minecraft:date.formattedSpecific.yesterday";

    /**
     * {@literal %1#s:%2#sam}
     */
    String MC_DATE_FORMATTEDTIMEAM = "minecraft:date.formattedTimeAM";

    /**
     * {@literal %1#s:%2#spm}
     */
    String MC_DATE_FORMATTEDTIMEPM = "minecraft:date.formattedTimePm";

    /**
     * {@literal %d %s %d}
     */
    String MC_DATE_FORMATTED_ALT = "minecraft:date.formatted_alt";

    /**
     * {@literal April}
     */
    String MC_DATE_FULL_APRIL = "minecraft:date.full_april";

    /**
     * {@literal August}
     */
    String MC_DATE_FULL_AUGUST = "minecraft:date.full_august";

    /**
     * {@literal December}
     */
    String MC_DATE_FULL_DECEMBER = "minecraft:date.full_december";

    /**
     * {@literal February}
     */
    String MC_DATE_FULL_FEBRUARY = "minecraft:date.full_february";

    /**
     * {@literal January}
     */
    String MC_DATE_FULL_JANUARY = "minecraft:date.full_january";

    /**
     * {@literal July}
     */
    String MC_DATE_FULL_JULY = "minecraft:date.full_july";

    /**
     * {@literal June}
     */
    String MC_DATE_FULL_JUNE = "minecraft:date.full_june";

    /**
     * {@literal March}
     */
    String MC_DATE_FULL_MARCH = "minecraft:date.full_march";

    /**
     * {@literal May}
     */
    String MC_DATE_FULL_MAY = "minecraft:date.full_may";

    /**
     * {@literal November}
     */
    String MC_DATE_FULL_NOVEMBER = "minecraft:date.full_november";

    /**
     * {@literal October}
     */
    String MC_DATE_FULL_OCTOBER = "minecraft:date.full_october";

    /**
     * {@literal September}
     */
    String MC_DATE_FULL_SEPTEMBER = "minecraft:date.full_september";

    /**
     * {@literal %s hour}
     */
    String MC_DATE_HOURSINGULAR = "minecraft:date.hourSingular";

    /**
     * {@literal %sh}
     */
    String MC_DATE_HOURSABBREVIATED1CHAR = "minecraft:date.hoursAbbreviated1Char";

    /**
     * {@literal %shr}
     */
    String MC_DATE_HOURSABBREVIATED3CHAR = "minecraft:date.hoursAbbreviated3Char";

    /**
     * {@literal %s hours}
     */
    String MC_DATE_HOURSPLURAL = "minecraft:date.hoursPlural";

    /**
     * {@literal %s minute}
     */
    String MC_DATE_MINUTESINGULAR = "minecraft:date.minuteSingular";

    /**
     * {@literal %sm}
     */
    String MC_DATE_MINUTESABBREVIATED1CHAR = "minecraft:date.minutesAbbreviated1Char";

    /**
     * {@literal %smin}
     */
    String MC_DATE_MINUTESABBREVIATED3CHAR = "minecraft:date.minutesAbbreviated3Char";

    /**
     * {@literal %s minutes}
     */
    String MC_DATE_MINUTESPLURAL = "minecraft:date.minutesPlural";

    /**
     * {@literal %s second}
     */
    String MC_DATE_SECONDSINGULAR = "minecraft:date.secondSingular";

    /**
     * {@literal %ss}
     */
    String MC_DATE_SECONDSABBREVIATED1CHAR = "minecraft:date.secondsAbbreviated1Char";

    /**
     * {@literal %ssec}
     */
    String MC_DATE_SECONDSABBREVIATED3CHAR = "minecraft:date.secondsAbbreviated3Char";

    /**
     * {@literal %s seconds}
     */
    String MC_DATE_SECONDSPLURAL = "minecraft:date.secondsPlural";

    /**
     * {@literal Apr}
     */
    String MC_DATE_SHORT_APRIL = "minecraft:date.short_april";

    /**
     * {@literal Aug}
     */
    String MC_DATE_SHORT_AUGUST = "minecraft:date.short_august";

    /**
     * {@literal Dec}
     */
    String MC_DATE_SHORT_DECEMBER = "minecraft:date.short_december";

    /**
     * {@literal Feb}
     */
    String MC_DATE_SHORT_FEBRUARY = "minecraft:date.short_february";

    /**
     * {@literal Jan}
     */
    String MC_DATE_SHORT_JANUARY = "minecraft:date.short_january";

    /**
     * {@literal Jul}
     */
    String MC_DATE_SHORT_JULY = "minecraft:date.short_july";

    /**
     * {@literal Jun}
     */
    String MC_DATE_SHORT_JUNE = "minecraft:date.short_june";

    /**
     * {@literal Mar}
     */
    String MC_DATE_SHORT_MARCH = "minecraft:date.short_march";

    /**
     * {@literal May}
     */
    String MC_DATE_SHORT_MAY = "minecraft:date.short_may";

    /**
     * {@literal Nov}
     */
    String MC_DATE_SHORT_NOVEMBER = "minecraft:date.short_november";

    /**
     * {@literal Oct}
     */
    String MC_DATE_SHORT_OCTOBER = "minecraft:date.short_october";

    /**
     * {@literal Sep}
     */
    String MC_DATE_SHORT_SEPTEMBER = "minecraft:date.short_september";

    /**
     * {@literal %s left}
     */
    String MC_DATE_TIMELEFT = "minecraft:date.timeLeft";

    /**
     * {@literal UTC}
     */
    String MC_DATE_TIME_ZONE_UTC = "minecraft:date.time_zone_utc";

    /**
     * {@literal %1#s was squashed by a falling anvil}
     */
    String MC_DEATH_ATTACK_ANVIL = "minecraft:death.attack.anvil";

    /**
     * {@literal %1#s was shot by %2#s}
     */
    String MC_DEATH_ATTACK_ARROW = "minecraft:death.attack.arrow";

    /**
     * {@literal %1#s was shot by %2#s using %3#s}
     */
    String MC_DEATH_ATTACK_ARROW_ITEM = "minecraft:death.attack.arrow.item";

    /**
     * {@literal %1#s was sniped by %2#s}
     */
    String MC_DEATH_ATTACK_BULLET = "minecraft:death.attack.bullet";

    /**
     * {@literal %1#s was pricked to death}
     */
    String MC_DEATH_ATTACK_CACTUS = "minecraft:death.attack.cactus";

    /**
     * {@literal %1#s walked into a cactus whilst trying to escape %2#s}
     */
    String MC_DEATH_ATTACK_CACTUS_PLAYER = "minecraft:death.attack.cactus.player";

    /**
     * {@literal %1#s died from dehydration}
     */
    String MC_DEATH_ATTACK_DEHYDRATION = "minecraft:death.attack.dehydration";

    /**
     * {@literal %1#s drowned}
     */
    String MC_DEATH_ATTACK_DROWN = "minecraft:death.attack.drown";

    /**
     * {@literal %1#s drowned whilst trying to escape %2#s}
     */
    String MC_DEATH_ATTACK_DROWN_PLAYER = "minecraft:death.attack.drown.player";

    /**
     * {@literal %1#s blew up}
     */
    String MC_DEATH_ATTACK_EXPLOSION = "minecraft:death.attack.explosion";

    /**
     * {@literal %1#s was killed by [Intentional Game Design]}
     */
    String MC_DEATH_ATTACK_EXPLOSION_BY_BED = "minecraft:death.attack.explosion.by.bed";

    /**
     * {@literal %1#s was blown up by %2#s}
     */
    String MC_DEATH_ATTACK_EXPLOSION_PLAYER = "minecraft:death.attack.explosion.player";

    /**
     * {@literal %1#s hit the ground too hard}
     */
    String MC_DEATH_ATTACK_FALL = "minecraft:death.attack.fall";

    /**
     * {@literal %1#s was squashed by a falling block}
     */
    String MC_DEATH_ATTACK_FALLINGBLOCK = "minecraft:death.attack.fallingBlock";

    /**
     * {@literal %1#s was fireballed by %2#s}
     */
    String MC_DEATH_ATTACK_FIREBALL = "minecraft:death.attack.fireball";

    /**
     * {@literal %1#s was fireballed by %2#s using %3#s}
     */
    String MC_DEATH_ATTACK_FIREBALL_ITEM = "minecraft:death.attack.fireball.item";

    /**
     * {@literal %1#s went off with a bang}
     */
    String MC_DEATH_ATTACK_FIREWORKS = "minecraft:death.attack.fireworks";

    /**
     * {@literal %1#s experienced kinetic energy}
     */
    String MC_DEATH_ATTACK_FLYINTOWALL = "minecraft:death.attack.flyIntoWall";

    /**
     * {@literal %1#s froze to death}
     */
    String MC_DEATH_ATTACK_FREEZE = "minecraft:death.attack.freeze";

    /**
     * {@literal %1#s died}
     */
    String MC_DEATH_ATTACK_GENERIC = "minecraft:death.attack.generic";

    /**
     * {@literal %1#s went up in flames}
     */
    String MC_DEATH_ATTACK_INFIRE = "minecraft:death.attack.inFire";

    /**
     * {@literal %1#s walked into fire whilst fighting %2#s}
     */
    String MC_DEATH_ATTACK_INFIRE_PLAYER = "minecraft:death.attack.inFire.player";

    /**
     * {@literal %1#s suffocated in a wall}
     */
    String MC_DEATH_ATTACK_INWALL = "minecraft:death.attack.inWall";

    /**
     * {@literal %1#s was killed by %2#s using magic}
     */
    String MC_DEATH_ATTACK_INDIRECTMAGIC = "minecraft:death.attack.indirectMagic";

    /**
     * {@literal %1#s was killed by %2#s using %3#s}
     */
    String MC_DEATH_ATTACK_INDIRECTMAGIC_ITEM = "minecraft:death.attack.indirectMagic.item";

    /**
     * {@literal %1#s tried to swim in lava}
     */
    String MC_DEATH_ATTACK_LAVA = "minecraft:death.attack.lava";

    /**
     * {@literal %1#s tried to swim in lava to escape %2#s}
     */
    String MC_DEATH_ATTACK_LAVA_PLAYER = "minecraft:death.attack.lava.player";

    /**
     * {@literal %1#s was struck by lightning}
     */
    String MC_DEATH_ATTACK_LIGHTNINGBOLT = "minecraft:death.attack.lightningBolt";

    /**
     * {@literal %1#s was smashed by %2#s}
     */
    String MC_DEATH_ATTACK_MACESMASH_PLAYER = "minecraft:death.attack.maceSmash.player";

    /**
     * {@literal %1#s was smashed by %2#s with %3#s}
     */
    String MC_DEATH_ATTACK_MACESMASH_PLAYER_ITEM = "minecraft:death.attack.maceSmash.player.item";

    /**
     * {@literal %1#s was killed by magic}
     */
    String MC_DEATH_ATTACK_MAGIC = "minecraft:death.attack.magic";

    /**
     * {@literal %1#s discovered the floor was lava}
     */
    String MC_DEATH_ATTACK_MAGMA = "minecraft:death.attack.magma";

    /**
     * {@literal %1#s walked on danger zone due to %2#s}
     */
    String MC_DEATH_ATTACK_MAGMA_PLAYER = "minecraft:death.attack.magma.player";

    /**
     * {@literal %1#s was slain by %2#s}
     */
    String MC_DEATH_ATTACK_MOB = "minecraft:death.attack.mob";

    /**
     * {@literal %1#s was slain by %2#s using %3#s}
     */
    String MC_DEATH_ATTACK_MOB_ITEM = "minecraft:death.attack.mob.item";

    /**
     * {@literal %1#s burned to death}
     */
    String MC_DEATH_ATTACK_ONFIRE = "minecraft:death.attack.onFire";

    /**
     * {@literal %1#s was burnt to a crisp whilst fighting %2#s}
     */
    String MC_DEATH_ATTACK_ONFIRE_PLAYER = "minecraft:death.attack.onFire.player";

    /**
     * {@literal %1#s fell out of the world}
     */
    String MC_DEATH_ATTACK_OUTOFWORLD = "minecraft:death.attack.outOfWorld";

    /**
     * {@literal %1#s was slain by %2#s}
     */
    String MC_DEATH_ATTACK_PLAYER = "minecraft:death.attack.player";

    /**
     * {@literal %1#s was slain by %2#s using %3#s}
     */
    String MC_DEATH_ATTACK_PLAYER_ITEM = "minecraft:death.attack.player.item";

    /**
     * {@literal %1#s was obliterated by a sonically-charged shriek}
     */
    String MC_DEATH_ATTACK_SONICBOOM = "minecraft:death.attack.sonicBoom";

    /**
     * {@literal %1#s was obliterated by a sonically-charged shriek whilst trying to escape %2#s}
     */
    String MC_DEATH_ATTACK_SONICBOOM_PLAYER = "minecraft:death.attack.sonicBoom.player";

    /**
     * {@literal %1#s was spitballed by %2#s}
     */
    String MC_DEATH_ATTACK_SPIT = "minecraft:death.attack.spit";

    /**
     * {@literal %1#s was skewered by a falling stalactite}
     */
    String MC_DEATH_ATTACK_STALACTITE = "minecraft:death.attack.stalactite";

    /**
     * {@literal %1#s was impaled on a stalagmite}
     */
    String MC_DEATH_ATTACK_STALAGMITE = "minecraft:death.attack.stalagmite";

    /**
     * {@literal %1#s starved to death}
     */
    String MC_DEATH_ATTACK_STARVE = "minecraft:death.attack.starve";

    /**
     * {@literal %1#s was poked to death by a sweet berry bush}
     */
    String MC_DEATH_ATTACK_SWEETBERRY = "minecraft:death.attack.sweetBerry";

    /**
     * {@literal %1#s was killed trying to hurt %2#s}
     */
    String MC_DEATH_ATTACK_THORNS = "minecraft:death.attack.thorns";

    /**
     * {@literal %1#s was pummeled by %2#s}
     */
    String MC_DEATH_ATTACK_THROWN = "minecraft:death.attack.thrown";

    /**
     * {@literal %1#s was pummeled by %2#s using %3#s}
     */
    String MC_DEATH_ATTACK_THROWN_ITEM = "minecraft:death.attack.thrown.item";

    /**
     * {@literal %1#s was impaled to death by %2#s}
     */
    String MC_DEATH_ATTACK_TRIDENT = "minecraft:death.attack.trident";

    /**
     * {@literal %1#s withered away}
     */
    String MC_DEATH_ATTACK_WITHER = "minecraft:death.attack.wither";

    /**
     * {@literal %1#s fell from a high place}
     */
    String MC_DEATH_FELL_ACCIDENT_GENERIC = "minecraft:death.fell.accident.generic";

    /**
     * {@literal %1#s fell off a ladder}
     */
    String MC_DEATH_FELL_ACCIDENT_LADDER = "minecraft:death.fell.accident.ladder";

    /**
     * {@literal %1#s fell off some vines}
     */
    String MC_DEATH_FELL_ACCIDENT_VINES = "minecraft:death.fell.accident.vines";

    /**
     * {@literal %1#s fell out of the water}
     */
    String MC_DEATH_FELL_ACCIDENT_WATER = "minecraft:death.fell.accident.water";

    /**
     * {@literal %1#s was doomed to fall by %2#s}
     */
    String MC_DEATH_FELL_ASSIST = "minecraft:death.fell.assist";

    /**
     * {@literal %1#s was doomed to fall by %2#s using %3#s}
     */
    String MC_DEATH_FELL_ASSIST_ITEM = "minecraft:death.fell.assist.item";

    /**
     * {@literal %1#s fell too far and was finished by %2#s}
     */
    String MC_DEATH_FELL_FINISH = "minecraft:death.fell.finish";

    /**
     * {@literal %1#s fell too far and was finished by %2#s using %3#s}
     */
    String MC_DEATH_FELL_FINISH_ITEM = "minecraft:death.fell.finish.item";

    /**
     * {@literal %1#s was doomed to fall}
     */
    String MC_DEATH_FELL_KILLER = "minecraft:death.fell.killer";

    /**
     * {@literal Delete world}
     */
    String MC_DEATHSCREEN_DELETEWORLD = "minecraft:deathScreen.deleteWorld";

    /**
     * {@literal You cannot respawn in hardcore mode!}
     */
    String MC_DEATHSCREEN_HARDCOREINFO = "minecraft:deathScreen.hardcoreInfo";

    /**
     * {@literal Leave server}
     */
    String MC_DEATHSCREEN_LEAVESERVER = "minecraft:deathScreen.leaveServer";

    /**
     * {@literal You died!}
     */
    String MC_DEATHSCREEN_MESSAGE = "minecraft:deathScreen.message";

    /**
     * {@literal Main menu}
     */
    String MC_DEATHSCREEN_QUIT = "minecraft:deathScreen.quit";

    /**
     * {@literal Are you sure you want to quit?}
     */
    String MC_DEATHSCREEN_QUIT_CONFIRM = "minecraft:deathScreen.quit.confirm";

    /**
     * {@literal Quit to Main Menu?}
     */
    String MC_DEATHSCREEN_QUIT_CONFIRMTOMAINMENUTITLEWARNING = "minecraft:deathScreen.quit.confirmToMainMenuTitleWarning";

    /**
     * {@literal Are you sure you want to exit the game to the main menu?}
     */
    String MC_DEATHSCREEN_QUIT_CONFIRMTOMAINMENUWARNING = "minecraft:deathScreen.quit.confirmToMainMenuWarning";

    /**
     * {@literal Save and Leave}
     */
    String MC_DEATHSCREEN_QUIT_SECONDARYCLIENT = "minecraft:deathScreen.quit.secondaryClient";

    /**
     * {@literal Save and Leave}
     */
    String MC_DEATHSCREEN_QUIT_SECONDARYCLIENT_CONFIRMLEAVETITLEWARNING = "minecraft:deathScreen.quit.secondaryClient.confirmLeaveTitleWarning";

    /**
     * {@literal Are you sure you want to save and leave the game?}
     */
    String MC_DEATHSCREEN_QUIT_SECONDARYCLIENT_CONFIRMLEAVEWARNING = "minecraft:deathScreen.quit.secondaryClient.confirmLeaveWarning";

    /**
     * {@literal Respawn}
     */
    String MC_DEATHSCREEN_RESPAWN = "minecraft:deathScreen.respawn";

    /**
     * {@literal Score}
     */
    String MC_DEATHSCREEN_SCORE = "minecraft:deathScreen.score";

    /**
     * {@literal You died!}
     */
    String MC_DEATHSCREEN_TITLE = "minecraft:deathScreen.title";

    /**
     * {@literal Game over!}
     */
    String MC_DEATHSCREEN_TITLE_HARDCORE = "minecraft:deathScreen.title.hardcore";

    /**
     * {@literal Title screen}
     */
    String MC_DEATHSCREEN_TITLESCREEN = "minecraft:deathScreen.titleScreen";

    /**
     * {@literal Are you sure you want to lock the difficulty of this world? This will set this world to always be %1#s, and you will never be able to change that again.}
     */
    String MC_DIFFICULTY_LOCK_QUESTION = "minecraft:difficulty.lock.question";

    /**
     * {@literal Lock World Difficulty}
     */
    String MC_DIFFICULTY_LOCK_TITLE = "minecraft:difficulty.lock.title";

    /**
     * {@literal Overworld}
     */
    String MC_DIMENSION_DIMENSIONNAME0 = "minecraft:dimension.dimensionName0";

    /**
     * {@literal Nether}
     */
    String MC_DIMENSION_DIMENSIONNAME1 = "minecraft:dimension.dimensionName1";

    /**
     * {@literal The End}
     */
    String MC_DIMENSION_DIMENSIONNAME2 = "minecraft:dimension.dimensionName2";

    /**
     * {@literal This server has %s disabled. Are you sure want to keep this skin equipped?}
     */
    String MC_DISABLEDSKIN_BODY_DRESSINGROOM = "minecraft:disabledSkin.body.dressingRoom";

    /**
     * {@literal This server has %s disabled. You can switch to a different skin in the dressing room.}
     */
    String MC_DISABLEDSKIN_BODY_ONJOIN = "minecraft:disabledSkin.body.onJoin";

    /**
     * {@literal You are being displayed as a default character}
     */
    String MC_DISABLEDSKIN_TITLE = "minecraft:disabledSkin.title";

    /**
     * {@literal custom skins and personas}
     */
    String MC_DISABLEDSKIN_TYPE_BOTH = "minecraft:disabledSkin.type.both";

    /**
     * {@literal custom skins}
     */
    String MC_DISABLEDSKIN_TYPE_CUSTOMSKINS = "minecraft:disabledSkin.type.customSkins";

    /**
     * {@literal personas}
     */
    String MC_DISABLEDSKIN_TYPE_PERSONA = "minecraft:disabledSkin.type.persona";

    /**
     * {@literal Connection closed}
     */
    String MC_DISCONNECT_CLOSED = "minecraft:disconnect.closed";

    /**
     * {@literal Disconnected by Server}
     */
    String MC_DISCONNECT_DISCONNECTED = "minecraft:disconnect.disconnected";

    /**
     * {@literal Error while downloading packs from server. Please try again.}
     */
    String MC_DISCONNECT_DOWNLOADPACK = "minecraft:disconnect.downloadPack";

    /**
     * {@literal End of stream}
     */
    String MC_DISCONNECT_ENDOFSTREAM = "minecraft:disconnect.endOfStream";

    /**
     * {@literal %s}
     */
    String MC_DISCONNECT_GENERICREASON = "minecraft:disconnect.genericReason";

    /**
     * {@literal You were kicked from the game}
     */
    String MC_DISCONNECT_KICKED = "minecraft:disconnect.kicked";

    /**
     * {@literal You were kicked from the game:}
     */
    String MC_DISCONNECT_KICKED_REASON = "minecraft:disconnect.kicked.reason";

    /**
     * {@literal Failed to login}
     */
    String MC_DISCONNECT_LOGINFAILED = "minecraft:disconnect.loginFailed";

    /**
     * {@literal Failed to login: %s}
     */
    String MC_DISCONNECT_LOGINFAILEDINFO = "minecraft:disconnect.loginFailedInfo";

    /**
     * {@literal Playfab environment mismatch - Discovery=%s/%s, Playfab=%s/%s}
     */
    String MC_DISCONNECT_LOGINFAILEDINFO_ENVIRONMENTMISMATCH = "minecraft:disconnect.loginFailedInfo.environmentMismatch";

    /**
     * {@literal Invalid session (Try restarting your game)}
     */
    String MC_DISCONNECT_LOGINFAILEDINFO_INVALIDSESSION = "minecraft:disconnect.loginFailedInfo.invalidSession";

    /**
     * {@literal The authentication servers are currently down for maintenance.}
     */
    String MC_DISCONNECT_LOGINFAILEDINFO_SERVERSUNAVAILABLE = "minecraft:disconnect.loginFailedInfo.serversUnavailable";

    /**
     * {@literal Connection Lost}
     */
    String MC_DISCONNECT_LOST = "minecraft:disconnect.lost";

    /**
     * {@literal Buffer overflow}
     */
    String MC_DISCONNECT_OVERFLOW = "minecraft:disconnect.overflow";

    /**
     * {@literal Quitting}
     */
    String MC_DISCONNECT_QUITTING = "minecraft:disconnect.quitting";

    /**
     * {@literal You were removed from the game}
     */
    String MC_DISCONNECT_REMOVED = "minecraft:disconnect.removed";

    /**
     * {@literal You were removed from the game:}
     */
    String MC_DISCONNECT_REMOVED_REASON = "minecraft:disconnect.removed.reason";

    /**
     * {@literal The server was shut down due to an unhandled scripting watchdog exception.}
     */
    String MC_DISCONNECT_SCRIPTWATCHDOG = "minecraft:disconnect.scriptWatchdog";

    /**
     * {@literal The server was shut down due to exceeding the scripting memory limit.}
     */
    String MC_DISCONNECT_SCRIPTWATCHDOGOUTOFMEMORY = "minecraft:disconnect.scriptWatchdogOutOfMemory";

    /**
     * {@literal Kicked for spamming}
     */
    String MC_DISCONNECT_SPAM = "minecraft:disconnect.spam";

    /**
     * {@literal Timed out}
     */
    String MC_DISCONNECT_TIMEOUT = "minecraft:disconnect.timeout";

    /**
     * {@literal Server sent broken packet.}
     */
    String MC_DISCONNECTIONSCREEN_BADPACKET = "minecraft:disconnectionScreen.badPacket";

    /**
     * {@literal %s ID collides with %s}
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_IDDUPLICATION = "minecraft:disconnectionScreen.biomeData.IdDuplication";

    /**
     * {@literal Malformed Biome Data}
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_MALFORMEDDATA = "minecraft:disconnectionScreen.biomeData.MalformedData";

    /**
     * {@literal Biome Packet has mixed id presence for Custom Biomes}
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_MIXEDIDPRESENCE = "minecraft:disconnectionScreen.biomeData.MixedIdPresence";

    /**
     * {@literal Custom Biome %s uses ID from a reserved range. ID range for Custom Biomes is: %s-%s}
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_RESERVEDRANGE = "minecraft:disconnectionScreen.biomeData.ReservedRange";

    /**
     * {@literal Vanilla Biome data should not contain 'id' field}
     */
    String MC_DISCONNECTIONSCREEN_BIOMEDATA_VANILLABIOMESWITHID = "minecraft:disconnectionScreen.biomeData.VanillaBiomesWithId";

    /**
     * {@literal Blocks between client and server do not match.}
     */
    String MC_DISCONNECTIONSCREEN_BLOCKMISMATCH = "minecraft:disconnectionScreen.blockMismatch";

    /**
     * {@literal We are unable to connect you to this server.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_CANTCONNECTSERVER = "minecraft:disconnectionScreen.body.cantConnectServer";

    /**
     * {@literal Unable to find Client.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_CANTFINDCLIENT = "minecraft:disconnectionScreen.body.cantFindClient";

    /**
     * {@literal Server not found.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_CANTFINDSERVER = "minecraft:disconnectionScreen.body.cantFindServer";

    /**
     * {@literal Check your connection and try again}
     */
    String MC_DISCONNECTIONSCREEN_BODY_CHECKCONNECTIONANDTRY = "minecraft:disconnectionScreen.body.checkConnectionAndTry";

    /**
     * {@literal Check the storage space on your device, delete items to free up more space.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_CHECKDEVICESTORAGE = "minecraft:disconnectionScreen.body.checkDeviceStorage";

    /**
     * {@literal Your discovery environment does not match the host's.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_DISCOVERYMISMATCH = "minecraft:disconnectionScreen.body.discoveryMismatch";

    /**
     * {@literal The game version you are using is out of date. Update your game to the latest version and try again.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_GAMEOUTOFDATE = "minecraft:disconnectionScreen.body.gameOutOfDate";

    /**
     * {@literal The host signed out.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_HOSTSIGNEDOUT = "minecraft:disconnectionScreen.body.hostSignedOut";

    /**
     * {@literal The host you are trying to join is using an older version of Minecraft. Try again when the host has updated its version.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_HOSTVERSIONOLDER = "minecraft:disconnectionScreen.body.hostVersionOlder";

    /**
     * {@literal The host you are trying to join is not prepared to accept authenticated connections. Try again in a few minutes.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_HOSTWITHOUTKEYS = "minecraft:disconnectionScreen.body.hostWithoutKeys";

    /**
     * {@literal You have been idle for too long.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_IDLETOOLONG = "minecraft:disconnectionScreen.body.idleTooLong";

    /**
     * {@literal You may be logged in at another location.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_LOGGEDINELSEWHERE = "minecraft:disconnectionScreen.body.loggedInElsewhere";

    /**
     * {@literal Your client is having trouble establishing a connection to multiplayer services.  Please check your internet connection, restart your client, and try again.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_MULTIPLAYERCONNECTION = "minecraft:disconnectionScreen.body.multiplayerConnection";

    /**
     * {@literal You can't join the server unless the host unblocks you.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_NEEDSHOSTUNBLOCK = "minecraft:disconnectionScreen.body.needsHostUnblock";

    /**
     * {@literal Permissions do not allow you to join.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_PERMISSIONSDONOTALLOW = "minecraft:disconnectionScreen.body.permissionsDoNotAllow";

    /**
     * {@literal Unable to join this specific server.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_REALMS_HIDDEN = "minecraft:disconnectionScreen.body.realms_hidden";

    /**
     * {@literal The server you are attempting to join may not exist or be locked.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_SERVERNONEORLOCKED = "minecraft:disconnectionScreen.body.serverNoneOrLocked";

    /**
     * {@literal Your skin appears to be banned.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_SKINBANNED = "minecraft:disconnectionScreen.body.skinBanned";

    /**
     * {@literal Your skin may be locked to a specific platform.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_SKINLOCKEDPLATFORM = "minecraft:disconnectionScreen.body.skinLockedPlatform";

    /**
     * {@literal You may be out of storage space on your device.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_STORAGEOUT = "minecraft:disconnectionScreen.body.storageOut";

    /**
     * {@literal Your version does not match the host's version.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_VERSIONMISMATCHHOST = "minecraft:disconnectionScreen.body.versionMismatchHost";

    /**
     * {@literal Your version is not supported.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_VERSIONNOTSUPPORTED = "minecraft:disconnectionScreen.body.versionNotSupported";

    /**
     * {@literal This world was made with a more recent version.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_WORLDNEWERVERSION = "minecraft:disconnectionScreen.body.worldNewerVersion";

    /**
     * {@literal The world you're connecting to is no longer active.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_WORLDNOLONGERACTIVE = "minecraft:disconnectionScreen.body.worldNoLongerActive";

    /**
     * {@literal This world is no longer available.}
     */
    String MC_DISCONNECTIONSCREEN_BODY_WORLDNOLONGERAVAILABLE = "minecraft:disconnectionScreen.body.worldNoLongerAvailable";

    /**
     * {@literal Unable to connect to world.}
     */
    String MC_DISCONNECTIONSCREEN_CANTCONNECT = "minecraft:disconnectionScreen.cantConnect";

    /**
     * {@literal Unable to connect to Realm.}
     */
    String MC_DISCONNECTIONSCREEN_CANTCONNECTTOREALM = "minecraft:disconnectionScreen.cantConnectToRealm";

    /**
     * {@literal Unable to connect to Realms.}
     */
    String MC_DISCONNECTIONSCREEN_CANTCONNECTTOREALMS = "minecraft:disconnectionScreen.cantConnectToRealms";

    /**
     * {@literal Server and Client have different values set for feature toggle 'Client Side Generation'}
     */
    String MC_DISCONNECTIONSCREEN_CLIENTSIDEGENERATIONDESYNC = "minecraft:disconnectionScreen.clientsideGenerationDesync";

    /**
     * {@literal The connection to the host was lost. If that world is still being hosted, please check your internet connection and try to connect again.}
     */
    String MC_DISCONNECTIONSCREEN_CONNECTIONLOST = "minecraft:disconnectionScreen.connectionLost";

    /**
     * {@literal Enable cross-platform play in the main menu to connect with friends on other platforms. To play with other friends online without cross-play, the host of the game must also have cross-play disabled via the main menu.}
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLATFORMDISABLED = "minecraft:disconnectionScreen.crossPlatformDisabled";

    /**
     * {@literal Cross-Platform Play Disabled.}
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLATFORMDISABLED_HEADER = "minecraft:disconnectionScreen.crossPlatformDisabled.header";

    /**
     * {@literal CrossPlay is temporarily disabled!}
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLAYDISABLED = "minecraft:disconnectionScreen.crossPlayDisabled";

    /**
     * {@literal CrossPlay Disabled}
     */
    String MC_DISCONNECTIONSCREEN_CROSSPLAYDISABLED_TITLE = "minecraft:disconnectionScreen.crossPlayDisabled.title";

    /**
     * {@literal Try opening this world in demo mode.}
     */
    String MC_DISCONNECTIONSCREEN_DEEPLINK_TRYINGTOOPENDEMOWORLDWHILESIGNEDIN_BODY = "minecraft:disconnectionScreen.deepLink.TryingToOpenDemoWorldWhileSignedIn.body";

    /**
     * {@literal Unable to load world.}
     */
    String MC_DISCONNECTIONSCREEN_DEEPLINK_TRYINGTOOPENDEMOWORLDWHILESIGNEDIN_TITLE = "minecraft:disconnectionScreen.deepLink.TryingToOpenDemoWorldWhileSignedIn.title";

    /**
     * {@literal Disconnected from Server}
     */
    String MC_DISCONNECTIONSCREEN_DISCONNECTED = "minecraft:disconnectionScreen.disconnected";

    /**
     * {@literal This world was saved from Minecraft Education. It cannot be loaded.}
     */
    String MC_DISCONNECTIONSCREEN_EDITIONMISMATCH = "minecraft:disconnectionScreen.editionMismatch";

    /**
     * {@literal The server is not running Minecraft Education. Failed to connect.}
     */
    String MC_DISCONNECTIONSCREEN_EDITIONMISMATCHEDUTOVANILLA = "minecraft:disconnectionScreen.editionMismatchEduToVanilla";

    /**
     * {@literal The server is running an incompatible edition of Minecraft. Failed to connect.}
     */
    String MC_DISCONNECTIONSCREEN_EDITIONMISMATCHVANILLATOEDU = "minecraft:disconnectionScreen.editionMismatchVanillaToEdu";

    /**
     * {@literal The world you selected cannot be opened in this version of Minecraft Education.}
     */
    String MC_DISCONNECTIONSCREEN_EDITIONVERSIONMISMATCH_BODY = "minecraft:disconnectionScreen.editionVersionMismatch.body";

    /**
     * {@literal Unable to load world.}
     */
    String MC_DISCONNECTIONSCREEN_EDITIONVERSIONMISMATCH_TITLE = "minecraft:disconnectionScreen.editionVersionMismatch.title";

    /**
     * {@literal Ray Tracing enabled resource packs are not yet compatible with Editor projects.}
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_CANNOTUSERAYTRACING = "minecraft:disconnectionScreen.editor.cannotUseRayTracing";

    /**
     * {@literal The server is not in Editor Mode. Failed to connect.}
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHEDITORTOVANILLA = "minecraft:disconnectionScreen.editor.mismatchEditorToVanilla";

    /**
     * {@literal This world is in Editor Mode. It cannot be loaded.}
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHEDITORWORLD = "minecraft:disconnectionScreen.editor.mismatchEditorWorld";

    /**
     * {@literal The server is in Editor Mode. Failed to connect.}
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHVANILLATOEDITOR = "minecraft:disconnectionScreen.editor.mismatchVanillaToEditor";

    /**
     * {@literal This world is a not in Editor Mode. It cannot be loaded.}
     */
    String MC_DISCONNECTIONSCREEN_EDITOR_MISMATCHVANILLAWORLD = "minecraft:disconnectionScreen.editor.mismatchVanillaWorld";

    /**
     * {@literal Error Code: }
     */
    String MC_DISCONNECTIONSCREEN_ERRORCODE = "minecraft:disconnectionScreen.errorCode";

    /**
     * {@literal There was a problem loading into the server. }
     */
    String MC_DISCONNECTIONSCREEN_FAILEDASYNCCHECK = "minecraft:disconnectionScreen.failedAsyncCheck";

    /**
     * {@literal Realm not found, or you are not a member.}
     */
    String MC_DISCONNECTIONSCREEN_FORBIDDEN = "minecraft:disconnectionScreen.forbidden";

    /**
     * {@literal A newer version of the game has saved this world. It cannot be loaded.}
     */
    String MC_DISCONNECTIONSCREEN_FUTUREVERSION = "minecraft:disconnectionScreen.futureVersion";

    /**
     * {@literal Multiplayer Invitation}
     */
    String MC_DISCONNECTIONSCREEN_HEADER_REALMS_HIDDEN = "minecraft:disconnectionScreen.header.realms_hidden";

    /**
     * {@literal Disconnected due to the server being unavailable}
     */
    String MC_DISCONNECTIONSCREEN_HOSTSUSPENDED = "minecraft:disconnectionScreen.hostSuspended";

    /**
     * {@literal You are unable to join the world because you have the following incompatible packs: %s}
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEPACK = "minecraft:disconnectionScreen.incompatiblePack";

    /**
     * {@literal You are unable to join the world because your device doesn't have compatible hardware to support ray tracing}
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEPACK_GRAPHICS_RAYTRACING = "minecraft:disconnectionScreen.incompatiblePack.graphics.raytracing";

    /**
     * {@literal You are unable to join the world because your device doesn't have enough memory for the following packs: %s}
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEPACK_MEMORY = "minecraft:disconnectionScreen.incompatiblePack.memory";

    /**
     * {@literal Your client is incompatible or out of date. Update your client to connect to Realms.}
     */
    String MC_DISCONNECTIONSCREEN_INCOMPATIBLEREALM = "minecraft:disconnectionScreen.incompatibleRealm";

    /**
     * {@literal We were unable to connect you.}
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTCONNECT = "minecraft:disconnectionScreen.internalError.cantConnect";

    /**
     * {@literal There was a problem finding this server.}
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTEDIT = "minecraft:disconnectionScreen.internalError.cantEdit";

    /**
     * {@literal We were unable to connect you. Try adding a new server.}
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDEDIT = "minecraft:disconnectionScreen.internalError.cantFindEdit";

    /**
     * {@literal There was a problem loading this world.}
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDLOCAL = "minecraft:disconnectionScreen.internalError.cantFindLocal";

    /**
     * {@literal There was a problem finding this Realm.}
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDREALM = "minecraft:disconnectionScreen.internalError.cantFindRealm";

    /**
     * {@literal There was a problem finding this server.}
     */
    String MC_DISCONNECTIONSCREEN_INTERNALERROR_CANTFINDSERVER = "minecraft:disconnectionScreen.internalError.cantFindServer";

    /**
     * {@literal Invalid IP address!}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDIP = "minecraft:disconnectionScreen.invalidIP";

    /**
     * {@literal Invalid Level!}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDLEVEL = "minecraft:disconnectionScreen.invalidLevel";

    /**
     * {@literal Invalid name!}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDNAME = "minecraft:disconnectionScreen.invalidName";

    /**
     * {@literal The server provided invalid height values for the overworld dimension}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDOVERWORLDDIMENSIONHEIGHTS = "minecraft:disconnectionScreen.invalidOverworldDimensionHeights";

    /**
     * {@literal This world's multiplayer setting is set to friends only. You must be friends with the host of this world to join.}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDPLAYER = "minecraft:disconnectionScreen.invalidPlayer";

    /**
     * {@literal Invalid or corrupt skin!}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDSKIN = "minecraft:disconnectionScreen.invalidSkin";

    /**
     * {@literal Unable to connect to the world. Please check your join code and try again.}
     */
    String MC_DISCONNECTIONSCREEN_INVALIDTENANT = "minecraft:disconnectionScreen.invalidTenant";

    /**
     * {@literal The Skin that you have equipped is from a content pack that is not allowed in cross-platform multiplayer games.}
     */
    String MC_DISCONNECTIONSCREEN_LOCKEDSKIN = "minecraft:disconnectionScreen.lockedSkin";

    /**
     * {@literal Platform Restricted Skin!}
     */
    String MC_DISCONNECTIONSCREEN_LOCKEDSKIN_TITLE = "minecraft:disconnectionScreen.lockedSkin.title";

    /**
     * {@literal Logged in from other location}
     */
    String MC_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION = "minecraft:disconnectionScreen.loggedinOtherLocation";

    /**
     * {@literal The world has been set to single player mode.}
     */
    String MC_DISCONNECTIONSCREEN_MULTIPLAYERDISABLED = "minecraft:disconnectionScreen.multiplayerDisabled";

    /**
     * {@literal The Skin that you have equipped is from a content pack that is not allowed in multiplayer games.}
     */
    String MC_DISCONNECTIONSCREEN_MULTIPLAYERLOCKEDSKIN = "minecraft:disconnectionScreen.multiplayerLockedSkin";

    /**
     * {@literal Multiplayer Restricted Skin!}
     */
    String MC_DISCONNECTIONSCREEN_MULTIPLAYERLOCKEDSKIN_TITLE = "minecraft:disconnectionScreen.multiplayerLockedSkin.title";

    /**
     * {@literal Please check your connection to the internet and try again.}
     */
    String MC_DISCONNECTIONSCREEN_NOINTERNET = "minecraft:disconnectionScreen.noInternet";

    /**
     * {@literal You have been disconnected}
     */
    String MC_DISCONNECTIONSCREEN_NOREASON = "minecraft:disconnectionScreen.noReason";

    /**
     * {@literal You're not invited to play on this server.}
     */
    String MC_DISCONNECTIONSCREEN_NOTALLOWED = "minecraft:disconnectionScreen.notAllowed";

    /**
     * {@literal You need to authenticate to Microsoft services.}
     */
    String MC_DISCONNECTIONSCREEN_NOTAUTHENTICATED = "minecraft:disconnectionScreen.notAuthenticated";

    /**
     * {@literal It appears you are unable to connect to your network, please connect to a Wi-Fi network or enable 'Use Mobile Data' in profile settings.}
     */
    String MC_DISCONNECTIONSCREEN_NOWIFI = "minecraft:disconnectionScreen.nowifi";

    /**
     * {@literal Could not connect: Outdated client!}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDCLIENT = "minecraft:disconnectionScreen.outdatedClient";

    /**
     * {@literal You need to update your game to continue playing on this Realm.}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDCLIENTREALMS = "minecraft:disconnectionScreen.outdatedClientRealms";

    /**
     * {@literal Realms Has Updated}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDCLIENTREALMS_TITLE = "minecraft:disconnectionScreen.outdatedClientRealms.title";

    /**
     * {@literal The host is using an older version of Minecraft. Everyone should update to the latest version of Minecraft and try again.}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVER = "minecraft:disconnectionScreen.outdatedServer";

    /**
     * {@literal Your game is up-to-date but this Realm is not.}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVERREALMS = "minecraft:disconnectionScreen.outdatedServerRealms";

    /**
     * {@literal We can't update your Realm server right now because other players are currently online. You can close and reopen your Realm from the Manage Realm screen to make the other players quit and then join your Realm to update it.}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVERREALMS_TITLE = "minecraft:disconnectionScreen.outdatedServerRealms.title";

    /**
     * {@literal The Realm you are attempting to access is a different version. Contact the owner of the Realm to address the issue.}
     */
    String MC_DISCONNECTIONSCREEN_OUTDATEDSERVERREALMSNONOWNER_TITLE = "minecraft:disconnectionScreen.outdatedServerRealmsNonOwner.title";

    /**
     * {@literal Realms is not available right now.}
     */
    String MC_DISCONNECTIONSCREEN_REALMSALPHAENDEDMESSAGE = "minecraft:disconnectionScreen.realmsAlphaEndedMessage";

    /**
     * {@literal Testing the Realms Alpha has ended. Thank you for helping!}
     */
    String MC_DISCONNECTIONSCREEN_REALMSALPHAENDEDTITLE = "minecraft:disconnectionScreen.realmsAlphaEndedTitle";

    /**
     * {@literal Realms are disabled.}
     */
    String MC_DISCONNECTIONSCREEN_REALMSDISABLED = "minecraft:disconnectionScreen.realmsDisabled";

    /**
     * {@literal Realms are disabled for the beta.}
     */
    String MC_DISCONNECTIONSCREEN_REALMSDISABLEDBETA = "minecraft:disconnectionScreen.realmsDisabledBeta";

    /**
     * {@literal Your Realm will soon be updated. You will need an update too.}
     */
    String MC_DISCONNECTIONSCREEN_REALMSSERVERUPDATEINCOMING = "minecraft:disconnectionScreen.realmsServerUpdateIncoming";

    /**
     * {@literal Check For Game Update Now?}
     */
    String MC_DISCONNECTIONSCREEN_REALMSSERVERUPDATEINCOMING_TITLE = "minecraft:disconnectionScreen.realmsServerUpdateIncoming.title";

    /**
     * {@literal Assign version in dev options.}
     */
    String MC_DISCONNECTIONSCREEN_REALMSWORLDUNASSIGNED = "minecraft:disconnectionScreen.realmsWorldUnassigned";

    /**
     * {@literal Realm is Unassigned!}
     */
    String MC_DISCONNECTIONSCREEN_REALMSWORLDUNASSIGNED_TITLE = "minecraft:disconnectionScreen.realmsWorldUnassigned.title";

    /**
     * {@literal Encountered a problem while downloading or applying resource pack.}
     */
    String MC_DISCONNECTIONSCREEN_RESOURCEPACK = "minecraft:disconnectionScreen.resourcePack";

    /**
     * {@literal Unable to connect, please restart your client.}
     */
    String MC_DISCONNECTIONSCREEN_RESTARTCLIENT = "minecraft:disconnectionScreen.restartClient";

    /**
     * {@literal Wow this server is popular! Check back later to see if space opens up.}
     */
    String MC_DISCONNECTIONSCREEN_SERVERFULL = "minecraft:disconnectionScreen.serverFull";

    /**
     * {@literal Server Full}
     */
    String MC_DISCONNECTIONSCREEN_SERVERFULL_TITLE = "minecraft:disconnectionScreen.serverFull.title";

    /**
     * {@literal Cannot join world. The account you are signed in to is currently playing in this world on a different device.}
     */
    String MC_DISCONNECTIONSCREEN_SERVERIDCONFLICT = "minecraft:disconnectionScreen.serverIdConflict";

    /**
     * {@literal Unable to connect to world. The world is no longer available to join.}
     */
    String MC_DISCONNECTIONSCREEN_SESSIONNOTFOUND = "minecraft:disconnectionScreen.sessionNotFound";

    /**
     * {@literal There was a problem connecting to the world. Please try again. If this error continues, check your internet connection or try restarting Minecraft.}
     */
    String MC_DISCONNECTIONSCREEN_TIMEOUT = "minecraft:disconnectionScreen.timeout";

    /**
     * {@literal You are banned by the host.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_BANNEDBYHOST = "minecraft:disconnectionScreen.title.bannedByHost";

    /**
     * {@literal Connection timed out.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_CONNECTIONTIMEOUT = "minecraft:disconnectionScreen.title.connectionTimeout";

    /**
     * {@literal Disconnected.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_DISCONNECTED = "minecraft:disconnectionScreen.title.disconnected";

    /**
     * {@literal Disconnected from host.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_DISCONNECTEDFROMHOST = "minecraft:disconnectionScreen.title.disconnectedFromHost";

    /**
     * {@literal You have encountered an error.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_ERRORENCOUNTERED = "minecraft:disconnectionScreen.title.errorEncountered";

    /**
     * {@literal An error has occurred.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_ERROROCCURRED = "minecraft:disconnectionScreen.title.errorOccurred";

    /**
     * {@literal Incompatible Pack.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INCOMPATIBLEPACK = "minecraft:disconnectionScreen.title.incompatiblePack";

    /**
     * {@literal Incompatible World.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INCOMPATIBLEWORLD = "minecraft:disconnectionScreen.title.incompatibleWorld";

    /**
     * {@literal Invalid Skin.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INVALIDSKIN = "minecraft:disconnectionScreen.title.invalidSkin";

    /**
     * {@literal Invalid Tenant ID}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_INVALIDTENANTID = "minecraft:disconnectionScreen.title.invalidTenantId";

    /**
     * {@literal You have been kicked from the world.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_KICKEDFROMWORLD = "minecraft:disconnectionScreen.title.kickedFromWorld";

    /**
     * {@literal Local server not found.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_LOCALSERVERNOTFOUND = "minecraft:disconnectionScreen.title.localServerNotFound";

    /**
     * {@literal Multiplayer Connection Failed}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_MULTIPLAYERCONNECTION = "minecraft:disconnectionScreen.title.multiplayerConnection";

    /**
     * {@literal Multiplayer is disabled.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_MULTIPLAYERDISABLED = "minecraft:disconnectionScreen.title.multiplayerDisabled";

    /**
     * {@literal No WiFi Connection}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_NOWIFI = "minecraft:disconnectionScreen.title.noWifi";

    /**
     * {@literal Out of storage space}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_OUTOFSTORAGE = "minecraft:disconnectionScreen.title.outOfStorage";

    /**
     * {@literal This Realm has no world assigned.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_REALMSWORLDUNASSIGNED = "minecraft:disconnectionScreen.title.realmsWorldUnassigned";

    /**
     * {@literal Resource pack failed to load.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_RESOURCEPACKLOAD = "minecraft:disconnectionScreen.title.resourcePackLoad";

    /**
     * {@literal Server not found.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_SERVERNOTFOUND = "minecraft:disconnectionScreen.title.serverNotFound";

    /**
     * {@literal Skin error.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_SKINERROR = "minecraft:disconnectionScreen.title.skinError";

    /**
     * {@literal There is an issue with your skin.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_SKINISSUE = "minecraft:disconnectionScreen.title.skinIssue";

    /**
     * {@literal Unable to connect.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_UNABLETOCONNECT = "minecraft:disconnectionScreen.title.unableToConnect";

    /**
     * {@literal Version mismatch}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_VERSIONMISMATCH = "minecraft:disconnectionScreen.title.versionMismatch";

    /**
     * {@literal Your version does not match the host's version}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_VERSIONMISMATCHHOST = "minecraft:disconnectionScreen.title.versionMismatchHost";

    /**
     * {@literal Version is out of date.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_VERSIONOUTOFDATE = "minecraft:disconnectionScreen.title.versionOutOfDate";

    /**
     * {@literal World failed to load.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_WORLDFAILEDLOAD = "minecraft:disconnectionScreen.title.worldFailedLoad";

    /**
     * {@literal The World you are trying to join is full.}
     */
    String MC_DISCONNECTIONSCREEN_TITLE_WORLDFULL = "minecraft:disconnectionScreen.title.worldFull";

    /**
     * {@literal Disconnected due to an unexpected type of packet received which cannot be processed.  This could be due to a version mismatch or corruption at the networking level.}
     */
    String MC_DISCONNECTIONSCREEN_UNEXPECTEDPACKET = "minecraft:disconnectionScreen.unexpectedPacket";

    /**
     * {@literal Disconnected due to world corruption}
     */
    String MC_DISCONNECTIONSCREEN_WORLDCORRUPTION = "minecraft:disconnectionScreen.worldCorruption";

    /**
     * {@literal Try reopening the world or restoring it from a previous backup}
     */
    String MC_DISCONNECTIONSCREEN_WORLDCORRUPTION_MESSAGE = "minecraft:disconnectionScreen.worldCorruption.message";

    /**
     * {@literal Accents}
     */
    String MC_DR_ACCENT_CHANNEL = "minecraft:dr.accent_channel";

    /**
     * {@literal Narrow Arms}
     */
    String MC_DR_ACCESSIBILITY_ARM_SIZE_NARROW = "minecraft:dr.accessibility.arm_size.narrow";

    /**
     * {@literal Wide Arms}
     */
    String MC_DR_ACCESSIBILITY_ARM_SIZE_WIDE = "minecraft:dr.accessibility.arm_size.wide";

    /**
     * {@literal Select previous character}
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_LEFT = "minecraft:dr.accessibility.character_left";

    /**
     * {@literal Select next character}
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_RIGHT = "minecraft:dr.accessibility.character_right";

    /**
     * {@literal Medium Character}
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_MEDIUM = "minecraft:dr.accessibility.character_size.medium";

    /**
     * {@literal Small Character}
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_SMALL = "minecraft:dr.accessibility.character_size.small";

    /**
     * {@literal Smaller character}
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_SMALLER = "minecraft:dr.accessibility.character_size.smaller";

    /**
     * {@literal Tall Character}
     */
    String MC_DR_ACCESSIBILITY_CHARACTER_SIZE_TALL = "minecraft:dr.accessibility.character_size.tall";

    /**
     * {@literal Toggle. Open Color Picker}
     */
    String MC_DR_ACCESSIBILITY_COLOR_PICKER = "minecraft:dr.accessibility.color_picker";

    /**
     * {@literal Custom Classic Skin}
     */
    String MC_DR_ACCESSIBILITY_CUSTOM_SKIN = "minecraft:dr.accessibility.custom_skin";

    /**
     * {@literal Toggle. View Character in full screen}
     */
    String MC_DR_ACCESSIBILITY_EXPAND_APPEARANCE = "minecraft:dr.accessibility.expand_appearance";

    /**
     * {@literal Switch to both limbs}
     */
    String MC_DR_ACCESSIBILITY_LIMB_BOTH = "minecraft:dr.accessibility.limb_both";

    /**
     * {@literal Switch to left side}
     */
    String MC_DR_ACCESSIBILITY_LIMB_LEFT = "minecraft:dr.accessibility.limb_left";

    /**
     * {@literal Switch to right side}
     */
    String MC_DR_ACCESSIBILITY_LIMB_RIGHT = "minecraft:dr.accessibility.limb_right";

    /**
     * {@literal Locked}
     */
    String MC_DR_ACCESSIBILITY_LOCKED = "minecraft:dr.accessibility.locked";

    /**
     * {@literal Owned}
     */
    String MC_DR_ACCESSIBILITY_OWNED = "minecraft:dr.accessibility.owned";

    /**
     * {@literal Character Arms}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_ARMS = "minecraft:dr.accessibility.persona_arms";

    /**
     * {@literal Character Back Item}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_BACK = "minecraft:dr.accessibility.persona_back";

    /**
     * {@literal Character Body}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_BODY = "minecraft:dr.accessibility.persona_body";

    /**
     * {@literal Character Bottom}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_BOTTOM = "minecraft:dr.accessibility.persona_bottom";

    /**
     * {@literal Character Cape}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_CAPES = "minecraft:dr.accessibility.persona_capes";

    /**
     * {@literal Character Skin}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_CLASSIC_SKIN = "minecraft:dr.accessibility.persona_classic_skin";

    /**
     * {@literal Character Emote}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_EMOTE = "minecraft:dr.accessibility.persona_emote";

    /**
     * {@literal Character Eyes}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_EYES = "minecraft:dr.accessibility.persona_eyes";

    /**
     * {@literal Character Face Accessory}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_FACE_ACCESSORY = "minecraft:dr.accessibility.persona_face_accessory";

    /**
     * {@literal Character Facial Hair}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_FACIAL_HAIR = "minecraft:dr.accessibility.persona_facial_hair";

    /**
     * {@literal Character Footwear}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_FEET = "minecraft:dr.accessibility.persona_feet";

    /**
     * {@literal Character Hair}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HAIR = "minecraft:dr.accessibility.persona_hair";

    /**
     * {@literal Character Gloves}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HAND = "minecraft:dr.accessibility.persona_hand";

    /**
     * {@literal Character Headwear}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HEAD = "minecraft:dr.accessibility.persona_head";

    /**
     * {@literal Character Face Item}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_HOOD = "minecraft:dr.accessibility.persona_hood";

    /**
     * {@literal Character Legs}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_LEGS = "minecraft:dr.accessibility.persona_legs";

    /**
     * {@literal Character Mouth}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_MOUTH = "minecraft:dr.accessibility.persona_mouth";

    /**
     * {@literal Character Outerwear}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_OUTERWEAR = "minecraft:dr.accessibility.persona_outerwear";

    /**
     * {@literal Character Base}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_SKIN = "minecraft:dr.accessibility.persona_skin";

    /**
     * {@literal Character Top}
     */
    String MC_DR_ACCESSIBILITY_PERSONA_TOP = "minecraft:dr.accessibility.persona_top";

    /**
     * {@literal Purchaseable}
     */
    String MC_DR_ACCESSIBILITY_PURCHASEABLE = "minecraft:dr.accessibility.purchaseable";

    /**
     * {@literal Redeemable}
     */
    String MC_DR_ACCESSIBILITY_REDEEMABLE = "minecraft:dr.accessibility.redeemable";

    /**
     * {@literal Switch to %s}
     */
    String MC_DR_ACCESSIBILITY_SWITCH_COLOR_CHANNEL = "minecraft:dr.accessibility.switch_color_channel";

    /**
     * {@literal This item is unlocked through completing an achievement.}
     */
    String MC_DR_ACHIEVEMENT_ACHIEVEMENT_LOCKED_NOT_SIGNED_IN = "minecraft:dr.achievement.achievement_locked.not_signed_in";

    /**
     * {@literal This item is unlocked by completing the %s achievement.}
     */
    String MC_DR_ACHIEVEMENT_ACHIEVEMENT_LOCKED_SIGNED_IN = "minecraft:dr.achievement.achievement_locked.signed_in";

    /**
     * {@literal This item was unlocked by completing the %s achievement.}
     */
    String MC_DR_ACHIEVEMENT_ACHIEVEMENT_UNLOCKED = "minecraft:dr.achievement.achievement_unlocked";

    /**
     * {@literal This item is unlocked by completing a challenge from Minecraft Earth.}
     */
    String MC_DR_ACHIEVEMENT_CHALLENGE_LOCKED = "minecraft:dr.achievement.challenge_locked";

    /**
     * {@literal This item was unlocked by completing a challenge from Minecraft Earth.}
     */
    String MC_DR_ACHIEVEMENT_CHALLENGE_UNLOCKED = "minecraft:dr.achievement.challenge_unlocked";

    /**
     * {@literal Arm Size}
     */
    String MC_DR_ARM_SIZE_LABEL_TEXT = "minecraft:dr.arm_size_label_text";

    /**
     * {@literal Limited Time Offer!}
     */
    String MC_DR_BANNER_LIMITEDTIMEOFFER = "minecraft:dr.banner.limitedTimeOffer";

    /**
     * {@literal Sale!}
     */
    String MC_DR_BANNER_SALE = "minecraft:dr.banner.sale";

    /**
     * {@literal Height}
     */
    String MC_DR_BODY_SIZE_LABEL_TEXT = "minecraft:dr.body_size_label_text";

    /**
     * {@literal Both Arms}
     */
    String MC_DR_BOTH_ARMS = "minecraft:dr.both_arms";

    /**
     * {@literal Both Legs}
     */
    String MC_DR_BOTH_LEGS = "minecraft:dr.both_legs";

    /**
     * {@literal Ok}
     */
    String MC_DR_BUTTON_OK = "minecraft:dr.button.ok";

    /**
     * {@literal Emotes}
     */
    String MC_DR_CATEGORIES_ANIMATION = "minecraft:dr.categories.animation";

    /**
     * {@literal Arms}
     */
    String MC_DR_CATEGORIES_ARMS = "minecraft:dr.categories.arms";

    /**
     * {@literal Back Items}
     */
    String MC_DR_CATEGORIES_BACK_ITEM = "minecraft:dr.categories.back_item";

    /**
     * {@literal Bases}
     */
    String MC_DR_CATEGORIES_BASE = "minecraft:dr.categories.base";

    /**
     * {@literal Body}
     */
    String MC_DR_CATEGORIES_BODY = "minecraft:dr.categories.body";

    /**
     * {@literal Bottoms}
     */
    String MC_DR_CATEGORIES_BOTTOM = "minecraft:dr.categories.bottom";

    /**
     * {@literal Capes}
     */
    String MC_DR_CATEGORIES_CAPES = "minecraft:dr.categories.capes";

    /**
     * {@literal Emotes}
     */
    String MC_DR_CATEGORIES_EMOTES = "minecraft:dr.categories.emotes";

    /**
     * {@literal Eyes}
     */
    String MC_DR_CATEGORIES_EYES = "minecraft:dr.categories.eyes";

    /**
     * {@literal Face Items}
     */
    String MC_DR_CATEGORIES_FACE_ITEM = "minecraft:dr.categories.face_item";

    /**
     * {@literal Facial Hairs}
     */
    String MC_DR_CATEGORIES_FACIAL_HAIR = "minecraft:dr.categories.facial_hair";

    /**
     * {@literal Footwear}
     */
    String MC_DR_CATEGORIES_FOOTWEAR = "minecraft:dr.categories.footwear";

    /**
     * {@literal Gloves}
     */
    String MC_DR_CATEGORIES_GLOVES = "minecraft:dr.categories.gloves";

    /**
     * {@literal Hairs}
     */
    String MC_DR_CATEGORIES_HAIR = "minecraft:dr.categories.hair";

    /**
     * {@literal Headwear}
     */
    String MC_DR_CATEGORIES_HEADWEAR = "minecraft:dr.categories.headwear";

    /**
     * {@literal Legs}
     */
    String MC_DR_CATEGORIES_LEGS = "minecraft:dr.categories.legs";

    /**
     * {@literal Mouths}
     */
    String MC_DR_CATEGORIES_MOUTH = "minecraft:dr.categories.mouth";

    /**
     * {@literal Outerwear}
     */
    String MC_DR_CATEGORIES_OUTERWEAR = "minecraft:dr.categories.outerwear";

    /**
     * {@literal Size}
     */
    String MC_DR_CATEGORIES_SIZE = "minecraft:dr.categories.size";

    /**
     * {@literal Style}
     */
    String MC_DR_CATEGORIES_STYLE = "minecraft:dr.categories.style";

    /**
     * {@literal Tops}
     */
    String MC_DR_CATEGORIES_TOP = "minecraft:dr.categories.top";

    /**
     * {@literal Select items on the left to see how they look on your character!}
     */
    String MC_DR_CHARACTER_CREATOR_GETTING_STARTED_DETAIL = "minecraft:dr.character_creator_getting_started_detail";

    /**
     * {@literal Getting Started}
     */
    String MC_DR_CHARACTER_CREATOR_GETTING_STARTED_TITLE = "minecraft:dr.character_creator_getting_started_title";

    /**
     * {@literal +%s}
     */
    String MC_DR_CLASSIC_SKINS_ADDITION_SKIN_COUNT = "minecraft:dr.classic_skins.addition_skin_count";

    /**
     * {@literal Choose New Skin}
     */
    String MC_DR_CLASSIC_SKINS_CHOOSE_CUSTOM_SKIN = "minecraft:dr.classic_skins.choose_custom_skin";

    /**
     * {@literal Import a png (64x32, 64x64, or 128x128) from your device to use as your skin. This will not sync between devices or games.}
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_DESCRIPTION = "minecraft:dr.classic_skins.custom_skin_description";

    /**
     * {@literal Your device cannot select a custom skin while in a multiplayer game. Please return to the main menu to equip one.}
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_DESCRIPTION_DISABLED = "minecraft:dr.classic_skins.custom_skin_description_disabled";

    /**
     * {@literal Players with custom skins will not be visible to you if the “Only Allow Trusted Skins” option is enabled}
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_DESCRIPTION_POPUP = "minecraft:dr.classic_skins.custom_skin_description_popup";

    /**
     * {@literal Owned Skins}
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_SECTION_TITLE = "minecraft:dr.classic_skins.custom_skin_section_title";

    /**
     * {@literal Import Skin}
     */
    String MC_DR_CLASSIC_SKINS_CUSTOM_SKIN_TITLE = "minecraft:dr.classic_skins.custom_skin_title";

    /**
     * {@literal Please import a .png file of dimensions 64x64, 64x32, or 128x128}
     */
    String MC_DR_CLASSIC_SKINS_INVALIDCUSTOMSKIN = "minecraft:dr.classic_skins.invalidCustomSkin";

    /**
     * {@literal Owned}
     */
    String MC_DR_CLASSIC_SKINS_OWNED = "minecraft:dr.classic_skins.owned";

    /**
     * {@literal Get More}
     */
    String MC_DR_CLASSIC_SKINS_PURCHASABLE = "minecraft:dr.classic_skins.purchasable";

    /**
     * {@literal Realms Plus}
     */
    String MC_DR_CLASSIC_SKINS_REALMS_PLUS = "minecraft:dr.classic_skins.realms_plus";

    /**
     * {@literal Realms Plus}
     */
    String MC_DR_CLASSIC_SKINS_REALMS_PLUS_BUTTON = "minecraft:dr.classic_skins.realms_plus_button";

    /**
     * {@literal by Minecraft}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_AUTHOR_MINECRAFT = "minecraft:dr.classic_skins.right_side.author_minecraft";

    /**
     * {@literal by %s}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_AUTHOR_NAME = "minecraft:dr.classic_skins.right_side.author_name";

    /**
     * {@literal Custom Skin}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_CUSTOM_SKIN = "minecraft:dr.classic_skins.right_side.custom_skin";

    /**
     * {@literal Featured & Recommended Skin Packs}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_FEATURED_TITLE = "minecraft:dr.classic_skins.right_side.featured_title";

    /**
     * {@literal Usable everywhere}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_FREE_USAGE = "minecraft:dr.classic_skins.right_side.free_usage";

    /**
     * {@literal This skin will roam wherever you've logged in.}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_FREE_USAGE_TOOLTIP = "minecraft:dr.classic_skins.right_side.free_usage.tooltip";

    /**
     * {@literal Limited usage}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_LIMITED_USAGE = "minecraft:dr.classic_skins.right_side.limited_usage";

    /**
     * {@literal This skin can be used here, but may not roam.}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_LIMITED_USAGE_TOOLTIP = "minecraft:dr.classic_skins.right_side.limited_usage.tooltip";

    /**
     * {@literal *Buys %s Minecoins for %s and redeems this pack for %s Minecoins leaving %s left over.}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_PURCHASING_DISCLAIMER = "minecraft:dr.classic_skins.right_side.purchasing_disclaimer";

    /**
     * {@literal %s Skins}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_SKIN_COUNT_PLURAL = "minecraft:dr.classic_skins.right_side.skin_count.plural";

    /**
     * {@literal %s Skin}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_SKIN_COUNT_SINGULAR = "minecraft:dr.classic_skins.right_side.skin_count.singular";

    /**
     * {@literal %s Texture Packs}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_TEXTURE_COUNT_PLURAL = "minecraft:dr.classic_skins.right_side.texture_count.plural";

    /**
     * {@literal %s Texture Pack}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_TEXTURE_COUNT_SINGULAR = "minecraft:dr.classic_skins.right_side.texture_count.singular";

    /**
     * {@literal %s Worlds}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_WORLD_COUNT_PLURAL = "minecraft:dr.classic_skins.right_side.world_count.plural";

    /**
     * {@literal %s World}
     */
    String MC_DR_CLASSIC_SKINS_RIGHT_SIDE_WORLD_COUNT_SINGULAR = "minecraft:dr.classic_skins.right_side.world_count.singular";

    /**
     * {@literal See in Store}
     */
    String MC_DR_CLASSIC_SKINS_SEE_IN_STORE = "minecraft:dr.classic_skins.see_in_store";

    /**
     * {@literal See Pack in Store}
     */
    String MC_DR_CLASSIC_SKINS_SEE_PACK_IN_STORE = "minecraft:dr.classic_skins.see_pack_in_store";

    /**
     * {@literal Choose the correct model type for your skin}
     */
    String MC_DR_CLASSIC_SKINS_SELECT_SKIN = "minecraft:dr.classic_skins.select_skin";

    /**
     * {@literal Skin Type}
     */
    String MC_DR_CLASSIC_SKINS_SELECT_SKIN_TITLE = "minecraft:dr.classic_skins.select_skin.title";

    /**
     * {@literal Show More}
     */
    String MC_DR_CLASSIC_SKINS_SHOW_MORE = "minecraft:dr.classic_skins.show_more";

    /**
     * {@literal You must purchase the skin pack to use that skin, and we can't connect to the Marketplace.}
     */
    String MC_DR_CLASSIC_SKINS_UPSELLWITHOUTSTORE = "minecraft:dr.classic_skins.upsellWithoutStore";

    /**
     * {@literal Collect}
     */
    String MC_DR_COLLECT_ACHIEVEMENT_ITEM = "minecraft:dr.collect_achievement_item";

    /**
     * {@literal Featured %s}
     */
    String MC_DR_COLLECTOR_TITLE_FEATURED = "minecraft:dr.collector_title.featured";

    /**
     * {@literal All %s}
     */
    String MC_DR_COLLECTOR_TITLE_GENERAL = "minecraft:dr.collector_title.general";

    /**
     * {@literal Owned %s}
     */
    String MC_DR_COLLECTOR_TITLE_OWNED = "minecraft:dr.collector_title.owned";

    /**
     * {@literal Amber - Dark}
     */
    String MC_DR_COLOR_AMBER_DARK = "minecraft:dr.color.amber_dark";

    /**
     * {@literal Amber - Dark Soft}
     */
    String MC_DR_COLOR_AMBER_DARK_SOFT = "minecraft:dr.color.amber_dark_soft";

    /**
     * {@literal Amber - Light}
     */
    String MC_DR_COLOR_AMBER_LIGHT = "minecraft:dr.color.amber_light";

    /**
     * {@literal Auburn Red}
     */
    String MC_DR_COLOR_AUBURN_RED = "minecraft:dr.color.auburn_red";

    /**
     * {@literal Blue - Dark}
     */
    String MC_DR_COLOR_BLUE_DARK = "minecraft:dr.color.blue_dark";

    /**
     * {@literal Blue - Light}
     */
    String MC_DR_COLOR_BLUE_LIGHT = "minecraft:dr.color.blue_light";

    /**
     * {@literal Blue - Mid}
     */
    String MC_DR_COLOR_BLUE_MID = "minecraft:dr.color.blue_mid";

    /**
     * {@literal Blue - Vivid}
     */
    String MC_DR_COLOR_BLUE_VIVID = "minecraft:dr.color.blue_vivid";

    /**
     * {@literal Blueish Black}
     */
    String MC_DR_COLOR_BLUEISH_BLACK = "minecraft:dr.color.blueish_black";

    /**
     * {@literal Brown - Dark}
     */
    String MC_DR_COLOR_BROWN_DARK = "minecraft:dr.color.brown_dark";

    /**
     * {@literal Brown - Light}
     */
    String MC_DR_COLOR_BROWN_LIGHT = "minecraft:dr.color.brown_light";

    /**
     * {@literal Brown - Mid}
     */
    String MC_DR_COLOR_BROWN_MID = "minecraft:dr.color.brown_mid";

    /**
     * {@literal Brown – Very Dark}
     */
    String MC_DR_COLOR_BROWN_VERY_DARK = "minecraft:dr.color.brown_very_dark";

    /**
     * {@literal Color}
     */
    String MC_DR_COLOR_BUTTON_NAME = "minecraft:dr.color.button_name";

    /**
     * {@literal Copper Red}
     */
    String MC_DR_COLOR_COPPER_RED = "minecraft:dr.color.copper_red";

    /**
     * {@literal Dark Blonde}
     */
    String MC_DR_COLOR_DARK_BLONDE = "minecraft:dr.color.dark_blonde";

    /**
     * {@literal Dark Brown}
     */
    String MC_DR_COLOR_DARK_BROWN = "minecraft:dr.color.dark_brown";

    /**
     * {@literal Dark - Cool}
     */
    String MC_DR_COLOR_DARK_COOL = "minecraft:dr.color.dark_cool";

    /**
     * {@literal Dark Gray}
     */
    String MC_DR_COLOR_DARK_GRAY = "minecraft:dr.color.dark_gray";

    /**
     * {@literal Dark Red}
     */
    String MC_DR_COLOR_DARK_RED = "minecraft:dr.color.dark_red";

    /**
     * {@literal Dark - Soft}
     */
    String MC_DR_COLOR_DARK_SOFT = "minecraft:dr.color.dark_soft";

    /**
     * {@literal Dark - Warm}
     */
    String MC_DR_COLOR_DARK_WARM = "minecraft:dr.color.dark_warm";

    /**
     * {@literal Deep Black}
     */
    String MC_DR_COLOR_DEEP_BLACK = "minecraft:dr.color.deep_black";

    /**
     * {@literal Deep Brown}
     */
    String MC_DR_COLOR_DEEP_BROWN = "minecraft:dr.color.deep_brown";

    /**
     * {@literal Gray Blue}
     */
    String MC_DR_COLOR_GRAY_BLUE = "minecraft:dr.color.gray_blue";

    /**
     * {@literal Gray - Dark}
     */
    String MC_DR_COLOR_GRAY_DARK = "minecraft:dr.color.gray_dark";

    /**
     * {@literal Gray - Light}
     */
    String MC_DR_COLOR_GRAY_LIGHT = "minecraft:dr.color.gray_light";

    /**
     * {@literal Green - Bright}
     */
    String MC_DR_COLOR_GREEN_BRIGHT = "minecraft:dr.color.green_bright";

    /**
     * {@literal Green – Cool Dark}
     */
    String MC_DR_COLOR_GREEN_COOL_DARK = "minecraft:dr.color.green_cool_dark";

    /**
     * {@literal Green - Light}
     */
    String MC_DR_COLOR_GREEN_LIGHT = "minecraft:dr.color.green_light";

    /**
     * {@literal Green - Mid}
     */
    String MC_DR_COLOR_GREEN_MID = "minecraft:dr.color.green_mid";

    /**
     * {@literal Green – Warm Dark}
     */
    String MC_DR_COLOR_GREEN_WARM_DARK = "minecraft:dr.color.green_warm_dark";

    /**
     * {@literal Light Blonde}
     */
    String MC_DR_COLOR_LIGHT_BLONDE = "minecraft:dr.color.light_blonde";

    /**
     * {@literal Light Brown}
     */
    String MC_DR_COLOR_LIGHT_BROWN = "minecraft:dr.color.light_brown";

    /**
     * {@literal Light - Cool}
     */
    String MC_DR_COLOR_LIGHT_COOL = "minecraft:dr.color.light_cool";

    /**
     * {@literal Light Gray}
     */
    String MC_DR_COLOR_LIGHT_GRAY = "minecraft:dr.color.light_gray";

    /**
     * {@literal Light - Medium}
     */
    String MC_DR_COLOR_LIGHT_MEDIUM = "minecraft:dr.color.light_medium";

    /**
     * {@literal Light Red}
     */
    String MC_DR_COLOR_LIGHT_RED = "minecraft:dr.color.light_red";

    /**
     * {@literal Light - Warm}
     */
    String MC_DR_COLOR_LIGHT_WARM = "minecraft:dr.color.light_warm";

    /**
     * {@literal MC Black}
     */
    String MC_DR_COLOR_MC_BLACK = "minecraft:dr.color.mc_black";

    /**
     * {@literal Medium Blonde}
     */
    String MC_DR_COLOR_MEDIUM_BLONDE = "minecraft:dr.color.medium_blonde";

    /**
     * {@literal Medium Brown}
     */
    String MC_DR_COLOR_MEDIUM_BROWN = "minecraft:dr.color.medium_brown";

    /**
     * {@literal Medium - Cool}
     */
    String MC_DR_COLOR_MEDIUM_COOL = "minecraft:dr.color.medium_cool";

    /**
     * {@literal Medium Dark - Cool}
     */
    String MC_DR_COLOR_MEDIUM_DARK_COOL = "minecraft:dr.color.medium_dark_cool";

    /**
     * {@literal Medium Dark - Soft}
     */
    String MC_DR_COLOR_MEDIUM_DARK_SOFT = "minecraft:dr.color.medium_dark_soft";

    /**
     * {@literal Medium Dark - Warm}
     */
    String MC_DR_COLOR_MEDIUM_DARK_WARM = "minecraft:dr.color.medium_dark_warm";

    /**
     * {@literal Medium Light Blonde}
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_BLONDE = "minecraft:dr.color.medium_light_blonde";

    /**
     * {@literal Medium Light - Cool}
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_COOL = "minecraft:dr.color.medium_light_cool";

    /**
     * {@literal Medium Light - Deep}
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_DEEP = "minecraft:dr.color.medium_light_deep";

    /**
     * {@literal Medium Light - Vibrant}
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_VIBRANT = "minecraft:dr.color.medium_light_vibrant";

    /**
     * {@literal Medium Light - Warm}
     */
    String MC_DR_COLOR_MEDIUM_LIGHT_WARM = "minecraft:dr.color.medium_light_warm";

    /**
     * {@literal Medium - Soft}
     */
    String MC_DR_COLOR_MEDIUM_SOFT = "minecraft:dr.color.medium_soft";

    /**
     * {@literal Medium - Warm}
     */
    String MC_DR_COLOR_MEDIUM_WARM = "minecraft:dr.color.medium_warm";

    /**
     * {@literal Minecraft Black}
     */
    String MC_DR_COLOR_MINECRAFT_BLACK = "minecraft:dr.color.minecraft_black";

    /**
     * {@literal Minecraft Blue}
     */
    String MC_DR_COLOR_MINECRAFT_BLUE = "minecraft:dr.color.minecraft_blue";

    /**
     * {@literal Minecraft Cyan}
     */
    String MC_DR_COLOR_MINECRAFT_CYAN = "minecraft:dr.color.minecraft_cyan";

    /**
     * {@literal Minecraft Gray}
     */
    String MC_DR_COLOR_MINECRAFT_GRAY = "minecraft:dr.color.minecraft_gray";

    /**
     * {@literal Minecraft Green}
     */
    String MC_DR_COLOR_MINECRAFT_GREEN = "minecraft:dr.color.minecraft_green";

    /**
     * {@literal Minecraft Light Blue}
     */
    String MC_DR_COLOR_MINECRAFT_LIGHT_BLUE = "minecraft:dr.color.minecraft_light_blue";

    /**
     * {@literal Minecraft Light Gray}
     */
    String MC_DR_COLOR_MINECRAFT_LIGHT_GRAY = "minecraft:dr.color.minecraft_light_gray";

    /**
     * {@literal Minecraft Lime Green}
     */
    String MC_DR_COLOR_MINECRAFT_LIME_GREEN = "minecraft:dr.color.minecraft_lime_green";

    /**
     * {@literal Minecraft Magenta}
     */
    String MC_DR_COLOR_MINECRAFT_MAGENTA = "minecraft:dr.color.minecraft_magenta";

    /**
     * {@literal Minecraft Orange}
     */
    String MC_DR_COLOR_MINECRAFT_ORANGE = "minecraft:dr.color.minecraft_orange";

    /**
     * {@literal Minecraft Pink}
     */
    String MC_DR_COLOR_MINECRAFT_PINK = "minecraft:dr.color.minecraft_pink";

    /**
     * {@literal Minecraft Purple}
     */
    String MC_DR_COLOR_MINECRAFT_PURPLE = "minecraft:dr.color.minecraft_purple";

    /**
     * {@literal Minecraft Red}
     */
    String MC_DR_COLOR_MINECRAFT_RED = "minecraft:dr.color.minecraft_red";

    /**
     * {@literal Minecraft White}
     */
    String MC_DR_COLOR_MINECRAFT_WHITE = "minecraft:dr.color.minecraft_white";

    /**
     * {@literal Minecraft Yellow}
     */
    String MC_DR_COLOR_MINECRAFT_YELLOW = "minecraft:dr.color.minecraft_yellow";

    /**
     * {@literal Muted Gray}
     */
    String MC_DR_COLOR_MUTED_GRAY = "minecraft:dr.color.muted_gray";

    /**
     * {@literal Purpleish Black}
     */
    String MC_DR_COLOR_PURPLEISH_BLACK = "minecraft:dr.color.purpleish_black";

    /**
     * {@literal Red - Dark}
     */
    String MC_DR_COLOR_RED_DARK = "minecraft:dr.color.red_dark";

    /**
     * {@literal Reddish Black}
     */
    String MC_DR_COLOR_REDDISH_BLACK = "minecraft:dr.color.reddish_black";

    /**
     * {@literal Soft Black}
     */
    String MC_DR_COLOR_SOFT_BLACK = "minecraft:dr.color.soft_black";

    /**
     * {@literal Soft Blonde}
     */
    String MC_DR_COLOR_SOFT_BLONDE = "minecraft:dr.color.soft_blonde";

    /**
     * {@literal Soft Magenta}
     */
    String MC_DR_COLOR_SOFT_MAGENTA = "minecraft:dr.color.soft_magenta";

    /**
     * {@literal Very Dark - Cool}
     */
    String MC_DR_COLOR_VERY_DARK_COOL = "minecraft:dr.color.very_dark_cool";

    /**
     * {@literal Very Dark - Warm}
     */
    String MC_DR_COLOR_VERY_DARK_WARM = "minecraft:dr.color.very_dark_warm";

    /**
     * {@literal Very Light Blonde}
     */
    String MC_DR_COLOR_VERY_LIGHT_BLONDE = "minecraft:dr.color.very_light_blonde";

    /**
     * {@literal Very Light - Cool}
     */
    String MC_DR_COLOR_VERY_LIGHT_COOL = "minecraft:dr.color.very_light_cool";

    /**
     * {@literal Very Light - Soft}
     */
    String MC_DR_COLOR_VERY_LIGHT_SOFT = "minecraft:dr.color.very_light_soft";

    /**
     * {@literal Very Light - Warm}
     */
    String MC_DR_COLOR_VERY_LIGHT_WARM = "minecraft:dr.color.very_light_warm";

    /**
     * {@literal Vibrant Orange}
     */
    String MC_DR_COLOR_VIBRANT_ORANGE = "minecraft:dr.color.vibrant_orange";

    /**
     * {@literal White}
     */
    String MC_DR_COLOR_WHITE = "minecraft:dr.color.white";

    /**
     * {@literal White - Deep}
     */
    String MC_DR_COLOR_WHITE_DEEP = "minecraft:dr.color.white_deep";

    /**
     * {@literal White - Light}
     */
    String MC_DR_COLOR_WHITE_LIGHT = "minecraft:dr.color.white_light";

    /**
     * {@literal White - Mid}
     */
    String MC_DR_COLOR_WHITE_MID = "minecraft:dr.color.white_mid";

    /**
     * {@literal Yellow Green}
     */
    String MC_DR_COLOR_YELLOW_GREEN = "minecraft:dr.color.yellow_green";

    /**
     * {@literal Deep Linking Failed: Unable to open offer}
     */
    String MC_DR_DEEP_LINK_FAIL_DRESSING_ROOM = "minecraft:dr.deep_link_fail.dressing_room";

    /**
     * {@literal Deep Linking Failed: Unable to load profile}
     */
    String MC_DR_DEEP_LINK_FAIL_PROFILE = "minecraft:dr.deep_link_fail.profile";

    /**
     * {@literal Alex's Pants}
     */
    String MC_DR_DEFAULT_ALEX_BOTTOM = "minecraft:dr.default.alex.bottom";

    /**
     * {@literal Alex's Hair}
     */
    String MC_DR_DEFAULT_ALEX_HAIR = "minecraft:dr.default.alex.hair";

    /**
     * {@literal Alex's Mouth}
     */
    String MC_DR_DEFAULT_ALEX_MOUTH = "minecraft:dr.default.alex.mouth";

    /**
     * {@literal Alex's Shoes}
     */
    String MC_DR_DEFAULT_ALEX_SHOES = "minecraft:dr.default.alex.shoes";

    /**
     * {@literal Alex}
     */
    String MC_DR_DEFAULT_ALEX_SKIN = "minecraft:dr.default.alex.skin";

    /**
     * {@literal Alex's Shirt}
     */
    String MC_DR_DEFAULT_ALEX_TOP = "minecraft:dr.default.alex.top";

    /**
     * {@literal Simple Clap}
     */
    String MC_DR_DEFAULT_EMOTE_BASICCLAP = "minecraft:dr.default.emote.basicClap";

    /**
     * {@literal Follow Me}
     */
    String MC_DR_DEFAULT_EMOTE_COMMUNICATIONFOLLOWME = "minecraft:dr.default.emote.communicationFollowMe";

    /**
     * {@literal Over There!}
     */
    String MC_DR_DEFAULT_EMOTE_COMMUNICATIONPOINT = "minecraft:dr.default.emote.communicationPoint";

    /**
     * {@literal Wave}
     */
    String MC_DR_DEFAULT_EMOTE_OVERHEADWAVE = "minecraft:dr.default.emote.overheadWave";

    /**
     * {@literal Standard Stare}
     */
    String MC_DR_DEFAULT_EYES = "minecraft:dr.default.eyes";

    /**
     * {@literal Steve's Pants}
     */
    String MC_DR_DEFAULT_STEVE_BOTTOM = "minecraft:dr.default.steve.bottom";

    /**
     * {@literal Steve's Hair}
     */
    String MC_DR_DEFAULT_STEVE_HAIR = "minecraft:dr.default.steve.hair";

    /**
     * {@literal Steve's Mouth}
     */
    String MC_DR_DEFAULT_STEVE_MOUTH = "minecraft:dr.default.steve.mouth";

    /**
     * {@literal Steve's Shoes}
     */
    String MC_DR_DEFAULT_STEVE_SHOES = "minecraft:dr.default.steve.shoes";

    /**
     * {@literal Steve}
     */
    String MC_DR_DEFAULT_STEVE_SKIN = "minecraft:dr.default.steve.skin";

    /**
     * {@literal Steve's Shirt}
     */
    String MC_DR_DEFAULT_STEVE_TOP = "minecraft:dr.default.steve.top";

    /**
     * {@literal Default}
     */
    String MC_DR_DEFAULT_PIECE_BUTTON_TEXT = "minecraft:dr.default_piece_button_text";

    /**
     * {@literal Assign "%s" to a Slot}
     */
    String MC_DR_EMOTES_ASSIGNMENT = "minecraft:dr.emotes.assignment";

    /**
     * {@literal Key %d}
     */
    String MC_DR_EMOTES_KEY = "minecraft:dr.emotes.key";

    /**
     * {@literal Play Again}
     */
    String MC_DR_EMOTES_PLAY_AGAIN = "minecraft:dr.emotes.play_again";

    /**
     * {@literal Replace}
     */
    String MC_DR_EMOTES_REPLACE = "minecraft:dr.emotes.replace";

    /**
     * {@literal Emote %d}
     */
    String MC_DR_EMOTES_SLOT = "minecraft:dr.emotes.slot";

    /**
     * {@literal Equip}
     */
    String MC_DR_EQUIP_PIECE = "minecraft:dr.equip_piece";

    /**
     * {@literal Eyebrows}
     */
    String MC_DR_EYEBROWS_COLOR = "minecraft:dr.eyebrows_color";

    /**
     * {@literal Facial Hair}
     */
    String MC_DR_FACIAL_HAIR_COLOR = "minecraft:dr.facial_hair_color";

    /**
     * {@literal Go to Settings}
     */
    String MC_DR_GO_TO_PROFILE_SETTINGS_TEXT = "minecraft:dr.go_to_profile_settings_text";

    /**
     * {@literal Hair}
     */
    String MC_DR_HAIR_COLOR = "minecraft:dr.hair_color";

    /**
     * {@literal Emotes}
     */
    String MC_DR_HEADER_ANIMATION = "minecraft:dr.header.animation";

    /**
     * {@literal Capes}
     */
    String MC_DR_HEADER_CAPES = "minecraft:dr.header.capes";

    /**
     * {@literal %s Featured  }
     */
    String MC_DR_HEADER_CATEGORYFEATUREDLIST = "minecraft:dr.header.categoryFeaturedList";

    /**
     * {@literal Classic Skins}
     */
    String MC_DR_HEADER_CLASSIC_SKINS = "minecraft:dr.header.classic_skins";

    /**
     * {@literal Color Picker}
     */
    String MC_DR_HEADER_COLORPICKER = "minecraft:dr.header.colorPicker";

    /**
     * {@literal All by %s}
     */
    String MC_DR_HEADER_CREATOR_PIECE_LIST = "minecraft:dr.header.creator_piece_list";

    /**
     * {@literal Character Creator}
     */
    String MC_DR_HEADER_CUSTOMIZATION = "minecraft:dr.header.customization";

    /**
     * {@literal Featured Items}
     */
    String MC_DR_HEADER_FEATURED = "minecraft:dr.header.featured";

    /**
     * {@literal Featured Skins}
     */
    String MC_DR_HEADER_FEATURED_SKINS = "minecraft:dr.header.featured_skins";

    /**
     * {@literal Preview Appearance}
     */
    String MC_DR_HEADER_PREVIEWAPPEARANCE = "minecraft:dr.header.previewAppearance";

    /**
     * {@literal Dressing Room Search}
     */
    String MC_DR_HEADER_SEARCH_HOME = "minecraft:dr.header.search.home";

    /**
     * {@literal Iris}
     */
    String MC_DR_IRIS_COLOR = "minecraft:dr.iris_color";

    /**
     * {@literal This is your current Character}
     */
    String MC_DR_LABEL_CURRENT_PERSONA = "minecraft:dr.label.current_persona";

    /**
     * {@literal This is your current Skin}
     */
    String MC_DR_LABEL_CURRENT_SKIN = "minecraft:dr.label.current_skin";

    /**
     * {@literal Left Arm}
     */
    String MC_DR_LEFT_ARM = "minecraft:dr.left_arm";

    /**
     * {@literal Left Leg}
     */
    String MC_DR_LEFT_LEG = "minecraft:dr.left_leg";

    /**
     * {@literal Limb Selection}
     */
    String MC_DR_LIMB_SWITCHER_DEFAULT_TITLE = "minecraft:dr.limb_switcher_default_title";

    /**
     * {@literal Loading...}
     */
    String MC_DR_LOADING = "minecraft:dr.loading";

    /**
     * {@literal Get More Skins}
     */
    String MC_DR_MARKETPLACE_GET_MORE_SKINS = "minecraft:dr.marketplace.get_more_skins";

    /**
     * {@literal Character and Classic Skin Differences}
     */
    String MC_DR_MODAL_DIFFERENCE_INFORMATION_TITLE = "minecraft:dr.modal.difference_information_title";

    /**
     * {@literal The current character will be deleted. Are you sure you want to continue?}
     */
    String MC_DR_MODAL_PERSONA_DELETE_CONFIRM = "minecraft:dr.modal.persona_delete_confirm";

    /**
     * {@literal Delete Character?}
     */
    String MC_DR_MODAL_PERSONA_DELETE_CONFIRM_TITLE = "minecraft:dr.modal.persona_delete_confirm_title";

    /**
     * {@literal This content is available in most geographic regions, but restricted in a few.}
     */
    String MC_DR_MODAL_RESTRICTED_CONTENT_TEXT = "minecraft:dr.modal.restricted_content_text";

    /**
     * {@literal Restriction warning}
     */
    String MC_DR_MODAL_RESTRICTED_CONTENT_TITLE = "minecraft:dr.modal.restricted_content_title";

    /**
     * {@literal Mouth}
     */
    String MC_DR_MOUTH_COLOR = "minecraft:dr.mouth_color";

    /**
     * {@literal None}
     */
    String MC_DR_NONE_BUTTON_TEXT = "minecraft:dr.none_button_text";

    /**
     * {@literal Clear All}
     */
    String MC_DR_NONE_EMOTE_BUTTON_TEXT = "minecraft:dr.none_emote_button_text";

    /**
     * {@literal Failed to load character %s}
     */
    String MC_DR_NOTIFICATION_CHARACTER_FAILED = "minecraft:dr.notification.character_failed";

    /**
     * {@literal Failed to load character %s. You can reload it from your Profile.}
     */
    String MC_DR_NOTIFICATION_CHARACTER_FAILED_START = "minecraft:dr.notification.character_failed_start";

    /**
     * {@literal New Skin has been created}
     */
    String MC_DR_NOTIFICATION_CLASSIC_SKIN_CREATED_NEW = "minecraft:dr.notification.classic_skin_created_new";

    /**
     * {@literal Skin blocked from multiplayer for violating Community Standards.}
     */
    String MC_DR_NOTIFICATION_CUSTOM_BANNED = "minecraft:dr.notification.custom_banned";

    /**
     * {@literal You just equipped %s}
     */
    String MC_DR_NOTIFICATION_EQUIPPED_CLASSIC_SKIN = "minecraft:dr.notification.equipped_classic_skin";

    /**
     * {@literal You just equipped the %s}
     */
    String MC_DR_NOTIFICATION_EQUIPPED_PIECE = "minecraft:dr.notification.equipped_piece";

    /**
     * {@literal Failed to select item}
     */
    String MC_DR_NOTIFICATION_ITEM_SELECTION_FAILED = "minecraft:dr.notification.item_selection_failed";

    /**
     * {@literal The Character has been created}
     */
    String MC_DR_NOTIFICATION_PERSONA_CREATE = "minecraft:dr.notification.persona_create";

    /**
     * {@literal New Character has been created}
     */
    String MC_DR_NOTIFICATION_PERSONA_CREATE_NEW = "minecraft:dr.notification.persona_create_new";

    /**
     * {@literal The Character has been deleted}
     */
    String MC_DR_NOTIFICATION_PERSONA_DELETE = "minecraft:dr.notification.persona_delete";

    /**
     * {@literal The Character has been saved}
     */
    String MC_DR_NOTIFICATION_PERSONA_SAVE = "minecraft:dr.notification.persona_save";

    /**
     * {@literal A local or platform skin could not be loaded.}
     */
    String MC_DR_NOTIFICATION_SKIN_NOT_FOUND = "minecraft:dr.notification.skin_not_found";

    /**
     * {@literal Switching to Character Creator}
     */
    String MC_DR_NOTIFICATION_TO_PERSONA = "minecraft:dr.notification.to_persona";

    /**
     * {@literal Switching to Classic Skins}
     */
    String MC_DR_NOTIFICATION_TO_SKINS = "minecraft:dr.notification.to_skins";

    /**
     * {@literal Primary}
     */
    String MC_DR_PRIMARY_CHANNEL = "minecraft:dr.primary_channel";

    /**
     * {@literal Don't show this again}
     */
    String MC_DR_PROMPT_DO_NOT_SHOW_AGAIN = "minecraft:dr.prompt.do_not_show_again";

    /**
     * {@literal You are switching over to the character creator in this slot. Character creator allows you to build a character in Minecraft item by item. Your classic skin will be saved as a back up in this slot if you want to switch back later.}
     */
    String MC_DR_PROMPT_SWITCHING_TO_PERSONA_DETAIL = "minecraft:dr.prompt.switching_to_persona_detail";

    /**
     * {@literal Switch to Character Creator}
     */
    String MC_DR_PROMPT_SWITCHING_TO_PERSONA_TITLE = "minecraft:dr.prompt.switching_to_persona_title";

    /**
     * {@literal You are switching over to a classic skin in this slot.  Classic skins are applied to the full character and cannot be customized. Your character creator look will be saved as back up in this slot if you want to switch back later.}
     */
    String MC_DR_PROMPT_SWITCHING_TO_SKIN_DETAIL = "minecraft:dr.prompt.switching_to_skin_detail";

    /**
     * {@literal Switch to Classic Skins}
     */
    String MC_DR_PROMPT_SWITCHING_TO_SKIN_TITLE = "minecraft:dr.prompt.switching_to_skin_title";

    /**
     * {@literal Rarity}
     */
    String MC_DR_RARITY = "minecraft:dr.rarity";

    /**
     * {@literal Common}
     */
    String MC_DR_RARITY_COMMON = "minecraft:dr.rarity.common";

    /**
     * {@literal Epic}
     */
    String MC_DR_RARITY_EPIC = "minecraft:dr.rarity.epic";

    /**
     * {@literal Legendary}
     */
    String MC_DR_RARITY_LEGENDARY = "minecraft:dr.rarity.legendary";

    /**
     * {@literal Rare}
     */
    String MC_DR_RARITY_RARE = "minecraft:dr.rarity.rare";

    /**
     * {@literal Uncommon}
     */
    String MC_DR_RARITY_UNCOMMON = "minecraft:dr.rarity.uncommon";

    /**
     * {@literal Right Arm}
     */
    String MC_DR_RIGHT_ARM = "minecraft:dr.right_arm";

    /**
     * {@literal Right Leg}
     */
    String MC_DR_RIGHT_LEG = "minecraft:dr.right_leg";

    /**
     * {@literal %s}
     */
    String MC_DR_RIGHT_SIDE_CATEGORY_TITLE = "minecraft:dr.right_side.category_title";

    /**
     * {@literal Sclera}
     */
    String MC_DR_SCLERA_COLOR = "minecraft:dr.sclera_color";

    /**
     * {@literal Secondary}
     */
    String MC_DR_SECONDARY_CHANNEL = "minecraft:dr.secondary_channel";

    /**
     * {@literal Base}
     */
    String MC_DR_SKIN_COLOR = "minecraft:dr.skin_color";

    /**
     * {@literal Zooming}
     */
    String MC_DR_ZOOMING = "minecraft:dr.zooming";

    /**
     * {@literal Skin Color}
     */
    String MC_DRESSINGROOM_SKIN_COLOR_PICKER_TITLE = "minecraft:dressingRoom.skin_color_picker_title";

    /**
     * {@literal coming soon}
     */
    String MC_EDITORMODE_TEXT = "minecraft:editorMode.text";

    /**
     * {@literal Bad Omen}
     */
    String MC_EFFECT_BADOMEN = "minecraft:effect.badOmen";

    /**
     * {@literal  Breath of the Nautilus}
     */
    String MC_EFFECT_CUSTOMNAUTILUS = "minecraft:effect.customNautilus";

    /**
     * {@literal Darkness}
     */
    String MC_EFFECT_DARKNESS = "minecraft:effect.darkness";

    /**
     * {@literal ∞}
     */
    String MC_EFFECT_DURATION_INFINITE = "minecraft:effect.duration.infinite";

    /**
     * {@literal Infested}
     */
    String MC_EFFECT_INFESTED = "minecraft:effect.infested";

    /**
     * {@literal Oozing}
     */
    String MC_EFFECT_OOZING = "minecraft:effect.oozing";

    /**
     * {@literal Raid Omen}
     */
    String MC_EFFECT_RAID_OMEN = "minecraft:effect.raid_omen";

    /**
     * {@literal Trial Omen}
     */
    String MC_EFFECT_TRIAL_OMEN = "minecraft:effect.trial_omen";

    /**
     * {@literal Hero of the Village}
     */
    String MC_EFFECT_VILLAGEHERO = "minecraft:effect.villageHero";

    /**
     * {@literal Weaving}
     */
    String MC_EFFECT_WEAVING = "minecraft:effect.weaving";

    /**
     * {@literal Wind Charged}
     */
    String MC_EFFECT_WIND_CHARGED = "minecraft:effect.wind_charged";

    /**
     * {@literal Press to Select}
     */
    String MC_EMOTE_WHEEL_GAMEPAD_HELPER_SELECT = "minecraft:emote_wheel.gamepad_helper.select";

    /**
     * {@literal Change Emotes}
     */
    String MC_EMOTES_CHANGE_EMOTES = "minecraft:emotes.change_emotes";

    /**
     * {@literal Use hot Key or Mouse to activate Emote}
     */
    String MC_EMOTES_EMOTES_INSTRUCTIONS_GENERAL = "minecraft:emotes.emotes_instructions_general";

    /**
     * {@literal Execute}
     */
    String MC_EMOTES_EXECUTE = "minecraft:emotes.execute";

    /**
     * {@literal %s Exit}
     */
    String MC_EMOTES_EXIT_GAMEPAD = "minecraft:emotes.exit_gamepad";

    /**
     * {@literal Emotes}
     */
    String MC_EMOTES_HEADER_TEXT = "minecraft:emotes.header_text";

    /**
     * {@literal To quickly play your emotes in game, you can always press and hold %s for a quick menu}
     */
    String MC_EMOTES_INSTRUCTIONS_GAMEPAD = "minecraft:emotes.instructions_gamepad";

    /**
     * {@literal Use Hotkey or Mouse to activate Emote}
     */
    String MC_EMOTES_INSTRUCTIONS_KEYBOARD = "minecraft:emotes.instructions_keyboard";

    /**
     * {@literal Tap emote to activate}
     */
    String MC_EMOTES_INSTRUCTIONS_TOUCH = "minecraft:emotes.instructions_touch";

    /**
     * {@literal Power}
     */
    String MC_ENCHANTMENT_ARROWDAMAGE = "minecraft:enchantment.arrowDamage";

    /**
     * {@literal Flame}
     */
    String MC_ENCHANTMENT_ARROWFIRE = "minecraft:enchantment.arrowFire";

    /**
     * {@literal Infinity}
     */
    String MC_ENCHANTMENT_ARROWINFINITE = "minecraft:enchantment.arrowInfinite";

    /**
     * {@literal Punch}
     */
    String MC_ENCHANTMENT_ARROWKNOCKBACK = "minecraft:enchantment.arrowKnockback";

    /**
     * {@literal Multishot}
     */
    String MC_ENCHANTMENT_CROSSBOWMULTISHOT = "minecraft:enchantment.crossbowMultishot";

    /**
     * {@literal Piercing}
     */
    String MC_ENCHANTMENT_CROSSBOWPIERCING = "minecraft:enchantment.crossbowPiercing";

    /**
     * {@literal Quick Charge}
     */
    String MC_ENCHANTMENT_CROSSBOWQUICKCHARGE = "minecraft:enchantment.crossbowQuickCharge";

    /**
     * {@literal Curse of Binding}
     */
    String MC_ENCHANTMENT_CURSE_BINDING = "minecraft:enchantment.curse.binding";

    /**
     * {@literal Curse of Vanishing}
     */
    String MC_ENCHANTMENT_CURSE_VANISHING = "minecraft:enchantment.curse.vanishing";

    /**
     * {@literal Sharpness}
     */
    String MC_ENCHANTMENT_DAMAGE_ALL = "minecraft:enchantment.damage.all";

    /**
     * {@literal Bane of Arthropods}
     */
    String MC_ENCHANTMENT_DAMAGE_ARTHROPODS = "minecraft:enchantment.damage.arthropods";

    /**
     * {@literal Smite}
     */
    String MC_ENCHANTMENT_DAMAGE_UNDEAD = "minecraft:enchantment.damage.undead";

    /**
     * {@literal Efficiency}
     */
    String MC_ENCHANTMENT_DIGGING = "minecraft:enchantment.digging";

    /**
     * {@literal Unbreaking}
     */
    String MC_ENCHANTMENT_DURABILITY = "minecraft:enchantment.durability";

    /**
     * {@literal Fire Aspect}
     */
    String MC_ENCHANTMENT_FIRE = "minecraft:enchantment.fire";

    /**
     * {@literal Lure}
     */
    String MC_ENCHANTMENT_FISHINGSPEED = "minecraft:enchantment.fishingSpeed";

    /**
     * {@literal Frost Walker}
     */
    String MC_ENCHANTMENT_FROSTWALKER = "minecraft:enchantment.frostwalker";

    /**
     * {@literal Breach}
     */
    String MC_ENCHANTMENT_HEAVY_WEAPON_BREACH = "minecraft:enchantment.heavy_weapon.breach";

    /**
     * {@literal Density}
     */
    String MC_ENCHANTMENT_HEAVY_WEAPON_DENSITY = "minecraft:enchantment.heavy_weapon.density";

    /**
     * {@literal Wind Burst}
     */
    String MC_ENCHANTMENT_HEAVY_WEAPON_WINDBURST = "minecraft:enchantment.heavy_weapon.windburst";

    /**
     * {@literal Knockback}
     */
    String MC_ENCHANTMENT_KNOCKBACK = "minecraft:enchantment.knockback";

    /**
     * {@literal I}
     */
    String MC_ENCHANTMENT_LEVEL_1 = "minecraft:enchantment.level.1";

    /**
     * {@literal X}
     */
    String MC_ENCHANTMENT_LEVEL_10 = "minecraft:enchantment.level.10";

    /**
     * {@literal II}
     */
    String MC_ENCHANTMENT_LEVEL_2 = "minecraft:enchantment.level.2";

    /**
     * {@literal III}
     */
    String MC_ENCHANTMENT_LEVEL_3 = "minecraft:enchantment.level.3";

    /**
     * {@literal IV}
     */
    String MC_ENCHANTMENT_LEVEL_4 = "minecraft:enchantment.level.4";

    /**
     * {@literal V}
     */
    String MC_ENCHANTMENT_LEVEL_5 = "minecraft:enchantment.level.5";

    /**
     * {@literal VI}
     */
    String MC_ENCHANTMENT_LEVEL_6 = "minecraft:enchantment.level.6";

    /**
     * {@literal VII}
     */
    String MC_ENCHANTMENT_LEVEL_7 = "minecraft:enchantment.level.7";

    /**
     * {@literal VIII}
     */
    String MC_ENCHANTMENT_LEVEL_8 = "minecraft:enchantment.level.8";

    /**
     * {@literal IX}
     */
    String MC_ENCHANTMENT_LEVEL_9 = "minecraft:enchantment.level.9";

    /**
     * {@literal Looting}
     */
    String MC_ENCHANTMENT_LOOTBONUS = "minecraft:enchantment.lootBonus";

    /**
     * {@literal Fortune}
     */
    String MC_ENCHANTMENT_LOOTBONUSDIGGER = "minecraft:enchantment.lootBonusDigger";

    /**
     * {@literal Luck of the Sea}
     */
    String MC_ENCHANTMENT_LOOTBONUSFISHING = "minecraft:enchantment.lootBonusFishing";

    /**
     * {@literal Lunge}
     */
    String MC_ENCHANTMENT_LUNGE = "minecraft:enchantment.lunge";

    /**
     * {@literal Mending}
     */
    String MC_ENCHANTMENT_MENDING = "minecraft:enchantment.mending";

    /**
     * {@literal Respiration}
     */
    String MC_ENCHANTMENT_OXYGEN = "minecraft:enchantment.oxygen";

    /**
     * {@literal Protection}
     */
    String MC_ENCHANTMENT_PROTECT_ALL = "minecraft:enchantment.protect.all";

    /**
     * {@literal Blast Protection}
     */
    String MC_ENCHANTMENT_PROTECT_EXPLOSION = "minecraft:enchantment.protect.explosion";

    /**
     * {@literal Feather Falling}
     */
    String MC_ENCHANTMENT_PROTECT_FALL = "minecraft:enchantment.protect.fall";

    /**
     * {@literal Fire Protection}
     */
    String MC_ENCHANTMENT_PROTECT_FIRE = "minecraft:enchantment.protect.fire";

    /**
     * {@literal Projectile Protection}
     */
    String MC_ENCHANTMENT_PROTECT_PROJECTILE = "minecraft:enchantment.protect.projectile";

    /**
     * {@literal Soul Speed}
     */
    String MC_ENCHANTMENT_SOUL_SPEED = "minecraft:enchantment.soul_speed";

    /**
     * {@literal Swift Sneak}
     */
    String MC_ENCHANTMENT_SWIFT_SNEAK = "minecraft:enchantment.swift_sneak";

    /**
     * {@literal Thorns}
     */
    String MC_ENCHANTMENT_THORNS = "minecraft:enchantment.thorns";

    /**
     * {@literal Channeling}
     */
    String MC_ENCHANTMENT_TRIDENTCHANNELING = "minecraft:enchantment.tridentChanneling";

    /**
     * {@literal Impaling}
     */
    String MC_ENCHANTMENT_TRIDENTIMPALING = "minecraft:enchantment.tridentImpaling";

    /**
     * {@literal Loyalty}
     */
    String MC_ENCHANTMENT_TRIDENTLOYALTY = "minecraft:enchantment.tridentLoyalty";

    /**
     * {@literal Riptide}
     */
    String MC_ENCHANTMENT_TRIDENTRIPTIDE = "minecraft:enchantment.tridentRiptide";

    /**
     * {@literal Silk Touch}
     */
    String MC_ENCHANTMENT_UNTOUCHING = "minecraft:enchantment.untouching";

    /**
     * {@literal Depth Strider}
     */
    String MC_ENCHANTMENT_WATERWALKER = "minecraft:enchantment.waterWalker";

    /**
     * {@literal Aqua Affinity}
     */
    String MC_ENCHANTMENT_WATERWORKER = "minecraft:enchantment.waterWorker";

    /**
     * {@literal Allay}
     */
    String MC_ENTITY_ALLAY_NAME = "minecraft:entity.allay.name";

    /**
     * {@literal Area Effect Cloud}
     */
    String MC_ENTITY_AREA_EFFECT_CLOUD_NAME = "minecraft:entity.area_effect_cloud.name";

    /**
     * {@literal Armadillo}
     */
    String MC_ENTITY_ARMADILLO_NAME = "minecraft:entity.armadillo.name";

    /**
     * {@literal Armor Stand}
     */
    String MC_ENTITY_ARMOR_STAND_NAME = "minecraft:entity.armor_stand.name";

    /**
     * {@literal Arrow}
     */
    String MC_ENTITY_ARROW_NAME = "minecraft:entity.arrow.name";

    /**
     * {@literal Axolotl}
     */
    String MC_ENTITY_AXOLOTL_NAME = "minecraft:entity.axolotl.name";

    /**
     * {@literal Bat}
     */
    String MC_ENTITY_BAT_NAME = "minecraft:entity.bat.name";

    /**
     * {@literal Bee}
     */
    String MC_ENTITY_BEE_NAME = "minecraft:entity.bee.name";

    /**
     * {@literal Blaze}
     */
    String MC_ENTITY_BLAZE_NAME = "minecraft:entity.blaze.name";

    /**
     * {@literal Boat}
     */
    String MC_ENTITY_BOAT_NAME = "minecraft:entity.boat.name";

    /**
     * {@literal Bogged}
     */
    String MC_ENTITY_BOGGED_NAME = "minecraft:entity.bogged.name";

    /**
     * {@literal Breeze}
     */
    String MC_ENTITY_BREEZE_NAME = "minecraft:entity.breeze.name";

    /**
     * {@literal Wind Charge}
     */
    String MC_ENTITY_BREEZE_WIND_CHARGE_PROJECTILE_NAME = "minecraft:entity.breeze_wind_charge_projectile.name";

    /**
     * {@literal Camel}
     */
    String MC_ENTITY_CAMEL_NAME = "minecraft:entity.camel.name";

    /**
     * {@literal Camel Husk}
     */
    String MC_ENTITY_CAMEL_HUSK_NAME = "minecraft:entity.camel_husk.name";

    /**
     * {@literal Cat}
     */
    String MC_ENTITY_CAT_NAME = "minecraft:entity.cat.name";

    /**
     * {@literal Cave Spider}
     */
    String MC_ENTITY_CAVE_SPIDER_NAME = "minecraft:entity.cave_spider.name";

    /**
     * {@literal Boat with Chest}
     */
    String MC_ENTITY_CHEST_BOAT_NAME = "minecraft:entity.chest_boat.name";

    /**
     * {@literal Minecart with Chest}
     */
    String MC_ENTITY_CHEST_MINECART_NAME = "minecraft:entity.chest_minecart.name";

    /**
     * {@literal Raft with Chest}
     */
    String MC_ENTITY_CHEST_RAFT_NAME = "minecraft:entity.chest_raft.name";

    /**
     * {@literal Chicken}
     */
    String MC_ENTITY_CHICKEN_NAME = "minecraft:entity.chicken.name";

    /**
     * {@literal Cod}
     */
    String MC_ENTITY_COD_NAME = "minecraft:entity.cod.name";

    /**
     * {@literal Minecart with Command Block}
     */
    String MC_ENTITY_COMMAND_BLOCK_MINECART_NAME = "minecraft:entity.command_block_minecart.name";

    /**
     * {@literal Copper Golem}
     */
    String MC_ENTITY_COPPER_GOLEM_NAME = "minecraft:entity.copper_golem.name";

    /**
     * {@literal Cow}
     */
    String MC_ENTITY_COW_NAME = "minecraft:entity.cow.name";

    /**
     * {@literal Creaking}
     */
    String MC_ENTITY_CREAKING_NAME = "minecraft:entity.creaking.name";

    /**
     * {@literal Creeper}
     */
    String MC_ENTITY_CREEPER_NAME = "minecraft:entity.creeper.name";

    /**
     * {@literal Dolphin}
     */
    String MC_ENTITY_DOLPHIN_NAME = "minecraft:entity.dolphin.name";

    /**
     * {@literal Donkey}
     */
    String MC_ENTITY_DONKEY_NAME = "minecraft:entity.donkey.name";

    /**
     * {@literal Dragon Fireball}
     */
    String MC_ENTITY_DRAGON_FIREBALL_NAME = "minecraft:entity.dragon_fireball.name";

    /**
     * {@literal Drowned}
     */
    String MC_ENTITY_DROWNED_NAME = "minecraft:entity.drowned.name";

    /**
     * {@literal Egg}
     */
    String MC_ENTITY_EGG_NAME = "minecraft:entity.egg.name";

    /**
     * {@literal Elder Guardian}
     */
    String MC_ENTITY_ELDER_GUARDIAN_NAME = "minecraft:entity.elder_guardian.name";

    /**
     * {@literal Ender Crystal}
     */
    String MC_ENTITY_ENDER_CRYSTAL_NAME = "minecraft:entity.ender_crystal.name";

    /**
     * {@literal Ender Dragon}
     */
    String MC_ENTITY_ENDER_DRAGON_NAME = "minecraft:entity.ender_dragon.name";

    /**
     * {@literal Ender Pearl}
     */
    String MC_ENTITY_ENDER_PEARL_NAME = "minecraft:entity.ender_pearl.name";

    /**
     * {@literal Enderman}
     */
    String MC_ENTITY_ENDERMAN_NAME = "minecraft:entity.enderman.name";

    /**
     * {@literal Endermite}
     */
    String MC_ENTITY_ENDERMITE_NAME = "minecraft:entity.endermite.name";

    /**
     * {@literal Evoker Fang}
     */
    String MC_ENTITY_EVOCATION_FANG_NAME = "minecraft:entity.evocation_fang.name";

    /**
     * {@literal Evoker}
     */
    String MC_ENTITY_EVOCATION_ILLAGER_NAME = "minecraft:entity.evocation_illager.name";

    /**
     * {@literal Eye of Ender}
     */
    String MC_ENTITY_EYE_OF_ENDER_SIGNAL_NAME = "minecraft:entity.eye_of_ender_signal.name";

    /**
     * {@literal Falling Block}
     */
    String MC_ENTITY_FALLING_BLOCK_NAME = "minecraft:entity.falling_block.name";

    /**
     * {@literal Fireball}
     */
    String MC_ENTITY_FIREBALL_NAME = "minecraft:entity.fireball.name";

    /**
     * {@literal Firework Rocket}
     */
    String MC_ENTITY_FIREWORKS_ROCKET_NAME = "minecraft:entity.fireworks_rocket.name";

    /**
     * {@literal Clownfish}
     */
    String MC_ENTITY_FISH_CLOWNFISH_NAME = "minecraft:entity.fish.clownfish.name";

    /**
     * {@literal Fishing Hook}
     */
    String MC_ENTITY_FISHING_HOOK_NAME = "minecraft:entity.fishing_hook.name";

    /**
     * {@literal Fox}
     */
    String MC_ENTITY_FOX_NAME = "minecraft:entity.fox.name";

    /**
     * {@literal Frog}
     */
    String MC_ENTITY_FROG_NAME = "minecraft:entity.frog.name";

    /**
     * {@literal Minecart with Furnace}
     */
    String MC_ENTITY_FURNACE_MINECART_NAME = "minecraft:entity.furnace_minecart.name";

    /**
     * {@literal Ghast}
     */
    String MC_ENTITY_GHAST_NAME = "minecraft:entity.ghast.name";

    /**
     * {@literal Glow Squid}
     */
    String MC_ENTITY_GLOW_SQUID_NAME = "minecraft:entity.glow_squid.name";

    /**
     * {@literal Goat}
     */
    String MC_ENTITY_GOAT_NAME = "minecraft:entity.goat.name";

    /**
     * {@literal Guardian}
     */
    String MC_ENTITY_GUARDIAN_NAME = "minecraft:entity.guardian.name";

    /**
     * {@literal Happy Ghast}
     */
    String MC_ENTITY_HAPPY_GHAST_NAME = "minecraft:entity.happy_ghast.name";

    /**
     * {@literal Hoglin}
     */
    String MC_ENTITY_HOGLIN_NAME = "minecraft:entity.hoglin.name";

    /**
     * {@literal Minecart with Hopper}
     */
    String MC_ENTITY_HOPPER_MINECART_NAME = "minecraft:entity.hopper_minecart.name";

    /**
     * {@literal Horse}
     */
    String MC_ENTITY_HORSE_NAME = "minecraft:entity.horse.name";

    /**
     * {@literal Husk}
     */
    String MC_ENTITY_HUSK_NAME = "minecraft:entity.husk.name";

    /**
     * {@literal Iron Golem}
     */
    String MC_ENTITY_IRON_GOLEM_NAME = "minecraft:entity.iron_golem.name";

    /**
     * {@literal Item}
     */
    String MC_ENTITY_ITEM_NAME = "minecraft:entity.item.name";

    /**
     * {@literal Leash Knot}
     */
    String MC_ENTITY_LEASH_KNOT_NAME = "minecraft:entity.leash_knot.name";

    /**
     * {@literal Lightning Bolt}
     */
    String MC_ENTITY_LIGHTNING_BOLT_NAME = "minecraft:entity.lightning_bolt.name";

    /**
     * {@literal Lingering Potion}
     */
    String MC_ENTITY_LINGERING_POTION_NAME = "minecraft:entity.lingering_potion.name";

    /**
     * {@literal Llama}
     */
    String MC_ENTITY_LLAMA_NAME = "minecraft:entity.llama.name";

    /**
     * {@literal Llama Spit}
     */
    String MC_ENTITY_LLAMA_SPIT_NAME = "minecraft:entity.llama_spit.name";

    /**
     * {@literal Magma Cube}
     */
    String MC_ENTITY_MAGMA_CUBE_NAME = "minecraft:entity.magma_cube.name";

    /**
     * {@literal Minecart}
     */
    String MC_ENTITY_MINECART_NAME = "minecraft:entity.minecart.name";

    /**
     * {@literal Mooshroom}
     */
    String MC_ENTITY_MOOSHROOM_NAME = "minecraft:entity.mooshroom.name";

    /**
     * {@literal Moving Block}
     */
    String MC_ENTITY_MOVING_BLOCK_NAME = "minecraft:entity.moving_block.name";

    /**
     * {@literal Mule}
     */
    String MC_ENTITY_MULE_NAME = "minecraft:entity.mule.name";

    /**
     * {@literal Nautilus}
     */
    String MC_ENTITY_NAUTILUS_NAME = "minecraft:entity.nautilus.name";

    /**
     * {@literal Ocelot}
     */
    String MC_ENTITY_OCELOT_NAME = "minecraft:entity.ocelot.name";

    /**
     * {@literal Painting}
     */
    String MC_ENTITY_PAINTING_NAME = "minecraft:entity.painting.name";

    /**
     * {@literal Panda}
     */
    String MC_ENTITY_PANDA_NAME = "minecraft:entity.panda.name";

    /**
     * {@literal Parched}
     */
    String MC_ENTITY_PARCHED_NAME = "minecraft:entity.parched.name";

    /**
     * {@literal Parrot}
     */
    String MC_ENTITY_PARROT_NAME = "minecraft:entity.parrot.name";

    /**
     * {@literal Phantom}
     */
    String MC_ENTITY_PHANTOM_NAME = "minecraft:entity.phantom.name";

    /**
     * {@literal Pig}
     */
    String MC_ENTITY_PIG_NAME = "minecraft:entity.pig.name";

    /**
     * {@literal Piglin}
     */
    String MC_ENTITY_PIGLIN_NAME = "minecraft:entity.piglin.name";

    /**
     * {@literal Piglin Brute}
     */
    String MC_ENTITY_PIGLIN_BRUTE_NAME = "minecraft:entity.piglin_brute.name";

    /**
     * {@literal Pillager}
     */
    String MC_ENTITY_PILLAGER_NAME = "minecraft:entity.pillager.name";

    /**
     * {@literal Polar Bear}
     */
    String MC_ENTITY_POLAR_BEAR_NAME = "minecraft:entity.polar_bear.name";

    /**
     * {@literal Pufferfish}
     */
    String MC_ENTITY_PUFFERFISH_NAME = "minecraft:entity.pufferfish.name";

    /**
     * {@literal Rabbit}
     */
    String MC_ENTITY_RABBIT_NAME = "minecraft:entity.rabbit.name";

    /**
     * {@literal Ravager}
     */
    String MC_ENTITY_RAVAGER_NAME = "minecraft:entity.ravager.name";

    /**
     * {@literal Salmon}
     */
    String MC_ENTITY_SALMON_NAME = "minecraft:entity.salmon.name";

    /**
     * {@literal Sheep}
     */
    String MC_ENTITY_SHEEP_NAME = "minecraft:entity.sheep.name";

    /**
     * {@literal Shulker}
     */
    String MC_ENTITY_SHULKER_NAME = "minecraft:entity.shulker.name";

    /**
     * {@literal Shulker Bullet}
     */
    String MC_ENTITY_SHULKER_BULLET_NAME = "minecraft:entity.shulker_bullet.name";

    /**
     * {@literal Silverfish}
     */
    String MC_ENTITY_SILVERFISH_NAME = "minecraft:entity.silverfish.name";

    /**
     * {@literal Skeleton}
     */
    String MC_ENTITY_SKELETON_NAME = "minecraft:entity.skeleton.name";

    /**
     * {@literal Skeleton Horse}
     */
    String MC_ENTITY_SKELETON_HORSE_NAME = "minecraft:entity.skeleton_horse.name";

    /**
     * {@literal Slime}
     */
    String MC_ENTITY_SLIME_NAME = "minecraft:entity.slime.name";

    /**
     * {@literal Small Fireball}
     */
    String MC_ENTITY_SMALL_FIREBALL_NAME = "minecraft:entity.small_fireball.name";

    /**
     * {@literal Sniffer}
     */
    String MC_ENTITY_SNIFFER_NAME = "minecraft:entity.sniffer.name";

    /**
     * {@literal Snow Golem}
     */
    String MC_ENTITY_SNOW_GOLEM_NAME = "minecraft:entity.snow_golem.name";

    /**
     * {@literal Snowball}
     */
    String MC_ENTITY_SNOWBALL_NAME = "minecraft:entity.snowball.name";

    /**
     * {@literal Spider}
     */
    String MC_ENTITY_SPIDER_NAME = "minecraft:entity.spider.name";

    /**
     * {@literal Potion}
     */
    String MC_ENTITY_SPLASH_POTION_NAME = "minecraft:entity.splash_potion.name";

    /**
     * {@literal Squid}
     */
    String MC_ENTITY_SQUID_NAME = "minecraft:entity.squid.name";

    /**
     * {@literal Stray}
     */
    String MC_ENTITY_STRAY_NAME = "minecraft:entity.stray.name";

    /**
     * {@literal Strider}
     */
    String MC_ENTITY_STRIDER_NAME = "minecraft:entity.strider.name";

    /**
     * {@literal Tadpole}
     */
    String MC_ENTITY_TADPOLE_NAME = "minecraft:entity.tadpole.name";

    /**
     * {@literal Trident}
     */
    String MC_ENTITY_THROWN_TRIDENT_NAME = "minecraft:entity.thrown_trident.name";

    /**
     * {@literal Block of TNT}
     */
    String MC_ENTITY_TNT_NAME = "minecraft:entity.tnt.name";

    /**
     * {@literal Minecart with TNT}
     */
    String MC_ENTITY_TNT_MINECART_NAME = "minecraft:entity.tnt_minecart.name";

    /**
     * {@literal Trader Llama}
     */
    String MC_ENTITY_TRADER_LLAMA_NAME = "minecraft:entity.trader_llama.name";

    /**
     * {@literal Tripod Camera}
     */
    String MC_ENTITY_TRIPOD_CAMERA_NAME = "minecraft:entity.tripod_camera.name";

    /**
     * {@literal Tropical Fish}
     */
    String MC_ENTITY_TROPICALFISH_NAME = "minecraft:entity.tropicalfish.name";

    /**
     * {@literal Turtle}
     */
    String MC_ENTITY_TURTLE_NAME = "minecraft:entity.turtle.name";

    /**
     * {@literal Unknown}
     */
    String MC_ENTITY_UNKNOWN_NAME = "minecraft:entity.unknown.name";

    /**
     * {@literal Vex}
     */
    String MC_ENTITY_VEX_NAME = "minecraft:entity.vex.name";

    /**
     * {@literal Armorer}
     */
    String MC_ENTITY_VILLAGER_ARMOR = "minecraft:entity.villager.armor";

    /**
     * {@literal Butcher}
     */
    String MC_ENTITY_VILLAGER_BUTCHER = "minecraft:entity.villager.butcher";

    /**
     * {@literal Cartographer}
     */
    String MC_ENTITY_VILLAGER_CARTOGRAPHER = "minecraft:entity.villager.cartographer";

    /**
     * {@literal Cleric}
     */
    String MC_ENTITY_VILLAGER_CLERIC = "minecraft:entity.villager.cleric";

    /**
     * {@literal Farmer}
     */
    String MC_ENTITY_VILLAGER_FARMER = "minecraft:entity.villager.farmer";

    /**
     * {@literal Fisherman}
     */
    String MC_ENTITY_VILLAGER_FISHERMAN = "minecraft:entity.villager.fisherman";

    /**
     * {@literal Fletcher}
     */
    String MC_ENTITY_VILLAGER_FLETCHER = "minecraft:entity.villager.fletcher";

    /**
     * {@literal Leatherworker}
     */
    String MC_ENTITY_VILLAGER_LEATHER = "minecraft:entity.villager.leather";

    /**
     * {@literal Librarian}
     */
    String MC_ENTITY_VILLAGER_LIBRARIAN = "minecraft:entity.villager.librarian";

    /**
     * {@literal Mason}
     */
    String MC_ENTITY_VILLAGER_MASON = "minecraft:entity.villager.mason";

    /**
     * {@literal Villager}
     */
    String MC_ENTITY_VILLAGER_NAME = "minecraft:entity.villager.name";

    /**
     * {@literal Shepherd}
     */
    String MC_ENTITY_VILLAGER_SHEPHERD = "minecraft:entity.villager.shepherd";

    /**
     * {@literal Tool Smith}
     */
    String MC_ENTITY_VILLAGER_TOOL = "minecraft:entity.villager.tool";

    /**
     * {@literal Unskilled Villager}
     */
    String MC_ENTITY_VILLAGER_UNSKILLED = "minecraft:entity.villager.unskilled";

    /**
     * {@literal Weapon Smith}
     */
    String MC_ENTITY_VILLAGER_WEAPON = "minecraft:entity.villager.weapon";

    /**
     * {@literal Villager}
     */
    String MC_ENTITY_VILLAGER_V2_NAME = "minecraft:entity.villager_v2.name";

    /**
     * {@literal Vindicator}
     */
    String MC_ENTITY_VINDICATOR_NAME = "minecraft:entity.vindicator.name";

    /**
     * {@literal Wandering Trader}
     */
    String MC_ENTITY_WANDERING_TRADER_NAME = "minecraft:entity.wandering_trader.name";

    /**
     * {@literal Warden}
     */
    String MC_ENTITY_WARDEN_NAME = "minecraft:entity.warden.name";

    /**
     * {@literal Wind Charge}
     */
    String MC_ENTITY_WIND_CHARGE_PROJECTILE_NAME = "minecraft:entity.wind_charge_projectile.name";

    /**
     * {@literal Witch}
     */
    String MC_ENTITY_WITCH_NAME = "minecraft:entity.witch.name";

    /**
     * {@literal Wither}
     */
    String MC_ENTITY_WITHER_NAME = "minecraft:entity.wither.name";

    /**
     * {@literal Wither Skeleton}
     */
    String MC_ENTITY_WITHER_SKELETON_NAME = "minecraft:entity.wither_skeleton.name";

    /**
     * {@literal Wither Skull}
     */
    String MC_ENTITY_WITHER_SKULL_NAME = "minecraft:entity.wither_skull.name";

    /**
     * {@literal Wither Skull}
     */
    String MC_ENTITY_WITHER_SKULL_DANGEROUS_NAME = "minecraft:entity.wither_skull_dangerous.name";

    /**
     * {@literal Wolf}
     */
    String MC_ENTITY_WOLF_NAME = "minecraft:entity.wolf.name";

    /**
     * {@literal Bottle o' Enchanting}
     */
    String MC_ENTITY_XP_BOTTLE_NAME = "minecraft:entity.xp_bottle.name";

    /**
     * {@literal Experience Orb}
     */
    String MC_ENTITY_XP_ORB_NAME = "minecraft:entity.xp_orb.name";

    /**
     * {@literal Zoglin}
     */
    String MC_ENTITY_ZOGLIN_NAME = "minecraft:entity.zoglin.name";

    /**
     * {@literal Zombie}
     */
    String MC_ENTITY_ZOMBIE_NAME = "minecraft:entity.zombie.name";

    /**
     * {@literal Zombie Horse}
     */
    String MC_ENTITY_ZOMBIE_HORSE_NAME = "minecraft:entity.zombie_horse.name";

    /**
     * {@literal Zombie Nautilus}
     */
    String MC_ENTITY_ZOMBIE_NAUTILUS_NAME = "minecraft:entity.zombie_nautilus.name";

    /**
     * {@literal Zombified Piglin}
     */
    String MC_ENTITY_ZOMBIE_PIGMAN_NAME = "minecraft:entity.zombie_pigman.name";

    /**
     * {@literal Zombie Villager}
     */
    String MC_ENTITY_ZOMBIE_VILLAGER_NAME = "minecraft:entity.zombie_villager.name";

    /**
     * {@literal Zombie Villager}
     */
    String MC_ENTITY_ZOMBIE_VILLAGER_V2_NAME = "minecraft:entity.zombie_villager_v2.name";

    /**
     * {@literal Add Server}
     */
    String MC_EXTERNALSERVERSCREEN_ADDSERVER = "minecraft:externalServerScreen.addServer";

    /**
     * {@literal Adding an external server requires knowing the server name and server IP address. Once added, the server can be found in the server list for play.}
     */
    String MC_EXTERNALSERVERSCREEN_ADDSERVERDESCRIPTION = "minecraft:externalServerScreen.addServerDescription";

    /**
     * {@literal Add Server:}
     */
    String MC_EXTERNALSERVERSCREEN_ADDSERVERTITLE = "minecraft:externalServerScreen.addServerTitle";

    /**
     * {@literal Add External Server}
     */
    String MC_EXTERNALSERVERSCREEN_HEADER = "minecraft:externalServerScreen.header";

    /**
     * {@literal Add server by IP/Address.}
     */
    String MC_EXTERNALSERVERSCREEN_LABEL = "minecraft:externalServerScreen.label";

    /**
     * {@literal IP/Address}
     */
    String MC_EXTERNALSERVERSCREEN_SERVERADDRESS = "minecraft:externalServerScreen.serverAddress";

    /**
     * {@literal Server IP or Address}
     */
    String MC_EXTERNALSERVERSCREEN_SERVERADDRESSINPUT = "minecraft:externalServerScreen.serverAddressInput";

    /**
     * {@literal Name}
     */
    String MC_EXTERNALSERVERSCREEN_SERVERNAME = "minecraft:externalServerScreen.serverName";

    /**
     * {@literal Server Name}
     */
    String MC_EXTERNALSERVERSCREEN_SERVERNAMEINPUT = "minecraft:externalServerScreen.serverNameInput";

    /**
     * {@literal Port}
     */
    String MC_EXTERNALSERVERSCREEN_SERVERPORT = "minecraft:externalServerScreen.serverPort";

    /**
     * {@literal Server Port}
     */
    String MC_EXTERNALSERVERSCREEN_SERVERPORTINPUT = "minecraft:externalServerScreen.serverPortInput";

    /**
     * {@literal Ancient City}
     */
    String MC_FEATURE_ANCIENT_CITY = "minecraft:feature.ancient_city";

    /**
     * {@literal Bastion Remnant}
     */
    String MC_FEATURE_BASTION_REMNANT = "minecraft:feature.bastion_remnant";

    /**
     * {@literal Buried Treasure}
     */
    String MC_FEATURE_BURIED_TREASURE = "minecraft:feature.buried_treasure";

    /**
     * {@literal End City}
     */
    String MC_FEATURE_END_CITY = "minecraft:feature.end_city";

    /**
     * {@literal Nether Fortress}
     */
    String MC_FEATURE_FORTRESS = "minecraft:feature.fortress";

    /**
     * {@literal Woodland Mansion}
     */
    String MC_FEATURE_MANSION = "minecraft:feature.mansion";

    /**
     * {@literal Mineshaft}
     */
    String MC_FEATURE_MINESHAFT = "minecraft:feature.mineshaft";

    /**
     * {@literal Unknown Feature}
     */
    String MC_FEATURE_MISSINGNO = "minecraft:feature.missingno";

    /**
     * {@literal Ocean Monument}
     */
    String MC_FEATURE_MONUMENT = "minecraft:feature.monument";

    /**
     * {@literal Pillager Outpost}
     */
    String MC_FEATURE_PILLAGER_OUTPOST = "minecraft:feature.pillager_outpost";

    /**
     * {@literal Ruined Portal}
     */
    String MC_FEATURE_RUINED_PORTAL = "minecraft:feature.ruined_portal";

    /**
     * {@literal Ocean Ruins}
     */
    String MC_FEATURE_RUINS = "minecraft:feature.ruins";

    /**
     * {@literal Shipwreck}
     */
    String MC_FEATURE_SHIPWRECK = "minecraft:feature.shipwreck";

    /**
     * {@literal Stronghold}
     */
    String MC_FEATURE_STRONGHOLD = "minecraft:feature.stronghold";

    /**
     * {@literal Temple}
     */
    String MC_FEATURE_TEMPLE = "minecraft:feature.temple";

    /**
     * {@literal Trail Ruins}
     */
    String MC_FEATURE_TRAIL_RUINS = "minecraft:feature.trail_ruins";

    /**
     * {@literal Trial Chambers}
     */
    String MC_FEATURE_TRIAL_CHAMBERS = "minecraft:feature.trial_chambers";

    /**
     * {@literal Village}
     */
    String MC_FEATURE_VILLAGE = "minecraft:feature.village";

    /**
     * {@literal Adventure Mode}
     */
    String MC_GAMEMODE_ADVENTURE = "minecraft:gameMode.adventure";

    /**
     * {@literal Your game mode has been updated to %s}
     */
    String MC_GAMEMODE_CHANGED = "minecraft:gameMode.changed";

    /**
     * {@literal Creative Mode}
     */
    String MC_GAMEMODE_CREATIVE = "minecraft:gameMode.creative";

    /**
     * {@literal Hardcore Mode!}
     */
    String MC_GAMEMODE_HARDCORE = "minecraft:gameMode.hardcore";

    /**
     * {@literal Spectator Mode}
     */
    String MC_GAMEMODE_SPECTATOR = "minecraft:gameMode.spectator";

    /**
     * {@literal Survival Mode}
     */
    String MC_GAMEMODE_SURVIVAL = "minecraft:gameMode.survival";

    /**
     * {@literal Could not setBlock '%s'}
     */
    String MC_GAMETEST_ASSERT_COULDNOTSETBLOCK = "minecraft:gameTest.assert.couldNotSetBlock";

    /**
     * {@literal Could not spawn Entity of type '%s'}
     */
    String MC_GAMETEST_ASSERT_COULDNOTSPAWNENTITY = "minecraft:gameTest.assert.couldNotSpawnEntity";

    /**
     * {@literal Did not expect entity instance of type '%s'}
     */
    String MC_GAMETEST_ASSERT_DIDNOTEXPECTENTITYINSTANCE = "minecraft:gameTest.assert.didNotExpectEntityInstance";

    /**
     * {@literal Did not expect Entity of type '%s'}
     */
    String MC_GAMETEST_ASSERT_DIDNOTEXPECTENTITYOFTYPE = "minecraft:gameTest.assert.didNotExpectEntityOfType";

    /**
     * {@literal Duplicated entity with the same ID as another entity}
     */
    String MC_GAMETEST_ASSERT_ERRORDUPLICATEDENTITY = "minecraft:gameTest.assert.errorDuplicatedEntity";

    /**
     * {@literal Duplicated entity, entity trying to load with player's ActorUniqueId}
     */
    String MC_GAMETEST_ASSERT_ERRORDUPLICATEDENTITYPLAYERUNIQUEID = "minecraft:gameTest.assert.errorDuplicatedEntityPlayerUniqueID";

    /**
     * {@literal Attempting to spawn a hostile mob in a peaceful world}
     */
    String MC_GAMETEST_ASSERT_ERRORSPAWNHOSTILEINPEACEFULWORLD = "minecraft:gameTest.assert.errorSpawnHostileInPeacefulWorld";

    /**
     * {@literal Expected Block of type '%s'}
     */
    String MC_GAMETEST_ASSERT_EXPECTBLOCK = "minecraft:gameTest.assert.expectBlock";

    /**
     * {@literal Expected block with state '%s'}
     */
    String MC_GAMETEST_ASSERT_EXPECTBLOCKWITHSTATE = "minecraft:gameTest.assert.expectBlockWithState";

    /**
     * {@literal Expected block of type Cauldron}
     */
    String MC_GAMETEST_ASSERT_EXPECTCAULDRON = "minecraft:gameTest.assert.expectCauldron";

    /**
     * {@literal Expected empty container}
     */
    String MC_GAMETEST_ASSERT_EXPECTEMPTYCONTAINER = "minecraft:gameTest.assert.expectEmptyContainer";

    /**
     * {@literal Expected entity instance of type '%s'}
     */
    String MC_GAMETEST_ASSERT_EXPECTENTITYINSTANCE = "minecraft:gameTest.assert.expectEntityInstance";

    /**
     * {@literal Expected Entity of type '%s'}
     */
    String MC_GAMETEST_ASSERT_EXPECTENTITYOFTYPE = "minecraft:gameTest.assert.expectEntityOfType";

    /**
     * {@literal Container does not contain item of type %s}
     */
    String MC_GAMETEST_ASSERT_EXPECTITEMINCONTAINER = "minecraft:gameTest.assert.expectItemInContainer";

    /**
     * {@literal Expected Item Entity of type %s}
     */
    String MC_GAMETEST_ASSERT_EXPECTITEMOFTYPE = "minecraft:gameTest.assert.expectItemOfType";

    /**
     * {@literal Expected waterlogged block}
     */
    String MC_GAMETEST_ASSERT_EXPECTWATERLOGGED = "minecraft:gameTest.assert.expectWaterLogged";

    /**
     * {@literal Entity has armor %s in slot %s with data %s}
     */
    String MC_GAMETEST_ASSERT_HASARMOR = "minecraft:gameTest.assert.hasArmor";

    /**
     * {@literal Entity has component '%s'}
     */
    String MC_GAMETEST_ASSERT_HASCOMPONENT = "minecraft:gameTest.assert.hasComponent";

    /**
     * {@literal Invalid entity instance}
     */
    String MC_GAMETEST_ASSERT_INVALIDENTITYINSTANCE = "minecraft:gameTest.assert.invalidEntityInstance";

    /**
     * {@literal Invalid fluid type}
     */
    String MC_GAMETEST_ASSERT_INVALIDFLUIDTYPE = "minecraft:gameTest.assert.invalidFluidType";

    /**
     * {@literal Entity missing armor %s in slot %s with data %s}
     */
    String MC_GAMETEST_ASSERT_MISSINGARMOR = "minecraft:gameTest.assert.missingArmor";

    /**
     * {@literal Expected a button}
     */
    String MC_GAMETEST_ASSERT_MISSINGBUTTON = "minecraft:gameTest.assert.missingButton";

    /**
     * {@literal Entity missing component '%s'}
     */
    String MC_GAMETEST_ASSERT_MISSINGCOMPONENT = "minecraft:gameTest.assert.missingComponent";

    /**
     * {@literal Can not set fuse of entity because it does not have an explosion component}
     */
    String MC_GAMETEST_ASSERT_MISSINGEXPLOSIONCOMPONENT = "minecraft:gameTest.assert.missingExplosionComponent";

    /**
     * {@literal Expected a lever}
     */
    String MC_GAMETEST_ASSERT_MISSINGLEVER = "minecraft:gameTest.assert.missingLever";

    /**
     * {@literal Could not make the mob move '%s'}
     */
    String MC_GAMETEST_ASSERT_MOBWONTMOVE = "minecraft:gameTest.assert.mobWontMove";

    /**
     * {@literal No container found}
     */
    String MC_GAMETEST_ASSERT_NOCONTAINERFOUND = "minecraft:gameTest.assert.noContainerFound";

    /**
     * {@literal No path found for Entity of type '%s'}
     */
    String MC_GAMETEST_ASSERT_NOPATHFOUND = "minecraft:gameTest.assert.noPathFound";

    /**
     * {@literal spawnWithoutBehaviors() only works with mobs}
     */
    String MC_GAMETEST_ASSERT_ONLYWORKSWITHMOBS = "minecraft:gameTest.assert.onlyWorksWithMobs";

    /**
     * {@literal Path found for Entity of type '%s'}
     */
    String MC_GAMETEST_ASSERT_PATHFOUND = "minecraft:gameTest.assert.pathFound";

    /**
     * {@literal Did not expect Block of type '%s'}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDBLOCK = "minecraft:gameTest.assert.unexpectedBlock";

    /**
     * {@literal Block does not have the expected Block State}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDBLOCKSTATE = "minecraft:gameTest.assert.unexpectedBlockState";

    /**
     * {@literal Unexpected Entity data result}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDDATARESULT = "minecraft:gameTest.assert.unexpectedDataResult";

    /**
     * {@literal Did not expect Item Entity of type %s}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDITEMOFTYPE = "minecraft:gameTest.assert.unexpectedItemOfType";

    /**
     * {@literal Unexpected value for parameter '%s'}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDPARAMETERVALUE = "minecraft:gameTest.assert.unexpectedParameterValue";

    /**
     * {@literal Unexpected Redstone power level. Expected: %s. Actual: %s.}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDREDSTONEPOWERLEVEL = "minecraft:gameTest.assert.unexpectedRedstonePowerLevel";

    /**
     * {@literal Did not expect waterlogged block}
     */
    String MC_GAMETEST_ASSERT_UNEXPECTEDWATERLOGGED = "minecraft:gameTest.assert.unexpectedWaterLogged";

    /**
     * {@literal Unexpected Item Entity count of type %s. Expected: %s. Actual: %s.}
     */
    String MC_GAMETEST_ASSERT_WRONGENTITYITEMCOUNT = "minecraft:gameTest.assert.wrongEntityItemCount";

    /**
     * {@literal Interacts with gametest.}
     */
    String MC_GAMETEST_DESCRIPTION = "minecraft:gameTest.description";

    /**
     * {@literal Can't find the block you're looking at}
     */
    String MC_GAMETEST_ERROR_CANNOTFINDLOOKEDATBLOCK = "minecraft:gameTest.error.cannotFindLookedAtBlock";

    /**
     * {@literal Can't find a structure block that contains the targeted pos %s, %s, %s}
     */
    String MC_GAMETEST_ERROR_CANNOTFINDSTUCTUREBLOCKCONTAININGBLOCK = "minecraft:gameTest.error.cannotFindStuctureBlockContainingBlock";

    /**
     * {@literal Fail conditions met}
     */
    String MC_GAMETEST_ERROR_FAILCONDITIONMET = "minecraft:gameTest.error.failConditionMet";

    /**
     * {@literal %s failed!}
     */
    String MC_GAMETEST_ERROR_FAILED = "minecraft:gameTest.error.failed";

    /**
     * {@literal Flaky test %s failed, attempt: %s / %s}
     */
    String MC_GAMETEST_ERROR_FLAKYFAILED = "minecraft:gameTest.error.flakyFailed";

    /**
     * {@literal , successes: %s (%s required)           }
     */
    String MC_GAMETEST_ERROR_FLAKYFAILEDSHOWSUCCESSES = "minecraft:gameTest.error.flakyFailedShowSuccesses";

    /**
     * {@literal Not enough successes: %s out of %s attempts. Required successes: %s. Max attempts: %s.}
     */
    String MC_GAMETEST_ERROR_FLAKYTESTERROR = "minecraft:gameTest.error.flakyTestError";

    /**
     * {@literal Couldn't find any structure block within %s radius}
     */
    String MC_GAMETEST_ERROR_MISSINGSTRUCTURE = "minecraft:gameTest.error.missingStructure";

    /**
     * {@literal No sequences finished}
     */
    String MC_GAMETEST_ERROR_NOFINISHEDSEQUENCES = "minecraft:gameTest.error.noFinishedSequences";

    /**
     * {@literal No tests found for tag '%s'}
     */
    String MC_GAMETEST_ERROR_NOTESTSFOUND = "minecraft:gameTest.error.noTestsFound";

    /**
     * {@literal Repeat count must be greater than 0}
     */
    String MC_GAMETEST_ERROR_REPEATCOUNTTOOSMALL = "minecraft:gameTest.error.repeatCountTooSmall";

    /**
     * {@literal Failed to spawn test structure with path '%s' }
     */
    String MC_GAMETEST_ERROR_STRUCTURENOTSPAWNED = "minecraft:gameTest.error.structureNotSpawned";

    /**
     * {@literal The structure must be less than %s blocks big in each axis}
     */
    String MC_GAMETEST_ERROR_STRUCTURETOOBIG = "minecraft:gameTest.error.structureTooBig";

    /**
     * {@literal Succeeded in invalid tick: expected %s, but current tick is %s}
     */
    String MC_GAMETEST_ERROR_SUCCEEDEDININVALIDTICK = "minecraft:gameTest.error.succeededInInvalidTick";

    /**
     * {@literal Could not find test with name '%s'.}
     */
    String MC_GAMETEST_ERROR_TESTMISSING = "minecraft:gameTest.error.testMissing";

    /**
     * {@literal Didn't succeed or fail within %s ticks}
     */
    String MC_GAMETEST_ERROR_TIMEOUT = "minecraft:gameTest.error.timeout";

    /**
     * {@literal Unknown command mode provided}
     */
    String MC_GAMETEST_ERROR_UNKNOWNCOMMANDMODE = "minecraft:gameTest.error.unknownCommandMode";

    /**
     * {@literal Unknown event name '%s'}
     */
    String MC_GAMETEST_ERROR_UNKOWNEVENT = "minecraft:gameTest.error.unkownEvent";

    /**
     * {@literal (optional)}
     */
    String MC_GAMETEST_OPTIONALPREFIX = "minecraft:gameTest.optionalPrefix";

    /**
     * {@literal All required tests passed :)}
     */
    String MC_GAMETEST_REPORT_ALLREQUIREDTESTSPASSED = "minecraft:gameTest.report.allRequiredTestsPassed";

    /**
     * {@literal Game Test Report                                      }
     */
    String MC_GAMETEST_REPORT_BOOKTITLE = "minecraft:gameTest.report.bookTitle";

    /**
     * {@literal %s optional tests failed}
     */
    String MC_GAMETEST_REPORT_OPTIONALFAILURES = "minecraft:gameTest.report.optionalFailures";

    /**
     * {@literal %s required tests failed :(}
     */
    String MC_GAMETEST_REPORT_SOMETESTSFAILED = "minecraft:gameTest.report.someTestsFailed";

    /**
     * {@literal GameTest done! %s tests were run}
     */
    String MC_GAMETEST_REPORT_TESTCOUNT = "minecraft:gameTest.report.testCount";

    /**
     * {@literal At: %s, %s, %s (relative: %s, %s, %s)}
     */
    String MC_GAMETEST_REPORT_TESTLOCATION = "minecraft:gameTest.report.testLocation";

    /**
     * {@literal Tick: %s                                              }
     */
    String MC_GAMETEST_REPORT_TICKCOUNT = "minecraft:gameTest.report.tickCount";

    /**
     * {@literal Flaky Test %s succeeded, attempt: %s successes: %s.}
     */
    String MC_GAMETEST_SUCCESS_FLAKYPASSED = "minecraft:gameTest.success.flakyPassed";

    /**
     * {@literal %s passed %s times of %s attempts.           }
     */
    String MC_GAMETEST_SUCCESS_FLAKYPASSEDENOUGH = "minecraft:gameTest.success.flakyPassedEnough";

    /**
     * {@literal %s passed!}
     */
    String MC_GAMETEST_SUCCESS_PASSED = "minecraft:gameTest.success.passed";

    /**
     * {@literal Position relative to %s: %s}
     */
    String MC_GAMETEST_SUCCESS_RELATIVEPOSITION = "minecraft:gameTest.success.relativePosition";

    /**
     * {@literal Running %s tests...}
     */
    String MC_GAMETEST_SUCCESS_RUNNINGTESTS = "minecraft:gameTest.success.runningTests";

    /**
     * {@literal Running %s tests with tag '%s'...}
     */
    String MC_GAMETEST_SUCCESS_TESTSSTARTED = "minecraft:gameTest.success.testsStarted";

    /**
     * {@literal Your game is missing a required component. To resolve this, choose Install to initiate installation and restart the game.}
     */
    String MC_GAMEINPUT_LOAD_ERROR_MESSAGE = "minecraft:gameinput_load.error.message";

    /**
     * {@literal Your game is missing a required component. To resolve this, choose Ok to initiate installation and restart the game.}
     */
    String MC_GAMEINPUT_LOAD_ERROR_MESSAGE2 = "minecraft:gameinput_load.error.message2";

    /**
     * {@literal Your game is missing a required component. To resolve this, run GameInputRedist.msi (on your local installation) and restart the game.}
     */
    String MC_GAMEINPUT_LOAD_ERROR_MESSAGE3 = "minecraft:gameinput_load.error.message3";

    /**
     * {@literal Error}
     */
    String MC_GAMEINPUT_LOAD_ERROR_TITLE = "minecraft:gameinput_load.error.title";

    /**
     * {@literal Install}
     */
    String MC_GAMEINPUT_LOAD_INSTALLBUTTON = "minecraft:gameinput_load.installButton";

    /**
     * {@literal Page %1 of %2}
     */
    String MC_IMMERSIVE_READER_BOOK_PAGE_HEADER = "minecraft:immersive_reader.book_page_header";

    /**
     * {@literal There was a problem connecting to Immersive Reader. Please restart Minecraft Education and try again.}
     */
    String MC_IMMERSIVE_READER_ERROR_IDENTITY_FAILURE = "minecraft:immersive_reader.error.identity_failure";

    /**
     * {@literal There was a problem connecting to Immersive Reader.}
     */
    String MC_IMMERSIVE_READER_ERROR_WEBVIEW_FAILURE = "minecraft:immersive_reader.error.webview_failure";

    /**
     * {@literal  Page %1}
     */
    String MC_IMMERSIVE_READER_PORTFOLIO_PAGE_HEADER = "minecraft:immersive_reader.portfolio_page_header";

    /**
     * {@literal Game Invite from %s}
     */
    String MC_INBOX_GAMEINVITE_TITLE = "minecraft:inbox.gameInvite.title";

    /**
     * {@literal Realm invite to %s}
     */
    String MC_INBOX_REALMINVITE_TITLE = "minecraft:inbox.realmInvite.title";

    /**
     * {@literal Destroy Item}
     */
    String MC_INVENTORY_BINSLOT = "minecraft:inventory.binSlot";

    /**
     * {@literal Offline Friends}
     */
    String MC_INVITE_OFFLINEFRIENDS = "minecraft:invite.OfflineFriends";

    /**
     * {@literal Online Friends}
     */
    String MC_INVITE_ONLINEFRIENDS = "minecraft:invite.OnlineFriends";

    /**
     * {@literal Suggested Friends}
     */
    String MC_INVITE_SUGGESTEDFRIENDS = "minecraft:invite.SuggestedFriends";

    /**
     * {@literal Clear Selection}
     */
    String MC_INVITE_CLEAR = "minecraft:invite.clear";

    /**
     * {@literal %s has invited you to play Minecraft}
     */
    String MC_INVITE_CONFIRMATION_DESCRIPTION = "minecraft:invite.confirmation.description";

    /**
     * {@literal Some of your invites may have failed to send.}
     */
    String MC_INVITE_ERROR_MESSAGE = "minecraft:invite.error.message";

    /**
     * {@literal Something went wrong. We couldn't load your friends list.}
     */
    String MC_INVITE_ERROR1 = "minecraft:invite.error1";

    /**
     * {@literal Something went wrong. We couldn't load some of your friends details.}
     */
    String MC_INVITE_ERROR2 = "minecraft:invite.error2";

    /**
     * {@literal You haven't added anyone to your friend's list!}
     */
    String MC_INVITE_NOFRIENDS = "minecraft:invite.noFriends";

    /**
     * {@literal You haven't added anyone to your Realm!}
     */
    String MC_INVITE_NOREALMFRIENDS = "minecraft:invite.noRealmFriends";

    /**
     * {@literal Press %s to accept}
     */
    String MC_INVITE_NOTIFICATION_ACCEPTINVITETTSGAMEPAD = "minecraft:invite.notification.acceptInviteTtsGamepad";

    /**
     * {@literal Double tap the invite at the top of the screen to accept}
     */
    String MC_INVITE_NOTIFICATION_ACCEPTINVITETTSTOUCH = "minecraft:invite.notification.acceptInviteTtsTouch";

    /**
     * {@literal %s has invited you to play Minecraft}
     */
    String MC_INVITE_NOTIFICATION_DESCRIPTION = "minecraft:invite.notification.description";

    /**
     * {@literal Game Invite}
     */
    String MC_INVITE_NOTIFICATION_TITLE = "minecraft:invite.notification.title";

    /**
     * {@literal %d/%d}
     */
    String MC_INVITE_PAGECOUNTER = "minecraft:invite.pageCounter";

    /**
     * {@literal Add Realm Members}
     */
    String MC_INVITE_REALM_ADD_MEMBER = "minecraft:invite.realm.add_member";

    /**
     * {@literal Send %d Invites}
     */
    String MC_INVITE_SEND = "minecraft:invite.send";

    /**
     * {@literal Send 1 Invite}
     */
    String MC_INVITE_SENDONE = "minecraft:invite.sendOne";

    /**
     * {@literal Send Invites}
     */
    String MC_INVITE_SENDUNNUMBERED = "minecraft:invite.sendUnnumbered";

    /**
     * {@literal Invite Friends to your Game}
     */
    String MC_INVITE_TITLE = "minecraft:invite.title";

    /**
     * {@literal Invite Members to your Realm Server}
     */
    String MC_INVITE_TITLEREALMS = "minecraft:invite.titleRealms";

    /**
     * {@literal Acacia Door}
     */
    String MC_ITEM_ACACIA_DOOR_NAME = "minecraft:item.acacia_door.name";

    /**
     * {@literal Acacia Hanging Sign}
     */
    String MC_ITEM_ACACIA_HANGING_SIGN_NAME = "minecraft:item.acacia_hanging_sign.name";

    /**
     * {@literal Acacia Sign}
     */
    String MC_ITEM_ACACIA_SIGN_NAME = "minecraft:item.acacia_sign.name";

    /**
     * {@literal Air}
     */
    String MC_ITEM_AIR_NAME = "minecraft:item.air.name";

    /**
     * {@literal Amethyst Shard}
     */
    String MC_ITEM_AMETHYST_SHARD_NAME = "minecraft:item.amethyst_shard.name";

    /**
     * {@literal Angler Pottery Sherd}
     */
    String MC_ITEM_ANGLER_POTTERY_SHERD_NAME = "minecraft:item.angler_pottery_sherd.name";

    /**
     * {@literal Apple}
     */
    String MC_ITEM_APPLE_NAME = "minecraft:item.apple.name";

    /**
     * {@literal Enchanted Golden Apple}
     */
    String MC_ITEM_APPLEENCHANTED_NAME = "minecraft:item.appleEnchanted.name";

    /**
     * {@literal Archer Pottery Sherd}
     */
    String MC_ITEM_ARCHER_POTTERY_SHERD_NAME = "minecraft:item.archer_pottery_sherd.name";

    /**
     * {@literal Armadillo Scute}
     */
    String MC_ITEM_ARMADILLO_SCUTE_NAME = "minecraft:item.armadillo_scute.name";

    /**
     * {@literal Upgrade:}
     */
    String MC_ITEM_ARMOR_UPGRADE = "minecraft:item.armor.upgrade";

    /**
     * {@literal Armor Stand}
     */
    String MC_ITEM_ARMOR_STAND_NAME = "minecraft:item.armor_stand.name";

    /**
     * {@literal Arms Up Pottery Sherd}
     */
    String MC_ITEM_ARMS_UP_POTTERY_SHERD_NAME = "minecraft:item.arms_up_pottery_sherd.name";

    /**
     * {@literal Arrow}
     */
    String MC_ITEM_ARROW_NAME = "minecraft:item.arrow.name";

    /**
     * {@literal Adult %1#s Axolotl}
     */
    String MC_ITEM_AXOLOTLADULTBODYSINGLE_NAME = "minecraft:item.axolotlAdultBodySingle.name";

    /**
     * {@literal Baby %1#s Axolotl}
     */
    String MC_ITEM_AXOLOTLBABYBODYSINGLE_NAME = "minecraft:item.axolotlBabyBodySingle.name";

    /**
     * {@literal Blue}
     */
    String MC_ITEM_AXOLOTLCOLORBLUE_NAME = "minecraft:item.axolotlColorBlue.name";

    /**
     * {@literal Cyan}
     */
    String MC_ITEM_AXOLOTLCOLORCYAN_NAME = "minecraft:item.axolotlColorCyan.name";

    /**
     * {@literal Gold}
     */
    String MC_ITEM_AXOLOTLCOLORGOLD_NAME = "minecraft:item.axolotlColorGold.name";

    /**
     * {@literal Leucistic}
     */
    String MC_ITEM_AXOLOTLCOLORLUCY_NAME = "minecraft:item.axolotlColorLucy.name";

    /**
     * {@literal Brown}
     */
    String MC_ITEM_AXOLOTLCOLORWILD_NAME = "minecraft:item.axolotlColorWild.name";

    /**
     * {@literal Baked Potato}
     */
    String MC_ITEM_BAKED_POTATO_NAME = "minecraft:item.baked_potato.name";

    /**
     * {@literal Bamboo Door}
     */
    String MC_ITEM_BAMBOO_DOOR_NAME = "minecraft:item.bamboo_door.name";

    /**
     * {@literal Bamboo Hanging Sign}
     */
    String MC_ITEM_BAMBOO_HANGING_SIGN_NAME = "minecraft:item.bamboo_hanging_sign.name";

    /**
     * {@literal Bamboo Sign}
     */
    String MC_ITEM_BAMBOO_SIGN_NAME = "minecraft:item.bamboo_sign.name";

    /**
     * {@literal Black Banner}
     */
    String MC_ITEM_BANNER_BLACK_NAME = "minecraft:item.banner.black.name";

    /**
     * {@literal Blue Banner}
     */
    String MC_ITEM_BANNER_BLUE_NAME = "minecraft:item.banner.blue.name";

    /**
     * {@literal Black Bordure}
     */
    String MC_ITEM_BANNER_BORDER_BLACK = "minecraft:item.banner.border.black";

    /**
     * {@literal Blue Bordure}
     */
    String MC_ITEM_BANNER_BORDER_BLUE = "minecraft:item.banner.border.blue";

    /**
     * {@literal Brown Bordure}
     */
    String MC_ITEM_BANNER_BORDER_BROWN = "minecraft:item.banner.border.brown";

    /**
     * {@literal Cyan Bordure}
     */
    String MC_ITEM_BANNER_BORDER_CYAN = "minecraft:item.banner.border.cyan";

    /**
     * {@literal Gray Bordure}
     */
    String MC_ITEM_BANNER_BORDER_GRAY = "minecraft:item.banner.border.gray";

    /**
     * {@literal Green Bordure}
     */
    String MC_ITEM_BANNER_BORDER_GREEN = "minecraft:item.banner.border.green";

    /**
     * {@literal Light Blue Bordure}
     */
    String MC_ITEM_BANNER_BORDER_LIGHTBLUE = "minecraft:item.banner.border.lightBlue";

    /**
     * {@literal Lime Bordure}
     */
    String MC_ITEM_BANNER_BORDER_LIME = "minecraft:item.banner.border.lime";

    /**
     * {@literal Magenta Bordure}
     */
    String MC_ITEM_BANNER_BORDER_MAGENTA = "minecraft:item.banner.border.magenta";

    /**
     * {@literal Orange Bordure}
     */
    String MC_ITEM_BANNER_BORDER_ORANGE = "minecraft:item.banner.border.orange";

    /**
     * {@literal Pink Bordure}
     */
    String MC_ITEM_BANNER_BORDER_PINK = "minecraft:item.banner.border.pink";

    /**
     * {@literal Purple Bordure}
     */
    String MC_ITEM_BANNER_BORDER_PURPLE = "minecraft:item.banner.border.purple";

    /**
     * {@literal Red Bordure}
     */
    String MC_ITEM_BANNER_BORDER_RED = "minecraft:item.banner.border.red";

    /**
     * {@literal Light Gray Bordure}
     */
    String MC_ITEM_BANNER_BORDER_SILVER = "minecraft:item.banner.border.silver";

    /**
     * {@literal White Bordure}
     */
    String MC_ITEM_BANNER_BORDER_WHITE = "minecraft:item.banner.border.white";

    /**
     * {@literal Yellow Bordure}
     */
    String MC_ITEM_BANNER_BORDER_YELLOW = "minecraft:item.banner.border.yellow";

    /**
     * {@literal Black Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_BLACK = "minecraft:item.banner.bricks.black";

    /**
     * {@literal Blue Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_BLUE = "minecraft:item.banner.bricks.blue";

    /**
     * {@literal Brown Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_BROWN = "minecraft:item.banner.bricks.brown";

    /**
     * {@literal Cyan Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_CYAN = "minecraft:item.banner.bricks.cyan";

    /**
     * {@literal Gray Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_GRAY = "minecraft:item.banner.bricks.gray";

    /**
     * {@literal Green Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_GREEN = "minecraft:item.banner.bricks.green";

    /**
     * {@literal Light Blue Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_LIGHTBLUE = "minecraft:item.banner.bricks.lightBlue";

    /**
     * {@literal Lime Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_LIME = "minecraft:item.banner.bricks.lime";

    /**
     * {@literal Magenta Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_MAGENTA = "minecraft:item.banner.bricks.magenta";

    /**
     * {@literal Orange Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_ORANGE = "minecraft:item.banner.bricks.orange";

    /**
     * {@literal Pink Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_PINK = "minecraft:item.banner.bricks.pink";

    /**
     * {@literal Purple Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_PURPLE = "minecraft:item.banner.bricks.purple";

    /**
     * {@literal Red Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_RED = "minecraft:item.banner.bricks.red";

    /**
     * {@literal Light Gray Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_SILVER = "minecraft:item.banner.bricks.silver";

    /**
     * {@literal White Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_WHITE = "minecraft:item.banner.bricks.white";

    /**
     * {@literal Yellow Field Masoned}
     */
    String MC_ITEM_BANNER_BRICKS_YELLOW = "minecraft:item.banner.bricks.yellow";

    /**
     * {@literal Brown Banner}
     */
    String MC_ITEM_BANNER_BROWN_NAME = "minecraft:item.banner.brown.name";

    /**
     * {@literal Black Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_BLACK = "minecraft:item.banner.circle.black";

    /**
     * {@literal Blue Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_BLUE = "minecraft:item.banner.circle.blue";

    /**
     * {@literal Brown Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_BROWN = "minecraft:item.banner.circle.brown";

    /**
     * {@literal Cyan Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_CYAN = "minecraft:item.banner.circle.cyan";

    /**
     * {@literal Gray Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_GRAY = "minecraft:item.banner.circle.gray";

    /**
     * {@literal Green Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_GREEN = "minecraft:item.banner.circle.green";

    /**
     * {@literal Light Blue Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_LIGHTBLUE = "minecraft:item.banner.circle.lightBlue";

    /**
     * {@literal Lime Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_LIME = "minecraft:item.banner.circle.lime";

    /**
     * {@literal Magenta Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_MAGENTA = "minecraft:item.banner.circle.magenta";

    /**
     * {@literal Orange Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_ORANGE = "minecraft:item.banner.circle.orange";

    /**
     * {@literal Pink Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_PINK = "minecraft:item.banner.circle.pink";

    /**
     * {@literal Purple Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_PURPLE = "minecraft:item.banner.circle.purple";

    /**
     * {@literal Red Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_RED = "minecraft:item.banner.circle.red";

    /**
     * {@literal Light Gray Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_SILVER = "minecraft:item.banner.circle.silver";

    /**
     * {@literal White Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_WHITE = "minecraft:item.banner.circle.white";

    /**
     * {@literal Yellow Roundel}
     */
    String MC_ITEM_BANNER_CIRCLE_YELLOW = "minecraft:item.banner.circle.yellow";

    /**
     * {@literal Black Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_BLACK = "minecraft:item.banner.creeper.black";

    /**
     * {@literal Blue Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_BLUE = "minecraft:item.banner.creeper.blue";

    /**
     * {@literal Brown Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_BROWN = "minecraft:item.banner.creeper.brown";

    /**
     * {@literal Cyan Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_CYAN = "minecraft:item.banner.creeper.cyan";

    /**
     * {@literal Gray Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_GRAY = "minecraft:item.banner.creeper.gray";

    /**
     * {@literal Green Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_GREEN = "minecraft:item.banner.creeper.green";

    /**
     * {@literal Light Blue Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_LIGHTBLUE = "minecraft:item.banner.creeper.lightBlue";

    /**
     * {@literal Lime Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_LIME = "minecraft:item.banner.creeper.lime";

    /**
     * {@literal Magenta Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_MAGENTA = "minecraft:item.banner.creeper.magenta";

    /**
     * {@literal Orange Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_ORANGE = "minecraft:item.banner.creeper.orange";

    /**
     * {@literal Pink Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_PINK = "minecraft:item.banner.creeper.pink";

    /**
     * {@literal Purple Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_PURPLE = "minecraft:item.banner.creeper.purple";

    /**
     * {@literal Red Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_RED = "minecraft:item.banner.creeper.red";

    /**
     * {@literal Light Gray Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_SILVER = "minecraft:item.banner.creeper.silver";

    /**
     * {@literal White Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_WHITE = "minecraft:item.banner.creeper.white";

    /**
     * {@literal Yellow Creeper Charge}
     */
    String MC_ITEM_BANNER_CREEPER_YELLOW = "minecraft:item.banner.creeper.yellow";

    /**
     * {@literal Black Saltire}
     */
    String MC_ITEM_BANNER_CROSS_BLACK = "minecraft:item.banner.cross.black";

    /**
     * {@literal Blue Saltire}
     */
    String MC_ITEM_BANNER_CROSS_BLUE = "minecraft:item.banner.cross.blue";

    /**
     * {@literal Brown Saltire}
     */
    String MC_ITEM_BANNER_CROSS_BROWN = "minecraft:item.banner.cross.brown";

    /**
     * {@literal Cyan Saltire}
     */
    String MC_ITEM_BANNER_CROSS_CYAN = "minecraft:item.banner.cross.cyan";

    /**
     * {@literal Gray Saltire}
     */
    String MC_ITEM_BANNER_CROSS_GRAY = "minecraft:item.banner.cross.gray";

    /**
     * {@literal Green Saltire}
     */
    String MC_ITEM_BANNER_CROSS_GREEN = "minecraft:item.banner.cross.green";

    /**
     * {@literal Light Blue Saltire}
     */
    String MC_ITEM_BANNER_CROSS_LIGHTBLUE = "minecraft:item.banner.cross.lightBlue";

    /**
     * {@literal Lime Saltire}
     */
    String MC_ITEM_BANNER_CROSS_LIME = "minecraft:item.banner.cross.lime";

    /**
     * {@literal Magenta Saltire}
     */
    String MC_ITEM_BANNER_CROSS_MAGENTA = "minecraft:item.banner.cross.magenta";

    /**
     * {@literal Orange Saltire}
     */
    String MC_ITEM_BANNER_CROSS_ORANGE = "minecraft:item.banner.cross.orange";

    /**
     * {@literal Pink Saltire}
     */
    String MC_ITEM_BANNER_CROSS_PINK = "minecraft:item.banner.cross.pink";

    /**
     * {@literal Purple Saltire}
     */
    String MC_ITEM_BANNER_CROSS_PURPLE = "minecraft:item.banner.cross.purple";

    /**
     * {@literal Red Saltire}
     */
    String MC_ITEM_BANNER_CROSS_RED = "minecraft:item.banner.cross.red";

    /**
     * {@literal Light Gray Saltire}
     */
    String MC_ITEM_BANNER_CROSS_SILVER = "minecraft:item.banner.cross.silver";

    /**
     * {@literal White Saltire}
     */
    String MC_ITEM_BANNER_CROSS_WHITE = "minecraft:item.banner.cross.white";

    /**
     * {@literal Yellow Saltire}
     */
    String MC_ITEM_BANNER_CROSS_YELLOW = "minecraft:item.banner.cross.yellow";

    /**
     * {@literal Black Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_BLACK = "minecraft:item.banner.curly_border.black";

    /**
     * {@literal Blue Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_BLUE = "minecraft:item.banner.curly_border.blue";

    /**
     * {@literal Brown Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_BROWN = "minecraft:item.banner.curly_border.brown";

    /**
     * {@literal Cyan Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_CYAN = "minecraft:item.banner.curly_border.cyan";

    /**
     * {@literal Gray Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_GRAY = "minecraft:item.banner.curly_border.gray";

    /**
     * {@literal Green Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_GREEN = "minecraft:item.banner.curly_border.green";

    /**
     * {@literal Light Blue Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_LIGHTBLUE = "minecraft:item.banner.curly_border.lightBlue";

    /**
     * {@literal Lime Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_LIME = "minecraft:item.banner.curly_border.lime";

    /**
     * {@literal Magenta Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_MAGENTA = "minecraft:item.banner.curly_border.magenta";

    /**
     * {@literal Orange Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_ORANGE = "minecraft:item.banner.curly_border.orange";

    /**
     * {@literal Pink Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_PINK = "minecraft:item.banner.curly_border.pink";

    /**
     * {@literal Purple Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_PURPLE = "minecraft:item.banner.curly_border.purple";

    /**
     * {@literal Red Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_RED = "minecraft:item.banner.curly_border.red";

    /**
     * {@literal Light Gray Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_SILVER = "minecraft:item.banner.curly_border.silver";

    /**
     * {@literal White Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_WHITE = "minecraft:item.banner.curly_border.white";

    /**
     * {@literal Yellow Bordure Indented}
     */
    String MC_ITEM_BANNER_CURLY_BORDER_YELLOW = "minecraft:item.banner.curly_border.yellow";

    /**
     * {@literal Cyan Banner}
     */
    String MC_ITEM_BANNER_CYAN_NAME = "minecraft:item.banner.cyan.name";

    /**
     * {@literal Black Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_BLACK = "minecraft:item.banner.diagonal_left.black";

    /**
     * {@literal Blue Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_BLUE = "minecraft:item.banner.diagonal_left.blue";

    /**
     * {@literal Brown Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_BROWN = "minecraft:item.banner.diagonal_left.brown";

    /**
     * {@literal Cyan Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_CYAN = "minecraft:item.banner.diagonal_left.cyan";

    /**
     * {@literal Gray Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_GRAY = "minecraft:item.banner.diagonal_left.gray";

    /**
     * {@literal Green Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_GREEN = "minecraft:item.banner.diagonal_left.green";

    /**
     * {@literal Light Blue Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_LIGHTBLUE = "minecraft:item.banner.diagonal_left.lightBlue";

    /**
     * {@literal Lime Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_LIME = "minecraft:item.banner.diagonal_left.lime";

    /**
     * {@literal Magenta Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_MAGENTA = "minecraft:item.banner.diagonal_left.magenta";

    /**
     * {@literal Orange Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_ORANGE = "minecraft:item.banner.diagonal_left.orange";

    /**
     * {@literal Pink Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_PINK = "minecraft:item.banner.diagonal_left.pink";

    /**
     * {@literal Purple Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_PURPLE = "minecraft:item.banner.diagonal_left.purple";

    /**
     * {@literal Red Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_RED = "minecraft:item.banner.diagonal_left.red";

    /**
     * {@literal Light Gray Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_SILVER = "minecraft:item.banner.diagonal_left.silver";

    /**
     * {@literal White Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_WHITE = "minecraft:item.banner.diagonal_left.white";

    /**
     * {@literal Yellow Per Bend Sinister}
     */
    String MC_ITEM_BANNER_DIAGONAL_LEFT_YELLOW = "minecraft:item.banner.diagonal_left.yellow";

    /**
     * {@literal Black Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_BLACK = "minecraft:item.banner.diagonal_right.black";

    /**
     * {@literal Blue Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_BLUE = "minecraft:item.banner.diagonal_right.blue";

    /**
     * {@literal Brown Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_BROWN = "minecraft:item.banner.diagonal_right.brown";

    /**
     * {@literal Cyan Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_CYAN = "minecraft:item.banner.diagonal_right.cyan";

    /**
     * {@literal Gray Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_GRAY = "minecraft:item.banner.diagonal_right.gray";

    /**
     * {@literal Green Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_GREEN = "minecraft:item.banner.diagonal_right.green";

    /**
     * {@literal Light Blue Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_LIGHTBLUE = "minecraft:item.banner.diagonal_right.lightBlue";

    /**
     * {@literal Lime Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_LIME = "minecraft:item.banner.diagonal_right.lime";

    /**
     * {@literal Magenta Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_MAGENTA = "minecraft:item.banner.diagonal_right.magenta";

    /**
     * {@literal Orange Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_ORANGE = "minecraft:item.banner.diagonal_right.orange";

    /**
     * {@literal Pink Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_PINK = "minecraft:item.banner.diagonal_right.pink";

    /**
     * {@literal Purple Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_PURPLE = "minecraft:item.banner.diagonal_right.purple";

    /**
     * {@literal Red Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_RED = "minecraft:item.banner.diagonal_right.red";

    /**
     * {@literal Light Gray Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_SILVER = "minecraft:item.banner.diagonal_right.silver";

    /**
     * {@literal White Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_WHITE = "minecraft:item.banner.diagonal_right.white";

    /**
     * {@literal Yellow Per Bend}
     */
    String MC_ITEM_BANNER_DIAGONAL_RIGHT_YELLOW = "minecraft:item.banner.diagonal_right.yellow";

    /**
     * {@literal Black Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_BLACK = "minecraft:item.banner.diagonal_up_left.black";

    /**
     * {@literal Blue Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_BLUE = "minecraft:item.banner.diagonal_up_left.blue";

    /**
     * {@literal Brown Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_BROWN = "minecraft:item.banner.diagonal_up_left.brown";

    /**
     * {@literal Cyan Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_CYAN = "minecraft:item.banner.diagonal_up_left.cyan";

    /**
     * {@literal Gray Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_GRAY = "minecraft:item.banner.diagonal_up_left.gray";

    /**
     * {@literal Green Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_GREEN = "minecraft:item.banner.diagonal_up_left.green";

    /**
     * {@literal Light Blue Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_LIGHTBLUE = "minecraft:item.banner.diagonal_up_left.lightBlue";

    /**
     * {@literal Lime Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_LIME = "minecraft:item.banner.diagonal_up_left.lime";

    /**
     * {@literal Magenta Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_MAGENTA = "minecraft:item.banner.diagonal_up_left.magenta";

    /**
     * {@literal Orange Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_ORANGE = "minecraft:item.banner.diagonal_up_left.orange";

    /**
     * {@literal Pink Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_PINK = "minecraft:item.banner.diagonal_up_left.pink";

    /**
     * {@literal Purple Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_PURPLE = "minecraft:item.banner.diagonal_up_left.purple";

    /**
     * {@literal Red Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_RED = "minecraft:item.banner.diagonal_up_left.red";

    /**
     * {@literal Light Gray Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_SILVER = "minecraft:item.banner.diagonal_up_left.silver";

    /**
     * {@literal White Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_WHITE = "minecraft:item.banner.diagonal_up_left.white";

    /**
     * {@literal Yellow Per Bend Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_LEFT_YELLOW = "minecraft:item.banner.diagonal_up_left.yellow";

    /**
     * {@literal Black Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_BLACK = "minecraft:item.banner.diagonal_up_right.black";

    /**
     * {@literal Blue Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_BLUE = "minecraft:item.banner.diagonal_up_right.blue";

    /**
     * {@literal Brown Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_BROWN = "minecraft:item.banner.diagonal_up_right.brown";

    /**
     * {@literal Cyan Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_CYAN = "minecraft:item.banner.diagonal_up_right.cyan";

    /**
     * {@literal Gray Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_GRAY = "minecraft:item.banner.diagonal_up_right.gray";

    /**
     * {@literal Green Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_GREEN = "minecraft:item.banner.diagonal_up_right.green";

    /**
     * {@literal Light Blue Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_LIGHTBLUE = "minecraft:item.banner.diagonal_up_right.lightBlue";

    /**
     * {@literal Lime Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_LIME = "minecraft:item.banner.diagonal_up_right.lime";

    /**
     * {@literal Magenta Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_MAGENTA = "minecraft:item.banner.diagonal_up_right.magenta";

    /**
     * {@literal Orange Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_ORANGE = "minecraft:item.banner.diagonal_up_right.orange";

    /**
     * {@literal Pink Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_PINK = "minecraft:item.banner.diagonal_up_right.pink";

    /**
     * {@literal Purple Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_PURPLE = "minecraft:item.banner.diagonal_up_right.purple";

    /**
     * {@literal Red Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_RED = "minecraft:item.banner.diagonal_up_right.red";

    /**
     * {@literal Light Gray Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_SILVER = "minecraft:item.banner.diagonal_up_right.silver";

    /**
     * {@literal White Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_WHITE = "minecraft:item.banner.diagonal_up_right.white";

    /**
     * {@literal Yellow Per Bend Sinister Inverted}
     */
    String MC_ITEM_BANNER_DIAGONAL_UP_RIGHT_YELLOW = "minecraft:item.banner.diagonal_up_right.yellow";

    /**
     * {@literal Black Flow}
     */
    String MC_ITEM_BANNER_FLOW_BLACK = "minecraft:item.banner.flow.black";

    /**
     * {@literal Blue Flow}
     */
    String MC_ITEM_BANNER_FLOW_BLUE = "minecraft:item.banner.flow.blue";

    /**
     * {@literal Brown Flow}
     */
    String MC_ITEM_BANNER_FLOW_BROWN = "minecraft:item.banner.flow.brown";

    /**
     * {@literal Cyan Flow}
     */
    String MC_ITEM_BANNER_FLOW_CYAN = "minecraft:item.banner.flow.cyan";

    /**
     * {@literal Gray Flow}
     */
    String MC_ITEM_BANNER_FLOW_GRAY = "minecraft:item.banner.flow.gray";

    /**
     * {@literal Green Flow}
     */
    String MC_ITEM_BANNER_FLOW_GREEN = "minecraft:item.banner.flow.green";

    /**
     * {@literal Light Blue Flow}
     */
    String MC_ITEM_BANNER_FLOW_LIGHTBLUE = "minecraft:item.banner.flow.lightBlue";

    /**
     * {@literal Lime Flow}
     */
    String MC_ITEM_BANNER_FLOW_LIME = "minecraft:item.banner.flow.lime";

    /**
     * {@literal Magenta Flow}
     */
    String MC_ITEM_BANNER_FLOW_MAGENTA = "minecraft:item.banner.flow.magenta";

    /**
     * {@literal Orange Flow}
     */
    String MC_ITEM_BANNER_FLOW_ORANGE = "minecraft:item.banner.flow.orange";

    /**
     * {@literal Pink Flow}
     */
    String MC_ITEM_BANNER_FLOW_PINK = "minecraft:item.banner.flow.pink";

    /**
     * {@literal Purple Flow}
     */
    String MC_ITEM_BANNER_FLOW_PURPLE = "minecraft:item.banner.flow.purple";

    /**
     * {@literal Red Flow}
     */
    String MC_ITEM_BANNER_FLOW_RED = "minecraft:item.banner.flow.red";

    /**
     * {@literal Light Gray Flow}
     */
    String MC_ITEM_BANNER_FLOW_SILVER = "minecraft:item.banner.flow.silver";

    /**
     * {@literal White Flow}
     */
    String MC_ITEM_BANNER_FLOW_WHITE = "minecraft:item.banner.flow.white";

    /**
     * {@literal Yellow Flow}
     */
    String MC_ITEM_BANNER_FLOW_YELLOW = "minecraft:item.banner.flow.yellow";

    /**
     * {@literal Black Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_BLACK = "minecraft:item.banner.flower.black";

    /**
     * {@literal Blue Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_BLUE = "minecraft:item.banner.flower.blue";

    /**
     * {@literal Brown Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_BROWN = "minecraft:item.banner.flower.brown";

    /**
     * {@literal Cyan Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_CYAN = "minecraft:item.banner.flower.cyan";

    /**
     * {@literal Gray Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_GRAY = "minecraft:item.banner.flower.gray";

    /**
     * {@literal Green Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_GREEN = "minecraft:item.banner.flower.green";

    /**
     * {@literal Light Blue Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_LIGHTBLUE = "minecraft:item.banner.flower.lightBlue";

    /**
     * {@literal Lime Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_LIME = "minecraft:item.banner.flower.lime";

    /**
     * {@literal Magenta Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_MAGENTA = "minecraft:item.banner.flower.magenta";

    /**
     * {@literal Orange Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_ORANGE = "minecraft:item.banner.flower.orange";

    /**
     * {@literal Pink Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_PINK = "minecraft:item.banner.flower.pink";

    /**
     * {@literal Purple Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_PURPLE = "minecraft:item.banner.flower.purple";

    /**
     * {@literal Red Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_RED = "minecraft:item.banner.flower.red";

    /**
     * {@literal Light Gray Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_SILVER = "minecraft:item.banner.flower.silver";

    /**
     * {@literal White Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_WHITE = "minecraft:item.banner.flower.white";

    /**
     * {@literal Yellow Flower Charge}
     */
    String MC_ITEM_BANNER_FLOWER_YELLOW = "minecraft:item.banner.flower.yellow";

    /**
     * {@literal Black Globe}
     */
    String MC_ITEM_BANNER_GLOBE_BLACK = "minecraft:item.banner.globe.black";

    /**
     * {@literal Blue Globe}
     */
    String MC_ITEM_BANNER_GLOBE_BLUE = "minecraft:item.banner.globe.blue";

    /**
     * {@literal Brown Globe}
     */
    String MC_ITEM_BANNER_GLOBE_BROWN = "minecraft:item.banner.globe.brown";

    /**
     * {@literal Cyan Globe}
     */
    String MC_ITEM_BANNER_GLOBE_CYAN = "minecraft:item.banner.globe.cyan";

    /**
     * {@literal Gray Globe}
     */
    String MC_ITEM_BANNER_GLOBE_GRAY = "minecraft:item.banner.globe.gray";

    /**
     * {@literal Green Globe}
     */
    String MC_ITEM_BANNER_GLOBE_GREEN = "minecraft:item.banner.globe.green";

    /**
     * {@literal Light Blue Globe}
     */
    String MC_ITEM_BANNER_GLOBE_LIGHTBLUE = "minecraft:item.banner.globe.lightBlue";

    /**
     * {@literal Lime Globe}
     */
    String MC_ITEM_BANNER_GLOBE_LIME = "minecraft:item.banner.globe.lime";

    /**
     * {@literal Magenta Globe}
     */
    String MC_ITEM_BANNER_GLOBE_MAGENTA = "minecraft:item.banner.globe.magenta";

    /**
     * {@literal Orange Globe}
     */
    String MC_ITEM_BANNER_GLOBE_ORANGE = "minecraft:item.banner.globe.orange";

    /**
     * {@literal Pink Globe}
     */
    String MC_ITEM_BANNER_GLOBE_PINK = "minecraft:item.banner.globe.pink";

    /**
     * {@literal Purple Globe}
     */
    String MC_ITEM_BANNER_GLOBE_PURPLE = "minecraft:item.banner.globe.purple";

    /**
     * {@literal Red Globe}
     */
    String MC_ITEM_BANNER_GLOBE_RED = "minecraft:item.banner.globe.red";

    /**
     * {@literal Light Gray Globe}
     */
    String MC_ITEM_BANNER_GLOBE_SILVER = "minecraft:item.banner.globe.silver";

    /**
     * {@literal White Globe}
     */
    String MC_ITEM_BANNER_GLOBE_WHITE = "minecraft:item.banner.globe.white";

    /**
     * {@literal Yellow Globe}
     */
    String MC_ITEM_BANNER_GLOBE_YELLOW = "minecraft:item.banner.globe.yellow";

    /**
     * {@literal Black Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_BLACK = "minecraft:item.banner.gradient.black";

    /**
     * {@literal Blue Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_BLUE = "minecraft:item.banner.gradient.blue";

    /**
     * {@literal Brown Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_BROWN = "minecraft:item.banner.gradient.brown";

    /**
     * {@literal Cyan Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_CYAN = "minecraft:item.banner.gradient.cyan";

    /**
     * {@literal Gray Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_GRAY = "minecraft:item.banner.gradient.gray";

    /**
     * {@literal Green Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_GREEN = "minecraft:item.banner.gradient.green";

    /**
     * {@literal Light Blue Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_LIGHTBLUE = "minecraft:item.banner.gradient.lightBlue";

    /**
     * {@literal Lime Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_LIME = "minecraft:item.banner.gradient.lime";

    /**
     * {@literal Magenta Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_MAGENTA = "minecraft:item.banner.gradient.magenta";

    /**
     * {@literal Orange Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_ORANGE = "minecraft:item.banner.gradient.orange";

    /**
     * {@literal Pink Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_PINK = "minecraft:item.banner.gradient.pink";

    /**
     * {@literal Purple Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_PURPLE = "minecraft:item.banner.gradient.purple";

    /**
     * {@literal Red Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_RED = "minecraft:item.banner.gradient.red";

    /**
     * {@literal Light Gray Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_SILVER = "minecraft:item.banner.gradient.silver";

    /**
     * {@literal White Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_WHITE = "minecraft:item.banner.gradient.white";

    /**
     * {@literal Yellow Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_YELLOW = "minecraft:item.banner.gradient.yellow";

    /**
     * {@literal Black Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_BLACK = "minecraft:item.banner.gradient_up.black";

    /**
     * {@literal Blue Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_BLUE = "minecraft:item.banner.gradient_up.blue";

    /**
     * {@literal Brown Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_BROWN = "minecraft:item.banner.gradient_up.brown";

    /**
     * {@literal Cyan Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_CYAN = "minecraft:item.banner.gradient_up.cyan";

    /**
     * {@literal Gray Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_GRAY = "minecraft:item.banner.gradient_up.gray";

    /**
     * {@literal Green Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_GREEN = "minecraft:item.banner.gradient_up.green";

    /**
     * {@literal Light Blue Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_LIGHTBLUE = "minecraft:item.banner.gradient_up.lightBlue";

    /**
     * {@literal Lime Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_LIME = "minecraft:item.banner.gradient_up.lime";

    /**
     * {@literal Magenta Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_MAGENTA = "minecraft:item.banner.gradient_up.magenta";

    /**
     * {@literal Orange Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_ORANGE = "minecraft:item.banner.gradient_up.orange";

    /**
     * {@literal Pink Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_PINK = "minecraft:item.banner.gradient_up.pink";

    /**
     * {@literal Purple Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_PURPLE = "minecraft:item.banner.gradient_up.purple";

    /**
     * {@literal Red Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_RED = "minecraft:item.banner.gradient_up.red";

    /**
     * {@literal Light Gray Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_SILVER = "minecraft:item.banner.gradient_up.silver";

    /**
     * {@literal White Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_WHITE = "minecraft:item.banner.gradient_up.white";

    /**
     * {@literal Yellow Base Gradient}
     */
    String MC_ITEM_BANNER_GRADIENT_UP_YELLOW = "minecraft:item.banner.gradient_up.yellow";

    /**
     * {@literal Gray Banner}
     */
    String MC_ITEM_BANNER_GRAY_NAME = "minecraft:item.banner.gray.name";

    /**
     * {@literal Green Banner}
     */
    String MC_ITEM_BANNER_GREEN_NAME = "minecraft:item.banner.green.name";

    /**
     * {@literal Black Guster}
     */
    String MC_ITEM_BANNER_GUSTER_BLACK = "minecraft:item.banner.guster.black";

    /**
     * {@literal Blue Guster}
     */
    String MC_ITEM_BANNER_GUSTER_BLUE = "minecraft:item.banner.guster.blue";

    /**
     * {@literal Brown Guster}
     */
    String MC_ITEM_BANNER_GUSTER_BROWN = "minecraft:item.banner.guster.brown";

    /**
     * {@literal Cyan Guster}
     */
    String MC_ITEM_BANNER_GUSTER_CYAN = "minecraft:item.banner.guster.cyan";

    /**
     * {@literal Gray Guster}
     */
    String MC_ITEM_BANNER_GUSTER_GRAY = "minecraft:item.banner.guster.gray";

    /**
     * {@literal Green Guster}
     */
    String MC_ITEM_BANNER_GUSTER_GREEN = "minecraft:item.banner.guster.green";

    /**
     * {@literal Light Blue Guster}
     */
    String MC_ITEM_BANNER_GUSTER_LIGHTBLUE = "minecraft:item.banner.guster.lightBlue";

    /**
     * {@literal Lime Guster}
     */
    String MC_ITEM_BANNER_GUSTER_LIME = "minecraft:item.banner.guster.lime";

    /**
     * {@literal Magenta Guster}
     */
    String MC_ITEM_BANNER_GUSTER_MAGENTA = "minecraft:item.banner.guster.magenta";

    /**
     * {@literal Orange Guster}
     */
    String MC_ITEM_BANNER_GUSTER_ORANGE = "minecraft:item.banner.guster.orange";

    /**
     * {@literal Pink Guster}
     */
    String MC_ITEM_BANNER_GUSTER_PINK = "minecraft:item.banner.guster.pink";

    /**
     * {@literal Purple Guster}
     */
    String MC_ITEM_BANNER_GUSTER_PURPLE = "minecraft:item.banner.guster.purple";

    /**
     * {@literal Red Guster}
     */
    String MC_ITEM_BANNER_GUSTER_RED = "minecraft:item.banner.guster.red";

    /**
     * {@literal Light Gray Guster}
     */
    String MC_ITEM_BANNER_GUSTER_SILVER = "minecraft:item.banner.guster.silver";

    /**
     * {@literal White Guster}
     */
    String MC_ITEM_BANNER_GUSTER_WHITE = "minecraft:item.banner.guster.white";

    /**
     * {@literal Yellow Guster}
     */
    String MC_ITEM_BANNER_GUSTER_YELLOW = "minecraft:item.banner.guster.yellow";

    /**
     * {@literal Black Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BLACK = "minecraft:item.banner.half_horizontal.black";

    /**
     * {@literal Blue Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BLUE = "minecraft:item.banner.half_horizontal.blue";

    /**
     * {@literal Brown Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BROWN = "minecraft:item.banner.half_horizontal.brown";

    /**
     * {@literal Cyan Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_CYAN = "minecraft:item.banner.half_horizontal.cyan";

    /**
     * {@literal Gray Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_GRAY = "minecraft:item.banner.half_horizontal.gray";

    /**
     * {@literal Green Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_GREEN = "minecraft:item.banner.half_horizontal.green";

    /**
     * {@literal Light Blue Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_LIGHTBLUE = "minecraft:item.banner.half_horizontal.lightBlue";

    /**
     * {@literal Lime Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_LIME = "minecraft:item.banner.half_horizontal.lime";

    /**
     * {@literal Magenta Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_MAGENTA = "minecraft:item.banner.half_horizontal.magenta";

    /**
     * {@literal Orange Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_ORANGE = "minecraft:item.banner.half_horizontal.orange";

    /**
     * {@literal Pink Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_PINK = "minecraft:item.banner.half_horizontal.pink";

    /**
     * {@literal Purple Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_PURPLE = "minecraft:item.banner.half_horizontal.purple";

    /**
     * {@literal Red Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_RED = "minecraft:item.banner.half_horizontal.red";

    /**
     * {@literal Light Gray Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_SILVER = "minecraft:item.banner.half_horizontal.silver";

    /**
     * {@literal White Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_WHITE = "minecraft:item.banner.half_horizontal.white";

    /**
     * {@literal Yellow Per Fess}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_YELLOW = "minecraft:item.banner.half_horizontal.yellow";

    /**
     * {@literal Black Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_BLACK = "minecraft:item.banner.half_horizontal_bottom.black";

    /**
     * {@literal Blue Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_BLUE = "minecraft:item.banner.half_horizontal_bottom.blue";

    /**
     * {@literal Brown Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_BROWN = "minecraft:item.banner.half_horizontal_bottom.brown";

    /**
     * {@literal Cyan Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_CYAN = "minecraft:item.banner.half_horizontal_bottom.cyan";

    /**
     * {@literal Gray Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_GRAY = "minecraft:item.banner.half_horizontal_bottom.gray";

    /**
     * {@literal Green Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_GREEN = "minecraft:item.banner.half_horizontal_bottom.green";

    /**
     * {@literal Light Blue Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_LIGHTBLUE = "minecraft:item.banner.half_horizontal_bottom.lightBlue";

    /**
     * {@literal Lime Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_LIME = "minecraft:item.banner.half_horizontal_bottom.lime";

    /**
     * {@literal Magenta Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_MAGENTA = "minecraft:item.banner.half_horizontal_bottom.magenta";

    /**
     * {@literal Orange Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_ORANGE = "minecraft:item.banner.half_horizontal_bottom.orange";

    /**
     * {@literal Pink Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_PINK = "minecraft:item.banner.half_horizontal_bottom.pink";

    /**
     * {@literal Purple Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_PURPLE = "minecraft:item.banner.half_horizontal_bottom.purple";

    /**
     * {@literal Red Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_RED = "minecraft:item.banner.half_horizontal_bottom.red";

    /**
     * {@literal Light Gray Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_SILVER = "minecraft:item.banner.half_horizontal_bottom.silver";

    /**
     * {@literal White Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_WHITE = "minecraft:item.banner.half_horizontal_bottom.white";

    /**
     * {@literal Yellow Per Fess Inverted}
     */
    String MC_ITEM_BANNER_HALF_HORIZONTAL_BOTTOM_YELLOW = "minecraft:item.banner.half_horizontal_bottom.yellow";

    /**
     * {@literal Black Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_BLACK = "minecraft:item.banner.half_vertical.black";

    /**
     * {@literal Blue Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_BLUE = "minecraft:item.banner.half_vertical.blue";

    /**
     * {@literal Brown Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_BROWN = "minecraft:item.banner.half_vertical.brown";

    /**
     * {@literal Cyan Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_CYAN = "minecraft:item.banner.half_vertical.cyan";

    /**
     * {@literal Gray Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_GRAY = "minecraft:item.banner.half_vertical.gray";

    /**
     * {@literal Green Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_GREEN = "minecraft:item.banner.half_vertical.green";

    /**
     * {@literal Light Blue Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_LIGHTBLUE = "minecraft:item.banner.half_vertical.lightBlue";

    /**
     * {@literal Lime Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_LIME = "minecraft:item.banner.half_vertical.lime";

    /**
     * {@literal Magenta Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_MAGENTA = "minecraft:item.banner.half_vertical.magenta";

    /**
     * {@literal Orange Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_ORANGE = "minecraft:item.banner.half_vertical.orange";

    /**
     * {@literal Pink Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_PINK = "minecraft:item.banner.half_vertical.pink";

    /**
     * {@literal Purple Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_PURPLE = "minecraft:item.banner.half_vertical.purple";

    /**
     * {@literal Red Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RED = "minecraft:item.banner.half_vertical.red";

    /**
     * {@literal Light Gray Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_SILVER = "minecraft:item.banner.half_vertical.silver";

    /**
     * {@literal White Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_WHITE = "minecraft:item.banner.half_vertical.white";

    /**
     * {@literal Yellow Per Pale}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_YELLOW = "minecraft:item.banner.half_vertical.yellow";

    /**
     * {@literal Black Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_BLACK = "minecraft:item.banner.half_vertical_right.black";

    /**
     * {@literal Blue Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_BLUE = "minecraft:item.banner.half_vertical_right.blue";

    /**
     * {@literal Brown Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_BROWN = "minecraft:item.banner.half_vertical_right.brown";

    /**
     * {@literal Cyan Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_CYAN = "minecraft:item.banner.half_vertical_right.cyan";

    /**
     * {@literal Gray Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_GRAY = "minecraft:item.banner.half_vertical_right.gray";

    /**
     * {@literal Green Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_GREEN = "minecraft:item.banner.half_vertical_right.green";

    /**
     * {@literal Light Blue Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_LIGHTBLUE = "minecraft:item.banner.half_vertical_right.lightBlue";

    /**
     * {@literal Lime Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_LIME = "minecraft:item.banner.half_vertical_right.lime";

    /**
     * {@literal Magenta Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_MAGENTA = "minecraft:item.banner.half_vertical_right.magenta";

    /**
     * {@literal Orange Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_ORANGE = "minecraft:item.banner.half_vertical_right.orange";

    /**
     * {@literal Pink Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_PINK = "minecraft:item.banner.half_vertical_right.pink";

    /**
     * {@literal Purple Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_PURPLE = "minecraft:item.banner.half_vertical_right.purple";

    /**
     * {@literal Red Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_RED = "minecraft:item.banner.half_vertical_right.red";

    /**
     * {@literal Light Gray Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_SILVER = "minecraft:item.banner.half_vertical_right.silver";

    /**
     * {@literal White Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_WHITE = "minecraft:item.banner.half_vertical_right.white";

    /**
     * {@literal Yellow Per Pale Inverted}
     */
    String MC_ITEM_BANNER_HALF_VERTICAL_RIGHT_YELLOW = "minecraft:item.banner.half_vertical_right.yellow";

    /**
     * {@literal Illager}
     */
    String MC_ITEM_BANNER_ILLAGER = "minecraft:item.banner.illager";

    /**
     * {@literal Ominous Banner}
     */
    String MC_ITEM_BANNER_ILLAGER_CAPTAIN_NAME = "minecraft:item.banner.illager_captain.name";

    /**
     * {@literal Light Blue Banner}
     */
    String MC_ITEM_BANNER_LIGHTBLUE_NAME = "minecraft:item.banner.lightBlue.name";

    /**
     * {@literal Lime Banner}
     */
    String MC_ITEM_BANNER_LIME_NAME = "minecraft:item.banner.lime.name";

    /**
     * {@literal Magenta Banner}
     */
    String MC_ITEM_BANNER_MAGENTA_NAME = "minecraft:item.banner.magenta.name";

    /**
     * {@literal Black Thing}
     */
    String MC_ITEM_BANNER_MOJANG_BLACK = "minecraft:item.banner.mojang.black";

    /**
     * {@literal Blue Thing}
     */
    String MC_ITEM_BANNER_MOJANG_BLUE = "minecraft:item.banner.mojang.blue";

    /**
     * {@literal Brown Thing}
     */
    String MC_ITEM_BANNER_MOJANG_BROWN = "minecraft:item.banner.mojang.brown";

    /**
     * {@literal Cyan Thing}
     */
    String MC_ITEM_BANNER_MOJANG_CYAN = "minecraft:item.banner.mojang.cyan";

    /**
     * {@literal Gray Thing}
     */
    String MC_ITEM_BANNER_MOJANG_GRAY = "minecraft:item.banner.mojang.gray";

    /**
     * {@literal Green Thing}
     */
    String MC_ITEM_BANNER_MOJANG_GREEN = "minecraft:item.banner.mojang.green";

    /**
     * {@literal Light Blue Thing}
     */
    String MC_ITEM_BANNER_MOJANG_LIGHTBLUE = "minecraft:item.banner.mojang.lightBlue";

    /**
     * {@literal Lime Thing}
     */
    String MC_ITEM_BANNER_MOJANG_LIME = "minecraft:item.banner.mojang.lime";

    /**
     * {@literal Magenta Thing}
     */
    String MC_ITEM_BANNER_MOJANG_MAGENTA = "minecraft:item.banner.mojang.magenta";

    /**
     * {@literal Orange Thing}
     */
    String MC_ITEM_BANNER_MOJANG_ORANGE = "minecraft:item.banner.mojang.orange";

    /**
     * {@literal Pink Thing}
     */
    String MC_ITEM_BANNER_MOJANG_PINK = "minecraft:item.banner.mojang.pink";

    /**
     * {@literal Purple Thing}
     */
    String MC_ITEM_BANNER_MOJANG_PURPLE = "minecraft:item.banner.mojang.purple";

    /**
     * {@literal Red Thing}
     */
    String MC_ITEM_BANNER_MOJANG_RED = "minecraft:item.banner.mojang.red";

    /**
     * {@literal Light Gray Thing}
     */
    String MC_ITEM_BANNER_MOJANG_SILVER = "minecraft:item.banner.mojang.silver";

    /**
     * {@literal White Thing}
     */
    String MC_ITEM_BANNER_MOJANG_WHITE = "minecraft:item.banner.mojang.white";

    /**
     * {@literal Yellow Thing}
     */
    String MC_ITEM_BANNER_MOJANG_YELLOW = "minecraft:item.banner.mojang.yellow";

    /**
     * {@literal Orange Banner}
     */
    String MC_ITEM_BANNER_ORANGE_NAME = "minecraft:item.banner.orange.name";

    /**
     * {@literal Black Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_BLACK = "minecraft:item.banner.piglin.black";

    /**
     * {@literal Blue Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_BLUE = "minecraft:item.banner.piglin.blue";

    /**
     * {@literal Brown Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_BROWN = "minecraft:item.banner.piglin.brown";

    /**
     * {@literal Cyan Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_CYAN = "minecraft:item.banner.piglin.cyan";

    /**
     * {@literal Gray Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_GRAY = "minecraft:item.banner.piglin.gray";

    /**
     * {@literal Green Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_GREEN = "minecraft:item.banner.piglin.green";

    /**
     * {@literal Light Blue Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_LIGHTBLUE = "minecraft:item.banner.piglin.lightBlue";

    /**
     * {@literal Lime Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_LIME = "minecraft:item.banner.piglin.lime";

    /**
     * {@literal Magenta Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_MAGENTA = "minecraft:item.banner.piglin.magenta";

    /**
     * {@literal Orange Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_ORANGE = "minecraft:item.banner.piglin.orange";

    /**
     * {@literal Pink Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_PINK = "minecraft:item.banner.piglin.pink";

    /**
     * {@literal Purple Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_PURPLE = "minecraft:item.banner.piglin.purple";

    /**
     * {@literal Red Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_RED = "minecraft:item.banner.piglin.red";

    /**
     * {@literal Light Gray Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_SILVER = "minecraft:item.banner.piglin.silver";

    /**
     * {@literal White Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_WHITE = "minecraft:item.banner.piglin.white";

    /**
     * {@literal Yellow Snout}
     */
    String MC_ITEM_BANNER_PIGLIN_YELLOW = "minecraft:item.banner.piglin.yellow";

    /**
     * {@literal Pink Banner}
     */
    String MC_ITEM_BANNER_PINK_NAME = "minecraft:item.banner.pink.name";

    /**
     * {@literal Purple Banner}
     */
    String MC_ITEM_BANNER_PURPLE_NAME = "minecraft:item.banner.purple.name";

    /**
     * {@literal Red Banner}
     */
    String MC_ITEM_BANNER_RED_NAME = "minecraft:item.banner.red.name";

    /**
     * {@literal Black Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_BLACK = "minecraft:item.banner.rhombus.black";

    /**
     * {@literal Blue Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_BLUE = "minecraft:item.banner.rhombus.blue";

    /**
     * {@literal Brown Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_BROWN = "minecraft:item.banner.rhombus.brown";

    /**
     * {@literal Cyan Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_CYAN = "minecraft:item.banner.rhombus.cyan";

    /**
     * {@literal Gray Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_GRAY = "minecraft:item.banner.rhombus.gray";

    /**
     * {@literal Green Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_GREEN = "minecraft:item.banner.rhombus.green";

    /**
     * {@literal Light Blue Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_LIGHTBLUE = "minecraft:item.banner.rhombus.lightBlue";

    /**
     * {@literal Lime Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_LIME = "minecraft:item.banner.rhombus.lime";

    /**
     * {@literal Magenta Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_MAGENTA = "minecraft:item.banner.rhombus.magenta";

    /**
     * {@literal Orange Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_ORANGE = "minecraft:item.banner.rhombus.orange";

    /**
     * {@literal Pink Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_PINK = "minecraft:item.banner.rhombus.pink";

    /**
     * {@literal Purple Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_PURPLE = "minecraft:item.banner.rhombus.purple";

    /**
     * {@literal Red Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_RED = "minecraft:item.banner.rhombus.red";

    /**
     * {@literal Light Gray Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_SILVER = "minecraft:item.banner.rhombus.silver";

    /**
     * {@literal White Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_WHITE = "minecraft:item.banner.rhombus.white";

    /**
     * {@literal Yellow Lozenge}
     */
    String MC_ITEM_BANNER_RHOMBUS_YELLOW = "minecraft:item.banner.rhombus.yellow";

    /**
     * {@literal Light Gray Banner}
     */
    String MC_ITEM_BANNER_SILVER_NAME = "minecraft:item.banner.silver.name";

    /**
     * {@literal Black Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_BLACK = "minecraft:item.banner.skull.black";

    /**
     * {@literal Blue Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_BLUE = "minecraft:item.banner.skull.blue";

    /**
     * {@literal Brown Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_BROWN = "minecraft:item.banner.skull.brown";

    /**
     * {@literal Cyan Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_CYAN = "minecraft:item.banner.skull.cyan";

    /**
     * {@literal Gray Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_GRAY = "minecraft:item.banner.skull.gray";

    /**
     * {@literal Green Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_GREEN = "minecraft:item.banner.skull.green";

    /**
     * {@literal Light Blue Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_LIGHTBLUE = "minecraft:item.banner.skull.lightBlue";

    /**
     * {@literal Lime Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_LIME = "minecraft:item.banner.skull.lime";

    /**
     * {@literal Magenta Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_MAGENTA = "minecraft:item.banner.skull.magenta";

    /**
     * {@literal Orange Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_ORANGE = "minecraft:item.banner.skull.orange";

    /**
     * {@literal Pink Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_PINK = "minecraft:item.banner.skull.pink";

    /**
     * {@literal Purple Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_PURPLE = "minecraft:item.banner.skull.purple";

    /**
     * {@literal Red Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_RED = "minecraft:item.banner.skull.red";

    /**
     * {@literal Light Gray Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_SILVER = "minecraft:item.banner.skull.silver";

    /**
     * {@literal White Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_WHITE = "minecraft:item.banner.skull.white";

    /**
     * {@literal Yellow Skull Charge}
     */
    String MC_ITEM_BANNER_SKULL_YELLOW = "minecraft:item.banner.skull.yellow";

    /**
     * {@literal Black Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_BLACK = "minecraft:item.banner.small_stripes.black";

    /**
     * {@literal Blue Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_BLUE = "minecraft:item.banner.small_stripes.blue";

    /**
     * {@literal Brown Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_BROWN = "minecraft:item.banner.small_stripes.brown";

    /**
     * {@literal Cyan Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_CYAN = "minecraft:item.banner.small_stripes.cyan";

    /**
     * {@literal Gray Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_GRAY = "minecraft:item.banner.small_stripes.gray";

    /**
     * {@literal Green Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_GREEN = "minecraft:item.banner.small_stripes.green";

    /**
     * {@literal Light Blue Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_LIGHTBLUE = "minecraft:item.banner.small_stripes.lightBlue";

    /**
     * {@literal Lime Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_LIME = "minecraft:item.banner.small_stripes.lime";

    /**
     * {@literal Magenta Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_MAGENTA = "minecraft:item.banner.small_stripes.magenta";

    /**
     * {@literal Orange Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_ORANGE = "minecraft:item.banner.small_stripes.orange";

    /**
     * {@literal Pink Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_PINK = "minecraft:item.banner.small_stripes.pink";

    /**
     * {@literal Purple Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_PURPLE = "minecraft:item.banner.small_stripes.purple";

    /**
     * {@literal Red Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_RED = "minecraft:item.banner.small_stripes.red";

    /**
     * {@literal Light Gray Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_SILVER = "minecraft:item.banner.small_stripes.silver";

    /**
     * {@literal White Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_WHITE = "minecraft:item.banner.small_stripes.white";

    /**
     * {@literal Yellow Paly}
     */
    String MC_ITEM_BANNER_SMALL_STRIPES_YELLOW = "minecraft:item.banner.small_stripes.yellow";

    /**
     * {@literal Black Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_BLACK = "minecraft:item.banner.square_bottom_left.black";

    /**
     * {@literal Blue Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_BLUE = "minecraft:item.banner.square_bottom_left.blue";

    /**
     * {@literal Brown Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_BROWN = "minecraft:item.banner.square_bottom_left.brown";

    /**
     * {@literal Cyan Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_CYAN = "minecraft:item.banner.square_bottom_left.cyan";

    /**
     * {@literal Gray Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_GRAY = "minecraft:item.banner.square_bottom_left.gray";

    /**
     * {@literal Green Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_GREEN = "minecraft:item.banner.square_bottom_left.green";

    /**
     * {@literal Light Blue Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_LIGHTBLUE = "minecraft:item.banner.square_bottom_left.lightBlue";

    /**
     * {@literal Lime Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_LIME = "minecraft:item.banner.square_bottom_left.lime";

    /**
     * {@literal Magenta Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_MAGENTA = "minecraft:item.banner.square_bottom_left.magenta";

    /**
     * {@literal Orange Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_ORANGE = "minecraft:item.banner.square_bottom_left.orange";

    /**
     * {@literal Pink Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_PINK = "minecraft:item.banner.square_bottom_left.pink";

    /**
     * {@literal Purple Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_PURPLE = "minecraft:item.banner.square_bottom_left.purple";

    /**
     * {@literal Red Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_RED = "minecraft:item.banner.square_bottom_left.red";

    /**
     * {@literal Light Gray Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_SILVER = "minecraft:item.banner.square_bottom_left.silver";

    /**
     * {@literal White Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_WHITE = "minecraft:item.banner.square_bottom_left.white";

    /**
     * {@literal Yellow Base Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_LEFT_YELLOW = "minecraft:item.banner.square_bottom_left.yellow";

    /**
     * {@literal Black Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_BLACK = "minecraft:item.banner.square_bottom_right.black";

    /**
     * {@literal Blue Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_BLUE = "minecraft:item.banner.square_bottom_right.blue";

    /**
     * {@literal Brown Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_BROWN = "minecraft:item.banner.square_bottom_right.brown";

    /**
     * {@literal Cyan Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_CYAN = "minecraft:item.banner.square_bottom_right.cyan";

    /**
     * {@literal Gray Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_GRAY = "minecraft:item.banner.square_bottom_right.gray";

    /**
     * {@literal Green Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_GREEN = "minecraft:item.banner.square_bottom_right.green";

    /**
     * {@literal Light Blue Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_LIGHTBLUE = "minecraft:item.banner.square_bottom_right.lightBlue";

    /**
     * {@literal Lime Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_LIME = "minecraft:item.banner.square_bottom_right.lime";

    /**
     * {@literal Magenta Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_MAGENTA = "minecraft:item.banner.square_bottom_right.magenta";

    /**
     * {@literal Orange Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_ORANGE = "minecraft:item.banner.square_bottom_right.orange";

    /**
     * {@literal Pink Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_PINK = "minecraft:item.banner.square_bottom_right.pink";

    /**
     * {@literal Purple Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_PURPLE = "minecraft:item.banner.square_bottom_right.purple";

    /**
     * {@literal Red Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_RED = "minecraft:item.banner.square_bottom_right.red";

    /**
     * {@literal Light Gray Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_SILVER = "minecraft:item.banner.square_bottom_right.silver";

    /**
     * {@literal White Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_WHITE = "minecraft:item.banner.square_bottom_right.white";

    /**
     * {@literal Yellow Base Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_BOTTOM_RIGHT_YELLOW = "minecraft:item.banner.square_bottom_right.yellow";

    /**
     * {@literal Black Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_BLACK = "minecraft:item.banner.square_top_left.black";

    /**
     * {@literal Blue Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_BLUE = "minecraft:item.banner.square_top_left.blue";

    /**
     * {@literal Brown Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_BROWN = "minecraft:item.banner.square_top_left.brown";

    /**
     * {@literal Cyan Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_CYAN = "minecraft:item.banner.square_top_left.cyan";

    /**
     * {@literal Gray Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_GRAY = "minecraft:item.banner.square_top_left.gray";

    /**
     * {@literal Green Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_GREEN = "minecraft:item.banner.square_top_left.green";

    /**
     * {@literal Light Blue Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_LIGHTBLUE = "minecraft:item.banner.square_top_left.lightBlue";

    /**
     * {@literal Lime Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_LIME = "minecraft:item.banner.square_top_left.lime";

    /**
     * {@literal Magenta Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_MAGENTA = "minecraft:item.banner.square_top_left.magenta";

    /**
     * {@literal Orange Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_ORANGE = "minecraft:item.banner.square_top_left.orange";

    /**
     * {@literal Pink Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_PINK = "minecraft:item.banner.square_top_left.pink";

    /**
     * {@literal Purple Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_PURPLE = "minecraft:item.banner.square_top_left.purple";

    /**
     * {@literal Red Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_RED = "minecraft:item.banner.square_top_left.red";

    /**
     * {@literal Light Gray Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_SILVER = "minecraft:item.banner.square_top_left.silver";

    /**
     * {@literal White Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_WHITE = "minecraft:item.banner.square_top_left.white";

    /**
     * {@literal Yellow Chief Dexter Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_LEFT_YELLOW = "minecraft:item.banner.square_top_left.yellow";

    /**
     * {@literal Black Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_BLACK = "minecraft:item.banner.square_top_right.black";

    /**
     * {@literal Blue Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_BLUE = "minecraft:item.banner.square_top_right.blue";

    /**
     * {@literal Brown Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_BROWN = "minecraft:item.banner.square_top_right.brown";

    /**
     * {@literal Cyan Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_CYAN = "minecraft:item.banner.square_top_right.cyan";

    /**
     * {@literal Gray Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_GRAY = "minecraft:item.banner.square_top_right.gray";

    /**
     * {@literal Green Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_GREEN = "minecraft:item.banner.square_top_right.green";

    /**
     * {@literal Light Blue Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_LIGHTBLUE = "minecraft:item.banner.square_top_right.lightBlue";

    /**
     * {@literal Lime Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_LIME = "minecraft:item.banner.square_top_right.lime";

    /**
     * {@literal Magenta Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_MAGENTA = "minecraft:item.banner.square_top_right.magenta";

    /**
     * {@literal Orange Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_ORANGE = "minecraft:item.banner.square_top_right.orange";

    /**
     * {@literal Pink Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_PINK = "minecraft:item.banner.square_top_right.pink";

    /**
     * {@literal Purple Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_PURPLE = "minecraft:item.banner.square_top_right.purple";

    /**
     * {@literal Red Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_RED = "minecraft:item.banner.square_top_right.red";

    /**
     * {@literal Light Gray Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_SILVER = "minecraft:item.banner.square_top_right.silver";

    /**
     * {@literal White Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_WHITE = "minecraft:item.banner.square_top_right.white";

    /**
     * {@literal Yellow Chief Sinister Canton}
     */
    String MC_ITEM_BANNER_SQUARE_TOP_RIGHT_YELLOW = "minecraft:item.banner.square_top_right.yellow";

    /**
     * {@literal Black Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_BLACK = "minecraft:item.banner.straight_cross.black";

    /**
     * {@literal Blue Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_BLUE = "minecraft:item.banner.straight_cross.blue";

    /**
     * {@literal Brown Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_BROWN = "minecraft:item.banner.straight_cross.brown";

    /**
     * {@literal Cyan Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_CYAN = "minecraft:item.banner.straight_cross.cyan";

    /**
     * {@literal Gray Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_GRAY = "minecraft:item.banner.straight_cross.gray";

    /**
     * {@literal Green Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_GREEN = "minecraft:item.banner.straight_cross.green";

    /**
     * {@literal Light Blue Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_LIGHTBLUE = "minecraft:item.banner.straight_cross.lightBlue";

    /**
     * {@literal Lime Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_LIME = "minecraft:item.banner.straight_cross.lime";

    /**
     * {@literal Magenta Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_MAGENTA = "minecraft:item.banner.straight_cross.magenta";

    /**
     * {@literal Orange Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_ORANGE = "minecraft:item.banner.straight_cross.orange";

    /**
     * {@literal Pink Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_PINK = "minecraft:item.banner.straight_cross.pink";

    /**
     * {@literal Purple Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_PURPLE = "minecraft:item.banner.straight_cross.purple";

    /**
     * {@literal Red Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_RED = "minecraft:item.banner.straight_cross.red";

    /**
     * {@literal Light Gray Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_SILVER = "minecraft:item.banner.straight_cross.silver";

    /**
     * {@literal White Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_WHITE = "minecraft:item.banner.straight_cross.white";

    /**
     * {@literal Yellow Cross}
     */
    String MC_ITEM_BANNER_STRAIGHT_CROSS_YELLOW = "minecraft:item.banner.straight_cross.yellow";

    /**
     * {@literal Black Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_BLACK = "minecraft:item.banner.stripe_bottom.black";

    /**
     * {@literal Blue Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_BLUE = "minecraft:item.banner.stripe_bottom.blue";

    /**
     * {@literal Brown Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_BROWN = "minecraft:item.banner.stripe_bottom.brown";

    /**
     * {@literal Cyan Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_CYAN = "minecraft:item.banner.stripe_bottom.cyan";

    /**
     * {@literal Gray Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_GRAY = "minecraft:item.banner.stripe_bottom.gray";

    /**
     * {@literal Green Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_GREEN = "minecraft:item.banner.stripe_bottom.green";

    /**
     * {@literal Light Blue Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_LIGHTBLUE = "minecraft:item.banner.stripe_bottom.lightBlue";

    /**
     * {@literal Lime Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_LIME = "minecraft:item.banner.stripe_bottom.lime";

    /**
     * {@literal Magenta Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_MAGENTA = "minecraft:item.banner.stripe_bottom.magenta";

    /**
     * {@literal Orange Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_ORANGE = "minecraft:item.banner.stripe_bottom.orange";

    /**
     * {@literal Pink Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_PINK = "minecraft:item.banner.stripe_bottom.pink";

    /**
     * {@literal Purple Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_PURPLE = "minecraft:item.banner.stripe_bottom.purple";

    /**
     * {@literal Red Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_RED = "minecraft:item.banner.stripe_bottom.red";

    /**
     * {@literal Light Gray Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_SILVER = "minecraft:item.banner.stripe_bottom.silver";

    /**
     * {@literal White Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_WHITE = "minecraft:item.banner.stripe_bottom.white";

    /**
     * {@literal Yellow Base Fess}
     */
    String MC_ITEM_BANNER_STRIPE_BOTTOM_YELLOW = "minecraft:item.banner.stripe_bottom.yellow";

    /**
     * {@literal Black Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_BLACK = "minecraft:item.banner.stripe_center.black";

    /**
     * {@literal Blue Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_BLUE = "minecraft:item.banner.stripe_center.blue";

    /**
     * {@literal Brown Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_BROWN = "minecraft:item.banner.stripe_center.brown";

    /**
     * {@literal Cyan Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_CYAN = "minecraft:item.banner.stripe_center.cyan";

    /**
     * {@literal Gray Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_GRAY = "minecraft:item.banner.stripe_center.gray";

    /**
     * {@literal Green Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_GREEN = "minecraft:item.banner.stripe_center.green";

    /**
     * {@literal Light Blue Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_LIGHTBLUE = "minecraft:item.banner.stripe_center.lightBlue";

    /**
     * {@literal Lime Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_LIME = "minecraft:item.banner.stripe_center.lime";

    /**
     * {@literal Magenta Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_MAGENTA = "minecraft:item.banner.stripe_center.magenta";

    /**
     * {@literal Orange Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_ORANGE = "minecraft:item.banner.stripe_center.orange";

    /**
     * {@literal Pink Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_PINK = "minecraft:item.banner.stripe_center.pink";

    /**
     * {@literal Purple Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_PURPLE = "minecraft:item.banner.stripe_center.purple";

    /**
     * {@literal Red Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_RED = "minecraft:item.banner.stripe_center.red";

    /**
     * {@literal Light Gray Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_SILVER = "minecraft:item.banner.stripe_center.silver";

    /**
     * {@literal White Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_WHITE = "minecraft:item.banner.stripe_center.white";

    /**
     * {@literal Yellow Pale}
     */
    String MC_ITEM_BANNER_STRIPE_CENTER_YELLOW = "minecraft:item.banner.stripe_center.yellow";

    /**
     * {@literal Black Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_BLACK = "minecraft:item.banner.stripe_downleft.black";

    /**
     * {@literal Blue Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_BLUE = "minecraft:item.banner.stripe_downleft.blue";

    /**
     * {@literal Brown Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_BROWN = "minecraft:item.banner.stripe_downleft.brown";

    /**
     * {@literal Cyan Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_CYAN = "minecraft:item.banner.stripe_downleft.cyan";

    /**
     * {@literal Gray Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_GRAY = "minecraft:item.banner.stripe_downleft.gray";

    /**
     * {@literal Green Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_GREEN = "minecraft:item.banner.stripe_downleft.green";

    /**
     * {@literal Light Blue Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_LIGHTBLUE = "minecraft:item.banner.stripe_downleft.lightBlue";

    /**
     * {@literal Lime Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_LIME = "minecraft:item.banner.stripe_downleft.lime";

    /**
     * {@literal Magenta Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_MAGENTA = "minecraft:item.banner.stripe_downleft.magenta";

    /**
     * {@literal Orange Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_ORANGE = "minecraft:item.banner.stripe_downleft.orange";

    /**
     * {@literal Pink Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_PINK = "minecraft:item.banner.stripe_downleft.pink";

    /**
     * {@literal Purple Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_PURPLE = "minecraft:item.banner.stripe_downleft.purple";

    /**
     * {@literal Red Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_RED = "minecraft:item.banner.stripe_downleft.red";

    /**
     * {@literal Light Gray Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_SILVER = "minecraft:item.banner.stripe_downleft.silver";

    /**
     * {@literal White Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_WHITE = "minecraft:item.banner.stripe_downleft.white";

    /**
     * {@literal Yellow Bend Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNLEFT_YELLOW = "minecraft:item.banner.stripe_downleft.yellow";

    /**
     * {@literal Black Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_BLACK = "minecraft:item.banner.stripe_downright.black";

    /**
     * {@literal Blue Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_BLUE = "minecraft:item.banner.stripe_downright.blue";

    /**
     * {@literal Brown Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_BROWN = "minecraft:item.banner.stripe_downright.brown";

    /**
     * {@literal Cyan Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_CYAN = "minecraft:item.banner.stripe_downright.cyan";

    /**
     * {@literal Gray Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_GRAY = "minecraft:item.banner.stripe_downright.gray";

    /**
     * {@literal Green Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_GREEN = "minecraft:item.banner.stripe_downright.green";

    /**
     * {@literal Light Blue Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_LIGHTBLUE = "minecraft:item.banner.stripe_downright.lightBlue";

    /**
     * {@literal Lime Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_LIME = "minecraft:item.banner.stripe_downright.lime";

    /**
     * {@literal Magenta Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_MAGENTA = "minecraft:item.banner.stripe_downright.magenta";

    /**
     * {@literal Orange Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_ORANGE = "minecraft:item.banner.stripe_downright.orange";

    /**
     * {@literal Pink Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_PINK = "minecraft:item.banner.stripe_downright.pink";

    /**
     * {@literal Purple Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_PURPLE = "minecraft:item.banner.stripe_downright.purple";

    /**
     * {@literal Red Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_RED = "minecraft:item.banner.stripe_downright.red";

    /**
     * {@literal Light Gray Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_SILVER = "minecraft:item.banner.stripe_downright.silver";

    /**
     * {@literal White Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_WHITE = "minecraft:item.banner.stripe_downright.white";

    /**
     * {@literal Yellow Bend}
     */
    String MC_ITEM_BANNER_STRIPE_DOWNRIGHT_YELLOW = "minecraft:item.banner.stripe_downright.yellow";

    /**
     * {@literal Black Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_BLACK = "minecraft:item.banner.stripe_left.black";

    /**
     * {@literal Blue Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_BLUE = "minecraft:item.banner.stripe_left.blue";

    /**
     * {@literal Brown Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_BROWN = "minecraft:item.banner.stripe_left.brown";

    /**
     * {@literal Cyan Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_CYAN = "minecraft:item.banner.stripe_left.cyan";

    /**
     * {@literal Gray Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_GRAY = "minecraft:item.banner.stripe_left.gray";

    /**
     * {@literal Green Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_GREEN = "minecraft:item.banner.stripe_left.green";

    /**
     * {@literal Light Blue Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_LIGHTBLUE = "minecraft:item.banner.stripe_left.lightBlue";

    /**
     * {@literal Lime Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_LIME = "minecraft:item.banner.stripe_left.lime";

    /**
     * {@literal Magenta Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_MAGENTA = "minecraft:item.banner.stripe_left.magenta";

    /**
     * {@literal Orange Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_ORANGE = "minecraft:item.banner.stripe_left.orange";

    /**
     * {@literal Pink Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_PINK = "minecraft:item.banner.stripe_left.pink";

    /**
     * {@literal Purple Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_PURPLE = "minecraft:item.banner.stripe_left.purple";

    /**
     * {@literal Red Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_RED = "minecraft:item.banner.stripe_left.red";

    /**
     * {@literal Light Gray Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_SILVER = "minecraft:item.banner.stripe_left.silver";

    /**
     * {@literal White Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_WHITE = "minecraft:item.banner.stripe_left.white";

    /**
     * {@literal Yellow Pale Dexter}
     */
    String MC_ITEM_BANNER_STRIPE_LEFT_YELLOW = "minecraft:item.banner.stripe_left.yellow";

    /**
     * {@literal Black Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_BLACK = "minecraft:item.banner.stripe_middle.black";

    /**
     * {@literal Blue Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_BLUE = "minecraft:item.banner.stripe_middle.blue";

    /**
     * {@literal Brown Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_BROWN = "minecraft:item.banner.stripe_middle.brown";

    /**
     * {@literal Cyan Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_CYAN = "minecraft:item.banner.stripe_middle.cyan";

    /**
     * {@literal Gray Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_GRAY = "minecraft:item.banner.stripe_middle.gray";

    /**
     * {@literal Green Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_GREEN = "minecraft:item.banner.stripe_middle.green";

    /**
     * {@literal Light Blue Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_LIGHTBLUE = "minecraft:item.banner.stripe_middle.lightBlue";

    /**
     * {@literal Lime Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_LIME = "minecraft:item.banner.stripe_middle.lime";

    /**
     * {@literal Magenta Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_MAGENTA = "minecraft:item.banner.stripe_middle.magenta";

    /**
     * {@literal Orange Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_ORANGE = "minecraft:item.banner.stripe_middle.orange";

    /**
     * {@literal Pink Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_PINK = "minecraft:item.banner.stripe_middle.pink";

    /**
     * {@literal Purple Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_PURPLE = "minecraft:item.banner.stripe_middle.purple";

    /**
     * {@literal Red Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_RED = "minecraft:item.banner.stripe_middle.red";

    /**
     * {@literal Light Gray Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_SILVER = "minecraft:item.banner.stripe_middle.silver";

    /**
     * {@literal White Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_WHITE = "minecraft:item.banner.stripe_middle.white";

    /**
     * {@literal Yellow Fess}
     */
    String MC_ITEM_BANNER_STRIPE_MIDDLE_YELLOW = "minecraft:item.banner.stripe_middle.yellow";

    /**
     * {@literal Black Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_BLACK = "minecraft:item.banner.stripe_right.black";

    /**
     * {@literal Blue Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_BLUE = "minecraft:item.banner.stripe_right.blue";

    /**
     * {@literal Brown Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_BROWN = "minecraft:item.banner.stripe_right.brown";

    /**
     * {@literal Cyan Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_CYAN = "minecraft:item.banner.stripe_right.cyan";

    /**
     * {@literal Gray Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_GRAY = "minecraft:item.banner.stripe_right.gray";

    /**
     * {@literal Green Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_GREEN = "minecraft:item.banner.stripe_right.green";

    /**
     * {@literal Light Blue Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_LIGHTBLUE = "minecraft:item.banner.stripe_right.lightBlue";

    /**
     * {@literal Lime Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_LIME = "minecraft:item.banner.stripe_right.lime";

    /**
     * {@literal Magenta Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_MAGENTA = "minecraft:item.banner.stripe_right.magenta";

    /**
     * {@literal Orange Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_ORANGE = "minecraft:item.banner.stripe_right.orange";

    /**
     * {@literal Pink Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_PINK = "minecraft:item.banner.stripe_right.pink";

    /**
     * {@literal Purple Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_PURPLE = "minecraft:item.banner.stripe_right.purple";

    /**
     * {@literal Red Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_RED = "minecraft:item.banner.stripe_right.red";

    /**
     * {@literal Light Gray Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_SILVER = "minecraft:item.banner.stripe_right.silver";

    /**
     * {@literal White Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_WHITE = "minecraft:item.banner.stripe_right.white";

    /**
     * {@literal Yellow Pale Sinister}
     */
    String MC_ITEM_BANNER_STRIPE_RIGHT_YELLOW = "minecraft:item.banner.stripe_right.yellow";

    /**
     * {@literal Black Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_BLACK = "minecraft:item.banner.stripe_top.black";

    /**
     * {@literal Blue Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_BLUE = "minecraft:item.banner.stripe_top.blue";

    /**
     * {@literal Brown Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_BROWN = "minecraft:item.banner.stripe_top.brown";

    /**
     * {@literal Cyan Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_CYAN = "minecraft:item.banner.stripe_top.cyan";

    /**
     * {@literal Gray Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_GRAY = "minecraft:item.banner.stripe_top.gray";

    /**
     * {@literal Green Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_GREEN = "minecraft:item.banner.stripe_top.green";

    /**
     * {@literal Light Blue Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_LIGHTBLUE = "minecraft:item.banner.stripe_top.lightBlue";

    /**
     * {@literal Lime Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_LIME = "minecraft:item.banner.stripe_top.lime";

    /**
     * {@literal Magenta Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_MAGENTA = "minecraft:item.banner.stripe_top.magenta";

    /**
     * {@literal Orange Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_ORANGE = "minecraft:item.banner.stripe_top.orange";

    /**
     * {@literal Pink Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_PINK = "minecraft:item.banner.stripe_top.pink";

    /**
     * {@literal Purple Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_PURPLE = "minecraft:item.banner.stripe_top.purple";

    /**
     * {@literal Red Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_RED = "minecraft:item.banner.stripe_top.red";

    /**
     * {@literal Light Gray Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_SILVER = "minecraft:item.banner.stripe_top.silver";

    /**
     * {@literal White Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_WHITE = "minecraft:item.banner.stripe_top.white";

    /**
     * {@literal Yellow Chief Fess}
     */
    String MC_ITEM_BANNER_STRIPE_TOP_YELLOW = "minecraft:item.banner.stripe_top.yellow";

    /**
     * {@literal Black Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_BLACK = "minecraft:item.banner.triangle_bottom.black";

    /**
     * {@literal Blue Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_BLUE = "minecraft:item.banner.triangle_bottom.blue";

    /**
     * {@literal Brown Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_BROWN = "minecraft:item.banner.triangle_bottom.brown";

    /**
     * {@literal Cyan Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_CYAN = "minecraft:item.banner.triangle_bottom.cyan";

    /**
     * {@literal Gray Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_GRAY = "minecraft:item.banner.triangle_bottom.gray";

    /**
     * {@literal Green Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_GREEN = "minecraft:item.banner.triangle_bottom.green";

    /**
     * {@literal Light Blue Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_LIGHTBLUE = "minecraft:item.banner.triangle_bottom.lightBlue";

    /**
     * {@literal Lime Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_LIME = "minecraft:item.banner.triangle_bottom.lime";

    /**
     * {@literal Magenta Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_MAGENTA = "minecraft:item.banner.triangle_bottom.magenta";

    /**
     * {@literal Orange Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_ORANGE = "minecraft:item.banner.triangle_bottom.orange";

    /**
     * {@literal Pink Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_PINK = "minecraft:item.banner.triangle_bottom.pink";

    /**
     * {@literal Purple Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_PURPLE = "minecraft:item.banner.triangle_bottom.purple";

    /**
     * {@literal Red Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_RED = "minecraft:item.banner.triangle_bottom.red";

    /**
     * {@literal Light Gray Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_SILVER = "minecraft:item.banner.triangle_bottom.silver";

    /**
     * {@literal White Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_WHITE = "minecraft:item.banner.triangle_bottom.white";

    /**
     * {@literal Yellow Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_BOTTOM_YELLOW = "minecraft:item.banner.triangle_bottom.yellow";

    /**
     * {@literal Black Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_BLACK = "minecraft:item.banner.triangle_top.black";

    /**
     * {@literal Blue Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_BLUE = "minecraft:item.banner.triangle_top.blue";

    /**
     * {@literal Brown Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_BROWN = "minecraft:item.banner.triangle_top.brown";

    /**
     * {@literal Cyan Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_CYAN = "minecraft:item.banner.triangle_top.cyan";

    /**
     * {@literal Gray Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_GRAY = "minecraft:item.banner.triangle_top.gray";

    /**
     * {@literal Green Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_GREEN = "minecraft:item.banner.triangle_top.green";

    /**
     * {@literal Light Blue Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_LIGHTBLUE = "minecraft:item.banner.triangle_top.lightBlue";

    /**
     * {@literal Lime Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_LIME = "minecraft:item.banner.triangle_top.lime";

    /**
     * {@literal Magenta Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_MAGENTA = "minecraft:item.banner.triangle_top.magenta";

    /**
     * {@literal Orange Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_ORANGE = "minecraft:item.banner.triangle_top.orange";

    /**
     * {@literal Pink Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_PINK = "minecraft:item.banner.triangle_top.pink";

    /**
     * {@literal Purple Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_PURPLE = "minecraft:item.banner.triangle_top.purple";

    /**
     * {@literal Red Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_RED = "minecraft:item.banner.triangle_top.red";

    /**
     * {@literal Light Gray Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_SILVER = "minecraft:item.banner.triangle_top.silver";

    /**
     * {@literal White Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_WHITE = "minecraft:item.banner.triangle_top.white";

    /**
     * {@literal Yellow Inverted Chevron}
     */
    String MC_ITEM_BANNER_TRIANGLE_TOP_YELLOW = "minecraft:item.banner.triangle_top.yellow";

    /**
     * {@literal Black Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_BLACK = "minecraft:item.banner.triangles_bottom.black";

    /**
     * {@literal Blue Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_BLUE = "minecraft:item.banner.triangles_bottom.blue";

    /**
     * {@literal Brown Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_BROWN = "minecraft:item.banner.triangles_bottom.brown";

    /**
     * {@literal Cyan Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_CYAN = "minecraft:item.banner.triangles_bottom.cyan";

    /**
     * {@literal Gray Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_GRAY = "minecraft:item.banner.triangles_bottom.gray";

    /**
     * {@literal Green Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_GREEN = "minecraft:item.banner.triangles_bottom.green";

    /**
     * {@literal Light Blue Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_LIGHTBLUE = "minecraft:item.banner.triangles_bottom.lightBlue";

    /**
     * {@literal Lime Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_LIME = "minecraft:item.banner.triangles_bottom.lime";

    /**
     * {@literal Magenta Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_MAGENTA = "minecraft:item.banner.triangles_bottom.magenta";

    /**
     * {@literal Orange Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_ORANGE = "minecraft:item.banner.triangles_bottom.orange";

    /**
     * {@literal Pink Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_PINK = "minecraft:item.banner.triangles_bottom.pink";

    /**
     * {@literal Purple Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_PURPLE = "minecraft:item.banner.triangles_bottom.purple";

    /**
     * {@literal Red Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_RED = "minecraft:item.banner.triangles_bottom.red";

    /**
     * {@literal Light Gray Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_SILVER = "minecraft:item.banner.triangles_bottom.silver";

    /**
     * {@literal White Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_WHITE = "minecraft:item.banner.triangles_bottom.white";

    /**
     * {@literal Yellow Base Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_BOTTOM_YELLOW = "minecraft:item.banner.triangles_bottom.yellow";

    /**
     * {@literal Black Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_BLACK = "minecraft:item.banner.triangles_top.black";

    /**
     * {@literal Blue Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_BLUE = "minecraft:item.banner.triangles_top.blue";

    /**
     * {@literal Brown Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_BROWN = "minecraft:item.banner.triangles_top.brown";

    /**
     * {@literal Cyan Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_CYAN = "minecraft:item.banner.triangles_top.cyan";

    /**
     * {@literal Gray Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_GRAY = "minecraft:item.banner.triangles_top.gray";

    /**
     * {@literal Green Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_GREEN = "minecraft:item.banner.triangles_top.green";

    /**
     * {@literal Light Blue Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_LIGHTBLUE = "minecraft:item.banner.triangles_top.lightBlue";

    /**
     * {@literal Lime Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_LIME = "minecraft:item.banner.triangles_top.lime";

    /**
     * {@literal Magenta Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_MAGENTA = "minecraft:item.banner.triangles_top.magenta";

    /**
     * {@literal Orange Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_ORANGE = "minecraft:item.banner.triangles_top.orange";

    /**
     * {@literal Pink Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_PINK = "minecraft:item.banner.triangles_top.pink";

    /**
     * {@literal Purple Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_PURPLE = "minecraft:item.banner.triangles_top.purple";

    /**
     * {@literal Red Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_RED = "minecraft:item.banner.triangles_top.red";

    /**
     * {@literal Light Gray Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_SILVER = "minecraft:item.banner.triangles_top.silver";

    /**
     * {@literal White Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_WHITE = "minecraft:item.banner.triangles_top.white";

    /**
     * {@literal Yellow Chief Indented}
     */
    String MC_ITEM_BANNER_TRIANGLES_TOP_YELLOW = "minecraft:item.banner.triangles_top.yellow";

    /**
     * {@literal White Banner}
     */
    String MC_ITEM_BANNER_WHITE_NAME = "minecraft:item.banner.white.name";

    /**
     * {@literal Yellow Banner}
     */
    String MC_ITEM_BANNER_YELLOW_NAME = "minecraft:item.banner.yellow.name";

    /**
     * {@literal Field Masoned}
     */
    String MC_ITEM_BANNER_PATTERN_BRICKS = "minecraft:item.banner_pattern.bricks";

    /**
     * {@literal Creeper Charge}
     */
    String MC_ITEM_BANNER_PATTERN_CREEPER = "minecraft:item.banner_pattern.creeper";

    /**
     * {@literal Flow}
     */
    String MC_ITEM_BANNER_PATTERN_FLOW = "minecraft:item.banner_pattern.flow";

    /**
     * {@literal Flower Charge}
     */
    String MC_ITEM_BANNER_PATTERN_FLOWER = "minecraft:item.banner_pattern.flower";

    /**
     * {@literal Globe}
     */
    String MC_ITEM_BANNER_PATTERN_GLOBE = "minecraft:item.banner_pattern.globe";

    /**
     * {@literal Guster}
     */
    String MC_ITEM_BANNER_PATTERN_GUSTER = "minecraft:item.banner_pattern.guster";

    /**
     * {@literal Banner Pattern}
     */
    String MC_ITEM_BANNER_PATTERN_NAME = "minecraft:item.banner_pattern.name";

    /**
     * {@literal Snout}
     */
    String MC_ITEM_BANNER_PATTERN_PIGLIN = "minecraft:item.banner_pattern.piglin";

    /**
     * {@literal Skull Charge}
     */
    String MC_ITEM_BANNER_PATTERN_SKULL = "minecraft:item.banner_pattern.skull";

    /**
     * {@literal Thing}
     */
    String MC_ITEM_BANNER_PATTERN_THING = "minecraft:item.banner_pattern.thing";

    /**
     * {@literal Bordure Indented}
     */
    String MC_ITEM_BANNER_PATTERN_VINES = "minecraft:item.banner_pattern.vines";

    /**
     * {@literal Black Bed}
     */
    String MC_ITEM_BED_BLACK_NAME = "minecraft:item.bed.black.name";

    /**
     * {@literal Blue Bed}
     */
    String MC_ITEM_BED_BLUE_NAME = "minecraft:item.bed.blue.name";

    /**
     * {@literal Brown Bed}
     */
    String MC_ITEM_BED_BROWN_NAME = "minecraft:item.bed.brown.name";

    /**
     * {@literal Cyan Bed}
     */
    String MC_ITEM_BED_CYAN_NAME = "minecraft:item.bed.cyan.name";

    /**
     * {@literal Gray Bed}
     */
    String MC_ITEM_BED_GRAY_NAME = "minecraft:item.bed.gray.name";

    /**
     * {@literal Green Bed}
     */
    String MC_ITEM_BED_GREEN_NAME = "minecraft:item.bed.green.name";

    /**
     * {@literal Light Blue Bed}
     */
    String MC_ITEM_BED_LIGHTBLUE_NAME = "minecraft:item.bed.lightBlue.name";

    /**
     * {@literal Lime Bed}
     */
    String MC_ITEM_BED_LIME_NAME = "minecraft:item.bed.lime.name";

    /**
     * {@literal Magenta Bed}
     */
    String MC_ITEM_BED_MAGENTA_NAME = "minecraft:item.bed.magenta.name";

    /**
     * {@literal Orange Bed}
     */
    String MC_ITEM_BED_ORANGE_NAME = "minecraft:item.bed.orange.name";

    /**
     * {@literal Pink Bed}
     */
    String MC_ITEM_BED_PINK_NAME = "minecraft:item.bed.pink.name";

    /**
     * {@literal Purple Bed}
     */
    String MC_ITEM_BED_PURPLE_NAME = "minecraft:item.bed.purple.name";

    /**
     * {@literal Red Bed}
     */
    String MC_ITEM_BED_RED_NAME = "minecraft:item.bed.red.name";

    /**
     * {@literal Light Gray Bed}
     */
    String MC_ITEM_BED_SILVER_NAME = "minecraft:item.bed.silver.name";

    /**
     * {@literal White Bed}
     */
    String MC_ITEM_BED_WHITE_NAME = "minecraft:item.bed.white.name";

    /**
     * {@literal Yellow Bed}
     */
    String MC_ITEM_BED_YELLOW_NAME = "minecraft:item.bed.yellow.name";

    /**
     * {@literal Raw Beef}
     */
    String MC_ITEM_BEEF_NAME = "minecraft:item.beef.name";

    /**
     * {@literal Beetroot}
     */
    String MC_ITEM_BEETROOT_NAME = "minecraft:item.beetroot.name";

    /**
     * {@literal Beetroot Seeds}
     */
    String MC_ITEM_BEETROOT_SEEDS_NAME = "minecraft:item.beetroot_seeds.name";

    /**
     * {@literal Beetroot Soup}
     */
    String MC_ITEM_BEETROOT_SOUP_NAME = "minecraft:item.beetroot_soup.name";

    /**
     * {@literal Bell}
     */
    String MC_ITEM_BELL_NAME = "minecraft:item.bell.name";

    /**
     * {@literal Birch Door}
     */
    String MC_ITEM_BIRCH_DOOR_NAME = "minecraft:item.birch_door.name";

    /**
     * {@literal Birch Hanging Sign}
     */
    String MC_ITEM_BIRCH_HANGING_SIGN_NAME = "minecraft:item.birch_hanging_sign.name";

    /**
     * {@literal Birch Sign}
     */
    String MC_ITEM_BIRCH_SIGN_NAME = "minecraft:item.birch_sign.name";

    /**
     * {@literal Black Bundle}
     */
    String MC_ITEM_BLACK_BUNDLE = "minecraft:item.black_bundle";

    /**
     * {@literal Black Harness}
     */
    String MC_ITEM_BLACK_HARNESS_NAME = "minecraft:item.black_harness.name";

    /**
     * {@literal Blade Pottery Sherd}
     */
    String MC_ITEM_BLADE_POTTERY_SHERD_NAME = "minecraft:item.blade_pottery_sherd.name";

    /**
     * {@literal Blaze Powder}
     */
    String MC_ITEM_BLAZE_POWDER_NAME = "minecraft:item.blaze_powder.name";

    /**
     * {@literal Blaze Rod}
     */
    String MC_ITEM_BLAZE_ROD_NAME = "minecraft:item.blaze_rod.name";

    /**
     * {@literal Blue Bundle}
     */
    String MC_ITEM_BLUE_BUNDLE = "minecraft:item.blue_bundle";

    /**
     * {@literal Blue Egg}
     */
    String MC_ITEM_BLUE_EGG_NAME = "minecraft:item.blue_egg.name";

    /**
     * {@literal Blue Harness}
     */
    String MC_ITEM_BLUE_HARNESS_NAME = "minecraft:item.blue_harness.name";

    /**
     * {@literal Acacia Boat}
     */
    String MC_ITEM_BOAT_ACACIA_NAME = "minecraft:item.boat.acacia.name";

    /**
     * {@literal Bamboo Raft}
     */
    String MC_ITEM_BOAT_BAMBOO_NAME = "minecraft:item.boat.bamboo.name";

    /**
     * {@literal Dark Oak Boat}
     */
    String MC_ITEM_BOAT_BIG_OAK_NAME = "minecraft:item.boat.big_oak.name";

    /**
     * {@literal Birch Boat}
     */
    String MC_ITEM_BOAT_BIRCH_NAME = "minecraft:item.boat.birch.name";

    /**
     * {@literal Cherry Boat}
     */
    String MC_ITEM_BOAT_CHERRY_NAME = "minecraft:item.boat.cherry.name";

    /**
     * {@literal Jungle Boat}
     */
    String MC_ITEM_BOAT_JUNGLE_NAME = "minecraft:item.boat.jungle.name";

    /**
     * {@literal Mangrove Boat}
     */
    String MC_ITEM_BOAT_MANGROVE_NAME = "minecraft:item.boat.mangrove.name";

    /**
     * {@literal Oak Boat}
     */
    String MC_ITEM_BOAT_OAK_NAME = "minecraft:item.boat.oak.name";

    /**
     * {@literal Pale Oak Boat}
     */
    String MC_ITEM_BOAT_PALE_OAK_NAME = "minecraft:item.boat.pale_oak.name";

    /**
     * {@literal Spruce Boat}
     */
    String MC_ITEM_BOAT_SPRUCE_NAME = "minecraft:item.boat.spruce.name";

    /**
     * {@literal Bolt Armor Trim}
     */
    String MC_ITEM_BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.bolt_armor_trim_smithing_template.name";

    /**
     * {@literal Bone}
     */
    String MC_ITEM_BONE_NAME = "minecraft:item.bone.name";

    /**
     * {@literal Book}
     */
    String MC_ITEM_BOOK_NAME = "minecraft:item.book.name";

    /**
     * {@literal Bordure Indented Banner Pattern}
     */
    String MC_ITEM_BORDURE_INDENTED_BANNER_PATTERN_NAME = "minecraft:item.bordure_indented_banner_pattern.name";

    /**
     * {@literal Bow}
     */
    String MC_ITEM_BOW_NAME = "minecraft:item.bow.name";

    /**
     * {@literal Bowl}
     */
    String MC_ITEM_BOWL_NAME = "minecraft:item.bowl.name";

    /**
     * {@literal Bread}
     */
    String MC_ITEM_BREAD_NAME = "minecraft:item.bread.name";

    /**
     * {@literal Breeze Rod}
     */
    String MC_ITEM_BREEZE_ROD_NAME = "minecraft:item.breeze_rod.name";

    /**
     * {@literal Brewer Pottery Sherd}
     */
    String MC_ITEM_BREWER_POTTERY_SHERD_NAME = "minecraft:item.brewer_pottery_sherd.name";

    /**
     * {@literal Brewing Stand}
     */
    String MC_ITEM_BREWING_STAND_NAME = "minecraft:item.brewing_stand.name";

    /**
     * {@literal Brick}
     */
    String MC_ITEM_BRICK_NAME = "minecraft:item.brick.name";

    /**
     * {@literal Brown Bundle}
     */
    String MC_ITEM_BROWN_BUNDLE = "minecraft:item.brown_bundle";

    /**
     * {@literal Brown Egg}
     */
    String MC_ITEM_BROWN_EGG_NAME = "minecraft:item.brown_egg.name";

    /**
     * {@literal Brown Harness}
     */
    String MC_ITEM_BROWN_HARNESS_NAME = "minecraft:item.brown_harness.name";

    /**
     * {@literal Brush}
     */
    String MC_ITEM_BRUSH_NAME = "minecraft:item.brush.name";

    /**
     * {@literal Bucket}
     */
    String MC_ITEM_BUCKET_NAME = "minecraft:item.bucket.name";

    /**
     * {@literal Bucket of Axolotl}
     */
    String MC_ITEM_BUCKETAXOLOTL_NAME = "minecraft:item.bucketAxolotl.name";

    /**
     * {@literal Bucket of }
     */
    String MC_ITEM_BUCKETCUSTOMFISH_NAME = "minecraft:item.bucketCustomFish.name";

    /**
     * {@literal Bucket of Cod}
     */
    String MC_ITEM_BUCKETFISH_NAME = "minecraft:item.bucketFish.name";

    /**
     * {@literal Lava Bucket}
     */
    String MC_ITEM_BUCKETLAVA_NAME = "minecraft:item.bucketLava.name";

    /**
     * {@literal Powder Snow Bucket}
     */
    String MC_ITEM_BUCKETPOWDERSNOW_NAME = "minecraft:item.bucketPowderSnow.name";

    /**
     * {@literal Bucket of Pufferfish}
     */
    String MC_ITEM_BUCKETPUFFER_NAME = "minecraft:item.bucketPuffer.name";

    /**
     * {@literal Bucket of Salmon}
     */
    String MC_ITEM_BUCKETSALMON_NAME = "minecraft:item.bucketSalmon.name";

    /**
     * {@literal Bucket of Tadpole}
     */
    String MC_ITEM_BUCKETTADPOLE_NAME = "minecraft:item.bucketTadpole.name";

    /**
     * {@literal Bucket of Tropical Fish}
     */
    String MC_ITEM_BUCKETTROPICAL_NAME = "minecraft:item.bucketTropical.name";

    /**
     * {@literal Water Bucket}
     */
    String MC_ITEM_BUCKETWATER_NAME = "minecraft:item.bucketWater.name";

    /**
     * {@literal Bundle}
     */
    String MC_ITEM_BUNDLE = "minecraft:item.bundle";

    /**
     * {@literal Empty}
     */
    String MC_ITEM_BUNDLE_EMPTY = "minecraft:item.bundle.empty";

    /**
     * {@literal Can hold a mixed stack of items}
     */
    String MC_ITEM_BUNDLE_EMPTY_DESCRIPTION = "minecraft:item.bundle.empty.description";

    /**
     * {@literal Full}
     */
    String MC_ITEM_BUNDLE_FULL = "minecraft:item.bundle.full";

    /**
     * {@literal %s left}
     */
    String MC_ITEM_BUNDLE_FULLNESS = "minecraft:item.bundle.fullness";

    /**
     * {@literal Burn Pottery Sherd}
     */
    String MC_ITEM_BURN_POTTERY_SHERD_NAME = "minecraft:item.burn_pottery_sherd.name";

    /**
     * {@literal Cake}
     */
    String MC_ITEM_CAKE_NAME = "minecraft:item.cake.name";

    /**
     * {@literal Camera}
     */
    String MC_ITEM_CAMERA_NAME = "minecraft:item.camera.name";

    /**
     * {@literal Can break:}
     */
    String MC_ITEM_CANBREAK = "minecraft:item.canBreak";

    /**
     * {@literal Can be placed on:}
     */
    String MC_ITEM_CANPLACE = "minecraft:item.canPlace";

    /**
     * {@literal Carrot}
     */
    String MC_ITEM_CARROT_NAME = "minecraft:item.carrot.name";

    /**
     * {@literal Carrot on a Stick}
     */
    String MC_ITEM_CARROTONASTICK_NAME = "minecraft:item.carrotOnAStick.name";

    /**
     * {@literal Cauldron}
     */
    String MC_ITEM_CAULDRON_NAME = "minecraft:item.cauldron.name";

    /**
     * {@literal Chainmail Boots}
     */
    String MC_ITEM_CHAINMAIL_BOOTS_NAME = "minecraft:item.chainmail_boots.name";

    /**
     * {@literal Chainmail Chestplate}
     */
    String MC_ITEM_CHAINMAIL_CHESTPLATE_NAME = "minecraft:item.chainmail_chestplate.name";

    /**
     * {@literal Chainmail Helmet}
     */
    String MC_ITEM_CHAINMAIL_HELMET_NAME = "minecraft:item.chainmail_helmet.name";

    /**
     * {@literal Chainmail Leggings}
     */
    String MC_ITEM_CHAINMAIL_LEGGINGS_NAME = "minecraft:item.chainmail_leggings.name";

    /**
     * {@literal Charcoal}
     */
    String MC_ITEM_CHARCOAL_NAME = "minecraft:item.charcoal.name";

    /**
     * {@literal Cherry Door}
     */
    String MC_ITEM_CHERRY_DOOR_NAME = "minecraft:item.cherry_door.name";

    /**
     * {@literal Cherry Hanging Sign}
     */
    String MC_ITEM_CHERRY_HANGING_SIGN_NAME = "minecraft:item.cherry_hanging_sign.name";

    /**
     * {@literal Cherry Sign}
     */
    String MC_ITEM_CHERRY_SIGN_NAME = "minecraft:item.cherry_sign.name";

    /**
     * {@literal Acacia Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_ACACIA_NAME = "minecraft:item.chest_boat.acacia.name";

    /**
     * {@literal Bamboo Raft with Chest}
     */
    String MC_ITEM_CHEST_BOAT_BAMBOO_NAME = "minecraft:item.chest_boat.bamboo.name";

    /**
     * {@literal Dark Oak Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_BIG_OAK_NAME = "minecraft:item.chest_boat.big_oak.name";

    /**
     * {@literal Birch Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_BIRCH_NAME = "minecraft:item.chest_boat.birch.name";

    /**
     * {@literal Cherry Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_CHERRY_NAME = "minecraft:item.chest_boat.cherry.name";

    /**
     * {@literal Jungle Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_JUNGLE_NAME = "minecraft:item.chest_boat.jungle.name";

    /**
     * {@literal Mangrove Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_MANGROVE_NAME = "minecraft:item.chest_boat.mangrove.name";

    /**
     * {@literal Oak Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_OAK_NAME = "minecraft:item.chest_boat.oak.name";

    /**
     * {@literal Pale Oak Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_PALE_OAK_NAME = "minecraft:item.chest_boat.pale_oak.name";

    /**
     * {@literal Spruce Boat with Chest}
     */
    String MC_ITEM_CHEST_BOAT_SPRUCE_NAME = "minecraft:item.chest_boat.spruce.name";

    /**
     * {@literal Minecart with Chest}
     */
    String MC_ITEM_CHEST_MINECART_NAME = "minecraft:item.chest_minecart.name";

    /**
     * {@literal Raw Chicken}
     */
    String MC_ITEM_CHICKEN_NAME = "minecraft:item.chicken.name";

    /**
     * {@literal Chorus Fruit}
     */
    String MC_ITEM_CHORUS_FRUIT_NAME = "minecraft:item.chorus_fruit.name";

    /**
     * {@literal Popped Chorus Fruit}
     */
    String MC_ITEM_CHORUS_FRUIT_POPPED_NAME = "minecraft:item.chorus_fruit_popped.name";

    /**
     * {@literal Clay Ball}
     */
    String MC_ITEM_CLAY_BALL_NAME = "minecraft:item.clay_ball.name";

    /**
     * {@literal Clock}
     */
    String MC_ITEM_CLOCK_NAME = "minecraft:item.clock.name";

    /**
     * {@literal Tropical Fish}
     */
    String MC_ITEM_CLOWNFISH_NAME = "minecraft:item.clownfish.name";

    /**
     * {@literal Coal}
     */
    String MC_ITEM_COAL_NAME = "minecraft:item.coal.name";

    /**
     * {@literal Coast Armor Trim}
     */
    String MC_ITEM_COAST_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.coast_armor_trim_smithing_template.name";

    /**
     * {@literal Minecart with Command Block}
     */
    String MC_ITEM_COMMAND_BLOCK_MINECART_NAME = "minecraft:item.command_block_minecart.name";

    /**
     * {@literal Redstone Comparator}
     */
    String MC_ITEM_COMPARATOR_NAME = "minecraft:item.comparator.name";

    /**
     * {@literal Compass}
     */
    String MC_ITEM_COMPASS_NAME = "minecraft:item.compass.name";

    /**
     * {@literal Steak}
     */
    String MC_ITEM_COOKED_BEEF_NAME = "minecraft:item.cooked_beef.name";

    /**
     * {@literal Cooked Chicken}
     */
    String MC_ITEM_COOKED_CHICKEN_NAME = "minecraft:item.cooked_chicken.name";

    /**
     * {@literal Cooked Cod}
     */
    String MC_ITEM_COOKED_FISH_NAME = "minecraft:item.cooked_fish.name";

    /**
     * {@literal Cooked Porkchop}
     */
    String MC_ITEM_COOKED_PORKCHOP_NAME = "minecraft:item.cooked_porkchop.name";

    /**
     * {@literal Cooked Rabbit}
     */
    String MC_ITEM_COOKED_RABBIT_NAME = "minecraft:item.cooked_rabbit.name";

    /**
     * {@literal Cooked Salmon}
     */
    String MC_ITEM_COOKED_SALMON_NAME = "minecraft:item.cooked_salmon.name";

    /**
     * {@literal Cookie}
     */
    String MC_ITEM_COOKIE_NAME = "minecraft:item.cookie.name";

    /**
     * {@literal Copper Axe}
     */
    String MC_ITEM_COPPER_AXE_NAME = "minecraft:item.copper_axe.name";

    /**
     * {@literal Copper Boots}
     */
    String MC_ITEM_COPPER_BOOTS_NAME = "minecraft:item.copper_boots.name";

    /**
     * {@literal Copper Chestplate}
     */
    String MC_ITEM_COPPER_CHESTPLATE_NAME = "minecraft:item.copper_chestplate.name";

    /**
     * {@literal Copper Door}
     */
    String MC_ITEM_COPPER_DOOR_NAME = "minecraft:item.copper_door.name";

    /**
     * {@literal Copper Helmet}
     */
    String MC_ITEM_COPPER_HELMET_NAME = "minecraft:item.copper_helmet.name";

    /**
     * {@literal Copper Hoe}
     */
    String MC_ITEM_COPPER_HOE_NAME = "minecraft:item.copper_hoe.name";

    /**
     * {@literal Copper Horse Armor}
     */
    String MC_ITEM_COPPER_HORSE_ARMOR_NAME = "minecraft:item.copper_horse_armor.name";

    /**
     * {@literal Copper Ingot}
     */
    String MC_ITEM_COPPER_INGOT_NAME = "minecraft:item.copper_ingot.name";

    /**
     * {@literal Copper Leggings}
     */
    String MC_ITEM_COPPER_LEGGINGS_NAME = "minecraft:item.copper_leggings.name";

    /**
     * {@literal Copper Nautilus Armor}
     */
    String MC_ITEM_COPPER_NAUTILUS_ARMOR_NAME = "minecraft:item.copper_nautilus_armor.name";

    /**
     * {@literal Copper Nugget}
     */
    String MC_ITEM_COPPER_NUGGET_NAME = "minecraft:item.copper_nugget.name";

    /**
     * {@literal Copper Pickaxe}
     */
    String MC_ITEM_COPPER_PICKAXE_NAME = "minecraft:item.copper_pickaxe.name";

    /**
     * {@literal Copper Shovel}
     */
    String MC_ITEM_COPPER_SHOVEL_NAME = "minecraft:item.copper_shovel.name";

    /**
     * {@literal Copper Spear}
     */
    String MC_ITEM_COPPER_SPEAR_NAME = "minecraft:item.copper_spear.name";

    /**
     * {@literal Copper Sword}
     */
    String MC_ITEM_COPPER_SWORD_NAME = "minecraft:item.copper_sword.name";

    /**
     * {@literal Creeper Charge Banner Pattern}
     */
    String MC_ITEM_CREEPER_BANNER_PATTERN_NAME = "minecraft:item.creeper_banner_pattern.name";

    /**
     * {@literal Crimson Door}
     */
    String MC_ITEM_CRIMSON_DOOR_NAME = "minecraft:item.crimson_door.name";

    /**
     * {@literal Crimson Hanging Sign}
     */
    String MC_ITEM_CRIMSON_HANGING_SIGN_NAME = "minecraft:item.crimson_hanging_sign.name";

    /**
     * {@literal Crimson Sign}
     */
    String MC_ITEM_CRIMSON_SIGN_NAME = "minecraft:item.crimson_sign.name";

    /**
     * {@literal Crossbow}
     */
    String MC_ITEM_CROSSBOW_NAME = "minecraft:item.crossbow.name";

    /**
     * {@literal Has Custom Properties}
     */
    String MC_ITEM_CUSTOMPROPERTIES = "minecraft:item.customProperties";

    /**
     * {@literal Cyan Bundle}
     */
    String MC_ITEM_CYAN_BUNDLE = "minecraft:item.cyan_bundle";

    /**
     * {@literal Cyan Harness}
     */
    String MC_ITEM_CYAN_HARNESS_NAME = "minecraft:item.cyan_harness.name";

    /**
     * {@literal Danger Pottery Sherd}
     */
    String MC_ITEM_DANGER_POTTERY_SHERD_NAME = "minecraft:item.danger_pottery_sherd.name";

    /**
     * {@literal Dark Oak Door}
     */
    String MC_ITEM_DARK_OAK_DOOR_NAME = "minecraft:item.dark_oak_door.name";

    /**
     * {@literal Dark Oak Hanging Sign}
     */
    String MC_ITEM_DARK_OAK_HANGING_SIGN_NAME = "minecraft:item.dark_oak_hanging_sign.name";

    /**
     * {@literal Dark Oak Sign}
     */
    String MC_ITEM_DARKOAK_SIGN_NAME = "minecraft:item.darkoak_sign.name";

    /**
     * {@literal Diamond}
     */
    String MC_ITEM_DIAMOND_NAME = "minecraft:item.diamond.name";

    /**
     * {@literal Diamond Axe}
     */
    String MC_ITEM_DIAMOND_AXE_NAME = "minecraft:item.diamond_axe.name";

    /**
     * {@literal Diamond Boots}
     */
    String MC_ITEM_DIAMOND_BOOTS_NAME = "minecraft:item.diamond_boots.name";

    /**
     * {@literal Diamond Chestplate}
     */
    String MC_ITEM_DIAMOND_CHESTPLATE_NAME = "minecraft:item.diamond_chestplate.name";

    /**
     * {@literal Diamond Helmet}
     */
    String MC_ITEM_DIAMOND_HELMET_NAME = "minecraft:item.diamond_helmet.name";

    /**
     * {@literal Diamond Hoe}
     */
    String MC_ITEM_DIAMOND_HOE_NAME = "minecraft:item.diamond_hoe.name";

    /**
     * {@literal Diamond Leggings}
     */
    String MC_ITEM_DIAMOND_LEGGINGS_NAME = "minecraft:item.diamond_leggings.name";

    /**
     * {@literal Diamond Nautilus Armor}
     */
    String MC_ITEM_DIAMOND_NAUTILUS_ARMOR_NAME = "minecraft:item.diamond_nautilus_armor.name";

    /**
     * {@literal Diamond Pickaxe}
     */
    String MC_ITEM_DIAMOND_PICKAXE_NAME = "minecraft:item.diamond_pickaxe.name";

    /**
     * {@literal Diamond Shovel}
     */
    String MC_ITEM_DIAMOND_SHOVEL_NAME = "minecraft:item.diamond_shovel.name";

    /**
     * {@literal Diamond Spear}
     */
    String MC_ITEM_DIAMOND_SPEAR_NAME = "minecraft:item.diamond_spear.name";

    /**
     * {@literal Diamond Sword}
     */
    String MC_ITEM_DIAMOND_SWORD_NAME = "minecraft:item.diamond_sword.name";

    /**
     * {@literal Disc Fragment}
     */
    String MC_ITEM_DISC_FRAGMENT_NAME = "minecraft:item.disc_fragment.name";

    /**
     * {@literal Music Disc - 5}
     */
    String MC_ITEM_DISC_FRAGMENT_5_DESC = "minecraft:item.disc_fragment_5.desc";

    /**
     * {@literal Dragon's Breath}
     */
    String MC_ITEM_DRAGON_BREATH_NAME = "minecraft:item.dragon_breath.name";

    /**
     * {@literal Dried Kelp}
     */
    String MC_ITEM_DRIED_KELP_NAME = "minecraft:item.dried_kelp.name";

    /**
     * {@literal Dune Armor Trim}
     */
    String MC_ITEM_DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.dune_armor_trim_smithing_template.name";

    /**
     * {@literal Ink Sac}
     */
    String MC_ITEM_DYE_BLACK_NAME = "minecraft:item.dye.black.name";

    /**
     * {@literal Black Dye}
     */
    String MC_ITEM_DYE_BLACK_NEW_NAME = "minecraft:item.dye.black_new.name";

    /**
     * {@literal Lapis Lazuli}
     */
    String MC_ITEM_DYE_BLUE_NAME = "minecraft:item.dye.blue.name";

    /**
     * {@literal Blue Dye}
     */
    String MC_ITEM_DYE_BLUE_NEW_NAME = "minecraft:item.dye.blue_new.name";

    /**
     * {@literal Cocoa Beans}
     */
    String MC_ITEM_DYE_BROWN_NAME = "minecraft:item.dye.brown.name";

    /**
     * {@literal Brown Dye}
     */
    String MC_ITEM_DYE_BROWN_NEW_NAME = "minecraft:item.dye.brown_new.name";

    /**
     * {@literal Cyan Dye}
     */
    String MC_ITEM_DYE_CYAN_NAME = "minecraft:item.dye.cyan.name";

    /**
     * {@literal Gray Dye}
     */
    String MC_ITEM_DYE_GRAY_NAME = "minecraft:item.dye.gray.name";

    /**
     * {@literal Green Dye}
     */
    String MC_ITEM_DYE_GREEN_NAME = "minecraft:item.dye.green.name";

    /**
     * {@literal Light Blue Dye}
     */
    String MC_ITEM_DYE_LIGHTBLUE_NAME = "minecraft:item.dye.lightBlue.name";

    /**
     * {@literal Lime Dye}
     */
    String MC_ITEM_DYE_LIME_NAME = "minecraft:item.dye.lime.name";

    /**
     * {@literal Magenta Dye}
     */
    String MC_ITEM_DYE_MAGENTA_NAME = "minecraft:item.dye.magenta.name";

    /**
     * {@literal Orange Dye}
     */
    String MC_ITEM_DYE_ORANGE_NAME = "minecraft:item.dye.orange.name";

    /**
     * {@literal Pink Dye}
     */
    String MC_ITEM_DYE_PINK_NAME = "minecraft:item.dye.pink.name";

    /**
     * {@literal Purple Dye}
     */
    String MC_ITEM_DYE_PURPLE_NAME = "minecraft:item.dye.purple.name";

    /**
     * {@literal Red Dye}
     */
    String MC_ITEM_DYE_RED_NAME = "minecraft:item.dye.red.name";

    /**
     * {@literal Light Gray Dye}
     */
    String MC_ITEM_DYE_SILVER_NAME = "minecraft:item.dye.silver.name";

    /**
     * {@literal Bone Meal}
     */
    String MC_ITEM_DYE_WHITE_NAME = "minecraft:item.dye.white.name";

    /**
     * {@literal White Dye}
     */
    String MC_ITEM_DYE_WHITE_NEW_NAME = "minecraft:item.dye.white_new.name";

    /**
     * {@literal Yellow Dye}
     */
    String MC_ITEM_DYE_YELLOW_NAME = "minecraft:item.dye.yellow.name";

    /**
     * {@literal Dyed}
     */
    String MC_ITEM_DYED = "minecraft:item.dyed";

    /**
     * {@literal Echo Shard}
     */
    String MC_ITEM_ECHO_SHARD_NAME = "minecraft:item.echo_shard.name";

    /**
     * {@literal Egg}
     */
    String MC_ITEM_EGG_NAME = "minecraft:item.egg.name";

    /**
     * {@literal Elytra}
     */
    String MC_ITEM_ELYTRA_NAME = "minecraft:item.elytra.name";

    /**
     * {@literal Emerald}
     */
    String MC_ITEM_EMERALD_NAME = "minecraft:item.emerald.name";

    /**
     * {@literal Empty Locator Map}
     */
    String MC_ITEM_EMPTYLOCATORMAP_NAME = "minecraft:item.emptyLocatorMap.name";

    /**
     * {@literal Empty Map}
     */
    String MC_ITEM_EMPTYMAP_NAME = "minecraft:item.emptyMap.name";

    /**
     * {@literal Enchanted Book}
     */
    String MC_ITEM_ENCHANTED_BOOK_NAME = "minecraft:item.enchanted_book.name";

    /**
     * {@literal End Crystal}
     */
    String MC_ITEM_END_CRYSTAL_NAME = "minecraft:item.end_crystal.name";

    /**
     * {@literal Eye of Ender}
     */
    String MC_ITEM_ENDER_EYE_NAME = "minecraft:item.ender_eye.name";

    /**
     * {@literal Ender Pearl}
     */
    String MC_ITEM_ENDER_PEARL_NAME = "minecraft:item.ender_pearl.name";

    /**
     * {@literal Bottle o' Enchanting}
     */
    String MC_ITEM_EXPERIENCE_BOTTLE_NAME = "minecraft:item.experience_bottle.name";

    /**
     * {@literal Explorer Pottery Sherd}
     */
    String MC_ITEM_EXPLORER_POTTERY_SHERD_NAME = "minecraft:item.explorer_pottery_sherd.name";

    /**
     * {@literal Exposed Copper Door}
     */
    String MC_ITEM_EXPOSED_COPPER_DOOR_NAME = "minecraft:item.exposed_copper_door.name";

    /**
     * {@literal Eye Armor Trim}
     */
    String MC_ITEM_EYE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.eye_armor_trim_smithing_template.name";

    /**
     * {@literal Feather}
     */
    String MC_ITEM_FEATHER_NAME = "minecraft:item.feather.name";

    /**
     * {@literal Fermented Spider Eye}
     */
    String MC_ITEM_FERMENTED_SPIDER_EYE_NAME = "minecraft:item.fermented_spider_eye.name";

    /**
     * {@literal Field Masoned Banner Pattern}
     */
    String MC_ITEM_FIELD_MASONED_BANNER_PATTERN_NAME = "minecraft:item.field_masoned_banner_pattern.name";

    /**
     * {@literal Fire Charge}
     */
    String MC_ITEM_FIREBALL_NAME = "minecraft:item.fireball.name";

    /**
     * {@literal Flight Duration:}
     */
    String MC_ITEM_FIREWORKS_FLIGHT = "minecraft:item.fireworks.flight";

    /**
     * {@literal Firework Rocket}
     */
    String MC_ITEM_FIREWORKS_NAME = "minecraft:item.fireworks.name";

    /**
     * {@literal Black}
     */
    String MC_ITEM_FIREWORKSCHARGE_BLACK = "minecraft:item.fireworksCharge.black";

    /**
     * {@literal Blue}
     */
    String MC_ITEM_FIREWORKSCHARGE_BLUE = "minecraft:item.fireworksCharge.blue";

    /**
     * {@literal Brown}
     */
    String MC_ITEM_FIREWORKSCHARGE_BROWN = "minecraft:item.fireworksCharge.brown";

    /**
     * {@literal Custom}
     */
    String MC_ITEM_FIREWORKSCHARGE_CUSTOMCOLOR = "minecraft:item.fireworksCharge.customColor";

    /**
     * {@literal Cyan}
     */
    String MC_ITEM_FIREWORKSCHARGE_CYAN = "minecraft:item.fireworksCharge.cyan";

    /**
     * {@literal Fade to}
     */
    String MC_ITEM_FIREWORKSCHARGE_FADETO = "minecraft:item.fireworksCharge.fadeTo";

    /**
     * {@literal Twinkle}
     */
    String MC_ITEM_FIREWORKSCHARGE_FLICKER = "minecraft:item.fireworksCharge.flicker";

    /**
     * {@literal Gray}
     */
    String MC_ITEM_FIREWORKSCHARGE_GRAY = "minecraft:item.fireworksCharge.gray";

    /**
     * {@literal Green}
     */
    String MC_ITEM_FIREWORKSCHARGE_GREEN = "minecraft:item.fireworksCharge.green";

    /**
     * {@literal Light Blue}
     */
    String MC_ITEM_FIREWORKSCHARGE_LIGHTBLUE = "minecraft:item.fireworksCharge.lightBlue";

    /**
     * {@literal Lime}
     */
    String MC_ITEM_FIREWORKSCHARGE_LIME = "minecraft:item.fireworksCharge.lime";

    /**
     * {@literal Magenta}
     */
    String MC_ITEM_FIREWORKSCHARGE_MAGENTA = "minecraft:item.fireworksCharge.magenta";

    /**
     * {@literal Firework Star}
     */
    String MC_ITEM_FIREWORKSCHARGE_NAME = "minecraft:item.fireworksCharge.name";

    /**
     * {@literal Orange}
     */
    String MC_ITEM_FIREWORKSCHARGE_ORANGE = "minecraft:item.fireworksCharge.orange";

    /**
     * {@literal Pink}
     */
    String MC_ITEM_FIREWORKSCHARGE_PINK = "minecraft:item.fireworksCharge.pink";

    /**
     * {@literal Purple}
     */
    String MC_ITEM_FIREWORKSCHARGE_PURPLE = "minecraft:item.fireworksCharge.purple";

    /**
     * {@literal Red}
     */
    String MC_ITEM_FIREWORKSCHARGE_RED = "minecraft:item.fireworksCharge.red";

    /**
     * {@literal Light Gray}
     */
    String MC_ITEM_FIREWORKSCHARGE_SILVER = "minecraft:item.fireworksCharge.silver";

    /**
     * {@literal Trail}
     */
    String MC_ITEM_FIREWORKSCHARGE_TRAIL = "minecraft:item.fireworksCharge.trail";

    /**
     * {@literal Unknown Shape}
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE = "minecraft:item.fireworksCharge.type";

    /**
     * {@literal Small Ball}
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_0 = "minecraft:item.fireworksCharge.type.0";

    /**
     * {@literal Large Ball}
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_1 = "minecraft:item.fireworksCharge.type.1";

    /**
     * {@literal Star-shaped}
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_2 = "minecraft:item.fireworksCharge.type.2";

    /**
     * {@literal Creeper-shaped}
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_3 = "minecraft:item.fireworksCharge.type.3";

    /**
     * {@literal Burst}
     */
    String MC_ITEM_FIREWORKSCHARGE_TYPE_4 = "minecraft:item.fireworksCharge.type.4";

    /**
     * {@literal White}
     */
    String MC_ITEM_FIREWORKSCHARGE_WHITE = "minecraft:item.fireworksCharge.white";

    /**
     * {@literal Yellow}
     */
    String MC_ITEM_FIREWORKSCHARGE_YELLOW = "minecraft:item.fireworksCharge.yellow";

    /**
     * {@literal Raw Cod}
     */
    String MC_ITEM_FISH_NAME = "minecraft:item.fish.name";

    /**
     * {@literal Fishing Rod}
     */
    String MC_ITEM_FISHING_ROD_NAME = "minecraft:item.fishing_rod.name";

    /**
     * {@literal Flint}
     */
    String MC_ITEM_FLINT_NAME = "minecraft:item.flint.name";

    /**
     * {@literal Flint and Steel}
     */
    String MC_ITEM_FLINT_AND_STEEL_NAME = "minecraft:item.flint_and_steel.name";

    /**
     * {@literal Flow Armor Trim}
     */
    String MC_ITEM_FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.flow_armor_trim_smithing_template.name";

    /**
     * {@literal Flow Banner Pattern}
     */
    String MC_ITEM_FLOW_BANNER_PATTERN_NAME = "minecraft:item.flow_banner_pattern.name";

    /**
     * {@literal Flow Pottery Sherd}
     */
    String MC_ITEM_FLOW_POTTERY_SHERD_NAME = "minecraft:item.flow_pottery_sherd.name";

    /**
     * {@literal Flower Charge Banner Pattern}
     */
    String MC_ITEM_FLOWER_BANNER_PATTERN_NAME = "minecraft:item.flower_banner_pattern.name";

    /**
     * {@literal Flower Pot}
     */
    String MC_ITEM_FLOWER_POT_NAME = "minecraft:item.flower_pot.name";

    /**
     * {@literal Item Frame}
     */
    String MC_ITEM_FRAME_NAME = "minecraft:item.frame.name";

    /**
     * {@literal Friend Pottery Sherd}
     */
    String MC_ITEM_FRIEND_POTTERY_SHERD_NAME = "minecraft:item.friend_pottery_sherd.name";

    /**
     * {@literal Ghast Tear}
     */
    String MC_ITEM_GHAST_TEAR_NAME = "minecraft:item.ghast_tear.name";

    /**
     * {@literal Glass Bottle}
     */
    String MC_ITEM_GLASS_BOTTLE_NAME = "minecraft:item.glass_bottle.name";

    /**
     * {@literal Globe Banner Pattern}
     */
    String MC_ITEM_GLOBE_BANNER_PATTERN_NAME = "minecraft:item.globe_banner_pattern.name";

    /**
     * {@literal Glow Berries}
     */
    String MC_ITEM_GLOW_BERRIES_NAME = "minecraft:item.glow_berries.name";

    /**
     * {@literal Glow Item Frame}
     */
    String MC_ITEM_GLOW_FRAME_NAME = "minecraft:item.glow_frame.name";

    /**
     * {@literal Glow Ink Sac}
     */
    String MC_ITEM_GLOW_INK_SAC_NAME = "minecraft:item.glow_ink_sac.name";

    /**
     * {@literal Glowstone Dust}
     */
    String MC_ITEM_GLOWSTONE_DUST_NAME = "minecraft:item.glowstone_dust.name";

    /**
     * {@literal Goat Horn}
     */
    String MC_ITEM_GOAT_HORN_NAME = "minecraft:item.goat_horn.name";

    /**
     * {@literal Gold Ingot}
     */
    String MC_ITEM_GOLD_INGOT_NAME = "minecraft:item.gold_ingot.name";

    /**
     * {@literal Gold Nugget}
     */
    String MC_ITEM_GOLD_NUGGET_NAME = "minecraft:item.gold_nugget.name";

    /**
     * {@literal Golden Apple}
     */
    String MC_ITEM_GOLDEN_APPLE_NAME = "minecraft:item.golden_apple.name";

    /**
     * {@literal Golden Axe}
     */
    String MC_ITEM_GOLDEN_AXE_NAME = "minecraft:item.golden_axe.name";

    /**
     * {@literal Golden Boots}
     */
    String MC_ITEM_GOLDEN_BOOTS_NAME = "minecraft:item.golden_boots.name";

    /**
     * {@literal Golden Carrot}
     */
    String MC_ITEM_GOLDEN_CARROT_NAME = "minecraft:item.golden_carrot.name";

    /**
     * {@literal Golden Chestplate}
     */
    String MC_ITEM_GOLDEN_CHESTPLATE_NAME = "minecraft:item.golden_chestplate.name";

    /**
     * {@literal Golden Helmet}
     */
    String MC_ITEM_GOLDEN_HELMET_NAME = "minecraft:item.golden_helmet.name";

    /**
     * {@literal Golden Hoe}
     */
    String MC_ITEM_GOLDEN_HOE_NAME = "minecraft:item.golden_hoe.name";

    /**
     * {@literal Golden Leggings}
     */
    String MC_ITEM_GOLDEN_LEGGINGS_NAME = "minecraft:item.golden_leggings.name";

    /**
     * {@literal Golden Nautilus Armor}
     */
    String MC_ITEM_GOLDEN_NAUTILUS_ARMOR_NAME = "minecraft:item.golden_nautilus_armor.name";

    /**
     * {@literal Golden Pickaxe}
     */
    String MC_ITEM_GOLDEN_PICKAXE_NAME = "minecraft:item.golden_pickaxe.name";

    /**
     * {@literal Golden Shovel}
     */
    String MC_ITEM_GOLDEN_SHOVEL_NAME = "minecraft:item.golden_shovel.name";

    /**
     * {@literal Golden Spear}
     */
    String MC_ITEM_GOLDEN_SPEAR_NAME = "minecraft:item.golden_spear.name";

    /**
     * {@literal Golden Sword}
     */
    String MC_ITEM_GOLDEN_SWORD_NAME = "minecraft:item.golden_sword.name";

    /**
     * {@literal Gray Bundle}
     */
    String MC_ITEM_GRAY_BUNDLE = "minecraft:item.gray_bundle";

    /**
     * {@literal Gray Harness}
     */
    String MC_ITEM_GRAY_HARNESS_NAME = "minecraft:item.gray_harness.name";

    /**
     * {@literal Green Bundle}
     */
    String MC_ITEM_GREEN_BUNDLE = "minecraft:item.green_bundle";

    /**
     * {@literal Green Harness}
     */
    String MC_ITEM_GREEN_HARNESS_NAME = "minecraft:item.green_harness.name";

    /**
     * {@literal Gunpowder}
     */
    String MC_ITEM_GUNPOWDER_NAME = "minecraft:item.gunpowder.name";

    /**
     * {@literal Guster Banner Pattern}
     */
    String MC_ITEM_GUSTER_BANNER_PATTERN_NAME = "minecraft:item.guster_banner_pattern.name";

    /**
     * {@literal Guster Pottery Sherd}
     */
    String MC_ITEM_GUSTER_POTTERY_SHERD_NAME = "minecraft:item.guster_pottery_sherd.name";

    /**
     * {@literal Heart of the Sea}
     */
    String MC_ITEM_HEART_OF_THE_SEA_NAME = "minecraft:item.heart_of_the_sea.name";

    /**
     * {@literal Heart Pottery Sherd}
     */
    String MC_ITEM_HEART_POTTERY_SHERD_NAME = "minecraft:item.heart_pottery_sherd.name";

    /**
     * {@literal Heartbreak Pottery Sherd}
     */
    String MC_ITEM_HEARTBREAK_POTTERY_SHERD_NAME = "minecraft:item.heartbreak_pottery_sherd.name";

    /**
     * {@literal Honey Bottle}
     */
    String MC_ITEM_HONEY_BOTTLE_NAME = "minecraft:item.honey_bottle.name";

    /**
     * {@literal Honeycomb}
     */
    String MC_ITEM_HONEYCOMB_NAME = "minecraft:item.honeycomb.name";

    /**
     * {@literal Minecart with Hopper}
     */
    String MC_ITEM_HOPPER_MINECART_NAME = "minecraft:item.hopper_minecart.name";

    /**
     * {@literal Diamond Horse Armor}
     */
    String MC_ITEM_HORSEARMORDIAMOND_NAME = "minecraft:item.horsearmordiamond.name";

    /**
     * {@literal Golden Horse Armor}
     */
    String MC_ITEM_HORSEARMORGOLD_NAME = "minecraft:item.horsearmorgold.name";

    /**
     * {@literal Iron Horse Armor}
     */
    String MC_ITEM_HORSEARMORIRON_NAME = "minecraft:item.horsearmoriron.name";

    /**
     * {@literal Leather Horse Armor}
     */
    String MC_ITEM_HORSEARMORLEATHER_NAME = "minecraft:item.horsearmorleather.name";

    /**
     * {@literal Host Armor Trim}
     */
    String MC_ITEM_HOST_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.host_armor_trim_smithing_template.name";

    /**
     * {@literal Howl Pottery Sherd}
     */
    String MC_ITEM_HOWL_POTTERY_SHERD_NAME = "minecraft:item.howl_pottery_sherd.name";

    /**
     * {@literal Iron Axe}
     */
    String MC_ITEM_IRON_AXE_NAME = "minecraft:item.iron_axe.name";

    /**
     * {@literal Iron Boots}
     */
    String MC_ITEM_IRON_BOOTS_NAME = "minecraft:item.iron_boots.name";

    /**
     * {@literal Iron Chestplate}
     */
    String MC_ITEM_IRON_CHESTPLATE_NAME = "minecraft:item.iron_chestplate.name";

    /**
     * {@literal Iron Door}
     */
    String MC_ITEM_IRON_DOOR_NAME = "minecraft:item.iron_door.name";

    /**
     * {@literal Iron Helmet}
     */
    String MC_ITEM_IRON_HELMET_NAME = "minecraft:item.iron_helmet.name";

    /**
     * {@literal Iron Hoe}
     */
    String MC_ITEM_IRON_HOE_NAME = "minecraft:item.iron_hoe.name";

    /**
     * {@literal Iron Ingot}
     */
    String MC_ITEM_IRON_INGOT_NAME = "minecraft:item.iron_ingot.name";

    /**
     * {@literal Iron Leggings}
     */
    String MC_ITEM_IRON_LEGGINGS_NAME = "minecraft:item.iron_leggings.name";

    /**
     * {@literal Iron Nautilus Armor}
     */
    String MC_ITEM_IRON_NAUTILUS_ARMOR_NAME = "minecraft:item.iron_nautilus_armor.name";

    /**
     * {@literal Iron Nugget}
     */
    String MC_ITEM_IRON_NUGGET_NAME = "minecraft:item.iron_nugget.name";

    /**
     * {@literal Iron Pickaxe}
     */
    String MC_ITEM_IRON_PICKAXE_NAME = "minecraft:item.iron_pickaxe.name";

    /**
     * {@literal Iron Shovel}
     */
    String MC_ITEM_IRON_SHOVEL_NAME = "minecraft:item.iron_shovel.name";

    /**
     * {@literal Iron Spear}
     */
    String MC_ITEM_IRON_SPEAR_NAME = "minecraft:item.iron_spear.name";

    /**
     * {@literal Iron Sword}
     */
    String MC_ITEM_IRON_SWORD_NAME = "minecraft:item.iron_sword.name";

    /**
     * {@literal :hollow_star: Can't Drop Items can't be:}
     */
    String MC_ITEM_ITEMLOCK_CANTDROP = "minecraft:item.itemLock.cantDrop";

    /**
     * {@literal :solid_star: Can't Move Items can't be:}
     */
    String MC_ITEM_ITEMLOCK_CANTMOVE = "minecraft:item.itemLock.cantMove";

    /**
     * {@literal crafted with}
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_CRAFTEDWITH = "minecraft:item.itemLock.hoverText.cantBe.craftedWith";

    /**
     * {@literal dropped}
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_DROPPED = "minecraft:item.itemLock.hoverText.cantBe.dropped";

    /**
     * {@literal moved}
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_MOVED = "minecraft:item.itemLock.hoverText.cantBe.moved";

    /**
     * {@literal removed}
     */
    String MC_ITEM_ITEMLOCK_HOVERTEXT_CANTBE_REMOVED = "minecraft:item.itemLock.hoverText.cantBe.removed";

    /**
     * {@literal This item is not lost on death}
     */
    String MC_ITEM_ITEMLOCK_KEEPONDEATH = "minecraft:item.itemLock.keepOnDeath";

    /**
     * {@literal :hollow_star: Can't Drop Items can't be: dropped, removed, crafted with}
     */
    String MC_ITEM_ITEMLOCK_POPUPNOTICE_CANTDROP = "minecraft:item.itemLock.popupNotice.cantDrop";

    /**
     * {@literal :solid_star: Can't Move Items can't be: moved, dropped, removed, crafted with}
     */
    String MC_ITEM_ITEMLOCK_POPUPNOTICE_CANTMOVE = "minecraft:item.itemLock.popupNotice.cantMove";

    /**
     * {@literal Jungle Door}
     */
    String MC_ITEM_JUNGLE_DOOR_NAME = "minecraft:item.jungle_door.name";

    /**
     * {@literal Jungle Hanging Sign}
     */
    String MC_ITEM_JUNGLE_HANGING_SIGN_NAME = "minecraft:item.jungle_hanging_sign.name";

    /**
     * {@literal Jungle Sign}
     */
    String MC_ITEM_JUNGLE_SIGN_NAME = "minecraft:item.jungle_sign.name";

    /**
     * {@literal Kelp}
     */
    String MC_ITEM_KELP_NAME = "minecraft:item.kelp.name";

    /**
     * {@literal Lead}
     */
    String MC_ITEM_LEAD_NAME = "minecraft:item.lead.name";

    /**
     * {@literal Leather}
     */
    String MC_ITEM_LEATHER_NAME = "minecraft:item.leather.name";

    /**
     * {@literal Leather Boots}
     */
    String MC_ITEM_LEATHER_BOOTS_NAME = "minecraft:item.leather_boots.name";

    /**
     * {@literal Leather Tunic}
     */
    String MC_ITEM_LEATHER_CHESTPLATE_NAME = "minecraft:item.leather_chestplate.name";

    /**
     * {@literal Leather Cap}
     */
    String MC_ITEM_LEATHER_HELMET_NAME = "minecraft:item.leather_helmet.name";

    /**
     * {@literal Leather Pants}
     */
    String MC_ITEM_LEATHER_LEGGINGS_NAME = "minecraft:item.leather_leggings.name";

    /**
     * {@literal Leaves}
     */
    String MC_ITEM_LEAVES_NAME = "minecraft:item.leaves.name";

    /**
     * {@literal Light Blue Bundle}
     */
    String MC_ITEM_LIGHT_BLUE_BUNDLE = "minecraft:item.light_blue_bundle";

    /**
     * {@literal Light Blue Harness}
     */
    String MC_ITEM_LIGHT_BLUE_HARNESS_NAME = "minecraft:item.light_blue_harness.name";

    /**
     * {@literal Light Gray Bundle}
     */
    String MC_ITEM_LIGHT_GRAY_BUNDLE = "minecraft:item.light_gray_bundle";

    /**
     * {@literal Light Gray Harness}
     */
    String MC_ITEM_LIGHT_GRAY_HARNESS_NAME = "minecraft:item.light_gray_harness.name";

    /**
     * {@literal Lime Bundle}
     */
    String MC_ITEM_LIME_BUNDLE = "minecraft:item.lime_bundle";

    /**
     * {@literal Lime Harness}
     */
    String MC_ITEM_LIME_HARNESS_NAME = "minecraft:item.lime_harness.name";

    /**
     * {@literal Lodestone Compass}
     */
    String MC_ITEM_LODESTONECOMPASS_NAME = "minecraft:item.lodestonecompass.name";

    /**
     * {@literal Mace}
     */
    String MC_ITEM_MACE_NAME = "minecraft:item.mace.name";

    /**
     * {@literal Magenta Bundle}
     */
    String MC_ITEM_MAGENTA_BUNDLE = "minecraft:item.magenta_bundle";

    /**
     * {@literal Magenta Harness}
     */
    String MC_ITEM_MAGENTA_HARNESS_NAME = "minecraft:item.magenta_harness.name";

    /**
     * {@literal Magma Cream}
     */
    String MC_ITEM_MAGMA_CREAM_NAME = "minecraft:item.magma_cream.name";

    /**
     * {@literal Mangrove Door}
     */
    String MC_ITEM_MANGROVE_DOOR_NAME = "minecraft:item.mangrove_door.name";

    /**
     * {@literal Mangrove Hanging Sign}
     */
    String MC_ITEM_MANGROVE_HANGING_SIGN_NAME = "minecraft:item.mangrove_hanging_sign.name";

    /**
     * {@literal Mangrove Sign}
     */
    String MC_ITEM_MANGROVE_SIGN_NAME = "minecraft:item.mangrove_sign.name";

    /**
     * {@literal Treasure Map}
     */
    String MC_ITEM_MAP_EXPLORATION_BURIED_TREASURE_NAME = "minecraft:item.map.exploration.buried_treasure.name";

    /**
     * {@literal Jungle Explorer Map}
     */
    String MC_ITEM_MAP_EXPLORATION_JUNGLE_TEMPLE_NAME = "minecraft:item.map.exploration.jungle_temple.name";

    /**
     * {@literal Woodland Explorer Map}
     */
    String MC_ITEM_MAP_EXPLORATION_MANSION_NAME = "minecraft:item.map.exploration.mansion.name";

    /**
     * {@literal Ocean Explorer Map}
     */
    String MC_ITEM_MAP_EXPLORATION_MONUMENT_NAME = "minecraft:item.map.exploration.monument.name";

    /**
     * {@literal Swamp Explorer Map}
     */
    String MC_ITEM_MAP_EXPLORATION_SWAMP_HUT_NAME = "minecraft:item.map.exploration.swamp_hut.name";

    /**
     * {@literal Treasure Map}
     */
    String MC_ITEM_MAP_EXPLORATION_TREASURE_NAME = "minecraft:item.map.exploration.treasure.name";

    /**
     * {@literal Trial Explorer Map}
     */
    String MC_ITEM_MAP_EXPLORATION_TRIAL_CHAMBERS_NAME = "minecraft:item.map.exploration.trial_chambers.name";

    /**
     * {@literal Desert Village Map}
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_DESERT_NAME = "minecraft:item.map.exploration.village_desert.name";

    /**
     * {@literal Plains Village Map}
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_PLAINS_NAME = "minecraft:item.map.exploration.village_plains.name";

    /**
     * {@literal Savanna Village Map}
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_SAVANNA_NAME = "minecraft:item.map.exploration.village_savanna.name";

    /**
     * {@literal Snowy Village Map}
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_SNOWY_NAME = "minecraft:item.map.exploration.village_snowy.name";

    /**
     * {@literal Taiga Village Map}
     */
    String MC_ITEM_MAP_EXPLORATION_VILLAGE_TAIGA_NAME = "minecraft:item.map.exploration.village_taiga.name";

    /**
     * {@literal Map}
     */
    String MC_ITEM_MAP_NAME = "minecraft:item.map.name";

    /**
     * {@literal Melon Slice}
     */
    String MC_ITEM_MELON_NAME = "minecraft:item.melon.name";

    /**
     * {@literal Melon Seeds}
     */
    String MC_ITEM_MELON_SEEDS_NAME = "minecraft:item.melon_seeds.name";

    /**
     * {@literal Milk Bucket}
     */
    String MC_ITEM_MILK_NAME = "minecraft:item.milk.name";

    /**
     * {@literal Minecart}
     */
    String MC_ITEM_MINECART_NAME = "minecraft:item.minecart.name";

    /**
     * {@literal Minecart with Furnace}
     */
    String MC_ITEM_MINECARTFURNACE_NAME = "minecraft:item.minecartFurnace.name";

    /**
     * {@literal Ponder}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_0 = "minecraft:item.minecraft.goat_horn.sound.0";

    /**
     * {@literal Sing}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_1 = "minecraft:item.minecraft.goat_horn.sound.1";

    /**
     * {@literal Seek}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_2 = "minecraft:item.minecraft.goat_horn.sound.2";

    /**
     * {@literal Feel}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_3 = "minecraft:item.minecraft.goat_horn.sound.3";

    /**
     * {@literal Admire}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_4 = "minecraft:item.minecraft.goat_horn.sound.4";

    /**
     * {@literal Call}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_5 = "minecraft:item.minecraft.goat_horn.sound.5";

    /**
     * {@literal Yearn}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_6 = "minecraft:item.minecraft.goat_horn.sound.6";

    /**
     * {@literal Dream}
     */
    String MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_7 = "minecraft:item.minecraft.goat_horn.sound.7";

    /**
     * {@literal Miner Pottery Sherd}
     */
    String MC_ITEM_MINER_POTTERY_SHERD_NAME = "minecraft:item.miner_pottery_sherd.name";

    /**
     * {@literal Thing Banner Pattern}
     */
    String MC_ITEM_MOJANG_BANNER_PATTERN_NAME = "minecraft:item.mojang_banner_pattern.name";

    /**
     * {@literal Mourner Pottery Sherd}
     */
    String MC_ITEM_MOURNER_POTTERY_SHERD_NAME = "minecraft:item.mourner_pottery_sherd.name";

    /**
     * {@literal Mushroom Stew}
     */
    String MC_ITEM_MUSHROOM_STEW_NAME = "minecraft:item.mushroom_stew.name";

    /**
     * {@literal Cooked Mutton}
     */
    String MC_ITEM_MUTTONCOOKED_NAME = "minecraft:item.muttonCooked.name";

    /**
     * {@literal Raw Mutton}
     */
    String MC_ITEM_MUTTONRAW_NAME = "minecraft:item.muttonRaw.name";

    /**
     * {@literal Name Tag}
     */
    String MC_ITEM_NAME_TAG_NAME = "minecraft:item.name_tag.name";

    /**
     * {@literal Nautilus Shell}
     */
    String MC_ITEM_NAUTILUS_SHELL_NAME = "minecraft:item.nautilus_shell.name";

    /**
     * {@literal Nether Star}
     */
    String MC_ITEM_NETHERSTAR_NAME = "minecraft:item.netherStar.name";

    /**
     * {@literal Nether Wart}
     */
    String MC_ITEM_NETHER_WART_NAME = "minecraft:item.nether_wart.name";

    /**
     * {@literal Nether Brick}
     */
    String MC_ITEM_NETHERBRICK_NAME = "minecraft:item.netherbrick.name";

    /**
     * {@literal Netherite Axe}
     */
    String MC_ITEM_NETHERITE_AXE_NAME = "minecraft:item.netherite_axe.name";

    /**
     * {@literal Netherite Boots}
     */
    String MC_ITEM_NETHERITE_BOOTS_NAME = "minecraft:item.netherite_boots.name";

    /**
     * {@literal Netherite Chestplate}
     */
    String MC_ITEM_NETHERITE_CHESTPLATE_NAME = "minecraft:item.netherite_chestplate.name";

    /**
     * {@literal Netherite Helmet}
     */
    String MC_ITEM_NETHERITE_HELMET_NAME = "minecraft:item.netherite_helmet.name";

    /**
     * {@literal Netherite Hoe}
     */
    String MC_ITEM_NETHERITE_HOE_NAME = "minecraft:item.netherite_hoe.name";

    /**
     * {@literal Netherite Horse Armor}
     */
    String MC_ITEM_NETHERITE_HORSE_ARMOR_NAME = "minecraft:item.netherite_horse_armor.name";

    /**
     * {@literal Netherite Ingot}
     */
    String MC_ITEM_NETHERITE_INGOT_NAME = "minecraft:item.netherite_ingot.name";

    /**
     * {@literal Netherite Leggings}
     */
    String MC_ITEM_NETHERITE_LEGGINGS_NAME = "minecraft:item.netherite_leggings.name";

    /**
     * {@literal Netherite Nautilus Armor}
     */
    String MC_ITEM_NETHERITE_NAUTILUS_ARMOR_NAME = "minecraft:item.netherite_nautilus_armor.name";

    /**
     * {@literal Netherite Pickaxe}
     */
    String MC_ITEM_NETHERITE_PICKAXE_NAME = "minecraft:item.netherite_pickaxe.name";

    /**
     * {@literal Netherite Scrap}
     */
    String MC_ITEM_NETHERITE_SCRAP_NAME = "minecraft:item.netherite_scrap.name";

    /**
     * {@literal Netherite Shovel}
     */
    String MC_ITEM_NETHERITE_SHOVEL_NAME = "minecraft:item.netherite_shovel.name";

    /**
     * {@literal Netherite Spear}
     */
    String MC_ITEM_NETHERITE_SPEAR_NAME = "minecraft:item.netherite_spear.name";

    /**
     * {@literal Netherite Sword}
     */
    String MC_ITEM_NETHERITE_SWORD_NAME = "minecraft:item.netherite_sword.name";

    /**
     * {@literal Netherite Upgrade}
     */
    String MC_ITEM_NETHERITE_UPGRADE_SMITHING_TEMPLATE_NAME = "minecraft:item.netherite_upgrade_smithing_template.name";

    /**
     * {@literal Oak Hanging Sign}
     */
    String MC_ITEM_OAK_HANGING_SIGN_NAME = "minecraft:item.oak_hanging_sign.name";

    /**
     * {@literal Ominous Bottle}
     */
    String MC_ITEM_OMINOUS_BOTTLE_NAME = "minecraft:item.ominous_bottle.name";

    /**
     * {@literal Ominous Trial Key}
     */
    String MC_ITEM_OMINOUS_TRIAL_KEY_NAME = "minecraft:item.ominous_trial_key.name";

    /**
     * {@literal Orange Bundle}
     */
    String MC_ITEM_ORANGE_BUNDLE = "minecraft:item.orange_bundle";

    /**
     * {@literal Orange Harness}
     */
    String MC_ITEM_ORANGE_HARNESS_NAME = "minecraft:item.orange_harness.name";

    /**
     * {@literal Oxidized Copper Door}
     */
    String MC_ITEM_OXIDIZED_COPPER_DOOR_NAME = "minecraft:item.oxidized_copper_door.name";

    /**
     * {@literal Painting}
     */
    String MC_ITEM_PAINTING_NAME = "minecraft:item.painting.name";

    /**
     * {@literal Pale Oak Door}
     */
    String MC_ITEM_PALE_OAK_DOOR_NAME = "minecraft:item.pale_oak_door.name";

    /**
     * {@literal Pale Oak Hanging Sign}
     */
    String MC_ITEM_PALE_OAK_HANGING_SIGN_NAME = "minecraft:item.pale_oak_hanging_sign.name";

    /**
     * {@literal Pale Oak Sign}
     */
    String MC_ITEM_PALE_OAK_SIGN_NAME = "minecraft:item.pale_oak_sign.name";

    /**
     * {@literal Paper}
     */
    String MC_ITEM_PAPER_NAME = "minecraft:item.paper.name";

    /**
     * {@literal Phantom Membrane}
     */
    String MC_ITEM_PHANTOM_MEMBRANE_NAME = "minecraft:item.phantom_membrane.name";

    /**
     * {@literal Photo}
     */
    String MC_ITEM_PHOTO_NAME = "minecraft:item.photo.name";

    /**
     * {@literal Snout Banner Pattern}
     */
    String MC_ITEM_PIGLIN_BANNER_PATTERN_NAME = "minecraft:item.piglin_banner_pattern.name";

    /**
     * {@literal Pink Bundle}
     */
    String MC_ITEM_PINK_BUNDLE = "minecraft:item.pink_bundle";

    /**
     * {@literal Pink Harness}
     */
    String MC_ITEM_PINK_HARNESS_NAME = "minecraft:item.pink_harness.name";

    /**
     * {@literal Pitcher Pod}
     */
    String MC_ITEM_PITCHER_POD_NAME = "minecraft:item.pitcher_pod.name";

    /**
     * {@literal Plenty Pottery Sherd}
     */
    String MC_ITEM_PLENTY_POTTERY_SHERD_NAME = "minecraft:item.plenty_pottery_sherd.name";

    /**
     * {@literal Poisonous Potato}
     */
    String MC_ITEM_POISONOUS_POTATO_NAME = "minecraft:item.poisonous_potato.name";

    /**
     * {@literal Raw Porkchop}
     */
    String MC_ITEM_PORKCHOP_NAME = "minecraft:item.porkchop.name";

    /**
     * {@literal Cooked Porkchop}
     */
    String MC_ITEM_PORKCHOP_COOKED_NAME = "minecraft:item.porkchop_cooked.name";

    /**
     * {@literal Portfolio}
     */
    String MC_ITEM_PORTFOLIO_NAME = "minecraft:item.portfolio.name";

    /**
     * {@literal Potato}
     */
    String MC_ITEM_POTATO_NAME = "minecraft:item.potato.name";

    /**
     * {@literal Prismarine Crystals}
     */
    String MC_ITEM_PRISMARINE_CRYSTALS_NAME = "minecraft:item.prismarine_crystals.name";

    /**
     * {@literal Prismarine Shard}
     */
    String MC_ITEM_PRISMARINE_SHARD_NAME = "minecraft:item.prismarine_shard.name";

    /**
     * {@literal Prize Pottery Sherd}
     */
    String MC_ITEM_PRIZE_POTTERY_SHERD_NAME = "minecraft:item.prize_pottery_sherd.name";

    /**
     * {@literal Pufferfish}
     */
    String MC_ITEM_PUFFERFISH_NAME = "minecraft:item.pufferfish.name";

    /**
     * {@literal Pumpkin Pie}
     */
    String MC_ITEM_PUMPKIN_PIE_NAME = "minecraft:item.pumpkin_pie.name";

    /**
     * {@literal Pumpkin Seeds}
     */
    String MC_ITEM_PUMPKIN_SEEDS_NAME = "minecraft:item.pumpkin_seeds.name";

    /**
     * {@literal Purple Bundle}
     */
    String MC_ITEM_PURPLE_BUNDLE = "minecraft:item.purple_bundle";

    /**
     * {@literal Purple Harness}
     */
    String MC_ITEM_PURPLE_HARNESS_NAME = "minecraft:item.purple_harness.name";

    /**
     * {@literal Nether Quartz}
     */
    String MC_ITEM_QUARTZ_NAME = "minecraft:item.quartz.name";

    /**
     * {@literal Raw Rabbit}
     */
    String MC_ITEM_RABBIT_NAME = "minecraft:item.rabbit.name";

    /**
     * {@literal Rabbit's Foot}
     */
    String MC_ITEM_RABBIT_FOOT_NAME = "minecraft:item.rabbit_foot.name";

    /**
     * {@literal Rabbit Hide}
     */
    String MC_ITEM_RABBIT_HIDE_NAME = "minecraft:item.rabbit_hide.name";

    /**
     * {@literal Rabbit Stew}
     */
    String MC_ITEM_RABBIT_STEW_NAME = "minecraft:item.rabbit_stew.name";

    /**
     * {@literal Raiser Armor Trim}
     */
    String MC_ITEM_RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.raiser_armor_trim_smithing_template.name";

    /**
     * {@literal Raw Copper}
     */
    String MC_ITEM_RAW_COPPER_NAME = "minecraft:item.raw_copper.name";

    /**
     * {@literal Raw Gold}
     */
    String MC_ITEM_RAW_GOLD_NAME = "minecraft:item.raw_gold.name";

    /**
     * {@literal Raw Iron}
     */
    String MC_ITEM_RAW_IRON_NAME = "minecraft:item.raw_iron.name";

    /**
     * {@literal Music Disc}
     */
    String MC_ITEM_RECORD_NAME = "minecraft:item.record.name";

    /**
     * {@literal C418 - 11}
     */
    String MC_ITEM_RECORD_11_DESC = "minecraft:item.record_11.desc";

    /**
     * {@literal C418 - 13}
     */
    String MC_ITEM_RECORD_13_DESC = "minecraft:item.record_13.desc";

    /**
     * {@literal Samuel Åberg - 5}
     */
    String MC_ITEM_RECORD_5_DESC = "minecraft:item.record_5.desc";

    /**
     * {@literal C418 - blocks}
     */
    String MC_ITEM_RECORD_BLOCKS_DESC = "minecraft:item.record_blocks.desc";

    /**
     * {@literal C418 - cat}
     */
    String MC_ITEM_RECORD_CAT_DESC = "minecraft:item.record_cat.desc";

    /**
     * {@literal C418 - chirp}
     */
    String MC_ITEM_RECORD_CHIRP_DESC = "minecraft:item.record_chirp.desc";

    /**
     * {@literal Lena Raine - Creator}
     */
    String MC_ITEM_RECORD_CREATOR_DESC = "minecraft:item.record_creator.desc";

    /**
     * {@literal Lena Raine - Creator (Music Box)}
     */
    String MC_ITEM_RECORD_CREATOR_MUSIC_BOX_DESC = "minecraft:item.record_creator_music_box.desc";

    /**
     * {@literal C418 - far}
     */
    String MC_ITEM_RECORD_FAR_DESC = "minecraft:item.record_far.desc";

    /**
     * {@literal Hyper Potions - Lava Chicken}
     */
    String MC_ITEM_RECORD_LAVA_CHICKEN_DESC = "minecraft:item.record_lava_chicken.desc";

    /**
     * {@literal C418 - mall}
     */
    String MC_ITEM_RECORD_MALL_DESC = "minecraft:item.record_mall.desc";

    /**
     * {@literal C418 - mellohi}
     */
    String MC_ITEM_RECORD_MELLOHI_DESC = "minecraft:item.record_mellohi.desc";

    /**
     * {@literal Lena Raine - otherside}
     */
    String MC_ITEM_RECORD_OTHERSIDE_DESC = "minecraft:item.record_otherside.desc";

    /**
     * {@literal Lena Raine - Pigstep}
     */
    String MC_ITEM_RECORD_PIGSTEP_DESC = "minecraft:item.record_pigstep.desc";

    /**
     * {@literal Aaron Cherof - Precipice}
     */
    String MC_ITEM_RECORD_PRECIPICE_DESC = "minecraft:item.record_precipice.desc";

    /**
     * {@literal Aaron Cherof - Relic}
     */
    String MC_ITEM_RECORD_RELIC_DESC = "minecraft:item.record_relic.desc";

    /**
     * {@literal C418 - stal}
     */
    String MC_ITEM_RECORD_STAL_DESC = "minecraft:item.record_stal.desc";

    /**
     * {@literal C418 - strad}
     */
    String MC_ITEM_RECORD_STRAD_DESC = "minecraft:item.record_strad.desc";

    /**
     * {@literal Amos Roddy - Tears}
     */
    String MC_ITEM_RECORD_TEARS_DESC = "minecraft:item.record_tears.desc";

    /**
     * {@literal C418 - wait}
     */
    String MC_ITEM_RECORD_WAIT_DESC = "minecraft:item.record_wait.desc";

    /**
     * {@literal C418 - ward}
     */
    String MC_ITEM_RECORD_WARD_DESC = "minecraft:item.record_ward.desc";

    /**
     * {@literal Recovery Compass}
     */
    String MC_ITEM_RECOVERY_COMPASS_NAME = "minecraft:item.recovery_compass.name";

    /**
     * {@literal Red Bundle}
     */
    String MC_ITEM_RED_BUNDLE = "minecraft:item.red_bundle";

    /**
     * {@literal Red Harness}
     */
    String MC_ITEM_RED_HARNESS_NAME = "minecraft:item.red_harness.name";

    /**
     * {@literal Redstone Dust}
     */
    String MC_ITEM_REDSTONE_NAME = "minecraft:item.redstone.name";

    /**
     * {@literal Sugar Cane}
     */
    String MC_ITEM_REEDS_NAME = "minecraft:item.reeds.name";

    /**
     * {@literal Redstone Repeater}
     */
    String MC_ITEM_REPEATER_NAME = "minecraft:item.repeater.name";

    /**
     * {@literal Resin Brick}
     */
    String MC_ITEM_RESIN_BRICK_NAME = "minecraft:item.resin_brick.name";

    /**
     * {@literal Rib Armor Trim}
     */
    String MC_ITEM_RIB_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.rib_armor_trim_smithing_template.name";

    /**
     * {@literal Rotten Flesh}
     */
    String MC_ITEM_ROTTEN_FLESH_NAME = "minecraft:item.rotten_flesh.name";

    /**
     * {@literal Ruby}
     */
    String MC_ITEM_RUBY_NAME = "minecraft:item.ruby.name";

    /**
     * {@literal Saddle}
     */
    String MC_ITEM_SADDLE_NAME = "minecraft:item.saddle.name";

    /**
     * {@literal Raw Salmon}
     */
    String MC_ITEM_SALMON_NAME = "minecraft:item.salmon.name";

    /**
     * {@literal Scrape Pottery Sherd}
     */
    String MC_ITEM_SCRAPE_POTTERY_SHERD_NAME = "minecraft:item.scrape_pottery_sherd.name";

    /**
     * {@literal Sentry Armor Trim}
     */
    String MC_ITEM_SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.sentry_armor_trim_smithing_template.name";

    /**
     * {@literal Shaper Armor Trim}
     */
    String MC_ITEM_SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.shaper_armor_trim_smithing_template.name";

    /**
     * {@literal Sheaf Pottery Sherd}
     */
    String MC_ITEM_SHEAF_POTTERY_SHERD_NAME = "minecraft:item.sheaf_pottery_sherd.name";

    /**
     * {@literal Shears}
     */
    String MC_ITEM_SHEARS_NAME = "minecraft:item.shears.name";

    /**
     * {@literal Shelter Pottery Sherd}
     */
    String MC_ITEM_SHELTER_POTTERY_SHERD_NAME = "minecraft:item.shelter_pottery_sherd.name";

    /**
     * {@literal Black Shield}
     */
    String MC_ITEM_SHIELD_BLACK_NAME = "minecraft:item.shield.black.name";

    /**
     * {@literal Blue Shield}
     */
    String MC_ITEM_SHIELD_BLUE_NAME = "minecraft:item.shield.blue.name";

    /**
     * {@literal Brown Shield}
     */
    String MC_ITEM_SHIELD_BROWN_NAME = "minecraft:item.shield.brown.name";

    /**
     * {@literal Cyan Shield}
     */
    String MC_ITEM_SHIELD_CYAN_NAME = "minecraft:item.shield.cyan.name";

    /**
     * {@literal Gray Shield}
     */
    String MC_ITEM_SHIELD_GRAY_NAME = "minecraft:item.shield.gray.name";

    /**
     * {@literal Green Shield}
     */
    String MC_ITEM_SHIELD_GREEN_NAME = "minecraft:item.shield.green.name";

    /**
     * {@literal Light Blue Shield}
     */
    String MC_ITEM_SHIELD_LIGHTBLUE_NAME = "minecraft:item.shield.lightBlue.name";

    /**
     * {@literal Lime Shield}
     */
    String MC_ITEM_SHIELD_LIME_NAME = "minecraft:item.shield.lime.name";

    /**
     * {@literal Magenta Shield}
     */
    String MC_ITEM_SHIELD_MAGENTA_NAME = "minecraft:item.shield.magenta.name";

    /**
     * {@literal Shield}
     */
    String MC_ITEM_SHIELD_NAME = "minecraft:item.shield.name";

    /**
     * {@literal Orange Shield}
     */
    String MC_ITEM_SHIELD_ORANGE_NAME = "minecraft:item.shield.orange.name";

    /**
     * {@literal Pink Shield}
     */
    String MC_ITEM_SHIELD_PINK_NAME = "minecraft:item.shield.pink.name";

    /**
     * {@literal Purple Shield}
     */
    String MC_ITEM_SHIELD_PURPLE_NAME = "minecraft:item.shield.purple.name";

    /**
     * {@literal Red Shield}
     */
    String MC_ITEM_SHIELD_RED_NAME = "minecraft:item.shield.red.name";

    /**
     * {@literal Light Gray Shield}
     */
    String MC_ITEM_SHIELD_SILVER_NAME = "minecraft:item.shield.silver.name";

    /**
     * {@literal White Shield}
     */
    String MC_ITEM_SHIELD_WHITE_NAME = "minecraft:item.shield.white.name";

    /**
     * {@literal Yellow Shield}
     */
    String MC_ITEM_SHIELD_YELLOW_NAME = "minecraft:item.shield.yellow.name";

    /**
     * {@literal Shulker Shell}
     */
    String MC_ITEM_SHULKER_SHELL_NAME = "minecraft:item.shulker_shell.name";

    /**
     * {@literal Oak Sign}
     */
    String MC_ITEM_SIGN_NAME = "minecraft:item.sign.name";

    /**
     * {@literal Silence Armor Trim}
     */
    String MC_ITEM_SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.silence_armor_trim_smithing_template.name";

    /**
     * {@literal Player Head}
     */
    String MC_ITEM_SKULL_CHAR_NAME = "minecraft:item.skull.char.name";

    /**
     * {@literal Creeper Head}
     */
    String MC_ITEM_SKULL_CREEPER_NAME = "minecraft:item.skull.creeper.name";

    /**
     * {@literal Dragon Head}
     */
    String MC_ITEM_SKULL_DRAGON_NAME = "minecraft:item.skull.dragon.name";

    /**
     * {@literal Piglin Head}
     */
    String MC_ITEM_SKULL_PIGLIN_NAME = "minecraft:item.skull.piglin.name";

    /**
     * {@literal %s's Head}
     */
    String MC_ITEM_SKULL_PLAYER_NAME = "minecraft:item.skull.player.name";

    /**
     * {@literal Skeleton Skull}
     */
    String MC_ITEM_SKULL_SKELETON_NAME = "minecraft:item.skull.skeleton.name";

    /**
     * {@literal Wither Skeleton Skull}
     */
    String MC_ITEM_SKULL_WITHER_NAME = "minecraft:item.skull.wither.name";

    /**
     * {@literal Zombie Head}
     */
    String MC_ITEM_SKULL_ZOMBIE_NAME = "minecraft:item.skull.zombie.name";

    /**
     * {@literal Skull Charge Banner Pattern}
     */
    String MC_ITEM_SKULL_BANNER_PATTERN_NAME = "minecraft:item.skull_banner_pattern.name";

    /**
     * {@literal Skull Pottery Sherd}
     */
    String MC_ITEM_SKULL_POTTERY_SHERD_NAME = "minecraft:item.skull_pottery_sherd.name";

    /**
     * {@literal Slimeball}
     */
    String MC_ITEM_SLIME_BALL_NAME = "minecraft:item.slime_ball.name";

    /**
     * {@literal Applies to:}
     */
    String MC_ITEM_SMITHING_TEMPLATE_APPLIES_TO = "minecraft:item.smithing_template.applies_to";

    /**
     * {@literal Armor}
     */
    String MC_ITEM_SMITHING_TEMPLATE_ARMOR_TRIM_APPLIES_TO = "minecraft:item.smithing_template.armor_trim.applies_to";

    /**
     * {@literal Ingots & Crystals}
     */
    String MC_ITEM_SMITHING_TEMPLATE_ARMOR_TRIM_INGREDIENTS = "minecraft:item.smithing_template.armor_trim.ingredients";

    /**
     * {@literal Ingredients:}
     */
    String MC_ITEM_SMITHING_TEMPLATE_INGREDIENTS = "minecraft:item.smithing_template.ingredients";

    /**
     * {@literal Smithing Template}
     */
    String MC_ITEM_SMITHING_TEMPLATE_NAME = "minecraft:item.smithing_template.name";

    /**
     * {@literal Diamond Equipment}
     */
    String MC_ITEM_SMITHING_TEMPLATE_NETHERITE_UPGRADE_APPLIES_TO = "minecraft:item.smithing_template.netherite_upgrade.applies_to";

    /**
     * {@literal Netherite Ingot}
     */
    String MC_ITEM_SMITHING_TEMPLATE_NETHERITE_UPGRADE_INGREDIENTS = "minecraft:item.smithing_template.netherite_upgrade.ingredients";

    /**
     * {@literal Snort Pottery Sherd}
     */
    String MC_ITEM_SNORT_POTTERY_SHERD_NAME = "minecraft:item.snort_pottery_sherd.name";

    /**
     * {@literal Snout Armor Trim}
     */
    String MC_ITEM_SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.snout_armor_trim_smithing_template.name";

    /**
     * {@literal Snowball}
     */
    String MC_ITEM_SNOWBALL_NAME = "minecraft:item.snowball.name";

    /**
     * {@literal Agent Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_AGENT_NAME = "minecraft:item.spawn_egg.entity.agent.name";

    /**
     * {@literal Allay Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ALLAY_NAME = "minecraft:item.spawn_egg.entity.allay.name";

    /**
     * {@literal Armadillo Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ARMADILLO_NAME = "minecraft:item.spawn_egg.entity.armadillo.name";

    /**
     * {@literal Axolotl Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_AXOLOTL_NAME = "minecraft:item.spawn_egg.entity.axolotl.name";

    /**
     * {@literal Bat Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BAT_NAME = "minecraft:item.spawn_egg.entity.bat.name";

    /**
     * {@literal Bee Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BEE_NAME = "minecraft:item.spawn_egg.entity.bee.name";

    /**
     * {@literal Blaze Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BLAZE_NAME = "minecraft:item.spawn_egg.entity.blaze.name";

    /**
     * {@literal Bogged Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BOGGED_NAME = "minecraft:item.spawn_egg.entity.bogged.name";

    /**
     * {@literal Breeze Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_BREEZE_NAME = "minecraft:item.spawn_egg.entity.breeze.name";

    /**
     * {@literal Camel Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAMEL_NAME = "minecraft:item.spawn_egg.entity.camel.name";

    /**
     * {@literal Camel Husk Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAMEL_HUSK_NAME = "minecraft:item.spawn_egg.entity.camel_husk.name";

    /**
     * {@literal Cat Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAT_NAME = "minecraft:item.spawn_egg.entity.cat.name";

    /**
     * {@literal Cave Spider Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CAVE_SPIDER_NAME = "minecraft:item.spawn_egg.entity.cave_spider.name";

    /**
     * {@literal Chicken Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CHICKEN_NAME = "minecraft:item.spawn_egg.entity.chicken.name";

    /**
     * {@literal Cod Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_COD_NAME = "minecraft:item.spawn_egg.entity.cod.name";

    /**
     * {@literal Copper Golem Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_COPPER_GOLEM_NAME = "minecraft:item.spawn_egg.entity.copper_golem.name";

    /**
     * {@literal Cow Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_COW_NAME = "minecraft:item.spawn_egg.entity.cow.name";

    /**
     * {@literal Creaking Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CREAKING_NAME = "minecraft:item.spawn_egg.entity.creaking.name";

    /**
     * {@literal Creeper Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_CREEPER_NAME = "minecraft:item.spawn_egg.entity.creeper.name";

    /**
     * {@literal Dolphin Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_DOLPHIN_NAME = "minecraft:item.spawn_egg.entity.dolphin.name";

    /**
     * {@literal Donkey Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_DONKEY_NAME = "minecraft:item.spawn_egg.entity.donkey.name";

    /**
     * {@literal Drowned Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_DROWNED_NAME = "minecraft:item.spawn_egg.entity.drowned.name";

    /**
     * {@literal Elder Guardian Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ELDER_GUARDIAN_NAME = "minecraft:item.spawn_egg.entity.elder_guardian.name";

    /**
     * {@literal Ender Dragon Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ENDER_DRAGON_NAME = "minecraft:item.spawn_egg.entity.ender_dragon.name";

    /**
     * {@literal Enderman Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ENDERMAN_NAME = "minecraft:item.spawn_egg.entity.enderman.name";

    /**
     * {@literal Endermite Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ENDERMITE_NAME = "minecraft:item.spawn_egg.entity.endermite.name";

    /**
     * {@literal Evoker Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_EVOCATION_ILLAGER_NAME = "minecraft:item.spawn_egg.entity.evocation_illager.name";

    /**
     * {@literal Fox Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_FOX_NAME = "minecraft:item.spawn_egg.entity.fox.name";

    /**
     * {@literal Frog Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_FROG_NAME = "minecraft:item.spawn_egg.entity.frog.name";

    /**
     * {@literal Ghast Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GHAST_NAME = "minecraft:item.spawn_egg.entity.ghast.name";

    /**
     * {@literal Glow Squid Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GLOW_SQUID_NAME = "minecraft:item.spawn_egg.entity.glow_squid.name";

    /**
     * {@literal Goat Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GOAT_NAME = "minecraft:item.spawn_egg.entity.goat.name";

    /**
     * {@literal Guardian Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_GUARDIAN_NAME = "minecraft:item.spawn_egg.entity.guardian.name";

    /**
     * {@literal Happy Ghast Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HAPPY_GHAST_NAME = "minecraft:item.spawn_egg.entity.happy_ghast.name";

    /**
     * {@literal Hoglin Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HOGLIN_NAME = "minecraft:item.spawn_egg.entity.hoglin.name";

    /**
     * {@literal Horse Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HORSE_NAME = "minecraft:item.spawn_egg.entity.horse.name";

    /**
     * {@literal Husk Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_HUSK_NAME = "minecraft:item.spawn_egg.entity.husk.name";

    /**
     * {@literal Iron Golem Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_IRON_GOLEM_NAME = "minecraft:item.spawn_egg.entity.iron_golem.name";

    /**
     * {@literal Llama Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_LLAMA_NAME = "minecraft:item.spawn_egg.entity.llama.name";

    /**
     * {@literal Magma Cube Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_MAGMA_CUBE_NAME = "minecraft:item.spawn_egg.entity.magma_cube.name";

    /**
     * {@literal Mooshroom Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_MOOSHROOM_NAME = "minecraft:item.spawn_egg.entity.mooshroom.name";

    /**
     * {@literal Mule Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_MULE_NAME = "minecraft:item.spawn_egg.entity.mule.name";

    /**
     * {@literal Nautilus Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_NAUTILUS_NAME = "minecraft:item.spawn_egg.entity.nautilus.name";

    /**
     * {@literal You need Creative + Operator to spawn an NPC}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_NPC_FAILED = "minecraft:item.spawn_egg.entity.npc.failed";

    /**
     * {@literal NPC Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_NPC_NAME = "minecraft:item.spawn_egg.entity.npc.name";

    /**
     * {@literal Ocelot Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_OCELOT_NAME = "minecraft:item.spawn_egg.entity.ocelot.name";

    /**
     * {@literal Panda Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PANDA_NAME = "minecraft:item.spawn_egg.entity.panda.name";

    /**
     * {@literal Parched Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PARCHED_NAME = "minecraft:item.spawn_egg.entity.parched.name";

    /**
     * {@literal Parrot Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PARROT_NAME = "minecraft:item.spawn_egg.entity.parrot.name";

    /**
     * {@literal Phantom Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PHANTOM_NAME = "minecraft:item.spawn_egg.entity.phantom.name";

    /**
     * {@literal Pig Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PIG_NAME = "minecraft:item.spawn_egg.entity.pig.name";

    /**
     * {@literal Piglin Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PIGLIN_NAME = "minecraft:item.spawn_egg.entity.piglin.name";

    /**
     * {@literal Piglin Brute Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PIGLIN_BRUTE_NAME = "minecraft:item.spawn_egg.entity.piglin_brute.name";

    /**
     * {@literal Pillager Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PILLAGER_NAME = "minecraft:item.spawn_egg.entity.pillager.name";

    /**
     * {@literal Polar Bear Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_POLAR_BEAR_NAME = "minecraft:item.spawn_egg.entity.polar_bear.name";

    /**
     * {@literal Pufferfish Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_PUFFERFISH_NAME = "minecraft:item.spawn_egg.entity.pufferfish.name";

    /**
     * {@literal Rabbit Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_RABBIT_NAME = "minecraft:item.spawn_egg.entity.rabbit.name";

    /**
     * {@literal Ravager Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_RAVAGER_NAME = "minecraft:item.spawn_egg.entity.ravager.name";

    /**
     * {@literal Salmon Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SALMON_NAME = "minecraft:item.spawn_egg.entity.salmon.name";

    /**
     * {@literal Sheep Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SHEEP_NAME = "minecraft:item.spawn_egg.entity.sheep.name";

    /**
     * {@literal Shulker Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SHULKER_NAME = "minecraft:item.spawn_egg.entity.shulker.name";

    /**
     * {@literal Silverfish Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SILVERFISH_NAME = "minecraft:item.spawn_egg.entity.silverfish.name";

    /**
     * {@literal Skeleton Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SKELETON_NAME = "minecraft:item.spawn_egg.entity.skeleton.name";

    /**
     * {@literal Skeleton Horse Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SKELETON_HORSE_NAME = "minecraft:item.spawn_egg.entity.skeleton_horse.name";

    /**
     * {@literal Slime Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SLIME_NAME = "minecraft:item.spawn_egg.entity.slime.name";

    /**
     * {@literal Sniffer Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SNIFFER_NAME = "minecraft:item.spawn_egg.entity.sniffer.name";

    /**
     * {@literal Snow Golem Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SNOW_GOLEM_NAME = "minecraft:item.spawn_egg.entity.snow_golem.name";

    /**
     * {@literal Spider Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SPIDER_NAME = "minecraft:item.spawn_egg.entity.spider.name";

    /**
     * {@literal Squid Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_SQUID_NAME = "minecraft:item.spawn_egg.entity.squid.name";

    /**
     * {@literal Stray Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_STRAY_NAME = "minecraft:item.spawn_egg.entity.stray.name";

    /**
     * {@literal Strider Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_STRIDER_NAME = "minecraft:item.spawn_egg.entity.strider.name";

    /**
     * {@literal Tadpole Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TADPOLE_NAME = "minecraft:item.spawn_egg.entity.tadpole.name";

    /**
     * {@literal Trader Llama Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TRADER_LLAMA_NAME = "minecraft:item.spawn_egg.entity.trader_llama.name";

    /**
     * {@literal Tropical Fish Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TROPICALFISH_NAME = "minecraft:item.spawn_egg.entity.tropicalfish.name";

    /**
     * {@literal Turtle Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_TURTLE_NAME = "minecraft:item.spawn_egg.entity.turtle.name";

    /**
     * {@literal Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_UNKNOWN_NAME = "minecraft:item.spawn_egg.entity.unknown.name";

    /**
     * {@literal Vex Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VEX_NAME = "minecraft:item.spawn_egg.entity.vex.name";

    /**
     * {@literal Villager Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VILLAGER_NAME = "minecraft:item.spawn_egg.entity.villager.name";

    /**
     * {@literal Villager Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VILLAGER_V2_NAME = "minecraft:item.spawn_egg.entity.villager_v2.name";

    /**
     * {@literal Vindicator Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_VINDICATOR_NAME = "minecraft:item.spawn_egg.entity.vindicator.name";

    /**
     * {@literal Wandering Trader Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WANDERING_TRADER_NAME = "minecraft:item.spawn_egg.entity.wandering_trader.name";

    /**
     * {@literal Warden Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WARDEN_NAME = "minecraft:item.spawn_egg.entity.warden.name";

    /**
     * {@literal Witch Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WITCH_NAME = "minecraft:item.spawn_egg.entity.witch.name";

    /**
     * {@literal Wither Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WITHER_NAME = "minecraft:item.spawn_egg.entity.wither.name";

    /**
     * {@literal Wither Skeleton Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WITHER_SKELETON_NAME = "minecraft:item.spawn_egg.entity.wither_skeleton.name";

    /**
     * {@literal Wolf Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_WOLF_NAME = "minecraft:item.spawn_egg.entity.wolf.name";

    /**
     * {@literal Zoglin Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOGLIN_NAME = "minecraft:item.spawn_egg.entity.zoglin.name";

    /**
     * {@literal Zombie Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_NAME = "minecraft:item.spawn_egg.entity.zombie.name";

    /**
     * {@literal Zombie Horse Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_HORSE_NAME = "minecraft:item.spawn_egg.entity.zombie_horse.name";

    /**
     * {@literal Zombie Nautilus Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_NAUTILUS_NAME = "minecraft:item.spawn_egg.entity.zombie_nautilus.name";

    /**
     * {@literal Zombified Piglin Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_PIGMAN_NAME = "minecraft:item.spawn_egg.entity.zombie_pigman.name";

    /**
     * {@literal Zombie Villager Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_VILLAGER_NAME = "minecraft:item.spawn_egg.entity.zombie_villager.name";

    /**
     * {@literal Zombie Villager Spawn Egg}
     */
    String MC_ITEM_SPAWN_EGG_ENTITY_ZOMBIE_VILLAGER_V2_NAME = "minecraft:item.spawn_egg.entity.zombie_villager_v2.name";

    /**
     * {@literal Glistering Melon Slice}
     */
    String MC_ITEM_SPECKLED_MELON_NAME = "minecraft:item.speckled_melon.name";

    /**
     * {@literal Spider Eye}
     */
    String MC_ITEM_SPIDER_EYE_NAME = "minecraft:item.spider_eye.name";

    /**
     * {@literal Spire Armor Trim}
     */
    String MC_ITEM_SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.spire_armor_trim_smithing_template.name";

    /**
     * {@literal Spruce Door}
     */
    String MC_ITEM_SPRUCE_DOOR_NAME = "minecraft:item.spruce_door.name";

    /**
     * {@literal Spruce Hanging Sign}
     */
    String MC_ITEM_SPRUCE_HANGING_SIGN_NAME = "minecraft:item.spruce_hanging_sign.name";

    /**
     * {@literal Spruce Sign}
     */
    String MC_ITEM_SPRUCE_SIGN_NAME = "minecraft:item.spruce_sign.name";

    /**
     * {@literal Spyglass}
     */
    String MC_ITEM_SPYGLASS_NAME = "minecraft:item.spyglass.name";

    /**
     * {@literal Steak}
     */
    String MC_ITEM_STEAK_NAME = "minecraft:item.steak.name";

    /**
     * {@literal Stick}
     */
    String MC_ITEM_STICK_NAME = "minecraft:item.stick.name";

    /**
     * {@literal Stone Axe}
     */
    String MC_ITEM_STONE_AXE_NAME = "minecraft:item.stone_axe.name";

    /**
     * {@literal Stone Hoe}
     */
    String MC_ITEM_STONE_HOE_NAME = "minecraft:item.stone_hoe.name";

    /**
     * {@literal Stone Pickaxe}
     */
    String MC_ITEM_STONE_PICKAXE_NAME = "minecraft:item.stone_pickaxe.name";

    /**
     * {@literal Stone Shovel}
     */
    String MC_ITEM_STONE_SHOVEL_NAME = "minecraft:item.stone_shovel.name";

    /**
     * {@literal Stone Spear}
     */
    String MC_ITEM_STONE_SPEAR_NAME = "minecraft:item.stone_spear.name";

    /**
     * {@literal Stone Sword}
     */
    String MC_ITEM_STONE_SWORD_NAME = "minecraft:item.stone_sword.name";

    /**
     * {@literal String}
     */
    String MC_ITEM_STRING_NAME = "minecraft:item.string.name";

    /**
     * {@literal Sugar}
     */
    String MC_ITEM_SUGAR_NAME = "minecraft:item.sugar.name";

    /**
     * {@literal Suspicious Stew}
     */
    String MC_ITEM_SUSPICIOUS_STEW_NAME = "minecraft:item.suspicious_stew.name";

    /**
     * {@literal Sweet Berries}
     */
    String MC_ITEM_SWEET_BERRIES_NAME = "minecraft:item.sweet_berries.name";

    /**
     * {@literal Tide Armor Trim}
     */
    String MC_ITEM_TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.tide_armor_trim_smithing_template.name";

    /**
     * {@literal Tipped Arrow}
     */
    String MC_ITEM_TIPPED_ARROW_NAME = "minecraft:item.tipped_arrow.name";

    /**
     * {@literal Minecart with TNT}
     */
    String MC_ITEM_TNT_MINECART_NAME = "minecraft:item.tnt_minecart.name";

    /**
     * {@literal Torchflower Seeds}
     */
    String MC_ITEM_TORCHFLOWER_SEEDS_NAME = "minecraft:item.torchflower_seeds.name";

    /**
     * {@literal Totem of Undying}
     */
    String MC_ITEM_TOTEM_NAME = "minecraft:item.totem.name";

    /**
     * {@literal Trial Key}
     */
    String MC_ITEM_TRIAL_KEY_NAME = "minecraft:item.trial_key.name";

    /**
     * {@literal Trident}
     */
    String MC_ITEM_TRIDENT_NAME = "minecraft:item.trident.name";

    /**
     * {@literal %1#s-%2#s Betty}
     */
    String MC_ITEM_TROPICALBODYBETTYMULTI_NAME = "minecraft:item.tropicalBodyBettyMulti.name";

    /**
     * {@literal %1#s Betty}
     */
    String MC_ITEM_TROPICALBODYBETTYSINGLE_NAME = "minecraft:item.tropicalBodyBettySingle.name";

    /**
     * {@literal %1#s-%2#s Blockfish}
     */
    String MC_ITEM_TROPICALBODYBLOCKFISHMULTI_NAME = "minecraft:item.tropicalBodyBlockfishMulti.name";

    /**
     * {@literal %1#s Blockfish}
     */
    String MC_ITEM_TROPICALBODYBLOCKFISHSINGLE_NAME = "minecraft:item.tropicalBodyBlockfishSingle.name";

    /**
     * {@literal %1#s-%2#s Brinely}
     */
    String MC_ITEM_TROPICALBODYBRINELYMULTI_NAME = "minecraft:item.tropicalBodyBrinelyMulti.name";

    /**
     * {@literal %1#s Brinely}
     */
    String MC_ITEM_TROPICALBODYBRINELYSINGLE_NAME = "minecraft:item.tropicalBodyBrinelySingle.name";

    /**
     * {@literal %1#s-%2#s Clayfish}
     */
    String MC_ITEM_TROPICALBODYCLAYFISHMULTI_NAME = "minecraft:item.tropicalBodyClayfishMulti.name";

    /**
     * {@literal %1#s Clayfish}
     */
    String MC_ITEM_TROPICALBODYCLAYFISHSINGLE_NAME = "minecraft:item.tropicalBodyClayfishSingle.name";

    /**
     * {@literal %1#s-%2#s Dasher}
     */
    String MC_ITEM_TROPICALBODYDASHERMULTI_NAME = "minecraft:item.tropicalBodyDasherMulti.name";

    /**
     * {@literal %1#s Dasher}
     */
    String MC_ITEM_TROPICALBODYDASHERSINGLE_NAME = "minecraft:item.tropicalBodyDasherSingle.name";

    /**
     * {@literal %1#s-%2#s Flopper}
     */
    String MC_ITEM_TROPICALBODYFLOPPERMULTI_NAME = "minecraft:item.tropicalBodyFlopperMulti.name";

    /**
     * {@literal %1#s Flopper}
     */
    String MC_ITEM_TROPICALBODYFLOPPERSINGLE_NAME = "minecraft:item.tropicalBodyFlopperSingle.name";

    /**
     * {@literal %1#s-%2#s Glitter}
     */
    String MC_ITEM_TROPICALBODYGLITTERMULTI_NAME = "minecraft:item.tropicalBodyGlitterMulti.name";

    /**
     * {@literal %1#s Glitter}
     */
    String MC_ITEM_TROPICALBODYGLITTERSINGLE_NAME = "minecraft:item.tropicalBodyGlitterSingle.name";

    /**
     * {@literal %1#s-%2#s Kob}
     */
    String MC_ITEM_TROPICALBODYKOBMULTI_NAME = "minecraft:item.tropicalBodyKobMulti.name";

    /**
     * {@literal %1#s Kob}
     */
    String MC_ITEM_TROPICALBODYKOBSINGLE_NAME = "minecraft:item.tropicalBodyKobSingle.name";

    /**
     * {@literal %1#s-%2#s Snooper}
     */
    String MC_ITEM_TROPICALBODYSNOOPERMULTI_NAME = "minecraft:item.tropicalBodySnooperMulti.name";

    /**
     * {@literal %1#s Snooper}
     */
    String MC_ITEM_TROPICALBODYSNOOPERSINGLE_NAME = "minecraft:item.tropicalBodySnooperSingle.name";

    /**
     * {@literal %1#s-%2#s Spotty}
     */
    String MC_ITEM_TROPICALBODYSPOTTYMULTI_NAME = "minecraft:item.tropicalBodySpottyMulti.name";

    /**
     * {@literal %1#s Spotty}
     */
    String MC_ITEM_TROPICALBODYSPOTTYSINGLE_NAME = "minecraft:item.tropicalBodySpottySingle.name";

    /**
     * {@literal %1#s-%2#s Stripey}
     */
    String MC_ITEM_TROPICALBODYSTRIPEYMULTI_NAME = "minecraft:item.tropicalBodyStripeyMulti.name";

    /**
     * {@literal %1#s Stripey}
     */
    String MC_ITEM_TROPICALBODYSTRIPEYSINGLE_NAME = "minecraft:item.tropicalBodyStripeySingle.name";

    /**
     * {@literal %1#s-%2#s SunStreak}
     */
    String MC_ITEM_TROPICALBODYSUNSTREAKMULTI_NAME = "minecraft:item.tropicalBodySunstreakMulti.name";

    /**
     * {@literal %1#s SunStreak}
     */
    String MC_ITEM_TROPICALBODYSUNSTREAKSINGLE_NAME = "minecraft:item.tropicalBodySunstreakSingle.name";

    /**
     * {@literal Blue}
     */
    String MC_ITEM_TROPICALCOLORBLUE_NAME = "minecraft:item.tropicalColorBlue.name";

    /**
     * {@literal Brown}
     */
    String MC_ITEM_TROPICALCOLORBROWN_NAME = "minecraft:item.tropicalColorBrown.name";

    /**
     * {@literal Gray}
     */
    String MC_ITEM_TROPICALCOLORGRAY_NAME = "minecraft:item.tropicalColorGray.name";

    /**
     * {@literal Green}
     */
    String MC_ITEM_TROPICALCOLORGREEN_NAME = "minecraft:item.tropicalColorGreen.name";

    /**
     * {@literal Lime}
     */
    String MC_ITEM_TROPICALCOLORLIME_NAME = "minecraft:item.tropicalColorLime.name";

    /**
     * {@literal Magenta}
     */
    String MC_ITEM_TROPICALCOLORMAGENTA_NAME = "minecraft:item.tropicalColorMagenta.name";

    /**
     * {@literal Orange}
     */
    String MC_ITEM_TROPICALCOLORORANGE_NAME = "minecraft:item.tropicalColorOrange.name";

    /**
     * {@literal Plum}
     */
    String MC_ITEM_TROPICALCOLORPLUM_NAME = "minecraft:item.tropicalColorPlum.name";

    /**
     * {@literal Red}
     */
    String MC_ITEM_TROPICALCOLORRED_NAME = "minecraft:item.tropicalColorRed.name";

    /**
     * {@literal Rose}
     */
    String MC_ITEM_TROPICALCOLORROSE_NAME = "minecraft:item.tropicalColorRose.name";

    /**
     * {@literal Silver}
     */
    String MC_ITEM_TROPICALCOLORSILVER_NAME = "minecraft:item.tropicalColorSilver.name";

    /**
     * {@literal Sky}
     */
    String MC_ITEM_TROPICALCOLORSKY_NAME = "minecraft:item.tropicalColorSky.name";

    /**
     * {@literal Teal}
     */
    String MC_ITEM_TROPICALCOLORTEAL_NAME = "minecraft:item.tropicalColorTeal.name";

    /**
     * {@literal White}
     */
    String MC_ITEM_TROPICALCOLORWHITE_NAME = "minecraft:item.tropicalColorWhite.name";

    /**
     * {@literal Yellow}
     */
    String MC_ITEM_TROPICALCOLORYELLOW_NAME = "minecraft:item.tropicalColorYellow.name";

    /**
     * {@literal Anemone}
     */
    String MC_ITEM_TROPICALSCHOOLANEMONE_NAME = "minecraft:item.tropicalSchoolAnemone.name";

    /**
     * {@literal Black Tang}
     */
    String MC_ITEM_TROPICALSCHOOLBLACKTANG_NAME = "minecraft:item.tropicalSchoolBlackTang.name";

    /**
     * {@literal Blue Dory}
     */
    String MC_ITEM_TROPICALSCHOOLBLUEDORY_NAME = "minecraft:item.tropicalSchoolBlueDory.name";

    /**
     * {@literal Butterfly Fish}
     */
    String MC_ITEM_TROPICALSCHOOLBUTTERFLYFISH_NAME = "minecraft:item.tropicalSchoolButterflyFish.name";

    /**
     * {@literal Chichlid}
     */
    String MC_ITEM_TROPICALSCHOOLCICHLID_NAME = "minecraft:item.tropicalSchoolCichlid.name";

    /**
     * {@literal Clownfish}
     */
    String MC_ITEM_TROPICALSCHOOLCLOWNFISH_NAME = "minecraft:item.tropicalSchoolClownfish.name";

    /**
     * {@literal Cotton Candy Betta}
     */
    String MC_ITEM_TROPICALSCHOOLCOTTONCANDYBETTA_NAME = "minecraft:item.tropicalSchoolCottonCandyBetta.name";

    /**
     * {@literal Dottyback}
     */
    String MC_ITEM_TROPICALSCHOOLDOTTYBACK_NAME = "minecraft:item.tropicalSchoolDottyback.name";

    /**
     * {@literal Emperor Red Snapper}
     */
    String MC_ITEM_TROPICALSCHOOLEMPERORREDSNAPPER_NAME = "minecraft:item.tropicalSchoolEmperorRedSnapper.name";

    /**
     * {@literal Goatfish}
     */
    String MC_ITEM_TROPICALSCHOOLGOATFISH_NAME = "minecraft:item.tropicalSchoolGoatfish.name";

    /**
     * {@literal Moorish Idol}
     */
    String MC_ITEM_TROPICALSCHOOLMOORISHIDOL_NAME = "minecraft:item.tropicalSchoolMoorishIdol.name";

    /**
     * {@literal Ornate Butterfly}
     */
    String MC_ITEM_TROPICALSCHOOLORNATEBUTTERFLY_NAME = "minecraft:item.tropicalSchoolOrnateButterfly.name";

    /**
     * {@literal Parrotfish}
     */
    String MC_ITEM_TROPICALSCHOOLPARROTFISH_NAME = "minecraft:item.tropicalSchoolParrotfish.name";

    /**
     * {@literal Queen Angel Fish}
     */
    String MC_ITEM_TROPICALSCHOOLQUEENANGELFISH_NAME = "minecraft:item.tropicalSchoolQueenAngelFish.name";

    /**
     * {@literal Red Cichlid}
     */
    String MC_ITEM_TROPICALSCHOOLREDCICHLID_NAME = "minecraft:item.tropicalSchoolRedCichlid.name";

    /**
     * {@literal Red Lipped Blenny}
     */
    String MC_ITEM_TROPICALSCHOOLREDLIPPEDBLENNY_NAME = "minecraft:item.tropicalSchoolRedLippedBlenny.name";

    /**
     * {@literal Red Snapper}
     */
    String MC_ITEM_TROPICALSCHOOLREDSNAPPER_NAME = "minecraft:item.tropicalSchoolRedSnapper.name";

    /**
     * {@literal Threadfin}
     */
    String MC_ITEM_TROPICALSCHOOLTHREADFIN_NAME = "minecraft:item.tropicalSchoolThreadfin.name";

    /**
     * {@literal Tomato Clown}
     */
    String MC_ITEM_TROPICALSCHOOLTOMATOCLOWN_NAME = "minecraft:item.tropicalSchoolTomatoClown.name";

    /**
     * {@literal Triggerfish}
     */
    String MC_ITEM_TROPICALSCHOOLTRIGGERFISH_NAME = "minecraft:item.tropicalSchoolTriggerfish.name";

    /**
     * {@literal Yellow Tang}
     */
    String MC_ITEM_TROPICALSCHOOLYELLOWTANG_NAME = "minecraft:item.tropicalSchoolYellowTang.name";

    /**
     * {@literal Yellowtail Parrot}
     */
    String MC_ITEM_TROPICALSCHOOLYELLOWTAILPARROT_NAME = "minecraft:item.tropicalSchoolYellowtailParrot.name";

    /**
     * {@literal Turtle Shell}
     */
    String MC_ITEM_TURTLE_HELMET_NAME = "minecraft:item.turtle_helmet.name";

    /**
     * {@literal Turtle Scute}
     */
    String MC_ITEM_TURTLE_SHELL_PIECE_NAME = "minecraft:item.turtle_shell_piece.name";

    /**
     * {@literal Unbreakable}
     */
    String MC_ITEM_UNBREAKABLE = "minecraft:item.unbreakable";

    /**
     * {@literal Vex Armor Trim}
     */
    String MC_ITEM_VEX_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.vex_armor_trim_smithing_template.name";

    /**
     * {@literal Ward Armor Trim}
     */
    String MC_ITEM_WARD_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.ward_armor_trim_smithing_template.name";

    /**
     * {@literal Warped Door}
     */
    String MC_ITEM_WARPED_DOOR_NAME = "minecraft:item.warped_door.name";

    /**
     * {@literal Warped Fungus on a Stick}
     */
    String MC_ITEM_WARPED_FUNGUS_ON_A_STICK_NAME = "minecraft:item.warped_fungus_on_a_stick.name";

    /**
     * {@literal Warped Hanging Sign}
     */
    String MC_ITEM_WARPED_HANGING_SIGN_NAME = "minecraft:item.warped_hanging_sign.name";

    /**
     * {@literal Warped Sign}
     */
    String MC_ITEM_WARPED_SIGN_NAME = "minecraft:item.warped_sign.name";

    /**
     * {@literal Waxed Copper Door}
     */
    String MC_ITEM_WAXED_COPPER_DOOR_NAME = "minecraft:item.waxed_copper_door.name";

    /**
     * {@literal Waxed Exposed Copper Door}
     */
    String MC_ITEM_WAXED_EXPOSED_COPPER_DOOR_NAME = "minecraft:item.waxed_exposed_copper_door.name";

    /**
     * {@literal Waxed Oxidized Copper Door}
     */
    String MC_ITEM_WAXED_OXIDIZED_COPPER_DOOR_NAME = "minecraft:item.waxed_oxidized_copper_door.name";

    /**
     * {@literal Waxed Weathered Copper Door}
     */
    String MC_ITEM_WAXED_WEATHERED_COPPER_DOOR_NAME = "minecraft:item.waxed_weathered_copper_door.name";

    /**
     * {@literal Wayfinder Armor Trim}
     */
    String MC_ITEM_WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.wayfinder_armor_trim_smithing_template.name";

    /**
     * {@literal Weathered Copper Door}
     */
    String MC_ITEM_WEATHERED_COPPER_DOOR_NAME = "minecraft:item.weathered_copper_door.name";

    /**
     * {@literal Wheat}
     */
    String MC_ITEM_WHEAT_NAME = "minecraft:item.wheat.name";

    /**
     * {@literal Wheat Seeds}
     */
    String MC_ITEM_WHEAT_SEEDS_NAME = "minecraft:item.wheat_seeds.name";

    /**
     * {@literal White Bundle}
     */
    String MC_ITEM_WHITE_BUNDLE = "minecraft:item.white_bundle";

    /**
     * {@literal White Harness}
     */
    String MC_ITEM_WHITE_HARNESS_NAME = "minecraft:item.white_harness.name";

    /**
     * {@literal Wild Armor Trim}
     */
    String MC_ITEM_WILD_ARMOR_TRIM_SMITHING_TEMPLATE_NAME = "minecraft:item.wild_armor_trim_smithing_template.name";

    /**
     * {@literal Wind Charge}
     */
    String MC_ITEM_WIND_CHARGE_NAME = "minecraft:item.wind_charge.name";

    /**
     * {@literal Wolf Armor}
     */
    String MC_ITEM_WOLF_ARMOR_NAME = "minecraft:item.wolf_armor.name";

    /**
     * {@literal Wooden Axe}
     */
    String MC_ITEM_WOODEN_AXE_NAME = "minecraft:item.wooden_axe.name";

    /**
     * {@literal Oak Door}
     */
    String MC_ITEM_WOODEN_DOOR_NAME = "minecraft:item.wooden_door.name";

    /**
     * {@literal Wooden Hoe}
     */
    String MC_ITEM_WOODEN_HOE_NAME = "minecraft:item.wooden_hoe.name";

    /**
     * {@literal Wooden Pickaxe}
     */
    String MC_ITEM_WOODEN_PICKAXE_NAME = "minecraft:item.wooden_pickaxe.name";

    /**
     * {@literal Wooden Shovel}
     */
    String MC_ITEM_WOODEN_SHOVEL_NAME = "minecraft:item.wooden_shovel.name";

    /**
     * {@literal Wooden Spear}
     */
    String MC_ITEM_WOODEN_SPEAR_NAME = "minecraft:item.wooden_spear.name";

    /**
     * {@literal Wooden Sword}
     */
    String MC_ITEM_WOODEN_SWORD_NAME = "minecraft:item.wooden_sword.name";

    /**
     * {@literal You need Creative + Operator to place this block}
     */
    String MC_ITEM_WORLDBUILDER_BLOCK_FAILED = "minecraft:item.worldbuilder.block.failed";

    /**
     * {@literal Book and Quill}
     */
    String MC_ITEM_WRITABLE_BOOK_NAME = "minecraft:item.writable_book.name";

    /**
     * {@literal Written Book}
     */
    String MC_ITEM_WRITTEN_BOOK_NAME = "minecraft:item.written_book.name";

    /**
     * {@literal Yellow Bundle}
     */
    String MC_ITEM_YELLOW_BUNDLE = "minecraft:item.yellow_bundle";

    /**
     * {@literal Yellow Harness}
     */
    String MC_ITEM_YELLOW_HARNESS_NAME = "minecraft:item.yellow_harness.name";

    /**
     * {@literal Anvils}
     */
    String MC_ITEMGROUP_NAME_ANVIL = "minecraft:itemGroup.name.anvil";

    /**
     * {@literal Arrows}
     */
    String MC_ITEMGROUP_NAME_ARROW = "minecraft:itemGroup.name.arrow";

    /**
     * {@literal Axes}
     */
    String MC_ITEMGROUP_NAME_AXE = "minecraft:itemGroup.name.axe";

    /**
     * {@literal Banners}
     */
    String MC_ITEMGROUP_NAME_BANNER = "minecraft:itemGroup.name.banner";

    /**
     * {@literal Banner Patterns}
     */
    String MC_ITEMGROUP_NAME_BANNER_PATTERN = "minecraft:itemGroup.name.banner_pattern";

    /**
     * {@literal Bars}
     */
    String MC_ITEMGROUP_NAME_BARS = "minecraft:itemGroup.name.bars";

    /**
     * {@literal Beds}
     */
    String MC_ITEMGROUP_NAME_BED = "minecraft:itemGroup.name.bed";

    /**
     * {@literal Boats}
     */
    String MC_ITEMGROUP_NAME_BOAT = "minecraft:itemGroup.name.boat";

    /**
     * {@literal Boots}
     */
    String MC_ITEMGROUP_NAME_BOOTS = "minecraft:itemGroup.name.boots";

    /**
     * {@literal Bundles}
     */
    String MC_ITEMGROUP_NAME_BUNDLES = "minecraft:itemGroup.name.bundles";

    /**
     * {@literal Buttons}
     */
    String MC_ITEMGROUP_NAME_BUTTONS = "minecraft:itemGroup.name.buttons";

    /**
     * {@literal Candles}
     */
    String MC_ITEMGROUP_NAME_CANDLES = "minecraft:itemGroup.name.candles";

    /**
     * {@literal Chains}
     */
    String MC_ITEMGROUP_NAME_CHAINS = "minecraft:itemGroup.name.chains";

    /**
     * {@literal Chalkboards}
     */
    String MC_ITEMGROUP_NAME_CHALKBOARD = "minecraft:itemGroup.name.chalkboard";

    /**
     * {@literal Chests}
     */
    String MC_ITEMGROUP_NAME_CHEST = "minecraft:itemGroup.name.chest";

    /**
     * {@literal Boats with Chest}
     */
    String MC_ITEMGROUP_NAME_CHESTBOAT = "minecraft:itemGroup.name.chestboat";

    /**
     * {@literal Chestplates}
     */
    String MC_ITEMGROUP_NAME_CHESTPLATE = "minecraft:itemGroup.name.chestplate";

    /**
     * {@literal Compounds}
     */
    String MC_ITEMGROUP_NAME_COMPOUNDS = "minecraft:itemGroup.name.compounds";

    /**
     * {@literal Concrete}
     */
    String MC_ITEMGROUP_NAME_CONCRETE = "minecraft:itemGroup.name.concrete";

    /**
     * {@literal Concrete Powder}
     */
    String MC_ITEMGROUP_NAME_CONCRETEPOWDER = "minecraft:itemGroup.name.concretePowder";

    /**
     * {@literal Cooked Food}
     */
    String MC_ITEMGROUP_NAME_COOKEDFOOD = "minecraft:itemGroup.name.cookedFood";

    /**
     * {@literal Copper}
     */
    String MC_ITEMGROUP_NAME_COPPER = "minecraft:itemGroup.name.copper";

    /**
     * {@literal Copper Golem Statues}
     */
    String MC_ITEMGROUP_NAME_COPPER_GOLEM_STATUE = "minecraft:itemGroup.name.copper_golem_statue";

    /**
     * {@literal Coral Blocks}
     */
    String MC_ITEMGROUP_NAME_CORAL = "minecraft:itemGroup.name.coral";

    /**
     * {@literal Coral Decorations}
     */
    String MC_ITEMGROUP_NAME_CORAL_DECORATIONS = "minecraft:itemGroup.name.coral_decorations";

    /**
     * {@literal Crops}
     */
    String MC_ITEMGROUP_NAME_CROP = "minecraft:itemGroup.name.crop";

    /**
     * {@literal Doors}
     */
    String MC_ITEMGROUP_NAME_DOOR = "minecraft:itemGroup.name.door";

    /**
     * {@literal Dyes}
     */
    String MC_ITEMGROUP_NAME_DYE = "minecraft:itemGroup.name.dye";

    /**
     * {@literal Enchanted Books}
     */
    String MC_ITEMGROUP_NAME_ENCHANTEDBOOK = "minecraft:itemGroup.name.enchantedBook";

    /**
     * {@literal Fences}
     */
    String MC_ITEMGROUP_NAME_FENCE = "minecraft:itemGroup.name.fence";

    /**
     * {@literal Fence Gates}
     */
    String MC_ITEMGROUP_NAME_FENCEGATE = "minecraft:itemGroup.name.fenceGate";

    /**
     * {@literal Fireworks}
     */
    String MC_ITEMGROUP_NAME_FIREWORK = "minecraft:itemGroup.name.firework";

    /**
     * {@literal Firework Charges}
     */
    String MC_ITEMGROUP_NAME_FIREWORKSTARS = "minecraft:itemGroup.name.fireworkStars";

    /**
     * {@literal Flowers}
     */
    String MC_ITEMGROUP_NAME_FLOWER = "minecraft:itemGroup.name.flower";

    /**
     * {@literal Glass}
     */
    String MC_ITEMGROUP_NAME_GLASS = "minecraft:itemGroup.name.glass";

    /**
     * {@literal Glass Panes}
     */
    String MC_ITEMGROUP_NAME_GLASSPANE = "minecraft:itemGroup.name.glassPane";

    /**
     * {@literal Glazed Terracottas}
     */
    String MC_ITEMGROUP_NAME_GLAZEDTERRACOTTA = "minecraft:itemGroup.name.glazedTerracotta";

    /**
     * {@literal Goat Horns}
     */
    String MC_ITEMGROUP_NAME_GOATHORN = "minecraft:itemGroup.name.goatHorn";

    /**
     * {@literal Ground Cover}
     */
    String MC_ITEMGROUP_NAME_GRASS = "minecraft:itemGroup.name.grass";

    /**
     * {@literal Hanging Signs}
     */
    String MC_ITEMGROUP_NAME_HANGING_SIGN = "minecraft:itemGroup.name.hanging_sign";

    /**
     * {@literal Harnesses}
     */
    String MC_ITEMGROUP_NAME_HARNESSES = "minecraft:itemGroup.name.harnesses";

    /**
     * {@literal Helmets}
     */
    String MC_ITEMGROUP_NAME_HELMET = "minecraft:itemGroup.name.helmet";

    /**
     * {@literal Hoes}
     */
    String MC_ITEMGROUP_NAME_HOE = "minecraft:itemGroup.name.hoe";

    /**
     * {@literal Horse Armor}
     */
    String MC_ITEMGROUP_NAME_HORSEARMOR = "minecraft:itemGroup.name.horseArmor";

    /**
     * {@literal Lanterns}
     */
    String MC_ITEMGROUP_NAME_LANTERNS = "minecraft:itemGroup.name.lanterns";

    /**
     * {@literal Leaves}
     */
    String MC_ITEMGROUP_NAME_LEAVES = "minecraft:itemGroup.name.leaves";

    /**
     * {@literal Leggings}
     */
    String MC_ITEMGROUP_NAME_LEGGINGS = "minecraft:itemGroup.name.leggings";

    /**
     * {@literal Lightning Rods}
     */
    String MC_ITEMGROUP_NAME_LIGHTNING_ROD = "minecraft:itemGroup.name.lightning_rod";

    /**
     * {@literal Lingering Potions}
     */
    String MC_ITEMGROUP_NAME_LINGERINGPOTION = "minecraft:itemGroup.name.lingeringPotion";

    /**
     * {@literal Logs}
     */
    String MC_ITEMGROUP_NAME_LOG = "minecraft:itemGroup.name.log";

    /**
     * {@literal Minecarts}
     */
    String MC_ITEMGROUP_NAME_MINECART = "minecraft:itemGroup.name.minecart";

    /**
     * {@literal Miscellaneous Foods}
     */
    String MC_ITEMGROUP_NAME_MISCFOOD = "minecraft:itemGroup.name.miscFood";

    /**
     * {@literal Mob Eggs}
     */
    String MC_ITEMGROUP_NAME_MOBEGG = "minecraft:itemGroup.name.mobEgg";

    /**
     * {@literal Infested Stone}
     */
    String MC_ITEMGROUP_NAME_MONSTERSTONEEGG = "minecraft:itemGroup.name.monsterStoneEgg";

    /**
     * {@literal Mushrooms}
     */
    String MC_ITEMGROUP_NAME_MUSHROOM = "minecraft:itemGroup.name.mushroom";

    /**
     * {@literal Nautilus Armor}
     */
    String MC_ITEMGROUP_NAME_NAUTILUS_ARMOR = "minecraft:itemGroup.name.nautilus_armor";

    /**
     * {@literal Nether Warts}
     */
    String MC_ITEMGROUP_NAME_NETHERWARTBLOCK = "minecraft:itemGroup.name.netherWartBlock";

    /**
     * {@literal Ominous Bottles}
     */
    String MC_ITEMGROUP_NAME_OMINOUSBOTTLE = "minecraft:itemGroup.name.ominousBottle";

    /**
     * {@literal Ores}
     */
    String MC_ITEMGROUP_NAME_ORE = "minecraft:itemGroup.name.ore";

    /**
     * {@literal Permission Blocks}
     */
    String MC_ITEMGROUP_NAME_PERMISSION = "minecraft:itemGroup.name.permission";

    /**
     * {@literal Pickaxes}
     */
    String MC_ITEMGROUP_NAME_PICKAXE = "minecraft:itemGroup.name.pickaxe";

    /**
     * {@literal Planks}
     */
    String MC_ITEMGROUP_NAME_PLANKS = "minecraft:itemGroup.name.planks";

    /**
     * {@literal Potions}
     */
    String MC_ITEMGROUP_NAME_POTION = "minecraft:itemGroup.name.potion";

    /**
     * {@literal Pottery Sherds}
     */
    String MC_ITEMGROUP_NAME_POTTERYSHERDS = "minecraft:itemGroup.name.potterySherds";

    /**
     * {@literal Pressure Plates}
     */
    String MC_ITEMGROUP_NAME_PRESSUREPLATE = "minecraft:itemGroup.name.pressurePlate";

    /**
     * {@literal Products}
     */
    String MC_ITEMGROUP_NAME_PRODUCTS = "minecraft:itemGroup.name.products";

    /**
     * {@literal Rails}
     */
    String MC_ITEMGROUP_NAME_RAIL = "minecraft:itemGroup.name.rail";

    /**
     * {@literal Raw Food}
     */
    String MC_ITEMGROUP_NAME_RAWFOOD = "minecraft:itemGroup.name.rawFood";

    /**
     * {@literal Records}
     */
    String MC_ITEMGROUP_NAME_RECORD = "minecraft:itemGroup.name.record";

    /**
     * {@literal Sandstone}
     */
    String MC_ITEMGROUP_NAME_SANDSTONE = "minecraft:itemGroup.name.sandstone";

    /**
     * {@literal Saplings}
     */
    String MC_ITEMGROUP_NAME_SAPLING = "minecraft:itemGroup.name.sapling";

    /**
     * {@literal Sculk}
     */
    String MC_ITEMGROUP_NAME_SCULK = "minecraft:itemGroup.name.sculk";

    /**
     * {@literal Seeds}
     */
    String MC_ITEMGROUP_NAME_SEED = "minecraft:itemGroup.name.seed";

    /**
     * {@literal Shelves}
     */
    String MC_ITEMGROUP_NAME_SHELF = "minecraft:itemGroup.name.shelf";

    /**
     * {@literal Shovels}
     */
    String MC_ITEMGROUP_NAME_SHOVEL = "minecraft:itemGroup.name.shovel";

    /**
     * {@literal Shulker Boxes}
     */
    String MC_ITEMGROUP_NAME_SHULKERBOX = "minecraft:itemGroup.name.shulkerBox";

    /**
     * {@literal Signs}
     */
    String MC_ITEMGROUP_NAME_SIGN = "minecraft:itemGroup.name.sign";

    /**
     * {@literal Mob Skulls}
     */
    String MC_ITEMGROUP_NAME_SKULL = "minecraft:itemGroup.name.skull";

    /**
     * {@literal Slabs}
     */
    String MC_ITEMGROUP_NAME_SLAB = "minecraft:itemGroup.name.slab";

    /**
     * {@literal Smithing Templates}
     */
    String MC_ITEMGROUP_NAME_SMITHING_TEMPLATES = "minecraft:itemGroup.name.smithing_templates";

    /**
     * {@literal Spears}
     */
    String MC_ITEMGROUP_NAME_SPEAR = "minecraft:itemGroup.name.spear";

    /**
     * {@literal Splash Potions}
     */
    String MC_ITEMGROUP_NAME_SPLASHPOTION = "minecraft:itemGroup.name.splashPotion";

    /**
     * {@literal Terracotta}
     */
    String MC_ITEMGROUP_NAME_STAINEDCLAY = "minecraft:itemGroup.name.stainedClay";

    /**
     * {@literal Stairs}
     */
    String MC_ITEMGROUP_NAME_STAIRS = "minecraft:itemGroup.name.stairs";

    /**
     * {@literal Stone}
     */
    String MC_ITEMGROUP_NAME_STONE = "minecraft:itemGroup.name.stone";

    /**
     * {@literal Decorative Stone}
     */
    String MC_ITEMGROUP_NAME_STONEBRICK = "minecraft:itemGroup.name.stoneBrick";

    /**
     * {@literal Swords}
     */
    String MC_ITEMGROUP_NAME_SWORD = "minecraft:itemGroup.name.sword";

    /**
     * {@literal Trapdoors}
     */
    String MC_ITEMGROUP_NAME_TRAPDOOR = "minecraft:itemGroup.name.trapdoor";

    /**
     * {@literal Walls}
     */
    String MC_ITEMGROUP_NAME_WALLS = "minecraft:itemGroup.name.walls";

    /**
     * {@literal Woods}
     */
    String MC_ITEMGROUP_NAME_WOOD = "minecraft:itemGroup.name.wood";

    /**
     * {@literal Wool}
     */
    String MC_ITEMGROUP_NAME_WOOL = "minecraft:itemGroup.name.wool";

    /**
     * {@literal Wool Carpet}
     */
    String MC_ITEMGROUP_NAME_WOOLCARPET = "minecraft:itemGroup.name.woolCarpet";

    /**
     * {@literal Search Items}
     */
    String MC_ITEMGROUP_SEARCH = "minecraft:itemGroup.search";

    /**
     * {@literal Done}
     */
    String MC_JIGSAW_EXIT_DONE = "minecraft:jigsaw.exit.done";

    /**
     * {@literal Aligned}
     */
    String MC_JIGSAW_JOINT_TYPE_ALIGNED = "minecraft:jigsaw.joint_type.aligned";

    /**
     * {@literal Rollable}
     */
    String MC_JIGSAW_JOINT_TYPE_ROLLABLE = "minecraft:jigsaw.joint_type.rollable";

    /**
     * {@literal Turns Into:}
     */
    String MC_JIGSAW_TITLE_FINAL_BLOCK = "minecraft:jigsaw.title.final_block";

    /**
     * {@literal Joint Type:}
     */
    String MC_JIGSAW_TITLE_JOINT_TYPE = "minecraft:jigsaw.title.joint_type";

    /**
     * {@literal Aligned}
     */
    String MC_JIGSAW_TITLE_JOINT_TYPE_ALIGNED = "minecraft:jigsaw.title.joint_type.aligned";

    /**
     * {@literal Name:}
     */
    String MC_JIGSAW_TITLE_NAME = "minecraft:jigsaw.title.name";

    /**
     * {@literal Placement Priority:}
     */
    String MC_JIGSAW_TITLE_PLACEMENT_PRIORITY = "minecraft:jigsaw.title.placement_priority";

    /**
     * {@literal Selection Priority:}
     */
    String MC_JIGSAW_TITLE_SELECTION_PRIORITY = "minecraft:jigsaw.title.selection_priority";

    /**
     * {@literal Target Name:}
     */
    String MC_JIGSAW_TITLE_TARGET = "minecraft:jigsaw.title.target";

    /**
     * {@literal Target Pool:}
     */
    String MC_JIGSAW_TITLE_TARGET_POOL = "minecraft:jigsaw.title.target_pool";

    /**
     * {@literal Jigsaw Block}
     */
    String MC_JIGSAW_BLOCK_TITLE = "minecraft:jigsaw_block.title";

    /**
     * {@literal %s: Did not find valid value for property. Expected %s}
     */
    String MC_JSONVALIDATIONERROR_INVALIDVALUEERROR = "minecraft:jsonValidationError.invalidValueError";

    /**
     * {@literal %s: Could not find required property '%s'}
     */
    String MC_JSONVALIDATIONERROR_REQUIREDPROPERTYERROR = "minecraft:jsonValidationError.requiredPropertyError";

    /**
     * {@literal %s: Invalid type for property. Expected %s got %s}
     */
    String MC_JSONVALIDATIONERROR_TYPEERROR = "minecraft:jsonValidationError.typeError";

    /**
     * {@literal Attack/Destroy}
     */
    String MC_KEY_ATTACK = "minecraft:key.attack";

    /**
     * {@literal Walk Backwards}
     */
    String MC_KEY_BACK = "minecraft:key.back";

    /**
     * {@literal Cancel    }
     */
    String MC_KEY_CANCELTOAST = "minecraft:key.cancelToast";

    /**
     * {@literal Gameplay}
     */
    String MC_KEY_CATEGORIES_GAMEPLAY = "minecraft:key.categories.gameplay";

    /**
     * {@literal Inventory}
     */
    String MC_KEY_CATEGORIES_INVENTORY = "minecraft:key.categories.inventory";

    /**
     * {@literal Miscellaneous}
     */
    String MC_KEY_CATEGORIES_MISC = "minecraft:key.categories.misc";

    /**
     * {@literal Movement}
     */
    String MC_KEY_CATEGORIES_MOVEMENT = "minecraft:key.categories.movement";

    /**
     * {@literal Multiplayer}
     */
    String MC_KEY_CATEGORIES_MULTIPLAYER = "minecraft:key.categories.multiplayer";

    /**
     * {@literal Streaming}
     */
    String MC_KEY_CATEGORIES_STREAM = "minecraft:key.categories.stream";

    /**
     * {@literal Game Interface}
     */
    String MC_KEY_CATEGORIES_UI = "minecraft:key.categories.ui";

    /**
     * {@literal Chat button}
     */
    String MC_KEY_CHAT = "minecraft:key.chat";

    /**
     * {@literal Code Builder}
     */
    String MC_KEY_CODEBUILDER = "minecraft:key.codeBuilder";

    /**
     * {@literal Open Command}
     */
    String MC_KEY_COMMAND = "minecraft:key.command";

    /**
     * {@literal Copy Coordinates}
     */
    String MC_KEY_COPYCOORDINATES = "minecraft:key.copyCoordinates";

    /**
     * {@literal Copy Facing Coordinates}
     */
    String MC_KEY_COPYFACINGCOORDINATES = "minecraft:key.copyFacingCoordinates";

    /**
     * {@literal Cycle Item Left}
     */
    String MC_KEY_CYCLEITEMLEFT = "minecraft:key.cycleItemLeft";

    /**
     * {@literal Cycle Item Right}
     */
    String MC_KEY_CYCLEITEMRIGHT = "minecraft:key.cycleItemRight";

    /**
     * {@literal Cycle Fixed Inventory}
     */
    String MC_KEY_CYCLEFIXEDINVENTORY = "minecraft:key.cyclefixedinventory";

    /**
     * {@literal Decline    }
     */
    String MC_KEY_DECLINETOAST = "minecraft:key.declineToast";

    /**
     * {@literal Drop Item}
     */
    String MC_KEY_DROP = "minecraft:key.drop";

    /**
     * {@literal Emote}
     */
    String MC_KEY_EMOTE = "minecraft:key.emote";

    /**
     * {@literal Fly Down Slow}
     */
    String MC_KEY_FLYDOWNSLOW = "minecraft:key.flyDownSlow";

    /**
     * {@literal Fly Up Slow}
     */
    String MC_KEY_FLYUPSLOW = "minecraft:key.flyUpSlow";

    /**
     * {@literal Walk Forwards}
     */
    String MC_KEY_FORWARD = "minecraft:key.forward";

    /**
     * {@literal Toggle Fullscreen}
     */
    String MC_KEY_FULLSCREEN = "minecraft:key.fullscreen";

    /**
     * {@literal Hotbar Slot 1}
     */
    String MC_KEY_HOTBAR_1 = "minecraft:key.hotbar.1";

    /**
     * {@literal Hotbar Slot 2}
     */
    String MC_KEY_HOTBAR_2 = "minecraft:key.hotbar.2";

    /**
     * {@literal Hotbar Slot 3}
     */
    String MC_KEY_HOTBAR_3 = "minecraft:key.hotbar.3";

    /**
     * {@literal Hotbar Slot 4}
     */
    String MC_KEY_HOTBAR_4 = "minecraft:key.hotbar.4";

    /**
     * {@literal Hotbar Slot 5}
     */
    String MC_KEY_HOTBAR_5 = "minecraft:key.hotbar.5";

    /**
     * {@literal Hotbar Slot 6}
     */
    String MC_KEY_HOTBAR_6 = "minecraft:key.hotbar.6";

    /**
     * {@literal Hotbar Slot 7}
     */
    String MC_KEY_HOTBAR_7 = "minecraft:key.hotbar.7";

    /**
     * {@literal Hotbar Slot 8}
     */
    String MC_KEY_HOTBAR_8 = "minecraft:key.hotbar.8";

    /**
     * {@literal Hotbar Slot 9}
     */
    String MC_KEY_HOTBAR_9 = "minecraft:key.hotbar.9";

    /**
     * {@literal Immersive Reader}
     */
    String MC_KEY_IMMERSIVEREADER = "minecraft:key.immersivereader";

    /**
     * {@literal Game Menu}
     */
    String MC_KEY_INGAMEMENU = "minecraft:key.inGameMenu";

    /**
     * {@literal Open Notification}
     */
    String MC_KEY_INTERACTWITHTOAST = "minecraft:key.interactwithtoast";

    /**
     * {@literal Inventory}
     */
    String MC_KEY_INVENTORY = "minecraft:key.inventory";

    /**
     * {@literal Jump/Fly Up}
     */
    String MC_KEY_JUMP = "minecraft:key.jump";

    /**
     * {@literal Strafe Left}
     */
    String MC_KEY_LEFT = "minecraft:key.left";

    /**
     * {@literal Look Center}
     */
    String MC_KEY_LOOKCENTER = "minecraft:key.lookCenter";

    /**
     * {@literal Look Down}
     */
    String MC_KEY_LOOKDOWN = "minecraft:key.lookDown";

    /**
     * {@literal Look Down Left}
     */
    String MC_KEY_LOOKDOWNLEFT = "minecraft:key.lookDownLeft";

    /**
     * {@literal Look Down Right}
     */
    String MC_KEY_LOOKDOWNRIGHT = "minecraft:key.lookDownRight";

    /**
     * {@literal Look Down Slight}
     */
    String MC_KEY_LOOKDOWNSLIGHT = "minecraft:key.lookDownSlight";

    /**
     * {@literal Look Down Smooth}
     */
    String MC_KEY_LOOKDOWNSMOOTH = "minecraft:key.lookDownSmooth";

    /**
     * {@literal Look Left}
     */
    String MC_KEY_LOOKLEFT = "minecraft:key.lookLeft";

    /**
     * {@literal Look Left Smooth}
     */
    String MC_KEY_LOOKLEFTSMOOTH = "minecraft:key.lookLeftSmooth";

    /**
     * {@literal Look Right}
     */
    String MC_KEY_LOOKRIGHT = "minecraft:key.lookRight";

    /**
     * {@literal Look Right Smooth}
     */
    String MC_KEY_LOOKRIGHTSMOOTH = "minecraft:key.lookRightSmooth";

    /**
     * {@literal Look Up}
     */
    String MC_KEY_LOOKUP = "minecraft:key.lookUp";

    /**
     * {@literal Look Up Left}
     */
    String MC_KEY_LOOKUPLEFT = "minecraft:key.lookUpLeft";

    /**
     * {@literal Look Up Right}
     */
    String MC_KEY_LOOKUPRIGHT = "minecraft:key.lookUpRight";

    /**
     * {@literal Look Up Slight}
     */
    String MC_KEY_LOOKUPSLIGHT = "minecraft:key.lookUpSlight";

    /**
     * {@literal Look Up Smooth}
     */
    String MC_KEY_LOOKUPSMOOTH = "minecraft:key.lookUpSmooth";

    /**
     * {@literal Menu Cancel}
     */
    String MC_KEY_MENUCANCEL = "minecraft:key.menuCancel";

    /**
     * {@literal Menu Tab Left}
     */
    String MC_KEY_MENUTABLEFT = "minecraft:key.menuTabLeft";

    /**
     * {@literal Menu Tab Right}
     */
    String MC_KEY_MENUTABRIGHT = "minecraft:key.menuTabRight";

    /**
     * {@literal Mob Effects}
     */
    String MC_KEY_MOBEFFECTS = "minecraft:key.mobEffects";

    /**
     * {@literal Open Notification/Mob Effects}
     */
    String MC_KEY_MOBEFFECTSANDINTERACTWITHTOAST = "minecraft:key.mobeffectsandinteractwithtoast";

    /**
     * {@literal Button %1#s}
     */
    String MC_KEY_MOUSEBUTTON = "minecraft:key.mouseButton";

    /**
     * {@literal Move Back}
     */
    String MC_KEY_MOVEBACK = "minecraft:key.moveBack";

    /**
     * {@literal Move Forward}
     */
    String MC_KEY_MOVEFORWARD = "minecraft:key.moveForward";

    /**
     * {@literal Move Left}
     */
    String MC_KEY_MOVELEFT = "minecraft:key.moveLeft";

    /**
     * {@literal Move Right}
     */
    String MC_KEY_MOVERIGHT = "minecraft:key.moveRight";

    /**
     * {@literal Pause}
     */
    String MC_KEY_PAUSE = "minecraft:key.pause";

    /**
     * {@literal Pick Block}
     */
    String MC_KEY_PICKITEM = "minecraft:key.pickItem";

    /**
     * {@literal List Players}
     */
    String MC_KEY_PLAYERLIST = "minecraft:key.playerlist";

    /**
     * {@literal Strafe Right}
     */
    String MC_KEY_RIGHT = "minecraft:key.right";

    /**
     * {@literal Show Scoreboard}
     */
    String MC_KEY_SCOREBOARD = "minecraft:key.scoreboard";

    /**
     * {@literal Take Screenshot}
     */
    String MC_KEY_SCREENSHOT = "minecraft:key.screenshot";

    /**
     * {@literal Cancel Current Action}
     */
    String MC_KEY_SCRIPT_CANCEL = "minecraft:key.script_cancel";

    /**
     * {@literal Open Chat UI}
     */
    String MC_KEY_SCRIPT_OPEN_CHAT = "minecraft:key.script_open_chat";

    /**
     * {@literal Open Inventory UI}
     */
    String MC_KEY_SCRIPT_OPEN_INVENTORY = "minecraft:key.script_open_inventory";

    /**
     * {@literal Open Map UI}
     */
    String MC_KEY_SCRIPT_OPEN_MAP = "minecraft:key.script_open_map";

    /**
     * {@literal Open Mission UI}
     */
    String MC_KEY_SCRIPT_OPEN_MISSION = "minecraft:key.script_open_mission";

    /**
     * {@literal Toggle Mouse Mode}
     */
    String MC_KEY_SCRIPT_TOGGLE = "minecraft:key.script_toggle";

    /**
     * {@literal Toggle Cinematic Camera}
     */
    String MC_KEY_SMOOTHCAMERA = "minecraft:key.smoothCamera";

    /**
     * {@literal Sneak/Fly Down}
     */
    String MC_KEY_SNEAK = "minecraft:key.sneak";

    /**
     * {@literal Highlight Players (Spectators)}
     */
    String MC_KEY_SPECTATOROUTLINES = "minecraft:key.spectatorOutlines";

    /**
     * {@literal Sprint}
     */
    String MC_KEY_SPRINT = "minecraft:key.sprint";

    /**
     * {@literal Show Stream Commercials}
     */
    String MC_KEY_STREAMCOMMERCIAL = "minecraft:key.streamCommercial";

    /**
     * {@literal Pause/Unpause Stream}
     */
    String MC_KEY_STREAMPAUSEUNPAUSE = "minecraft:key.streamPauseUnpause";

    /**
     * {@literal Start/Stop Stream}
     */
    String MC_KEY_STREAMSTARTSTOP = "minecraft:key.streamStartStop";

    /**
     * {@literal Push To Talk/Mute}
     */
    String MC_KEY_STREAMTOGGLEMIC = "minecraft:key.streamToggleMic";

    /**
     * {@literal Control Tips}
     */
    String MC_KEY_TOGGLECONTROLTIPS = "minecraft:key.toggleControlTips";

    /**
     * {@literal Toggle Perspective}
     */
    String MC_KEY_TOGGLEPERSPECTIVE = "minecraft:key.togglePerspective";

    /**
     * {@literal Toggle Renderer}
     */
    String MC_KEY_TOGGLERENDERER = "minecraft:key.toggleRenderer";

    /**
     * {@literal Use Item/Place Block}
     */
    String MC_KEY_USE = "minecraft:key.use";

    /**
     * {@literal View    }
     */
    String MC_KEY_VIEWTOAST = "minecraft:key.viewToast";

    /**
     * {@literal 0}
     */
    String MC_KEYBOARD_KEYNAME_0 = "minecraft:keyboard.keyName.0";

    /**
     * {@literal 1}
     */
    String MC_KEYBOARD_KEYNAME_1 = "minecraft:keyboard.keyName.1";

    /**
     * {@literal 2}
     */
    String MC_KEYBOARD_KEYNAME_2 = "minecraft:keyboard.keyName.2";

    /**
     * {@literal 3}
     */
    String MC_KEYBOARD_KEYNAME_3 = "minecraft:keyboard.keyName.3";

    /**
     * {@literal 4}
     */
    String MC_KEYBOARD_KEYNAME_4 = "minecraft:keyboard.keyName.4";

    /**
     * {@literal 5}
     */
    String MC_KEYBOARD_KEYNAME_5 = "minecraft:keyboard.keyName.5";

    /**
     * {@literal 6}
     */
    String MC_KEYBOARD_KEYNAME_6 = "minecraft:keyboard.keyName.6";

    /**
     * {@literal 7}
     */
    String MC_KEYBOARD_KEYNAME_7 = "minecraft:keyboard.keyName.7";

    /**
     * {@literal 8}
     */
    String MC_KEYBOARD_KEYNAME_8 = "minecraft:keyboard.keyName.8";

    /**
     * {@literal 9}
     */
    String MC_KEYBOARD_KEYNAME_9 = "minecraft:keyboard.keyName.9";

    /**
     * {@literal A}
     */
    String MC_KEYBOARD_KEYNAME_A = "minecraft:keyboard.keyName.a";

    /**
     * {@literal ADD}
     */
    String MC_KEYBOARD_KEYNAME_ADD = "minecraft:keyboard.keyName.add";

    /**
     * {@literal N+}
     */
    String MC_KEYBOARD_KEYNAME_ADD_SHORT = "minecraft:keyboard.keyName.add.short";

    /**
     * {@literal ALTERNATIVE}
     */
    String MC_KEYBOARD_KEYNAME_ALT = "minecraft:keyboard.keyName.alt";

    /**
     * {@literal ALT}
     */
    String MC_KEYBOARD_KEYNAME_ALT_SHORT = "minecraft:keyboard.keyName.alt.short";

    /**
     * {@literal APOSTROPHE}
     */
    String MC_KEYBOARD_KEYNAME_APOSTROPHE = "minecraft:keyboard.keyName.apostrophe";

    /**
     * {@literal '}
     */
    String MC_KEYBOARD_KEYNAME_APOSTROPHE_SHORT = "minecraft:keyboard.keyName.apostrophe.short";

    /**
     * {@literal B}
     */
    String MC_KEYBOARD_KEYNAME_B = "minecraft:keyboard.keyName.b";

    /**
     * {@literal BACKSLASH}
     */
    String MC_KEYBOARD_KEYNAME_BACKSLASH = "minecraft:keyboard.keyName.backslash";

    /**
     * {@literal \}
     */
    String MC_KEYBOARD_KEYNAME_BACKSLASH_SHORT = "minecraft:keyboard.keyName.backslash.short";

    /**
     * {@literal BACK}
     */
    String MC_KEYBOARD_KEYNAME_BACKSPACE = "minecraft:keyboard.keyName.backspace";

    /**
     * {@literal BACK}
     */
    String MC_KEYBOARD_KEYNAME_BACKSPACE_SHORT = "minecraft:keyboard.keyName.backspace.short";

    /**
     * {@literal C}
     */
    String MC_KEYBOARD_KEYNAME_C = "minecraft:keyboard.keyName.c";

    /**
     * {@literal CAPITAL}
     */
    String MC_KEYBOARD_KEYNAME_CAPSLOCK = "minecraft:keyboard.keyName.capsLock";

    /**
     * {@literal CAPS}
     */
    String MC_KEYBOARD_KEYNAME_CAPSLOCK_SHORT = "minecraft:keyboard.keyName.capsLock.short";

    /**
     * {@literal COMMA}
     */
    String MC_KEYBOARD_KEYNAME_COMMA = "minecraft:keyboard.keyName.comma";

    /**
     * {@literal ,}
     */
    String MC_KEYBOARD_KEYNAME_COMMA_SHORT = "minecraft:keyboard.keyName.comma.short";

    /**
     * {@literal CONTROL}
     */
    String MC_KEYBOARD_KEYNAME_CONTROL = "minecraft:keyboard.keyName.control";

    /**
     * {@literal CTRL}
     */
    String MC_KEYBOARD_KEYNAME_CONTROL_SHORT = "minecraft:keyboard.keyName.control.short";

    /**
     * {@literal D}
     */
    String MC_KEYBOARD_KEYNAME_D = "minecraft:keyboard.keyName.d";

    /**
     * {@literal DECIMAL}
     */
    String MC_KEYBOARD_KEYNAME_DECIMAL = "minecraft:keyboard.keyName.decimal";

    /**
     * {@literal N.}
     */
    String MC_KEYBOARD_KEYNAME_DECIMAL_SHORT = "minecraft:keyboard.keyName.decimal.short";

    /**
     * {@literal DELETE}
     */
    String MC_KEYBOARD_KEYNAME_DELETE = "minecraft:keyboard.keyName.delete";

    /**
     * {@literal DEL}
     */
    String MC_KEYBOARD_KEYNAME_DELETE_SHORT = "minecraft:keyboard.keyName.delete.short";

    /**
     * {@literal DIVIDE}
     */
    String MC_KEYBOARD_KEYNAME_DIVIDE = "minecraft:keyboard.keyName.divide";

    /**
     * {@literal N/}
     */
    String MC_KEYBOARD_KEYNAME_DIVIDE_SHORT = "minecraft:keyboard.keyName.divide.short";

    /**
     * {@literal DOWN}
     */
    String MC_KEYBOARD_KEYNAME_DOWN = "minecraft:keyboard.keyName.down";

    /**
     * {@literal DOWN}
     */
    String MC_KEYBOARD_KEYNAME_DOWN_SHORT = "minecraft:keyboard.keyName.down.short";

    /**
     * {@literal E}
     */
    String MC_KEYBOARD_KEYNAME_E = "minecraft:keyboard.keyName.e";

    /**
     * {@literal END}
     */
    String MC_KEYBOARD_KEYNAME_END = "minecraft:keyboard.keyName.end";

    /**
     * {@literal END}
     */
    String MC_KEYBOARD_KEYNAME_END_SHORT = "minecraft:keyboard.keyName.end.short";

    /**
     * {@literal EQUALS}
     */
    String MC_KEYBOARD_KEYNAME_EQUALS = "minecraft:keyboard.keyName.equals";

    /**
     * {@literal =}
     */
    String MC_KEYBOARD_KEYNAME_EQUALS_SHORT = "minecraft:keyboard.keyName.equals.short";

    /**
     * {@literal ESCAPE}
     */
    String MC_KEYBOARD_KEYNAME_ESCAPE = "minecraft:keyboard.keyName.escape";

    /**
     * {@literal ESC}
     */
    String MC_KEYBOARD_KEYNAME_ESCAPE_SHORT = "minecraft:keyboard.keyName.escape.short";

    /**
     * {@literal F}
     */
    String MC_KEYBOARD_KEYNAME_F = "minecraft:keyboard.keyName.f";

    /**
     * {@literal F1}
     */
    String MC_KEYBOARD_KEYNAME_F1 = "minecraft:keyboard.keyName.f1";

    /**
     * {@literal F10}
     */
    String MC_KEYBOARD_KEYNAME_F10 = "minecraft:keyboard.keyName.f10";

    /**
     * {@literal F11}
     */
    String MC_KEYBOARD_KEYNAME_F11 = "minecraft:keyboard.keyName.f11";

    /**
     * {@literal F12}
     */
    String MC_KEYBOARD_KEYNAME_F12 = "minecraft:keyboard.keyName.f12";

    /**
     * {@literal F13}
     */
    String MC_KEYBOARD_KEYNAME_F13 = "minecraft:keyboard.keyName.f13";

    /**
     * {@literal F2}
     */
    String MC_KEYBOARD_KEYNAME_F2 = "minecraft:keyboard.keyName.f2";

    /**
     * {@literal F3}
     */
    String MC_KEYBOARD_KEYNAME_F3 = "minecraft:keyboard.keyName.f3";

    /**
     * {@literal F4}
     */
    String MC_KEYBOARD_KEYNAME_F4 = "minecraft:keyboard.keyName.f4";

    /**
     * {@literal F5}
     */
    String MC_KEYBOARD_KEYNAME_F5 = "minecraft:keyboard.keyName.f5";

    /**
     * {@literal F6}
     */
    String MC_KEYBOARD_KEYNAME_F6 = "minecraft:keyboard.keyName.f6";

    /**
     * {@literal F7}
     */
    String MC_KEYBOARD_KEYNAME_F7 = "minecraft:keyboard.keyName.f7";

    /**
     * {@literal F8}
     */
    String MC_KEYBOARD_KEYNAME_F8 = "minecraft:keyboard.keyName.f8";

    /**
     * {@literal F9}
     */
    String MC_KEYBOARD_KEYNAME_F9 = "minecraft:keyboard.keyName.f9";

    /**
     * {@literal G}
     */
    String MC_KEYBOARD_KEYNAME_G = "minecraft:keyboard.keyName.g";

    /**
     * {@literal GRAVE}
     */
    String MC_KEYBOARD_KEYNAME_GRAVE = "minecraft:keyboard.keyName.grave";

    /**
     * {@literal `}
     */
    String MC_KEYBOARD_KEYNAME_GRAVE_SHORT = "minecraft:keyboard.keyName.grave.short";

    /**
     * {@literal H}
     */
    String MC_KEYBOARD_KEYNAME_H = "minecraft:keyboard.keyName.h";

    /**
     * {@literal HOME}
     */
    String MC_KEYBOARD_KEYNAME_HOME = "minecraft:keyboard.keyName.home";

    /**
     * {@literal HOME}
     */
    String MC_KEYBOARD_KEYNAME_HOME_SHORT = "minecraft:keyboard.keyName.home.short";

    /**
     * {@literal I}
     */
    String MC_KEYBOARD_KEYNAME_I = "minecraft:keyboard.keyName.i";

    /**
     * {@literal INSERT}
     */
    String MC_KEYBOARD_KEYNAME_INSERT = "minecraft:keyboard.keyName.insert";

    /**
     * {@literal INS}
     */
    String MC_KEYBOARD_KEYNAME_INSERT_SHORT = "minecraft:keyboard.keyName.insert.short";

    /**
     * {@literal J}
     */
    String MC_KEYBOARD_KEYNAME_J = "minecraft:keyboard.keyName.j";

    /**
     * {@literal K}
     */
    String MC_KEYBOARD_KEYNAME_K = "minecraft:keyboard.keyName.k";

    /**
     * {@literal L}
     */
    String MC_KEYBOARD_KEYNAME_L = "minecraft:keyboard.keyName.l";

    /**
     * {@literal LBRACKET}
     */
    String MC_KEYBOARD_KEYNAME_LBRACKET = "minecraft:keyboard.keyName.lbracket";

    /**
     * {@literal [}
     */
    String MC_KEYBOARD_KEYNAME_LBRACKET_SHORT = "minecraft:keyboard.keyName.lbracket.short";

    /**
     * {@literal LEFT}
     */
    String MC_KEYBOARD_KEYNAME_LEFT = "minecraft:keyboard.keyName.left";

    /**
     * {@literal LEFT}
     */
    String MC_KEYBOARD_KEYNAME_LEFT_SHORT = "minecraft:keyboard.keyName.left.short";

    /**
     * {@literal SHIFT}
     */
    String MC_KEYBOARD_KEYNAME_LSHIFT = "minecraft:keyboard.keyName.lshift";

    /**
     * {@literal SHIFT}
     */
    String MC_KEYBOARD_KEYNAME_LSHIFT_SHORT = "minecraft:keyboard.keyName.lshift.short";

    /**
     * {@literal M}
     */
    String MC_KEYBOARD_KEYNAME_M = "minecraft:keyboard.keyName.m";

    /**
     * {@literal MINUS}
     */
    String MC_KEYBOARD_KEYNAME_MINUS = "minecraft:keyboard.keyName.minus";

    /**
     * {@literal -}
     */
    String MC_KEYBOARD_KEYNAME_MINUS_SHORT = "minecraft:keyboard.keyName.minus.short";

    /**
     * {@literal MULTIPLY}
     */
    String MC_KEYBOARD_KEYNAME_MULTIPLY = "minecraft:keyboard.keyName.multiply";

    /**
     * {@literal N*}
     */
    String MC_KEYBOARD_KEYNAME_MULTIPLY_SHORT = "minecraft:keyboard.keyName.multiply.short";

    /**
     * {@literal N}
     */
    String MC_KEYBOARD_KEYNAME_N = "minecraft:keyboard.keyName.n";

    /**
     * {@literal NUMLOCK}
     */
    String MC_KEYBOARD_KEYNAME_NUMLOCK = "minecraft:keyboard.keyName.numLock";

    /**
     * {@literal NUMLOCK}
     */
    String MC_KEYBOARD_KEYNAME_NUMLOCK_SHORT = "minecraft:keyboard.keyName.numLock.short";

    /**
     * {@literal NUMPAD0}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD0 = "minecraft:keyboard.keyName.numpad0";

    /**
     * {@literal N0}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD0_SHORT = "minecraft:keyboard.keyName.numpad0.short";

    /**
     * {@literal NUMPAD1}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD1 = "minecraft:keyboard.keyName.numpad1";

    /**
     * {@literal N1}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD1_SHORT = "minecraft:keyboard.keyName.numpad1.short";

    /**
     * {@literal NUMPAD2}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD2 = "minecraft:keyboard.keyName.numpad2";

    /**
     * {@literal N2}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD2_SHORT = "minecraft:keyboard.keyName.numpad2.short";

    /**
     * {@literal NUMPAD3}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD3 = "minecraft:keyboard.keyName.numpad3";

    /**
     * {@literal N3}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD3_SHORT = "minecraft:keyboard.keyName.numpad3.short";

    /**
     * {@literal NUMPAD4}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD4 = "minecraft:keyboard.keyName.numpad4";

    /**
     * {@literal N4}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD4_SHORT = "minecraft:keyboard.keyName.numpad4.short";

    /**
     * {@literal NUMPAD5}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD5 = "minecraft:keyboard.keyName.numpad5";

    /**
     * {@literal N5}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD5_SHORT = "minecraft:keyboard.keyName.numpad5.short";

    /**
     * {@literal NUMPAD6}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD6 = "minecraft:keyboard.keyName.numpad6";

    /**
     * {@literal N6}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD6_SHORT = "minecraft:keyboard.keyName.numpad6.short";

    /**
     * {@literal NUMPAD7}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD7 = "minecraft:keyboard.keyName.numpad7";

    /**
     * {@literal N7}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD7_SHORT = "minecraft:keyboard.keyName.numpad7.short";

    /**
     * {@literal NUMPAD8}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD8 = "minecraft:keyboard.keyName.numpad8";

    /**
     * {@literal N8}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD8_SHORT = "minecraft:keyboard.keyName.numpad8.short";

    /**
     * {@literal NUMPAD9}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD9 = "minecraft:keyboard.keyName.numpad9";

    /**
     * {@literal N9}
     */
    String MC_KEYBOARD_KEYNAME_NUMPAD9_SHORT = "minecraft:keyboard.keyName.numpad9.short";

    /**
     * {@literal O}
     */
    String MC_KEYBOARD_KEYNAME_O = "minecraft:keyboard.keyName.o";

    /**
     * {@literal P}
     */
    String MC_KEYBOARD_KEYNAME_P = "minecraft:keyboard.keyName.p";

    /**
     * {@literal PAUSE}
     */
    String MC_KEYBOARD_KEYNAME_PAUSE = "minecraft:keyboard.keyName.pause";

    /**
     * {@literal PAUSE}
     */
    String MC_KEYBOARD_KEYNAME_PAUSE_SHORT = "minecraft:keyboard.keyName.pause.short";

    /**
     * {@literal PERIOD}
     */
    String MC_KEYBOARD_KEYNAME_PERIOD = "minecraft:keyboard.keyName.period";

    /**
     * {@literal .}
     */
    String MC_KEYBOARD_KEYNAME_PERIOD_SHORT = "minecraft:keyboard.keyName.period.short";

    /**
     * {@literal PAGE DOWN}
     */
    String MC_KEYBOARD_KEYNAME_PGDOWN = "minecraft:keyboard.keyName.pgDown";

    /**
     * {@literal PgDn}
     */
    String MC_KEYBOARD_KEYNAME_PGDOWN_SHORT = "minecraft:keyboard.keyName.pgDown.short";

    /**
     * {@literal PAGE UP}
     */
    String MC_KEYBOARD_KEYNAME_PGUP = "minecraft:keyboard.keyName.pgUp";

    /**
     * {@literal PgUp}
     */
    String MC_KEYBOARD_KEYNAME_PGUP_SHORT = "minecraft:keyboard.keyName.pgUp.short";

    /**
     * {@literal Q}
     */
    String MC_KEYBOARD_KEYNAME_Q = "minecraft:keyboard.keyName.q";

    /**
     * {@literal R}
     */
    String MC_KEYBOARD_KEYNAME_R = "minecraft:keyboard.keyName.r";

    /**
     * {@literal RBRACKET}
     */
    String MC_KEYBOARD_KEYNAME_RBRACKET = "minecraft:keyboard.keyName.rbracket";

    /**
     * {@literal ]}
     */
    String MC_KEYBOARD_KEYNAME_RBRACKET_SHORT = "minecraft:keyboard.keyName.rbracket.short";

    /**
     * {@literal RETURN}
     */
    String MC_KEYBOARD_KEYNAME_RETURN = "minecraft:keyboard.keyName.return";

    /**
     * {@literal RETURN}
     */
    String MC_KEYBOARD_KEYNAME_RETURN_SHORT = "minecraft:keyboard.keyName.return.short";

    /**
     * {@literal RIGHT}
     */
    String MC_KEYBOARD_KEYNAME_RIGHT = "minecraft:keyboard.keyName.right";

    /**
     * {@literal RIGHT}
     */
    String MC_KEYBOARD_KEYNAME_RIGHT_SHORT = "minecraft:keyboard.keyName.right.short";

    /**
     * {@literal S}
     */
    String MC_KEYBOARD_KEYNAME_S = "minecraft:keyboard.keyName.s";

    /**
     * {@literal SCROLL}
     */
    String MC_KEYBOARD_KEYNAME_SCROLL = "minecraft:keyboard.keyName.scroll";

    /**
     * {@literal SCROLL}
     */
    String MC_KEYBOARD_KEYNAME_SCROLL_SHORT = "minecraft:keyboard.keyName.scroll.short";

    /**
     * {@literal SEMICOLON}
     */
    String MC_KEYBOARD_KEYNAME_SEMICOLON = "minecraft:keyboard.keyName.semicolon";

    /**
     * {@literal ;}
     */
    String MC_KEYBOARD_KEYNAME_SEMICOLON_SHORT = "minecraft:keyboard.keyName.semicolon.short";

    /**
     * {@literal SLASH}
     */
    String MC_KEYBOARD_KEYNAME_SLASH = "minecraft:keyboard.keyName.slash";

    /**
     * {@literal /}
     */
    String MC_KEYBOARD_KEYNAME_SLASH_SHORT = "minecraft:keyboard.keyName.slash.short";

    /**
     * {@literal SPACE}
     */
    String MC_KEYBOARD_KEYNAME_SPACE = "minecraft:keyboard.keyName.space";

    /**
     * {@literal SPACE}
     */
    String MC_KEYBOARD_KEYNAME_SPACE_SHORT = "minecraft:keyboard.keyName.space.short";

    /**
     * {@literal SUBTRACT}
     */
    String MC_KEYBOARD_KEYNAME_SUBTRACT = "minecraft:keyboard.keyName.subtract";

    /**
     * {@literal N-}
     */
    String MC_KEYBOARD_KEYNAME_SUBTRACT_SHORT = "minecraft:keyboard.keyName.subtract.short";

    /**
     * {@literal T}
     */
    String MC_KEYBOARD_KEYNAME_T = "minecraft:keyboard.keyName.t";

    /**
     * {@literal TAB}
     */
    String MC_KEYBOARD_KEYNAME_TAB = "minecraft:keyboard.keyName.tab";

    /**
     * {@literal TAB}
     */
    String MC_KEYBOARD_KEYNAME_TAB_SHORT = "minecraft:keyboard.keyName.tab.short";

    /**
     * {@literal U}
     */
    String MC_KEYBOARD_KEYNAME_U = "minecraft:keyboard.keyName.u";

    /**
     * {@literal UP}
     */
    String MC_KEYBOARD_KEYNAME_UP = "minecraft:keyboard.keyName.up";

    /**
     * {@literal UP}
     */
    String MC_KEYBOARD_KEYNAME_UP_SHORT = "minecraft:keyboard.keyName.up.short";

    /**
     * {@literal V}
     */
    String MC_KEYBOARD_KEYNAME_V = "minecraft:keyboard.keyName.v";

    /**
     * {@literal W}
     */
    String MC_KEYBOARD_KEYNAME_W = "minecraft:keyboard.keyName.w";

    /**
     * {@literal X}
     */
    String MC_KEYBOARD_KEYNAME_X = "minecraft:keyboard.keyName.x";

    /**
     * {@literal Y}
     */
    String MC_KEYBOARD_KEYNAME_Y = "minecraft:keyboard.keyName.y";

    /**
     * {@literal Z}
     */
    String MC_KEYBOARD_KEYNAME_Z = "minecraft:keyboard.keyName.z";

    /**
     * {@literal Line %s}
     */
    String MC_KEYBOARD_ONSCREEN_LINENUMBER = "minecraft:keyboard.onScreen.lineNumber";

    /**
     * {@literal Go Back}
     */
    String MC_LICENSED_CONTENT_GOBACK = "minecraft:licensed_content.goBack";

    /**
     * {@literal To view licensed content, please visit https://minecraft.net/licensed-content/ in any web browser.}
     */
    String MC_LICENSED_CONTENT_VIEWLICENSEDCONTENT = "minecraft:licensed_content.viewLicensedContent";

    /**
     * {@literal Basic Map}
     */
    String MC_MAP_BASICMAP = "minecraft:map.basicMap";

    /**
     * {@literal Copy Map}
     */
    String MC_MAP_CLONE = "minecraft:map.clone";

    /**
     * {@literal Zoom Out & Clear}
     */
    String MC_MAP_EXTENDANDCLEAR = "minecraft:map.extendAndClear";

    /**
     * {@literal Map that Shows Players}
     */
    String MC_MAP_LOCATORMAP = "minecraft:map.locatorMap";

    /**
     * {@literal Lock Map}
     */
    String MC_MAP_LOCK = "minecraft:map.lock";

    /**
     * {@literal Map Name}
     */
    String MC_MAP_NAME = "minecraft:map.name";

    /**
     * {@literal Position: %s, %s, %s}
     */
    String MC_MAP_POSITION = "minecraft:map.position";

    /**
     * {@literal Agent Pos: %s, %s, %s}
     */
    String MC_MAP_POSITION_AGENT = "minecraft:map.position.agent";

    /**
     * {@literal Rename Map}
     */
    String MC_MAP_RENAME = "minecraft:map.rename";

    /**
     * {@literal Display Markers}
     */
    String MC_MAP_TOOLTIP_DISPLAYMARKERS = "minecraft:map.toolTip.displayMarkers";

    /**
     * {@literal Level %s/%s}
     */
    String MC_MAP_TOOLTIP_LEVEL = "minecraft:map.toolTip.level";

    /**
     * {@literal Locked}
     */
    String MC_MAP_TOOLTIP_LOCKED = "minecraft:map.toolTip.locked";

    /**
     * {@literal Scaling at 1:%s}
     */
    String MC_MAP_TOOLTIP_SCALING = "minecraft:map.toolTip.scaling";

    /**
     * {@literal Unknown Map}
     */
    String MC_MAP_TOOLTIP_UNKOWN = "minecraft:map.toolTip.unkown";

    /**
     * {@literal Press %1#s to dismount}
     */
    String MC_MOUNT_ONBOARD = "minecraft:mount.onboard";

    /**
     * {@literal Connect}
     */
    String MC_MULTIPLAYER_CONNECT = "minecraft:multiplayer.connect";

    /**
     * {@literal Downloading statistics & achievements...}
     */
    String MC_MULTIPLAYER_DOWNLOADINGSTATS = "minecraft:multiplayer.downloadingStats";

    /**
     * {@literal Downloading terrain}
     */
    String MC_MULTIPLAYER_DOWNLOADINGTERRAIN = "minecraft:multiplayer.downloadingTerrain";

    /**
     * {@literal Open Chat}
     */
    String MC_MULTIPLAYER_INBEDOPENCHAT = "minecraft:multiplayer.inBedOpenChat";

    /**
     * {@literal Minecraft Multiplayer is currently not finished, but there}
     */
    String MC_MULTIPLAYER_INFO1 = "minecraft:multiplayer.info1";

    /**
     * {@literal is some buggy early testing going on.}
     */
    String MC_MULTIPLAYER_INFO2 = "minecraft:multiplayer.info2";

    /**
     * {@literal Enter the IP of a server to connect to it:}
     */
    String MC_MULTIPLAYER_IPINFO = "minecraft:multiplayer.ipinfo";

    /**
     * {@literal The game's join code has been updated.}
     */
    String MC_MULTIPLAYER_JOINCODE_REFRESHED = "minecraft:multiplayer.joincode.refreshed";

    /**
     * {@literal At least one of your resource or behavior packs failed to load.}
     */
    String MC_MULTIPLAYER_PACKERRORS = "minecraft:multiplayer.packErrors";

    /**
     * {@literal At least one of your resource or behavior packs failed to load. Try downloading this world from your Realm settings to see more details on the error.}
     */
    String MC_MULTIPLAYER_PACKERRORS_REALMS = "minecraft:multiplayer.packErrors.realms";

    /**
     * {@literal %s edited the character appearance.}
     */
    String MC_MULTIPLAYER_PLAYER_CHANGETOPERSONA = "minecraft:multiplayer.player.changeToPersona";

    /**
     * {@literal %s has changed their skin.}
     */
    String MC_MULTIPLAYER_PLAYER_CHANGETOSKIN = "minecraft:multiplayer.player.changeToSkin";

    /**
     * {@literal Inventory recovered. Find a safe place, and we'll place a chest near you the next time you join the world.}
     */
    String MC_MULTIPLAYER_PLAYER_INVENTORY_FAILED = "minecraft:multiplayer.player.inventory.failed";

    /**
     * {@literal Inventory recovered and placed in chests near you.}
     */
    String MC_MULTIPLAYER_PLAYER_INVENTORY_RECOVERED = "minecraft:multiplayer.player.inventory.recovered";

    /**
     * {@literal %s joined the game}
     */
    String MC_MULTIPLAYER_PLAYER_JOINED = "minecraft:multiplayer.player.joined";

    /**
     * {@literal %s joined the Realm}
     */
    String MC_MULTIPLAYER_PLAYER_JOINED_REALMS = "minecraft:multiplayer.player.joined.realms";

    /**
     * {@literal %s (formerly known as %s) joined the Realm}
     */
    String MC_MULTIPLAYER_PLAYER_JOINED_REALMS_RENAMED = "minecraft:multiplayer.player.joined.realms.renamed";

    /**
     * {@literal %s (formerly known as %s) joined the game}
     */
    String MC_MULTIPLAYER_PLAYER_JOINED_RENAMED = "minecraft:multiplayer.player.joined.renamed";

    /**
     * {@literal %s left the game}
     */
    String MC_MULTIPLAYER_PLAYER_LEFT = "minecraft:multiplayer.player.left";

    /**
     * {@literal %s left the Realm}
     */
    String MC_MULTIPLAYER_PLAYER_LEFT_REALMS = "minecraft:multiplayer.player.left.realms";

    /**
     * {@literal Sleeping through this night}
     */
    String MC_MULTIPLAYER_PLAYERSSKIPPINGNIGHT = "minecraft:multiplayer.playersSkippingNight";

    /**
     * {@literal %s/%s players sleeping}
     */
    String MC_MULTIPLAYER_PLAYERSSLEEPING = "minecraft:multiplayer.playersSleeping";

    /**
     * {@literal No amount of rest can pass this night}
     */
    String MC_MULTIPLAYER_PLAYERSSLEEPINGNOTPOSSIBLE = "minecraft:multiplayer.playersSleepingNotPossible";

    /**
     * {@literal Leave Bed}
     */
    String MC_MULTIPLAYER_STOPSLEEPING = "minecraft:multiplayer.stopSleeping";

    /**
     * {@literal This server recommends the use of a custom resource pack.}
     */
    String MC_MULTIPLAYER_TEXTUREPROMPT_LINE1 = "minecraft:multiplayer.texturePrompt.line1";

    /**
     * {@literal Would you like to download and install it automagically?}
     */
    String MC_MULTIPLAYER_TEXTUREPROMPT_LINE2 = "minecraft:multiplayer.texturePrompt.line2";

    /**
     * {@literal Play Multiplayer}
     */
    String MC_MULTIPLAYER_TITLE = "minecraft:multiplayer.title";

    /**
     * {@literal This item is locked and can't be crafted with}
     */
    String MC_NOTIFICATION_LOCK_NOCRAFT = "minecraft:notification.lock.noCraft";

    /**
     * {@literal This item is locked and can't be dropped}
     */
    String MC_NOTIFICATION_LOCK_NODROP = "minecraft:notification.lock.noDrop";

    /**
     * {@literal This item is locked and can't be removed from the inventory}
     */
    String MC_NOTIFICATION_LOCK_NOREMOVE = "minecraft:notification.lock.noRemove";

    /**
     * {@literal This item is locked and can't be replaced}
     */
    String MC_NOTIFICATION_LOCK_NOREPLACE = "minecraft:notification.lock.noReplace";

    /**
     * {@literal Unable to open the NPC Link. This doesn't look like a supported URL.}
     */
    String MC_NPCURI_LAUNCH_FAILURE = "minecraft:npcUri.launch.failure";

    /**
     * {@literal Opened the NPC Link.}
     */
    String MC_NPCURI_LAUNCH_SUCCESS = "minecraft:npcUri.launch.success";

    /**
     * {@literal Back}
     */
    String MC_ONLINEPLAY_BACK = "minecraft:onlinePlay.Back";

    /**
     * {@literal Do not show this screen again}
     */
    String MC_ONLINEPLAY_DONOTSHOWAGAIN = "minecraft:onlinePlay.doNotShowAgain";

    /**
     * {@literal During online editing you may be exposed to chat messages or other types of user generated content that has not been rated, and may not be suitable for all ages.}
     */
    String MC_ONLINEPLAY_EDITOR_MESSAGE = "minecraft:onlinePlay.editor.message";

    /**
     * {@literal Online editing is not rated}
     */
    String MC_ONLINEPLAY_EDITOR_NOTRATED = "minecraft:onlinePlay.editor.notRated";

    /**
     * {@literal During online play you might see chat messages or other user generated content that might not be suitable for all ages.}
     */
    String MC_ONLINEPLAY_MESSAGE = "minecraft:onlinePlay.message";

    /**
     * {@literal Online play is not rated}
     */
    String MC_ONLINEPLAY_NOTRATED = "minecraft:onlinePlay.notRated";

    /**
     * {@literal Proceed}
     */
    String MC_ONLINEPLAY_PROCEED = "minecraft:onlinePlay.proceed";

    /**
     * {@literal You should only connect to servers you trust! Do you still wish to join?}
     */
    String MC_ONLINEPLAY_UNTRUSTEDIP_MESSAGE = "minecraft:onlinePlay.untrustedIp.message";

    /**
     * {@literal Unknown External Server}
     */
    String MC_ONLINEPLAY_UNTRUSTEDIP_TITLE = "minecraft:onlinePlay.untrustedIp.title";

    /**
     * {@literal Author: %s}
     */
    String MC_PACK_AUTHORS_LABEL = "minecraft:pack.authors.label";

    /**
     * {@literal Unknown}
     */
    String MC_PACK_AUTHORS_NONE = "minecraft:pack.authors.none";

    /**
     * {@literal Failed to accept invite}
     */
    String MC_PARTIES_TOAST_ACCEPTINVITEFAILED = "minecraft:parties.toast.acceptInviteFailed";

    /**
     * {@literal Failed to create party}
     */
    String MC_PARTIES_TOAST_CREATEFAILED = "minecraft:parties.toast.createFailed";

    /**
     * {@literal Failed to join party}
     */
    String MC_PARTIES_TOAST_JOINFAILED = "minecraft:parties.toast.joinFailed";

    /**
     * {@literal Joinable party expired}
     */
    String MC_PARTIES_TOAST_JOINABLEPARTYEXPIRED = "minecraft:parties.toast.joinablePartyExpired";

    /**
     * {@literal You joined a party}
     */
    String MC_PARTIES_TOAST_JOINEDPARTY = "minecraft:parties.toast.joinedParty";

    /**
     * {@literal %s is now the party leader}
     */
    String MC_PARTIES_TOAST_LEADERCHANGED = "minecraft:parties.toast.leaderChanged";

    /**
     * {@literal Failed to leave party}
     */
    String MC_PARTIES_TOAST_LEAVEFAILED = "minecraft:parties.toast.leaveFailed";

    /**
     * {@literal You left the party}
     */
    String MC_PARTIES_TOAST_LEFTPARTY = "minecraft:parties.toast.leftParty";

    /**
     * {@literal %s joined the party}
     */
    String MC_PARTIES_TOAST_MEMBERJOINED = "minecraft:parties.toast.memberJoined";

    /**
     * {@literal %s left the party}
     */
    String MC_PARTIES_TOAST_MEMBERLEFT = "minecraft:parties.toast.memberLeft";

    /**
     * {@literal Party invite}
     */
    String MC_PARTIES_TOAST_PARTYINVITE = "minecraft:parties.toast.partyInvite";

    /**
     * {@literal Party invite expired}
     */
    String MC_PARTIES_TOAST_PARTYINVITEEXPIRED = "minecraft:parties.toast.partyInviteExpired";

    /**
     * {@literal Failed to remove party member}
     */
    String MC_PARTIES_TOAST_REMOVEMEMBERFAILED = "minecraft:parties.toast.removeMemberFailed";

    /**
     * {@literal Failed to send invite}
     */
    String MC_PARTIES_TOAST_SENDINVITEFAILED = "minecraft:parties.toast.sendInviteFailed";

    /**
     * {@literal Failed to set party leader}
     */
    String MC_PARTIES_TOAST_SETLEADERFAILED = "minecraft:parties.toast.setLeaderFailed";

    /**
     * {@literal Party loading - Main Menu}
     */
    String MC_PARTIES_TOAST_TRAVELTOMENU = "minecraft:parties.toast.travelToMenu";

    /**
     * {@literal Party loading - %s}
     */
    String MC_PARTIES_TOAST_TRAVELTOWORLD = "minecraft:parties.toast.travelToWorld";

    /**
     * {@literal Continue}
     */
    String MC_PATCHNOTES_CONTINUE = "minecraft:patchNotes.continue";

    /**
     * {@literal Oops! Something went wrong. Maybe check your internet connection?}
     */
    String MC_PATCHNOTES_ERROR_NOINTERNET_MSG = "minecraft:patchNotes.error.noInternet.msg";

    /**
     * {@literal Disconnected from the Internet}
     */
    String MC_PATCHNOTES_ERROR_NOINTERNET_TITLE = "minecraft:patchNotes.error.noInternet.title";

    /**
     * {@literal We fixed a few bugs in this release. We'll send you new patch notes when we have more to report.}
     */
    String MC_PATCHNOTES_ERROR_NOTFOUND_MSG = "minecraft:patchNotes.error.notFound.msg";

    /**
     * {@literal Patch Notes %1}
     */
    String MC_PATCHNOTES_ERROR_NOTFOUND_TITLE = "minecraft:patchNotes.error.notFound.title";

    /**
     * {@literal Loading Patch Notes}
     */
    String MC_PATCHNOTES_LOADING = "minecraft:patchNotes.loading";

    /**
     * {@literal Unlock}
     */
    String MC_PATCHNOTES_UNLOCK = "minecraft:patchNotes.unlock";

    /**
     * {@literal Play}
     */
    String MC_PAUSENOTIFICATION_PLAYBUTTON = "minecraft:pauseNotification.playButton";

    /**
     * {@literal Watch out, game is no longer paused!}
     */
    String MC_PAUSENOTIFICATION_SUBTITLE_GAMEUNPAUSED = "minecraft:pauseNotification.subtitle.gameUnpaused";

    /**
     * {@literal Multiple people joined your game!}
     */
    String MC_PAUSENOTIFICATION_TITLE_MORETHANTWOPLAYERS = "minecraft:pauseNotification.title.moreThanTwoPlayers";

    /**
     * {@literal %s joined your game!}
     */
    String MC_PAUSENOTIFICATION_TITLE_ONEPLAYER = "minecraft:pauseNotification.title.onePlayer";

    /**
     * {@literal %s and %s joined your game!}
     */
    String MC_PAUSENOTIFICATION_TITLE_TWOPLAYERS = "minecraft:pauseNotification.title.twoPlayers";

    /**
     * {@literal Back to Game}
     */
    String MC_PAUSESCREEN_BACK = "minecraft:pauseScreen.back";

    /**
     * {@literal Beta Feedback}
     */
    String MC_PAUSESCREEN_BETAFEEDBACK = "minecraft:pauseScreen.betaFeedback";

    /**
     * {@literal Current World}
     */
    String MC_PAUSESCREEN_CURRENTWORLD = "minecraft:pauseScreen.currentWorld";

    /**
     * {@literal Return to Editor}
     */
    String MC_PAUSESCREEN_EDITOR_QUIT = "minecraft:pauseScreen.editor.quit";

    /**
     * {@literal Resume Editing}
     */
    String MC_PAUSESCREEN_EDITOR_RETURNTOEDITING = "minecraft:pauseScreen.editor.returnToEditing";

    /**
     * {@literal Resume Testing}
     */
    String MC_PAUSESCREEN_EDITOR_RETURNTOTESTING = "minecraft:pauseScreen.editor.returnToTesting";

    /**
     * {@literal No IP found}
     */
    String MC_PAUSESCREEN_ERROR_NOIPADDRESS = "minecraft:pauseScreen.error.noIpAddress";

    /**
     * {@literal No Port found}
     */
    String MC_PAUSESCREEN_ERROR_NOPORT = "minecraft:pauseScreen.error.noPort";

    /**
     * {@literal Feed}
     */
    String MC_PAUSESCREEN_FEED = "minecraft:pauseScreen.feed";

    /**
     * {@literal Game is paused}
     */
    String MC_PAUSESCREEN_GAMEISPAUSED = "minecraft:pauseScreen.gameIsPaused";

    /**
     * {@literal Game Menu}
     */
    String MC_PAUSESCREEN_HEADER = "minecraft:pauseScreen.header";

    /**
     * {@literal Invite to Game}
     */
    String MC_PAUSESCREEN_INVITE = "minecraft:pauseScreen.invite";

    /**
     * {@literal IP: %1}
     */
    String MC_PAUSESCREEN_IPADDRESS = "minecraft:pauseScreen.ipAddress";

    /**
     * {@literal %3 join code icon, %1 of %2}
     */
    String MC_PAUSESCREEN_JOINCODE_ICON = "minecraft:pauseScreen.joinCode.Icon";

    /**
     * {@literal JOIN CODE}
     */
    String MC_PAUSESCREEN_JOINCODE_LABEL = "minecraft:pauseScreen.joinCode.Label";

    /**
     * {@literal Options}
     */
    String MC_PAUSESCREEN_OPTIONS = "minecraft:pauseScreen.options";

    /**
     * {@literal Players in %s}
     */
    String MC_PAUSESCREEN_PLAYERSTITLE = "minecraft:pauseScreen.playersTitle";

    /**
     * {@literal Save & Quit}
     */
    String MC_PAUSESCREEN_QUIT = "minecraft:pauseScreen.quit";

    /**
     * {@literal Realms Stories}
     */
    String MC_PAUSESCREEN_REALMSSTORIES = "minecraft:pauseScreen.realmsStories";

    /**
     * {@literal Save & Leave}
     */
    String MC_PAUSESCREEN_SECONDARYCLIENTLEAVE = "minecraft:pauseScreen.secondaryClientLeave";

    /**
     * {@literal Game Paused}
     */
    String MC_PAUSESCREEN_TITLE = "minecraft:pauseScreen.title";

    /**
     * {@literal Oops! Your account was disconnected. To access online features, please sign in at the Main Menu and restart your world.}
     */
    String MC_PAUSESCREEN_XBOXLIVEDISCONNECT = "minecraft:pauseScreen.xboxLiveDisconnect";

    /**
     * {@literal You save %s minecoins with your Marketplace Pass subscription!}
     */
    String MC_PERSONA_CSB_SAVINGS = "minecraft:persona.csb.savings";

    /**
     * {@literal In Pass}
     */
    String MC_PERSONA_CSB_SEE_SUBSCRIPTION = "minecraft:persona.csb.see.subscription";

    /**
     * {@literal medium}
     */
    String MC_PERSONA_MEDIUM_TITLE = "minecraft:persona.medium.title";

    /**
     * {@literal Play Emote}
     */
    String MC_PERSONA_PREVIEW_EMOTE = "minecraft:persona.preview.emote";

    /**
     * {@literal Redeem}
     */
    String MC_PERSONA_REALMS_REDEEM = "minecraft:persona.realms.redeem";

    /**
     * {@literal You save %s minecoins with your Realms Plus subscription!}
     */
    String MC_PERSONA_REALMS_SAVINGS = "minecraft:persona.realms.savings";

    /**
     * {@literal In Realms Plus}
     */
    String MC_PERSONA_REALMS_SEE_SUBSCRIPTION = "minecraft:persona.realms.see.subscription";

    /**
     * {@literal %s to redeem}
     */
    String MC_PERSONA_REALMS_TIME_REMAINING = "minecraft:persona.realms.time.remaining";

    /**
     * {@literal narrow}
     */
    String MC_PERSONA_SLIM_TITLE = "minecraft:persona.slim.title";

    /**
     * {@literal small}
     */
    String MC_PERSONA_SMALL_TITLE = "minecraft:persona.small.title";

    /**
     * {@literal smaller}
     */
    String MC_PERSONA_SMALLER_TITLE = "minecraft:persona.smaller.title";

    /**
     * {@literal tall}
     */
    String MC_PERSONA_TALL_TITLE = "minecraft:persona.tall.title";

    /**
     * {@literal wide}
     */
    String MC_PERSONA_WIDE_TITLE = "minecraft:persona.wide.title";

    /**
     * {@literal UNKNOWN}
     */
    String MC_PLATFORM_MODEL_UNKNOWN = "minecraft:platform.model.unknown";

    /**
     * {@literal Multiplayer}
     */
    String MC_PLAYERCOUNT_MULTIPLAYER = "minecraft:playercount.multiplayer";

    /**
     * {@literal Single Player}
     */
    String MC_PLAYERCOUNT_SINGLE_PLAYER = "minecraft:playercount.single_player";

    /**
     * {@literal Add Photo}
     */
    String MC_PORTFOLIOSCREEN_ADDPHOTO = "minecraft:portfolioScreen.addPhoto";

    /**
     * {@literal [ caption ]}
     */
    String MC_PORTFOLIOSCREEN_CAPTION = "minecraft:portfolioScreen.caption";

    /**
     * {@literal Export Portfolio}
     */
    String MC_PORTFOLIOSCREEN_EXPORT = "minecraft:portfolioScreen.export";

    /**
     * {@literal You do not have any photos in your inventory. Try using the camera to take a picture.}
     */
    String MC_PORTFOLIOSCREEN_NOINVENTORY = "minecraft:portfolioScreen.noInventory";

    /**
     * {@literal You currently have no photos in your portfolio. Photos taken with the camera will show up here. You can also use the button below to add some.}
     */
    String MC_PORTFOLIOSCREEN_NOPICS0 = "minecraft:portfolioScreen.nopics0";

    /**
     * {@literal Photos you take with the camera will show up here.}
     */
    String MC_PORTFOLIOSCREEN_NOPICS1 = "minecraft:portfolioScreen.nopics1";

    /**
     * {@literal Page %s}
     */
    String MC_PORTFOLIOSCREEN_PAGE = "minecraft:portfolioScreen.page";

    /**
     * {@literal Absorption}
     */
    String MC_POTION_ABSORPTION = "minecraft:potion.absorption";

    /**
     * {@literal Lingering Awkward Potion}
     */
    String MC_POTION_AWKWARD_LINGER_NAME = "minecraft:potion.awkward.linger.name";

    /**
     * {@literal Awkward Potion}
     */
    String MC_POTION_AWKWARD_NAME = "minecraft:potion.awkward.name";

    /**
     * {@literal Splash Awkward Potion}
     */
    String MC_POTION_AWKWARD_SPLASH_NAME = "minecraft:potion.awkward.splash.name";

    /**
     * {@literal Blindness}
     */
    String MC_POTION_BLINDNESS = "minecraft:potion.blindness";

    /**
     * {@literal Conduit Power}
     */
    String MC_POTION_CONDUITPOWER = "minecraft:potion.conduitPower";

    /**
     * {@literal Nausea}
     */
    String MC_POTION_CONFUSION = "minecraft:potion.confusion";

    /**
     * {@literal Strength}
     */
    String MC_POTION_DAMAGEBOOST = "minecraft:potion.damageBoost";

    /**
     * {@literal Lingering Potion of Strength}
     */
    String MC_POTION_DAMAGEBOOST_LINGER_NAME = "minecraft:potion.damageBoost.linger.name";

    /**
     * {@literal Potion of Strength}
     */
    String MC_POTION_DAMAGEBOOST_NAME = "minecraft:potion.damageBoost.name";

    /**
     * {@literal Splash Potion of Strength}
     */
    String MC_POTION_DAMAGEBOOST_SPLASH_NAME = "minecraft:potion.damageBoost.splash.name";

    /**
     * {@literal Mining Fatigue}
     */
    String MC_POTION_DIGSLOWDOWN = "minecraft:potion.digSlowDown";

    /**
     * {@literal Haste}
     */
    String MC_POTION_DIGSPEED = "minecraft:potion.digSpeed";

    /**
     * {@literal When Applied:}
     */
    String MC_POTION_EFFECTS_WHENDRANK = "minecraft:potion.effects.whenDrank";

    /**
     * {@literal No Effects}
     */
    String MC_POTION_EMPTY = "minecraft:potion.empty";

    /**
     * {@literal Lingering Water Bottle}
     */
    String MC_POTION_EMPTYPOTION_LINGER_NAME = "minecraft:potion.emptyPotion.linger.name";

    /**
     * {@literal Water Bottle}
     */
    String MC_POTION_EMPTYPOTION_NAME = "minecraft:potion.emptyPotion.name";

    /**
     * {@literal Splash Water Bottle}
     */
    String MC_POTION_EMPTYPOTION_SPLASH_NAME = "minecraft:potion.emptyPotion.splash.name";

    /**
     * {@literal Fire Resistance}
     */
    String MC_POTION_FIRERESISTANCE = "minecraft:potion.fireResistance";

    /**
     * {@literal Lingering Potion of Fire Resistance}
     */
    String MC_POTION_FIRERESISTANCE_LINGER_NAME = "minecraft:potion.fireResistance.linger.name";

    /**
     * {@literal Potion of Fire Resistance}
     */
    String MC_POTION_FIRERESISTANCE_NAME = "minecraft:potion.fireResistance.name";

    /**
     * {@literal Splash Potion of Fire Resistance}
     */
    String MC_POTION_FIRERESISTANCE_SPLASH_NAME = "minecraft:potion.fireResistance.splash.name";

    /**
     * {@literal Instant Damage}
     */
    String MC_POTION_HARM = "minecraft:potion.harm";

    /**
     * {@literal Lingering Potion of Harming}
     */
    String MC_POTION_HARM_LINGER_NAME = "minecraft:potion.harm.linger.name";

    /**
     * {@literal Potion of Harming}
     */
    String MC_POTION_HARM_NAME = "minecraft:potion.harm.name";

    /**
     * {@literal Splash Potion of Harming}
     */
    String MC_POTION_HARM_SPLASH_NAME = "minecraft:potion.harm.splash.name";

    /**
     * {@literal Instant Health}
     */
    String MC_POTION_HEAL = "minecraft:potion.heal";

    /**
     * {@literal Lingering Potion of Healing}
     */
    String MC_POTION_HEAL_LINGER_NAME = "minecraft:potion.heal.linger.name";

    /**
     * {@literal Potion of Healing}
     */
    String MC_POTION_HEAL_NAME = "minecraft:potion.heal.name";

    /**
     * {@literal Splash Potion of Healing}
     */
    String MC_POTION_HEAL_SPLASH_NAME = "minecraft:potion.heal.splash.name";

    /**
     * {@literal Health Boost}
     */
    String MC_POTION_HEALTHBOOST = "minecraft:potion.healthBoost";

    /**
     * {@literal Hunger}
     */
    String MC_POTION_HUNGER = "minecraft:potion.hunger";

    /**
     * {@literal Infested}
     */
    String MC_POTION_INFESTED = "minecraft:potion.infested";

    /**
     * {@literal Lingering Potion of Infestation}
     */
    String MC_POTION_INFESTED_LINGER_NAME = "minecraft:potion.infested.linger.name";

    /**
     * {@literal Potion of Infestation}
     */
    String MC_POTION_INFESTED_NAME = "minecraft:potion.infested.name";

    /**
     * {@literal Splash Potion of Infestation}
     */
    String MC_POTION_INFESTED_SPLASH_NAME = "minecraft:potion.infested.splash.name";

    /**
     * {@literal Invisibility}
     */
    String MC_POTION_INVISIBILITY = "minecraft:potion.invisibility";

    /**
     * {@literal Lingering Potion of Invisibility}
     */
    String MC_POTION_INVISIBILITY_LINGER_NAME = "minecraft:potion.invisibility.linger.name";

    /**
     * {@literal Potion of Invisibility}
     */
    String MC_POTION_INVISIBILITY_NAME = "minecraft:potion.invisibility.name";

    /**
     * {@literal Splash Potion of Invisibility}
     */
    String MC_POTION_INVISIBILITY_SPLASH_NAME = "minecraft:potion.invisibility.splash.name";

    /**
     * {@literal Jump Boost}
     */
    String MC_POTION_JUMP = "minecraft:potion.jump";

    /**
     * {@literal Lingering Potion of Leaping}
     */
    String MC_POTION_JUMP_LINGER_NAME = "minecraft:potion.jump.linger.name";

    /**
     * {@literal Potion of Leaping}
     */
    String MC_POTION_JUMP_NAME = "minecraft:potion.jump.name";

    /**
     * {@literal Splash Potion of Leaping}
     */
    String MC_POTION_JUMP_SPLASH_NAME = "minecraft:potion.jump.splash.name";

    /**
     * {@literal Levitation}
     */
    String MC_POTION_LEVITATION = "minecraft:potion.levitation";

    /**
     * {@literal Slowness}
     */
    String MC_POTION_MOVESLOWDOWN = "minecraft:potion.moveSlowdown";

    /**
     * {@literal Lingering Potion of Slowness}
     */
    String MC_POTION_MOVESLOWDOWN_LINGER_NAME = "minecraft:potion.moveSlowdown.linger.name";

    /**
     * {@literal Potion of Slowness}
     */
    String MC_POTION_MOVESLOWDOWN_NAME = "minecraft:potion.moveSlowdown.name";

    /**
     * {@literal Splash Potion of Slowness}
     */
    String MC_POTION_MOVESLOWDOWN_SPLASH_NAME = "minecraft:potion.moveSlowdown.splash.name";

    /**
     * {@literal Speed}
     */
    String MC_POTION_MOVESPEED = "minecraft:potion.moveSpeed";

    /**
     * {@literal Lingering Potion of Swiftness}
     */
    String MC_POTION_MOVESPEED_LINGER_NAME = "minecraft:potion.moveSpeed.linger.name";

    /**
     * {@literal Potion of Swiftness}
     */
    String MC_POTION_MOVESPEED_NAME = "minecraft:potion.moveSpeed.name";

    /**
     * {@literal Splash Potion of Swiftness}
     */
    String MC_POTION_MOVESPEED_SPLASH_NAME = "minecraft:potion.moveSpeed.splash.name";

    /**
     * {@literal Lingering Long Mundane Potion}
     */
    String MC_POTION_MUNDANE_EXTENDED_LINGER_NAME = "minecraft:potion.mundane.extended.linger.name";

    /**
     * {@literal Long Mundane Potion}
     */
    String MC_POTION_MUNDANE_EXTENDED_NAME = "minecraft:potion.mundane.extended.name";

    /**
     * {@literal Splash Long Mundane Potion}
     */
    String MC_POTION_MUNDANE_EXTENDED_SPLASH_NAME = "minecraft:potion.mundane.extended.splash.name";

    /**
     * {@literal Lingering Mundane Potion}
     */
    String MC_POTION_MUNDANE_LINGER_NAME = "minecraft:potion.mundane.linger.name";

    /**
     * {@literal Mundane Potion}
     */
    String MC_POTION_MUNDANE_NAME = "minecraft:potion.mundane.name";

    /**
     * {@literal Splash Mundane Potion}
     */
    String MC_POTION_MUNDANE_SPLASH_NAME = "minecraft:potion.mundane.splash.name";

    /**
     * {@literal Night Vision}
     */
    String MC_POTION_NIGHTVISION = "minecraft:potion.nightVision";

    /**
     * {@literal Lingering Potion of Night Vision}
     */
    String MC_POTION_NIGHTVISION_LINGER_NAME = "minecraft:potion.nightVision.linger.name";

    /**
     * {@literal Potion of Night Vision}
     */
    String MC_POTION_NIGHTVISION_NAME = "minecraft:potion.nightVision.name";

    /**
     * {@literal Splash Potion of Night Vision}
     */
    String MC_POTION_NIGHTVISION_SPLASH_NAME = "minecraft:potion.nightVision.splash.name";

    /**
     * {@literal Oozing}
     */
    String MC_POTION_OOZING = "minecraft:potion.oozing";

    /**
     * {@literal Lingering Potion of Oozing}
     */
    String MC_POTION_OOZING_LINGER_NAME = "minecraft:potion.oozing.linger.name";

    /**
     * {@literal Potion of Oozing}
     */
    String MC_POTION_OOZING_NAME = "minecraft:potion.oozing.name";

    /**
     * {@literal Splash Potion of Oozing}
     */
    String MC_POTION_OOZING_SPLASH_NAME = "minecraft:potion.oozing.splash.name";

    /**
     * {@literal Poison}
     */
    String MC_POTION_POISON = "minecraft:potion.poison";

    /**
     * {@literal Lingering Potion of Poison}
     */
    String MC_POTION_POISON_LINGER_NAME = "minecraft:potion.poison.linger.name";

    /**
     * {@literal Potion of Poison}
     */
    String MC_POTION_POISON_NAME = "minecraft:potion.poison.name";

    /**
     * {@literal Splash Potion of Poison}
     */
    String MC_POTION_POISON_SPLASH_NAME = "minecraft:potion.poison.splash.name";

    /**
     * {@literal }
     */
    String MC_POTION_POTENCY_0 = "minecraft:potion.potency.0";

    /**
     * {@literal II}
     */
    String MC_POTION_POTENCY_1 = "minecraft:potion.potency.1";

    /**
     * {@literal III}
     */
    String MC_POTION_POTENCY_2 = "minecraft:potion.potency.2";

    /**
     * {@literal IV}
     */
    String MC_POTION_POTENCY_3 = "minecraft:potion.potency.3";

    /**
     * {@literal V}
     */
    String MC_POTION_POTENCY_4 = "minecraft:potion.potency.4";

    /**
     * {@literal VI}
     */
    String MC_POTION_POTENCY_5 = "minecraft:potion.potency.5";

    /**
     * {@literal Regeneration}
     */
    String MC_POTION_REGENERATION = "minecraft:potion.regeneration";

    /**
     * {@literal Lingering Potion of Regeneration}
     */
    String MC_POTION_REGENERATION_LINGER_NAME = "minecraft:potion.regeneration.linger.name";

    /**
     * {@literal Potion of Regeneration}
     */
    String MC_POTION_REGENERATION_NAME = "minecraft:potion.regeneration.name";

    /**
     * {@literal Splash Potion of Regeneration}
     */
    String MC_POTION_REGENERATION_SPLASH_NAME = "minecraft:potion.regeneration.splash.name";

    /**
     * {@literal Resistance}
     */
    String MC_POTION_RESISTANCE = "minecraft:potion.resistance";

    /**
     * {@literal Saturation}
     */
    String MC_POTION_SATURATION = "minecraft:potion.saturation";

    /**
     * {@literal Slow Falling}
     */
    String MC_POTION_SLOWFALLING = "minecraft:potion.slowFalling";

    /**
     * {@literal Lingering Potion of Slow Falling}
     */
    String MC_POTION_SLOWFALLING_LINGER_NAME = "minecraft:potion.slowFalling.linger.name";

    /**
     * {@literal Potion of Slow Falling}
     */
    String MC_POTION_SLOWFALLING_NAME = "minecraft:potion.slowFalling.name";

    /**
     * {@literal Splash Potion of Slow Falling}
     */
    String MC_POTION_SLOWFALLING_SPLASH_NAME = "minecraft:potion.slowFalling.splash.name";

    /**
     * {@literal Lingering Thick Potion}
     */
    String MC_POTION_THICK_LINGER_NAME = "minecraft:potion.thick.linger.name";

    /**
     * {@literal Thick Potion}
     */
    String MC_POTION_THICK_NAME = "minecraft:potion.thick.name";

    /**
     * {@literal Splash Thick Potion}
     */
    String MC_POTION_THICK_SPLASH_NAME = "minecraft:potion.thick.splash.name";

    /**
     * {@literal Slowness}
     */
    String MC_POTION_TURTLEMASTER = "minecraft:potion.turtleMaster";

    /**
     * {@literal Lingering Potion of the Turtle Master}
     */
    String MC_POTION_TURTLEMASTER_LINGER_NAME = "minecraft:potion.turtleMaster.linger.name";

    /**
     * {@literal Potion of the Turtle Master}
     */
    String MC_POTION_TURTLEMASTER_NAME = "minecraft:potion.turtleMaster.name";

    /**
     * {@literal Splash Potion of the Turtle Master}
     */
    String MC_POTION_TURTLEMASTER_SPLASH_NAME = "minecraft:potion.turtleMaster.splash.name";

    /**
     * {@literal Resistance}
     */
    String MC_POTION_TURTLEMASTER2 = "minecraft:potion.turtleMaster2";

    /**
     * {@literal Water Breathing}
     */
    String MC_POTION_WATERBREATHING = "minecraft:potion.waterBreathing";

    /**
     * {@literal Lingering Potion of Water Breathing}
     */
    String MC_POTION_WATERBREATHING_LINGER_NAME = "minecraft:potion.waterBreathing.linger.name";

    /**
     * {@literal Potion of Water Breathing}
     */
    String MC_POTION_WATERBREATHING_NAME = "minecraft:potion.waterBreathing.name";

    /**
     * {@literal Splash Potion of Water Breathing}
     */
    String MC_POTION_WATERBREATHING_SPLASH_NAME = "minecraft:potion.waterBreathing.splash.name";

    /**
     * {@literal Weakness}
     */
    String MC_POTION_WEAKNESS = "minecraft:potion.weakness";

    /**
     * {@literal Lingering Potion of Weakness}
     */
    String MC_POTION_WEAKNESS_LINGER_NAME = "minecraft:potion.weakness.linger.name";

    /**
     * {@literal Potion of Weakness}
     */
    String MC_POTION_WEAKNESS_NAME = "minecraft:potion.weakness.name";

    /**
     * {@literal Splash Potion of Weakness}
     */
    String MC_POTION_WEAKNESS_SPLASH_NAME = "minecraft:potion.weakness.splash.name";

    /**
     * {@literal Weaving}
     */
    String MC_POTION_WEAVING = "minecraft:potion.weaving";

    /**
     * {@literal Lingering Potion of Weaving}
     */
    String MC_POTION_WEAVING_LINGER_NAME = "minecraft:potion.weaving.linger.name";

    /**
     * {@literal Potion of Weaving}
     */
    String MC_POTION_WEAVING_NAME = "minecraft:potion.weaving.name";

    /**
     * {@literal Splash Potion of Weaving}
     */
    String MC_POTION_WEAVING_SPLASH_NAME = "minecraft:potion.weaving.splash.name";

    /**
     * {@literal Wind Charged}
     */
    String MC_POTION_WINDCHARGED = "minecraft:potion.windCharged";

    /**
     * {@literal Lingering Potion of Wind Charging}
     */
    String MC_POTION_WINDCHARGED_LINGER_NAME = "minecraft:potion.windCharged.linger.name";

    /**
     * {@literal Potion of Wind Charging}
     */
    String MC_POTION_WINDCHARGED_NAME = "minecraft:potion.windCharged.name";

    /**
     * {@literal Splash Potion of Wind Charging}
     */
    String MC_POTION_WINDCHARGED_SPLASH_NAME = "minecraft:potion.windCharged.splash.name";

    /**
     * {@literal Wither}
     */
    String MC_POTION_WITHER = "minecraft:potion.wither";

    /**
     * {@literal Lingering Potion of Decay}
     */
    String MC_POTION_WITHER_LINGER_NAME = "minecraft:potion.wither.linger.name";

    /**
     * {@literal Potion of Decay}
     */
    String MC_POTION_WITHER_NAME = "minecraft:potion.wither.name";

    /**
     * {@literal Splash Potion of Decay}
     */
    String MC_POTION_WITHER_SPLASH_NAME = "minecraft:potion.wither.splash.name";

    /**
     * {@literal The text you entered contains words that others may find offensive and cannot be used. Please try again.}
     */
    String MC_PROFANITY_FILTER_MSG = "minecraft:profanity_filter.msg";

    /**
     * {@literal Profanity Filter}
     */
    String MC_PROFANITY_FILTER_TITLE = "minecraft:profanity_filter.title";

    /**
     * {@literal Take the Quiz}
     */
    String MC_QUIZ_POPUP_OK = "minecraft:quiz.popup.ok";

    /**
     * {@literal The quiz will open in a new window. You may return to Minecraft after you have completed the quiz.}
     */
    String MC_QUIZ_POPUP_TEXT = "minecraft:quiz.popup.text";

    /**
     * {@literal Quiz}
     */
    String MC_QUIZ_POPUP_TITLE = "minecraft:quiz.popup.title";

    /**
     * {@literal Defeat}
     */
    String MC_RAID_DEFEAT = "minecraft:raid.defeat";

    /**
     * {@literal A raid has expired}
     */
    String MC_RAID_EXPIRY = "minecraft:raid.expiry";

    /**
     * {@literal Raid}
     */
    String MC_RAID_NAME = "minecraft:raid.name";

    /**
     * {@literal Mobs remaining:}
     */
    String MC_RAID_PROGRESS = "minecraft:raid.progress";

    /**
     * {@literal Victory}
     */
    String MC_RAID_VICTORY = "minecraft:raid.victory";

    /**
     * {@literal We have detected that your device does not meet the system requirements to run this pack so you are unable to download this pack. You can review the minimum system requirements below: %s+ GPU: Hardware ray tracing capable GPU such as NVIDIA GeForce RTX 2060 or better. See our FAQ on minecraft.net for more details. %s+ RAM: 8GB or better %s+ CPU: Intel Core i5 or equivalent }
     */
    String MC_RAYCASTING_PURCHASE_ERROR = "minecraft:raycasting.purchase.error";

    /**
     * {@literal System Requirements Warning}
     */
    String MC_RAYCASTING_PURCHASE_ERROR_TITLE = "minecraft:raycasting.purchase.error.title";

    /**
     * {@literal This pack is not available to download as your device doesn't meet the minimum system requirements. These requirements can be found in the pack description.}
     */
    String MC_RAYCASTING_PURCHASE_ERROR_TOOLTIP = "minecraft:raycasting.purchase.error.tooltip";

    /**
     * {@literal Joining Realm...}
     */
    String MC_REALMJOINING_PROGRESSTITLE = "minecraft:realmJoining.progressTitle";

    /**
     * {@literal Now playing: %s}
     */
    String MC_RECORD_NOWPLAYING = "minecraft:record.nowPlaying";

    /**
     * {@literal Candy Texture Pack}
     */
    String MC_RESOURCEPACK_CANDY = "minecraft:resourcepack.Candy";

    /**
     * {@literal Cartoon Texture Pack}
     */
    String MC_RESOURCEPACK_CARTOON = "minecraft:resourcepack.Cartoon";

    /**
     * {@literal Chinese Mythology Mash-up}
     */
    String MC_RESOURCEPACK_CHINESEMYTHOLOGY = "minecraft:resourcepack.ChineseMythology";

    /**
     * {@literal City Texture Pack}
     */
    String MC_RESOURCEPACK_CITY = "minecraft:resourcepack.City";

    /**
     * {@literal Fallout Mash-up}
     */
    String MC_RESOURCEPACK_FALLOUT = "minecraft:resourcepack.Fallout";

    /**
     * {@literal Fantasy Texture Pack}
     */
    String MC_RESOURCEPACK_FANTASY = "minecraft:resourcepack.Fantasy";

    /**
     * {@literal Festive Mash-up 2016}
     */
    String MC_RESOURCEPACK_FESTIVEMASHUP2016 = "minecraft:resourcepack.FestiveMashup2016";

    /**
     * {@literal Magic: The Gathering}
     */
    String MC_RESOURCEPACK_MAGICTHEGATHERING_NAME = "minecraft:resourcepack.MagicTheGathering.name";

    /**
     * {@literal Natural Texture Pack}
     */
    String MC_RESOURCEPACK_NATURAL = "minecraft:resourcepack.Natural";

    /**
     * {@literal Plastic Texture Pack}
     */
    String MC_RESOURCEPACK_PLASTIC = "minecraft:resourcepack.Plastic";

    /**
     * {@literal We're sorry to hear you didn't enjoy your pack at all. Use the button below to let the creator know how they can improve.}
     */
    String MC_REVIEW_ITEM_POST_RATING_1STAR = "minecraft:review.item.post.rating.1star";

    /**
     * {@literal That's no good. Use the button below to let the creator know how they can improve.}
     */
    String MC_REVIEW_ITEM_POST_RATING_2STAR = "minecraft:review.item.post.rating.2star";

    /**
     * {@literal There's always room for improvement, but we're glad you're still having fun.}
     */
    String MC_REVIEW_ITEM_POST_RATING_3STAR = "minecraft:review.item.post.rating.3star";

    /**
     * {@literal We're glad you're having a great time!}
     */
    String MC_REVIEW_ITEM_POST_RATING_4STAR = "minecraft:review.item.post.rating.4star";

    /**
     * {@literal Woohoo! We're glad you're having a good time!}
     */
    String MC_REVIEW_ITEM_POST_RATING_5STAR = "minecraft:review.item.post.rating.5star";

    /**
     * {@literal Choose a star rating}
     */
    String MC_REVIEW_ITEM_POST_RATING_DROPDOWN_LABEL = "minecraft:review.item.post.rating.dropdown.label";

    /**
     * {@literal You will be able to change your rating.}
     */
    String MC_REVIEW_ITEM_POST_RATING_FOOTER = "minecraft:review.item.post.rating.footer";

    /**
     * {@literal Rate this pack}
     */
    String MC_REVIEW_ITEM_POST_RATING_SUBMIT_BUTTON = "minecraft:review.item.post.rating.submit.button";

    /**
     * {@literal Thanks for rating this pack!}
     */
    String MC_REVIEW_ITEM_POST_RATING_SUBMIT_TOAST_LINE1 = "minecraft:review.item.post.rating.submit.toast.line1";

    /**
     * {@literal It may take some time for us to show your rating.}
     */
    String MC_REVIEW_ITEM_POST_RATING_SUBMIT_TOAST_LINE2 = "minecraft:review.item.post.rating.submit.toast.line2";

    /**
     * {@literal Limited usage}
     */
    String MC_ROAMING_STATUS_BRIEF_LIMITED_USAGE = "minecraft:roaming.status_brief.limited_usage";

    /**
     * {@literal Usable cross-platform}
     */
    String MC_ROAMING_STATUS_BRIEF_NO_RESTRICTIONS = "minecraft:roaming.status_brief.no_restrictions";

    /**
     * {@literal Only available on specific platforms.}
     */
    String MC_ROAMING_STATUS_HOVER_LIMITED_USAGE = "minecraft:roaming.status_hover.limited_usage";

    /**
     * {@literal Available on any Bedrock platform!}
     */
    String MC_ROAMING_STATUS_HOVER_NO_RESTRICTIONS = "minecraft:roaming.status_hover.no_restrictions";

    /**
     * {@literal Your current skin violates our Community Standards. Please select a different skin to join a multiplayer game. Click "%s" to view our Community Standards or submit a case review for your current skin.}
     */
    String MC_SAFETY_BANNEDSKIN = "minecraft:safety.bannedSkin";

    /**
     * {@literal Learn More}
     */
    String MC_SAFETY_BANNEDSKIN_BUTTON = "minecraft:safety.bannedSkin.button";

    /**
     * {@literal Continue with default skin}
     */
    String MC_SAFETY_BANNEDSKIN_CONTINUE = "minecraft:safety.bannedSkin.continue";

    /**
     * {@literal Skin Not Allowed In Multiplayer}
     */
    String MC_SAFETY_BANNEDSKIN_TITLE = "minecraft:safety.bannedSkin.title";

    /**
     * {@literal Remember to follow the Minecraft Community Standards.}
     */
    String MC_SAFETY_MUTED_MESSAGE = "minecraft:safety.muted.message";

    /**
     * {@literal You've been temporarily muted in chat.}
     */
    String MC_SAFETY_MUTED_TITLE = "minecraft:safety.muted.title";

    /**
     * {@literal §eChat and other user generated text is not available right now. Please try again later.§f}
     */
    String MC_SAFETY_TEXTPROCESSOROFFLINE_MESSAGE = "minecraft:safety.textProcessorOffline.message";

    /**
     * {@literal Add a caption and share?}
     */
    String MC_SCREENSHOT_CAPTION = "minecraft:screenshot.caption";

    /**
     * {@literal Couldn't save screenshot: %s}
     */
    String MC_SCREENSHOT_FAILURE = "minecraft:screenshot.failure";

    /**
     * {@literal Share}
     */
    String MC_SCREENSHOT_POST = "minecraft:screenshot.post";

    /**
     * {@literal Saved screenshot as %s}
     */
    String MC_SCREENSHOT_SUCCESS = "minecraft:screenshot.success";

    /**
     * {@literal Screenshot Captured!}
     */
    String MC_SCREENSHOT_TITLE = "minecraft:screenshot.title";

    /**
     * {@literal Search}
     */
    String MC_SEEDPICKER_SEARCH = "minecraft:seedPicker.search";

    /**
     * {@literal Seed Picker}
     */
    String MC_SEEDPICKER_TITLE = "minecraft:seedPicker.title";

    /**
     * {@literal Received invalid form json. Error:%s}
     */
    String MC_SERVERUI_ERRORDESCRIPTION = "minecraft:serverUI.errorDescription";

    /**
     * {@literal Error creating form.}
     */
    String MC_SERVERUI_ERRORTITLE = "minecraft:serverUI.errorTitle";

    /**
     * {@literal Edit sign message}
     */
    String MC_SIGN_EDIT = "minecraft:sign.edit";

    /**
     * {@literal When on Chest:}
     */
    String MC_SLOT_ARMOR_CHEST_WHEN_EQUIPPED = "minecraft:slot.armor.chest.when_equipped";

    /**
     * {@literal When on Feet:}
     */
    String MC_SLOT_ARMOR_FEET_WHEN_EQUIPPED = "minecraft:slot.armor.feet.when_equipped";

    /**
     * {@literal When on Head:}
     */
    String MC_SLOT_ARMOR_HEAD_WHEN_EQUIPPED = "minecraft:slot.armor.head.when_equipped";

    /**
     * {@literal When on Legs:}
     */
    String MC_SLOT_ARMOR_LEGS_WHEN_EQUIPPED = "minecraft:slot.armor.legs.when_equipped";

    /**
     * {@literal Go Back}
     */
    String MC_TERMS_AND_CONDITIONS_GOBACK = "minecraft:terms_and_conditions.goBack";

    /**
     * {@literal To view terms and conditions, please visit https://minecraft.net/terms in any web browser.}
     */
    String MC_TERMS_AND_CONDITIONS_VIEWTERMSANDCONDITIONS = "minecraft:terms_and_conditions.viewTermsAndConditions";

    /**
     * {@literal TAP}
     */
    String MC_TEXTTOICON_TOUCH_ASSIGMENT_ATTACK = "minecraft:textToIcon.touch.assigment.attack";

    /**
     * {@literal LONG TAP}
     */
    String MC_TEXTTOICON_TOUCH_ASSIGMENT_USE = "minecraft:textToIcon.touch.assigment.use";

    /**
     * {@literal Acacia Fence}
     */
    String MC_TILE_ACACIAFENCE_NAME = "minecraft:tile.acaciaFence.name";

    /**
     * {@literal Acacia Button}
     */
    String MC_TILE_ACACIA_BUTTON_NAME = "minecraft:tile.acacia_button.name";

    /**
     * {@literal Acacia Fence Gate}
     */
    String MC_TILE_ACACIA_FENCE_GATE_NAME = "minecraft:tile.acacia_fence_gate.name";

    /**
     * {@literal Acacia Pressure Plate}
     */
    String MC_TILE_ACACIA_PRESSURE_PLATE_NAME = "minecraft:tile.acacia_pressure_plate.name";

    /**
     * {@literal Acacia Shelf}
     */
    String MC_TILE_ACACIA_SHELF_NAME = "minecraft:tile.acacia_shelf.name";

    /**
     * {@literal Acacia Stairs}
     */
    String MC_TILE_ACACIA_STAIRS_NAME = "minecraft:tile.acacia_stairs.name";

    /**
     * {@literal Acacia Sign}
     */
    String MC_TILE_ACACIA_STANDING_SIGN_NAME = "minecraft:tile.acacia_standing_sign.name";

    /**
     * {@literal Acacia Trapdoor}
     */
    String MC_TILE_ACACIA_TRAPDOOR_NAME = "minecraft:tile.acacia_trapdoor.name";

    /**
     * {@literal Activator Rail}
     */
    String MC_TILE_ACTIVATOR_RAIL_NAME = "minecraft:tile.activator_rail.name";

    /**
     * {@literal Air}
     */
    String MC_TILE_AIR_NAME = "minecraft:tile.air.name";

    /**
     * {@literal Allow}
     */
    String MC_TILE_ALLOW_NAME = "minecraft:tile.allow.name";

    /**
     * {@literal Block of Amethyst}
     */
    String MC_TILE_AMETHYST_BLOCK_NAME = "minecraft:tile.amethyst_block.name";

    /**
     * {@literal Amethyst Cluster}
     */
    String MC_TILE_AMETHYST_CLUSTER_NAME = "minecraft:tile.amethyst_cluster.name";

    /**
     * {@literal Ancient Debris}
     */
    String MC_TILE_ANCIENT_DEBRIS_NAME = "minecraft:tile.ancient_debris.name";

    /**
     * {@literal Andesite Stairs}
     */
    String MC_TILE_ANDESITE_STAIRS_NAME = "minecraft:tile.andesite_stairs.name";

    /**
     * {@literal Anvil}
     */
    String MC_TILE_ANVIL_INTACT_NAME = "minecraft:tile.anvil.intact.name";

    /**
     * {@literal Anvil}
     */
    String MC_TILE_ANVIL_NAME = "minecraft:tile.anvil.name";

    /**
     * {@literal Chipped Anvil}
     */
    String MC_TILE_ANVIL_SLIGHTLYDAMAGED_NAME = "minecraft:tile.anvil.slightlyDamaged.name";

    /**
     * {@literal Damaged Anvil}
     */
    String MC_TILE_ANVIL_VERYDAMAGED_NAME = "minecraft:tile.anvil.veryDamaged.name";

    /**
     * {@literal Azalea}
     */
    String MC_TILE_AZALEA_NAME = "minecraft:tile.azalea.name";

    /**
     * {@literal Azalea Leaves}
     */
    String MC_TILE_AZALEA_LEAVES_NAME = "minecraft:tile.azalea_leaves.name";

    /**
     * {@literal Flowering Azalea Leaves}
     */
    String MC_TILE_AZALEA_LEAVES_FLOWERED_NAME = "minecraft:tile.azalea_leaves_flowered.name";

    /**
     * {@literal Bamboo}
     */
    String MC_TILE_BAMBOO_NAME = "minecraft:tile.bamboo.name";

    /**
     * {@literal Block of Bamboo}
     */
    String MC_TILE_BAMBOO_BLOCK_NAME = "minecraft:tile.bamboo_block.name";

    /**
     * {@literal Bamboo Button}
     */
    String MC_TILE_BAMBOO_BUTTON_NAME = "minecraft:tile.bamboo_button.name";

    /**
     * {@literal Bamboo Fence}
     */
    String MC_TILE_BAMBOO_FENCE_NAME = "minecraft:tile.bamboo_fence.name";

    /**
     * {@literal Bamboo Fence Gate}
     */
    String MC_TILE_BAMBOO_FENCE_GATE_NAME = "minecraft:tile.bamboo_fence_gate.name";

    /**
     * {@literal Bamboo Mosaic}
     */
    String MC_TILE_BAMBOO_MOSAIC_NAME = "minecraft:tile.bamboo_mosaic.name";

    /**
     * {@literal Bamboo Mosaic Slab}
     */
    String MC_TILE_BAMBOO_MOSAIC_SLAB_NAME = "minecraft:tile.bamboo_mosaic_slab.name";

    /**
     * {@literal Bamboo Mosaic Stairs}
     */
    String MC_TILE_BAMBOO_MOSAIC_STAIRS_NAME = "minecraft:tile.bamboo_mosaic_stairs.name";

    /**
     * {@literal Bamboo Planks}
     */
    String MC_TILE_BAMBOO_PLANKS_NAME = "minecraft:tile.bamboo_planks.name";

    /**
     * {@literal Bamboo Pressure Plate}
     */
    String MC_TILE_BAMBOO_PRESSURE_PLATE_NAME = "minecraft:tile.bamboo_pressure_plate.name";

    /**
     * {@literal Bamboo Shelf}
     */
    String MC_TILE_BAMBOO_SHELF_NAME = "minecraft:tile.bamboo_shelf.name";

    /**
     * {@literal Bamboo Slab}
     */
    String MC_TILE_BAMBOO_SLAB_NAME = "minecraft:tile.bamboo_slab.name";

    /**
     * {@literal Bamboo Stairs}
     */
    String MC_TILE_BAMBOO_STAIRS_NAME = "minecraft:tile.bamboo_stairs.name";

    /**
     * {@literal Bamboo Trapdoor}
     */
    String MC_TILE_BAMBOO_TRAPDOOR_NAME = "minecraft:tile.bamboo_trapdoor.name";

    /**
     * {@literal Barrel}
     */
    String MC_TILE_BARREL_NAME = "minecraft:tile.barrel.name";

    /**
     * {@literal Barrier}
     */
    String MC_TILE_BARRIER_NAME = "minecraft:tile.barrier.name";

    /**
     * {@literal Basalt}
     */
    String MC_TILE_BASALT_NAME = "minecraft:tile.basalt.name";

    /**
     * {@literal Beacon}
     */
    String MC_TILE_BEACON_NAME = "minecraft:tile.beacon.name";

    /**
     * {@literal Primary Power}
     */
    String MC_TILE_BEACON_PRIMARY = "minecraft:tile.beacon.primary";

    /**
     * {@literal Primary}
     */
    String MC_TILE_BEACON_PRIMARY_POCKET = "minecraft:tile.beacon.primary.pocket";

    /**
     * {@literal Secondary Power}
     */
    String MC_TILE_BEACON_SECONDARY = "minecraft:tile.beacon.secondary";

    /**
     * {@literal Secondary}
     */
    String MC_TILE_BEACON_SECONDARY_POCKET = "minecraft:tile.beacon.secondary.pocket";

    /**
     * {@literal Bed}
     */
    String MC_TILE_BED_NAME = "minecraft:tile.bed.name";

    /**
     * {@literal You can only sleep at night and during thunderstorms}
     */
    String MC_TILE_BED_NOSLEEP = "minecraft:tile.bed.noSleep";

    /**
     * {@literal You may not rest now, there are monsters nearby}
     */
    String MC_TILE_BED_NOTSAFE = "minecraft:tile.bed.notSafe";

    /**
     * {@literal Your home bed was missing or obstructed}
     */
    String MC_TILE_BED_NOTVALID = "minecraft:tile.bed.notValid";

    /**
     * {@literal Bed is obstructed}
     */
    String MC_TILE_BED_OBSTRUCTED = "minecraft:tile.bed.obstructed";

    /**
     * {@literal This bed is occupied}
     */
    String MC_TILE_BED_OCCUPIED = "minecraft:tile.bed.occupied";

    /**
     * {@literal Respawn point set}
     */
    String MC_TILE_BED_RESPAWNSET = "minecraft:tile.bed.respawnSet";

    /**
     * {@literal Bed is too far away}
     */
    String MC_TILE_BED_TOOFAR = "minecraft:tile.bed.tooFar";

    /**
     * {@literal Bedrock}
     */
    String MC_TILE_BEDROCK_NAME = "minecraft:tile.bedrock.name";

    /**
     * {@literal Bee Nest}
     */
    String MC_TILE_BEE_NEST_NAME = "minecraft:tile.bee_nest.name";

    /**
     * {@literal Beehive}
     */
    String MC_TILE_BEEHIVE_NAME = "minecraft:tile.beehive.name";

    /**
     * {@literal Beetroot}
     */
    String MC_TILE_BEETROOT_NAME = "minecraft:tile.beetroot.name";

    /**
     * {@literal Bell}
     */
    String MC_TILE_BELL_NAME = "minecraft:tile.bell.name";

    /**
     * {@literal Big Dripleaf}
     */
    String MC_TILE_BIG_DRIPLEAF_NAME = "minecraft:tile.big_dripleaf.name";

    /**
     * {@literal Birch Fence}
     */
    String MC_TILE_BIRCHFENCE_NAME = "minecraft:tile.birchFence.name";

    /**
     * {@literal Birch Button}
     */
    String MC_TILE_BIRCH_BUTTON_NAME = "minecraft:tile.birch_button.name";

    /**
     * {@literal Birch Fence Gate}
     */
    String MC_TILE_BIRCH_FENCE_GATE_NAME = "minecraft:tile.birch_fence_gate.name";

    /**
     * {@literal Birch Pressure Plate}
     */
    String MC_TILE_BIRCH_PRESSURE_PLATE_NAME = "minecraft:tile.birch_pressure_plate.name";

    /**
     * {@literal Birch Shelf}
     */
    String MC_TILE_BIRCH_SHELF_NAME = "minecraft:tile.birch_shelf.name";

    /**
     * {@literal Birch Stairs}
     */
    String MC_TILE_BIRCH_STAIRS_NAME = "minecraft:tile.birch_stairs.name";

    /**
     * {@literal Birch Sign}
     */
    String MC_TILE_BIRCH_STANDING_SIGN_NAME = "minecraft:tile.birch_standing_sign.name";

    /**
     * {@literal Birch Trapdoor}
     */
    String MC_TILE_BIRCH_TRAPDOOR_NAME = "minecraft:tile.birch_trapdoor.name";

    /**
     * {@literal Black Candle}
     */
    String MC_TILE_BLACK_CANDLE_NAME = "minecraft:tile.black_candle.name";

    /**
     * {@literal Cake with Black Candle}
     */
    String MC_TILE_BLACK_CANDLE_CAKE_NAME = "minecraft:tile.black_candle_cake.name";

    /**
     * {@literal Blackstone}
     */
    String MC_TILE_BLACKSTONE_NAME = "minecraft:tile.blackstone.name";

    /**
     * {@literal Blackstone Slab}
     */
    String MC_TILE_BLACKSTONE_SLAB_NAME = "minecraft:tile.blackstone_slab.name";

    /**
     * {@literal Blackstone Stairs}
     */
    String MC_TILE_BLACKSTONE_STAIRS_NAME = "minecraft:tile.blackstone_stairs.name";

    /**
     * {@literal Blackstone Wall}
     */
    String MC_TILE_BLACKSTONE_WALL_NAME = "minecraft:tile.blackstone_wall.name";

    /**
     * {@literal Blast Furnace}
     */
    String MC_TILE_BLAST_FURNACE_NAME = "minecraft:tile.blast_furnace.name";

    /**
     * {@literal Blue Candle}
     */
    String MC_TILE_BLUE_CANDLE_NAME = "minecraft:tile.blue_candle.name";

    /**
     * {@literal Cake with Blue Candle}
     */
    String MC_TILE_BLUE_CANDLE_CAKE_NAME = "minecraft:tile.blue_candle_cake.name";

    /**
     * {@literal Blue Ice}
     */
    String MC_TILE_BLUE_ICE_NAME = "minecraft:tile.blue_ice.name";

    /**
     * {@literal Bone Block}
     */
    String MC_TILE_BONE_BLOCK_NAME = "minecraft:tile.bone_block.name";

    /**
     * {@literal Bookshelf}
     */
    String MC_TILE_BOOKSHELF_NAME = "minecraft:tile.bookshelf.name";

    /**
     * {@literal Border}
     */
    String MC_TILE_BORDER_BLOCK_NAME = "minecraft:tile.border_block.name";

    /**
     * {@literal Bricks}
     */
    String MC_TILE_BRICK_BLOCK_NAME = "minecraft:tile.brick_block.name";

    /**
     * {@literal Brick Stairs}
     */
    String MC_TILE_BRICK_STAIRS_NAME = "minecraft:tile.brick_stairs.name";

    /**
     * {@literal Brown Candle}
     */
    String MC_TILE_BROWN_CANDLE_NAME = "minecraft:tile.brown_candle.name";

    /**
     * {@literal Cake with Brown Candle}
     */
    String MC_TILE_BROWN_CANDLE_CAKE_NAME = "minecraft:tile.brown_candle_cake.name";

    /**
     * {@literal Brown Mushroom}
     */
    String MC_TILE_BROWN_MUSHROOM_NAME = "minecraft:tile.brown_mushroom.name";

    /**
     * {@literal Brown Mushroom Block}
     */
    String MC_TILE_BROWN_MUSHROOM_BLOCK_CAP_NAME = "minecraft:tile.brown_mushroom_block.cap.name";

    /**
     * {@literal Mushroom}
     */
    String MC_TILE_BROWN_MUSHROOM_BLOCK_MUSHROOM_NAME = "minecraft:tile.brown_mushroom_block.mushroom.name";

    /**
     * {@literal Mushroom Stem}
     */
    String MC_TILE_BROWN_MUSHROOM_BLOCK_STEM_NAME = "minecraft:tile.brown_mushroom_block.stem.name";

    /**
     * {@literal Budding Amethyst}
     */
    String MC_TILE_BUDDING_AMETHYST_NAME = "minecraft:tile.budding_amethyst.name";

    /**
     * {@literal Bush}
     */
    String MC_TILE_BUSH_NAME = "minecraft:tile.bush.name";

    /**
     * {@literal Cactus}
     */
    String MC_TILE_CACTUS_NAME = "minecraft:tile.cactus.name";

    /**
     * {@literal Cactus Flower}
     */
    String MC_TILE_CACTUS_FLOWER_NAME = "minecraft:tile.cactus_flower.name";

    /**
     * {@literal Cake}
     */
    String MC_TILE_CAKE_NAME = "minecraft:tile.cake.name";

    /**
     * {@literal Calcite}
     */
    String MC_TILE_CALCITE_NAME = "minecraft:tile.calcite.name";

    /**
     * {@literal Calibrated Sculk Sensor}
     */
    String MC_TILE_CALIBRATED_SCULK_SENSOR_NAME = "minecraft:tile.calibrated_sculk_sensor.name";

    /**
     * {@literal Camera}
     */
    String MC_TILE_CAMERA_NAME = "minecraft:tile.camera.name";

    /**
     * {@literal Campfire}
     */
    String MC_TILE_CAMPFIRE_NAME = "minecraft:tile.campfire.name";

    /**
     * {@literal Candle}
     */
    String MC_TILE_CANDLE_NAME = "minecraft:tile.candle.name";

    /**
     * {@literal Cake with Candle}
     */
    String MC_TILE_CANDLE_CAKE_NAME = "minecraft:tile.candle_cake.name";

    /**
     * {@literal Black Carpet}
     */
    String MC_TILE_CARPET_BLACK_NAME = "minecraft:tile.carpet.black.name";

    /**
     * {@literal Blue Carpet}
     */
    String MC_TILE_CARPET_BLUE_NAME = "minecraft:tile.carpet.blue.name";

    /**
     * {@literal Brown Carpet}
     */
    String MC_TILE_CARPET_BROWN_NAME = "minecraft:tile.carpet.brown.name";

    /**
     * {@literal Cyan Carpet}
     */
    String MC_TILE_CARPET_CYAN_NAME = "minecraft:tile.carpet.cyan.name";

    /**
     * {@literal Gray Carpet}
     */
    String MC_TILE_CARPET_GRAY_NAME = "minecraft:tile.carpet.gray.name";

    /**
     * {@literal Green Carpet}
     */
    String MC_TILE_CARPET_GREEN_NAME = "minecraft:tile.carpet.green.name";

    /**
     * {@literal Light Blue Carpet}
     */
    String MC_TILE_CARPET_LIGHTBLUE_NAME = "minecraft:tile.carpet.lightBlue.name";

    /**
     * {@literal Lime Carpet}
     */
    String MC_TILE_CARPET_LIME_NAME = "minecraft:tile.carpet.lime.name";

    /**
     * {@literal Magenta Carpet}
     */
    String MC_TILE_CARPET_MAGENTA_NAME = "minecraft:tile.carpet.magenta.name";

    /**
     * {@literal Carpet}
     */
    String MC_TILE_CARPET_NAME = "minecraft:tile.carpet.name";

    /**
     * {@literal Orange Carpet}
     */
    String MC_TILE_CARPET_ORANGE_NAME = "minecraft:tile.carpet.orange.name";

    /**
     * {@literal Pink Carpet}
     */
    String MC_TILE_CARPET_PINK_NAME = "minecraft:tile.carpet.pink.name";

    /**
     * {@literal Purple Carpet}
     */
    String MC_TILE_CARPET_PURPLE_NAME = "minecraft:tile.carpet.purple.name";

    /**
     * {@literal Red Carpet}
     */
    String MC_TILE_CARPET_RED_NAME = "minecraft:tile.carpet.red.name";

    /**
     * {@literal Light Gray Carpet}
     */
    String MC_TILE_CARPET_SILVER_NAME = "minecraft:tile.carpet.silver.name";

    /**
     * {@literal White Carpet}
     */
    String MC_TILE_CARPET_WHITE_NAME = "minecraft:tile.carpet.white.name";

    /**
     * {@literal Yellow Carpet}
     */
    String MC_TILE_CARPET_YELLOW_NAME = "minecraft:tile.carpet.yellow.name";

    /**
     * {@literal Carrots}
     */
    String MC_TILE_CARROTS_NAME = "minecraft:tile.carrots.name";

    /**
     * {@literal Cartography Table}
     */
    String MC_TILE_CARTOGRAPHY_TABLE_NAME = "minecraft:tile.cartography_table.name";

    /**
     * {@literal Carved Pumpkin}
     */
    String MC_TILE_CARVED_PUMPKIN_NAME = "minecraft:tile.carved_pumpkin.name";

    /**
     * {@literal Cauldron}
     */
    String MC_TILE_CAULDRON_NAME = "minecraft:tile.cauldron.name";

    /**
     * {@literal Cave Vines}
     */
    String MC_TILE_CAVE_VINES_NAME = "minecraft:tile.cave_vines.name";

    /**
     * {@literal Cave Vines}
     */
    String MC_TILE_CAVE_VINES_BODY_WITH_BERRIES_NAME = "minecraft:tile.cave_vines_body_with_berries.name";

    /**
     * {@literal Cave Vines}
     */
    String MC_TILE_CAVE_VINES_HEAD_WITH_BERRIES_NAME = "minecraft:tile.cave_vines_head_with_berries.name";

    /**
     * {@literal Chain}
     */
    String MC_TILE_CHAIN_NAME = "minecraft:tile.chain.name";

    /**
     * {@literal Chain Command Block}
     */
    String MC_TILE_CHAIN_COMMAND_BLOCK_NAME = "minecraft:tile.chain_command_block.name";

    /**
     * {@literal Slate}
     */
    String MC_TILE_CHALKBOARD_ONEBYONE_NAME = "minecraft:tile.chalkboard.oneByOne.name";

    /**
     * {@literal Board}
     */
    String MC_TILE_CHALKBOARD_THREEBYTWO_NAME = "minecraft:tile.chalkboard.threeByTwo.name";

    /**
     * {@literal Poster}
     */
    String MC_TILE_CHALKBOARD_TWOBYONE_NAME = "minecraft:tile.chalkboard.twoByOne.name";

    /**
     * {@literal Cherry Button}
     */
    String MC_TILE_CHERRY_BUTTON_NAME = "minecraft:tile.cherry_button.name";

    /**
     * {@literal Cherry Double Slab}
     */
    String MC_TILE_CHERRY_DOUBLE_SLAB_NAME = "minecraft:tile.cherry_double_slab.name";

    /**
     * {@literal Cherry Fence}
     */
    String MC_TILE_CHERRY_FENCE_NAME = "minecraft:tile.cherry_fence.name";

    /**
     * {@literal Cherry Fence Gate}
     */
    String MC_TILE_CHERRY_FENCE_GATE_NAME = "minecraft:tile.cherry_fence_gate.name";

    /**
     * {@literal Cherry Leaves}
     */
    String MC_TILE_CHERRY_LEAVES_NAME = "minecraft:tile.cherry_leaves.name";

    /**
     * {@literal Cherry Log}
     */
    String MC_TILE_CHERRY_LOG_NAME = "minecraft:tile.cherry_log.name";

    /**
     * {@literal Cherry Planks}
     */
    String MC_TILE_CHERRY_PLANKS_NAME = "minecraft:tile.cherry_planks.name";

    /**
     * {@literal Cherry Pressure Plate}
     */
    String MC_TILE_CHERRY_PRESSURE_PLATE_NAME = "minecraft:tile.cherry_pressure_plate.name";

    /**
     * {@literal Cherry Sapling}
     */
    String MC_TILE_CHERRY_SAPLING_NAME = "minecraft:tile.cherry_sapling.name";

    /**
     * {@literal Cherry Shelf}
     */
    String MC_TILE_CHERRY_SHELF_NAME = "minecraft:tile.cherry_shelf.name";

    /**
     * {@literal Cherry Slab}
     */
    String MC_TILE_CHERRY_SLAB_NAME = "minecraft:tile.cherry_slab.name";

    /**
     * {@literal Cherry Stairs}
     */
    String MC_TILE_CHERRY_STAIRS_NAME = "minecraft:tile.cherry_stairs.name";

    /**
     * {@literal Cherry Trapdoor}
     */
    String MC_TILE_CHERRY_TRAPDOOR_NAME = "minecraft:tile.cherry_trapdoor.name";

    /**
     * {@literal Cherry Wood}
     */
    String MC_TILE_CHERRY_WOOD_NAME = "minecraft:tile.cherry_wood.name";

    /**
     * {@literal Chest}
     */
    String MC_TILE_CHEST_NAME = "minecraft:tile.chest.name";

    /**
     * {@literal Chiseled Bookshelf}
     */
    String MC_TILE_CHISELED_BOOKSHELF_NAME = "minecraft:tile.chiseled_bookshelf.name";

    /**
     * {@literal Chiseled Copper}
     */
    String MC_TILE_CHISELED_COPPER_NAME = "minecraft:tile.chiseled_copper.name";

    /**
     * {@literal Chiseled Deepslate}
     */
    String MC_TILE_CHISELED_DEEPSLATE_NAME = "minecraft:tile.chiseled_deepslate.name";

    /**
     * {@literal Chiseled Nether Bricks}
     */
    String MC_TILE_CHISELED_NETHER_BRICKS_NAME = "minecraft:tile.chiseled_nether_bricks.name";

    /**
     * {@literal Chiseled Polished Blackstone}
     */
    String MC_TILE_CHISELED_POLISHED_BLACKSTONE_NAME = "minecraft:tile.chiseled_polished_blackstone.name";

    /**
     * {@literal Chiseled Resin Bricks}
     */
    String MC_TILE_CHISELED_RESIN_BRICKS_NAME = "minecraft:tile.chiseled_resin_bricks.name";

    /**
     * {@literal Chiseled Tuff}
     */
    String MC_TILE_CHISELED_TUFF_NAME = "minecraft:tile.chiseled_tuff.name";

    /**
     * {@literal Chiseled Tuff Bricks}
     */
    String MC_TILE_CHISELED_TUFF_BRICKS_NAME = "minecraft:tile.chiseled_tuff_bricks.name";

    /**
     * {@literal Chorus Flower}
     */
    String MC_TILE_CHORUS_FLOWER_NAME = "minecraft:tile.chorus_flower.name";

    /**
     * {@literal Chorus Plant}
     */
    String MC_TILE_CHORUS_PLANT_NAME = "minecraft:tile.chorus_plant.name";

    /**
     * {@literal Clay}
     */
    String MC_TILE_CLAY_NAME = "minecraft:tile.clay.name";

    /**
     * {@literal Closed Eyeblossom}
     */
    String MC_TILE_CLOSED_EYEBLOSSOM_NAME = "minecraft:tile.closed_eyeblossom.name";

    /**
     * {@literal Block of Coal}
     */
    String MC_TILE_COAL_BLOCK_NAME = "minecraft:tile.coal_block.name";

    /**
     * {@literal Coal Ore}
     */
    String MC_TILE_COAL_ORE_NAME = "minecraft:tile.coal_ore.name";

    /**
     * {@literal Cobbled Deepslate}
     */
    String MC_TILE_COBBLED_DEEPSLATE_NAME = "minecraft:tile.cobbled_deepslate.name";

    /**
     * {@literal Cobbled Deepslate Double Slab}
     */
    String MC_TILE_COBBLED_DEEPSLATE_DOUBLE_SLAB_NAME = "minecraft:tile.cobbled_deepslate_double_slab.name";

    /**
     * {@literal Cobbled Deepslate Slab}
     */
    String MC_TILE_COBBLED_DEEPSLATE_SLAB_NAME = "minecraft:tile.cobbled_deepslate_slab.name";

    /**
     * {@literal Cobbled Deepslate Stairs}
     */
    String MC_TILE_COBBLED_DEEPSLATE_STAIRS_NAME = "minecraft:tile.cobbled_deepslate_stairs.name";

    /**
     * {@literal Cobbled Deepslate Wall}
     */
    String MC_TILE_COBBLED_DEEPSLATE_WALL_NAME = "minecraft:tile.cobbled_deepslate_wall.name";

    /**
     * {@literal Cobblestone}
     */
    String MC_TILE_COBBLESTONE_NAME = "minecraft:tile.cobblestone.name";

    /**
     * {@literal Andesite Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_ANDESITE_NAME = "minecraft:tile.cobblestone_wall.andesite.name";

    /**
     * {@literal Brick Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_BRICK_NAME = "minecraft:tile.cobblestone_wall.brick.name";

    /**
     * {@literal Diorite Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_DIORITE_NAME = "minecraft:tile.cobblestone_wall.diorite.name";

    /**
     * {@literal End Stone Brick Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_END_BRICK_NAME = "minecraft:tile.cobblestone_wall.end_brick.name";

    /**
     * {@literal Granite Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_GRANITE_NAME = "minecraft:tile.cobblestone_wall.granite.name";

    /**
     * {@literal Mossy Cobblestone Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_MOSSY_NAME = "minecraft:tile.cobblestone_wall.mossy.name";

    /**
     * {@literal Mossy Stone Brick Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_MOSSY_STONE_BRICK_NAME = "minecraft:tile.cobblestone_wall.mossy_stone_brick.name";

    /**
     * {@literal Nether Brick Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_NETHER_BRICK_NAME = "minecraft:tile.cobblestone_wall.nether_brick.name";

    /**
     * {@literal Cobblestone Wall }
     */
    String MC_TILE_COBBLESTONE_WALL_NORMAL_NAME = "minecraft:tile.cobblestone_wall.normal.name";

    /**
     * {@literal Prismarine Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_PRISMARINE_NAME = "minecraft:tile.cobblestone_wall.prismarine.name";

    /**
     * {@literal Red Nether Brick Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_RED_NETHER_BRICK_NAME = "minecraft:tile.cobblestone_wall.red_nether_brick.name";

    /**
     * {@literal Red Sandstone Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_RED_SANDSTONE_NAME = "minecraft:tile.cobblestone_wall.red_sandstone.name";

    /**
     * {@literal Sandstone Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_SANDSTONE_NAME = "minecraft:tile.cobblestone_wall.sandstone.name";

    /**
     * {@literal Stone Brick Wall}
     */
    String MC_TILE_COBBLESTONE_WALL_STONE_BRICK_NAME = "minecraft:tile.cobblestone_wall.stone_brick.name";

    /**
     * {@literal Cocoa}
     */
    String MC_TILE_COCOA_NAME = "minecraft:tile.cocoa.name";

    /**
     * {@literal Command Block}
     */
    String MC_TILE_COMMAND_BLOCK_NAME = "minecraft:tile.command_block.name";

    /**
     * {@literal Composter}
     */
    String MC_TILE_COMPOSTER_NAME = "minecraft:tile.composter.name";

    /**
     * {@literal Black Concrete}
     */
    String MC_TILE_CONCRETE_BLACK_NAME = "minecraft:tile.concrete.black.name";

    /**
     * {@literal Blue Concrete}
     */
    String MC_TILE_CONCRETE_BLUE_NAME = "minecraft:tile.concrete.blue.name";

    /**
     * {@literal Brown Concrete}
     */
    String MC_TILE_CONCRETE_BROWN_NAME = "minecraft:tile.concrete.brown.name";

    /**
     * {@literal Cyan Concrete}
     */
    String MC_TILE_CONCRETE_CYAN_NAME = "minecraft:tile.concrete.cyan.name";

    /**
     * {@literal Gray Concrete}
     */
    String MC_TILE_CONCRETE_GRAY_NAME = "minecraft:tile.concrete.gray.name";

    /**
     * {@literal Green Concrete}
     */
    String MC_TILE_CONCRETE_GREEN_NAME = "minecraft:tile.concrete.green.name";

    /**
     * {@literal Light Blue Concrete}
     */
    String MC_TILE_CONCRETE_LIGHTBLUE_NAME = "minecraft:tile.concrete.lightBlue.name";

    /**
     * {@literal Lime Concrete}
     */
    String MC_TILE_CONCRETE_LIME_NAME = "minecraft:tile.concrete.lime.name";

    /**
     * {@literal Magenta Concrete}
     */
    String MC_TILE_CONCRETE_MAGENTA_NAME = "minecraft:tile.concrete.magenta.name";

    /**
     * {@literal Orange Concrete}
     */
    String MC_TILE_CONCRETE_ORANGE_NAME = "minecraft:tile.concrete.orange.name";

    /**
     * {@literal Pink Concrete}
     */
    String MC_TILE_CONCRETE_PINK_NAME = "minecraft:tile.concrete.pink.name";

    /**
     * {@literal Purple Concrete}
     */
    String MC_TILE_CONCRETE_PURPLE_NAME = "minecraft:tile.concrete.purple.name";

    /**
     * {@literal Red Concrete}
     */
    String MC_TILE_CONCRETE_RED_NAME = "minecraft:tile.concrete.red.name";

    /**
     * {@literal Light Gray Concrete}
     */
    String MC_TILE_CONCRETE_SILVER_NAME = "minecraft:tile.concrete.silver.name";

    /**
     * {@literal White Concrete}
     */
    String MC_TILE_CONCRETE_WHITE_NAME = "minecraft:tile.concrete.white.name";

    /**
     * {@literal Yellow Concrete}
     */
    String MC_TILE_CONCRETE_YELLOW_NAME = "minecraft:tile.concrete.yellow.name";

    /**
     * {@literal Black Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_BLACK_NAME = "minecraft:tile.concretePowder.black.name";

    /**
     * {@literal Blue Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_BLUE_NAME = "minecraft:tile.concretePowder.blue.name";

    /**
     * {@literal Brown Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_BROWN_NAME = "minecraft:tile.concretePowder.brown.name";

    /**
     * {@literal Cyan Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_CYAN_NAME = "minecraft:tile.concretePowder.cyan.name";

    /**
     * {@literal Gray Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_GRAY_NAME = "minecraft:tile.concretePowder.gray.name";

    /**
     * {@literal Green Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_GREEN_NAME = "minecraft:tile.concretePowder.green.name";

    /**
     * {@literal Light Blue Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_LIGHTBLUE_NAME = "minecraft:tile.concretePowder.lightBlue.name";

    /**
     * {@literal Lime Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_LIME_NAME = "minecraft:tile.concretePowder.lime.name";

    /**
     * {@literal Magenta Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_MAGENTA_NAME = "minecraft:tile.concretePowder.magenta.name";

    /**
     * {@literal Orange Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_ORANGE_NAME = "minecraft:tile.concretePowder.orange.name";

    /**
     * {@literal Pink Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_PINK_NAME = "minecraft:tile.concretePowder.pink.name";

    /**
     * {@literal Purple Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_PURPLE_NAME = "minecraft:tile.concretePowder.purple.name";

    /**
     * {@literal Red Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_RED_NAME = "minecraft:tile.concretePowder.red.name";

    /**
     * {@literal Light Gray Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_SILVER_NAME = "minecraft:tile.concretePowder.silver.name";

    /**
     * {@literal White Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_WHITE_NAME = "minecraft:tile.concretePowder.white.name";

    /**
     * {@literal Yellow Concrete Powder}
     */
    String MC_TILE_CONCRETEPOWDER_YELLOW_NAME = "minecraft:tile.concretePowder.yellow.name";

    /**
     * {@literal Conduit}
     */
    String MC_TILE_CONDUIT_NAME = "minecraft:tile.conduit.name";

    /**
     * {@literal Copper Bars}
     */
    String MC_TILE_COPPER_BARS_NAME = "minecraft:tile.copper_bars.name";

    /**
     * {@literal Block of Copper}
     */
    String MC_TILE_COPPER_BLOCK_NAME = "minecraft:tile.copper_block.name";

    /**
     * {@literal Copper Bulb}
     */
    String MC_TILE_COPPER_BULB_NAME = "minecraft:tile.copper_bulb.name";

    /**
     * {@literal Copper Chain}
     */
    String MC_TILE_COPPER_CHAIN_NAME = "minecraft:tile.copper_chain.name";

    /**
     * {@literal Copper Chest}
     */
    String MC_TILE_COPPER_CHEST_NAME = "minecraft:tile.copper_chest.name";

    /**
     * {@literal Copper Door}
     */
    String MC_TILE_COPPER_DOOR_NAME = "minecraft:tile.copper_door.name";

    /**
     * {@literal Copper Golem Statue}
     */
    String MC_TILE_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.copper_golem_statue.name";

    /**
     * {@literal Copper Grate}
     */
    String MC_TILE_COPPER_GRATE_NAME = "minecraft:tile.copper_grate.name";

    /**
     * {@literal Copper Lantern}
     */
    String MC_TILE_COPPER_LANTERN_NAME = "minecraft:tile.copper_lantern.name";

    /**
     * {@literal Copper Ore}
     */
    String MC_TILE_COPPER_ORE_NAME = "minecraft:tile.copper_ore.name";

    /**
     * {@literal Copper Torch}
     */
    String MC_TILE_COPPER_TORCH_NAME = "minecraft:tile.copper_torch.name";

    /**
     * {@literal Copper Trapdoor}
     */
    String MC_TILE_COPPER_TRAPDOOR_NAME = "minecraft:tile.copper_trapdoor.name";

    /**
     * {@literal Tube Coral}
     */
    String MC_TILE_CORAL_BLUE_NAME = "minecraft:tile.coral.blue.name";

    /**
     * {@literal Dead Tube Coral}
     */
    String MC_TILE_CORAL_BLUE_DEAD_NAME = "minecraft:tile.coral.blue_dead.name";

    /**
     * {@literal Brain Coral}
     */
    String MC_TILE_CORAL_PINK_NAME = "minecraft:tile.coral.pink.name";

    /**
     * {@literal Dead Brain Coral}
     */
    String MC_TILE_CORAL_PINK_DEAD_NAME = "minecraft:tile.coral.pink_dead.name";

    /**
     * {@literal Bubble Coral}
     */
    String MC_TILE_CORAL_PURPLE_NAME = "minecraft:tile.coral.purple.name";

    /**
     * {@literal Dead Bubble Coral}
     */
    String MC_TILE_CORAL_PURPLE_DEAD_NAME = "minecraft:tile.coral.purple_dead.name";

    /**
     * {@literal Fire Coral}
     */
    String MC_TILE_CORAL_RED_NAME = "minecraft:tile.coral.red.name";

    /**
     * {@literal Dead Fire Coral}
     */
    String MC_TILE_CORAL_RED_DEAD_NAME = "minecraft:tile.coral.red_dead.name";

    /**
     * {@literal Horn Coral}
     */
    String MC_TILE_CORAL_YELLOW_NAME = "minecraft:tile.coral.yellow.name";

    /**
     * {@literal Dead Horn Coral}
     */
    String MC_TILE_CORAL_YELLOW_DEAD_NAME = "minecraft:tile.coral.yellow_dead.name";

    /**
     * {@literal Tube Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_BLUE_NAME = "minecraft:tile.coral_block.blue.name";

    /**
     * {@literal Dead Tube Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_BLUE_DEAD_NAME = "minecraft:tile.coral_block.blue_dead.name";

    /**
     * {@literal Brain Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_PINK_NAME = "minecraft:tile.coral_block.pink.name";

    /**
     * {@literal Dead Brain Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_PINK_DEAD_NAME = "minecraft:tile.coral_block.pink_dead.name";

    /**
     * {@literal Bubble Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_PURPLE_NAME = "minecraft:tile.coral_block.purple.name";

    /**
     * {@literal Dead Bubble Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_PURPLE_DEAD_NAME = "minecraft:tile.coral_block.purple_dead.name";

    /**
     * {@literal Fire Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_RED_NAME = "minecraft:tile.coral_block.red.name";

    /**
     * {@literal Dead Fire Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_RED_DEAD_NAME = "minecraft:tile.coral_block.red_dead.name";

    /**
     * {@literal Horn Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_YELLOW_NAME = "minecraft:tile.coral_block.yellow.name";

    /**
     * {@literal Dead Horn Coral Block}
     */
    String MC_TILE_CORAL_BLOCK_YELLOW_DEAD_NAME = "minecraft:tile.coral_block.yellow_dead.name";

    /**
     * {@literal Tube Coral Fan}
     */
    String MC_TILE_CORAL_FAN_BLUE_FAN_NAME = "minecraft:tile.coral_fan.blue_fan.name";

    /**
     * {@literal Brain Coral Fan}
     */
    String MC_TILE_CORAL_FAN_PINK_FAN_NAME = "minecraft:tile.coral_fan.pink_fan.name";

    /**
     * {@literal Bubble Coral Fan}
     */
    String MC_TILE_CORAL_FAN_PURPLE_FAN_NAME = "minecraft:tile.coral_fan.purple_fan.name";

    /**
     * {@literal Fire Coral Fan}
     */
    String MC_TILE_CORAL_FAN_RED_FAN_NAME = "minecraft:tile.coral_fan.red_fan.name";

    /**
     * {@literal Horn Coral Fan}
     */
    String MC_TILE_CORAL_FAN_YELLOW_FAN_NAME = "minecraft:tile.coral_fan.yellow_fan.name";

    /**
     * {@literal Dead Tube Coral Fan}
     */
    String MC_TILE_CORAL_FAN_DEAD_BLUE_FAN_NAME = "minecraft:tile.coral_fan_dead.blue_fan.name";

    /**
     * {@literal Dead Brain Coral Fan}
     */
    String MC_TILE_CORAL_FAN_DEAD_PINK_FAN_NAME = "minecraft:tile.coral_fan_dead.pink_fan.name";

    /**
     * {@literal Dead Bubble Coral Fan}
     */
    String MC_TILE_CORAL_FAN_DEAD_PURPLE_FAN_NAME = "minecraft:tile.coral_fan_dead.purple_fan.name";

    /**
     * {@literal Dead Fire Coral Fan}
     */
    String MC_TILE_CORAL_FAN_DEAD_RED_FAN_NAME = "minecraft:tile.coral_fan_dead.red_fan.name";

    /**
     * {@literal Dead Horn Coral Fan}
     */
    String MC_TILE_CORAL_FAN_DEAD_YELLOW_FAN_NAME = "minecraft:tile.coral_fan_dead.yellow_fan.name";

    /**
     * {@literal Cracked Deepslate Bricks}
     */
    String MC_TILE_CRACKED_DEEPSLATE_BRICKS_NAME = "minecraft:tile.cracked_deepslate_bricks.name";

    /**
     * {@literal Cracked Deepslate Tiles}
     */
    String MC_TILE_CRACKED_DEEPSLATE_TILES_NAME = "minecraft:tile.cracked_deepslate_tiles.name";

    /**
     * {@literal Cracked Nether Bricks}
     */
    String MC_TILE_CRACKED_NETHER_BRICKS_NAME = "minecraft:tile.cracked_nether_bricks.name";

    /**
     * {@literal Cracked Polished Blackstone Bricks}
     */
    String MC_TILE_CRACKED_POLISHED_BLACKSTONE_BRICKS_NAME = "minecraft:tile.cracked_polished_blackstone_bricks.name";

    /**
     * {@literal Crafter}
     */
    String MC_TILE_CRAFTER_NAME = "minecraft:tile.crafter.name";

    /**
     * {@literal Crafting Table}
     */
    String MC_TILE_CRAFTING_TABLE_NAME = "minecraft:tile.crafting_table.name";

    /**
     * {@literal Creaking Heart}
     */
    String MC_TILE_CREAKING_HEART_NAME = "minecraft:tile.creaking_heart.name";

    /**
     * {@literal Crimson Button}
     */
    String MC_TILE_CRIMSON_BUTTON_NAME = "minecraft:tile.crimson_button.name";

    /**
     * {@literal Crimson Door}
     */
    String MC_TILE_CRIMSON_DOOR_NAME = "minecraft:tile.crimson_door.name";

    /**
     * {@literal Crimson Slab}
     */
    String MC_TILE_CRIMSON_DOUBLE_SLAB_NAME = "minecraft:tile.crimson_double_slab.name";

    /**
     * {@literal Crimson Fence}
     */
    String MC_TILE_CRIMSON_FENCE_NAME = "minecraft:tile.crimson_fence.name";

    /**
     * {@literal Crimson Fence Gate}
     */
    String MC_TILE_CRIMSON_FENCE_GATE_NAME = "minecraft:tile.crimson_fence_gate.name";

    /**
     * {@literal Crimson Fungus}
     */
    String MC_TILE_CRIMSON_FUNGUS_NAME = "minecraft:tile.crimson_fungus.name";

    /**
     * {@literal Crimson Hyphae}
     */
    String MC_TILE_CRIMSON_HYPHAE_NAME = "minecraft:tile.crimson_hyphae.name";

    /**
     * {@literal Crimson Nylium}
     */
    String MC_TILE_CRIMSON_NYLIUM_NAME = "minecraft:tile.crimson_nylium.name";

    /**
     * {@literal Crimson Planks}
     */
    String MC_TILE_CRIMSON_PLANKS_NAME = "minecraft:tile.crimson_planks.name";

    /**
     * {@literal Crimson Pressure Plate}
     */
    String MC_TILE_CRIMSON_PRESSURE_PLATE_NAME = "minecraft:tile.crimson_pressure_plate.name";

    /**
     * {@literal Crimson Roots}
     */
    String MC_TILE_CRIMSON_ROOTS_CRIMSONROOTS_NAME = "minecraft:tile.crimson_roots.crimsonRoots.name";

    /**
     * {@literal Crimson Shelf}
     */
    String MC_TILE_CRIMSON_SHELF_NAME = "minecraft:tile.crimson_shelf.name";

    /**
     * {@literal Crimson Slab}
     */
    String MC_TILE_CRIMSON_SLAB_NAME = "minecraft:tile.crimson_slab.name";

    /**
     * {@literal Crimson Stairs}
     */
    String MC_TILE_CRIMSON_STAIRS_NAME = "minecraft:tile.crimson_stairs.name";

    /**
     * {@literal Crimson Sign}
     */
    String MC_TILE_CRIMSON_STANDING_SIGN_NAME = "minecraft:tile.crimson_standing_sign.name";

    /**
     * {@literal Crimson Stem}
     */
    String MC_TILE_CRIMSON_STEM_NAME = "minecraft:tile.crimson_stem.name";

    /**
     * {@literal Crimson Trapdoor}
     */
    String MC_TILE_CRIMSON_TRAPDOOR_NAME = "minecraft:tile.crimson_trapdoor.name";

    /**
     * {@literal Crimson Sign}
     */
    String MC_TILE_CRIMSON_WALL_SIGN_NAME = "minecraft:tile.crimson_wall_sign.name";

    /**
     * {@literal Crying Obsidian}
     */
    String MC_TILE_CRYING_OBSIDIAN_NAME = "minecraft:tile.crying_obsidian.name";

    /**
     * {@literal Cut Copper}
     */
    String MC_TILE_CUT_COPPER_NAME = "minecraft:tile.cut_copper.name";

    /**
     * {@literal Cut Copper Slab}
     */
    String MC_TILE_CUT_COPPER_SLAB_NAME = "minecraft:tile.cut_copper_slab.name";

    /**
     * {@literal Cut Copper Stairs}
     */
    String MC_TILE_CUT_COPPER_STAIRS_NAME = "minecraft:tile.cut_copper_stairs.name";

    /**
     * {@literal Cyan Candle}
     */
    String MC_TILE_CYAN_CANDLE_NAME = "minecraft:tile.cyan_candle.name";

    /**
     * {@literal Cake with Cyan Candle}
     */
    String MC_TILE_CYAN_CANDLE_CAKE_NAME = "minecraft:tile.cyan_candle_cake.name";

    /**
     * {@literal Dark Oak Fence}
     */
    String MC_TILE_DARKOAKFENCE_NAME = "minecraft:tile.darkOakFence.name";

    /**
     * {@literal Dark Oak Button}
     */
    String MC_TILE_DARK_OAK_BUTTON_NAME = "minecraft:tile.dark_oak_button.name";

    /**
     * {@literal Dark Oak Fence Gate}
     */
    String MC_TILE_DARK_OAK_FENCE_GATE_NAME = "minecraft:tile.dark_oak_fence_gate.name";

    /**
     * {@literal Dark Oak Pressure Plate}
     */
    String MC_TILE_DARK_OAK_PRESSURE_PLATE_NAME = "minecraft:tile.dark_oak_pressure_plate.name";

    /**
     * {@literal Dark Oak Shelf}
     */
    String MC_TILE_DARK_OAK_SHELF_NAME = "minecraft:tile.dark_oak_shelf.name";

    /**
     * {@literal Dark Oak Stairs}
     */
    String MC_TILE_DARK_OAK_STAIRS_NAME = "minecraft:tile.dark_oak_stairs.name";

    /**
     * {@literal Dark Oak Trapdoor}
     */
    String MC_TILE_DARK_OAK_TRAPDOOR_NAME = "minecraft:tile.dark_oak_trapdoor.name";

    /**
     * {@literal Dark Prismarine Stairs}
     */
    String MC_TILE_DARK_PRISMARINE_STAIRS_NAME = "minecraft:tile.dark_prismarine_stairs.name";

    /**
     * {@literal Dark Oak Sign}
     */
    String MC_TILE_DARKOAK_STANDING_SIGN_NAME = "minecraft:tile.darkoak_standing_sign.name";

    /**
     * {@literal Daylight Detector}
     */
    String MC_TILE_DAYLIGHT_DETECTOR_NAME = "minecraft:tile.daylight_detector.name";

    /**
     * {@literal Dead Bush}
     */
    String MC_TILE_DEADBUSH_NAME = "minecraft:tile.deadbush.name";

    /**
     * {@literal Decorated Pot}
     */
    String MC_TILE_DECORATED_POT_NAME = "minecraft:tile.decorated_pot.name";

    /**
     * {@literal Deepslate}
     */
    String MC_TILE_DEEPSLATE_NAME = "minecraft:tile.deepslate.name";

    /**
     * {@literal Deepslate Brick Double Slab}
     */
    String MC_TILE_DEEPSLATE_BRICK_DOUBLE_SLAB_NAME = "minecraft:tile.deepslate_brick_double_slab.name";

    /**
     * {@literal Deepslate Brick Slab}
     */
    String MC_TILE_DEEPSLATE_BRICK_SLAB_NAME = "minecraft:tile.deepslate_brick_slab.name";

    /**
     * {@literal Deepslate Brick Stairs}
     */
    String MC_TILE_DEEPSLATE_BRICK_STAIRS_NAME = "minecraft:tile.deepslate_brick_stairs.name";

    /**
     * {@literal Deepslate Brick Wall}
     */
    String MC_TILE_DEEPSLATE_BRICK_WALL_NAME = "minecraft:tile.deepslate_brick_wall.name";

    /**
     * {@literal Deepslate Bricks}
     */
    String MC_TILE_DEEPSLATE_BRICKS_NAME = "minecraft:tile.deepslate_bricks.name";

    /**
     * {@literal Deepslate Coal Ore}
     */
    String MC_TILE_DEEPSLATE_COAL_ORE_NAME = "minecraft:tile.deepslate_coal_ore.name";

    /**
     * {@literal Deepslate Copper Ore}
     */
    String MC_TILE_DEEPSLATE_COPPER_ORE_NAME = "minecraft:tile.deepslate_copper_ore.name";

    /**
     * {@literal Deepslate Diamond Ore}
     */
    String MC_TILE_DEEPSLATE_DIAMOND_ORE_NAME = "minecraft:tile.deepslate_diamond_ore.name";

    /**
     * {@literal Deepslate Emerald Ore}
     */
    String MC_TILE_DEEPSLATE_EMERALD_ORE_NAME = "minecraft:tile.deepslate_emerald_ore.name";

    /**
     * {@literal Deepslate Gold Ore}
     */
    String MC_TILE_DEEPSLATE_GOLD_ORE_NAME = "minecraft:tile.deepslate_gold_ore.name";

    /**
     * {@literal Deepslate Iron Ore}
     */
    String MC_TILE_DEEPSLATE_IRON_ORE_NAME = "minecraft:tile.deepslate_iron_ore.name";

    /**
     * {@literal Deepslate Lapis Lazuli Ore}
     */
    String MC_TILE_DEEPSLATE_LAPIS_ORE_NAME = "minecraft:tile.deepslate_lapis_ore.name";

    /**
     * {@literal Deepslate Redstone Ore}
     */
    String MC_TILE_DEEPSLATE_REDSTONE_ORE_NAME = "minecraft:tile.deepslate_redstone_ore.name";

    /**
     * {@literal Deepslate Tile Double Slab}
     */
    String MC_TILE_DEEPSLATE_TILE_DOUBLE_SLAB_NAME = "minecraft:tile.deepslate_tile_double_slab.name";

    /**
     * {@literal Deepslate Tile Slab}
     */
    String MC_TILE_DEEPSLATE_TILE_SLAB_NAME = "minecraft:tile.deepslate_tile_slab.name";

    /**
     * {@literal Deepslate Tile Stairs}
     */
    String MC_TILE_DEEPSLATE_TILE_STAIRS_NAME = "minecraft:tile.deepslate_tile_stairs.name";

    /**
     * {@literal Deepslate Tile Wall}
     */
    String MC_TILE_DEEPSLATE_TILE_WALL_NAME = "minecraft:tile.deepslate_tile_wall.name";

    /**
     * {@literal Deepslate Tiles}
     */
    String MC_TILE_DEEPSLATE_TILES_NAME = "minecraft:tile.deepslate_tiles.name";

    /**
     * {@literal Deny}
     */
    String MC_TILE_DENY_NAME = "minecraft:tile.deny.name";

    /**
     * {@literal Detector Rail}
     */
    String MC_TILE_DETECTOR_RAIL_NAME = "minecraft:tile.detector_rail.name";

    /**
     * {@literal Block of Diamond}
     */
    String MC_TILE_DIAMOND_BLOCK_NAME = "minecraft:tile.diamond_block.name";

    /**
     * {@literal Diamond Ore}
     */
    String MC_TILE_DIAMOND_ORE_NAME = "minecraft:tile.diamond_ore.name";

    /**
     * {@literal Diorite Stairs}
     */
    String MC_TILE_DIORITE_STAIRS_NAME = "minecraft:tile.diorite_stairs.name";

    /**
     * {@literal Coarse Dirt}
     */
    String MC_TILE_DIRT_COARSE_NAME = "minecraft:tile.dirt.coarse.name";

    /**
     * {@literal Dirt}
     */
    String MC_TILE_DIRT_DEFAULT_NAME = "minecraft:tile.dirt.default.name";

    /**
     * {@literal Dirt}
     */
    String MC_TILE_DIRT_NAME = "minecraft:tile.dirt.name";

    /**
     * {@literal Rooted Dirt}
     */
    String MC_TILE_DIRT_WITH_ROOTS_NAME = "minecraft:tile.dirt_with_roots.name";

    /**
     * {@literal Dispenser}
     */
    String MC_TILE_DISPENSER_NAME = "minecraft:tile.dispenser.name";

    /**
     * {@literal Wooden Door}
     */
    String MC_TILE_DOORWOOD_NAME = "minecraft:tile.doorWood.name";

    /**
     * {@literal Large Fern}
     */
    String MC_TILE_DOUBLE_PLANT_FERN_NAME = "minecraft:tile.double_plant.fern.name";

    /**
     * {@literal Tall Grass}
     */
    String MC_TILE_DOUBLE_PLANT_GRASS_NAME = "minecraft:tile.double_plant.grass.name";

    /**
     * {@literal Plant}
     */
    String MC_TILE_DOUBLE_PLANT_NAME = "minecraft:tile.double_plant.name";

    /**
     * {@literal Peony}
     */
    String MC_TILE_DOUBLE_PLANT_PAEONIA_NAME = "minecraft:tile.double_plant.paeonia.name";

    /**
     * {@literal Rose Bush}
     */
    String MC_TILE_DOUBLE_PLANT_ROSE_NAME = "minecraft:tile.double_plant.rose.name";

    /**
     * {@literal Sunflower}
     */
    String MC_TILE_DOUBLE_PLANT_SUNFLOWER_NAME = "minecraft:tile.double_plant.sunflower.name";

    /**
     * {@literal Lilac}
     */
    String MC_TILE_DOUBLE_PLANT_SYRINGA_NAME = "minecraft:tile.double_plant.syringa.name";

    /**
     * {@literal Brick Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_BRICK_NAME = "minecraft:tile.double_stone_slab.brick.name";

    /**
     * {@literal Cobblestone Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_COBBLE_NAME = "minecraft:tile.double_stone_slab.cobble.name";

    /**
     * {@literal Stone Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_NAME = "minecraft:tile.double_stone_slab.name";

    /**
     * {@literal Nether Brick Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_NETHER_BRICK_NAME = "minecraft:tile.double_stone_slab.nether_brick.name";

    /**
     * {@literal Quartz Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_QUARTZ_NAME = "minecraft:tile.double_stone_slab.quartz.name";

    /**
     * {@literal Sandstone Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_SAND_NAME = "minecraft:tile.double_stone_slab.sand.name";

    /**
     * {@literal Stone Brick Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_SMOOTHSTONEBRICK_NAME = "minecraft:tile.double_stone_slab.smoothStoneBrick.name";

    /**
     * {@literal Stone Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_STONE_NAME = "minecraft:tile.double_stone_slab.stone.name";

    /**
     * {@literal Wooden Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB_WOOD_NAME = "minecraft:tile.double_stone_slab.wood.name";

    /**
     * {@literal Red Sandstone Slab}
     */
    String MC_TILE_DOUBLE_STONE_SLAB2_RED_SANDSTONE_NAME = "minecraft:tile.double_stone_slab2.red_sandstone.name";

    /**
     * {@literal Dragon Egg}
     */
    String MC_TILE_DRAGON_EGG_NAME = "minecraft:tile.dragon_egg.name";

    /**
     * {@literal Dried Ghast}
     */
    String MC_TILE_DRIED_GHAST_NAME = "minecraft:tile.dried_ghast.name";

    /**
     * {@literal Dried Kelp Block}
     */
    String MC_TILE_DRIED_KELP_BLOCK_NAME = "minecraft:tile.dried_kelp_block.name";

    /**
     * {@literal Dripstone Block}
     */
    String MC_TILE_DRIPSTONE_BLOCK_NAME = "minecraft:tile.dripstone_block.name";

    /**
     * {@literal Dropper}
     */
    String MC_TILE_DROPPER_NAME = "minecraft:tile.dropper.name";

    /**
     * {@literal Block of Emerald}
     */
    String MC_TILE_EMERALD_BLOCK_NAME = "minecraft:tile.emerald_block.name";

    /**
     * {@literal Emerald Ore}
     */
    String MC_TILE_EMERALD_ORE_NAME = "minecraft:tile.emerald_ore.name";

    /**
     * {@literal Enchanting Table}
     */
    String MC_TILE_ENCHANTING_TABLE_NAME = "minecraft:tile.enchanting_table.name";

    /**
     * {@literal End Stone Brick Stairs}
     */
    String MC_TILE_END_BRICK_STAIRS_NAME = "minecraft:tile.end_brick_stairs.name";

    /**
     * {@literal End Stone Bricks}
     */
    String MC_TILE_END_BRICKS_NAME = "minecraft:tile.end_bricks.name";

    /**
     * {@literal End Portal Frame}
     */
    String MC_TILE_END_PORTAL_FRAME_NAME = "minecraft:tile.end_portal_frame.name";

    /**
     * {@literal End Rod}
     */
    String MC_TILE_END_ROD_NAME = "minecraft:tile.end_rod.name";

    /**
     * {@literal End Stone}
     */
    String MC_TILE_END_STONE_NAME = "minecraft:tile.end_stone.name";

    /**
     * {@literal Ender Chest}
     */
    String MC_TILE_ENDERCHEST_NAME = "minecraft:tile.enderChest.name";

    /**
     * {@literal Ender Chest}
     */
    String MC_TILE_ENDER_CHEST_NAME = "minecraft:tile.ender_chest.name";

    /**
     * {@literal Exposed Chiseled Copper}
     */
    String MC_TILE_EXPOSED_CHISELED_COPPER_NAME = "minecraft:tile.exposed_chiseled_copper.name";

    /**
     * {@literal Exposed Copper}
     */
    String MC_TILE_EXPOSED_COPPER_NAME = "minecraft:tile.exposed_copper.name";

    /**
     * {@literal Exposed Copper Bars}
     */
    String MC_TILE_EXPOSED_COPPER_BARS_NAME = "minecraft:tile.exposed_copper_bars.name";

    /**
     * {@literal Exposed Copper Bulb}
     */
    String MC_TILE_EXPOSED_COPPER_BULB_NAME = "minecraft:tile.exposed_copper_bulb.name";

    /**
     * {@literal Exposed Copper Chain}
     */
    String MC_TILE_EXPOSED_COPPER_CHAIN_NAME = "minecraft:tile.exposed_copper_chain.name";

    /**
     * {@literal Exposed Copper Chest}
     */
    String MC_TILE_EXPOSED_COPPER_CHEST_NAME = "minecraft:tile.exposed_copper_chest.name";

    /**
     * {@literal Exposed Copper Door}
     */
    String MC_TILE_EXPOSED_COPPER_DOOR_NAME = "minecraft:tile.exposed_copper_door.name";

    /**
     * {@literal Exposed Copper Golem Statue}
     */
    String MC_TILE_EXPOSED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.exposed_copper_golem_statue.name";

    /**
     * {@literal Exposed Copper Grate}
     */
    String MC_TILE_EXPOSED_COPPER_GRATE_NAME = "minecraft:tile.exposed_copper_grate.name";

    /**
     * {@literal Exposed Copper Lantern}
     */
    String MC_TILE_EXPOSED_COPPER_LANTERN_NAME = "minecraft:tile.exposed_copper_lantern.name";

    /**
     * {@literal Exposed Copper Trapdoor}
     */
    String MC_TILE_EXPOSED_COPPER_TRAPDOOR_NAME = "minecraft:tile.exposed_copper_trapdoor.name";

    /**
     * {@literal Exposed Cut Copper}
     */
    String MC_TILE_EXPOSED_CUT_COPPER_NAME = "minecraft:tile.exposed_cut_copper.name";

    /**
     * {@literal Exposed Cut Copper Slab}
     */
    String MC_TILE_EXPOSED_CUT_COPPER_SLAB_NAME = "minecraft:tile.exposed_cut_copper_slab.name";

    /**
     * {@literal Exposed Cut Copper Stairs}
     */
    String MC_TILE_EXPOSED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.exposed_cut_copper_stairs.name";

    /**
     * {@literal Exposed Lightning Rod}
     */
    String MC_TILE_EXPOSED_LIGHTNING_ROD_NAME = "minecraft:tile.exposed_lightning_rod.name";

    /**
     * {@literal Farmland}
     */
    String MC_TILE_FARMLAND_NAME = "minecraft:tile.farmland.name";

    /**
     * {@literal Oak Fence}
     */
    String MC_TILE_FENCE_NAME = "minecraft:tile.fence.name";

    /**
     * {@literal Oak Fence Gate}
     */
    String MC_TILE_FENCE_GATE_NAME = "minecraft:tile.fence_gate.name";

    /**
     * {@literal Fire}
     */
    String MC_TILE_FIRE_NAME = "minecraft:tile.fire.name";

    /**
     * {@literal Firefly Bush}
     */
    String MC_TILE_FIREFLY_BUSH_NAME = "minecraft:tile.firefly_bush.name";

    /**
     * {@literal Fletching Table}
     */
    String MC_TILE_FLETCHING_TABLE_NAME = "minecraft:tile.fletching_table.name";

    /**
     * {@literal Flowering Azalea}
     */
    String MC_TILE_FLOWERING_AZALEA_NAME = "minecraft:tile.flowering_azalea.name";

    /**
     * {@literal Lava}
     */
    String MC_TILE_FLOWING_LAVA_NAME = "minecraft:tile.flowing_lava.name";

    /**
     * {@literal Water}
     */
    String MC_TILE_FLOWING_WATER_NAME = "minecraft:tile.flowing_water.name";

    /**
     * {@literal Frogspawn}
     */
    String MC_TILE_FROG_SPAWN_NAME = "minecraft:tile.frog_spawn.name";

    /**
     * {@literal Frosted Ice}
     */
    String MC_TILE_FROSTED_ICE_NAME = "minecraft:tile.frosted_ice.name";

    /**
     * {@literal Furnace}
     */
    String MC_TILE_FURNACE_NAME = "minecraft:tile.furnace.name";

    /**
     * {@literal Gilded Blackstone}
     */
    String MC_TILE_GILDED_BLACKSTONE_NAME = "minecraft:tile.gilded_blackstone.name";

    /**
     * {@literal Glass}
     */
    String MC_TILE_GLASS_NAME = "minecraft:tile.glass.name";

    /**
     * {@literal Glass Pane}
     */
    String MC_TILE_GLASS_PANE_NAME = "minecraft:tile.glass_pane.name";

    /**
     * {@literal Black Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_BLACK_NAME = "minecraft:tile.glazedTerracotta.black.name";

    /**
     * {@literal Blue Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_BLUE_NAME = "minecraft:tile.glazedTerracotta.blue.name";

    /**
     * {@literal Brown Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_BROWN_NAME = "minecraft:tile.glazedTerracotta.brown.name";

    /**
     * {@literal Cyan Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_CYAN_NAME = "minecraft:tile.glazedTerracotta.cyan.name";

    /**
     * {@literal Gray Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_GRAY_NAME = "minecraft:tile.glazedTerracotta.gray.name";

    /**
     * {@literal Green Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_GREEN_NAME = "minecraft:tile.glazedTerracotta.green.name";

    /**
     * {@literal Light Blue Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_LIGHT_BLUE_NAME = "minecraft:tile.glazedTerracotta.light_blue.name";

    /**
     * {@literal Lime Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_LIME_NAME = "minecraft:tile.glazedTerracotta.lime.name";

    /**
     * {@literal Magenta Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_MAGENTA_NAME = "minecraft:tile.glazedTerracotta.magenta.name";

    /**
     * {@literal Orange Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_ORANGE_NAME = "minecraft:tile.glazedTerracotta.orange.name";

    /**
     * {@literal Pink Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_PINK_NAME = "minecraft:tile.glazedTerracotta.pink.name";

    /**
     * {@literal Purple Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_PURPLE_NAME = "minecraft:tile.glazedTerracotta.purple.name";

    /**
     * {@literal Red Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_RED_NAME = "minecraft:tile.glazedTerracotta.red.name";

    /**
     * {@literal Light Gray Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_SILVER_NAME = "minecraft:tile.glazedTerracotta.silver.name";

    /**
     * {@literal White Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_WHITE_NAME = "minecraft:tile.glazedTerracotta.white.name";

    /**
     * {@literal Yellow Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTA_YELLOW_NAME = "minecraft:tile.glazedTerracotta.yellow.name";

    /**
     * {@literal Black Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTABLACK_NAME = "minecraft:tile.glazedTerracottaBlack.name";

    /**
     * {@literal Blue Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTABLUE_NAME = "minecraft:tile.glazedTerracottaBlue.name";

    /**
     * {@literal Brown Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTABROWN_NAME = "minecraft:tile.glazedTerracottaBrown.name";

    /**
     * {@literal Cyan Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTACYAN_NAME = "minecraft:tile.glazedTerracottaCyan.name";

    /**
     * {@literal Gray Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAGRAY_NAME = "minecraft:tile.glazedTerracottaGray.name";

    /**
     * {@literal Green Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAGREEN_NAME = "minecraft:tile.glazedTerracottaGreen.name";

    /**
     * {@literal Light Blue Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTALIGHTBLUE_NAME = "minecraft:tile.glazedTerracottaLightBlue.name";

    /**
     * {@literal Lime Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTALIME_NAME = "minecraft:tile.glazedTerracottaLime.name";

    /**
     * {@literal Magenta Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAMAGENTA_NAME = "minecraft:tile.glazedTerracottaMagenta.name";

    /**
     * {@literal Orange Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAORANGE_NAME = "minecraft:tile.glazedTerracottaOrange.name";

    /**
     * {@literal Pink Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAPINK_NAME = "minecraft:tile.glazedTerracottaPink.name";

    /**
     * {@literal Purple Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAPURPLE_NAME = "minecraft:tile.glazedTerracottaPurple.name";

    /**
     * {@literal Red Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTARED_NAME = "minecraft:tile.glazedTerracottaRed.name";

    /**
     * {@literal Light Gray Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTASILVER_NAME = "minecraft:tile.glazedTerracottaSilver.name";

    /**
     * {@literal White Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAWHITE_NAME = "minecraft:tile.glazedTerracottaWhite.name";

    /**
     * {@literal Yellow Glazed Terracotta}
     */
    String MC_TILE_GLAZEDTERRACOTTAYELLOW_NAME = "minecraft:tile.glazedTerracottaYellow.name";

    /**
     * {@literal Glow Lichen}
     */
    String MC_TILE_GLOW_LICHEN_NAME = "minecraft:tile.glow_lichen.name";

    /**
     * {@literal Glowstone}
     */
    String MC_TILE_GLOWSTONE_NAME = "minecraft:tile.glowstone.name";

    /**
     * {@literal Block of Gold}
     */
    String MC_TILE_GOLD_BLOCK_NAME = "minecraft:tile.gold_block.name";

    /**
     * {@literal Gold Ore}
     */
    String MC_TILE_GOLD_ORE_NAME = "minecraft:tile.gold_ore.name";

    /**
     * {@literal Powered Rail}
     */
    String MC_TILE_GOLDEN_RAIL_NAME = "minecraft:tile.golden_rail.name";

    /**
     * {@literal Granite Stairs}
     */
    String MC_TILE_GRANITE_STAIRS_NAME = "minecraft:tile.granite_stairs.name";

    /**
     * {@literal Grass Block}
     */
    String MC_TILE_GRASS_NAME = "minecraft:tile.grass.name";

    /**
     * {@literal Dirt Path}
     */
    String MC_TILE_GRASS_PATH_NAME = "minecraft:tile.grass_path.name";

    /**
     * {@literal Gravel}
     */
    String MC_TILE_GRAVEL_NAME = "minecraft:tile.gravel.name";

    /**
     * {@literal Gray Candle}
     */
    String MC_TILE_GRAY_CANDLE_NAME = "minecraft:tile.gray_candle.name";

    /**
     * {@literal Cake with Gray Candle}
     */
    String MC_TILE_GRAY_CANDLE_CAKE_NAME = "minecraft:tile.gray_candle_cake.name";

    /**
     * {@literal Green Candle}
     */
    String MC_TILE_GREEN_CANDLE_NAME = "minecraft:tile.green_candle.name";

    /**
     * {@literal Cake with Green Candle}
     */
    String MC_TILE_GREEN_CANDLE_CAKE_NAME = "minecraft:tile.green_candle_cake.name";

    /**
     * {@literal Grindstone}
     */
    String MC_TILE_GRINDSTONE_NAME = "minecraft:tile.grindstone.name";

    /**
     * {@literal Hanging Roots}
     */
    String MC_TILE_HANGING_ROOTS_NAME = "minecraft:tile.hanging_roots.name";

    /**
     * {@literal Terracotta}
     */
    String MC_TILE_HARDENED_CLAY_NAME = "minecraft:tile.hardened_clay.name";

    /**
     * {@literal Hay Bale}
     */
    String MC_TILE_HAY_BLOCK_NAME = "minecraft:tile.hay_block.name";

    /**
     * {@literal Heavy Core}
     */
    String MC_TILE_HEAVY_CORE_NAME = "minecraft:tile.heavy_core.name";

    /**
     * {@literal Heavy Weighted Pressure Plate}
     */
    String MC_TILE_HEAVY_WEIGHTED_PRESSURE_PLATE_NAME = "minecraft:tile.heavy_weighted_pressure_plate.name";

    /**
     * {@literal Honey Block}
     */
    String MC_TILE_HONEY_BLOCK_NAME = "minecraft:tile.honey_block.name";

    /**
     * {@literal Honeycomb Block}
     */
    String MC_TILE_HONEYCOMB_BLOCK_NAME = "minecraft:tile.honeycomb_block.name";

    /**
     * {@literal Hopper}
     */
    String MC_TILE_HOPPER_NAME = "minecraft:tile.hopper.name";

    /**
     * {@literal Ice}
     */
    String MC_TILE_ICE_NAME = "minecraft:tile.ice.name";

    /**
     * {@literal Infested Deepslate}
     */
    String MC_TILE_INFESTED_DEEPSLATE_NAME = "minecraft:tile.infested_deepslate.name";

    /**
     * {@literal Invisible Bedrock}
     */
    String MC_TILE_INVISIBLEBEDROCK_NAME = "minecraft:tile.invisibleBedrock.name";

    /**
     * {@literal Iron Bars}
     */
    String MC_TILE_IRON_BARS_NAME = "minecraft:tile.iron_bars.name";

    /**
     * {@literal Block of Iron}
     */
    String MC_TILE_IRON_BLOCK_NAME = "minecraft:tile.iron_block.name";

    /**
     * {@literal Iron Chain}
     */
    String MC_TILE_IRON_CHAIN_NAME = "minecraft:tile.iron_chain.name";

    /**
     * {@literal Iron Door}
     */
    String MC_TILE_IRON_DOOR_NAME = "minecraft:tile.iron_door.name";

    /**
     * {@literal Iron Ore}
     */
    String MC_TILE_IRON_ORE_NAME = "minecraft:tile.iron_ore.name";

    /**
     * {@literal Iron Trapdoor}
     */
    String MC_TILE_IRON_TRAPDOOR_NAME = "minecraft:tile.iron_trapdoor.name";

    /**
     * {@literal Jigsaw Block}
     */
    String MC_TILE_JIGSAW_NAME = "minecraft:tile.jigsaw.name";

    /**
     * {@literal Jukebox}
     */
    String MC_TILE_JUKEBOX_NAME = "minecraft:tile.jukebox.name";

    /**
     * {@literal Jungle Fence}
     */
    String MC_TILE_JUNGLEFENCE_NAME = "minecraft:tile.jungleFence.name";

    /**
     * {@literal Jungle Button}
     */
    String MC_TILE_JUNGLE_BUTTON_NAME = "minecraft:tile.jungle_button.name";

    /**
     * {@literal Jungle Fence Gate}
     */
    String MC_TILE_JUNGLE_FENCE_GATE_NAME = "minecraft:tile.jungle_fence_gate.name";

    /**
     * {@literal Jungle Pressure Plate}
     */
    String MC_TILE_JUNGLE_PRESSURE_PLATE_NAME = "minecraft:tile.jungle_pressure_plate.name";

    /**
     * {@literal Jungle Shelf}
     */
    String MC_TILE_JUNGLE_SHELF_NAME = "minecraft:tile.jungle_shelf.name";

    /**
     * {@literal Jungle Stairs}
     */
    String MC_TILE_JUNGLE_STAIRS_NAME = "minecraft:tile.jungle_stairs.name";

    /**
     * {@literal Jungle Sign}
     */
    String MC_TILE_JUNGLE_STANDING_SIGN_NAME = "minecraft:tile.jungle_standing_sign.name";

    /**
     * {@literal Jungle Trapdoor}
     */
    String MC_TILE_JUNGLE_TRAPDOOR_NAME = "minecraft:tile.jungle_trapdoor.name";

    /**
     * {@literal Ladder}
     */
    String MC_TILE_LADDER_NAME = "minecraft:tile.ladder.name";

    /**
     * {@literal Lantern}
     */
    String MC_TILE_LANTERN_NAME = "minecraft:tile.lantern.name";

    /**
     * {@literal Block of Lapis Lazuli}
     */
    String MC_TILE_LAPIS_BLOCK_NAME = "minecraft:tile.lapis_block.name";

    /**
     * {@literal Lapis Lazuli Ore}
     */
    String MC_TILE_LAPIS_ORE_NAME = "minecraft:tile.lapis_ore.name";

    /**
     * {@literal Large Amethyst Bud}
     */
    String MC_TILE_LARGE_AMETHYST_BUD_NAME = "minecraft:tile.large_amethyst_bud.name";

    /**
     * {@literal Lava}
     */
    String MC_TILE_LAVA_NAME = "minecraft:tile.lava.name";

    /**
     * {@literal Leaf Litter}
     */
    String MC_TILE_LEAF_LITTER_NAME = "minecraft:tile.leaf_litter.name";

    /**
     * {@literal Acacia Leaves}
     */
    String MC_TILE_LEAVES_ACACIA_NAME = "minecraft:tile.leaves.acacia.name";

    /**
     * {@literal Dark Oak Leaves}
     */
    String MC_TILE_LEAVES_BIG_OAK_NAME = "minecraft:tile.leaves.big_oak.name";

    /**
     * {@literal Birch Leaves}
     */
    String MC_TILE_LEAVES_BIRCH_NAME = "minecraft:tile.leaves.birch.name";

    /**
     * {@literal Jungle Leaves}
     */
    String MC_TILE_LEAVES_JUNGLE_NAME = "minecraft:tile.leaves.jungle.name";

    /**
     * {@literal Leaves}
     */
    String MC_TILE_LEAVES_NAME = "minecraft:tile.leaves.name";

    /**
     * {@literal Oak Leaves}
     */
    String MC_TILE_LEAVES_OAK_NAME = "minecraft:tile.leaves.oak.name";

    /**
     * {@literal Spruce Leaves}
     */
    String MC_TILE_LEAVES_SPRUCE_NAME = "minecraft:tile.leaves.spruce.name";

    /**
     * {@literal Acacia Leaves}
     */
    String MC_TILE_LEAVES2_ACACIA_NAME = "minecraft:tile.leaves2.acacia.name";

    /**
     * {@literal Dark Oak Leaves}
     */
    String MC_TILE_LEAVES2_BIG_OAK_NAME = "minecraft:tile.leaves2.big_oak.name";

    /**
     * {@literal Lectern}
     */
    String MC_TILE_LECTERN_NAME = "minecraft:tile.lectern.name";

    /**
     * {@literal Lever}
     */
    String MC_TILE_LEVER_NAME = "minecraft:tile.lever.name";

    /**
     * {@literal Light}
     */
    String MC_TILE_LIGHT_BLOCK_NAME = "minecraft:tile.light_block.name";

    /**
     * {@literal Light Blue Candle}
     */
    String MC_TILE_LIGHT_BLUE_CANDLE_NAME = "minecraft:tile.light_blue_candle.name";

    /**
     * {@literal Cake with Light Blue Candle}
     */
    String MC_TILE_LIGHT_BLUE_CANDLE_CAKE_NAME = "minecraft:tile.light_blue_candle_cake.name";

    /**
     * {@literal Light Gray Candle}
     */
    String MC_TILE_LIGHT_GRAY_CANDLE_NAME = "minecraft:tile.light_gray_candle.name";

    /**
     * {@literal Cake with Light Gray Candle}
     */
    String MC_TILE_LIGHT_GRAY_CANDLE_CAKE_NAME = "minecraft:tile.light_gray_candle_cake.name";

    /**
     * {@literal Light Weighted Pressure Plate}
     */
    String MC_TILE_LIGHT_WEIGHTED_PRESSURE_PLATE_NAME = "minecraft:tile.light_weighted_pressure_plate.name";

    /**
     * {@literal Lightning Rod}
     */
    String MC_TILE_LIGHTNING_ROD_NAME = "minecraft:tile.lightning_rod.name";

    /**
     * {@literal Lime Candle}
     */
    String MC_TILE_LIME_CANDLE_NAME = "minecraft:tile.lime_candle.name";

    /**
     * {@literal Cake with Lime Candle}
     */
    String MC_TILE_LIME_CANDLE_CAKE_NAME = "minecraft:tile.lime_candle_cake.name";

    /**
     * {@literal Jack o'Lantern}
     */
    String MC_TILE_LIT_PUMPKIN_NAME = "minecraft:tile.lit_pumpkin.name";

    /**
     * {@literal Locked chest}
     */
    String MC_TILE_LOCKEDCHEST_NAME = "minecraft:tile.lockedchest.name";

    /**
     * {@literal Lodestone}
     */
    String MC_TILE_LODESTONE_NAME = "minecraft:tile.lodestone.name";

    /**
     * {@literal Acacia Log}
     */
    String MC_TILE_LOG_ACACIA_NAME = "minecraft:tile.log.acacia.name";

    /**
     * {@literal Dark Oak Log}
     */
    String MC_TILE_LOG_BIG_OAK_NAME = "minecraft:tile.log.big_oak.name";

    /**
     * {@literal Birch Log}
     */
    String MC_TILE_LOG_BIRCH_NAME = "minecraft:tile.log.birch.name";

    /**
     * {@literal Jungle Log}
     */
    String MC_TILE_LOG_JUNGLE_NAME = "minecraft:tile.log.jungle.name";

    /**
     * {@literal Log}
     */
    String MC_TILE_LOG_NAME = "minecraft:tile.log.name";

    /**
     * {@literal Oak Log}
     */
    String MC_TILE_LOG_OAK_NAME = "minecraft:tile.log.oak.name";

    /**
     * {@literal Spruce Log}
     */
    String MC_TILE_LOG_SPRUCE_NAME = "minecraft:tile.log.spruce.name";

    /**
     * {@literal Loom}
     */
    String MC_TILE_LOOM_NAME = "minecraft:tile.loom.name";

    /**
     * {@literal Magenta Candle}
     */
    String MC_TILE_MAGENTA_CANDLE_NAME = "minecraft:tile.magenta_candle.name";

    /**
     * {@literal Cake with Magenta Candle}
     */
    String MC_TILE_MAGENTA_CANDLE_CAKE_NAME = "minecraft:tile.magenta_candle_cake.name";

    /**
     * {@literal Magma Block}
     */
    String MC_TILE_MAGMA_NAME = "minecraft:tile.magma.name";

    /**
     * {@literal Mangrove Button}
     */
    String MC_TILE_MANGROVE_BUTTON_NAME = "minecraft:tile.mangrove_button.name";

    /**
     * {@literal Mangrove Fence}
     */
    String MC_TILE_MANGROVE_FENCE_NAME = "minecraft:tile.mangrove_fence.name";

    /**
     * {@literal Mangrove Fence Gate}
     */
    String MC_TILE_MANGROVE_FENCE_GATE_NAME = "minecraft:tile.mangrove_fence_gate.name";

    /**
     * {@literal Mangrove Leaves}
     */
    String MC_TILE_MANGROVE_LEAVES_NAME = "minecraft:tile.mangrove_leaves.name";

    /**
     * {@literal Mangrove Log}
     */
    String MC_TILE_MANGROVE_LOG_NAME = "minecraft:tile.mangrove_log.name";

    /**
     * {@literal Mangrove Planks}
     */
    String MC_TILE_MANGROVE_PLANKS_NAME = "minecraft:tile.mangrove_planks.name";

    /**
     * {@literal Mangrove Pressure Plate}
     */
    String MC_TILE_MANGROVE_PRESSURE_PLATE_NAME = "minecraft:tile.mangrove_pressure_plate.name";

    /**
     * {@literal Mangrove Propagule}
     */
    String MC_TILE_MANGROVE_PROPAGULE_NAME = "minecraft:tile.mangrove_propagule.name";

    /**
     * {@literal Mangrove Roots}
     */
    String MC_TILE_MANGROVE_ROOTS_NAME = "minecraft:tile.mangrove_roots.name";

    /**
     * {@literal Mangrove Shelf}
     */
    String MC_TILE_MANGROVE_SHELF_NAME = "minecraft:tile.mangrove_shelf.name";

    /**
     * {@literal Mangrove Slab}
     */
    String MC_TILE_MANGROVE_SLAB_NAME = "minecraft:tile.mangrove_slab.name";

    /**
     * {@literal Mangrove Stairs}
     */
    String MC_TILE_MANGROVE_STAIRS_NAME = "minecraft:tile.mangrove_stairs.name";

    /**
     * {@literal Mangrove Trapdoor}
     */
    String MC_TILE_MANGROVE_TRAPDOOR_NAME = "minecraft:tile.mangrove_trapdoor.name";

    /**
     * {@literal Mangrove Wood}
     */
    String MC_TILE_MANGROVE_WOOD_NAME = "minecraft:tile.mangrove_wood.name";

    /**
     * {@literal Medium Amethyst Bud}
     */
    String MC_TILE_MEDIUM_AMETHYST_BUD_NAME = "minecraft:tile.medium_amethyst_bud.name";

    /**
     * {@literal Melon}
     */
    String MC_TILE_MELON_BLOCK_NAME = "minecraft:tile.melon_block.name";

    /**
     * {@literal Monster Spawner}
     */
    String MC_TILE_MOB_SPAWNER_NAME = "minecraft:tile.mob_spawner.name";

    /**
     * {@literal Infested Stone Bricks}
     */
    String MC_TILE_MONSTER_EGG_BRICK_NAME = "minecraft:tile.monster_egg.brick.name";

    /**
     * {@literal Infested Chiseled Stone Brick}
     */
    String MC_TILE_MONSTER_EGG_CHISELEDBRICK_NAME = "minecraft:tile.monster_egg.chiseledbrick.name";

    /**
     * {@literal Infested Cobblestone}
     */
    String MC_TILE_MONSTER_EGG_COBBLE_NAME = "minecraft:tile.monster_egg.cobble.name";

    /**
     * {@literal Infested Cracked Stone Brick}
     */
    String MC_TILE_MONSTER_EGG_CRACKEDBRICK_NAME = "minecraft:tile.monster_egg.crackedbrick.name";

    /**
     * {@literal Infested Mossy Stone Brick}
     */
    String MC_TILE_MONSTER_EGG_MOSSYBRICK_NAME = "minecraft:tile.monster_egg.mossybrick.name";

    /**
     * {@literal Infested Stone}
     */
    String MC_TILE_MONSTER_EGG_NAME = "minecraft:tile.monster_egg.name";

    /**
     * {@literal Infested Stone}
     */
    String MC_TILE_MONSTER_EGG_STONE_NAME = "minecraft:tile.monster_egg.stone.name";

    /**
     * {@literal Moss Block}
     */
    String MC_TILE_MOSS_BLOCK_NAME = "minecraft:tile.moss_block.name";

    /**
     * {@literal Moss Carpet}
     */
    String MC_TILE_MOSS_CARPET_NAME = "minecraft:tile.moss_carpet.name";

    /**
     * {@literal Mossy Cobblestone}
     */
    String MC_TILE_MOSSY_COBBLESTONE_NAME = "minecraft:tile.mossy_cobblestone.name";

    /**
     * {@literal Mossy Cobblestone Stairs}
     */
    String MC_TILE_MOSSY_COBBLESTONE_STAIRS_NAME = "minecraft:tile.mossy_cobblestone_stairs.name";

    /**
     * {@literal Mossy Stone Brick Stairs}
     */
    String MC_TILE_MOSSY_STONE_BRICK_STAIRS_NAME = "minecraft:tile.mossy_stone_brick_stairs.name";

    /**
     * {@literal Mud}
     */
    String MC_TILE_MUD_NAME = "minecraft:tile.mud.name";

    /**
     * {@literal Mud Brick Double Slab}
     */
    String MC_TILE_MUD_BRICK_DOUBLE_SLAB = "minecraft:tile.mud_brick_double_slab";

    /**
     * {@literal Mud Brick Slab}
     */
    String MC_TILE_MUD_BRICK_SLAB_NAME = "minecraft:tile.mud_brick_slab.name";

    /**
     * {@literal Mud Brick Stairs}
     */
    String MC_TILE_MUD_BRICK_STAIRS_NAME = "minecraft:tile.mud_brick_stairs.name";

    /**
     * {@literal Mud Brick Wall}
     */
    String MC_TILE_MUD_BRICK_WALL_NAME = "minecraft:tile.mud_brick_wall.name";

    /**
     * {@literal Mud Bricks}
     */
    String MC_TILE_MUD_BRICKS_NAME = "minecraft:tile.mud_bricks.name";

    /**
     * {@literal Muddy Mangrove Roots}
     */
    String MC_TILE_MUDDY_MANGROVE_ROOTS_NAME = "minecraft:tile.muddy_mangrove_roots.name";

    /**
     * {@literal Mushroom}
     */
    String MC_TILE_MUSHROOM_NAME = "minecraft:tile.mushroom.name";

    /**
     * {@literal Mycelium}
     */
    String MC_TILE_MYCELIUM_NAME = "minecraft:tile.mycelium.name";

    /**
     * {@literal Nether Bricks}
     */
    String MC_TILE_NETHER_BRICK_NAME = "minecraft:tile.nether_brick.name";

    /**
     * {@literal Nether Brick Fence}
     */
    String MC_TILE_NETHER_BRICK_FENCE_NAME = "minecraft:tile.nether_brick_fence.name";

    /**
     * {@literal Nether Brick Stairs}
     */
    String MC_TILE_NETHER_BRICK_STAIRS_NAME = "minecraft:tile.nether_brick_stairs.name";

    /**
     * {@literal Nether Gold Ore}
     */
    String MC_TILE_NETHER_GOLD_ORE_NAME = "minecraft:tile.nether_gold_ore.name";

    /**
     * {@literal Nether Sprouts}
     */
    String MC_TILE_NETHER_SPROUTS_NAME = "minecraft:tile.nether_sprouts.name";

    /**
     * {@literal Nether Wart}
     */
    String MC_TILE_NETHER_WART_NAME = "minecraft:tile.nether_wart.name";

    /**
     * {@literal Nether Wart Block}
     */
    String MC_TILE_NETHER_WART_BLOCK_NAME = "minecraft:tile.nether_wart_block.name";

    /**
     * {@literal Block of Netherite}
     */
    String MC_TILE_NETHERITE_BLOCK_NAME = "minecraft:tile.netherite_block.name";

    /**
     * {@literal Netherrack}
     */
    String MC_TILE_NETHERRACK_NAME = "minecraft:tile.netherrack.name";

    /**
     * {@literal Active!}
     */
    String MC_TILE_NETHERREACTOR_ACTIVE = "minecraft:tile.netherreactor.active";

    /**
     * {@literal The nether reactor needs to be built lower down.}
     */
    String MC_TILE_NETHERREACTOR_BUILTTOOHIGH = "minecraft:tile.netherreactor.builtTooHigh";

    /**
     * {@literal The nether reactor needs to be built higher up.}
     */
    String MC_TILE_NETHERREACTOR_BUILTTOOLOW = "minecraft:tile.netherreactor.builtTooLow";

    /**
     * {@literal Nether Reactor Core}
     */
    String MC_TILE_NETHERREACTOR_NAME = "minecraft:tile.netherreactor.name";

    /**
     * {@literal All players need to be close to the reactor.}
     */
    String MC_TILE_NETHERREACTOR_PLAYERSTOOFAR = "minecraft:tile.netherreactor.playersTooFar";

    /**
     * {@literal Not the correct pattern!}
     */
    String MC_TILE_NETHERREACTOR_WRONGPATTERN = "minecraft:tile.netherreactor.wrongPattern";

    /**
     * {@literal Stone Stairs}
     */
    String MC_TILE_NORMAL_STONE_STAIRS_NAME = "minecraft:tile.normal_stone_stairs.name";

    /**
     * {@literal Note Block}
     */
    String MC_TILE_NOTEBLOCK_NAME = "minecraft:tile.noteblock.name";

    /**
     * {@literal Oak Shelf}
     */
    String MC_TILE_OAK_SHELF_NAME = "minecraft:tile.oak_shelf.name";

    /**
     * {@literal Oak Stairs}
     */
    String MC_TILE_OAK_STAIRS_NAME = "minecraft:tile.oak_stairs.name";

    /**
     * {@literal Observer}
     */
    String MC_TILE_OBSERVER_NAME = "minecraft:tile.observer.name";

    /**
     * {@literal Obsidian}
     */
    String MC_TILE_OBSIDIAN_NAME = "minecraft:tile.obsidian.name";

    /**
     * {@literal Ochre Froglight}
     */
    String MC_TILE_OCHRE_FROGLIGHT_NAME = "minecraft:tile.ochre_froglight.name";

    /**
     * {@literal Open Eyeblossom}
     */
    String MC_TILE_OPEN_EYEBLOSSOM_NAME = "minecraft:tile.open_eyeblossom.name";

    /**
     * {@literal Orange Candle}
     */
    String MC_TILE_ORANGE_CANDLE_NAME = "minecraft:tile.orange_candle.name";

    /**
     * {@literal Cake with Orange Candle}
     */
    String MC_TILE_ORANGE_CANDLE_CAKE_NAME = "minecraft:tile.orange_candle_cake.name";

    /**
     * {@literal Ruby Ore}
     */
    String MC_TILE_ORERUBY_NAME = "minecraft:tile.oreRuby.name";

    /**
     * {@literal Oxidized Chiseled Copper}
     */
    String MC_TILE_OXIDIZED_CHISELED_COPPER_NAME = "minecraft:tile.oxidized_chiseled_copper.name";

    /**
     * {@literal Oxidized Copper}
     */
    String MC_TILE_OXIDIZED_COPPER_NAME = "minecraft:tile.oxidized_copper.name";

    /**
     * {@literal Oxidized Copper Bars}
     */
    String MC_TILE_OXIDIZED_COPPER_BARS_NAME = "minecraft:tile.oxidized_copper_bars.name";

    /**
     * {@literal Oxidized Copper Bulb}
     */
    String MC_TILE_OXIDIZED_COPPER_BULB_NAME = "minecraft:tile.oxidized_copper_bulb.name";

    /**
     * {@literal Oxidized Copper Chain}
     */
    String MC_TILE_OXIDIZED_COPPER_CHAIN_NAME = "minecraft:tile.oxidized_copper_chain.name";

    /**
     * {@literal Oxidized Copper Chest}
     */
    String MC_TILE_OXIDIZED_COPPER_CHEST_NAME = "minecraft:tile.oxidized_copper_chest.name";

    /**
     * {@literal Oxidized Copper Door}
     */
    String MC_TILE_OXIDIZED_COPPER_DOOR_NAME = "minecraft:tile.oxidized_copper_door.name";

    /**
     * {@literal Oxidized Copper Golem Statue}
     */
    String MC_TILE_OXIDIZED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.oxidized_copper_golem_statue.name";

    /**
     * {@literal Oxidized Copper Grate}
     */
    String MC_TILE_OXIDIZED_COPPER_GRATE_NAME = "minecraft:tile.oxidized_copper_grate.name";

    /**
     * {@literal Oxidized Copper Lantern}
     */
    String MC_TILE_OXIDIZED_COPPER_LANTERN_NAME = "minecraft:tile.oxidized_copper_lantern.name";

    /**
     * {@literal Oxidized Copper Trapdoor}
     */
    String MC_TILE_OXIDIZED_COPPER_TRAPDOOR_NAME = "minecraft:tile.oxidized_copper_trapdoor.name";

    /**
     * {@literal Oxidized Cut Copper}
     */
    String MC_TILE_OXIDIZED_CUT_COPPER_NAME = "minecraft:tile.oxidized_cut_copper.name";

    /**
     * {@literal Oxidized Cut Copper Slab}
     */
    String MC_TILE_OXIDIZED_CUT_COPPER_SLAB_NAME = "minecraft:tile.oxidized_cut_copper_slab.name";

    /**
     * {@literal Oxidized Cut Copper Stairs}
     */
    String MC_TILE_OXIDIZED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.oxidized_cut_copper_stairs.name";

    /**
     * {@literal Oxidized Lightning Rod}
     */
    String MC_TILE_OXIDIZED_LIGHTNING_ROD_NAME = "minecraft:tile.oxidized_lightning_rod.name";

    /**
     * {@literal Packed Ice}
     */
    String MC_TILE_PACKED_ICE_NAME = "minecraft:tile.packed_ice.name";

    /**
     * {@literal Packed Mud}
     */
    String MC_TILE_PACKED_MUD_NAME = "minecraft:tile.packed_mud.name";

    /**
     * {@literal Pale Hanging Moss}
     */
    String MC_TILE_PALE_HANGING_MOSS_NAME = "minecraft:tile.pale_hanging_moss.name";

    /**
     * {@literal Pale Moss Block}
     */
    String MC_TILE_PALE_MOSS_BLOCK_NAME = "minecraft:tile.pale_moss_block.name";

    /**
     * {@literal Pale Moss Carpet}
     */
    String MC_TILE_PALE_MOSS_CARPET_NAME = "minecraft:tile.pale_moss_carpet.name";

    /**
     * {@literal Pale Oak Button}
     */
    String MC_TILE_PALE_OAK_BUTTON_NAME = "minecraft:tile.pale_oak_button.name";

    /**
     * {@literal Pale Oak Double Slab}
     */
    String MC_TILE_PALE_OAK_DOUBLE_SLAB_NAME = "minecraft:tile.pale_oak_double_slab.name";

    /**
     * {@literal Pale Oak Fence}
     */
    String MC_TILE_PALE_OAK_FENCE_NAME = "minecraft:tile.pale_oak_fence.name";

    /**
     * {@literal Pale Oak Fence Gate}
     */
    String MC_TILE_PALE_OAK_FENCE_GATE_NAME = "minecraft:tile.pale_oak_fence_gate.name";

    /**
     * {@literal Pale Oak Leaves}
     */
    String MC_TILE_PALE_OAK_LEAVES_NAME = "minecraft:tile.pale_oak_leaves.name";

    /**
     * {@literal Pale Oak Log}
     */
    String MC_TILE_PALE_OAK_LOG_NAME = "minecraft:tile.pale_oak_log.name";

    /**
     * {@literal Pale Oak Planks}
     */
    String MC_TILE_PALE_OAK_PLANKS_NAME = "minecraft:tile.pale_oak_planks.name";

    /**
     * {@literal Pale Oak Pressure Plate}
     */
    String MC_TILE_PALE_OAK_PRESSURE_PLATE_NAME = "minecraft:tile.pale_oak_pressure_plate.name";

    /**
     * {@literal Pale Oak Sapling}
     */
    String MC_TILE_PALE_OAK_SAPLING_NAME = "minecraft:tile.pale_oak_sapling.name";

    /**
     * {@literal Pale Oak Shelf}
     */
    String MC_TILE_PALE_OAK_SHELF_NAME = "minecraft:tile.pale_oak_shelf.name";

    /**
     * {@literal Pale Oak Slab}
     */
    String MC_TILE_PALE_OAK_SLAB_NAME = "minecraft:tile.pale_oak_slab.name";

    /**
     * {@literal Pale Oak Stairs}
     */
    String MC_TILE_PALE_OAK_STAIRS_NAME = "minecraft:tile.pale_oak_stairs.name";

    /**
     * {@literal Pale Oak Trapdoor}
     */
    String MC_TILE_PALE_OAK_TRAPDOOR_NAME = "minecraft:tile.pale_oak_trapdoor.name";

    /**
     * {@literal Pale Oak Wood}
     */
    String MC_TILE_PALE_OAK_WOOD_NAME = "minecraft:tile.pale_oak_wood.name";

    /**
     * {@literal Pearlescent Froglight}
     */
    String MC_TILE_PEARLESCENT_FROGLIGHT_NAME = "minecraft:tile.pearlescent_froglight.name";

    /**
     * {@literal Pink Candle}
     */
    String MC_TILE_PINK_CANDLE_NAME = "minecraft:tile.pink_candle.name";

    /**
     * {@literal Cake with Pink Candle}
     */
    String MC_TILE_PINK_CANDLE_CAKE_NAME = "minecraft:tile.pink_candle_cake.name";

    /**
     * {@literal Pink Petals}
     */
    String MC_TILE_PINK_PETALS_NAME = "minecraft:tile.pink_petals.name";

    /**
     * {@literal Piston}
     */
    String MC_TILE_PISTON_NAME = "minecraft:tile.piston.name";

    /**
     * {@literal Pitcher Plant}
     */
    String MC_TILE_PITCHER_PLANT_NAME = "minecraft:tile.pitcher_plant.name";

    /**
     * {@literal Acacia Planks}
     */
    String MC_TILE_PLANKS_ACACIA_NAME = "minecraft:tile.planks.acacia.name";

    /**
     * {@literal Dark Oak Planks}
     */
    String MC_TILE_PLANKS_BIG_OAK_NAME = "minecraft:tile.planks.big_oak.name";

    /**
     * {@literal Birch Planks}
     */
    String MC_TILE_PLANKS_BIRCH_NAME = "minecraft:tile.planks.birch.name";

    /**
     * {@literal Jungle Planks}
     */
    String MC_TILE_PLANKS_JUNGLE_NAME = "minecraft:tile.planks.jungle.name";

    /**
     * {@literal Wooden Planks}
     */
    String MC_TILE_PLANKS_NAME = "minecraft:tile.planks.name";

    /**
     * {@literal Oak Planks}
     */
    String MC_TILE_PLANKS_OAK_NAME = "minecraft:tile.planks.oak.name";

    /**
     * {@literal Spruce Planks}
     */
    String MC_TILE_PLANKS_SPRUCE_NAME = "minecraft:tile.planks.spruce.name";

    /**
     * {@literal Podzol}
     */
    String MC_TILE_PODZOL_NAME = "minecraft:tile.podzol.name";

    /**
     * {@literal Pointed Dripstone}
     */
    String MC_TILE_POINTED_DRIPSTONE_NAME = "minecraft:tile.pointed_dripstone.name";

    /**
     * {@literal Polished Andesite Stairs}
     */
    String MC_TILE_POLISHED_ANDESITE_STAIRS_NAME = "minecraft:tile.polished_andesite_stairs.name";

    /**
     * {@literal Polished Basalt}
     */
    String MC_TILE_POLISHED_BASALT_NAME = "minecraft:tile.polished_basalt.name";

    /**
     * {@literal Polished Blackstone}
     */
    String MC_TILE_POLISHED_BLACKSTONE_NAME = "minecraft:tile.polished_blackstone.name";

    /**
     * {@literal Polished Blackstone Brick Slab}
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICK_SLAB_NAME = "minecraft:tile.polished_blackstone_brick_slab.name";

    /**
     * {@literal Polished Blackstone Brick Stairs}
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICK_STAIRS_NAME = "minecraft:tile.polished_blackstone_brick_stairs.name";

    /**
     * {@literal Polished Blackstone Brick Wall}
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICK_WALL_NAME = "minecraft:tile.polished_blackstone_brick_wall.name";

    /**
     * {@literal Polished Blackstone Bricks}
     */
    String MC_TILE_POLISHED_BLACKSTONE_BRICKS_NAME = "minecraft:tile.polished_blackstone_bricks.name";

    /**
     * {@literal Polished Blackstone Button}
     */
    String MC_TILE_POLISHED_BLACKSTONE_BUTTON_NAME = "minecraft:tile.polished_blackstone_button.name";

    /**
     * {@literal Polished Blackstone Pressure Plate}
     */
    String MC_TILE_POLISHED_BLACKSTONE_PRESSURE_PLATE_NAME = "minecraft:tile.polished_blackstone_pressure_plate.name";

    /**
     * {@literal Polished Blackstone Slab}
     */
    String MC_TILE_POLISHED_BLACKSTONE_SLAB_NAME = "minecraft:tile.polished_blackstone_slab.name";

    /**
     * {@literal Polished Blackstone Stairs}
     */
    String MC_TILE_POLISHED_BLACKSTONE_STAIRS_NAME = "minecraft:tile.polished_blackstone_stairs.name";

    /**
     * {@literal Polished Blackstone Wall}
     */
    String MC_TILE_POLISHED_BLACKSTONE_WALL_NAME = "minecraft:tile.polished_blackstone_wall.name";

    /**
     * {@literal Polished Deepslate}
     */
    String MC_TILE_POLISHED_DEEPSLATE_NAME = "minecraft:tile.polished_deepslate.name";

    /**
     * {@literal Polished Deepslate Double Slab}
     */
    String MC_TILE_POLISHED_DEEPSLATE_DOUBLE_SLAB_NAME = "minecraft:tile.polished_deepslate_double_slab.name";

    /**
     * {@literal Polished Deepslate Slab}
     */
    String MC_TILE_POLISHED_DEEPSLATE_SLAB_NAME = "minecraft:tile.polished_deepslate_slab.name";

    /**
     * {@literal Polished Deepslate Stairs}
     */
    String MC_TILE_POLISHED_DEEPSLATE_STAIRS_NAME = "minecraft:tile.polished_deepslate_stairs.name";

    /**
     * {@literal Polished Deepslate Wall}
     */
    String MC_TILE_POLISHED_DEEPSLATE_WALL_NAME = "minecraft:tile.polished_deepslate_wall.name";

    /**
     * {@literal Polished Diorite Stairs}
     */
    String MC_TILE_POLISHED_DIORITE_STAIRS_NAME = "minecraft:tile.polished_diorite_stairs.name";

    /**
     * {@literal Polished Granite Stairs}
     */
    String MC_TILE_POLISHED_GRANITE_STAIRS_NAME = "minecraft:tile.polished_granite_stairs.name";

    /**
     * {@literal Polished Tuff}
     */
    String MC_TILE_POLISHED_TUFF_NAME = "minecraft:tile.polished_tuff.name";

    /**
     * {@literal Polished Tuff Slab}
     */
    String MC_TILE_POLISHED_TUFF_SLAB_NAME = "minecraft:tile.polished_tuff_slab.name";

    /**
     * {@literal Polished Tuff Stairs}
     */
    String MC_TILE_POLISHED_TUFF_STAIRS_NAME = "minecraft:tile.polished_tuff_stairs.name";

    /**
     * {@literal Polished Tuff Wall}
     */
    String MC_TILE_POLISHED_TUFF_WALL_NAME = "minecraft:tile.polished_tuff_wall.name";

    /**
     * {@literal Portal}
     */
    String MC_TILE_PORTAL_NAME = "minecraft:tile.portal.name";

    /**
     * {@literal Potatoes}
     */
    String MC_TILE_POTATOES_NAME = "minecraft:tile.potatoes.name";

    /**
     * {@literal Powder Snow}
     */
    String MC_TILE_POWDER_SNOW_NAME = "minecraft:tile.powder_snow.name";

    /**
     * {@literal Prismarine Bricks}
     */
    String MC_TILE_PRISMARINE_BRICKS_NAME = "minecraft:tile.prismarine.bricks.name";

    /**
     * {@literal Dark Prismarine}
     */
    String MC_TILE_PRISMARINE_DARK_NAME = "minecraft:tile.prismarine.dark.name";

    /**
     * {@literal Prismarine}
     */
    String MC_TILE_PRISMARINE_ROUGH_NAME = "minecraft:tile.prismarine.rough.name";

    /**
     * {@literal Prismarine Brick Stairs}
     */
    String MC_TILE_PRISMARINE_BRICKS_STAIRS_NAME = "minecraft:tile.prismarine_bricks_stairs.name";

    /**
     * {@literal Prismarine Stairs}
     */
    String MC_TILE_PRISMARINE_STAIRS_NAME = "minecraft:tile.prismarine_stairs.name";

    /**
     * {@literal Pumpkin}
     */
    String MC_TILE_PUMPKIN_NAME = "minecraft:tile.pumpkin.name";

    /**
     * {@literal Pumpkin Stem}
     */
    String MC_TILE_PUMPKIN_STEM_NAME = "minecraft:tile.pumpkin_stem.name";

    /**
     * {@literal Purple Candle}
     */
    String MC_TILE_PURPLE_CANDLE_NAME = "minecraft:tile.purple_candle.name";

    /**
     * {@literal Cake with Purple Candle}
     */
    String MC_TILE_PURPLE_CANDLE_CAKE_NAME = "minecraft:tile.purple_candle_cake.name";

    /**
     * {@literal Chiseled Purpur}
     */
    String MC_TILE_PURPUR_BLOCK_CHISELED_NAME = "minecraft:tile.purpur_block.chiseled.name";

    /**
     * {@literal Purpur Block}
     */
    String MC_TILE_PURPUR_BLOCK_DEFAULT_NAME = "minecraft:tile.purpur_block.default.name";

    /**
     * {@literal Purpur Pillar}
     */
    String MC_TILE_PURPUR_BLOCK_LINES_NAME = "minecraft:tile.purpur_block.lines.name";

    /**
     * {@literal Purpur Stairs}
     */
    String MC_TILE_PURPUR_STAIRS_NAME = "minecraft:tile.purpur_stairs.name";

    /**
     * {@literal Chiseled Quartz Block}
     */
    String MC_TILE_QUARTZ_BLOCK_CHISELED_NAME = "minecraft:tile.quartz_block.chiseled.name";

    /**
     * {@literal Block of Quartz}
     */
    String MC_TILE_QUARTZ_BLOCK_DEFAULT_NAME = "minecraft:tile.quartz_block.default.name";

    /**
     * {@literal Quartz Pillar}
     */
    String MC_TILE_QUARTZ_BLOCK_LINES_NAME = "minecraft:tile.quartz_block.lines.name";

    /**
     * {@literal Block of Quartz}
     */
    String MC_TILE_QUARTZ_BLOCK_NAME = "minecraft:tile.quartz_block.name";

    /**
     * {@literal Smooth Quartz Block}
     */
    String MC_TILE_QUARTZ_BLOCK_SMOOTH_NAME = "minecraft:tile.quartz_block.smooth.name";

    /**
     * {@literal Quartz Bricks}
     */
    String MC_TILE_QUARTZ_BRICKS_NAME = "minecraft:tile.quartz_bricks.name";

    /**
     * {@literal Nether Quartz Ore}
     */
    String MC_TILE_QUARTZ_ORE_NAME = "minecraft:tile.quartz_ore.name";

    /**
     * {@literal Quartz Stairs}
     */
    String MC_TILE_QUARTZ_STAIRS_NAME = "minecraft:tile.quartz_stairs.name";

    /**
     * {@literal Rail}
     */
    String MC_TILE_RAIL_NAME = "minecraft:tile.rail.name";

    /**
     * {@literal Block of Raw Copper}
     */
    String MC_TILE_RAW_COPPER_BLOCK_NAME = "minecraft:tile.raw_copper_block.name";

    /**
     * {@literal Block of Raw Gold}
     */
    String MC_TILE_RAW_GOLD_BLOCK_NAME = "minecraft:tile.raw_gold_block.name";

    /**
     * {@literal Block of Raw Iron}
     */
    String MC_TILE_RAW_IRON_BLOCK_NAME = "minecraft:tile.raw_iron_block.name";

    /**
     * {@literal Red Candle}
     */
    String MC_TILE_RED_CANDLE_NAME = "minecraft:tile.red_candle.name";

    /**
     * {@literal Cake with Red Candle}
     */
    String MC_TILE_RED_CANDLE_CAKE_NAME = "minecraft:tile.red_candle_cake.name";

    /**
     * {@literal Allium}
     */
    String MC_TILE_RED_FLOWER_ALLIUM_NAME = "minecraft:tile.red_flower.allium.name";

    /**
     * {@literal Blue Orchid}
     */
    String MC_TILE_RED_FLOWER_BLUEORCHID_NAME = "minecraft:tile.red_flower.blueOrchid.name";

    /**
     * {@literal Cornflower}
     */
    String MC_TILE_RED_FLOWER_CORNFLOWER_NAME = "minecraft:tile.red_flower.cornflower.name";

    /**
     * {@literal Azure Bluet}
     */
    String MC_TILE_RED_FLOWER_HOUSTONIA_NAME = "minecraft:tile.red_flower.houstonia.name";

    /**
     * {@literal Lily of the Valley}
     */
    String MC_TILE_RED_FLOWER_LILYOFTHEVALLEY_NAME = "minecraft:tile.red_flower.lilyOfTheValley.name";

    /**
     * {@literal Flower}
     */
    String MC_TILE_RED_FLOWER_NAME = "minecraft:tile.red_flower.name";

    /**
     * {@literal Oxeye Daisy}
     */
    String MC_TILE_RED_FLOWER_OXEYEDAISY_NAME = "minecraft:tile.red_flower.oxeyeDaisy.name";

    /**
     * {@literal Poppy}
     */
    String MC_TILE_RED_FLOWER_POPPY_NAME = "minecraft:tile.red_flower.poppy.name";

    /**
     * {@literal Orange Tulip}
     */
    String MC_TILE_RED_FLOWER_TULIPORANGE_NAME = "minecraft:tile.red_flower.tulipOrange.name";

    /**
     * {@literal Pink Tulip}
     */
    String MC_TILE_RED_FLOWER_TULIPPINK_NAME = "minecraft:tile.red_flower.tulipPink.name";

    /**
     * {@literal Red Tulip}
     */
    String MC_TILE_RED_FLOWER_TULIPRED_NAME = "minecraft:tile.red_flower.tulipRed.name";

    /**
     * {@literal White Tulip}
     */
    String MC_TILE_RED_FLOWER_TULIPWHITE_NAME = "minecraft:tile.red_flower.tulipWhite.name";

    /**
     * {@literal Red Mushroom}
     */
    String MC_TILE_RED_MUSHROOM_NAME = "minecraft:tile.red_mushroom.name";

    /**
     * {@literal Red Mushroom Block}
     */
    String MC_TILE_RED_MUSHROOM_BLOCK_NAME = "minecraft:tile.red_mushroom_block.name";

    /**
     * {@literal Red Nether Bricks}
     */
    String MC_TILE_RED_NETHER_BRICK_NAME = "minecraft:tile.red_nether_brick.name";

    /**
     * {@literal Red Nether Brick Stairs}
     */
    String MC_TILE_RED_NETHER_BRICK_STAIRS_NAME = "minecraft:tile.red_nether_brick_stairs.name";

    /**
     * {@literal Chiseled Red Sandstone}
     */
    String MC_TILE_RED_SANDSTONE_CHISELED_NAME = "minecraft:tile.red_sandstone.chiseled.name";

    /**
     * {@literal Cut Red Sandstone}
     */
    String MC_TILE_RED_SANDSTONE_CUT_NAME = "minecraft:tile.red_sandstone.cut.name";

    /**
     * {@literal Red Sandstone}
     */
    String MC_TILE_RED_SANDSTONE_DEFAULT_NAME = "minecraft:tile.red_sandstone.default.name";

    /**
     * {@literal Red Sandstone}
     */
    String MC_TILE_RED_SANDSTONE_NAME = "minecraft:tile.red_sandstone.name";

    /**
     * {@literal Smooth Red Sandstone}
     */
    String MC_TILE_RED_SANDSTONE_SMOOTH_NAME = "minecraft:tile.red_sandstone.smooth.name";

    /**
     * {@literal Red Sandstone Stairs}
     */
    String MC_TILE_RED_SANDSTONE_STAIRS_NAME = "minecraft:tile.red_sandstone_stairs.name";

    /**
     * {@literal Block of Redstone}
     */
    String MC_TILE_REDSTONE_BLOCK_NAME = "minecraft:tile.redstone_block.name";

    /**
     * {@literal Redstone Lamp}
     */
    String MC_TILE_REDSTONE_LAMP_NAME = "minecraft:tile.redstone_lamp.name";

    /**
     * {@literal Redstone Ore}
     */
    String MC_TILE_REDSTONE_ORE_NAME = "minecraft:tile.redstone_ore.name";

    /**
     * {@literal Redstone Torch}
     */
    String MC_TILE_REDSTONE_TORCH_NAME = "minecraft:tile.redstone_torch.name";

    /**
     * {@literal Redstone Dust}
     */
    String MC_TILE_REDSTONE_WIRE_NAME = "minecraft:tile.redstone_wire.name";

    /**
     * {@literal Sugar cane}
     */
    String MC_TILE_REEDS_NAME = "minecraft:tile.reeds.name";

    /**
     * {@literal Reinforced Deepslate}
     */
    String MC_TILE_REINFORCED_DEEPSLATE_NAME = "minecraft:tile.reinforced_deepslate.name";

    /**
     * {@literal Repeating Command Block}
     */
    String MC_TILE_REPEATING_COMMAND_BLOCK_NAME = "minecraft:tile.repeating_command_block.name";

    /**
     * {@literal Block of Resin}
     */
    String MC_TILE_RESIN_BLOCK_NAME = "minecraft:tile.resin_block.name";

    /**
     * {@literal Resin Brick Double Slab}
     */
    String MC_TILE_RESIN_BRICK_DOUBLE_SLAB_NAME = "minecraft:tile.resin_brick_double_slab.name";

    /**
     * {@literal Resin Brick Slab}
     */
    String MC_TILE_RESIN_BRICK_SLAB_NAME = "minecraft:tile.resin_brick_slab.name";

    /**
     * {@literal Resin Brick Stairs}
     */
    String MC_TILE_RESIN_BRICK_STAIRS_NAME = "minecraft:tile.resin_brick_stairs.name";

    /**
     * {@literal Resin Brick Wall}
     */
    String MC_TILE_RESIN_BRICK_WALL_NAME = "minecraft:tile.resin_brick_wall.name";

    /**
     * {@literal Resin Bricks}
     */
    String MC_TILE_RESIN_BRICKS_NAME = "minecraft:tile.resin_bricks.name";

    /**
     * {@literal Resin Clump}
     */
    String MC_TILE_RESIN_CLUMP_NAME = "minecraft:tile.resin_clump.name";

    /**
     * {@literal Respawn Anchor}
     */
    String MC_TILE_RESPAWN_ANCHOR_NAME = "minecraft:tile.respawn_anchor.name";

    /**
     * {@literal Your respawn anchor was out of charges, missing or obstructed}
     */
    String MC_TILE_RESPAWN_ANCHOR_NOTVALID = "minecraft:tile.respawn_anchor.notValid";

    /**
     * {@literal Respawn point set}
     */
    String MC_TILE_RESPAWN_ANCHOR_RESPAWNSET = "minecraft:tile.respawn_anchor.respawnSet";

    /**
     * {@literal Sand}
     */
    String MC_TILE_SAND_DEFAULT_NAME = "minecraft:tile.sand.default.name";

    /**
     * {@literal Sand}
     */
    String MC_TILE_SAND_NAME = "minecraft:tile.sand.name";

    /**
     * {@literal Red Sand}
     */
    String MC_TILE_SAND_RED_NAME = "minecraft:tile.sand.red.name";

    /**
     * {@literal Chiseled Sandstone}
     */
    String MC_TILE_SANDSTONE_CHISELED_NAME = "minecraft:tile.sandstone.chiseled.name";

    /**
     * {@literal Cut Sandstone}
     */
    String MC_TILE_SANDSTONE_CUT_NAME = "minecraft:tile.sandstone.cut.name";

    /**
     * {@literal Sandstone}
     */
    String MC_TILE_SANDSTONE_DEFAULT_NAME = "minecraft:tile.sandstone.default.name";

    /**
     * {@literal Sandstone}
     */
    String MC_TILE_SANDSTONE_NAME = "minecraft:tile.sandstone.name";

    /**
     * {@literal Smooth Sandstone}
     */
    String MC_TILE_SANDSTONE_SMOOTH_NAME = "minecraft:tile.sandstone.smooth.name";

    /**
     * {@literal Sandstone Stairs}
     */
    String MC_TILE_SANDSTONE_STAIRS_NAME = "minecraft:tile.sandstone_stairs.name";

    /**
     * {@literal Acacia Sapling}
     */
    String MC_TILE_SAPLING_ACACIA_NAME = "minecraft:tile.sapling.acacia.name";

    /**
     * {@literal Dark Oak Sapling}
     */
    String MC_TILE_SAPLING_BIG_OAK_NAME = "minecraft:tile.sapling.big_oak.name";

    /**
     * {@literal Birch Sapling}
     */
    String MC_TILE_SAPLING_BIRCH_NAME = "minecraft:tile.sapling.birch.name";

    /**
     * {@literal Jungle Sapling}
     */
    String MC_TILE_SAPLING_JUNGLE_NAME = "minecraft:tile.sapling.jungle.name";

    /**
     * {@literal Oak Sapling}
     */
    String MC_TILE_SAPLING_OAK_NAME = "minecraft:tile.sapling.oak.name";

    /**
     * {@literal Spruce Sapling}
     */
    String MC_TILE_SAPLING_SPRUCE_NAME = "minecraft:tile.sapling.spruce.name";

    /**
     * {@literal Scaffolding}
     */
    String MC_TILE_SCAFFOLDING_NAME = "minecraft:tile.scaffolding.name";

    /**
     * {@literal Sculk}
     */
    String MC_TILE_SCULK_NAME = "minecraft:tile.sculk.name";

    /**
     * {@literal Sculk Catalyst}
     */
    String MC_TILE_SCULK_CATALYST_NAME = "minecraft:tile.sculk_catalyst.name";

    /**
     * {@literal Sculk Sensor}
     */
    String MC_TILE_SCULK_SENSOR_NAME = "minecraft:tile.sculk_sensor.name";

    /**
     * {@literal Sculk Shrieker}
     */
    String MC_TILE_SCULK_SHRIEKER_NAME = "minecraft:tile.sculk_shrieker.name";

    /**
     * {@literal Sculk Vein}
     */
    String MC_TILE_SCULK_VEIN_NAME = "minecraft:tile.sculk_vein.name";

    /**
     * {@literal Sea Lantern}
     */
    String MC_TILE_SEALANTERN_NAME = "minecraft:tile.seaLantern.name";

    /**
     * {@literal Sea Pickle}
     */
    String MC_TILE_SEA_PICKLE_NAME = "minecraft:tile.sea_pickle.name";

    /**
     * {@literal Seagrass}
     */
    String MC_TILE_SEAGRASS_SEAGRASS_NAME = "minecraft:tile.seagrass.seagrass.name";

    /**
     * {@literal Short Dry Grass}
     */
    String MC_TILE_SHORT_DRY_GRASS_NAME = "minecraft:tile.short_dry_grass.name";

    /**
     * {@literal Shroomlight}
     */
    String MC_TILE_SHROOMLIGHT_NAME = "minecraft:tile.shroomlight.name";

    /**
     * {@literal Shulker Box}
     */
    String MC_TILE_SHULKERBOX_NAME = "minecraft:tile.shulkerBox.name";

    /**
     * {@literal Black Shulker Box}
     */
    String MC_TILE_SHULKERBOXBLACK_NAME = "minecraft:tile.shulkerBoxBlack.name";

    /**
     * {@literal Blue Shulker Box}
     */
    String MC_TILE_SHULKERBOXBLUE_NAME = "minecraft:tile.shulkerBoxBlue.name";

    /**
     * {@literal Brown Shulker Box}
     */
    String MC_TILE_SHULKERBOXBROWN_NAME = "minecraft:tile.shulkerBoxBrown.name";

    /**
     * {@literal Cyan Shulker Box}
     */
    String MC_TILE_SHULKERBOXCYAN_NAME = "minecraft:tile.shulkerBoxCyan.name";

    /**
     * {@literal Gray Shulker Box}
     */
    String MC_TILE_SHULKERBOXGRAY_NAME = "minecraft:tile.shulkerBoxGray.name";

    /**
     * {@literal Green Shulker Box}
     */
    String MC_TILE_SHULKERBOXGREEN_NAME = "minecraft:tile.shulkerBoxGreen.name";

    /**
     * {@literal Light Blue Shulker Box}
     */
    String MC_TILE_SHULKERBOXLIGHTBLUE_NAME = "minecraft:tile.shulkerBoxLightBlue.name";

    /**
     * {@literal Lime Shulker Box}
     */
    String MC_TILE_SHULKERBOXLIME_NAME = "minecraft:tile.shulkerBoxLime.name";

    /**
     * {@literal Magenta Shulker Box}
     */
    String MC_TILE_SHULKERBOXMAGENTA_NAME = "minecraft:tile.shulkerBoxMagenta.name";

    /**
     * {@literal Orange Shulker Box}
     */
    String MC_TILE_SHULKERBOXORANGE_NAME = "minecraft:tile.shulkerBoxOrange.name";

    /**
     * {@literal Pink Shulker Box}
     */
    String MC_TILE_SHULKERBOXPINK_NAME = "minecraft:tile.shulkerBoxPink.name";

    /**
     * {@literal Purple Shulker Box}
     */
    String MC_TILE_SHULKERBOXPURPLE_NAME = "minecraft:tile.shulkerBoxPurple.name";

    /**
     * {@literal Red Shulker Box}
     */
    String MC_TILE_SHULKERBOXRED_NAME = "minecraft:tile.shulkerBoxRed.name";

    /**
     * {@literal Light Gray Shulker Box}
     */
    String MC_TILE_SHULKERBOXSILVER_NAME = "minecraft:tile.shulkerBoxSilver.name";

    /**
     * {@literal White Shulker Box}
     */
    String MC_TILE_SHULKERBOXWHITE_NAME = "minecraft:tile.shulkerBoxWhite.name";

    /**
     * {@literal Yellow Shulker Box}
     */
    String MC_TILE_SHULKERBOXYELLOW_NAME = "minecraft:tile.shulkerBoxYellow.name";

    /**
     * {@literal Slime Block}
     */
    String MC_TILE_SLIME_NAME = "minecraft:tile.slime.name";

    /**
     * {@literal Small Amethyst Bud}
     */
    String MC_TILE_SMALL_AMETHYST_BUD_NAME = "minecraft:tile.small_amethyst_bud.name";

    /**
     * {@literal Small Dripleaf}
     */
    String MC_TILE_SMALL_DRIPLEAF_BLOCK_NAME = "minecraft:tile.small_dripleaf_block.name";

    /**
     * {@literal Smithing Table}
     */
    String MC_TILE_SMITHING_TABLE_NAME = "minecraft:tile.smithing_table.name";

    /**
     * {@literal Smoker}
     */
    String MC_TILE_SMOKER_NAME = "minecraft:tile.smoker.name";

    /**
     * {@literal Smooth Basalt}
     */
    String MC_TILE_SMOOTH_BASALT_NAME = "minecraft:tile.smooth_basalt.name";

    /**
     * {@literal Smooth Quartz Stairs}
     */
    String MC_TILE_SMOOTH_QUARTZ_STAIRS_NAME = "minecraft:tile.smooth_quartz_stairs.name";

    /**
     * {@literal Smooth Red Sandstone Stairs}
     */
    String MC_TILE_SMOOTH_RED_SANDSTONE_STAIRS_NAME = "minecraft:tile.smooth_red_sandstone_stairs.name";

    /**
     * {@literal Smooth Sandstone Stairs}
     */
    String MC_TILE_SMOOTH_SANDSTONE_STAIRS_NAME = "minecraft:tile.smooth_sandstone_stairs.name";

    /**
     * {@literal Smooth Stone}
     */
    String MC_TILE_SMOOTH_STONE_NAME = "minecraft:tile.smooth_stone.name";

    /**
     * {@literal Sniffer Egg}
     */
    String MC_TILE_SNIFFER_EGG_NAME = "minecraft:tile.sniffer_egg.name";

    /**
     * {@literal Snow Block}
     */
    String MC_TILE_SNOW_NAME = "minecraft:tile.snow.name";

    /**
     * {@literal Snow}
     */
    String MC_TILE_SNOW_LAYER_NAME = "minecraft:tile.snow_layer.name";

    /**
     * {@literal Soul Campfire}
     */
    String MC_TILE_SOUL_CAMPFIRE_NAME = "minecraft:tile.soul_campfire.name";

    /**
     * {@literal Soul Fire}
     */
    String MC_TILE_SOUL_FIRE_NAME = "minecraft:tile.soul_fire.name";

    /**
     * {@literal Soul Lantern}
     */
    String MC_TILE_SOUL_LANTERN_NAME = "minecraft:tile.soul_lantern.name";

    /**
     * {@literal Soul Sand}
     */
    String MC_TILE_SOUL_SAND_NAME = "minecraft:tile.soul_sand.name";

    /**
     * {@literal Soul Soil}
     */
    String MC_TILE_SOUL_SOIL_NAME = "minecraft:tile.soul_soil.name";

    /**
     * {@literal Soul Torch}
     */
    String MC_TILE_SOUL_TORCH_NAME = "minecraft:tile.soul_torch.name";

    /**
     * {@literal Sponge}
     */
    String MC_TILE_SPONGE_DRY_NAME = "minecraft:tile.sponge.dry.name";

    /**
     * {@literal Wet Sponge}
     */
    String MC_TILE_SPONGE_WET_NAME = "minecraft:tile.sponge.wet.name";

    /**
     * {@literal Spore Blossom}
     */
    String MC_TILE_SPORE_BLOSSOM_NAME = "minecraft:tile.spore_blossom.name";

    /**
     * {@literal Spruce Fence}
     */
    String MC_TILE_SPRUCEFENCE_NAME = "minecraft:tile.spruceFence.name";

    /**
     * {@literal Spruce Button}
     */
    String MC_TILE_SPRUCE_BUTTON_NAME = "minecraft:tile.spruce_button.name";

    /**
     * {@literal Spruce Fence Gate}
     */
    String MC_TILE_SPRUCE_FENCE_GATE_NAME = "minecraft:tile.spruce_fence_gate.name";

    /**
     * {@literal Spruce Pressure Plate}
     */
    String MC_TILE_SPRUCE_PRESSURE_PLATE_NAME = "minecraft:tile.spruce_pressure_plate.name";

    /**
     * {@literal Spruce Shelf}
     */
    String MC_TILE_SPRUCE_SHELF_NAME = "minecraft:tile.spruce_shelf.name";

    /**
     * {@literal Spruce Stairs}
     */
    String MC_TILE_SPRUCE_STAIRS_NAME = "minecraft:tile.spruce_stairs.name";

    /**
     * {@literal Spruce Sign}
     */
    String MC_TILE_SPRUCE_STANDING_SIGN_NAME = "minecraft:tile.spruce_standing_sign.name";

    /**
     * {@literal Spruce Trapdoor}
     */
    String MC_TILE_SPRUCE_TRAPDOOR_NAME = "minecraft:tile.spruce_trapdoor.name";

    /**
     * {@literal Black Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_BLACK_NAME = "minecraft:tile.stained_glass.black.name";

    /**
     * {@literal Blue Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_BLUE_NAME = "minecraft:tile.stained_glass.blue.name";

    /**
     * {@literal Brown Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_BROWN_NAME = "minecraft:tile.stained_glass.brown.name";

    /**
     * {@literal Cyan Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_CYAN_NAME = "minecraft:tile.stained_glass.cyan.name";

    /**
     * {@literal Gray Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_GRAY_NAME = "minecraft:tile.stained_glass.gray.name";

    /**
     * {@literal Green Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_GREEN_NAME = "minecraft:tile.stained_glass.green.name";

    /**
     * {@literal Light Blue Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_LIGHT_BLUE_NAME = "minecraft:tile.stained_glass.light_blue.name";

    /**
     * {@literal Lime Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_LIME_NAME = "minecraft:tile.stained_glass.lime.name";

    /**
     * {@literal Magenta Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_MAGENTA_NAME = "minecraft:tile.stained_glass.magenta.name";

    /**
     * {@literal Orange Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_ORANGE_NAME = "minecraft:tile.stained_glass.orange.name";

    /**
     * {@literal Pink Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_PINK_NAME = "minecraft:tile.stained_glass.pink.name";

    /**
     * {@literal Purple Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_PURPLE_NAME = "minecraft:tile.stained_glass.purple.name";

    /**
     * {@literal Red Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_RED_NAME = "minecraft:tile.stained_glass.red.name";

    /**
     * {@literal Light Gray Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_SILVER_NAME = "minecraft:tile.stained_glass.silver.name";

    /**
     * {@literal White Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_WHITE_NAME = "minecraft:tile.stained_glass.white.name";

    /**
     * {@literal Yellow Stained Glass}
     */
    String MC_TILE_STAINED_GLASS_YELLOW_NAME = "minecraft:tile.stained_glass.yellow.name";

    /**
     * {@literal Black Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_BLACK_NAME = "minecraft:tile.stained_glass_pane.black.name";

    /**
     * {@literal Blue Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_BLUE_NAME = "minecraft:tile.stained_glass_pane.blue.name";

    /**
     * {@literal Brown Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_BROWN_NAME = "minecraft:tile.stained_glass_pane.brown.name";

    /**
     * {@literal Cyan Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_CYAN_NAME = "minecraft:tile.stained_glass_pane.cyan.name";

    /**
     * {@literal Gray Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_GRAY_NAME = "minecraft:tile.stained_glass_pane.gray.name";

    /**
     * {@literal Green Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_GREEN_NAME = "minecraft:tile.stained_glass_pane.green.name";

    /**
     * {@literal Light Blue Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_LIGHT_BLUE_NAME = "minecraft:tile.stained_glass_pane.light_blue.name";

    /**
     * {@literal Lime Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_LIME_NAME = "minecraft:tile.stained_glass_pane.lime.name";

    /**
     * {@literal Magenta Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_MAGENTA_NAME = "minecraft:tile.stained_glass_pane.magenta.name";

    /**
     * {@literal Orange Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_ORANGE_NAME = "minecraft:tile.stained_glass_pane.orange.name";

    /**
     * {@literal Pink Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_PINK_NAME = "minecraft:tile.stained_glass_pane.pink.name";

    /**
     * {@literal Purple Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_PURPLE_NAME = "minecraft:tile.stained_glass_pane.purple.name";

    /**
     * {@literal Red Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_RED_NAME = "minecraft:tile.stained_glass_pane.red.name";

    /**
     * {@literal Light Gray Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_SILVER_NAME = "minecraft:tile.stained_glass_pane.silver.name";

    /**
     * {@literal White Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_WHITE_NAME = "minecraft:tile.stained_glass_pane.white.name";

    /**
     * {@literal Yellow Stained Glass Pane}
     */
    String MC_TILE_STAINED_GLASS_PANE_YELLOW_NAME = "minecraft:tile.stained_glass_pane.yellow.name";

    /**
     * {@literal Black Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_BLACK_NAME = "minecraft:tile.stained_hardened_clay.black.name";

    /**
     * {@literal Blue Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_BLUE_NAME = "minecraft:tile.stained_hardened_clay.blue.name";

    /**
     * {@literal Brown Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_BROWN_NAME = "minecraft:tile.stained_hardened_clay.brown.name";

    /**
     * {@literal Cyan Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_CYAN_NAME = "minecraft:tile.stained_hardened_clay.cyan.name";

    /**
     * {@literal Gray Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_GRAY_NAME = "minecraft:tile.stained_hardened_clay.gray.name";

    /**
     * {@literal Green Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_GREEN_NAME = "minecraft:tile.stained_hardened_clay.green.name";

    /**
     * {@literal Light Blue Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_LIGHTBLUE_NAME = "minecraft:tile.stained_hardened_clay.lightBlue.name";

    /**
     * {@literal Lime Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_LIME_NAME = "minecraft:tile.stained_hardened_clay.lime.name";

    /**
     * {@literal Magenta Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_MAGENTA_NAME = "minecraft:tile.stained_hardened_clay.magenta.name";

    /**
     * {@literal Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_NAME = "minecraft:tile.stained_hardened_clay.name";

    /**
     * {@literal Orange Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_ORANGE_NAME = "minecraft:tile.stained_hardened_clay.orange.name";

    /**
     * {@literal Pink Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_PINK_NAME = "minecraft:tile.stained_hardened_clay.pink.name";

    /**
     * {@literal Purple Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_PURPLE_NAME = "minecraft:tile.stained_hardened_clay.purple.name";

    /**
     * {@literal Red Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_RED_NAME = "minecraft:tile.stained_hardened_clay.red.name";

    /**
     * {@literal Light Gray Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_SILVER_NAME = "minecraft:tile.stained_hardened_clay.silver.name";

    /**
     * {@literal White Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_WHITE_NAME = "minecraft:tile.stained_hardened_clay.white.name";

    /**
     * {@literal Yellow Terracotta}
     */
    String MC_TILE_STAINED_HARDENED_CLAY_YELLOW_NAME = "minecraft:tile.stained_hardened_clay.yellow.name";

    /**
     * {@literal Black Banner}
     */
    String MC_TILE_STANDING_BANNER_BLACK_NAME = "minecraft:tile.standing_banner.black.name";

    /**
     * {@literal Blue Banner}
     */
    String MC_TILE_STANDING_BANNER_BLUE_NAME = "minecraft:tile.standing_banner.blue.name";

    /**
     * {@literal Brown Banner}
     */
    String MC_TILE_STANDING_BANNER_BROWN_NAME = "minecraft:tile.standing_banner.brown.name";

    /**
     * {@literal Cyan Banner}
     */
    String MC_TILE_STANDING_BANNER_CYAN_NAME = "minecraft:tile.standing_banner.cyan.name";

    /**
     * {@literal Gray Banner}
     */
    String MC_TILE_STANDING_BANNER_GRAY_NAME = "minecraft:tile.standing_banner.gray.name";

    /**
     * {@literal Green Banner}
     */
    String MC_TILE_STANDING_BANNER_GREEN_NAME = "minecraft:tile.standing_banner.green.name";

    /**
     * {@literal Light Blue Banner}
     */
    String MC_TILE_STANDING_BANNER_LIGHTBLUE_NAME = "minecraft:tile.standing_banner.lightBlue.name";

    /**
     * {@literal Lime Banner}
     */
    String MC_TILE_STANDING_BANNER_LIME_NAME = "minecraft:tile.standing_banner.lime.name";

    /**
     * {@literal Magenta Banner}
     */
    String MC_TILE_STANDING_BANNER_MAGENTA_NAME = "minecraft:tile.standing_banner.magenta.name";

    /**
     * {@literal Banner}
     */
    String MC_TILE_STANDING_BANNER_NAME = "minecraft:tile.standing_banner.name";

    /**
     * {@literal Orange Banner}
     */
    String MC_TILE_STANDING_BANNER_ORANGE_NAME = "minecraft:tile.standing_banner.orange.name";

    /**
     * {@literal Pink Banner}
     */
    String MC_TILE_STANDING_BANNER_PINK_NAME = "minecraft:tile.standing_banner.pink.name";

    /**
     * {@literal Purple Banner}
     */
    String MC_TILE_STANDING_BANNER_PURPLE_NAME = "minecraft:tile.standing_banner.purple.name";

    /**
     * {@literal Red Banner}
     */
    String MC_TILE_STANDING_BANNER_RED_NAME = "minecraft:tile.standing_banner.red.name";

    /**
     * {@literal Light Gray Banner}
     */
    String MC_TILE_STANDING_BANNER_SILVER_NAME = "minecraft:tile.standing_banner.silver.name";

    /**
     * {@literal Banner}
     */
    String MC_TILE_STANDING_BANNER_WHITE_NAME = "minecraft:tile.standing_banner.white.name";

    /**
     * {@literal Yellow Banner}
     */
    String MC_TILE_STANDING_BANNER_YELLOW_NAME = "minecraft:tile.standing_banner.yellow.name";

    /**
     * {@literal Sign}
     */
    String MC_TILE_STANDING_SIGN_NAME = "minecraft:tile.standing_sign.name";

    /**
     * {@literal Sticky Piston}
     */
    String MC_TILE_STICKY_PISTON_NAME = "minecraft:tile.sticky_piston.name";

    /**
     * {@literal Andesite}
     */
    String MC_TILE_STONE_ANDESITE_NAME = "minecraft:tile.stone.andesite.name";

    /**
     * {@literal Polished Andesite}
     */
    String MC_TILE_STONE_ANDESITESMOOTH_NAME = "minecraft:tile.stone.andesiteSmooth.name";

    /**
     * {@literal Diorite}
     */
    String MC_TILE_STONE_DIORITE_NAME = "minecraft:tile.stone.diorite.name";

    /**
     * {@literal Polished Diorite}
     */
    String MC_TILE_STONE_DIORITESMOOTH_NAME = "minecraft:tile.stone.dioriteSmooth.name";

    /**
     * {@literal Granite}
     */
    String MC_TILE_STONE_GRANITE_NAME = "minecraft:tile.stone.granite.name";

    /**
     * {@literal Polished Granite}
     */
    String MC_TILE_STONE_GRANITESMOOTH_NAME = "minecraft:tile.stone.graniteSmooth.name";

    /**
     * {@literal Stone}
     */
    String MC_TILE_STONE_STONE_NAME = "minecraft:tile.stone.stone.name";

    /**
     * {@literal Stone Brick Stairs}
     */
    String MC_TILE_STONE_BRICK_STAIRS_NAME = "minecraft:tile.stone_brick_stairs.name";

    /**
     * {@literal Stone Button}
     */
    String MC_TILE_STONE_BUTTON_NAME = "minecraft:tile.stone_button.name";

    /**
     * {@literal Stone Pressure Plate}
     */
    String MC_TILE_STONE_PRESSURE_PLATE_NAME = "minecraft:tile.stone_pressure_plate.name";

    /**
     * {@literal Brick Slab}
     */
    String MC_TILE_STONE_SLAB_BRICK_NAME = "minecraft:tile.stone_slab.brick.name";

    /**
     * {@literal Cobblestone Slab}
     */
    String MC_TILE_STONE_SLAB_COBBLE_NAME = "minecraft:tile.stone_slab.cobble.name";

    /**
     * {@literal Stone Slab}
     */
    String MC_TILE_STONE_SLAB_NAME = "minecraft:tile.stone_slab.name";

    /**
     * {@literal Nether Brick Slab}
     */
    String MC_TILE_STONE_SLAB_NETHER_BRICK_NAME = "minecraft:tile.stone_slab.nether_brick.name";

    /**
     * {@literal Quartz Slab}
     */
    String MC_TILE_STONE_SLAB_QUARTZ_NAME = "minecraft:tile.stone_slab.quartz.name";

    /**
     * {@literal Sandstone Slab}
     */
    String MC_TILE_STONE_SLAB_SAND_NAME = "minecraft:tile.stone_slab.sand.name";

    /**
     * {@literal Stone Brick Slab}
     */
    String MC_TILE_STONE_SLAB_SMOOTHSTONEBRICK_NAME = "minecraft:tile.stone_slab.smoothStoneBrick.name";

    /**
     * {@literal Smooth Stone Slab}
     */
    String MC_TILE_STONE_SLAB_STONE_NAME = "minecraft:tile.stone_slab.stone.name";

    /**
     * {@literal Wooden Slab}
     */
    String MC_TILE_STONE_SLAB_WOOD_NAME = "minecraft:tile.stone_slab.wood.name";

    /**
     * {@literal Mossy Cobblestone Slab}
     */
    String MC_TILE_STONE_SLAB2_MOSSY_COBBLESTONE_NAME = "minecraft:tile.stone_slab2.mossy_cobblestone.name";

    /**
     * {@literal Prismarine Brick Slab}
     */
    String MC_TILE_STONE_SLAB2_PRISMARINE_BRICKS_NAME = "minecraft:tile.stone_slab2.prismarine.bricks.name";

    /**
     * {@literal Dark Prismarine Slab}
     */
    String MC_TILE_STONE_SLAB2_PRISMARINE_DARK_NAME = "minecraft:tile.stone_slab2.prismarine.dark.name";

    /**
     * {@literal Prismarine Slab}
     */
    String MC_TILE_STONE_SLAB2_PRISMARINE_ROUGH_NAME = "minecraft:tile.stone_slab2.prismarine.rough.name";

    /**
     * {@literal Purpur Slab}
     */
    String MC_TILE_STONE_SLAB2_PURPUR_NAME = "minecraft:tile.stone_slab2.purpur.name";

    /**
     * {@literal Red Nether Brick Slab}
     */
    String MC_TILE_STONE_SLAB2_RED_NETHER_BRICK_NAME = "minecraft:tile.stone_slab2.red_nether_brick.name";

    /**
     * {@literal Red Sandstone Slab}
     */
    String MC_TILE_STONE_SLAB2_RED_SANDSTONE_NAME = "minecraft:tile.stone_slab2.red_sandstone.name";

    /**
     * {@literal Smooth Sandstone Slab}
     */
    String MC_TILE_STONE_SLAB2_SANDSTONE_SMOOTH_NAME = "minecraft:tile.stone_slab2.sandstone.smooth.name";

    /**
     * {@literal Andesite Slab}
     */
    String MC_TILE_STONE_SLAB3_ANDESITE_NAME = "minecraft:tile.stone_slab3.andesite.name";

    /**
     * {@literal Polished Andesite Slab}
     */
    String MC_TILE_STONE_SLAB3_ANDESITE_SMOOTH_NAME = "minecraft:tile.stone_slab3.andesite.smooth.name";

    /**
     * {@literal Diorite Slab}
     */
    String MC_TILE_STONE_SLAB3_DIORITE_NAME = "minecraft:tile.stone_slab3.diorite.name";

    /**
     * {@literal Polished Diorite Slab}
     */
    String MC_TILE_STONE_SLAB3_DIORITE_SMOOTH_NAME = "minecraft:tile.stone_slab3.diorite.smooth.name";

    /**
     * {@literal End Stone Brick Slab}
     */
    String MC_TILE_STONE_SLAB3_END_BRICK_NAME = "minecraft:tile.stone_slab3.end_brick.name";

    /**
     * {@literal Granite Slab}
     */
    String MC_TILE_STONE_SLAB3_GRANITE_NAME = "minecraft:tile.stone_slab3.granite.name";

    /**
     * {@literal Polished Granite Slab}
     */
    String MC_TILE_STONE_SLAB3_GRANITE_SMOOTH_NAME = "minecraft:tile.stone_slab3.granite.smooth.name";

    /**
     * {@literal Smooth Red Sandstone Slab}
     */
    String MC_TILE_STONE_SLAB3_RED_SANDSTONE_SMOOTH_NAME = "minecraft:tile.stone_slab3.red_sandstone.smooth.name";

    /**
     * {@literal Cut Red Sandstone Slab}
     */
    String MC_TILE_STONE_SLAB4_CUT_RED_SANDSTONE_NAME = "minecraft:tile.stone_slab4.cut_red_sandstone.name";

    /**
     * {@literal Cut Sandstone Slab}
     */
    String MC_TILE_STONE_SLAB4_CUT_SANDSTONE_NAME = "minecraft:tile.stone_slab4.cut_sandstone.name";

    /**
     * {@literal Mossy Stone Brick Slab}
     */
    String MC_TILE_STONE_SLAB4_MOSSY_STONE_BRICK_NAME = "minecraft:tile.stone_slab4.mossy_stone_brick.name";

    /**
     * {@literal Smooth Quartz Slab}
     */
    String MC_TILE_STONE_SLAB4_SMOOTH_QUARTZ_NAME = "minecraft:tile.stone_slab4.smooth_quartz.name";

    /**
     * {@literal Stone Slab}
     */
    String MC_TILE_STONE_SLAB4_STONE_NAME = "minecraft:tile.stone_slab4.stone.name";

    /**
     * {@literal Cobblestone Stairs}
     */
    String MC_TILE_STONE_STAIRS_NAME = "minecraft:tile.stone_stairs.name";

    /**
     * {@literal Chiseled Stone Bricks}
     */
    String MC_TILE_STONEBRICK_CHISELED_NAME = "minecraft:tile.stonebrick.chiseled.name";

    /**
     * {@literal Cracked Stone Bricks}
     */
    String MC_TILE_STONEBRICK_CRACKED_NAME = "minecraft:tile.stonebrick.cracked.name";

    /**
     * {@literal Stone Bricks}
     */
    String MC_TILE_STONEBRICK_DEFAULT_NAME = "minecraft:tile.stonebrick.default.name";

    /**
     * {@literal Mossy Stone Bricks}
     */
    String MC_TILE_STONEBRICK_MOSSY_NAME = "minecraft:tile.stonebrick.mossy.name";

    /**
     * {@literal Stone Bricks}
     */
    String MC_TILE_STONEBRICK_NAME = "minecraft:tile.stonebrick.name";

    /**
     * {@literal Smooth Stone Bricks}
     */
    String MC_TILE_STONEBRICK_SMOOTH_NAME = "minecraft:tile.stonebrick.smooth.name";

    /**
     * {@literal Stonecutter}
     */
    String MC_TILE_STONECUTTER_NAME = "minecraft:tile.stonecutter.name";

    /**
     * {@literal Stonecutter}
     */
    String MC_TILE_STONECUTTER_BLOCK_NAME = "minecraft:tile.stonecutter_block.name";

    /**
     * {@literal Stripped Acacia Log}
     */
    String MC_TILE_STRIPPED_ACACIA_LOG_NAME = "minecraft:tile.stripped_acacia_log.name";

    /**
     * {@literal Block of Stripped Bamboo}
     */
    String MC_TILE_STRIPPED_BAMBOO_BLOCK_NAME = "minecraft:tile.stripped_bamboo_block.name";

    /**
     * {@literal Stripped Birch Log}
     */
    String MC_TILE_STRIPPED_BIRCH_LOG_NAME = "minecraft:tile.stripped_birch_log.name";

    /**
     * {@literal Stripped Cherry Log}
     */
    String MC_TILE_STRIPPED_CHERRY_LOG_NAME = "minecraft:tile.stripped_cherry_log.name";

    /**
     * {@literal Stripped Cherry Wood}
     */
    String MC_TILE_STRIPPED_CHERRY_WOOD_NAME = "minecraft:tile.stripped_cherry_wood.name";

    /**
     * {@literal Stripped Crimson Hyphae}
     */
    String MC_TILE_STRIPPED_CRIMSON_HYPHAE_NAME = "minecraft:tile.stripped_crimson_hyphae.name";

    /**
     * {@literal Stripped Crimson Stem}
     */
    String MC_TILE_STRIPPED_CRIMSON_STEM_NAME = "minecraft:tile.stripped_crimson_stem.name";

    /**
     * {@literal Stripped Dark Oak Log}
     */
    String MC_TILE_STRIPPED_DARK_OAK_LOG_NAME = "minecraft:tile.stripped_dark_oak_log.name";

    /**
     * {@literal Stripped Jungle Log}
     */
    String MC_TILE_STRIPPED_JUNGLE_LOG_NAME = "minecraft:tile.stripped_jungle_log.name";

    /**
     * {@literal Stripped Mangrove Log}
     */
    String MC_TILE_STRIPPED_MANGROVE_LOG_NAME = "minecraft:tile.stripped_mangrove_log.name";

    /**
     * {@literal Stripped Mangrove Wood}
     */
    String MC_TILE_STRIPPED_MANGROVE_WOOD_NAME = "minecraft:tile.stripped_mangrove_wood.name";

    /**
     * {@literal Stripped Oak Log}
     */
    String MC_TILE_STRIPPED_OAK_LOG_NAME = "minecraft:tile.stripped_oak_log.name";

    /**
     * {@literal Stripped Pale Oak Log}
     */
    String MC_TILE_STRIPPED_PALE_OAK_LOG_NAME = "minecraft:tile.stripped_pale_oak_log.name";

    /**
     * {@literal Stripped Pale Oak Wood}
     */
    String MC_TILE_STRIPPED_PALE_OAK_WOOD_NAME = "minecraft:tile.stripped_pale_oak_wood.name";

    /**
     * {@literal Stripped Spruce Log}
     */
    String MC_TILE_STRIPPED_SPRUCE_LOG_NAME = "minecraft:tile.stripped_spruce_log.name";

    /**
     * {@literal Stripped Warped Hyphae}
     */
    String MC_TILE_STRIPPED_WARPED_HYPHAE_NAME = "minecraft:tile.stripped_warped_hyphae.name";

    /**
     * {@literal Stripped Warped Stem}
     */
    String MC_TILE_STRIPPED_WARPED_STEM_NAME = "minecraft:tile.stripped_warped_stem.name";

    /**
     * {@literal Structure Block}
     */
    String MC_TILE_STRUCTURE_BLOCK_NAME = "minecraft:tile.structure_block.name";

    /**
     * {@literal Structure Void}
     */
    String MC_TILE_STRUCTURE_VOID_NAME = "minecraft:tile.structure_void.name";

    /**
     * {@literal Suspicious Gravel}
     */
    String MC_TILE_SUSPICIOUS_GRAVEL_NAME = "minecraft:tile.suspicious_gravel.name";

    /**
     * {@literal Suspicious Sand}
     */
    String MC_TILE_SUSPICIOUS_SAND_NAME = "minecraft:tile.suspicious_sand.name";

    /**
     * {@literal Sweet Berry Bush}
     */
    String MC_TILE_SWEET_BERRY_BUSH_NAME = "minecraft:tile.sweet_berry_bush.name";

    /**
     * {@literal Tall Dry Grass}
     */
    String MC_TILE_TALL_DRY_GRASS_NAME = "minecraft:tile.tall_dry_grass.name";

    /**
     * {@literal Fern}
     */
    String MC_TILE_TALLGRASS_FERN_NAME = "minecraft:tile.tallgrass.fern.name";

    /**
     * {@literal Short Grass}
     */
    String MC_TILE_TALLGRASS_GRASS_NAME = "minecraft:tile.tallgrass.grass.name";

    /**
     * {@literal Short Grass}
     */
    String MC_TILE_TALLGRASS_NAME = "minecraft:tile.tallgrass.name";

    /**
     * {@literal Shrub}
     */
    String MC_TILE_TALLGRASS_SHRUB_NAME = "minecraft:tile.tallgrass.shrub.name";

    /**
     * {@literal Target}
     */
    String MC_TILE_TARGET_NAME = "minecraft:tile.target.name";

    /**
     * {@literal Tinted Glass}
     */
    String MC_TILE_TINTED_GLASS_NAME = "minecraft:tile.tinted_glass.name";

    /**
     * {@literal TNT}
     */
    String MC_TILE_TNT_NAME = "minecraft:tile.tnt.name";

    /**
     * {@literal Torch}
     */
    String MC_TILE_TORCH_NAME = "minecraft:tile.torch.name";

    /**
     * {@literal Torchflower}
     */
    String MC_TILE_TORCHFLOWER_NAME = "minecraft:tile.torchflower.name";

    /**
     * {@literal Oak Trapdoor}
     */
    String MC_TILE_TRAPDOOR_NAME = "minecraft:tile.trapdoor.name";

    /**
     * {@literal Trapped Chest}
     */
    String MC_TILE_TRAPPED_CHEST_NAME = "minecraft:tile.trapped_chest.name";

    /**
     * {@literal Trial Spawner}
     */
    String MC_TILE_TRIAL_SPAWNER_NAME = "minecraft:tile.trial_spawner.name";

    /**
     * {@literal Tripwire}
     */
    String MC_TILE_TRIPWIRE_NAME = "minecraft:tile.tripWire.name";

    /**
     * {@literal Tripwire Hook}
     */
    String MC_TILE_TRIPWIRE_HOOK_NAME = "minecraft:tile.tripwire_hook.name";

    /**
     * {@literal Tuff}
     */
    String MC_TILE_TUFF_NAME = "minecraft:tile.tuff.name";

    /**
     * {@literal Tuff Brick Slab}
     */
    String MC_TILE_TUFF_BRICK_SLAB_NAME = "minecraft:tile.tuff_brick_slab.name";

    /**
     * {@literal Tuff Brick Stairs}
     */
    String MC_TILE_TUFF_BRICK_STAIRS_NAME = "minecraft:tile.tuff_brick_stairs.name";

    /**
     * {@literal Tuff Brick Wall}
     */
    String MC_TILE_TUFF_BRICK_WALL_NAME = "minecraft:tile.tuff_brick_wall.name";

    /**
     * {@literal Tuff Bricks}
     */
    String MC_TILE_TUFF_BRICKS_NAME = "minecraft:tile.tuff_bricks.name";

    /**
     * {@literal Tuff Slab}
     */
    String MC_TILE_TUFF_SLAB_NAME = "minecraft:tile.tuff_slab.name";

    /**
     * {@literal Tuff Stairs}
     */
    String MC_TILE_TUFF_STAIRS_NAME = "minecraft:tile.tuff_stairs.name";

    /**
     * {@literal Tuff Wall}
     */
    String MC_TILE_TUFF_WALL_NAME = "minecraft:tile.tuff_wall.name";

    /**
     * {@literal Turtle Egg}
     */
    String MC_TILE_TURTLE_EGG_NAME = "minecraft:tile.turtle_egg.name";

    /**
     * {@literal Twisting Vines}
     */
    String MC_TILE_TWISTING_VINES_NAME = "minecraft:tile.twisting_vines.name";

    /**
     * {@literal Unknown}
     */
    String MC_TILE_UNKNOWN_NAME = "minecraft:tile.unknown.name";

    /**
     * {@literal Redstone Torch}
     */
    String MC_TILE_UNLIT_REDSTONE_TORCH_NAME = "minecraft:tile.unlit_redstone_torch.name";

    /**
     * {@literal Vault}
     */
    String MC_TILE_VAULT_NAME = "minecraft:tile.vault.name";

    /**
     * {@literal Verdant Froglight}
     */
    String MC_TILE_VERDANT_FROGLIGHT_NAME = "minecraft:tile.verdant_froglight.name";

    /**
     * {@literal Vines}
     */
    String MC_TILE_VINE_NAME = "minecraft:tile.vine.name";

    /**
     * {@literal Warped Button}
     */
    String MC_TILE_WARPED_BUTTON_NAME = "minecraft:tile.warped_button.name";

    /**
     * {@literal Warped Door}
     */
    String MC_TILE_WARPED_DOOR_NAME = "minecraft:tile.warped_door.name";

    /**
     * {@literal Warped Slab}
     */
    String MC_TILE_WARPED_DOUBLE_SLAB_NAME = "minecraft:tile.warped_double_slab.name";

    /**
     * {@literal Warped Fence}
     */
    String MC_TILE_WARPED_FENCE_NAME = "minecraft:tile.warped_fence.name";

    /**
     * {@literal Warped Fence Gate}
     */
    String MC_TILE_WARPED_FENCE_GATE_NAME = "minecraft:tile.warped_fence_gate.name";

    /**
     * {@literal Warped Fungus}
     */
    String MC_TILE_WARPED_FUNGUS_NAME = "minecraft:tile.warped_fungus.name";

    /**
     * {@literal Warped Hyphae}
     */
    String MC_TILE_WARPED_HYPHAE_NAME = "minecraft:tile.warped_hyphae.name";

    /**
     * {@literal Warped Nylium}
     */
    String MC_TILE_WARPED_NYLIUM_NAME = "minecraft:tile.warped_nylium.name";

    /**
     * {@literal Warped Planks}
     */
    String MC_TILE_WARPED_PLANKS_NAME = "minecraft:tile.warped_planks.name";

    /**
     * {@literal Warped Pressure Plate}
     */
    String MC_TILE_WARPED_PRESSURE_PLATE_NAME = "minecraft:tile.warped_pressure_plate.name";

    /**
     * {@literal Warped Roots}
     */
    String MC_TILE_WARPED_ROOTS_WARPEDROOTS_NAME = "minecraft:tile.warped_roots.warpedRoots.name";

    /**
     * {@literal Warped Shelf}
     */
    String MC_TILE_WARPED_SHELF_NAME = "minecraft:tile.warped_shelf.name";

    /**
     * {@literal Warped Slab}
     */
    String MC_TILE_WARPED_SLAB_NAME = "minecraft:tile.warped_slab.name";

    /**
     * {@literal Warped Stairs}
     */
    String MC_TILE_WARPED_STAIRS_NAME = "minecraft:tile.warped_stairs.name";

    /**
     * {@literal Warped Sign}
     */
    String MC_TILE_WARPED_STANDING_SIGN_NAME = "minecraft:tile.warped_standing_sign.name";

    /**
     * {@literal Warped Stem}
     */
    String MC_TILE_WARPED_STEM_NAME = "minecraft:tile.warped_stem.name";

    /**
     * {@literal Warped Trapdoor}
     */
    String MC_TILE_WARPED_TRAPDOOR_NAME = "minecraft:tile.warped_trapdoor.name";

    /**
     * {@literal Warped Sign}
     */
    String MC_TILE_WARPED_WALL_SIGN_NAME = "minecraft:tile.warped_wall_sign.name";

    /**
     * {@literal Warped Wart Block}
     */
    String MC_TILE_WARPED_WART_BLOCK_NAME = "minecraft:tile.warped_wart_block.name";

    /**
     * {@literal Water}
     */
    String MC_TILE_WATER_NAME = "minecraft:tile.water.name";

    /**
     * {@literal Lily Pad}
     */
    String MC_TILE_WATERLILY_NAME = "minecraft:tile.waterlily.name";

    /**
     * {@literal Waxed Chiseled Copper}
     */
    String MC_TILE_WAXED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_chiseled_copper.name";

    /**
     * {@literal Waxed Block of Copper}
     */
    String MC_TILE_WAXED_COPPER_NAME = "minecraft:tile.waxed_copper.name";

    /**
     * {@literal Waxed Copper Bars}
     */
    String MC_TILE_WAXED_COPPER_BARS_NAME = "minecraft:tile.waxed_copper_bars.name";

    /**
     * {@literal Waxed Copper Bulb}
     */
    String MC_TILE_WAXED_COPPER_BULB_NAME = "minecraft:tile.waxed_copper_bulb.name";

    /**
     * {@literal Waxed Copper Chain}
     */
    String MC_TILE_WAXED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_copper_chain.name";

    /**
     * {@literal Waxed Copper Chest}
     */
    String MC_TILE_WAXED_COPPER_CHEST_NAME = "minecraft:tile.waxed_copper_chest.name";

    /**
     * {@literal Waxed Copper Door}
     */
    String MC_TILE_WAXED_COPPER_DOOR_NAME = "minecraft:tile.waxed_copper_door.name";

    /**
     * {@literal Waxed Copper Golem Statue}
     */
    String MC_TILE_WAXED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_copper_golem_statue.name";

    /**
     * {@literal Waxed Copper Grate}
     */
    String MC_TILE_WAXED_COPPER_GRATE_NAME = "minecraft:tile.waxed_copper_grate.name";

    /**
     * {@literal Waxed Copper Lantern}
     */
    String MC_TILE_WAXED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_copper_lantern.name";

    /**
     * {@literal Waxed Copper Trapdoor}
     */
    String MC_TILE_WAXED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_copper_trapdoor.name";

    /**
     * {@literal Waxed Cut Copper}
     */
    String MC_TILE_WAXED_CUT_COPPER_NAME = "minecraft:tile.waxed_cut_copper.name";

    /**
     * {@literal Waxed Cut Copper Slab}
     */
    String MC_TILE_WAXED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_cut_copper_slab.name";

    /**
     * {@literal Waxed Cut Copper Stairs}
     */
    String MC_TILE_WAXED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_cut_copper_stairs.name";

    /**
     * {@literal Waxed Exposed Chiseled Copper}
     */
    String MC_TILE_WAXED_EXPOSED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_exposed_chiseled_copper.name";

    /**
     * {@literal Waxed Exposed Copper}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_NAME = "minecraft:tile.waxed_exposed_copper.name";

    /**
     * {@literal Waxed Exposed Copper Bars}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_BARS_NAME = "minecraft:tile.waxed_exposed_copper_bars.name";

    /**
     * {@literal Waxed Exposed Copper Bulb}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_BULB_NAME = "minecraft:tile.waxed_exposed_copper_bulb.name";

    /**
     * {@literal Waxed Exposed Copper Chain}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_exposed_copper_chain.name";

    /**
     * {@literal Waxed Exposed Copper Chest}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_CHEST_NAME = "minecraft:tile.waxed_exposed_copper_chest.name";

    /**
     * {@literal Waxed Exposed Copper Door}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_DOOR_NAME = "minecraft:tile.waxed_exposed_copper_door.name";

    /**
     * {@literal Waxed Exposed Copper Golem Statue}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_exposed_copper_golem_statue.name";

    /**
     * {@literal Waxed Exposed Copper Grate}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_GRATE_NAME = "minecraft:tile.waxed_exposed_copper_grate.name";

    /**
     * {@literal Waxed Exposed Copper Lantern}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_exposed_copper_lantern.name";

    /**
     * {@literal Waxed Exposed Copper Trapdoor}
     */
    String MC_TILE_WAXED_EXPOSED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_exposed_copper_trapdoor.name";

    /**
     * {@literal Waxed Exposed Cut Copper}
     */
    String MC_TILE_WAXED_EXPOSED_CUT_COPPER_NAME = "minecraft:tile.waxed_exposed_cut_copper.name";

    /**
     * {@literal Waxed Exposed Cut Copper Slab}
     */
    String MC_TILE_WAXED_EXPOSED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_exposed_cut_copper_slab.name";

    /**
     * {@literal Waxed Exposed Cut Copper Stairs}
     */
    String MC_TILE_WAXED_EXPOSED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_exposed_cut_copper_stairs.name";

    /**
     * {@literal Waxed Exposed Lightning Rod}
     */
    String MC_TILE_WAXED_EXPOSED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_exposed_lightning_rod.name";

    /**
     * {@literal Waxed Lightning Rod}
     */
    String MC_TILE_WAXED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_lightning_rod.name";

    /**
     * {@literal Waxed Oxidized Chiseled Copper}
     */
    String MC_TILE_WAXED_OXIDIZED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_oxidized_chiseled_copper.name";

    /**
     * {@literal Waxed Oxidized Copper}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_NAME = "minecraft:tile.waxed_oxidized_copper.name";

    /**
     * {@literal Waxed Oxidized Copper Bars}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_BARS_NAME = "minecraft:tile.waxed_oxidized_copper_bars.name";

    /**
     * {@literal Waxed Oxidized Copper Bulb}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_BULB_NAME = "minecraft:tile.waxed_oxidized_copper_bulb.name";

    /**
     * {@literal Waxed Oxidized Copper Chain}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_oxidized_copper_chain.name";

    /**
     * {@literal Waxed Oxidized Copper Chest}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_CHEST_NAME = "minecraft:tile.waxed_oxidized_copper_chest.name";

    /**
     * {@literal Waxed Oxidized Copper Door}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_DOOR_NAME = "minecraft:tile.waxed_oxidized_copper_door.name";

    /**
     * {@literal Waxed Oxidized Copper Golem Statue}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_oxidized_copper_golem_statue.name";

    /**
     * {@literal Waxed Oxidized Copper Grate}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_GRATE_NAME = "minecraft:tile.waxed_oxidized_copper_grate.name";

    /**
     * {@literal Waxed Oxidized Copper Lantern}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_oxidized_copper_lantern.name";

    /**
     * {@literal Waxed Oxidized Copper Trapdoor}
     */
    String MC_TILE_WAXED_OXIDIZED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_oxidized_copper_trapdoor.name";

    /**
     * {@literal Waxed Oxidized Cut Copper}
     */
    String MC_TILE_WAXED_OXIDIZED_CUT_COPPER_NAME = "minecraft:tile.waxed_oxidized_cut_copper.name";

    /**
     * {@literal Waxed Oxidized Cut Copper Slab}
     */
    String MC_TILE_WAXED_OXIDIZED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_oxidized_cut_copper_slab.name";

    /**
     * {@literal Waxed Oxidized Cut Copper Stairs}
     */
    String MC_TILE_WAXED_OXIDIZED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_oxidized_cut_copper_stairs.name";

    /**
     * {@literal Waxed Oxidized Lightning Rod}
     */
    String MC_TILE_WAXED_OXIDIZED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_oxidized_lightning_rod.name";

    /**
     * {@literal Waxed Weathered Chiseled Copper}
     */
    String MC_TILE_WAXED_WEATHERED_CHISELED_COPPER_NAME = "minecraft:tile.waxed_weathered_chiseled_copper.name";

    /**
     * {@literal Waxed Weathered Copper}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_NAME = "minecraft:tile.waxed_weathered_copper.name";

    /**
     * {@literal Waxed Weathered Copper Bars}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_BARS_NAME = "minecraft:tile.waxed_weathered_copper_bars.name";

    /**
     * {@literal Waxed Weathered Copper Bulb}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_BULB_NAME = "minecraft:tile.waxed_weathered_copper_bulb.name";

    /**
     * {@literal Waxed Weathered Copper Chain}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_CHAIN_NAME = "minecraft:tile.waxed_weathered_copper_chain.name";

    /**
     * {@literal Waxed Weathered Copper Chest}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_CHEST_NAME = "minecraft:tile.waxed_weathered_copper_chest.name";

    /**
     * {@literal Waxed Weathered Copper Door}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_DOOR_NAME = "minecraft:tile.waxed_weathered_copper_door.name";

    /**
     * {@literal Waxed Weathered Copper Golem Statue}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.waxed_weathered_copper_golem_statue.name";

    /**
     * {@literal Waxed Weathered Copper Grate}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_GRATE_NAME = "minecraft:tile.waxed_weathered_copper_grate.name";

    /**
     * {@literal Waxed Weathered Copper Lantern}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_LANTERN_NAME = "minecraft:tile.waxed_weathered_copper_lantern.name";

    /**
     * {@literal Waxed Weathered Copper Trapdoor}
     */
    String MC_TILE_WAXED_WEATHERED_COPPER_TRAPDOOR_NAME = "minecraft:tile.waxed_weathered_copper_trapdoor.name";

    /**
     * {@literal Waxed Weathered Cut Copper}
     */
    String MC_TILE_WAXED_WEATHERED_CUT_COPPER_NAME = "minecraft:tile.waxed_weathered_cut_copper.name";

    /**
     * {@literal Waxed Weathered Cut Copper Slab}
     */
    String MC_TILE_WAXED_WEATHERED_CUT_COPPER_SLAB_NAME = "minecraft:tile.waxed_weathered_cut_copper_slab.name";

    /**
     * {@literal Waxed Weathered Cut Copper Stairs}
     */
    String MC_TILE_WAXED_WEATHERED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.waxed_weathered_cut_copper_stairs.name";

    /**
     * {@literal Waxed Weathered Lightning Rod}
     */
    String MC_TILE_WAXED_WEATHERED_LIGHTNING_ROD_NAME = "minecraft:tile.waxed_weathered_lightning_rod.name";

    /**
     * {@literal Weathered Chiseled Copper}
     */
    String MC_TILE_WEATHERED_CHISELED_COPPER_NAME = "minecraft:tile.weathered_chiseled_copper.name";

    /**
     * {@literal Weathered Copper}
     */
    String MC_TILE_WEATHERED_COPPER_NAME = "minecraft:tile.weathered_copper.name";

    /**
     * {@literal Weathered Copper Bars}
     */
    String MC_TILE_WEATHERED_COPPER_BARS_NAME = "minecraft:tile.weathered_copper_bars.name";

    /**
     * {@literal Weathered Copper Bulb}
     */
    String MC_TILE_WEATHERED_COPPER_BULB_NAME = "minecraft:tile.weathered_copper_bulb.name";

    /**
     * {@literal Weathered Copper Chain}
     */
    String MC_TILE_WEATHERED_COPPER_CHAIN_NAME = "minecraft:tile.weathered_copper_chain.name";

    /**
     * {@literal Weathered Copper Chest}
     */
    String MC_TILE_WEATHERED_COPPER_CHEST_NAME = "minecraft:tile.weathered_copper_chest.name";

    /**
     * {@literal Weathered Copper Door}
     */
    String MC_TILE_WEATHERED_COPPER_DOOR_NAME = "minecraft:tile.weathered_copper_door.name";

    /**
     * {@literal Weathered Copper Golem Statue}
     */
    String MC_TILE_WEATHERED_COPPER_GOLEM_STATUE_NAME = "minecraft:tile.weathered_copper_golem_statue.name";

    /**
     * {@literal Weathered Copper Grate}
     */
    String MC_TILE_WEATHERED_COPPER_GRATE_NAME = "minecraft:tile.weathered_copper_grate.name";

    /**
     * {@literal Weathered Copper Lantern}
     */
    String MC_TILE_WEATHERED_COPPER_LANTERN_NAME = "minecraft:tile.weathered_copper_lantern.name";

    /**
     * {@literal Weathered Copper Trapdoor}
     */
    String MC_TILE_WEATHERED_COPPER_TRAPDOOR_NAME = "minecraft:tile.weathered_copper_trapdoor.name";

    /**
     * {@literal Weathered Cut Copper}
     */
    String MC_TILE_WEATHERED_CUT_COPPER_NAME = "minecraft:tile.weathered_cut_copper.name";

    /**
     * {@literal Weathered Cut Copper Slab}
     */
    String MC_TILE_WEATHERED_CUT_COPPER_SLAB_NAME = "minecraft:tile.weathered_cut_copper_slab.name";

    /**
     * {@literal Weathered Cut Copper Stairs}
     */
    String MC_TILE_WEATHERED_CUT_COPPER_STAIRS_NAME = "minecraft:tile.weathered_cut_copper_stairs.name";

    /**
     * {@literal Weathered Lightning Rod}
     */
    String MC_TILE_WEATHERED_LIGHTNING_ROD_NAME = "minecraft:tile.weathered_lightning_rod.name";

    /**
     * {@literal Cobweb}
     */
    String MC_TILE_WEB_NAME = "minecraft:tile.web.name";

    /**
     * {@literal Weeping Vines}
     */
    String MC_TILE_WEEPING_VINES_NAME = "minecraft:tile.weeping_vines.name";

    /**
     * {@literal Crops}
     */
    String MC_TILE_WHEAT_NAME = "minecraft:tile.wheat.name";

    /**
     * {@literal White Candle}
     */
    String MC_TILE_WHITE_CANDLE_NAME = "minecraft:tile.white_candle.name";

    /**
     * {@literal Cake with White Candle}
     */
    String MC_TILE_WHITE_CANDLE_CAKE_NAME = "minecraft:tile.white_candle_cake.name";

    /**
     * {@literal Wildflowers}
     */
    String MC_TILE_WILDFLOWERS_NAME = "minecraft:tile.wildflowers.name";

    /**
     * {@literal Wither Rose}
     */
    String MC_TILE_WITHER_ROSE_NAME = "minecraft:tile.wither_rose.name";

    /**
     * {@literal Acacia Wood}
     */
    String MC_TILE_WOOD_ACACIA_NAME = "minecraft:tile.wood.acacia.name";

    /**
     * {@literal Birch Wood}
     */
    String MC_TILE_WOOD_BIRCH_NAME = "minecraft:tile.wood.birch.name";

    /**
     * {@literal Dark Oak Wood}
     */
    String MC_TILE_WOOD_DARK_OAK_NAME = "minecraft:tile.wood.dark_oak.name";

    /**
     * {@literal Jungle Wood}
     */
    String MC_TILE_WOOD_JUNGLE_NAME = "minecraft:tile.wood.jungle.name";

    /**
     * {@literal Oak Wood}
     */
    String MC_TILE_WOOD_OAK_NAME = "minecraft:tile.wood.oak.name";

    /**
     * {@literal Spruce Wood}
     */
    String MC_TILE_WOOD_SPRUCE_NAME = "minecraft:tile.wood.spruce.name";

    /**
     * {@literal Stripped Acacia Wood}
     */
    String MC_TILE_WOOD_STRIPPED_ACACIA_NAME = "minecraft:tile.wood.stripped.acacia.name";

    /**
     * {@literal Stripped Birch Wood}
     */
    String MC_TILE_WOOD_STRIPPED_BIRCH_NAME = "minecraft:tile.wood.stripped.birch.name";

    /**
     * {@literal Stripped Dark Oak Wood}
     */
    String MC_TILE_WOOD_STRIPPED_DARK_OAK_NAME = "minecraft:tile.wood.stripped.dark_oak.name";

    /**
     * {@literal Stripped Jungle Wood}
     */
    String MC_TILE_WOOD_STRIPPED_JUNGLE_NAME = "minecraft:tile.wood.stripped.jungle.name";

    /**
     * {@literal Stripped Oak Wood}
     */
    String MC_TILE_WOOD_STRIPPED_OAK_NAME = "minecraft:tile.wood.stripped.oak.name";

    /**
     * {@literal Stripped Spruce Wood}
     */
    String MC_TILE_WOOD_STRIPPED_SPRUCE_NAME = "minecraft:tile.wood.stripped.spruce.name";

    /**
     * {@literal Oak Button}
     */
    String MC_TILE_WOODEN_BUTTON_NAME = "minecraft:tile.wooden_button.name";

    /**
     * {@literal Oak Pressure Plate}
     */
    String MC_TILE_WOODEN_PRESSURE_PLATE_NAME = "minecraft:tile.wooden_pressure_plate.name";

    /**
     * {@literal Acacia Slab}
     */
    String MC_TILE_WOODEN_SLAB_ACACIA_NAME = "minecraft:tile.wooden_slab.acacia.name";

    /**
     * {@literal Dark Oak Slab}
     */
    String MC_TILE_WOODEN_SLAB_BIG_OAK_NAME = "minecraft:tile.wooden_slab.big_oak.name";

    /**
     * {@literal Birch Slab}
     */
    String MC_TILE_WOODEN_SLAB_BIRCH_NAME = "minecraft:tile.wooden_slab.birch.name";

    /**
     * {@literal Jungle Slab}
     */
    String MC_TILE_WOODEN_SLAB_JUNGLE_NAME = "minecraft:tile.wooden_slab.jungle.name";

    /**
     * {@literal Wood Slab}
     */
    String MC_TILE_WOODEN_SLAB_NAME = "minecraft:tile.wooden_slab.name";

    /**
     * {@literal Oak Slab}
     */
    String MC_TILE_WOODEN_SLAB_OAK_NAME = "minecraft:tile.wooden_slab.oak.name";

    /**
     * {@literal Spruce Slab}
     */
    String MC_TILE_WOODEN_SLAB_SPRUCE_NAME = "minecraft:tile.wooden_slab.spruce.name";

    /**
     * {@literal Black Wool}
     */
    String MC_TILE_WOOL_BLACK_NAME = "minecraft:tile.wool.black.name";

    /**
     * {@literal Blue Wool}
     */
    String MC_TILE_WOOL_BLUE_NAME = "minecraft:tile.wool.blue.name";

    /**
     * {@literal Brown Wool}
     */
    String MC_TILE_WOOL_BROWN_NAME = "minecraft:tile.wool.brown.name";

    /**
     * {@literal Cyan Wool}
     */
    String MC_TILE_WOOL_CYAN_NAME = "minecraft:tile.wool.cyan.name";

    /**
     * {@literal Gray Wool}
     */
    String MC_TILE_WOOL_GRAY_NAME = "minecraft:tile.wool.gray.name";

    /**
     * {@literal Green Wool}
     */
    String MC_TILE_WOOL_GREEN_NAME = "minecraft:tile.wool.green.name";

    /**
     * {@literal Light Blue Wool}
     */
    String MC_TILE_WOOL_LIGHTBLUE_NAME = "minecraft:tile.wool.lightBlue.name";

    /**
     * {@literal Lime Wool}
     */
    String MC_TILE_WOOL_LIME_NAME = "minecraft:tile.wool.lime.name";

    /**
     * {@literal Magenta Wool}
     */
    String MC_TILE_WOOL_MAGENTA_NAME = "minecraft:tile.wool.magenta.name";

    /**
     * {@literal Wool}
     */
    String MC_TILE_WOOL_NAME = "minecraft:tile.wool.name";

    /**
     * {@literal Orange Wool}
     */
    String MC_TILE_WOOL_ORANGE_NAME = "minecraft:tile.wool.orange.name";

    /**
     * {@literal Pink Wool}
     */
    String MC_TILE_WOOL_PINK_NAME = "minecraft:tile.wool.pink.name";

    /**
     * {@literal Purple Wool}
     */
    String MC_TILE_WOOL_PURPLE_NAME = "minecraft:tile.wool.purple.name";

    /**
     * {@literal Red Wool}
     */
    String MC_TILE_WOOL_RED_NAME = "minecraft:tile.wool.red.name";

    /**
     * {@literal Light Gray Wool}
     */
    String MC_TILE_WOOL_SILVER_NAME = "minecraft:tile.wool.silver.name";

    /**
     * {@literal White Wool}
     */
    String MC_TILE_WOOL_WHITE_NAME = "minecraft:tile.wool.white.name";

    /**
     * {@literal Yellow Wool}
     */
    String MC_TILE_WOOL_YELLOW_NAME = "minecraft:tile.wool.yellow.name";

    /**
     * {@literal Yellow Candle}
     */
    String MC_TILE_YELLOW_CANDLE_NAME = "minecraft:tile.yellow_candle.name";

    /**
     * {@literal Cake with Yellow Candle}
     */
    String MC_TILE_YELLOW_CANDLE_CAKE_NAME = "minecraft:tile.yellow_candle_cake.name";

    /**
     * {@literal Dandelion}
     */
    String MC_TILE_YELLOW_FLOWER_DANDELION_NAME = "minecraft:tile.yellow_flower.dandelion.name";

    /**
     * {@literal Flower}
     */
    String MC_TILE_YELLOW_FLOWER_NAME = "minecraft:tile.yellow_flower.name";

    /**
     * {@literal Tipped Arrow}
     */
    String MC_TIPPED_ARROW_EFFECT_AWKWARD = "minecraft:tipped_arrow.effect.awkward";

    /**
     * {@literal Arrow of Strength}
     */
    String MC_TIPPED_ARROW_EFFECT_DAMAGEBOOST = "minecraft:tipped_arrow.effect.damageBoost";

    /**
     * {@literal Tipped Arrow}
     */
    String MC_TIPPED_ARROW_EFFECT_EMPTY = "minecraft:tipped_arrow.effect.empty";

    /**
     * {@literal Arrow of Fire Resistance}
     */
    String MC_TIPPED_ARROW_EFFECT_FIRERESISTANCE = "minecraft:tipped_arrow.effect.fireResistance";

    /**
     * {@literal Arrow of Harming}
     */
    String MC_TIPPED_ARROW_EFFECT_HARM = "minecraft:tipped_arrow.effect.harm";

    /**
     * {@literal Arrow of Healing}
     */
    String MC_TIPPED_ARROW_EFFECT_HEAL = "minecraft:tipped_arrow.effect.heal";

    /**
     * {@literal Arrow of Infestation}
     */
    String MC_TIPPED_ARROW_EFFECT_INFESTED = "minecraft:tipped_arrow.effect.infested";

    /**
     * {@literal Arrow of Invisibility}
     */
    String MC_TIPPED_ARROW_EFFECT_INVISIBILITY = "minecraft:tipped_arrow.effect.invisibility";

    /**
     * {@literal Arrow of Leaping}
     */
    String MC_TIPPED_ARROW_EFFECT_JUMP = "minecraft:tipped_arrow.effect.jump";

    /**
     * {@literal Arrow of Levitation}
     */
    String MC_TIPPED_ARROW_EFFECT_LEVITATION = "minecraft:tipped_arrow.effect.levitation";

    /**
     * {@literal Arrow of Luck}
     */
    String MC_TIPPED_ARROW_EFFECT_LUCK = "minecraft:tipped_arrow.effect.luck";

    /**
     * {@literal Arrow of Slowness}
     */
    String MC_TIPPED_ARROW_EFFECT_MOVESLOWDOWN = "minecraft:tipped_arrow.effect.moveSlowdown";

    /**
     * {@literal Arrow of Swiftness}
     */
    String MC_TIPPED_ARROW_EFFECT_MOVESPEED = "minecraft:tipped_arrow.effect.moveSpeed";

    /**
     * {@literal Tipped Arrow}
     */
    String MC_TIPPED_ARROW_EFFECT_MUNDANE = "minecraft:tipped_arrow.effect.mundane";

    /**
     * {@literal Arrow of Night Vision}
     */
    String MC_TIPPED_ARROW_EFFECT_NIGHTVISION = "minecraft:tipped_arrow.effect.nightVision";

    /**
     * {@literal Arrow of Oozing}
     */
    String MC_TIPPED_ARROW_EFFECT_OOZING = "minecraft:tipped_arrow.effect.oozing";

    /**
     * {@literal Arrow of Poison}
     */
    String MC_TIPPED_ARROW_EFFECT_POISON = "minecraft:tipped_arrow.effect.poison";

    /**
     * {@literal Arrow of Regeneration}
     */
    String MC_TIPPED_ARROW_EFFECT_REGENERATION = "minecraft:tipped_arrow.effect.regeneration";

    /**
     * {@literal Arrow of Slow Falling}
     */
    String MC_TIPPED_ARROW_EFFECT_SLOWFALLING = "minecraft:tipped_arrow.effect.slowFalling";

    /**
     * {@literal Tipped Arrow}
     */
    String MC_TIPPED_ARROW_EFFECT_THICK = "minecraft:tipped_arrow.effect.thick";

    /**
     * {@literal Arrow of the Turtle Master}
     */
    String MC_TIPPED_ARROW_EFFECT_TURTLEMASTER = "minecraft:tipped_arrow.effect.turtleMaster";

    /**
     * {@literal Arrow of Splashing}
     */
    String MC_TIPPED_ARROW_EFFECT_WATER = "minecraft:tipped_arrow.effect.water";

    /**
     * {@literal Arrow of Water Breathing}
     */
    String MC_TIPPED_ARROW_EFFECT_WATERBREATHING = "minecraft:tipped_arrow.effect.waterBreathing";

    /**
     * {@literal Arrow of Weakness}
     */
    String MC_TIPPED_ARROW_EFFECT_WEAKNESS = "minecraft:tipped_arrow.effect.weakness";

    /**
     * {@literal Arrow of Weaving}
     */
    String MC_TIPPED_ARROW_EFFECT_WEAVING = "minecraft:tipped_arrow.effect.weaving";

    /**
     * {@literal Arrow of Wind Charging}
     */
    String MC_TIPPED_ARROW_EFFECT_WINDCHARGED = "minecraft:tipped_arrow.effect.windCharged";

    /**
     * {@literal Arrow of Decay}
     */
    String MC_TIPPED_ARROW_EFFECT_WITHER = "minecraft:tipped_arrow.effect.wither";

    /**
     * {@literal Trader does not want this}
     */
    String MC_TRADE_DOESNOTWANT = "minecraft:trade.doesNotWant";

    /**
     * {@literal !}
     */
    String MC_TRADE_EXCALAMATION = "minecraft:trade.excalamation";

    /**
     * {@literal Experience: MAX}
     */
    String MC_TRADE_EXPMAX = "minecraft:trade.expMax";

    /**
     * {@literal Experience: %d/%d}
     */
    String MC_TRADE_EXPPROGRESS = "minecraft:trade.expProgress";

    /**
     * {@literal Novice}
     */
    String MC_TRADE_LEVEL_1 = "minecraft:trade.level.1";

    /**
     * {@literal Apprentice}
     */
    String MC_TRADE_LEVEL_2 = "minecraft:trade.level.2";

    /**
     * {@literal Journeyman}
     */
    String MC_TRADE_LEVEL_3 = "minecraft:trade.level.3";

    /**
     * {@literal Expert}
     */
    String MC_TRADE_LEVEL_4 = "minecraft:trade.level.4";

    /**
     * {@literal Master}
     */
    String MC_TRADE_LEVEL_5 = "minecraft:trade.level.5";

    /**
     * {@literal Level %d}
     */
    String MC_TRADE_LEVELPREFIX = "minecraft:trade.levelPrefix";

    /**
     * {@literal Level %d - %s}
     */
    String MC_TRADE_LEVELPREFIXANDNAME = "minecraft:trade.levelPrefixAndName";

    /**
     * {@literal Max Level - %s}
     */
    String MC_TRADE_LEVELPREFIXANDNAME_MAX = "minecraft:trade.levelPrefixAndName.max";

    /**
     * {@literal dab}
     */
    String MC_TRADE_MYSTERIOUSTEXT = "minecraft:trade.mysteriousText";

    /**
     * {@literal %s - %s}
     */
    String MC_TRADE_NAMEANDLEVEL = "minecraft:trade.nameAndLevel";

    /**
     * {@literal Not enough %s}
     */
    String MC_TRADE_NOTENOUGH = "minecraft:trade.notEnough";

    /**
     * {@literal ?}
     */
    String MC_TRADE_QUESTION = "minecraft:trade.question";

    /**
     * {@literal Trade}
     */
    String MC_TRADE_TRADE = "minecraft:trade.trade";

    /**
     * {@literal %s %s}
     */
    String MC_TRANSLATION_TEST_ARGS = "minecraft:translation.test.args";

    /**
     * {@literal Prefix, %s%2#s again %s and %1#s lastly %s and also %1#s again!}
     */
    String MC_TRANSLATION_TEST_COMPLEX = "minecraft:translation.test.complex";

    /**
     * {@literal %%s %%%s %%%%s %%%%%s}
     */
    String MC_TRANSLATION_TEST_ESCAPE = "minecraft:translation.test.escape";

    /**
     * {@literal hi %}
     */
    String MC_TRANSLATION_TEST_INVALID = "minecraft:translation.test.invalid";

    /**
     * {@literal hi %  s}
     */
    String MC_TRANSLATION_TEST_INVALID2 = "minecraft:translation.test.invalid2";

    /**
     * {@literal Hello, world!}
     */
    String MC_TRANSLATION_TEST_NONE = "minecraft:translation.test.none";

    /**
     * {@literal world}
     */
    String MC_TRANSLATION_TEST_WORLD = "minecraft:translation.test.world";

    /**
     * {@literal Travel to new World}
     */
    String MC_TRAVEL_TOAST = "minecraft:travel.toast";

    /**
     * {@literal Amethyst Material}
     */
    String MC_TRIM_MATERIAL_AMETHYST_NAME = "minecraft:trim_material.amethyst.name";

    /**
     * {@literal Copper Material}
     */
    String MC_TRIM_MATERIAL_COPPER_NAME = "minecraft:trim_material.copper.name";

    /**
     * {@literal Diamond Material}
     */
    String MC_TRIM_MATERIAL_DIAMOND_NAME = "minecraft:trim_material.diamond.name";

    /**
     * {@literal Emerald Material}
     */
    String MC_TRIM_MATERIAL_EMERALD_NAME = "minecraft:trim_material.emerald.name";

    /**
     * {@literal Gold Material}
     */
    String MC_TRIM_MATERIAL_GOLD_NAME = "minecraft:trim_material.gold.name";

    /**
     * {@literal Iron Material}
     */
    String MC_TRIM_MATERIAL_IRON_NAME = "minecraft:trim_material.iron.name";

    /**
     * {@literal Lapis Material}
     */
    String MC_TRIM_MATERIAL_LAPIS_NAME = "minecraft:trim_material.lapis.name";

    /**
     * {@literal Netherite Material}
     */
    String MC_TRIM_MATERIAL_NETHERITE_NAME = "minecraft:trim_material.netherite.name";

    /**
     * {@literal Quartz Material}
     */
    String MC_TRIM_MATERIAL_QUARTZ_NAME = "minecraft:trim_material.quartz.name";

    /**
     * {@literal Redstone Material}
     */
    String MC_TRIM_MATERIAL_REDSTONE_NAME = "minecraft:trim_material.redstone.name";

    /**
     * {@literal Resin Material}
     */
    String MC_TRIM_MATERIAL_RESIN_NAME = "minecraft:trim_material.resin.name";

    /**
     * {@literal Bolt Armor Trim}
     */
    String MC_TRIM_PATTERN_BOLT_NAME = "minecraft:trim_pattern.bolt.name";

    /**
     * {@literal Coast Armor Trim}
     */
    String MC_TRIM_PATTERN_COAST_NAME = "minecraft:trim_pattern.coast.name";

    /**
     * {@literal Dune Armor Trim}
     */
    String MC_TRIM_PATTERN_DUNE_NAME = "minecraft:trim_pattern.dune.name";

    /**
     * {@literal Eye Armor Trim}
     */
    String MC_TRIM_PATTERN_EYE_NAME = "minecraft:trim_pattern.eye.name";

    /**
     * {@literal Flow Armor Trim}
     */
    String MC_TRIM_PATTERN_FLOW_NAME = "minecraft:trim_pattern.flow.name";

    /**
     * {@literal Host Armor Trim}
     */
    String MC_TRIM_PATTERN_HOST_NAME = "minecraft:trim_pattern.host.name";

    /**
     * {@literal Raiser Armor Trim}
     */
    String MC_TRIM_PATTERN_RAISER_NAME = "minecraft:trim_pattern.raiser.name";

    /**
     * {@literal Rib Armor Trim}
     */
    String MC_TRIM_PATTERN_RIB_NAME = "minecraft:trim_pattern.rib.name";

    /**
     * {@literal Sentry Armor Trim}
     */
    String MC_TRIM_PATTERN_SENTRY_NAME = "minecraft:trim_pattern.sentry.name";

    /**
     * {@literal Shaper Armor Trim}
     */
    String MC_TRIM_PATTERN_SHAPER_NAME = "minecraft:trim_pattern.shaper.name";

    /**
     * {@literal Silence Armor Trim}
     */
    String MC_TRIM_PATTERN_SILENCE_NAME = "minecraft:trim_pattern.silence.name";

    /**
     * {@literal Snout Armor Trim}
     */
    String MC_TRIM_PATTERN_SNOUT_NAME = "minecraft:trim_pattern.snout.name";

    /**
     * {@literal Spire Armor Trim}
     */
    String MC_TRIM_PATTERN_SPIRE_NAME = "minecraft:trim_pattern.spire.name";

    /**
     * {@literal Tide Armor Trim}
     */
    String MC_TRIM_PATTERN_TIDE_NAME = "minecraft:trim_pattern.tide.name";

    /**
     * {@literal Vex Armor Trim}
     */
    String MC_TRIM_PATTERN_VEX_NAME = "minecraft:trim_pattern.vex.name";

    /**
     * {@literal Ward Armor Trim}
     */
    String MC_TRIM_PATTERN_WARD_NAME = "minecraft:trim_pattern.ward.name";

    /**
     * {@literal Wayfinder Armor Trim}
     */
    String MC_TRIM_PATTERN_WAYFINDER_NAME = "minecraft:trim_pattern.wayfinder.name";

    /**
     * {@literal Wild Armor Trim}
     */
    String MC_TRIM_PATTERN_WILD_NAME = "minecraft:trim_pattern.wild.name";

    /**
     * {@literal Chunk still loading. You may encounter some mild performance issues}
     */
    String MC_UNLOADEDCHUNK_MESSAGE_WARNING = "minecraft:unloadedchunk.message.warning";

    /**
     * {@literal We detected that your user settings have become corrupted but failed to recover them.}
     */
    String MC_USERDATA_UNRECOVERABLE_TEXT = "minecraft:userdata.unrecoverable.text";

    /**
     * {@literal The requested operation is estimated to take %d minutes to complete. Please do not turn off your device during this time.}
     */
    String MC_WRITETHROTTLING_OSDIALOG_BODY = "minecraft:writeThrottling.osDialog.body";

    /**
     * {@literal Cancel Operation}
     */
    String MC_WRITETHROTTLING_OSDIALOG_CANCEL = "minecraft:writeThrottling.osDialog.cancel";

    /**
     * {@literal Proceed}
     */
    String MC_WRITETHROTTLING_OSDIALOG_CONFIRM = "minecraft:writeThrottling.osDialog.confirm";

    /**
     * {@literal Getting your files ready...}
     */
    String MC_WRITETHROTTLING_PROGRESSBAR_BODY = "minecraft:writeThrottling.progressBar.body";

    /**
     * {@literal Preparing Data}
     */
    String MC_WRITETHROTTLING_PROGRESSBAR_TITLE = "minecraft:writeThrottling.progressBar.title";
}
