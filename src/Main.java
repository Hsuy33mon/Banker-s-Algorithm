import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static String file = "src/deadlock.txt";
//        private static String file = "src/data1.txt";
    private static List<String> processes = new ArrayList<>();
    private static List<int[]> allocation = new ArrayList<>();
    private static List<int[]> max = new ArrayList<>();
    private static int[] available = null;
    private static int[] totalResources = null;
    private static int[] totalAllocation;
    private static List<int[]> need = new ArrayList<>();
    private static int count = 0;
    private static List<String> result = new ArrayList<>();
    private static boolean[] finished ;

    public static void main(String[] args) throws IOException {
        readFile(file);
        finished = new boolean[processes.size()];

        for(int i = 0; i < allocation.size(); i++) {
            int[] needrow = new int[allocation.get(i).length];
            for(int j =0; j<allocation.get(i).length; j++) {
                needrow[j] = (max.get(i)[j] - allocation.get(i)[j]);
            }
            need.add(needrow);
        }

        System.out.println("Available Matrix : ");
        System.out.println(Arrays.toString(available));

        System.out.println();
        System.out.println("Need Matrix : ");
        for (int[] rowArr : need) {
            System.out.println(Arrays.toString(rowArr));
        }

        System.out.println("Allocation Matrix : ");
        for (int[] rowArr : allocation) {
            System.out.println(Arrays.toString(rowArr));
        }

        System.out.println("Max Matrix : ");
        for (int[] rowArr : max) {
            System.out.println(Arrays.toString(rowArr));
        }

        while (!ifZero(need)){
            boolean progressMade = false;
            for(int i = 0; i < need.size(); i++) {
                if(finished[i]) continue;

                boolean canExecute = true;
                for(int j = 0; j < need.get(i).length; j++) {
                    if (need.get(i)[j] > available[j]) {
                        canExecute = false;
                        break;
                    }
                }

                if(canExecute){
                    for(int j = 0; j < available.length; j++) {
                        available[j] = available[j] + allocation.get(i)[j];
                    }
                    result.add(processes.get(i));
                    Arrays.fill(need.get(i),0);
                    finished[i] = true;
                    progressMade = true;
                    System.out.println("------------");
                    System.out.println("Need Matrix : ");
                    for (int[] rowArr : need) {
                        System.out.println(Arrays.toString(rowArr));
                    }
                    System.out.println("------------");
                    System.out.println("Available Matrix : ");
                    System.out.println(Arrays.toString(available));
                }
            }
            if (!progressMade) {
                System.out.println("Deadlock detected: No process can proceed");
                System.out.println(result);
                return;
            }
        }
        System.out.println("Safe Sequence"+result);
    }

    public static void readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();  //skip the first line

        String[] resources = br.readLine().trim().split("\\s+");
        count = resources.length / 3;
        totalResources = new int[count];
        available = new int[count];
        totalAllocation = new int[count];
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.trim().split("\\s+");
            int[] allocRow = new int[count];
            int[] maxRow = new int[count];
            processes.add(data[0]);

            for (int i = 0; i < count; i++) {
                allocRow[i] = Integer.parseInt(data[i + 1]);
                maxRow[i] = Integer.parseInt(data[i + 1 + count]);
            }

            allocation.add(allocRow);
            max.add(maxRow);

            int ifResources = (count*2)+1;
            if (data.length > ifResources) {
                for (int j = 0; j < count; j++) {
                    totalResources[j] = Integer.parseInt(data[ifResources]);
                    ifResources++;
                }
            }
        }
        for (int i = 0; i < allocation.size(); i++) {
            for (int j = 0; j < allocation.get(i).length; j++) {
                totalAllocation[j] += allocation.get(i)[j];
            }
        }

        for (int i = 0; i < count; i++) {
            available[i] = totalResources[i] - totalAllocation[i];
        }
    }

    public static boolean ifZero(List<int[]> need){
        int rowIndex = 0;
        while (rowIndex < need.size()) {
            int[] row = need.get(rowIndex);
            for(int val: row){
                if(val != 0){
                    return false;
                }
            }
            rowIndex++;
        }
        return true;
    }

}
