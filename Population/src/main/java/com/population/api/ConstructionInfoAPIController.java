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

import com.population.service.ConstructionInfoService;
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
public class ConstructionInfoAPIController {
    @Autowired
    ConstructionInfoService service;
    @GetMapping("/api/gest")
    public Map<String, Object> getConstructionInfo(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/safeDriving/hiwayCnstnPrss"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*페이지번호*/
        // System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());
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
            String cmcnCstrClss = getTagValue("cmcnCstrClss", elem);
            String cnstnStpntAddr = getTagValue("cnstnStpntAddr", elem);
            String cnstnEnpntAddr = getTagValue("cnstnEnpntAddr", elem);
            String cnstnTerm = getTagValue("cnstnTerm", elem);
            String cmcnDate = getTagValue("cmcnDate", elem);
            String routeName = getTagValue("routeName", elem);
            String sectionName = getTagValue("sectionName", elem);
        
            
            ConstructionInfoVO vo = new ConstructionInfoVO();
            Date Dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Dt = formatter.parse(cmcnDate); // 문자열로부터 날짜를 유추한다.
            vo.setCmcnCstrClss(cmcnCstrClss);
            vo.setCnstnStpntAddr(cnstnStpntAddr);
            vo.setCnstnEnpntAddr(cnstnEnpntAddr);
            vo.setCnstnTerm(cnstnTerm);
            vo.setCmcnDate(cmcnDate);
            vo.setRouteName(routeName);
            vo.setSectionName(sectionName);
            
            Date dt = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            dt = dtFormat.parse(getTagValue("cmcnDate", elem));

            vo.setCmcnDate(cmcnDate);
            service.insertConstructionInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/construt")
    public Map<String, Object> getConstructionInfo(@RequestParam String sigong) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ConstructionInfoVO> list = service.selectConstructionInfoByDate(sigong);
        System.out.println(list);
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
