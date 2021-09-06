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

import com.population.service.TimeSmsService;
import com.population.vo.TimeSmsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@RestController
public class TimeSmsAPIController {
    @Autowired
    TimeSmsService service;
    @GetMapping("/api/time")
    public Map<String, Object> getTimeSmsInfo(
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
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
        if(nList.getLength() <= 0){
            resultMap.put("status", false);
            resultMap.put("message", "데이터가 없습니다.");
            return resultMap;
        }
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
            service.insertTimeSmsInfo(vo);
        }
        resultMap.put("status", true);
        resultMap.put("message", "데이터가 입력되었습니다.");
        return resultMap;
        
    }
    @GetMapping("/api/sms")
    public Map<String, Object> getTiemSmsInfo(@RequestParam String start) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        start = "%"+start+"%";
        List<TimeSmsVO> data = service.selectTimeSmsInfoByDate(start);
        resultMap.put("status", true);
        resultMap.put("data", data);
        
        return resultMap;
    }
    @GetMapping("/api/smslist")
    public Map<String, Object> getTiemSmsListInfoList(@RequestParam String roadNM) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(roadNM == null || roadNM.equals("") || roadNM.equals("undefined")){
            roadNM = "all";
        }
        List<TimeSmsVO> list = service.selectTimeSmsInfoList(roadNM);
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
