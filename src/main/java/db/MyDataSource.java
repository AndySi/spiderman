package db;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class MyDataSource {
	
	public static DataSource getDataSource(String connectURI){
		BasicDataSource ds = new BasicDataSource();
		//MySQL的jdbc驱动
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		//所要连接的数据库名
		ds.setUsername("root");
		//MySQL的登陆密码
		ds.setPassword("rootpwd");
		ds.setUrl(connectURI);
		return ds;
	}

}

