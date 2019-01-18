
package Assignment1;
import java.io.*;
/**
 * @param <T>
 * @author Ruiqian Zhang G00948867
 */
public class Grid<T>
{
    private int r , c; // rows, cols
    private Node [][] Nodes;
    private Node head;
    private Node curPointer;
    
    public Grid ()
    {
        this.r=r;
        this.c=c;
        this.Nodes = new Node [r][c];
    }
    
    public Grid (int rows, int cols)
    {
        this.r=rows;
        this.c=cols;
        this.Nodes = new Node [r][c];
        
        for (int i=0; i<r;i++)
        {   
            for (int j=0; j<c; j++)
            {
                if (0==j)
                {
                     Nodes[i][0] = new Node("");
                     curPointer = Nodes[i][0];
                     if (0==i){
                     head = Nodes[0][0];
                    } 
                }
                else
                {
                    Nodes[i][j] = new Node ("");
                    curPointer.setRight(Nodes[i][j]);
                    curPointer = Nodes[i][j];
                } 
            }
            curPointer.setRight(Nodes[i][0]);
          }
            
        for (int j=0; j<c;j++)
        {  
            for (int i=1; i<r; i++)
            {
                if(1==i){
                    curPointer = Nodes[0][j];
                }
                curPointer.setDown(Nodes[i][j]);
                curPointer = Nodes[i][j];
             }
            curPointer.setDown(Nodes[0][j]);
        }
    }
    
    // Get how many rows
    public int getRowNum ()
    {
        int a=0;
        Node temp = head;
        Node temp1 = temp.getDown();
        while (temp1!=temp)
        {
            temp1 = temp1.getDown();
            a++;
        }
        return a+1;
    }
    
    // Get how many Columns
    public int getColNum ()
    {
        int a=0;
        Node temp = head;
        Node temp1 = temp.getRight();
        while (temp1!=temp)
        {
            temp1 = temp1.getRight();
            a++;
        }
        return a+1;
    }
    
    // Set the pointer to head
    protected void reset ()
    {
	curPointer = head;
    }
    
    // Get the head
    protected Node getHead ()
    {
        return head;
    }
        
    // Get the Pointer
    protected Node getCurPointer ()
    {
        return curPointer;
    }
    
    // Check if the Grid is empty
    protected boolean empty ()
    {
        return (head == null);
    }
    
    // Add a head for the Grid
    protected void addHead(T newMember)
    {
        Node<T> newNode = new Node<T> (newMember);
	
        newNode.setRight (head);
        head = newNode;
        reset ();
    }
    
        
    public void assignCell (int row, int col, String str)
    {  
        Value v = new Value ();
        Node temp = head;
        double d=0;
        str.trim();
        if (str.substring(0,1).equals("\""))      
        {
            v.setVal(str.substring(1));
            for (int i=0;i<row;i++)
            {
                temp = temp.getDown();
            }
            for (int j=0;j<col;j++)
            {
                temp = temp.getRight();
            }
            temp.setV(v);
        }
        else
        {
            d = Double.parseDouble(str);
            v.setVal(d);
            for (int i=0;i<row;i++)
            {
                temp = temp.getDown();
            }
            for (int j=0;j<col;j++)
            {
                temp = temp.getRight();
            }
            temp.setV(v);
        }
    }
    
    public void Fill (int r1, int c1, int r2, int c2,String str)
    {
        Value v = new Value ();
        Node temp = head;
        for (int i=0;i<r1;i++)
        {
            temp = temp.getDown();
        }
        for (int j=0;j<c1;j++)
        {
            temp = temp.getRight();
        }
        
        Node curPointer = temp;
        if (r1<=r2&&c1<=c2)
        {
            for (int j=0; j<r2-r1+1; j++)
            { 
                for (int i=0; i<c2-c1+1; i++)
                {   
                    try  
                    {  
                        double d = Double.parseDouble(str);  
                        v.setVal(d);
                        curPointer.setV(v);  
                        curPointer = curPointer.getRight();
                    }  
                    catch(NumberFormatException nfe)  
                    {  
                        v.setVal(str.substring(1));
                        curPointer.setV(v);   
                        curPointer = curPointer.getRight();
                    }
                    
                   }
                temp = temp.getDown();
                curPointer = temp;
            }
        }
        else
            System.out.println("Invalid input! Type again!");
    }

