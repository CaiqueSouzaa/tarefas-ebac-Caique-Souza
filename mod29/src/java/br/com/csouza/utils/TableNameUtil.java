package br.com.csouza.utils;

import br.com.csouza.annotations.TableName;
import br.com.csouza.exceptions.WithoutTableName;

public class TableNameUtil {
	public static String getTableName(Object o) throws WithoutTableName {
		final Class<?> oClass = o.getClass();
		return getName(oClass);
	}
	
	public static String getTableNameClass(Class<?> oClass) throws WithoutTableName {
		return getName(oClass);
		
	}
	
	private static String getName(Class<?> oClass) throws WithoutTableName {
		TableName hasAnnotation = oClass.getAnnotation(TableName.class);
		
		if (hasAnnotation == null) {
			throw new WithoutTableName("Nome de tabela não configurada na entidade [" + oClass.getSimpleName() + "]");
		}
		
		return hasAnnotation.value();
	}
}
