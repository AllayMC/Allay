package org.allaymc.api.math.position;

import com.google.common.base.Objects;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.joml.Runtime;
import org.joml.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.text.NumberFormat;

/**
 * @author Cool_Loong
 */
public class Position3i extends Vector3i implements Position3ic {
    public Dimension dimension;

    public Position3i(Position3ic p) {
        super(p);
        this.dimension = p.dimension();
    }

    public Position3i(Dimension dimension) {
        super();
        this.dimension = dimension;
    }

    public Position3i(int d, Dimension dimension) {
        super(d);
        this.dimension = dimension;
    }

    public Position3i(int x, int y, int z, Dimension dimension) {
        super(x, y, z);
        this.dimension = dimension;
    }

    public Position3i(Vector3ic v, Dimension dimension) {
        super(v);
        this.dimension = dimension;
    }

    public Position3i(Vector2ic v, int z, Dimension dimension) {
        super(v, z);
        this.dimension = dimension;
    }

    public Position3i(float x, float y, float z, int mode, Dimension dimension) {
        super(x, y, z, mode);
        this.dimension = dimension;
    }

    public Position3i(double x, double y, double z, int mode, Dimension dimension) {
        super(x, y, z, mode);
        this.dimension = dimension;
    }

    public Position3i(Vector2fc v, float z, int mode, Dimension dimension) {
        super(v, z, mode);
        this.dimension = dimension;
    }

    public Position3i(Vector3fc v, int mode, Dimension dimension) {
        super(v, mode);
        this.dimension = dimension;
    }

    public Position3i(Vector2dc v, float z, int mode, Dimension dimension) {
        super(v, z, mode);
        this.dimension = dimension;
    }

    public Position3i(Vector3dc v, int mode, Dimension dimension) {
        super(v, mode);
        this.dimension = dimension;
    }

    public Position3i(int[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = dimension;
    }

    public Position3i(ByteBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = dimension;
    }

    public Position3i(int index, ByteBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = dimension;
    }

    public Position3i(IntBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = dimension;
    }

    public Position3i(int index, IntBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = dimension;
    }

    @Override
    public Dimension dimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public Position3i set(Vector3ic v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3i set(Vector3dc v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3i set(Vector3dc v, int mode) {
        super.set(v, mode);
        return this;
    }

    @Override
    public Position3i set(Vector3fc v, int mode) {
        super.set(v, mode);
        return this;
    }

    @Override
    public Position3i set(Vector2ic v, int z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3i set(int d) {
        super.set(d);
        return this;
    }

    @Override
    public Position3i set(int x, int y, int z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Position3i set(int[] xyz) {
        super.set(xyz);
        return this;
    }

    @Override
    public Position3i set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Position3i set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Position3i set(IntBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Position3i set(int index, IntBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Position3i setFromAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3i setComponent(int component, int value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
    }

    @Override
    public Position3ic getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3i sub(Vector3ic v) {
        super.sub(v);
        return this;
    }

    @Override
    public Position3i sub(Vector3ic v, Vector3i dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Position3i sub(int x, int y, int z) {
        super.sub(x, y, z);
        return this;
    }

    @Override
    public Position3i sub(int x, int y, int z, Vector3i dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    @Override
    public Position3i add(Vector3ic v) {
        super.add(v);
        return this;
    }

    @Override
    public Position3i add(Vector3ic v, Vector3i dest) {
        super.add(v, dest);
        return this;
    }

    @Override
    public Position3i add(int x, int y, int z) {
        super.add(x, y, z);
        return this;
    }

    @Override
    public Position3i add(int x, int y, int z, Vector3i dest) {
        super.add(x, y, z, dest);
        return this;
    }

    @Override
    public Position3i mul(int scalar) {
        super.mul(scalar);
        return this;
    }

    @Override
    public Position3i mul(int scalar, Vector3i dest) {
        super.mul(scalar, dest);
        return this;
    }

    @Override
    public Position3i mul(Vector3ic v) {
        super.mul(v);
        return this;
    }

    @Override
    public Position3i mul(Vector3ic v, Vector3i dest) {
        super.mul(v, dest);
        return this;
    }

    @Override
    public Position3i mul(int x, int y, int z) {
        super.mul(x, y, z);
        return this;
    }

    @Override
    public Position3i mul(int x, int y, int z, Vector3i dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    @Override
    public Position3i div(float scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Position3i div(float scalar, Vector3i dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Position3i div(int scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Position3i div(int scalar, Vector3i dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Position3i zero() {
        super.zero();
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
        out.writeUTF(this.dimension.getWorld().getWorldData().getName());
        out.writeInt(this.dimension.getDimensionInfo().dimensionId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        dimension = Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt());
    }

    @Override
    public Position3i negate() {
        super.negate();
        return this;
    }

    @Override
    public Position3i negate(Vector3i dest) {
        super.negate(dest);
        return this;
    }

    @Override
    public Position3i min(Vector3ic v) {
        super.min(v);
        return this;
    }

    @Override
    public Position3i min(Vector3ic v, Vector3i dest) {
        super.min(v, dest);
        return this;
    }

    @Override
    public Position3i max(Vector3ic v) {
        super.max(v);
        return this;
    }

    @Override
    public Position3i max(Vector3ic v, Vector3i dest) {
        super.max(v, dest);
        return this;
    }

    @Override
    public Position3i absolute() {
        super.absolute();
        return this;
    }

    @Override
    public Position3i absolute(Vector3i dest) {
        super.absolute(dest);
        return this;
    }

    @Override
    public Position3i clone() throws CloneNotSupportedException {
        return (Position3i) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3ic that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension, that.dimension());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), dimension);
    }

    @Override
    public String toString() {
        return toString(Options.NUMBER_FORMAT);
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " dimension=" + this.dimension.getWorld().getWorldData().getName() + this.dimension.getDimensionInfo().dimensionId() + ")";
    }
}
