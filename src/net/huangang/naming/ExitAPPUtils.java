package net.huangang.naming;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;


public class ExitAPPUtils extends Application {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static ExitAPPUtils instance;
  
    private ExitAPPUtils() {
    }
  
    // ����ģʽ�л�ȡΨһ��ExitAPPUtilsʵ��
    public static ExitAPPUtils getInstance() {
        if(null == instance) {
            instance =new ExitAPPUtils();
        }
        return instance;
    }
  
    // ���Activity��������
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
  
    // ��������Activity��finish
  
    public void exit() {
        for(Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}