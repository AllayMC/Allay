package cn.allay.api.entity.interfaces.item;

import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.item.ItemStack;
import cn.allay.api.utils.MathUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import javax.annotation.Nullable;
import java.util.function.Function;

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
        if (age != -1) {
            age++;
            if (age >= MAX_AGE) {
                location.world.removeEntity(manager.getComponentedObject());
            }
        }
        if (pickupDelay > 0) pickupDelay--;
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
