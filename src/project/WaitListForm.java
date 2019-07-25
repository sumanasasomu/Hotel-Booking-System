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
public class WaitListForm extends Frame implements WindowListener,ActionListener{
    Panel Pwlist;
    Label Lwlist;
    Label LName;
    Label LUname;
    JPanel panels[];
    Panel Innerpanel1;
    Label IPHname;
    Label IPtext1;
    Label IPtext2;
    Panel Innerpanel2;
    Button[] proceed;
    Button LBButton;
    LinkedList lstWnumbers = new LinkedList();
    LinkedList<String> lstfromDates_w = new LinkedList<String>();
    LinkedList<String> lstHnames_w = new LinkedList<String>();
    LinkedList<String> lsttoDates_w = new LinkedList<String>();
    LinkedList lstpersons_w = new LinkedList();
    LinkedList lstrooms_w = new LinkedList();
    int n=0;
    
    GlobalVariables gv = GlobalVariables.getinstance();
    public WaitListForm()
    {
        Pwlist = new Panel();
        add(Pwlist);
        Pwlist.setBackground(Color.BLACK);
        Lwlist = new Label("MY WAITING LIST",Label.CENTER);
        Lwlist.setFont(new Font("Arial",Font.BOLD,24));
        Lwlist.setForeground(new Color(255, 255, 0));
        LName = new Label("Name : "+gv.getName(),Label.CENTER);
        LName.setForeground(new Color(255, 255, 255));
        LName.setFont(new Font("Arial",Font.PLAIN,18));
        LUname = new Label("Username : "+gv.getUsername(),Label.CENTER);
        LUname.setForeground(new Color(255, 255, 255));
        LUname.setFont(new Font("Arial",Font.PLAIN,18));
        Pwlist.add(Lwlist);
        Pwlist.add(LName);
        Pwlist.add(LUname);
        Pwlist.setLayout(new BoxLayout (Pwlist, BoxLayout.Y_AXIS));
       
        
                ResultSet rs = null;
         
    
                try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL = "select distinct waitlist.w_num,Hname,fromD,toD,persons,rooms from waitlist,Hotel_parent where id = '"+gv.getUserid()+"' and waitlist.Hid=Hotel_parent.Hid";
                System.out.println(strSQL);
                rs = st.executeQuery(strSQL);

                while(rs.next()){
                    lstWnumbers.add(rs.getInt(1));
                    lstHnames_w.add(rs.getString(2));
                    lstfromDates_w.add(rs.getString(3));
                    lsttoDates_w.add(rs.getString(4));
                    lstpersons_w.add(rs.getInt(5));
                    lstrooms_w.add(rs.getInt(6));
                }
                }
                 catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                
            n = lstHnames_w.size();
            panels = new JPanel [n];
            proceed = new Button [n];

