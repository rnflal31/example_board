package com.samhyun.study.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Ibatis Sql XML을 주어진 파라미터가 적용된 실제 쿼리로 뽑아주는 클래스
 * Sql관리는 Ibatis로 통합하고 실제 쿼리는 R에서 날릴 수 있도록 하기 위해서
 * Sql문 생성은 Java, Sql문 실행은 R 내부에서 호출할 수 있도록 하기 위함.
 * @author tooktok
 *
 */
@Component
public class SQLGenerator {

	@Autowired
	private SqlSession sqlSession;

	private final Logger log = LoggerFactory.getLogger(getClass());



	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public String getQuery(String sqlId, Object paramObj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String sql = null;
		MappedStatement mappedStatement = sqlSession.getConfiguration()
				.getMappedStatement(sqlId);
		BoundSql boundSql = mappedStatement.getBoundSql(paramObj);
		sql = boundSql.getSql();
//		log.info(sql);
		Object param = boundSql.getParameterObject();
//		log.info("instanceOf : " + (param instanceof Map));
//		log.info("param : " + param);
		String listName = "";
		int index = 0;
		if (param == null) {
			sql = sql.replaceFirst("\\?", "''");
		} else {
			if (param instanceof Integer || param instanceof Long
					|| param instanceof Float || param instanceof Double) {
				sql = sql.replaceFirst("\\?", param.toString());
			} else if (param instanceof String) {
				sql = sql.replaceFirst("\\?", "'" + param + "'");
			} else if (param instanceof Map) { // 해당 파라미터가 Map 일 경우

				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
				for (int i = 0; i < paramMapping.size(); i++) {
					ParameterMapping mapping = paramMapping.get(i);
//					ObjectMapper mapper = new ObjectMapper();
//					HashMap convertedMapping = mapper.convertValue(mapping, HashMap.class);
					String propValue = mapping.getProperty();
					log.info("propValue : " + propValue);
					if (propValue.contains("__frch_")) {

//						log.debug("++++++++++++++++++++++++++++++++++++++++++");
						String[] propValueElement = propValue.split("_");
						Object listValue = ((Map) param).get(propValueElement[3]);
						List tempList = (List) listValue;
						log.info("propValueElement" + propValueElement[3]);
						log.info("tempList" + tempList);
						if(!listName.equals(propValueElement[3]) || tempList.size() == index )index = 0;
						Class<?> javaType = tempList.get(index).getClass();

						if (String.class == javaType) { // SQL의 ? 대신에 실제
														// 값을 넣는다.
							sql = sql.replaceFirst("\\?", "'"
									+ tempList.get(index) + "'");
						} else {
							sql = sql.replaceFirst("\\?",
									tempList.get(index).toString());
						}
						listName = propValueElement[3];
						index++;
					} else {
						Object value = ((Map) param).get(propValue);
						if (value instanceof String) {
							sql = sql.replaceFirst("\\?", "'" + value + "'");
						} else {
							sql = sql.replaceFirst("\\?", value==null ? "NULL":value.toString());
						}
					}
				}
			} else {
				List<ParameterMapping> paramMapping = boundSql
						.getParameterMappings();

				Class<? extends Object> paramClass = param.getClass();
//				log.debug("paramClass.getName() : {}", paramClass.getName());
//				log.debug("size = " + paramMapping.size());
				for (int i = 0; i < paramMapping.size(); i++) {
					ParameterMapping mapping = paramMapping.get(i);
//					ObjectMapper mapper = new ObjectMapper();
//					HashMap convertedMapping = mapper.convertValue(mapping, HashMap.class);
					String propValue = mapping.getProperty();
//					log.info("mapping : "+convertedMapping);
//					log.info("paramName = " + propValue);
					if (propValue.contains("__frch_")) {

//						log.debug("++++++++++++++++++++++++++++++++++++++++++");
						String[] propValueElement = propValue.split("_");
						Field listField = paramClass.getDeclaredField(propValueElement[3]);
//						log.info("name : " + listField.getName());
						listField.setAccessible(true);
						List tempList = (List) listField.get(param);
						if(!listName.equals(propValueElement[3]) || tempList.size() == index )index = 0;

						Class<?> javaType = tempList.get(index).getClass();

//						log.info("value : " + tempList.get(index));
						if (String.class == javaType) { // SQL의 ? 대신에 실제
														// 값을 넣는다.
							sql = sql.replaceFirst("\\?", "'"
									+ tempList.get(index) + "'");
						} else {
							sql = sql.replaceFirst("\\?",
									tempList.get(index).toString());
						}
						listName = propValueElement[3];
						index++;
					} else {
						Field field = paramClass.getDeclaredField(propValue); // 관련

						field.setAccessible(true);

						Class<?> javaType = mapping.getJavaType();

						if (String.class == javaType) {
							sql = sql.replaceFirst("\\?",
									"'" + field.get(param) + "'");
						} else {
							sql = sql.replaceFirst("\\?", field.get(param)
									.toString());
						}
					}
				}
			}

		}

//		log.debug("=============================================================");
//		log.debug("query = " + sql);
//		log.debug("=============================================================");
		return sql;
	}
}
