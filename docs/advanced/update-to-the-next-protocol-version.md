---
comments: true
---

# Update to The Next Protocol Version

This tutorial will detail the general process Allay uses to complete a protocol version update.
In practice, many special situations may arise, and you should not rely entirely on this document.

## Update Endstone

Allay use **Endstone DevTools** to export the necessary large amounts of data from BDS.
Therefore, before updating Allay, we need to update [Endstone](https://github.com/EndstoneMC/endstone).

## Update the First Batch of Resource Files

Please note that resource files must be updated in a specific order.
Allay's resource files are stored in `data/resources` directory.

!!! tip

    Unless otherwise specified, the default root directory is `data/resources`.

### Directly update the following files

- `biome_definitions.nbt`: obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData)
- `block_types.json`
- `creative_items.nbt`
- `entity_identifiers.nbt`: obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData)
- `items.json`
- `materials.json`
- `block_tags_custom.json`: this file is manually maintained, and you should check if any IDs need updating.
- `item_tags_custom.json`: this file is manually maintained, and you should check if any IDs need updating.
- `recipes.json`

### Update the files under the `unpacked` directory

Although these files will not be included in the final jar, they will be used during the code generation process:

- `block_tags.json`
- `block_states_raw.json`: rename `block_states.json` to `block_states_raw.json` and place it in the `unpacked` directory.
- `item_tags.json`
- `block_palette.nbt`
- `block_property_types.json`: run `BlockPropertyTypeGen` under `data` after updating `block_palette.nbt`
- `biome_id_and_type.json`: obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData)
- `entity_id_map.json`: obtained from [pmmp/BedrockData](https://github.com/pmmp/BedrockData)

### Generate `block_states.json`

**Run `BlockStateDataProcessor` under `data`**. This script will generate `block_states.json` based on `block_states_raw.json`.

### Update Lang Files

Firstly, obtain the original language files from BDS and copy them to the `unpacked/lang_raw/vanilla` directory. 
Then, run `LangBuilder` under `data`. Finally, run `TrKeysGen` under `codegen`. With this, the language file update is complete.

### Generate `item_meta_block_state_bimap.nbt`

**Run `ItemMetaToBlockStateMappingsGenerator` under `data`**. This script will generate
item_meta_block_state_bimap.nbt based on creative_items.nbt. Remember to delete the old files before running!

## Code Generation

Allay completes most repetitive work through code generation. Next, we'll focus on `codegen`.

**Check if there are any new biomes in `biome_id_and_type.json`**. If so, run `BiomeIdEnumGen`. This file usually doesn't change much in minor updates.

**Check if there are any new entities in `entity_id_map.json`**. If so, first run `EntityIdEnumGen`, then run `EntityInterfaceGen`. This file usually doesn't change much in minor updates.

**Check for changes in `item_tags.json` and `block_tags.json`**. If changes exist, run `ItemTagGen`or `BlockTagGen`.

**Check for changes in `block_property_types.json`**. If changes exist, run `BlockPropertyTypeGen`.

**Run BlockInterfaceGen**. This step requires more manual operation:

- You need to manually delete old blocks. If there are changes in block properties, you need to manually modify them to
  adapt. You can refer to [StateUpdater](https://github.com/AllayMC/StateUpdater) to understand block
  changes.
  By checking if there are errors
  in `server/src/main/java/org/allaymc/server/block/type/BlockTypeInitializer.java`, you can quickly determine
  which block properties have changed.
  **Adapting block properties is not just about modifying the parameters of `setProperties()`, you also need to adapt
  the block's code logic**, which is crucial!
- If there is a batch of similar blocks, you need to register new sub-packages in the `registerSubPackages()` method
  of `BlockInterfaceGen` to avoid overcrowding the block class group.

**Run `ItemInterfaceGen`**. Similar to blocks, this step also requires a certain amount of manual operation, but the workload is less than before:

- You need to manually delete old items. If an item has only changed its name, you need `to migrate code logic from the
  old item to the new one.`
- Even if there are no additions or deletions of items, the logic of implemented items may change in the original
  version. To respect the original version, Allay needs to synchronize changes. However, if we are only discussing
  protocol updates, this can be deferred.
- If there is a batch of similar items, you need to register new sub-packages in the `registerSubPackages()` method
  of `ItemInterfaceGen` to avoid overcrowding the item class group.

## Update Dependencies

Update the protocol library [Cloudburst/Protocol](https://github.com/CloudburstMC/Protocol) and the block state
updater [AllayMC/StateUpdater](https://github.com/AllayMC/StateUpdater) to the latest version.

## Update `ProtocolInfo.java`

You need to update the `PACKET_CODEC` and `MINECRAFT_VERSION` in `ProtocolInfo`. If you can't figure out the new value of `MINECRAFT_VERSION`, you can refer
to [pmmp/PocketMine-MP](https://github.com/pmmp/PocketMine-MP).
Make sure the dependency libraries are updated before this!

## Test and Complete the Update

Run `gradle test` to ensure nothing is broken, then update the client and test on the server (this is very important!).
The update is now complete.