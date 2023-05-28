# Upgrade to the next minecraft version

## 1. Update resource files

(Files under Allay-Server/src/main/resources)

- ```block_attributes.json``` (Using tool AllayMC/DataExtractor to generate)
- ```block_palette.nbt``` (From CloudburstMC/Data)

If the block-palette.nbt file is not updated in time, you can also use the [file](https:github.compmmpBedrockDatablobmastercanonical_block_states.nbt) provided by PMMP.

But please note that the files provided by PMMP cannot be used directly, you need to convert them into cb format files. Here is a ready-made script to help you: ```Allay-API\src\test\java\cn\allay\converter\BlockPaletteFileConverter.java```

The output file is named ```block_palette_converted.nbt```

## 2. Update the auto-generated code

(Using Allay-API/src/test/cn/allay/codegen/CodeGen.java to update. Under normal circumstances, the following files
should not be modified manually)

- ```cn/allay/block/data/VanillaBlockId.java```
- ```cn/allay/block/property/vanilla/*``` 

After the code is generated, you also need to apply "Format Code" to the generated code

Note that code generation is not 100% reliable,
for example when block name changes/state splits occur `````e.g. wool was split into separate blocks in 1.19.80`````, you need to manually handle the changes and make sure they work correctly in the new version