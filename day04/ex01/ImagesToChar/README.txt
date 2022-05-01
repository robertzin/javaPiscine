# Delete existing target directory
rm -rf target

# Creating a directory for results
mkdir target

# Specify where to put compiled files
javac -d target src/java/edu/school21/printer/*/*.java

# Copying resource directory
cp -R src/resources target/.

# Creating jar. file using manifest
jar cfm target/images-to-chars-printer.jar  src/manifest.txt -C target .

# Grant privileges to jar. file
chmod u+x target/images-to-chars-printer.jar

# Executing jar. file
java -jar target/images-to-chars-printer.jar . 0