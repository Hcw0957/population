package com.population.component;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.population.service.PopulationService;
import com.population.service.TimeSmsService;
import com.population.service.TrafficService;
import com.population.vo.CongestInfoVO;
import com.population.vo.TimeSmsVO;
import com.population.vo.TrafficVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class PopulationInfoComponent {
    @Autowired
    TrafficService service2;
    @Autowired
    TimeSmsService service1;
    @Autowired
    PopulationService service;
    // 매일 10시 30분에 한 번 호출
    @Scheduled(cron="0 0 */1 * * *")
    public void getPopulationinfo() throws Exception {
        Date dt = new Date(); // 현재시간
        SimpleDateFormat dtFormatter = new SimpleDateFormat("YYYYMMdd");
        String today = dtFormatter.format(dt);
        
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/odtraffic/trafficAmountByRealtime"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        // System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("list");
        // System.out.println("데이터 수 : "+nList.getLength());
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String conzoneName = getTagValue("conzoneName", elem);
            String grade = getTagValue("grade", elem);
            String routeName = getTagValue("routeName", elem);
            String routeNo = getTagValue("routeNo", elem);
            String shareRatio = getTagValue("shareRatio", elem);
            String speed = getTagValue("speed", elem);
            String stdDate = getTagValue("stdDate", elem);
            String stdHour = getTagValue("stdHour", elem);
            String timeAvg = getTagValue("timeAvg", elem);
            String trafficAmount = getTagValue("trafficAmout", elem); 
            
            CongestInfoVO vo = new CongestInfoVO();
            Date Dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Dt = formatter.parse(stdDate); // 문자열로부터 날짜를 유추한다.
            vo.setConzoneName(conzoneName);
            vo.setGrade(grade);
            vo.setRouteName(routeName);
            vo.setRouteNo(routeNo);
            vo.setShareRatio(shareRatio);
            vo.setSpeed(speed);
            vo.setStdDate(stdDate);
            vo.setStdHour(stdHour);
            vo.setTimeAvg(timeAvg);
            vo.setTrafficAmount(trafficAmount);

            
            vo.setStdDate(stdDate);
            service.insertCongestInfo(vo);
        }
    }
    
    // 매일 10시 30분에 한 번 호출
    @Scheduled(cron="0 0 */1 * * *")
    public void getCongestInfo() throws Exception {
        Date dt = new Date(); // 현재시간
        SimpleDateFormat dtFormatter = new SimpleDateFormat("YYYYMMdd");
        String today = dtFormatter.format(dt);
        
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/odtraffic/trafficAmountByCongest"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*검색할 생성일 범위의 종료*/
        System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        // System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("list");
        // System.out.println("데이터 수 : "+nList.getLength());
    
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String conzoneName = getTagValue("conzoneName", elem);
            String grade = getTagValue("grade", elem);
            String routeName = getTagValue("routeName", elem);
            String routeNo = getTagValue("routeNo", elem);
            String shareRatio = getTagValue("shareRatio", elem);
            String speed = getTagValue("speed", elem);
            String stdDate = getTagValue("stdDate", elem);
            String stdHour = getTagValue("stdHour", elem);
            String timeAvg = getTagValue("timeAvg", elem);
            String trafficAmount = getTagValue("trafficAmout", elem); 
            
            CongestInfoVO vo = new CongestInfoVO();
            Date Dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Dt = formatter.parse(stdDate); // 문자열로부터 날짜를 유추한다.
            vo.setConzoneName(conzoneName);
            vo.setGrade(grade);
            vo.setRouteName(routeName);
            vo.setRouteNo(routeNo);
            vo.setShareRatio(shareRatio);
            vo.setSpeed(speed);
            vo.setStdDate(stdDate);
            vo.setStdHour(stdHour);
            vo.setTimeAvg(timeAvg);
            vo.setTrafficAmount(trafficAmount);

            Date cdt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            cdt = dtFormat.parse(getTagValue("stdDate", elem));

            vo.setStdDate(stdDate);
            service.insertCongestInfo(vo);
        }
    }
    public static String getTagValue(String tag, Element elem) {
        if(elem.getElementsByTagName(tag).item(0) == null) return null;
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }

        

    @Scheduled(cron="0 0 */1 * * *")
    public void getTimeSmsInfo() throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/burstInfo/realTimeSms"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("pagingYn","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*한 페이지 결과 수*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        // System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("realTimeSMSList");
        // System.out.println("데이터 수 : "+nList.getLength());
        
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String accDate = getTagValue("accDate", elem);
            String accHour = getTagValue("accHour", elem);
            String accType = getTagValue("accType", elem);
            String roadNM = getTagValue("roadNM", elem);
            String smsText = getTagValue("smsText", elem);
            String startEndTypeCode = getTagValue("startEndTypeCode", elem);
            
            TimeSmsVO vo = new TimeSmsVO();
            Date Dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            Dt = formatter.parse(accDate); // 문자열로부터 날짜를 유추한다.
            vo.setAccDate(accDate);
            vo.setAccHour(accHour);
            vo.setAccType(accType);
            vo.setRoadNM(roadNM);
            vo.setSmsText(smsText);
            vo.setStartEndTypeCode(startEndTypeCode);
            

            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy.MM.dd");
            dt = dtFormat.parse(getTagValue("accDate", elem));

            vo.setAccDate(accDate);
            service1.insertTimeSmsInfo(vo);
        }
    }

    @Scheduled(cron="0 0 */1 * * *")
    public void getTrafficInfo() throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/trafficapi/trafficAll"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        // System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("trafficAll");
        // System.out.println("데이터 수 : "+nList.getLength());
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String sumTm = getTagValue("sumTm", elem);
            String trafficAmout = getTagValue("trafficAmout", elem);
            String exDivName = getTagValue("exDivName", elem);
    
            
            TrafficVO vo = new TrafficVO();
            vo.setSumTm(sumTm);
            vo.setTrafficAmout(trafficAmout);
            vo.setExDivName(exDivName);


            service2.insertTrafficInfo(vo);
        }
    }
}
