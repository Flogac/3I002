package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip{
	
	public ClipEllipse( double left, double top, double right , double bottom, Color color){
		super( left , top , right , bottom , color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(this.getColor());
		ctx.fillOval( ((this.getLeft()+this.getRight())/2) - ((this.getRight()-this.getLeft()) /2), ((this.getTop()+this.getBottom())/2) - ((this.getBottom()-this.getTop() )/2),
				(this.getRight()-this.getLeft()), (this.getBottom()-this.getTop()));
		
	}

	@Override
	public Clip copy() {
		return new ClipEllipse( this.getLeft() , this.getTop() , this.getRight() , this.getBottom() , this.getColor() );
	}
	
}
