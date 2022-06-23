package com.example.annotationtest.annotation;

import java.lang.annotation.*;

/**
 * @author jiutao.Tan
 * @date 2022-06-22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OnceRequest {
}