    public void Number (int r1, int c1, int r2, int c2)
    {
        double k=0.0;
        
        Node temp = Nodes[r1][c1];
        Node curPointer = temp;
        if (r1<=r2&&c1<=c2)
        {
            for (int j=0; j<r2-r1+1; j++)
            { 
                for (int i=0; i<c2-c1+1; i++)
                {   
                        Value v = new Value ();
                        v.setVal(k);
                        curPointer.setV(v);
                        curPointer = curPointer.getRight();
                        k++;
                }
                temp = temp.getDown();
                curPointer = temp;
            }
        }
        else
            System.out.println("Invalid input! Type again!");
        
    }
    
     
    public void addCell (int r1, int c1, int r2, int c2, int r3, int c3)
    {
        double a,b,c;
        Value v1 = new Value ();
        Value v2 = new Value ();
        Value v3 = new Value ();
        Node temp1 = head;
        Node temp2 = head;
        Node temp3 = head;
        
        for (int i=0;i<r1;i++)
        {
            temp1 = temp1.getDown();
        }
        for (int j=0;j<c1;j++)
        {
            temp1 = temp1.getRight();
        }
        
        for (int i=0;i<r2;i++)
        {
            temp2 = temp2.getDown();
        }
        for (int j=0;j<c2;j++)
        {
            temp2 = temp2.getRight();
        }
        
        for (int i=0;i<r3;i++)
        {
            temp3 = temp3.getDown();
        }
        for (int j=0;j<c3;j++)
        {
            temp3 = temp3.getRight();
        }
        
        if (((Value) temp1.getV()).getTag().equals("DBL")&&((Value) temp2.getV()).getTag().equals("DBL"))
        {
            
            v1 =(Value) temp1.getV();
            v2 =(Value) temp2.getV();
            a = Double.parseDouble(v1.getVal());
            b = Double.parseDouble(v2.getVal());
            c = a+b;
            
            v3.setVal(c);
            temp3.setV(v3);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }
    
    public void subCell (int r1, int c1, int r2, int c2, int r3, int c3)
    {
        double a,b,c;
        Value v1 = new Value ();
        Value v2 = new Value ();
        Value v3 = new Value ();
        Node temp1 = head;
        Node temp2 = head;
        Node temp3 = head;
        
        for (int i=0;i<r1;i++)
        {
            temp1 = temp1.getDown();
        }
        for (int j=0;j<c1;j++)
        {
            temp1 = temp1.getRight();
        }
        
        for (int i=0;i<r2;i++)
        {
            temp2 = temp2.getDown();
        }
        for (int j=0;j<c2;j++)
        {
            temp2 = temp2.getRight();
        }
        
        for (int i=0;i<r3;i++)
        {
            temp3 = temp3.getDown();
        }
        for (int j=0;j<c3;j++)
        {
            temp3 = temp3.getRight();
        }
        
        if (((Value) temp1.getV()).getTag().equals("DBL")&&((Value) temp2.getV()).getTag().equals("DBL"))
        {
            
            v1 =(Value) temp1.getV();
            v2 =(Value) temp2.getV();
            a = Double.parseDouble(v1.getVal());
            b = Double.parseDouble(v2.getVal());
            c = a-b;
            
            v3.setVal(c);
            temp3.setV(v3);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }
    
    public void mulCell (int r1, int c1, int r2, int c2, int r3, int c3)
    {
        double a,b,c;
        Value v1 = new Value ();
        Value v2 = new Value ();
        Value v3 = new Value ();
        
        Node temp1 = head;
        Node temp2 = head;
        Node temp3 = head;
        
        for (int i=0;i<r1;i++)
        {
            temp1 = temp1.getDown();
        }
        for (int j=0;j<c1;j++)
        {
            temp1 = temp1.getRight();
        }
        
        for (int i=0;i<r2;i++)
        {
            temp2 = temp2.getDown();
        }
        for (int j=0;j<c2;j++)
        {
            temp2 = temp2.getRight();
        }
        
        for (int i=0;i<r3;i++)
        {
            temp3 = temp3.getDown();
        }
        for (int j=0;j<c3;j++)
        {
            temp3 = temp3.getRight();
        }
        
        if (((Value) temp1.getV()).getTag().equals("DBL")&&((Value) temp2.getV()).getTag().equals("DBL"))
        {
            
            v1 =(Value) temp1.getV();
            v2 =(Value) temp2.getV();
            a = Double.parseDouble(v1.getVal());
            b = Double.parseDouble(v2.getVal());
            c = a*b;
            
            v3.setVal(c);
            temp3.setV(v3);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }
    
    public void divCell (int r1, int c1, int r2, int c2, int r3, int c3)
    {
        double a,b,c;
        Value v1 = new Value ();
        Value v2 = new Value ();
        Value v3 = new Value ();
        
        Node temp1 = head;
        Node temp2 = head;
        Node temp3 = head;
        
        for (int i=0;i<r1;i++)
        {
            temp1 = temp1.getDown();
        }
        for (int j=0;j<c1;j++)
        {
            temp1 = temp1.getRight();
        }
        
        for (int i=0;i<r2;i++)
        {
            temp2 = temp2.getDown();
        }
        for (int j=0;j<c2;j++)
        {
            temp2 = temp2.getRight();
        }
        
        for (int i=0;i<r3;i++)
        {
            temp3 = temp3.getDown();
        }
        for (int j=0;j<c3;j++)
        {
            temp3 = temp3.getRight();
        }
        
        if (((Value) temp1.getV()).getTag().equals("DBL")&&((Value) temp2.getV()).getTag().equals("DBL"))
        {
            
            v1 =(Value) temp1.getV();
            v2 =(Value) temp2.getV();
            a = Double.parseDouble(v1.getVal());
            b = Double.parseDouble(v2.getVal());
            c = a/b;
            
            v3.setVal(c);
            temp3.setV(v3);
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }
    
    public void addRows (int r1, int r2, int r3)
    {
        int col=this.getColNum();
        
        for (int i=0; i<col; i++)
        {
            addCell(r1,i,r2,i,r3,i);
        }
        
    }
    
    public void subRows (int r1, int r2, int r3)
    {
        int col=this.getColNum();
        
        for (int i=0; i<col; i++)
        {
            subCell(r1,i,r2,i,r3,i);
        }
        
    }
    
    public void mulRows (int r1, int r2, int r3)
    {
        int col=this.getColNum();
        
        for (int i=0; i<col; i++)
        {
            mulCell(r1,i,r2,i,r3,i);
        }
        
    }
    
    public void divRows (int r1, int r2, int r3)
    {
        int col=this.getColNum();
        
        for (int i=0; i<col; i++)
        {
            divCell(r1,i,r2,i,r3,i);
        }
        
    }
    
    public void addCols (int c1, int c2, int c3)
    {
        int row=this.getRowNum();
        
        for (int i=0; i<row; i++)
        {
            addCell(i,c1,i,c2,i,c3);
        }
        
    }
    
    public void subCols (int c1, int c2, int c3)
    {
        int row=this.getRowNum();
        
        for (int i=0; i<row; i++)
        {
            subCell(i,c1,i,c2,i,c3);
        }
        
    }
    
    public void mulCols (int c1, int c2, int c3)
    {
        int row=this.getRowNum();
        
        for (int i=0; i<row; i++)
        {
            mulCell(i,c1,i,c2,i,c3);
        }
    
    }
    
    public void divCols (int c1, int c2, int c3)
    {
        int row=this.getRowNum();
        
        for (int i=0; i<row; i++)
        {
            divCell(i,c1,i,c2,i,c3);
        }
        
    }
    
    public void deleteRow (int n)
    {
        int row = this.getRowNum();
        int col = this.getColNum();
        Node temp = head;
        
        if (0==n)
        {
            Node bottom = head;
            head = head.getDown();
            for (int i=0;i<row-1;i++)
            {
                bottom = bottom.getDown();
            }
            for (int i=0; i<col; i++)
            {
                bottom.setDown(temp.getDown());
                temp = temp.getRight();
                bottom = bottom.getRight();
            }
       }
        
        else 
        {
            Node above = head;
            for (int i=0; i<n;i++)
            {
                above = above.getDown();
            }
            for (int i=0; i<n-1;i++)
            {
                temp = temp.getDown();
            }
            
            for (int j=0; j<col; j++)
            {
                temp.setDown(above.getDown());
                temp = temp.getRight();
                above = above.getRight();
            }
        }
    }
    
    public void deleteCol (int n)
    {
        int row = this.getRowNum();
        int col = this.getColNum();
        Node temp = head;
        
        if (0==n)
        {
            Node last = head;
            head = head.getRight();
            for (int i=0;i<col-1;i++)
            {
                last = last.getRight();
            }
            for (int i=0; i<row; i++)
            {
                last.setRight(temp.getRight());
                temp = temp.getDown();
                last = last.getDown();
            }
       }
        
        else 
        {
            Node above = head;
            for (int i=0; i<n;i++)
            {
                above = above.getRight();
            }
            for (int i=0; i<n-1;i++)
            {
                temp = temp.getRight();
            }
            
            for (int j=0; j<row; j++)
            {
                temp.setRight(above.getRight());
                temp = temp.getDown();
                above = above.getDown();
            }
        }
    }
    
    public void insertRow (int n)
    {
        int row = this.getRowNum();
        int col = this.getColNum();
        Node first = new Node ("");
        Node second = new Node ("");
        Node temp = head;
        
        second.setRight(first);
        
        for (int i=0; i<col-2; i++)
        {
            Node empty = new Node ("");
            first.setRight(empty);
            first = first.getRight();
        }
        first.setRight(second);
        first = first.getRight();
        
        if (0==n)
        {
            Node bottom = head;
            
            for (int i=0;i<row-1;i++)
            {
                bottom=bottom.getDown();
            }   
            for (int i=0;i<col-1;i++)
            {
                first.setDown(temp);
                bottom.setDown(first);
                first = first.getRight();
                temp = temp.getRight();
                bottom = bottom.getRight();
            }
            
            head = second;
        }
        else
        {
            Node above = head;
            for (int i=0;i<n-1;i++)
            {
                above = above.getDown();
            }
            
            for (int i=0;i<col-1;i++)
            {
                first.setDown(above.getDown());
                above.setDown(first);
                first = first.getRight();
                above = above.getRight();
            }
            
        }
    }
    
    public void insertCol (int m)
    {
        int row = this.getRowNum();
        int col = this.getColNum();
        Node first = new Node ("");
        Node second = new Node ("");
        Node temp = head;
        
        second.setDown(first);
        
        for (int i=0; i<row-2; i++)
        {
            Node empty = new Node ("");
            first.setDown(empty);
            first = first.getDown();
        }
        first.setDown(second);
        first = first.getDown();
        
        if (0==m)
        {
            Node last = head;
            
            for (int i=0;i<col-1;i++)
            {
                last=last.getRight();
            }   
            for (int i=0;i<row;i++)
            {
                first.setRight(temp);
                last.setRight(first);
                first = first.getDown();
                temp = temp.getDown();
                last = last.getDown();
            }
            
            head = second;
        }
        else
        {
            Node before = head;
            for (int i=0;i<m-1;i++)
            {
                before = before.getRight();
            }
            
            for (int i=0;i<row;i++)
            {
                first.setRight(before.getRight());
                before.setRight(first);
                first = first.getDown();
                before = before.getDown();
            }
            
        }
    }
    
    public String toString ()
    {
        StringBuilder outString = new StringBuilder ();
        String fmt = "%10s";
        Node temp = head;
        
        if (!empty ())
        {
            for(int i=-1; i<this.getRowNum();i++)
            {
                for (int j=-1; j<this.getColNum();j++)
                {
                    if (-1==i)
                    {
                        if(-1==j)
                        {
                            String tab = "";
                            String tab1 = "%5s";
                            outString.append(String.format(tab1,tab));
                        }
                        else
                        {
                            String ColNum = "Col "+(j);
                            outString.append(String.format(fmt,ColNum));
                        }
                    }
                    
                    if(-1==j&&i>-1)
                    {
                        String RowNum = "\nRow "+i;
                        outString.append(String.format(fmt,RowNum));
                    }
                    else if(j>-1&&i>-1)
                    {
                        String val = temp.toString();
                        outString.append(String.format(fmt,val));
                        temp = temp.getRight();
                    }
                    
                 }
                if (i>-1)
                        temp = temp.getDown();
            }            
        }
        return outString.toString();
     }
}
