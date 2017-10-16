package PortfolioManager;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class AssetTableModel<T extends Asset> extends AbstractTableModel {
	
	private static final long serialVersionUID = 8727850667225925336L;
	private List<T> assetList;
	
	public AssetTableModel(List<T> list) {
		assetList = list;
	}

	@Override
	public int getRowCount() {
		return assetList.size();
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
		T asset = assetList.get(rowIndex);
		if(columnIndex == 0)
			return (Object) asset;
		else
			return (Object) ("$" + (Math.round(asset.getValue() * 100.0)) / 100.0);
	}

}