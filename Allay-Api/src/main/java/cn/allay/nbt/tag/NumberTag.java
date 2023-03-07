package cn.allay.nbt.tag;

import java.util.Objects;

public abstract class NumberTag<T extends Number> extends Tag {
    public abstract T getData();

    public abstract void setData(T data);

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getData());
    }
}
