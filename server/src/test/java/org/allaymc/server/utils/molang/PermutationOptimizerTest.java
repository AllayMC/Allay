package org.allaymc.server.utils.molang;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.TestComponentImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.BlockStateDefinition;
import org.allaymc.server.block.type.BlockStateDefinition.Geometry;
import org.allaymc.server.block.type.BlockStateDefinition.Materials;
import org.allaymc.server.block.type.BlockStateDefinition.Transformation;
import org.allaymc.server.block.type.TestBlock;
import org.allaymc.server.block.type.TestBlockImpl;
import org.allaymc.server.block.type.TestEnum;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class PermutationOptimizerTest {

    static BlockPropertyType<Boolean> BOOL_PROP;
    static BlockPropertyType<Integer> INT_PROP;
    static BlockPropertyType<TestEnum> ENUM_PROP;
    static BlockType<TestBlock> blockTypeWithAllProps;
    static BlockType<TestBlock> blockTypeNoProps;

    @BeforeAll
    static void init() {
        BOOL_PROP = BooleanPropertyType.of("perm_test_bool", false);
        INT_PROP = IntPropertyType.of("perm_test_int", 0, 2, 0);
        ENUM_PROP = EnumPropertyType.of("perm_test_enum", TestEnum.class, TestEnum.A);

        blockTypeWithAllProps = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("minecraft:perm_test_block")
                .setProperties(BOOL_PROP, INT_PROP, ENUM_PROP)
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();

        blockTypeNoProps = AllayBlockType
                .builder(TestBlockImpl.class)
                .identifier("minecraft:perm_test_no_props")
                .setComponents(List.of(
                        new TestComponentImpl(),
                        BlockStateDataComponentImpl.ofDefault()))
                .build();
    }

    @Test
    void testCollectDefinitionsIteratesAllStates() {
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> BlockStateDefinition.builder()
                        .geometry(Geometry.of("geometry.test"))
                        .build()
        );

        // BlockType has: 2 (bool) * 3 (int: 0,1,2) * 3 (enum: A,B,C) = 18 states
        assertEquals(18, definitions.size());
    }

    @Test
    void testCollectDefinitionsThrowsOnNull() {
        assertThrows(NullPointerException.class, () -> {
            PermutationOptimizer.collectDefinitions(
                    blockTypeWithAllProps,
                    state -> null
            );
        });
    }

    @Test
    void testOptimizeSingleStateNoPermutations() {
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeNoProps,
                state -> BlockStateDefinition.builder()
                        .geometry(Geometry.of("geometry.simple"))
                        .materials(Materials.builder().any("texture").build())
                        .build()
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeNoProps);

        // Single state = no permutations needed, everything is global
        assertTrue(optimized.permutations().isEmpty());
        assertEquals("geometry.simple", optimized.globalComponents().geometry().identifier());
        assertNotNull(optimized.globalComponents().materials());
    }

    @Test
    void testOptimizeExtractsGlobalComponents() {
        // All states have the same geometry but different materials based on bool prop
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> {
                    boolean boolVal = state.getPropertyValue(BOOL_PROP);
                    return BlockStateDefinition.builder()
                            .geometry(Geometry.of("geometry.shared"))  // Same for all
                            .materials(Materials.builder()
                                    .any(boolVal ? "texture_on" : "texture_off")
                                    .build())
                            .build();
                }
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        // Geometry should be extracted to global components
        assertEquals("geometry.shared", optimized.globalComponents().geometry().identifier());
        // Materials vary, so should be in permutations
        assertFalse(optimized.permutations().isEmpty());
    }

    @Test
    void testOptimizeMergesIdenticalDefinitions() {
        // Create a scenario where multiple states have identical definitions
        // Only bool prop affects the output
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> {
                    boolean boolVal = state.getPropertyValue(BOOL_PROP);
                    return BlockStateDefinition.builder()
                            .geometry(Geometry.of(boolVal ? "geometry.on" : "geometry.off"))
                            .build();
                }
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        // Since only 2 distinct geometries exist (on/off),
        // and geometry differs across states, there should be permutations
        // The number of permutations should be small due to merging
        assertTrue(optimized.permutations().size() <= 2);
    }

    @Test
    void testOptimizeAllSameDefinition() {
        // All states return exactly the same definition
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> BlockStateDefinition.builder()
                        .geometry(Geometry.of("geometry.same"))
                        .materials(Materials.builder().any("same_texture").build())
                        .transformation(Transformation.builder().ry(90).build())
                        .build()
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        // All components should be global, no permutations needed
        assertTrue(optimized.permutations().isEmpty());
        assertEquals("geometry.same", optimized.globalComponents().geometry().identifier());
        assertNotNull(optimized.globalComponents().materials());
        assertNotNull(optimized.globalComponents().transformation());
    }

    @Test
    void testOptimizeWithDisplayName() {
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> {
                    int intVal = state.getPropertyValue(INT_PROP);
                    return BlockStateDefinition.builder()
                            .displayName("Block Stage " + intVal)
                            .build();
                }
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        // Display name varies by int prop (3 values), so permutations needed
        // But should be merged for states with same int value
        assertFalse(optimized.permutations().isEmpty());
    }

    @Test
    void testOptimizeEmptyDefinitions() {
        // Test with an empty function result (but non-null default)
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeNoProps,
                state -> BlockStateDefinition.DEFAULT
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeNoProps);

        // Default definition has no properties, so no permutations
        assertTrue(optimized.permutations().isEmpty());
        assertNull(optimized.globalComponents().geometry());
    }

    @Test
    void testOptimizeWithTransformationBasedOnEnum() {
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> {
                    TestEnum enumVal = state.getPropertyValue(ENUM_PROP);
                    int rotation = switch (enumVal) {
                        case A -> 0;
                        case B -> 90;
                        case C -> 180;
                    };
                    return BlockStateDefinition.builder()
                            .geometry(Geometry.of("geometry.rotatable"))
                            .transformation(Transformation.builder()
                                    .ry(rotation)
                                    .build())
                            .build();
                }
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        // Geometry is shared (global), transformation varies (permutations)
        assertEquals("geometry.rotatable", optimized.globalComponents().geometry().identifier());
        // Should have permutations for different rotations
        assertFalse(optimized.permutations().isEmpty());
    }

    @Test
    void testMergedPermutationConditionFormat() {
        // Test that merged permutations have proper Molang conditions
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> {
                    boolean boolVal = state.getPropertyValue(BOOL_PROP);
                    return BlockStateDefinition.builder()
                            .geometry(Geometry.of(boolVal ? "geometry.on" : "geometry.off"))
                            .build();
                }
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        for (var perm : optimized.permutations()) {
            assertNotNull(perm.condition());
            assertFalse(perm.condition().isEmpty());
            // Conditions should reference block_state query
            assertTrue(perm.condition().contains("q.block_state"));
        }
    }

    @Test
    void testBlockStateDefinitionEquivalence() {
        var def1 = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.test"))
                .materials(Materials.builder().any("texture").build())
                .build();

        var def2 = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.test"))
                .materials(Materials.builder().any("texture").build())
                .build();

        var def3 = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.different"))
                .build();

        assertEquals(def1, def2);
        assertNotEquals(def1, def3);
        assertNotEquals(def1, null);
        assertEquals(def1, def1);
    }

    @Test
    void testBlockStateDefinitionDiff() {
        var base = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.base"))
                .displayName("Base")
                .build();

        var same = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.base"))
                .displayName("Base")
                .build();

        var different = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.different"))
                .displayName("Base")
                .build();

        // Same should return null diff
        assertNull(base.diff(same));

        // different.diff(base) returns the properties in 'different' that differ from 'base'
        var diff = different.diff(base);
        assertNotNull(diff);
        assertEquals("geometry.different", diff.geometry().identifier());
        assertNull(diff.displayName()); // Not different, so not in diff
    }

    @Test
    void testBlockStateDefinitionDiffWithNullBase() {
        var def = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.test"))
                .build();

        // diff with null should return null
        assertNull(def.diff(null));
    }

    @Test
    void testBlockStateDefinitionDiffMaterialsOnly() {
        var base = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.same"))
                .materials(Materials.builder().any("texture_a").build())
                .build();

        var different = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.same"))
                .materials(Materials.builder().any("texture_b").build())
                .build();

        var diff = different.diff(base);
        assertNotNull(diff);
        assertNull(diff.geometry()); // Same, not in diff
        assertNotNull(diff.materials()); // Different, in diff
    }

    @Test
    void testBlockStateDefinitionDiffTransformationOnly() {
        var base = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.same"))
                .transformation(Transformation.builder().ry(0).build())
                .build();

        var different = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.same"))
                .transformation(Transformation.builder().ry(90).build())
                .build();

        var diff = different.diff(base);
        assertNotNull(diff);
        assertNull(diff.geometry()); // Same, not in diff
        assertNotNull(diff.transformation()); // Different, in diff
    }

    @Test
    void testHasAnyPropertyWithEmptyMaterials() {
        var def = BlockStateDefinition.builder()
                .materials(Materials.builder().build()) // Empty materials
                .build();

        // Empty materials should not count as having property
        assertFalse(def.hasAnyProperty());
    }

    @Test
    void testHasAnyPropertyWithGeometryOnly() {
        var def = BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.test"))
                .build();

        assertTrue(def.hasAnyProperty());
    }

    @Test
    void testHasAnyPropertyWithTransformationOnly() {
        var def = BlockStateDefinition.builder()
                .transformation(Transformation.builder().ry(90).build())
                .build();

        assertTrue(def.hasAnyProperty());
    }

    @Test
    void testHasAnyPropertyWithDisplayNameOnly() {
        var def = BlockStateDefinition.builder()
                .displayName("Test")
                .build();

        assertTrue(def.hasAnyProperty());
    }

    @Test
    void testMaterialsEquals() {
        var mat1 = Materials.builder().any("texture").build();
        var mat2 = Materials.builder().any("texture").build();
        var mat3 = Materials.builder().any("different").build();

        assertEquals(mat1, mat2);
        assertNotEquals(mat1, mat3);
        assertEquals(mat1, mat1); // Same reference
        assertNotEquals(mat1, null);
        assertNotEquals(mat1, "not a Materials");
    }

    @Test
    void testMaterialsIsEmpty() {
        var empty = Materials.builder().build();
        var notEmpty = Materials.builder().any("texture").build();

        assertTrue(empty.isEmpty());
        assertFalse(notEmpty.isEmpty());
    }

    @Test
    void testOptimizeWithAllPropertiesVarying() {
        // All 3 properties affect the geometry
        var definitions = PermutationOptimizer.collectDefinitions(
                blockTypeWithAllProps,
                state -> {
                    boolean boolVal = state.getPropertyValue(BOOL_PROP);
                    int intVal = state.getPropertyValue(INT_PROP);
                    TestEnum enumVal = state.getPropertyValue(ENUM_PROP);
                    return BlockStateDefinition.builder()
                            .geometry(Geometry.of("geometry." + boolVal + "_" + intVal + "_" + enumVal))
                            .build();
                }
        );

        var optimized = PermutationOptimizer.optimize(definitions, blockTypeWithAllProps);

        // No global geometry since all are different
        assertNull(optimized.globalComponents().geometry());
        // Should have permutations for each unique combination
        assertFalse(optimized.permutations().isEmpty());
    }

    // ========== Geometry Bone Visibility Tests ==========

    @Test
    void testGeometryBoneVisibilityStatic() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .boneVisibility("bone1", true)
                .boneVisibility("bone2", false)
                .build();

        assertEquals("geometry.test", geometry.identifier());
        assertNotNull(geometry.boneVisibility());
        assertEquals(2, geometry.boneVisibility().size());

        var nbt = geometry.toNBT();
        assertEquals("geometry.test", nbt.getString("identifier"));
        var boneVis = nbt.getCompound("bone_visibility");
        assertEquals("true", boneVis.getString("bone1"));
        assertEquals("false", boneVis.getString("bone2"));
    }

    @Test
    void testGeometryBoneVisibilityWithBooleanProperty() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .boneVisibility("indicator", BOOL_PROP)  // visible when true
                .build();

        var nbt = geometry.toNBT();
        var boneVis = nbt.getCompound("bone_visibility");
        assertEquals("q.block_state('perm_test_bool')", boneVis.getString("indicator"));
    }

    @Test
    void testGeometryBoneVisibilityWithBooleanPropertyAndValue() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .boneVisibility("handle", BOOL_PROP, true)
                .boneVisibility("lock", BOOL_PROP, false)
                .build();

        var nbt = geometry.toNBT();
        var boneVis = nbt.getCompound("bone_visibility");
        assertEquals("q.block_state('perm_test_bool')", boneVis.getString("handle"));
        assertEquals("!q.block_state('perm_test_bool')", boneVis.getString("lock"));
    }

    @Test
    void testGeometryBoneVisibilityWithIntProperty() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .boneVisibility("stage_0", INT_PROP, 0)
                .boneVisibility("stage_1", INT_PROP, 1)
                .boneVisibility("stage_2", INT_PROP, 2)
                .build();

        var nbt = geometry.toNBT();
        var boneVis = nbt.getCompound("bone_visibility");
        assertEquals("q.block_state('perm_test_int') == 0", boneVis.getString("stage_0"));
        assertEquals("q.block_state('perm_test_int') == 1", boneVis.getString("stage_1"));
        assertEquals("q.block_state('perm_test_int') == 2", boneVis.getString("stage_2"));
    }

    @Test
    void testGeometryBoneVisibilityWithEnumProperty() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .boneVisibility("mode_a", ENUM_PROP, TestEnum.A)
                .boneVisibility("mode_b", ENUM_PROP, TestEnum.B)
                .build();

        var nbt = geometry.toNBT();
        var boneVis = nbt.getCompound("bone_visibility");
        assertEquals("q.block_state('perm_test_enum') == 'a'", boneVis.getString("mode_a"));
        assertEquals("q.block_state('perm_test_enum') == 'b'", boneVis.getString("mode_b"));
    }

    @Test
    void testGeometryBoneVisibilityMolang() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .boneVisibilityMolang("complex", "q.block_state('a') == 1 && q.block_state('b') == 2")
                .build();

        var nbt = geometry.toNBT();
        var boneVis = nbt.getCompound("bone_visibility");
        assertEquals("q.block_state('a') == 1 && q.block_state('b') == 2", boneVis.getString("complex"));
    }

    @Test
    void testGeometrySimpleForm() {
        var geometry = Geometry.of("geometry.simple");

        assertEquals("geometry.simple", geometry.identifier());
        assertNull(geometry.boneVisibility());
        assertNull(geometry.culling());
        assertFalse(geometry.uvLockAll());
    }

    @Test
    void testGeometryWithCulling() {
        var geometry = Geometry.builder()
                .identifier("geometry.test")
                .culling("myplugin:culling.leaves")
                .cullingLayer("minecraft:culling_layer.leaves")
                .build();

        var nbt = geometry.toNBT();
        assertEquals("myplugin:culling.leaves", nbt.getString("culling"));
        assertEquals("minecraft:culling_layer.leaves", nbt.getString("culling_layer"));
    }

    @Test
    void testGeometryWithUvLock() {
        var geometryAll = Geometry.builder()
                .identifier("geometry.test")
                .uvLockAll()
                .build();

        var nbtAll = geometryAll.toNBT();
        assertTrue(nbtAll.getBoolean("uv_lock"));

        var geometrySpecific = Geometry.builder()
                .identifier("geometry.test")
                .uvLock("top", "bottom")
                .build();

        var nbtSpecific = geometrySpecific.toNBT();
        var uvLockList = nbtSpecific.getList("uv_lock", org.cloudburstmc.nbt.NbtType.STRING);
        assertEquals(2, uvLockList.size());
        assertTrue(uvLockList.contains("top"));
        assertTrue(uvLockList.contains("bottom"));
    }
}
