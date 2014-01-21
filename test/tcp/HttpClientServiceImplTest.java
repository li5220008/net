package tcp;
import io.netty.channel.ChannelFuture;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import tcp.HCS;
import tcp.HttpClientServiceImpl;

import java.net.SocketAddress;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-11-28
 * Time: 上午9:19
 */
public class HttpClientServiceImplTest {
    public static HCS hcs;
    @Before
    public void setUp() throws Exception {
        hcs = new HttpClientServiceImpl();
    }
    @Test
    public void testService(){
        try {
            /*//第一步 登陆
            String url0="http://oa.gtadata.com/C6/JHSoft.Web.Login/AjaxForLogin.aspx";
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("loginCode", "d2VpZ2w="));
            nvps.add(new BasicNameValuePair("pwd", "MTIzNDU2"));
            nvps.add(new BasicNameValuePair("type", "login"));
            System.out.println(hcs.url(url0).nvp(nvps).post());

            //第二步 创建流程
            String url1="http://oa.gtadata.com/C6/JHsoft.Web.Workflow/WorkFlowTemplate/InstanceEntry.aspx";
            String html1 = hcs.url(url1).get();
            Document doc2 = Jsoup.parse(html1);
            Element li = doc2.select(".normal").first().child(0);
            String param = li.attr("onclick");
            Pattern p = Pattern.compile("^CreateInstance\\(\"(.+)\",\"(.+)\",\"(.+)\"\\)(.+)$");
            Matcher m = p.matcher(param);
            if(m.find()){
                System.out.println(m.groupCount());
                System.out.println(m.group(1));
                System.out.println(m.group(2));
                System.out.println(m.group(3));
                System.out.println(m.group(4));
            }

            //第三步 生成最终提交的表单
            StringBuilder urll3 = new StringBuilder(m.group(2).replace("JHSoft.Web.Module/fceform/common/djframe.htm","http://oa.gtadata.com/C6/JHSoft.Web.Module/ToolBar/toolbarwf.aspx"))
                    .append("&_FlowTemplateID=").append(m.group(1))
                    .append("&Version=").append(m.group(3))
                    .append("&_FlowInstanceID=0");
            String html3 = hcs.url(urll3.toString()).get();

            //第四步 转下一步
            Document doc3 = Jsoup.parse(html3);
            String urlDJ = "http://oa.gtadata.com/C6/JHSoft.Web.Module/eformaspx/WebBill.aspx?djupdate";//生成单据url
            String djNo = hcs.url(urlDJ).body("<root><add1>JHC</add1><no>SW5zZXJ0IGludG8ga3FqbGdnIChNYWluSUQsZGVwdCx4bSxycSxkcixkcnJxLGdnc2Jzancsc2Jzaix5eTEseXkyLGdneGJzancseGJzaixjYyxjY3NqLHosZCxiaCkgVmFsdWVzICgnOmdldF9rZXlmaWVsZCcsJ+acuuaehOW6lOeUqOaKgOacr+mDqCcsJ+mtj+i0teekvCcsJzIwMTMtMTItMDMnLCfmmK8nLCcyMDEzLTEyLTI1Jywn5pivJywnJywn5b+Y6K6w5omT5Y2hMTEwJywnJywnJywnJywnJywnJywnJywnJywnMTA2NTInKQ==</no></root>").post();
            System.out.println("单据号："+djNo);
            String param4 = doc3.select("#btn6").first().attr("onclick");
            Pattern p4 = Pattern.compile("^.*funDialogNext\\('(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)','(.*)'\\).*$");
            Matcher m4 = p4.matcher(param4);
            if(m4.find()){
                System.out.println(m4.group(1));
                System.out.println(m4.group(2));
                System.out.println(m4.group(3));
                System.out.println(m4.group(4));
                System.out.println(m4.group(5));
                System.out.println(m4.group(6));
                System.out.println(m4.group(7));
                System.out.println(m4.group(8));
                System.out.println(m4.group(9));
                System.out.println(m4.group(10));
                System.out.println(m4.group(11));
                System.out.println(m4.group(12));
            }
            StringBuilder urll4 = new StringBuilder("http://oa.gtadata.com/C6/JHSoft.Web.WorkFlow/WorkFlow/NextStepSelectNew.aspx")
                    .append("?httAppTID=").append(m4.group(1))
                    .append("&httAppDID=").append(m4.group(2))
                    .append("&httCurrProcessName=").append(m4.group(3))
                    .append("&httCurrBtnText=").append(m4.group(4))
                    .append("&httAppID=").append(m4.group(6))
                    .append("&httDaType=").append(m4.group(7))
                    .append("&httCurUserID=").append(m4.group(8)*//*.append("10652")这里可以改变提交的人，比如我的员工号 10652*//*)
                    .append("&GroupCode=").append(m4.group(9))
                    .append("&Condition=").append(doc2.select("#app_hiddenFlowCondition").val())
                    .append("&_FlowInstanceID=").append(doc2.select("#hid_FlowInstanceTitle").val())
                    .append("&_FlowVersion=").append(m4.group(11))
                    .append("&httOperationType=").append(m4.group(5));
            String html4 = hcs.url(urll4.toString()).get();

            //第五步 提交表单
            Document doc4 = Jsoup.parse(html4);
            String[] receiveObjs = doc4.select("#InitReceiveObjs").first().child(0).val().split(",");

            doc3.select("#hidReceiveMan").val("<receiveMan><recobj><rorder>0</rorder><recid><![CDATA["+receiveObjs[1]+"]]></recid><deptid><![CDATA["+receiveObjs[0]+"]]></deptid></recobj></receiveMan>");//设置部门 和接收人
            doc3.select("#hidNextStep").val("3540");
            doc3.select("#hidPromptContent").val("考勤记录更改申请");
            doc3.select("#hidSms").val("N");
            doc3.select("#hidMail").val("1");
            doc3.select("#hidDel").val("0");
            doc3.select("#hidTransactMode").val("2");
            doc3.select("#hidDAType").val("6");
            doc3.select("#hidCall").val("1");
            doc3.select("#hidCurVersion").val("2.0");
            //doc3.select("#hid_value").val(djNo); //单据号，如何利用输入的考勤信息，生成这个单据号还没有破解。
            doc3.select("#hid_value").val("JHC00032046"); //写死了
            doc3.select("#hidTableName").val("kqjlgg");
            doc3.select("#hidAppID").val("-1");
            doc3.select("#hidTempAttributes").val("<root></root>");
            doc3.select("#hidFormName").val("考勤");
            doc3.select("#hidFormSN").val("kqjlggsq_"+doc3.select("#hidAppTID").val());

            FormElement form3 = (FormElement)doc3.select("#Form1").first();//强制转换成一个form
            Elements elements = form3.elements();
            List<Connection.KeyVal> lists = form3.formData();
            List<NameValuePair> nvps5 = new ArrayList<NameValuePair>(); //创建提交的键值参数
            for(Connection.KeyVal list : lists){
                nvps5.add(new BasicNameValuePair(list.key(),list.value()));
                System.out.println(list.key()+":"+list.value());
            }
            nvps5.add(new BasicNameValuePair("__EVENTTARGET","ToolBar1"));
            nvps5.add(new BasicNameValuePair("ToolBar1","6"));
            nvps5.add(new BasicNameValuePair("_FlowInstanceTitle","考勤记录更改"));
            //String html5 = hcs.url(url3.toString()).nvp(nvps5).post(); //这里提交的url第三步一样，只是提交的方法要用post方法。千万别随便提交，不然就搞大了。

            //第六步 退出系统
            hcs.url("http://oa.gtadata.com/C6/jhsoft.web.workflat/UserDispose.aspx?userid="+m4.group(8)).get();*/

            /*String url="http://211.66.64.35/default3.aspx";
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("__VIEWSTATE", "dDwtNjg3Njk1NzQ3O3Q8O2w8aTwxPjs+O2w8dDw7bDxpPDg+O2k8MTM+O2k8MTU+Oz47bDx0PHA8O3A8bDxvbmNsaWNrOz47bDx3aW5kb3cuY2xvc2UoKVw7Oz4+Pjs7Pjt0PHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Ozs+O3Q8O2w8aTwwPjs+O2w8dDw7bDxpPDE+Oz47bDx0PHA8bDxpbm5lcmh0bWw7PjtsPFw8dGFibGUgd2lkdGg9JzEwMCUnIGJvcmRlcj0nMCcgY2VsbHNwYWNpbmc9JzAnIGNlbGxwYWRkaW5nPScwJ1w+XDx0clw+XDx0ZCBhbGlnbj0nY2VudGVyJ1w+XDxhIHRhcmdldD1fYmxhbmsgIGhyZWY9J2p3Z2djay5hc3B4P2Zic2o9MjAxMC0wMy0wOCsxNSUzYTExJTNhMDImZ2didD0yMDA5JWM0JWVhMTIlZDQlYzIlYjclZGQlZDMlYTIlZDMlZWZCJWJjJWI2JWIzJWM5JWJjJWE4JWIxJWVkJkNvZGVGbGFnPSVjZCVhOCVkNiVhYSVjZSVjNCViYyVmZSdcPjIwMTAtMy04IDIwMDnlubQxMuaciOS7veiLseivrULnuqfmiJDnu6nooago6YCa55+l5paH5Lu2KVw8L2FcPlw8L3RkXD5cPC90clw+XDx0clw+XDx0ZCBhbGlnbj0nY2VudGVyJ1w+XDxhIHRhcmdldD1fYmxhbmsgIGhyZWY9J2p3Z2djay5hc3B4P2Zic2o9MjAxMC0wNS0xMCsxMSUzYTAxJTNhNTYmZ2didD0yMDA5LTIwMTAlZDElYTclYzQlZWElYjUlZGEyJWQxJWE3JWM2JWRhJWNjJWU1JWQzJWZkJWIyJWI5JWJmJWJjJWNkJWE4JWQ2JWFhJkNvZGVGbGFnPSVjZCVhOCVkNiVhYSVjZSVjNCViYyVmZSdcPjIwMTAtNS0xMCAyMDA5LTIwMTDlrablubTnrKwy5a2m5pyf5L2T6IKy6KGl6ICD6YCa55+lKOmAmuefpeaWh+S7tilcPC9hXD5cPC90ZFw+XDwvdHJcPlw8dHJcPlw8dGQgYWxpZ249J2NlbnRlcidcPlw8YSB0YXJnZXQ9X2JsYW5rICBocmVmPSdqd2dnY2suYXNweD9mYnNqPTIwMTAtMDgtMzArMTQlM2ExNiUzYTU1JmdnYnQ9JWJiJWM2JWM2JWQyJWQwJWEzJWM3JWY4MTAtMTElZDElYTclYzQlZWElYjUlZGElZDIlYmIlZDElYTclYzYlZGElYjIlYjklYmYlYmMlYmYlY2UlYjMlY2MlY2ElYjElYmMlZTQlYjAlYjIlYzUlYzUlYjElZWQmQ29kZUZsYWc9JWNkJWE4JWQ2JWFhJWNlJWM0JWJjJWZlJ1w+MjAxMC04LTMwIOm7hOWflOagoeWMujEwLTEx5a2m5bm056ys5LiA5a2m5pyf6KGl6ICD6K++56iL5pe26Ze05a6J5o6S6KGoKOmAmuefpeaWh+S7tilcPC9hXD5cPC90ZFw+XDwvdHJcPlw8dHJcPlw8dGQgYWxpZ249J2NlbnRlcidcPlw8YSB0YXJnZXQ9X2JsYW5rICBocmVmPSdqd2dnY2suYXNweD9mYnNqPTIwMTAtMDktMDIrMjMlM2EyNSUzYTU1JmdnYnQ9JWIyJWI5JWJmJWJjJWNhJWIxJWJjJWU0JWI1JWY3JWQ1JWZiJWJjJWIwJWJhJWJkJWJhJWEzJWExJWEyJWMyJWQ2JWJiJWZhJWQ3JWE4JWQyJWI1MDglYmMlYjYlYjElY2YlZDIlYjUlYzclYjAlYjIlYjklYmYlYmMlYjAlYjIlYzUlYzUmQ29kZUZsYWc9JWNkJWE4JWQ2JWFhJWNlJWM0JWJjJWZlJ1w+MjAxMC05LTIg6KGl6ICD5pe26Ze06LCD5pW05Y+K6Iiq5rW344CB6L2u5py65LiT5LiaMDjnuqfmr5XkuJrliY3ooaXogIPlronmjpIo6YCa55+l5paH5Lu2KVw8L2FcPlw8L3RkXD5cPC90clw+XDx0clw+XDx0ZCBhbGlnbj0nY2VudGVyJ1w+XDxhIHRhcmdldD1fYmxhbmsgIGhyZWY9J2p3Z2djay5hc3B4P2Zic2o9MjAxMC0xMi0wMisxNiUzYTM0JTNhNDYmZ2didD1JU085MDAwJWQ2JWNhJWMxJWJmJWI5JWRjJWMwJWVkJWNjJWU1JWNmJWI1JWM0JWRhJWM5JWYzJWQ0JWIxJWM1JWUwJWQxJWI1JWIwJWUwJWJmJWFhJWJmJWNlJWNkJWE4JWQ2JWFhJkNvZGVGbGFnPSVjZCVhOCVkNiVhYSVjZSVjNCViYyVmZSdcPjIwMTAtMTItMiBJU085MDAw6LSo6YeP566h55CG5L2T57O75YaF5a6h5ZGY5Z+56K6t54+t5byA6K++6YCa55+lKOmAmuefpeaWh+S7tilcPC9hXD5cPC90ZFw+XDwvdHJcPlw8dHJcPlw8dGQgYWxpZ249J2NlbnRlcidcPlw8YSB0YXJnZXQ9X2JsYW5rICBocmVmPSdqd2dnY2suYXNweD9mYnNqPTIwMTAtMTItMzArMjMlM2E1MSUzYTM2JmdnYnQ9JWI1JWRhMTklZDYlZGMlYmYlYmMlY2ElZDQlYzglZDUlYjMlY2MlYjElZWQmQ29kZUZsYWc9JWNkJWE4JWQ2JWFhJWNlJWM0JWJjJWZlJ1w+MjAxMC0xMi0zMCDnrKwxOeWRqOiAg+ivleaXpeeoi+ihqCjpgJrnn6Xmlofku7YpXDwvYVw+XDwvdGRcPlw8L3RyXD5cPHRyXD5cPHRkIGFsaWduPSdjZW50ZXInXD5cPGEgdGFyZ2V0PV9ibGFuayAgaHJlZj0nandnZ2NrLmFzcHg/ZmJzaj0yMDExLTAxLTEwKzE1JTNhNDQlM2E0NiZnZ2J0PSViOSVkOCVkMyVkYTIwMTAtMjAxMSVkMSVhNyVjNCVlYSViNSVkYTElZDElYTclYzYlZGElYjMlYzklYmMlYTglYjIlZTklZDElYWYlYTElYTIlYjIlYjklYmYlYmMlYjAlYjIlYzUlYzUlYmMlYjAlY2YlYzIlZDElYTclYzYlZGElZDYlZDglZDAlZGUlYjAlYjIlYzUlYzUlYjUlYzQlY2QlYTglZDYlYWEmQ29kZUZsYWc9JWNkJWE4JWQ2JWFhJWNlJWM0JWJjJWZlJ1w+MjAxMS0xLTEwIOWFs+S6jjIwMTAtMjAxMeWtpuW5tOesrDHlrabmnJ/miJDnu6nmn6Xor6LjgIHooaXogIPlronmjpLlj4rkuIvlrabmnJ/ph43kv67lronmjpLnmoTpgJrnn6Uo6YCa55+l5paH5Lu2KVw8L2FcPlw8L3RkXD5cPC90clw+XDx0clw+XDx0ZCBhbGlnbj0nY2VudGVyJ1w+XDxhIHRhcmdldD1fYmxhbmsgIGhyZWY9J2p3Z2djay5hc3B4P2Zic2o9MjAxMi0wNS0xOCsxNyUzYTEyJTNhMzYmZ2didD0lYjklZDglZDMlZGElYjAlZWMlYzAlZWRDQ1QlZDYlYTQlY2ElZTklYjUlYzQlY2QlYTglZDYlYWEmQ29kZUZsYWc9JWNkJWE4JWQ2JWFhJWNlJWM0JWJjJWZlJ1w+MjAxMi01LTE4IOWFs+S6juWKnueQhkNDVOivgeS5pueahOmAmuefpSjpgJrnn6Xmlofku7YpXDwvYVw+XDwvdGRcPlw8L3RyXD5cPHRyXD5cPHRkIGFsaWduPSdjZW50ZXInXD5cPGEgdGFyZ2V0PV9ibGFuayAgaHJlZj0nandnZ2NrLmFzcHg/ZmJzaj0yMDEzLTA3LTEyKzA4JTNhMzElM2EyNyZnZ2J0PTIwMTItMjAxMyVkMSVhNyVjNCVlYSViNSVkYSViNiVmZSVkMSVhNyVjNiVkYSViZiVjZSViMyVjYyViMiViOSViZiViYyViMCViMiVjNSVjNSViNSVjNCVjZCVhOCVkNiVhYSZDb2RlRmxhZz0lY2QlYTglZDYlYWElY2UlYzQlYmMlZmUnXD4yMDEzLTctMTIgMjAxMi0yMDEz5a2m5bm056ys5LqM5a2m5pyf6K++56iL6KGl6ICD5a6J5o6S55qE6YCa55+lKOmAmuefpeaWh+S7tilcPC9hXD5cPC90ZFw+XDwvdHJcPlw8dHJcPlw8dGQgYWxpZ249J2NlbnRlcidcPlw8YSB0YXJnZXQ9X2JsYW5rICBocmVmPSdqd2dnY2suYXNweD9mYnNqPTIwMTMtMDktMDkrMTElM2E0NyUzYTEwJmdnYnQ9MjAxMyVjNCVlYSVjZiVjMiViMCVlYiVjNCVlYSVkMyVhMiVkMyVlZiVjYiVjNCVhMSVhMiVjMSVmOSViYyViNiViYyViMCVkMyVhMiVkMyVlZiVkMyVhNiVkMyVjMyVjNCVkYyVjMSVhNkIlYmMlYjYlYmYlYmMlY2ElZDQlYjElYTglYzMlZmIlY2QlYTglZDYlYWEmQ29kZUZsYWc9JWNkJWE4JWQ2JWFhJWNlJWM0JWJjJWZlJ1w+MjAxMy05LTkgMjAxM+W5tOS4i+WNiuW5tOiLseivreWbm+OAgeWFree6p+WPiuiLseivreW6lOeUqOiDveWKm0LnuqfogIPor5XmiqXlkI3pgJrnn6Uo6YCa55+l5paH5Lu2KVw8L2FcPlw8L3RkXD5cPC90clw+XDwvdGFibGVcPlw8c2NyaXB0IHR5cGU9J3RleHQvamF2YXNjcmlwdCdcPnZhciBvTWFycXVlZSA9IGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdtcScpXDt2YXIgaUxpbmVIZWlnaHQgPSAxNlw7dmFyIGlMaW5lQ291bnQgPSAxMFw7dmFyIGlTY3JvbGxBbW91bnQgPSAxXDsgZnVuY3Rpb24gcnVuKCl7b01hcnF1ZWUuc2Nyb2xsVG9wICs9IGlTY3JvbGxBbW91bnRcO2lmICggb01hcnF1ZWUuc2Nyb2xsVG9wID09IGlMaW5lQ291bnQgKiBpTGluZUhlaWdodCApe29NYXJxdWVlLnNjcm9sbFRvcCA9IDBcO31pZiAoIG9NYXJxdWVlLnNjcm9sbFRvcCAlIGlMaW5lSGVpZ2h0ID09IDAgKSB7d2luZG93LnNldFRpbWVvdXQoICdydW4oKScsIDIwMDAgKVw7fSBlbHNlIHt3aW5kb3cuc2V0VGltZW91dCggJ3J1bigpJywgNTAgKVw7fX1vTWFycXVlZS5pbm5lckhUTUwgKz0gb01hcnF1ZWUuaW5uZXJIVE1MXDt3aW5kb3cuc2V0VGltZW91dCggJ3J1bigpJywgMjAwMCApXDtcPC9zY3JpcHRcPjs+Pjs7Pjs+Pjs+Pjs+Pjs+PjtsPGltZ0RMO2ltZ1RDO2ltZ1FNTTs+PgQ3BaEIyYe7a70jT6lKPaU8jBdA"));
            nvps.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", "94A5C162"));
            nvps.add(new BasicNameValuePair("tbYHM", "201315090214"));
            nvps.add(new BasicNameValuePair("tbPSW", "chenguoshun"));
            nvps.add(new BasicNameValuePair("ddlSF", "学生"));
            nvps.add(new BasicNameValuePair("imgDL.x", "23"));
            nvps.add(new BasicNameValuePair("imgDL.y", "12"));
            System.out.println(hcs.url(url).nvp(nvps).post());

            String url1 = "http://211.66.64.35/xstop.aspx";
            System.out.println(hcs.url(url1).get());*/

            /*LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setRedirectStrategy(redirectStrategy)
                    .build();*/

            /*CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpClientContext context = HttpClientContext.create();
            HttpGet httpget = new HttpGet("http://localhost:9000/");
            CloseableHttpResponse response = httpclient.execute(httpget, context);
            try {
                HttpHost target = context.getTargetHost();
                List<URI> redirectLocations = context.getRedirectLocations();
                URI location = URIUtils.resolve(httpget.getURI(), target, redirectLocations);
                System.out.println("Final HTTP location: " + location.toASCIIString());
                // Expected to be an absolute URI
            } finally {
                response.close();
            }*/

            /*String googleUrl="https://www.google.com.hk/webhp?hl=zh-CN";
            /*String googleUrl="https://www.google.com.hk/webhp?hl=zh-CN";
            String html0 =hcs.url(googleUrl).get();
            System.out.println(html0);*/

            /*BlockingQueue queue = new ArrayBlockingQueue(1024);
            queue.put("test");
            System.out.println(queue.take());*/

            /*BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

            deque.addFirst("1");
            deque.addLast("2");

            String two = deque.takeLast();
            String one = deque.takeFirst();
            System.out.println(String.format("firse:%s last:%s",one,two));*/

            /*LoggingInfo logInfo = new LoggingInfo("MIKE", "MECHANICS");
            System.out.println(logInfo.toString());

            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("logInfo.text"));
            o.writeObject(logInfo);
            o.close();*/

            /*ObjectInputStream in =new ObjectInputStream(new FileInputStream("logInfo.text"));
            LoggingInfo logInfo = (LoggingInfo)in.readObject();
            System.out.println(logInfo.toString());*/

            /*ConcurrentNavigableMap map = new ConcurrentSkipListMap();

            map.put("1", "one");
            map.put("b", "two");
            map.put("a", "four");
            map.put("3", "three");
            map.put("5", "five");

            ConcurrentNavigableMap headMap = map.headMap("a");
            NavigableSet ns = map.descendingKeySet();
            System.out.println(headMap);
            System.out.println(testFinal(new A("k","t")));*/




        } catch (Exception e) {
            e.printStackTrace();
        } finally {//保证释放链接
            hcs.close();
        }
    }

    private String testFinal(final A a) {
//        a = new A("c","d");
        /*a.a = "a";
        a.b = "b";*/
        return a.toString();

    }

    class A {
        String a;
        String b;

        A(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }
    }
}
