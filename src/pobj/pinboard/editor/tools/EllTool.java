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
	private ClipEllipse ext;
	private ClipEllipse intC;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		this.left = e.getX();
		this.top = e.getY();
		this.right = this.left;
		this.bottom = this.top;
		this.ext = new ClipEllipse(this.left, this.top, this.right , this.bottom, this.color);
		this.intC = new ClipEllipse(this.left, this.top, this.right , this.bottom, Color.WHITE);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		this.ext.setGeometry(this.ext.getLeft(), this.ext.getTop(), e.getSceneY()-84, e.getSceneX()-1);
		this.intC.setGeometry(this.ext.getLeft() + 1 , this.ext.getTop() + 1, this.ext.getRight() - 1, this.ext.getBottom() - 1);

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(this.ext);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		this.ext.draw(gc);
		this.intC.draw(gc);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
