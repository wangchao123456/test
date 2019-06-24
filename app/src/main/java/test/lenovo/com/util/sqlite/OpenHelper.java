package test.lenovo.com.util.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper{

    /*建表语句（创建用户表）
    建库建表的代码就是这样，当app运行之后，名为SQLite_Test的数据库里面就有了一张user表。
    user表里面有3个参数：userid 主键、username、password*/
    public static final String CREATE_USER = "create table user (" + "userid integer primary key autoincrement, " + "username text, " + "password text)";

    /**
     * 构造方法
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    /**
     * 初次创建
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        db.execSQL(CREATE_USER);
    }
    /**
     * 当数据库版本出现改变时
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
