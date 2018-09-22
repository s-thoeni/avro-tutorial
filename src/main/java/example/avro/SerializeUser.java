package example.avro;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SerializeUser {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("Anna");
        user1.setFavoriteNumber(256);

        // Leave favorite color null

        // Alternate constructor
        User user2 = new User("Stefan", 1337, "Red");


        // Construct via builder
        User user3 = User.newBuilder()
                .setName("Simon")
                .setFavoriteColor("Blue")
                .setFavoriteNumber(null)
                .build();
        serializeUser(user1, user2, user3);
    }


    private static void serializeUser(User ... user) {
        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        try {
            dataFileWriter.create(user[0].getSchema(), new File("users.avro"));
            // If we want to append instead of creating a new File remove the line above and add:
            // dataFileWriter.appendTo(new File("users.avro"));
            for(User u : user){
                dataFileWriter.append(u);
            }
            dataFileWriter.close();
        }catch (IOException ex) {
            System.out.println("unable to serialize User");
            ex.printStackTrace();
        }
    }
}
