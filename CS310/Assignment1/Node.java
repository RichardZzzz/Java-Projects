package Assignment1;
/* @author Ruiqian Zhang G00948867
 */
public class Node<Value>{
        
        private Value V;
        private Node right;
        private Node down;

        public Value getV() 
        {
            return V;
	}

	public void setV(Value v) {
		V = v;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node () {
            V = null;
            right = null;
            down = null;
        } 
	
	public Node (Value newValue) {
            V = newValue;
            right = null;
            down = null;
        }

        public String toString ( )
        {
            return V.toString();
        }
    }	
