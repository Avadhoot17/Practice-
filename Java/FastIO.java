import java.io.*;
import java.util.*;

public class FastIO {

    /* ---------------- FAST SCANNER ---------------- */   

    static class FastScanner {

        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
                arr[i] = nextInt();
            return arr;
        }

        long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];
            for(int i=0;i<n;i++)
                arr[i] = nextLong();
            return arr;
        }
    }

    /* ---------------- CONSTANTS ---------------- */

    static final long MOD = 1000000007;

    /* ---------------- MATH UTILS ---------------- */

    static long gcd(long a, long b){
        while(b!=0){
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    static long lcm(long a,long b){
        return (a*b)/gcd(a,b);
    }

    static long power(long a,long b){
        long res = 1;
        while(b>0){
            if((b&1)==1) res*=a;
            a*=a;
            b>>=1;
        }
        return res;
    }

    /* ---------------- DEBUG ---------------- */

    static void printArray(int[] arr){
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
    }

    /* ---------------- MAIN ---------------- */

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();
        int m = fs.nextInt();

        int[] arr = fs.nextIntArray(n);

        long sum = 0;
        for(int x:arr)
            sum += x;

        out.println("Numbers: "+Arrays.toString(arr));
        out.println("Sum of array = "+sum);
        out.println("n + m = "+(n+m));

        out.flush();
    }
}
