package com.my.project.spring.framework.context.support;

import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 15:25
 **/
public interface GenericConverter {


    @Nullable
    Set<ConvertiblePair> getConvertibleTypes();

    @Nullable
    Object convert(@Nullable Object source, Class sourceType, Class targetType);


    final class ConvertiblePair {
        private final Class<?> sourceType;

        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return this.sourceType;
        }

        public Class<?> getTargetType() {
            return this.targetType;
        }
    }
}
