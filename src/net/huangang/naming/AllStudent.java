package net.huangang.naming;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AllStudent extends Activity {
	
	SQLiteDatabase db;
	Cursor cursor;
	private MyDatabaseHelper dbHelper;
	String name;//学生姓名
	String number;//学号
	
	private List<Student> studentList = new ArrayList<Student>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dbHelper = new MyDatabaseHelper(this, "StudentNaming.db", null, 4);
		
	
		setContentView(R.layout.all_student);
		initStudents(); // 初始化学生数据
		StudentAdapter adapter = new StudentAdapter(AllStudent.this,R.layout.student_item, studentList);
		
		
		ListView listView = (ListView) findViewById(R.id.student_list_view); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//Toast.makeText(AllStudent.this, String.valueOf(position+1),Toast.LENGTH_SHORT).show();
				Student student = studentList.get(position);
			    //Toast.makeText(AllStudent.this, student.getName(),Toast.LENGTH_SHORT).show();
			    Intent intent = new Intent(AllStudent.this, StudentInfo.class); 
			    intent.putExtra("student_name", student.getName());
			    intent.putExtra("student_number", student.getNumber());
			    startActivity(intent);
			}
			
		});
		
	}

	private void initStudents() {
		// TODO Auto-generated method stub
		
		dbHelper.getWritableDatabase();
		db = dbHelper.getWritableDatabase();
		cursor = db.query("student", null, null, null, null, null, null);
		//data =  new String[cursor.getCount()];
		if(cursor.moveToFirst()){
			do{
				name = cursor.getString(cursor.getColumnIndex("name"));
				number = cursor.getString(cursor.getColumnIndex("number"));
				
				
				Log.d("AllStudent", "student name is " + name); 
				Log.d("AllStudent", "student number is " + number); 
				
				Student data = new Student(name, number); 
				studentList.add(data);
				
			}while(cursor.moveToNext());
			
		}
		cursor.close();
		
		
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(AllStudent.this, MainActivity.class); 
	    startActivity(intent);
	}

}
