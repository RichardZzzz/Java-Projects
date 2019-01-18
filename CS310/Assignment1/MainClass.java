package Assignment1;

import java.util.Scanner;

/* @author Ruiqian Zhang G00948867
 */
public class MainClass {
    
    static Scanner SC = new Scanner (System.in);
    public static void main(String[] args)
    {
            Value V = new Value ();
            
            Grid theGrid = new Grid (10,6);
            System.out.println(theGrid);
            System.out.println("Operations\n" +
"  display           dis         assign cell       as\n" +
"  fill              f           number            n\n" +
"  add cells         a           subtract cells    s\n" +
"  multiply cells    m           divide cells      d\n" +
"  add rows          ar          subtract rows     sr\n" +
"  multiply rows     mr          divide rows       dr\n" +
"  add columns       ac          subtract columns  sc\n" +
"  multiply columns  mc          divide columns    dc\n" +
"  insert row        ir          insert column     ic\n" +
"  delete row        delr        delete column     delc\n" +
"  quit              q\n"+"-->");                                // print out operations
        
         while (SC.hasNext())
        {
            Scanner sc = new Scanner (System.in);
            String OperationString=SC.nextLine();
           
            if (OperationString.equalsIgnoreCase("dis"))
            {
                System.out.println(theGrid);
            }

            else if (OperationString.equalsIgnoreCase("n"))
            {
                try
                {
                    System.out.println("from row: ");
                    int r1 = sc.nextInt();
                    System.out.println("from column: ");
                    int c1 = sc.nextInt();
                    System.out.println("to row: ");
                    int r2 = sc.nextInt();
                    System.out.println("to column: ");
                    int c2 = sc.nextInt();

                    theGrid.Number(r1, c1, r2, c2);
                    System.out.println(theGrid.toString());
                }
                catch (Exception ex)
                {
                    System.out.println("Invalid input! Type again!");
                }
            }

            else if (OperationString.equalsIgnoreCase("as"))
            {
                try
                {
                    Scanner sc1 = new Scanner (System.in);
                    System.out.println("row: ");
                    int r1 = sc.nextInt();
                    System.out.println("column: ");
                    int c1 = sc.nextInt();
                    System.out.println("with value:");
                    String str = sc1.nextLine();

                    theGrid.assignCell(r1, c1, str);
                    System.out.println(theGrid.toString());
                }
                catch (Exception ex)
                {
                    System.out.println("Invalid input! Type again!");
                }
            }

            else if (OperationString.equalsIgnoreCase("f"))
            {
                Scanner sc1 = new Scanner (System.in);
                System.out.println("from row: ");
                int r1 = sc.nextInt();
                System.out.println("from column: ");
                int c1 = sc.nextInt();
                System.out.println("to row: ");
                int r2 = sc.nextInt();
                System.out.println("to column: ");
                int c2 = sc.nextInt();
                System.out.println("with value:");
                String str = sc1.nextLine();

                theGrid.Fill(r1, c1, r2, c2,str);
                System.out.println(theGrid);
            }
            
            else if  (OperationString.equalsIgnoreCase("a"))
            {
                System.out.println("first row ");
                int r1 = sc.nextInt();
                System.out.println("first column: ");
                int c1 = sc.nextInt();
                System.out.println("second row: ");
                int r2 = sc.nextInt();
                System.out.println("second column: ");
                int c2 = sc.nextInt();
                System.out.println("to row: ");
                int r3 = sc.nextInt();
                System.out.println("to column: ");
                int c3 = sc.nextInt();
                
                theGrid.addCell(r1, c1, r2, c2, r3, c3);
                System.out.println(theGrid);
            }
            
            else if  (OperationString.equalsIgnoreCase("s"))
            {
                System.out.println("first row ");
                int r1 = sc.nextInt();
                System.out.println("first column: ");
                int c1 = sc.nextInt();
                System.out.println("second row: ");
                int r2 = sc.nextInt();
                System.out.println("second column: ");
                int c2 = sc.nextInt();
                System.out.println("to row: ");
                int r3 = sc.nextInt();
                System.out.println("to column: ");
                int c3 = sc.nextInt();
                
                theGrid.subCell(r1, c1, r2, c2, r3, c3);
                System.out.println(theGrid);
            }
            
            else if  (OperationString.equalsIgnoreCase("m"))
            {
                System.out.println("first row ");
                int r1 = sc.nextInt();
                System.out.println("first column: ");
                int c1 = sc.nextInt();
                System.out.println("second row: ");
                int r2 = sc.nextInt();
                System.out.println("second column: ");
                int c2 = sc.nextInt();
                System.out.println("to row: ");
                int r3 = sc.nextInt();
                System.out.println("to column: ");
                int c3 = sc.nextInt();
                
                theGrid.mulCell(r1, c1, r2, c2, r3, c3);
                System.out.println(theGrid);
            }

            else if  (OperationString.equalsIgnoreCase("d"))
            {
                System.out.println("first row ");
                int r1 = sc.nextInt();
                System.out.println("first column: ");
                int c1 = sc.nextInt();
                System.out.println("second row: ");
                int r2 = sc.nextInt();
                System.out.println("second column: ");
                int c2 = sc.nextInt();
                System.out.println("to row: ");
                int r3 = sc.nextInt();
                System.out.println("to column: ");
                int c3 = sc.nextInt();
                
                theGrid.divCell(r1, c1, r2, c2, r3, c3);
                System.out.println(theGrid);
            }
            
            else if (OperationString.equalsIgnoreCase("delr"))
            {
                System.out.println("delete row: ");
                int n = sc.nextInt();
                theGrid.deleteRow(n);
                System.out.println(theGrid);
            }

            else if (OperationString.equalsIgnoreCase("delc"))
            {
                System.out.println("delete column: ");
                int n = sc.nextInt();
                theGrid.deleteCol(n);
                System.out.println(theGrid);
            }
            
            else if (OperationString.equalsIgnoreCase("ir"))
            {
                System.out.println("insert row: ");
                int n = sc.nextInt();
                theGrid.insertRow(n);
                System.out.println(theGrid);
            }
            
            else if (OperationString.equalsIgnoreCase("ic"))
            {
                System.out.println("insert column: ");
                int n = sc.nextInt();
                theGrid.insertCol(n);
                System.out.println(theGrid);
            }
            
            else if (OperationString.equalsIgnoreCase("ar"))
            {
                System.out.println("first row");
                int r1 = sc.nextInt();
                System.out.println("second row");
                int r2 = sc.nextInt();
                System.out.println("to row");
                int r3 = sc.nextInt();
                theGrid.addRows(r1, r2, r3);
                System.out.println(theGrid);
             }
            
            else if (OperationString.equalsIgnoreCase("sr"))
            {
                System.out.println("first row");
                int r1 = sc.nextInt();
                System.out.println("second row");
                int r2 = sc.nextInt();
                System.out.println("to row");
                int r3 = sc.nextInt();
                theGrid.subRows(r1, r2, r3);
                System.out.println(theGrid);
             }
            
            else if (OperationString.equalsIgnoreCase("mr"))
            {
                System.out.println("first row");
                int r1 = sc.nextInt();
                System.out.println("second row");
                int r2 = sc.nextInt();
                System.out.println("to row");
                int r3 = sc.nextInt();
                theGrid.mulRows(r1, r2, r3);
                System.out.println(theGrid);
             }
                
            else if (OperationString.equalsIgnoreCase("dr"))
            {
                System.out.println("first row");
                int r1 = sc.nextInt();
                System.out.println("second row");
                int r2 = sc.nextInt();
                System.out.println("to row");
                int r3 = sc.nextInt();
                theGrid.divRows(r1, r2, r3);
                System.out.println(theGrid);
             }
            
            else if (OperationString.equalsIgnoreCase("ac"))
            {
                System.out.println("first column");
                int c1 = sc.nextInt();
                System.out.println("second column");
                int c2 = sc.nextInt();
                System.out.println("to column");
                int c3 = sc.nextInt();
                theGrid.addCols(c1, c2, c3);
                System.out.println(theGrid);
             }
            
            else if (OperationString.equalsIgnoreCase("sc"))
            {
                System.out.println("first column");
                int c1 = sc.nextInt();
                System.out.println("second column");
                int c2 = sc.nextInt();
                System.out.println("to column");
                int c3 = sc.nextInt();
                theGrid.subCols(c1, c2, c3);
                System.out.println(theGrid);
             }
            else if (OperationString.equalsIgnoreCase("mc"))
            {
                System.out.println("first column");
                int c1 = sc.nextInt();
                System.out.println("second column");
                int c2 = sc.nextInt();
                System.out.println("to column");
                int c3 = sc.nextInt();
                theGrid.mulCols(c1, c2, c3);
                System.out.println(theGrid);
             }
            else if (OperationString.equalsIgnoreCase("dc"))
            {
                System.out.println("first column");
                int c1 = sc.nextInt();
                System.out.println("second column");
                int c2 = sc.nextInt();
                System.out.println("to column");
                int c3 = sc.nextInt();
                theGrid.divCols(c1, c2, c3);
                System.out.println(theGrid);
             }
            else if (OperationString.equalsIgnoreCase("q"))
            {
                System.out.println("Thank you for using!");
            	System.exit(0);
            }
            
            else 
            {
                System.out.println("Invalid input! Type again!");
            }
        }
    }
}
