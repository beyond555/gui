import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.FutureTask;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Listen {
	public static ActionListener close=new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);			
		}
	};
	public static ActionListener filechoose=new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG , GIF , png Images", "jpg", "gif"," png ");
		    //设置文件类型
		    chooser.setFileFilter(filter);
		    //打开选择器面板
		    int returnVal = chooser.showOpenDialog(new JPanel());  
		                  //保存文件从这里入手，输出的是文件名
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("你打开的文件是: " +chooser.getSelectedFile().getParent()+"\t"+
		            chooser.getSelectedFile().getName());
		       Util.path=chooser.getSelectedFile().getParent()+"/";
		       Util.name=chooser.getSelectedFile().getName();
		       try {
					gui.getInstance().show(Util.path,Util.name);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		}
		
	};
	public static ActionListener run=new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("run");
			Call call1=new Call("run");
			Call call2=new Call("status");
			State.stopstate();
			gui.getInstance().status.setText("Run");
			FutureTask<Integer> future2 = new FutureTask<Integer>(call2);  
			Util.run=new Thread(future2);
			Util.run.start();
			FutureTask<Integer> future1 = new FutureTask<Integer>(call1);  
			new Thread(future1).start();
			
		}
	};
	public static ActionListener stop=new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("stop");
			if(Util.run!=null)
			{
				Util.run.interrupt();
				if(Util.rt!=null)
				{
					Util.proc.destroy();
				}
				System.gc();
				gui.getInstance().status.setText("terminate");				
			}
			Util.rt=null;
			Util.run=null;
			State.runstate();
		}
	};
}
