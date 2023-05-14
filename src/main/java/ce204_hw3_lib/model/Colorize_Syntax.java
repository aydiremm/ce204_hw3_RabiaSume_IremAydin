package ce204_hw3_lib.model;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 * @class Colorize_Syntax
 * @brief This class provides methods to set syntax highlighting for code written in different programming languages.
 */
public class Colorize_Syntax {
	/**
     * @brief Sets syntax highlighting for Java code in the given RSyntaxTextArea component.
     * @param text The RSyntaxTextArea component to apply the syntax highlighting to.
     */
	public void javaSyntax(RSyntaxTextArea text) {
		text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

	}
	/**
     * @brief Sets syntax highlighting for C# code in the given RSyntaxTextArea component.
     * @param text The RSyntaxTextArea component to apply the syntax highlighting to.
     */
	
	public void csSyntax(RSyntaxTextArea text) {
		text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);

	}
	/**
     * @brief Sets syntax highlighting for C++ code in the given RSyntaxTextArea component.
     * @param text The RSyntaxTextArea component to apply the syntax highlighting to.
     */

	public void cppSyntax(RSyntaxTextArea text) {
		text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);

	}
}
