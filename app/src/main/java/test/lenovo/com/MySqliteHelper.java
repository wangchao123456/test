package test.lenovo.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {
    //数据库版本
    private static final int DB_VERSION = 1;
    // 数据库名称
    private static final String DB_NAME = "myTest.db";
    // 表的名称
    public static final String TABLE_NAME = "Orders";

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //初始化数据表，可以再这里面对多个表进行处理
//        String sql = "create table if not exists " + TABLE_NAME + " (studentid text primary key, name text, sex text, age integer)";
//        db.execSQL(sql);
        db.execSQL("create table if not exists person (personid integer primary key autoincrement ,name varchar(30) ,age integer(3) )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
