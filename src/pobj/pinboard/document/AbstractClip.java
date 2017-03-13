package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip{
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color){
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
		this.color=color;
	}

	@Override
	public double getTop() {
		return this.top;
	}

	@Override
	public double getLeft() {
		return this.left;
	}

	@Override
	public double getBottom() {
		return this.bottom;
	}

	@Override
	public double getRight() {
		return this.right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
		
	}

	@Override
	public void move(double x, double y) {
		this.setGeometry(this.left + x, this.top + y, this.right+x, this.bottom + y);
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		if( x <= this.right && x>=this.left && y>=this.bottom&&y<=this.top)
			return true;
		return false;
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
		
	}

	@Override
	public Color getColor() {
		return this.color;
	}
}
