package dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookTicket {
	@Test(dataProvider = "bookingTheTickets")
	public void booking_Tickets(String dest, String src, int price)
	{
		System.out.println("From "+dest+" To "+src+" TicketPrice "+price);
	}
	
	@DataProvider
	public Object [][] bookingTheTickets()
	{
		Object[][] objArr=new Object[3][3];
		
		objArr[0][0]="Bangalore";
		objArr[0][1]="Mysore";
		objArr[0][2]=200;
		objArr[1][0]="Bangalore";
		objArr[1][1]="ooty";
		objArr[1][2]=300;
		objArr[2][0]="mysore";
		objArr[2][1]="ooty";
		objArr[2][2]=150;
		return objArr;
		
	}
	

}
