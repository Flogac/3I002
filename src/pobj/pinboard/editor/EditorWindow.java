package pobj.pinboard.editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.EllTool;
import pobj.pinboard.editor.tools.RectTool;
import pobj.pinboard.editor.tools.Tool;

public class EditorWindow implements EditorInterface {
	
	private MenuBar barreMenu;
	private ToolBar barreBouttons;
	private Canvas zoneDessin;
	private Label statut = new Label();
	private VBox layout;
	private Board planche = new Board();
	private Stage stage;
	private Tool outil = new RectTool();
	private Color color = Color.BLUE;
	
	public EditorWindow(Stage stage) {
		EditorWindow ceci = this;
		this.stage = stage;
		this.stage.setTitle("PinBoard");
		this.layout = new VBox();
		this.newCanvas( 400 , 600 );
		this.outil = new RectTool();
		this.zoneDessin.setOnMousePressed( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				ceci.buttonPressed( e );
			}
		}
		);
		this.zoneDessin.setOnMouseDragged( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				ceci.buttonDragged( e );
			}
		}
		);
		this.zoneDessin.setOnMouseReleased( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				ceci.buttonReleased( e );
			}
		}
		);
		this.newBarreMenu();
		this.newToolBar();
		this.setStatut();
		this.layout.getChildren().addAll(this.barreMenu , new Separator() ,this.barreBouttons,new Separator(),this.zoneDessin,new Separator(),this.statut);
		Scene scene = new Scene(this.layout);
		this.stage.setScene(scene);
		this.draw();
		this.stage.show();
	}

	private void setStatut() {
		this.statut.textProperty().set("Zone de dessin" + ":" + this.outil.getName() + "/" + this.color.toString());
		
	}

	protected void buttonReleased(MouseEvent e) {
		System.out.println("Released");
		this.outil.release( this , e );
		this.draw();
	}

	protected void buttonDragged(MouseEvent e) {
		System.out.println("Dragged");
		this.outil.drag( this , e );
		this.draw();
		this.outil.drawFeedback(this, this.zoneDessin.getGraphicsContext2D());
	}

	protected void buttonPressed(MouseEvent e) {
		System.out.println("Pressed" + " " + e.getSceneX() + " " + e.getSceneY());
		this.outil.press(this, e );
		this.outil.setColor(this.color);
		
	}

	private void newToolBar() {
		EditorWindow ceci = this;
		Button bouttonRect = new Button( "Rectangle");
		Button bouttonEll = new Button( "Ellipse");
		bouttonRect.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ceci.outil = new RectTool();
				ceci.setStatut();
			}
		});
		bouttonEll.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ceci.outil = new EllTool();
				ceci.setStatut();
			}
		});
		this.barreBouttons = new ToolBar( bouttonRect , bouttonEll );
	}

	private void newBarreMenu() {
		Menu menu1 = this.newMenuFile() ;
		Menu menu2 = this.newMenuColor();
		this.barreMenu  = new MenuBar(menu1 , menu2);
	}

	private Menu newMenuColor() {
		EditorWindow ceci = this;
		Menu menu = new Menu("Couleur");
		MenuItem bleu = new MenuItem("Bleu");
		MenuItem jaune = new MenuItem("Jaune");
		MenuItem rouge = new MenuItem( "Rouge");
		bleu.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ceci.setColor( Color.BLUE);
				ceci.setStatut();
			}
		});
		jaune.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ceci.setColor( Color.YELLOW);
				ceci.setStatut();
			}
		});
		rouge.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				ceci.setColor( Color.RED );
				ceci.setStatut();
			}
		});
		menu.getItems().addAll(  bleu, jaune , rouge );
		return menu;
	}

	protected void setColor(Color red) {
		this.color = red;
		
	}

	private Menu newMenuFile() {
		Stage stage = this.stage;
		Menu menu = new Menu("File");
		MenuItem newI = new MenuItem("New");
		MenuItem close = new MenuItem( "Close");
		newI.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				new EditorWindow(new Stage());
			}
		});
		close.setOnAction( 
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				stage.close();
			}
		});
		menu.getItems().addAll(  newI, close );
		return menu;
	}

	private void newCanvas(int hauteur, int longueur) {

		this.zoneDessin = new Canvas( longueur , hauteur );
		
	}

	@Override
	public Board getBoard() {
		return this.planche;
	}
	
	private void draw(){
		this.planche.draw(this.zoneDessin.getGraphicsContext2D());
	}

}
