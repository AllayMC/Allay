# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Each release is associated with a specific API version,
and any changes to API will have a prefix `(API)`.

Unless otherwise specified, any version comparison below is the comparison of server version, not API version.

## 0.3.1 (API 0.7.0) - Unreleased

<small>[Compare with 0.3.0](https://github.com/AllayMC/Allay/compare/0.3.0...HEAD)</small>

### Added

- Added Glided Blackstone and Glowstone drop.

### Fixed

- (API) Fixed binomial chance.
- (API) Fixed `DIRECTION_4` mapper.
- Fixed swords block breaking time.
- Fixed Beetroot, Carrots, Leaves, Potatoes, Redstone Ore, Grass and Wheat drops.

## 0.3.0 (API 0.6.0) - 2025-3-28

<small>[Compare with 0.2.0](https://github.com/AllayMC/Allay/compare/0.2.0...0.3.0)</small>

### Added

- (API) Added `ANVIL`, `STONECUTTER`, `GRINDSTONE`, `CARTOGRAPHY_TABLE`, `LOOM` and `SMITHING_TABLE` container types.
- (API) Implemented all behaviors for `Stonecutter`, `Grindstone`, `Smithing Table` and `Anvil`.
- (API) Added `ItemStack#isEmptyOrAir()` method.
- (API) Added `BlockPlaceHelper#processDirection4Property()` method.
- (API) Added `RecipeContainer` interface for validating crafting packets.
- (API) Added `ItemRepairableComponent` to check if an item can be repaired with a specific material.
- (API) Added `ItemTrimmableComponent` for trimming armor.
- (API) Added `AnvilDamageEvent`, `AnvilTakeResultEvent` and `GrindstoneTakeResultEvent` for plugins.
- (API) Added `ProtocolInfo.ITEM_STATE_UPDATER` which corresponds to `ProtocolInfo.BLOCK_STATE_UPDATER`.
- (API) Added `CommandNode#addLeaf(Function<CommandNode, CommandNode>)` method which helps keeping chain calls when using custom command node.
- (API) Introduced `PlayerService`, and added `Server#getPlayerService` method. Note that there are also a number of method moves from `Server` to `PlayerService`.
- (API) Added `ClientDisconnectEvent`.
- (API) Added `PlayerJoinEvent#joinMessage` and `PlayerQuitEvent#quitMessage` and correspond setters. Now these messages can be edited by plugins.
- (API) Added `BlockBaseComponent#getDropXpAmount` method.
- (API) Added option `tickDimensionInParallel` to control whether tick dimensions in the same world in parallel during world tick.
- (API) Added `BlockFace#fromMinecraftCardinalDirection` and `BlockFace#isVertical` methods.
- (API) Added `BlockStateWithPos#getBlockEntity` method.
- (API) Implemented chest pairing, and several related interfaces & objects including `BlockEntityPairableComponent`,
    `DoubleChestContainer`, are added to api module, see commit history for more details.
- (API) Added support for the new elements in simple and custom form: divider and header. Label element now can be added
  to simple form as well.
- Added support for bedrock 1.21.70.
- Implemented ores.
- Added `InternalRegistries#TRIM_PATTERNS` and `InternalRegistries#TRIM_MATERIALS`.

### Changed

- (API) Renamed durability related methods in`ItemBaseComponent`:
  - `getDurability()` -> `getDamage()`
  - `setDurability()` -> `setDamage()`
  - `tryReduceDurability()` -> `tryIncreaseDamage()`
- (API) Moved player, ban/whitelist related methods from `Server` to the new `PlayerService` class.
- (API) Moved `Server#getNetworkInterface` method from `Server` to the new `PlayerService` class. `NetworkInterface` is now held by `PlayerService` rather than `Server`.
- (API) Moved `PlayerQuitEvent#reason` to the newly introduced event `ClientDisconnectEvent`. `PlayerQuitEvent` will only be called for already logged in player now.
- (API) Removed `BlockEntityBaseComponent#clearCustomName` method, use `BlockEntityBaseComponent#setCustomName(null)` to clear custom name.
- Removed `BlockEntityContainerHolderComponentImpl#constructor(Supplier<Container>, Consumer<ContainerViewer>, Consumer<ContainerViewer>)`.
- Optimized `setItemStack` handling in `ItemStackRequestPacket` to no longer require sending an `InventorySlotPacket` (Issue #66).
- Use `AtomicReference<ServerState>` instead of `AtomicBoolean`'s fields for state management

### Fixed

- (API) Fixed `BlockStateData#isTransparent()` method.
- Fixed torch placement on non-full blocks (e.g., walls, fences) and automatic placement detection.
- Fixed skin display issue in 1.21.60.
- Fixed EnderChest behaviour and drops.
- Fixed LightningRod placing.
- Fixed Door placing.
- Fixed two bugs in `/give` command which make the gave item amount being shown incorrectly.
- Fixed block breaking time calculation to match Minecraft's official breaking time formula, resolving a rounding issue that caused slight discrepancies.
- Fixed a bug in sky light calculation which causes the sky light stop propagating.

### Removed

- (API) Removed `Server#findOnlinePlayerByName` method which is duplicated with `Server#getOnlinePlayerByName`.
- Removed loot table api.

## 0.2.0 (API 0.5.0) - 2025-3-3

<small>[Compare with 0.1.3](https://github.com/AllayMC/Allay/compare/0.1.3...0.2.0)</small>

### Added

- (API) Implemented ender chest, and several related interfaces & objects including `BlockEntityEnderChest`,
  `EnderChestContainer`, are added to api module, see commit history for more details.
- (API) Added `VoxelShape#intersectsRay` method, which can determine whether the given ray intersects the voxel shape.
- (API) Added multiple `BlockUpdateService#scheduleRandomBlockUpdate` method overloads, which can schedule a random block
  update at a specified position. These methods are used by fire block currently to make it able to change fire spreading
  speed by changing random block update speed.
- (API) Added `WorldSettings.WorldSetting#runtimeOnly`, If set this to true, the information of this world will not be saved
  to world-settings.yml, therefore it won't be loaded after the server restarted. This is useful for world created for game
  room by plugin and will be deleted when shutdown.
- (API) Added `PluginManager#registerCustomSource` and `PluginManager#registerCustomLoaderFactory`, custom plugin loaders
  and sources can be registered by plugin now.
- (API) Added `Form#onClose(Consumer<ModalFormCancelReason>)` method that can be used to set a callback which will be called
  with the close reason when the form is closed. The old `Form#onClose` method is still available that just ignores the reason.
- (API) Added `ChunkService#removeUnusedChunksImmediately` method that can remove unused chunks immediately. Also, the `/gc` command
  will call this method in all dimensions now.
- (API) Added `ItemBaseComponent#getLockMode` and `ItemBaseComponent#setLockMode` methods to get and set the lock mode of an item.
- (API) Added `ChunkSection`, chunk section can be obtained from chunk.
- (API) Added `EnchantmentType#canBeAppliedTo`, `EnchantmentType#getAppliableType` and `ApplicableType`, these methods can be used to
  check if an enchantment type can be applied to a specific item type.
- (API) Introduced new item tag `allay:head` and helper method `ItemHelper#isHead` which can be used to check if an item is a head item.
- (API) Introduced `ItemStack#isAllEnchantmentsAvailableInEnchantTable` method. This method is used in book item.
- (API) Introduced `EntityBaseComponent#getStatus` which can get the status of an entity. This status replaced the old boolean flags
  such as `spawned`, `dead`, `willBeSpawnedNextTick` and provide better stability and extensibility.
- (API) Added `ClientConnectEvent#setDisconnectReason` and `PlayerLoginEvent#setDisconnectReason` methods to set the disconnect reason
  that will be shown to the client when cancelling these events.
- (API) Implemented flower pot, and add custom block tag `allay:pottable_plant` which mark thant the plant can be potted.
- (API) Introduced PDC (Persistent Data Container) system. The PDC is a way to store custom data on a whole range of objects, such as
  items, entities, block entities and world. More PDC types will be added in the future.
- (API) Added `ItemType#getItemData` method which replaces the old `ItemDataComponent`.
- (API) Introduced new option `entity-auto-save-cycle` in `ServerSettings` to control the interval of entity auto save.
- (API) Entities are now held by `EntityService` directly, and a variety of new methods are added into `EntityService`. See the commit
  history for more details.
- (API) Introduced `WorldStorage#readEntities`, `WorldStorage#writeEntities` and their correspond sync methods. These methods are used
  to read and write entities in a specified chunk area.
- (API) Introduced a variety of methods for sending toast, title, subtitle and actionbar text to player, and new command `/title` is added.
- (API) Introduced `CommandNode#permission` method which can be used to set the permission requirement for accessing a specified command node.
  This is useful for setting permission for sub commands.
- Add support for bedrock 1.21.60.
- Add support for the new entity storage format used in 1.18.30+. Now entities in newer vanilla maps can be loaded correctly.
- Implemented reeds (also called sugar cane) and cactus.
- Implemented `UpdateSubChunkBlocksPacket` related logic, which will make client load large range block updates much quicker (e.g.
  using `/fill` command to fill a large area).
- Introduced `ChunkSectionLocks`, which replaced the old `StampedLock` in `Chunk`. Instead of locking the whole chunk when reading/writing
  blocks/biomes, only the related chunk section will be locked now. This should improve the performance of chunk reading/writing.
- Added `PluginDisableEvent` and `PluginEnableEvent` events.

### Changed

- (API) We now used `double`  instead of `float` in entity location, motion, aabb and some other classes which require high precision.
  This should improve the accuracy of entity movement and collision detection.
- (API) Renamed `FullContainerTypeBuilder` to `Builder`.
- (API) Moved method `Chunk#isLoaded` to `UnsafeChunk#isLoaded`.
- (API) Made method `Dimension#createUpdateBlockPacket` private, consider using `Dimension#sendBlockUpdateTo` method instead.
- World will be skipped if failed to be load.
- (API) Moved and renamed `UnsafeChunk#index` method to `HashUtils#hashChunkSectionXYZ`.
- (API) Refactored `Chunk` and `UnsafeChunk`, now `Chunk` works more likely a wrapper for `UnsafeChunk` that provides
  safe access to chunk data in multi-threads environment.
- (API) Replaced `Chunk#batchProcess` method with new `Chunk#applyOperation` and `Chunk#applyOperationInSection` methods.
- (API) Moved inner class `ItemArmorBaseComponent#ArmorType` to package `org.allaymc.api.item.data`.
- (API) Changed the default value of `ServerSettings#GenericSettings#defaultPermission` to `PlayerPermission.MEMBER`.
- (API) `VoxelShape` have being refactored. Now it doesn't allow using `vacancy`, this change is required by physics engine to fix some bugs.
- (API) Renamed `BlockState#setProperty` and `BlockState#setProperties` to `BlockState#setPropertyValue` and `BlockState#setPropertyValues` to match the getter methods.
- (API) Refactored the creative item registry and related classes & methods, which allow plugin to customize item groups.
- (API) Renamed `Command#COMMAND_PERM_PREFIX` to `Command#COMMAND_PERMISSION_PREFIX`.
- Main thread will sleep a short time if gui is enabled when the server exits abnormally. This gives user time to see what goes wrong.
- Server won't crash if failed to load the descriptor of a plugin now. An error message will be print to the console instead.
- Server won't crash if failed to create world generator. Void world generator will be used instead.
- It is not allowed to reset the dimension of player back to null now, and doing such thing will result in an exception.
- Changed `PlayerAuthInputPacketProcessor#TELEPORT_ACK_DIFF_TOLERANCE` from `public` to `protected`.
- Scheduled block updates are saved in vanilla format instead of our custom format now. This should improve the compatibility with vanilla map.

### Fixed

- Plugins are able to create their own world generator implementation now. In previous versions a ClassCastException would be thrown when
  initializing the dimension.
- Explosion now calculates entity exposure correctly. In previous version any non-air block will block the explosion ray.
- Explosion damage now scales with game difficulty.
- Fixed a rare NPE exception that may occur if player disconnect when joining the server.
- Fixed missing block breaking particle when breaking block.
- Item entity will be despawned immediately instead of having a `dead timer` when its health become 0. It's dead smoke is also removed.
- Fixed the bug that unusable enchantment types may be shown in enchantment table.
- Fixed the bug that `/give` command will stack item that cannot be stacked like sword.
- Fixed the bug that damage that smaller than 1 will never kill an entity even if the entity has only 1 health.
- Fixed the bug caused by incorrect initial value of runtime id counter. The initial value should be 1 instead of 0.
- Fixed the bug that `/alwaysday` command actually do the opposite thing.
- Several bugs in physics engine, including wrong collision detection and wrong movement calculation are fixed.
- Gamerule `doDaylightCycle` now works correctly.
- Fixed touch-mode player (phone player usually) block breaking with "delayed block breaking" option enabled.
- A series of getter/setter method in `Dimension` class now won't load the chunk. This behavior causes a lot of deadlock in the past.

### Removed

- (API) Removed `UnsafeChunk#SECTION_SIZE`.
- (API) Removed `UnsafeChunk#setHeight`, it is an unexpected behavior to set height of a chunk without block change.
- (API) Removed `BlockBaseComponent#canKeepExisting` method to eliminate ambiguity.
- (API) Removed `PlayerStorage#tick` and `PlayerStorage#shutdown` methods, these methods shouldn't be in api module.
- (API) Removed `Registries#BLOCK_STATE_DATA`. This registry is moved to `InternalRegistries`.
- (API) Removed `Registries#ITEM_DATA`. This registry is moved to `InternalRegistries`.
- (API) Removed `VoxelShapes#buildStairShape` method, we now have accurate collision shape data dumped from BDS.
- (API) Removed the old `BlockState#toNetworkBlockDefinition` method, and `BlockState#toNetworkBlockDefinitionRuntime` was renamed without `Runtime` suffix.
- (API) Removed `ItemDataComponent`. `ItemData` is now located in `ItemType<?>`.
- (API) Removed `Dimension#getEntityByRuntimeId`. This method is replaced by `EntityService#getEntityByRuntimeId`.
- (API) Removed `Dimension#getEntityPhysicsService`. This method is replaced by `EntityService#getPhysicsService`.
- (API) Removed `ChunkLoader#spawnEntity` and `ChunkLoader#despawnEntity` methods.
- (API) Removed `UnsafeChunk#getEntity`, `UnsafeChunk#getEntities`, `UnsafeChunk#spawnEntitiesTo` and
 `UnsafeChunk#despawnEntitiesFrom` methods. Because entity is not held by chunk now.
- Removed `Extension#afterServerStarted` method.
- Removed `org.allaymc.server.datastruct.collections.nb.*`, we now use the implementations provided by JCTools. Consider using `NonBlockingHashMap`
  and `NonBlockingHashMapLong` if your plugins use these classes.

## [0.1.3](https://github.com/AllayMC/Allay/releases/tag/0.1.3) (API 0.4.0) - 2025-1-17

<small>[Compare with 0.1.2](https://github.com/AllayMC/Allay/compare/0.1.2...0.1.3)</small>

### Added

- (API) Introduced `AllayAPI#isDevBuild` to let plugin know if the current server is a dev build.
- (API) Introduced `BlockCustomTags#WATER` and `BlockCustomTags#LAVA` to allow checking if a block is water or lava
  easier. This is because there are two types of water (`minecraft:water` and `minecraft:flowing_water`) and lava (
  `minecraft:lava` and `minecraft:flowing_lava`) in vanilla.
- (API) Introduced `Dimension#getLiquid`, `Dimension#setLiquid` and `Dimension#removeLiquid` methods to help plugin
  operate liquid easier.
- (API) Introduced `Dimension#addLevelSoundEvent(Vector3ic pos, SoundEvent soundEvent, int extraData)` and
  `Dimension#addLevelSoundEvent(Vector3fc pos, SoundEvent soundEvent, int extraData)`.
- (API) Added `EntityTrampleFarmlandEvent`. EntityTrampleFarmlandEvent is called when a farmland is trampled by an
  entity.
- (API) Added `BlockGrowEvent` which will be called when crops grow.
- (API) Added two overloads `LightService#getInternalLight(Vector3ic)` and `LightService#getSkyLight(Vector3ic)`, they
  have the same functionality as `LightService#getXXXLight(int, int, int)`.
- (API) `BlockContainer#getBlockPos` and `BlockContainer#setBlockPos` now return/require `Position3ic` instead of
  `Vector3ic`, this enables us to get the dimension information of a `BlockContainer`.
- (API) Implemented brewing stand, and several related interfaces & objects including `BlockEntityBrewingStand`,
  `BrewingStandContainer`, `Registries#POTION_MIX_RECIPES`, `PotionMixRecipe` are added to api module, see commit
  history for more details. Added `BrewingStandBrewEvent`, `BrewingStandConsumeFuelEvent` and
  `BrewingStandStartBrewEvent` events.
- (API) Implemented slab, and several related interfaces are added to api module.
- (API) Introduced `BlockBaseComponent#combine` method which is used by slab. For the details of this method, see the
  javadoc.
- (API) Implemented beacon block, and several related interfaces are added to api module.
- (API) Implemented brewing stand, and several related interfaces & objects including `BlockEntityBrewingStand`,
  `BrewingStandContainer`, `Registries#POTION_MIX_RECIPES`, `PotionMixRecipe` are added to api module. See commit
  history for more details.
- (API) Implemented picking block with block entity data. The following methods are added:
  `ItemBaseComponent#getBlockEntityNBT`, `ItemBaseComponent#setBlockEntityNBT`, `ItemBaseComponent#clearBlockEntityNBT`
  and `ItemBaseComponent#hasBlockEntityNBT`.
- (API) Implemented TNT. There is now a new class called `Explosion` which can be used by plugin to make custom
  explosion.
- (API) Introduced a number of overloads of `Dimension#addSound`.
- (API) Introduced method `EntityAttributeComponent#supportAttribute` to check if the entity support specified attribute
  type.
- (API) Introduced methods `DamageContainer#blockExplosion` and `DamageContainer#entityExplosion` to create explosion
  related damage.
- (API) Introduced methods `EntityBaseComponent#getDragFactorOnGround` and `EntityBaseComponent#getDragFactorInAir`,
  which can be used to customize the drag factor of an entity.
- (API) Introduced event `EntityExplodeEvent` which will be called when tnt or creeper(WIP) is about to explode.
- (API) Introduced method `EntityBaseComponent#isTouchingWater` to check if an entity is touching water.
- (API) Implemented TNT entity, block and related features. Several related interfaces are added to api module.
- Implemented trapdoor except redstone feature (Redstone feature requires the implementation of redstone system).
- Implemented sponge and wet sponge.
- Implemented farmland and hoe.
- Implemented most of the crops, including wheat, potato, carrot, beetroot, melon and pumpkin.
- Introduced [sentry](https://www.sentry.io) to capture exception and upload them to sentry server automatically, which
  helps us to track and fix bug more efficiently. Sentry is only enabled in non-dev version.
- Server version will also be uploaded to bStats now.
- Introduced `Extension#afterServerStarted` method which will be called after the server is started.

### Changed

- (API) Removed `BlockFace#toStairDirectionValue`, this method shouldn't exist in api module.
- (API) `BlockTags`, `BlockCustomTags`, `ItemTags` and `ItemCustomTags` are now annotated with
  `@MinecraftVersionSensitive` as these tags may change between different versions.
- (API) The second parameter of `BlockBaseComponent#onEntityFallOn` now accepts `BlockStateWithPos` instead of
  `BlockState`.
- (API) `EntityBaseComponent#getBlockStateStandingOn` now return `BlockStateWithPos` instead of `BlockState`.
- (API) Removed `BlockFace#horizontalIndex` which is useless.
- (API) Removed `ScoreboardService#ServerEventListener` as it is not supposed to be touched by plugin.
- (API) Methods `BlockEntityFurnaceBaseComponent#getStoredXP` and `BlockEntityFurnaceBaseComponent#setStoredXP` now
  accept `int` instead of `float`.
- (API) Renamed `Structure#pickStructure` to `Structure#pick`.
- (API) Renamed `ItemItemStorableComponentImpl` to `ItemStuffStorableComponentImpl`, and now `ItemShulkerBoxStack`
  extends `ItemStuffStorableComponent`.
- (API) Removed methods `EntityAttributeComponent#supportHealth` and `EntityAttributeComponent#supportAbsorption`.
  Consider using new method `EntityAttributeComponent#supportAttribute`.
- (API) Renamed method `EntityBaseComponent#getBaseOffset` to `EntityBaseComponent#getNetworkOffset` for better
  understanding.
- (API) Removed method `Dimension#setBlockStates`. This method is considered to be unsafe as it will only set the block
  state, block entity won't be created if the block has block entity. Further research is currently needed.
- Removed useless class `PackageClassLoaderUtils`, dependency `org.reflections.reflections` is also removed.
- Added `-dev` suffix to api version in development build.
- Changed `ContainerActionProcessorHolder` to a final class instead of an interface, because this abstraction is
  meaningless.
- Changed `enableGui` to `enable-gui` in `server-settings.yml`
- Disabled packet limit only in dev build.
- Optimized the performance of physics calculation when there are a lot of entities.
- Changed the gravity of item and xp orb entity to 0.04f to better match vanilla behavior.

### Fixed

- (API) `BlockHangingSignBehavior` now extends `BlockEntityHolderComponent<BlockEntityHangingSign>` which was forgotten
  to be added.
- Fixed several bugs that can led falling block keep existing even if it is already on ground or can't move.
- Fixed the `ClassCastException` when breaking shulker box.
- Fixed the bug that interacting with door doesn't have any sound.
- Waxing copper-made block using honeycomb won't call `BlockFadeEvent` now.
- Fixed the bug that player can still open enchant table even if he is sneaking.
- Fixed NaN motion caused by liquid in some very special cases.
- Fixed the bug that entity will still get ticked after called `removeEntity()`.
- Fixed the bug that player's pos sometimes get frozen after teleport. This is caused by the issue that sometimes client
  doesn't send back teleport ack after server sends teleport packet to client.
- Fixed the bug that flint and steel durability reduced in creative mode.
- Fixed the network offset of item entity. Its visual position should now be normal.
- Liquid won't be broken into item by falling block now.

## [0.1.2](https://github.com/AllayMC/Allay/releases/tag/0.1.2) (API 0.3.0) - 2024-12-31

<small>[Compare with 0.1.1](https://github.com/AllayMC/Allay/compare/0.1.1...0.1.2)</small>

### Added

- (API) Added an extra argument to `Dimension#breakBlock` method to control if the block breaking particle should be
  played.
- (API) Added `LiquidHardenEvent#setHardenedBlockState` method to allow changing the hardened block state.
- (API) Introduced `MathUtils#normalizeIfNotZero` method to normalize a vector only if it is not zero, this method
  prevents NaN caused by `Vector3fc#normalize` method.
- (API) Introduced `EntityBaseComponent#computeLiquidMotion` method to control whether an entity has liquid motion.
- (API) Introduced `EntityDamageComponent#hasDrowningDamage` method to control whether an entity has drowning damage.
- Added liquid motion for water and lava. Now entity will be moved by liquid flow if it is in the liquid.
- Pos sent by the client will only be handled when the pos is changed, as `PlayerAuthInputPacket` is sent every tick but
  the player may not move every tick.

### Changed

- (API) Removed `BlockFace#getBlockFaceByStairDirectionValue` method, some fields in `VoxelShapes` are also private for
  better
  maintainability now.
- (API) Remove `fat-aabb-margin` and `stepping-offset` fields from server settings as these properties shouldn't be
  touched by users.
- Introduced tag name constants for where a large number of NBT saving and reading are involved. This improved the
  maintainability of the project.
- Introduced better names for some of the fields in `PlayerAuthInputPacketProcessor`, this improved the readability of
  the code.

### Fixed

- (API) Corrected the return type of `Dimension#breakBlock(Vector3ic, ItemStack, Entity)` from `void` to `boolean`. Some
  overloads for this method are also added.
- (API) Fixed incorrect bit operations in `BlockLiquidBaseComponent#getLiquidBlockState` and
  `BlockLiquidBaseComponent#getLiquidBlockState#getDepth`, although it seems that they do not cause any issues.
- Block breaking particle won't be sent if block is broken by flowing liquid.
- Water placed in nether dimension will disappear immediately now.
- `Pos`, `Motion` and `Rotation` in entity nbt are now saved as list tag instead of compound tag to match vanilla.
  This also fixed the bug that entities being spawned in incorrect position when placing structure using `/structure`
  command. Please note that this change is not backward compatible and will break the old world and player data.
- Fixed several NaNs caused by `Vector3fc#normalize` methods in the physics engine, and now setting the motion/location
  of an entity to a vector which contains NaN will result in an exception.
- EntityItem now won't have drowning damage when it is in water, this bug causes entity item died after a period of time
  in water.
- `ServerboundLoadingScreenPacket` won't spam warnings in the console when switching dimension now.
- Fixed the bug that sometimes there may be `NaN` values in `PlayerAuthInputPacket`, this bug is also confirmed in
  df-mc ([issue#425](https://github.com/df-mc/dragonfly/issues/425)).

## [0.1.1](https://github.com/AllayMC/Allay/releases/tag/0.1.1) (API 0.2.0) - 2024-12-29

<small>[Compare with 0.1.0](https://github.com/AllayMC/Allay/compare/0.1.0...0.1.1)</small>

### Added

- (API) Added `TextFormat#MATERIAL_RESIN`.
- (API) Entity#teleport method now accepts an extra `Reason` argument.
- (API) Added structure API by @harry-xi.
- (API) Added a number of new methods to `BlockLiquidBaseComponent`.
- (API) Added `LiquidFlowEvent`, `LiquidDecayEvent` and `LiquidHardenEvent`.
- (API) Added `BlockBehavior#afterPlaced` and `BlockBehavior#afterReplaced` that are correspond to
  `BlockBehavior#onPlace` and `BlockBehavior#onReplace`.
- (API) Added `BlockStateData#liquidReactionOnTouch`, there are also a number of new methods in `BlockStateData`.
- Added `/structure` command to manage structures.
- Implemented liquid features including water, lava and related features.

### Changed

- (API) `GameRules` is changed to an interface.
- Breaking block related warnings are now moved to debug channel.
- Improved code readability for I18n module.
- NBT library is now updated to 3.0.10.
- `/gametest` command is now only available in dev build.
- Removed the `dimension` field in `BlockBreakEvent`, which is duplicated with `blockStateWithPos#dimension`.
- Removed unused `cn.powernukkitx:libdeflate-java` library, related classes are also removed.

### Fixed

- (API) Fixed exception when setting item count or meta to zero.
- (API) `VoxelShapes#buildLiquidShape` now works correctly.
- (API) Correct `BlockStateData#canContainLiquid` to `BlockStateData#canContainLiquidSource`.
- Passing non-positive amount or negative meta arguments to `/give` command now will result in a syntax error.
- Entity#teleport method now will reset fall distance correctly.
- Fixed visual flashes when eating chorus fruits.
- Fixed incorrect comparison of `Position3x#dimension`.
- Fixed a number of falling block related bugs.
- Fixed incorrectly drops when destroying the upper part of a door in creation mode.

## [0.1.0](https://github.com/AllayMC/Allay/releases/tag/0.1.0) (API 0.1.0) - 2024-12-22

Hello Allay! This is the first release of Allay.
