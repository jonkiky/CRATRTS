package com.cart.instrument;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;


public class AstInstrumenterTest {

	public static void main(String arg[]) {
		String angularSource ="app.controller('myCtrl', function($scope) {\r\n" + 
				"  $scope.firstName= \"John\";\r\n" + 
				"  $scope.lastName= \"Doe\";\r\n" + 
				"});";
		String simpleSource =" $(\"#kf6_login\").click(function(){\r\n" + 
				"	   if(window.location.protocol==\"http:\"){   \r\n" + 
				"		window.location.href =\"/IIUSs/kf6/login.jsp\"\r\n" + 
				"	   }else{\r\n" + 
				"		   window.location.href =\"/IIUSs/kf6/login.jsp\"  \r\n" + 
				"	   }\r\n" + 
				"   })\r\n" + 
				"   \r\n" + 
				"	function validateForm() {\r\n" + 
				"	    if ($(\"#account\").val()==\"\") {\r\n" + 
				"	        Materialize.toast('User name is null', 4000)\r\n" + 
				"	        return false;\r\n" + 
				"	    }\r\n" + 
				"	    if ($(\"#password\").val()==\"\") {\r\n" + 
				"	    	 Materialize.toast('Password is null', 4000)\r\n" + 
				"	        return false;\r\n" + 
				"	    }\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	var getUrlParameter = function getUrlParameter(sParam) {\r\n" + 
				"	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),\r\n" + 
				"	        sURLVariables = sPageURL.split('&'),\r\n" + 
				"	        sParameterName,\r\n" + 
				"	        i;\r\n" + 
				"\r\n" + 
				"	    for (i = 0; i < sURLVariables.length; i++) {\r\n" + 
				"	        sParameterName = sURLVariables[i].split('=');\r\n" + 
				"\r\n" + 
				"	        if (sParameterName[0] === sParam) {\r\n" + 
				"	            return sParameterName[1] === undefined ? true : sParameterName[1];\r\n" + 
				"	        }\r\n" + 
				"	    }\r\n" + 
				"	};\r\n" + 
				"	\r\n" + 
				"	var status =getUrlParameter('flag');\r\n" + 
				"	if(status==\"fails\"){\r\n" + 
				"		 Materialize.toast(\"Wrong user name or password\", 4000)\r\n" + 
				"		 \r\n" + 
				"	}";

		AstInstrumenter instrumenter = new AstInstrumenter();
		instrumenter.setScopeName("test");
		CompilerEnvirons compilerEnvirons = new CompilerEnvirons();
		Parser p = new Parser(compilerEnvirons, null);
		AstNode node = p.parse(simpleSource, null, 0); 
		node.visit(instrumenter);
		
		instrumenter.finish((AstRoot)node);
		System.out.print(node.toSource());
		
	}
	
//	/**
//	 * Generate a simple JavaScript AST.
//	 * 
//	 * @return The AST.
//	 */
//	private AstNode createAst(String source) {
//		Parser p = new Parser(compilerEnvirons, null);
//
//		return p.parse(source, null, 0);
//	}
////
//
//
//
//	/**
//	 * Test instrumentation initialization with simple input.
//	 */
//	@Test
//	public void finishSimple() {
//		AstRoot node = (AstRoot) createAst(simpleSource);
//
//		instrumenter.finish(node);
//
//		assertFalse("Should contain instrumentation initalization", equalSources(node.toSource(),
//		        simpleSource));
//	}
//
//	/**
//	 * Test visit with empty input.
//	 */
//	@Test
//	public void visitEmpty() {
//		AstRoot node = new AstRoot();
//
//		node.visit(instrumenter);
//
//		assertTrue("Should be empty", node.toSource().equals(""));
//	}
//
//	/**
//	 * Test visit with all kinds of input.
//	 */
//	@Test
//	public void visitSimple() {
//		/* all of these have weird instrumentation code like adding a pair of parenthesis */
//		String[] sources =
//		        new String[] { "function(a) { while(true) return false; }",
//		                "function test(a) { if(true) return true; }",
//		                "function(a) { if(true) return true; else return false; }",
//		                "function(a) { return false; }",
//		                "function(a) { for(i = 0; i < 1; i++) return false; }" };
//
//		for (int i = 0; i < sources.length; i++) {
//			AstNode node = createAst(sources[i]);
//
//			node.visit(instrumenter);
//
//		}
//
//	}
//
//	/**
//	 * Check if the excludes work.
//	 */
//	@Test
//	public void visitExcludes() {
//		String source = "function(b) { return b; }";
//		AstNode node = createAst(source);
//		List<String> excludes = new ArrayList<String>();
//		excludes.add("b");
//		AstInstrumenter excludeInstrumenter = new AstInstrumenter(excludes);
//		excludeInstrumenter.setScopeName("testscope");
//
//		node.visit(excludeInstrumenter);
//
//		assertTrue("Should not contain instrumentation code", equalSources(node.toSource(),
//		        source));
//	}
//
//	private boolean equalSources(String s1, String s2) {
//		return parse(s1).toSource().equals(parse(s2).toSource());
//	}
//
//	/**
//	 * Create an AST from the passed js.
//	 * 
//	 * @param js
//	 *            The javascript to be parsed.
//	 * @return The AST.
//	 */
//	private AstNode parse(String js) {
//		Parser p = new Parser(compilerEnvirons, null);
//
//		return p.parse(js, null, 0);
//	}
}