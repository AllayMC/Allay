package org.allaymc.testutils;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.TestComponentImpl;
import org.allaymc.server.block.type.*;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.item.type.CustomItemDefinition;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@code TestRegistryFixtures} supplies shared custom block and item definitions used by
 * registry, component, protocol-definition, and Molang tests. Registration constructs these
 * types before the test server starts freezing registries, and accessors reuse the same
 * global definitions rather than creating separate types for each test.
 *
 * <p>
 * The synchronized registration method sets {@code registered} only after every build
 * succeeds. A failed build leaves earlier registrations and field assignments in place;
 * there is no rollback, and retrying can encounter that partial state. Accessors check the
 * plain flag without acquiring its monitor, so callers arrange registration and publication
 * before using fixtures on another thread. Property constants are available before the full
 * registration pass, while the custom-definition counter records later callback evaluations
 * without resetting between tests.
 */
public final class TestRegistryFixtures {
    // BLOCK_BOOLEAN_PROPERTY, BLOCK_INT_PROPERTY, and BLOCK_ENUM_PROPERTY define the shared
    // component-test state space. MOLANG_BOOLEAN_PROPERTY, MOLANG_INT_PROPERTY, and MOLANG_ENUM_PROPERTY
    // define a separate smaller state space consumed by the Molang fixture block types.
    /**
     * Component-test boolean state property.
     */
    private static final BlockPropertyType<Boolean> BLOCK_BOOLEAN_PROPERTY =
            BooleanPropertyType.of("test_boolean", false);
    /**
     * Component-test bounded integer property.
     */
    private static final BlockPropertyType<Integer> BLOCK_INT_PROPERTY =
            IntPropertyType.of("test_int", 0, 10, 0);
    /**
     * Component-test enum state property.
     */
    private static final BlockPropertyType<TestEnum> BLOCK_ENUM_PROPERTY =
            EnumPropertyType.of("test_enum", TestEnum.class, TestEnum.A);
    /**
     * Molang-test boolean state property.
     */
    private static final BlockPropertyType<Boolean> MOLANG_BOOLEAN_PROPERTY =
            BooleanPropertyType.of("test_bool", false);
    /**
     * Molang-test bounded integer property.
     */
    private static final BlockPropertyType<Integer> MOLANG_INT_PROPERTY =
            IntPropertyType.of("test_int", 0, 3, 0);
    /**
     * Molang-test enum state property.
     */
    private static final BlockPropertyType<TestEnum> MOLANG_ENUM_PROPERTY =
            EnumPropertyType.of("test_enum", TestEnum.class, TestEnum.A);
    /**
     * {@code CUSTOMIZED_ITEM_TAG} identifies the tag installed on the customized block-item
     * fixture.
     * Tests compare the generated item type's membership against this same shared tag instance.
     */
    private static final ItemTag CUSTOMIZED_ITEM_TAG = new ItemTag("minecraft:test_auto_generated_block_item");

    // register assigns blockType1, blockType2, customizedItemBlockType, molangBlockTypeWithAllProperties,
    // molangBlockTypeWithBooleanOnly, molangBlockTypeWithoutProperties, customDefinitionBlockType, and
    // customDefinitionItemType before setting registered. Their accessors require registration and
    // return these shared fixture types rather than independent test-local copies.
    /**
     * Static-data component block fixture.
     */
    private static BlockType<TestBlock> blockType1;
    /**
     * Dynamic-data component block fixture.
     */
    private static BlockType<TestBlock> blockType2;
    /**
     * Customized generated-item block fixture.
     */
    private static BlockType<TestBlock> customizedItemBlockType;
    /**
     * Three-property Molang block fixture.
     */
    private static BlockType<TestBlock> molangBlockTypeWithAllProperties;
    /**
     * Boolean-only Molang block fixture.
     */
    private static BlockType<TestBlock> molangBlockTypeWithBooleanOnly;
    /**
     * Property-free Molang block fixture.
     */
    private static BlockType<TestBlock> molangBlockTypeWithoutProperties;
    /**
     * Custom client-definition block fixture.
     */
    private static BlockType<TestBlock> customDefinitionBlockType;
    /**
     * Custom client-definition item fixture.
     */
    private static ItemType<?> customDefinitionItemType;
    /**
     * {@code CUSTOM_BLOCK_DEFINITION_INVOCATIONS} counts custom block-definition callback
     * evaluation
     * across tests. The counter is shared and is not reset by accessor calls or a repeated
     * successful registration request.
     */
    private static final AtomicInteger CUSTOM_BLOCK_DEFINITION_INVOCATIONS = new AtomicInteger();
    /**
     * {@code registered} becomes {@code true} only after every fixture build succeeds under the
     * registration
     * monitor. Accessors read it without that monitor, so callers establish publication before
     * using fixtures across threads.
     */
    private static boolean registered;

