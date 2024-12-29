# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Each release is associated with a specific API version,
and any changes to API will have a prefix `(API)`.

Unless otherwise specified, any version comparison below is the comparison of server version, not API version.

## 0.1.2 (API 0.3.0) - Unreleased

<small>[Compare with 0.1.1](https://github.com/AllayMC/Allay/compare/0.1.1...HEAD)</small>

### Added

- (API) Added an extra argument to `Dimension#breakBlock` method to control if the block breaking particle should be
  played.

### Fixed

- (API) Corrected the return type of `Dimension#breakBlock(Vector3ic, ItemStack, Entity)` from `void` to `boolean`. Some
  overloads for this method are also added.
- Block breaking particle won't be sent if block is broken by flowing liquid.
- Water placed in nether dimension will disappear immediately now.
- `Pos`, `Motion` and `Rotation` in entity nbt are now saved as list tag instead of compound tag to match vanilla,
  this also fixed the bug that entities being spawned in the right position when placing structure using `/structure`
  command. Please note that this change is not backward compatible and will break the old world and player data.

## 0.1.1 (API 0.2.0)

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
