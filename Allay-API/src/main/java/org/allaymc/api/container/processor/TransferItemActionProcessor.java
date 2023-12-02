package org.allaymc.api.container.processor;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.List;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_TYPE;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class TransferItemActionProcessor<T extends TransferItemStackRequestAction> implements ContainerActionProcessor<T> {

    @Override
    public ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions) {
        var source = player.getReachableContainerBySlotType(action.getSource().getContainer());
        var destination = player.getReachableContainerBySlotType(action.getDestination().getContainer());
        int sourceSlot = source.fromNetworkSlotIndex(action.getSource().getSlot());
        int sourceStackNetworkId = action.getSource().getStackNetworkId();
        int destinationSlot = destination.fromNetworkSlotIndex(action.getDestination().getSlot());
        int destinationStackNetworkId = action.getDestination().getStackNetworkId();
        int count = action.getCount();
        var sourItem = source.getItemStack(sourceSlot);
        if (sourItem.getItemType() == AIR_TYPE) {
            log.warn("place an air item is not allowed");
            return error();
        }
        if (!validateStackNetworkId(sourItem.getStackNetworkId(), sourceStackNetworkId)) {
            log.warn("mismatch source stack network id!");
            return error();
        }
        if (sourItem.getCount() < count) {
            log.warn("place an item that has not enough count is not allowed");
            return error();
        }
        var destItem = destination.getItemStack(destinationSlot);
        if (destItem.getItemType() != AIR_TYPE && destItem.getItemType() != sourItem.getItemType()) {
            log.warn("place an item to a slot that has a different item is not allowed");
            return error();
        }
        if (!validateStackNetworkId(destItem.getStackNetworkId(), destinationStackNetworkId)) {
            log.warn("mismatch destination stack network id!");
            return error();
        }
        if (destItem.getCount() + count > destItem.getItemAttributes().maxStackSize()) {
            log.warn("destination stack size bigger than the max stack size!");
            return error();
        }
        ItemStack resultSourItem;
        ItemStack resultDestItem;
        if (sourItem.getCount() == count) {
            //第一种：全部拿走
            resultSourItem = Container.EMPTY_SLOT_PLACE_HOLDER;
            source.setItemStack(sourceSlot, resultSourItem);
            if (destItem.getItemType() != AIR_TYPE) {
                resultDestItem = destItem;
                //目标物品不为空，直接添加数量，目标物品网络堆栈id不变
                resultDestItem.setCount(destItem.getCount() + count);
                destination.onSlotChange(destinationSlot);
            } else {
                //目标物品为空，直接移动原有堆栈到新位置，网络堆栈id使用源物品的网络堆栈id（相当于换个位置）
                if (source.getContainerType() == FullContainerType.CREATED_OUTPUT) {
                    //HACK: 若是从CREATED_OUTPUT拿出的，需要服务端自行新建个网络堆栈id
                    sourItem = sourItem.copy(true);
                }
                resultDestItem = sourItem;
                destination.setItemStack(destinationSlot, resultDestItem);
            }
        } else {
            //第二种：拿走一部分
            resultSourItem = sourItem;
            resultSourItem.setCount(resultSourItem.getCount() - count);
            source.onSlotChange(sourceSlot);
            if (destItem.getItemType() != AIR_TYPE) {
                //目标物品不为空
                resultDestItem = destItem;
                resultDestItem.setCount(destItem.getCount() + count);
                destination.onSlotChange(destinationSlot);
            } else {
                //目标物品为空，为分出来的子物品堆栈新建网络堆栈id
                resultDestItem = sourItem.copy(true);
                resultDestItem.setCount(count);
                destination.setItemStack(destinationSlot, resultDestItem);
            }
        }
        var destItemStackResponseSlot =
                new ItemStackResponseContainer(
                        destination.getSlotType(destinationSlot),
                        List.of(
                                new ItemStackResponseSlot(
                                        destination.toNetworkSlotIndex(destinationSlot),
                                        destination.toNetworkSlotIndex(destinationSlot),
                                        resultDestItem.getCount(),
                                        resultDestItem.getStackNetworkId(),
                                        resultDestItem.getCustomName(),
                                        resultDestItem.getDurability()
                                )
                        )
                );
        //CREATED_OUTPUT不需要发响应（mjの奇妙hack）
        if (source.getContainerType() != FullContainerType.CREATED_OUTPUT) {
            return new ActionResponse(
                    true,
                    List.of(new ItemStackResponseContainer(
                            source.getSlotType(sourceSlot),
                            List.of(
                                    new ItemStackResponseSlot(
                                            source.toNetworkSlotIndex(sourceSlot),
                                            source.toNetworkSlotIndex(sourceSlot),
                                            resultSourItem.getCount(),
                                            resultSourItem.getStackNetworkId(),
                                            resultSourItem.getCustomName(),
                                            resultSourItem.getDurability()
                                    )
                            )
                    ), destItemStackResponseSlot)
            );
        } else {
            return new ActionResponse(
                    true,
                    List.of(destItemStackResponseSlot)
            );
        }
    }

}
