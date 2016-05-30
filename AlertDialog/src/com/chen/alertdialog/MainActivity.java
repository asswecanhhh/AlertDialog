package com.chen.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private int index;
	private ListView lv;
	private String[] provinces = {"GuangZhou","Hangzhou","Beijing","Shanghai","ShenZhen","TianJin"};
	private OnClickCheck mOnClickCheck;
	
	private Context context = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
       
    }
    void init(){
    	 mOnClickCheck = new OnClickCheck();
    	 btn1 = (Button) findViewById(R.id.button1);
         btn2 = (Button) findViewById(R.id.button2);
         btn3 = (Button) findViewById(R.id.button3);
         btn4 = (Button) findViewById(R.id.button4);
         btn5 = (Button) findViewById(R.id.button5);
         btn6 = (Button) findViewById(R.id.button6);
         btn1.setOnClickListener(mOnClickCheck);
         btn2.setOnClickListener(mOnClickCheck);
         btn3.setOnClickListener(mOnClickCheck);
         btn4.setOnClickListener(mOnClickCheck);
         btn5.setOnClickListener(mOnClickCheck);
         btn6.setOnClickListener(mOnClickCheck);
    }
    
    public class OnClickCheck implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button1:
				new AlertDialog.Builder(context).setTitle("标题").setMessage("消息").setNegativeButton("关闭", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "响应事件", Toast.LENGTH_SHORT).show();
					}
				}).show();
				break;
			
			case R.id.button2:
				new AlertDialog.Builder(context)
				.setTitle("标题")
				.setMessage("消息")
				.setNeutralButton("忽略", null)
				.setPositiveButton("确认", null).show();
				break;
			
			case R.id.button3:
				new AlertDialog.Builder(context)
				.setTitle("标题")
				.setMessage("消息")
				.setNegativeButton("取消", null)
				.setNeutralButton("忽略", null)
				.setPositiveButton("确认", null).show();
				break;
				
			case R.id.button4:
				new AlertDialog.Builder(context)
				.setTitle("城市选择")
				.setItems(provinces, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						new AlertDialog.Builder(context)
						.setTitle("提示")
						.setMessage("你已经选择了：" + which + ":" + provinces[which]).show();
					}
				}).show();
				break;
				
			case R.id.button5:
				new AlertDialog.Builder(context)
				.setTitle("城市选择")
				.setSingleChoiceItems(provinces, 0, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						index = which;
					}
				})
				.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						new AlertDialog.Builder(context)
						.setMessage("你选择了" + index + ":" + provinces[index]).show();
					}
				}).show();
				break;
				
			case R.id.button6:
				AlertDialog mAlertDialog = new AlertDialog.Builder(context)
				.setIcon(R.drawable.ic_launcher)
				.setTitle("选择城市")
				.setMultiChoiceItems(provinces, null, new DialogInterface.OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						// TODO Auto-generated method stub
					}
				})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						int count = lv.getCount();
						String s = "你选择了:";
						for(int i = 0; i < provinces.length; i++){
							if(lv.getCheckedItemPositions().get(i)){
								s += i + ":" + lv.getAdapter().getItem(i) + " ";
							}
						}
						if(lv.getCheckedItemPositions().size() > 0){
							new AlertDialog.Builder(context)
							.setMessage(s).show();
						}
						else {
							new AlertDialog.Builder(context)
							.setMessage("你还未选择").show();
						}
					}
				})
				.setNegativeButton("取消", null).create();
				lv = mAlertDialog.getListView();
				mAlertDialog.show();
				break;
				
			default:
				break;
			}
		}
    	
    }
}
