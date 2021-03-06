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
import procesor.wejście.Meil;

public class WidokWiadomości {
	private Stage primaryStage; 
	private Kontroler kontroler;
	private VBox panelWiadomości;
	private FlowPane panelNagłówkowy;
    private Font czcionkaNagłówkowa;
	private Label tekstNagłówkowy;
	private FlowPane panelOdstępuPodNagłówkiem;
	private FlowPane panelFiltru;
    private Font czcionkaFiltru;
	private Label tekstFiltru;
	private FlowPane panelOdstępuPodFiltrem;
	private javafx.scene.layout.FlowPane panelPodFiltrem;
    private Font czcionkaPodFiltrem;	
	private Label tekstNazwyPola;
	private Label tekstOperatora;
	private Label tekstWartości;
	private VBox panelWartości;
	private FlowPane panelWierszaDanych;
	private ComboBox poleNadawcy;
	private ComboBox poleOperatora;
	private DatePicker poleDaty;
	private TextField poleDanych;
	private javafx.scene.control.Button przyciskUsuń;
	private javafx.scene.control.CheckBox checkBoxAktywny;
	private FlowPane panelprzyciskuWyszukaj;
	private Button wyszukaj;
	private FlowPane panelOdstępuPodPrzyciskiem;
	private FlowPane panelNagłówkowyWyników;
	private Label tekstNagłówkowyWyników;
	private FlowPane panelOdstępuNadWynikami;
	private BorderPane panelDanychWyników;
	private TableView tabelaWyników;
	private TableColumn kolumnaEmail;
	private TableColumn kolumnaOdbiorca;
	private TableColumn kolumnaDataPrzesłania;
	private TableColumn kolumnaTemat;
	private TableColumn kolumnaIP;
	private ObservableList<Meil> dane;
	private FilteredList<Meil> przefiltrowaneDane;
	private javafx.scene.control.ScrollPane panelPrzewijaniaWyników;
	private FlowPane panelOdstępuPodTabelą;
	private FlowPane panelDolny;
	private Button przyciskWczytaj;
    
	public WidokWiadomości(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		//Inicjalizacja kontrolek
		panelWiadomości = new javafx.scene.layout.VBox();
		
		panelNagłówkowy = new FlowPane();
		czcionkaNagłówkowa = new javafx.scene.text.Font("Courier", 25);
		tekstNagłówkowy = new javafx.scene.control.Label("Lista wiadomości");
		
		panelOdstępuPodNagłówkiem = new FlowPane();
		
		panelFiltru = new FlowPane();
		czcionkaFiltru = new javafx.scene.text.Font("Courier", 20);
		tekstFiltru = new Label("Filtr listy wiadomości");
		
		panelOdstępuPodFiltrem = new FlowPane();
		
		panelPodFiltrem = new FlowPane();	
		panelPodFiltrem.setHgap(300);
		czcionkaPodFiltrem = new Font("Courier", 14);
		tekstNazwyPola = new Label("Nazwa pola");
		tekstOperatora = new Label("Operator");
		tekstWartości = new Label("Wartość");		
		
		panelWartości = new VBox();		
		panelWierszaDanych = new FlowPane();
		panelWierszaDanych.setHgap(7);

		poleNadawcy = new ComboBox();
		poleNadawcy.setPrefSize(380, 25);
		poleNadawcy.setStyle("-fx-background-color: #CECECE");
		poleNadawcy.getItems().addAll("Nadawca", "Odbiorca", "Data przesłania", "Temat", "IP");
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
            		case "Data przesłania" : 
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
		przyciskUsuń = new Button("Usuń");
		checkBoxAktywny = new CheckBox("Aktywny");
		checkBoxAktywny.setSelected(true);
		
		panelprzyciskuWyszukaj = new FlowPane();
		wyszukaj = new Button("Wyszukaj");
		wyszukaj.setOnAction((event) -> {		    
			przefiltrowaneDane = new FilteredList<>(dane);
			
			if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Nadawca")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                // Porównaj nadawcę z każdym wierszem filtrowanego tekstu.
		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getNadawca().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                // Porównaj nadawcę z każdym wierszem filtrowanego tekstu.
		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getNadawca().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });	
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Odbiorca")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getOdbiorcy().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getOdbiorcy().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });	
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Data przesłania")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals(">")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDaty.getValue().equals("")) {
		                    return true;
		                }

