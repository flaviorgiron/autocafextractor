package trayicon;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorBallon implements ActionListener
{
	private TrayIcon trayIcon;
	
	public ErrorBallon(TrayIcon trayIcon)
	{
		this.trayIcon = trayIcon;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		trayIcon.displayMessage("Error Ballon",
				"Mensagem de erro", TrayIcon.MessageType.ERROR);
	}
}