base=src/com/grosa

all:
	mkdir bin
	find . -name "*.java" -print | xargs javac -d bin
	echo "java -cp bin com.grosa.Main" > escalona
	chmod +x escalona

clean:
	rm -rf bin escalona