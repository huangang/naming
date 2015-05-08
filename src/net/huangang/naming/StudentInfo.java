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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentInfo extends Activity {
	
	private TextView snumber;
	private TextView sname;
	private EditText new_name;
	private Button save;
	private Button delete;
	private Button back;
	
	private MyDatabaseHelper dbHelper;
	String name;
	String number;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_info);
		Intent intent = getIntent();
        name = intent.getStringExtra("student_name");
        number = intent.getStringExtra("student_number");
        
        snumber = (TextView)findViewById(R.id.snumber);
        sname = (TextView)findViewById(R.id.sname);
        new_name = (EditText)findViewById(R.id.new_name);
        save = (Button)findViewById(R.id.save_student);
        delete = (Button)findViewById(R.id.delete_student);
        back = (Button)findViewById(R.id.back_all_student);
        
        
        sname.setText(" 姓名:"+name);
        snumber.setText(" 学号:"+number);
        
        dbHelper = new MyDatabaseHelper(this, "StudentNaming.db", null, 4);
        
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				
				String nname = new_name.getText().toString();
				ContentValues values = new ContentValues(); 
				values.put("name", nname);
				db.update("student", values, "number = ?", new String[] { number });
				Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
				onBackPressed();
				
			}
		});
        
        delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("student", "number = ?", new String[] { number });
				Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
				onBackPressed();
				
			}
		});
        
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "返回", Toast.LENGTH_SHORT).show();
				onBackPressed();
				
			}
		});
        
       

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(StudentInfo.this, AllStudent.class); 
	    startActivity(intent);
	}
	
}
