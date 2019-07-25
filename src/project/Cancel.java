/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import static project.GlobalVariables.gv;

/**
 *
 * @author WIN10
 */
public class Cancel extends Frame implements WindowListener,ActionListener{
    Panel PCancel;
    Label LCancel;
    Label LName;
    Label LUname;
    JPanel panels[];
    Panel Innerpanel1;
    Label IPHname;
    Label IPtext1;
    Label IPtext2;
    Panel Innerpanel2;
    Button cancel[];
    Button LBbutton;
    LinkedList lstbookingid = new LinkedList();
    LinkedList<String> lstfromDates = new LinkedList<String>();


    int n=0;
    
    GlobalVariables gv = GlobalVariables.getinstance();
    public Cancel()
    {
        PCancel = new Panel();
        add(PCancel);
        PCancel.setBackground(Color.BLACK);
        LCancel = new Label("CANCELLATION",Label.CENTER);
        LCancel.setFont(new Font("Arial",Font.BOLD,24));
        LCancel.setForeground(new Color(0, 204, 204));
        LName = new Label("Name : "+gv.getName(),Label.CENTER);
        LName.setForeground(new Color(255, 0, 255));
        LName.setFont(new Font("Arial",Font.PLAIN,18));
        LUname = new Label("Username : "+gv.getUsername(),Label.CENTER);
        LUname.setForeground(new Color(255, 0, 255));
        LUname.setFont(new Font("Arial",Font.PLAIN,18));
        PCancel.add(LCancel);
        PCancel.add(LName);
        PCancel.add(LUname);
        PCancel.setLayout(new BoxLayout (PCancel, BoxLayout.Y_AXIS));
       
        
        
            LinkedList<String> lstHnames = new LinkedList<String>();
            LinkedList<String> lsttoDates = new LinkedList<String>();
            LinkedList lstpersons = new LinkedList();
            LinkedList lstrooms = new LinkedList();
        
                ResultSet rs = null;
         
    
                try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL = "select distinct transaction_child.Bid,Hname,fromD,toD,persons,rooms from Transaction_child,transaction_parent,Hotel_parent where id = '"+gv.getUserid()+"' and Transaction_parent.Hid=Hotel_parent.Hid and Transaction_parent.Bid=Transaction_child.Bid";
                System.out.println(strSQL);
                rs = st.executeQuery(strSQL);

                while(rs.next()){
                    lstbookingid.add(rs.getInt(1));
                    lstHnames.add(rs.getString(2));
                    lstfromDates.add(rs.getString(3));
                    lsttoDates.add(rs.getString(4));
                    lstpersons.add(rs.getInt(5));
                    lstrooms.add(rs.getInt(6));
                }
                }
                 catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
              
            
            n = lstHnames.size();
            System.out.println("the ni is : "+n);
            panels = new JPanel [n];
            cancel = new Button [n];

            for(int i=0;i<n;i++)
            {
                panels[i] = new JPanel();
                add(panels[i]);
                panels[i].setBackground(Color.BLACK);
                IPHname = new Label(lstHnames.get(i),Label.CENTER);
                IPHname.setFont(new Font("Arial",Font.ITALIC,20));
                IPHname.setForeground(new Color(255, 255, 0));
                Innerpanel1 = new Panel();
                Innerpanel2 = new Panel();
                IPtext1 = new Label("Check IN : "+lstfromDates.get(i).toString(),Label.CENTER);
                IPtext2 = new Label("Check OUT : "+lsttoDates.get(i).toString(),Label.CENTER);
                panels[i].add(IPHname);
                panels[i].add(Innerpanel1);
                Innerpanel1.setBackground(Color.BLACK);
                
                Innerpanel1.add(IPtext1);
                IPtext1.setForeground(new Color(255, 255, 0));
                Innerpanel1.add(IPtext2);
                IPtext2.setForeground(new Color(255, 255, 0));
                panels[i].add(Innerpanel2);
                Innerpanel2.setBackground(Color.BLACK);
            //for(int j=0;j<2;j++)
            //{
            //Panel[] Innerpanels = new Panel [a[j]];
            //Innerpanels[j] = new Panel();
                    Panel panel_BID = new Panel();
                    Innerpanel2.add(panel_BID);
                    panel_BID.setBackground(Color.BLACK);
                    Label label_BID = new Label("Bookin ID : "+lstbookingid.get(i));
                    panel_BID.add(label_BID);
                    label_BID.setForeground(new Color(255, 51, 0));
                    Panel panel_people = new Panel();
                    Innerpanel2.add(panel_people);
                    panel_people.setBackground(Color.BLACK);
                    Label label_no_of_persons = new Label("No.of persons : "+lstpersons.get(i));
                    panel_people.add(label_no_of_persons);
                    label_no_of_persons.setForeground(new Color(255, 255, 0));
                    Panel panel_rooms = new Panel();
                    Innerpanel2.add(panel_rooms);
                    panel_rooms.setBackground(Color.BLACK);
                    Label label_no_of_rooms = new Label("No.of rooms : "+lstrooms.get(i));
                    panel_people.add(label_no_of_rooms);
                    label_no_of_rooms.setForeground(new Color(255, 255, 0));
                    //values[j] = new SpinnerModel(0,0,j,1);
                    Innerpanel2.setLayout(new BoxLayout(Innerpanel2,BoxLayout.Y_AXIS));
                    cancel[i] = new Button("Cancel");
                    cancel[i].addActionListener(this);
                    panels[i].add(cancel[i]);
//                    cancel[i].setBackground(new Color(51, 51, 51));
                    cancel[i].setBackground(new Color(0, 0, 0));
                    cancel[i].setForeground(Color.RED);
               
                panels[i].setLayout(new BoxLayout (panels[i], BoxLayout.Y_AXIS));
                Innerpanel1.setLayout(new BoxLayout (Innerpanel1, BoxLayout.X_AXIS));
                
                }
            
