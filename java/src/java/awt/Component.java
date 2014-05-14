package java.awt;

public abstract class Component {

	boolean visible = true;
	
	public void setVisible(boolean visible){
	    // Inspection by subclassing shows that Sun's implementation calls
	    // show(boolean) which then calls show() or hide(). It is the show()
	    // method that is overriden in subclasses like Window.
	    show(visible);
	}
	
	public void show(boolean visible){
	    if (visible)
	      show();
	    else
	      hide();
	}
	
	public void show(){
	    if(! visible){
	    	visible = true;
	    	
	    }
	}
	
	public void hide(){
	    if (visible){
	    	visible = false;
    	}
	}
}
