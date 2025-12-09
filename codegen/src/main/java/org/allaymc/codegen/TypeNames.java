package org.allaymc.codegen;

import com.palantir.javapoet.ArrayTypeName;
import com.palantir.javapoet.ClassName;

/**
 * @author daoge_cmd
 */
public interface TypeNames {
    // jdk
    ClassName STRING = ClassName.get("java.lang", "String");
    ClassName HASH_MAP = ClassName.get("java.util", "HashMap");
    ClassName MAP = ClassName.get("java.util", "Map");
    ClassName REGISTRIES = ClassName.get("org.allaymc.api.registry", "Registries");
    ClassName LIST = ClassName.get("java.util", "List");

    // 3rd libs
    ClassName GETTER = ClassName.get("lombok", "Getter");
    ClassName DELEGATE = ClassName.get("lombok.experimental", "Delegate");

    // allay
    ClassName API_IDENTIFIER = ClassName.get("org.allaymc.api.utils.identifier", "Identifier");
    ClassName DEP_IDENTIFIER = ClassName.get("org.allaymc.dependence", "Identifier");

    ClassName COMPONENT = ClassName.get("org.allaymc.api.component", "Component");
    ClassName COMPONENT_PROVIDER = ClassName.get("org.allaymc.server.component", "ComponentProvider");

    ClassName BLOCK_BEHAVIOR = ClassName.get("org.allaymc.api.block", "BlockBehavior");
    ClassName BLOCK_BEHAVIOR_IMPL = ClassName.get("org.allaymc.server.block.impl", "BlockBehaviorImpl");
    ClassName BLOCK_ID = ClassName.get("org.allaymc.server.block.data", "BlockId");
    ClassName BLOCK_PROPERTY_TYPE = ClassName.get("org.allaymc.api.block.property.type", "BlockPropertyType");
    ClassName BLOCK_PROPERTY_TYPES = ClassName.get("org.allaymc.api.block.property.type", "BlockPropertyTypes");
    ClassName BLOCK_TYPE = ClassName.get("org.allaymc.api.block.type", "BlockType");
    ClassName BLOCK_TYPES = ClassName.get("org.allaymc.api.block.type", "BlockTypes");
    ClassName BLOCK_TYPE_DEFAULT_INITIALIZER = ClassName.get("org.allaymc.server.block.type", "BlockTypeDefaultInitializer");
    ClassName BLOCK_TAG = ClassName.get("org.allaymc.api.block.data", "BlockTag");
    ClassName ALLAY_BLOCK_TYPE = ClassName.get("org.allaymc.server.block.type", "AllayBlockType");
    ClassName ENUM_PROPERTY = ClassName.get("org.allaymc.api.block.property.type", "EnumPropertyType");
    ClassName BOOLEAN_PROPERTY = ClassName.get("org.allaymc.api.block.property.type", "BooleanPropertyType");
    ClassName INT_PROPERTY = ClassName.get("org.allaymc.api.block.property.type", "IntPropertyType");

    ClassName BIOME_TYPE = ClassName.get("org.allaymc.api.world.biome", "BiomeType");
    ClassName BIOME_ID = ClassName.get("org.allaymc.server.world.biome", "BiomeId");
    ArrayTypeName BIOME_ID_ARRAY = ArrayTypeName.of(BIOME_ID.box());

    ClassName ENTITY = ClassName.get("org.allaymc.api.entity", "Entity");
    ClassName ENTITY_IMPL = ClassName.get("org.allaymc.server.entity.impl", "EntityImpl");
    ClassName ENTITY_INIT_INFO = ClassName.get("org.allaymc.api.entity", "EntityInitInfo");
    ClassName ENTITY_ID = ClassName.get("org.allaymc.api.entity.data", "EntityId");
    ClassName ENTITY_TYPE = ClassName.get("org.allaymc.server.entity.type", "EntityType");
    ClassName ENTITY_TYPES = ClassName.get("org.allaymc.api.entity.type", "EntityTypes");
    ClassName ALLAY_ENTITY_TYPE = ClassName.get("org.allaymc.server.entity.type", "AllayEntityType");
    ClassName ENTITY_TYPE_DEFAULT_INITIALIZER = ClassName.get("org.allaymc.server.entity.type", "EntityTypeDefaultInitializer");

    ClassName ITEM_ID = ClassName.get("org.allaymc.server.item.data", "ItemId");
    ClassName ITEM_STACK = ClassName.get("org.allaymc.api.item", "ItemStack");
    ClassName ITEM_STACK_IMPL = ClassName.get("org.allaymc.server.item.impl", "ItemStackImpl");
    ClassName ITEM_STACK_INIT_INFO = ClassName.get("org.allaymc.api.item", "ItemStackInitInfo");
    ClassName ITEM_TYPE = ClassName.get("org.allaymc.api.item.type", "ItemType");
    ClassName ITEM_TYPES = ClassName.get("org.allaymc.api.item.type", "ItemTypes");
    ClassName ALLAY_ITEM_TYPE = ClassName.get("org.allaymc.server.item.type", "AllayItemType");
    ClassName ITEM_TYPE_DEFAULT_INITIALIZER = ClassName.get("org.allaymc.server.item.type", "ItemTypeDefaultInitializer");
    ClassName ITEM_TAG = ClassName.get("org.allaymc.api.item.data", "ItemTag");

    ClassName TR_KEYS = ClassName.get("org.allaymc.api.message", "TrKeys");
    ClassName MINECRAFT_VERSION_SENSITIVE = ClassName.get("org.allaymc.api.annotation", "MinecraftVersionSensitive");
    ClassName SOUND_NAMES = ClassName.get("org.allaymc.api.world.sound", "SoundNames");
    ClassName CREATIVE_ITEM_GROUPS = ClassName.get("org.allaymc.api.item.creative", "CreativeItemGroups");
}
