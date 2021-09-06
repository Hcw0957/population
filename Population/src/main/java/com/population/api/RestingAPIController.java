package com.population.api;

import java.util.Map;

import com.population.service.RestingService;
import com.population.vo.RestingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.population.service.PopulationService;
import com.population.service.TrafficService;
import com.population.vo.TrafficVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class RestingAPIController {
    @Autowired
    RestingService service;
    @GetMapping("/api/rest")
    public Map<String, Object> getRestingInfo(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/business/serviceAreaRoute"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
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
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String batchMenu = getTagValue("batchMenu", elem);
            String brand = getTagValue("brand", elem);
            String convenience = getTagValue("convenience", elem);
            String direction = getTagValue("direction", elem);
            String routeName = getTagValue("routeName", elem);
            String serviceAreaName = getTagValue("serviceAreaName", elem);
            String svarAddr = getTagValue("svarAddr", elem);
            String telNo = getTagValue("telNo", elem);
            
            
            RestingVO vo = new RestingVO();
            vo.setBatchMenu(batchMenu);
            vo.setBrand(brand);
            vo.setConvenience(convenience);
            vo.setDirection(direction);
            vo.setRouteName(routeName);
            vo.setServiceAreaName(serviceAreaName);
            vo.setSvarAddr(svarAddr);
            vo.setTelNo(telNo);


            service.insertRestingInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/resting")
    public Map<String, Object> getRestingList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<RestingVO> list = service.selectRestingList();
        resultMap.put("status", true);
        resultMap.put("data", list);
        
        return resultMap;
    }
    @GetMapping("/api/restingsearch")
    public Map<String, Object> getRestingSearch(@RequestParam String region){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        region = region+"%";
        List<RestingVO> list = service.selectRestingPlace(region);
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
