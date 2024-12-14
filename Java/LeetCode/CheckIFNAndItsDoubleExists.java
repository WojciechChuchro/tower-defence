// https://leetcode.com/problems/check-if-n-and-its-double-exist/
class CheckIfNAndItsDoubleExits {

    public static void main(String[] args) {
        CheckIfNAndItsDoubleExits cinaide = new CheckIfNAndItsDoubleExits();
        int[] arr = { 10, 44, 10, 8, 45, 34, 30, 22, 3, 42, 42 };
        System.out.println(cinaide.checkIfExist(arr));
    }

    public boolean checkIfExist(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
               if(i == j)  continue;
               if(arr[i] == arr[j] * 2) return true;
            }
        }
        
        return false;
    }
}
