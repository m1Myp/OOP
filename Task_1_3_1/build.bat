javadoc -d .\batch\doc\ -charset utf-8 -sourcepath .\src\main\java\ -subpackages ru.nsu.fit.oop.lab2

javac -sourcepath .\src\main\java\ -d .\batch\bin\ .\src\main\java\ru\nsu\fit\oop\lab3\SubstringSearch.java

mkdir .\batch\jar\
jar cf .\batch\jar\SubstringSearch.jar -C .\batch\bin\ .
