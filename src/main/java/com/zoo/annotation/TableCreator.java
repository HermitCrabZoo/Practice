package com.zoo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时注解处理器，构造表创建语句
 *
 */
public class TableCreator {

	public static String createTableSql(String className) throws ClassNotFoundException {
		Class<?> cl = Class.forName(className);
		Table table = cl.getAnnotation(Table.class);
		// 如果没有表注解，直接返回
		if (table == null) {
			System.out.println("No Table annotations in class " + className);
			return null;
		}
		String tableName = table.name();
		
		// If the name is empty, use the Class name:
		if (tableName.length() < 1)
			tableName = cl.getSimpleName().toUpperCase();
		
		List<String> columnDefs = new ArrayList<String>();
		// 通过Class类API获取到所有成员字段
		for (Field field : cl.getDeclaredFields()) {
			String columnName = null;
			// 获取字段上的注解
			Annotation[] anns = field.getDeclaredAnnotations();
			if (anns.length < 1)
				continue; // Not a db table column

			// 判断注解类型
			if (anns[0] instanceof SQLInteger) {
				SQLInteger sql = (SQLInteger) anns[0];
				// 获取字段对应列名称，如果没有就是使用字段名称替代
				if (sql.name().length() < 1)
					columnName = field.getName().toUpperCase();
				else
					columnName = sql.name();
				// 构建语句
				columnDefs.add(columnName + " INT" + getConstraints(sql.constraint()));
			}else if (anns[0] instanceof SQLString) {
				// 判断String类型
				SQLString sql = (SQLString) anns[0];
				// Use field name if name not specified.
				if (sql.name().length() < 1)
					columnName = field.getName().toUpperCase();
				else
					columnName = sql.name();
				columnDefs.add(columnName + " VARCHAR(" + sql.value() + ")" + getConstraints(sql.constraint()));
			}

		}
		// 数据库表构建语句
		StringBuilder createCommand = new StringBuilder("CREATE TABLE ").append(tableName).append("(\n    ")
				.append(String.join(",\n    ", columnDefs))
				.append("\n);");
		String tableCreate = createCommand.toString();
		return tableCreate;
	}

	/**
	 * 判断该字段是否有其他约束
	 * 
	 * @param con
	 * @return
	 */
	private static String getConstraints(Constraints con) {
		StringBuilder sb = new StringBuilder();
		if (!con.allowNull())
			sb.append(" NOT NULL");
		if (con.primaryKey())
			sb.append(" PRIMARY KEY");
		if (con.unique())
			sb.append(" UNIQUE");
		return sb.toString();
	}

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(createTableSql(Member.class.getName()));
//		CREATE TABLE MEMBER(
//			    ID VARCHAR(50) NOT NULL PRIMARY KEY,
//			    NAME VARCHAR(30) NOT NULL,
//			    AGE INT NOT NULL,
//			    DESCRIPTION VARCHAR(150)
//			);
	}

}
