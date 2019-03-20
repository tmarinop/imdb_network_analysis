import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;



public class imdbproject {


    public static void main(String[] args) throws IOException {
        String fileName = "/Users/theoktistimarinopoulou/Documents/master/imdb_actors.csv";
        String file = "/Users/theoktistimarinopoulou/Documents/master/nodes.csv";
        String nfile = "/Users/theoktistimarinopoulou/Documents/master/ids.csv";
        String edges = "/Users/theoktistimarinopoulou/Documents/master/edges.csv";

        String print="";
        String sumedge="";


        // This will reference one line at a time
        String line = null;
        String line2 = null;
        String line3 = null;
        HashMap<String, String> hmap = new HashMap<String, String>();



        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            FileReader fileReader2 = new FileReader(file);
            FileReader fileReader3 = new FileReader(nfile);



            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);




            while ((line2 = bufferedReader2.readLine()) != null) {
                //System.out.println(line2);
                line2 = line2.replaceAll("\\s","");
                String[] parts = line2.split(",");
                //System.out.println(parts[1] +" " +parts[0]);
                hmap.put(parts[1],parts[0]);
                //System.out.println(Arrays.toString(parts));
            }

            while ((line = bufferedReader.readLine()) != null) {

                line = line.replaceAll("\\s", "");
                line = line.replaceAll("\"", "");
                String[] splitted = line.split(",");
                int len = splitted.length;
                int i = 0;
                while (len > 0) {

                    //System.out.println(splitted[i]);

                    for (Map.Entry m : hmap.entrySet()) {

                        //System.out.println("split" + splitted[i]);
                        //System.out.println("k" + m.getKey());

                        if (splitted[i].equals(m.getKey().toString())) {
                            //System.out.println(m.getValue().toString());
                            BufferedWriter writer = new BufferedWriter(new FileWriter(nfile));
                            if(len>0 && len!=1){
                                print=print+m.getValue().toString()+",";
                                writer.write(print);
                            }
                            if(len==1) {
                                print = print + m.getValue().toString() + "\n";
                                writer.write(print);
                            }

                            writer.close();

                        }


                        //System.out.println(m.getKey());
                    }



                    len--;
                    i++;

                }

                // System.out.println(m.getKey()+" "+m.getValue());
                // System.out.println(line);
            }

            while((line3 = bufferedReader3.readLine()) != null) {
                String ed[]=line3.split(",");
                int leng=ed.length;
                for(int i=0; i<=leng; i++){

                    for(int j=i+1; j<leng; j++){
                        BufferedWriter wr = new BufferedWriter(new FileWriter(edges));
                        sumedge=sumedge + ed[i]+"," +ed[j] +"\n";
                        wr.write(sumedge);
                        wr.close();

                    }
                    //leng--;
                }

            }


            // Always close files.
            bufferedReader3.close();
            bufferedReader2.close();
            bufferedReader.close();


        } catch (FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }





    }

}