            for(int i=0;i<n;i++)
            {
                panels[i] = new JPanel();
                add(panels[i]);
                panels[i].setBackground(Color.BLACK);
                IPHname = new Label(lstHnames_w.get(i),Label.CENTER);
                IPHname.setFont(new Font("Arial",Font.ITALIC,20));
                IPHname.setForeground(new Color(0, 204, 204));
                Innerpanel1 = new Panel();
                Innerpanel2 = new Panel();
                IPtext1 = new Label("Check IN : "+lstfromDates_w.get(i).toString(),Label.CENTER);
                IPtext2 = new Label("Check OUT : "+lsttoDates_w.get(i).toString(),Label.CENTER);
                panels[i].add(IPHname);
                panels[i].add(Innerpanel1);
                Innerpanel1.setBackground(Color.BLACK);
                Innerpanel1.add(IPtext1);
                IPtext1.setForeground(new Color(255, 255, 0));
                Innerpanel1.add(IPtext2);
                IPtext2.setForeground(new Color(255, 255, 0));
                panels[i].add(Innerpanel2);
                Innerpanel2.setForeground(new Color(255, 255, 0));
                Innerpanel2.setBackground(Color.BLACK);
            //for(int j=0;j<2;j++)
            //{
            //Panel[] Innerpanels = new Panel [a[j]];
            //Innerpanels[j] = new Panel();
                    Panel panel_BID = new Panel();
                    Innerpanel2.add(panel_BID);
                    panel_BID.setBackground(Color.BLACK);
                    Label label_BID = new Label("Waiting List No. : "+lstWnumbers.get(i));
                    panel_BID.add(label_BID);
                    label_BID.setForeground(new Color(255, 51, 0));
                    Panel panel_people = new Panel();
                    Innerpanel2.add(panel_people);
                    panel_people.setBackground(Color.BLACK);
                    Label label_no_of_persons = new Label("No.of persons : "+lstpersons_w.get(i));
                    panel_people.add(label_no_of_persons);
                    Panel panel_rooms = new Panel();
                    Innerpanel2.add(panel_rooms);
                    panel_rooms.setBackground(Color.BLACK);
                    Label label_no_of_rooms = new Label("No.of rooms : "+lstrooms_w.get(i));
                    panel_people.add(label_no_of_rooms);
                    //values[j] = new SpinnerModel(0,0,j,1);
                    Innerpanel2.setLayout(new BoxLayout(Innerpanel2,BoxLayout.Y_AXIS));
                    proceed[i] = new Button("PROCEED");
                    proceed[i].setBackground(new Color(51, 51, 51));
                    proceed[i].setForeground(new Color(255, 255, 0));
                    proceed[i].addActionListener(this);
                    if((int)lstWnumbers.get(i) == 1)
                    {
                    panels[i].add(proceed[i]);
                    }
                //}
                panels[i].setLayout(new BoxLayout (panels[i], BoxLayout.Y_AXIS));
                Innerpanel1.setLayout(new BoxLayout (Innerpanel1, BoxLayout.X_AXIS));
                
                }
                if(n==0)
                {
                  Label Lmessage = new Label("You dont have any pending Bookings ",Label.CENTER);
                  add(Lmessage);
                  Lmessage.setFont(new Font("Arial",Font.PLAIN,18));
                  Lmessage.setForeground(Color.RED);
                  Lmessage.setBackground(Color.BLACK);
                }
                LBButton = new Button("BACK");
                add(LBButton);
                LBButton.addActionListener(this);
                LBButton.setBackground(new Color(51, 51, 51));
                LBButton.setForeground(new Color(255, 255, 0));
                setSize(1000,1000);
                setVisible(true);
                setLayout(null);
                setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
                setLocationRelativeTo(null);
                addWindowListener(this);
                
    }
    
    public static void main(String[] args) {
       WaitListForm wlf = new WaitListForm();
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
        int m = 0;
        for(int a =0;a < n;a++)
        {
            if(proceed[a].hasFocus())
            {
                m = a;
                break;
            }
        }
        
        System.out.println(m);
        gv.setHotelname_w(lstHnames_w.get(m).toString());
        ResultSet rs = null;
         try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st2 = con.createStatement();
                String strSQL = "select hotel_parent.hid from hotel_parent where hotel_parent.Hname='"+gv.getHotelname_w()+"'";
                System.out.println(strSQL);
                rs = st2.executeQuery(strSQL);
                rs.next();
                gv.setHotelid_w(rs.getInt(1));
                }
                 catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
        
        
        gv.setWnum((int)lstWnumbers.get(m));
        gv.setFromD_w(lstfromDates_w.get(m).toString());
        gv.setToD_w(lsttoDates_w.get(m).toString());
        gv.setPersons_w((int)lstpersons_w.get(m));
        gv.setRooms_w((int)lstrooms_w.get(m));
        
           
    if(LBButton.hasFocus()){
        gv.setI_w(0);
        gv.setJ_w(0);
        Welcome w_fromWaitList = new Welcome();
        w_fromWaitList.setVisible(true);
        w_fromWaitList.pack();
        w_fromWaitList.setLocationRelativeTo(null);
        dispose();
    }
    else{
            gv.setI_w(0);
            gv.setJ_w(0);
            RoomsForm_w rf_fromWaitlistform = new RoomsForm_w();
            rf_fromWaitlistform.setVisible(true);
            rf_fromWaitlistform.pack();
            rf_fromWaitlistform.setLocationRelativeTo(null);
            dispose();
    }
    
    }
    
            
}