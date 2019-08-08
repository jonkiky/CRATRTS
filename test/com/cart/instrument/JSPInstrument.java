package com.cart.instrument;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JSPInstrument {
	
	
	public void instrument(String path, String fname) throws IOException{
		File input = new File(path + fname+".jsp");
		Document doc = Jsoup.parse(input, "UTF-8", "https://itm.arcc.albany.edu/ITM3/");

		Elements scriptElements = doc.getElementsByTag("script");
		InstumentExecutor executor  = new InstumentExecutor();
		String scriptStr = "";
		String scriptForDom="";
		for (Element element : scriptElements) {
			for (DataNode node : element.dataNodes()) {
				scriptStr  = node.getWholeData().toString();
				scriptForDom = "function sendCurrentDom(){\r\n" + 
						"	var domInfo = \r\n" + 
						"		document.getElementsByTagName(\"body\")[0].innerHTML;\r\n" + 
						"	 send(new Array(, "+fname+"jsp"+"':::ENTER', new Array(addVariable('domInfo', domInfo))));\r\n" + 
						"\r\n" + 
						"};sendCurrentDom();";
				element.replaceWith(new DataNode("<script> "+scriptForDom+executor.instrumentedCode(scriptStr, fname+"jsp")+"</script>"));
			}
		}
		
		String OutputFile = path.replace("WebContent", "WebContentInstrument")+ fname+".jsp";
		File file = new File(OutputFile);
		file.getParentFile().mkdirs();
		FileWriter fileWriter = new FileWriter(OutputFile);
	    fileWriter.write(doc.toString());
	    fileWriter.close();
	}

	public static void main(String arg[]) throws IOException {

		
		List<JspFile> jspFiles = new ArrayList<JspFile>();
		
		String path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/project";
		
		jspFiles.add(new JspFile(path,"/openedit"));
		jspFiles.add(new JspFile(path,"/request_to_join"));
		
		path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/project/ourspace";
		
		jspFiles.add(new JspFile(path,"/activity_rader"));
		jspFiles.add(new JspFile(path,"/dashboard"));
		
	path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/project/teacherspace";
		
	jspFiles.add(new JspFile(path,"/crossproject"));
	jspFiles.add(new JspFile(path,"/members"));
	jspFiles.add(new JspFile(path,"/modify"));
	jspFiles.add(new JspFile(path,"/planning"));
	jspFiles.add(new JspFile(path,"/proposal"));
	jspFiles.add(new JspFile(path,"/setup"));
		
	
	path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/thread";
	
	jspFiles.add(new JspFile(path,"/buildontofrom"));
	jspFiles.add(new JspFile(path,"/checkhighlight"));
	jspFiles.add(new JspFile(path,"/create"));
	jspFiles.add(new JspFile(path,"/cross_project_jot"));
	jspFiles.add(new JspFile(path,"/cross_project_sharing"));
	jspFiles.add(new JspFile(path,"/jot"));
	jspFiles.add(new JspFile(path,"/mapping"));
	jspFiles.add(new JspFile(path,"/notereadadd"));
	jspFiles.add(new JspFile(path,"/showauthor"));
	jspFiles.add(new JspFile(path,"/showauthortitle"));
	jspFiles.add(new JspFile(path,"/showbuildon_supertalk"));
	jspFiles.add(new JspFile(path,"/showbuildon"));
	jspFiles.add(new JspFile(path,"/showhighlight"));
	jspFiles.add(new JspFile(path,"/showtitle"));
	jspFiles.add(new JspFile(path,"/super_talk_info_modal"));
	jspFiles.add(new JspFile(path,"/supertalk"));
	jspFiles.add(new JspFile(path,"/supertalk3"));
	jspFiles.add(new JspFile(path,"/thread_import"));
	jspFiles.add(new JspFile(path,"/thread_info_modal"));
	jspFiles.add(new JspFile(path,"/thread"));
	jspFiles.add(new JspFile(path,"/viewlist"));
	jspFiles.add(new JspFile(path,"/wordcloud"));
	
	
		
		JSPInstrument ins =  new JSPInstrument();
		for (int i = 0; i<jspFiles.size();i++) {
			System.out.println(jspFiles.get(i).path+jspFiles.get(i).fName);
			ins.instrument(jspFiles.get(i).path, jspFiles.get(i).fName);
			System.out.println("done");
		}
	    
	}

}

class JspFile {
	String path;
	String fName;
	JspFile(String path, String fName){
		this.path = path;
		this.fName = fName;
	}
}