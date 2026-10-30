package org.allaymc.codegen;

import com.palantir.javapoet.ArrayTypeName;
import com.palantir.javapoet.ClassName;

/**
 * {@code TypeNames} defines JavaPoet symbols shared by the source generators.
 * Targets are specified by package and class name so generation can describe
 * API and server types without loading those classes. The same symbols drive
 * generated signatures, annotations, initializers, and imports across related
 * generators.
 *
 * @author daoge_cmd
 */
public interface TypeNames {
    // These symbols supply common Java values and collections, together with
    // the runtime registry entry point used in generated lookup methods.
    /** JavaPoet symbol for {@code java.lang.String}. */
    ClassName STRING = ClassName.get("java.lang", "String");
    /** JavaPoet symbol for {@code java.util.HashMap}. */
    ClassName HASH_MAP = ClassName.get("java.util", "HashMap");
    /** JavaPoet symbol for {@code java.util.Map}. */
    ClassName MAP = ClassName.get("java.util", "Map");
    /** JavaPoet symbol for {@code org.allaymc.api.registry.Registries}. */
    ClassName REGISTRIES = ClassName.get("org.allaymc.api.registry", "Registries");
    /** JavaPoet symbol for {@code java.util.List}. */
    ClassName LIST = ClassName.get("java.util", "List");

    // These annotation symbols describe Lombok-generated access and delegation
    // in emitted classes without requiring those classes to exist during generation.
    /** JavaPoet symbol for {@code lombok.Getter}. */
    ClassName GETTER = ClassName.get("lombok", "Getter");
    /** JavaPoet symbol for {@code lombok.experimental.Delegate}. */
    ClassName DELEGATE = ClassName.get("lombok.experimental", "Delegate");

    // These project symbols keep generator output directed at the corresponding
    // API contracts, runtime implementations, and generated catalogs throughout the modules.
    /** JavaPoet symbol for {@code org.allaymc.api.utils.identifier.Identifier}. */
    ClassName API_IDENTIFIER = ClassName.get("org.allaymc.api.utils.identifier", "Identifier");
    /** JavaPoet symbol for {@code org.allaymc.dependence.Identifier}. */
    ClassName DEP_IDENTIFIER = ClassName.get("org.allaymc.dependence", "Identifier");

    /** JavaPoet symbol for {@code org.allaymc.api.component.Component}. */
    ClassName COMPONENT = ClassName.get("org.allaymc.api.component", "Component");
    /** JavaPoet symbol for {@code org.allaymc.server.component.ComponentProvider}. */
    ClassName COMPONENT_PROVIDER = ClassName.get("org.allaymc.server.component", "ComponentProvider");

