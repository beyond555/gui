
public class State {
	public static void runstate()
	{
		gui.getInstance().toolbarbutton[0].setEnabled(true);
		gui.getInstance().toolbarbutton[1].setEnabled(false);
		gui.getInstance().mi[4].setEnabled(true);
		gui.getInstance().mi[5].setEnabled(true);
		gui.getInstance().mi[6].setEnabled(true);
		gui.getInstance().mi[7].setEnabled(true);
	}
	public static void stopstate()
	{
		gui.getInstance().toolbarbutton[0].setEnabled(false);
		gui.getInstance().toolbarbutton[1].setEnabled(true);
		gui.getInstance().mi[4].setEnabled(false);
		gui.getInstance().mi[5].setEnabled(false);
		gui.getInstance().mi[6].setEnabled(false);
		gui.getInstance().mi[7].setEnabled(false);
	}
}
