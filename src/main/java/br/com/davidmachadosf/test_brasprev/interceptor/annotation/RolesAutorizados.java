package br.com.davidmachadosf.test_brasprev.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.davidmachadosf.test_brasprev.model.enums.RoleType;

@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME)
public @interface RolesAutorizados {

    RoleType[] value();

}