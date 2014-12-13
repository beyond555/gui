import java.io.PrintStream;
public class Settings {
	public static double vessel_thresh;
	public static String savePath;
	public static void init()
	{
		vessel_thresh=0.15;
		savePath="/home/ljw/download/matlabWork/optic/";
		PrintStream ps=IO.setOut(Util.settingfile);
		System.out.println(ToString());
		ps.close();
		System.setOut(IO.ps);
	}
	public static void update()
	{
		System.out.println("update");
		PrintStream ps=IO.setOut(Util.settingfile);
		System.out.println(ToString());
		ps.close();
		System.setOut(IO.ps);
	}
	public static String ToString() {
		return String.valueOf(vessel_thresh+" "+savePath);
	}	
}