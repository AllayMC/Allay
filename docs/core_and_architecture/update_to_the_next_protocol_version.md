---
comments: true
---

This tutorial will detail the general process Allay uses to complete a protocol version update.
In practice, many special situations may arise, and you should not rely entirely on this document.

## 1. Update Extractor

Allay utilizes **Endstone DevTools** to export the necessary large amounts of data from BDS.
Therefore, before updating Allay, we need to update [Endstone](https://github.com/EndstoneMC/endstone).

## 2. Update the First Batch of Resource Files

Resource files must be updated in a specific order.
Allay's resource files are stored in the `Allay-Data/resources` directory.

Unless otherwise specified, the default root directory is `Allay-Data/resources`.

**Step one, directly update the following files:**

- biome_definitions.nbt (obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData))
- block_states.json
- block_types.json
- creative_items.nbt
- entity_identifiers.nbt (obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData))
- items.json
- materials.json
- block_tags_custom.json (this file is manually maintained; check if any block IDs need updating)
- item_tags_custom.json (this file is manually maintained; check if any item IDs need updating)
- recipes.json

**Step two, update the files under the unpacked directory**:

Although these files will not be included in the final jar, they will be used during the code generation process:

- block_tags.json
- item_tags.json
- block_palette.nbt
- block_property_types.json (run `BlockPropertyTypeGen` under `Allay-Data` after updating `block_palette.nbt`)
- biome_id_and_type.json (this file does not change often)
- entity_id_map.json (obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData) or manually updated; this
  file rarely changes)

**Step three, update lang files**:

Firstly, obtain the original language files from BDS and copy them to the `unpacked/lang_raw/vanilla` directory.

Then, run `LangBuilder` under `Allay-Data`.

Finally, run `TrKeysGen` under `Allay-CodeGen`. With this, the language file update is complete.

**Step four, run `ItemMetaToBlockStateMappingsGenerator` under `Allay-Data`**. This script will generate
item_meta_block_state_bimap.nbt based on creative_items.nbt. Remember to delete the old files before running!

## 3. Code Generation

Allay completes most repetitive work through code generation. Next, we'll focus on `Allay-CodeGen`.

**Step one, check if there are any new biomes in `biome_id_and_type.json`**. If so, run `BiomeIdEnumGen`. This
file usually doesn't change much in minor updates.

**Step two, check if there are any new entities in `entity_id_map.json`**. If so, first run `EntityIdEnumGen`,
then run `EntityInterfaceGen`. This file usually doesn't change much in minor updates.

**Step three, check for changes in `item_tags.json` and `block_tags.json`**. If changes exist, run `ItemTagGen`
or `BlockTagGen`.

**Step four, check for changes in `block_property_types.json`**. If changes exist, run `BlockPropertyTypeGen`.

**Step five, run BlockInterfaceGen**. This step requires more manual operation:

- You need to manually delete old blocks. If there are changes in block properties, you need to manually modify them to
  adapt. You can refer to [StateUpdater](https://github.com/AllayMC/StateUpdater) to understand block
  changes.
  By checking if there are errors
  in `Allay-Server/src/main/java/org/allaymc/server/block/type/BlockTypeInitializer.java`, you can quickly determine
  which block properties have changed.
  **Adapting block properties is not just about modifying the parameters of `setProperties()`, you also need to adapt
  the block's code logic**, which is crucial!

- If there is a batch of similar blocks, you need to register new sub-packages in the `registerSubPackages()` method
  of `BlockInterfaceGen` to avoid overcrowding the block class group.

**Step six, run `ItemInterfaceGen`**. Similar to blocks, this step also requires a certain amount of manual
operation, but the workload is less than before:

- You need to manually delete old items. If an item has only changed its name, you need `to migrate code logic from the
  old item to the new one.`

- Even if there are no additions or deletions of items, the logic of implemented items may change in the original
  version. To respect the original version, Allay needs to synchronize changes. However, if we are only discussing
  protocol updates, this can be deferred.

- If there is a batch of similar items, you need to register new sub-packages in the `registerSubPackages()` method
  of `ItemInterfaceGen` to avoid overcrowding the item class group.

## 4. Update Dependencies

Update the protocol library [Cloudburst/Protocol](https://github.com/CloudburstMC/Protocol) and the block state
updater [AllayMC/StateUpdater](https://github.com/AllayMC/StateUpdater) to the latest version.

## 5. Update `Allay-API/src/main/java/org/allaymc/api/network/ProtocolInfo.java`

You need to update the `PACKET_CODEC` and `MINECRAFT_VERSION` in `ProtocolInfo`. Refer
to [pmmp/PocketMine-MP](https://github.com/pmmp/PocketMine-MP) for updating `MINECRAFT_VERSION`.
Make sure the dependency libraries are updated before this!

## 6. Test and Complete the Update

Run `gradle test` to ensure nothing is broken, then update the client and test on the server (this is very important!).
The update is now complete.