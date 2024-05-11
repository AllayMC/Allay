---
comments: true
---

This tutorial will detail the general process for completing a protocol version update in Allay. There may be many
special cases in practice, so reliance solely on this document may not be sufficient.

## 1. Update Extractor

Allay relies on LeviLamina to export the massive data required from BDS. Therefore, before updating Allay, we need to
update Allay's exporter, **DataExtractor**.

Generally, the workload shouldn't be particularly large. There may be some functions added or removed internally in the
new version of BDS, which will require manual handling.

There are many pitfalls in **DataExtractor**, which will be detailed later.

## 2. Update the First Batch of Resource Files

Resource file updates have a specific order. All of Allay's resource files are stored in the `Allay-Data/resources`
directory.

If not specifically stated, the default root directory is `Allay-Data/resources`.

**Step one, directly update the following files:**

- biome_definitions.nbt
- block_attributes.nbt
- creative_items.nbt
- entity_identifiers.nbt
- item_data.nbt
- item_tags.json

**Step two, update the files under the unpacked directory**:

Although these files will not be packaged into the jar, they will be used during the code generation phase:

- block_palette.nbt
- block_property_types.json
- biome_id_and_type.json (this file rarely changes)
- entity_id_map.json (obtained from **pmmp/BedrockData** or manually updated, this file rarely changes)

**Step three, update lang files**:

Firstly, obtain the original language files from BDS and copy them to the `unpacked/lang_raw/vanilla` directory.

Then, run `LangBuilder` under `Allay-Data`.

Finally, run `TrKeysGen` under `Allay-CodeGen`. With this, the language file update is complete.

**Step four, run `ItemMetaToBlockStateMappingsGenerator` under `Allay-Data`**. This script will generate
item_meta_block_state_bimap.nbt based on creative_items.nbt. Remember to delete the old files before running!

## 3. Code Generation

Allay completes most repetitive work through code generation. Next, we'll focus on `Allay-CodeGen`.

**Step one, check if there are any new biomes in `biome_id_and_type.json`**. If so, run `VanillaBiomeIdEnumGen`. This
file usually doesn't change much in minor updates.

**Step two, check if there are any new entities in `entity_id_map.json`**. If so, first run `VanillaEntityIdEnumGen`,
then run `VanillaEntityInterfaceGen`. This file usually doesn't change much in minor updates.

**Step three, check for changes in `item_tags.json`**. If changes exist, run `VanillaItemTagGen`.

**Step four, check for changes in `block_property_types.json`**. If changes exist, run `VanillaBlockPropertyTypeGen`.

**Step five, run VanillaBlockInterfaceGen**. This step requires more manual operation:

- You need to manually delete old blocks. If there are changes in block properties, you need to manually modify them to
  adapt. You can refer to [BlockStateUpdater](https://github.com/CloudburstMC/BlockStateUpdater) to understand block
  changes.
  By checking if there are errors
  in `Allay-Server/src/main/java/org/allaymc/server/block/type/BlockTypeInitializer.java`, you can quickly determine
  which block properties have changed.
  **Adapting block properties is not just about modifying the parameters of `setProperties()`, you also need to adapt
  the block's code logic**, which is crucial!

- If there is a batch of similar blocks, you need to register new sub-packages in the `registerSubPackages()` method
  of `VanillaBlockInterfaceGen` to avoid overcrowding the block class group.

**Step six, run `VanillaItemInterfaceGen`**. Similar to blocks, this step also requires a certain amount of manual
operation, but the workload is less than before:

- You need to manually delete old items. If an item has only changed its name, you need to migrate code logic from the
  old item to the new one.

- Even if there are no additions or deletions of items, the logic of implemented items may change in the original
  version. To respect the original version, Allay needs to synchronize changes. However, if we are only discussing
  protocol updates, this can be deferred.

- If there is a batch of similar items, you need to register new sub-packages in the `registerSubPackages()` method
  of `VanillaItemInterfaceGen` to avoid overcrowding the item class group.

## 4. Update Dependencies

Update the protocol library `Cloudburst/Protocol` and the block state updater `CloudburstMC/BlockStateUpdater` to the
latest version.

## 5. Update `Allay-API/src/main/java/org/allaymc/api/network/ProtocolInfo.java`

You need to update `PACKET_CODEC` and `MINECRAFT_VERSION` in `ProtocolInfo`, with reference to **pmmp** for
updating `MINECRAFT_VERSION`. Ensure that the dependencies have been updated before this step!

## 6. Generate recipes.json (Recipe File)

**Copy `crafting_data_packet.bin` to `Allay-Data/resources` and run `RecipeExportUtil`**. Before running this, ensure
that you have correctly completed the updates for blocks and items! Remember to delete the old files before running!

If all goes well, `Allay-Data/resources/recipes.json` will be updated. With this, all resource files have been updated.
Remove the previously copied `crafting_data_packet.bin`.

## 7. Testing and Completion of Update

Run `gradle test` to ensure that nothing has been broken, then update the client and test on the server (this is
crucial!). Update completed.