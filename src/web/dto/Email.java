package web.dto;

import java.util.Date;

public class Email {

   private int emailno;
   private String title;
   private String content;
   private String isall;
   private String useremail;
   private Date writedate;
   
   public Date getWritedate() {
      return writedate;
   }
   public void setWritedate(Date writedate) {
      this.writedate = writedate;
   }
   public String getUseremail() {
      return useremail;
   }
   public void setUseremail(String useremail) {
      this.useremail = useremail;
   }
   public int getEmailno() {
      return emailno;
   }
   public void setEmailno(int emailno) {
      this.emailno = emailno;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getIsall() {
      return isall;
   }
   public void setIsall(String isall) {
      this.isall = isall;
   }
   @Override
   public String toString() {
      return "Email [emailno=" + emailno + ", title=" + title + ", content=" + content + ", isall=" + isall + "]";
   }
   
   
}