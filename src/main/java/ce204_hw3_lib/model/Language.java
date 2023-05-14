package ce204_hw3_lib.model;

/**
 * @brief Represents a programming language with its corresponding file extension, execute command and compile command
 */
public enum Language {
	/**
	 * @brief Java language with its file extension, execute command, and compile command
	 * @brief C# language with its file extension, execute command, and compile command
	 * @brief C++ language with its file extension, execute command, and compile command
	 */
	JAVA("Java", ".java", "java ", "javac "),
	C_SHARP("C#", ".cs", "", "g++ "),
	C_PLUS_PLUS("C++", ".cpp", "", "csc "),
	;
	
	/**
	 * @brief The display name of the programming language
	 * @brief The file extension of the programming language
	 * @brief The command to execute a file in the programming language
	 * @brief The command to compile a file in the programming language
	 */
	public String displayName;
	public String extension;
	public String executeCommand;
	public String compileCommand;
	
	/**
	 * @brief Constructor for the Language 
	 * @param displayName The display name of the programming language
	 * @param extension The file extension of the programming language
	 * @param executeCommand The command to execute a file in the programming language
	 * @param compileCommand The command to compile a file in the programming language
	 */
	Language(String displayName, String extension, String executeCommand, String compileCommand) {
		this.displayName = displayName;
		this.extension = extension;
		this.executeCommand = executeCommand;
		this.compileCommand = compileCommand;
	}
	
	/**
	 * @brief Returns the display name of the programming language
	 * @return The display name of the programming language
	 */
	
	@Override
	public String toString() {
		return displayName;
	}

}