		                LocalDate filtrDaty = poleDaty.getValue();
		                String przesłanaData = meil.getDataPrzesłania().substring(4, 10) + " " + meil.getDataPrzesłania().substring(25, 29);

		                Locale l = Locale.US ;
		                DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
		                LocalDate localDate = LocalDate.parse( przesłanaData , f );
		                
		                if (localDate.isAfter(filtrDaty)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals(">=")) {
					przefiltrowaneDane.setPredicate(meil-> {
			            // Jeżeli filtr jest pusty wyświetl wszystkie dane
						if (poleDaty.getValue().equals("")) {
							return true;
			            }

			            LocalDate filtrDaty = poleDaty.getValue();
			            String przesłanaData = meil.getDataPrzesłania().substring(4, 10) + " " + meil.getDataPrzesłania().substring(25, 29);

			            Locale l = Locale.US ;
			            DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
			            LocalDate localDate = LocalDate.parse( przesłanaData , f );
			                
			            if (localDate.isAfter(filtrDaty) || localDate.isEqual(filtrDaty)) {
			            	return true; // Filtr przypasowuje tekst.
			            } 
			            return false; // Nie spelania wymagań.
			        });		
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("<")) {
					przefiltrowaneDane.setPredicate(meil-> {
			            // Jeżeli filtr jest pusty wyświetl wszystkie dane
						if (poleDaty.getValue().equals("")) {
							return true;
			            }

			            LocalDate filtrDaty = poleDaty.getValue();
			            String przesłanaData = meil.getDataPrzesłania().substring(4, 10) + " " + meil.getDataPrzesłania().substring(25, 29);

			            Locale l = Locale.US ;
			            DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
			            LocalDate localDate = LocalDate.parse( przesłanaData , f );
			                
			            if (localDate.isBefore(filtrDaty)) {
			            	return true; // Filtr przypasowuje tekst.
			            } 
			            return false; // Nie spelania wymagań.
					});	
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("<=")) {
					  przefiltrowaneDane.setPredicate(meil-> {
				      // Jeżeli filtr jest pusty wyświetl wszystkie dane
					  if (poleDaty.getValue().equals("")) {
					      return true;
				      }

				      LocalDate filtrDaty = poleDaty.getValue();
				      String przesłanaData = meil.getDataPrzesłania().substring(4, 10) + " " + meil.getDataPrzesłania().substring(25, 29);

				      Locale l = Locale.US ;
				      DateTimeFormatter f = DateTimeFormatter.ofPattern( "MMM d yyyy" , l );
				      LocalDate localDate = LocalDate.parse( przesłanaData , f );
				                
				      if (localDate.isBefore(filtrDaty) || localDate.isEqual(filtrDaty)) {
				          return true; // Filtr przypasowuje tekst.
				      } 
				      return false; // Nie spelania wymagań.
				  });
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("Temat")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getTemat().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getTemat().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });	
				}
			} else if (poleNadawcy.getSelectionModel().getSelectedItem().equals("IP")) {
				if (poleOperatora.getSelectionModel().getSelectedItem().equals("=")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getAdresIP().toLowerCase().equals(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });					
				} else if (poleOperatora.getSelectionModel().getSelectedItem().equals("ZAWIERA")) {
					przefiltrowaneDane.setPredicate(meil-> {
		                // Jeżeli filtr jest pusty wyświetl wszystkie dane
		                if (poleDanych.getText().equals("")) {
		                    return true;
		                }

		                String lowerCaseFilter = poleDanych.getText().toLowerCase();

		                if (meil.getAdresIP().toLowerCase().contains(lowerCaseFilter)) {
		                    return true; // Filtr przypasowuje tekst.
		                } 
		                return false; // Nie spelania wymagań.
		            });	
				}
			}
			
			tabelaWyników.setItems(przefiltrowaneDane);
		});
		
		panelOdstępuPodPrzyciskiem = new FlowPane();
		
		panelNagłówkowyWyników = new FlowPane();
		tekstNagłówkowyWyników = new Label("Wyniki wyszukiwania");
		
		panelOdstępuNadWynikami = new FlowPane();
		
		panelDanychWyników = new BorderPane();
		tabelaWyników = new TableView();
		kolumnaEmail = new TableColumn("Nadawca");
		kolumnaEmail.setMinWidth(300);
		kolumnaEmail.setCellValueFactory(new PropertyValueFactory<>("nadawca"));
		kolumnaOdbiorca = new TableColumn("Odbiorca");
		kolumnaOdbiorca.setMinWidth(300);
		kolumnaOdbiorca.setCellValueFactory(new PropertyValueFactory<>("odbiorcy"));
		kolumnaDataPrzesłania = new TableColumn("Data przesłania");
		kolumnaDataPrzesłania.setMinWidth(250);
		kolumnaDataPrzesłania.setCellValueFactory(new PropertyValueFactory<>("dataPrzesłania"));
		kolumnaTemat = new TableColumn("Temat");
		kolumnaTemat.setMinWidth(300);
		kolumnaTemat.setCellValueFactory(new PropertyValueFactory<>("temat"));
		kolumnaIP = new TableColumn("IP");
		kolumnaIP.setMinWidth(130);
		kolumnaIP.setCellValueFactory(new PropertyValueFactory<>("adresIP"));
		
		dane = FXCollections.observableArrayList();
		
		panelPrzewijaniaWyników = new javafx.scene.control.ScrollPane();
		
		panelOdstępuPodTabelą = new FlowPane();
		
		panelDolny = new FlowPane();
		przyciskWczytaj = new Button("Wczytaj");
		
		//Konfiguracja kontrolek
		panelWiadomości.setPadding(new Insets(0, 5, 5, 5));
		
		panelNagłówkowy.setStyle("-fx-background-color: #696969;");
		panelNagłówkowy.setPrefHeight(55);
		//panelNagłówkowy.setMaxHeight(panelNagłówkowy.getPrefHeight());
		panelNagłówkowy.setMinHeight(panelNagłówkowy.getPrefHeight());
		panelNagłówkowy.setAlignment(Pos.CENTER);
		tekstNagłówkowy.setFont(czcionkaNagłówkowa);
		tekstNagłówkowy.setTextFill(Color.WHITE);

		panelOdstępuPodNagłówkiem.setPrefHeight(5);
		panelOdstępuPodNagłówkiem.setMaxHeight(panelOdstępuPodNagłówkiem.getPrefHeight());
		panelOdstępuPodNagłówkiem.setMinHeight(panelOdstępuPodNagłówkiem.getPrefHeight());

		panelFiltru.setStyle("-fx-background-color: #CECECE;");
		panelFiltru.setPrefHeight(31);
		//panelFiltru.setMaxHeight(panelFiltru.getPrefHeight());
		panelFiltru.setMinHeight(panelFiltru.getPrefHeight());
		panelFiltru.setAlignment(Pos.CENTER);
		tekstFiltru.setFont(czcionkaFiltru);
		tekstFiltru.setTextFill(Color.BLACK);
		
		panelOdstępuPodFiltrem.setPrefHeight(2);
		panelOdstępuPodFiltrem.setMaxHeight(panelOdstępuPodFiltrem.getPrefHeight());
		panelOdstępuPodFiltrem.setMinHeight(panelOdstępuPodFiltrem.getPrefHeight());
		
		panelPodFiltrem.setStyle("-fx-background-color: #ADD8E6;");
		panelPodFiltrem.setPrefHeight(30);
		//panelPodFiltrem.setMaxHeight(panelPodFiltrem.getPrefHeight());
		panelPodFiltrem.setMinHeight(panelPodFiltrem.getPrefHeight());
		panelPodFiltrem.setAlignment(Pos.CENTER_LEFT);
		tekstNazwyPola.setFont(czcionkaPodFiltrem);
		tekstNazwyPola.setTextFill(Color.BLACK);
		
		panelWartości.setPrefHeight(120);
		//panelWartości.setMaxHeight(panelWartości.getPrefHeight());
		panelWartości.setMinHeight(panelWartości.getPrefHeight());
		panelWartości.setAlignment(Pos.CENTER);		 
		
		/*comboNazwy.setPreferredSize(new Dimension(390, 25));
		comboNazwy.setFont(czcionkaPodFiltrem);
		comboNazwy.setBackground(new Color(206,206,206));
		comboOperatora.setPreferredSize(new Dimension(110, 25));
		comboOperatora.setFont(czcionkaPodFiltrem);
		comboOperatora.setBackground(new Color(206,206,206));
		poleWartości.setPreferredSize(new Dimension(390, 25));
		poleWartości.setFont(czcionkaPodFiltrem);
		poleWartości.setBackground(Color.WHITE);
		przyciskUsuń.setPreferredSize(new Dimension(80, 25));
		przyciskUsuń.setFont(czcionkaPodFiltrem);
		przyciskUsuń.setBackground(new Color(206,206,206));
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
                kontroler.załadujDane("C:\\Downloads\\message.eml");
            }           
        });  */
		panelOdstępuPodPrzyciskiem.setPrefHeight(9);
		panelOdstępuPodPrzyciskiem.setMaxHeight(panelOdstępuPodPrzyciskiem.getPrefHeight());
		panelOdstępuPodPrzyciskiem.setMinHeight(panelOdstępuPodPrzyciskiem.getPrefHeight());
			
		panelNagłówkowyWyników.setStyle("-fx-background-color: #CECECE;");
		panelNagłówkowyWyników.setPrefHeight(39);
		//panelNagłówkowyWyników.setMaxHeight(panelNagłówkowyWyników.getPrefHeight());
		panelNagłówkowyWyników.setMinHeight(panelNagłówkowyWyników.getPrefHeight());
		panelNagłówkowyWyników.setAlignment(Pos.CENTER);
		tekstNagłówkowyWyników.setFont(czcionkaFiltru);
		tekstNagłówkowyWyników.setTextFill(Color.BLACK);
				
		panelOdstępuNadWynikami.setPrefHeight(1);
		panelOdstępuNadWynikami.setMaxHeight(panelOdstępuNadWynikami.getPrefHeight());
		panelOdstępuNadWynikami.setMinHeight(panelOdstępuNadWynikami.getPrefHeight());
		
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
		
		panelOdstępuPodTabelą.setPrefHeight(38);
		panelOdstępuPodTabelą.setMaxHeight(panelOdstępuPodTabelą.getPrefHeight());
		panelOdstępuPodTabelą.setMinHeight(panelOdstępuPodTabelą.getPrefHeight());
			
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
                	kontroler.załadujDane(list);
                }
		});
		
		//Wstawienie kontrolek
		panelWiadomości.getChildren().add(panelNagłówkowy);
		panelNagłówkowy.getChildren().add(tekstNagłówkowy);
		
		panelWiadomości.getChildren().add(panelOdstępuPodNagłówkiem);
		
		panelWiadomości.getChildren().add(panelFiltru);
		panelFiltru.getChildren().add(tekstFiltru);
		
		panelWiadomości.getChildren().add(panelOdstępuPodFiltrem);
		
		panelWiadomości.getChildren().add(panelPodFiltrem);
		panelPodFiltrem.getChildren().add(tekstNazwyPola);
		panelPodFiltrem.getChildren().add(tekstOperatora);
		panelPodFiltrem.getChildren().add(tekstWartości);
		
		panelWiadomości.getChildren().add(panelWartości);
		panelWartości.getChildren().add(panelWierszaDanych);
		panelWierszaDanych.getChildren().add(poleNadawcy);
		panelWierszaDanych.getChildren().add(poleOperatora);
		panelWierszaDanych.getChildren().add(poleDanych);
		//panelWierszaDanych.getChildren().add(poleDaty);
		//panelWierszaDanych.getChildren().add(przyciskUsuń);
		panelWierszaDanych.getChildren().add(checkBoxAktywny);
		
		panelWiadomości.getChildren().add(panelprzyciskuWyszukaj);
		panelprzyciskuWyszukaj.getChildren().add(wyszukaj);
		
		panelWiadomości.getChildren().add(panelOdstępuPodPrzyciskiem);
		
		panelWiadomości.getChildren().add(panelNagłówkowyWyników);
		panelNagłówkowyWyników.getChildren().add(tekstNagłówkowyWyników);
		
		panelWiadomości.getChildren().add(panelOdstępuNadWynikami);
		
		panelWiadomości.getChildren().add(panelDanychWyników);
		//panelDanychWyników.getChildren().add(tabelaWyników);
		panelDanychWyników.setCenter(tabelaWyników);
		tabelaWyników.getColumns().addAll(kolumnaEmail, kolumnaOdbiorca, kolumnaDataPrzesłania, kolumnaTemat, kolumnaIP);
		tabelaWyników.setItems(dane);
		
		panelWiadomości.getChildren().add(panelOdstępuPodTabelą);
		
		panelWiadomości.getChildren().add(panelDolny);
		panelDolny.getChildren().add(przyciskWczytaj);
	}
	
	public void ustawReferencję(Kontroler kontroler) {
		this.kontroler = kontroler;
	}
	
	public void wyświetlDane(List<Meil> meile) {
		for (Meil meil : meile) {
			dane.add(meil);
		}
	}
	
	public VBox pobierzKontenerWiadomości() {
		return panelWiadomości;
	}
}
