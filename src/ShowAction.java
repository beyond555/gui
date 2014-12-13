import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShowAction implements ActionListener{
	String tag;
	ShowAction(String tag)
	{
		this.tag=tag;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(tag);
		if(tag.equals("P1"))
		{
			gui.getInstance().show(Util.path, Util.name);
		}
		else if(tag.equals("P2"))
		{
			
		}
		else if(tag.equals("P3"))
		{
			gui.getInstance().show(Util.anspath, Util.name);
		}
		else if(tag.equals("P4"))
		{
			
		}
	}
}
