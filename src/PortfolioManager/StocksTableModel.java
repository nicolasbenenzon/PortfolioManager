package PortfolioManager;

import java.util.List;

public class StocksTableModel extends AssetTableModel<Stock> {
	 
	private static final long serialVersionUID = -306282306234868972L;
	
	public StocksTableModel(List<Stock> list) {
		super(list);
	}
}

