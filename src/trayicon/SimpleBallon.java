package trayicon;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleBallon implements ActionListener
{
	private TrayIcon trayIcon;
	
	public SimpleBallon(TrayIcon trayIcon)
	{
		this.trayIcon = trayIcon;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		trayIcon.displayMessage("Simple Ballon",
				"Alguma informa��o", TrayIcon.MessageType.NONE);
	}
}