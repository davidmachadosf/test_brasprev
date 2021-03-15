package br.com.davidmachadosf.test_brasprev.interceptor;
import static br.com.davidmachadosf.test_brasprev.ConstantesApplication.INTERCEPTOR_HEADER_KEY;
import static br.com.davidmachadosf.test_brasprev.ConstantesApplication.TOKEN_READER_KEY;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.davidmachadosf.test_brasprev.interceptor.annotation.RolesAutorizados;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

	// este pre-handle será chamado antes de todos os requests, para checar se usuário tem autorização de acesso
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final RolesAutorizados rolesAutorizados = ((HandlerMethod)handler).getMethod().getAnnotation((RolesAutorizados.class));

        // se tem a anotation, processa!
        if(rolesAutorizados != null){
            response.addHeader(INTERCEPTOR_HEADER_KEY, "Passou por aqui");
            return true;
        }

        if (request.getHeader(TOKEN_READER_KEY) == null){
            response.addHeader(INTERCEPTOR_HEADER_KEY, "Token não enviado");
            log.info("Authorization não enviado.");
            log.info("Validação NOK.");
            //return false;
        }
        
        // comentar para processar as requisições normais
        else 
        if (request.getHeader(TOKEN_READER_KEY).equals("teste")) {
            response.addHeader(INTERCEPTOR_HEADER_KEY, "Token OK");
            log.info("Validação OK.");
            return true;
        } 
        else {
            response.addHeader(INTERCEPTOR_HEADER_KEY, "Token NOK");
            log.info("Validação NOK.");
            //return false;
        }
        
        return true;

    }

}