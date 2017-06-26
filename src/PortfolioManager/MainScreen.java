package PortfolioManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
 
public class MainScreen {

	private JFrame frmPortfolioManager;
	private JLabel lblTitle;
	private JPanel panelMenu;
	private JPanel panelPortfolio;
	private JLabel lblMarketInfo;
	private JTable tblHoldings;
	private JTable tblStocks;
	private JScrollPane holdingsScroll;
	private JScrollPane stocksScroll;
	private JScrollPane historyScroll;
	private JSpinner spDolarOficial;
	private JSpinner spDolarBlue;
	private Panel panelConversor;
	private JButton btnOperationHistory;
	private static JLabel lblNews4;
	private static JLabel lblNews3;
	private static JLabel lblNews2;
	private static JLabel lblNews1;
	private JLabel lblNotInvested_V;
	private JLabel lblInvested_V; 
	private JLabel lblOverallReturns_V;
	private JLabel lblOverallGains_V; 
	private JLabel lblNetWorth_V;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmPortfolioManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
		Syst.loadNewsFromInternet();
		
	}
	
	private void ClearScreen(){
		panelPortfolio.setVisible(false);
		holdingsScroll.setVisible(false);
		stocksScroll.setVisible(false);
		panelConversor.setVisible(false);
	}
	
	private void changeModule(String module){
		ClearScreen();
		lblTitle.setText(module);
		if(module == "PORTFOLIO"){
			panelPortfolio.setVisible(true);
			holdingsScroll.setVisible(true);
		}
		else if(module == "ACCIONES"){
			tblStocks.setVisible(true);
			stocksScroll.setVisible(true);
		}
		else if(module == "BONOS"){
			tblStocks.setVisible(true);
			stocksScroll.setVisible(true);
		}
		else if(module == "OPCIONES"){
			tblStocks.setVisible(true);
			stocksScroll.setVisible(true);
		}
		else if(module == "FUTUROS"){
			tblStocks.setVisible(true);
			stocksScroll.setVisible(true);
		}
		else if(module == "COMMODITIES"){
			tblStocks.setVisible(true);
			stocksScroll.setVisible(true);
		}
		else if(module == "CCL"){
			tblStocks.setVisible(true);
			stocksScroll.setVisible(true);
		}
		else if(module == "CONVERSOR"){
			panelConversor.setVisible(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPortfolioManager = new JFrame();
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		Image myIcon = myScreen.getImage("logo.jpg");
		frmPortfolioManager.setIconImage(myIcon);
		frmPortfolioManager.setResizable(false);
		frmPortfolioManager.setTitle("Portfolio Manager");
		frmPortfolioManager.setBounds(100, 100, 949, 616);
		frmPortfolioManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPortfolioManager.getContentPane().setLayout(null);



		Portfolio miPortfolio = new Portfolio();
		miPortfolio.setCash(100000);
		
		panelPortfolio = new JPanel();
		panelPortfolio.setBounds(10, 138, 921, 437);
		frmPortfolioManager.getContentPane().add(panelPortfolio);
		panelPortfolio.setLayout(null);
		
		JLabel lblNetWorth = new JLabel("Net Worth");
		lblNetWorth.setBounds(12, 120, 123, 29);
		panelPortfolio.add(lblNetWorth);
		lblNetWorth.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		lblNetWorth_V = new JLabel("$" +miPortfolio.getNetWorth());
		lblNetWorth_V.setBounds(193, 120, 123, 29);
		panelPortfolio.add(lblNetWorth_V);
		if(miPortfolio.getNetWorth()<0){
			lblNetWorth_V.setForeground(new Color(255, 0, 0));
		}else{
			lblNetWorth_V.setForeground(new Color(0, 128, 0));
		}
		
		lblNetWorth_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		lblOverallGains_V = new JLabel("$"+miPortfolio.getOverallGains());
		lblOverallGains_V.setBounds(193, 159, 123, 29);
		panelPortfolio.add(lblOverallGains_V);
		if(miPortfolio.getOverallGains()<0){
			lblOverallGains_V.setForeground(new Color(255, 0, 0));
		}else{
			lblOverallGains_V.setForeground(new Color(0, 128, 0));
		}		
		lblOverallGains_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblOverallGains = new JLabel("Overall Gains");
		lblOverallGains.setBounds(12, 160, 191, 27);
		panelPortfolio.add(lblOverallGains);
		lblOverallGains.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblOverallReturns = new JLabel("Overall Returns");
		lblOverallReturns.setBounds(12, 198, 191, 39);
		panelPortfolio.add(lblOverallReturns);
		lblOverallReturns.setFont(new Font("Tahoma", Font.PLAIN, 24));
		

		if(miPortfolio.getOverallReturns()==0.0){
			lblOverallReturns_V = new JLabel(miPortfolio.getOverallReturns()+"%");		
		}else{
			lblOverallReturns_V = new JLabel("--");	
		}
		lblOverallReturns_V.setBounds(193, 203, 123, 29);
		panelPortfolio.add(lblOverallReturns_V);
		if(miPortfolio.getOverallReturns()<0){
			lblOverallReturns_V.setForeground(new Color(255, 0, 0));
		}else{
			lblOverallReturns_V.setForeground(new Color(0, 128, 0));
		}
		
		lblOverallReturns_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		lblInvested_V = new JLabel("$"+miPortfolio.getAllAcquiredValues());
		lblInvested_V.setBounds(193, 243, 123, 29);
		panelPortfolio.add(lblInvested_V);
		lblInvested_V.setForeground(new Color(0, 128, 0));
		lblInvested_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblInvested = new JLabel("Invested");
		lblInvested.setBounds(12, 243, 182, 29);
		panelPortfolio.add(lblInvested);
		lblInvested.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblNotInvested = new JLabel("Not Invested");
		lblNotInvested.setBounds(12, 283, 182, 29);
		panelPortfolio.add(lblNotInvested);
		lblNotInvested.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		lblNotInvested_V = new JLabel("$"+ miPortfolio.getCash());
		lblNotInvested_V.setBounds(193, 283, 123, 29);
		panelPortfolio.add(lblNotInvested_V);
		lblNotInvested_V.setForeground(new Color(0, 128, 0));
		lblNotInvested_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		tblHoldings = new JTable();
		tblHoldings.setBounds(323, 33, 270, 392);
		tblHoldings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		HoldingsTableModel holdingsTableModel = new HoldingsTableModel(miPortfolio);
		tblHoldings.setModel(holdingsTableModel);
		tblHoldings.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(event.getValueIsAdjusting() || ((DefaultListSelectionModel)event.getSource()).isSelectionEmpty())
	        		return;
	        	Integer index = tblHoldings.getSelectedRow();
	        	tblHoldings.clearSelection();
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a vender");
	        	Asset currentAsset = (Asset) tblHoldings.getValueAt(index, 0);
	        	boolean isBuyingOperation = false;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        }
	    });
		tblHoldings.getColumnModel().getColumn(0).setResizable(false);
		tblHoldings.getColumnModel().getColumn(1).setResizable(false);
		tblHoldings.getColumnModel().getColumn(2).setResizable(false);
		holdingsScroll = new JScrollPane(tblHoldings);
		holdingsScroll.setBounds(323, 33, 270, 392);
		panelPortfolio.add(holdingsScroll);
		
		Label lblHoldings = new Label("Holdings");
		lblHoldings.setBounds(323, 5, 270, 22);
		panelPortfolio.add(lblHoldings);
		lblHoldings.setAlignment(Label.CENTER);
		lblHoldings.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		Label lblMarketNews = new Label("El mercado, en un vistazo");
		lblMarketNews.setBounds(643, 5, 270, 22);
		panelPortfolio.add(lblMarketNews);
		lblMarketNews.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMarketNews.setAlignment(Label.CENTER);
		
		lblNews1 = new JLabel("<html>El MERVAL pasa a ser catalogado como un mercado emergente por JPMorgan</html>");
		lblNews1.setVerticalAlignment(SwingConstants.TOP);
		lblNews1.setBounds(643, 45, 270, 65);
		panelPortfolio.add(lblNews1);
		lblNews1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNews1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNews2 = new JLabel("<html>El MERVAL pasa a ser catalogado como un mercado emergente por JPMorgan</html>");
		lblNews2.setVerticalAlignment(SwingConstants.TOP);
		lblNews2.setBounds(643, 142, 270, 65);
		panelPortfolio.add(lblNews2);
		lblNews2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNews2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNews3 = new JLabel("<html>El MERVAL pasa a ser catalogado como un mercado emergente por JPMorgan</html>");
		lblNews3.setVerticalAlignment(SwingConstants.TOP);
		lblNews3.setBounds(643, 239, 270, 65);
		panelPortfolio.add(lblNews3);
		lblNews3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNews3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnOperationHistory = new JButton("Ver historial de operaciones");
		btnOperationHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*Clickeo el boton*/
				
				JFrame window = new JFrame();
				Toolkit myScreen2 = Toolkit.getDefaultToolkit();
				Image myIcon2 = myScreen2.getImage("logo.jpg");
				window.setIconImage(myIcon);
	        	window.setResizable(false);
	        	window.setTitle("Historial de Operaciones");
	        	window.setBounds(100, 100, 800, 600);
	        	window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        	window.getContentPane().setLayout(null);
	        	String col[]={"Nombre", "Fecha", "Cant. de Acciones", "Monto gastado", "Tipo de Operacion"};
	        	DefaultTableModel modelHistory = new DefaultTableModel(col, 0);

	        	
	        	for(Operation o: miPortfolio.getHistory()){
	        		
	        		String data[]={o.getAsset().getName(), o.getDate().toString(),  Integer.toString(o.getPurchaseAmount()),"$" + Double.toString(o.getPurchaseValue()*o.getPurchaseAmount()),"  " + (o.isBuyingOperation()?"Compra":"Venta")};
        		
	        		modelHistory.addRow(data);

	        	}
	        	
	        	JTable tblHistory = new JTable(modelHistory);
	    		tblHistory.setPreferredScrollableViewportSize(new Dimension(400, 300));
	    		tblHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    		tblHistory.setFont(new Font("Dialog", Font.PLAIN, 16));
	    		tblHistory.setRowHeight(32);
	    		tblHistory.setVisible(true);
	    		historyScroll = new JScrollPane(tblHistory);
	    		historyScroll.setBounds(10, 10, 700, 700);
	    		historyScroll.setVisible(true);
	    		
	    		window.add(historyScroll);
	    		window.setVisible(true);
	    		tblHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    		tblHistory.setBounds(10, 10, 700, 700);
	    		/*Cierre click boton*/
			}
		});
		btnOperationHistory.setBounds(39, 399, 223, 26);
		panelPortfolio.add(btnOperationHistory);
		
		lblNews4 = new JLabel("<html>El MERVAL pasa a ser catalogado como un mercado emergente por JPMorgan</html>");
		lblNews4.setVerticalAlignment(SwingConstants.TOP);
		lblNews4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNews4.setBounds(643, 337, 270, 65);
		panelPortfolio.add(lblNews4);
		
		JLabel lblNewLabel_1 = new JLabel("Fuente: Cronista");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(771, 409, 142, 16);
		panelPortfolio.add(lblNewLabel_1);
		
		lblTitle = new JLabel("PORTFOLIO");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTitle.setBounds(10, 87, 913, 39);
		frmPortfolioManager.getContentPane().add(lblTitle);
		
		panelMenu = new JPanel();
		panelMenu.setBorder(null);
		panelMenu.setBounds(10, 34, 921, 41);
		frmPortfolioManager.getContentPane().add(panelMenu);
		
		JButton btnMyPortfolio = new JButton("Portfolio");
		btnMyPortfolio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("PORTFOLIO");
			}
		});
		btnMyPortfolio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMyPortfolio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnMyPortfolio);
		btnMyPortfolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnStocks = new JButton("Acciones");
		btnStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("ACCIONES");
			}
		});
		btnStocks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStocks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnStocks);
		
		JButton btnBonos = new JButton("Bonos");
		btnBonos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("BONOS");
			}
		});
		btnBonos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBonos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnBonos);
		
		JButton btnOptions = new JButton("Opciones");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("OPCIONES");
			}
		});
		btnOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOptions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnOptions);
		
		JButton btnFutures = new JButton("Futuros");
		btnFutures.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				changeModule("FUTUROS");
			}
		});
		btnFutures.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFutures.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnFutures);
		
		JButton btnCommodities = new JButton("Commodities");
		btnCommodities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("COMMODITIES");
			}
		});
		btnCommodities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCommodities.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnCommodities);
		
		JButton btnCcl = new JButton("CCL");
		btnCcl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("CCL");
			}
		});
		btnCcl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCcl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnCcl);
		
		JButton btnConversor = new JButton("Conversor");
		btnConversor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeModule("CONVERSOR");
			}
		});
		btnConversor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConversor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelMenu.add(btnConversor);
		btnBonos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		Timer marketInfoTimer = new Timer();
		marketInfoTimer.scheduleAtFixedRate(new TimerTask() {
			int count = 800;
			  @Override
			  public void run() {
				if(count == -975)
				{
					count = 800;
					lblMarketInfo.setLocation(500, 0);
				}
				lblMarketInfo.setLocation(count, 0);
			    count--;
			  }
			}, 10, 10);
		
		lblMarketInfo = new JLabel("REFERENCIAS DE RENDIMIENTO:                LEBAC: 25,2%                CAUCI\u00D3N: 25%                INFLACI\u00D3N ESTIMADA (FMI): 25,6%");
		lblMarketInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarketInfo.setOpaque(true);
		lblMarketInfo.setForeground(Color.WHITE);
		lblMarketInfo.setBackground(Color.BLACK);
		lblMarketInfo.setBounds(0, 0, 943, 22);
		frmPortfolioManager.getContentPane().add(lblMarketInfo);
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setBounds(0, 0, 943, 22);
		frmPortfolioManager.getContentPane().add(label);
		
		tblStocks = new JTable();
		tblStocks.setPreferredScrollableViewportSize(new Dimension(400, 300));
		tblStocks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStocks.setFont(new Font("Dialog", Font.PLAIN, 16));
		tblStocks.setRowHeight(32);

		tblStocks.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {	        	
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a comprar");
	        	int index = tblStocks.getSelectedRow();
	        	Stock currentAsset = (Stock) tblStocks.getValueAt(index, 0);
	        	boolean isBuyingOperation = true;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        	}
	    });

		tblStocks.setVisible(false);
		Syst.updateValuesFromInternet();
		List<Stock> miLista = Syst.getStocks();
		tblStocks.setModel(new StocksTableModel(miLista));
		tblStocks.getColumnModel().getColumn(0).setResizable(false);
		tblStocks.getColumnModel().getColumn(1).setResizable(false);
		tblStocks.getColumnModel().getColumn(1).setPreferredWidth(101);
		/*tblStocks.getColumnModel().getColumn(2).setResizable(false);
		tblStocks.getColumnModel().getColumn(3).setResizable(false);
		tblStocks.getColumnModel().getColumn(3).setPreferredWidth(127);
		tblStocks.getColumnModel().getColumn(4).setResizable(false);
		tblStocks.getColumnModel().getColumn(4).setPreferredWidth(145);*/
		tblStocks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblStocks.setBounds(10, 138, 921, 437);
		stocksScroll = new JScrollPane(tblStocks);
		stocksScroll.setBounds(10, 138, 921, 437);
		stocksScroll.setVisible(false);
		frmPortfolioManager.getContentPane().add(stocksScroll);
		
		panelConversor = new Panel();
		panelConversor.setVisible(false);
		panelConversor.setBounds(10, 581, 921, -6);
		frmPortfolioManager.getContentPane().add(panelConversor);
		panelConversor.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("D\u00F3lar oficial: 15,65");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(199, 166, 183, 16);
		panelConversor.add(lblNewLabel);
		
		JLabel lblDlarBlue = new JLabel("D\u00F3lar blue: 16,20");
		lblDlarBlue.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDlarBlue.setBounds(493, 166, 183, 16);
		panelConversor.add(lblDlarBlue);
		
		spDolarOficial = new JSpinner();
		spDolarOficial.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				spDolarBlue.setValue(spDolarOficial.getValue());
			}
		});
		spDolarOficial.setFont(new Font("Dialog", Font.BOLD, 18));
		spDolarOficial.setBounds(199, 194, 183, 33);
		panelConversor.add(spDolarOficial);
		
		spDolarBlue = new JSpinner();
		spDolarBlue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spDolarOficial.setValue(spDolarBlue.getValue());
			}
		});
		spDolarBlue.setFont(new Font("Dialog", Font.BOLD, 18));
		spDolarBlue.setBounds(493, 194, 183, 33);
		panelConversor.add(spDolarBlue);
	}
	
	public static void setNews(String news1, String news2, String news3, String news4){
		lblNews1.setText("<html>" + news1 + "</html>");
		lblNews2.setText("<html>" + news2 + "</html>");
		lblNews3.setText("<html>" + news3 + "</html>");
		lblNews4.setText("<html>" + news4 + "</html>");
	}
	
	private void refreshLabels(Portfolio miPortfolio){
		
		panelPortfolio.remove(lblNotInvested_V);	        			
		lblNotInvested_V = new JLabel("$"+ miPortfolio.getCash());
		lblNotInvested_V.setBounds(193, 283, 123, 29);
		panelPortfolio.add(lblNotInvested_V);
		lblNotInvested_V.setForeground(new Color(0, 128, 0));
		lblNotInvested_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotInvested_V.setVisible(true);
		
		panelPortfolio.remove(lblInvested_V );
		lblInvested_V = new JLabel("$"+miPortfolio.getAllAcquiredValues());
		lblInvested_V.setBounds(193, 243, 123, 29);
		panelPortfolio.add(lblInvested_V);
		lblInvested_V.setForeground(new Color(0, 128, 0));
		lblInvested_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		panelPortfolio.remove(lblOverallReturns_V);
		if(miPortfolio.getOverallReturns()==0.0) {
			lblOverallReturns_V = new JLabel(miPortfolio.getOverallReturns()+"%");		
		}else {
			lblOverallReturns_V = new JLabel("--");	
		}
		lblOverallReturns_V.setBounds(193, 203, 123, 29);
		panelPortfolio.add(lblOverallReturns_V);
		if(miPortfolio.getOverallReturns()<0) {
			lblOverallReturns_V.setForeground(new Color(255, 0, 0));
		}else {
			lblOverallReturns_V.setForeground(new Color(0, 128, 0));
		}	
		lblOverallReturns_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		panelPortfolio.remove(lblOverallGains_V);
		lblOverallGains_V = new JLabel("$"+miPortfolio.getOverallGains());
		lblOverallGains_V.setBounds(193, 159, 123, 29);
		panelPortfolio.add(lblOverallGains_V);
		
		if(miPortfolio.getOverallGains()<0) {
			lblOverallGains_V.setForeground(new Color(255, 0, 0));
		}else {
			lblOverallGains_V.setForeground(new Color(0, 128, 0));
		}		
		lblOverallGains_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		panelPortfolio.remove(lblNetWorth_V);
		lblNetWorth_V = new JLabel("$" +miPortfolio.getNetWorth());
		lblNetWorth_V.setBounds(193, 120, 123, 29);
		panelPortfolio.add(lblNetWorth_V);
		
		if(miPortfolio.getNetWorth()<0) {
			lblNetWorth_V.setForeground(new Color(255, 0, 0));
		}else {
			lblNetWorth_V.setForeground(new Color(0, 128, 0));
		}      			
		lblNetWorth_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
	}
	
	private void processOperation(Portfolio miPortfolio, HoldingsTableModel holdingsTableModel, boolean isBuyingOperation, Asset currentAsset, String input) {
		if(input != null) {
    		try {
    			int cantidad = Integer.parseInt(input);
    			Operation op = new Operation(isBuyingOperation, currentAsset, new Date(), cantidad);
    			miPortfolio.addOperation(op);
    			if(isBuyingOperation) {
    				holdingsTableModel.addRow(0, 0);
    			}
    			else {
    				holdingsTableModel.updateRow(0, 0);
    			}
    			/*Actualiza los numeros de la pantalla principal*/
    			refreshLabels(miPortfolio);
    			tblHoldings.clearSelection();
    			
    			
    		}
    		catch(NegativeAssetAmountException e) {
    			JOptionPane.showMessageDialog(new JFrame(), 
    					(isBuyingOperation) ? ("No puede comprar una cantidad negativa/nula"
    					+ " de activos.") : ("No posee tantos activos"), "Error", JOptionPane.ERROR_MESSAGE);
    			tblHoldings.clearSelection();
    		}
    		catch(NumberFormatException e) {
    			JOptionPane.showMessageDialog(new JFrame(), "Debe ingresar un número.", "Error", JOptionPane.ERROR_MESSAGE);
    			tblHoldings.clearSelection();
    		}
    		catch(InsufficientFundsException e) {
    			JOptionPane.showMessageDialog(new JFrame(), "¡Fondos insuficientes!", "Advertencia", JOptionPane.WARNING_MESSAGE);
    			tblHoldings.clearSelection();
    		}
		}
	}
}