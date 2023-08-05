package cn.allay.api.datastruct.aabbtree;

import lombok.Getter;

import java.util.Objects;

@Getter
public record CollisionPair<T extends HasAABB & HasLongId>(T objectA, T objectB) {

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      CollisionPair<?> that = (CollisionPair<?>) o;
      return (Objects.equals(objectA.getId(), that.objectA.getId()) || Objects.equals(objectA.getId(), that.objectB.getId())) &&
             (Objects.equals(objectB.getId(), that.objectB.getId()) || Objects.equals(objectB.getId(), that.objectA.getId()));
   }

   @Override
   public int hashCode() {
      return Objects.hash(objectA.getId(), objectB.getId()) ^ Objects.hash(objectB.getId(), objectA.getId());
   }
}
