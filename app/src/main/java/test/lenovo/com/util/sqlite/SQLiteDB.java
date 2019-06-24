package test.lenovo.com.util.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import test.lenovo.com.bean.UserInfo;

public class SQLiteDB {
    /**  数据库名 */
    public static final String DB_NAME = "SQLite_Test";
    /** 数据库版本 */
    public static final int VERSION = 1;
    /** 数据库 */
    private static SQLiteDB sqliteDB;
    private SQLiteDatabase db;

    private SQLiteDB(Context context) {
        /** 初始化数据库 */
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        /** 获取db */
        db = dbHelper.getWritableDatabase();
    }
        /**
     * 获取SqliteDB实例
     * @param context
     */
        public synchronized static SQLiteDB getInstance(Context context) {
            if (sqliteDB == null) {
                sqliteDB = new SQLiteDB(context);
            }
            return sqliteDB;
        }

        public void insert(UserInfo user){
            db.execSQL("INSERT INTO user VALUES (NULL, ? , ? )", new Object[]{user.getName() , user.getAge()});
        }
}
