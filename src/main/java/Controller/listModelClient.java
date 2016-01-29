package Controller;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import Model.Client;
import Model.Voiture;

public class listModelClient extends AbstractListModel<Client> implements ComboBoxModel<Client>{
	private Client itemSelected;
	Client [] clients;

	public listModelClient() {
		try {
			clients = UtilitaireDB.recupererClients();
			if (clients.length>0)
			{
				itemSelected = clients[0];
			}else
				itemSelected=null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getSize() {
		if (clients == null)
				return 0;
		return clients.length;
	}

	public Client getElementAt(int index) {
		// TODO Auto-generated method stub
		return clients[index];
	}

	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	public void setSelectedItem(Object anItem) {
		itemSelected = (Client)anItem;
		
		
		
	}

	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return itemSelected;
	}
	
	

}
