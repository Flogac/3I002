package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private List<Clip> contenu = new ArrayList<Clip>();

	public Board(){
	}
	
	public List<Clip> getContents(){
		return this.contenu;
	}
	
	public void addClip( Clip clip){
		this.contenu.add( clip );
	}
	
	public void addClip(List<Clip> clip){
		this.contenu.addAll( clip );
	}

	public void removeClip( Clip clip ){
		this.contenu.remove(clip);
	}
	
	public void removeClip( List<Clip> clip ){
		this.contenu.removeAll(clip);
	}
	
	public void draw( GraphicsContext gc ){
		gc.setFill( Color.WHITE);
		gc.fillRect( 0  , 0 , gc.getCanvas().getWidth() , gc.getCanvas().getHeight() );
		for( Clip i :  this.contenu ){
			i.draw(gc);
		}
	}
}
