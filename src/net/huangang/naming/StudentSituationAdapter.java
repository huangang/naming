package net.huangang.naming;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StudentSituationAdapter extends ArrayAdapter<StudentSituation> {
	
	 private int resourceId;
	 
	 public StudentSituationAdapter(Context context, int textViewResourceId,List<StudentSituation> objects) {
		 
         super(context, textViewResourceId, objects);
         resourceId = textViewResourceId;
     }
	 
	 @SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 StudentSituation studentSituation = getItem(position); // 获取当前项的StudentSituation实例
		 //View view = LayoutInflater.from(getContext()).inflate(resourceId, null); 
		 
		 View view;
		 ViewHolder viewHolder;
         if (convertView == null) {
        	 view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        	 viewHolder = new ViewHolder();
             viewHolder.studentSituationName = (TextView) view.findViewById(R.id.situation_student_name);
             viewHolder.studentSituationNumber = (TextView) view.findViewById(R.id.situation_student_number);
             viewHolder.studentSituationSituation = (TextView) view.findViewById(R.id.situation_student_situation);
             viewHolder.studentSituationCreatedTime = (TextView) view.findViewById(R.id.situation_created_time);
             view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
        	view = convertView;
        	viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
         
		 viewHolder.studentSituationName.setText(studentSituation.getStudent_name());
		 viewHolder.studentSituationNumber.setText(studentSituation.getStudent_number());
		 viewHolder.studentSituationSituation.setText(studentSituation.getStudent_situation() );
		 viewHolder.studentSituationCreatedTime.setText(studentSituation.getCreated_time());
		 
		 return view;
	}
	 
	 class ViewHolder {
		 TextView studentSituationName;
		 TextView studentSituationNumber;
		 TextView studentSituationSituation;
		 TextView studentSituationCreatedTime;
		 
	 }
}
