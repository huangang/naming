package net.huangang.naming;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	/*
	 *学生名单 
	 * */
	public static final String CREATE_STUDENT ="create table student("+
	"id integer primary key autoincrement,"+
	"name text,"+
	"number integer)";
	/*
	 * 点名情况
	 * */
   public static final String CREATE_SITUATION = "create table situation("+
   "id integer primary key autoincrement,"+
   "student_name text,"+
   "student_number integer,"+
   "student_situation text,"+
   "created_time TimeStamp NOT NULL DEFAULT (datetime('now','localtime')))";

	private Context mContext;
	
	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_STUDENT);
		db.execSQL(CREATE_SITUATION);
		Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists student");
		db.execSQL("drop table if exists situation");
		onCreate(db);

	}

}
