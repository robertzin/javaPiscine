# Creating a directory for results
mkdir target

# Specify where to put compiled files
javac -d ./target src/java/edu/school21/printer/*/*.java

# Specify where to find user class files
java -classpath target edu.school21.printer.app.Program . 0 /Users/robert_zin/Downloads/it.bmp

