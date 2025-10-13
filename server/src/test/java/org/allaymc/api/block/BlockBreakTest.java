package org.allaymc.api.block;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
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

    private static void testBreakTime(double expectedSeconds, BlockBehavior breakingBlock, ItemStack usedItem, boolean isOnGround, boolean isInWater) {
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
    void testByHand() {
        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testBreakTime(50, cobblestone, usedItem, true, true);
        testBreakTime(250, cobblestone, usedItem, false, true);
        testBreakTime(10, cobblestone, usedItem, true, false);
        testBreakTime(50, cobblestone, usedItem, false, false);
    }

    @Test
    void testByValidTool() {
        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.DIAMOND_PICKAXE.createItemStack();

        testBreakTime(1.9, cobblestone, usedItem, true, true);
        testBreakTime(9.4, cobblestone, usedItem, false, true);
        testBreakTime(0.4, cobblestone, usedItem, true, false);
        testBreakTime(1.9, cobblestone, usedItem, false, false);
    }

    @Test
    void testWithHasteOrConduitPower() {
        when(player.hasEffect(EffectTypes.CONDUIT_POWER)).thenReturn(true);
        when(player.getEffectLevel(EffectTypes.CONDUIT_POWER)).thenReturn(1);

        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testBreakTime(34.75, cobblestone, usedItem, true, true);
        testBreakTime(173.65, cobblestone, usedItem, false, true);
        testBreakTime(6.95, cobblestone, usedItem, true, false);
        testBreakTime(34.75, cobblestone, usedItem, false, false);

        when(player.hasEffect(EffectTypes.CONDUIT_POWER)).thenReturn(false);
    }

    @Test
    void testWithMiningFatigue() {
        when(player.hasEffect(EffectTypes.MINING_FATIGUE)).thenReturn(true);
        when(player.getEffectLevel(EffectTypes.MINING_FATIGUE)).thenReturn(2);

        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testBreakTime(1133.8, cobblestone, usedItem, true, true);
        testBreakTime(5668.95, cobblestone, usedItem, false, true);
        testBreakTime(226.8, cobblestone, usedItem, true, false);
        testBreakTime(1133.8, cobblestone, usedItem, false, false);

        when(player.hasEffect(EffectTypes.MINING_FATIGUE)).thenReturn(false);
    }

    @Test
    void testWithAquaAffinity() {
        var armorContainer = mock(ArmorContainerImpl.class);
        var helmet = ItemTypes.DIAMOND_HELMET.createItemStack();
        helmet.addEnchantment(EnchantmentTypes.AQUA_AFFINITY, 1);

        when(player.hasContainer(ContainerTypes.ARMOR)).thenReturn(true);
        when(player.getContainer(ContainerTypes.ARMOR)).thenReturn(armorContainer);

        when(armorContainer.getHelmet()).thenReturn(helmet);

        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemAirStack.AIR_STACK;

        testBreakTime(10, cobblestone, usedItem, true, true);
        testBreakTime(50, cobblestone, usedItem, false, true);
        testBreakTime(10, cobblestone, usedItem, true, false);
        testBreakTime(50, cobblestone, usedItem, false, false);

        when(player.hasContainer(ContainerTypes.ARMOR)).thenReturn(false);
    }

    @Test
    void testWithEfficiency() {
        var cobblestone = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.DIAMOND_PICKAXE.createItemStack();
        usedItem.addEnchantment(EnchantmentTypes.EFFICIENCY, 5);

        testBreakTime(0.45, cobblestone, usedItem, true, true);
        testBreakTime(2.25, cobblestone, usedItem, false, true);
        testBreakTime(0.1, cobblestone, usedItem, true, false);
        testBreakTime(0.45, cobblestone, usedItem, false, false);
    }

    @Test
    void testBySword() {
        var blockToBreak = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.DIAMOND_SWORD.createItemStack();

        testBreakTime(33.35, blockToBreak, usedItem, true, true);
        testBreakTime(166.7, blockToBreak, usedItem, false, true);
        testBreakTime(6.7, blockToBreak, usedItem, true, false);
        testBreakTime(33.35, blockToBreak, usedItem, false, false);

        blockToBreak = BlockTypes.WEB.getDefaultState().getBehavior();
        testBreakTime(2, blockToBreak, usedItem, true, true);
        testBreakTime(10, blockToBreak, usedItem, false, true);
        testBreakTime(0.4, blockToBreak, usedItem, true, false);
        testBreakTime(2, blockToBreak, usedItem, false, false);

        blockToBreak = BlockTypes.BAMBOO.getDefaultState().getBehavior();
        testBreakTime(0, blockToBreak, usedItem, true, true);
        testBreakTime(0, blockToBreak, usedItem, false, true);
        testBreakTime(0, blockToBreak, usedItem, true, false);
        testBreakTime(0, blockToBreak, usedItem, false, false);
    }

    @Test
    void testByShears() {
        var breakingBlock = BlockTypes.COBBLESTONE.getDefaultState().getBehavior();
        var usedItem = ItemTypes.SHEARS.createItemStack();

        testBreakTime(50, breakingBlock, usedItem, true, true);
        testBreakTime(250, breakingBlock, usedItem, false, true);
        testBreakTime(10, breakingBlock, usedItem, true, false);
        testBreakTime(50, breakingBlock, usedItem, false, false);

        breakingBlock = BlockTypes.BLACK_WOOL.getDefaultState().getBehavior();
        testBreakTime(1.25, breakingBlock, usedItem, true, true);
        testBreakTime(6.05, breakingBlock, usedItem, false, true);
        testBreakTime(0.25, breakingBlock, usedItem, true, false);
        testBreakTime(1.25, breakingBlock, usedItem, false, false);

        breakingBlock = BlockTypes.WEB.getDefaultState().getBehavior();
        testBreakTime(2, breakingBlock, usedItem, true, true);
        testBreakTime(10, breakingBlock, usedItem, false, true);
        testBreakTime(0.4, breakingBlock, usedItem, true, false);
        testBreakTime(2, breakingBlock, usedItem, false, false);

        breakingBlock = BlockTypes.OAK_LEAVES.getDefaultState().getBehavior();
        testBreakTime(0.15, breakingBlock, usedItem, true, true);
        testBreakTime(0.55, breakingBlock, usedItem, false, true);
        testBreakTime(0.05, breakingBlock, usedItem, true, false);
        testBreakTime(0.15, breakingBlock, usedItem, false, false);
    }
}
