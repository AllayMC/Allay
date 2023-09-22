package cn.allay.api.entity.event;

import cn.allay.api.component.interfaces.ComponentEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class EntityLoadNBTEvent implements ComponentEvent {
    private NbtMap nbt;
}
