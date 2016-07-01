package br.com.nutrihub.util;

import java.util.List;

public class ValidatorUtil {
	public static boolean isEmpty(Object o ){
		if(o == null)
			return true;
		if(o instanceof List){
			if(((List) o).size() == 0)
				return true;
		}
		if(o instanceof String){
			if(((String) o).trim().isEmpty())
				return true;
		}
		if(o instanceof Number){
			if(((Number) o).doubleValue() == 0)
				return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object o){
		return !isEmpty(o);
	}
}
