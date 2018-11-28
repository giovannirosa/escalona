all:
	rm -rf bin escalona
	mkdir bin
	find . -name "*.java" -print | xargs javac -d bin
	find . -name "*.java" -print | xargs javadoc -d bin
	echo "java -cp bin com.grosa.Main" > escalona
	chmod +x escalona

clean:
	rm -rf bin escalona