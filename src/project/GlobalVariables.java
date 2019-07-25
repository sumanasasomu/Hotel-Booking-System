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
import java.text.*;
import java.util.Date;
public class GlobalVariables {
    static GlobalVariables gv;
    private int userid;
    private int hotelid;
    private int hotelid_w;
    private String username;
    private String hotelname;
    private String location;
    private int persons;
    private int rooms;
    private int Wnum;
  
    private int pricecancel;
    private String cancelcharges;
    private int Days_cancel;
    private String BidCancel;

    private String Hotelname_w;

    private String BidModify;


    private String name;
    private String from_date;
    private String to_date;

   
    private String toD_M;
    private String fromD_M;
    private int no_of_typesofrooms;
    private int no_of_typesofrooms_w;
    private int price_per_night;
    private int price_per_night_w;
    private int bookingid;
    private int No_of_days=0;
    private int No_of_days_w=0;


    private int persons_w;
    private int rooms_w;

    private String fromD_w;
    private String toD_w;
    int wifi ;
    int breakfast ;
    int gym ;
    int car_rentals ;
    int car_parking ;

    
    
    private int wl_clicked=0;
    private int hb_clicked=0;
    private int i=0;
    private int j=0;
    private int RnameArray[] = new int[5];
    private int Rchoices[] = new int[5];
    
     
    
    private int i_w=0;
    private int j_w=0;
    private int RnameArray_w[] = new int[100];
    private int Rchoices_w[] = new int[100];
    public int getPricecancel() {
        return pricecancel;
    }

    public void setPricecancel(int pricecancel) {
        this.pricecancel = pricecancel;
    }

    public String getCancelcharges() {
        return cancelcharges;
    }

    public void setCancelcharges(String cancelcharges) {
        this.cancelcharges = cancelcharges;
    }
    
    public int getDays_cancel() {
        return Days_cancel;
    }

    public void setDays_cancel(int Days_cancel) {
        this.Days_cancel = Days_cancel;
    }
    public String getBidCancel() {
        return BidCancel;
    }

    public void setBidCancel(String BidCancel) {
        this.BidCancel = BidCancel;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public void setI_w(int i) {
        this.i_w = i;
    }

    public void setJ_w(int j) {
        this.j_w = j;
    }
    
    public int getI_w() {
        return i_w;
    }

    public int getJ_w() {
        return j_w;
    }
        
    public int[] getRnameArray() {
        return RnameArray;
    }
    public int getRnameArray(int index) {
        return RnameArray[index];
    }
    public void setRnameArray(int n) {
        
        RnameArray[i++]=n;
    }
    
    
    public int[] getRnameArray_w() {
        return RnameArray_w;
    }
    public int getRnameArray_w(int index) {
        return RnameArray_w[index];
    }
    public void setRnameArray_w(int n) {
        
        RnameArray_w[i_w++]=n;
    }
    
    public int[] getRchoices() {
        return Rchoices;
    }
    public int getRchoices(int index) {
        return Rchoices[index];
    }
    public void setRchoices(int n) {
        Rchoices[j++]=n;
    }
    
    public int[] getRchoices_w() {
        return Rchoices_w;
    }
    public int getRchoices_w(int index) {
        return Rchoices_w[index];
    }
    public void setRchoices_w(int n) {
        Rchoices_w[j_w++]=n;
    }
    public int getNo_of_typesofrooms() {
        return no_of_typesofrooms;
    }

    public void setNo_of_typesofrooms(int no_of_typesofrooms) {
        this.no_of_typesofrooms = no_of_typesofrooms;
    }
    
     public int getNo_of_typesofrooms_w() {
        return no_of_typesofrooms_w;
    }

    public void setNo_of_typesofrooms_w(int no_of_typesofrooms_w) {
        this.no_of_typesofrooms_w = no_of_typesofrooms_w;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getHotelid() {
        return hotelid;
    }

    public void setHotelid(int hotelid) {
        this.hotelid = hotelid;
    }
    
        public int getHotelid_w() {
        return hotelid_w;
    }

    public void setHotelid_w(int hotelid) {
        this.hotelid_w = hotelid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(String Spersons) {
        int Ipersons = Integer.parseInt(Spersons);
        this.persons= Ipersons;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(String Srooms) {
        int Irooms = Integer.parseInt(Srooms);
        this.rooms = Irooms;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date DfromD) {
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String SfromD = sdf.format(DfromD);
        this.from_date = SfromD;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(Date DtoD) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String StoD = sdf.format(DtoD);
        this.to_date = StoD;
    }
    
     public String getToD_M() {
        return toD_M;
    }

    public void setToD_M(Date toD_M) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String StoD = sdf.format(toD_M);
        this.toD_M = StoD;
    }

    public String getFromD_M() {
        return fromD_M;
    }

    public void setFromD_M(Date fromD_M) {
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String SfromD = sdf.format(fromD_M); 
        this.fromD_M = SfromD;
    }
    
      public int getTotalPrice() {
          
        return No_of_days*price_per_night;
    }
    public int getTotalPrice_w() {
          
        return No_of_days_w*price_per_night_w;
    }
    
    
    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }
    
    public int getPrice_per_night() {
        return price_per_night;
    }
    public int getPrice_per_night_w() {
        return price_per_night_w;
    }

    public void setPrice_per_night(int price_per_night) {
        this.price_per_night = price_per_night;
    }
    
      public void setPrice_per_night_w(int price_per_night) {
        this.price_per_night_w = price_per_night;
    }
    
        public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }
    
    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }

