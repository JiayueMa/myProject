import java.util.*;

public class Staff {
   int staffID;
   int AuthorizeLevel;
   String name;
   String password;
   
   public Staff() {
	   
   }
   
   public String showStaffInfo() {
	  StringBuffer buff = new StringBuffer();
	  buff.append("ID: ");
	  buff.append(this.staffID);
	  buff.append(" Name: ");
	  buff.append(this.name);	  
	  buff.append(" Authorized Level: ");
	  buff.append(this.AuthorizeLevel);
	  return buff.toString();
   }
   
}
