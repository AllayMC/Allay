# Upgrade to the next minecraft version

## 1. Update resource files

(Files under Allay-Server/src/main/resources)

- ```block-attributes.json``` (Using tool AllayMC/DataExtractor to generate)
- ```block-palette.nbt``` (From CloudburstMC/Data)

(Files under Allay-Server/src/test/resources)

- ```minecraft-bedrock-data.json``` (From XeroAlpha/caidlist, the original name was ```all.json```)

## 2. Update the auto-generated code

- ```cn/allay/block/data/VanillaBlockId.java``` (Using Allay-API/src/test/cn/allay/codegen/VanillaBlockIdEnumGen.java)
- ```cn/allay/block/property/vanilla/*``` (Using Allay-API/src/test/cn/allay/codegen/VanillaBlockPropertyEnumGen.java)