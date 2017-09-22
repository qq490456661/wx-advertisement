package com.onway.web.controller;

import com.onway.web.module.request.PayRequest;
import com.onway.web.module.response.Response;
import com.onway.web.pojo.PayOrder;
import com.onway.web.util.HttpConnection;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxJsPayController {
     /**
      * 应用编号（微信公众号编号）
      */
     private String appId="wxa16425d3780729a5";
     /**
      * 商户号码
      */
     private String mchId="1361615102";
     /**
      * 支付码
      */
     private String payKey="Otft8K1JHqZU3JNs1Bn7l2BKOzLalH9F";
     /**
      * 统一下单URL
      */
     private String unifiedOrderUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";
     @RequestMapping("testPay.do")
     public Response testPay(String openId,PayRequest payResponse) throws Exception {
         Map map=createOrder(openId,"20170921123456",1,"http://jj8uye.natappfree.cc/test.html");
         String paySign=createPaySign(map);

         Response response=new Response(payResponse);
         payResponse.setPrepay_id((String) map.get("package"));
         payResponse.setAppId((String) map.get("appId"));
         payResponse.setNonceStr((String) map.get("nonceStr"));
         payResponse.setPaySign(paySign);
         payResponse.setTimeStamp((String) map.get("timeStamp"));
         response.setData(payResponse);

         return response;
     }

     /**
      * 生成订单
      * @param openId
      * @param orderId
      * @param money
      * @param callbackUrl
      * @return
      * @throws Exception
      */

     public Map createOrder(String openId,String orderId,int money,String callbackUrl)
         throws Exception {
          String timestamp=String.valueOf(System.currentTimeMillis());
          PayOrder payOrder = new PayOrder();
          payOrder.setAppid(appId);
          payOrder.setMch_id(mchId);
          payOrder.setAttach("附加说明测试");
          payOrder.setBody("支付测试");
          String nonce = UUID.randomUUID().toString().replace("-", "").substring(0, 30);
          payOrder.setNonce_str(nonce);
          payOrder.setNotify_url(callbackUrl);
          payOrder.setOpenid(openId);
          payOrder.setOut_trade_no(orderId);
//          payOrder.setSpbill_create_ip("118.178.135.106");
          payOrder.setSpbill_create_ip("183.128.187.49");
          payOrder.setTotal_fee(money);
          payOrder.setTrade_type("JSAPI");

          String sign = createUnifiedOrderSign(payOrder);
          payOrder.setSign(sign);

          System.out.println("appId:"+appId+",nonceStr:"+nonce+",paySign:"+sign+",signType:JSAPI");

          /**
           * 转成XML格式
           */
          xstream.alias("xml",payOrder.getClass());
//          xmlUtil.getXstreamInclueUnderline().alias("xml", unifiedOrder.getClass());
//          String xml = xmlUtil.getXstreamInclueUnderline().toXML(unifiedOrder);
          String xml=xstream.toXML(payOrder);
          HttpConnection httpConnection=new HttpConnection();
          String response = httpConnection.post(unifiedOrderUrl, xml);
          Document document = DocumentHelper.parseText(response);
          Element root = document.getRootElement();
          Element element=root.element("prepay_id");
          String prepay_id=element.getText();
          Map map=new HashMap();
          payOrder.setTimeStamp(timestamp);
          map.put("appId",payOrder.getAppid());
          map.put("timeStamp",payOrder.getTimeStamp());
          map.put("nonceStr",payOrder.getNonce_str());
          map.put("package","prepay_id="+prepay_id);

          return map;
     }
     /**
      * 获取统一下单签名
      * @Title: createUnifiedOrderSign
      * @Description: TODO
      * @param @param unifiedOrder
      * @param @return
      * @return String
      * @throws
      */
     public String createUnifiedOrderSign(PayOrder payOrder){
          StringBuffer sign = new StringBuffer();
          sign.append("appid=").append(payOrder.getAppid());
          sign.append("&attach=").append(payOrder.getAttach());
          sign.append("&body=").append(payOrder.getBody());
          //sign.append("&device_info=").append(payOrder.getDevice_info());
          sign.append("&mch_id=").append(payOrder.getMch_id());
          sign.append("&nonce_str=").append(payOrder.getNonce_str());
          sign.append("&notify_url=").append(payOrder.getNotify_url());
          sign.append("&openid=").append(payOrder.getOpenid());
          sign.append("&out_trade_no=").append(payOrder.getOut_trade_no());
          sign.append("&spbill_create_ip=").append(payOrder.getSpbill_create_ip());
          sign.append("&total_fee=").append(payOrder.getTotal_fee());
          sign.append("&trade_type=").append(payOrder.getTrade_type());
          //sign.append("&timestamp=").append(payOrder.getTimeStamp());
          sign.append("&key=").append(payKey);

          return DigestUtils.md5Hex(sign.toString()).toUpperCase();
     }


     /**
      * 支付签名
      * @Title: createUnifiedOrderSign
      * @Description: TODO
      * @param @param unifiedOrder
      * @param @return
      * @return String
      * @throws
      */
     public String createPaySign(Map map){
          StringBuffer sign = new StringBuffer();
          sign.append("appid=").append(map.get("appId"));
          sign.append("&nonceStr=").append(map.get("nonceStr"));
          sign.append("&package=").append(map.get("package"));
          sign.append("&signType=MD5");
          sign.append("&timeStamp=").append(map.get("timeStamp"));
          sign.append("&key=").append(payKey);
          return DigestUtils.md5Hex(sign.toString()).toUpperCase();
     }
     /**
      *
      */
     private static XStream xstream = new XStream(new XppDriver() {
          public HierarchicalStreamWriter createWriter(Writer out) {
               return new PrettyPrintWriter(out) {
                    // 对所有 xml 节点的转换都增加 CDATA 标记
                    boolean cdata = false;

                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                         super.startNode(name, clazz);
                    }
                    @Override
                    public String encodeNode(String name) {
                         return name;
                    }
                    protected void writeText(QuickWriter writer, String text) {
                         if (cdata) {
                              writer.write("<![CDATA[");
                              writer.write(text);
                              writer.write("]]>");
                         } else {
                              writer.write(text);
                         }
                    }
               };
          }
     });
     /**
      * 解析微信发来的请求（XML）
      *
      * @param request
      * @return
      * @throws Exception
      */
     @SuppressWarnings("unchecked")
     public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
          // 将解析结果存储在HashMap中
          Map<String, String> map = new HashMap<String, String>();

          // 从request中取得输入流
          InputStream inputStream = request.getInputStream();
          // 读取输入流
          SAXReader reader = new SAXReader();
          Document document = reader.read(inputStream);
          // 得到xml根元素
          Element root = document.getRootElement();
          // 得到根元素的所有子节点
          List<Element> elementList = root.elements();

          // 遍历所有子节点
          for (Element e : elementList)
               map.put(e.getName(), e.getText());

          // 释放资源
          inputStream.close();
          inputStream = null;

          return map;
     }
}
