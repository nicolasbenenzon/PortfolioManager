package PortfolioManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListSelectionModel;
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

import org.json.JSONException;

/**
 * 
 * @author Nicolas Benenzon, Tomas Ferrer and Alejandro Santoflaminio
 *
 */
 
public class MainScreen {

	private JFrame frmPortfolioManager;
	private JLabel lblTitle;
	private JPanel panelMenu;
	private JPanel panelPortfolio;
	private JLabel lblMarketInfo;
	private JTable tblStocks;
	private JScrollPane holdingsScroll;
	private JTable tblHoldings;
	private JScrollPane stocksScroll;
	private JTable tblBonos;
	private JScrollPane bonosScroll;
	private JTable tblCommodities;
	private JScrollPane commoditiesScroll;
	private JTable tblFuturos;
	private JScrollPane futurosScroll;
	private JTable tblOpciones;
	private JScrollPane opcionesScroll;
	private JScrollPane historyScroll;
	private JSpinner spDolaresDolarOficial;
	private JSpinner spDolaresDolarBlue;
	private Panel panelConversor;
	private JButton btnOperationHistory;
	private static JLabel lblNews4;
	private static JLabel lblNews3;
	private static JLabel lblNews2;
	private static JLabel lblNews1;
	private JPanel panelCCL;
	private JLabel lblNewLabel_2;
	private JLabel lblPrecio;
	private JLabel lblPrecious;
	private static JLabel lblTickers1;
	private static JLabel lblPesosPrices;
	private static JLabel lblDollarsPrices;
	private JLabel lblFuenteYahooFinance;
	private JLabel lblOperacinptimaCcl;
	private static JLabel lblBestCCL;
	private JLabel lblOperacinptimaCcli;
	private static JLabel lblBestCCLI;
	private static JLabel lblDolarCCL;
	private JLabel lblDlarCcl;
	private JLabel lblMrgenDlarBlue;
	private static JLabel lblMargenDolarBlue;
	private JLabel lblMrgenDolarOficial;
	private static JLabel lblMargenDolarOficial;
	private JLabel lblNetWorth_V;
	private JLabel lblOverallGains_V;
	private JLabel lblOverallGains;
	private JLabel lblOverallReturns;
	private JLabel lblOverallReturns_V;
	private JLabel lblInvested_V;
	private JLabel lblNotInvested_V;
	private static JLabel lblDolarOficial;
	private static JLabel lblDolarBlue;
	private JLabel lblPesos;
	private JSpinner spPesosDolarOficial;
	private JLabel lblDlares;
	private JLabel lblDlares_1;
	private JSpinner spPesosDolarBlue;
	private JLabel lblPesos_1;
	private JLabel lblFuentembito;

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
		try {
			CCL.updateCCL();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Syst.getDollarsFromAmbito();
		
	}
	
