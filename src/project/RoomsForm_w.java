/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author RAHUL
 */
import java.awt.*;  
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;  
import javax.swing.*;
import java.awt.event.WindowEvent;  
import java.awt.event.WindowListener;  
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;
  
public class RoomsForm_w extends Frame implements WindowListener,ActionListener{  
Panel panels[];
Panel PHotelname;
Label LHotelname;
Label LRname[];
Label LAvailability[];
Panel Innerpanels[];
Label Innerlabels[];
TextField[] innerTextFields;
Panel PButton;
//Button LButton1;
Button Lbutton2;
Button LButton_decline;
Label Lerror;
Button LButton3;

int n;
int No_of_Textfields=0;

GlobalVariables gv = GlobalVariables.getinstance();
LinkedList lstCapacityColumn = new LinkedList();
LinkedList lstCapacity = new LinkedList();
LinkedList lstCapFreq = new LinkedList();
LinkedList lstBase = new LinkedList();
LinkedList lstPerPerson = new LinkedList();
LinkedList lstPrices = new LinkedList();


public RoomsForm_w() {
    
    ResultSet rs = null;
    
                try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL = "SELECT distinct HOTEL_PARENT.`Hid`,hotel_child.rid,HOTEL_CHILD.capacity,HOTEL_CHILD.basic_cost,HOTEL_CHILD.increment_per_person FROM hotel_parent,hotel_child,transaction_child WHERE HOTEL_PARENT.Hname= '"+ gv.getHotelname_w()+"' AND HOTEL_PARENT.`Hid`= HOTEL_CHILD.`Hid`and hotel_child.rid not in (select rid from transaction_child where (transaction_child.`fromD`<='"+gv.getToD_w()+"' and transaction_child.`fromD`>='"+gv.getFromD_w()+"') or (transaction_child.`toD`<='"+gv.getToD_w()+"' and transaction_child.`toD`>='"+gv.getFromD_w()+"')) order by HOTEL_PARENT.Hname";
                System.out.println(strSQL);
                rs = st.executeQuery(strSQL);
                while(rs.next())
                {
                    //System.out.println("updating");
                    lstCapacityColumn.add(rs.getInt(3));
                }
                
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
    
    int sizeofCapacityCol = lstCapacityColumn.size();
    
    ResultSet rs1 = null;
    
                try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL1 = "select tmp.ca, count(tmp.ca),tmp.base,tmp.PerPerson from(SELECT distinct HOTEL_PARENT.`Hid`,hotel_child.rid,HOTEL_CHILD.capacity as ca,HOTEL_CHILD.basic_cost as base,HOTEL_CHILD.increment_per_person as PerPerson FROM hotel_parent,hotel_child,transaction_child WHERE HOTEL_PARENT.Hname= '"+ gv.getHotelname_w() +"'AND HOTEL_PARENT.`Hid`= HOTEL_CHILD.`Hid` and hotel_child.rid not in (select rid from transaction_child where TRANSACTION_CHILD.fromD >= '"+ gv.getFromD_w()+"' AND TRANSACTION_CHILD.fromD <= '"+ gv.getToD_w() +"' AND TRANSACTION_CHILD.toD <= '"+ gv.getToD_w()+"' AND TRANSACTION_CHILD.toD >= '"+ gv.getFromD_w() +"')) tmp group by ca order by tmp.ca";
                System.out.println(strSQL1);
                rs1 = st.executeQuery(strSQL1);
                while(rs1.next())
                {
                    //System.out.println("updating");
                    lstCapacity.add(rs1.getInt(1));
                    gv.setRnameArray_w(rs1.getInt(1));
                    lstCapFreq.add(rs1.getInt(2));
                    lstBase.add(rs1.getInt(3));
                    lstPerPerson.add(rs1.getInt(4));
                }
                
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                n = lstCapacity.size();
                gv.setNo_of_typesofrooms_w(n);
    
for(int p= 0;p<lstCapacity.size();p++){
    No_of_Textfields+=(int)lstCapacity.get(p);
}   


    addWindowListener(this);
    panels = new Panel [n];
    //labels = new Label [n];
    Panel PHotelname = new Panel();
    add(PHotelname);
    PHotelname.setBackground(Color.BLACK);
    Label LHotelname =new Label(gv.getHotelname_w());
    PHotelname.add(LHotelname,BorderLayout.CENTER);
    LHotelname.setForeground(new Color(0, 204, 204));
    LHotelname.setFont(new Font("Arial",Font.BOLD,24));
    innerTextFields = new TextField [No_of_Textfields];
    int tfindex=0;
       for (int i = 0;i<n;i++)
       {
        Innerpanels = new Panel [(int)lstCapacity.get(i)];
        Innerlabels = new Label [(int)lstCapacity.get(i)];
        //innerTextFields = new TextField [No_of_Textfields];
        panels[i] = new Panel();  
        add(panels[i]);
        panels[i].setBackground(Color.BLACK);
        LRname = new Label [n];
        LAvailability = new Label [n];
        LRname[i] = new Label(giveRoomName((int)lstCapacity.get(i)));
        panels[i].add(LRname[i]);
        LRname[i].setForeground(new Color(255, 51, 0));
        LRname[i].setFont(new Font("Arial",Font.BOLD,18));
        if((int)lstCapFreq.get(i)==1){
            LAvailability[i] = new Label(" ["+(int)lstCapFreq.get(i)+" Room is Available ]");
        }
        else{
            LAvailability[i] = new Label(" ["+(int)lstCapFreq.get(i)+" Rooms are Available ]");
        }
            panels[i].add(LAvailability[i]);
            LAvailability[i].setForeground(new Color(255, 255, 0));
            for(int j=0;j<(int)lstCapacity.get(i);j++)
            {
            Innerpanels[j] = new Panel();
            panels[i].add(Innerpanels[j]);
            Innerpanels[j].setBackground(Color.BLACK);
           //Innerlabels[j] = new Label((j+1)+"person(s)");
           if(j==0){
               Innerlabels[j] = new Label("For "+(j+1)+" person - Rs."+((int)lstBase.get(i)+ (j+1)*(int)lstPerPerson.get(i)));
           } 
           else{
               Innerlabels[j] = new Label("For "+(j+1)+" people - Rs."+((int)lstBase.get(i)+ (j+1)*(int)lstPerPerson.get(i)));
           }
           lstPrices.add(((int)lstBase.get(i)+ (j+1)*(int)lstPerPerson.get(i)));
           Innerpanels[j].add(Innerlabels[j],BorderLayout.CENTER);
           Innerlabels[j].setForeground(new Color(255, 255, 0));
            //values[j] = new SpinnerModel(0,0,j,1);
            innerTextFields[tfindex] = new TextField();
            Innerpanels[j].add(innerTextFields[tfindex],BorderLayout.LINE_END);
            innerTextFields[tfindex].setForeground(new Color(255, 255, 255));
            innerTextFields[tfindex].setBackground(new Color(51, 51, 51));
        //Innerpanels[j].setLayout(new BoxLayout(Innerpanels[j],BoxLayout.X_AXIS));
            tfindex++;
            }  
        
    //labels[i] = new Label("Location"+(i+1));
    //panels[i].add(labels[i]);
    panels[i].setLayout(new BoxLayout(panels[i],BoxLayout.Y_AXIS));
    }
       if(n==0)
       {
           Label Lmessage = new Label("SORRY! No rooms are available",Label.CENTER);
           add(Lmessage);
           Lmessage.setFont(new Font("Arial",Font.PLAIN,18));
           Lmessage.setForeground(Color.RED);
           Lmessage.setBackground(Color.BLACK);
       }
      
       Lerror = new Label("",Label.CENTER);
       add(Lerror);
       Lerror.setForeground(Color.RED);
       Lerror.setBackground(Color.BLACK);
       PButton = new Panel();
       add(PButton);
       PButton.setBackground(Color.BLACK);
       //LButton1 = new Button("Put me in Waiting list");
       LButton3 = new Button("BACK");
       PButton.add(LButton3);
       LButton3.addActionListener(this);
       LButton3.setBackground(new Color(51, 51, 51));
       LButton3.setForeground(new Color(255, 255, 0));
       
       LButton_decline = new Button("Decline");
//       if(gv.getHb_clicked()==1){
//           PButton.add(LButton1);
//           LButton1.addActionListener(this);
//       }
       //else if(gv.getWl_clicked()==1){
           PButton.add(LButton_decline);
           LButton_decline.setForeground(new Color(255, 255, 0));
           LButton_decline.setBackground(new Color(51, 51, 51));
           LButton_decline.addActionListener(this);
       //}
//       gv.setWl_clicked(0);
//       gv.setHb_clicked(0);
       Lbutton2 = new Button("BOOK");
       if(n>0)
       {
       PButton.add(Lbutton2);
       }
       Lbutton2.setBackground(new Color(51, 51, 51));
       Lbutton2.setForeground(new Color(255, 255, 0));
       Lbutton2.addActionListener(this);
       PButton.setLayout(new BoxLayout(PButton, BoxLayout.X_AXIS));
setSize(900,600);  
setVisible(true);  
setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));  
}
public String giveRoomName(int c){
    String s = null;
    
        switch(c)
        {
            case 1 : s = "SINGLE OCCUPANCY ROOMS";
                        break;
            case 2 : s = "DOUBLE DELUXE ROOMS";
                        break;
            case 3 : s = "SMALL FAMILY ROOMS (3)";
                        break;
            case 4 : s = "LARGE FAMILY ROOMS (4)";
                        break;
        }
      return s;      
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
public void actionPerformed(ActionEvent ae)
{
//    int err_availability = 0,err_roomsasked = 0,err_noselection = 0,err_format=0,err_people= 0;
//    int q=0,l=0,a,k=0; 
//    int No_of_Textfields = lstPrices.size();
//    
//    int sum_of_ktf = 0;
//    int sum_of_kpeople = 0;
//    
//    int total_sumof_tf = 0; 
//    int total_sum_people = 0;
//    
//    
//    
//    
//    gv.setI_w(0);
//    gv.setJ_w(0);
//    
//    for(int i=0 ; i<No_of_Textfields ; i++)
//    {
//        boolean b = Pattern.matches("[0-9]{1}",innerTextFields[i].getText());
//        if(innerTextFields[i].getText().isEmpty()){
//            continue;}
//        else if(!b)
//        {
//            err_format = 1;
//            break;
//        }
//    }
//    
//
//    for(int i=0 ; i<(int)lstCapacity.size();i++)
//    {
//        sum_of_kpeople = 0;
//        sum_of_ktf = 0;
//        
//        k =0;
//        
//        while(k<(int)lstCapacity.get(q))
//        {
//            try
//            {
//                a = Integer.valueOf((innerTextFields[l++].getText()));               
//            }
//            catch(NumberFormatException e)
//            {
//                a = 0;
//            }
//            sum_of_ktf =  sum_of_ktf + a;
//            sum_of_kpeople = sum_of_kpeople + ((k+1)*a);
//            System.out.println("each line sum :"+ (k+1)*a);
//            
//            k++;
//        }
//        
//        gv.setRchoices_w(sum_of_ktf);
//        
//        if(sum_of_ktf >(int)lstCapFreq.get(q))
//        {
//            err_availability = 1;
//        }
//        
//        System.out.println("\npartial sum : "+sum_of_kpeople);
//        
//        total_sum_people += sum_of_kpeople;
//        total_sumof_tf += sum_of_ktf;
//        
//        q++;
//        
//    }
//    
//    
//    if(total_sumof_tf != gv.getRooms_w())
//    {
//        err_roomsasked = 1;
//    }
//    
//    if(total_sumof_tf == 0)
//    {
//        err_noselection = 1;
//    }
//
//    if(total_sum_people != gv.getPersons_w())
//    {
//        err_people = 1;
//    }
//    // for updatig labels 
//    if(err_format == 1)
//    {
//        Lerror.setText("please give inputs in numbers only");
//        for(int i=0 ; i<No_of_Textfields ; i++)
//        {
//            innerTextFields[i].setText(null);
//        }
//    }
//    
//    else if(err_noselection == 1)
//    {
//        Lerror.setText("select your rooms");
//    }
//    
//    else if(err_availability==1)
//    {
//        Lerror.setText("check availability");
//    }
//    
//    else if(err_roomsasked==1)
//    {
//        Lerror.setText("Your selection doesn't match your requirement of rooms");
//    }
//    
//    else if(err_people==1)
//    {
//        Lerror.setText("check no.of.People for room allocation");
//    }
//    
//    
//   
//
//    System.out.println("rooms: "+ total_sumof_tf);
//    System.out.println("people: "+ total_sum_people);

    if(Lbutton2.hasFocus())
    {
        
       int err_availability = 0,err_roomsasked = 0,err_noselection = 0,err_format=0,err_people= 0;
    int q=0,l=0,a,k=0; 
    int No_of_Textfields = lstPrices.size();
    
    int sum_of_ktf = 0;
    int sum_of_kpeople = 0;
    
    int total_sumof_tf = 0; 
    int total_sum_people = 0;
    
    
    
    
    gv.setI_w(0);
    gv.setJ_w(0);
    
    for(int i=0 ; i<No_of_Textfields ; i++)
    {
        boolean b = Pattern.matches("[0-9]{1}",innerTextFields[i].getText());
        if(innerTextFields[i].getText().isEmpty()){
            continue;}
        else if(!b)
        {
            err_format = 1;
            break;
        }
    }
    

    for(int i=0 ; i<(int)lstCapacity.size();i++)
    {
        sum_of_kpeople = 0;
        sum_of_ktf = 0;
        
        k =0;
        
        while(k<(int)lstCapacity.get(q))
        {
            try
            {
                a = Integer.valueOf((innerTextFields[l++].getText()));               
            }
            catch(NumberFormatException e)
            {
                a = 0;
            }
            sum_of_ktf =  sum_of_ktf + a;
            sum_of_kpeople = sum_of_kpeople + ((k+1)*a);
            System.out.println("each line sum :"+ (k+1)*a);
            
            k++;
        }
        
        gv.setRchoices_w(sum_of_ktf);
        
        if(sum_of_ktf >(int)lstCapFreq.get(q))
        {
            err_availability = 1;
        }
        
        System.out.println("\npartial sum : "+sum_of_kpeople);
        
        total_sum_people += sum_of_kpeople;
        total_sumof_tf += sum_of_ktf;
        
        q++;
        
    }
    
    
    if(total_sumof_tf != gv.getRooms_w())
    {
        err_roomsasked = 1;
    }
    
    if(total_sumof_tf == 0)
    {
        err_noselection = 1;
    }

    if(total_sum_people != gv.getPersons_w())
    {
        err_people = 1;
    }
    // for updatig labels 
    if(err_format == 1)
    {
        Lerror.setText("please give inputs in numbers only");
        for(int i=0 ; i<No_of_Textfields ; i++)
        {
            innerTextFields[i].setText(null);
        }
    }
    
    else if(err_noselection == 1)
    {
        Lerror.setText("select your rooms");
    }
    
    else if(err_availability==1)
    {
        Lerror.setText("check availability");
    }
    
    else if(err_roomsasked==1)
    {
        Lerror.setText("Your selection doesn't match your requirement of rooms");
    }
    
    else if(err_people==1)
    {
        Lerror.setText("check no.of.People for room allocation");
    }
    
    
   

    System.out.println("rooms: "+ total_sumof_tf);
    System.out.println("people: "+ total_sum_people); 
        
        
       if(err_availability==0 && err_noselection==0 && err_roomsasked==0 && err_format== 0 && err_people == 0)
       {
        int Tprice=0;
                
        for(int i=0;i<No_of_Textfields;i++)
        {
            int b;
            int prices_for_occupants = (int)(lstPrices.get(i));
            try
            {
                 b =  Integer.valueOf(innerTextFields[i].getText());
            }
            catch(NumberFormatException e)
            {
                b = 0;
            }

            Tprice += (prices_for_occupants*b);
        }
        gv.setPrice_per_night_w(Tprice);

        System.out.println("availability "+err_availability+"\nnoselection "+err_noselection+"\nroomsasked "+err_roomsasked);
    
        for(int i = 0;i<n;i++)
        { 
            System.out.print(gv.getRnameArray_w(i)+"\t");
            System.out.print(gv.getRchoices_w(i)+"\n");
        }
        System.out.println((int) gv.getPrice_per_night_w());
        
        extras e_fromroomsform = new extras();
        e_fromroomsform.setVisible(true);
        e_fromroomsform.pack();
        e_fromroomsform.setLocationRelativeTo(null);
        dispose();
        
        }
    }   
//    else if(LButton1.hasFocus())
//    {
//         try
//                { 
//                    ResultSet rs = null;
//                Connection con = ConnectDB.getConnection();
//                Statement st = con.createStatement();
//                String strSQL1 = "(select max(w_num) from waitlist)";
//                rs = st.executeQuery(strSQL1);
//                rs.next();
//                int nextw_num = rs.getInt(1)+1;
//                String strSQL2 = "insert into waitlist(w_num,Hid,id,persons,rooms,fromD,toD) values('"+nextw_num+"','"+gv.getHotelid()+"','"+gv.getUserid()+"','"+gv.getPersons()+"','"+gv.getRooms()+"','"+gv.getFrom_date()+"','"+gv.getTo_date()+"')";
//                System.out.println(strSQL2);
//                st.executeUpdate(strSQL2); 
//                }
//                catch(Exception e)
//                {
//                    System.out.println(e.getMessage());
//                    e.printStackTrace();
//                }
//    }
    else if(LButton_decline.hasFocus())
    {
        
        gv.setI_w(0);
        gv.setJ_w(0);
         try
                { 
                Connection con = ConnectDB.getConnection();
                Statement st = con.createStatement();
                String strSQL = "delete from waitlist where w_num = '"+gv.getWnum()+"'";
                String strSQL1 = "set @rank := 0"; 
                String strSQL2 =  "update waitlist set w_num = @rank:=@rank+1";
                System.out.println(strSQL);
                System.out.println(strSQL1);
                System.out.println(strSQL2);
                st.executeUpdate(strSQL);
                st.executeUpdate(strSQL1);
                st.executeUpdate(strSQL2);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
         Welcome w_fromRoomsForm_w = new Welcome();
         w_fromRoomsForm_w.setVisible(true);
         w_fromRoomsForm_w.pack();
         w_fromRoomsForm_w.setLocationRelativeTo(null);
         dispose();
    }
    
    else if(LButton3.hasFocus()){
        
        gv.setI_w(0);
        gv.setJ_w(0);
        WaitListForm wf_fromRoomsForm_w = new WaitListForm();
        wf_fromRoomsForm_w.setVisible(true);
        wf_fromRoomsForm_w.pack();
        wf_fromRoomsForm_w.setLocationRelativeTo(null);
        dispose();
    }

}


    public static void main(String args[])
    {  
        RoomsForm_w rf_w = new RoomsForm_w();  
    }  
}
