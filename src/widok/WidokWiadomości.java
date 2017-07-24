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
import procesor.wejœcie.Meil;

public class WidokWiadomoœci {
	private Stage primaryStage; 
	private Kontroler kontroler;
	private VBox panelWiadomoœci;
	private FlowPane panelNag³ówkowy;
    private Font czcionkaNag³ówkowa;
	private Label tekstNag³ówkowy;
	private FlowPane panelOdstêpuPodNag³ówkiem;
	private FlowPane panelFiltru;
    private Font czcionkaFiltru;
	private Label tekstFiltru;
	private FlowPane panelOdstêpuPodFiltrem;
	private javafx.scene.layout.FlowPane panelPodFiltrem;
    private Font czcionkaPodFiltrem;	
	private Label tekstNazwyPola;
	private Label tekstOperatora;
	private Label tekstWartoœci;
	private VBox panelWartoœci;
	private javafx.scene.layout.FlowPane panelWierszaDanych;
	private javafx.scene.control.ComboBox comboNazwy;
	private javafx.scene.control.ComboBox comboOperatora;
	private javafx.scene.control.TextField poleWartoœci;
	private javafx.scene.control.Button przyciskUsuñ;
	private javafx.scene.control.CheckBox checkBoxAktywny;
	private FlowPane panelprzyciskuWyszukaj;
	private Button Wyszukaj;
	private FlowPane panelOdstêpuPodPrzyciskiem;
	private FlowPane panelNag³ówkowyWyników;
	private Label tekstNag³ówkowyWyników;
	private FlowPane panelOdstêpuNadWynikami;
	private BorderPane panelDanychWyników;
	private TableView tabelaWyników;
	private TableColumn kolumnaEmail;
	private TableColumn kolumnaDataPrzes³ania;
	private TableColumn kolumnaTemat;
	private TableColumn kolumnaTreœæ;
	private ObservableList<Meil> dane;
	private javafx.scene.control.ScrollPane panelPrzewijaniaWyników;
	private FlowPane panelOdstêpuPodTabel¹;
	private FlowPane panelDolny;
	private Button przyciskWczytaj;
    