    /**
     * Prevents construction of the shared custom-type and property fixture holder.
     */
    private TestRegistryFixtures() {
    }

    /**
     * Builds and retains shared fixture types before the server freezes their registries.
     * Repeated calls return immediately after a completed registration pass, and the method's
     * monitor serializes competing registration attempts.
     *
     * <p>
     * The completion flag is written only after all block and item builds finish. A failure
     * leaves earlier global registry entries and assigned fields intact rather than rolling them
     * back, so another attempt can encounter partial registration. Readers do not acquire this
     * monitor through the accessors and therefore rely on caller-established startup ordering and
     * publication before concurrent fixture use.
     */
    public static synchronized void register() {
        if (registered) {
            return;
        }

        // Fixture types must exist before server startup freezes registries. Complete all
        // builds before marking registration finished so accessors reject an unfinished setup pass.
        blockType1 = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:test_block")
                .setProperties(BLOCK_BOOLEAN_PROPERTY, BLOCK_INT_PROPERTY, BLOCK_ENUM_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder().burnOdds(2).build())))
                .build();
        blockType2 = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:test_block2")
                .setProperties(BLOCK_BOOLEAN_PROPERTY, BLOCK_INT_PROPERTY, BLOCK_ENUM_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDirectDynamic(blockState -> BlockStateData.builder().burnOdds(3).build())))
                .build();
        customizedItemBlockType = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:test_block_with_customized_item")
                .setComponents(List.of(new TestComponentImpl()))
                .customizeItemType(itemBuilder -> itemBuilder.setItemTags(Set.of(CUSTOMIZED_ITEM_TAG)))
                .customBlockDefinition(CustomBlockStateDefinition.ofTexture("allay_test_constant_block"))
                .build();
        molangBlockTypeWithAllProperties = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:molang_test_block")
                .setProperties(MOLANG_BOOLEAN_PROPERTY, MOLANG_INT_PROPERTY, MOLANG_ENUM_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
        molangBlockTypeWithBooleanOnly = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:molang_test_bool_block")
                .setProperties(MOLANG_BOOLEAN_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
        molangBlockTypeWithoutProperties = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:molang_no_props")
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
        customDefinitionBlockType = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("allay_test:custom_definition_block")
                .setProperties(BLOCK_BOOLEAN_PROPERTY)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder()
                                .collisionShape(VoxelShape.builder()
                                        .solid(0, 0, 0, 0.25, 0.5, 0.25)
                                        .solid(0.5, 0.125, 0.5, 1, 1, 1)
                                        .build())
                                .build())))
                .customBlockDefinition(state -> {
                    // Count callback evaluations so definition tests can distinguish cached
                    // reuse from another invocation without rebuilding or resetting shared fixture types.
                    CUSTOM_BLOCK_DEFINITION_INVOCATIONS.incrementAndGet();
                    return CustomBlockStateDefinition.builder()
                            .geometry("geometry.allay_test.custom_block")
                            .materials(CustomBlockStateDefinition.Materials.builder()
                                    .any(CustomBlockStateDefinition.MaterialInstance.builder()
                                            .texture("allay_test_custom_block")
                                            .faceDimming(true)
                                            .randomUVRotation(true)
                                            .ambientOcclusionIntensity(1f)
                                            .build())
                                    .build())
                            .displayName("tile.allay_test.custom_definition_block.name")
                            .rawComponents(Map.of(
                                    "minecraft:collision_box",
                                    NbtMap.builder().putBoolean("enabled", false).build()
                            ))
                            .build();
                })
                .customBlockRotationOffset(90)
                .customBlockRawComponent("allay_test:global", NbtMap.EMPTY)
                .build();
        customDefinitionItemType = AllayItemType
                .builder(ItemStackImpl.class)
                .identifier("allay_test:custom_definition_item")
                .customItemDefinition(CustomItemDefinition.builder()
                        .texture("allay_test_custom_item")
                        .displayName("item.allay_test.custom_definition_item.name")
                        .renderOffsets(CustomItemDefinition.RenderOffsets.builder()
                                .mainHand(CustomItemDefinition.RenderOffsets.Hand.builder()
                                        .firstPerson(CustomItemDefinition.RenderOffsets.Offset.builder()
                                                .scale(new Vector3f(0.25f))
                                                .build())
                                        .build())
                                .build())
                        .foil(true)
                        .rawProperties(Map.of("allay_test:property", NbtMap.EMPTY))
                        .rawComponents(Map.of("allay_test:component", NbtMap.EMPTY))
                        .build())
                .build();

        registered = true;
    }

    /**
     * Returns the component fixture's boolean state property without requiring type registration.
     */
    public static BlockPropertyType<Boolean> blockBooleanProperty() {
        return BLOCK_BOOLEAN_PROPERTY;
    }

    /**
     * Returns the component fixture's bounded integer state property without requiring type
     * registration.
     */
    public static BlockPropertyType<Integer> blockIntProperty() {
        return BLOCK_INT_PROPERTY;
    }

    /**
     * Returns the component fixture's enum state property without requiring type registration.
     */
    public static BlockPropertyType<TestEnum> blockEnumProperty() {
        return BLOCK_ENUM_PROPERTY;
    }

    /**
     * Returns the Molang fixture's boolean state property without requiring type registration.
     */
    public static BlockPropertyType<Boolean> molangBooleanProperty() {
        return MOLANG_BOOLEAN_PROPERTY;
    }

    /**
     * Returns the Molang fixture's bounded integer state property without requiring type
     * registration.
     */
    public static BlockPropertyType<Integer> molangIntProperty() {
        return MOLANG_INT_PROPERTY;
    }

    /**
     * Returns the Molang fixture's enum state property without requiring type registration.
     */
    public static BlockPropertyType<TestEnum> molangEnumProperty() {
        return MOLANG_ENUM_PROPERTY;
    }

    /**
     * Returns the shared generated block-item membership tag used by tests without requiring type
     * registration.
     */
    public static ItemTag customizedItemTag() {
        return CUSTOMIZED_ITEM_TAG;
    }

    /**
     * Returns the static block-data fixture after checking registration. The returned type is
     * shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> blockType1() {
        ensureRegistered();
        return blockType1;
    }

    /**
     * Returns the dynamic block-data fixture after checking registration. The returned type is
     * shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> blockType2() {
        ensureRegistered();
        return blockType2;
    }

    /**
     * Returns the customized generated-item block fixture after checking registration. The
     * returned type is shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> customizedItemBlockType() {
        ensureRegistered();
        return customizedItemBlockType;
    }

    /**
     * Returns the three-property Molang block fixture after checking registration. The returned
     * type is shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> molangBlockTypeWithAllProperties() {
        ensureRegistered();
        return molangBlockTypeWithAllProperties;
    }

    /**
     * Returns the boolean-only Molang block fixture after checking registration. The returned type
     * is shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> molangBlockTypeWithBooleanOnly() {
        ensureRegistered();
        return molangBlockTypeWithBooleanOnly;
    }

    /**
     * Returns the property-free Molang block fixture after checking registration. The returned
     * type is shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> molangBlockTypeWithoutProperties() {
        ensureRegistered();
        return molangBlockTypeWithoutProperties;
    }

    /**
     * Returns the custom client-definition block fixture after checking registration. The returned
     * type is shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static BlockType<TestBlock> customDefinitionBlockType() {
        ensureRegistered();
        return customDefinitionBlockType;
    }

    /**
     * Returns the custom client-definition item fixture after checking registration. The returned
     * type is shared
     * across tests rather than copied; callers initialize and publish the fixture registry before
     * access, and an unfinished registration raises {@code IllegalStateException}.
     */
    public static ItemType<?> customDefinitionItemType() {
        ensureRegistered();
        return customDefinitionItemType;
    }

    /**
     * Returns the shared definition-evaluation count after checking registration. The count can
     * increase as other tests request definitions, so callers compare snapshots rather than
     * assuming an isolated counter starting at zero for each test.
     */
    public static int customBlockDefinitionInvocations() {
        ensureRegistered();
        return CUSTOM_BLOCK_DEFINITION_INVOCATIONS.get();
    }

    /**
     * Rejects fixture access before the registration completion flag is set. The check does not
     * acquire the registration monitor or establish publication itself, so callers preserve the
     * test extension's initialization-before-use ordering.
     */
    private static void ensureRegistered() {
        if (!registered) {
            throw new IllegalStateException("Test registry fixtures have not been registered");
        }
    }
}