	private void ClearScreen(){
		panelPortfolio.setVisible(false);
		stocksScroll.setVisible(false);
		panelConversor.setVisible(false);
		panelCCL.setVisible(false);
		holdingsScroll.setVisible(false);
		bonosScroll.setVisible(false);
		commoditiesScroll.setVisible(false);
		futurosScroll.setVisible(false);
		opcionesScroll.setVisible(false);
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
			tblBonos.setVisible(true);
			bonosScroll.setVisible(true);
		}
		else if(module == "OPCIONES"){
			tblOpciones.setVisible(true);
			opcionesScroll.setVisible(true);
		}
		else if(module == "FUTUROS"){
			tblFuturos.setVisible(true);
			futurosScroll.setVisible(true);
		}
		else if(module == "COMMODITIES"){
			tblCommodities.setVisible(true);
			commoditiesScroll.setVisible(true);
		}
		else if(module == "CCL"){
			panelCCL.setVisible(true);
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
		frmPortfolioManager.setResizable(false);
		frmPortfolioManager.setTitle("Portfolio Manager");
		frmPortfolioManager.setBounds(100, 100, 949, 616);
		frmPortfolioManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPortfolioManager.getContentPane().setLayout(null);
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		java.awt.Image myIcon = myScreen.getImage("logo.jpg");
		frmPortfolioManager.setIconImage(myIcon);
		
		Portfolio miPortfolio = new Portfolio();
		
		
		panelPortfolio = new JPanel();
		panelPortfolio.setBounds(12, 137, 919, 438);
		frmPortfolioManager.getContentPane().add(panelPortfolio);
		panelPortfolio.setLayout(null);
		HoldingsTableModel holdingsTableModel = new HoldingsTableModel(miPortfolio);
		tblHoldings = new JTable();
		tblHoldings.setBounds(323, 33, 270, 392);
		tblHoldings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
				
				JLabel lblNetWorth = new JLabel("Net Worth");
				lblNetWorth.setBounds(12, 120, 123, 29);
				panelPortfolio.add(lblNetWorth);
				lblNetWorth.setFont(new Font("Tahoma", Font.PLAIN, 24));
				
				lblNetWorth_V = new JLabel("$" +miPortfolio.getNetWorth());
				lblNetWorth_V.setBounds(193, 120, 123, 29);
				lblNetWorth_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
				panelPortfolio.add(lblNetWorth_V);
				
				lblOverallGains = new JLabel("Overall Gains");
				lblOverallGains.setBounds(12, 160, 191, 27);
				panelPortfolio.add(lblOverallGains);
				lblOverallGains.setFont(new Font("Tahoma", Font.PLAIN, 24));
				
				lblOverallGains_V = new JLabel("$"+miPortfolio.getOverallGains());
				lblOverallGains_V.setBounds(193, 159, 123, 29);
				panelPortfolio.add(lblOverallGains_V);
				lblOverallGains_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
				
				lblOverallReturns = new JLabel("Overall Returns");
				lblOverallReturns.setBounds(12, 198, 191, 39);
				lblOverallReturns.setFont(new Font("Tahoma", Font.PLAIN, 24));
				panelPortfolio.add(lblOverallReturns);
				
				lblOverallReturns_V = new JLabel("$"+miPortfolio.getOverallReturns());
				lblOverallReturns_V.setBounds(193, 197, 123, 41);
				panelPortfolio.add(lblOverallReturns_V);
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
				lblNotInvested_V.setForeground(new Color(0, 128, 0));
				lblNotInvested_V.setFont(new Font("Tahoma", Font.PLAIN, 24));
				panelPortfolio.add(lblNotInvested_V);
				
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
		lblNews1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNews1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNews2 = new JLabel("<html>El MERVAL pasa a ser catalogado como un mercado emergente por JPMorgan</html>");
		lblNews2.setVerticalAlignment(SwingConstants.TOP);
		lblNews2.setBounds(643, 142, 270, 65);
		panelPortfolio.add(lblNews2);
		lblNews2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNews2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNews3 = new JLabel("<html>El MERVAL pasa a ser catalogado como un mercado emergente por JPMorgan</html>");
		lblNews3.setVerticalAlignment(SwingConstants.TOP);
		lblNews3.setBounds(643, 239, 270, 65);
		panelPortfolio.add(lblNews3);
		lblNews3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNews3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnOperationHistory = new JButton("Ver historial de operaciones");
		btnOperationHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOperationHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*Clickeo el boton*/
				
				ArrayList<Operation> operationList = miPortfolio.readOperationListFromFile();
				if(operationList == null){
					JOptionPane.showMessageDialog(new JFrame(), "No has realizado operaciones.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				JFrame window = new JFrame();
				Toolkit myScreen2 = Toolkit.getDefaultToolkit();
				java.awt.Image myIcon2 = myScreen2.getImage("logo.jpg");
				window.setIconImage(myIcon);
	        	window.setResizable(false);
	        	window.setTitle("Historial de Operaciones");
	        	window.setBounds(100, 100, 800, 600);
	        	window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        	window.getContentPane().setLayout(null);
	        	String col[]={"Nombre", "Fecha", "Cant. de Acciones", "Monto gastado", "Tipo de Operacion"};
	        	DefaultTableModel modelHistory = new DefaultTableModel(col, 0);
	        	
	        	
	        	for(Operation o : operationList){
	        		
	        		String data[]={o.getAsset().getTicker(), o.getDate().toString(),  Integer.toString(o.getPurchaseAmount()),"$" + Double.toString(o.getPurchaseValue()*o.getPurchaseAmount()),"  " + (o.isBuyingOperation()?"Compra":"Venta")};
        		
	        		modelHistory.addRow(data);

	        	}
	        	
	        	JTable tblHistory = new JTable(modelHistory);
	    		tblHistory.setPreferredScrollableViewportSize(new Dimension(400, 300));
	    		tblHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    		tblHistory.setFont(new Font("Dialog", Font.PLAIN, 16));
	    		tblHistory.setRowHeight(32);
	    		tblHistory.setVisible(true);
	    		historyScroll = new JScrollPane(tblHistory);
	    		historyScroll.setBounds(10, 10, 765, 560);
	    		historyScroll.setVisible(true);
	    		
	    		window.getContentPane().add(historyScroll);
	    		window.setVisible(true);
	    		tblHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    		tblHistory.setBounds(10, 10, 765, 560);
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
		
		JButton btnConversor = new JButton("D\u00F3lar y Conversor");
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
	        	if(event.getValueIsAdjusting() || ((DefaultListSelectionModel)event.getSource()).isSelectionEmpty())
	        		return;
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a comprar");
	        	int index = tblStocks.getSelectedRow();
	        	tblStocks.clearSelection();
	        	Stock currentAsset = (Stock) tblStocks.getValueAt(index, 0);
	        	boolean isBuyingOperation = true;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        }
	    });
		tblStocks.setVisible(false);
		Syst.updateValuesFromInternet();
		List<Stock> miLista = Syst.getStocks();
		tblStocks.setModel(new StocksTableModel(miLista)); // ACÁ DESACTIVÉ PORQUE TIRA UN NULL POINTER EXCEPTION Y NO TE DEJA VER LA GRÁFICA
		tblStocks.getColumnModel().getColumn(1).setResizable(false);
		tblStocks.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblStocks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblStocks.setBounds(10, 138, 921, 437);
		stocksScroll = new JScrollPane(tblStocks);
		stocksScroll.setVisible(false);
		stocksScroll.setBounds(10, 138, 921, 437);
		frmPortfolioManager.getContentPane().add(stocksScroll);
		
