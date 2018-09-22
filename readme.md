* Serialization:
mvn compile -q exec:java -Dexec.mainClass=example.avro.SerializeUser

* Deserialization: 
mvn compile -q exec:java -Dexec.mainClass=example.avro.DeserializeUser

* Usage without code-generation:
mvn compile -q exec:java -Dexec.mainClass=example.avro.WithoutCodeGeneration