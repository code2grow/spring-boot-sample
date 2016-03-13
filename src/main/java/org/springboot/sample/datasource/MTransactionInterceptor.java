package org.springboot.sample.datasource;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 扩展TransactionInterceptor
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年3月13日
 */
@SuppressWarnings("serial")
public class MTransactionInterceptor extends TransactionInterceptor {

	protected transient final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected Object invokeWithinTransaction(Method method, Class<?> targetClass, InvocationCallback invocation)
			throws Throwable {
		Object obj;
		try {
			TargetDataSource ds = method.getAnnotation(TargetDataSource.class);
			if (ds != null) {
				String dsId = ds.name();
				if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
					logger.error("数据源[{}]不存在，使用默认数据源", dsId);
				} else {
					logger.debug("Use DataSource : {}", ds.name());
					DynamicDataSourceContextHolder.setDataSourceType(ds.name());
				}
			}
			obj = super.invokeWithinTransaction(method, targetClass, invocation);
		} finally {
			DynamicDataSourceContextHolder.clearDataSourceType();
		}
		return obj;
	}

}
