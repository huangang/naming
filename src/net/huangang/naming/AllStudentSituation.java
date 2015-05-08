package net.huangang.naming;



import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class AllStudentSituation extends Activity {
	
	SQLiteDatabase db;
	Cursor cursor;
	private MyDatabaseHelper dbHelper;
	 
	private String[] data;
	String student_name;//学生姓名
	String student_number;//学号
	String student_situation;//出勤情况
	String created_time;//创建时间

	
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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(AllStudentSituation.this, android.R.layout.simple_list_item_1, data); 
		ListView listView = (ListView) findViewById(R.id.list_view); 
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(AllStudentSituation.this, String.valueOf(position+1),Toast.LENGTH_SHORT).show();

			}
			
		});
	}
}
