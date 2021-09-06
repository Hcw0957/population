package com.population.api;

import org.springframework.web.bind.annotation.RestController;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.population.service.PopulationService;
import com.population.service.TrafficService;
import com.population.vo.TrafficVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class TrafficAPIController {
    @Autowired
    TrafficService service;
    @GetMapping("/api/traffic")
    public Map<String, Object> getPopulation(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
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
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
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


            service.insertTrafficInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/sector")
    public Map<String, Object> getTrafficSectorInfo() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<TrafficVO> list = service.selectTrafficSector();
        resultMap.put("status", true);
        resultMap.put("data", list);
        
        return resultMap;
    }
    @GetMapping("/api/gong")
    public Map<String, Object> getTrafficGongInfo() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<TrafficVO> list = service.selectTrafficGong();
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
