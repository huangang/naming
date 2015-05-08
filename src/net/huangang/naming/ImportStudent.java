package net.huangang.naming;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImportStudent extends Activity {
	
	private Button create_student;
	private EditText number_text;
	private EditText name_text;
	
	private MyDatabaseHelper dbHelper;
	SQLiteDatabase db;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.importstudent);
        create_student = (Button)findViewById(R.id.create_student);
        number_text = (EditText)findViewById(R.id.number);
        name_text = (EditText)findViewById(R.id.name);
        
        dbHelper = new MyDatabaseHelper(this, "StudentNaming.db", null, 4);
        db = dbHelper.getWritableDatabase();
        
        create_student.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = name_text.getText().toString();
				int number =Integer.parseInt(number_text.getText().toString());
				Log.d("name", name);
				Log.d("number", String.valueOf(number));
				
				//SQLiteDatabase db = dbHelper.getWritableDatabase();
//				ContentValues values = new ContentValues();
//				values.put("number", number );
//				values.put("name",  name);
//				if(db.insert("student", null, values) != -1){
//					Toast.makeText(getApplicationContext(), "插入学生成功", Toast.LENGTH_SHORT).show();
//					values.clear();
//				}
				addStudent(name,  String.valueOf(number));
				
			}
		});
        
        
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(ImportStudent.this, MainActivity.class);
        startActivity(intent);
	}
	
	
     public void addStudent(String name,String number){
		
		ContentValues values = new ContentValues();
		cursor = db.query("student", new  String[] { "name" }, "number=?", new  String[]{ number }, null, null, null);
		if(cursor.moveToLast()){
			
			Log.d("student is exist", name);
			Toast.makeText(getApplicationContext(), "该学号已存在", Toast.LENGTH_SHORT).show();
			
		}else{
			
			Log.d("student is not exist", name);
			values.put("number", number );
			values.put("name",  name);
			db.insert("student", null, values);
			if(db.insert("student", null, values) != -1){
				Toast.makeText(getApplicationContext(), "插入学生成功", Toast.LENGTH_SHORT).show();
				values.clear();
			}
		}

		
		
	}
}
