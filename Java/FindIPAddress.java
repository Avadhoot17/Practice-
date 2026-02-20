import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class FindIPAddress {

    public static void main(String[] args) {

        try {
            // Get local host
            InetAddress localHost = InetAddress.getLocalHost();

            System.out.println("Host Name: " + localHost.getHostName());
            System.out.println("Local IP Address: " + localHost.getHostAddress());

            System.out.println("\nAll Network IP Addresses:");

            // Get all network interfaces
            Enumeration<NetworkInterface> networkInterfaces = 
                    NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();

                Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress address = inetAddresses.nextElement();

                    if (!address.isLoopbackAddress()) {
                        System.out.println("Interface: " + ni.getName());
                        System.out.println("IP Address: " + address.getHostAddress());
                        System.out.println("-----------------------------------");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
