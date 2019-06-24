package test.lenovo.com.util.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.lenovo.com.MySqliteHelper;
import test.lenovo.com.bean.Person;
//调用getWritableDatabase()或getReadableDatabase()才会真正创建或打开
public class PersonDao {
    private Context context;
    private MySqliteHelper dbHelper;
    public PersonDao(Context context){
        this.context = context;
        dbHelper = new MySqliteHelper(context);
    }


    /**
     * 添加一条记录
     */
    public void add(String name, int age) {
        //调用getWritableDatabase()才会真正创建数据库表
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("age", age);
            values.put("name", name);
            //不允许插入一个空值,如果contentvalue,一般第二个参
            db.insert("person", null, values);
            //通过组拼完成的添加的操作
        }
        db.close();
    }
    //查询单条记录
   /* public boolean find(String name) {
        boolean status_result = false;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        public android.database.Cursor query(
//                String table,
//                String[] columns,
//                String selection,
//                String[] selectionArgs,
//                String groupBy,
//                String having,
//                String orderBy)

        if (db.isOpen()) {
            Cursor cursor = db.query("person", null, "name=?", new String[]{name}, null, null, null);
            if (cursor.moveToFirst()) {
                status_result = true;
            }
            cursor.close();
            db.close();
        }
        return status_result;
    }*/

    public Person find(String name) {
        Person person = new Person();
        //调用getReadableDatabase()才会真正打开数据库表
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        public android.database.Cursor query(
//                String table,
//                String[] columns,
//                String selection,
//                String[] selectionArgs,
//                String groupBy,
//                String having,
//                String orderBy)

        if (db.isOpen()) {
            Cursor cursor = db.query("person", null, "name=?", new String[]{name}, null, null, null);
            if (cursor.moveToFirst()) {
                person.setName(cursor.getString(cursor.getColumnIndex("name")));
                person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            }
            cursor.close();
            db.close();
        }
        return person;
    }

    public List<Person> findAll(){
        List<Person> persons = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db.isOpen()){
            persons = new ArrayList<Person>();

            Cursor cursor = db.query("person", null, null, null, null, null, null);

            while(cursor.moveToNext()){
                Person person = new Person();
                person.setName(cursor.getString(cursor.getColumnIndex("name")));
                person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                persons.add(person);
            }
            cursor.close();
            db.close();
        }
        return persons;
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null, null);
        if(cursor.moveToNext()){
//            db.execSQL("delete from person");
           int num = db.delete("person",null,null);
            Log.e("aaaaa", "====person====delete===" + num);
        }
        cursor.close();
        db.close();
    }
}