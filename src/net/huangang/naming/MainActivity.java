package net.huangang.naming;




import org.xmlpull.v1.XmlPullParser;


import android.app.Activity;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private MyDatabaseHelper dbHelper;
	private Button create;
	
	ActionBar actionBar;
	
	String name;//ѧ������
	String number;//ѧ��
	String situation;//ѧ�����
	
	private TextView student_name;
	private TextView student_number;
	private TextView student_all;
	private TextView student_now;
	int nowid;
	int allid;
	private Button next_student;
	private Button previous_student;
	private Button first_student;
	private Button last_student;
	private Button submit_situation;
	private Button view_situation;
	private Button view_student;
	private RadioGroup student_situation;
	private RadioButton now_situation;
	
	SQLiteDatabase db;
	Cursor cursor;
	
	
	private Resources resources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar=getActionBar();
        actionBar.show();
	
		dbHelper = new MyDatabaseHelper(this, "StudentNaming.db", null, 4);
		dbHelper.getWritableDatabase();
		create = (Button)findViewById(R.id.create);
		
		create.setOnClickListener(this);
		
		student_name = (TextView)findViewById(R.id.showname);//չʾѧ������
		student_number = (TextView)findViewById(R.id.shownumber);//չʾѧ��ѧ��
		student_all = (TextView)findViewById(R.id.all);//չʾ��ѧ����
		student_now = (TextView)findViewById(R.id.now);//չʾĿǰѧ��˳��
//		nowid = 1;
//		student_now.setText(String.valueOf(nowid));
		next_student = (Button)findViewById(R.id.next);
		next_student.setOnClickListener(this);
		previous_student = (Button)findViewById(R.id.previous);
		previous_student.setOnClickListener(this);
		previous_student.setEnabled(false);
		first_student = (Button)findViewById(R.id.first);
		first_student.setOnClickListener(this);
		first_student.setEnabled(false);
		last_student = (Button)findViewById(R.id.last);
		last_student.setOnClickListener(this);
		submit_situation = (Button)findViewById(R.id.submit);
		submit_situation.setOnClickListener(this);
		view_situation = (Button)findViewById(R.id.view);
		view_situation.setOnClickListener(this);
		student_situation = (RadioGroup)findViewById(R.id.attendance);
		now_situation = (RadioButton)findViewById(R.id.chu);
		view_student = (Button)findViewById(R.id.view_student);
		view_student.setOnClickListener(this);
		
		this.resources = this.getResources(); 
		
		db = dbHelper.getWritableDatabase();
		cursor = db.query("student", null, null, null, null, null, null);
		allid = cursor.getCount();
		if(allid>0){
			nowid = 1;
		}else{
			nowid = 0;
		}
		student_now.setText(String.valueOf(nowid));
		String all_num = String.valueOf(allid);//��ȡ������������
		student_all.setText("/"+all_num);
		if(cursor.moveToFirst()){
			//do{
				name = cursor.getString(cursor.getColumnIndex("name"));
			    number = cursor.getString(cursor.getColumnIndex("number"));
			    
				Log.d("MainActivity", "student name is " + name); 
				Log.d("MainActivity", "student number is " + number); 
				
				student_number.setText(number);
				student_name.setText(name);
				
				
				
			//}while(cursor.moveToNext());
			
		}
		//cursor.close();
		
		student_situation.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				//��ȡ������ѡ�����ID 
				int radioButtonId = group.getCheckedRadioButtonId(); 
				//����ID��ȡRadioButton��ʵ�� 
				now_situation = (RadioButton)MainActivity.this.findViewById(radioButtonId); 
				Toast.makeText(getApplicationContext(), name+":"+now_situation.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.create:
			Intent intent = new Intent(MainActivity.this, ImportStudent.class);
            startActivity(intent);
			break;
		case R.id.next:
			if(cursor.moveToNext()){
				name = cursor.getString(cursor.getColumnIndex("name"));
			    number = cursor.getString(cursor.getColumnIndex("number"));
			    
				Log.d("MainActivity", "student name is " + name); 
				Log.d("MainActivity", "student number is " + number); 
				
				student_number.setText(number);
				student_name.setText(name);
				nowid++;
				if(nowid>=allid){
					next_student.setEnabled(false);
					previous_student.setEnabled(true);
					last_student.setEnabled(false);
					first_student.setEnabled(true);
				}else{
					next_student.setEnabled(true);
					previous_student.setEnabled(true);
					last_student.setEnabled(true);
					first_student.setEnabled(true);
				}
				student_now.setText(String.valueOf(nowid));
			}
			break;
		case R.id.previous:
					if(cursor.moveToPrevious()){
						name = cursor.getString(cursor.getColumnIndex("name"));
					    number = cursor.getString(cursor.getColumnIndex("number"));
					    
						Log.d("MainActivity", "student name is " + name); 
						Log.d("MainActivity", "student number is " + number); 
						
						student_number.setText(number);
						student_name.setText(name);
						nowid--;
						if(nowid<=1){
							previous_student.setEnabled(false);
							next_student.setEnabled(true);
							first_student.setEnabled(false);
							last_student.setEnabled(true);
						}else{
							last_student.setEnabled(true);
							previous_student.setEnabled(true);
							next_student.setEnabled(true);
							first_student.setEnabled(true);
						}
						student_now.setText(String.valueOf(nowid));
						
					}
			break;
		case R.id.first:
			if(cursor.moveToFirst()){
				name = cursor.getString(cursor.getColumnIndex("name"));
			    number = cursor.getString(cursor.getColumnIndex("number"));
			    
				Log.d("MainActivity", "student name is " + name); 
				Log.d("MainActivity", "student number is " + number); 
				
				student_number.setText(number);
				student_name.setText(name);
				nowid=1;
				if(nowid<=1){
					previous_student.setEnabled(false);
					next_student.setEnabled(true);
					first_student.setEnabled(false);
					last_student.setEnabled(true);
				}else{
					last_student.setEnabled(true);
					previous_student.setEnabled(true);
					next_student.setEnabled(true);
					first_student.setEnabled(true);
				}
				student_now.setText(String.valueOf(nowid));
			}
			break;
		case R.id.last:
			if(cursor.moveToLast()){
				name = cursor.getString(cursor.getColumnIndex("name"));
			    number = cursor.getString(cursor.getColumnIndex("number"));
			    
				Log.d("MainActivity", "student name is " + name); 
				Log.d("MainActivity", "student number is " + number); 
				
				student_number.setText(number);
				student_name.setText(name);
				nowid=cursor.getCount();
				if(nowid>=allid){
					next_student.setEnabled(false);
					previous_student.setEnabled(true);
					last_student.setEnabled(false);
					first_student.setEnabled(true);
				}else{
					next_student.setEnabled(true);
					previous_student.setEnabled(true);
					last_student.setEnabled(true);
					first_student.setEnabled(true);
				}
				student_now.setText(String.valueOf(nowid));
			}
			break;
			
		case R.id.view:
			Intent intent2 = new Intent("net.huangang.naming.ACTION_START");
            startActivity(intent2);
			break;
		case R.id.submit:
			situation = (String) now_situation.getText();
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("student_number", number );
			values.put("student_name",  name);
			values.put("student_situation",  situation);
			if(db.insert("situation", null, values) != -1){
				Toast.makeText(getApplicationContext(), "�ύѧ������ɹ�", Toast.LENGTH_SHORT).show();
				values.clear();
			}
			break;
		case R.id.view_student:
			Intent intent3 = new Intent(MainActivity.this, AllStudent.class);
            startActivity(intent3);
			break;
		default:
			break;
		}
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		cursor.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 
		//��Ӳ˵���
	    MenuItem imp=menu.add(0,0,0,"����");
	    imp.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
