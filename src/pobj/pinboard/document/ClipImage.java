package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage implements Clip {
	
	private Image image;
	private double top;
	private double left;
	private double bottom;
	private double right;
	private Color color;

	public ClipImage(double left, double top, File filename){
		this.setGeometry(left, top, left + this.image.getWidth(), top + this.image.getHeight());
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(this.image, this.getLeft(), this.getTop());
		
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return null;
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
