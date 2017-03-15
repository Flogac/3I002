package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class RectTool implements Tool {
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
		i.getBoard().addClip(new ClipRect(this.left, this.top, this.right, this.bottom, this.color));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(this.left, this.top, Math.abs(this.right-this.left), Math.abs(this.top-this.bottom));
		gc.setStroke(color);
		gc.strokeRect(this.left, this.top, Math.abs(this.right-this.left), Math.abs(this.top-this.bottom));

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Rectangle";
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		
	}

}
