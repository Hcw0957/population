package com.population.api;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.population.service.PopulationService;
import com.population.vo.CongestInfoVO;
import com.population.vo.ConstructionInfoVO;
import com.population.vo.PopulationInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



@RestController
public class MainAPIController {
    @Autowired
    PopulationService service;
    @GetMapping("/api/population")
    public Map<String, Object> getPopulation(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/odtraffic/trafficAmountByRealtime"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("list");
        System.out.println("데이터 수 : "+nList.getLength());
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String conzoneName = getTagValue("conzoneName", elem);
            String routeName = getTagValue("routeName", elem);
            String shareRatio = getTagValue("shareRatio", elem);
            String speed = getTagValue("speed", elem);
            String stdDate = getTagValue("stdDate", elem);
            String trafficAmount = getTagValue("trafficAmout", elem);
            
            PopulationInfoVO vo = new PopulationInfoVO();
            Date Dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Dt = formatter.parse(stdDate); // 문자열로부터 날짜를 유추한다.
            vo.setConzoneName(conzoneName) ; // 문자열 타입의 데이터를 정수형으로 변환해서 저장
            vo.setRouteName(routeName);
            vo.setShareRatio(shareRatio);
            vo.setSpeed(speed);
            vo.setStdDate(stdDate);
            vo.setTrafficAmount(trafficAmount);


            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            dt = dtFormat.parse(getTagValue("stdDate", elem));

            vo.setStdDate(stdDate);
            service.insertPopulationInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/road/{date}")
    public Map<String, Object> getPopulationInfo(
        @PathVariable String date
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(date.equals("today")) {
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String strNow = formatter.format(now);
            List<PopulationInfoVO> list = service.selectPopulationByDate(strNow);
            resultMap.put("status", true);
            resultMap.put("data", list);
            return resultMap;
        }
        List<PopulationInfoVO> list = service.selectPopulationByDate(date);
        resultMap.put("status", true);
        resultMap.put("data", list);
        
        return resultMap;
    }

    @GetMapping("/api/con")
    public Map<String, Object> getCongestion(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/odtraffic/trafficAmountByRealtime"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("list");
        System.out.println("데이터 수 : "+nList.getLength());
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
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
            String trafficAmount = getTagValue("trafficAmout", elem); // 오타
            
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


            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            dt = dtFormat.parse(getTagValue("stdDate", elem));

            vo.setStdDate(stdDate);
            service.insertCongestInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/congest")
    public Map<String, Object> getCongest() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<CongestInfoVO> list = service.selectCongestInfoByDate();
        resultMap.put("status", true);
        resultMap.put("data", list);
        
        return resultMap;
    }

    public static String getTagValue(String tag, Element elem) {
        if(elem.getElementsByTagName(tag).item(0)==null) return null;
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }
}