                if(n==0)
                {
                  Label Lmessage = new Label("You dont have any Bookings",Label.CENTER);
                  add(Lmessage);
                  Lmessage.setFont(new Font("Arial",Font.PLAIN,18));
                  Lmessage.setForeground(Color.RED);
                  Lmessage.setBackground(Color.BLACK);
                }
            
                Panel BBpanel = new Panel();
                add(BBpanel);
                BBpanel.setBackground(Color.BLACK);
                LBbutton = new Button("BACK");
                BBpanel.add(LBbutton);
                LBbutton.addActionListener(this);
                LBbutton.setBackground(new Color(51, 51, 51));
                LBbutton.setForeground(new Color(255, 255, 0));
                setSize(1000,1000);
                setVisible(true);
                setLayout(null);
                setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
                setLocationRelativeTo(null);
                addWindowListener(this);
    }
    
    public static void main(String[] args) {
        Cancel c = new Cancel();
    }
    public void windowOpened(WindowEvent we) { } 
    public void windowClosing(WindowEvent we) 
    {
    dispose();
    }    
    public void windowClosed(WindowEvent we) { }
    public void windowIconified(WindowEvent we) { }  
    public void windowDeiconified(WindowEvent we) { }   
    public void windowActivated(WindowEvent we) {  }    
    public void windowDeactivated(WindowEvent we) {  }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(LBbutton.hasFocus()){
        Welcome w_fromCancel = new Welcome();
        w_fromCancel.setVisible(true);
        w_fromCancel.pack();
        w_fromCancel.setLocationRelativeTo(null);
        dispose();
        }
        else if(n>0)
        {
        int m = 0;
        for(int a =0;a < n;a++)
        {
            if(cancel[a].hasFocus())
            {
                m = a;
                break;
            }
        }
        System.out.println(m);
        gv.setBidCancel(lstbookingid.get(m).toString());
        long days_millisec;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
        Date currDate = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strCurrdate = sdf.format(currDate);
        Date d1 = myFormat.parse(lstfromDates.get(m));
        Date d2 = myFormat.parse(strCurrdate);
        days_millisec =(d1.getTime() - d2.getTime());
        int days = (int)(days_millisec)/(1000*60*60*24);
        gv.setDays_cancel(days);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
            {
            ResultSet rs = null;
            Connection con = ConnectDB.getConnection();
            Statement st2 = con.createStatement();
            String strSQL = "select amount from transaction_parent where transaction_parent.Bid='"+gv.getBidCancel()+"'";
            System.out.println(strSQL);
            rs = st2.executeQuery(strSQL);
            rs.next();
            int amt = rs.getInt(1);            
            gv.setPricecancel(amt);
            if(gv.getDays_cancel()==1 || gv.getDays_cancel()==2){
                gv.setCancelcharges(Integer.toString(amt/2));}
            else if(gv.getDays_cancel()<1){
                gv.setCancelcharges("Cancellation Not Allowed");
            }
            else if(gv.getDays_cancel()>2){
                gv.setCancelcharges("No Charges");
            }
           
            
            }
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
        ConfirmCancellation cf_fromCancel= new ConfirmCancellation();
        cf_fromCancel.setVisible(true);
        cf_fromCancel.pack();
        cf_fromCancel.setLocationRelativeTo(null);
        dispose();
        
        }
    }
}