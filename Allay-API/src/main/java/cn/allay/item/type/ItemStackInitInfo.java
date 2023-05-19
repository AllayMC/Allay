package cn.allay.item.type;

import cn.allay.component.interfaces.ComponentInitInfo;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemStackInitInfo extends ComponentInitInfo {

    int count();

    int meta();

    NbtMap nbt();

    record Simple(int count, int meta, NbtMap nbt) implements ItemStackInitInfo {
        public Simple(int count, int meta, NbtMap nbt) {
            this.count = count;
            this.meta = meta;
            this.nbt = nbt;
        }

        public Simple(int count) {
            //todo: 空nbt有关的逻辑也许可以优化？
            this(count, 0, NbtMap.builder().build());
        }

        public Simple(int count, int meta) {
            this(count, meta, NbtMap.builder().build());
        }
    }

}
