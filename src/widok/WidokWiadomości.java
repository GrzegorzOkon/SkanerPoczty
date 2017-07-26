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
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
	private FlowPane panelWierszaDanych;
	private ComboBox poleNadawcy;
	private ComboBox poleOperatora;
	private DatePicker poleDaty;
	private TextField poleDanych;
	private javafx.scene.control.Button przyciskUsuñ;
	private javafx.scene.control.CheckBox checkBoxAktywny;
	private FlowPane panelprzyciskuWyszukaj;
	private Button wyszukaj;
	private FlowPane panelOdstêpuPodPrzyciskiem;
	private FlowPane panelNag³ówkowyWyników;
	private Label tekstNag³ówkowyWyników;
	private FlowPane panelOdstêpuNadWynikami;
	private BorderPane panelDanychWyników;
	private TableView tabelaWyników;
	private TableColumn kolumnaEmail;
	private TableColumn kolumnaOdbiorca;
	private TableColumn kolumnaDataPrzes³ania;
	private TableColumn kolumnaTemat;
	private TableColumn kolumnaIP;
	private ObservableList<Meil> dane;
	private FilteredList<Meil> przefiltrowaneDane;
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
		
		panelPodFiltrem = new FlowPane();	
		panelPodFiltrem.setHgap(300);
		czcionkaPodFiltrem = new Font("Courier", 14);
		tekstNazwyPola = new Label("Nazwa pola");
		tekstOperatora = new Label("Operator");
		tekstWartoœci = new Label("Wartoœæ");		
		
		panelWartoœci = new VBox();		
		panelWierszaDanych = new FlowPane();
		panelWierszaDanych.setHgap(7);

		poleNadawcy = new ComboBox();
		poleNadawcy.setPrefSize(380, 25);
		poleNadawcy.setStyle("-fx-background-color: #CECECE");
		poleNadawcy.getItems().addAll("Nadawca", "Odbiorca", "Data przes³ania", "Temat", "IP");
		poleNadawcy.setValue("Odbiorca");
		poleNadawcy.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) { 
            	switch (t1) {
            		case "Nadawca": 
            			poleOperatora.getItems().clear();
            			poleOperatora.getItems().addAll("=", "ZAWIERA"); 
            			poleOperatora.setValue("=");    
            			panelWierszaDanych.getChildren().remove(2);
            			panelWierszaDanych.getChildren().add(2, poleDanych);
            			break;
            		case "Odbiorca" : 
            			poleOperatora.getItems().clear();
            			poleOperatora.getItems().addAll("=", "ZAWIERA"); 
            			poleOperatora.setValue("=");  
            			panelWierszaDanych.getChildren().remove(2);
            			panelWierszaDanych.getChildren().add(2, poleDanych);
            			break;
            		case "Data przes³ania" : 
            			poleOperatora.getItems().clear();
            			poleOperatora.getItems().addAll(">", ">=", "<", "<="); 
            			poleOperatora.setValue(">="); 
            			panelWierszaDanych.getChildren().remove(2);
            			panelWierszaDanych.getChildren().add(2, poleDaty);
            			break;
            		case "Temat" : 
            			poleOperatora.getItems().clear();
            			poleOperatora.getItems().addAll("=", "ZAWIERA"); 
            			poleOperatora.setValue("="); 
            			panelWierszaDanych.getChildren().remove(2);
            			panelWierszaDanych.getChildren().add(2, poleDanych);
            			break;
            		case "IP" : 
            			poleOperatora.getItems().clear();
            			poleOperatora.getItems().addAll("=", "ZAWIERA"); 
            			poleOperatora.setValue("="); 
            			panelWierszaDanych.getChildren().remove(2);
            			panelWierszaDanych.getChildren().add(2, poleDanych);
            			break;
            	};         
            }    
        });
		poleOperatora = new ComboBox();
		poleOperatora.setPrefSize(110, 25);
		poleOperatora.setStyle("-fx-background-color: #CECECE");
		poleDaty = new DatePicker();
		poleDaty.setPrefSize(365, 25);
		poleDaty.setOnAction(new EventHandler() {
		     public void handle(Event t) {
		         LocalDate date = poleDaty.getValue();
		     }
		 });
		poleDaty.setEditable(false);
		poleDanych = new TextField();
		poleDanych.setPrefSize(365, 25);
		przyciskUsuñ = new Button("Usuñ");
		checkBoxAktywny = new CheckBox("Aktywny");
		checkBoxAktywny.setSelected(true);
		
		panelprzyciskuWyszukaj = new FlowPane();
		wyszukaj = new Button("Wyszukaj");
		wyszukaj.setOnAction((event) -> {		    
			przefiltrowaneDane = new FilteredList<>(dane);
			
			if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Nadawca")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                // Porównaj nadawcê z ka¿dym wierszem filtrowanego tekstu.
		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getNadawca().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                // Porównaj nadawcê z ka¿dym wierszem filtrowanego tekstu.
		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getNadawca().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });	
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Odbiorca")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getOdbiorcy().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getOdbiorcy().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });	
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Data przes³ania")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals(">")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDaty.getValue().equals("")) {
		                    return true;
		                }

		                LocalDate filtrDaty = poleDaty.getValue();
		                String przes³anaData = meil.getDataPrzes³ania().substring(4, 10) + " " + meil.getDataPrzes³ania().substring(25, 29);

		                Locale l = Locale.US ;
		                DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
		                LocalDate localDate = LocalDate.parse( przes³anaData , f );
		                
		                if (localDate.isAfter(filtrDaty)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals(">=")) {
					przefiltrowaneDane.setPredicate(meil-> {
			            // Je¿eli filtr jest pusty wyœwietl wszystkie dane
						if (poleDaty.getValue().equals("")) {
							return true;
			            }

			            LocalDate filtrDaty = poleDaty.getValue();
			            String przes³anaData = meil.getDataPrzes³ania().substring(4, 10) + " " + meil.getDataPrzes³ania().substring(25, 29);

			            Locale l = Locale.US ;
			            DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
			            LocalDate localDate = LocalDate.parse( przes³anaData , f );
			                
			            if (localDate.isAfter(filtrDaty) || localDate.isEqual(filtrDaty)) {
			            	return true; // Filtr przypasowuje tekst.
			            } 
			            return false; // Nie spelania wymagañ.
			        });		
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("<")) {
					przefiltrowaneDane.setPredicate(meil-> {
			            // Je¿eli filtr jest pusty wyœwietl wszystkie dane
						if (poleDaty.getValue().equals("")) {
							return true;
			            }

			            LocalDate filtrDaty = poleDaty.getValue();
			            String przes³anaData = meil.getDataPrzes³ania().substring(4, 10) + " " + meil.getDataPrzes³ania().substring(25, 29);

			            Locale l = Locale.US ;
			            DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
			            LocalDate localDate = LocalDate.parse( przes³anaData , f );
			                
			            if (localDate.isBefore(filtrDaty)) {
			            	return true; // Filtr przypasowuje tekst.
			            } 
			            return false; // Nie spelania wymagañ.
					});	
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("<=")) {
					  przefiltrowaneDane.setPredicate(meil-> {
				      // Je¿eli filtr jest pusty wyœwietl wszystkie dane
					  if (poleDaty.getValue().equals("")) {
					      return true;
				      }

				      LocalDate filtrDaty = poleDaty.getValue();
				      String przes³anaData = meil.getDataPrzes³ania().substring(4, 10) + " " + meil.getDataPrzes³ania().substring(25, 29);

				      Locale l = Locale.US ;
				      DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
				      LocalDate localDate = LocalDate.parse( przes³anaData , f );
				                
				      if (localDate.isBefore(filtrDaty) || localDate.isEqual(filtrDaty)) {
				          return true; // Filtr przypasowuje tekst.
				      } 
				      return false; // Nie spelania wymagañ.
				  });
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Temat")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getTemat().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getTemat().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });	
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("IP")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getAdresIP().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Je¿eli filtr jest pusty wyœwietl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getAdresIP().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagañ.
		            });	
				}
			}
			
			tabelaWyników.setItems(przefiltrowaneDane);
		});
		
		panelOdstêpuPodPrzyciskiem = new FlowPane();
		
		panelNag³ówkowyWyników = new FlowPane();
		tekstNag³ówkowyWyników = new Label("Wyniki wyszukiwania");
		
		panelOdstêpuNadWynikami = new FlowPane();
		
		panelDanychWyników = new BorderPane();
		tabelaWyników = new TableView();
		kolumnaEmail = new TableColumn("Nadawca");
		kolumnaEmail.setMinWidth(300);
		kolumnaEmail.setCellValueFactory(new PropertyValueFactory<>("nadawca"));
		kolumnaOdbiorca = new TableColumn("Odbiorca");
		kolumnaOdbiorca.setMinWidth(300);
		kolumnaOdbiorca.setCellValueFactory(new PropertyValueFactory<>("odbiorcy"));
		kolumnaDataPrzes³ania = new TableColumn("Data przes³ania");
		kolumnaDataPrzes³ania.setMinWidth(250);
		kolumnaDataPrzes³ania.setCellValueFactory(new PropertyValueFactory<>("dataPrzes³ania"));
		kolumnaTemat = new TableColumn("Temat");
		kolumnaTemat.setMinWidth(300);
		kolumnaTemat.setCellValueFactory(new PropertyValueFactory<>("temat"));
		kolumnaIP = new TableColumn("IP");
		kolumnaIP.setMinWidth(130);
		kolumnaIP.setCellValueFactory(new PropertyValueFactory<>("adresIP"));
		
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
		wyszukaj.setPrefSize(200, 40);
		wyszukaj.setFont(czcionkaPodFiltrem);
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
		
		/*tabelaWyników.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		panelPodFiltrem.getChildren().add(tekstWartoœci);
		
		panelWiadomoœci.getChildren().add(panelWartoœci);
		panelWartoœci.getChildren().add(panelWierszaDanych);
		panelWierszaDanych.getChildren().add(poleNadawcy);
		panelWierszaDanych.getChildren().add(poleOperatora);
		panelWierszaDanych.getChildren().add(poleDanych);
		//panelWierszaDanych.getChildren().add(poleDaty);
		//panelWierszaDanych.getChildren().add(przyciskUsuñ);
		panelWierszaDanych.getChildren().add(checkBoxAktywny);
		
		panelWiadomoœci.getChildren().add(panelprzyciskuWyszukaj);
		panelprzyciskuWyszukaj.getChildren().add(wyszukaj);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuPodPrzyciskiem);
		
		panelWiadomoœci.getChildren().add(panelNag³ówkowyWyników);
		panelNag³ówkowyWyników.getChildren().add(tekstNag³ówkowyWyników);
		
		panelWiadomoœci.getChildren().add(panelOdstêpuNadWynikami);
		
		panelWiadomoœci.getChildren().add(panelDanychWyników);
		//panelDanychWyników.getChildren().add(tabelaWyników);
		panelDanychWyników.setCenter(tabelaWyników);
		tabelaWyników.getColumns().addAll(kolumnaEmail, kolumnaOdbiorca, kolumnaDataPrzes³ania, kolumnaTemat, kolumnaIP);
		tabelaWyników.setItems(dane);
		
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
