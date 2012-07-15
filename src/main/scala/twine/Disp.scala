package twine

import twine.URLEncoder;
import xml.Elem
 import java.awt.{TextArea,TextField}
import dispatch._
import collection.immutable.HashMap


object disp{
  val windowSize = 100
  val delay = 1000


  
  def para2URL(para:HashMap[String,String]):String=
  {
    var paraString="?";
     para foreach (x => x match{case(a,b)=>paraString+="&"+a+"="+b})
    paraString
  }

}