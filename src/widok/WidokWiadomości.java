package widok;

import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kontroler.Kontroler;
import procesor.wej�cie.Meil;

public class WidokWiadomo�ci {
	private Stage primaryStage; 
	private Kontroler kontroler;
	private VBox panelWiadomo�ci;
	private FlowPane panelNag��wkowy;
    private Font czcionkaNag��wkowa;
	private Label tekstNag��wkowy;
	private FlowPane panelOdst�puPodNag��wkiem;
	private FlowPane panelFiltru;
    private Font czcionkaFiltru;
	private Label tekstFiltru;
	private FlowPane panelOdst�puPodFiltrem;
	private javafx.scene.layout.FlowPane panelPodFiltrem;
    private Font czcionkaPodFiltrem;	
	private Label tekstNazwyPola;
	private Label tekstOperatora;
	private Label tekstWarto�ci;
	private VBox panelWarto�ci;
	private javafx.scene.layout.FlowPane panelWierszaDanych;
	private javafx.scene.control.ComboBox comboNazwy;
	private javafx.scene.control.ComboBox comboOperatora;
	private javafx.scene.control.TextField poleWarto�ci;
	private javafx.scene.control.Button przyciskUsu�;
	private javafx.scene.control.CheckBox checkBoxAktywny;
	private FlowPane panelprzyciskuWyszukaj;
	private Button Wyszukaj;
	private FlowPane panelOdst�puPodPrzyciskiem;
	private FlowPane panelNag��wkowyWynik�w;
	private Label tekstNag��wkowyWynik�w;
	private FlowPane panelOdst�puNadWynikami;
	private BorderPane panelDanychWynik�w;
	private TableView tabelaWynik�w;
	private TableColumn kolumnaEmail;
	private TableColumn kolumnaDataPrzes�ania;
	private TableColumn kolumnaTemat;
	private TableColumn kolumnaTre��;
	private ObservableList<Meil> dane;
	private javafx.scene.control.ScrollPane panelPrzewijaniaWynik�w;
	private FlowPane panelOdst�puPodTabel�;
	private FlowPane panelDolny;
	private Button przyciskWczytaj;
    
