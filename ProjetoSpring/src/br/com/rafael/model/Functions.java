package br.com.rafael.model;

public class Functions {
	public static String toUtf8(String str){
		String ret = null;
		try {
			ret = new String(str.getBytes("UTF-8"), "ISO-8859-1");
		}
		catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return ret;
	}
}
