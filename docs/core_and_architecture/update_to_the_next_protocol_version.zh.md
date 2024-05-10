---
comments: true
---

# 更新到下一个协议版本

此教程将详细介绍Allay完成一次协议版本更新的大体流程。实际可能遇到很多特殊情况，不能完全依赖于此文档。

### 1.更新导出器

Allay借助LeviLamina从BDS导出所需的海量数据。故在更新Allay之前，我们需要先更新Allay的导出器DataExtractor。

通常来说工作量不会特别大，新版本BDS内部可能会有一些函数的增删，这需要人工处理。

DataExtractor有很多容易踩的坑，后面会详细说明一下

### 2.更新第一批资源文件

资源文件更新存在先后顺序。Allay的所有资源文件都存放在Allay-Data/resources目录下。

如未特别说明，默认根目录为Allay-Data/resources

**第一步，直接更新以下文件:**

- biome_definitions.nbt
- block_attributes.nbt
- creative_items.nbt
- entity_identifiers.nbt
- item_data.nbt
- item_tags.json

**第二步，更新unpacked目录下的文件**。这些文件虽然说不会被打包进jar内，但是会在代码生成阶段被使用：

- block_palette.nbt
- block_property_types.json
- biome_id_and_type.json（这个文件不经常变动）
- entity_id_map.json（从pmmp/BedrockData获取或手动更新，这个文件不经常变动）

**第三步，更新lang文件**

首先从BDS获取到原版语言文件，并复制到`unpacked/lang_raw/vanilla`目录下

然后，运行`Allay-Data`下的`LangBuilder`。

最后，运行`Allay-CodeGen`下的`TrKeysGen`。自此完成语言文件更新。

**第四步，运行`Allay-Data`下的`ItemMetaToBlockStateMappingsGenerator`**，此脚本会基于creative_items.nbt生成item_meta_block_state_bimap.nbt。运行前记得要删除旧的文件！

### 3.代码生成

Allay通过代码生成完成大部分重复工作。接下来我们将注意力转移到`Allay-CodeGen`

**第一步，检查`biome_id_and_type.json`里面是否存在新的群系**。若存在，运行`VanillaBiomeIdEnumGen`。此文件小版本更新通常不会发生改变。

**第二步，检查`entity_id_map.json`里面是否存在新的实体**。若存在，先运行`VanillaEntityIdEnumGen`，然后运行`VanillaEntityInterfaceGen`。此文件小版本更新通常不会发生改变。

**第三步，检查`item_tags.json`里面是否存在变动**。若存在，运行`VanillaItemTagGen`。

**第四步，检查`block_property_types.json`里面是否存在变动**。若存在，运行`VanillaBlockPropertyTypeGen`。

**第五步，运行VanillaBlockInterfaceGen**。此步需要较多人工操作：

- 你需要手动删除旧的方块，若存在方块属性变动，你需要手动修改以适配。你可以查看[BlockStateUpdater](https://github.com/CloudburstMC/BlockStateUpdater)来了解方块更改情况。
  通过查看`Allay-Server/src/main/java/org/allaymc/server/block/type/BlockTypeInitializer.java`内是否存在报错，你可以快速确定哪些方块属性发生了变动， 
  **方块属性适配不仅仅是修改setProperties()的传参，你同样需要适配方块的代码逻辑**，这点很重要！
- 若存在一批相似的方块，你需要在`VanillaBlockInterfaceGen`的`registerSubPackages()`方法中注册新的子包避免方块类群过于冗杂。

**第六步，运行`VanillaItemInterfaceGen`**。与方块类似，此步同样需要一定量的人工操作，不过工作量少于前者：

- 你需要手动删除旧的物品。如果只是物品改了个名称，你需要从旧的物品迁移代码逻辑到新物品。
- 即使不存在物品增删，已实现物品的逻辑在原版中也有可能发生改变，为了尊重原版，Allay需要同步更改。当然，如果只讨论协议更新的话，这点可以暂缓。
- 若存在一批相似的物品，你需要在`VanillaItemInterfaceGen`的`registerSubPackages()`方法中注册新的子包避免物品类群过于冗杂。

### 4.更新依赖

更新Allay使用的协议库`Cloudburst/Protocol`以及方块状态更新器`CloudburstMC/BlockStateUpdater`到最新

### 5.更新`Allay-API/src/main/java/org/allaymc/api/network/ProtocolInfo.java`

你需要更新`ProtocolInfo`里的`PACKET_CODEC`以及`MINECRAFT_VERSION`，`MINECRAFT_VERSION`的更新参考pmmp。确保在此之前依赖库已更新！

### 6.生成recipes.json（配方文件）

**复制`crafting_data_packet.bin`到`Allay-Data/resources`下，运行`RecipeExportUtil`**。在此之前你需要确保你已经正确完成方块和物品的更新！运行前记得要删除旧的文件！

若不出意外，`Allay-Data/resources/recipes.json`会被更新。自此资源文件已经全部更新完毕，删除先前复制的`crafting_data_packet.bin`。

### 7.测试，完成更新

运行`gradle test`确保没有东西被破坏，然后更新客户端，进服测试（这很重要！）。更新完毕。