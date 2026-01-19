# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Each release is associated with a specific API version, and any changes to API will have a prefix `(API)`.

Unless otherwise specified, any version comparison below is the comparison of the server version, not the API version.

# 0.10.6 (API 0.23.0) - Unreleased

<small>[Compare with 0.10.5](https://github.com/AllayMC/Allay/compare/0.10.5...HEAD)</small>

### Added

- (API) Added method `Dimension.updateComparatorOutputLevel()` for updating comparators that may be reading a block's comparator output, including second-level neighbors (comparators behind solid blocks).
- (API) Added method `Container.calculateComparatorSignal()` for calculating the comparator signal strength based on container contents.
- (API) Added method `ItemMusicDiscBaseComponent.getComparatorSignal()` for getting the comparator signal output for music discs.
- (API) Added `comparatorSignal` field to `DiscType` enum for each music disc's comparator output value.
- Implemented comparator signal reading for various blocks: containers (chest, barrel, hopper, shulker box), item frames, cake, candle cake, jukebox, campfire, and end portal frame.
- (API) Added a new overload to method `WorldViewer.viewPlayerEmote` which accepts a boolean `silence` parameter.
- (API) Introduced new thread type `ThreadType.DIMENSION` which responses the ticking thread of a dimension.
- (API) Added events `PlayerPickupItemEvent` and `PlayerPickupArrowEvent` for player picking up item and arrow entities.
- (API) Initial implementation of custom block support:
  - Added `CustomBlockDefinitionGenerator` for generating custom block definitions with support for various components like geometry, materials, collision box, selection box, and transformation.
  - Added `AllayBlockType.Builder.autoCreateItemType(boolean)` option to automatically create the corresponding item type for a custom block.
- (API) Implemented piston and sticky piston:
  - Added `BlockPistonEvent` event which is called when a piston extends or retracts, allowing plugins to monitor and cancel piston operations.
  - Added `BlockEntityPistonArm` and `BlockEntityPistonArmBaseComponent` interfaces for piston arm block entities with properties like facing direction, sticky state, progress, and attached blocks.
  - Added `BlockEntityMovingBlock` and `BlockEntityMovingBlockBaseComponent` interfaces for moving block entities that handle the visual block movement animation.
  - Added `BlockEntityTypes.PISTON_ARM` and `BlockEntityTypes.MOVING_BLOCK` block entity types.
  - Added block tags `BlockTags.UNPUSHABLE`, `BlockTags.UNPULLABLE`, `BlockTags.BREAK_WHEN_PUSHED`, and `BlockTags.CAN_STICK_BLOCKS` to control piston push/pull behavior for different block types.
  - Added sounds `SimpleSound.PISTON_PUSH` and `SimpleSound.PISTON_PULL`.
- (API) Added block tag `BlockTags.REDSTONE_WIRE_CONNECT_TO` for controlling redstone wire connection behavior.
- (API) Added method `BlockEntityBaseComponent.saveCleanNBT()` to save clean NBT data without position-related information, useful for piston block movement.
- (API) Added methods `BlockEntityContainerHolderComponent.shouldDropItemOnBreak()` and `setDropItemOnBreak(boolean)` for controlling whether items should drop when the block is broken, useful for pistons moving container blocks.
- (API) Added method `Dimension.updateAllAround()` for triggering second-order neighbor updates (updates adjacent blocks and each adjacent block's neighbors).
- (API) Added method `BlockBaseComponent.onMoved()` callback for blocks to handle being moved by pistons. Observers use this to trigger pulses when pushed.
- Implement candle, cake and candle cake.
- Improved server-authorized block breaking logic in `PlayerAuthInputPacketProcessor` for better accuracy and reliability.

### Fixed

- Fixed a bug where `NpcDialoguePacket` used `RuntimeEntityId` instead of `UniqueEntityId`, causing NPC dialogs to not display for non-actual players (e.g., fake players created by plugins).
- Fixed a bug where skin can't be set for fake player.
- Fixed duplicate "Client disconnected" messages during server shutdown caused by players not being removed from the dimension's player list when disconnecting.
- Fixed method `WorldViewer.viewPlayerEmote` not working.
- Fixed duplicate player quit messages caused by `DISCONNECTED` state being set multiple times due to missing state check in `PacketProcessorHolder.setClientState()`.
- Fixed redstone torch incorrectly turning off when receiving weak power through solid blocks from redstone wire.
- Fixed redstone wire not correctly pointing to all connected directions in L-shape, T-shape, and cross configurations.
- Fixed observer placement direction - the observing face now correctly points in the direction the player is looking.
- Fixed lever placement check - now verifies that the attachment surface is a full face instead of just checking if the block is solid.
- Fixed block replacement order - `onReplace` is now called before `onPlace` to ensure old block entities are removed before new ones are created, preventing block entity type mismatch issues.
- Fixed piston to trigger second-order neighbor updates after animation completes at both original and target positions.
- Fixed several observer bugs:
  - Not responding to short redstone pulses (e.g., from other observers) - implemented proper activation delay and scheduling.
  - Face-to-face observers not forming oscillators - state changes now notify all neighbors including the observed face.
  - Oscillator frequency being too fast - corrected activation delay to 4 game ticks (2 redstone ticks) to match MCBE behavior per MCPE-15793.
- Fixed repeaters/comparators not responding to short input pulses (e.g., from observers) - implemented latching behavior to capture pulses even if input is gone when scheduled update runs.
- Fixed `Dimension.getWeakPowerAt()` not considering strong power through solid blocks, causing pistons to not extend when powered indirectly.
- Fixed several blocks not properly checking attachment surface support (now uses collision shape instead of just solid flag):
  - Item frames
  - buttons
  - pressure plates
  - redstone torch
  - redstone diodes (repeaters/comparators)

# 0.10.5 (API 0.22.0) - 2026/1/15

<small>[Compare with 0.10.4](https://github.com/AllayMC/Allay/compare/0.10.4...0.10.5)</small>

### Added

- (API) Added method `Player.sendCooldown()`.
- (API) Added event `ItemDamageEvent` which is called when an item is about to be damaged.
- (API) Added method `ItemBaseComponent.calculateAttackDamage(Entity, Entity)` for context-aware attack damage calculation.
- (API) Implemented lingering potion and area effect cloud:
  - Added `EntityAreaEffectCloudBaseComponent` interface with radius, duration, and reapplication delay properties.
  - Added `EntityPotionComponent` interface for entities that hold potion types (splash potion, lingering potion, area effect cloud, arrow).
  - Removed `getPotionType()` and `setPotionType()` from `EntityArrowBaseComponent`, use `EntityPotionComponent` instead.
- (API) Implemented mace item:
  - Added sounds `SimpleSound.MACE_SMASH_AIR`, `SimpleSound.MACE_SMASH_GROUND` and `SimpleSound.MACE_SMASH_HEAVY_GROUND`.
  - Added particle `SimpleParticle.SMASH_ATTACK_GROUND_DUST`.
- (API) Implemented wind charge:
  - Added `WindExplosion` class in `org.allaymc.api.world.explosion` package for wind charge knockback effects.
  - Added particles `SimpleParticle.WIND_EXPLOSION` and `SimpleParticle.BREEZE_WIND_EXPLOSION`.
  - Added sounds `SimpleSound.WIND_CHARGE_BURST` and `SimpleSound.BREEZE_WIND_CHARGE_BURST`.
  - `Wind Charged` effect now triggers wind explosion on entity death.
- (API) Added `PotionType.WIND_CHARGED`, `WEAVING`, `OOZING`, and `INFESTED` for 1.21 tipped arrows and potions.
- Implemented Wind Burst enchantment effects for mace attacks.
- Implemented Density enchantment damage bonus for mace.
- Implemented Breach enchantment effect for mace armor reduction.
- Implemented mending repairs from experience orbs.

### Changed

- (API) Classes `Explosion` and `FireworkExplosion` are moved from package `org.allaymc.api.world` to package `org.allaymc.api.world.explosion`.
- (API) Corrected field name `EffectTypes.WEAVING_OMEN` to `EffectTypes.WEAVING`.

### Fixed

- (API) Renamed method `HasAABB.getBaseAABB()` back to `HasAABB.getAABB()`.
- Fixed a possible NPE in method `EntityPlayerBaseComponent.setCooldown()`.
- Fixed a bug where aabb not being updated in the physics engine due to missing `join()` call on parallel forEach.
- Fixed a bug where sometimes the player can't respawn.
- Fixed `PotionType` enum order where `STRONG_SLOWNESS` was incorrectly placed, causing potion effects to be mismatched (e.g., healing potion giving water breathing).
- Fixed enchant table option cleanup when closing the table to avoid stale cached entries.
- Fixed wind burst enchantment max level and applicable type.
- Fixed 2x2 crafting grid.

# 0.10.4 (API 0.21.0) - 2026/1/12

<small>[Compare with 0.10.3](https://github.com/AllayMC/Allay/compare/0.10.3...0.10.4)</small>

### Added

- (API) Implemented world feature system and tree saplings:
  - Added `WorldFeature` interface and `WorldFeatureContext` class for defining world generation features.
  - Added `WorldFeatures` class and `Registries.WORLD_FEATURES` registry.
  - Implemented tree saplings that can grow into trees when bone mealed or after random ticks.
  - Added tree features for all wood types: Oak, Birch, Spruce, Jungle, Acacia, Dark Oak, Cherry, Mangrove, Pale Oak, and their variants (Fancy Oak, Tall Birch, Mega Jungle, Mega Pine, Mega Spruce).
- (API) Added `Entity.setPersistent()` method to control entity persistence.
- (API) Added redstone power methods to `BlockBaseComponent`:
  - `getWeakPower(Block, BlockFace)` - gets weak power output from a block.
  - `getStrongPower(Block, BlockFace)` - gets strong power output from a block.
  - `isPowerSource()` - checks if a block is a redstone power source.
  - `MAX_REDSTONE_POWER` constant (15).
- (API) Added `BlockEntityNoteblockBaseComponent.isPowered()` and `setPowered()` methods to track redstone power state.
- (API) Added sound `PowerSound` which is used by redstone components such as lever.
- (API) Implemented basic redstone system, here is a list of implemented redstone components:
  - Redstone Wire
  - Redstone Block
  - Redstone Lamp
  - Redstone Torch
  - Pressure Plate
  - Lever
  - Button
  - Door
  - Trapdoor
  - Fence Gate
  - Observer
  - Noteblock (responds to redstone signal)
  - Hopper (stops transferring items when powered)
  - Daylight Detector (normal and inverted)
  - Trapped Chest (outputs signal based on viewer count)
  - Redstone Repeater
  - Redstone Comparator
  - Target (outputs signal when hit by projectiles)
  - Dragon Head and Piglin Head (animate when powered)
- (API) Implemented campfire and soul campfire:
  - Added `BlockEntityCampfire` and `BlockEntityCampfireBaseComponent` interfaces.
  - Added `CampfireSmeltEvent` event.
  - Added `DamageType.CAMPFIRE` and `DamageContainer.campfire()` for campfire damage.
- (API) Implemented crossbow:
  - Added `ItemCrossbowBaseComponent` interface with `isLoaded()`, `getLoadedProjectile()`, and `setLoadedProjectile()` methods.
  - Added `EntityShootCrossbowEvent` event fired when shooting crossbow.
  - Added `EntityArrowBaseComponent.getPiercingLevel()` and `setPiercingLevel()` for Piercing enchantment support.
  - Added `ItemBaseComponent.onUseInAirTick()` callback for tick-based item usage logic.
- (API) Added `Instrument.fromBlockBelow(BlockType<?>)` static method to determine instrument based on block below noteblock.
- (API) Added `BlockEntityNoteblockBaseComponent.getNextPitch()` method to increment and return pitch.
- Added `onIdle` callback and `wakeUp()` method to `GameLoop` class for event-driven idle processing.

### Changed

- (API) Renamed `Entity.willBeSaved()` to `Entity.isPersistent()`.
- (API) Merged sound classes:
  - `PressurePlateActivateSound` and `PressurePlateDeactivateSound` -> `PressurePlateSound(blockState, activated)`
  - `DoorOpenSound` and `DoorCloseSound` -> `DoorSound(blockState, open)`
  - `TrapdoorOpenSound` and `TrapdoorCloseSound` -> `TrapdoorSound(blockState, open)`
  - `FenceGateOpenSound` and `FenceGateCloseSound` -> `FenceGateSound(blockState, open)`
- (API) Moved `AbstractEffectType` from api module to server module.
- Merged world tick thread and network thread into a single thread using an event-driven wake-up mechanism. This simplifies
  the threading model while maintaining low packet processing latency through `LockSupport.parkNanos/unpark`.

### Fixed

- Fixed noteblock being triggered when player is sneaking (should allow placing blocks on top instead).
- Fixed eating animation incorrectly triggering after interacting with blocks while holding food.
- Added missing ambient crackle sounds for furnace, blast furnace, smoker, and campfire.

### Removed

- Removed `enableIndependentNetworkThread` configuration option from `server-settings.yml` as it is no longer necessary.

# 0.10.3 (API 0.20.0) - 2026/1/7

<small>[Compare with 0.10.2](https://github.com/AllayMC/Allay/compare/0.10.2...0.10.3)</small>

### Added

- (API) Added API for setting player hud element's visibility.
- (API) Added `Runnable` overloads to `Scheduler` for `scheduleDelayed`, `scheduleRepeating`, and `scheduleDelayedRepeating` methods.
- (API) Added methods `WorldViewer.stopSound(String)` and `WorldViewer.stopAllSounds()`.
- (API) Added method `Player.transfer()` to transfer the player to another server.
- (API) Added method `Entity.setImmobile()` to make the entity immobile.
- (API) Added method `Container.isFull()` to check if a container is full.
- (API) Added method `BlockEntityChest.getDoubleChestContainer()` to get the container object to use when the chest is paired.
- (API) Implemented hopper:
  - (API) Added `BlockEntityHopper` and related interfaces.
  - (API) Added `SidedContainer` interface to represent a container that restricts insertion/extraction by side.
  - (API) Added `ContainerItemPickupEvent` for item entity pickups into containers.
  - (API) Added `ContainerTypes.HOPPER`.
- (API) Added methods `ChunkLoader.isWorldViewer()` and `ChunkLoader.toWorldViewer()`.
- (API) Added entity and dimension level scheduler.
- (API) Added APIs for setting the scale of entity:
  - Added methods `Entity.setScale()` and `Entity.getScale()`.
  - Added method `Entity.getBaseAABB()` to get the original aabb which is not affected by the scale factor.
- (API) Added method `EntityPhysicsComponent.resetFallDistance()`.
- Added properties `network-settings.raknet-send-cookie` and `network-settings.raknet-max-mtu` to `server.properties`.
- Implemented Totem of Undying.
- Implemented End Crystal.
- Implemented commands `/playsound`, `/stopsound`, `/playanimation`, `/hud`, and `/particle`.

### Changed

- (API) Refactor and clean up the config util under `org.allaymc.api.config`. This refactor is backward-compatible and shouldn't affect any plugin.
- (API) Added `ServerPlayerEvent` base class for all `Player` related events under `org.allaymc.api.eventbus.event.server` package.
- (API) Made `AllayAPI.APIInstanceHolder<T>` public in allay-api by removing the `@ApiStatus.Internal` annotation.
- (API) `EntityEnderCrystal` now extends `EntityLiving` and `EntityEnderCrystalBaseComponent` interfaces.
- Entity's display name is now default to the title case. For example: `minecraft:arrow` -> `Arrow`.
- List plugin command (`/plugin list`) now will show the plugin version.

### Fixed

- (API) Fixed method `Container.removeAllViewers()` won't remove all viewers from the container.
- Fixed a bug where calling method `Container.removeViewer()` server-side will make the client no longer able to open any container.
- Fixed a bug where the speed of the player would be incorrect in some special cases.
- Fixed a bug where a player teleporting into another player's loaded area would not be visible to that player.
- Fixed a bug where the jukebox could not eject music discs.
- Fixed a bug where the block will not be recovered client-side when events `BlockBreakEvent` and `PlayerInteractBlockEvent` is cancelled.
- Fixed a bug where packed and blue ice will melt.
- `RAK_SEND_COOKIE` is set to `true` by default in `AllayNetworkInterface` to resolve connection issue.

### Removed

- (API) Removed `@ApiStatus.OverrideOnly` annotation in `WorldStorage.shutdown()` method.

# 0.10.2 (API 0.19.0) - 2025/12/22

<small>[Compare with 0.10.1](https://github.com/AllayMC/Allay/compare/0.10.1...0.10.2)</small>

### Added

- (API) Added API for npc dialog. The related classes are under `org.allaymc.api.dialog` package.
- Updated the feature version to 1.21.130.
- Implemented `/tell` (aka. `/msg` and `/w`) command.
- Implemented `/say` command.
- Implemented small and big flowers:
  - Small flowers: Dandelion, Poppy, Blue Orchid, Allium, Azure Bluet, Red Tulip, Orange Tulip, White Tulip, Pink Tulip, Oxeye Daisy, Cornflower, Lily of the Valley, Wither Rose, Torchflower, Closed Eyeblossom, Open Eyeblossom.
  - Big flowers: Sunflower, Lilac, Rose Bush, Peony, Pitcher Plant
- Implemented Leaf Litter, Pink Petals, and Wildflowers.

### Changed

- (API) Moved `PlayerKickEvent` from `player` package to `server` package.
- (API) Renamed field `blockEntityBrewingStand` to `brewingStand` in classes `BrewingStandBrewEvent`, `BrewingStandConsumeFuelEvent`, and `BrewingStandStartBrewEvent`.
- (API) Renamed field `blockEntityFurnace` to `furnace` in classes `FurnaceConsumeFuelEvent` and `FurnaceSmeltEvent`.
- (API) Renamed method `FormViewer.removeAllForms()` to `FormViewer.closeAllForms()`.
- (API) Event `PlayerItemHeldEvent` is now cancellable with some field name changes.

### Fixed

- (API) Fixed the issue that some item class do not extend `ItemToolComponent` interface.
- Fixed a rare NPE when saving the nbt of painting entity.
- Fixed a NPE when using TargetNode (`.target()`) in command.
- Fixed a client-side hotbar flicking issue when changing the hotbar slot quickly.
- Fixed a bug where right-clicking on a bow briefly would cause the wrong charge state.

### Removed

- (API) Removed selector type `@initiator` since it is useless in third server software implementation:
  - `EntitySelectorAPI` no longer regard `@initiator` as a valid selector type.
  - Removed enum member `SelectorType.NPC_INITIATOR`.

# 0.10.1 (API 0.18.0) - 2025/12/10

<small>[Compare with 0.10.0](https://github.com/AllayMC/Allay/compare/0.10.0...0.10.1)</small>

### Fixed

- Used explicit version numbers for the Cloudburst protocol library to resolve compilation inconsistencies due to upstream maven repository updates.

# 0.10.0 (API 0.18.0) - 2025/12/10

<small>[Compare with 0.9.1](https://github.com/AllayMC/Allay/compare/0.9.1...0.10.0)</small>

### Added

- Added support for bedrock 1.21.130 (protocol version 897/898).
- Javadoc for allay-api is now published to maven central and is available [here](https://javadoc.io/doc/org.allaymc.allay/api).

### Changed

- (API) Moved field `joinMessage` from `PlayerSpawnEvent` to `PlayerJoinEvent`.
- (API) Added field `disconnectReason` to `PlayerJoinEvent`.

### Fixed

- Fixed method `AllayStringUtils.splitCommandArgs()` when using quote and curly brace together.
- Fixed `relative` parameter of method `WorldViewer.viewSound()` being inverted unexpectedly.

# 0.9.1 (API 0.17.0) - 2025/11/28

<small>[Compare with 0.9.0](https://github.com/AllayMC/Allay/compare/0.9.0...0.9.1)</small>

### Added

- (API) `DebugText` now support `scale` property.
- (API) Introduced `CallerThread` annotation to events to specify the execution thread's type.
- (API) Added methods `PlayerManager.isOperator()` and `PlayerManager.setOperaotor()`.
- (API) Readded `DebugShapeViewer`, and `WorldViewer` now extends `DebugShapeViewer`. There should be no breaking changes.
- (API) Added `SenderType.ACTUAL_PLAYER` which limits the command sender from must be an actual player.
- (API) Introduced new `Player` class, there are some things worth noting:
  - A `Player` object is basically controlling an `EntityPlayer` object now. Most client-related methods
    are moved to the new `Player` class (e.g. most of the methods in the old `EntityPlayerClientComponent`).
  - A `EntityPlayer` object now can be either a real player or a fake player. This is determined by checking
    if the player entity is `controlled` by a `Player` object (use `EntityPlayer.isActualPlayer()` method).
  - You can now create fake player via `EntityTypes.PLAYER` just like how you created the other entity.
  - The type of some variables has been changed from `EntityPlayer` to `Player` to better fit their purpose,
    such as the `player` variable in `PlayerJoinEvent`.
- (API) Added `PlayerSpawnEvent` event.
- Command nodes that the player doesn't have permission to access will not be sent to the player, and the commands will
  be resent when the specific permission changes.
- Updated log4j2 patterns to use `%logger{0}` instead of `%c{0}`.
- Added support for bedrock 1.21.124 (protocol version 860).

### Changed

- (API) Renamed class `Structure` to `StructureFile` to avoid confusion with the upcoming structure feature in world gen.
- (API) Refactored the permission system. The permission system has now been reconstructed into a lightweight, easy-to-interface
  system in preparation for docking with LuckPerms. There are some breaking changes, especially in command creation. Check the updated
  documentation on our website for details.
- (API) Update some events:
  - `IPBanEvent` and `IPUnbanEvent` are moved from `network` to `server` package.
  - `PlayerBanEvent`, `PlayerUnbanEvent`, `PlayerLoginEvent`, `PlayerJoinEvent` and `PlayerQuitEvent` are moved from `player` to `server` package.
  - `ClientConnectEvent` and `ClientDisconnectEvent` are renamed to `PlayerXXXEvent`. They are also moved from `network` to `server` package.
- (API) Added a new `permissions` parameter to the parameter list of method `MessageReceiver.sendCommandOutputs()`.
- (API) Changed member `SimpleEntityAction.CRITICAL_HIT` to independent `CriticalHit(int count)` record (the same to `ENCHANTED_HIT`) due
  to the addition of the newly added `count` parameter since protocol version 859.
- (API) Renamed method `PlayerManager.getOnlinePlayerByName()` to `PlayerManager.getPlayerByName()` for clarity and consistency.
- (API) Moved field `joinMessage` in `PlayerLoginEvent` to `PlayerSpawnEvent` since the server now will broadcast the join message when the player
  spawns instead of when logged in.
- Permission data now will not be saved into the player's nbt data, which allows third permission plugins to store the permission data in their own way.
  The core will only saved a `operator` list now.

### Fixed

- Fixed debug shape rendering in 1.21.120.

### Removed

- (API) Removed useless method `Entity.isHeadYawEnabled()`.
- (API) Removed classes `EntityPlayerBossBarViewerComponent`, `EntityPlayerChunkLoaderComponent`, `EntityPlayerFormViewerComponent` and `EntityPlayerScoreboardViewerComponent`.

# 0.9.0 (API 0.16.0) - 2025/10/30

<small>[Compare with 0.8.3](https://github.com/AllayMC/Allay/compare/0.8.3...HEAD)</small>

### Added

- (API) Implemented `FakeChestContainer` and `FakeDoubleChestContainer`. Plugin developer can get fake container instance through
  the new `FakeContainerFactory` class.
- (API) Introduced record `EntityPlayerBaseComponent.Speed` to represent the `speed`, `flySpeed`, and `verticalFlySpeed` for player.
- Implemented copper torch.
- Implemented copper door.
- Add support for bedrock 1.21.120.

### Changed

- (API) Event `ContainerCloseEvent` is not cancellable now.
- (API) Changed the getter/setter methods for `speed`, `flySpeed`, and `verticalFlySpeed` to use the new `Speed` record.

### Fixed

- Fixed (maybe a bug) sometime event `ContainerOpenEvent` will be called twice.
- Fixed incorrect player speed after applying `speed` and `slowness` effect. These effects now will also affect the fly speed and vertical
  fly speed of the player.
- Fixed a rare NPE when the client disconnects while haven't logged in.

### Removed

- (API) Removed `headYaw` from `Location3d(c)`, `Location3f(c)`, and `Location3i(c)`.

## 0.8.3 (API 0.15.0) - 2025/10/20

<small>[Compare with 0.8.2](https://github.com/AllayMC/Allay/compare/0.8.2...0.8.3)</small>

### Added

- (API) Added method `MessageChannel.hasReceiver()` to check if the specific message receiver is registered in the message channel.
- (API) Added events `PlayerRightClickItemInAirEvent`, `PlayerStartUseItemInAirEvent`, `PlayerUseItemInAirEvent`.
- (API) Added events `PlayerPunchBlockEvent` and `PlayerPunchAirEvent`.
- (API) Added event `PlayerCraftItemEvent`.
- (API) Added tooltip field to `Dropdown`, `Input`, `Slider`, `StepSlider`, and `Toggle` which are used in `CustomForm`.
- Implemented short and tall dry grass.

### Changed

- (API) Renamed method `ItemStack.clickItemInAir()` to `ItemStack.rightClickItemInAir()`.
- (API) Renamed method `ItemStack.rightClickItemOn()` to `ItemStack.rightClickItemOnBlock()`.

### Fixed

- Fixed a bug that custom command sender (which is not registered in the default message channel) cannot receive command outputs.
- Fixed a bug where all player skins would be set to the same skin.
- Fixed an issue where commands had two permission nodes.
- Fixed a bug where players' name tags are not always shown.
- Fixed a bug where using firework rocket in a block won't decrease the item count.
- Fixed a client crash bug due to the incorrect absorption attribute sending.
- Fixed a bug where water block cannot extinguish the fire on the entity.
- Fixed a bug where the player dying in a non-overworld dimension could not be properly teleported back to the spawn point in overworld.
- Fixed a NPE when interacting bed block in a non-overworld dimension.
- Fixed a bug where chunks sent to the client sometimes did not display immediately.
- Fixed a NPE which rarely happens in the light engine.

### Removed

- (API) Removed method `ItemStack.releaseItem()` which is duplicated with method `ItemStack.useItemInAir()`.

## 0.8.2 (API 0.14.0) - 2025/10/16

<small>[Compare with 0.8.1](https://github.com/AllayMC/Allay/compare/0.8.1...0.8.2)</small>

### Added

- (API) Added method `OtherChunkAccessibleContext.getBlockEntity()`.
- (API) Added method `DyeColor.getColor()` to get the corresponded `Color` object of the dye color.
- (API) Added methods `EntityPlayerClientComponent.viewPlayerListChange()` and `EntityPlayerClientComponent.viewPlayerPermission()`.
- (API) Added particle `SimpleParticle.FIREWORK_CONTRAIL`.
- (API) Added event `FireworkExplodeEvent` which will be called when a firework rocket is about to explode.
- (API) Added class `ItemDyeComponent` which is used in all items that can be used as dye.
- (API) Implemented firework and firework star. Multiple classes including `FireworkType`, `FireworkExplosion` were added.
- Implemented recipes for fireworks.
- Implemented elytra. The fall distance for the player will be reset if the player is gliding.
- Player's height will be changed when the player is sneaking, swimming, gliding, or crawling now.

### Changed

- (API) Method `ItemStack.saveExtraTag()` won't return a `null` value when the encoded extra tag is empty now.
- (API) Used `double` instead of `float` for player speed, fly speed, and vertical fly speed.
- (API) Renamed field `SimpleSoundEvent.TWINKLE` to `SimpleSoundEvent.FLICKER`.
- (API) Renamed field `SimpleEntityAction.FIREWORK_EXPLOSION` to `SimpleEntityAction.FIREWORK_EXPLODE`.
- (API) Renamed class `AllayNbtUtils` to `AllayNBTUtils`.
- (API) Flattened packages except `events` under package `org.allaymc.api.entity.component`.
- Optimized memory performance when loading chunks by reducing the large number of temporary objects generated in the light update queue.
- Renamed `ItemComponent` to `ItemDefinition` in many places.

### Fixed

- Player in spectator mode will no longer lose the saturation level.
- Speed, fly speed, and vertical fly speed for the player won't be saved now.
- The online player list shown in the dashboard will be correctly updated when a player quits now.
- Fixed an issue where the client would sometimes get stuck on the screen of death.
- The client will be disconnected now if failed to decode the login packet sent from the client.

### Removed

- (API) Removed internal method `ArmorContainer.onArmorChange()`.

## 0.8.1 (API 0.13.0) - 2025/10/7

<small>[Compare with 0.8.0](https://github.com/AllayMC/Allay/compare/0.8.0...0.8.1)</small>

### Added

- (API) Implemented void damage. New methods `EntityLivingComponent.hasVoidDamage()` and `DamageContainer.voidDamage()` were added.
- (API) Implemented lightning rods.
- (API) Implemented (copper) lanterns.
- (API) Implemented copper golem statues.
- (API) Implemented copper chains.
- (API) Implemented heads.
- (API) Implemented painting.
- (API) Added field `SimpleSound.PAINTING_PLACE`.
- (API) Added classes `PaintingType`, `EntityPaintingBaseComponent`.
- (API) Added method `DamageContainer.api(float)`.
- (API) Added method `MathUtils.grow(AABBd, BlockFace, double)`.
- (API) Added method overload `Entity.remove(Runnable)`.

### Changed

- (API) Renamed field `SimpleSound.SIGN_WAXED` to `SimpleSound.WAXED`.
- (API) Renamed method `EntityUndeadComponent.getCatchesFireUnderSunlight()` to `EntityUndeadComponent.ignitedBySunlight()`.
- (API) Changed the return type of methods `Entity.getOffsetAABB()` and `Entity.getOffsetAABBForCollisionCheck()`. They return `AABBdc` instead of `AABBd` now.
- Changed the behavior of method `EntityLivingComponent.kill()`. It now applies 1,000,000 damage to the entity instead of setting the health to zero directly.

### Fixed

- Disabled dead timer for item entity.
- Fixed fire damage wasn't given when the on fire tick is continuously updated.
- Fixed the bug that air supply, effect particle, and on fire state are invisible.
- Fixed an issue where the server could not be launched in certain locales (e.g. Turkey).

## 0.8.0 (API 0.12.0) - 2025/10/4

<small>[Compare with 0.7.1](https://github.com/AllayMC/Allay/compare/0.7.1...HEAD)</small>

### Added

- (API) Implemented basic bed feature.
- (API) Implemented arrow and bow. A new event `EntityShootBowEvent` is added.
- (API) Implemented noteblock.
- (API) Introduced PlayerInteractBlockEvent.
- (API) Introduced PlayerBookEditEvent.
- (API) Introduced methods `Entity.getOffsetAABBForCollisionCheck()` and `Entity.checkBlockCollision()`.
- (API) Introduced event `BlockExplodeEvent`.
- (API) Introduced option `ServerSettings.NetworkSettings.enablev6` to close ipv6 if needed.
- (API) Introduced field `PlayerRespawnEvent.respawnLocation` which can be changed to modify the respawn location of a player after death.
- (API) Introduced field `LiquidHardenEvent.hardenedBlockPosition` which is the position of the hardened block.
- (API) Introduced `api_version` for plugin descriptor, which can set the api version requirement of a plugin.
- (API) Introduced component `EntityPlayerScoreboardViewerComponent` for player.
- (API) Introduced a bunch of  `XXXViewer` (e.g. `WorldViewer`) interfaces which is a tiny wrapper for packet operations. This is
  inspired by df-mc/dragonfly to reduce the code associated with network packet contained in api module.
- (API) Introduced a bunch of functional interfaces in package `utils.funtion`.
- (API) Introduced classes `EntityFlag` and `EntityData` which are correspond to classes in the protocol library.
- (API) Introduced enum `DiscType` for music disc.
- (API) Introduced method `Chunk.addChunkTask()` that adds a task which will be performed later in the chunk tick.
- (API) Introduced method `EntityBaaeComponent.applyAnimation()` and class `EntityAnimation`.
- (API) Introduced classes `EntityAction` and `BlockAction`.
- (API) Introduced class `Skin` as the replacement for the `SerializedSkin` class in protocol lib.
- (API) Introduced a new `MessageChannel` system to replace the old `broadcastXXX()` methods in `Server`. The default message channel of
  the server can be got using method `Server.getMessageChannel()` and can be changed using method `Server.setMessageChannel()`.
- (API) Introduced new registry `Registries.BIOMES` which contains all available biomes in the game. This is the replacement for the old
  `BiomeId` enum. Biome data is also accessible through method `BiomeType.getBiomeData` now.
- (API) Introduced method `Command.isDebugCommand()` which can be overridden to determine whether the command is a debug command. Debug
  command name will be shown blue client-side.
- (API) Introduced class `NBTIO` which replaced the old `fromNBT()` methods in `XXXHelper` classes.
- (API) Introduced interfaces `FormViewer` and `EntityPlayerFormViewerComponent`.
- Implemented sharpness enchantment.
- Implemented `SetDifficultyPacketProcessor` to support changing level difficulty through setting menu.
- Block entity's custom name will be set to the custom name of the item now, which allows the player to change the inventory name of containers.
- Players can now extinguish the fire on the surface of the block by left-clicking.
- Introduced a dirty flag for block layers in chunk sections. Now blocks will only be rewritten to the database if they are changed. This
  would speed up the time used during server shutdown significantly if there are many only loaded chunks.
- Added support for basic multi-version. The server now supports 1.21.80 - 1.21.110 client to join.
- The motion of the player will be added to the arrow shot by the player now.

### Changed

- (API) Renamed method `Entity.despawn()` to `Entity.remove()` as a clarification with other methods like `Entity.despawnFrom()` and `Entity.despawnFromAll()`.
- (API) Renamed entries in class `TrKeys`. The old `M_XXX` is renamed to `MC_XXX` and `A_XXX` is renamed to `ALLAY_XXX`.
- (API) Renamed field `Block.pos` to `Block.position`.
- (API) Renamed class `BlockUpdateService` to `BlockUpdateManager`.
- (API) Renamed class `ChunkService` to `ChunkManager`.
- (API) Renamed class `EntityService` to `EntityManager`.
- (API) Renamed class `EntityPhysicsService` to `EntityPhysicsEngine`, and physics engine related classes are now moved to package `org.allaymc.api(server).world.physics`.
- (API) Renamed class `LightService` to `LightEngine`, and light engine related classes are now moved to package `org.allaymc.api(server).world.light`.
- (API) Renamed class `PlayerService` to `PlayerManager`.
- (API) Renamed class `ScoreboardService` to `ScoreboardManager`.
- (API) Renamed package `org.allaymc.api(server).client` to `org.allaymc.api(server).player`.
- (API) Renamed classes `BlockStateSafeGetter` and `ItemTypeSafeGetter` to `BlockStateGetter` and `ItemTypeGetter`.
- (API) Renamed class `ApiInstanceHolder` to `APIInstanceHolder`.
- (API) Renamed class `EntityStatus` to `EntityState`.
- (API) Renamed `FullContainerType` to `ContainerType`. All the network related code inside are moved to server module.
- (API) Renamed classes `PlayerArmorContainer`, `PlayerInventoryContainer` and `PlayerOffhandContainer` to `ArmorContainer`, `InventoryContainer` and `OffhandContainer`.
- (API) Renamed method `TextReceiver.sendTr()` to `TextReceiver.sendTranslatable()` for better readability.
- (API) Renamed method `LoginData.isXboxAuthenticated()` to `LoginData.isAuthed()`.
- (API) Renamed package `i18n` to `message`.
- (API) Renamed class `TextReceiver` to `MessageReceiver` and method `TextReceiver.sendText()` to `MessageReceiver.sendMessage()`.
- (API) Renamed class `DefaultDescriptor` to `ItemTypeDescriptor`.
- (API) Renamed item stack network id to unique id for better readability, changes are made to multiple related methods.
- (API) Renamed method `Command.getCommandOverloads()` to `Command.getOverloads()`.
- (API) Renamed method `BlockState.getBlockStateTag()` to `BlockState.getBlockStateNBT()`.
- (API) Renamed class `EntityDamageComponent` to `EntityLivingComponent`.
- (API) Renamed class `EntityPlayerNetworkComponent` to `EntityPlayerClientComponent`.
- (API) Renamed class `BlockEntityHolderComponent` to `BlockBlockEntityHolderComponent` to match the naming convention.
- (API) Refactored the container system, multiple methods were renamed/deleted. See the commit history for details.
- (API) Refactored the permission system to add support for multiple parents in a permission group. See the commit history for details.
- (API) Due to the new `XXXViewer` system, a number of network related methods are removed (e.g. `Entity.createSpawnPacket()`). See the commit history for details.
- (API) Flattened all classes under `player` package.
- (API) Introduced new `GameMode` enum, and the old `GameType` used in protocol lib is unused since it has many game types which only exist in vanilla.
- (API) Moved effect-related methods from `EntityBaseComponent` to `EntityLivingComponent`.
- (API) Moved class `ComponentManager` from api to server module since it is useless in api module.
- (API) Moved classes `BlockPropertyProcessor`, `BlockPlaceHelper` and `FortuneDropHelper` from api to server.
- (API) Moved classes `Structure` and `StructureException` from `utils` to `world` package.
- (API) Moved class `BlockAndItemIdMapper` from api to server module.
- (API) Moved all effect implementations from api to server module.
- (API) Moved classes under package `component.interfaces` to `component` package.
- (API) Moved class `ScoreboardStorage` form package `scoreboard.storage` to `scoreboard` package.
- (API) Moved classes `HashUtils` and `HashException` from package `utils` to `utils.hash`.
- (API) Moved classes `Identified`, `Identifier`, `IdentifierUtils` and `InvalidIdentifierException` from package `utils` to `utils.identifier`.
- (API) Moved class `GameLoop` from api module to server module.
- (API) Moved class `JSONUtils` and the classes only used by this util from api module to server module.
- (API) Moved class `Difficulty`, `DimensionInfo`, `Sound` and `Weather` from package `world` to package `world.data`.
- (API) Moved class `NPCCommandSender` from api module to server module.
- (API) Moved class `ClientStatus` from package `network` to package `player` and renamed it to `ClientState`.
- (API) Moved the implementations of `EnchantmentType` from api module to server module.
- (API) Moved class `CommandParseException` from api module to server module.
- (API) Moved methods `BlockStateData.fromJson()` to class `BlockStateDataLoader`.
- (API) Moved several data classes for block and item to packages `block.data` and `item.data`.
- (API) Moved class `XXXId` from api to server, since the user is expected to use objects in `XXXTypes`.
- (API) Moved events `PacketSendEvent` and  `PacketReceiveEvent` from api module to server module.
- (API) Moved package `item.descriptor` to `item.recipe.descriptor` since the item descriptor is only used in recipe system.
- (API) Moved `XXXInitInfo` classes to their parent packages.
- (API) Moved class `CommonEnums` from api to server.
- (API) Moved classes `CachedSimpleSelectorArgument` and `CachedFilterSelectorArgument` from api to server.
- (API) Moved classes `XXXTag` and `XXXTags` from `tag` package to `data` package. 
- (API) Moved class `DamageType` out from class `DamageContainer`.
- (API) Moved class `ServerSettings` from api to server.
- (API) Moved class `WorldSettings` from api to server.
- (API) Moved classes `BanInfo` and `Whitelist` from api to server. Plugin should use the APIs in `PlayerManager` instead of reading/writing
  the config directly.
- (API) Updated several methods in `EntityBaseComponent` to use `WorldViewer` as the viewer of entity instead of `EntityPlayer`.
- (API) Refactored the weather system. World will hold only one `Weather` instance now.
- (API) Refactored the container system. All container implementations are moved to server module now.
- (API) Refactored the sound and particle system, now each sound and particle instance is a pure data object or an enum of `SimpleSound`/`SimpleParticle` if it doesn't require
  additional parameters.
- (API) Refactored the recipe system, network-related code is moved to the server module.
- (API) Made classes `UIProfile`, `DeviceInfo` and `Device` as the inner classes of `LoginData`.
- (API) Made classes `APINotImplementedException`, `MissingImplementationException`, `MissingRequirementException` and `APIInstanceHolder` as the private subclasses of `AllayAPI`.
- (API) Made class `LoginData` as an interface, the implementation is moved to the server module.
- (API) Replaced protocol library class `ModalFormCancelReason` with `FormCancelReason` in the modal form APIs.
- (API) Replaced protocol library class `CreativeItemCategory` with `CreativeItemCategory.Type` in the creative item APIs.
- (API) Class `BossBar` now accepts `BossBarViewer` instead of `EntityPlayer` directly, but the usage won't change since `EntityPlayer` implemented `BossBarViewer`.
- (API) Changed methods in `ItemSignBaseComponent` to return `BlockType` instead of `BlockId`.
- (API) Changed the parameters of method `WorldPool.loadWorld()`.

### Fixed

- Fixed the bug that snowball will always make knockback even if the entity being hit is in damage cool down.
- Fixed the bug where Ender Pearls would deal damage to Blazes.
- Fixed an NPE bug that could occur in extreme cases for the light engine.
- Fixed incorrect hardened block pos when lava flow to the water from above.
- Fixed the bug where plugins lacking soft dependencies could not be loaded.
- Fixed the bug where plugins which have dependencies will only be loaded but not enabled.
- Fixed the sub chunk sending system.
- Fixed the bug that food leve is not frozen when world difficulty is set to peaceful.
- Fixed the placement of wall sign.
- Fixed the bug that jukebox accepts any type of item, it now only allows music disc item to be placed.

### Removed

- (API) Removed methods `isClientCacheEnabled()`, `isNetworkEncryptionEnabled()` and `getEncryptionSecretKey()` in `EntityPlayerClientComponent` because
  these methods are not very useful as APIs.
- (API) Removed classes `Abilities` and `AdventureSettings`.
- (API) Removed class `CustomBlockComponent` since it is never used.
- (API) Removed class `Metadata` which is used in entity. Using the getter/setter methods for entity data and flag in `EntityBaseComponent` directly.
- (API) Removed class `EntityAttributeComponent`, health related methods are moved to `EntityLivingComponent` and experience/food related methods are
  moved to `EntityPlayerBaseComponent`.
- (API) Removed class `XXXCustomTags`, all the custom tags are merged to `XXXTags` and the namespace is renamed to `minecraft` instead of `allay`.
- (API) Removed classes `BaseCommand` and `SimpleCommand`, and plugin commands now should extend `Command` directly.
- (API) Removed class `PacketReceiver`.
- (API) Removed class `IntMappedRegistry`.
- (API) Removed class `WorldGeneratorType` which is not very useful.
- (API) Removed adventure settings related permissions in `Permissions` since adventure settings should only change when the game mode change.
- (API) Removed field `CommonEnums.GAMEMODE_ENUM`.
- (API) Removed field `networkId` in `EntityId` since it is never used.
- (API) Removed method `Difficulty.from(String)`.
- (API) Removed methods `ChunkLoader.onChunkInRangeSend()` and `ChunkLoader.onChunkOutOfRange()`, they are replaced by `WorldViewer.viewChunk()` and `WorldViewer.removeChunk()`.
- (API) Removed method `WorldData.sendTimeOfDay()`.
- (API) Removed method `UnsafeChunk.getPlayerChunkLoaders()`.
- (API) Removed methods `Dimension.addLevelSoundEvent()` and `Dimension.addLevelEvent()` due to the new sound/particle system.
- (API) Removed methods `Dimension.sendBlockUpdateTo()`, please use `WorldViewer.viewBlockUpdate()` instead.
- (API) Removed network-related methods in class `BlockEntityBaseComponent`, use the newly introduced world viewer interface instead.
- (API) Removed methods `BlockEntityHolderComponent.createBlockEntity()` and `BlockEntityHolderComponent.removeBlockEntity()` since these methods are not expected
  to be touched by the user.
- (API) Removed method `EntityBaseComponent.applyEntityEvent()` which is replaced by the new entity action system.
- (API) Removed method `EntityBaseComponent.saveNBTWithoutPos()` which is never used.
- (API) Removed method `Position3ic.toNetwork()`.
- (API) Removed method `BlockEntityBaseComponent.sendPacketToViewers()`.
- (API) Removed chunk packet related methods in `UnsafeChunk`.
- (API) Removed method `CommandSender.getCommandOriginData()`.
- (API) Removed method `CommandSender.handleResult()`.
- (API) Removed network related methods in classes `ScoreboardLine` and `Scorer`.
- (API) Removed method `BlockState.toNetworkDefinition()`.
- (API) Removed method `Container.toNetworkItemData()`.
- (API) Removed methods `toNetwork()`, `getChunkDataPacket()` and `toEntryInfo()` in class `Pack`.
- (API) Removed method `EntityPlayerClientComponent.getClientSession()`.
- (API) Removed method `EnchantmentInstance.toNetwork()`.
- (API) Removed network-related methods in the command system.
- (API) Removed method `Command.getCommandFormatTips()`.
- (API) Removed methods `BlockPropertyType.getProcessor()` and `BlockPropertyType.setProcessor()`.
- (API) Removed network-related code in `Weather`
- (API) Removed method `BlockType.register()`.
- (API) Removed method `GenerateFunction.getName()`.
- (API) Removed method `Entity.teleportAsync()` since the normal teleport method is not blocking now.
- (API) Removed field `BlockPlaceEvent.entity` since the entity can be obtained from the interact info.
- (API) Removed a number of useless build-in permissions which are listed in class `Permissions`, the developer should listen to the corresponded events.

## 0.7.1 (API 0.11.0) - 2025/8/20

<small>[Compare with 0.7.0](https://github.com/AllayMC/Allay/compare/0.7.0...0.7.1)</small>

### Added

- (API) Introduced `EntityPhysicsComponent`, `EntityAgeComponent` and `EntityProjectileComponent` to split different logic 
  to different components instead of all in base component.
- (API) Introduced a new mutable field `throwForce` in `ProjectileLaunchEvent` which indicates the force of the projectile when it is launched.
- (API) Introduced method `Entity.getLastLocation()` which returns the last tick location of the entity.
- (API) Introduced a new configuration `ServerSettings.WorldSettings.removeUnusedProtoChunkCycle` which controls how long a proto chunk can remain in memory.
- Introduced component `EntityBreatheComponent` which handle the logic of entity breathe.
- Added armor component to the newly added copper armor items.
- Introduced a new optional arg for command `/enchant` which indicates whether the entered level should be checked to be in acceptable range.
- Implemented xp bottle.
- Added back spawn point finding logic, and now the world will look for a suitable spawn point when it is first created.

### Changed

- (API) `ChunkLoadEvent` and `ChunkUnloadEvent` were uncancellable now. Consider using `FakeChunkLoader` instead.
- (API) Refactored the chunk system, some APIs are changed.
- (API) Renamed `ServerSettings.WorldSettings.removeUnneededChunkCycle` to `ServerSettings.WorldSettings.removeUnusedFullChunkCycle`.
- (API) Renamed `ServerSettings.WorldSettings.chunkTrySendCountPerTick` to `ServerSettings.WorldSettings.chunkMaxSendCountPerTick`.
  in corresponding to the new `removeUnusedProtoChunkCycle` configuration.
- Refactored `EntityPhysicsComponentImpl` for readability.
- Resending available commands is now the default behavior for `PermissionListener`.
- Removed `Command.createPermissionForCommand()`, use `Permission.createForCommand()` instead. See commit history for details.

### Fixed

- Fixed client lag due to `AvailableCommandsPacket` being sent continuously.
- Fixed client sometime miss textures for some items like snowball, ender pearl, etc.
- Fixed `ClassCastException` on `BlockChorusFlowerBaseComponentImpl`.
- Fixed recursive wall updating.
- Farmland trampling now correctly considers any entity whose bounding box exceeds a certain size threshold, instead of relying solely on a fixed entity whitelist.
- Player movement now normally reduces satiety
- `/world tp <world>` now teleports the player to the spawn point for overworld dimension.
- Fixed the physics for some entities including TNT, and stepping is disabled by default now.
- Fixed `WeatherChangeEvent` spamming.
- Removed `Utils.convertByteBuf2Array()`. Use `ByteBufUtil.getBytes()` instead for better safety and reliability.

### Removed

- (API) Removed `EntityPhysicsBaseComponent`.
- (API) Removed `ChunkPreLoadEvent`.
- (API) Removed keep loading chunk related methods in `ChunkService`. Consider using `FakeChunkLoader` instead.

## 0.7.0 (API 0.10.0) - 2025-8-10

<small>[Compare with 0.6.0](https://github.com/AllayMC/Allay/compare/0.6.0...0.7.0)</small>

### Added

- (API) Added `GameRule.LOCATOR_BAR`.
- (API) Added method `BlockFace.getHorizontalIndex()` to get the horizontal index of the block face.
- (API) Added method `BlockFace.fromHorizontalIndex()` to get the block face from the horizontal index.
- (API) Added event `PlayerInteractEntityEvent`.
- (API) Added events `PlayerBucketFillEvent` and `PlayerBucketEmptyEvent`.
- (API) Added class `CustomItemComponentDataGenerator` which adds basic support for custom item.
- (API) Added method `CreativeItemCategory.getNamedGroup()` to get a named group in a creative category by its name.
- (API) Added class `CreativeItemGroups` which contains all the available creative item groups in vanilla.
- (API) Added event `ItemFrameUseEvent` which is called when an item frame is used.
- (API) Added `scale` property for `DebugArrow`.
- (API) Added methods `updateMotion()`, `trySetLocation()` and `applyMotion()` to `EntityBaseComponent`. These methods
  are intended only be called by physics engine, and user should override them only.
- (API) Added method `onCollideWithBlock()` to `EntityBaseComponent`.
- (API) Added `ProjectileLaunchEvent` and `ProjectileHitEvent` events.
- (API) Added `PlayerMapInfoRequestEvent` event.
- (API) Added method `EffectType.getColor()` to get the color of an effect type.
- (API) Added class `PotionType` which represents the type of potion bottle item.
- (API) Added methods `BlockBehavior.onEntityInside()` and `Entity.onInsideBlock()`.
- (API) Added method `CustomItemComponentDataGenerator.Builder.cooldown()` to set cooldown for custom item.
- (API) Added methods `BlockBehavior.onProjectileHit()` and `Entity.onProjectileHit()`.
- (API) Implemented writable book and written book.
- (API) Implemented (glowing) item frame.
- (API) Implemented map.
- (API) Implemented snowball.
- (API) Implemented potion and splash potion.
- (API) Implemented ender pearl.
- The color of the player on the locator bar is now calculated based on their name.
- Added overloads for methods `AllayXXXType.Builder.build()` which accept a custom `ClassLoader`. This parameter should be used by plugins
  if plugins want to create custom item type, because plugins classes are loaded by `JarPluginClassLoader`.
- Added support for in-plugin resource pack, which allows plugins to include their resource pack under `assets/resource_pack`. This
  is useful for plugins that have custom items or blocks that require a resource pack to be displayed correctly.
- Players now can see each other's permission level in pause menu.
- Improved `/kick` command.
- Added support for 1.21.100.

### Changed

- (API) Renamed method `BlockFace.fromId()` to `BlockFace.fromIndex()`.
- (API) Several methods in `PackLoader` are renamed to better match their usage.
- (API) Renamed method `CommandSender.getCmdExecuteLocation()` to `CommandSender.getCommandExecuteLocation()`.
- (API) Moved plugin i18n file directory from `lang` to `assets/lang`.
- (API) Renamed `ItemFoodComponent` to `ItemEdibleComponent`.
- (API) Renamed `PlayerEatFoodEvent` to `PlayerEatEvent`.
- (API) Method `Entity.teleport()` now return a `boolean` value which indicates whether the event is cancelled.
- (API) Updated method `Entity.knockback()`. It now accepts a custom `additionalMotion` vector.
- (API) Renamed class `BlockStateWithPos` to `Block`.
- Commands `/op` and `/deop` now use player target parameter instead of string parameter.`
- Refactored knockback related logic, it now better matches the vanilla.

### Fixed

- Fixed the bug that `AvailableCommandsPacket` won't be resent to the player if command permissions changed, usually when using `/op` and `/deop` commands.
- Fixed trapdoor placement.
- Fixed melon/pumpkin stem drop.
- Fixed the bug that vibration visual feature can't be enabled in the settings menu when playing in the server.
- Fixed lag in entity movement due to packets not being sent immediately.
- Fixed falling blocks, they now become blocks instead of dropped items when they hit the ground.
- Fixed the bug that client's offhand won't get updated when setting offhand item.
- Fixed a possible NPE in method `ItemBaseComponent.isCorrectToolFor()`.
- Fixed the bug that lava and fire won't ignite the entity
- Fixed NPE when creative player breaks bedrock.

### Removed

- (API) Removed method `BlockPlaceHelper.processDirection4Property()`.
- (API) Removed several default methods in `EntityPlayerNetworkComponent` that forward call to `LoginData` for better codebase.
- (API) Removed methods `PackLoader.hasFolder()` and `PackLoader.forEachIn()`, since these methods are never used.
- (API) Removed method `MathUtils.grow()`. Consider using `AABBd.expand` instead.
- (API) Removed methods `BlockBehavior.canCollideWithEntity()` and `EntityBaseComponent.hasBlockCollision()`.
- (API) Removed fields `customKnockbackXXX` in `DamageContainer`. Consider setting `DamageContainer.hasKnockback` to `false` and apply your own knockback logic.

## 0.6.0 (API 0.9.0) - 2025-7-7

<small>[Compare with 0.5.0](https://github.com/AllayMC/Allay/compare/0.5.0...0.6.0)</small>

### Added

- (API) Added APIs for the new `DebugShape` system. Available debug shape classes can be found under `org.allaymc.api.debugshape`,
  and add/remove methods for debug shape are available in `Dimension` class.
- (API) Added method `World.getState` as the replacement for the removed `World.isRunning` method.
- (API) Added `ServerSettings.NetworkSettings.raknetGlobalPacketLimit` to control the global packet limit for RakNet.
- (API) Added support for ipv6, and two new options `ipv6` and `portv6` are added to `server-settings.yml`.
- (API) Added `PlayerMoveEvent.setRevertTo()` method which allow to specify custom cancel/revert position for `PlayerMoveEvent`.
- Added support for bedrock 1.21.93.

### Changed

- (API) Method `EntityService.getEntitiesInChunk` is considered not thread-safe to better its behavior now.
- (API) Refactored `LoginData` to match the update of LoginPacket recently. Method `LoginData.getDisplayName()` was renamed to `LoginData.getXname()`.
- (API) All constructors of `EffectXXXType` are now package-private to prevent plugins from constructing them directly. Plugin developers are expected
  to use `EffectTypes` class to get the effect types.
- (API) Method `Dimension.setBlockState()` now return `boolean` instead of `void`, since in some cases the block state may not be set successfully.

### Fixed

- (API) Fixed the bug that `BlockPlaceEvent.getBlockState()` won't return the accurate block state that is being placed.
- Fixed the bug that in some cases entities would still spawn after a server reboot even though they had been killed.
- Fixed `/fill` command behavior, it now more closely matches the vanilla behavior.

### Removed

- (API) Removed `World.isRunning` method, please use `World.getState` method instead.
- (API) Removed `BlockPlaceEvent.getItemUsed()`, consider using `EntityPlayer.getItemInHand()` instead.

## 0.5.0 (API 0.8.0) - 2025-6-19

<small>[Compare with 0.4.1](https://github.com/AllayMC/Allay/compare/0.4.1...HEAD)</small>

### Added

- (API) Introduced new option `raknet-packet-limit` in `ServerSettings` to control the maximum number of datagram
  packets each client can send within a single RakNet tick.
- Added docker and docker-compose support, now you can build docker image by your own (image will be uploaded to Docker Hub in the future).
- Added support for bedrock 1.21.90.

### Changed

- (API) Removed classes under `org.allaymc.api.client.skin`. `SerializedSkin` class in protocol library is used directly now.
- Replaced the outdated event loop group handling with new `MultiThreadIoEventLoopGroup`.
- Refactored permission system, see commit history for more info.

### Fixed

- Fixed block picking behavior.
- Fixed fence gate opening in server-side.
- Fixed quartz and purpur blocks behavior.
- Fixed `BlockFace.UP` axis.
- Fixed potential NPE in light service during server shutdown.

## 0.4.1 (API 0.7.0) - 2025-5-11

<small>[Compare with 0.4.0](https://github.com/AllayMC/Allay/compare/0.4.0...0.4.1)</small>

### Fixed

- Fixed item components.

## 0.4.0 (API 0.7.0) - 2025-5-10

<small>[Compare with 0.3.0](https://github.com/AllayMC/Allay/compare/0.3.0...0.4.0)</small>

### Added

- (API) Added `DamageContainer#fallingBlock(float)`.
- (API) Added `BlockStateWithPos#updateBlockProperty(BlockPropertyType<DATATYPE>, DATATYPE)` method.
- Added Glided Blackstone, Gravel, and Glowstone drop.
- Implemented ladder, infested blocks, ice, snow, sea lantern, dead bush, and end portal frame behavior.
- Implemented basic behavior for scaffolding.
- Implemented chorus plant & flower behavior.
- Implemented walls & fence gate behavior.
- Added support for bedrock 1.21.80.

### Changed

- (API) `onFall()` now accepted `fallDistance`.
- Refactored light engine, and it should work much faster than before with multiple bug fixes.
- Refactored falling block system, see commit history for more info.
- Replaced `PaleMossCarpetSide(FACE)` and `WallConnectionType(FACE)` to `PaleMossCarpetSide` and `WallConnectionType`.
- Refactored `BlockStateWithPos`, now this allows calling `BlockState` methods directly through `BlockStateWithPos`.

### Fixed

- (API) Fixed binomial chance.
- (API) Fixed `DIRECTION_4` mapper.
- Fixed swords block breaking time.
- Fixed `NullPointerException` when breathe disabled.
- Fixed Beetroot, Carrots, Leaves, Potatoes, Redstone Ore, Grass, and Wheat drops.
- Fixed `SetTimePacket` spamming.
- Fixed random bounds.
- Fixed `BlockBreakEvent` cancelling.
- Fixed spam click bug.
- Fixed chicken food points.
- Fixed jukebox music continue playing when broken.
- Fixed incorrect horizontal sky light propagation.
- Fixed `Position3i` math methods.
- Fixed execute commands in terminal.

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
  items, entities, block entities, and world. More PDC types will be added in the future.
- (API) Added `ItemType#getItemData` method which replaces the old `ItemDataComponent`.
- (API) Introduced new option `entity-auto-save-cycle` in `ServerSettings` to control the interval of entity auto save.
- (API) Entities are now held by `EntityService` directly, and a variety of new methods are added into `EntityService`. See the commit
  history for more details.
- (API) Introduced `WorldStorage#readEntities`, `WorldStorage#writeEntities` and their correspond sync methods. These methods are used
  to read and write entities in a specified chunk area.
- (API) Introduced a variety of methods for sending toast, title, subtitle, and actionbar text to player, and new command `/title` is added.
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

- (API) We now used `double`  instead of `float` in entity location, motion, aabb, and some other classes which require high precision.
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
- (API) Implemented TNT entity, block, and related features. Several related interfaces are added to api module.
- Implemented trapdoor except redstone feature (Redstone feature requires the implementation of redstone system).
- Implemented sponge and wet sponge.
- Implemented farmland and hoe.
- Implemented most of the crops, including wheat, potato, carrot, beetroot, melon, and pumpkin.
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
- Implemented liquid features including water, lava, and related features.

### Changed

- (API) `GameRules` is changed to an interface.
- Breaking block related warnings are now moved to debug channel.
- Improved code readability for I18n module.
- NBT library is now updated to 3.0.10.
- `/gametest` command is now only available in dev build.
- Removed the `dimension` field in `BlockBreakEvent`, which is duplicated with `block#dimension`.
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
