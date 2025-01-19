# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Each release is associated with a specific API version,
and any changes to API will have a prefix `(API)`.

Unless otherwise specified, any version comparison below is the comparison of server version, not API version.

## 0.1.4 (API 0.5.0) - Unreleased

<small>[Compare with 0.1.3](https://github.com/AllayMC/Allay/compare/0.1.3...HEAD)</small>

### Changed

- World will be skipped if failed to be load.
- Main thread will sleep a short time if gui is enabled when the server exits abnormally. This gives user time to see what goes wrong.

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
- (API) Implemented brewing stand, and several related interfaces & objects including `BlockEntityBrewingStand`, `BrewingStandContainer`,
  `Registries#POTION_MIX_RECIPES`, `PotionMixRecipe` are added to api module, see commit history for more details. Added `BrewingStandBrewEvent`,
  `BrewingStandConsumeFuelEvent` and `BrewingStandStartBrewEvent` events.
- (API) Implemented slab, and several related interfaces are added to api module.
- (API) Introduced `BlockBaseComponent#combine` method which is used by slab. For the details of this method, see the javadoc.
- (API) Implemented beacon block, and several related interfaces are added to api module.
- (API) Implemented brewing stand, and several related interfaces & objects including `BlockEntityBrewingStand`,
  `BrewingStandContainer`, `Registries#POTION_MIX_RECIPES`, `PotionMixRecipe` are added to api module. See commit
  history for more details.
- (API) Implemented picking block with block entity data. The following methods are added: `ItemBaseComponent#getBlockEntityNBT`,
  `ItemBaseComponent#setBlockEntityNBT`, `ItemBaseComponent#clearBlockEntityNBT` and `ItemBaseComponent#hasBlockEntityNBT`.
- (API) Implemented TNT. There is now a new class called `Explosion` which can be used by plugin to make custom explosion.
- (API) Introduced a number of overloads of `Dimension#addSound`.
- (API) Introduced method `EntityAttributeComponent#supportAttribute` to check if the entity support specified attribute type.
- (API) Introduced methods `DamageContainer#blockExplosion` and `DamageContainer#entityExplosion` to create explosion related damage.
- (API) Introduced methods `EntityBaseComponent#getDragFactorOnGround` and `EntityBaseComponent#getDragFactorInAir`, which can be used to
  customize the drag factor of an entity.
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
- (API) Removed method `Dimension#setBlockStates`. This method is considered to be unsafe as it will only set the block state,
  block entity won't be created if the block has block entity. Further research is currently needed.
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
- Fixed the bug that player's pos sometimes get frozen after teleport. This is caused by the issue that sometimes client doesn't send 
  back teleport ack after server sends teleport packet to client.
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
