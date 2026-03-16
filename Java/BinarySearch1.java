public class BinarySearch1 {

    static int binarySearch(int arr[], int key){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(arr[mid] == key)
                return mid;
            else if(arr[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {

        int arr[] = {2,4,6,8,10,12};
        int result = binarySearch(arr,10);

        System.out.println("Index: " + result);
    }
}