	public WidokWiadomoœci(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		//Inicjalizacja kontrolek
		panelWiadomoœci = new javafx.scene.layout.VBox();
		
		panelNag³ówkowy = new FlowPane();
		czcionkaNag³ówkowa = new javafx.scene.text.Font("Courier", 25);
		tekstNag³ówkowy = new javafx.scene.control.Label("Lista wiadomoœci");
		
		panelOdstêpuPodNag³ówkiem = new FlowPane();
		
		panelFiltru = new FlowPane();
		czcionkaFiltru = new javafx.scene.text.Font("Courier", 20);
		tekstFiltru = new Label("Filtr listy wiadomoœci");
		
		panelOdstêpuPodFiltrem = new FlowPane();
		
		panelPodFiltrem = new javafx.scene.layout.FlowPane();		
		czcionkaPodFiltrem = new Font("Courier", 14);
		tekstNazwyPola = new javafx.scene.control.Label("Nazwa pola");
		tekstOperatora = new javafx.scene.control.Label("Operator     Wartoœæ");
		tekstWartoœci = new javafx.scene.control.Label("Wartoœæ");		
		
		panelWartoœci = new VBox();		
		panelWierszaDanych = new javafx.scene.layout.FlowPane();
		String[] tekstyNazw = { "Nadawca" };
		comboNazwy = new javafx.scene.control.ComboBox();
		String[] tekstyOperatorów = { "ZAWIERA" };
		comboOperatora = new javafx.scene.control.ComboBox();
		poleWartoœci = new javafx.scene.control.TextField();
		przyciskUsuñ = new javafx.scene.control.Button("Usuñ");
		checkBoxAktywny = new javafx.scene.control.CheckBox("Aktywny");
		
		panelprzyciskuWyszukaj = new FlowPane();
		Wyszukaj = new Button("Wyszukaj");
		
		panelOdstêpuPodPrzyciskiem = new FlowPane();
		
		panelNag³ówkowyWyników = new FlowPane();
		tekstNag³ówkowyWyników = new Label("Wyniki wyszukiwania");
		
		panelOdstêpuNadWynikami = new FlowPane();
		
		panelDanychWyników = new BorderPane();
		tabelaWyników = new TableView();
		kolumnaEmail = new TableColumn("Od");
		kolumnaEmail.setMinWidth(400);
		kolumnaEmail.setCellValueFactory(new PropertyValueFactory<>("nadawca"));
		kolumnaDataPrzes³ania = new TableColumn("Data przes³ania");
		kolumnaDataPrzes³ania.setMinWidth(250);
		kolumnaDataPrzes³ania.setCellValueFactory(new PropertyValueFactory<>("dataPrzes³ania"));
		kolumnaTemat = new TableColumn("Temat");
		kolumnaTemat.setMinWidth(300);
		kolumnaTemat.setCellValueFactory(new PropertyValueFactory<>("temat"));
		kolumnaTreœæ = new TableColumn("Treœæ");
		kolumnaTreœæ.setMinWidth(300);
		kolumnaTreœæ.setCellValueFactory(new PropertyValueFactory<>("treœæ"));
		
		dane = FXCollections.observableArrayList();
		
		panelPrzewijaniaWyników = new javafx.scene.control.ScrollPane();
		
		panelOdstêpuPodTabel¹ = new FlowPane();
		
		panelDolny = new FlowPane();
		przyciskWczytaj = new Button("Wczytaj");
		
		//Konfiguracja kontrolek
		panelWiadomoœci.setPadding(new Insets(0, 5, 5, 5));
		
		panelNag³ówkowy.setStyle("-fx-background-color: #696969;");
		panelNag³ówkowy.setPrefHeight(55);
		//panelNag³ówkowy.setMaxHeight(panelNag³ówkowy.getPrefHeight());
		panelNag³ówkowy.setMinHeight(panelNag³ówkowy.getPrefHeight());
		panelNag³ówkowy.setAlignment(Pos.CENTER);
		tekstNag³ówkowy.setFont(czcionkaNag³ówkowa);
		tekstNag³ówkowy.setTextFill(Color.WHITE);

		panelOdstêpuPodNag³ówkiem.setPrefHeight(5);
		panelOdstêpuPodNag³ówkiem.setMaxHeight(panelOdstêpuPodNag³ówkiem.getPrefHeight());
		panelOdstêpuPodNag³ówkiem.setMinHeight(panelOdstêpuPodNag³ówkiem.getPrefHeight());

		panelFiltru.setStyle("-fx-background-color: #CECECE;");
		panelFiltru.setPrefHeight(31);
		//panelFiltru.setMaxHeight(panelFiltru.getPrefHeight());
		panelFiltru.setMinHeight(panelFiltru.getPrefHeight());
		panelFiltru.setAlignment(Pos.CENTER);
		tekstFiltru.setFont(czcionkaFiltru);
		tekstFiltru.setTextFill(Color.BLACK);
		
		panelOdstêpuPodFiltrem.setPrefHeight(2);
		panelOdstêpuPodFiltrem.setMaxHeight(panelOdstêpuPodFiltrem.getPrefHeight());
		panelOdstêpuPodFiltrem.setMinHeight(panelOdstêpuPodFiltrem.getPrefHeight());
		
		panelPodFiltrem.setStyle("-fx-background-color: #ADD8E6;");
		panelPodFiltrem.setPrefHeight(30);
		//panelPodFiltrem.setMaxHeight(panelPodFiltrem.getPrefHeight());
		panelPodFiltrem.setMinHeight(panelPodFiltrem.getPrefHeight());
		panelPodFiltrem.setAlignment(Pos.CENTER_LEFT);
		tekstNazwyPola.setFont(czcionkaPodFiltrem);
		tekstNazwyPola.setTextFill(Color.BLACK);
		
		panelWartoœci.setPrefHeight(120);
		//panelWartoœci.setMaxHeight(panelWartoœci.getPrefHeight());
		panelWartoœci.setMinHeight(panelWartoœci.getPrefHeight());
		panelWartoœci.setAlignment(Pos.CENTER);		 
		
		/*comboNazwy.setPreferredSize(new Dimension(390, 25));
		comboNazwy.setFont(czcionkaPodFiltrem);
		comboNazwy.setBackground(new Color(206,206,206));
		comboOperatora.setPreferredSize(new Dimension(110, 25));
		comboOperatora.setFont(czcionkaPodFiltrem);
		comboOperatora.setBackground(new Color(206,206,206));
		poleWartoœci.setPreferredSize(new Dimension(390, 25));
		poleWartoœci.setFont(czcionkaPodFiltrem);
		poleWartoœci.setBackground(Color.WHITE);
		przyciskUsuñ.setPreferredSize(new Dimension(80, 25));
		przyciskUsuñ.setFont(czcionkaPodFiltrem);
		przyciskUsuñ.setBackground(new Color(206,206,206));
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
                kontroler.za³adujDane("C:\\Downloads\\message.eml");
            }           
        });  */
		panelOdstêpuPodPrzyciskiem.setPrefHeight(9);
		panelOdstêpuPodPrzyciskiem.setMaxHeight(panelOdstêpuPodPrzyciskiem.getPrefHeight());
		panelOdstêpuPodPrzyciskiem.setMinHeight(panelOdstêpuPodPrzyciskiem.getPrefHeight());
			
		panelNag³ówkowyWyników.setStyle("-fx-background-color: #CECECE;");
		panelNag³ówkowyWyników.setPrefHeight(39);
		//panelNag³ówkowyWyników.setMaxHeight(panelNag³ówkowyWyników.getPrefHeight());
		panelNag³ówkowyWyników.setMinHeight(panelNag³ówkowyWyników.getPrefHeight());
		panelNag³ówkowyWyników.setAlignment(Pos.CENTER);
		tekstNag³ówkowyWyników.setFont(czcionkaFiltru);
		tekstNag³ówkowyWyników.setTextFill(Color.BLACK);
				
		panelOdstêpuNadWynikami.setPrefHeight(1);
		panelOdstêpuNadWynikami.setMaxHeight(panelOdstêpuNadWynikami.getPrefHeight());
		panelOdstêpuNadWynikami.setMinHeight(panelOdstêpuNadWynikami.getPrefHeight());
		
		panelDanychWyników.setStyle("-fx-background-color: #A9A9A9; -fx-border-color: black");
		panelDanychWyników.setPrefHeight(453);
		//panelDanychWyników.setMaxHeight(panelDanychWyników.getPrefHeight());
		panelDanychWyników.setMinHeight(panelDanychWyników.getPrefHeight());
		tabelaWyników.setStyle("-fx-background-color: #A9A9A9");

		//tabelaWyników.get
		//panelDanychWyników.setAlignment(Pos.CENTER);
		//panelDanychWyników.setStyle("-fx-border-color: black");
		/*panelDanychWyników.setLayout(new BoxLayout(panelDanychWyników, BoxLayout.Y_AXIS));
		//panelDanychWyników.setLayout(new GridLayout());
		//panelDanychWyników.setLayout(new BorderLayout());
		//panelDanychWyników.setBackground(new Color(169,169,169));
		panelDanychWyników.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelDanychWyników.setPreferredSize(new Dimension(1600, 450));
		panelDanychWyników.setMinimumSize(panelDanychWyników.getPreferredSize());
		panelDanychWyników.setMaximumSize(panelDanychWyników.getPreferredSize());
		//panelDanychWyników.setOpaque(true);*/
		
		//tabelaWyników
		/*tabelaWyników.setModel(new javax.swing.table.DefaultTableModel(
                new Object [1][4],
                new String [] {
                    "Nadawca", "Nag³ówek 2", "Nag³ówek 3", "..."
                }
            )); 
		tabelaWyników.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaWyników.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabelaWyników.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabelaWyników.getColumnModel().getColumn(2).setPreferredWidth(250);
		tabelaWyników.getColumnModel().getColumn(3).setPreferredWidth(200);
		tabelaWyników.getTableHeader().setBackground(Color.WHITE);
		//tabelaWyników.setShowHorizontalLines(false);
		((DefaultTableCellRenderer)tabelaWyników.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//tabelaWyników.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		//tabelaWyników.getColumnModel().setColumnMargin(7);
		//tabelaWyników.setBackground(new Color(169,169,169));
		//tabelaWyników.getTableHeader().setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		tabelaWyników.setEnabled(true);
		panelPrzewijaniaWyników.setViewportView(tabelaWyników);
		panelPrzewijaniaWyników.setBackground(new Color(169,169,169));
		panelPrzewijaniaWyników.getViewport().setBackground(new Color(169,169,169));*/
		
		panelOdstêpuPodTabel¹.setPrefHeight(38);
		panelOdstêpuPodTabel¹.setMaxHeight(panelOdstêpuPodTabel¹.getPrefHeight());
		panelOdstêpuPodTabel¹.setMinHeight(panelOdstêpuPodTabel¹.getPrefHeight());
			
		//panelDolny.setPrefHeight(453);
		//panelDanychWyników.setMaxHeight(panelDanychWyników.getPrefHeight());
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
                	kontroler.za³adujDane(list);
                }
		});
		
		//Wstawienie kontrolek
		panelWiadomoœci.getChildren().add(panelNag³ówkowy);
		panelNag³ówkowy.getChildren().add(tekstNag³ówkowy);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuPodNag³ówkiem);
		
		panelWiadomoœci.getChildren().add(panelFiltru);
		panelFiltru.getChildren().add(tekstFiltru);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuPodFiltrem);
		
		panelWiadomoœci.getChildren().add(panelPodFiltrem);
		panelPodFiltrem.getChildren().add(tekstNazwyPola);
		panelPodFiltrem.getChildren().add(tekstOperatora);
		
		panelWiadomoœci.getChildren().add(panelWartoœci);
		panelWartoœci.getChildren().add(panelWierszaDanych);
		panelWierszaDanych.getChildren().add(comboNazwy);
		panelWierszaDanych.getChildren().add(comboOperatora);
		panelWierszaDanych.getChildren().add(poleWartoœci);
		panelWierszaDanych.getChildren().add(przyciskUsuñ);
		panelWierszaDanych.getChildren().add(checkBoxAktywny);
		
		panelWiadomoœci.getChildren().add(panelprzyciskuWyszukaj);
		panelprzyciskuWyszukaj.getChildren().add(Wyszukaj);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuPodPrzyciskiem);
		
		panelWiadomoœci.getChildren().add(panelNag³ówkowyWyników);
		panelNag³ówkowyWyników.getChildren().add(tekstNag³ówkowyWyników);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuNadWynikami);
		
		panelWiadomoœci.getChildren().add(panelDanychWyników);
		//panelDanychWyników.getChildren().add(tabelaWyników);
		panelDanychWyników.setCenter(tabelaWyników);
		tabelaWyników.getColumns().addAll(kolumnaEmail, kolumnaDataPrzes³ania, kolumnaTemat, kolumnaTreœæ);
		tabelaWyników.setItems(dane);

		//panelDanychWyników.getChildren().add(panelPrzewijaniaWyników);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuPodTabel¹);
		
		panelWiadomoœci.getChildren().add(panelDolny);
		panelDolny.getChildren().add(przyciskWczytaj);
	}
	
	public void ustawReferencjê(Kontroler kontroler) {
		this.kontroler = kontroler;
	}
	
	public void wyœwietlDane(List<Meil> meile) {
		for (Meil meil : meile) {
			dane.add(meil);
		}
	}
	
	public VBox pobierzKontenerWiadomoœci() {
		return panelWiadomoœci;
	}
}
