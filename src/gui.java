import java.awt.*;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;


public class gui
{
	private static gui uniqueInstance = null;  
	public MenuBar mb;
	public JToolBar tb;
	public JLabel status;
	public JList info;
	public JLabel imageLabel;
	public JPanel jp;
	public Menu m1;
	public Menu m2;
	public Menu m3;
	public BorderLayout bl;
	public Frame frame;
	public MenuItem mi[]=new MenuItem[9];
	public ImageIcon toolbaricon[]=new ImageIcon[2];
	public JButton toolbarbutton[]=new JButton[2];
	public ImageIcon mainImage;
	public String statusString="status";
	gui() 
	{
		uniqueInstance=this;
		Settings.init();
		generate();
		bind();
		show(Util.path,Util.name);
		initListener();
	}
	public static gui getInstance()  {  
       if (uniqueInstance == null) {  
           uniqueInstance = new gui();  
       }
       return uniqueInstance;  
    }
	void initListener()
	{
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	void bind(MenuItem mi,ActionListener listener)
	{
		mi.addActionListener(listener);
	}
	void bind(JButton mi,ActionListener listener)
	{
		mi.addActionListener(listener);
	}
	void menu()
	{
		m1=new Menu("文件");
	    m2=new Menu("参数");
	    m3=new Menu("图像");
		mi[0]=new MenuItem("打开");
	    mi[1]=new MenuItem("退出");
	    mi[2]=new MenuItem("血管像素阈值");
	    mi[3]=new MenuItem("保存路径");
	    mi[8]=new MenuItem("还原默认设置");
	    mi[4]=new MenuItem("P1");
	    mi[5]=new MenuItem("P2");
	    mi[6]=new MenuItem("P3");
	    mi[7]=new MenuItem("P4");
	    
	    m1.add(mi[0]);
	    m1.add(mi[1]);
	    m2.add(mi[2]);
	    m2.add(mi[3]);
	    m2.add(mi[8]);
	    m3.add(mi[4]);
	    m3.add(mi[5]);
	    m3.add(mi[6]);
	    m3.add(mi[7]);
	    
	    mb.add(m1);
		mb.add(m2);
		mb.add(m3);
	}
	void generate()
	{
		frame=new Frame("eye");
	    frame.setSize(600,600);
	    frame.setVisible(true);
		bl=new BorderLayout();
	    frame.setLayout(bl);
		mb=new MenuBar();
	    tb=new JToolBar(JToolBar.HORIZONTAL);
	    menu();
	    tb.setFloatable(false);
	    
	    status=new JLabel();
	    status.setText(statusString);
	    String[] data = {"one", "two", "three", "four"};
	    info=new JList(data);
	    toolbaricon[0]=new ImageIcon("/home/ljw/workspace/gui/src/image/help_gs.png");
	    toolbaricon[1]=new ImageIcon("/home/ljw/workspace/gui/src/image/Stop.png");
		toolbarbutton[0]=new JButton(toolbaricon[0]);
		toolbarbutton[1]=new JButton(toolbaricon[1]);
		State.runstate();
		
		tb.add(toolbarbutton[0]);
		tb.add(toolbarbutton[1]);
		
		mainImage=new ImageIcon();
		imageLabel = new JLabel(mainImage);
	    imageLabel.setBounds(0, 0, mainImage.getIconWidth(), mainImage.getIconHeight()); //标签填满整个面板	    
	    frame.setMenuBar(mb);
	    frame.add(tb,BorderLayout.NORTH);   
	    frame.add(status,BorderLayout.SOUTH);
	    frame.add(imageLabel,BorderLayout.WEST);
	    frame.add(info,BorderLayout.EAST);
	}
	void bind()
	{
	    bind(mi[1],Listen.close);
	    bind(mi[0],Listen.filechoose);
	    bind(mi[2],new ParameterAction(mi[2].getLabel()));
	    bind(mi[3],new ParameterAction(mi[3].getLabel()));
	    bind(mi[8],new ParameterAction(mi[8].getLabel()));
	    bind(mi[4],new ShowAction(mi[4].getLabel()));
	    bind(mi[5],new ShowAction(mi[5].getLabel()));
	    bind(mi[6],new ShowAction(mi[6].getLabel()));
	    bind(mi[7],new ShowAction(mi[7].getLabel()));
	    bind(toolbarbutton[0],Listen.run);
	    bind(toolbarbutton[1],Listen.stop);
	}
	// [a1 a2]=textread('/home/ljw/workspace/gui/settings','%f%s')
	void show(String path,String name) 
	{
		statusString=path+name;
		status.setText(statusString);
		if(! new File(statusString).exists())
		{
			JOptionPane.showMessageDialog(null, statusString+"不存在");
			return ;
		}
		mainImage.setImage(new ImageIcon(path+name).getImage());
	//	imageLabel.setBounds(100, 0, 600, 600);
		System.out.println(imageLabel.getHeight()+" "+imageLabel.getWidth());
		mainImage.setImage(mainImage.getImage().getScaledInstance(imageLabel.getHeight(),imageLabel.getWidth(),Image.SCALE_DEFAULT)); 	    
		imageLabel.validate();
		frame.pack();//fresh to show the image
	}
}