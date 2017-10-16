package PortfolioManager;

import javax.swing.table.AbstractTableModel;
/**
 * TableModel to be used by a JTable to display the user's current holdings. 
 * 
 * @author Tomas Ferrer
 *
 */
public class HoldingsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6958444462320212349L;
	private Portfolio myPortfolio;
	
	public HoldingsTableModel(Portfolio myPortfolio) {
		this.myPortfolio = myPortfolio;
	}

	@Override
	public int getRowCount() {
		return myPortfolio.getHoldings().keySet().size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}
	 
	@Override
	public String getColumnName(int column) {
		if(column == 0)
			return "Ticker";
		else if(column == 1)
			return "Amount";
		else
			return "MoneyInvested";
	}
	
	public void addRow(int firstRow, int lastRow) {
        this.fireTableRowsInserted(firstRow, lastRow);
    }
	
	public void updateRow(int firstRow, int lastRow) {
		this.fireTableRowsUpdated(firstRow, lastRow);
		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Asset assetRequested = null;
		for(Asset asset : myPortfolio.getHoldings().keySet()) {
			if(rowIndex == 0) {
				assetRequested = asset;
				break;
			}
			rowIndex--;
		}
		if(columnIndex == 0) {
			return (Object) assetRequested;
		}
		else if(columnIndex == 1) {
			return (Object) myPortfolio.getHoldings().get(assetRequested).getAssetAmount();		
		}
		else {
			Double aux = myPortfolio.getHoldings().get(assetRequested).getMoneyInvested();
			return (Object) ("$" + (Math.round(aux * 100.0)) / 100);
		}
	}

	

}
