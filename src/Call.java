import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class Call implements Callable {
    private String name;

    public Call(String name) {
        this.name = name;
    } 
	@Override
	public Object call() throws Exception {
		if(name.equals("status"))
		{
			status();
		}
		else if(name.equals("run"))
		{
			run();
		}
		return null;
	}
	public boolean judge()
	{
		return Util.rt!=null;
	}
	public void status() throws InterruptedException
	{
		Thread.sleep(1000);
		while(judge())
		{
			gui.getInstance().status.setText("wating\t");
			Thread.sleep(1000);
			if(judge())
			{
				gui.getInstance().status.setText("wating\t.");
				Thread.sleep(1000);
			}
			if(judge())
			{
				gui.getInstance().status.setText("wating\t.  .");
				Thread.sleep(1000);
			}
			if(judge())
			{
				gui.getInstance().status.setText("wating\t.  .  .");
				Thread.sleep(1000);
			}
		}
		gui.getInstance().status.setText("finish");
		gui.getInstance().show(Util.anspath, Util.name);
	}
	public void run() throws Exception
	{
		if(Util.rt==null)
		{
			System.out.println("rt");
			String command="/home/ljw/desktop/matlab2012a/bin/matlab -nodesktop -nosplash  -r \"eachFile('"+Util.path+"','"+Util.name+"');quit;\";";
			try {
				Util.rt=Runtime.getRuntime();
				Util.proc=Util.rt.exec(new String[] {"/bin/sh","-c",command},null,null);//用String[]这种形式才能进入matlab执行matlab程序
				
				System.out.println(command);
				InputStream in= Util.proc.getInputStream();
				 BufferedReader input = new BufferedReader(new InputStreamReader(Util.proc.getInputStream()));  
				 String line = "";
				 while ((line = input.readLine()) != null) {  
		                System.out.println(line);
	                }
	            in.close();
	            Util.proc.waitFor();
				System.out.println("end");
			}

			catch (InterruptedException e) {
				System.out.println("InterruptedException!!!");
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}			
		}
		Util.rt=null;
	}
}