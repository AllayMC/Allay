package cn.allay.entity;

import cn.allay.entity.type.EntityType;
import cn.allay.identifier.Identified;
import cn.allay.math.location.Location;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Entity extends Identified, Location<Double> {
    EntityType<? extends Entity> getEntityType();
}
