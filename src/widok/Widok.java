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
	private WidokWiadomości widokWiadomości;
	private Kontroler kontroler;
	
    private Scene scena; // scena
    private BorderPane głównyKontener; // główny kontener
    private SplitPane lewyKontener; // kontener boczny
    private BorderPane kontenerZadań, administracyjnyKontener; //kontenery wewnętrzne
    private TreeItem<String> rootItem; 
    private TreeItem<String> item; 
    private TreeView<String> tree;
    
    // =============================================================================
    
    private void ustawReferencje(Stage primaryStage)
    {
    	widokWiadomości = new WidokWiadomości(primaryStage);
    	model = new Model();
    	kontroler = new Kontroler(model, this, widokWiadomości);
    	widokWiadomości.ustawReferencję(kontroler);
    }
	
    // =============================================================================
    
    private void prepareScene(Stage primaryStage)
    {
        primaryStage.setMinWidth(450);
        głównyKontener = new BorderPane();
        
        lewyKontener = new SplitPane();
        //leftContainer.setId("leftContainer");
        lewyKontener.setOrientation(Orientation.VERTICAL);
        lewyKontener.setPrefWidth(440);
        //lewyKontener.setMaxWidth(lewyKontener.getPrefWidth());
        //lewyKontener.setMinWidth(lewyKontener.getPrefWidth());
        kontenerZadań = new  BorderPane();      
        rootItem = new TreeItem<String> ("Skaner wiadomości");
        rootItem.setExpanded(true);
        item = new TreeItem<String> ("Wiadomości (na dysku)"); 
        rootItem.getChildren().add(item);      
        tree = new TreeView<String> (rootItem);
        kontenerZadań.setCenter(tree);
        administracyjnyKontener = new  BorderPane();
        lewyKontener.getItems().addAll(kontenerZadań, administracyjnyKontener);
       
        // ==============================
       
        głównyKontener.setLeft(lewyKontener);
        głównyKontener.setCenter(widokWiadomości.pobierzKontenerWiadomości());
        //centerContainer = new Pane();
        //centerContainer.setId("centerContainer");

        scena = new Scene(głównyKontener, 1550, 920);
    }
   
    // =============================================================================
    
    @Override
    public void start(Stage primaryStage) { 
    	
    	ustawReferencje(primaryStage);
              
        prepareScene(primaryStage);

        primaryStage.setTitle("Skaner wiadomości");
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
    private javafx.scene.layout.BorderPane panelListyZadań;
    private javafx.scene.layout.BorderPane panelAdministracji;
    private JLabel tekstListyZadań;
    private Font czcionka;
    private JTree drzewoZadań;
    private DefaultMutableTreeNode skanerPoczty;
    private DefaultMutableTreeNode wiadomości;
    private JLabel tekstAdministracji;
    private DefaultMutableTreeNode logZdarzeń;*/
    //private JTree drzewoAdministracji;
	
    
    

     

        

    
    /*
    @Override
    public void start(Stage primaryStage) {	
		//Utworzenie ramki i nadanie waściwości
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
 
        //panelListyZadań = new javafx.scene.layout.BorderPane();
        //panelListyZadań.setLayout(new BorderLayout());

		/*czcionka = new Font("Courier", Font.PLAIN, 19);
        
		tekstListyZadań = new JLabel("Listy zadań");
		tekstListyZadań.setFont(czcionka);
		tekstListyZadań.setHorizontalAlignment(JLabel.CENTER);
		tekstListyZadań.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tekstListyZadań.setBackground(new Color(206,206,206));
		tekstListyZadań.setOpaque(true);
		
		skanerPoczty = new DefaultMutableTreeNode("Skaner poczty"); 
		wiadomości = new DefaultMutableTreeNode("Wiadomości"); 
		skanerPoczty.add(wiadomości);
		drzewoZadań = new JTree(skanerPoczty);
		
		panelListyZadań.add(tekstListyZadań, BorderLayout.NORTH);
		panelListyZadań.add(drzewoZadań, BorderLayout.CENTER);
		drzewoZadań.setBorder(BorderFactory.createLineBorder(Color.gray));		*/
        
        //panelAdministracji = new javafx.scene.layout.BorderPane();  
        /*panelAdministracji.setLayout(new BorderLayout());
        
		tekstAdministracji = new JLabel("Administracja");
		tekstAdministracji.setFont(czcionka);
		tekstAdministracji.setHorizontalAlignment(JLabel.CENTER);
		tekstAdministracji.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));		
		tekstAdministracji.setBackground(new Color(206,206,206));
		tekstAdministracji.setOpaque(true);
		
		
		logZdarzeń = new DefaultMutableTreeNode("Log zdarzeń"); 
		drzewoAdministracji = new JTree(logZdarzeń);
		drzewoAdministracji.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		panelAdministracji.add(tekstAdministracji, BorderLayout.NORTH);
		panelAdministracji.add(drzewoAdministracji, BorderLayout.CENTER);*/

        //Create a split pane with the two scroll panes in it.
        /*panelNaLewo = new javafx.scene.control.SplitPane();
        panelNaLewo.setOrientation(Orientation.VERTICAL);
        panelNaLewo.setPrefSize(200, 200);
        panelNaLewo.getItems().addAll(panelListyZadań, panelAdministracji);*/
        /*panelNaLewo.setOneTouchExpandable(true);
        panelNaLewo.setDividerLocation(665);
        panelNaLewo.setPreferredSize(new Dimension(440, 900));
        panelNaLewo.setMaximumSize(panelNaLewo.getPreferredSize()); 
        panelNaLewo.setMinimumSize(panelNaLewo.getPreferredSize());*/

        //Provide minimum sizes for the two components in the split pane
        /*Dimension minimumSize = new Dimension(232, 232);
        panelListyZadań.setMinimumSize(minimumSize);
        panelAdministracji.setMinimumSize(minimumSize);	*/	

		
        //Dodanie elementów panelu na lewo
        
        //Wyświetlenie panelu na lewo
        //scena.add(panelNaLewo, BorderLayout.WEST);
        /*root.getChildren().add(panelNaLewo);

        //Wyświetlenie panelu na prawo
        //getContentPane().add(widokWiadomości.pobierzPanelWiadomości(), BorderLayout.CENTER);
        
        //getContentPane();
        //setSize(1570,965);   //Określenie rozmiaru
        //setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        //setVisible(true);   //Wyświetlenie okienka	
        primaryStage.setScene(scena);
        primaryStage.show();*/
	//}
	
	public void wyświetlDane(String dana) {
		//widokWiadomości.wyświetlDane(dana);
	}
	
	public void ustawReferencję(Kontroler kontroler) {
		//this.kontroler = kontroler;
		//this.widokWiadomości = new WidokWiadomości();
		//widokWiadomości.ustawReferencję(kontroler);
	}
}
