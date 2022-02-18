package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Cipher FX. All Tabs working.
 * @author Tanvir
 *
 */
public class Main extends Application {
	private Stage popup;
	private GridPane gPop;
	private Scene popScene;
	private Button selectPreDir;
	private String dbPath="";
	
	private GridPane gPane;
	private Button trainDirSelect;
	private Label trainDirLabel;
	private Button newDataBaseSelect;
	private Button oldDataBaseSelect;
	private Button updDataBase;
	private Button builderReset;
	private String inputText;
	private String dbDir;
	private HashMap<String,Integer> trainingMap;
	private File trainingFile;
	private DirectoryChooser dChoose;
	private FileChooser fChoose;
	private TextArea textArea;
	
	private GridPane explorerPane;
	private Button dbDirSelect;
	private Label dbDirLabel;
	private Button sortAlpha;
	private Button sortFreq;
	private Button removeLowFreq;
	private Button explorerReset;
	private Button mergeDB;
	private HashMap<String,Integer> explorerMap;
	private ArrayList<Node>sortedArr;
	private TextField enterLowFreq;
	private TextArea showDBContent;
	
	private GridPane langPane;
	private Button selectTextFile;
	private Label langDirLabel;
	private Button detectLangBtn;
	private Button langBoxReset;
	private TextArea showLangContent;
	private Label showLang;
	private String langName;
	private String langDetectInput;
	private ArrayList<HashMap<String,Integer>>langMap;
	
	private GridPane cipherPane;
	private Button openCipherFile;
	private Button encrypt;
	private Button cipherReset;
	private Button openDecipher;
	private Label keyEntryLabel;
	private TextField keyEntryBox;
	private Button decipher;
	private TextArea showCipherText;
	private String toEncrypt;
	private String toDecrypt;
	private String cipherDir;
	private int key;
	
	private GridPane robustPane;
	private Button openEncryptRobustFile;
	private Button robustEncrypt;
	private Button robustReset;
	private Button openDecryptRobustFile;
	private Button robustDecrypt;
	private TextArea robustTextBox;
	private Label enterRobustKey;
	private TextField robustKeyEntry;
	private String toRobustEncrypt;
	private String toRobustDecrypt;
	private String robustDir;
	private Label complexityMessage;
	
	private GridPane hackPane;
	private Button selectHackFile;
	private Button startHack;
	private TextArea hackTextBox;
	private Button hackReset;
	private String toHack;
	
