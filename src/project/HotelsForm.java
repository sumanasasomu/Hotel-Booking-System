/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.WindowEvent;  
import java.awt.event.WindowListener;  
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
  
public class HotelsForm extends Frame implements WindowListener,ActionListener{  
Panel panels[];
Panel Plocation;
Label Llocation;
Label Hnamelabels[];
Label Haddresslabels[];
Label Hratinglabels[];
Label Hno_ratingslabels[];
Button b[];
Button LBbutton;
Panel BackBpanel;
int n;
GlobalVariables gv = GlobalVariables.getinstance();
LinkedList<String> lstHNames = new LinkedList<String>();
public HotelsForm () { 
    LinkedList lstHid = new LinkedList();
    //String arr_Hnames[] = {};
    LinkedList<String> lstHAddress = new LinkedList<String>();
    //String arr_Haddress[]= {};
    LinkedList lstHRating = new LinkedList();
    //int arr_Hrating[] = {};
    LinkedList lstHno_rating = new LinkedList();
    //int arr_no_ratings[] = {};
    
    
    ResultSet rs = null;
    
                try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL = "SELECT distinct HOTEL_PARENT.`Hid`,HOTEL_PARENT.`Hname`,hotel_parent.address,hotel_parent.rating,hotel_parent.no_of_ratings FROM hotel_parent,hotel_child,transaction_child WHERE HOTEL_PARENT.location= '"+ gv.getLocation() +"' AND HOTEL_PARENT.`Hid`= HOTEL_CHILD.`Hid`and hotel_child.rid not in (select rid from transaction_child where (transaction_child.`fromD`<='"+gv.getTo_date()+"' and transaction_child.`fromD`>='"+gv.getFrom_date()+"') or (transaction_child.`toD`<='"+gv.getTo_date()+"' and transaction_child.`toD`>='"+gv.getFrom_date()+"')) order by HOTEL_PARENT.Hname";
                System.out.println(strSQL);
                rs = st.executeQuery(strSQL);
                int m=0;
                while(rs.next())
                {
                    //rs.next();
                    //arr_Hnames[m] = rs.getString(1);
                    //arr_Haddress[m] = rs.getString(2);
                    //arr_Hrating[m] = rs.getInt(3);
                    //arr_no_ratings[m] = rs.getInt(4);
                    lstHid.add(rs.getInt(1));
                    lstHNames.add(rs.getString(2));
                    lstHAddress.add(rs.getString(3));
                    lstHRating.add(rs.getInt(4));
                    lstHno_rating.add(rs.getInt(5));
                    
                    m++;
                    
                }

                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
     n = lstHNames.size();
//    int n=0;
//    for(int k=0;k<size;k++)
//    {
//        int l;
//        for(l=0;l<k;l++)
//        {
//           if(lstHid.get(k)==lstHid.get(l))
//               break;
//        }
//           if(l==k)
//               n++;
//        
//    }
    
    System.out.println(n);
    addWindowListener(this);
    panels = new Panel [n];
    b = new Button [n];
    //labels = new Label [n];
    Hnamelabels = new Label [n];
    Haddresslabels = new Label [n];
    Hratinglabels = new Label [n];
    Hno_ratingslabels = new Label [n];
    Panel Plocation = new Panel();
    add(Plocation);
    Plocation.setBackground(Color.black);
    Label Llocation =new Label(gv.getLocation());
    Llocation.setFont( new Font("Arial",Font.BOLD,30));
    Llocation.setForeground(new Color(0, 204, 204));
    Plocation.add(Llocation,BorderLayout.CENTER);
    
    for (int i = 0;i<n;i++)
    {  
          panels[i] = new Panel();
          panels[i].setBackground(Color.BLACK);
          add(panels[i]);
          
    //labels[i] = new Label("Location"+(i+1));
    Hnamelabels[i] = new Label(lstHNames.get(i));
    Hnamelabels[i].setForeground(new Color(255, 102, 0));
    Haddresslabels[i] = new Label(lstHAddress.get(i));
    Haddresslabels[i].setForeground(new Color(255, 255, 0));
    Hratinglabels[i] = new Label("Rating : "+lstHRating.get(i).toString());
    Hratinglabels[i].setForeground(new Color(255, 255, 0));
    Hno_ratingslabels[i] = new Label("No. of Reviews "+"("+lstHno_rating.get(i).toString()+")");
    Hno_ratingslabels[i].setForeground(new Color(255, 255, 0));
    b[i] = new Button("VIEW");
    panels[i].add(Hnamelabels[i]);
    panels[i].add(Haddresslabels[i]);    
    panels[i].add(Hratinglabels[i]);    
    panels[i].add(Hno_ratingslabels[i]);
    panels[i].add(b[i]);   
    b[i].addActionListener(this); 
    b[i].setBackground(Color.BLACK);
    b[i].setForeground(Color.GREEN);
   
    b[i].setFont(new Font("Arial",Font.BOLD,14));
    panels[i].setLayout(new BoxLayout (panels[i], BoxLayout.Y_AXIS));
    //panels[i].add(labels[i]);
    panels[i].setBackground(Color.BLACK);
    Hnamelabels[i].setFont( new Font("Arial",Font.BOLD,16));
    Hratinglabels[i].setFont( new Font("Arial",Font.BOLD,13));
    }
    
     if(n==0)
       {
           Label Lmessage = new Label("SORRY! No Hotels are available",Label.CENTER);
           add(Lmessage);
           Lmessage.setFont(new Font("Arial",Font.PLAIN,18));
           Lmessage.setForeground(Color.RED);
           Lmessage.setBackground(Color.BLACK);
       }
    
    BackBpanel = new Panel();
    add(BackBpanel);
    BackBpanel.setBackground(Color.BLACK);
    LBbutton = new Button("BACK");
       BackBpanel.add(LBbutton);
       LBbutton.addActionListener(this);
       LBbutton.setBackground(new Color(51, 51, 51));
       LBbutton.setForeground(new Color(255, 255, 0));
    setBackground(Color.BLACK);
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));  
    setSize(500,500);  
    setVisible(true);  
}
public void windowActivated(WindowEvent arg0)  {
}
public void windowClosed(WindowEvent arg0) {  }  
public void windowClosing(WindowEvent arg0) {  

    dispose();  
}  
public void windowDeactivated(WindowEvent arg0) {  

}  
public void windowDeiconified(WindowEvent arg0) {  

}  
public void windowIconified(WindowEvent arg0) {  

}  
public void windowOpened(WindowEvent arg0) {  

}  
public static void main(String args[]){  
//HotelsForm h=new HotelsForm();  
}  

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(n>0){
        int m = 0;
        for(int a =0;a < n;a++)
        {
            if(b[a].hasFocus()){
                m = a;
                break;
            }
        }
        gv.setHotelname(lstHNames.get(m));
        ResultSet rs2 = null;
         try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL2 = "select hotel_parent.`Hid` from hotel_parent where hotel_parent.`Hname` = '"+ gv.getHotelname() +"'";
                System.out.println(strSQL2);
                rs2 = st.executeQuery(strSQL2);
                rs2.next();
                gv.setHotelid(rs2.getInt(1));
                }
         catch(Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
         } 
        System.out.println(m);
        }
        if(LBbutton.hasFocus())
        {
           gv.setI(0);
           gv.setJ(0);
            Mainform mf_fromhotelsForm = new Mainform();
        mf_fromhotelsForm.setVisible(true);
        mf_fromhotelsForm.pack();
        mf_fromhotelsForm.setLocationRelativeTo(null);
        dispose();
        }
        else{
             gv.setI(0);
             gv.setJ(0);
        RoomsForm rf_fromhotelsform =new RoomsForm();
        rf_fromhotelsform.setVisible(true);
        rf_fromhotelsform.pack();  
        rf_fromhotelsform.setLocationRelativeTo(null);
        dispose();
        }
    }
    
}