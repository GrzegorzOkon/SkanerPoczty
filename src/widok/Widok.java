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
	private WidokWiadomo�ci widokWiadomo�ci;
	private Kontroler kontroler;
	
    private Scene scena; // scena
    private BorderPane g��wnyKontener; // g��wny kontener
    private SplitPane lewyKontener; // kontener boczny
    private BorderPane kontenerZada�, administracyjnyKontener; //kontenery wewn�trzne
    private TreeItem<String> rootItem; 
    private TreeItem<String> item; 
    private TreeView<String> tree;
    
    // =============================================================================
    
    private void ustawReferencje(Stage primaryStage)
    {
    	widokWiadomo�ci = new WidokWiadomo�ci(primaryStage);
    	model = new Model();
    	kontroler = new Kontroler(model, this, widokWiadomo�ci);
    	widokWiadomo�ci.ustawReferencj�(kontroler);
    }
	
    // =============================================================================
    
    private void prepareScene(Stage primaryStage)
    {
        primaryStage.setMinWidth(450);
        g��wnyKontener = new BorderPane();
        
        lewyKontener = new SplitPane();
        //leftContainer.setId("leftContainer");
        lewyKontener.setOrientation(Orientation.VERTICAL);
        lewyKontener.setPrefWidth(440);
        //lewyKontener.setMaxWidth(lewyKontener.getPrefWidth());
        //lewyKontener.setMinWidth(lewyKontener.getPrefWidth());
        kontenerZada� = new  BorderPane();      
        rootItem = new TreeItem<String> ("Skaner wiadomo�ci");
        rootItem.setExpanded(true);
        item = new TreeItem<String> ("Wiadomo�ci (na dysku)"); 
        rootItem.getChildren().add(item);      
        tree = new TreeView<String> (rootItem);
        kontenerZada�.setCenter(tree);
        administracyjnyKontener = new  BorderPane();
        lewyKontener.getItems().addAll(kontenerZada�, administracyjnyKontener);
       
        // ==============================
       
        g��wnyKontener.setLeft(lewyKontener);
        g��wnyKontener.setCenter(widokWiadomo�ci.pobierzKontenerWiadomo�ci());
        //centerContainer = new Pane();
        //centerContainer.setId("centerContainer");

        scena = new Scene(g��wnyKontener, 1550, 920);
    }
   
    // =============================================================================
    
    @Override
    public void start(Stage primaryStage) { 
    	
    	ustawReferencje(primaryStage);
              
        prepareScene(primaryStage);

        primaryStage.setTitle("Skaner wiadomo�ci");
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
    private javafx.scene.layout.BorderPane panelListyZada�;
    private javafx.scene.layout.BorderPane panelAdministracji;
    private JLabel tekstListyZada�;
    private Font czcionka;
    private JTree drzewoZada�;
    private DefaultMutableTreeNode skanerPoczty;
    private DefaultMutableTreeNode wiadomo�ci;
    private JLabel tekstAdministracji;
    private DefaultMutableTreeNode logZdarze�;*/
    //private JTree drzewoAdministracji;
	
    
    

     

        

    
    /*
    @Override
    public void start(Stage primaryStage) {	
		//Utworzenie ramki i nadanie wa�ciwo�ci
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
 
        //panelListyZada� = new javafx.scene.layout.BorderPane();
        //panelListyZada�.setLayout(new BorderLayout());

		/*czcionka = new Font("Courier", Font.PLAIN, 19);
        
		tekstListyZada� = new JLabel("Listy zada�");
		tekstListyZada�.setFont(czcionka);
		tekstListyZada�.setHorizontalAlignment(JLabel.CENTER);
		tekstListyZada�.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tekstListyZada�.setBackground(new Color(206,206,206));
		tekstListyZada�.setOpaque(true);
		
		skanerPoczty = new DefaultMutableTreeNode("Skaner poczty"); 
		wiadomo�ci = new DefaultMutableTreeNode("Wiadomo�ci"); 
		skanerPoczty.add(wiadomo�ci);
		drzewoZada� = new JTree(skanerPoczty);
		
		panelListyZada�.add(tekstListyZada�, BorderLayout.NORTH);
		panelListyZada�.add(drzewoZada�, BorderLayout.CENTER);
		drzewoZada�.setBorder(BorderFactory.createLineBorder(Color.gray));		*/
        
        //panelAdministracji = new javafx.scene.layout.BorderPane();  
        /*panelAdministracji.setLayout(new BorderLayout());
        
		tekstAdministracji = new JLabel("Administracja");
		tekstAdministracji.setFont(czcionka);
		tekstAdministracji.setHorizontalAlignment(JLabel.CENTER);
		tekstAdministracji.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));		
		tekstAdministracji.setBackground(new Color(206,206,206));
		tekstAdministracji.setOpaque(true);
		
		
		logZdarze� = new DefaultMutableTreeNode("Log zdarze�"); 
		drzewoAdministracji = new JTree(logZdarze�);
		drzewoAdministracji.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		panelAdministracji.add(tekstAdministracji, BorderLayout.NORTH);
		panelAdministracji.add(drzewoAdministracji, BorderLayout.CENTER);*/

        //Create a split pane with the two scroll panes in it.
        /*panelNaLewo = new javafx.scene.control.SplitPane();
        panelNaLewo.setOrientation(Orientation.VERTICAL);
        panelNaLewo.setPrefSize(200, 200);
        panelNaLewo.getItems().addAll(panelListyZada�, panelAdministracji);*/
        /*panelNaLewo.setOneTouchExpandable(true);
        panelNaLewo.setDividerLocation(665);
        panelNaLewo.setPreferredSize(new Dimension(440, 900));
        panelNaLewo.setMaximumSize(panelNaLewo.getPreferredSize()); 
        panelNaLewo.setMinimumSize(panelNaLewo.getPreferredSize());*/

        //Provide minimum sizes for the two components in the split pane
        /*Dimension minimumSize = new Dimension(232, 232);
        panelListyZada�.setMinimumSize(minimumSize);
        panelAdministracji.setMinimumSize(minimumSize);	*/	

		
        //Dodanie element�w panelu na lewo
        
        //Wy�wietlenie panelu na lewo
        //scena.add(panelNaLewo, BorderLayout.WEST);
        /*root.getChildren().add(panelNaLewo);

        //Wy�wietlenie panelu na prawo
        //getContentPane().add(widokWiadomo�ci.pobierzPanelWiadomo�ci(), BorderLayout.CENTER);
        
        //getContentPane();
        //setSize(1570,965);   //Okre�lenie rozmiaru
        //setLocationRelativeTo(null);   //Ustawienie na �rodku ekranu
        //setVisible(true);   //Wy�wietlenie okienka	
        primaryStage.setScene(scena);
        primaryStage.show();*/
	//}
	
	public void wy�wietlDane(String dana) {
		//widokWiadomo�ci.wy�wietlDane(dana);
	}
	
	public void ustawReferencj�(Kontroler kontroler) {
		//this.kontroler = kontroler;
		//this.widokWiadomo�ci = new WidokWiadomo�ci();
		//widokWiadomo�ci.ustawReferencj�(kontroler);
	}
}
