##README GOES HERE AS SPECIFIED IN THE ASSIGNMENT BRIEF


7\)
a)  if on windows OS: Download the binary zip archive "apache-maven-3.6.0-bin.zip" from the maven website,
    add maven to the environment Variables in "path", 
    Use command line and access assignment1 then type "mvn jdepend:generate"
    
b)  I opened the jdepend report in a browser and looked in the Cycles section. The dependencies should be correct if there are no cyclic dependencies.

c)  1) Store DB in local repo with: mvn install:install-file -Dfile=lib\studentmemdb-1.0.0.jar -DgroupId=nz.ac.vuw.swen301 -DartifactId=studentmemdb -Dversion=1.0.0 -Dpackaging=jar
    2) Package assignment with maven-assembly-plugin
    3) build the jar with: mvn assembly:single
    4) run: java -jar target/studentfinder.jar "id42"
    
8\)  Static vars and methods should be minimised in order for the garbage collection to restore memory allocations.
    Connections between the DB should be closed to lessen memory leakage. No .close() causes memory leakage because the statements and the connections to occupy the heap space
    
    