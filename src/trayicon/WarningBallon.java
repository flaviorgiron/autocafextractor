package trayicon;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarningBallon implements ActionListener
{
	private TrayIcon trayIcon;
	
	public WarningBallon(TrayIcon trayIcon)
	{
		this.trayIcon = trayIcon;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		trayIcon.displayMessage("Warning Ballon",
				"Aviso! Aviso!", TrayIcon.MessageType.WARNING);
	}
}