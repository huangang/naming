package net.huangang.naming;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StudentAdapter extends ArrayAdapter<Student> {
	
	private int resourceId;
	
	public StudentAdapter(Context context, int textViewResourceId,
            List<Student> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Student student = getItem(position); // ��ȡ��ǰ���Studentʵ��
		View view;
		ViewHolder viewHolder;
        if (convertView == null) {
        	 view = LayoutInflater.from(getContext()).inflate(resourceId, null); 
        	 viewHolder = new ViewHolder();
             viewHolder.studentNumber = (TextView) view.findViewById(R.id.student_number);
             viewHolder.studentName = (TextView) view.findViewById(R.id.student_name);
             view.setTag(viewHolder); // ��ViewHolder�洢��View��
        }else {
        	
        	view = convertView;
        	viewHolder = (ViewHolder) view.getTag(); // ���»�ȡViewHolder
        }
		
        viewHolder.studentNumber.setText(student.getNumber()); 
        viewHolder.studentName.setText(student.getName());
		return view;

	}
	
	class ViewHolder {
		TextView studentNumber;
        TextView studentName;
    }

}
