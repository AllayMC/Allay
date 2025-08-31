package org.allaymc.api.math.position;

import com.google.common.base.Objects;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.joml.*;
import org.joml.Runtime;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.NumberFormat;

/**
 * @author Cool_Loong
 */
public class Position3f extends Vector3f implements Position3fc {
    public WeakReference<Dimension> dimension;

    public Position3f(Position3ic p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    public Position3f(Position3fc p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    public Position3f(Dimension dimension) {
        super();
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(float d, Dimension dimension) {
        super(d);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(float x, float y, float z, Dimension dimension) {
        super(x, y, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(Vector3fc v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(Vector3ic v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(Vector2fc v, float z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(Vector2ic v, float z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(float[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(ByteBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(int index, ByteBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(FloatBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3f(int index, FloatBuffer buffer, Dimension dimension) {
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
    public Position3fc getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
        out.writeUTF(this.dimension().getWorld().getWorldData().getDisplayName());
        out.writeInt(this.dimension().getDimensionInfo().dimensionId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3fc that)) return false;
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
