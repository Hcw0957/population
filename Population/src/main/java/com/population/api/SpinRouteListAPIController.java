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

import com.population.service.SpinRouteListService;
import com.population.service.TimeSmsService;
import com.population.vo.SpinRouteListVO;
import com.population.vo.TimeSmsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
@RestController
public class SpinRouteListAPIController {
    @Autowired
    SpinRouteListService service;
    @GetMapping("/api/spin")
    public Map<String, Object> getSpinRouteListInfo(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/roadEtcInfo/spinRouteList"); /*URL*/
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
            String edpntDstnc = getTagValue("edpntDstnc", elem);
            String routeNm = getTagValue("routeNm", elem);
            String routeNo = getTagValue("routeNo", elem);
            String stptDstnc = getTagValue("stptDstnc", elem);
            String totExtnsDstne = getTagValue("totExtnsDstne", elem);
            String useYn = getTagValue("useYn", elem);
            
            SpinRouteListVO vo = new SpinRouteListVO();
            vo.setEdpntDstnc(edpntDstnc);
            vo.setRouteNm(routeNm);
            vo.setRouteNo(routeNo);
            vo.setStptDstnc(stptDstnc);
            vo.setTotExtnsDstne(totExtnsDstne);
            vo.setUseYn(useYn);

            service.insertSpinRouteList(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/route")
    public Map<String, Object> getSpinRouteListInfo(@RequestParam String list) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        list = "%"+list+"%";
        List<SpinRouteListVO> data = service.selectSpinRouteListbyData(list);
        resultMap.put("status", true);
        resultMap.put("data", data);
        
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

