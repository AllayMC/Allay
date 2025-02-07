package org.allaymc.api.math.position;

import com.google.common.base.Objects;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.joml.Runtime;
import org.joml.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.text.NumberFormat;

/**
 * @author Cool_Loong
 */
public class Position3d extends Vector3d implements Position3dc {
    public WeakReference<Dimension> dimension;

    public Position3d(Position3ic p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    public Position3d(Position3dc p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    public Position3d(Dimension dimension) {
        super();
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(double d, Dimension dimension) {
        super(d);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(double x, double y, double z, Dimension dimension) {
        super(x, y, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(Vector3fc v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(Vector3ic v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(Vector2fc v, double z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(Vector2ic v, double z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(Vector3dc v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(Vector2dc v, double z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(double[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(float[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(ByteBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(int index, ByteBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(DoubleBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3d(int index, DoubleBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    @Override
    public Dimension dimension() {
        return dimension.get();
    }

    public void setDimension(Dimension dimension) {
        this.dimension = new WeakReference<>(dimension);
    }

    @Override
    public Position3dc getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(x);
        out.writeDouble(y);
        out.writeDouble(z);
        out.writeUTF(this.dimension().getWorld().getWorldData().getDisplayName());
        out.writeInt(this.dimension().getDimensionInfo().dimensionId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readDouble();
        y = in.readDouble();
        z = in.readDouble();
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3dc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension.get(), that.dimension());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), dimension.get());
    }

    @Override
    public String toString() {
        return toString(Options.NUMBER_FORMAT);
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " dimension=" + this.dimension().getWorld().getWorldData().getDisplayName() + this.dimension().getDimensionInfo().dimensionId() + ")";
    }
}
