package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class EllTool implements Tool {
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color = Color.BLUE;
	
	@Override
	public void press(EditorInterface i, MouseEvent e ) {
		this.left = e.getX();
		this.top = e.getY();
		this.right = this.left;
		this.bottom = this.top;
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		this.right=e.getX();
		this.bottom=e.getY();

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(new ClipEllipse(this.left, this.top, this.right, this.bottom, this.color));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(((this.left+this.right)/2) - ((this.right-this.left)/2), ((this.top+this.bottom)/2) - ((this.bottom-this.top)/2),
				(this.right-this.left), (this.bottom-this.top));
		gc.setStroke(this.color);
		gc.strokeOval( ((this.left+this.right)/2) - ((this.right-this.left)/2), ((this.top+this.bottom)/2) - ((this.bottom-this.top)/2),
				(this.right-this.left), (this.bottom-this.top));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Ellipse";
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		
	}

}