    /** JavaPoet symbol for {@code org.allaymc.api.block.BlockBehavior}. */
    ClassName BLOCK_BEHAVIOR = ClassName.get("org.allaymc.api.block", "BlockBehavior");
    /** JavaPoet symbol for {@code org.allaymc.server.block.impl.BlockBehaviorImpl}. */
    ClassName BLOCK_BEHAVIOR_IMPL = ClassName.get("org.allaymc.server.block.impl", "BlockBehaviorImpl");
    /** JavaPoet symbol for {@code org.allaymc.server.block.data.BlockId}. */
    ClassName BLOCK_ID = ClassName.get("org.allaymc.server.block.data", "BlockId");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.api.block.property.type.BlockPropertyType}.
     */
    ClassName BLOCK_PROPERTY_TYPE = ClassName.get("org.allaymc.api.block.property.type", "BlockPropertyType");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.api.block.property.type.BlockPropertyTypes}.
     */
    ClassName BLOCK_PROPERTY_TYPES = ClassName.get("org.allaymc.api.block.property.type", "BlockPropertyTypes");
    /** JavaPoet symbol for {@code org.allaymc.api.block.type.BlockType}. */
    ClassName BLOCK_TYPE = ClassName.get("org.allaymc.api.block.type", "BlockType");
    /** JavaPoet symbol for {@code org.allaymc.api.block.type.BlockTypes}. */
    ClassName BLOCK_TYPES = ClassName.get("org.allaymc.api.block.type", "BlockTypes");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.server.block.type.BlockTypeDefaultInitializer}.
     */
    ClassName BLOCK_TYPE_DEFAULT_INITIALIZER = ClassName.get("org.allaymc.server.block.type", "BlockTypeDefaultInitializer");
    /** JavaPoet symbol for {@code org.allaymc.api.block.data.BlockTag}. */
    ClassName BLOCK_TAG = ClassName.get("org.allaymc.api.block.data", "BlockTag");
    /** JavaPoet symbol for {@code org.allaymc.server.block.type.AllayBlockType}. */
    ClassName ALLAY_BLOCK_TYPE = ClassName.get("org.allaymc.server.block.type", "AllayBlockType");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.api.block.property.type.EnumPropertyType}.
     */
    ClassName ENUM_PROPERTY = ClassName.get("org.allaymc.api.block.property.type", "EnumPropertyType");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.api.block.property.type.BooleanPropertyType}.
     */
    ClassName BOOLEAN_PROPERTY = ClassName.get("org.allaymc.api.block.property.type", "BooleanPropertyType");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.api.block.property.type.IntPropertyType}.
     */
    ClassName INT_PROPERTY = ClassName.get("org.allaymc.api.block.property.type", "IntPropertyType");

    /** JavaPoet symbol for {@code org.allaymc.api.world.biome.BiomeType}. */
    ClassName BIOME_TYPE = ClassName.get("org.allaymc.api.world.biome", "BiomeType");
    /** JavaPoet symbol for {@code org.allaymc.api.world.biome.BiomeTag}. */
    ClassName BIOME_TAG = ClassName.get("org.allaymc.api.world.biome", "BiomeTag");
    /** JavaPoet symbol for {@code org.allaymc.server.world.biome.BiomeId}. */
    ClassName BIOME_ID = ClassName.get("org.allaymc.server.world.biome", "BiomeId");
    /** Array of generated biome-identifier values. */
    ArrayTypeName BIOME_ID_ARRAY = ArrayTypeName.of(BIOME_ID.box());

    /** JavaPoet symbol for {@code org.allaymc.api.entity.Entity}. */
    ClassName ENTITY = ClassName.get("org.allaymc.api.entity", "Entity");
    /** JavaPoet symbol for {@code org.allaymc.server.entity.impl.EntityImpl}. */
    ClassName ENTITY_IMPL = ClassName.get("org.allaymc.server.entity.impl", "EntityImpl");
    /** JavaPoet symbol for {@code org.allaymc.api.entity.EntityInitInfo}. */
    ClassName ENTITY_INIT_INFO = ClassName.get("org.allaymc.api.entity", "EntityInitInfo");
    /** JavaPoet symbol for {@code org.allaymc.server.entity.data.EntityId}. */
    ClassName ENTITY_ID = ClassName.get("org.allaymc.server.entity.data", "EntityId");
    /** JavaPoet symbol for {@code org.allaymc.api.entity.type.EntityType}. */
    ClassName ENTITY_TYPE = ClassName.get("org.allaymc.api.entity.type", "EntityType");
    /** JavaPoet symbol for {@code org.allaymc.api.entity.type.EntityTypes}. */
    ClassName ENTITY_TYPES = ClassName.get("org.allaymc.api.entity.type", "EntityTypes");
    /** JavaPoet symbol for {@code org.allaymc.server.entity.type.AllayEntityType}. */
    ClassName ALLAY_ENTITY_TYPE = ClassName.get("org.allaymc.server.entity.type", "AllayEntityType");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.server.entity.type.EntityTypeDefaultInitializer}.
     */
    ClassName ENTITY_TYPE_DEFAULT_INITIALIZER = ClassName.get("org.allaymc.server.entity.type", "EntityTypeDefaultInitializer");

    /** JavaPoet symbol for {@code org.allaymc.server.item.data.ItemId}. */
    ClassName ITEM_ID = ClassName.get("org.allaymc.server.item.data", "ItemId");
    /** JavaPoet symbol for {@code org.allaymc.api.item.ItemStack}. */
    ClassName ITEM_STACK = ClassName.get("org.allaymc.api.item", "ItemStack");
    /** JavaPoet symbol for {@code org.allaymc.server.item.impl.ItemStackImpl}. */
    ClassName ITEM_STACK_IMPL = ClassName.get("org.allaymc.server.item.impl", "ItemStackImpl");
    /** JavaPoet symbol for {@code org.allaymc.api.item.ItemStackInitInfo}. */
    ClassName ITEM_STACK_INIT_INFO = ClassName.get("org.allaymc.api.item", "ItemStackInitInfo");
    /** JavaPoet symbol for {@code org.allaymc.api.item.type.ItemType}. */
    ClassName ITEM_TYPE = ClassName.get("org.allaymc.api.item.type", "ItemType");
    /** JavaPoet symbol for {@code org.allaymc.api.item.type.ItemTypes}. */
    ClassName ITEM_TYPES = ClassName.get("org.allaymc.api.item.type", "ItemTypes");
    /** JavaPoet symbol for {@code org.allaymc.server.item.type.AllayItemType}. */
    ClassName ALLAY_ITEM_TYPE = ClassName.get("org.allaymc.server.item.type", "AllayItemType");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.server.item.type.ItemTypeDefaultInitializer}.
     */
    ClassName ITEM_TYPE_DEFAULT_INITIALIZER = ClassName.get("org.allaymc.server.item.type", "ItemTypeDefaultInitializer");
    /** JavaPoet symbol for {@code org.allaymc.api.item.data.ItemTag}. */
    ClassName ITEM_TAG = ClassName.get("org.allaymc.api.item.data", "ItemTag");

    /** JavaPoet symbol for {@code org.allaymc.api.message.TrKeys}. */
    ClassName TR_KEYS = ClassName.get("org.allaymc.api.message", "TrKeys");
    /**
     * JavaPoet symbol for
     * {@code org.allaymc.api.annotation.MinecraftVersionSensitive}.
     */
    ClassName MINECRAFT_VERSION_SENSITIVE = ClassName.get("org.allaymc.api.annotation", "MinecraftVersionSensitive");
    /** JavaPoet symbol for {@code org.allaymc.api.world.sound.SoundNames}. */
    ClassName SOUND_NAMES = ClassName.get("org.allaymc.api.world.sound", "SoundNames");
    /** JavaPoet symbol for {@code org.allaymc.api.item.creative.CreativeItemGroups}. */
    ClassName CREATIVE_ITEM_GROUPS = ClassName.get("org.allaymc.api.item.creative", "CreativeItemGroups");
}
