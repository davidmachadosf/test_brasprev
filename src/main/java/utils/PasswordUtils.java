package utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import br.com.davidmachadosf.test_brasprev.model.Usuario;
import br.com.davidmachadosf.test_brasprev.repository.UsuarioRepository;

public class PasswordUtils {

	private static final Charset BASE64_CHARSET = StandardCharsets.UTF_8;
	
	private static long SEGUNDOS = 1000/*milissegundos*/;
	private static long MINUTOS = 60*SEGUNDOS;
	private static long HORAS = 60*MINUTOS;
	private static long DIAS = 24*HORAS;
	private static long SEMANAS = 7*DIAS;
	//private static long expires = 2*DIAS;
	
	// tokens de autenticação expiram em 2 horas e meia
	private static long expiresDuration = 2*HORAS + 30*MINUTOS;		
	private static String chave = "secret$key";
	
	// autenticação de login/senha
	//public static void auth(String login, String senha, String... roles) {
	//	SecurityContextHolder.getContext().setAuthentication(
	//		new UsernamePasswordAuthenticationToken(login, senha, AuthorityUtils.createAuthorityList(roles)));
	//}
	
	// gera token de autenticação com data de expiração
	public static String geraToken(String login, String senha) throws Exception{		
		long agora = (new Date()).getTime();
		return 
		"{ 'Authorization' : '" +
			encrypt(
				encodeBase64String(login)+":"+
				encodeBase64String(senha)+":"+
				encodeBase64String(""+(agora+expiresDuration)), chave) +
		"'; }";
	}

	public static void verificaToken(UsuarioRepository repository, String token, List<String> callerRoles, String loginParam) throws Exception {		
		
		String authString = decrypt(token, chave);
		
		String[]authParts = authString.split(":");
		String login = decodeBase64String(authParts[0]);
		String senha = decodeBase64String(authParts[1]);
		long expires = Long.parseLong(decodeBase64String(authParts[2]));
		
		// verifica se token já expirou
		long agora = (new Date()).getTime();
		if(agora>=expires) {
			 throw new Exception("Token expirado!");
		}
		
		try{
			Usuario usuario = repository.getByLogin(login);
		    String hashAtual = usuario.getHash();
	        String[]roles = new String[]{""+usuario.getNivel()};
	        
	        // verifica se senha fornecida está correta
	        if(!verificaSenha(hashAtual, senha)) {
				throw new Exception("Autenticação inválida![1]");
			}
	        
	        // uma das exigências do serviço é que o usuário só possa mudar dados dele próprio? (ex.: troca de senha)
	        if(callerRoles.contains("OWNER") && loginParam.equals(login)) return;
	       
	        // papel do usuário está na lista de papéis que podem acessar o serviço?
	        for(String role:roles) {
	        	if(callerRoles.contains(role)) return;
	        }
	        
	        throw new Exception("Autenticação inválida![2]");
		}
		catch (Exception ex) {
			throw new Exception("Autenticação inválida![3]");
		}        
	}
	
	
	
	// verifica se senha fornecida gera mesmo hash que o armazenado na base
	public static boolean verificaSenha(String hashAtual, String senha) {		
		byte[]salt = decodeBase64(hashAtual.split(":")[0]);		
		return hashAtual.equals(geraHashComSaltParaSenha(salt,senha));
	}
	
	// cria um novo hash de salt aleatório a partir da senha fornacida
	public static String geraHashNovoParaSenha(String senha) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
        return geraHashComSaltParaSenha(salt,senha);        
	}
	
    // cria um hash para a senha aberta acresentando um salt aleatório a ela;
	// isto faz com que uma mesma senha gere hashs aleatórios diferentes, o que
	// dificulta muito a tentativa de descobri-la por ataques de brute force
	private static String geraHashComSaltParaSenha(byte[] salt, String senha) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] hashedPassword = md.digest(senha.getBytes(BASE64_CHARSET));
			return encodeBase64(salt)+":"+encodeBase64(hashedPassword);
		} 
		catch (NoSuchAlgorithmException nsaex) {
			nsaex.printStackTrace();
		}
		
		return null;
	}
	
	private static String encodeBase64(byte[]bytes) {
	   return Base64.getEncoder().encodeToString(bytes);
	}
	
	private static byte[] decodeBase64(String encodedString) {
	    return Base64.getDecoder().decode(encodedString);
	}
	
	
	private static String encodeBase64String(String string) {
	    byte[] bytes = string.getBytes(BASE64_CHARSET);
	    return Base64.getEncoder().encodeToString(bytes);
	}
		
	
	private static String decodeBase64String(String encodedString) {
		byte[] bytes = Base64.getDecoder().decode(encodedString);
	    return  new String(bytes, BASE64_CHARSET);
	}
	
	
	public static String encrypt(String strClearText,String strKey) throws Exception{
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
			strData=new String(encrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	

	public static String decrypt(String strEncrypted,String strKey) throws Exception{
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
			strData=new String(decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
}