	public WidokWiadomo�ci(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		//Inicjalizacja kontrolek
		panelWiadomo�ci = new javafx.scene.layout.VBox();
		
		panelNag��wkowy = new FlowPane();
		czcionkaNag��wkowa = new javafx.scene.text.Font("Courier", 25);
		tekstNag��wkowy = new javafx.scene.control.Label("Lista wiadomo�ci");
		
		panelOdst�puPodNag��wkiem = new FlowPane();
		
		panelFiltru = new FlowPane();
		czcionkaFiltru = new javafx.scene.text.Font("Courier", 20);
		tekstFiltru = new Label("Filtr listy wiadomo�ci");
		
		panelOdst�puPodFiltrem = new FlowPane();
		
		panelPodFiltrem = new javafx.scene.layout.FlowPane();		
		czcionkaPodFiltrem = new Font("Courier", 14);
		tekstNazwyPola = new javafx.scene.control.Label("Nazwa pola");
		tekstOperatora = new javafx.scene.control.Label("Operator     Warto��");
		tekstWarto�ci = new javafx.scene.control.Label("Warto��");		
		
		panelWarto�ci = new VBox();		
		panelWierszaDanych = new javafx.scene.layout.FlowPane();
		String[] tekstyNazw = { "Nadawca" };
		comboNazwy = new javafx.scene.control.ComboBox();
		String[] tekstyOperator�w = { "ZAWIERA" };
		comboOperatora = new javafx.scene.control.ComboBox();
		poleWarto�ci = new javafx.scene.control.TextField();
		przyciskUsu� = new javafx.scene.control.Button("Usu�");
		checkBoxAktywny = new javafx.scene.control.CheckBox("Aktywny");
		
		panelprzyciskuWyszukaj = new FlowPane();
		Wyszukaj = new Button("Wyszukaj");
		
		panelOdst�puPodPrzyciskiem = new FlowPane();
		
		panelNag��wkowyWynik�w = new FlowPane();
		tekstNag��wkowyWynik�w = new Label("Wyniki wyszukiwania");
		
		panelOdst�puNadWynikami = new FlowPane();
		
		panelDanychWynik�w = new BorderPane();
		tabelaWynik�w = new TableView();
		kolumnaEmail = new TableColumn("Od");
		kolumnaEmail.setMinWidth(400);
		kolumnaEmail.setCellValueFactory(new PropertyValueFactory<>("nadawca"));
		kolumnaDataPrzes�ania = new TableColumn("Data przes�ania");
		kolumnaDataPrzes�ania.setMinWidth(250);
		kolumnaDataPrzes�ania.setCellValueFactory(new PropertyValueFactory<>("dataPrzes�ania"));
		kolumnaTemat = new TableColumn("Temat");
		kolumnaTemat.setMinWidth(300);
		kolumnaTemat.setCellValueFactory(new PropertyValueFactory<>("temat"));
		kolumnaTre�� = new TableColumn("Tre��");
		kolumnaTre��.setMinWidth(300);
		kolumnaTre��.setCellValueFactory(new PropertyValueFactory<>("tre��"));
		
		dane = FXCollections.observableArrayList();
		
		panelPrzewijaniaWynik�w = new javafx.scene.control.ScrollPane();
		
		panelOdst�puPodTabel� = new FlowPane();
		
		panelDolny = new FlowPane();
		przyciskWczytaj = new Button("Wczytaj");
		
		//Konfiguracja kontrolek
		panelWiadomo�ci.setPadding(new Insets(0, 5, 5, 5));
		
		panelNag��wkowy.setStyle("-fx-background-color: #696969;");
		panelNag��wkowy.setPrefHeight(55);
		//panelNag��wkowy.setMaxHeight(panelNag��wkowy.getPrefHeight());
		panelNag��wkowy.setMinHeight(panelNag��wkowy.getPrefHeight());
		panelNag��wkowy.setAlignment(Pos.CENTER);
		tekstNag��wkowy.setFont(czcionkaNag��wkowa);
		tekstNag��wkowy.setTextFill(Color.WHITE);

		panelOdst�puPodNag��wkiem.setPrefHeight(5);
		panelOdst�puPodNag��wkiem.setMaxHeight(panelOdst�puPodNag��wkiem.getPrefHeight());
		panelOdst�puPodNag��wkiem.setMinHeight(panelOdst�puPodNag��wkiem.getPrefHeight());

		panelFiltru.setStyle("-fx-background-color: #CECECE;");
		panelFiltru.setPrefHeight(31);
		//panelFiltru.setMaxHeight(panelFiltru.getPrefHeight());
		panelFiltru.setMinHeight(panelFiltru.getPrefHeight());
		panelFiltru.setAlignment(Pos.CENTER);
		tekstFiltru.setFont(czcionkaFiltru);
		tekstFiltru.setTextFill(Color.BLACK);
		
		panelOdst�puPodFiltrem.setPrefHeight(2);
		panelOdst�puPodFiltrem.setMaxHeight(panelOdst�puPodFiltrem.getPrefHeight());
		panelOdst�puPodFiltrem.setMinHeight(panelOdst�puPodFiltrem.getPrefHeight());
		
		panelPodFiltrem.setStyle("-fx-background-color: #ADD8E6;");
		panelPodFiltrem.setPrefHeight(30);
		//panelPodFiltrem.setMaxHeight(panelPodFiltrem.getPrefHeight());
		panelPodFiltrem.setMinHeight(panelPodFiltrem.getPrefHeight());
		panelPodFiltrem.setAlignment(Pos.CENTER_LEFT);
		tekstNazwyPola.setFont(czcionkaPodFiltrem);
		tekstNazwyPola.setTextFill(Color.BLACK);
		
		panelWarto�ci.setPrefHeight(120);
		//panelWarto�ci.setMaxHeight(panelWarto�ci.getPrefHeight());
		panelWarto�ci.setMinHeight(panelWarto�ci.getPrefHeight());
		panelWarto�ci.setAlignment(Pos.CENTER);		 
		
		/*comboNazwy.setPreferredSize(new Dimension(390, 25));
		comboNazwy.setFont(czcionkaPodFiltrem);
		comboNazwy.setBackground(new Color(206,206,206));
		comboOperatora.setPreferredSize(new Dimension(110, 25));
		comboOperatora.setFont(czcionkaPodFiltrem);
		comboOperatora.setBackground(new Color(206,206,206));
		poleWarto�ci.setPreferredSize(new Dimension(390, 25));
		poleWarto�ci.setFont(czcionkaPodFiltrem);
		poleWarto�ci.setBackground(Color.WHITE);
		przyciskUsu�.setPreferredSize(new Dimension(80, 25));
		przyciskUsu�.setFont(czcionkaPodFiltrem);
		przyciskUsu�.setBackground(new Color(206,206,206));
		checkBoxAktywny.setPreferredSize(new Dimension(80, 25));
		checkBoxAktywny.setFont(czcionkaPodFiltrem);
		checkBoxAktywny.setSelected(true);	*/	
		
		panelprzyciskuWyszukaj.setPrefHeight(50);
		//panelprzyciskuWyszukaj.setMaxHeight(panelprzyciskuWyszukaj.getPrefHeight());
		panelprzyciskuWyszukaj.setMinHeight(panelprzyciskuWyszukaj.getPrefHeight());
		panelprzyciskuWyszukaj.setAlignment(Pos.CENTER_RIGHT);	
		panelprzyciskuWyszukaj.setPadding(new Insets(0, 7, 0, 0));
		Wyszukaj.setPrefSize(200, 40);
		Wyszukaj.setFont(czcionkaPodFiltrem);
		//Wyszukaj.setStyle("-fx-background-color: #CECECE;");
		//tekstNazwyPola.setFont(czcionkaPodFiltrem);
		//tekstNazwyPola.setTextFill(Color.BLACK);
		/*panelprzyciskuWyszukaj.setLayout(new FlowLayout(FlowLayout.RIGHT));	
		Wyszukaj.setPreferredSize(new Dimension(200, 40));
		Wyszukaj.setFont(czcionkaPodFiltrem);
		Wyszukaj.setBackground(new Color(206,206,206));*/
		
        //Przypisanie akcji do przycisku 
		/*Wyszukaj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                kontroler.za�adujDane("C:\\Downloads\\message.eml");
            }           
        });  */
		panelOdst�puPodPrzyciskiem.setPrefHeight(9);
		panelOdst�puPodPrzyciskiem.setMaxHeight(panelOdst�puPodPrzyciskiem.getPrefHeight());
		panelOdst�puPodPrzyciskiem.setMinHeight(panelOdst�puPodPrzyciskiem.getPrefHeight());
			
		panelNag��wkowyWynik�w.setStyle("-fx-background-color: #CECECE;");
		panelNag��wkowyWynik�w.setPrefHeight(39);
		//panelNag��wkowyWynik�w.setMaxHeight(panelNag��wkowyWynik�w.getPrefHeight());
		panelNag��wkowyWynik�w.setMinHeight(panelNag��wkowyWynik�w.getPrefHeight());
		panelNag��wkowyWynik�w.setAlignment(Pos.CENTER);
		tekstNag��wkowyWynik�w.setFont(czcionkaFiltru);
		tekstNag��wkowyWynik�w.setTextFill(Color.BLACK);
				
		panelOdst�puNadWynikami.setPrefHeight(1);
		panelOdst�puNadWynikami.setMaxHeight(panelOdst�puNadWynikami.getPrefHeight());
		panelOdst�puNadWynikami.setMinHeight(panelOdst�puNadWynikami.getPrefHeight());
		
		panelDanychWynik�w.setStyle("-fx-background-color: #A9A9A9; -fx-border-color: black");
		panelDanychWynik�w.setPrefHeight(453);
		//panelDanychWynik�w.setMaxHeight(panelDanychWynik�w.getPrefHeight());
		panelDanychWynik�w.setMinHeight(panelDanychWynik�w.getPrefHeight());
		tabelaWynik�w.setStyle("-fx-background-color: #A9A9A9");

		//tabelaWynik�w.get
		//panelDanychWynik�w.setAlignment(Pos.CENTER);
		//panelDanychWynik�w.setStyle("-fx-border-color: black");
		/*panelDanychWynik�w.setLayout(new BoxLayout(panelDanychWynik�w, BoxLayout.Y_AXIS));
		//panelDanychWynik�w.setLayout(new GridLayout());
		//panelDanychWynik�w.setLayout(new BorderLayout());
		//panelDanychWynik�w.setBackground(new Color(169,169,169));
		panelDanychWynik�w.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelDanychWynik�w.setPreferredSize(new Dimension(1600, 450));
		panelDanychWynik�w.setMinimumSize(panelDanychWynik�w.getPreferredSize());
		panelDanychWynik�w.setMaximumSize(panelDanychWynik�w.getPreferredSize());
		//panelDanychWynik�w.setOpaque(true);*/
		
		//tabelaWynik�w
		/*tabelaWynik�w.setModel(new javax.swing.table.DefaultTableModel(
                new Object [1][4],
                new String [] {
                    "Nadawca", "Nag��wek 2", "Nag��wek 3", "..."
                }
            )); 
		tabelaWynik�w.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaWynik�w.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabelaWynik�w.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabelaWynik�w.getColumnModel().getColumn(2).setPreferredWidth(250);
		tabelaWynik�w.getColumnModel().getColumn(3).setPreferredWidth(200);
		tabelaWynik�w.getTableHeader().setBackground(Color.WHITE);
		//tabelaWynik�w.setShowHorizontalLines(false);
		((DefaultTableCellRenderer)tabelaWynik�w.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//tabelaWynik�w.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		//tabelaWynik�w.getColumnModel().setColumnMargin(7);
		//tabelaWynik�w.setBackground(new Color(169,169,169));
		//tabelaWynik�w.getTableHeader().setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		tabelaWynik�w.setEnabled(true);
		panelPrzewijaniaWynik�w.setViewportView(tabelaWynik�w);
		panelPrzewijaniaWynik�w.setBackground(new Color(169,169,169));
		panelPrzewijaniaWynik�w.getViewport().setBackground(new Color(169,169,169));*/
		
		panelOdst�puPodTabel�.setPrefHeight(38);
		panelOdst�puPodTabel�.setMaxHeight(panelOdst�puPodTabel�.getPrefHeight());
		panelOdst�puPodTabel�.setMinHeight(panelOdst�puPodTabel�.getPrefHeight());
			
		//panelDolny.setPrefHeight(453);
		//panelDanychWynik�w.setMaxHeight(panelDanychWynik�w.getPrefHeight());
		panelDolny.setMinHeight(panelDolny.getPrefHeight());
		panelDolny.setAlignment(Pos.CENTER_RIGHT);
		panelDolny.setPadding(new Insets(0, 7, 0, 0));
		przyciskWczytaj.setPrefSize(100, 40);
		przyciskWczytaj.setFont(czcionkaPodFiltrem);
		
		przyciskWczytaj.setOnAction((event) -> {		    
		    FileChooser fileChooser = new FileChooser();
		    fileChooser.setTitle("Wybierz pliki eml");
	        fileChooser.setInitialDirectory (new File(System.getProperty("user.home"))); 
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("EML", "*.eml")
                    );
	        		    
            List<File> list = fileChooser.showOpenMultipleDialog(primaryStage);
                if (list != null) {
                	kontroler.za�adujDane(list);
                }
		});
		
