package trayicon;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 *
 * @author Fabio
 * @since 04/12/2008
 * @version 0.1
 */
public class TyBallon{

    private TrayIcon trayIcon;
    private Image image;
    private PopupMenu menuPopup;
    private boolean usePopMenu = false;

	public TrayIcon create(String titleTrayIcon)
	{
		if (SystemTray.isSupported()){
			trayIcon = new TrayIcon(this.image, titleTrayIcon);
			trayIcon.setImageAutoSize(true);
			
            if(this.isUsePopMenu()){
                createPopupMenu();
                trayIcon.setPopupMenu(this.menuPopup);
            }

            return trayIcon;
		} else {
			System.err.println("System tray não e suportado pelo SO.");
            return null;
		}
	}

    public boolean isUsePopMenu(){
        return this.usePopMenu;
    }

    public void setUsePopMenu(boolean flag){
        this.usePopMenu = flag;
    }

	public void setImageIcon(String path, String description){
		URL imageURL = this.getClass().getResource(path);
		if (imageURL == null){
			System.err.println("Imagem nao encontrada: " + path);
		}else{
			this.image =(Image) new ImageIcon(imageURL, description).getImage();
		}
	}

    public void createPopupMenu(){
       //argumento PopupMenu pop
        //this.menuPopup = pop;

        //Este Código deve ser inserido de fora dessa classe
        //por exemplo uma classe que cria um PopupMenu
        PopupMenu popup = new PopupMenu();

			MenuItem infoBalaoItem = new MenuItem("Balão de informação");
			infoBalaoItem.addActionListener(new InfoBallon(trayIcon));

			MenuItem errorBalaoItem = new MenuItem("Balão de erro");
			errorBalaoItem.addActionListener(new ErrorBallon(trayIcon));

			MenuItem warningBalaoItem = new MenuItem("Balão de aviso");
			warningBalaoItem.addActionListener(new WarningBallon(trayIcon));

			MenuItem simpleBalaoItem = new MenuItem("Balão simples");
			simpleBalaoItem.addActionListener(new SimpleBallon(trayIcon));

			MenuItem sairItem = new MenuItem("Sair");
			sairItem.addActionListener(new ExitListener());

			popup.add(infoBalaoItem);
			popup.add(errorBalaoItem);
			popup.add(warningBalaoItem);
			popup.add(simpleBalaoItem);
			popup.add(sairItem);

            this.menuPopup = popup;
    }
}
