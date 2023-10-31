package cn.allay.api.entity.interfaces.item;

import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.item.ItemStack;
import cn.allay.api.utils.MathUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.primitives.AABBf;

import javax.annotation.Nullable;

import static cn.allay.api.item.ItemHelper.fromNBT;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class EntityItemBaseComponentImpl extends EntityBaseComponentImpl<EntityItem> implements EntityItemBaseComponent {

    public static final int MAX_AGE = 6000;
    protected ItemStack itemStack;
    protected int pickupDelay = 10;
    protected int age;

    public EntityItemBaseComponentImpl(EntityInitInfo<EntityItem> info, @Nullable ItemStack itemStack) {
        super(info, new AABBf(-0.125f, 0.0f, -0.125f, 0.125f, 0.25f, 0.125f));
        this.itemStack = itemStack;
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        metadata.setFlag(EntityFlag.HAS_COLLISION, false);
    }

    @Override
    public void tick() {
        super.tick();
        // update age
        if (age != -1) {
            age++;
            if (age >= MAX_AGE) {
                removeEntity();
            }
        }
        // update pick up delay
        if (pickupDelay > 0) pickupDelay--;
    }

    @Override
    public void onCollideWith(Entity other) {
        // check can merge
        if (itemStack != null && other.getEntityType() == EntityItem.ITEM_TYPE) {
            var otherEntityItem = (EntityItem) other;
            var otherItemStack = otherEntityItem.getItemStack();

            // 物品捡起的时候会执行 EntityPlayerBaseComponentImpl 中的 tryPickUpItems方法，物品数量会被清空，此时两个物品都是count = 0 的状态导致合并后误删掉落物实体。
            // 问题最终导致： 物品在同一tick下捡起会导致吞方块
            if (otherItemStack != null && otherItemStack.canMerge(itemStack) &&
                    itemStack.getCount() > 0 && otherItemStack.getCount() > 0 &&
                    itemStack.getCount() + otherItemStack.getCount() <= itemStack.getItemAttributes().maxStackSize()) {
                itemStack.setCount(itemStack.getCount() + otherItemStack.getCount());
                otherEntityItem.setItemStack(null);
                otherEntityItem.removeEntity();
            }
        }
    }

    @Override
    public NbtMap saveNBT() {
        var nbt = super.saveNBT();
        if (itemStack != null)
            nbt = nbt.toBuilder().putCompound("Item", itemStack.saveNBT()).build();
        return nbt;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        if (nbt.containsKey("Item"))
            itemStack = fromNBT(nbt.getCompound("Item"));
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public int getPickupDelay() {
        return pickupDelay;
    }

    @Override
    public void setPickupDelay(int delay) {
        this.pickupDelay = delay;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        AddItemEntityPacket addItemEntityPacket = new AddItemEntityPacket();
        addItemEntityPacket.setRuntimeEntityId(uniqueId);
        addItemEntityPacket.setUniqueEntityId(uniqueId);
        addItemEntityPacket.setItemInHand(itemStack.toNetworkItemData());
        addItemEntityPacket.setPosition(MathUtils.JOMLVecTocbVec(location));
        addItemEntityPacket.setMotion(MathUtils.JOMLVecTocbVec(motion));
        addItemEntityPacket.getMetadata().putAll(metadata.getEntityDataMap());
        return addItemEntityPacket;
    }
}
