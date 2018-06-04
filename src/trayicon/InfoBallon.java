package trayicon;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoBallon implements ActionListener
{
	private TrayIcon trayIcon;
	
	public InfoBallon(TrayIcon trayIcon)
	{
		this.trayIcon = trayIcon;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		trayIcon.displayMessage("Info Ballon",
				"Alguma informação", TrayIcon.MessageType.INFO);
	}
}
