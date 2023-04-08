# Upgrade to the next minecraft version

## 1. Update resource files

(Files under Allay-Server/src/main/resources)

- ```block-attributes.json``` (Using tool AllayMC/DataExtractor to generate)
- ```block-palette.nbt``` (From CloudburstMC/Data)

(Files under Allay-Server/src/test/resources)

- ```minecraft-bedrock-data.json``` (From XeroAlpha/caidlist, the original name was ```all.json```)

## 2. Update the auto-generated code

(Using Allay-API/src/test/cn/allay/codegen/CodeGenMain.java to update, Under normal circumstances, the following files should not be modified manually)

- ```cn/allay/block/data/VanillaBlockId.java``` 
- ```cn/allay/block/property/vanilla/*``` 