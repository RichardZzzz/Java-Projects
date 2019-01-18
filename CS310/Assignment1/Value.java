
package Assignment1;
/*
 * @author Ruiqian Zhang G00948867
 */
public class Value {
    
    protected double dval;
    protected String sval;
    protected String tag; 
    
    protected Value ()
    {
        dval = 0;
        sval = null; 
        tag = "NULL";
    }
    
    protected Value (double newDval, String newSval, String newTag)
    {
        dval = newDval;
        sval = newSval;
        tag = newTag;
    }
    
    protected void setVal(double newDval)
    {
        this.dval = newDval;
        this.tag = "DBL";
    }
    
    protected void setVal(String newSval)
    {
        this.sval = newSval;
        this.tag = "STRING";
    }
    
    protected void setTag(String newTag)
    {
        this.tag = newTag;
    }
    
    protected String getVal ()
    {
        if (this.getTag().equals("DBL"))
        {
            return String.valueOf(dval);
        }
        if (this.getTag().equals("STRING"))
        {
            return sval;
        }    
        else 
            return tag;
    }
    
    protected String getTag ()
    {
        return tag;
    }
    
    public String toString ()
    {
        if (this.getTag()=="DBL")
            return (String.format("%10.4f",dval));
        if (this.getTag()=="STRING")
            return (String.format("%10s",sval));
        else
            return ("Null");
    }
}
