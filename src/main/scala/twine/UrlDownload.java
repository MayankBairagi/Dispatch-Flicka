package twine;

import java.io.*;
import java.net.*;

public class UrlDownload {
    BufferedInputStream bis;
    BufferedOutputStream bos;
final static int size=1024;
public static void fileUrl(String fAddress, String
localFileName, String destinationDir) {
OutputStream outStream = null;
URLConnection  uCon = null;

InputStream is = null;
try {
URL Url;
byte[] buf;
int ByteRead,ByteWritten=0;
Url= new URL(fAddress);
outStream = new BufferedOutputStream(new
FileOutputStream(destinationDir+"\\"+localFileName));

uCon = Url.openConnection();
is = uCon.getInputStream();
buf = new byte[size];
while ((ByteRead = is.read(buf)) != -1) {
outStream.write(buf, 0, ByteRead);
ByteWritten += ByteRead;
}
System.out.println("Downloaded Successfully.");
System.out.println
("File name:\""+localFileName+ "\"\nNo ofbytes :" + ByteWritten);
}
catch (Exception e) {
e.printStackTrace();
}
finally {
try {
is.close();
outStream.close();
}
catch (IOException e) {
e.printStackTrace();
}}}


public static void fileDownload(String fAddress, String destinationDir)
{
 
  int slashIndex =fAddress.lastIndexOf('/');
int periodIndex =fAddress.lastIndexOf('.');

String fileName=fAddress.substring(slashIndex + 1);

if (periodIndex >=1 &&  slashIndex >= 0 
&& slashIndex < fAddress.length()-1)
{
fileUrl(fAddress,fileName,destinationDir);
}
else
{
System.err.println("path or file name.");
}}

  public void upload(String urlString,String source){

    try
    {
        URL url = new URL(urlString);
        URLConnection urlc = url.openConnection();

        bos = new BufferedOutputStream( urlc.getOutputStream() );
        bis = new BufferedInputStream( new FileInputStream( source ) );

        int i;
        // read byte by byte until end of stream
        while ((i = bis.read()) != -1)
        {
            bos.write( i );
        }

  }catch(Exception e)
    {
        e.printStackTrace();
    }

}

/*public static void main(String[] args)
{
	UrlDownload ud=new UrlDownload();
	ud.fileDownload("http://farm8.staticflickr.com/7066/6822654366_334f27281b_m.jpg","");
  //  ud.upload("http://farm8.staticflickr.com/7066/","x.bmp");

}    */
}