package net.huangang.naming;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class StudentSituationInfo extends Activity {
	
	private TextView snumber;
	private TextView sname;
	private Button save;
	private Button delete;
	private Button back;
	private RadioButton chu;
	private RadioButton que;
	private RadioButton jia;
	private RadioGroup situation;
	private RadioButton now_situation;
	
	private MyDatabaseHelper dbHelper;
	
	String student_name;//学生姓名
	String student_number;//学号
	String student_situation;//出勤情况
	String created_time;//创建时间

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_situation_info);
		ExitAPPUtils.getInstance().addActivity(this);
		Intent intent = getIntent();
		student_name = intent.getStringExtra("student_name");
		student_number = intent.getStringExtra("student_number");
		student_situation = intent.getStringExtra("student_situation");
		created_time = intent.getStringExtra("created_time");
        
        snumber = (TextView)findViewById(R.id.situation_snumber);
        sname = (TextView)findViewById(R.id.situation_sname);
        save = (Button)findViewById(R.id.save_situation);
        delete = (Button)findViewById(R.id.delete_situation);
        back = (Button)findViewById(R.id.back_all_situation);
        chu = (RadioButton)findViewById(R.id.situation_chu);
        que = (RadioButton)findViewById(R.id.situation_que);
        jia = (RadioButton)findViewById(R.id.situation_jia);
        situation = (RadioGroup)findViewById(R.id.situation);
       
        
        if(student_situation.equals("出")){
        	chu.setChecked(true);
        	Log.d("StudentSituationInfo","出");
        	now_situation = (RadioButton)findViewById(R.id.situation_chu);
        }else if(student_situation.equals("缺")){
        	que.setChecked(true);
        	Log.d("StudentSituationInfo","缺");
        	now_situation = (RadioButton)findViewById(R.id.situation_que);
        }else if(student_situation.equals("假")){
        	Log.d("StudentSituationInfo","假");
        	jia.setChecked(true);
        	now_situation = (RadioButton)findViewById(R.id.situation_jia);
        }else {
        	chu.setChecked(true);
        	now_situation = (RadioButton)findViewById(R.id.situation_chu);
        	Log.d("StudentSituationInfo","无");
        }
        
        sname.setText(" 姓名:"+student_name);
        snumber.setText(" 学号:"+student_number);
        
        dbHelper = new MyDatabaseHelper(this, "StudentNaming.db", null, 4);
        
        situation.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				//获取变更后的选中项的ID 
				int radioButtonId = group.getCheckedRadioButtonId(); 
				//根据ID获取RadioButton的实例 
				now_situation = (RadioButton)StudentSituationInfo.this.findViewById(radioButtonId); 
			}
		});
        
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				
				ContentValues values = new ContentValues(); 
				String n_situation = (String) now_situation.getText();
				values.put("student_situation", n_situation);
				db.update("situation", values, "created_time = ?", new String[] { created_time });
				Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
				onBackPressed();
				
			}
		});
        
        delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("situation", "created_time = ?", new String[] { created_time });
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
		Intent intent = new Intent(StudentSituationInfo.this, AllStudentSituation.class); 
	    startActivity(intent);
	}
	
	
	
}
