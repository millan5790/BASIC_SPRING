package com.STL.stl_admin.execution;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletSupport {

    @SuppressWarnings("unchecked")
	public static Map<String, Object> getParam(HttpServletRequest request) throws UnsupportedEncodingException {
    	
    	Map<String, Object>	map		= new HashMap<String, Object>();
    	Enumeration<String>	param 	= request.getParameterNames();
    	int i = 0;
    	while(param.hasMoreElements()) {
    		String key	= param.nextElement();
    			
//    		System.out.println("key : " + key);
    			
    		if(request.getParameterValues(key).length > 1){
    			String	kvVal = new String(request.getParameterValues(key)[i].getBytes("iso-8859-1"), "utf-8");
    			String	filterVal = applyFilter(kvVal);
    			
//    			System.out.println(String.format("1)key='%s' kvVal='%s' filterVal='%s'", key, kvVal, filterVal));
    			
    			map.put(key, filterVal);
    		}else{
    			String param_key = request.getParameter(key);
    			if ( null != param_key) {
    				String	kvVal = getStr(new String(param_key.getBytes("iso-8859-1"), "utf-8"), "");
        			String	filterVal = applyFilter(kvVal);
        			
//    				System.out.println(String.format("2)key='%s' kvVal='%s' filterVal='%s'", key, kvVal, filterVal));
    				
    				map.put(key, filterVal);
    			}
    		}
    		i++;
    	}
    	return map;
    }
    
    public static String getStr(Object obj, String ifNulltoReplace) {
    	if(obj != null && !obj.toString().trim().equals(""))
    		return obj.toString().trim();
    	else
    		return ifNulltoReplace;
    }
    
    
    public static String getServletPath(HttpServletRequest request) {
    	return request.getSession().getServletContext().getRealPath("/");
    }
    
	
	public static void script(HttpServletResponse response, String script) {
		htmlView(response, "<script type='text/javascript'>" + script + "</script>");
	}
	
	public static void htmlView(HttpServletResponse response, String body) {
		if (response == null ) {
			return;
		}
		response.setContentType("text/html; charset=UTF-8");
		responseWrite(response, body);
	}
	
	public static void responseWrite(HttpServletResponse response, String body) {
		try {
			response.getWriter().println(body);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

    public static String applyFilter(String contents) {
        try {
            @SuppressWarnings("unused")
			int pos1 = -1;
            if ((pos1 = contents.indexOf("<")) > -1) {
                // on* Attributes
                contents = Pattern.compile("(<[^>]*) on([A-Z]+)[\t\n\r ]*=([^>]*)>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("$1 Xon$2=$3>");
                // script
                contents = Pattern.compile("<SCRIPT[^>]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;script&gt;");
                contents = Pattern.compile("</[\t\n\r ]*SCRIPT[\t\n\r ]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;/script&gt;");
                // *script protocols
                contents = Pattern.compile("<[^>]+((j|&#(106|x6A)[;]*)[\t\n\r]*(a|&#(97|x61)[;]*)[\t\n\r ]*(v|&#(118|x76)[;]*)[\t\n\r]*(a|&#(97|x61)[;]*)|v[\t\n\r]*b)[\t\n\r]*(s|&#(115|x73)[;]*)[\t\n\r]*(c|&#(99|x63)[;]*)[\t\n\r]*(r|&#(114|x72)[;]*)[\t\n\r]*(i|&#(105|x69)[;]*)[\t\n\r]*(p|&#(112|x70)[;]*)[\t\n\r]*(t|&#(116|x74)[;]*)[\t\n\r]*(:|&#(58|x3A)[;]*)[^>]+>",
                                           Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("<no-script>");
                // iframe
                contents = Pattern.compile("<IFRAME[^>]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;iframe&gt;");
                // object
                contents = Pattern.compile("<[\t\n\r ]*OBJECT[^>]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;object&gt;");
                // form
                contents = Pattern.compile("<[\t\n\r ]*FORM[^>]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;form&gt;");
                // base
                contents = Pattern.compile("<[\t\n\r ]*BASE[^>]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;base&gt;");

                // style
                contents = Pattern.compile("<STYLE[^>]*>", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;STYLE&gt;");
                               
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return contents;

    }
    
    public static String replaceFilter(String contents) {
        try {
            @SuppressWarnings("unused")
			int pos1 = -1;
            if ((pos1 = contents.indexOf("<")) > -1) {                
                
                // < 싹다 막아버려
                contents = Pattern.compile("<", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&lt;");
                
                // >  
                contents = Pattern.compile(">", Pattern.CASE_INSENSITIVE)
                        .matcher(contents)
                        .replaceAll("&gt;");
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return contents;

    }
}