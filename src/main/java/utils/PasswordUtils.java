package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

	// verifica se senha fornecida gera mesmo hash que o armazenado na base;
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
			byte[] hashedPassword = md.digest(senha.getBytes(StandardCharsets.UTF_8));
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
}
