package cn.allay.entity.definition;

import cn.allay.entity.Entity;
import cn.allay.entity.component.EntityComponentImpl;
import cn.allay.identifier.Identified;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/18 <br>
 * Allay Project <br>
 * TODO: remove this
 */
public interface EntityDefinition<T extends Entity> extends Identified {
    List<EntityComponentImpl> getComponents();
    Class<T> getTargetInterface();
}
