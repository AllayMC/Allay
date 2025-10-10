package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkRocketBaseComponent;
import org.allaymc.api.world.FireworkExplosion;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.HashSet;
import java.util.Set;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class ItemFireworkRocketBaseComponentImpl extends ItemBaseComponentImpl implements ItemFireworkRocketBaseComponent {

    protected static final String TAG_FIREWORKS = "Fireworks";
    protected static final String TAG_EXPLOSIONS = "Explosions";
    protected static final String TAG_FLIGHT = "Flight";

    protected int fireworkDuration;
    protected Set<FireworkExplosion> fireworkExplosions;

    public ItemFireworkRocketBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.fireworkExplosions = new HashSet<>();
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_FIREWORKS, this::loadFireworks);
    }

    @Override
    public NbtMap saveExtraTag() {
        return super.saveExtraTag()
                .toBuilder()
                .putCompound(TAG_FIREWORKS, saveFireworks())
                .build();
    }

    protected void loadFireworks(NbtMap nbt) {
        nbt.listenForList(TAG_EXPLOSIONS, NbtType.COMPOUND, entries -> {
            for (var entry : entries) {
                this.fireworkExplosions.add(FireworkExplosion.fromNBT(entry));
            }
        });

        nbt.listenForByte(TAG_FLIGHT, value -> this.fireworkDuration = value);
    }

    protected NbtMap saveFireworks() {
        return NbtMap.builder()
                .putList(TAG_EXPLOSIONS, NbtType.COMPOUND, this.fireworkExplosions.stream().map(FireworkExplosion::saveNBT).toList())
                .putByte(TAG_FLIGHT, (byte) this.fireworkDuration)
                .build();
    }
}
