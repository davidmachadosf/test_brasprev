package br.com.davidmachadosf.test_brasprev.interceptor;
import static br.com.davidmachadosf.test_brasprev.ConstantesApplication.HEADER_KEY_INTERCEPTOR;
import static br.com.davidmachadosf.test_brasprev.ConstantesApplication.HEADER_KEY_TOKEN;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.davidmachadosf.test_brasprev.interceptor.annotation.RolesAutorizados;
import br.com.davidmachadosf.test_brasprev.model.enums.RoleType;
import br.com.davidmachadosf.test_brasprev.service.utils.SenhaUtil;
import br.com.davidmachadosf.test_brasprev.service.utils.SenhaUtilService;
import lombok.extern.slf4j.Slf4j;

@Component
public class TokenInterceptor implements HandlerInterceptor {
	
	@Autowired
	SenhaUtilService service;

	// este pre-handle será chamado antes de todos os requests, para checar se usuário tem autorização de acesso
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	boolean autorizado = true;
    	StringBuilder proc = new StringBuilder();
    	
        final RolesAutorizados rolesAutorizados = ((HandlerMethod)handler).getMethod().getAnnotation((RolesAutorizados.class));

        // se tem a anotation, processa!
        if(rolesAutorizados != null){
        	List<RoleType> callerRoles = Arrays.asList(rolesAutorizados.value());
        	
        	proc.append("RolesAutorizados("+SenhaUtil.rolesToString(callerRoles)+")->");
        	String token = request.getHeader(HEADER_KEY_TOKEN);
        	proc.append("token("+token+")->");
        	String method = request.getMethod();
        	proc.append("method("+method+")->");
        	String uri = request.getRequestURI();
        	proc.append("uri("+uri+")->");
        	Map<String, String[]> paramMap = request.getParameterMap();
        	String loginParam = paramMap.get("login")[0];
        	proc.append("loginParam("+loginParam+")->");
        	
        	autorizado = service.verificaToken(token, callerRoles, loginParam);
        }

		/*
		 * if (token == null){ response.addHeader(HEADER_KEY_INTERCEPTOR,
		 * "Token não enviado"); log.info("Authorization não enviado.");
		 * log.info("Validação NOK."); return false; }
		 */
        
        // comentar para processar as requisições normais
		/*
		 * else if (token.equals("teste")) { response.addHeader(HEADER_KEY_INTERCEPTOR,
		 * "Token OK"); log.info("Validação OK."); return true; } else {
		 * response.addHeader(HEADER_KEY_INTERCEPTOR, "Token NOK");
		 * log.info("Validação NOK."); //return false; }
		 */
        
        proc.append(autorizado);
        response.addHeader(HEADER_KEY_INTERCEPTOR, proc.toString());
        return autorizado;
    }

}