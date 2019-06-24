package test.lenovo.com.mtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import test.lenovo.com.bean.Person;
import test.lenovo.com.util.sqlite.PersonDao;
import test.lenovo.com.util.sqlite.SQLiteDB;

import static junit.framework.Assert.assertEquals;

public class SqliteActivity extends Activity implements View.OnClickListener {
    private Button btAdd, btFind, btFindOne, btDeleteAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity);
        btAdd = (Button)findViewById(R.id.bt_add);
        btFind = (Button)findViewById(R.id.bt_find);
        btFindOne = (Button)findViewById(R.id.bt_find_one);
        btDeleteAll = (Button)findViewById(R.id.bt_delete_all);
        SQLiteDB.getInstance(this);
        btAdd.setOnClickListener(this);
        btFind.setOnClickListener(this);
        btFindOne.setOnClickListener(this);
        btDeleteAll.setOnClickListener(this);
        ///Log.e("aaaaa", "===ndk==" + getSingnativePassword());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add:
                int age = 10;
                age++;
                PersonDao personDao_add = new PersonDao(SqliteActivity.this);
                personDao_add.add("amosli_1",age);
                personDao_add.add("amosli_2",age + 1);
                personDao_add.add("amosli_3",age + 2);
                break;
            case R.id.bt_find:
                PersonDao personDao_find = new PersonDao(SqliteActivity.this);
//                assertEquals(true, personDao_find.find("amosli_1"));
                List<Person> personList = personDao_find.findAll();
                if(personList != null && personList.size() > 0){
                    for(Person person: personList){
                        Log.e("aaaaa:","====person====" + person.getName() + "==" + person.getAge());
                    }
                }else{
                    Log.e("aaaaa:","====person====nothing");
                }

                break;
            case R.id.bt_find_one:
                PersonDao personDao_find_one = new PersonDao(SqliteActivity.this);
                Person person = personDao_find_one.find("amosli_3");
                Log.e("aaaaa:","====person==one==" + person.getName() + "==" + person.getAge());
                break;
            case R.id.bt_delete_all:
                PersonDao personDao_delete_all = new PersonDao(SqliteActivity.this);
                personDao_delete_all.deleteAll();
                break;
        }

    }

    private native String getSingnativePassword();
}
