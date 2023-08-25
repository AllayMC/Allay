package cn.allay.worldconvert.utils;

import cn.allay.api.mapping.JeBlockState;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.ArrayList;
import java.util.Map;

public class MappingUtils {
    public static JeBlockState convertBlockState(org.jglrxavpok.hephaistos.mca.BlockState blockState) {
        String name = blockState.getName();
        Map<String, String> properties = blockState.getProperties();
        StringBuilder ret = new StringBuilder(name).append('[');
        String join = String.join(",", properties.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).toList());
        return new JeBlockState(ret.append(join).append(']').toString());
    }

    public static NBTCompound convertNBT(NbtMap blockState) {
        return NBT.Compound((b) -> {
            for (var entry : blockState.entrySet()) {
                if (entry.getValue() instanceof NbtMap nbtMap) {
                    b.set(entry.getKey(), convertNBT(nbtMap));
                } else if (entry.getValue() instanceof NbtList<?> list) {
                    b.set(entry.getKey(), convertList(list));
                } else if (entry.getValue() instanceof long[] longs) {
                    b.setLongArray(entry.getKey(), longs);
                } else if (entry.getValue() instanceof byte[] bytes) {
                    b.setByteArray(entry.getKey(), bytes);
                } else if (entry.getValue() instanceof int[] ints) {
                    b.setIntArray(entry.getKey(), ints);
                } else if (entry.getValue() instanceof Integer integer) {
                    b.setInt(entry.getKey(), integer);
                } else if (entry.getValue() instanceof Byte by) {
                    b.setByte(entry.getKey(), by);
                } else if (entry.getValue() instanceof String s) {
                    b.setString(entry.getKey(), s);
                } else if (entry.getValue() instanceof Float f) {
                    b.setFloat(entry.getKey(), f);
                } else if (entry.getValue() instanceof Double d) {
                    b.setDouble(entry.getKey(), d);
                } else if (entry.getValue() instanceof Short s) {
                    b.setShort(entry.getKey(), s);
                } else if (entry.getValue() instanceof Long l) {
                    b.setLong(entry.getKey(), l);
                }
            }
        });
    }

    public static NBT convertList(NbtList<?> list) {
        ArrayList<NBT> arrayList = new ArrayList<>();
        for (var v : list) {
            if (v instanceof NbtMap nbtMap) {
                arrayList.add(convertNBT(nbtMap));
            } else if (v instanceof NbtList<?> vl) {
                arrayList.add(convertList(vl));
            } else if (v instanceof long[] longs) {
                arrayList.add(NBT.LongArray(longs));
            } else if (v instanceof byte[] bytes) {
                arrayList.add(NBT.ByteArray(bytes));
            } else if (v instanceof int[] ints) {
                arrayList.add(NBT.IntArray(ints));
            } else if (v instanceof Integer integer) {
                arrayList.add(NBT.Int(integer));
            } else if (v instanceof Byte b) {
                arrayList.add(NBT.Byte(b));
            } else if (v instanceof String s) {
                arrayList.add(NBT.String(s));
            } else if (v instanceof Float f) {
                arrayList.add(NBT.Float(f));
            } else if (v instanceof Double d) {
                arrayList.add(NBT.Double(d));
            } else if (v instanceof Short s) {
                arrayList.add(NBT.Short(s));
            } else if (v instanceof Long l) {
                arrayList.add(NBT.Long(l));
            }
        }
        return NBT.List(arrayList.get(0).getID(), arrayList);
    }
}
