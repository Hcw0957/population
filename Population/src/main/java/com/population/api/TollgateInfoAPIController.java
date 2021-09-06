package com.population.api;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.population.service.TollgateInfoService;
import com.population.vo.TollgateInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@RestController
public class TollgateInfoAPIController {
    @Autowired
    TollgateInfoService service;
    @GetMapping("/api/toll/list")
    public Map<String, Object> getTollgateInfo() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/locationinfo/locationinfoIc"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=8078659211"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
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
            String icName = getTagValue("icName", elem);
            String routeName = getTagValue("routeName", elem);
            String routeNo = getTagValue("routeNo", elem);
            String xValue = getTagValue("xValue", elem);
            String yValue = getTagValue("yValue", elem);

        
        
            TollgateInfoVO vo = new TollgateInfoVO();
            vo.setIcName(icName);
            vo.setRouteName(routeName);
            vo.setRouteNo(routeNo);
            vo.setXValue(xValue);
            vo.setYValue(yValue);
            
            service.insertTollgateInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
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
    
    @GetMapping("/api/toll")
    public Map<String, Object> getrouteNameAll(String routeName, String icName){
        Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
            List<TollgateInfoVO> list =  service.selectRouteAndIC(routeName, icName);
            resulMap.put("list", list);
            return resulMap;
    }
    @GetMapping("/api/icnames")
    public Map<String, Object> getIcNames(@RequestParam @Nullable String route) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(route == null || route.equals("") || route.equals("undefined")) {
            route = "all";
        }
        List<TollgateInfoVO> list = service.selectIcNames(route);
        resultMap.put("list", list);
        return resultMap;
    }
    @GetMapping("/api/routenames")
    public List<TollgateInfoVO> getRouteNames() {
        return service.selectRouteNames();
    }
}
