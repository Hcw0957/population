package com.population.api;

import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.population.service.SmsHistoryService;
import com.population.vo.SmsHistoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class SmsHistoryAPIController {
    @Autowired
    SmsHistoryService service;
    @GetMapping("/api/history")
    public Map<String, Object> getSmsHistoryInfo(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/burstInfo/smsHistory"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        // System.out.println(doc.getDocumentElement().getNodeName());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("SMSHistoryLists");
        // System.out.println("데이터 수 : "+nList.getLength());
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;
            String burstStateNm = getTagValue("burstStateNm", elem);
            String burstTypeCd = getTagValue("burstTypeCd", elem);
            String content = getTagValue("content", elem);
            String route = getTagValue("route", elem);
            String section = getTagValue("section", elem);
            String upDownType = getTagValue("upDownType", elem);
            String oDate = getTagValue("oDate", elem);
            String oTime = getTagValue("oTime", elem);
            
            SmsHistoryVO vo = new SmsHistoryVO();
            vo.setBurstStateNm(burstStateNm);
            vo.setBurstTypeCd(burstTypeCd);
            vo.setContent(content);
            vo.setRoute(route);
            vo.setSection(section);
            vo.setUpDownType(upDownType);
            vo.setODate(oDate);
            vo.setOTime(oTime);
            
            service.insertSmsHistoryInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    // @GetMapping("/api/story")
    // public Map<String, Object> getHistoryInfo() {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     List<SmsHistoryVO> list = service.selectHistoryByList();
    //     resultMap.put("status", true);
    //     resultMap.put("data", list);
        
    //     return resultMap;
    // }

    public static String getTagValue(String tag, Element elem) {
        if(elem.getElementsByTagName(tag).item(0)==null) return null;
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }
}
