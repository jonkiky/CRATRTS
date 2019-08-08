package com.cart.instrument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestFromSource {

	public static void main(String arg[]) throws IOException {

		String path = "/Users/cheny39/Documents/JAVA/ITMInstrumented/WebContent/js";
		String fname = "/TEST.JS";
		String simpleSource  ="";
		try(BufferedReader br = new BufferedReader(new FileReader(path+fname))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		     simpleSource = sb.toString();
		}


		System.out.print(new InstumentExecutor().instrumentedCode(simpleSource, fname + "jsp"));

	}
}
