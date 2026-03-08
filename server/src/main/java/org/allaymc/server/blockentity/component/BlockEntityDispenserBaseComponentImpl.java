package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.eventbus.event.block.BlockDispenseEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.block.dispenser.DefaultDispenseBehavior;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockEntityDispenserBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityDispenserBaseComponent {

    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityDispenserBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
    }

    @Override
    public boolean tryDispense() {
        var container = containerHolderComponent.getContainer();
        var slot = selectRandomNonEmptySlot(container);
        if (slot == -1) {
            playFailSound();
            return false;
        }

        var item = container.getItemStack(slot);
        var block = new Block(position.dimension(), position);
        var facing = getFacing(block);

        var behavior = getBehavior(item);
        var event = new BlockDispenseEvent(block, item, facing, behavior);
        if (!event.call()) {
            return false;
        }

        behavior = event.getBehavior();
        var itemToDispense = item.copy();
        itemToDispense.setCount(1);

        var result = behavior.dispense(block, facing, itemToDispense);

        if (result.succeeded()) {
            item.reduceCount(1);
            if (item.getCount() <= 0) {
                container.clearSlot(slot);
            } else {
                container.notifySlotChange(slot);
            }

            if (result.remainingItem() != ItemAirStack.AIR_STACK) {
                handleRemainingItem(container, slot, item, result.remainingItem());
            }
        } else {
            playFailSound();
        }

        return result.succeeded();
    }

    protected DispenseBehavior getBehavior(ItemStack item) {
        var behavior = Registries.DISPENSER_BEHAVIORS.get(item.getItemType());
        return behavior != null ? behavior : DefaultDispenseBehavior.INSTANCE;
    }

    protected int selectRandomNonEmptySlot(Container container) {
        var stacks = container.getItemStackArray();
        var random = ThreadLocalRandom.current();

        int r = 1;
        int selectedSlot = -1;

        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i] != null && stacks[i] != ItemAirStack.AIR_STACK) {
                if (random.nextInt(r++) == 0) {
                    selectedSlot = i;
                }
            }
        }

        return selectedSlot;
    }

    protected BlockFace getFacing(Block block) {
        var facingIndex = block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION);
        return BlockFace.fromIndex(facingIndex);
    }

    protected void handleRemainingItem(Container container, int originalSlot, ItemStack originalItem, ItemStack remainingItem) {
        if (originalItem.getCount() <= 0) {
            container.setItemStack(originalSlot, remainingItem);
        } else {
            var slot = container.tryAddItem(remainingItem);
            if (slot == -1) {
                var dropPos = MathUtils.center(position);
                position.dimension().dropItem(remainingItem, dropPos);
            }
        }
    }

    protected void playFailSound() {
        position.dimension().addSound(MathUtils.center(position), new CustomSound(SoundNames.RANDOM_CLICK, 1.0f, 1.2f));
    }
}
