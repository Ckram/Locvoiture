package Controller;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import Model.Voiture;

public class listModelVoitures extends AbstractListModel<Voiture> implements ComboBoxModel<Voiture>{
	private Voiture itemSelected;
	Voiture [] voitures;

	public listModelVoitures() {
		try {
			voitures = UtilitaireDB.recupererVoitures();
			System.out.println(voitures);
			if (voitures.length>0)
			{
				itemSelected = voitures[0];
			}else
				itemSelected=null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getSize() {
		if (voitures == null)
				return 0;
		return voitures.length;
	}

	public Voiture getElementAt(int index) {
		// TODO Auto-generated method stub
		return voitures[index];
	}

	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	public void setSelectedItem(Object anItem) {
		itemSelected = (Voiture)anItem;
		
		
		
	}

	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return itemSelected;
	}
	
	

}