		//Wstawienie kontrolek
		panelWiadomo�ci.getChildren().add(panelNag��wkowy);
		panelNag��wkowy.getChildren().add(tekstNag��wkowy);
		
		panelWiadomo�ci.getChildren().add(panelOdst�puPodNag��wkiem);
		
		panelWiadomo�ci.getChildren().add(panelFiltru);
		panelFiltru.getChildren().add(tekstFiltru);
		
		panelWiadomo�ci.getChildren().add(panelOdst�puPodFiltrem);
		
		panelWiadomo�ci.getChildren().add(panelPodFiltrem);
		panelPodFiltrem.getChildren().add(tekstNazwyPola);
		panelPodFiltrem.getChildren().add(tekstOperatora);
		
		panelWiadomo�ci.getChildren().add(panelWarto�ci);
		panelWarto�ci.getChildren().add(panelWierszaDanych);
		panelWierszaDanych.getChildren().add(comboNazwy);
		panelWierszaDanych.getChildren().add(comboOperatora);
		panelWierszaDanych.getChildren().add(poleWarto�ci);
		panelWierszaDanych.getChildren().add(przyciskUsu�);
		panelWierszaDanych.getChildren().add(checkBoxAktywny);
		
		panelWiadomo�ci.getChildren().add(panelprzyciskuWyszukaj);
		panelprzyciskuWyszukaj.getChildren().add(Wyszukaj);
		
