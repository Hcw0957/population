package com.population.api;


import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.population.service.EmergencyService;

import com.population.vo.EmergencyVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
@RestController
public class EmergencyAPIControllre {
    @Autowired
    EmergencyService service;
    @GetMapping("/api/emergency")
    public Map<String, Object> getEmergency(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/ctsrMngmEtctPrcd/emrgRamp"); /*URL*/
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
        NodeList nList = doc.getElementsByTagName("emrgRampLists");
        // System.out.println("데이터 수 : "+nList.getLength());
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String bgwdIc = getTagValue("bgwdIc", elem);
            String drveDrctNm = getTagValue("drveDrctNm", elem);
            String frwdIc = getTagValue("frwdIc", elem);
            String routeNm = getTagValue("routeNm", elem);
            String shift = getTagValue("shift", elem);
            String bypsRoad = getTagValue("bypsRoad", elem);

            EmergencyVO vo = new EmergencyVO();
            vo.setBgwdIc(bgwdIc);
            vo.setBypsRoad(bypsRoad);
            vo.setDrveDrctNm(drveDrctNm);
            vo.setFrwdIc(frwdIc);
            vo.setRouteNm(routeNm);
            vo.setShift(shift);

            service.insertEmergencyinfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/emer")
    public Map<String, Object> getEmergencyInfo(@RequestParam String routeNm) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<EmergencyVO> list = service.selectEmergencyList(routeNm);
        resultMap.put("status", true);
        resultMap.put("data", list);
        
        return resultMap;
    }
    @GetMapping("/api/selectEmer")
    public Map<String, Object> getSelectEmer(@RequestParam String frwdIc){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        frwdIc = "%"+frwdIc+"%";
        List<EmergencyVO> list = service.selectEmer(frwdIc);
        resultMap.put("list", list);
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


