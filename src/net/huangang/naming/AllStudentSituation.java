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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class AllStudentSituation extends Activity {
	
	SQLiteDatabase db;
	Cursor cursor;
	private MyDatabaseHelper dbHelper;
	 
	private String[] data;
	String student_name;//学生姓名
	String student_number;//学号
	String student_situation;//出勤情况
	String created_time;//创建时间
	
	private List<StudentSituation> studentSituationList = new ArrayList<StudentSituation>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		dbHelper = new MyDatabaseHelper(this, "StudentNaming.db", null, 4);
		dbHelper.getWritableDatabase();
		db = dbHelper.getWritableDatabase();
		cursor = db.query("situation", null, null, null, null, null, null);
		data =  new String[cursor.getCount()];
		if(cursor.moveToLast()){
			int i=0;
			do{
				student_name = cursor.getString(cursor.getColumnIndex("student_name"));
				student_number = cursor.getString(cursor.getColumnIndex("student_number"));
				student_situation =cursor.getString(cursor.getColumnIndex("student_situation"));
				created_time = cursor.getString(cursor.getColumnIndex("created_time"));
				
				Log.d("AllStudentSituation", "student name is " + student_name); 
				Log.d("AllStudentSituation", "student number is " + student_number); 
				Log.d("AllStudentSituation", "student situation is " + student_situation); 
				Log.d("AllStudentSituation", "student created_time is " + created_time); 
				
				data[i]="姓名:"+student_name+",学号:"+student_number+",情况:"+student_situation+",时间:"+created_time;
				i++;
				
			}while(cursor.moveToPrevious());
			
		}
		cursor.close();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_student_situation);
		
		initStudentSituations(); // 初始化考勤数据
		StudentSituationAdapter adapter = new StudentSituationAdapter(AllStudentSituation.this,R.layout.student_situation_item, studentSituationList);
		
		ExitAPPUtils.getInstance().addActivity(this);
		
		ListView listView = (ListView) findViewById(R.id.list_view); 
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				StudentSituation studentSituation = studentSituationList.get(position);
			    //Toast.makeText(AllStudentSituation.this, studentSituation.getStudent_name(),Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(AllStudentSituation.this, StudentSituationInfo.class); 
			    intent.putExtra("student_name", studentSituation.getStudent_name());
			    intent.putExtra("student_number", studentSituation.getStudent_number());
			    intent.putExtra("student_situation", studentSituation.getStudent_situation());
			    intent.putExtra("created_time", studentSituation.getCreated_time());
			    startActivity(intent);
			}
			
		});
	}

	private void initStudentSituations() {
		dbHelper.getWritableDatabase();
		db = dbHelper.getWritableDatabase();
		cursor = db.query("situation", null, null, null, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
				student_name = cursor.getString(cursor.getColumnIndex("student_name"));
				student_number = cursor.getString(cursor.getColumnIndex("student_number"));
				student_situation = cursor.getString(cursor.getColumnIndex("student_situation"));
				created_time = cursor.getString(cursor.getColumnIndex("created_time"));
				
				StudentSituation data = new StudentSituation(student_name, student_number, student_situation, created_time); 
				studentSituationList.add(data);
				
			}while(cursor.moveToNext());
			
		}
		cursor.close();
		
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(AllStudentSituation.this, MainActivity.class); 
	    startActivity(intent);
	}
	
	
}