	/**
	 * Initializes the Hack Tab
	 */
	public void initHack() {
		hackPane = new GridPane();
		selectHackFile = new Button("Select File to Hack");
		startHack = new Button("Hack!");
		hackTextBox = new TextArea();
		hackReset = new Button("Reset");
		startHack.setDisable(true);
		hackTextBox.setEditable(false);
		
		hackPane.setMinSize(800, 500);
		hackPane.setPadding(new Insets(10,10,10,10));
		hackPane.setHgap(10);
		hackPane.setVgap(10);
		
		hackPane.add(selectHackFile, 0, 0);
		hackPane.add(startHack, 0, 1);
		hackPane.add(hackTextBox, 1, 2);
		hackPane.add(hackReset, 0,2);
	}
	/**
	 * Initializes the Robust Cipher Tab
	 */
	public void initRobustCipher() {
		robustPane = new GridPane();
		openEncryptRobustFile = new Button("Open File");
		robustEncrypt = new Button("Encrypt");
		openDecryptRobustFile = new Button("Open File to Decrypt");
		robustDecrypt = new Button("Decrypt");
		robustTextBox = new TextArea();
		enterRobustKey = new Label("Enter Key");
		robustKeyEntry = new TextField();
		robustReset = new Button("Reset");
		complexityMessage = new Label("Hacking this cipher using brute force has a time complexity of O(N!),\r\n" + 
				"where N is the size of the alphabet.\r\n" + 
				"For this reason, the Hacking tab does not support hacking this cipher.");
		
		robustEncrypt.setDisable(true);
		robustDecrypt.setDisable(true);
		robustTextBox.setMaxWidth(400);
		
		robustPane.setMinSize(800, 500);
		robustPane.setPadding(new Insets(10,10,10,10));
		robustPane.setHgap(10);
		robustPane.setVgap(10);
		
		robustPane.add(openEncryptRobustFile, 0, 0);
		robustPane.add(robustEncrypt, 0, 1);
		robustPane.add(openDecryptRobustFile, 2, 0);
		robustPane.add(enterRobustKey, 2, 1);
		robustPane.add(robustKeyEntry, 2, 2);
		robustPane.add(robustDecrypt, 2, 3);
		robustPane.add(robustReset, 0, 2);
		robustPane.add(robustTextBox, 1, 4);
		robustPane.add(complexityMessage, 1, 1);
		
	}
	/**
	 * Initializes the Cipher Tab
	 */
	public void initCipher() {
		cipherPane = new GridPane();
		openCipherFile = new Button("Open file to Encrypt");
		encrypt = new Button("Encrypt");
		cipherReset = new Button("Reset");
		openDecipher = new Button("Open file to Decipher");
		keyEntryLabel = new Label("Enter key");
		keyEntryBox = new TextField();
		decipher = new Button("Decipher");
		showCipherText = new TextArea();
		showCipherText.setMaxWidth(400);
		showCipherText.setEditable(false);
		encrypt.setDisable(true);
		keyEntryBox.setDisable(true);
		decipher.setDisable(true);
		
		cipherPane.setMinSize(800, 500);
		cipherPane.setPadding(new Insets(10,10,10,10));
		cipherPane.setVgap(10);
		cipherPane.setHgap(10);
		
		cipherPane.add(openCipherFile, 0, 0);
		cipherPane.add(encrypt, 0, 1);
		cipherPane.add(cipherReset, 0, 2);
		cipherPane.add(showCipherText, 1,4);
		cipherPane.add(openDecipher, 2, 0);
		cipherPane.add(keyEntryLabel, 2, 1);
		cipherPane.add(keyEntryBox, 2, 2);
		cipherPane.add(decipher, 2, 3);
		
	}
	/**
	 * Initializes the Detect Language Tab
	 */
	public void initDetectLang() {
		langPane = new GridPane();
		selectTextFile = new Button("Choose file");
		langDirLabel = new Label("");
		detectLangBtn = new Button("Detect Language");
		showLangContent = new TextArea();
		showLangContent.setEditable(false);
		langBoxReset = new Button("Reset");
		showLang = new Label("Language is: ");
		
		langPane.setMinSize(800, 500);
		langPane.setPadding(new Insets(10,10,10,10));
		langPane.setVgap(10);
		langPane.setHgap(10);
		
		langPane.add(selectTextFile, 0, 0);
		langPane.add(langDirLabel, 1, 0);
		langPane.add(detectLangBtn, 0, 1);
		langPane.add(showLangContent, 1, 2);
		langPane.add(langBoxReset, 0, 2);
		langPane.add(showLang, 0, 3);
	
				
	}
	/**
	 * Initializes the DataBase Explorer Tab
	 */
	public void initDataBaseExplorer(){
		explorerPane = new GridPane();
		dbDirSelect = new Button("Select Training Files Directory");
		dbDirLabel = new Label("");
		sortAlpha = new Button("Sort by Alphabetical");
		sortFreq = new Button("Sort by Frequency");
		removeLowFreq = new Button("Remove Low Freq Words");
		mergeDB = new Button("Merge DataBase");
		explorerReset = new Button("Reset Text Box");
		enterLowFreq = new TextField();
		showDBContent = new TextArea();
		showDBContent.setEditable(false);
		explorerMap = new HashMap<String,Integer>();
		sortedArr = new ArrayList<Node>();
		sortAlpha.setDisable(true);
		sortFreq.setDisable(true);
		removeLowFreq.setDisable(true);
		mergeDB.setDisable(true);

		
		explorerPane.setMinSize(800, 500);
		explorerPane.setPadding(new Insets(10,10,10,10));
		explorerPane.setVgap(10);
		explorerPane.setHgap(10);
		
		explorerPane.add(dbDirSelect, 0, 0);
		explorerPane.add(dbDirLabel, 1, 0);
		explorerPane.add(sortAlpha, 0, 1);
		explorerPane.add(sortFreq, 0, 2);
		explorerPane.add(removeLowFreq, 0, 3);
		explorerPane.add(enterLowFreq, 1, 3);
		explorerPane.add(mergeDB, 0, 4);
		explorerPane.add(showDBContent, 1, 5);
		explorerPane.add(explorerReset, 0, 5);
		
	}
	/**
	 * Initializes the Data Base Builder Tab
	 */
	public void initDataBaseBuilder() {
		gPane = new GridPane();
		trainDirSelect = new Button("Select Training Directory");
		trainDirLabel = new Label("");
		newDataBaseSelect = new Button("Use New DataBase");
		oldDataBaseSelect = new Button("Use Existing DataBase");
		builderReset = new Button("Reset");
		updDataBase = new Button("Update DataBase");
		textArea = new TextArea();
		textArea.setEditable(false);
		fChoose = new FileChooser();
		dChoose = new DirectoryChooser();

		gPane.setMinSize(800, 500);
		gPane.setPadding(new Insets(10,10,10,10));
		gPane.setVgap(10);
		gPane.setHgap(10);
		
		gPane.add(trainDirSelect, 0,0);
		gPane.add(trainDirLabel, 1, 0);
		gPane.add(newDataBaseSelect, 0, 1);
		gPane.add(oldDataBaseSelect, 1, 1);
		oldDataBaseSelect.setTranslateX(-30);
		gPane.add(updDataBase, 0, 2);
		gPane.add(builderReset, 1, 2);
		gPane.add(textArea, 1, 3);
		
		updDataBase.setDisable(true);
	}
	/**
	 * The start method initializes all the Tabs and the Scene
	 * @throws InterruptedException 
	 */
	public void initPopup(Stage primaryStage) throws InterruptedException {
		Label secondLabel = new Label("Please select the Directory of the 'Program DB' folder you extracted");
		selectPreDir = new Button("Select");
		gPop = new GridPane();
		gPop.setHgap(10);
		gPop.setVgap(10);
		gPop.add(secondLabel, 1, 0);
		gPop.add(selectPreDir, 1, 1);
		popScene = new Scene(gPop,500,100);
		popup = new Stage();
		popup.setTitle("Initialize DataBase");
		popup.setScene(popScene);
		popup.initModality(Modality.WINDOW_MODAL);
		popup.initOwner(primaryStage);
		
		if(langMap == null) {
		Thread.sleep(1000);
		popup.show();}
	}
	@Override
	public void start(final Stage primaryStage) throws InterruptedException {
			TabPane tabPane = new TabPane();
	        Tab dbBuilder = new Tab("Database Builder");
	        Tab dbExplorer = new Tab("Database Explorer"  );
	        Tab detectLang = new Tab("Detect Language");
	        Tab caeserCipher = new Tab("Caeser Cipher");
	        Tab robustCipher = new Tab("Robust Cipher");
	        Tab hacking = new Tab("Hacking");
	        
	        initDataBaseBuilder();
	        dbBuilder.setContent(gPane);
	        
	        initDataBaseExplorer();
	        dbExplorer.setContent(explorerPane);
	        
	        initDetectLang();
	        detectLang.setContent(langPane);
	        
	        initCipher();
	        caeserCipher.setContent(cipherPane);
	        
	        initHack();
	        hacking.setContent(hackPane);
	        
	        initRobustCipher();
	        robustCipher.setContent(robustPane);
	        
	        primaryStage.getIcons().add(new Image("http://clipart-library.com/images/dc4oaaezi.png"));
	        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	        tabPane.getTabs().addAll(dbBuilder,dbExplorer,detectLang,caeserCipher,robustCipher,hacking);
			BorderPane root = new BorderPane(tabPane);
			Scene scene = new Scene(root,800,500);
			primaryStage.setTitle("CipherFX");
			primaryStage.setScene(scene);
			primaryStage.show();
			initPopup(primaryStage);
			actions(primaryStage);
	}
	/**
	 * The actions method handles what the program should do if 
	 * a button is interacted with. It uses eventHandlers to act when an
	 * action is performed on the button.
	 * @param primaryStage - The stage that holds all the tabs and elements
	 */
	public void actions(Stage primaryStage) {
		//Popup Event
		selectPreDir.setOnMouseClicked((MouseEvent me)->{
			dbPath = dChoose.showDialog(primaryStage).getAbsolutePath();
			if(dbPath.length()>0)popup.close();
			try {
				langMap = DBUtility.fetchDataBases(dbPath);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		});
		//Select Training Directory Button
		trainDirSelect.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent e1) {
			trainingFile = fChoose.showOpenDialog(primaryStage);
			
            try {
            	inputText = DBUtility.fileToString(trainingFile);
				if(langMap == null) {System.out.println("ITS NULL");langMap = DBUtility.fetchDataBases(dbPath);}
				langName = DBUtility.detectLang(inputText, langMap,showLangContent,false);
				textArea.setText(textArea.getText()+ "Language Detected: " + langName);
			} catch (IOException | ClassNotFoundException e) {
				textArea.setText(e.getMessage());
			}
            trainDirLabel.setText(trainingFile.getAbsolutePath());
			}
		});
		//Use new DataBase Button
		newDataBaseSelect.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
			dbDir = dChoose.showDialog(primaryStage).getAbsolutePath(); 
			dbDir+="\\"+langName+".bin";
			//System.out.println(dbDir);
			updDataBase.setDisable(false);
			trainingMap = new HashMap<String,Integer>();
			}
        });
		//Use existing DataBase Button
		oldDataBaseSelect.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				dbDir = fChoose.showOpenDialog(primaryStage).getAbsolutePath(); 
				updDataBase.setDisable(false);
				try {
					trainingMap = FileUtility.readObject(dbDir);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		//Update DataBase Button
		updDataBase.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				String newWords[] = CleanText.clean(inputText, langName);
				trainingMap = DBUtility.updateDataBase(newWords, trainingMap);
				DBUtility.printDB(trainingMap, textArea);
				try {
					FileUtility.saveObject(trainingMap, dbDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
				textArea.appendText("\nDataBase complete");
			}
			
		});
		//Reset button on DB Builder Tab
		builderReset.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
			textArea.setText("");
			}
		});
		//Select Training Directory for Explorer Tab
		dbDirSelect.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				try {
					String inputText = DBUtility.fileToString(selectedFile);
					if(langMap == null) langMap = DBUtility.fetchDataBases(dbPath);
					langName = DBUtility.detectLang(inputText, langMap,showDBContent,false);
					showDBContent.setText("Language Detected: "+ langName);
					String explorerWords[] = CleanText.clean(inputText, langName);
					explorerMap = DBUtility.updateDataBase(explorerWords, explorerMap);
					DBUtility.printDB(explorerMap, showDBContent);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				sortAlpha.setDisable(false);
				sortFreq.setDisable(false);
				mergeDB.setDisable(false);
				removeLowFreq.setDisable(false);
				dbDirLabel.setText(selectedFile.getAbsolutePath());
			}
		});
		//Sort by Alphabetical Order Button
		sortAlpha.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				sortedArr = DBUtility.sortByAlpha(explorerMap);
				showDBContent.setText("");
				DBUtility.printSortedDB(sortedArr, showDBContent);
			}
		});
		//Sort by Frequency Button
		sortFreq.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				sortedArr = DBUtility.sortByFreq(explorerMap);
				showDBContent.setText("");
				DBUtility.printSortedDB(sortedArr, showDBContent);
			}
		});
		//Remove low Frequency words button
		removeLowFreq.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				int threshold =-1;
				try {
					threshold = Integer.parseInt(enterLowFreq.getText());
				}catch (NumberFormatException e) {showDBContent.appendText("\nOnly Numerical Input Allowed");}
				
				if(threshold >=0) {
					explorerMap = DBUtility.removeLowFreq(explorerMap, threshold);
					showDBContent.setText("");
					DBUtility.printDB(explorerMap, showDBContent);
				}
				else showDBContent.appendText("\nEnter Valid Input");	
			}
		});
		//Merge dataBases button
		mergeDB.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				String oldDBDir = selectedFile.getAbsolutePath();
				try {
					HashMap<String,Integer>oldDB = FileUtility.readObject(oldDBDir);
					DBUtility.printDB(oldDB, showDBContent);
					oldDB = FileUtility.mergeDataBases(explorerMap, oldDB);
					FileUtility.saveObject(oldDB, oldDBDir);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				showDBContent.appendText("\nDataBases Merged");
			}
		});
		//Reset Button for DB Explorer Tab
		explorerReset.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				showDBContent.setText("");
			}
		});
		
		//Detect Language Button
		detectLangBtn.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				try {
					if(langMap == null) langMap = DBUtility.fetchDataBases(dbPath);
					showLang.setText(showLang.getText()+ DBUtility.detectLang(langDetectInput, langMap,showLangContent,true));
	
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		//Resets TextBox in Detect Language Tab
		langBoxReset.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				showLangContent.setText("");
			}
		});
		//Process file to detect Language from
		selectTextFile.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {			
			public void handle(ActionEvent event) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				try {
					langDetectInput = DBUtility.fileToString(selectedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}	
				langDirLabel.setText(selectedFile.getAbsolutePath());
			}
		});
		//Opens file to Encrypt
		openCipherFile.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				cipherDir = selectedFile.getAbsolutePath();
				int idx = cipherDir.lastIndexOf('\\');
				cipherDir= cipherDir.substring(0, idx);
				System.out.println(cipherDir);
				try {
					toEncrypt = DBUtility.fileToString(selectedFile);
					showCipherText.setText(toEncrypt);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				encrypt.setDisable(false);
			}
		});
		//Encrypts String
		encrypt.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String lang = DBUtility.detectLang(toEncrypt, langMap, showCipherText, false);
				String[] encData = CleanText.clean(toEncrypt, lang);
				String alpha ="";
				int langIdx =-1;
				switch(lang) {
				case "ENG": alpha = Alphabet.ENGLISH.getLetters(); langIdx =1; break;
				case "FRN": alpha = Alphabet.FRENCH.getLetters();langIdx =2; break;
				case "GRK": alpha = Alphabet.GREEK.getLetters(); langIdx =3; break;
				case "SPN": alpha = Alphabet.SPANISH.getLetters(); langIdx =4; break;
				case "ITA": alpha = Alphabet.ITALIAN.getLetters(); langIdx =5; break;
				}
				String[] encResults = CipherUtil.encryptText(encData,alpha);
				showCipherText.setText("Key: "+encResults[0]+"\n"+encResults[1]);
				
				try {
					FileUtility.writeToFile(langIdx+"\n"+encResults[1], cipherDir);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
		//Reset button for Cipher Tab
		cipherReset.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				showCipherText.setText("");
			}
		});
		//Opens file to Decipher
		openDecipher.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				try {
					toDecrypt = DBUtility.fileToString(selectedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				decipher.setDisable(false);
				keyEntryBox.setDisable(false);
			}
		});
		//Deciphers string using key
		decipher.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if(keyEntryBox.getText().length()>0) {
				key = Integer.parseInt(keyEntryBox.getText());
				}else showCipherText.appendText("Enter a Key!");
				toDecrypt = CipherUtil.decryptText(toDecrypt, key);
				showCipherText.setText(toDecrypt);
			}
		});
		//Opens file to encrypt using Robust Cipher
		openEncryptRobustFile.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				robustDir = selectedFile.getAbsolutePath();
				robustDir = robustDir.substring(0, robustDir.lastIndexOf('\\'));
				try {
					toRobustEncrypt = DBUtility.fileToString(selectedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				robustTextBox.setText(toRobustEncrypt);
				robustEncrypt.setDisable(false);
			}
		});
		//Opens file to decrypt using Robust Cipher
		openDecryptRobustFile.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				robustDir = selectedFile.getAbsolutePath();
				robustDir = robustDir.substring(0, robustDir.lastIndexOf('\\'));
				try {
					toRobustDecrypt = DBUtility.fileToString(selectedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				robustTextBox.setText(toRobustDecrypt);
				robustDecrypt.setDisable(false);
			}
		});
		//Encrypts a string using Robust Cipher
		robustEncrypt.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				String[] res = CipherUtil.robustEncrypt(toRobustEncrypt);
				robustTextBox.setText("Key: "+res[0]+"\n"+res[1]);
				try {
					FileUtility.writeToFile(res[1], robustDir,"Robust Encrypted");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		//Decrypts a string using Robust Cipher and a key
		robustDecrypt.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if(robustKeyEntry.getText().length()>0) {
					toRobustDecrypt = CipherUtil.robustDecrypt(robustKeyEntry.getText(), toRobustDecrypt);	
				}
				else robustTextBox.setText("Enter a Key!");
				
				robustTextBox.setText(toRobustDecrypt);
			}
		});
		//Reset button for Robust Cipher Tab
		robustReset.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				robustTextBox.setText("");
			}
		});
		//Select file to hack
		selectHackFile.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				File selectedFile = fChoose.showOpenDialog(primaryStage);
				try {
					toHack = DBUtility.fileToString(selectedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				startHack.setDisable(false);
			}
		});
		//Hack given text
		startHack.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				String hackedText = CipherUtil.hack(toHack, langMap);
				hackTextBox.setText(hackedText);
			}
		});
		//Reset button for hack tab
		hackReset.setOnAction((EventHandler<ActionEvent>)new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				hackTextBox.setText("");
			}
		});
	}
	/**
	 * The main method calls the start method
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		launch(args);
	}
}
