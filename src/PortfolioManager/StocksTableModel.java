package PortfolioManager;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StocksTableModel extends AbstractTableModel {
	 
	private List<Stock> stockList;
	
	public StocksTableModel(List<Stock> list) {
		stockList = list;
	}

	@Override
	public int getRowCount() {
		return stockList.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int column) {
		if(column == 0)
			return "Ticker";
		else
			return "Price";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Stock stock = stockList.get(rowIndex);
		if(columnIndex == 0)
			return (Object) stock;
		else
			return (Object) ("$" + stock.getValue());
	}

}
