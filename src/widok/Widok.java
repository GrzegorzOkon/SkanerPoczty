package widok;

import kontroler.Kontroler;
import procesor.Model;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Widok extends Application {

	//Klasy modelu mvc
	private Model model;
	private WidokWiadomoœci widokWiadomoœci;
	private Kontroler kontroler;
	
    private Scene scena; // scena
    private BorderPane g³ównyKontener; // g³ówny kontener
    private SplitPane lewyKontener; // kontener boczny
    private BorderPane kontenerZadañ, administracyjnyKontener; //kontenery wewnêtrzne
    private TreeItem<String> rootItem; 
    private TreeItem<String> item; 
    private TreeView<String> tree;
    
    // =============================================================================
    
    private void ustawReferencje(Stage primaryStage)
    {
    	widokWiadomoœci = new WidokWiadomoœci(primaryStage);
    	model = new Model();
    	kontroler = new Kontroler(model, this, widokWiadomoœci);
    	widokWiadomoœci.ustawReferencjê(kontroler);
    }
	
    // =============================================================================
    
    private void prepareScene(Stage primaryStage)
    {
        primaryStage.setMinWidth(450);
        g³ównyKontener = new BorderPane();
        
        lewyKontener = new SplitPane();
        //leftContainer.setId("leftContainer");
        lewyKontener.setOrientation(Orientation.VERTICAL);
        lewyKontener.setPrefWidth(440);
        //lewyKontener.setMaxWidth(lewyKontener.getPrefWidth());
        //lewyKontener.setMinWidth(lewyKontener.getPrefWidth());
        kontenerZadañ = new  BorderPane();      
        rootItem = new TreeItem<String> ("Skaner wiadomoœci");
        rootItem.setExpanded(true);
        item = new TreeItem<String> ("Wiadomoœci (na dysku)"); 
        rootItem.getChildren().add(item);      
        tree = new TreeView<String> (rootItem);
        kontenerZadañ.setCenter(tree);
        administracyjnyKontener = new  BorderPane();
        lewyKontener.getItems().addAll(kontenerZadañ, administracyjnyKontener);
       
        // ==============================
       
        g³ównyKontener.setLeft(lewyKontener);
        g³ównyKontener.setCenter(widokWiadomoœci.pobierzKontenerWiadomoœci());
        //centerContainer = new Pane();
        //centerContainer.setId("centerContainer");

        scena = new Scene(g³ównyKontener, 1550, 920);
    }
   
    // =============================================================================
    
    @Override
    public void start(Stage primaryStage) { 
    	
    	ustawReferencje(primaryStage);
              
        prepareScene(primaryStage);

        primaryStage.setTitle("Skaner wiadomoœci");
        primaryStage.setScene(scena);
        primaryStage.show();
    }

    // =============================================================================

    public static void main(String[] args)
    {
          launch(args);
    }
   
    // =============================================================================

	/*private Scene scena;
	
    private javafx.scene.control.SplitPane panelNaLewo;
    private javafx.scene.layout.BorderPane panelListyZadañ;
    private javafx.scene.layout.BorderPane panelAdministracji;
    private JLabel tekstListyZadañ;
    private Font czcionka;
    private JTree drzewoZadañ;
    private DefaultMutableTreeNode skanerPoczty;
    private DefaultMutableTreeNode wiadomoœci;
    private JLabel tekstAdministracji;
    private DefaultMutableTreeNode logZdarzeñ;*/
    //private JTree drzewoAdministracji;
	
    
    

     

        

    
    /*
    @Override
    public void start(Stage primaryStage) {	
		//Utworzenie ramki i nadanie waœciwoœci
		scena = new Scene(new javafx.scene.layout.BorderPane(), 1570, 965);
		primaryStage.setTitle("Skaner poczty");
		Group root = new Group();

        
        /*super("Skaner poczty");
        try {
        	javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        
        getContentPane().setLayout(new BorderLayout());*/
 
        //panelListyZadañ = new javafx.scene.layout.BorderPane();
        //panelListyZadañ.setLayout(new BorderLayout());

		/*czcionka = new Font("Courier", Font.PLAIN, 19);
        
		tekstListyZadañ = new JLabel("Listy zadañ");
		tekstListyZadañ.setFont(czcionka);
		tekstListyZadañ.setHorizontalAlignment(JLabel.CENTER);
		tekstListyZadañ.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tekstListyZadañ.setBackground(new Color(206,206,206));
		tekstListyZadañ.setOpaque(true);
		
		skanerPoczty = new DefaultMutableTreeNode("Skaner poczty"); 
		wiadomoœci = new DefaultMutableTreeNode("Wiadomoœci"); 
		skanerPoczty.add(wiadomoœci);
		drzewoZadañ = new JTree(skanerPoczty);
		
		panelListyZadañ.add(tekstListyZadañ, BorderLayout.NORTH);
		panelListyZadañ.add(drzewoZadañ, BorderLayout.CENTER);
		drzewoZadañ.setBorder(BorderFactory.createLineBorder(Color.gray));		*/
        
        //panelAdministracji = new javafx.scene.layout.BorderPane();  
        /*panelAdministracji.setLayout(new BorderLayout());
        
		tekstAdministracji = new JLabel("Administracja");
		tekstAdministracji.setFont(czcionka);
		tekstAdministracji.setHorizontalAlignment(JLabel.CENTER);
		tekstAdministracji.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));		
		tekstAdministracji.setBackground(new Color(206,206,206));
		tekstAdministracji.setOpaque(true);
		
		
		logZdarzeñ = new DefaultMutableTreeNode("Log zdarzeñ"); 
		drzewoAdministracji = new JTree(logZdarzeñ);
		drzewoAdministracji.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		panelAdministracji.add(tekstAdministracji, BorderLayout.NORTH);
		panelAdministracji.add(drzewoAdministracji, BorderLayout.CENTER);*/

        //Create a split pane with the two scroll panes in it.
        /*panelNaLewo = new javafx.scene.control.SplitPane();
        panelNaLewo.setOrientation(Orientation.VERTICAL);
        panelNaLewo.setPrefSize(200, 200);
        panelNaLewo.getItems().addAll(panelListyZadañ, panelAdministracji);*/
        /*panelNaLewo.setOneTouchExpandable(true);
        panelNaLewo.setDividerLocation(665);
        panelNaLewo.setPreferredSize(new Dimension(440, 900));
        panelNaLewo.setMaximumSize(panelNaLewo.getPreferredSize()); 
        panelNaLewo.setMinimumSize(panelNaLewo.getPreferredSize());*/

        //Provide minimum sizes for the two components in the split pane
        /*Dimension minimumSize = new Dimension(232, 232);
        panelListyZadañ.setMinimumSize(minimumSize);
        panelAdministracji.setMinimumSize(minimumSize);	*/	

		
        //Dodanie elementów panelu na lewo
        
        //Wyœwietlenie panelu na lewo
        //scena.add(panelNaLewo, BorderLayout.WEST);
        /*root.getChildren().add(panelNaLewo);

        //Wyœwietlenie panelu na prawo
        //getContentPane().add(widokWiadomoœci.pobierzPanelWiadomoœci(), BorderLayout.CENTER);
        
        //getContentPane();
        //setSize(1570,965);   //Okreœlenie rozmiaru
        //setLocationRelativeTo(null);   //Ustawienie na œrodku ekranu
        //setVisible(true);   //Wyœwietlenie okienka	
        primaryStage.setScene(scena);
        primaryStage.show();*/
	//}
	
	public void wyœwietlDane(String dana) {
		//widokWiadomoœci.wyœwietlDane(dana);
	}
	
	public void ustawReferencjê(Kontroler kontroler) {
		//this.kontroler = kontroler;
		//this.widokWiadomoœci = new WidokWiadomoœci();
		//widokWiadomoœci.ustawReferencjê(kontroler);
	}
}