		panelWiadomo�ci.getChildren().add(panelOdst�puPodPrzyciskiem);
		
		panelWiadomo�ci.getChildren().add(panelNag��wkowyWynik�w);
		panelNag��wkowyWynik�w.getChildren().add(tekstNag��wkowyWynik�w);
		
		panelWiadomo�ci.getChildren().add(panelOdst�puNadWynikami);
		
		panelWiadomo�ci.getChildren().add(panelDanychWynik�w);
		//panelDanychWynik�w.getChildren().add(tabelaWynik�w);
		panelDanychWynik�w.setCenter(tabelaWynik�w);
		tabelaWynik�w.getColumns().addAll(kolumnaEmail, kolumnaDataPrzes�ania, kolumnaTemat, kolumnaTre��);
		tabelaWynik�w.setItems(dane);

		//panelDanychWynik�w.getChildren().add(panelPrzewijaniaWynik�w);
		
		panelWiadomo�ci.getChildren().add(panelOdst�puPodTabel�);
		
		panelWiadomo�ci.getChildren().add(panelDolny);
		panelDolny.getChildren().add(przyciskWczytaj);
	}
	
	public void ustawReferencj�(Kontroler kontroler) {
		this.kontroler = kontroler;
	}
	
	public void wy�wietlDane(List<Meil> meile) {
		for (Meil meil : meile) {
			dane.add(meil);
		}
	}
	
	public VBox pobierzKontenerWiadomo�ci() {
		return panelWiadomo�ci;
	}
}
