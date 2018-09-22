package example.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

public class DeserializeUser {

    public static void main(String[] args) {
        try {
            // Deserialize Users from disk
            DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
            DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("users.avro"), userDatumReader);
            User user = null;
            while (dataFileReader.hasNext()) {
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        } catch (IOException ex) {
            System.out.println("unable to serialize User");
            ex.printStackTrace();
        }
    }
}
