import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class ParameterAction implements ActionListener{
	String tag;
	ParameterAction(String tag)
	{
		this.tag=tag;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(tag);
		if(tag.equals("血管像素阈值"))
		{
			vessel_w();
		}
		else if(tag.equals("保存路径"))
		{
			path();
		}
		else if(tag.equals("还原默认设置"))
		{
			Settings.init();
		}
		Settings.update();
	}
	public void vessel_w()
	{
		String t = JOptionPane.showInputDialog("血管阈值,0~1之间的数字"); 
		if(t==null||t.length()==0)
			return ;
		try
		{
			Settings.vessel_thresh=Double.valueOf(t);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入不合法");
		}
	}
	public void path()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = chooser.showOpenDialog(new JPanel());  
	                  //保存文件从这里入手，输出的是文件名
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("你打开的文件是: " +chooser.getSelectedFile().getParent()+"\t"+
	            chooser.getSelectedFile().getName());
	      System.out.println(chooser.getSelectedFile().getParent()+"/"+chooser.getSelectedFile().getName());
	       try {
	    	   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}