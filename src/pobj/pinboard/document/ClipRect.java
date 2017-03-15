package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {
	
	public ClipRect( double left, double top, double right , double bottom, Color color){
		super( left , top , right , bottom , color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		//System.out.println( "Rect" + this.getBottom() + this.getLeft() + this.getRight() + this.getTop() + this.getColor() );
		ctx.setFill( this.getColor());
		ctx.fillRect(this.getLeft(), this.getTop(), Math.abs(this.getRight()-this.getLeft()), Math.abs(this.getTop()-this.getBottom()));		
	}

	@Override
	public Clip copy() {
		return new ClipRect( this.getLeft() , this.getTop() , this.getRight() , this.getBottom(), this.getColor() );
	}

}
