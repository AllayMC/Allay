package org.allaymc.api.block;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.container.impl.ArmorContainerImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author IWareQ
 */
@ExtendWith(AllayTestExtension.class)
public class BlockBreakTest {
    private static final EntityPlayer player = mock(EntityPlayer.class);

    private static void testCalculatingBreakTime(double expectedSeconds, BlockBehavior breakingBlock, ItemStack usedItem, boolean isOnGround, boolean isInWater) {
        when(player.isOnGround()).thenReturn(isOnGround);
        when(player.isEyesInWater()).thenReturn(isInWater);
        assertEquals(expectedSeconds, breakingBlock.calculateBreakTime(
                breakingBlock.getBlockType().getDefaultState(),
                usedItem,
                player
        ));
    }

    /**
     * This tests based on <a href="https://minecraft.wiki/w/Breaking#Calculator">this calculator</a>.
     */
    @Test
    void testCalculatingBreakTime_by_hand() {
        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testCalculatingBreakTime(50, cobblestone, usedItem, true, true);
        testCalculatingBreakTime(250, cobblestone, usedItem, false, true);
        testCalculatingBreakTime(10, cobblestone, usedItem, true, false);
        testCalculatingBreakTime(50, cobblestone, usedItem, false, false);
    }

    @Test
    void testCalculatingBreakTime_by_valid_tool() {
        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.DIAMOND_PICKAXE.createItemStack();

        testCalculatingBreakTime(1.9, cobblestone, usedItem, true, true);
        testCalculatingBreakTime(9.4, cobblestone, usedItem, false, true);
        testCalculatingBreakTime(0.4, cobblestone, usedItem, true, false);
        testCalculatingBreakTime(1.9, cobblestone, usedItem, false, false);
    }

    @Test
    void testCalculatingBreakTime_with_haste_or_conduit_power() {
        when(player.hasEffect(EffectTypes.CONDUIT_POWER)).thenReturn(true);
        when(player.getEffectLevel(EffectTypes.CONDUIT_POWER)).thenReturn(1);

        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testCalculatingBreakTime(34.75, cobblestone, usedItem, true, true);
        testCalculatingBreakTime(173.65, cobblestone, usedItem, false, true);
        testCalculatingBreakTime(6.95, cobblestone, usedItem, true, false);
        testCalculatingBreakTime(34.75, cobblestone, usedItem, false, false);

        when(player.hasEffect(EffectTypes.CONDUIT_POWER)).thenReturn(false);
    }

    @Test
    void testCalculatingBreakTime_with_mining_fatigue() {
        when(player.hasEffect(EffectTypes.MINING_FATIGUE)).thenReturn(true);
        when(player.getEffectLevel(EffectTypes.MINING_FATIGUE)).thenReturn(2);

        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testCalculatingBreakTime(1133.8, cobblestone, usedItem, true, true);
        testCalculatingBreakTime(5668.95, cobblestone, usedItem, false, true);
        testCalculatingBreakTime(226.8, cobblestone, usedItem, true, false);
        testCalculatingBreakTime(1133.8, cobblestone, usedItem, false, false);

        when(player.hasEffect(EffectTypes.MINING_FATIGUE)).thenReturn(false);
    }

    @Test
    void testCalculatingBreakTime_with_aqua_affinity() {
        var armorContainer = mock(ArmorContainerImpl.class);
        var helmet = ItemTypes.DIAMOND_HELMET.createItemStack();
        helmet.addEnchantment(EnchantmentTypes.AQUA_AFFINITY, 1);

        when(player.hasContainer(ContainerType.ARMOR)).thenReturn(true);
        when(player.getContainer(ContainerType.ARMOR)).thenReturn(armorContainer);

        when(armorContainer.getHelmet()).thenReturn(helmet);

        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testCalculatingBreakTime(10, cobblestone, usedItem, true, true);
        testCalculatingBreakTime(50, cobblestone, usedItem, false, true);
        testCalculatingBreakTime(10, cobblestone, usedItem, true, false);
        testCalculatingBreakTime(50, cobblestone, usedItem, false, false);

        when(player.hasContainer(ContainerType.ARMOR)).thenReturn(false);
    }

