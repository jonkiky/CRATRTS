package com.cart.instrument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;


public class JSInstrument {
	
	public void instrument(String path, String fName) throws IOException{

		String simpleSource  ="";
		try(BufferedReader br = new BufferedReader(new FileReader(path+fName+".js"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		     simpleSource = sb.toString();
		}


		AstInstrumenter instrumenter = new AstInstrumenter();
		instrumenter.setScopeName(fName+"js");
		CompilerEnvirons compilerEnvirons = new CompilerEnvirons();
		Parser p = new Parser(compilerEnvirons, null);
		AstNode node = p.parse(simpleSource, null, 0); 
		node.visit(instrumenter);
		
		instrumenter.finish((AstRoot)node);
//		System.out.print(node.toSource());
		String OutputFile = path.replace("WebContent", "WebContentInstrument")+ fName+".js";
		File file = new File(OutputFile);
		file.getParentFile().mkdirs();
		FileWriter fileWriter = new FileWriter(OutputFile);
	    fileWriter.write(node.toSource().toString());
	    fileWriter.close();
	  
	}

	public static void main(String arg[]) throws IOException {
		
		List<JsFile> jsFiles = new ArrayList<JsFile>();
		
		String path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/js";
		
		jsFiles.add(new JsFile(path,"/insertAtCaret"));
		jsFiles.add(new JsFile(path,"/itm_local_storage"));
		jsFiles.add(new JsFile(path,"/itm"));
		jsFiles.add(new JsFile(path,"/wordcloud2"));
		jsFiles.add(new JsFile(path,"/websocket"));
		
		path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/js/thread";
		
		jsFiles.add(new JsFile(path,"/super_talk"));
		jsFiles.add(new JsFile(path,"/thread"));
		
		
		JSInstrument ins =  new JSInstrument();
		for (int i = 0; i<jsFiles.size();i++) {
			ins.instrument(jsFiles.get(i).path, jsFiles.get(i).fName);
		    System.out.println("done");
		}
	}
}


class JsFile {
	String path;
	String fName;
	JsFile(String path, String fName){
		this.path = path;
		this.fName = fName;
	}
}