		tblBonos = new JTable();
		tblBonos.setPreferredScrollableViewportSize(new Dimension(400, 300));
		tblBonos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBonos.setFont(new Font("Dialog", Font.PLAIN, 16));
		tblBonos.setRowHeight(32);

		tblBonos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {	        	
	        	if(event.getValueIsAdjusting() || ((DefaultListSelectionModel)event.getSource()).isSelectionEmpty())
	        		return;
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a comprar");
	        	int index = tblBonos.getSelectedRow();
	        	tblBonos.clearSelection();
	        	Bono currentAsset = (Bono) tblBonos.getValueAt(index, 0);
	        	boolean isBuyingOperation = true;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        }
	    });
		tblBonos.setVisible(false);
		List<Bono> bonos = Syst.getBonos();
		tblBonos.setModel(new BonosTableModel(bonos)); // ACÁ DESACTIVÉ PORQUE TIRA UN NULL POINTER EXCEPTION Y NO TE DEJA VER LA GRÁFICA
		tblBonos.getColumnModel().getColumn(1).setResizable(false);
		tblBonos.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblBonos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblBonos.setBounds(10, 138, 921, 437);
		bonosScroll = new JScrollPane(tblBonos);
		bonosScroll.setVisible(false);
		bonosScroll.setBounds(10, 138, 921, 437);
		frmPortfolioManager.getContentPane().add(bonosScroll);
		

		tblCommodities = new JTable();
		tblCommodities.setPreferredScrollableViewportSize(new Dimension(400, 300));
		tblCommodities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCommodities.setFont(new Font("Dialog", Font.PLAIN, 16));
		tblCommodities.setRowHeight(32);
		
		tblCommodities.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {	        	
	        	if(event.getValueIsAdjusting() || ((DefaultListSelectionModel)event.getSource()).isSelectionEmpty())
	        		return;
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a comprar");
	        	int index = tblCommodities.getSelectedRow();
	        	tblCommodities.clearSelection();
	        	Commodity currentAsset = (Commodity) tblCommodities.getValueAt(index, 0);
	        	boolean isBuyingOperation = true;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        	}
	    });
		tblCommodities.setVisible(false);
		List<Commodity> commodities = Syst.getCommodities();
		tblCommodities.setModel(new CommoditiesTableModel(commodities)); // ACÁ DESACTIVÉ PORQUE TIRA UN NULL POINTER EXCEPTION Y NO TE DEJA VER LA GRÁFICA
		tblCommodities.getColumnModel().getColumn(1).setResizable(false);
		tblCommodities.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblCommodities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblCommodities.setBounds(10, 138, 921, 437);
		commoditiesScroll = new JScrollPane(tblCommodities);
		commoditiesScroll.setVisible(false);
		commoditiesScroll.setBounds(10, 138, 921, 437);
		frmPortfolioManager.getContentPane().add(commoditiesScroll);
		
		tblFuturos = new JTable();
		tblFuturos.setPreferredScrollableViewportSize(new Dimension(400, 300));
		tblFuturos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblFuturos.setFont(new Font("Dialog", Font.PLAIN, 16));
		tblFuturos.setRowHeight(32);
		
		tblFuturos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {	        	
	        	if(event.getValueIsAdjusting() || ((DefaultListSelectionModel)event.getSource()).isSelectionEmpty())
	        		return;
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a comprar");
	        	int index = tblFuturos.getSelectedRow();
	        	tblFuturos.clearSelection();
	        	Futuro currentAsset = (Futuro) tblFuturos.getValueAt(index, 0);
	        	boolean isBuyingOperation = true;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        }
	    });
		tblFuturos.setVisible(false);
		List<Futuro> futuros = Syst.getFuturos();
		tblFuturos.setModel(new FuturosTableModel(futuros)); // ACÁ DESACTIVÉ PORQUE TIRA UN NULL POINTER EXCEPTION Y NO TE DEJA VER LA GRÁFICA
		tblFuturos.getColumnModel().getColumn(1).setResizable(false);
		tblFuturos.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblFuturos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblFuturos.setBounds(10, 138, 921, 437);
		futurosScroll = new JScrollPane(tblFuturos);
		futurosScroll.setVisible(false);
		futurosScroll.setBounds(10, 138, 921, 437);
		frmPortfolioManager.getContentPane().add(futurosScroll);
		
		tblOpciones = new JTable();
		tblOpciones.setPreferredScrollableViewportSize(new Dimension(400, 300));
		tblOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblOpciones.setFont(new Font("Dialog", Font.PLAIN, 16));
		tblOpciones.setRowHeight(32);
		
		tblOpciones.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {	        	
	        	if(event.getValueIsAdjusting() || ((DefaultListSelectionModel)event.getSource()).isSelectionEmpty())
	        		return;
	        	String input = JOptionPane.showInputDialog("Ingrese la cantidad a comprar");
	        	int index = tblOpciones.getSelectedRow();
	        	tblOpciones.clearSelection();
	        	Opcion currentAsset = (Opcion) tblOpciones.getValueAt(index, 0);
	        	boolean isBuyingOperation = true;
	        	processOperation(miPortfolio, holdingsTableModel, isBuyingOperation, currentAsset, input);
	        	}
	    });
		tblOpciones.setVisible(false);
		List<Opcion> opciones = Syst.getOpciones();
		tblOpciones.setModel(new OpcionesTableModel(opciones)); // ACÁ DESACTIVÉ PORQUE TIRA UN NULL POINTER EXCEPTION Y NO TE DEJA VER LA GRÁFICA
		tblOpciones.getColumnModel().getColumn(1).setResizable(false);
		tblOpciones.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblOpciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblOpciones.setBounds(10, 138, 921, 437);
		opcionesScroll = new JScrollPane(tblOpciones);
		opcionesScroll.setVisible(false);
		opcionesScroll.setBounds(10, 138, 921, 437);
		frmPortfolioManager.getContentPane().add(opcionesScroll);
		
		panelConversor = new Panel();
		panelConversor.setName("");
		panelConversor.setVisible(false);
		panelConversor.setBounds(12, 137, 919, 438);
		frmPortfolioManager.getContentPane().add(panelConversor);
		panelConversor.setLayout(null);
		
		lblDolarOficial = new JLabel("D\u00F3lar oficial: 15,65");
		lblDolarOficial.setForeground(new Color(0, 128, 0));
		lblDolarOficial.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDolarOficial.setBounds(199, 72, 183, 33);
		panelConversor.add(lblDolarOficial);
		
		lblDolarBlue = new JLabel("D\u00F3lar blue: 16,20");
		lblDolarBlue.setForeground(new Color(0, 0, 205));
		lblDolarBlue.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDolarBlue.setBounds(493, 74, 183, 29);
		panelConversor.add(lblDolarBlue);
		
		spDolaresDolarOficial = new JSpinner();
		spDolaresDolarOficial.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				spPesosDolarOficial.setValue(Math.abs(Double.parseDouble(spDolaresDolarOficial.getValue().toString()) * Syst.getDolarOficial()));
			}
		});
		spDolaresDolarOficial.setFont(new Font("Dialog", Font.BOLD, 18));
		spDolaresDolarOficial.setBounds(199, 194, 183, 33);
		panelConversor.add(spDolaresDolarOficial);
		
		spDolaresDolarBlue = new JSpinner();
		spDolaresDolarBlue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spPesosDolarBlue.setValue(Math.abs(Double.parseDouble(spDolaresDolarBlue.getValue().toString()) * Syst.getDolarBlue()));
			}
		});
		spDolaresDolarBlue.setFont(new Font("Dialog", Font.BOLD, 18));
		spDolaresDolarBlue.setBounds(493, 194, 183, 33);
		panelConversor.add(spDolaresDolarBlue);
		
		lblPesos = new JLabel("D\u00F3lares");
		lblPesos.setBounds(199, 166, 55, 16);
		panelConversor.add(lblPesos);
		
		spPesosDolarOficial = new JSpinner();
		spPesosDolarOficial.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				spDolaresDolarOficial.setValue(Math.abs(Double.parseDouble(spPesosDolarOficial.getValue().toString()) / Syst.getDolarOficial()));
			}
		});
		spPesosDolarOficial.setFont(new Font("Dialog", Font.BOLD, 18));
		spPesosDolarOficial.setBounds(199, 267, 183, 33);
		panelConversor.add(spPesosDolarOficial);
		
		lblDlares = new JLabel("Pesos");
		lblDlares.setBounds(199, 239, 55, 16);
		panelConversor.add(lblDlares);
		
		lblDlares_1 = new JLabel("D\u00F3lares");
		lblDlares_1.setBounds(493, 166, 55, 16);
		panelConversor.add(lblDlares_1);
		
		spPesosDolarBlue = new JSpinner();
		spPesosDolarBlue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spDolaresDolarBlue.setValue(Math.abs(Double.parseDouble(spPesosDolarBlue.getValue().toString()) / Syst.getDolarBlue()));
			}
		});
		spPesosDolarBlue.setFont(new Font("Dialog", Font.BOLD, 18));
		spPesosDolarBlue.setBounds(493, 267, 183, 33);
		panelConversor.add(spPesosDolarBlue);
		
		lblPesos_1 = new JLabel("Pesos");
		lblPesos_1.setBounds(493, 239, 55, 16);
		panelConversor.add(lblPesos_1);
		
		lblFuentembito = new JLabel("Fuente: \u00C1mbito");
		lblFuentembito.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuentembito.setBounds(774, 387, 135, 16);
		panelConversor.add(lblFuentembito);
		
		panelCCL = new JPanel();
		panelCCL.setVisible(false);
		panelCCL.setBounds(12, 137, 919, 438);
		frmPortfolioManager.getContentPane().add(panelCCL);
		panelCCL.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Ticker");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2.setBounds(12, 12, 88, 24);
		panelCCL.add(lblNewLabel_2);
		
		lblPrecio = new JLabel("Precio ($)");
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrecio.setBounds(112, 12, 88, 24);
		panelCCL.add(lblPrecio);
		
		lblPrecious = new JLabel("Precio (U$S)");
		lblPrecious.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrecious.setBounds(226, 12, 123, 24);
		panelCCL.add(lblPrecious);
		
		lblTickers1 = new JLabel("New label");
		lblTickers1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTickers1.setVerticalAlignment(SwingConstants.TOP);
		lblTickers1.setBounds(12, 48, 77, 377);
		panelCCL.add(lblTickers1);
		
		lblPesosPrices = new JLabel("New label");
		lblPesosPrices.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPesosPrices.setVerticalAlignment(SwingConstants.TOP);
		lblPesosPrices.setBounds(112, 48, 88, 377);
		panelCCL.add(lblPesosPrices);
		
		lblDollarsPrices = new JLabel("New label");
		lblDollarsPrices.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDollarsPrices.setVerticalAlignment(SwingConstants.TOP);
		lblDollarsPrices.setBounds(226, 48, 88, 377);
		panelCCL.add(lblDollarsPrices);
		
		lblFuenteYahooFinance = new JLabel("Fuente: Yahoo Finance");
		lblFuenteYahooFinance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuenteYahooFinance.setBounds(758, 409, 149, 16);
		panelCCL.add(lblFuenteYahooFinance);
		
		lblOperacinptimaCcl = new JLabel("OPERACI\u00D3N \u00D3PTIMA CCL");
		lblOperacinptimaCcl.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOperacinptimaCcl.setForeground(new Color(50, 205, 50));
		lblOperacinptimaCcl.setBounds(631, 82, 235, 24);
		panelCCL.add(lblOperacinptimaCcl);
		
		lblBestCCL = new JLabel("New label");
		lblBestCCL.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBestCCL.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestCCL.setBounds(631, 118, 235, 24);
		panelCCL.add(lblBestCCL);
		
		lblOperacinptimaCcli = new JLabel("OPERACI\u00D3N \u00D3PTIMA CCLI");
		lblOperacinptimaCcli.setForeground(new Color(50, 205, 50));
		lblOperacinptimaCcli.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOperacinptimaCcli.setBounds(631, 191, 235, 24);
		panelCCL.add(lblOperacinptimaCcli);
		
		lblBestCCLI = new JLabel("New label");
		lblBestCCLI.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBestCCLI.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestCCLI.setBounds(631, 227, 235, 24);
		panelCCL.add(lblBestCCLI);
		
		lblDolarCCL = new JLabel("New label");
		lblDolarCCL.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDolarCCL.setVerticalAlignment(SwingConstants.TOP);
		lblDolarCCL.setBounds(369, 48, 88, 377);
		panelCCL.add(lblDolarCCL);
		
		lblDlarCcl = new JLabel("D\u00F3lar CCL");
		lblDlarCcl.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDlarCcl.setBounds(369, 12, 123, 24);
		panelCCL.add(lblDlarCcl);
		
		lblMrgenDlarBlue = new JLabel("M\u00C1RGEN D\u00D3LAR BLUE");
		lblMrgenDlarBlue.setForeground(new Color(0, 0, 205));
		lblMrgenDlarBlue.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMrgenDlarBlue.setBounds(12, 365, 235, 24);
		panelCCL.add(lblMrgenDlarBlue);
		
		lblMargenDolarBlue = new JLabel("New label");
		lblMargenDolarBlue.setHorizontalAlignment(SwingConstants.CENTER);
		lblMargenDolarBlue.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMargenDolarBlue.setBounds(12, 401, 200, 24);
		panelCCL.add(lblMargenDolarBlue);
		
		lblMrgenDolarOficial = new JLabel("M\u00C1RGEN DOLAR OFICIAL");
		lblMrgenDolarOficial.setForeground(new Color(50, 205, 50));
		lblMrgenDolarOficial.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMrgenDolarOficial.setBounds(349, 365, 235, 24);
		panelCCL.add(lblMrgenDolarOficial);
		
		lblMargenDolarOficial = new JLabel("New label");
		lblMargenDolarOficial.setHorizontalAlignment(SwingConstants.CENTER);
		lblMargenDolarOficial.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMargenDolarOficial.setBounds(349, 401, 225, 24);
		panelCCL.add(lblMargenDolarOficial);
		refreshLabels(miPortfolio);
	}
	
	/**
	 * Sends operation to portfolio and updates the table
	 * @param miPortfolio
	 * @param holdingsTableModel
	 * @param isBuyingOperation
	 * @param currentAsset
	 * @param input
	 */
	
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
	
	private void refreshLabels(Portfolio miPortfolio){
		lblNotInvested_V.setText("$" + miPortfolio.getCash());
		lblInvested_V.setText("$"+miPortfolio.getAllAcquiredValues());
		lblOverallReturns_V.setText(miPortfolio.getOverallReturns() + "%");
		if(miPortfolio.getOverallReturns() < 0) {
			lblOverallReturns_V.setForeground(Color.red);
		}
		else {
			lblOverallReturns_V.setForeground(new Color(54, 139, 13));
		}
		lblOverallGains_V.setText("$" + miPortfolio.getOverallGains());
		if(miPortfolio.getOverallGains() < 0) {
			lblOverallGains_V.setForeground(Color.red);
		}
		else {
			lblOverallGains_V.setForeground(new Color(54, 139, 13));
		}
		lblNetWorth_V.setText("$" + miPortfolio.getNetWorth());
		if(miPortfolio.getNetWorth() < 0) {
			lblNetWorth_V.setForeground(Color.red);
		}
		else {
			lblNetWorth_V.setForeground(new Color(54, 139, 13));
		}  
	}
	
	public static void setDolarConverter(double dolarOficial, double dolarBlue){
		lblDolarOficial.setText("<html>Dólar oficial: " + dolarOficial + "</html>");
		lblDolarBlue.setText("<html>Dólar blue: " + dolarBlue + "</html>");
	}
	
	public static void setDolar(String dolarOficial, String dolarBlue, double bestCCL){
		lblMargenDolarOficial.setText("<html>" + (Math.round((Double.parseDouble(dolarOficial) - bestCCL) * 100.0) / 100.0) + "</html>");
		lblMargenDolarBlue.setText("<html>" + (Math.round((Double.parseDouble(dolarBlue) - bestCCL) * 100.0) / 100.0) + "</html>");
	}
	
	public static void setDolarCCL(String dolarCCL){
		lblDolarCCL.setText("<html>" + dolarCCL + "</html>");
	}
	
	public static void setBestCCLI(String bestCCLI){
		lblBestCCLI.setText("<html>" + bestCCLI + "</html>");
	}
	
	public static void setBestCCL(String bestCCL){
		lblBestCCL.setText("<html>" + bestCCL + "</html>");
	}
	
	public static void setCCLPrices_P(String prices_P){
		lblPesosPrices.setText("<html>" + prices_P + "</html>");
	}
	
	public static void setCCLPrices_D(String prices_D){
		lblDollarsPrices.setText("<html>" + prices_D + "</html>");
	}
	
	public static void setCCLTickers(String tickers){
		lblTickers1.setText("<html>" + tickers + "</html>");
	}
	
	public static void setNews(String news1, String news2, String news3, String news4){
		lblNews1.setText("<html>" + news1 + "</html>");
		lblNews2.setText("<html>" + news2 + "</html>");
		lblNews3.setText("<html>" + news3 + "</html>");
		lblNews4.setText("<html>" + news4 + "</html>");
	}
}