    @Test
    void testCalculatingBreakTime_with_efficiency() {
        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.DIAMOND_PICKAXE.createItemStack();
        usedItem.addEnchantment(EnchantmentTypes.EFFICIENCY, 5);

        testCalculatingBreakTime(0.45, cobblestone, usedItem, true, true);
        testCalculatingBreakTime(2.25, cobblestone, usedItem, false, true);
        testCalculatingBreakTime(0.1, cobblestone, usedItem, true, false);
        testCalculatingBreakTime(0.45, cobblestone, usedItem, false, false);
    }

    @Test
    void testCalculatingBreakTime_by_sword() {
        var blockToBreak = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.DIAMOND_SWORD.createItemStack();

        testCalculatingBreakTime(33.35, blockToBreak, usedItem, true, true);
        testCalculatingBreakTime(166.7, blockToBreak, usedItem, false, true);
        testCalculatingBreakTime(6.7, blockToBreak, usedItem, true, false);
        testCalculatingBreakTime(33.35, blockToBreak, usedItem, false, false);

        blockToBreak = BlockTypes.WEB.getDefaultState().getBehavior();
        testCalculatingBreakTime(2, blockToBreak, usedItem, true, true);
        testCalculatingBreakTime(10, blockToBreak, usedItem, false, true);
        testCalculatingBreakTime(0.4, blockToBreak, usedItem, true, false);
        testCalculatingBreakTime(2, blockToBreak, usedItem, false, false);

        blockToBreak = BlockTypes.BAMBOO.getDefaultState().getBehavior();
        testCalculatingBreakTime(0, blockToBreak, usedItem, true, true);
        testCalculatingBreakTime(0, blockToBreak, usedItem, false, true);
        testCalculatingBreakTime(0, blockToBreak, usedItem, true, false);
        testCalculatingBreakTime(0, blockToBreak, usedItem, false, false);
    }

    @Test
    void testCalculatingBreakTime_by_shears() {
        var breakingBlock = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.SHEARS.createItemStack();

        testCalculatingBreakTime(50, breakingBlock, usedItem, true, true);
        testCalculatingBreakTime(250, breakingBlock, usedItem, false, true);
        testCalculatingBreakTime(10, breakingBlock, usedItem, true, false);
        testCalculatingBreakTime(50, breakingBlock, usedItem, false, false);

        breakingBlock = BlockTypes.BLACK_WOOL.getDefaultState().getBehavior();
        testCalculatingBreakTime(1.25, breakingBlock, usedItem, true, true);
        testCalculatingBreakTime(6.05, breakingBlock, usedItem, false, true);
        testCalculatingBreakTime(0.25, breakingBlock, usedItem, true, false);
        testCalculatingBreakTime(1.25, breakingBlock, usedItem, false, false);

        breakingBlock = BlockTypes.WEB.getDefaultState().getBehavior();
        testCalculatingBreakTime(2, breakingBlock, usedItem, true, true);
        testCalculatingBreakTime(10, breakingBlock, usedItem, false, true);
        testCalculatingBreakTime(0.4, breakingBlock, usedItem, true, false);
        testCalculatingBreakTime(2, breakingBlock, usedItem, false, false);

        breakingBlock = BlockTypes.OAK_LEAVES.getDefaultState().getBehavior();
        testCalculatingBreakTime(0.15, breakingBlock, usedItem, true, true);
        testCalculatingBreakTime(0.55, breakingBlock, usedItem, false, true);
        testCalculatingBreakTime(0.05, breakingBlock, usedItem, true, false);
        testCalculatingBreakTime(0.15, breakingBlock, usedItem, false, false);
    }
}
