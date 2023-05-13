package ce204_hw3_lib.model;

public enum Language {
	
	JAVA("Java", ".java", "java ", "javac "),
	C_SHARP("C#", ".cs", "", "g++ "),
	C_PLUS_PLUS("C++", ".cpp", "", "csc "),
	;

	public String displayName;
	public String extension;
	public String executeCommand;
	public String compileCommand;
	
	Language(String displayName, String extension, String executeCommand, String compileCommand) {
		this.displayName = displayName;
		this.extension = extension;
		this.executeCommand = executeCommand;
		this.compileCommand = compileCommand;
	}
	
	@Override
	public String toString() {
		return displayName;
	}

}
