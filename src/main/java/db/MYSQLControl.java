package db;

import model.DyttModel;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class MYSQLControl {
	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/moviedata");
	static QueryRunner qr = new QueryRunner(ds);

	//第二类数据库操作方法
	public static void executeInsert(List<DyttModel> moviedata) throws SQLException {
		//定义一个Object数组，行列
		Object[][] params = new Object[moviedata.size()][3];
		for ( int i=0; i<params.length; i++ ){
			params[i][0] = moviedata.get(i).getMovieTitle();
		    params[i][1] = moviedata.get(i).getMovieUrl();
			params[i][2] = moviedata.get(i).getNewDate();
		}
		try {
			qr.batch("insert into tb_dytt_url (movieTitle,movieUrl,newDate)"
					+ "values (?,?,?)", params);
		} catch (Exception e){
			e.printStackTrace();
		}

		System.out.println("执行数据库完毕！"+"成功插入数据："+moviedata.size()+"条");
	}
}
