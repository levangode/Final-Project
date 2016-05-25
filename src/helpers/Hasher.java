package helpers;
import java.security.MessageDigest;

public class Hasher {
	
	public Hasher(){
		
	}

	private String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	public String generateHash(String toHash) {
		byte[] converted = null;
		try {
			MessageDigest mdg = MessageDigest.getInstance("SHA");
			converted = mdg.digest(toHash.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hexToString(converted);
	}
}
