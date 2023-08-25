package cn.allay.api.math.position;

import cn.allay.api.server.Server;
import cn.allay.api.world.World;
import com.google.common.base.Objects;
import org.joml.Runtime;
import org.joml.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.NumberFormat;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public class Position3f extends Vector3f implements Position3fc {
    public World world;

    public Position3f(World world) {
        super();
        this.world = world;
    }

    public Position3f(float d, World world) {
        super(d);
        this.world = world;
    }

    public Position3f(float x, float y, float z, World world) {
        super(x, y, z);
        this.world = world;
    }

    public Position3f(Vector3fc v, World world) {
        super(v);
        this.world = world;
    }

    public Position3f(Vector3ic v, World world) {
        super(v);
        this.world = world;
    }

    public Position3f(Vector2fc v, float z, World world) {
        super(v, z);
        this.world = world;
    }

    public Position3f(Vector2ic v, float z, World world) {
        super(v, z);
        this.world = world;
    }

    public Position3f(float[] xyz, World world) {
        super(xyz);
        this.world = world;
    }

    public Position3f(ByteBuffer buffer, World world) {
        super(buffer);
        this.world = world;
    }

    public Position3f(int index, ByteBuffer buffer, World world) {
        super(index, buffer);
        this.world = world;
    }

    public Position3f(FloatBuffer buffer, World world) {
        super(buffer);
        this.world = world;
    }

    public Position3f(int index, FloatBuffer buffer, World world) {
        super(index, buffer);
        this.world = world;
    }

    @Override
    public World world() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public Position3f set(Vector3ic v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3f set(Vector3fc v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3f set(Vector3dc v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3f set(Vector2fc v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3f set(Vector2dc v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3f set(Vector2ic v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3f set(float d) {
        super.set(d);
        return this;
    }

    @Override
    public Position3f set(float x, float y, float z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Position3f set(double d) {
        super.set(d);
        return this;
    }

    @Override
    public Position3f set(double x, double y, double z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Position3f set(float[] xyz) {
        super.set(xyz);
        return this;
    }

    @Override
    public Position3f set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Position3f set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Position3f set(FloatBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Position3f set(int index, FloatBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Position3f setFromAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3f setComponent(int component, float value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
    }

    @Override
    public Position3fc getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3f sub(Vector3fc v) {
        super.sub(v);
        return this;
    }

    @Override
    public Position3f sub(Vector3fc v, Vector3f dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Position3f sub(float x, float y, float z) {
        super.sub(x, y, z);
        return this;
    }

    @Override
    public Position3f sub(float x, float y, float z, Vector3f dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f add(Vector3fc v) {
        super.add(v);
        return this;
    }

    @Override
    public Position3f add(Vector3fc v, Vector3f dest) {
        super.add(v, dest);
        return this;
    }

    @Override
    public Position3f add(float x, float y, float z) {
        super.add(x, y, z);
        return this;
    }

    @Override
    public Position3f add(float x, float y, float z, Vector3f dest) {
        super.add(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f fma(Vector3fc a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Position3f fma(float a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Position3f fma(Vector3fc a, Vector3fc b, Vector3f dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3f fma(float a, Vector3fc b, Vector3f dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3f mulAdd(Vector3fc a, Vector3fc b) {
        super.mulAdd(a, b);
        return this;
    }

    @Override
    public Position3f mulAdd(float a, Vector3fc b) {
        super.mulAdd(a, b);
        return this;
    }

    @Override
    public Position3f mulAdd(Vector3fc a, Vector3fc b, Vector3f dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Position3f mulAdd(float a, Vector3fc b, Vector3f dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Position3f mul(float scalar) {
        super.mul(scalar);
        return this;
    }

    @Override
    public Position3f mul(float scalar, Vector3f dest) {
        super.mul(scalar, dest);
        return this;
    }

    @Override
    public Position3f mul(Vector3fc v) {
        super.mul(v);
        return this;
    }

    @Override
    public Position3f mul(Vector3fc v, Vector3f dest) {
        super.mul(v, dest);
        return this;
    }

    @Override
    public Position3f div(Vector3fc v) {
        super.div(v);
        return this;
    }

    @Override
    public Position3f div(Vector3fc v, Vector3f dest) {
        super.div(v, dest);
        return this;
    }

    @Override
    public Position3f mulProject(Matrix4fc mat, Vector3f dest) {
        super.mulProject(mat, dest);
        return this;
    }

    @Override
    public Position3f mulProject(Matrix4fc mat, float w, Vector3f dest) {
        super.mulProject(mat, w, dest);
        return this;
    }

    @Override
    public Position3f mulProject(Matrix4fc mat) {
        super.mulProject(mat);
        return this;
    }

    @Override
    public Position3f mul(Matrix3fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3f mul(Matrix3fc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Position3f mul(Matrix3dc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3f mul(Matrix3dc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Position3f mul(Matrix3x2fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3f mul(Matrix3x2fc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Position3f mulTranspose(Matrix3fc mat) {
        super.mulTranspose(mat);
        return this;
    }

    @Override
    public Position3f mulTranspose(Matrix3fc mat, Vector3f dest) {
        super.mulTranspose(mat, dest);
        return this;
    }

    @Override
    public Position3f mulPosition(Matrix4fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Position3f mulPosition(Matrix4x3fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Position3f mulPosition(Matrix4fc mat, Vector3f dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Position3f mulPosition(Matrix4x3fc mat, Vector3f dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Position3f mulTransposePosition(Matrix4fc mat) {
        super.mulTransposePosition(mat);
        return this;
    }

    @Override
    public Position3f mulTransposePosition(Matrix4fc mat, Vector3f dest) {
        super.mulTransposePosition(mat, dest);
        return this;
    }

    @Override
    public Position3f mulDirection(Matrix4dc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3f mulDirection(Matrix4fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3f mulDirection(Matrix4x3fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3f mulDirection(Matrix4dc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3f mulDirection(Matrix4fc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3f mulDirection(Matrix4x3fc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3f mulTransposeDirection(Matrix4fc mat) {
        super.mulTransposeDirection(mat);
        return this;
    }

    @Override
    public Position3f mulTransposeDirection(Matrix4fc mat, Vector3f dest) {
        super.mulTransposeDirection(mat, dest);
        return this;
    }

    @Override
    public Position3f mul(float x, float y, float z) {
        super.mul(x, y, z);
        return this;
    }

    @Override
    public Position3f mul(float x, float y, float z, Vector3f dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f div(float scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Position3f div(float scalar, Vector3f dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Position3f div(float x, float y, float z) {
        super.div(x, y, z);
        return this;
    }

    @Override
    public Position3f div(float x, float y, float z, Vector3f dest) {
        super.div(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f rotate(Quaternionfc quat) {
        super.rotate(quat);
        return this;
    }

    @Override
    public Position3f rotate(Quaternionfc quat, Vector3f dest) {
        super.rotate(quat, dest);
        return this;
    }

    @Override
    public Position3f rotateAxis(float angle, float x, float y, float z) {
        super.rotateAxis(angle, x, y, z);
        return this;
    }

    @Override
    public Position3f rotateAxis(float angle, float aX, float aY, float aZ, Vector3f dest) {
        super.rotateAxis(angle, aX, aY, aZ, dest);
        return this;
    }

    @Override
    public Position3f rotateX(float angle) {
        super.rotateX(angle);
        return this;
    }

    @Override
    public Position3f rotateX(float angle, Vector3f dest) {
        super.rotateX(angle, dest);
        return this;
    }

    @Override
    public Position3f rotateY(float angle) {
        super.rotateY(angle);
        return this;
    }

    @Override
    public Position3f rotateY(float angle, Vector3f dest) {
        super.rotateY(angle, dest);
        return this;
    }

    @Override
    public Position3f rotateZ(float angle) {
        super.rotateZ(angle);
        return this;
    }

    @Override
    public Position3f rotateZ(float angle, Vector3f dest) {
        super.rotateZ(angle, dest);
        return this;
    }

    @Override
    public Position3f normalize() {
        super.normalize();
        return this;
    }

    @Override
    public Position3f normalize(Vector3f dest) {
        super.normalize(dest);
        return this;
    }

    @Override
    public Position3f normalize(float length) {
        super.normalize(length);
        return this;
    }

    @Override
    public Position3f normalize(float length, Vector3f dest) {
        super.normalize(length, dest);
        return this;
    }

    @Override
    public Position3f cross(Vector3fc v) {
        super.cross(v);
        return this;
    }

    @Override
    public Position3f cross(float x, float y, float z) {
        super.cross(x, y, z);
        return this;
    }

    @Override
    public Position3f cross(Vector3fc v, Vector3f dest) {
        super.cross(v, dest);
        return this;
    }

    @Override
    public Position3f cross(float x, float y, float z, Vector3f dest) {
        super.cross(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f zero() {
        super.zero();
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
        out.writeUTF(this.world.getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        world = Server.getInstance().getWorldPool().getWorld(in.readUTF());
    }

    @Override
    public Position3f negate() {
        super.negate();
        return this;
    }

    @Override
    public Position3f negate(Vector3f dest) {
        super.negate(dest);
        return this;
    }

    @Override
    public Position3f min(Vector3fc v) {
        super.min(v);
        return this;
    }

    @Override
    public Position3f min(Vector3fc v, Vector3f dest) {
        super.min(v, dest);
        return this;
    }

    @Override
    public Position3f max(Vector3fc v) {
        super.max(v);
        return this;
    }

    @Override
    public Position3f max(Vector3fc v, Vector3f dest) {
        super.max(v, dest);
        return this;
    }

    @Override
    public Position3f orthogonalize(Vector3fc v, Vector3f dest) {
        super.orthogonalize(v, dest);
        return this;
    }

    @Override
    public Position3f orthogonalize(Vector3fc v) {
        super.orthogonalize(v);
        return this;
    }

    @Override
    public Position3f orthogonalizeUnit(Vector3fc v, Vector3f dest) {
        super.orthogonalizeUnit(v, dest);
        return this;
    }

    @Override
    public Position3f orthogonalizeUnit(Vector3fc v) {
        super.orthogonalizeUnit(v);
        return this;
    }

    @Override
    public Position3f floor() {
        super.floor();
        return this;
    }

    @Override
    public Position3f floor(Vector3f dest) {
        super.floor(dest);
        return this;
    }

    @Override
    public Position3f ceil() {
        super.ceil();
        return this;
    }

    @Override
    public Position3f ceil(Vector3f dest) {
        super.ceil(dest);
        return this;
    }

    @Override
    public Position3f round() {
        super.round();
        return this;
    }

    @Override
    public Position3f round(Vector3f dest) {
        super.round(dest);
        return this;
    }

    @Override
    public Position3f absolute() {
        super.absolute();
        return this;
    }

    @Override
    public Position3f absolute(Vector3f dest) {
        super.absolute(dest);
        return this;
    }

    @Override
    public Position3f reflect(Vector3fc normal) {
        super.reflect(normal);
        return this;
    }

    @Override
    public Position3f reflect(float x, float y, float z) {
        super.reflect(x, y, z);
        return this;
    }

    @Override
    public Position3f reflect(Vector3fc normal, Vector3f dest) {
        super.reflect(normal, dest);
        return this;
    }

    @Override
    public Position3f reflect(float x, float y, float z, Vector3f dest) {
        super.reflect(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f half(Vector3fc other) {
        super.half(other);
        return this;
    }

    @Override
    public Position3f half(float x, float y, float z) {
        super.half(x, y, z);
        return this;
    }

    @Override
    public Position3f half(Vector3fc other, Vector3f dest) {
        super.half(other, dest);
        return this;
    }

    @Override
    public Position3f half(float x, float y, float z, Vector3f dest) {
        super.half(x, y, z, dest);
        return this;
    }

    @Override
    public Position3f smoothStep(Vector3fc v, float t, Vector3f dest) {
        super.smoothStep(v, t, dest);
        return this;
    }

    @Override
    public Position3f hermite(Vector3fc t0, Vector3fc v1, Vector3fc t1, float t, Vector3f dest) {
        super.hermite(t0, v1, t1, t, dest);
        return this;
    }

    @Override
    public Position3f lerp(Vector3fc other, float t) {
        super.lerp(other, t);
        return this;
    }

    @Override
    public Position3f lerp(Vector3fc other, float t, Vector3f dest) {
        super.lerp(other, t, dest);
        return this;
    }

    @Override
    public Vector3i get(int mode, Vector3i dest) {
        return super.get(mode, dest);
    }

    @Override
    public Position3f get(Vector3f dest) {
        super.get(dest);
        return this;
    }

    @Override
    public Vector3d get(Vector3d dest) {
        return super.get(dest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3fc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(world, that.world());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), world);
    }

    @Override
    public String toString() {
        return Runtime.formatNumbers(toString(Options.NUMBER_FORMAT)) + ",world=" + this.world.getName();
    }

    @Override
    public String toString(NumberFormat formatter) {
        return super.toString(formatter) + ",world=" + this.world.getName();
    }
}
