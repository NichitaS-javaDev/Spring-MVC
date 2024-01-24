package org.example.listener;

import jakarta.persistence.PrePersist;
import org.example.annotation.GenerateUUID;

import java.lang.reflect.Field;
import java.util.UUID;

public class GenerateUUIDListener {
    @PrePersist
    public void prePersist(Object entity) throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(GenerateUUID.class)) {
                field.setAccessible(true);
                field.set(entity, UUID.randomUUID());
            }
        }
    }
}
