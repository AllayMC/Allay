package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockSaplingBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.server.block.component.crops.BlockWheatBaseComponentImpl;
import org.allaymc.server.block.component.sapling.BlockMangrovePropaguleBaseComponentImpl;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3dc;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class BlockFertilizableComponentTest {

    private final Map<Vector3i, BlockState> blocks = new HashMap<>();
    private Dimension dimension;

    @BeforeEach
    void setUp() {
        dimension = mock(Dimension.class, CALLS_REAL_METHODS);
        lenient().doReturn(true).when(dimension).isYInRange(anyInt());
        lenient().doAnswer(invocation -> {
            var vec = invocation.getArgument(0, Vector3ic.class);
            return blocks.getOrDefault(
                    new Vector3i(vec.x(), vec.y(), vec.z()),
                    BlockTypes.AIR.getDefaultState()
            );
        }).when(dimension).getBlockState(any(Vector3ic.class));
        lenient().doAnswer(invocation -> {
            var vec = invocation.getArgument(0, Vector3ic.class);
            blocks.put(new Vector3i(vec.x(), vec.y(), vec.z()), invocation.getArgument(1));
            return true;
        }).when(dimension).setBlockState(any(Vector3ic.class), any(BlockState.class));
        lenient().doNothing().when(dimension).addParticle(any(Vector3dc.class), any(Particle.class));
    }

    @Test
    void wheatFertilizesViaInterface() {
        var component = new BlockWheatBaseComponentImpl(BlockTypes.WHEAT);
        var pos = new Vector3i(0, 64, 0);
        var state = BlockTypes.WHEAT.getDefaultState().setPropertyValue(BlockPropertyTypes.GROWTH, 0);
        blocks.put(pos, state);

        assertTrue(component.onBoneMealUsed(dimension, pos, state));
        assertTrue(blocks.get(pos).getPropertyValue(BlockPropertyTypes.GROWTH) > 0);
    }

    @Test
    void fullyGrownCropReturnsFalse() {
        var component = new BlockWheatBaseComponentImpl(BlockTypes.WHEAT);
        var pos = new Vector3i(0, 64, 0);
        var state = BlockTypes.WHEAT.getDefaultState().setPropertyValue(BlockPropertyTypes.GROWTH, 7);
        blocks.put(pos, state);

        assertFalse(component.onBoneMealUsed(dimension, pos, state));
        assertEquals(state, blocks.get(pos));
    }

    @Test
    void hangingMangrovePropaguleAdvancesStage() {
        var component = new BlockMangrovePropaguleBaseComponentImpl(BlockTypes.MANGROVE_PROPAGULE);
        var pos = new Vector3i(0, 64, 0);
        var state = BlockTypes.MANGROVE_PROPAGULE.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.HANGING, true)
                .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, 0);
        blocks.put(pos, state);

        assertTrue(component.onBoneMealUsed(dimension, pos, state));
        assertEquals(1, blocks.get(pos).getPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE));
    }

    @Test
    void nonFertilizableBlockIsNotFertilizableComponent() {
        assertFalse(BlockTypes.CACTUS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
    }

    @Test
    void fertilizableBlockBehaviorsAreFertilizableComponents() {
        assertTrue(BlockTypes.WHEAT.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.POTATOES.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.CARROTS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.BEETROOT.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.MELON_STEM.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.PUMPKIN_STEM.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.TORCHFLOWER_CROP.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.PITCHER_CROP.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.OAK_SAPLING.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.BAMBOO_SAPLING.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.BAMBOO.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.CAVE_VINES.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.CAVE_VINES_BODY_WITH_BERRIES.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.KELP.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.TWISTING_VINES.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.WEEPING_VINES.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.SEAGRASS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.GRASS_BLOCK.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.BIG_DRIPLEAF.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.SMALL_DRIPLEAF_BLOCK.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.AZALEA.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.FLOWERING_AZALEA.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.COCOA.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.CRIMSON_FUNGUS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.WARPED_FUNGUS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.REEDS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.PINK_PETALS.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.BROWN_MUSHROOM.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.RED_MUSHROOM.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.SEA_PICKLE.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.SWEET_BERRY_BUSH.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.MOSS_BLOCK.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.PALE_MOSS_BLOCK.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
        assertTrue(BlockTypes.MANGROVE_PROPAGULE.getDefaultState().getBehavior() instanceof BlockFertilizableComponent);
    }

    @Test
    void mangroveAndAzaleaBehaviorsAreSaplingMarked() {
        assertTrue(BlockTypes.MANGROVE_PROPAGULE.getDefaultState().getBehavior() instanceof BlockSaplingBehavior);
        assertTrue(BlockTypes.AZALEA.getDefaultState().getBehavior() instanceof BlockSaplingBehavior);
    }

    @Test
    void onInteractBoneMealStillWorks() throws Exception {
        var component = new BlockWheatBaseComponentImpl(BlockTypes.WHEAT);
        var pos = new Vector3i(0, 64, 0);
        var state = BlockTypes.WHEAT.getDefaultState().setPropertyValue(BlockPropertyTypes.GROWTH, 0);
        blocks.put(pos, state);

        var manager = mock(ComponentManager.class);
        when(manager.callEvent(any())).thenAnswer(invocation -> invocation.getArgument(0));
        var managerField = BlockBaseComponentImpl.class.getDeclaredField("manager");
        managerField.setAccessible(true);
        managerField.set(component, manager);

        var player = mock(EntityPlayer.class);
        when(player.getDimension()).thenReturn(dimension);
        var interactInfo = new PlayerInteractInfo(player, pos, new Vector3f(0.5f, 0.5f, 0.5f), BlockFace.UP);

        assertTrue(component.onInteract(ItemTypes.BONE_MEAL.createItemStack(1), dimension, interactInfo));
        verify(player).tryConsumeItemInHand();
        assertTrue(blocks.get(pos).getPropertyValue(BlockPropertyTypes.GROWTH) > 0);
    }
}