    public int getCar_rentals() {
        return car_rentals;
    }

    public void setCar_rentals(int car_rentals) {
        this.car_rentals = car_rentals;
    }

    public int getCar_parking() {
        return car_parking;
    }

    public void setCar_parking(int car_parking) {
        this.car_parking = car_parking;
    }
    
        public String getBidModify() {
        return BidModify;
    }

    public void setBidModify(String BidModify) {
        this.BidModify = BidModify;
    }
    
    public int getWl_clicked() {
        return wl_clicked;
    }

    public void setWl_clicked(int wl_clicked) {
        this.wl_clicked = wl_clicked;
    }

    public int getHb_clicked() {
        return hb_clicked;
    }

    public void setHb_clicked(int hb_clicked) {
        this.hb_clicked = hb_clicked;
    }
    
    public int getWnum() {
        return Wnum;
    }

    public void setWnum(int Wnum) {
        this.Wnum = Wnum;
    }
    
    
    public String getHotelname_w() {
        return Hotelname_w;
    }

    public void setHotelname_w(String Hotelname_w) {
        this.Hotelname_w = Hotelname_w;
    }
    
        public int getPersons_w() {
        return persons_w;
    }

    public void setPersons_w(int persons_w) {
        this.persons_w = persons_w;
    }

    public int getRooms_w() {
        return rooms_w;
    }

    public void setRooms_w(int rooms_w) {
        this.rooms_w = rooms_w;
    }

    public String getFromD_w() {
        return fromD_w;
    }

    public String getToD_w() {
        return toD_w;
    }
 
    public void setFromD_w(String fromD_w) {
        this.fromD_w = fromD_w;
    }

    public void setToD_w(String toD_w) {
        this.toD_w = toD_w;
    }
    
    public int getNo_of_days()
    {
    
        long days_millisec;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
        Date in = myFormat.parse(gv.from_date);
        Date out = myFormat.parse(gv.to_date);
        days_millisec =(out.getTime() - in.getTime());
        No_of_days = (int)(days_millisec)/(1000*60*60*24);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return No_of_days;
    }
       public int getNo_of_days_w()
    {
    
        long days_millisec;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
        Date in = myFormat.parse(gv.fromD_w);
        Date out = myFormat.parse(gv.toD_w);
        days_millisec =(out.getTime() - in.getTime());
        No_of_days_w = (int)(days_millisec)/(1000*60*60*24);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return No_of_days_w;
    }
    
    
    private GlobalVariables(){
    }
    public static GlobalVariables getinstance()
    {
        if(gv == null)
        {
            gv=new GlobalVariables();
            return gv;
        }
        else
        {
            return gv;
        }
    }
    
}
