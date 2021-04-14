import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Decompress {
    private static String[] commandLineInput;
    private List<String> characterCode;
    private String filename;
    private StringBuilder output= new StringBuilder();
    String buffer="";
    private List<String> characterCode2;
    private String binCode="";
    int n;
    long totalTime = 0;
    long start;
    long tmp;

    public static void main(String[] args) {
        commandLineInput=args;
        new Decompress();
    }

    public Decompress() {
        characterCode = new ArrayList<>();
        characterCode2 = new ArrayList<>();
        filename= commandLineInput[0];
        File f=new File(filename);
        System.out.print("Reading file header...");
        start = System.currentTimeMillis();
        try(var fis = new FileInputStream(filename)) {
            String real = "";
            boolean notNextLine=true;
            boolean hasNext=true;
            while(hasNext) {
                while (notNextLine) {
                    char k = (char) fis.read();
                    if(k == '\n'){
                        notNextLine=false;
                        break;
                    }
                    real+=Character.toString(k);
                    if(real.equals("*****")){
                        hasNext=false;
                    }

                }
                notNextLine=true;
                characterCode.add(real);
                real="";
            }
            notNextLine=true;
            String nString = "";
            while (notNextLine) {
                char k = (char) fis.read();
                if(k == '\n'){
                    break;
                }
                nString+=Character.toString(k);
                notNextLine = k != '\n';
            }
            nString.replace('\n','0');
            n=Integer.parseInt(nString);
            int m=n/8;
            if(n%8==1){
                m+=1;
            }
            for(int i=0;i<m;i++){
                int b=fis.read();
                String bin=Integer.toBinaryString(b);
                int numZeros=8-bin.length();
                String padding="0".repeat(numZeros);
                bin=padding+bin;
                buffer +=bin;
            }
        } catch (IOException e){
            System.out.println("error in reading file");
        }
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");
        characterCode.remove("*****");
        System.out.print("Reading file body...");
        start = System.currentTimeMillis();
        for (int i = 0; i<characterCode.size(); i++){
            var chunks = characterCode.get(i).split(" ");
            characterCode2.add(chunks[0]);
            characterCode2.add(chunks[1]);
        }
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");

        convertingBin(buffer);
    }

    public void convertingBin(String inputBinaryString){
        System.out.print("Decrypting file...");
        start = System.currentTimeMillis();
        int pivot=0;
        while(pivot<inputBinaryString.length()) {
            int i=pivot;
            for(int j=i+1;j<=inputBinaryString.length();j++) {
                String singleByte = inputBinaryString.substring(i,j);
                if(sequentialSearch(singleByte)==true){
                    pivot=j;
                    break;
                }
                if(j == inputBinaryString.length()){
                    System.out.println(singleByte+"are not found");
                    pivot=inputBinaryString.length();
                }
            }
        }
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");
        String t=filename.replace(".zip301","2");
        String outFilename = t+".txt";
        try (FileWriter fw = new FileWriter(outFilename)) {
            String tmp=output.toString();
            fw.write(tmp);
            System.out.println("Wrote output to: " + outFilename);
            System.out.println("TOTAL TIME: " + (totalTime / 1000.0) + " seconds.");
        }catch (IOException e){
            System.out.println("Error in building file");
            e.printStackTrace();
        }
    }

    public boolean sequentialSearch(String lookUp){
        int p=0;
        for(int i=0;i<characterCode2.size();i+=2){
            String code=characterCode2.get(i);
            if(code.equals(lookUp)){
                p= characterCode2.indexOf(code);
                String tmp=characterCode2.get(p+1);
                if(tmp.equals("space")){
                    output.append(" ");
                }else if(tmp.equals("newline")){
                    output.append("\n");
                }
                else if(tmp.equals("tab")) {
                    output.append("\t");
                } else if(tmp.equals("return")) {
                    output.append("\r");
                }else  {
                    output.append(tmp);
                }
                return true;
            }
        }
        return false;
    }
}