//				Toast.makeText(getApplicationContext(), "����", Toast.LENGTH_SHORT).show();
				//showDialog(openfileDialogId);
		
				XmlPullParser parser = resources.getXml(R.xml.student);  
				try { 
					while (parser.next() != XmlPullParser.END_DOCUMENT) {     
						String people = parser.getName(); 
			            String name = null; 
			            String numberID = null; 
			            if ((people != null) && people.equals("person")) { 
			            	int count = parser.getAttributeCount();        
			            	for (int i = 0; i < count; i++) { 
			            		String attrName = parser.getAttributeName(i);    
			            		String attrValue = parser.getAttributeValue(i);  
			                    if ((attrName != null) && attrName.equals("name")) { 
			                    	name = attrValue; 
			                    } else if ((attrName != null) && attrName.equals("number")) { 
			                    	numberID  = attrValue; 
			                    } 
			                 } 
			            	if ((name != null) && (numberID  != null)) { 
//			                	Log.d("����", name);
//			                	Log.d("ѧ��", numberID);
//			                	ContentValues values = new ContentValues();
//			    				values.put("number", numberID );
//			    				values.put("name",  name);
//			    				db.insert("student", null, values);
			            		addStudent(name,numberID);
			                } 
			             } 
			            
			        } 
				} catch (Exception e) { 
			        Log.e("name", e.getMessage(), e); 
			    } 
				
				Toast.makeText(getApplicationContext(), "����ѧ��", Toast.LENGTH_LONG).show();
//				Intent intent = new Intent(MainActivity.this, MainActivity.class);
//		        startActivity(intent);
//				
				return true;
			}
		});
        MenuItem exp=menu.add(0,0,0,"����");
        exp.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Toast.makeText(getApplicationContext(), "����", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
  
        //�󶨵�ActionBar  
        imp.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        exp.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    
        return true;
		
	}
	
	
	
	public void addStudent(String name,String number){
		
		ContentValues values = new ContentValues();
		cursor = db.query("student", new  String[] { "name" }, "number=?", new  String[]{ number }, null, null, null);
		if(cursor.moveToLast()){
			
			Log.d("student is exist", name);
			
		}else{
			
			Log.d("student is not exist", name);
			values.put("number", number );
			values.put("name",  name);
			db.insert("student", null, values);
		}

		
		
	}
	

	
}
