package hu.swing.controls.jgrid;

import org.junit.jupiter.api.Test;

import java.io.*;

class JGridTest {

    void init() throws FileNotFoundException {
        System.out.println("@BeforeEach - executes before each test method in this class");
        File file = new File("src/test/resource/firstname.txt");
        if(file.exists()){
            System.out.println("exists");
            try(FileReader fr=new FileReader(file)){
                BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
                String line;
                while((line=br.readLine())!=null)
                {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }




        }
/*        InputStream is = TestEntity.class.getClassLoader().getResourceAsStream("firstname.txt");
        try( BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
            while(reader.ready()) {
                String line = reader.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Test
    void testSingleSuccessTest() {
        System.out.println("testSingleSuccessTest");
    }
}