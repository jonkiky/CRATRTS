package com.cart.instrument;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;

public class InstumentExecutor {
	
	public String instrumentedCode (String source , String nameSpace) {
		
		AstInstrumenter instrumenter = new AstInstrumenter();
		
		instrumenter.setScopeName(nameSpace);
		
		CompilerEnvirons compilerEnvirons = new CompilerEnvirons();
		
		Parser p = new Parser(compilerEnvirons, null);
		
		AstNode node = p.parse(source, null, 0); 
		
		node.visit(instrumenter);
		
		instrumenter.finish((AstRoot)node);
		
		return node.toSource().toString();
		
	}
}
