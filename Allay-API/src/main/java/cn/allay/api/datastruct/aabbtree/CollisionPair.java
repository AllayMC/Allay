package cn.allay.api.datastruct.aabbtree;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class CollisionPair<T extends Boundable & Identifiable> {
   private final T objectA;
   private final T objectB;

   public CollisionPair(T objectA, T objectB) {
      this.objectA = objectA;
      this.objectB = objectB;
   }

    @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      CollisionPair<?> that = (CollisionPair<?>) o;
      return (Objects.equals(objectA.getID(), that.objectA.getID()) || Objects.equals(objectA.getID(), that.objectB.getID())) &&
             (Objects.equals(objectB.getID(), that.objectB.getID()) || Objects.equals(objectB.getID(), that.objectA.getID()));
   }

   @Override
   public int hashCode() {
      return Objects.hash(objectA.getID(), objectB.getID()) ^ Objects.hash(objectB.getID(), objectA.getID());
   }
}
